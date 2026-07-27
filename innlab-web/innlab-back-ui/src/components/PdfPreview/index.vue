<template>
  <div class="pdf-preview">
    <!-- 用相对定位的容器包裹PDF内容 -->
    <div class="pdf-content">
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

    <!-- 分页工具栏放在PDF下方 -->
    <div class="page-tool">
      <div class="page-tool-item" @click="lastPage">上一页</div>
      <div class="page-tool-item">{{ state.pageNum }}/{{ state.numPages }}</div>
      <div class="page-tool-item" @click="nextPage">下一页</div>
    </div>
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
  source: props.pdfUrl,
  pageNum: 1,
  scale: 1,
  numPages: 0,
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

onMounted(() => {
  const loadingTask = createLoadingTask(state.source);
  loadingTask.promise.then((pdf: { numPages: number }) => {
    state.numPages = pdf.numPages;
  });
});
</script>

<style scoped>
.pdf-preview {
  position: relative;
  height: auto;
  aspect-ratio: 1/1.41;
  padding: 20px 0;
  width: 100%;
  box-sizing: border-box;
  background-color: #e9e9e9;
  display: flex;
  flex-direction: column;  /* 纵向排列子元素 */
  align-items: center;     /* 子元素水平居中 */
}

.pdf-content {
  position: relative;
  width: 100%;
  height: 100%;
  flex: 1; /* 占据剩余空间 */
}

.vue-pdf-embed {
  text-align: center;
  width: 100%;
  border: 1px solid #e5e5e5;
  margin: 0 auto;
  box-sizing: border-box;
}

/* 分页工具栏放在PDF底部，相对定位 */
.page-tool {
  width: fit-content;
  margin-top: 10px;  /* 与PDF区域保持间距 */
  display: flex;
  align-items: center;
  background: rgb(66, 66, 66);
  color: white;
  border-radius: 19px;
  cursor: pointer;
  z-index: 100;
}

.page-tool-item {
  padding: 8px 15px;
  padding-left: 10px;
  cursor: pointer;
}
</style>
