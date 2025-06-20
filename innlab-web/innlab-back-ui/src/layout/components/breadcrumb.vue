<template>
  <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/dashboard' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item v-for="item in breadcrumbList" :key="item.path">{{
        item.meta.breadcrumbName
      }}</el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script setup>
import { watch, ref } from "vue";
import { useRoute } from "vue-router";
const route = useRoute();
const breadcrumbList = ref(route.path == "/dashboard" ? [] : route.matched);

watch(
  () => route.matched,
  (newList, oldList) => {
    if (route.path == "/dashboard") {
      breadcrumbList.value = [];
    } else {
      breadcrumbList.value = newList;
    }
  }
);
</script>

<style scoped>
</style>