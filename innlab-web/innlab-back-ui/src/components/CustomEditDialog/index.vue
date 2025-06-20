<template>
    <el-dialog  :title="title" :width="width" center destroy-on-close>
      <CustomForm :formConfig="editFormConfig" :rules="rules" :data="initialData" @submitForm="submitForm"></CustomForm>
    </el-dialog>
  </template>
  
  <script setup>
  import CustomForm from "@/components/CustomForm/index.vue";
  import {editFormConfig} from '../../views/TableTemplate/tableConfig.js'
import { ref, watch } from "vue";
  
  const prop = defineProps({
    title: {
        Type: String,
        default: '编辑'
    },
    width: {
      Type: Number,
      default: 600
    },
    rules: {
      Type: Object,
      default: () => {}
    },
    initialData: {
      Type: Object,
      default: () => {}
    }
  })
 
  const emit = defineEmits(['submitForm'])
  const submitForm = (data) => {
    emit('submitForm', data)
  }
  </script>
  
  <style scoped>
  </style>