<template>
  <div class="wh-full flex-col bg-[url(@/assets/images/login_bg.png)] bg-cover">
    <div class="m-auto max-w-700 min-w-345 f-c-c rounded-8 bg-opacity-20 bg-cover p-12 card-shadow auto-bg">
      <div class="hidden w-380 px-20 py-35 md:block">
        <img src="@/assets/images/logo.png" class="w-full" alt="login_banner" />
      </div>

      <div class="w-320 flex-col px-20 py-32">
        <h2 class="f-c-c text-24 text-#000000 font-weight">
          {{ title }}
        </h2>
        <n-input v-model:value="loginInfo.username" autofocus class="mt-32 h-40 items-center" placeholder="请输入用户名"
          :maxlength="20">
          <template #prefix>
            <i class="i-fe:user mr-12 opacity-20" />
          </template>
        </n-input>
        <n-input v-model:value="loginInfo.password" class="mt-20 h-40 items-center" type="password"
          show-password-on="mousedown" placeholder="请输入密码" :maxlength="20" @keydown.enter="handleLogin()">

          <template #prefix>
            <i class="i-fe:lock mr-12 opacity-20" />
          </template>
        </n-input>

        <div class="mt-20 flex items-center relative  slider-container" ref="sliderContainer">
          <div ref="slider" :style="{ left: sliderLeft + 'px' }" @mousedown="startDrag" class="slider" :maxlength="20"
            @keydown.enter="handleLogin()">
          </div>
          <div class="slider-text">{{ isVerified ? '验证成功' : '请拖动滑块完成验证' }}</div>

        </div>

        <n-checkbox class="mt-20" :checked="isRemember" label="记住账号密码"
          :on-update:checked="(val) => (isRemember = val)" />

        <div class="mt-20 flex items-center">
          <n-button class="h-40 flex-1 rounded-5 text-16" type="primary" ghost @click="quickLogin()">
            注册
          </n-button>

          <n-button class="ml-32 h-40 flex-1 rounded-5 text-16" type="primary" :loading="loading"
            @click="handleLogin()">
            登录
          </n-button>
        </div>
      </div>
    </div>

    <TheFooter class="py-12" />
  </div>
</template>

<script setup>
import { throttle, lStorage } from '@/utils'
import { useStorage } from '@vueuse/core'
import api from './api'

import { useAuthStore } from '@/store'
import { initUserAndPermissions } from '@/router'
import { watch, watchEffect } from 'vue'

const authStore = useAuthStore()
const router = useRouter()
const route = useRoute()
const title = import.meta.env.VITE_TITLE

const loginInfo = ref({
  username: 'admin1',
  password: 'admin2',
})

const sliderContainer = ref(null)
const slider = ref(null)
const sliderLeft = ref(0)
const startX = ref(0)
const isDragging = ref(false)
const isVerified = ref(false)
//滑块重新验证定时器
let timeid = ref(0)
// 获取滑块容器和滑块元素的宽度
const sliderContainerWidth = computed(() => sliderContainer.value?.offsetWidth || 0)
const sliderWidth = computed(() => slider.value?.offsetWidth || 0)

// 初始化滑块位置
onMounted(() => {
  resetSlider()
})



// 重置滑块位置
function resetSlider() {
  sliderLeft.value = 0
  isVerified.value = false
  if(sliderContainer.value){
    sliderContainer.value.style.backgroundColor = `rgb(240, 240, 240)`
  }
  
}


// 鼠标按下事件处理函数
function startDrag(event) {
  event.preventDefault()
  startX.value = event.pageX - sliderLeft.value
  isDragging.value = true
}

// 鼠标移动事件处理函数
window.addEventListener('mousemove', (event) => {
  if (!isDragging.value) return
  const newLeft = event.pageX - startX.value
  sliderLeft.value = Math.max(0, Math.min(newLeft, sliderContainerWidth.value - sliderWidth.value))
  // 计算滑块相对于滑块容器左侧的位置比例
  const progress = sliderLeft.value / sliderContainerWidth.value;

  // 计算背景色的透明度，使其随着滑块的移动而变化
  const opacity = Math.max(0, Math.min(progress, 1)); // 确保透明度在 0 到 1 之间
  const rgbaColor = `rgba(49, 108, 114, ${opacity})`; // 举例：使用红色作为背景色
  sliderContainer.value.style.backgroundColor = rgbaColor;
})

