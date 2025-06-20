<template>
     <el-input
        :style="style"
         v-model="localValue"
        :placeholder="placeholder"
        clearable
      ></el-input>
</template>

<script setup>
import { ref } from "vue";
const prop = defineProps({
    style: {
        Type: Object,
        default: () => {}
    },
    value: {
        Type: String,
        default: ''
    },
    placeholder: {
        Type: String,
        default: ''
    }
})


const localValue = ref(prop.value)


</script>

<style scoped>

</style>