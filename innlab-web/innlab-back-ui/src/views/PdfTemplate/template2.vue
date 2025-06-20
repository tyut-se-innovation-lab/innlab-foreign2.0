<template>
  <div class="pdf-container">
    <PDFView :pdfUrl="jsPdf" />
  </div>
</template>
    <script setup>
import PDFView from "@/components/PdfPreview/index.vue";
import jsPdf from "/pdf/test.pdf"; // 本地地址
// const jsPdf = 'https://c1026.dmpdmp.com/d37c54153a3aa8950f928d50b2efb1c2/684fe38d/2024/12/22/5e51dc587f898871e0c5b50e8c70ae1b.pdf?fn=%E3%80%8AC%E8%AF%AD%E8%A8%80%E3%80%8B%E8%AE%B2%E4%B9%89%E7%AC%94%E8%AE%B0%E3%80%90%E9%AB%98%E6%96%AF%E8%AF%BE%E5%A0%82%E3%80%91.pdf' // 线上地址也可，但需要注意跨域问题
</script>
    <style scoped>
.pdf-container {
  overflow: auto;
}
</style>
