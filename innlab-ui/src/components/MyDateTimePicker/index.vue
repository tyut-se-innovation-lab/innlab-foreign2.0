<template>
  <el-date-picker
    v-model="localValue"
    :type="type"
    :placeholder="placeholder"
    :start-placeholder="startPlaceholder"
    :end-placeholder="endPlaceholder"
    :format="format"
  />
</template>

<script setup>
import { ref, watch } from "vue";

const prop = defineProps({
  type: {
    Type: String,
    default: "datetime",
  },
  placeholder: {
    Type: String,
    default: "请选择时间",
  },
  startPlaceholder: {
    Type: String,
    default: "请选择开始时间",
  },
  endPlaceholder: {
    Type: String,
    default: "请选择结束时间",
  },
  format: {
    Type: String,
    default: "YYYY-MM-DD HH:mm:ss",
  },
  defaultValue: {
    Type: String,
    default: "",
  },
  customId: {
    Type: String,
    default: "",
  },
  automaticQuery: {
    Type: Boolean,
    default: false,
  },
});

// 设置默认值，并将默认值传回父组件
const emit = defineEmits(["change"]);
if (prop.defaultValue) {
  emit("change", prop.defaultValue, prop.customId, prop.automaticQuery);
}

const localValue = ref(prop.defaultValue);
</script>

<style scoped>
</style>