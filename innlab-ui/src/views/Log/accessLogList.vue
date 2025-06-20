<template>
  <div class="p-20">
    <CustomTable
      :tableConfig="accessLogConfig"
      :tableData="tableData"
      :total="total"
      @updateQueryData="updateQueryData"
    >
      <!-- 操作列的插槽 -->
      <template #handle="{ row }">
        <el-button type="primary" plain @click="handleDetail(row)">详情</el-button>
      </template>
    </CustomTable>

    <!-- 详情对话框 -->
    <el-dialog v-model="detailVisible" title="日志详情" width="600px">
      <el-descriptions :column="1" border>
        <el-descriptions-item label="日志ID">{{ detailData.logId }}</el-descriptions-item>
        <el-descriptions-item label="访问IP">{{ detailData.accessIp }}</el-descriptions-item>
        <el-descriptions-item label="访问路径">{{ detailData.accessUrl }}</el-descriptions-item>
        <el-descriptions-item label="访问时间">{{ detailData.accessTime }}</el-descriptions-item>
        <el-descriptions-item label="请求参数">
          <pre>{{ JSON.stringify(JSON.parse(detailData.method || '{}'), null, 2) }}</pre>
        </el-descriptions-item>
      </el-descriptions>

      <template #footer>
        <el-button type="primary" @click="detailVisible = false">关闭</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import CustomTable from "@/components/CustomTable/index.vue";
import { ref, onMounted } from "vue";
import { accessLogConfig } from "./logConfig";
import { accessLogApi } from "@/api/logApi";
import { ElMessage } from "element-plus";

// 表格数据
const tableData = ref([]);
const total = ref(0);
const queryParams = ref({
  pageNum: 1,
  pageSize: 10
});

// 详情对话框
const detailVisible = ref(false);
const detailData = ref({
  logId: null,
  accessIp: null,
  accessUrl: null,
  accessTime: null,
  method: null
});

const loading = ref(false);

// 获取日志列表
const fetchLogList = async () => {
  try {
    loading.value = true;
    const res = await accessLogApi(queryParams.value);
    if (res.code === 200) {
      tableData.value = res.data.records;
      total.value = res.data.total;
    } else {
      ElMessage.error(res.message || '获取日志列表失败');
    }
  } catch (error) {
    console.error('获取日志列表出错:', error);
    ElMessage.error('权限不足！');
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
    fetchLogList();
  }
};

// 查看详情
const handleDetail = (row) => {
  detailData.value = {
    logId: row.logId,
    accessIp: row.accessIp,
    accessUrl: row.accessUrl,
    accessTime: row.accessTime,
    method: row.method
  };
  detailVisible.value = true;
};

// 组件挂载时加载数据
onMounted(() => {
  fetchLogList();
});
</script>

<style scoped>
pre {
  white-space: pre-wrap;
  word-wrap: break-word;
  background: #f5f7fa;
  padding: 10px;
  border-radius: 4px;
}
</style>
