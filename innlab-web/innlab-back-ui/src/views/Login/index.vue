<template>
  <div class="login-container">
    <!-- 移动端隐藏左侧图片 -->
    <img src="../../../public/img/login-left.png" class="login-left" />

    <div class="login-right flex flex-col">
      <!-- 添加响应式标题 -->
      <div class="login-title">
        <div class="fz-20 text-center text-bold">欢迎登录创新实验室平台</div>
        <div class="text-center mb-20">WELCOME</div>
      </div>

      <el-form :model="form" :rules="rules" ref="ruleForm" @submit.prevent="onSubmit">
        <el-form-item class="login-input flex" prop="name">
          <el-icon class="flex1"><User /></el-icon>
          <el-input
            class="login-input-right"
            v-model="form.name"
            placeholder="请输入账号"
          />
        </el-form-item>
        <el-form-item class="login-input flex" prop="password">
          <el-icon class="flex1"><Lock /></el-icon>
          <el-input
            class="login-input-right"
            type="password"
            v-model="form.password"
            placeholder="请输入密码"
          />
        </el-form-item>
        <el-form-item>
          <el-checkbox class="login-remember mb-10" v-model="remember" label="记住密码" />
        </el-form-item>
        <el-form-item>
          <el-button class="login-btn w-p-100" type="primary" native-type="submit" :loading="loading">立即登录</el-button>
        </el-form-item>
        <!-- 添加的注册按钮 -->
        <el-form-item class="register-link">
          <span class="register-text">还没有账号？</span>
          <el-link type="primary" @click="goToRegister">立即注册</el-link>
        </el-form-item>
      </el-form>

    </div>
  </div>
</template>

<script setup>
import router from "@/router";
import { ref, reactive } from "vue";
import {getDynamicRoutes} from '@/utils/getData.js'
import {getAuthRouters} from '@/router/authRouter.js'
import { useAuthRouterStore } from '@/stores/authRouter.js'
const authRouterStore = useAuthRouterStore()
const ruleForm = ref(null)
import { ElMessage } from 'element-plus'
import { ElNotification } from 'element-plus'
import { CloseBold } from '@element-plus/icons-vue'
import {loginApi} from "@/api/userApi.js";
const form = reactive({
  name: "",
  password: "",
});

const remember = ref(false)

const rules = {
  name: [
    { required: true, message: '请输入账号', trigger: 'blur'}
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur'}
  ],
}
const loading = ref(false);  //状态防止表单重复登录


// 格式化 data 信息（过滤 token 并美化输出）
const formatLoginInfo = (data) => {
  const { lastLoginTime, lastLoginIp, lastLoginLocation } = data;
  return `
    <div style="line-height: 1.6">
      <b>上次登录时间:</b> ${new Date(lastLoginTime).toLocaleString()} <br/>
      <b>登录 IP:</b> ${lastLoginIp} <br/>
      <b>登录地点:</b> ${lastLoginLocation || "未知"}
    </div>
  `;
};

// 跳转到注册页面的方法
const goToRegister = () => {
  router.push('/register');
};
const onSubmit = () => {
  ruleForm.value.validate(async(valid) => {
    if (!valid) return; // 验证不通过直接返回
    loading.value = true;
    try {
      // 1. 使用await等待登录结果
      const res = await loginApi({
        loginAccount: form.name,
        loginPassword: form.password
      });

      console.log('登录成功', res);

      // 2. 检查响应状态
      if (res.code !== 200) {
        throw new Error(`登录失败: ${res.message || '未知错误'}`);
      }

      console.log(res.data.token);
      // 3. 存储token到sessionStorage
      sessionStorage.token = res.data.token ; // 使用实际token字段

      // 4. 获取动态路由
      const dynamicRoutes = await getDynamicRoutes();
      const newRoutes = getAuthRouters(dynamicRoutes);

      // 5. 添加路由
      authRouterStore.addRouterList(newRoutes);
      newRoutes.forEach(route => {
        router.addRoute(route);
      });

      ElMessage.success("登陆成功！");
// 调用通知组件
      ElNotification({
        title: '登录成功',
        message: formatLoginInfo(res.data), // 使用格式化后的 data 信息
        dangerouslyUseHTMLString: true,    // 启用 HTML 解析（需确保内容安全）
        type: 'success',
        position: 'bottom-right',
        closeIcon: CloseBold,
        duration: 5000  // 自动关闭延时（毫秒）
      });
      // 6. 跳转到首页
      await router.push('/dashboard');

    } catch (err) {
      console.error('登录失败', err);
      // 可以在这里添加错误提示
      ElMessage.error(err.message || '登录失败，请重试');
    } finally {
      // 7. 确保无论成功失败都关闭loading
      loading.value = false;
    }

  })

}
</script>

