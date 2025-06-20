<template>
    <div>
      <iframe :src="pdfUrl" width="100%" height="600px"></iframe>
    </div>
  </template>

<script setup>
import { ref } from 'vue';
const pdfUrl = ref('http://www.pwithe.com/Public/Upload/download/20170211/589ebf8e5bb13.pdf');

</script>

<style lang="scss" scoped>

</style>