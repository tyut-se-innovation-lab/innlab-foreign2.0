<template>
  <div class="p-20">
    <!-- 搜索和添加按钮容器 -->
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
        添加活动
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
      <!-- 封面图列的插槽 -->
      <template #headerImage="{ row }">
        <el-image
          style="width: 100px; height: 60px"
          :src="row.headerImageUrl"
          :preview-src-list="[row.headerImageUrl]"
          fit="cover"
          preview-teleported
        />
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

      <!-- 置顶列的插槽 -->
      <template #isTop="{ row }">
        <el-switch
          v-model="row.isTop"
          :active-value="true"
          :inactive-value="false"
          active-text="置顶"
          inactive-text="普通"
          inline-prompt
          style="--el-switch-on-color: #409EFF; --el-switch-off-color: #909399"
          @change="handleTopChange(row)"
        />
      </template>

      <!-- 操作列的插槽 -->
      <template #handle="{ row }">
        <el-button type="primary" plain @click="handleEdit(row)">编辑</el-button>
        <el-button type="primary" plain @click="handleContentEdit(row)">编辑内容</el-button>
        <el-button type="danger" plain @click="handleDelete(row)">删除</el-button>
      </template>
    </CustomTable>

    <!-- 编辑对话框 -->
    <el-dialog v-model="editVisible" title="编辑活动" width="800px">
      <el-form :model="formData" label-width="100px" v-loading="loading">
        <el-form-item label="活动标题" required>
          <el-input v-model="formData.activityTitle" />
        </el-form-item>

        <el-form-item label="活动类型" required>
          <el-select v-model="formData.activityType" placeholder="请选择活动类型" style="width: 100%">
            <el-option label="热门" value="热门" />
            <el-option label="站内公告" value="站内公告" />
            <el-option label="日常活动" value="日常活动" />
            <el-option label="技术分享" value="技术分享" />
            <el-option label="资料宝库" value="资料宝库" />
            <el-option label="软件分享" value="软件分享" />
          </el-select>
        </el-form-item>

        <el-form-item label="所属部门">
          <el-select v-model="formData.activityDepartment" placeholder="请选择部门" style="width: 100%">
            <el-option label="软件开发" value="软件开发" />
            <el-option label="人工智能" value="人工智能" />
            <el-option label="网络安全" value="网络安全" />
            <el-option label="虚拟现实" value="虚拟现实" />
            <el-option label="算法竞赛" value="算法竞赛" />
          </el-select>
        </el-form-item>

        <el-form-item label="活动简介" required>
          <el-input
            v-model="formData.activityIntroduction"
            type="textarea"
            :rows="6"
            placeholder="请输入活动简介"
          />
        </el-form-item>

        <el-form-item label="封面图">
          <el-image
            style="width: 200px; height: 120px; margin-right: 20px"
            :src="formData.headerImageUrl"
            fit="cover"
            v-if="formData.headerImageUrl"
          />
          <el-upload
            :auto-upload="false"
            :show-file-list="false"
            :on-change="handleHeaderImageChange"
          >
            <el-button type="primary">{{ formData.headerImageUrl ? '更换封面' : '上传封面' }}</el-button>
          </el-upload>
        </el-form-item>
      </el-form>

      <template #footer>
        <el-button @click="editVisible = false">取消</el-button>
        <el-button type="primary" @click="submitForm">保存</el-button>
      </template>
    </el-dialog>

    <!-- 添加对话框 -->
    <el-dialog v-model="addVisible" title="添加活动" width="800px">
      <el-form :model="addFormData" label-width="100px" v-loading="loading">
        <el-form-item label="活动标题" required>
          <el-input v-model="addFormData.activityTitle" />
        </el-form-item>

        <el-form-item label="活动类型" required>
          <el-select v-model="addFormData.activityType" placeholder="请选择活动类型" style="width: 100%">
            <el-option label="热门" value="热门" />
            <el-option label="站内公告" value="站内公告" />
            <el-option label="日常活动" value="日常活动" />
            <el-option label="技术分享" value="技术分享" />
            <el-option label="资料宝库" value="资料宝库" />
            <el-option label="软件分享" value="软件分享" />
          </el-select>
        </el-form-item>

        <el-form-item label="所属部门">
          <el-select v-model="addFormData.activityDepartment" placeholder="请选择部门" style="width: 100%">
            <el-option label="软件开发" value="软件开发" />
            <el-option label="人工智能" value="人工智能" />
            <el-option label="网络安全" value="网络安全" />
            <el-option label="虚拟现实" value="虚拟现实" />
            <el-option label="算法竞赛" value="算法竞赛" />
          </el-select>
        </el-form-item>

        <el-form-item label="活动简介" required>
          <el-input
            v-model="addFormData.activityIntroduction"
            type="textarea"
            :rows="6"
            placeholder="请输入活动简介"
          />
        </el-form-item>

        <el-form-item label="封面图">
          <el-image
            style="width: 200px; height: 120px; margin-right: 20px"
            :src="addFormData.headerImageUrl"
            fit="cover"
            v-if="addFormData.headerImageUrl"
          />
          <el-upload
            :auto-upload="false"
            :show-file-list="false"
            :on-change="handleAddHeaderImageChange"
          >
            <el-button type="primary">{{ addFormData.headerImageUrl ? '更换封面' : '上传封面' }}</el-button>
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
import { tableConfig, searchConfig } from "./activityListConfig.js";
import {
  activityListApi,
  deleteActivityApi,
  updateActivityApi,
  addActivityApi
} from "@/api/activityApi";
import {ElMessage, ElMessageBox} from "element-plus";
import { addImageApi, getResourceByLzApi } from "@/api/resourceApi.js";
import router from "@/router/index.js";

