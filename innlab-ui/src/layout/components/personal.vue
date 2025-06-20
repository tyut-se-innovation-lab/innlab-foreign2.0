<template>
  <div class="personal">
  <el-dropdown
    placement="bottom-end"
    class="h-p-100 flex-center"
  >
    <div class="flex-center">
      <span style="margin-right: 8px">{{ userInfo.nickname }}</span>
      <el-avatar :size="35" :src="userInfo.avatarUrl" />
    </div>
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item @click="openPersonal"
          ><span class="fz-12">个人中心</span></el-dropdown-item
        >
        <el-dropdown-item @click="passWordDialogVisible = true"
          ><span class="fz-12">修改密码</span></el-dropdown-item
        >
        <el-dropdown-item @click="loginOut"
          ><span class="fz-12">退出</span></el-dropdown-item
        >
      </el-dropdown-menu>
    </template>
  </el-dropdown>
  <el-dialog
    v-model="passWordDialogVisible"
    title="修改密码"
    width="500"
    align-center
    center
  >
  <el-form
    ref="ruleFormRef"
    style="max-width: 600px"
    :model="ruleForm"
    status-icon
    :rules="rules"
    label-width="auto"
    class="demo-ruleForm"
  >
    <el-form-item label="原密码" prop="oldpass">
      <el-input v-model="ruleForm.oldpass" type="password" autocomplete="off" />
    </el-form-item>
    <el-form-item label="新密码" prop="pass">
      <el-input v-model="ruleForm.pass" type="password" autocomplete="off" />
    </el-form-item>
    <el-form-item label="确认新密码" prop="checkPass">
      <el-input
        v-model="ruleForm.checkPass"
        type="password"
        autocomplete="off"
      />
    </el-form-item>
  </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button
          @click="passWordDialogVisible = false"
          :disabled="loading"
        >
          取消
        </el-button>
        <el-button
          type="primary"
          @click="submitForm()"
          :loading="loading"
        >
          确定
        </el-button>
      </div>
    </template>
  </el-dialog>
</div>
</template>

<script setup>
import {getSelfMsgApi, logoutApi, updateSelfPasswordApi} from "@/api/userApi.js";
// 在Vue组件中
const userInfo = ref({

  nickname: "加载中...",

  avatarUrl: ""

})
// 获取默认头像
const getDefaultAvatar = () => {
  return "https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/d809a05f94085f016d4ef0094eda7a221c3f4b102bb8b15c2dfe84ef76cc3ae2f6b012e6850a1be56ee5ddbf1274bcc9?pictype=scale&from=30013&version=3.3.3.3&fname=%E8%B5%84%E6%BA%90_13%20%285%29.png&size=750"
}

// 获取用户数据
const fetchUserData = async () => {
  try {
    const res = await getSelfMsgApi()
    if (res.code === 200) {
      userInfo.value = {
        nickname: res.data.nickname || "未命名用户",
        avatarUrl: res.data.avatarUrl || getDefaultAvatar()
      }
    }
  } catch (error) {
    console.error("获取用户数据失败:", error)
  }
}

// 在组件挂载时调用
onMounted(() => {
  fetchUserData()
})


import router from "@/router"
import {onMounted, reactive, ref} from 'vue'
import {ElMessage} from "element-plus";

const passWordDialogVisible = ref(false)
const ruleFormRef = ref()
const loading = ref(false) // 添加加载状态
const ruleForm = reactive({
  oldpass: '',
  pass: '',
  checkPass:''
})

// 密码验证规则
const validatePass = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请再次输入新密码'))
  } else if (value !== ruleForm.pass) {
    callback(new Error("两次密码不一致！"))
  } else if (value.length < 6 || value.length > 20) {
    callback(new Error("密码长度需在6-20位之间"))
  } else {
    callback()
  }
}


const rules = reactive({
  pass: [
    { required: true, message: '请输入新密码', trigger: 'blur' },
    { min: 6, max: 20, message: '长度在6到20个字符', trigger: 'blur' }
  ],
  checkPass: [
    { required: true, validator: validatePass, trigger: 'blur' }
  ],
  oldpass: [
    { required: true, message: '请输入原密码', trigger: 'blur' },
    { min: 6, max: 20, message: '长度在6到20个字符', trigger: 'blur' }
  ]
})

// 提交修改密码
const submitForm = () => {
  ruleFormRef.value.validate(async (valid) => {
    if (!valid) return;

    loading.value = true;

    try {
      const res = await updateSelfPasswordApi({
        userId: 666,
        oldUserPassword: ruleForm.oldpass,
        newUserPassword: ruleForm.pass
      });

      if (res.code === 200) {
        ElMessage.success('密码修改成功');
        passWordDialogVisible.value = false;
        // 清空表单
        ruleForm.oldpass = '';
        ruleForm.pass = '';
        ruleForm.checkPass = '';

        // 建议用户重新登录
        setTimeout(() => {
          ElMessage.info('请重新登录');
          loginOut();
        }, 1000);
      } else {
        ElMessage.error(res.message || '密码修改失败');
      }
    } catch (error) {
      ElMessage.error(res.message);
    } finally {
      loading.value = false;
    }
  });
}

// 打开个人中心
const openPersonal = () => {
  router.push('/user/userSelfMsg');
}

// 修改密码
const handlePassWord = () => {

}

// 退出登录
const loginOut = async () => {
  sessionStorage.clear();
  const res = await logoutApi();
  await router.push('/login')
}
</script>

<style  scoped>
</style>
