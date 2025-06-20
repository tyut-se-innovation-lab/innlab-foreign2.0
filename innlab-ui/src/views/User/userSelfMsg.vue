<template>
  <div class="personal-center">
    <el-card class="profile-card">
      <!-- 头部信息 -->
      <div class="profile-header">
        <el-avatar :size="120" :src="userInfo.avatarUrl" class="avatar" />
        <div class="profile-info">
          <h2 class="nickname">{{ userInfo.nickname }}</h2>
          <div class="account">账号: {{ userInfo.account }}</div>
          <el-tag :type="roleTagType" class="role-tag">
            {{ roleMap[userInfo.role] || userInfo.role }}
          </el-tag>
          <!-- 添加头像上传按钮 -->
          <el-upload
            class="avatar-uploader"
            :auto-upload="false"
            :show-file-list="false"
            :on-change="handleAvatarChange"
          >
            <el-button type="primary" size="small" class="mt-10">更换头像</el-button>
          </el-upload>
        </div>
      </div>

      <!-- 详细信息 -->
      <el-descriptions
        title="个人信息"
        :column="1"
        border
        class="info-details"
      >
        <el-descriptions-item label="部门">
          {{ userInfo.department || '未设置' }}
        </el-descriptions-item>
        <el-descriptions-item label="邮箱">
          {{ userInfo.email || '未设置' }}
          <el-icon class="edit-icon" @click="editField('email')">
          </el-icon>
        </el-descriptions-item>
        <el-descriptions-item label="手机">
          {{ userInfo.phone || '未设置' }}
          <el-icon class="edit-icon" @click="editField('phone')">
            <Edit />
          </el-icon>
        </el-descriptions-item>
        <el-descriptions-item label="上次登录时间">
          {{ formatTime(userInfo.lastLoginTime) }}
        </el-descriptions-item>
        <el-descriptions-item label="登录IP">
          {{ userInfo.lastLoginIp }}
        </el-descriptions-item>
        <el-descriptions-item label="登录地点">
          {{ userInfo.lastLoginLocation || '未知' }}
        </el-descriptions-item>
      </el-descriptions>
    </el-card>

    <!-- 编辑对话框 -->
    <el-dialog
      v-model="editDialog.visible"
      :title="`编辑${fieldMap[editDialog.field]}`"
      width="400px"
    >
      <el-form :model="editForm" label-width="80px">
        <el-form-item :label="fieldMap[editDialog.field]">
          <el-input
            v-model="editForm.value"
            :placeholder="`请输入${fieldMap[editDialog.field]}`"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="editDialog.visible = false">取消</el-button>
        <el-button type="primary" @click="saveEdit">保存</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import {getSelfMsgApi, updateSelfMsdApi} from '@/api/userApi'; // 请根据实际路径调整
import { ElMessage } from 'element-plus';
import { Edit } from '@element-plus/icons-vue';
import {addImageApi} from "@/api/resourceApi.js";

// 用户信息
const userInfo = ref({
  account: '',
  nickname: '',
  avatarUrl: '',
  email: '',
  phone: null,
  department: '',
  lastLoginIp: '',
  lastLoginLocation: '',
  lastLoginTime: '',
  role: '',
  avatarId: null // 添加avatarId字段
});

// 角色映射
const roleMap = {
  personnel: '普通成员',
  admin: '超级管理员',
  manager: '管理员'
};

// 角色标签类型
const roleTagType = computed(() => {
  switch(userInfo.value.role) {
    case 'admin': return 'success';
    case 'manager': return 'danger';
    default: return 'info';
  }
});

// 字段映射
const fieldMap = {
  email: '邮箱',
  phone: '手机号'
};

// 编辑对话框状态
const editDialog = ref({
  visible: false,
  field: ''
});

// 编辑表单
const editForm = ref({
  value: ''
});

// 获取用户信息
const fetchUserInfo = async () => {
  try {
    const res = await getSelfMsgApi();
    if (res.code === 200) {
      userInfo.value = res.data;
    } else {
      ElMessage.error(res.message || '获取用户信息失败');
    }
  } catch (error) {
    console.error('获取用户信息出错:', error);
    ElMessage.error('权限不足！');
  }
};

// 处理头像变更
const handleAvatarChange = async (file) => {
  try {
    // 1. 本地预览
    userInfo.value.avatarUrl = URL.createObjectURL(file.raw);



    const uploadRes = await addImageApi(file.raw,'用户头像');

    if (uploadRes.code === 200) {
      // 3. 获取返回的resourceId
      const resourceId = uploadRes.data.resourceId;

      // 4. 更新用户信息
      const updateRes = await updateSelfMsdApi({
        userId: 666, // 假设userInfo中有userId
        userAvatar: resourceId // 传resourceId而不是URL
      });

      if (updateRes.code === 200) {
        // 5. 更新本地avatarId
        userInfo.value.avatarId = resourceId;
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
    // 恢复原来的头像
    if (userInfo.value.avatarId) {
      // 这里需要调用获取头像URL的接口
      // userInfo.value.avatarUrl = await getAvatarUrl(userInfo.value.avatarId);
    }
  }
};
// 格式化时间
const formatTime = (timeString) => {
  if (!timeString) return '';
  const date = new Date(timeString);
  return `${date.getFullYear()}-${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')} ${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')}`;
};

// 打开编辑对话框
const editField = (field) => {
  editDialog.value = {
    visible: true,
    field: field
  };
  editForm.value.value = userInfo.value[field] || '';
};

// 保存编辑
const saveEdit = async () => {
  try {
    // 1. 调用API更新后端数据
    const res = await updateSelfMsdApi({
      userId: 666, // 假设userInfo中有userId
      userPhone: editForm.value.value // 要更新的电话号码
    });

    if (res.code === 200) {
      // 2. 更新前端显示
      userInfo.value[editDialog.value.field] = editForm.value.value;
      editDialog.value.visible = false;
      ElMessage.success('修改成功');

      // 3. (可选) 重新获取用户信息确保数据一致
      await fetchUserInfo();
    } else {
      throw new Error(res.message || '更新失败');
    }
  } catch (error) {
    console.error('保存失败:', error);
    ElMessage.error(`保存失败: ${error.message}`);
  }
};
// 组件挂载时获取用户信息
onMounted(() => {
  fetchUserInfo();
});
</script>

<style scoped>
.personal-center {
  padding: 20px;
  max-width: 800px;
  margin: 0 auto;
}

.profile-card {
  border-radius: 10px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

.profile-header {
  display: flex;
  align-items: center;
  padding: 20px;
  border-bottom: 1px solid #eee;
  margin-bottom: 20px;
}

.avatar {
  margin-right: 30px;
  border: 2px solid #409eff;
  padding: 3px;
}

.profile-info {
  flex: 1;
}

.nickname {
  margin: 0 0 10px;
  font-size: 24px;
  color: #333;
}

.account {
  margin-bottom: 15px;
  color: #666;
  font-size: 16px;
}

.role-tag {
  font-size: 14px;
  padding: 5px 15px;
}

.info-details {
  margin-top: 20px;
}

.edit-icon {
  margin-left: 10px;
  color: #409eff;
  cursor: pointer;
  font-size: 16px;
}

.edit-icon:hover {
  color: #79bbff;
}
/* 新增样式 */
.avatar-uploader {
  display: inline-block;
  margin-top: 10px;
}

.mt-10 {
  margin-top: 10px;
}
</style>
