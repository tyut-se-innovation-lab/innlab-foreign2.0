<template>
  <div class="p-20">
    <CustomSearch
      class="mb-20"
      :searchConfig="searchConfig2"
      @updateQueryData="updateQueryData"
    >
      <el-button type="success" plain @click="exportPDF">导出pdf</el-button>
      <el-button type="warning" plain @click="printPDF">打印pdf</el-button>
    </CustomSearch>
    <div id="table" class="w-p-100">
        <CustomTable
          :tableConfig="tableConfig2"
          :tableData="tableData"
          :pagination="false"
          @updateQueryData="updateQueryData"
          ><template #handle="row">
            <el-text v-if="row.row.pass" class="mx-1" type="primary"
              >合格</el-text
            >
            <el-text v-else class="mx-1" type="danger">不合格</el-text>
          </template>
        </CustomTable>
    </div>
  </div>
</template>
  
  <script setup>
import CustomTable from "@/components/CustomTable/index.vue";
import CustomSearch from "@/components/CustomSearch/index.vue";
import { ref } from "vue";
import { tableConfig2, searchConfig2 } from "./tableConfig";
import { ElMessage } from "element-plus";
import { downloadPdf } from "@/utils/htmlToPdf";
import printToPdf from "@/utils/printToPdf.js"
const tableData = [
  {
    date: "2016-05-04",
    name: "张三",
    address: "重庆交通大学",
    pushWayValue: "短信",
    num: 1,
    pass: true,
  },
  {
    date: "2016-05-03",
    name: "李四",
    address: "重庆理工大学",
    pushWayValue: "短信",
    num: 3,
    pass: false,
  },
  {
    date: "2016-05-02",
    name: "王五",
    address: "重庆工商大学",
    pushWayValue: "钉钉",
    num: 0,
    pass: true,
  },
  {
    date: "2016-05-01",
    name: "李阳",
    address: "重庆医科大学",
    pushWayValue: "微信",
    num: 6,
    pass: true,
  },
];

const queryParams = ref({});

// 更新查询参数，如果bool为true则调后台接口
const updateQueryData = (params, bool) => {
  queryParams.value = { ...queryParams.value, ...params };
  console.log(queryParams.value);
};

// 导出PDF
const exportPDF = () => {
  downloadPdf("table", "表格");
};

// 打印PDF
const printPDF = () => {
  printToPdf('table')
}
</script>
  
  <style scoped>
</style>