<template>
  <div ref="popRef">
    <el-popover placement="bottom" :width="treeWidth" :visible="visible">
      <template #reference>
        <el-input
          :style="inputStyle"
          v-model="localValue"
          :placeholder="placeholder"
          clearable
          @click="visible = true"
          @input="filterTree"
        ></el-input>
      </template>
      <el-tree
        ref="treeRef"
        :data="data"
        :props="defaultProps"
        :node-key="nodeKey"
        :current-node-key="defaultKey"
        highlight-current
        auto-expand-parent
        :expand-on-click-node="false"
        :default-expanded-keys="[defaultKey]"
        @node-click="handleNodeClick"
        :filter-node-method="filterNode"
      />
    </el-popover>
  </div>
</template>

<script setup>
import { ref, watch, onUnmounted } from "vue";
const prop = defineProps({
  treeWidth: {
    Type: Number,
    default: 200,
  },
  data: {
    Type: Array,
    default: () => [],
  },
  nodeKey: {
    Type: String,
    default: "id",
  },
  defaultProps: {
    Type: Object,
    default: () => {
      return {
        children: "children",
        label: "label",
      };
    },
  },
  defaultKey: {
    Type: String | Number,
    default: "",
  },
  inputStyle: {
    Type: Object,
    default: () => {},
  },
  defaultLabel: {
    Type: String,
    default: "",
  },
  placeholder: {
    Type: String,
    default: "请选择",
  },
});

const localValue = ref(prop.defaultLabel);

const visible = ref(false);
const emit = defineEmits(["node-click"]);

const handleNodeClick = (node) => {
  localValue.value = node.label;
  visible.value = false;
  emit("node-click", node);
};
const treeRef = ref();

const filterTree = (val) => {
  treeRef.value.filter(val);
};
const filterNode = (value, data) => {
  if (!value) return true;
  return data.label.includes(value);
};

const popRef = ref();
const handlePopTree = (e) => {
  if (!popRef.value.contains(e.target)) {
    visible.value = false;
  }
};
document.addEventListener("click", handlePopTree);
onUnmounted(() => {
  console.log("销毁");
  document.removeEventListener("click", handlePopTree);
});
</script>

<style scoped>
</style>