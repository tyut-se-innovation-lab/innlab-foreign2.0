<template>
  <el-card style="max-width: 100%;">
    <template #header>
      <div class="card-header">
        <span>用户管理</span>
        <el-button type="primary" style="position: absolute;right:20px"
          @click="addUserFormVisible = true">新增用户</el-button>
      </div>
      
    </template>
    <el-table :data="tableData" style="width: 100%" v-loading="loading" element-loading-text="加载中">
      <el-table-column prop="userAccount" label="用户名" width="200" />
      <el-table-column prop="userName" label="实名" width="200" />
      <el-table-column prop="userDepaerment" label="部门" width="200" />
      <el-table-column prop="lastLoginTime" label="最近登录时间" />
      <el-table-column label="状态" prop="userState">
        <template #default="scope">
          <el-switch v-model="scope.row.userState" active-color="#13ce66" inactive-color="#ff4949"
            @click="changeState(scope.row)"></el-switch>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="250">
        <template #default="scope">
          <el-button type="primary" size="small" @click="assignRolesClick(scope.row)" round>
            分配角色
          </el-button>
          <el-button type="warning" size="small" @click="ResetPwdClick(scope.row)" round>
            重置密码
          </el-button>
          <el-button type="danger" size="small" @click="handleDeleteClick(scope.row)" round>
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 切换角色弹窗 -->
    <el-dialog v-model="centerDialogVisible" title="切换角色" width="500" center>
      <el-form :model="form" style="height: 500px;">
        <el-form-item label="姓名">
          <el-input v-model="form.userName" autocomplete="off" />
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="form.userPhone" autocomplete="off" />
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.userEmail" autocomplete="off" placeholder="暂无邮箱设置" />
        </el-form-item>
        <el-form-item label="角色" prop="form.userRole">
          <el-select v-model="form.userRole" placeholder="Please select a zone" multiple filterable allow-create
            default-first-option :reserve-keyword="false">
            <el-option v-for="(item, index) in form.userRole" :key="index" :value="item" :label="item" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="centerDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="centerDialogVisible = false">
            成功
          </el-button>
        </div>
      </template>
    </el-dialog>
    <!-- 重置密码窗口 -->
    <el-dialog v-model="resetPwdDialogVisible" title="重置密码" width="500" center>
      <el-form :model="form" style="height: 500px;">
        <el-form-item label="姓 名" style="margin-left: 20px">
          <el-input v-model="resForm.name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="修改密钥">
          <el-input v-model="resForm.password" autocomplete="off" type="password" show-password />
        </el-form-item>
        <el-form-item label="重置密码">
          <el-input v-model="resForm.repassword" autocomplete="off" type="password" show-password />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="resetPwdDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="resFormfirm()">
            确认
          </el-button>
        </div>
      </template>
    </el-dialog>
    <!-- 新增用户窗口 -->
    <el-dialog v-model="addUserFormVisible" title="新增用户" width="500" center>
      <el-form :model="addUserForm" style="height: 500px;">
        <el-form-item label="账 号" style="margin-left: 20px">
          <el-input v-model="addUserForm.userAccount" autocomplete="off" />
        </el-form-item>
        <el-form-item label="密 码" style="margin-left: 20px">
          <el-input v-model="addUserForm.userPassword" autocomplete="off" type="password" show-password />
        </el-form-item>
        <el-form-item label="姓 名" style="margin-left: 20px">
          <el-input v-model="addUserForm.userName" autocomplete="off" />
        </el-form-item>
        <el-form-item label="电 话" style="margin-left: 20px">
          <el-input v-model="addUserForm.userPhone" autocomplete="off" />
        </el-form-item>
        <el-form-item label="部 门" style="margin-left: 20px">
          <el-input v-model="addUserForm.userDepartment" autocomplete="off" />
        </el-form-item>
        <el-form-item label="备 注" style="margin-left: 20px">
          <el-input v-model="addUserForm.remark" type="textarea" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="addUserFormVisible = false">取消</el-button>
          <el-button type="primary" @click="addUserfirm">
            确认
          </el-button>
        </div>
      </template>
    </el-dialog>
  </el-card>
  <el-pagination background layout="total, sizes, prev, pager, next, jumper" @size-change="handleSizeChange"
    @current-change="handleCurrentChange" :current-page="currentPage" :page-size="pageSize"
    :page-sizes="[10, 20, 30, 40]" :total="userTotal" style="margin-top: 20px;margin-left:10px" />
