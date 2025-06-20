<template>
  <div class="pdf-preview">
    <div id="page-view" :style="{
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: `translate(-50%,-50%) scale(${state.scale})`,
        width: '100%',
        height: `${pageHeight}`,
    }">
        <vue-pdf-embed :source="state.source" :page="state.pageNum" textLayer/>
    </div>
  </div>
  <div class="page-tool">
    <div class="page-tool-item" @click="lastPage">上一页</div>
    <div class="page-tool-item" @click="nextPage">下一页</div>
    <div class="page-tool-item">{{ state.pageNum }}/{{ state.numPages }}</div>
    <div class="page-tool-item" @click="pageZoomOut">放大</div>
    <div class="page-tool-item" @click="pageZoomIn">缩小</div>
  </div>
</template>
<script setup lang="ts">
import { reactive, onMounted, ref } from "vue";
import VuePdfEmbed from "vue-pdf-embed";
import { createLoadingTask } from "vue3-pdfjs";

const props = defineProps({
  pdfUrl: {
    type: String,
    required: true,
  },
});

const pageHeight = ref('100%');
const state = reactive({
  source: props.pdfUrl, //预览pdf文件地址
  pageNum: 1, //当前页面
  scale: 1, // 缩放比例
  numPages: 0, // 总页数
});

function lastPage() {
  if (state.pageNum > 1) {
    state.pageNum -= 1;
  }
}
function nextPage() {
  if (state.pageNum < state.numPages) {
    state.pageNum += 1;
  }
}
function pageZoomOut() {
  if (state.scale < 2) {
    state.scale += 0.1;
    pageHeight.value = (parseInt(pageHeight.value) - 5.0) + '%';
  }
}
function pageZoomIn() {
  if (state.scale > 1) {
    state.scale -= 0.1;
    pageHeight.value = (parseInt(pageHeight.value) + 5.0) + '%';
  }
}

onMounted(() => {
  const loadingTask = createLoadingTask(state.source);
  loadingTask.promise.then((pdf: { numPages: number }) => {
    state.numPages = pdf.numPages;
  });
});
</script>
<style lang="css" scoped>
.pdf-preview {
  position: relative;
  height: 100vh;
  padding: 20px 0;
  box-sizing: border-box;
  background-color: e9e9e9;
}
.pdf-wrap {
  overflow-y: auto;
}
.vue-pdf-embed {
  text-align: center;
  width: 515px;
  border: 1px solid #e5e5e5;
  margin: 0 auto;
  box-sizing: border-box;
}

.page-tool {
  position: absolute;
  bottom: 35px;
  padding-left: 15px;
  padding-right: 15px;
  display: flex;
  align-items: center;
  background: rgb(66, 66, 66);
  color: white;
  border-radius: 19px;
  z-index: 100;
  cursor: pointer;
  margin-left: 50%;
  transform: translateX(-50%);
}
.page-tool-item {
  padding: 8px 15px;
  padding-left: 10px;
  cursor: pointer;
}
</style>
