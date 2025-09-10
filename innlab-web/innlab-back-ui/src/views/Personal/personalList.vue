<template>
  <div class="p-20">
    <!-- 修改顶部搜索和按钮容器 -->
    <div class="search-and-add-container">
      <CustomSearch
        class="search-box"
        :searchConfig="searchConfig"
        @updateQueryData="updateQueryData"
      ></CustomSearch>
      <el-button
        type="primary"
        class="add-button"
        @click="handleAdd"
      >
        添加人员
      </el-button>
    </div>

    <CustomTable
      :tableConfig="tableConfig"
      :tableData="tableData"
      :total="total"
      @updateQueryData="updateQueryData"
      @handleEdit="handleEdit"
      @handleDelete="handleDelete"
      @handleStatusChange="handleStatusChange"
    >
      <!-- 头像列的插槽 -->
      <template #avatar="{ row }">
        <el-avatar :size="50" :src="row.avatarUrl" />
      </template>

      <!-- 状态列的插槽 -->
      <template #status="{ row }">
        <el-switch
          v-model="row.state"
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
    <el-dialog v-model="editVisible" title="编辑人员信息" width="600px">
      <el-form :model="formData" label-width="100px" v-loading="loading">
        <el-form-item label="姓名">
          <el-input v-model="formData.personnelName" />
        </el-form-item>

        <el-form-item label="级数">
          <el-input v-model="formData.personnelPeriod" />
        </el-form-item>

        <el-form-item label="部门">
          <el-select v-model="formData.personnelDepartment" placeholder="请选择部门" style="width: 100%">
            <el-option label="软件开发" value="软件开发" />
            <el-option label="人工智能" value="人工智能" />
            <el-option label="网络安全" value="网络安全" />
            <el-option label="虚拟现实" value="虚拟现实" />
            <el-option label="算法竞赛" value="算法竞赛" />
          </el-select>
        </el-form-item>

        <el-form-item label="职位">
          <el-input v-model="formData.personnelPost" placeholder="请输入职位" />
        </el-form-item>

        <el-form-item label="贡献">
          <el-input v-model="formData.personnelContribute" type="textarea" placeholder="请输入贡献" />
        </el-form-item>

        <el-form-item label="格言">
          <el-input v-model="formData.personnelSaying" type="textarea" placeholder="请输入格言" />
        </el-form-item>

        <el-form-item label="排序">
          <el-input-number v-model="formData.personnelSort" :min="0" />
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
    <!-- 新增添加人员对话框 -->
    <el-dialog v-model="addVisible" title="添加人员" width="600px">
      <el-form :model="addFormData" label-width="100px" v-loading="loading">
        <el-form-item label="姓名" required>
          <el-input v-model="addFormData.personnelName" />
        </el-form-item>

        <el-form-item label="级数" required>
          <el-select v-model="addFormData.personnelPeriod" placeholder="请选择级数" style="width: 100%">
            <el-option label="2020" value="2020" />
            <el-option label="2021" value="2021" />
            <el-option label="2022" value="2022" />
            <el-option label="2023" value="2023" />
            <el-option label="2024" value="2024" />
          </el-select>
        </el-form-item>

        <el-form-item label="部门" required>
          <el-select v-model="addFormData.personnelDepartment" placeholder="请选择部门" style="width: 100%">
            <el-option label="软件开发" value="软件开发" />
            <el-option label="人工智能" value="人工智能" />
            <el-option label="网络安全" value="网络安全" />
            <el-option label="虚拟现实" value="虚拟现实" />
            <el-option label="算法竞赛" value="算法竞赛" />
          </el-select>
        </el-form-item>

        <el-form-item label="职位">
          <el-input v-model="addFormData.personnelPost" placeholder="请输入职位" />
        </el-form-item>

        <el-form-item label="贡献">
          <el-input v-model="addFormData.personnelExample" type="textarea" placeholder="请输入贡献" />
        </el-form-item>

        <el-form-item label="格言">
          <el-input v-model="addFormData.personnelSaying" type="textarea" placeholder="请输入格言" />
        </el-form-item>

        <el-form-item label="排序">
          <el-input-number v-model="addFormData.personnelSort" :min="0" />
        </el-form-item>

        <el-form-item label="头像">
          <el-avatar :size="100" :src="addFormData.avatarUrl" class="mr-10" v-if="addFormData.avatarUrl" />
          <el-upload
            :auto-upload="false"
            :show-file-list="false"
            :on-change="handleAddAvatarChange"
          >
            <el-button type="primary">{{ addFormData.avatarUrl ? '更换头像' : '上传头像' }}</el-button>
          </el-upload>
        </el-form-item>
      </el-form>

      <template #footer>
        <el-button @click="addVisible = false">取消</el-button>
        <el-button type="primary" @click="submitAddForm">确认添加</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import CustomTable from "@/components/CustomTable/index.vue";
import CustomSearch from "@/components/CustomSearch/index.vue";
import { ref, onMounted } from "vue";
import { tableConfig, searchConfig } from "./personalListConfig.js";
import {
  getPersonalListApi,
  deletePersonalApi,
  updatePersonalApi, addPersonalApi
} from "@/api/personalApi";
import {ElMessage, ElMessageBox} from "element-plus";
import {addImageApi, getResourceByLzApi} from "@/api/resourceApi.js";
import {Document, VideoPlay} from "@element-plus/icons-vue";

