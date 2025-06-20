<template>
  <div class="register-container flex">
    <img src="../../../public/img/login-left.png" class="register-left" />
    <div class="register-right flex flex-col">
      <div class="fz-20 text-center text-bold">欢迎注册创新实验室官网平台</div>
      <div class="text-center mb-20">WELCOME</div>
      <el-form :model="form" :rules="rules" ref="ruleForm" @submit.prevent="onSubmit">
        <!-- 邀请码 -->
        <el-form-item class="register-input flex" prop="invitationCode">
          <el-icon class="flex1"><Key /></el-icon>
          <el-input
            class="register-input-right"
            v-model="form.invitationCode"
            placeholder="请输入邀请码"
          />
        </el-form-item>

        <!-- 账号 -->
        <el-form-item class="register-input flex" prop="userAccount">
          <el-icon class="flex1"><User /></el-icon>
          <el-input
            class="register-input-right"
            v-model="form.userAccount"
            placeholder="请输入账号"
          />
        </el-form-item>

        <!-- 密码 -->
        <el-form-item class="register-input flex" prop="userPassword">
          <el-icon class="flex1"><Lock /></el-icon>
          <el-input
            class="register-input-right"
            type="password"
            v-model="form.userPassword"
            placeholder="请输入密码"
          />
        </el-form-item>

        <!-- 昵称 -->
        <el-form-item class="register-input flex" prop="userNickname">
          <el-icon class="flex1"><Avatar /></el-icon>
          <el-input
            class="register-input-right"
            v-model="form.userNickname"
            placeholder="请输入昵称"
          />
        </el-form-item>

        <!-- 邮箱 -->
        <el-form-item class="register-input flex" prop="userEmail">
          <el-icon class="flex1"><Message /></el-icon>
          <el-input
            class="register-input-right"
            v-model="form.userEmail"
            placeholder="请输入邮箱"
          />
        </el-form-item>

        <!-- 验证码 -->
        <el-form-item class="register-input flex" prop="verificationCode">
          <el-icon class="flex1"><CircleCheck /></el-icon>
          <el-input
            class="register-input-right"
            v-model="form.verificationCode"
            placeholder="请输入验证码"
          >
            <template #append>
              <el-button
                @click="getVerifyCode"
                :disabled="countdown > 0"
                class="register-verify"
              >
                {{ countdown > 0 ? `${countdown}秒后重试` : '获取验证码' }}
              </el-button>
            </template>
          </el-input>
        </el-form-item>

        <el-form-item>
          <el-button
            class="register-btn w-p-100"
            type="primary"
            native-type="submit"
            :loading="loading"
          >
            立即注册
          </el-button>
        </el-form-item>

        <!-- 添加的登录按钮 -->
        <el-form-item class="login-link">
          <span class="login-text"> 已有账号？</span>
          <el-link type="primary" @click="goToLogin">返回登录</el-link>
        </el-form-item>
        </el-form>
    </div>
  </div>
</template>

<script setup>
import router from "@/router";
import { ref, reactive } from "vue";
import { Key, User, Lock, Avatar, Message, CircleCheck } from '@element-plus/icons-vue';
import { ElMessage } from 'element-plus';

// 注册API（需要您根据实际接口实现）
import { registerApi, registerVerifyApi } from "@/api/userApi.js";

const form = reactive({
  invitationCode: "",
  userAccount: "",
  userPassword: "",
  userNickname: "",
  userEmail: "",
  verificationCode: ""
});

// 倒计时状态
const countdown = ref(0);
let countdownTimer = null;

// 表单验证规则
const rules = {
  invitationCode: [{ required: true, message: '请输入邀请码', trigger: 'blur' }],
  userAccount: [{ required: true, message: '请输入账号', trigger: 'blur' }],
  userPassword: [{ required: true, message: '请输入密码', trigger: 'blur' }],
  userNickname: [{ required: true, message: '请输入昵称', trigger: 'blur' }],
  userEmail: [
    { required: true, message: '请输入邮箱', trigger: 'blur' },
    { type: 'email', message: '邮箱格式不正确', trigger: 'blur' }
  ],
  verificationCode: [{ required: true, message: '请输入验证码', trigger: 'blur' }]
};

const loading = ref(false);
const ruleForm = ref(null);
const goToLogin = () => {
  router.push('/login');
};

// 获取验证码
const getVerifyCode = async () => {
  try {
    // 邮箱格式验证
    if (!form.userEmail || !/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(form.userEmail)) {
      ElMessage.warning('请输入有效的邮箱地址');
      return;
    }

    // 调用验证码接口
    const res = await registerVerifyApi({
      invitationCode: form.invitationCode,
      userAccount: form.userAccount,
      userEmail: form.userEmail
    });

    if (res.code === 200) {
      ElMessage.success('验证码已发送');
      // 开始60秒倒计时
      countdown.value = 60;
      countdownTimer = setInterval(() => {
        if (countdown.value > 0) {
          countdown.value--;
        } else {
          clearInterval(countdownTimer);
        }
      }, 1000);
    } else {
      ElMessage.error(res.message || '验证码发送失败');
    }
  } catch (err) {
    console.error('获取验证码失败', err);
    ElMessage.error('验证码发送失败');
  }
};

// 注册提交
const onSubmit = async () => {
  try {
    // 表单验证
    await ruleForm.value.validate();
    loading.value = true;

    // 调用注册API
    const res = await registerApi(form);

    if (res.code === 200) {
      ElMessage.success('注册成功');
      // 注册成功后跳转到登录页
      router.push('/login');
    } else {
      ElMessage.error(res.message || '注册失败');
    }
  } catch (err) {
    console.error('注册失败', err);
  } finally {
    loading.value = false;
  }
};
</script>
<style scoped>
.register-container {
  width: 100vw;
  height: 100vh;
  background: url("../../../public/img/login.png") no-repeat;
  background-size: cover;
}
.register-left {
  width: 58%;
  height: 100%;
}
.register-right {
  width: 25%;
  height: 75%;
  background-color: #fff;
  margin: auto;
  border-radius: 10px;
  box-shadow: #b3d2fb 1px 1px 15px 1px;
  padding: 40px 30px;
}
.register-input {
  height:40px;
  border: 1px solid #b3d2fb;
  border-radius: 5px;
  background: rgba(231, 241, 253, 0.4);
}
.register-input-right {
  width: 80%;
  height: 60%;
  border-left: 1px solid #ccc;
}
.register-remember {
  font-size: 12px;
}
.register-btn {
  height: 40px;
}
register-verify{

  text-align: center;
}
</style>
<style>
.register-input-right .el-input__wrapper {
  border: none !important;
  box-shadow: none !important;
  background: transparent;
}
.register-remember .el-checkbox__label {
  font-size: 12px;
  color:#aaa;
}
</style>
<style scoped>
/* 添加注册链接的样式 */
.login-link {
  display: flex;
  justify-content: center;
  margin-top: 10px;
}

.login-text {
  margin-right: 8px;
  color: #606266;
  font-size: 14px;
}
</style>
