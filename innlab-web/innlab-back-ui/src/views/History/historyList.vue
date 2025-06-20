<template>
  <div class="p-20">
    <!-- 搜索和添加按钮容器 -->
    <div class="search-and-add-container">
      <el-button
        type="primary"
        class="add-button"
        @click="handleAdd"
      >
        添加历史
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

      <!-- 操作列的插槽 -->
      <template #handle="{ row }">
        <el-button type="primary" plain @click="handleEdit(row)">编辑</el-button>
        <el-button type="danger" plain @click="handleDelete(row)">删除</el-button>
      </template>
    </CustomTable>

    <!-- 编辑对话框 -->
    <el-dialog v-model="editVisible" title="编辑历史记录" width="800px">
      <el-form :model="formData" label-width="100px" v-loading="loading">
        <el-form-item label="历史时间" required>
          <el-date-picker
            v-model="formData.historyTime"
            type="date"
            placeholder="选择日期"
            value-format="YYYY-MM-DD"
            style="width: 100%"
          />
        </el-form-item>

        <el-form-item label="历史标题" required>
          <el-input v-model="formData.historyTitle" />
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

        <el-form-item label="历史内容" required>
          <el-input
            v-model="formData.historyContent"
            type="textarea"
            :rows="8"
            placeholder="请输入历史内容"
          />
        </el-form-item>
      </el-form>

      <template #footer>
        <el-button @click="editVisible = false">取消</el-button>
        <el-button type="primary" @click="submitForm">保存</el-button>
      </template>
    </el-dialog>

    <!-- 添加对话框 -->
    <el-dialog v-model="addVisible" title="添加历史记录" width="800px">
      <el-form :model="addFormData" label-width="100px" v-loading="loading">
        <el-form-item label="历史时间" required>
          <el-date-picker
            v-model="addFormData.historyTime"
            type="date"
            placeholder="选择日期"
            value-format="YYYY-MM-DD"
            style="width: 100%"
          />
        </el-form-item>

        <el-form-item label="历史标题" required>
          <el-input v-model="addFormData.historyTitle" />
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

        <el-form-item label="历史内容" required>
          <el-input
            v-model="addFormData.historyContent"
            type="textarea"
            :rows="8"
            placeholder="请输入历史内容"
          />
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
import {tableConfig} from "./historyListConfig.js";
import {
  historyListApi,
  deleteHistoryApi,
  updateHistoryApi,
  addHistoryApi
} from "@/api/historyApi";
import {ElMessage, ElMessageBox} from "element-plus";
import { addImageApi, getResourceByLzApi } from "@/api/resourceApi.js";

// 表格数据
const tableData = ref([]);
const total = ref(0);
const queryParams = ref({
  pageNum: 1,
  pageSize: 10
});

// 编辑对话框
const editVisible = ref(false);
const formData = ref({
  historyId: null,
  historyTime: null,
  historyTitle: null,
  headerImageId: null,
  historyContent: null,
  state: true,
  headerImageUrl: ""
});

// 添加对话框
const addVisible = ref(false);
const addFormData = ref({
  historyTime: null,
  historyTitle: null,
  headerImageId: null,
  historyContent: null,
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

// 获取历史列表
const fetchHistoryList = async () => {
  try {
    loading.value = true;
    const res = await historyListApi(queryParams.value);
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
      ElMessage.error(res.message || '获取历史列表失败');
    }
  } catch (error) {
    console.error('获取历史列表出错:', error);
    ElMessage.error('获取历史列表失败，请稍后重试');
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
    fetchHistoryList();
  }
};

// 编辑历史
const handleEdit = async (row) => {
  const headerImageUrl = await getHeaderImageUrl(row.headerImage);
  formData.value = {
    historyId: row.historyId,
    historyTime: row.historyTime,
    historyTitle: row.historyTitle,
    headerImageId: row.headerImageId,
    historyContent: row.historyContent,
    state: row.state,
    headerImageUrl: headerImageUrl
  };
  editVisible.value = true;
};

// 删除历史
const handleDelete = async (row) => {
  try {
    await ElMessageBox.confirm(
      `确定要删除【${row.historyTitle}】吗？此操作不可恢复！`,
      '删除确认',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }
    );
    loading.value = true;
    const res = await deleteHistoryApi(row.historyId);
    if (res.code === 200) {
      ElMessage.success('删除成功');
      fetchHistoryList();
    } else {
      ElMessage.error(res.message || '删除失败');
    }
  } catch (error) {
    console.error('删除历史失败:', error);
    ElMessage.error('删除失败');
  } finally {
    loading.value = false;
  }
};

// 状态切换
const handleStatusChange = async (row) => {
  try {
    loading.value = true;
    const res = await updateHistoryApi({
      historyId: row.historyId,
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

// 封面图上传处理
const handleHeaderImageChange = async (file) => {
  const localUrl = URL.createObjectURL(file.raw);
  formData.value.headerImageUrl = localUrl;

  try {
    loading.value = true;
    const res = await addImageApi(file.raw, '历史封面图');
    if (res.code === 200) {
      formData.value.headerImageId = res.data.resourceId;
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
    if (!formData.value.historyTime || !formData.value.historyTitle || !formData.value.historyContent) {
      ElMessage.warning('请填写历史时间、标题和内容');
      return;
    }

    const res = await updateHistoryApi({
      historyId: formData.value.historyId,
      historyTime: formData.value.historyTime,
      historyTitle: formData.value.historyTitle,
      headerImageId: formData.value.headerImageId,
      historyContent: formData.value.historyContent,
      state: formData.value.state
    });

    if (res.code === 200) {
      ElMessage.success('保存成功');
      editVisible.value = false;
      await fetchHistoryList();
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

// 添加历史按钮点击事件
const handleAdd = () => {
  addFormData.value = {
    historyTime: null,
    historyTitle: null,
    headerImageId: null,
    historyContent: null,
    headerImageUrl: ''
  };
  addVisible.value = true;
};

// 添加历史封面图上传处理
const handleAddHeaderImageChange = async (file) => {
  const localUrl = URL.createObjectURL(file.raw);
  addFormData.value.headerImageUrl = localUrl;

  try {
    loading.value = true;
    const res = await addImageApi(file.raw, '历史封面图');
    if (res.code === 200) {
      addFormData.value.headerImageId = res.data.resourceId;
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
    if (!addFormData.value.historyTime || !addFormData.value.historyTitle || !addFormData.value.historyContent) {
      ElMessage.warning('请填写历史时间、标题和内容');
      return;
    }

    const res = await addHistoryApi({
      historyTime: addFormData.value.historyTime,
      historyTitle: addFormData.value.historyTitle,
      headerImageId: addFormData.value.headerImageId,
      historyContent: addFormData.value.historyContent
    });

    if (res.code === 200) {
      ElMessage.success('添加成功');
      addVisible.value = false;
      fetchHistoryList();
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
  fetchHistoryList();
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
</style>