// 表格数据
const tableData = ref([]);
const total = ref(0);
const queryParams = ref({
  pageNum: 1,
  pageSize: 10,
  department: null,
  period: null
});

// 编辑对话框
const editVisible = ref(false);
const formData = ref({
  personnelId: null,
  personnelName: null,
  personnelAvatar: null,
  personnelPeriod: null,
  personnelDepartment: null,
  personnelPost: null,
  personnelContribute: null,
  personnelSaying: null,
  state: true,
  personnelSort: 0
});

const loading = ref(false);

// 获取头像URL
const getAvatarUrl = async (avatar) => {
  if (!avatar) return '';

  // 如果已经有url，直接返回
  if (avatar.url) {
    return avatar.url;
  }

  // 否则调用接口获取
  try {
    const res = await getResourceByLzApi({
      pwd: avatar.pwd,
      isNewd: avatar.isNewd,
      fid: avatar.fid
    });

    if (res.code === 200) {
      return res.data;
    }
  } catch (error) {
    console.error('获取头像失败:', error);
  }

  return '';
};

// 获取人员列表
const fetchPersonnelList = async () => {
  try {
    loading.value = true;
    const res = await getPersonalListApi(queryParams.value);
    if (res.code === 200) {
      // 使用Promise.all来等待所有头像URL获取完成
      const recordsWithAvatar = await Promise.all(res.data.records.map(async (item) => {
        const avatarUrl = await getAvatarUrl(item.personnelAvatar);
        return {
          ...item,
          avatarUrl: avatarUrl // 添加avatarUrl字段
        };
      }));
      tableData.value = recordsWithAvatar;
      total.value = res.data.total;
    } else {
      ElMessage.error(res.message || '获取人员列表失败');
    }
  } catch (error) {
    console.error('获取人员列表出错:', error);
    ElMessage.error('获取人员列表失败，请稍后重试');
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
    fetchPersonnelList();
  }
};

// 编辑人员
const handleEdit = async (row) => {
  const avatarUrl = await getAvatarUrl(row.personnelAvatar);
  formData.value = {
    personnelId: row.personnelId,
    personnelName: row.personnelName,
    personnelAvatar: row.personnelAvatar,
    personnelPeriod: row.personnelPeriod,
    personnelDepartment: row.personnelDepartment,
    personnelPost: row.personnelPost,
    personnelContribute: row.personnelContribute,
    personnelSaying: row.personnelSaying,
    state: row.state,
    personnelSort: row.personnelSort,
    avatarUrl: avatarUrl
  };
  editVisible.value = true;
};

// 删除人员
// 删除人员
const handleDelete = async (row) => {
  try {
    await ElMessageBox.confirm(
      `确定要删除【${row.personnelName}】吗？此操作不可恢复！`,
      '删除确认',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }
    );

    loading.value = true;
    const res = await deletePersonalApi(row.personnelId);
    if (res.code === 200) {
      ElMessage.success('删除成功');
      fetchPersonnelList();
    } else {
      ElMessage.error(res.message || '删除失败');
    }
  } catch (error) {
    if (error !== 'cancel') { // 只有当不是用户取消时才报错
      console.error('删除人员失败:', error);
      ElMessage.error('删除失败');
    }
  } finally {
    loading.value = false;
  }
};

// 状态切换
const handleStatusChange = async (row) => {
  try {
    loading.value = true;
    const res = await updatePersonalApi({
      personnelId:row.personnelId,
      state:row.state
    });
    if (res.code === 200) {
      ElMessage.success('状态更新成功');
    } else {
      row.state = !row.state;
      ElMessage.error(res.message || '状态更新失败');
    }
  } catch (error) {
    console.error('状态更新失败:', error);
    row.state = !row.state;
    ElMessage.error('状态更新失败');
  } finally {
    loading.value = false;
  }
};