</template>

<script setup>
defineOptions({ name: 'UserMgt' })
import api from './api'
import { onMounted, onUpdated, reactive, ref } from 'vue'
import { formRules } from "./utils/rules";
//定义table数据
const tableData = ref([])
//加载动画的显示
const loading = ref(false)
//分配角色弹窗显示
let centerDialogVisible = ref(false)
const form = reactive({
  userName: '',
  userPhone: '',
  userEmail: '',
  userRole: [],
})
//重置密码弹窗显示
let resetPwdDialogVisible = ref(false)
const resForm = reactive({
  "userId": "",
  "name": "",
  "password": "",
  "repassword": ""
})
//新增用户窗口
const addUserForm = reactive({
  "userAccount": "",
  "userPassword": "",
  "userName": "",
  "userPhone": "",
  "userDepartment": "",
  "remark": ""
})
let addUserFormVisible = ref(false)
//返回数据总数
let userTotal = ref(0)
//返回数据每页显示的数量
const pageSize = ref(10)
//当前页码
const currentPage = ref(1)
onMounted(async () => {
  loading.value = true
  await api.read({ pageNum: currentPage, pageSize: pageSize }).then(res => {
    userTotal.value = res.data.total
    console.log(res.data)
    res.data.records.forEach(item => {
      tableData.value.push(item)
    })
  })
  loading.value = false
})
onUpdated(async () => {
  loading.value = true
  await api.read({ pageNum: currentPage, pageSize: pageSize }).then(res => {
    userTotal.value = res.data.total
    tableData.value = []
    res.data.records.forEach(item => {
      tableData.value.push(item)
    })
  })
  loading.value = false
})
const changeState = async (row) => {
  //停用
  if (!row.userState) {
    await api.downUserState({ userId: row.userId }).then(res => {
      $message.warning('停用成功')
    })
  } else {
    await api.upUserState({ userId: row.userId }).then(res => {
      $message.success('启用成功')
    })
  }
}
//重置密码
const ResetPwdClick = (row) => {
  resForm.userId = row.userId
  resForm.name = row.userName
  resetPwdDialogVisible.value = true
}
//重置密码确认按钮函数
const resFormfirm = async () => {
  api.resetPwd({ userId: resForm.userId, userPassword1: resForm.password, userPassword2: resForm.repassword }).then(res => {
    $message.success('修改成功')

  })
  resetPwdDialogVisible.value = false
}
//删除用户
const handleDeleteClick = (row) => {
  api.delete({ userId: row.userId }).then(async (res) => {
    loading.value = true
    tableData.value = []
    await api.read({ pageNum: currentPage, pageSize: pageSize }).then(res => {
      userTotal.value = res.data.total
      res.data.records.forEach(item => {
        tableData.value.push(item)
      })
    })
    loading.value = false
    $message.success('删除成功')
  })
}

//新增用户确认按钮
const addUserfirm = async () => {
  await api.create({
    userAccount: addUserForm.userAccount,
    userPassword: addUserForm.userPassword,
    userName: addUserForm.userName,
    userPhone: +addUserForm.userPhone,
    userDepartment: addUserForm.userDepartment,
    remark: addUserForm.remark
  }).then(async (res) => {
    loading.value = true
    $message.success('新增成功')
    addUserFormVisible.value = false
    tableData.value = []
    await api.read({ pageNum: currentPage, pageSize: pageSize }).then(res => {
      res.data.records.forEach(item => {
        tableData.value.push(item)
      })
    })
    loading.value = false
  })
}
//分配角色
const assignRolesClick = async (row) => {
  console.log(row);
  form.userName = row.userName
  await api.getUserInfo({ userId: row.userId }).then(res => {
    form.userPhone = res.data.userPhone || []
    form.userEmail = res.data.userEmail || []
    console.log(res);
  })
  await api.assignRoles({ userId: row.userId }).then((res) => {
    res.data.forEach(el => {
      form.userRole = []
      form.userRole.push(el.roleName)
      console.log(el);
    })
  })
  centerDialogVisible.value = true
}
function handleSizeChange(val) {
  pageSize.value = val;
  currentPage.value = 1; // 当改变每页显示数量时，重置当前页码为第一页
}

// 处理当前页码变化
function handleCurrentChange(val) {
  currentPage.value = val;
}

</script>
<style scoped>
.main {
  height: 800px !important;
}
</style>