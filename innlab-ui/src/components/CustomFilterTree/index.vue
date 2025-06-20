<template>
  <div class="flex flex-col h-p-100">
    <div class="w-p-100 h-50">
      <el-input
        v-model="filterText"
        style="width: 100%"
        :prefix-icon="Search"
        :placeholder="placeholder"
        @input="filterTree"
      />
    </div>

    <div class="over-auto flex1">
      <el-tree
        ref="treeRef"
        :node-key="nodeKey"
        :data="data"
        :props="treeProps"
        highlight-current
        :default-expand-all="expandAll"
        :filter-node-method="filterNode"
        :default-expanded-keys="defaultExpandedKeys"
        :current-node-key="currentNodeKey"
        @current-change="handleCurrentChange"
      />
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from "vue";
import { Search } from "@element-plus/icons-vue";
const treeRef = ref(null);
const props = defineProps({
  data: {
    Type: Array,
    default: () => [],
  },
  placeholder: {
    Type: String,
    default: "请输入搜索内容",
  },
  treeProps: {
    Type: Object,
    default: () => {
      return {
        children: "children",
        label: "label",
      };
    },
  },
  expandAll: {
    Type: Boolean,
    default: false,
  },
  nodeKey: {
    Type: String,
    default: "id",
  },
  defaultExpandedKeys: {
    Type: Array,
    default: () => [], // 默认展开的节点数组
  },
  currentNodeKey: {
    Type: Number, // 默认选中的节点
  },
  defaultValue: {
    Type: String,
    default: ''
  }
});

const filterText = ref(props.defaultValue);

let timeOut;
const filterTree = (val) => {
  if (timeOut) {
    timeOut = null;
  }
  timeOut = setTimeout(() => {
    treeRef.value.filter(val);
  }, 200);
};
const filterNode = (value, data) => {
  if (!value) return true;
  return data[props.treeProps.label].includes(value);
};

const emits = defineEmits(["currentChange"]);
// 当选中节点发生变化时触发
const handleCurrentChange = (data, node) => {
  filterText.value = data.name;
  emits("currentChange", data);
};
</script>

<style lang="scss" scoped>
.over-auto {
  overflow: auto;
}
.over-auto::-webkit-scrollbar {
  display: none;
}
.h-50 {
  height: 50px;
}
</style>