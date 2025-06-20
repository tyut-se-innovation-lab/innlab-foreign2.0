<template>
  <div class="p-20">
    <CustomSearch
      class="mb-20"
      :searchConfig="searchConfig"
      @updateQueryData="updateQueryData"
    ></CustomSearch>

    <CustomTable
      :tableConfig="tableConfig"
      :tableData="tableData"
      :total="total"
      @updateQueryData="updateQueryData"
      @handleEdit="handleEdit"
      @handleDelete="handleDelete"
      @handleStatusChange="handleStatusChange"
    >
      <!-- 状态列的插槽 -->
      <template #status="{ row }">
        <el-switch
          v-model="row.userState"
          :active-value="true"
          :inactive-value="false"
          active-text="启用"
          inactive-text="停用"
          inline-prompt
          style="--el-switch-on-color: #13ce66; --el-switch-off-color: #ff4949"
          @change="handleStatusChange(row)"
        />
      </template>

      <!-- 操作列的插槽 -->
      <template #handle="{ row }">
        <el-button type="primary" plain @click="handleEdit(row)">编辑</el-button>
        <el-button type="danger" plain @click="handleDelete(row)">删除</el-button>
      </template>
    </CustomTable>

    <!-- 编辑对话框 -->
    <el-dialog v-model="editVisible" title="编辑用户信息" width="600px">
      <el-form :model="formData" label-width="100px" v-loading="loading">
        <el-form-item label="账号">
          <el-input v-model="formData.account" disabled />
        </el-form-item>

        <el-form-item label="昵称" prop="nickname" >
          <el-input v-model="formData.nickname" disabled />
        </el-form-item>

        <el-form-item label="邮箱" prop="email" :rules="[
          { required: true, message: '邮箱不能为空' },
          { type: 'email', message: '请输入正确的邮箱格式' }
        ]">
          <el-input v-model="formData.email" placeholder="请输入邮箱"   disabled />
        </el-form-item>

        <el-form-item label="手机号">
          <el-input v-model="formData.phone" placeholder="请输入手机号" />
        </el-form-item>

        <el-form-item label="部门">
          <el-select v-model="formData.department" placeholder="请选择部门" style="width: 100%"  disabled >
            <el-option label="软件开发" value="软件开发" />
            <el-option label="人工智能" value="人工智能" />
            <el-option label="网络安全" value="网络安全" />
            <el-option label="虚拟现实" value="虚拟现实" />
            <el-option label="算法竞赛" value="算法竞赛" />
          </el-select>
        </el-form-item>

        <el-form-item label="角色">
          <el-select v-model="formData.role" placeholder="请选择角色" style="width: 100%"  disabled >
            <el-option label="普通用户" value="personnel" />
            <el-option label="超级管理员" value="admin" />
            <el-option label="管理员" value="manager" />
          </el-select>
        </el-form-item>

        <el-form-item label="头像">
          <el-avatar :size="100" :src="formData.avatarUrl" class="mr-10" />
          <el-upload
            :auto-upload="false"
            :show-file-list="false"
            :on-change="handleAvatarChange"
          >
            <el-button type="primary">更换头像</el-button>
          </el-upload>
        </el-form-item>
      </el-form>

      <template #footer>
        <el-button @click="editVisible = false">取消</el-button>
        <el-button type="primary" @click="submitForm">保存</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import CustomTable from "@/components/CustomTable/index.vue";
import CustomSearch from "@/components/CustomSearch/index.vue";
import { ref, onMounted } from "vue";
import { tableConfig, searchConfig } from "./userListConfig";
import {
  getUserListApi,
  updateUserStatusApi,
  deleteUserApi,
  updateUserApi,
  getUserMsgApi
} from "@/api/userApi";
import {ElMessage, ElMessageBox} from "element-plus";
import {addImageApi} from "@/api/resourceApi.js";

// 表格数据
const tableData = ref([]);
const total = ref(0);
const queryParams = ref({
  pageNum: 1,
  pageSize: 10,
  userAccount: null,
  userDepartment: null
});

// 编辑对话框
const editVisible = ref(false);
const formData = ref({
  userId: null,
  account: null,
  nickname: null,
  avatarUrl: null,
  email: null,
  phone: null,
  department:null ,
  role:null
});

const loading = ref(false);

// 获取用户列表
const fetchUserList = async () => {
  try {
    loading.value = true;
    const res = await getUserListApi(queryParams.value);
    if (res.code === 200) {
      tableData.value = res.data.records;
      total.value = res.data.total;
    } else {
      ElMessage.error(res.message || '获取用户列表失败');
    }
  } catch (error) {
    console.error('获取用户列表出错:', error);
    ElMessage.error('获取用户列表失败，请稍后重试');
  } finally {
    loading.value = false;
  }
};