<style scoped>
/* 基础样式 */
.login-container {
  width: 100vw;
  min-height: 100vh;
  background: url("../../../public/img/login.png") no-repeat center center;
  background-size: cover;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
}

/* 左侧图片 - 桌面端显示，移动端隐藏 */
.login-left {
  display: none; /* 移动端默认隐藏 */
}

/* 右侧登录框 - 基础样式 */
.login-right {
  width: 100%;
  max-width: 420px;
  min-height: 500px;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: #b3d2fb 1px 1px 15px 1px;
  padding: 40px 30px;
  margin: 0 auto;
}

/* 登录输入框 */
.login-input {
  height: 50px;
  border: 1px solid #b3d2fb;
  border-radius: 5px;
  background: rgba(231, 241, 253, 0.4);
}

.login-input-right {
  width: 80%;
  height: 60%;
  border-left: 1px solid #ccc;
}

.login-btn {
  height: 40px;
}

/* 移动端适配 */
@media screen and (max-width: 768px) {
  .login-container {
    padding: 10px;
    background: #f5f7fa; /* 移动端使用纯色背景 */
  }

  .login-right {
    min-height: auto;
    padding: 30px 20px;
    max-width: 100%;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  }

  .login-title {
    margin-bottom: 20px;
  }

  .login-title .fz-20 {
    font-size: 18px !important;
  }

  .login-title .mb-20 {
    margin-bottom: 15px;
    font-size: 14px;
  }

  .login-input {
    height: 44px;
  }

  .login-btn {
    height: 44px;
    font-size: 16px;
  }

  .login-remember {
    font-size: 12px;
  }

  .register-text {
    font-size: 13px;
  }

  /* 调整表单间距 */
  .el-form-item {
    margin-bottom: 16px;
  }
}

/* 小屏幕手机适配 */
@media screen and (max-width: 375px) {
  .login-right {
    padding: 25px 15px;
  }

  .login-title .fz-20 {
    font-size: 16px !important;
  }

  .login-input {
    height: 40px;
  }

  .login-btn {
    height: 40px;
  }

  /* 调整图标大小 */
  .el-icon {
    font-size: 18px;
  }
}

/* 平板设备适配 */
@media screen and (min-width: 769px) and (max-width: 1024px) {
  .login-left {
    display: block;
    width: 45%;
    height: auto;
  }

  .login-right {
    width: 35%;
    max-width: 380px;
  }
}

/* 桌面端适配 */
@media screen and (min-width: 1025px) {
  .login-container {
    justify-content: space-between;
    padding: 0;
  }

  .login-left {
    display: block;
    width: 58%;
    height: 100vh;
    object-fit: cover;
  }

  .login-right {
    width: 25%;
    height: 56%;
    margin: auto 10% auto auto;
  }
}

/* 大屏幕桌面适配 */
@media screen and (min-width: 1440px) {
  .login-right {
    width: 420px;
    padding: 50px 40px;
  }
}
</style>

<style>
/* 全局样式调整 */
.login-input-right .el-input__wrapper {
  border: none !important;
  box-shadow: none !important;
  background: transparent;
}

.login-remember .el-checkbox__label {
  font-size: 12px;
  color: #aaa;
}

/* 移动端调整Element Plus组件 */
@media screen and (max-width: 768px) {
  .el-input__inner {
    font-size: 14px;
  }

  .el-button {
    font-size: 14px;
  }

  .el-checkbox__label {
    font-size: 12px;
  }

  .el-link {
    font-size: 13px;
  }
}
</style>

<style scoped>
/* 注册链接样式 */
.register-link {
  display: flex;
  justify-content: center;
  margin-top: 10px;
}

.register-text {
  margin-right: 8px;
  color: #606266;
  font-size: 14px;
}
</style>

