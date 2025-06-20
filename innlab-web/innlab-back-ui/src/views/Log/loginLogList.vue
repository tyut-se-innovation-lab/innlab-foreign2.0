<template>
  <div class="p-20">
    <CustomTable
      :tableConfig="loginLogConfig"
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
      <!-- 操作列的插槽 -->
      <template #handle="{ row }">
        <el-button type="primary" plain @click="handleDetail(row)">详情</el-button>
      </template>
    </CustomTable>

    <!-- 详情对话框 -->
    <el-dialog v-model="detailVisible" title="登录日志详情" width="600px">
      <el-descriptions :column="1" border>
        <el-descriptions-item label="日志ID">{{ detailData.logId }}</el-descriptions-item>
        <el-descriptions-item label="用户账号">{{ detailData.userAccount }}</el-descriptions-item>
        <el-descriptions-item label="登录IP">{{ detailData.loginIp }}</el-descriptions-item>
        <el-descriptions-item label="操作系统">{{ detailData.osName }}</el-descriptions-item>
        <el-descriptions-item label="浏览器">{{ detailData.browser }}</el-descriptions-item>
        <el-descriptions-item label="登录地点">{{ detailData.loginLocation }}</el-descriptions-item>
        <el-descriptions-item label="登录状态">
          <el-tag :type="detailData.resultCode === 200 ? 'success' : 'danger'">
            {{ detailData.resultCode }}
          </el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="状态信息">{{ detailData.resultMessage }}</el-descriptions-item>
        <el-descriptions-item label="登录时间">{{ detailData.loginTime }}</el-descriptions-item>
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
import { loginLogApi } from "@/api/logApi";
import { ElMessage } from "element-plus";
import {loginLogConfig} from "@/views/Log/logConfig.js";

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
  loginIp: null,
  osName: null,
  browser: null,
  loginLocation: null,
  resultCode: null,
  resultMessage: null,
  loginTime: null
});

const loading = ref(false);

// 获取登录日志列表
const fetchLoginLogList = async () => {
  try {
    loading.value = true;
    const res = await loginLogApi(queryParams.value);
    if (res.code === 200) {
      tableData.value = res.data.records;
      total.value = res.data.total;
    } else {
      ElMessage.error(res.message || '获取登录日志失败');
    }
  } catch (error) {
    console.error('获取登录日志出错:', error);
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
    fetchLoginLogList();
  }
};

// 查看详情
const handleDetail = (row) => {
  detailData.value = {
    logId: row.logId,
    userAccount: row.userAccount,
    loginIp: row.loginIp,
    osName: row.osName,
    browser: row.browser,
    loginLocation: row.loginLocation,
    resultCode: row.resultCode,
    resultMessage: row.resultMessage,
    loginTime: row.loginTime
  };
  detailVisible.value = true;
};

// 组件挂载时加载数据
onMounted(() => {
  fetchLoginLogList();
});
</script>

<style scoped>
.el-tag {
  margin-right: 10px;
}
</style>