// 表格数据
const tableData = ref([]);
const total = ref(0);
const queryParams = ref({
  pageNum: 1,
  pageSize: 10,
  activityType: null,
  department: null
});

// 编辑对话框
const editVisible = ref(false);
const formData = ref({
  activityId: null,
  activityTitle: null,
  activityIntroduction: null,
  headerImage: null,
  activityDepartment: null,
  activityType: null,
  state: true,
  isTop: false,
  headerImageUrl: ""
});

// 添加对话框
const addVisible = ref(false);
const addFormData = ref({
  activityTitle: null,
  activityIntroduction: null,
  headerImage: null,
  activityDepartment: null,
  activityType: null,
  headerImageUrl: ""
});

const loading = ref(false);

// 获取封面图URL
const getHeaderImageUrl = async (headerImage) => {
  if (!headerImage) return '';

  if (headerImage.url) {
    return headerImage.url;
  }

  try {
    const res = await getResourceByLzApi({
      pwd: headerImage.pwd,
      isNewd: headerImage.isNewd,
      fid: headerImage.fid
    });

    if (res.code === 200) {
      return res.data;
    }
  } catch (error) {
    console.error('获取封面图失败:', error);
  }

  return '';
};

// 获取活动列表
const fetchActivityList = async () => {
  try {
    loading.value = true;
    const res = await activityListApi(queryParams.value);
    if (res.code === 200) {
      const recordsWithImage = await Promise.all(res.data.records.map(async (item) => {
        const headerImageUrl = await getHeaderImageUrl(item.headerImage);
        return {
          ...item,
          headerImageUrl: headerImageUrl
        };
      }));
      tableData.value = recordsWithImage;
      total.value = res.data.total;
    } else {
      ElMessage.error(res.message || '获取活动列表失败');
    }
  } catch (error) {
    console.error('获取活动列表出错:', error);
    ElMessage.error('获取活动列表失败，请稍后重试');
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
  if (params.activityType !== undefined) {
    queryParams.value.activityType = params.activityType;
  }
  if (params.department !== undefined) {
    queryParams.value.department = params.department;
  }
  Object.assign(queryParams.value, params);
  if (shouldFetch) {
    fetchActivityList();
  }
};

// 编辑活动
const handleEdit = async (row) => {
  const headerImageUrl = await getHeaderImageUrl(row.headerImage);
  formData.value = {
    activityId: row.activityId,
    activityTitle: row.activityTitle,
    activityIntroduction: row.activityIntroduction,
    headerImage: row.headerImage,
    activityDepartment: row.activityDepartment,
    activityType: row.activityType,
    state: row.state,
    isTop: row.isTop,
    headerImageUrl: headerImageUrl
  };
  editVisible.value = true;
};

// 删除活动
const handleDelete = async (row) => {
  try {
    await ElMessageBox.confirm(
      `确定要删除【${row.activityTitle}】吗？此操作不可恢复！`,
      '删除确认',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }
    );
    loading.value = true;
    const res = await deleteActivityApi(row.activityId);
    if (res.code === 200) {
      ElMessage.success('删除成功');
      fetchActivityList();
    } else {
      ElMessage.error(res.message || '删除失败');
    }
  } catch (error) {
    console.error('删除活动失败:', error);
    ElMessage.error('删除失败');
  } finally {
    loading.value = false;
  }
};

