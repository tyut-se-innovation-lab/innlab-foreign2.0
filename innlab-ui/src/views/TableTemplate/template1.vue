<template>
  <div class="p-20">
    <CustomSearch
      class="mb-20"
      :searchConfig="searchConfig"
      @updateQueryData="updateQueryData"
    ></CustomSearch>
    <CustomTable
      :tableConfig="tableConfig"
      :tableData="tableData"
      :total="total"
      @updateQueryData="updateQueryData"
      @handleEdit="handleEdit"
      @handleDelete="handleDelete"
    ></CustomTable>
    <EditDialog
      v-model="editVisible"
      :rules="rules"
      :initialData="formData"
      @submitForm="submitForm"
    ></EditDialog>
  </div>
</template>

<script setup>
import CustomTable from "@/components/CustomTable/index.vue";
import CustomSearch from "@/components/CustomSearch/index.vue";
import EditDialog from "@/components/CustomEditDialog/index.vue";
import { ref } from "vue";
import { tableConfig, searchConfig } from "./tableConfig";

const total = ref(14);
const tableData = [
  {
    date: "2016-05-04",
    name: "张三",
    address: "重庆交通大学",
    pushWayValue: "短信",
    num: 1,
  },
  {
    date: "2016-05-03",
    name: "李四",
    address: "重庆理工大学",
    pushWayValue: "短信",
    num: 3,
  },
  {
    date: "2016-05-02",
    name: "王五",
    address: "重庆工商大学",
    pushWayValue: "钉钉",
    num: 0,
  },
  {
    date: "2016-05-01",
    name: "李阳",
    address: "重庆医科大学",
    pushWayValue: "微信",
    num: 6,
  },
  {
    date: "2016-05-01",
    name: "李阳",
    address: "重庆医科大学",
    pushWayValue: "微信",
    num: 6,
  },
  {
    date: "2016-05-01",
    name: "李阳",
    address: "重庆医科大学",
    pushWayValue: "微信",
    num: 6,
  },
  {
    date: "2016-05-01",
    name: "李阳",
    address: "重庆医科大学",
    pushWayValue: "微信",
    num: 6,
  },
  {
    date: "2016-05-01",
    name: "李阳",
    address: "重庆医科大学",
    pushWayValue: "微信",
    num: 6,
  },
  {
    date: "2016-05-01",
    name: "李阳",
    address: "重庆医科大学",
    pushWayValue: "微信",
    num: 6,
  },
  {
    date: "2016-05-01",
    name: "李阳",
    address: "重庆医科大学",
    pushWayValue: "微信",
    num: 6,
  },
  {
    date: "2016-05-01",
    name: "李阳",
    address: "重庆医科大学",
    pushWayValue: "微信",
    num: 6,
  },
  {
    date: "2016-05-01",
    name: "李阳",
    address: "重庆医科大学",
    pushWayValue: "微信",
    num: 6,
  },
  {
    date: "2016-05-01",
    name: "李阳",
    address: "重庆医科大学",
    pushWayValue: "微信",
    num: 6,
  },
  {
    date: "2016-05-01",
    name: "李阳",
    address: "重庆医科大学",
    pushWayValue: "微信",
    num: 6,
  }
];

const treeData = [
  {
    label: "分组1",
    groupsId: 1,
    children: [
      {
        label: "分组1-1",
        groupsId: 11,
        children: [
          {
            label: "分组1-1-1",
            groupsId: 111,
          },
        ],
      },
    ],
  },
  {
    label: "分组2",
    groupsId: 2,
    children: [
      {
        label: "分组2-1",
        groupsId: 21,
        children: [
          {
            label: "分组2-1-1",
            groupsId: 211,
          },
        ],
      },
      {
        label: "分组2-2",
        groupsId: 22,
        children: [
          {
            label: "分组2-2-1",
            groupsId: 221,
          },
        ],
      },
    ],
  },
  {
    label: "分组3",
    groupsId: 3,
    children: [
      {
        label: "分组3-1",
        groupsId: 31,
        children: [
          {
            label: "分组3-1-1",
            groupsId: 311,
          },
        ],
      },
      {
        label: "分组3-2",
        groupsId: 32,
        children: [
          {
            label: "分组3-2-1",
            groupsId: 321,
          },
        ],
      },
    ],
  },
];
searchConfig[0].data = treeData;

const queryParams = ref({});

// 更新查询参数，如果bool为true则调后台接口
const updateQueryData = (params, bool) => {
  queryParams.value = { ...queryParams.value, ...params };
  console.log(queryParams.value);
};

const editVisible = ref(false);
const rules = {
  name: [{ required: true, message: "请输入姓名", trigger: "blur" }],
  pushWayValue: [{ required: true, message: "请选择推送方式", trigger: "blur" }],
};

const formData = ref({});
// 编辑
const handleEdit = (row) => {
  // 实际项目中，formData建议通过id去重新请求获取
  formData.value = row;
  editVisible.value = true;
};

// 确定
const submitForm = (data) => {
  console.log(data);
  editVisible.value = false;
};

// 删除
const handleDelete = (row) => {
  console.log("删除", row);
};
</script>

<style scoped>
</style>