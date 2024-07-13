

<template>
  <AppPage show-footer>
    <n-card>
      <n-button type="primary" ghost class="absolute right-10 top-5" @click="goToHomePage">返回首页</n-button>
      <n-space align="center">
        <n-avatar round :size="100" :src="userStore.avatar" />
        <div class="ml-20">
          <div class="flex items-center text-16">
            <span>用户名:</span>
            <span class="ml-12 opacity-80">{{ userStore.username }}</span>
            <n-button class="ml-32" type="primary" text @click="pwdModalRef.open()">
              <i class="i-fe:edit mr-4" />
              修改密码
            </n-button>
          </div>
          <div class="mt-16 flex items-center">
            <n-button type="primary" ghost @click="avatarModalRef.open()">更改头像</n-button>
            <span class="ml-12 opacity-60">
              请选择合适的头像上传哦
            </span>
          </div>
        </div>
      </n-space>

    </n-card>
   
    <n-card class="mt-20" title="个人资料信息">
      <template #header-extra>
        <n-button type="primary" text @click="profileModalRef.open()">
          <i class="i-fe:edit mr-4" />
          修改资料
        </n-button>
      </template>

      <n-descriptions
        label-placement="left"
        :label-style="{ width: '200px', textAlign: 'center' }"
        :column="1"
        bordered
      >
        <n-descriptions-item label="昵称">{{ userStore.nickName }}</n-descriptions-item>
        <n-descriptions-item label="性别">
          {{ genders.find((item) => item.value === userStore.userInfo?.gender)?.label ?? '未知' }}
        </n-descriptions-item>
        <n-descriptions-item label="电话">{{ userStore.userInfo?.address }}</n-descriptions-item>
        <n-descriptions-item label="邮箱">{{ userStore.userInfo?.email || '暂无' }}</n-descriptions-item>
        <n-descriptions-item label="角色">{{ userStore.userInfo?.roles }}</n-descriptions-item>
      </n-descriptions>
    </n-card>

    <MeModal ref="avatarModalRef" width="600px" title="更改头像" @ok="handleAvatarSave()">
      <n-input v-model:value="newAvatar" />
      <CommonPage>
    <n-upload
      class="mx-auto w-[75%] p-20 text-center"
      :custom-request="handleUpload"
      :show-file-list="false"
      accept=".png,.jpg,.jpeg"
      @before-upload="onBeforeUpload"
    >
      <n-upload-dragger>
        <div class="h-150 f-c-c flex-col">
          <i class="i-mdi:upload mb-12 text-68 color-primary" />
          <n-text class="text-14 color-gray">点击或者拖动文件到该区域来上传</n-text>
        </div>
      </n-upload-dragger>
    </n-upload>

    <n-card v-if="imgList && imgList.length" class="mt-16 items-center">
      <n-image-group>
        <n-space justify="space-between" align="center">
          <n-card v-for="(item, index) in imgList" :key="index" class="w-280 hover:card-shadow">
            <div class="h-160 f-c-c">
              <n-image width="200" :src="item.url" />
            </div>
            <n-space class="mt-16" justify="space-evenly">
              <n-button dashed type="primary" @click="copy(item.url)">url</n-button>
              <n-button dashed type="primary" @click="copy(`![${item.fileName}](${item.url})`)">
                MD
              </n-button>
              <n-button
                dashed
                type="primary"
                @click="copy(`&lt;img src=&quot;${item.url}&quot; /&gt;`)"
              >
                img
              </n-button>
            </n-space>
          </n-card>
          <div v-for="i in 4" :key="i" class="w-280" />
        </n-space>
      </n-image-group>
    </n-card>
  </CommonPage>
    </MeModal>

    <MeModal ref="pwdModalRef" title="修改密码" width="420px" @ok="handlePwdSave()">
      <n-form
        ref="pwdFormRef"
        :model="pwdForm"
        label-placement="left"
        require-mark-placement="left"
      >
        <n-form-item label="原密码" path="oldPassword" :rule="required">
          <n-input v-model:value="pwdForm.oldPassword" type="password" placeholder="请输入原密码" />
        </n-form-item>
        <n-form-item label="新密码" path="newPassword" :rule="required">
          <n-input v-model:value="pwdForm.newPassword" type="password" placeholder="请输入新密码" />
        </n-form-item>
      </n-form>
    </MeModal>

    <MeModal ref="profileModalRef" title="修改资料" width="420px" @ok="handleProfileSave()">
      <n-form ref="profileFormRef" :model="profileForm" label-placement="left">
        <n-form-item label="昵称" path="nickName">
          <n-input v-model:value="profileForm.nickName" placeholder="请输入昵称" />
        </n-form-item>
        <n-form-item label="性别" path="gender">
          <n-select
            v-model:value="profileForm.gender"
            :options="genders"
            placeholder="请选择性别"
          />
        </n-form-item>
        <n-form-item label="电话" path="address">
          <n-input v-model:value="profileForm.address" placeholder="请输入地址" />
        </n-form-item>
        <n-form-item label="邮箱" path="email">
          <n-input v-model:value="profileForm.email" placeholder="请输入邮箱" />
        </n-form-item>

      </n-form>
    </MeModal>
  </AppPage>
</template>

<script setup>
import { MeModal } from '@/components'
import { useForm, useModal } from '@/composables'
import { useUserStore } from '@/store'
import { getUserInfo } from '@/store/helper'
import api from './api'
const router = useRouter()
const userStore = useUserStore()
const required = {
  required: true,
  message: '此为必填项',
  trigger: ['blur', 'change'],
}

const [pwdModalRef] = useModal()
const [pwdFormRef, pwdForm, pwdValidation] = useForm()

async function handlePwdSave() {
  await pwdValidation()
  await api.changePassword(pwdForm.value)
  $message.success('密码修改成功')
  refreshUserInfo()
}
function goToHomePage() {
      router.push('/'); // 使用路由实例的 push 方法进行页面跳转
}
const newAvatar = ref(userStore.avatar)
const [avatarModalRef] = useModal()
async function handleAvatarSave() {
  if (!newAvatar.value) {
    $message.error('请输入头像地址')
    return false
  }
  await api.updateProfile({ id: userStore.userId, avatar: newAvatar.value })
  $message.success('头像修改成功')
  refreshUserInfo()
}

const genders = [
  { label: '保密', value: 0 },
  { label: '男', value: 1 },
  { label: '女', value: 2 },
]
const [profileModalRef] = useModal()
const [profileFormRef, profileForm, profileValidation] = useForm({
  id: userStore.userId,
  nickName: userStore.nickName,
  gender: userStore.userInfo?.gender ?? 0,
  address: userStore.userInfo?.address,
  email: userStore.userInfo?.email,
})
async function handleProfileSave() {
  await profileValidation()
  await api.updateProfile(profileForm.value)
  $message.success('资料修改成功')
  refreshUserInfo()
}

async function refreshUserInfo() {
  const user = await getUserInfo()
  userStore.setUser(user)
  userStore.setRole(user)
}
</script>
