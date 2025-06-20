<template>
    <div class="p-20">
      <CustomForm :formConfig="formConfig" labelPosition="right" :rules="rules" @submitForm="submitForm"></CustomForm>
    </div>
  </template>
  
  <script setup>
  import CustomForm from "@/components/CustomForm/index.vue";
  import {formConfig} from './formConfig.js'
  
  const rules = {
      name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
      pushWay: [{ required: true, message: '请选择推送方式', trigger: 'blur' }]
  }
  
  const submitForm = (data) => {
   console.log(data);
  }
  </script>
  
  <style scoped>
  </style>