// 状态切换
const handleStatusChange = async (row) => {
  try {
    loading.value = true;
    const res = await updateActivityApi({
      activityId: row.activityId,
      state: row.state
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

// 置顶切换
const handleTopChange = async (row) => {
  try {
    loading.value = true;
    const res = await updateActivityApi({
      activityId: row.activityId,
      isTop: row.isTop
    });
    if (res.code === 200) {
      ElMessage.success('置顶状态更新成功');
    } else {
      row.isTop = !row.isTop;
      ElMessage.error(res.message || '置顶状态更新失败');
    }
  } catch (error) {
    console.error('置顶状态更新失败:', error);
    row.isTop = !row.isTop;
    ElMessage.error('置顶状态更新失败');
  } finally {
    loading.value = false;
  }
};

// 封面图上传处理
const handleHeaderImageChange = async (file) => {
  const localUrl = URL.createObjectURL(file.raw);
  formData.value.headerImageUrl = localUrl;

  try {
    loading.value = true;
    const res = await addImageApi(file.raw, '活动封面图');
    if (res.code === 200) {
      formData.value.headerImage = res.data.resourceId;
      const newImageUrl = await getHeaderImageUrl(res.data.lz);
      formData.value.headerImageUrl = newImageUrl;
      ElMessage.success('封面图上传成功');
    } else {
      ElMessage.error(res.message || '上传封面图失败');
      formData.value.headerImageUrl = '';
    }
  } catch (error) {
    console.error('上传封面图失败:', error);
    ElMessage.error('上传封面图失败');
    formData.value.headerImageUrl = '';
  } finally {
    loading.value = false;
  }
};

// 提交表单（编辑）
const submitForm = async () => {
  try {
    loading.value = true;

    // 验证必填字段
    if (!formData.value.activityTitle || !formData.value.activityType || !formData.value.activityIntroduction) {
      ElMessage.warning('请填写活动标题、类型和简介');
      return;
    }

    const res = await updateActivityApi({
      activityId: formData.value.activityId,
      activityTitle: formData.value.activityTitle,
      activityIntroduction: formData.value.activityIntroduction,
      headerImage:  typeof formData.value.headerImage=== 'number'
        ? formData.value.headerImage
        : null,
      activityDepartment: formData.value.activityDepartment,
      activityType: formData.value.activityType,
      state: formData.value.state,
      isTop: formData.value.isTop
    });

    if (res.code === 200) {
      ElMessage.success('保存成功');
      editVisible.value = false;
      await fetchActivityList();
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

// 添加活动按钮点击事件
const handleAdd = () => {
  addFormData.value = {
    activityTitle: null,
    activityIntroduction: null,
    headerImage: null,
    activityDepartment: null,
    activityType: null,
    headerImageUrl: ''
  };
  addVisible.value = true;
};

// 添加活动封面图上传处理
const handleAddHeaderImageChange = async (file) => {
  const localUrl = URL.createObjectURL(file.raw);
  addFormData.value.headerImageUrl = localUrl;

  try {
    loading.value = true;
    const res = await addImageApi(file.raw, '活动封面图');
    if (res.code === 200) {
      addFormData.value.headerImage = res.data.resourceId;
      const newImageUrl = await getHeaderImageUrl(res.data.lz);
      addFormData.value.headerImageUrl = newImageUrl;
      ElMessage.success('封面图上传成功');
    } else {
      ElMessage.error(res.message || '上传封面图失败');
      addFormData.value.headerImageUrl = '';
    }
  } catch (error) {
    console.error('上传封面图失败:', error);
    ElMessage.error('上传封面图失败');
    addFormData.value.headerImageUrl = '';
  } finally {
    loading.value = false;
  }
};

// 提交添加表单
const submitAddForm = async () => {
  try {
    loading.value = true;

    // 验证必填字段
    if (!addFormData.value.activityTitle || !addFormData.value.activityType || !addFormData.value.activityIntroduction) {
      ElMessage.warning('请填写活动标题、类型和简介');
      return;
    }

    const res = await addActivityApi({
      activityTitle: addFormData.value.activityTitle,
      activityIntroduction: addFormData.value.activityIntroduction,
      headerImage: addFormData.value.headerImage,
      activityDepartment: addFormData.value.activityDepartment,
      activityType: addFormData.value.activityType
    });

    if (res.code === 200) {
      ElMessage.success('添加成功');
      addVisible.value = false;
      fetchActivityList();
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

// 编辑内容跳转
const handleContentEdit = (row) => {
  console.log('跳转参数:', row.activityId) // 确认有值
  router.push({
    name: 'activityEditor',
    params: {
      activityId: row.activityId
    }
  });
};


// 组件挂载时加载数据
onMounted(() => {
  fetchActivityList();
});
</script>

<style scoped>
.search-and-add-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.search-box {
  flex: 1;
  max-width: calc(100% - 120px);
}

.add-button {
  margin-left: 20px;
  white-space: nowrap;
}

.el-image {
  border-radius: 4px;
  cursor: pointer;
  transition: transform 0.3s;
}

.el-image:hover {
  transform: scale(1.05);
}

.el-dialog {
  border-radius: 8px;
}

.el-form-item {
  margin-bottom: 20px;
}

.el-textarea {
  min-height: 120px;
}
</style>