// 鼠标释放事件处理函数
window.addEventListener('mouseup', () => {
  if (!isDragging.value) return
  isDragging.value = false
  if (sliderLeft.value >= sliderContainerWidth.value - sliderWidth.value) {
    isVerified.value = true
    reLogin()
    if (isVerified.value && route.fullPath === '/login') {
        timeid = setTimeout(() => {
        isVerified.value = false
        resetSlider()
        $message.warning('验证过期，请重新滑动验证')
      }, 15000);
      console.log(isVerified.value);
    }
  } else {
    resetSlider()
  }
})





const localLoginInfo = lStorage.get('loginInfo')
if (localLoginInfo) {
  loginInfo.value.username = localLoginInfo.username || ''
  loginInfo.value.password = localLoginInfo.password || ''
}



//滑块验证
async function reLogin() {
  const { username, password } = loginInfo.value
  if (isVerified.value === true) {
    const { message } = await api.reLogin({ userAccount: username, userPassword: password.toString() });
    await $message.success(message);
  }
}
const isRemember = useStorage('isRemember', true)
const loading = ref(false)
async function handleLogin() {
  const { username, password, } = loginInfo.value
  if (!username || !password) return $message.warning('请输入用户名和密码')
  if (isVerified.value == false) return $message.warning('请滑动进行验证')
  if (isVerified.value == true) {
    await api.reLogin({ userAccount: username, userPassword: password.toString() })
  }
  try {
    loading.value = true
    $message.loading('正在登录，请稍后...', { key: 'login' })
    const { data } = await api.login({ userAccount: username, userPassword: password.toString() })
    if (isRemember.value) {
      lStorage.set('loginInfo', { userAccount: username, userPassword: password })
    } else {
      lStorage.remove('loginInfo')
    }
    onLoginSuccess(data)
  } catch(error) {
    $message.destroy('login')
  }
  loading.value = false
}

async function onLoginSuccess(data = {}) {
  authStore.setToken(data)
  $message.loading('登录中...', { key: 'login' })
  try {
    await initUserAndPermissions()

    $message.success('登录成功', { key: 'login' })
    clearTimeout(timeid)
    if (route.query.redirect) {
      const path = route.query.redirect
      delete route.query.redirect
      router.push({ path, query: route.query })
      //删除十五秒重新验证的定时器
    } else {
      router.push('/')
    }
  } catch (error) {
    console.error(error)
    loading.value = false
    $message.destroy('login')
  }
}
</script>

<style scoped>
.form-slider-captcha {
  max-width: 400px;
  margin: 0 auto;
}

.form-group {
  margin-bottom: 20px;
}

.slider-container {
  position: relative;
  width: 100%;
  height: 40px;
  background-color: rgb(240, 240, 240);
  border-radius: 5px;
}

.slider {
  position: absolute;
  top: 0;
  left: 0;
  width: 50px;
  height: 40px;
  background-color: rgb(49, 108, 114);
  border-radius: 5px;
  cursor: pointer;
}

.slider-text {
  position: absolute;
  top: 50%;
  left: 57%;
  width: 200px;
  transform: translate(-50%, -50%);
  font-size: 14px;
  color: #333;

  animation: blink 2s linear infinite;
  -webkit-animation: blink 2s linear infinite;
  -moz-animation: blink 2s linear infinite;
  -ms-animation: blink 2s linear infinite;
  -o-animation: blink 2s linear infinite;

}

@keyframes blink {
  0% {
    color: rgba(51, 51, 51, 1);
  }

  50% {
    color: rgba(51, 51, 51, 0.5);
  }

  100% {
    color: rgba(51, 51, 51, 1);
  }
}
</style>