// 头像上传处理
const handleAvatarChange = async (file) => {
  // 先创建一个本地URL用于临时预览
  const localUrl = URL.createObjectURL(file.raw);
  formData.value.avatarUrl = localUrl; // 临时显示本地预览

  try {
    loading.value = true;
    // 调用上传接口
    const res = await addImageApi(file.raw, '头像');
    if (res.code === 200) {
      // 将返回的资源信息保存到formData中
      formData.value.personnelAvatar = res.data.resourceId;

      // 获取新头像的URL
      const newAvatarUrl = await getAvatarUrl(res.data.lz);
      formData.value.avatarUrl = newAvatarUrl;

      ElMessage.success('头像上传成功');
    } else {
      ElMessage.error(res.message || '上传头像失败');
      formData.value.avatarUrl = '';
    }
  } catch (error) {
    console.error('上传头像失败:', error);
    ElMessage.error('上传头像失败');
    formData.value.avatarUrl = '';
  } finally {
    loading.value = false;
  }
};
// 根据resourceId获取资源URL
const getResourceUrl = async (resourceId) => {
  if (!resourceId) return '';

  try {
    const res = await getResourceByIdApi(resourceId); // 需要创建此API
    if (res.code === 200 && res.data) {
      // 根据实际接口返回结构调整
      return res.data.url ||
        res.data.lz?.isNewd + '/' + res.data.lz?.fid;
    }
  } catch (error) {
    console.error('获取资源URL失败:', error);
  }

  return '';
};
/// 提交表单（编辑）
const submitForm = async () => {
  try {
    loading.value = true;

    // 准备更新数据
    const updateData = {
      personnelId: formData.value.personnelId,
      personnelName: formData.value.personnelName,
      personnelPeriod: formData.value.personnelPeriod,
      personnelDepartment: formData.value.personnelDepartment,
      personnelPost: formData.value.personnelPost === '' ? null : formData.value.personnelPost,
      personnelExample: formData.value.personnelContribute === '' ? null : formData.value.personnelContribute,
      personnelSaying: formData.value.personnelSaying,
      personnelSort: formData.value.personnelSort,
      state: formData.value.state,
      // 如果personnelAvatar是resourceId，直接传入
      // 如果是lz对象，需要转换为resourceId
      personnelAvatar: typeof formData.value.personnelAvatar === 'number'
        ? formData.value.personnelAvatar
        : null
    };

    const res = await updatePersonalApi(updateData);
    if (res.code === 200) {
      ElMessage.success('保存成功');
      editVisible.value = false;
      await fetchPersonnelList();
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

// 新增添加对话框相关状态
const addVisible = ref(false);
const addFormData = ref({
  personnelName: null,
  personnelAvatar: null,
  personnelPeriod: null,
  personnelDepartment: null,
  personnelPost: null,
  personnelExample: null,
  personnelSaying: null,
  personnelSort: 0,
  avatarUrl: null
});

// 添加人员按钮点击事件
const handleAdd = () => {
  addFormData.value = {
    personnelName: null,
    personnelAvatar: null,
    personnelPeriod: null,
    personnelDepartment: null,
    personnelPost: null,
    personnelExample: null,
    personnelSaying: null,
    personnelSort: 0,
    avatarUrl: null
  };
  addVisible.value = true;
};

// 添加人员头像上传处理
const handleAddAvatarChange = async (file) => {
  const localUrl = URL.createObjectURL(file.raw);
  addFormData.value.avatarUrl = localUrl;

  try {
    loading.value = true;
    const res = await addImageApi(file.raw, '头像');
    if (res.code === 200) {
      addFormData.value.personnelAvatar = res.data.resourceId;
      const newAvatarUrl = await getAvatarUrl(res.data.lz);
      addFormData.value.avatarUrl = newAvatarUrl;
      ElMessage.success('头像上传成功');
    } else {
      ElMessage.error(res.message || '上传头像失败');
      addFormData.value.avatarUrl = '';
    }
  } catch (error) {
    console.error('上传头像失败:', error);
    ElMessage.error('上传头像失败');
    addFormData.value.avatarUrl = '';
  } finally {
    loading.value = false;
  }
};

// 提交添加表单
const submitAddForm = async () => {
  try {
    loading.value = true;

    // 验证必填字段
    if (!addFormData.value.personnelName ||
      !addFormData.value.personnelPeriod ||
      !addFormData.value.personnelDepartment) {
      ElMessage.warning('请填写姓名、级数和部门');
      return;
    }

    const res = await addPersonalApi({
      personnelName: addFormData.value.personnelName,
      personnelAvatar: addFormData.value.personnelAvatar,
      personnelPeriod: addFormData.value.personnelPeriod,
      personnelDepartment: addFormData.value.personnelDepartment,
      personnelPost: addFormData.value.personnelPost === '' ? null : addFormData.value.personnelPost,
      personnelExample: addFormData.value.personnelExample === '' ? null : addFormData.value.personnelExample,
      personnelSaying: addFormData.value.personnelSaying,
      personnelSort: addFormData.value.personnelSort
    });

    if (res.code === 200) {
      ElMessage.success('添加成功');
      addVisible.value = false;
      fetchPersonnelList(); // 刷新列表
    } else {
      ElMessage.error(res.message || '添加失败');
    }
  } catch (error) {
    console.error('添加失败:', error);
    ElMessage.error('添加失败');
  } finally {
    loading.value = false;
  }
};

// 组件挂载时加载数据
onMounted(() => {
  fetchPersonnelList();
});
</script>

<style scoped>
/* 可以根据需要添加自定义样式 */
</style>
<style scoped>
/* 新增样式 */
.flex {
  display: flex;
}
.justify-between {
  justify-content: space-between;
}
.items-center {
  align-items: center;
}
</style>
<style scoped>
/* 新增样式 */
.search-and-add-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.search-box {
  flex: 1; /* 让搜索框占据剩余空间 */
  max-width: calc(100% - 120px); /* 留出按钮的空间 */
}

.add-button {
  margin-left: 20px; /* 添加左边距 */
  margin-right: 20px;
  white-space: nowrap; /* 防止按钮文字换行 */
}

.mt-20 {
  margin-top: 20px;
}
</style>
