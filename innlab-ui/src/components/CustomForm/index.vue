<template>
  <el-form
    :label-position="labelPosition"
    label-width="auto"
    :model="formData"
    :style="style"
    :rules="rules"
    ref="formRef"
  >
    <el-form-item
      v-for="(item, index) in formConfig"
      :key="index"
      :prop="item.id"
      :label="item.label"
      :label-position="item.itemLabelPosition"
    >
      <MyInput
        v-if="item.type == 'input'"
        :value="data?.[item.id]"
        @change="(e) => changeValue(e, item.id)"
      ></MyInput>
      <MySelect
        v-if="item.type == 'select'"
        :value="data?.[item.id]"
        :placeholder="item.placeholder"
        :options="item.options"
        :label-key="item.label"
        :value-key="item.value"
        @change="(e) => changeValue(e, item.id)"
      ></MySelect>
    </el-form-item>
    <div class="flex-center">
      <el-button type="primary" @click="submitForm">
        {{ submitBtn }}
      </el-button>
      <el-button @click="cancelForm">{{ cancelBtn }}</el-button>
    </div>
  </el-form>
</template>

  <script setup>
import { reactive, ref} from "vue";
import MyInput from "../MyInput/index.vue";
import MySelect from "../MySelect/index.vue";

const prop = defineProps({
  labelPosition: {
    Type: String,
    default: "left",
  },
  style: {
    Type: Object,
    default: () => {
      return {};
    },
  },
  data: {
    Type: Object,
    default: () => {},
  },
  formConfig: {
    Type: Array,
    required: true,
  },
  rules: {
    Type: Object,
    default: () => {},
  },
  submitBtn: {
    Type: String,
    default: "确定",
  },
  cancelBtn: {
    Type: String,
    default: "取消",
  },
});
const formData = reactive(prop.data || {});
const changeValue = (value, id) => {
  formData[id] = value;
};
const emit = defineEmits(["submitForm", "cancelForm"]);
// 确定
const formRef = ref(null);
const submitForm = () => {
  formRef.value.validate(async (valid, fields) => {
    if (valid) {
      emit("submitForm", formData);
    }else {
      console.log('表单验证不通过')
    }
  })

};

// 取消
const cancelForm = () => {
  emit("cancelForm");
};
</script>
