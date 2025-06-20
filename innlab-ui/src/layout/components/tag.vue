<template>
  <div class="tag-container flex justif-between pl-3 pr-3 align-center">
    <div class="tag-item-container flex align-center h-p-100">
      <div
        class="tag-item flex justify-around align-center mr-3 relative h-p-100"
        :class="item.name == $route.path ? 'tag-active' : 'tag-normal'"
        v-for="(item, index) in tagList"
        :key="item.name"
        @click.stop="switchRoute(item)"
      >
        <div :underline="false" class="tag-title fz-12">
          {{ item.breadcrumbName }}
        </div>
        <el-icon
          v-if="item.name != '/dashboard'"
          :size="14"
          title="关闭窗口"
          @click.stop="closeTag(item.name, index)"
          ><Close
        /></el-icon>
      </div>
    </div>
    <el-dropdown
      trigger="click"
      placement="bottom-end"
      class="tag-item tag-active h-p-100 flex-center"
    >
      <div class="flex-center fz-12">
        标签管理<el-icon class="el-icon--right"><arrow-down /></el-icon>
      </div>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item @click="reload"
            ><span class="fz-12">刷新</span></el-dropdown-item
          >
          <el-dropdown-item @click="closeOther"
            ><span class="fz-12">关闭其他</span></el-dropdown-item
          >
          <el-dropdown-item @click="closeAll"
            ><span class="fz-12">关闭所有</span></el-dropdown-item
          >
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
</template>

<script setup>
import router from "@/router";
import { useTagStore } from "@/stores/tagList.js";
import { useRoute } from "vue-router";
const route = useRoute();
const tagStore = useTagStore();
const tagList =  tagStore.tagList;

// 切换Tag
const switchRoute = (item) => {
  router.push(item.name);
};

// 关闭窗口
const closeTag = (path, index) => {
  tagStore.deleteTagList(index);
  if (route.path == path) {
    router.push(tagList[tagList.length - 1].name);
  }
};

// 刷新
const reload = () => {
  tagStore.reload()
};

// 关闭除首页外其他窗口
const closeOther = () => {
   let rawArray = tagList.find(val => val.name == route.path)
    tagStore.closeTagOther(rawArray)
};

// 关闭除首页外所有窗口
const closeAll = () => {
  tagStore.closeAllTag()
  router.push('/dashboard')
};
</script>

<style scoped>
.tag-container {
  z-index: 1;
  height: 30px;
  background: #fff;
  box-shadow: #d8d8d8 1px 1px 5px 1px;
  border-radius: 5px 5px 0 0;
}

.tag-item {
  width: 80px;
  height: 80%;
  border-radius: 3px;
  cursor: pointer;
  padding:0 2px;
}
.tag-title {
  width: 70px;
  overflow: hidden;
  text-align: center;
  align-items: center;
  white-space: nowrap;
  text-overflow: ellipsis;
}
.tag-active {
  background-color: #409efc;
  color: #fff;
}
.tag-normal {
  background-color: #f0f0f0;
  color: #888;
}
</style>