<template>
  <el-dialog
    title="菜单权限"
    width="1000"
    center
    destroy-on-close
    @open="dialogOpen"
  >
    <div>
      <div>角色名称：jiaberr</div>
      <div>
        <text>菜单权限：</text>
        <el-checkbox v-model="expandAll" @change="handleExpandAllChange">
          展开/折叠
        </el-checkbox>
        <el-checkbox v-model="checkAll" @change="handleCheckAllChange">
          全选/全不选
        </el-checkbox>
        <el-checkbox v-model="checkStrictly"> 父子联动 </el-checkbox>
      </div>
      <div class="w-p-100 containner">
        <el-tree
          ref="treeRef"
          :props="treeProps"
          :data="menudatas"
          show-checkbox
          node-key="id"
          :check-strictly="!checkStrictly"
          :default-expanded-keys="expandedKeys"
          :default-checked-keys="checkedData"
        />
      </div>
    </div>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="close">取消</el-button>
        <el-button type="primary" @click="handleSubmit"> 确定 </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, provide, watchEffect, onMounted } from "vue";
import { roleMenusPermissionData } from "./simulatedData.js";
import { getDynamicRoutes } from "@/utils/getData.js";

const props = defineProps({
  roleId: {
    Type: Number,
    required: true,
  },
});
const treeProps = {
  label: (data) => data.meta.title,
};
// 获取菜单树结构
const menudatas = ref([]);

const checkAll = ref(false); // 全选
const expandAll = ref(true); // 展开
const checkStrictly = ref(true); // 父子联动
watchEffect(() => {
  // 仅模拟页面效果使用，根据不同id获取不同的菜单权限
  if (props.roleId == 1) {
  } else {
  }
});

const emits = defineEmits(["close"]);
const close = () => {
  emits("close");
};
const checkedData = ref(roleMenusPermissionData);
const handleCheckAllChange = (val) => {
  const permissionIds = extractPermissionIds(menudatas.value);
  if (val) {
    treeRef.value.setCheckedKeys(permissionIds);
  } else {
    treeRef.value.setCheckedKeys([]);
  }
};

const expandedKeys = ref([]);
const treeRef = ref(null);
//展开、折叠
const handleExpandAllChange = (value) => {
  expandAll.value = value;
  let nodes = treeRef.value.store._getAllNodes();
  if (expandAll.value) {
    nodes.forEach((item) => {
      item.expanded = expandedKeys.value;
    });
  } else {
    nodes.forEach((item) => {
      item.expanded = !expandedKeys.value;
    });
  }
};

// 父子联动
const handleCheckStrictlyChange = (val) => {
  // 因为后端返回的id包括了父节点的id,父子联动时如果有父节点则默认会将所有子节点勾选，为了避免这种情况，所有需要把后台返回的父节点id筛出去
  if (val) {
    treeRef.value.setCheckedKeys(extractChildPermissionIds(menudatas.value));
  }
};
const handleCheckData = (id) => {
  let index = checkedData.value.findIndex((val) => val == id);
  if (index != -1) {
    checkedData.value.splice(index, 1);
  } else {
    checkedData.value.push(id);
  }
};
onMounted(() => {
  getDynamicRoutes().then((res) => {
    menudatas.value = res;
  });
});

const dialogOpen = () => {
  handleExpandAllChange(expandAll.value);
  handleCheckStrictlyChange(checkStrictly.value);
};

provide("handleEvent", handleCheckData);

// 提交
const handleSubmit = () => {
  // 将选中的id数组上传至后台接口,根据后端的要求，如果需要穿父节点，那checkedKeys和halfCheckedKeys都需要上传
  const checkedKeys = treeRef.value.getCheckedKeys()
  const halfCheckedKeys = treeRef.value.getHalfCheckedKeys()

  console.log(
    treeRef.value.getCheckedKeys(),
    treeRef.value.getHalfCheckedKeys()
  );

  emits("close");
};

// 收集所有功能权限的id
const extractPermissionIds = (data) => {
  const ids = [];

  // 递归遍历函数
  function traverse(node) {
    if (node) {
      ids.push(node.id);
    }

    // 如果有子节点，继续递归遍历
    if (node.children && Array.isArray(node.children)) {
      node.children.forEach((child) => traverse(child));
    }
  }

  // 遍历根节点
  data.forEach((item) => traverse(item));

  return ids;
};

// 保留所有子菜单的id
const extractChildPermissionIds = (data) => {
  const ids = [];

  // 递归遍历函数
  function traverse(node) {
    // 如果有子节点，继续递归遍历
    if (node.children && Array.isArray(node.children)) {
      node.children.forEach((child) => traverse(child));
    } else {
      if (checkedData.value.includes(node.id)) {
        ids.push(node.id);
      }
    }
  }

  // 遍历根节点
  data.forEach((item) => traverse(item));

  return ids;
};
</script>

<style lang="scss" scoped>
.containner {
  border: 1px solid #d3d3d3;
}
</style>