<template>
  <div class="not-found-container">
    <h2 class="status-code">404</h2>
    <p class="describe">抱歉！您访问的页面<em>走丢</em>啦...</p>
    <p class="timeout">
      <!-- <em>{{ endTime }}</em>秒后自动返回首页 -->
    </p>
    <!-- <el-button @click="jump(0)">返回</el-button>
    <el-button type="primary" @click="jump(1)">首页</el-button> -->
  </div>
</template>

<script setup>
import { ref, useRouter, onMounted } from 'vue';

// const router = useRouter()

// const endTime = ref(10)
// const timer = ref(-1)

// watch(endTime, (newVal, _oldVal) => {
//   if (newVal === 0) {
//     clearTimeout(timer.value)
//     router.push({ name: 'login', replace: true })
//   }
// })

onMounted(() => {
  //加载动画
  setTimeout(() => {
    bus.emit('loading', false);
  }, 800);
  // timer.value = window.setInterval(() => {
  //   endTime.value--
  // }, 1000)
})

// const jump = type => {
//   switch (type) {
//     case 0:
//       router.back(-1)
//       break
//     case 1:
//       router.push({ path: '/', name: 'home', replace: true })
//       break
//   }
//   clearTimeout(timer.value)
// }
</script>

<style scoped>
.not-found-container {
  position: absolute;
  top: 20%;
  left: 50%;
  transform: translate(-50%, 0);
  text-align: center;
  font-style: normal;

  .status-code {
    margin: 20px 0;
    font-size: 10em;
  }

  .describe {
    margin-bottom: 30px;
    font-size: 26px;
  }

  .timeout {
    margin: 0 10px;
    margin-bottom: 30px;
    font-size: 18px;
  }
}
</style>