<template>
  <div class="flex1 border-left">
    <div
      v-for="(item, index) in data"
      :key="item.menuId + index"
      class="flex align-center"
      :class="index == data.length - 1 ? '' : 'border-bottom'"
    >
      <div class="menuName text-center">{{ item.menuName }}</div>
      <nested-menu
        v-if="item.sonMenuButtonCustom && item.sonMenuButtonCustom.length"
        :data="item.sonMenuButtonCustom"
        :checked-data="checkedData"
      />
      <div
        v-else
        class="flex flex-wrap flex1 border-left pl-10 pt-5 pr-10 pb-5 min-height"
      >
        <el-checkbox
          v-for="(ite, idx) in item.listPermission"
          :key="ite.id || idx"
          :model-value="isChecked(ite.id)"
          @change="handleChange(ite.id)"
        >
          {{ ite.name }}
        </el-checkbox>
      </div>
    </div>
  </div>
</template>
  
  <script setup>
import { inject } from "vue";
const handleEvent = inject("handleEvent");
const props = defineProps({
  data: Array,
  checkedData: Array,
});

// 判断是否选中
const isChecked = (id) => {
  return props.checkedData.includes(id);
};

// 调用父组件的方法，修改checkedData
const handleChange = (id) => {
  handleEvent(id);
};
</script>
  <style scoped>
.border-bottom {
  border-bottom: 1px solid #d3d3d3;
}

.border-left {
  border-left: 1px solid #d3d3d3;
}

.menuName {
  width: 110px;
}
.min-height {
  min-height: 40px;
}
</style>
  