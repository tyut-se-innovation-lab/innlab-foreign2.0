<template>
  <div class="p-20">
    <CustomTable
      :tableConfig="sysLogConfig"
      :tableData="tableData"
      :total="total"
      @updateQueryData="updateQueryData"
    >
      <!-- 状态列的插槽 -->
      <template #status="{ row }">
        <el-tag :type="row.resultCode === 200 ? 'success' : 'danger'"
        >{{row.resultCode}}
        </el-tag>
      </template>

      <!-- 操作类型列的插槽 -->
      <template #type="{ row }">
        <el-tag :type="row.type === '新增' ? 'success' : (row.type === '修改' ? 'primary' : (row.type === '删除' ? 'danger' : 'info'))">
        {{row.type}}
        </el-tag>
      </template>
      <!-- 操作列的插槽 -->
      <template #handle="{ row }">
        <el-button type="primary" plain @click="handleDetail(row)">详情</el-button>
      </template>
    </CustomTable>

    <!-- 详情对话框 -->
    <el-dialog v-model="detailVisible" title="系统日志详情" width="700px">
      <el-descriptions :column="1" border>
        <el-descriptions-item label="日志ID">{{ detailData.logId }}</el-descriptions-item>
        <el-descriptions-item label="用户账号">{{ detailData.userAccount }}</el-descriptions-item>
        <el-descriptions-item label="操作IP">{{ detailData.ip }}</el-descriptions-item>
        <el-descriptions-item label="操作类型">{{ detailData.type }}</el-descriptions-item>
        <el-descriptions-item label="操作模块">{{ detailData.model }}</el-descriptions-item>
        <el-descriptions-item label="操作描述">{{ detailData.description }}</el-descriptions-item>
        <el-descriptions-item label="请求URL">{{ detailData.url }}</el-descriptions-item>
        <el-descriptions-item label="请求方法">{{ detailData.method }}</el-descriptions-item>
        <el-descriptions-item label="操作状态">
          <el-tag :type="detailData.resultCode === 200 ? 'success' : 'danger'">
            {{ detailData.resultCode}}
          </el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="状态信息">{{ detailData.resultMessage }}</el-descriptions-item>
        <el-descriptions-item label="请求参数">
          <pre>{{ formatParams(detailData.params) }}</pre>
        </el-descriptions-item>
        <el-descriptions-item label="操作时间">{{ detailData.createTime }}</el-descriptions-item>
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
import { sysLogConfig } from "./logConfig.js";
import { sysLogApi } from "@/api/logApi";
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
  userAccount: null,
  url: null,
  method: null,
  params: null,
  ip: null,
  type: null,
  model: null,
  resultCode: null,
  resultMessage: null,
  description: null,
  createTime: null
});

const loading = ref(false);

// 格式化参数显示
const formatParams = (params) => {
  try {
    // 尝试解析类似Java对象的字符串
    if (params.startsWith('[') && params.includes('(') && params.includes(')')) {
      return params
        .replace(/$$([^$$]+)\]/g, '$1') // 移除外层方括号
        .replace(/(\w+)=/g, '"$1": ') // 将 key=value 转换为 "key": value
        .replace(/\(/g, '{') // 将 ( 替换为 {
        .replace(/\)/g, '}'); // 将 ) 替换为 }
    }
    return params;
  } catch {
    return params;
  }
};

// 获取系统日志列表
const fetchSysLogList = async () => {
  try {
    loading.value = true;
    const res = await sysLogApi(queryParams.value);
    if (res.code === 200) {
      tableData.value = res.data.records;
      total.value = res.data.total;
    } else {
      ElMessage.error(res.message || '获取系统日志失败');
    }
  } catch (error) {
    console.error('获取系统日志出错:', error);
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
    fetchSysLogList();
  }
};

// 查看详情
const handleDetail = (row) => {
  detailData.value = {
    logId: row.logId,
    userAccount: row.userAccount,
    url: row.url,
    method: row.method,
    params: row.params,
    ip: row.ip,
    type: row.type,
    model: row.model,
    resultCode: row.resultCode,
    resultMessage: row.resultMessage,
    description: row.description,
    createTime: row.createTime
  };
  detailVisible.value = true;
};

// 组件挂载时加载数据
onMounted(() => {
  fetchSysLogList();
});
</script>

<style scoped>
.el-tag {
  margin-right: 10px;
}

pre {
  white-space: pre-wrap;
  word-wrap: break-word;
  background: #f5f7fa;
  padding: 10px;
  border-radius: 4px;
  max-height: 200px;
  overflow-y: auto;
}
</style>
