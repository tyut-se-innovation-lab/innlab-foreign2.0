<template>
    <el-select
      v-model="localValue"
      :placeholder="placeholder"
      clearable
    >
      <el-option
        v-for="(option, idx) in options"
        :key="option.id || idx"
        :label="option[labelKey] || option.label"
        :value="option[valueKey] || option.id"
      ></el-option>
    </el-select>
</template>

<script setup>
import { ref } from "vue"

const prop = defineProps({
      value: {
        type: [String, Number],
        default: ''
      },
      placeholder: {
        type: String,
        default: ''
      },
      options: {
        type: Array,
        default: () => []
      },
      labelKey: {
        type: String,
        default: 'label'
      },
      valueKey: {
        type: String,
        default: 'value'
      }
    })

    const localValue = ref(prop.value)

</script>

<style scoped>

</style>