<template>
  <div>
    <CustomSearch
      class="mb-20"
      :searchConfig="roleSearchConfig"
      @updateQueryData="updateQueryData"
    ></CustomSearch>
    <CustomTable
      :tableConfig="roleTableConfig"
      :tableData="roleData"
      :total="total"
      @updateQueryData="updateQueryData"
    >
    <template #handle="row">
     <el-button text type="success" size="small">编辑</el-button>
     <el-button text type="danger" size="small">删除</el-button>
     <el-button text type="primary" size="small" @click="handleMenuPermissions(row.row.id)">菜单权限</el-button>
     <el-button text type="primary" size="small" @click="handleFunctionalAuthority(row.row.id)">功能权限</el-button>
    </template>
  </CustomTable>
  </div>
  <CustomFunctionsDialog v-model="functionsDialogVisible" @close = "functionsDialogVisible = false" :roleId="roleId"></CustomFunctionsDialog>
  <CustomMenuPermissionsDialog v-model="menuPermissionsDialogVisible" @close = "menuPermissionsDialogVisible = false" :roleId="roleId"></CustomMenuPermissionsDialog>
  
</template>

<script setup>
import CustomTable from "@/components/CustomTable/index.vue";
import CustomSearch from "@/components/CustomSearch/index.vue";
import CustomFunctionsDialog from "./CustomFunctionsDialog.vue";
import CustomMenuPermissionsDialog from "./CustomMenuPermissionsDialog.vue";
import { roleTableConfig } from "./tableConfig.js";
import { roleSearchConfig } from "./searchConfig.js";
import { roleData } from "./simulatedData.js";
import { ref } from "vue";

const total = ref(0);

const updateQueryData = (params, bool) => {};

// 打开功能权限弹窗
const roleId = ref() // 根据不同的角色id获取不同的权限按钮
const functionsDialogVisible = ref(false)
const handleFunctionalAuthority = (id) => {
  roleId.value = id
  functionsDialogVisible.value = true
}

// 打开菜单权限弹窗
const menuPermissionsDialogVisible = ref(false)
const handleMenuPermissions = (id) => {
  roleId.value = id
  menuPermissionsDialogVisible.value = true
}
</script>

<style lang="scss" scoped>
</style>