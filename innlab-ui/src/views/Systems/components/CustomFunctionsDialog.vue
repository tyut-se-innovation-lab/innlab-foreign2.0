<template>
  <el-dialog title="功能权限" width="1000" center destroy-on-close>
    <div>
      <div>角色名称：jiaberr</div>
      <div>
        <text>功能权限：</text>
        <el-checkbox v-model="checkAll" @change="handleCheckAllChange">
          全选/全不选
        </el-checkbox>
      </div>
      <div class="w-p-100 containner">
        <nested-menu :data="funtionsData" :checkedData="checkedData" />
      </div>
    </div>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="close">取消</el-button>
        <el-button type="primary" @click="handleSubmit"> 确定 </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, provide, watchEffect } from "vue";
import {
  funtionsData1,
  funtionsData2,
  rolePermissionData,
} from "./simulatedData.js";
import NestedMenu from "./NestedMenu.vue"; // 确保正确导入NestedMenu组件

const props = defineProps({
  roleId: {
    Type: Number,
    required: true,
  },
});

const checkAll = ref(false);
const funtionsData = ref();
watchEffect(() => {

  // 仅模拟页面效果使用，真实项目funtionsData需根据id获取后台数据
  if (props.roleId == 1) {
    funtionsData.value = funtionsData1;
  } else {
    funtionsData.value = funtionsData2;
  }
});

const emits = defineEmits(["close"]);
const close = () => {
  emits("close");
};
const checkedData = ref(rolePermissionData);
const handleCheckAllChange = (val) => {
  checkedData.value = val ? permissionIds : [];
  console.log(checkedData.value);
};
// provide('checkedData', checkedData.value);
const handleCheckData = (id) => {
  let index = checkedData.value.findIndex((val) => val == id);
  if (index != -1) {
    checkedData.value.splice(index, 1);
  } else {
    checkedData.value.push(id);
  }
};
provide("handleEvent", handleCheckData);

// 提交
const handleSubmit = () => {
  // 将选中的id数组上传至后台接口
  console.log(checkedData.value);

  emits("close");
};

// 收集所有功能权限的id
const extractPermissionIds = (data) => {
  const ids = [];

  // 递归遍历函数
  function traverse(node) {
    if (node.listPermission && Array.isArray(node.listPermission)) {
      // 将 listPermission 中的 id 添加到 ids 数组
      node.listPermission.forEach((permission) => {
        ids.push(permission.id);
      });
    }

    // 如果有子节点，继续递归遍历
    if (node.sonMenuButtonCustom && Array.isArray(node.sonMenuButtonCustom)) {
      node.sonMenuButtonCustom.forEach((child) => traverse(child));
    }
  }

  // 遍历根节点
  data.forEach((item) => traverse(item));

  return ids;
};
const permissionIds = extractPermissionIds(funtionsData.value);
</script>

<style lang="scss" scoped>
.containner {
  border-top: 1px solid #d3d3d3;
  border-right: 1px solid #d3d3d3;
  border-bottom: 1px solid #d3d3d3;
}
</style>