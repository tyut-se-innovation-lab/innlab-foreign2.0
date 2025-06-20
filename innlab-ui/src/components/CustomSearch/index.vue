<template>
  <div class="flex flex-wrap">
    <div
      class="flex align-center mb-10"
      v-for="(item, index) in searchConfig"
      :key="index"
    >
      <div v-if="item.title">{{ item.title }}：</div>
      <my-input
        v-if="item.type == 'input'"
        class="mr-10 w-200"
        :style="item.style"
        :value="item.defaultValue"
        :placeholder="item.placeholder"
        clearable
        @change="(e) => changeValue(e, item.id, item.automaticQuery)"
      ></my-input>
      <MySelect
        v-if="item.type == 'select'"
        class="mr-10 w-200"
        :value="item.defaultValue"
        :placeholder="item.placeholder"
        :options="item.options"
        :label-key="item.label"
        :value-key="item.value"
        @change="(e) => changeValue(e, item.id, item.automaticQuery)"
      ></MySelect>
      <MyTree
      v-if="item.type == 'tree'"
      class="mr-10 w-200"
        :data="item.data"
        :defaultProps="item.defaultProps"
        :placeholder="item.placeholder"
        :node-key="item.id"
        @node-click="(e) => changeValue(e[item.id], item.id, item.automaticQuery)"
      ></MyTree>
      <MyDateTimePicker
      v-if="item.type == 'datetime' || item.type == 'datetimerange'"
      class="mr-10 w-200"
      :type="item.type"
      :placeholder="item.placeholder"
      :start-placeholder="item.startPlaceholder"
      :end-placeholder="item.endPlaceholder"
      :defaultValue="item.defaultValue"
      :customId="item.id"
      :automaticQuery="item.automaticQuery"
       @change="(e) => changeValue(e, item.id, item.automaticQuery)"
      ></MyDateTimePicker>
    </div>
    <el-button
      v-if="showQueryButton"
      type="primary"
      plain
      icon="Search"
      @click="queryData"
      >{{ queryButtonText }}</el-button
    >
    <slot></slot>
  </div>
</template>

<script setup>
import MyInput from "../MyInput/index.vue";
import MySelect from "../MySelect/index.vue";
import MyTree from "../MyTree/index.vue"
import MyDateTimePicker from "../MyDateTimePicker/index.vue"
import { ref } from "vue";

const prop = defineProps({
  searchConfig: {
    Type: Array,
    required: true,
  },
  showQueryButton: {
    Type: Boolean,
    default: true,
  },
  queryButtonText: {
    Type: String,
    default: "查询",
  },
});
const emit = defineEmits(["updateQueryData"]);

// 搜索值变化
// value 新值
// id 对应的key
// automaticQuery 是否立即触发查询接口
const changeValue = (value, id, automaticQuery) => {
  let params = {
    [id]: value,
  };
  emit("updateQueryData", params, automaticQuery);
};

// 查询
const queryData = () => {
  emit("updateQueryData", {}, true);
};
</script>

<style scoped>
.w-200 {
  width: 200px;
}
</style>