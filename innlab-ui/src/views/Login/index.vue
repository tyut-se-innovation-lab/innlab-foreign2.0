<template>
  <div class="login-container flex">
    <img src="../../../public/img/login-left.png" class="login-left" />
    <div class="login-right flex flex-col">
      <div class="fz-20 text-center text-bold">欢迎登录创新实验室平台</div>
      <div class="text-center mb-20">WELCOME</div>
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
.login-container {
  width: 100vw;
  height: 100vh;
  background: url("../../../public/img/login.png") no-repeat;
  background-size: cover;
}
.login-left {
  width: 58%;
  height: 100%;
}
.login-right {
  width: 25%;
  height: 56%;
  background-color: #fff;
  margin: auto;
  border-radius: 10px;
  box-shadow: #b3d2fb 1px 1px 15px 1px;
  padding: 40px 30px;
}
.login-input {
    height:50px;
  border: 1px solid #b3d2fb;
  border-radius: 5px;
  background: rgba(231, 241, 253, 0.4);
}
.login-input-right {
  width: 80%;
  height: 60%;
  border-left: 1px solid #ccc;
}
.login-remember {
    font-size: 12px;
}
.login-btn {
    height: 40px;
}
</style>
<style>
.login-input-right .el-input__wrapper {
  border: none !important;
  box-shadow: none !important;
  background: transparent;
}
.login-remember .el-checkbox__label {
    font-size: 12px;
    color:#aaa;
}
</style>
<style scoped>
/* 添加注册链接的样式 */
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
