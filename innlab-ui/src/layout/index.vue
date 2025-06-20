
<template>
  <div class="common-layout h-p-100 w-p-100">
    <el-container class="h-p-100 w-p-100">
      <el-aside
        :width="isCollapse ? '60px' : '180px'"
        class="common-aside w-p-100"
      >
        <MyMenu :isCollapse="isCollapse"></MyMenu>
      </el-aside>
      <el-container>
        <el-header class="common-header flex align-center">
          <el-icon :size="24" color="#409efc" @click="isCollapse = !isCollapse">
            <Expand v-if="isCollapse" />
            <Fold v-else />
          </el-icon>
          <Breadcrumb class="ml-10"></Breadcrumb>
          <Personal class="flex-item-right"></Personal>
        </el-header>
        <el-main class="flex-col">
          <Tag class="my-tag"></Tag>
          <div class="flex1 bg-white common-main" v-if="alive">
            <router-view></router-view>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
  <script setup>
import { ref, watchEffect } from "vue";
import { useTagStore } from "@/stores/tagList.js";
const tagStore = useTagStore();
import MyMenu from "./components/menu.vue";
import Tag from "./components/tag.vue";
import Breadcrumb from "./components/breadcrumb.vue";
import Personal from "./components/personal.vue";
const isCollapse = ref(false);
const alive = ref(true)
watchEffect(() => {
  alive.value = tagStore.alive
})
</script>
<style scoped>
.common-aside {
  background: #fff;
  box-shadow: #d8d8d8 1px 1px 15px 1px;
  z-index: 1;
  transition: width 0.2s ease-in-out;
}
.common-header {
  background: #fff;
  box-shadow: #d8d8d8 1px 1px 5px 1px;
}
.common-main {
  border-radius: 0 0 5px 5px;
  overflow: auto;
}
.my-tag {
  flex-shrink: 0;
}
</style>