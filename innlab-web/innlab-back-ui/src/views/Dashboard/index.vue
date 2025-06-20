<template>
  <div class="p-20">
    <div class="flex-center flex-col">
      <div class="fz-18 text-bold">组件信息</div>
      <div class="text-333 mt-20 mb-20">——基于element-plus二次封装</div>
      <CustomTable
        :pagination="false"
        :tableConfig="tableConfig"
        :tableData="tableData"
        stripe
      >
        <template #url="scope">
          <el-link :underline="false" type="primary" :href="scope.row.url" target="_blank">{{ scope.row.url }}</el-link>
        </template>
      </CustomTable>
    </div>

    <div class="flex-center flex-col mt-20">
      <div class="fz-18 text-bold">工具信息</div>
      <div class="text-333 mt-20 mb-20">——常见需求的工具封装</div>
      <CustomTable
        :pagination="false"
        :tableConfig="tableConfig2"
        :tableData="tableData2"
        stripe
      >
        <template #url="scope">
          <el-link type="primary">{{ scope.row.url }}</el-link>
        </template>
      </CustomTable>
    </div>
  </div>
</template>

<script setup>
import CustomTable from "@/components/CustomTable/index.vue";
import { tableConfig, tableConfig2 } from "./tableConfig.js";

const tableData = [
  {
    name: "表格组件",
    info: "带分页器和不带分页器的表格展示",
    path: "src/components/CustomTable/index.vue",
    url: "https://blog.csdn.net/Jiaberrr/article/details/144894734",
  },
  {
    name: "条件筛选组件",
    info: "多中筛选条件查询，可结合表格组件使用",
    path: "src/components/CustomSearch/index.vue",
    url: "https://blog.csdn.net/Jiaberrr/article/details/144932802",
  },
  {
    name: "表单组件",
    info: "各种信息收集提交",
    path: "src/components/CustomForm/index.vue",
    url: "",
  },
  {
    name: "表单对话框组件",
    info: "新增、编辑等表单对话框",
    path: "src/components/CustomEditDialog/index.vue",
    url: "",
  },
];

const tableData2 = [
  {
    name: "页面转pdf",
    info: "将页面或页面中部分内容转成pdf并下载到本地",
    path: "src/utils/htmlToPdf.js",
    url: "https://blog.csdn.net/Jiaberrr/article/details/144928983",
  },
  {
    name: "目标元素打印",
    info: "将页面中指定id元素打印或生成pdf",
    path: "src/utils/printToPdf.js",
    url: "https://blog.csdn.net/Jiaberrr/article/details/144905038",
  },
];
</script>

<style lang="scss" scoped>
</style>