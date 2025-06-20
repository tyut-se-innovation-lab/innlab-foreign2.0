<template>
  <el-table
    :data="tableData"
    style="width: 100%;"
    :border="border"
    :stripe="stripe"
    :highlight-current-row="true"
    @selection-change="handleSelectionChange"
  >
    <el-table-column v-if="selection" type="selection" width="55" :align="align" />
    <el-table-column
      v-if="showIndex"
      type="index"
      width="60"
      label="序号"
      :index="indexMethod"
      :align="align"
    />
    <el-table-column
      v-for="(item, index) in tableConfig"
      :key="index"
      :label="item.label"
      :width="item.width"
      :align="align"
    >
      <template #default="scope">
        <slot v-if="item.slot" :name="item.slotName" :row="scope.row">
          <el-button type="primary" plain @click="handleEdit(scope.row)"
            >编辑</el-button
          >
          <el-button type="danger" plain @click="handleDelete(scope.row)"
            >删除</el-button
          >
        </slot>
        <div v-else-if="item.formatter">
          {{ item.formatter(scope.row[item.prop]) }}
        </div>
        <div v-else>{{ scope.row[item.prop] }}</div>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination
    v-if="pagination"
    class="mt-10"
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
    :current-page="currentPage"
    :page-sizes="pageSizes"
    :page-size="myPageSize"
    layout="total, sizes, prev, pager, next, jumper"
    :total="total"
    background
  >
  </el-pagination>
</template>

<script setup>
import { ref } from "vue";

const currentPage = ref(1);
const prop = defineProps({
  border: {
    Type: Boolean,
    default: true,
  },
  stripe: {
    Type: Boolean,
    default: false,
  },
  align: {
    Type: String,
    default: 'center'
  },
  tableConfig: {
    Type: Array,
    required: true,
  },
  tableData: {
    Type: Array,
    default: () => [],
  },
  selection: {
    Type: Boolean,
    default: false,
  },
  showIndex: {
    Type: Boolean,
    default: true,
  },
  pagination: {
    Type: Boolean,
    default: true,
  },
  pageSize: {
    Type: Number,
    default: 10,
  },
  pageSizes: {
    Type: Array,
    default: () => [10, 20, 50, 100],
  },
  total: {
    Type: Number,
    default: 0,
  },
});

const emit = defineEmits(["updateQueryData", "handleSelectionChange","handleEdit","handleDelete"]);
// 自定义序号
const indexMethod = (index) => {
  return (currentPage.value - 1) * myPageSize.value + index + 1;
};

// 设置每页条数
const myPageSize = ref(prop.pageSize);
const handleSizeChange = (num) => {
  myPageSize.value = num;
  currentPage.value = 1;
  let params = {
    pageNum: currentPage.value,
    pageSize: myPageSize.value,
  };
  emit("updateQueryData", params, true);
};

// 跳转到该页
const handleCurrentChange = (num) => {
  currentPage.value = num;
  let params = {
    pageNum: currentPage.value,
    pageSize: myPageSize.value,
  };
  emit("updateQueryData", params, true);
};

const handleSelectionChange = (array) => {
  emit("handleSelectionChange", array);
};

// 编辑
const handleEdit = (row) => {
  emit("handleEdit", row);
};

// 删除
const handleDelete = (row) => {
  emit("handleDelete", row);
};
</script>

<style scoped>
</style>
