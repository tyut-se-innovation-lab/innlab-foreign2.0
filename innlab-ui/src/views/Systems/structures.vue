<template>
  <div class="p-20 flex h-p-100 border-box over-hidden">
    <div
      class="el-card is-always-shadow w-200 mr-20 h-p-100 border-box over-hidden p-20"
    >
      <CustomFilterTree
        :data="myTreeData"
        :treeProps="treeProps"
        @currentChange="handleTreeChange"
        :currentNodeKey="currentNodeKey"
        :defaultExpandedKeys="defaultExpandedKeys"
        :defaultValue="userInfo.groupsName"
      ></CustomFilterTree>
    </div>
    <div class="flex1">
      <el-tabs v-model="activeName" class="demo-tabs">
        <el-tab-pane label="公司管理" name="first">公司管理</el-tab-pane>
        <el-tab-pane label="部门管理" name="second">部门管理</el-tab-pane>
        <el-tab-pane label="用户管理" name="third">用户管理</el-tab-pane>
        <el-tab-pane label="角色管理" name="fourth"
          ><roleVue></roleVue
        ></el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import roleVue from "./components/role.vue";
import CustomFilterTree from "@/components/CustomFilterTree/index.vue";
import { treeData } from "./simulatedData.js";
const activeName = ref("fourth");

// 根据后端返回的字段进行调整
const treeProps = {
  children: "listTreeCustom",
  label: "name",
  id: "id",
  isLeaf: "hasLeaf",
};

// 以下默认选中和展开的节点为模拟数据，实际开发可以从缓存获取用户的组织id
// 假设用户所在的组织信息为
const userInfo = {
  groupsId: 10000465,
  groupsName: '企业D'
}
const currentNodeKey = userInfo.groupsId;
const defaultExpandedKeys = [userInfo.groupsId];


// 如果公司不需要包在组织架构下面，可以不加这一层，直接使用treeData
const myTreeData = [
  {
    id: -1,
    name: "组织架构",
    type: 1,
    organizeId: -1,
    hasLeaf: false,
    listTreeCustom: treeData,
  },
];


// 当组织架构树结构选中节点发生改变时触发
const handleTreeChange = (node) => {
  console.log(node);
  // 根据返回的不同的节点id从后台请求不同的表格数据
};
</script>

<style lang="scss" scoped>
.w-200 {
  width: 200px;
}
.over-auto {
  overflow: auto;
}
</style>