// 更新查询参数并重新加载数据
const updateQueryData = (params, shouldFetch = true) => {
  if (params.pageNum !== undefined) {
    queryParams.value.pageNum = params.pageNum;
  }
  if (params.pageSize !== undefined) {
    queryParams.value.pageSize = params.pageSize;
  }

  Object.assign(queryParams.value, params);

  if (shouldFetch) {
    fetchUserList();
  }
};

// 编辑用户 - 调用详情接口
const handleEdit = async (row) => {
  try {
    loading.value = true;
    // 直接传递 userId 值
    const res = await getUserMsgApi(row.userId);

    if (res.code === 200) {
      formData.value = {
        userId: row.userId,
        account: res.data.account,
        nickname: res.data.nickname,
        avatarUrl: res.data.avatarUrl,
        email: res.data.email,
        phone: res.data.phone || "",
        department: res.data.department,
        role: res.data.role
      };
      editVisible.value = true;
    } else {
      ElMessage.error(res.message || '获取用户信息失败');
    }
  } catch (error) {
    console.error('获取用户信息出错:', error);
    ElMessage.error('权限不足！');
  } finally {
    loading.value = false;
  }
};

// 删除用户
const handleDelete = async (row) => {
  console.log(row)
  try {
    await ElMessageBox.confirm(
      `确定要删除【${row.userNickname}】吗？此操作不可恢复！`,
      '删除确认',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }
    );
    loading.value = true;
    const res = await deleteUserApi(row.userId);
    if (res.code === 200) {
      ElMessage.success('删除成功');
      fetchUserList();
    } else {
      ElMessage.error(res.message || '删除失败');
    }
  } catch (error) {
    console.error('删除用户失败:', error);
    ElMessage.error('删除失败');
  } finally {
    loading.value = false;
  }
};

// 状态切换
const handleStatusChange = async (row) => {
  try {
    loading.value = true;
    const res = await updateUserStatusApi(row.userId, row.userState);
    if (res.code === 200) {
      ElMessage.success('状态更新成功');
    } else {
      row.userState = !row.userState;
      ElMessage.error(res.message || '状态更新失败');
    }
  } catch (error) {
    console.error('状态更新失败:', error);
    row.userState = !row.userState;
    ElMessage.error('状态更新失败');
  } finally {
    loading.value = false;
  }
};

// 头像上传成功
const handleAvatarSuccess = async (response) => {
  try {
    loading.value = true;


    const uploadRes = await addImageApi(response.file,'用户头像');

    if (uploadRes.code === 200) {
      // 2. 获取返回的resourceId
      const resourceId = uploadRes.data.resourceId;

      // 3. 更新用户信息
      const updateRes = await updateUserApi({
        userId: formData.value.userId,
        userAvatar: resourceId // 传resourceId而不是URL
      });

      if (updateRes.code === 200) {
        // 4. 更新本地显示（可选：可能需要从接口重新获取完整用户信息）
        formData.value.avatarUrl = URL.createObjectURL(response.file);
        ElMessage.success('头像更新成功');
      } else {
        throw new Error(updateRes.message || '用户信息更新失败');
      }
    } else {
      throw new Error(uploadRes.message || '图片上传失败');
    }
  } catch (error) {
    console.error('头像更新失败:', error);
    ElMessage.error(`头像更新失败: ${error.message}`);
  } finally {
    loading.value = false;
  }
};

const handleAvatarChange = (file) => {
  // 预览图片（本地）
  formData.value.avatarUrl = URL.createObjectURL(file.raw);

  // 构造上传需要的响应对象
  const fakeResponse = {
    file: file.raw,
    code: 200
  };

  // 触发上传处理
  handleAvatarSuccess(fakeResponse);
};
// 提交表单（编辑）
const submitForm = async () => {
  try {
    loading.value = true;

    // 构造更新参数
    const updateData = {
      userId: formData.value.userId,
      userAccount: formData.value.account,
      userNickname: formData.value.nickname,
      userEmail: formData.value.email,
      userPhone: formData.value.phone,
      userDepartment: formData.value.department,
      userRole: formData.value.role,
      avatarUrl: formData.value.avatarUrl,
    };

    const res = await updateUserApi({
            userId:updateData.userId,
            userAvatar:updateData.userAvatarId,
            userPhone:updateData.userPhone
    });
    if (res.code === 200) {
      ElMessage.success('保存成功');
      editVisible.value = false;
      fetchUserList();
    } else {
      ElMessage.error(res.message || '保存失败');
    }
  } catch (error) {
    console.error('保存失败:', error);
    ElMessage.error('保存失败');
  } finally {
    loading.value = false;
  }
};

// 组件挂载时加载数据
onMounted(() => {
  fetchUserList();
});
</script>

<style scoped>
/* 可以根据需要添加自定义样式 */
</style>
