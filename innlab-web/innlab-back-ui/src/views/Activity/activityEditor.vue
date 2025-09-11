<template>
  <div class="activity-editor">
    <!-- 头部信息 -->
    <el-page-header @back="goBack" class="page-header">
      <template #content>
        <!-- 在header-content中添加 -->
        <div class="header-content">
          <h1>{{ activityData.activityTitle || '活动编辑器' }}</h1>
          <div class="sub-header">
            <el-tag type="info">活动ID: {{ activityId }}</el-tag>
            <el-tag>作者: {{ activityData.author || '未知' }}</el-tag>
            <el-tag type="success">最后更新: {{ formatDateTime(activityData.updateTime) }}</el-tag>
            <el-tag :type="isAutoSaving ? 'warning' : 'success'">
              {{ isAutoSaving ? '自动保存中...' : '自动保存' }}
            </el-tag>
            <el-button
              size="small"
              @click="toggleAutoSave"
              :type="autoSaveEnabled ? 'danger' : 'success'"
              :icon="autoSaveEnabled ? 'Switch' : 'CircleCheck'"
            >
              {{ autoSaveEnabled ? '停止自动保存' : '开启自动保存' }}
            </el-button>
          </div>
        </div>
      </template>
    </el-page-header>

    <!-- 加载状态 -->
    <div v-if="loading" class="loading-overlay">
      <el-icon class="loading-icon"><Loading /></el-icon>
      <span>加载活动数据中...</span>
    </div>

    <!-- 活动内容区域 -->
    <div v-if="activityData" class="activity-content">
      <!-- 修改小标题模块部分 -->
      <div v-for="(subtitle, index) in activityData.activityContent" :key="subtitle.subtitleId"
           class="subtitle-card" :class="`type-${subtitle.subtitleType}`">
        <div class="subtitle-header">
          <div class="header-left">
            <el-input
              v-model="subtitle.subtitleName"
              placeholder="小标题名称"
              size="large"
              class="title-input"
              style="width: 300px"
            />
            <el-tag :type="getTagType(subtitle.subtitleType)" size="large">
              {{ getTypeText(subtitle.subtitleType) }}
            </el-tag>
          </div>
          <div class="header-right">
            <el-button-group>
              <el-button size="small" @click="moveSubtitle(subtitle, 'up')" :disabled="index === 0">
                <el-icon><ArrowUp /></el-icon>上移
              </el-button>
              <el-button size="small" @click="moveSubtitle(subtitle, 'down')"
                         :disabled="index === activityData.activityContent.length - 1">
                <el-icon><ArrowDown /></el-icon>下移
              </el-button>
            </el-button-group>
          </div>
        </div>

        <!-- 文本编辑器 -->
        <div v-if="subtitle.subtitleType === 0" class="editor-container">
          <div :id="`vditor-${subtitle.subtitleId}`" class="vditor-wrapper"></div>
        </div>

        <!-- 在resource-container中添加资源ID显示区域 -->
        <div v-if="subtitle.subtitleType === 1 || subtitle.subtitleType === 2 || subtitle.subtitleType === 3" class="resource-container">
          <!-- 新增资源ID显示区域 -->
          <div class="resource-ids">
            <el-tag v-for="id in subtitle.subtitleContent.split(',')"
                    :key="id"
                    closable
                    @close="removeResourceId(subtitle, id)"
                    size="small"
                    type="success">
              {{ id }}
            </el-tag>
            <el-tag v-if="!subtitle.subtitleContent" size="small" type="info">暂无资源</el-tag>
          </div>

          <!-- 原有的资源网格 -->
          <div class="resource-grid">
            <!-- 图片资源 -->
            <div v-if="subtitle.subtitleType === 1" v-for="(res, resIndex) in subtitle.resource" :key="resIndex" class="resource-card">
              <div class="resource-img">
                <el-image
                  v-if="res.url"
                  :src="res.url"
                  fit="cover"
                  :preview-src-list="[res.url]"
                  :initial-index="0"
                  hide-on-click-modal
                  preview-teleported
                >
                <template #error>
                  <div class="image-error">
                    <el-icon><Picture /></el-icon>
                    <span>图片加载失败</span>
                  </div>
                </template>
                </el-image>
                <div v-else class="image-loading">
                  <el-icon class="loading-icon"><Loading /></el-icon>
                  <span>加载中...</span>
                </div>
              </div>
              <div class="resource-info">
                <div><el-tag>ID: {{ res.fid }}</el-tag></div>
                <div><el-tag type="info">密码: {{ res.pwd }}</el-tag></div>
              </div>
            </div>

            <!-- 视频资源 -->
            <div v-if="subtitle.subtitleType === 2" v-for="(res, resIndex) in subtitle.resource" :key="resIndex" class="resource-card">
              <div class="video-preview" @click="playVideo(res)">
                <el-icon><VideoPlay /></el-icon>
                <span>播放视频</span>
              </div>
              <div class="resource-info">
                <div><el-tag>ID: {{ res.fid }}</el-tag></div>
                <div><el-tag type="info">密码: {{ res.pwd }}</el-tag></div>
              </div>
            </div>

            <!-- 文件资源 -->
            <div v-if="subtitle.subtitleType === 3" v-for="(res, resIndex) in subtitle.resource" :key="resIndex" class="file-item">
              <el-icon class="file-icon"><Document /></el-icon>
              <div class="file-info">
                <div class="file-name">资源文件 {{ resIndex + 1 }}</div>
                <div class="file-meta">ID: {{ res.fid }} | 密码: {{ res.pwd }}</div>
              </div>
              <el-button type="primary" size="small" @click="downloadFile(res)">下载</el-button>
            </div>
          </div>

          <div class="upload-area" @click="openUploadDialog(subtitle, subtitle.subtitleType)">
            <el-icon><Plus /></el-icon>
            <span>添加{{ getTypeText(subtitle.subtitleType) }}</span>
          </div>
        </div>


        <div class="subtitle-footer">
          <el-button type="primary" @click="saveSubtitle(subtitle)" :loading="subtitle.saving">保存</el-button>
          <el-button type="danger" @click="deleteSubtitle(subtitle.subtitleId, index)">删除</el-button>
        </div>
<!--      </div>-->
      </div>

      <!-- 添加新模块 -->
      <div class="add-subtitle">
        <el-select v-model="newSubtitleType" placeholder="选择新模块类型" class="type-select">
          <el-option label="文本内容" :value="0" />
          <el-option label="图片资源" :value="1" />
          <el-option label="视频资源" :value="2" />
          <el-option label="文件下载" :value="3" />
        </el-select>
        <el-button type="primary" @click="addSubtitle" :loading="addingSubtitle">添加模块</el-button>
      </div>
    </div>

    <!-- 上传对话框 -->
    <el-dialog v-model="uploadVisible" :title="`上传${uploadTitle}`" width="500px">
      <el-form label-position="top">
        <el-form-item :label="`选择${uploadTitle}文件`">
          <el-upload
            ref="uploadRef"
            class="upload-demo"
            drag
            :auto-upload="false"
            :accept="uploadAccept"
            :on-change="handleFileChange"
            :show-file-list="false"
          >
            <template #trigger>
              <div v-if="!uploadForm.file" class="upload-area">
                <el-icon class="el-icon--upload"><Plus /></el-icon>
                <div class="el-upload__text">
                  将文件拖到此处，或<em>点击上传</em>
                </div>
              </div>
              <div v-else class="preview-area">
                <!-- 图片预览 -->
                <el-image
                  v-if="uploadType === 1"
                  :src="localPreviewUrl"
                  fit="contain"
                  style="max-height: 200px;"
                  :preview-src-list="[localPreviewUrl]"
                />
                <!-- 视频预览 -->
                <video
                  v-else-if="uploadType === 2"
                  :src="localPreviewUrl"
                  controls
                  style="max-height: 200px;"
                />
                <!-- 文件预览 -->
                <div v-else class="file-preview">
                  <el-icon :size="48"><Document /></el-icon>
                  <div class="file-name">{{ uploadForm.file.name }}</div>
                </div>
                <el-button
                  type="danger"
                  size="small"
                  @click.stop="clearFile"
                  class="mt-10"
                >
                  重新选择
                </el-button>
              </div>
            </template>
          </el-upload>
        </el-form-item>
        <el-form-item label="描述信息">
          <el-input v-model="uploadForm.description" type="textarea" rows="3" placeholder="请输入文件描述"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="closeUploadDialog">取消</el-button>
        <el-button type="primary" @click="submitUpload" :loading="uploadLoading">开始上传</el-button>
      </template>
    </el-dialog>

    <!-- 倒计时弹窗 -->
    <el-dialog v-model="countdownVisible" title="上传中" width="300px" :show-close="false" :close-on-click-modal="false">
      <div class="countdown-content">
        <el-progress type="circle" :percentage="(countdownSeconds / initialCountdown) * 100" :width="100" />
        <div class="countdown-text">
          <div class="countdown-number">{{ countdownSeconds }}</div>
          <div>剩余时间(秒)</div>
        </div>
        <div class="upload-tip">文件较大，请耐心等待...</div>
      </div>
    </el-dialog>

    <!-- 视频播放对话框 -->
    <el-dialog v-model="videoVisible" title="视频播放" width="70%" top="5vh">
      <video :src="currentVideoUrl" controls autoplay class="video-player"></video>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, onBeforeUnmount, computed, nextTick } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
import Vditor from 'vditor'
import 'vditor/dist/index.css'
import {
  Document, Loading, VideoPlay, Picture, Plus
} from '@element-plus/icons-vue'

// API函数
import {
  getActivityMsgApi,
  updateSubTitleApi,
  deleteSubTitleApi,
  addSubTitleApi,
  getSubTitleMsgApi
} from '@/api/activityApi'
import {
  getResourceByLzApi,
  addImageApi,
  addVideoApi,
  addResourceApi
} from '@/api/resourceApi'
import { decodeBase64, encodeBase64 } from "@/utils/base64Utils"

const route = useRoute()
const router = useRouter()
const activityId = ref(route.params.activityId)
const activityData = ref({
  activityTitle: '',
  author: '',
  updateTime: '',
  activityContent: []
})
const loading = ref(false)
const vditorInstances = ref({})
// 清空已选文件
const clearFile = () => {
  uploadForm.file = null;
  if (localPreviewUrl.value) {
    URL.revokeObjectURL(localPreviewUrl.value);
    localPreviewUrl.value = '';
  }
};
// 获取活动详情
const fetchActivityDetail = async () => {
  try {
    loading.value = true
    const res = await getActivityMsgApi(activityId.value)
    if (res.code === 200) {
      activityData.value = res.data
      // 处理资源URL
      await processResourceUrls()
      // 初始化编辑器
      nextTick(() => {
        initEditors()
      })
    } else {
      ElMessage.error(res.message || '获取活动详情失败')
    }
  } catch (error) {
    console.error('获取活动详情出错:', error)
    ElMessage.error('获取活动详情失败')
  } finally {
    loading.value = false
  }
}

// 处理资源URL
const processResourceUrls = async () => {
  if (!activityData.value?.activityContent) return

  for (const subtitle of activityData.value.activityContent) {
    if (!subtitle.resource) continue

    for (const resource of subtitle.resource) {
      if (!resource.url) {
        try {
          const res = await getResourceByLzApi({
            pwd: resource.pwd,
            isNewd: resource.isNewd,
            fid: resource.fid
          })
          if (res.code === 200) {
            resource.url = res.data
          }
        } catch (error) {
          console.error('获取资源URL出错:', error)
        }
      }
    }
  }
}

// 初始化编辑器
const initEditors = () => {
  if (!activityData.value) return

  activityData.value.activityContent.forEach(subtitle => {
    if (subtitle.subtitleType === 0) {
      const elementId = `vditor-${subtitle.subtitleId}`
      if (!vditorInstances.value[subtitle.subtitleId]) {
        const markdownContent = decodeBase64(subtitle.subtitleContent)

        vditorInstances.value[subtitle.subtitleId] = new Vditor(elementId, {
          // height: 400,
          value: markdownContent,
          placeholder: '请输入Markdown内容...',
          toolbar: [
            'emoji', 'headings', 'bold', 'italic', 'strike', '|',
            'list', 'ordered-list', 'check', '|',
            'quote', 'line', 'code', 'inline-code', '|',
            'table', 'link', '|',
            'undo', 'redo', '|',
            'fullscreen', 'preview'
          ],
          cache: {
            enable: false
          }
        })
      }
    }
  })
}

// 保存小标题
const saveSubtitle = async (subtitle) => {
  // 如果正在自动保存，避免重复保存
  if (isAutoSaving.value && subtitle.saving) {
    return
  }

  try {
    subtitle.saving = true
    let content = subtitle.subtitleContent

    // 处理文本类型
    if (subtitle.subtitleType === 0) {
      const vditor = vditorInstances.value[subtitle.subtitleId]
      if (vditor) {
        const markdownContent = vditor.getValue()
        content = encodeBase64(markdownContent)
      }
    }

    const res = await updateSubTitleApi({
      subtitleId: subtitle.subtitleId,
      subtitleName: subtitle.subtitleName,
      subtitleContent: content
    })

    if (res.code === 200) {
      // 只在手动保存时显示消息
      if (!isAutoSaving.value) {
        ElMessage.success('保存成功')
      }
      // 更新修改时间
      activityData.value.updateTime = new Date().toISOString()
    } else {
      ElMessage.error(res.message || '保存失败')
    }
  } catch (error) {
    console.error('保存小标题出错:', error)
    ElMessage.error('保存失败')
  } finally {
    subtitle.saving = false
  }
}
// 删除小标题
const deleteSubtitle = async (subtitleId, index) => {
  try {
    await ElMessageBox.confirm('确定删除这个小标题吗？此操作不可恢复！', '警告', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })

    const res = await deleteSubTitleApi(subtitleId)
    if (res.code === 200) {
      activityData.value.activityContent.splice(index, 1)
      ElMessage.success('删除成功')
    } else {
      ElMessage.error(res.message || '删除失败')
    }
  } catch (error) {
    if (error !== 'cancel') {
      console.error('删除小标题出错:', error)
      ElMessage.error('删除失败')
    }
  }
}

// 添加小标题
const newSubtitleType = ref(0)
const addingSubtitle = ref(false)

const addSubtitle = async () => {
  try {
    addingSubtitle.value = true
    const res = await addSubTitleApi({
      activityId: activityId.value,
      subtitleType: newSubtitleType.value
    })

    if (res.code === 200) {
      // 获取新小标题的详细信息
      const detailRes = await getSubTitleMsgApi(res.data.subtitleId)
      if (detailRes.code === 200) {
        if (!activityData.value.activityContent) {
          activityData.value.activityContent = []
        }
        activityData.value.activityContent.push(detailRes.data)
        ElMessage.success('添加成功')
        // 初始化新编辑器的内容
        nextTick(() => {
          if (detailRes.data.subtitleType === 0) {
            initEditors()
          }
        })
      }
    } else {
      ElMessage.error(res.message || '添加失败')
    }
  } catch (error) {
    console.error('添加小标题出错:', error)
    ElMessage.error('添加失败')
  } finally {
    addingSubtitle.value = false
  }
}

// 上传相关
const uploadVisible = ref(false)
const uploadType = ref(1)
const uploadForm = reactive({ file: null, description: '' })
const uploadRef = ref(null)
const uploadLoading = ref(false)
const localPreviewUrl = ref('')
const currentSubtitle = ref(null)

const uploadTitle = computed(() => {
  const types = { 1: '图片', 2: '视频', 3: '文件' }
  return types[uploadType.value] || '资源'
})

const uploadAccept = computed(() => {
  switch(uploadType.value) {
    case 1: return 'image/*'
    case 2: return 'video/*'
    default: return '*'
  }
})

// 打开上传对话框
const openUploadDialog = (subtitle, type) => {
  currentSubtitle.value = subtitle
  uploadType.value = type
  uploadForm.file = null
  uploadForm.description = ''
  localPreviewUrl.value = ''
  uploadVisible.value = true
}

// 关闭上传对话框
const closeUploadDialog = () => {
  uploadVisible.value = false
  uploadForm.file = null
  if (localPreviewUrl.value) {
    URL.revokeObjectURL(localPreviewUrl.value)
    localPreviewUrl.value = ''
  }
}

// 处理文件选择
const handleFileChange = (file) => {
  uploadForm.file = file.raw
  // 生成本地预览URL
  if (uploadType.value === 1 || uploadType.value === 2) {
    localPreviewUrl.value = URL.createObjectURL(file.raw)
  }
  ElMessage.success('文件已选择，请填写描述信息')
}

// 提交上传
const submitUpload = async () => {
  if (!uploadForm.file) {
    ElMessage.warning('请选择要上传的文件')
    return
  }

  uploadLoading.value = true

  // 计算并显示倒计时
  const countdownTime = calculateCountdown(uploadForm.file.size)
  startCountdown(countdownTime)

  try {
    const file = uploadForm.file
    const description = uploadForm.description || ''

    let res
    switch (uploadType.value) {
      case 1: res = await addImageApi(file, description); break
      case 2: res = await addVideoApi(file, description); break
      case 3: res = await addResourceApi(file, description); break
      default: throw new Error('未知的资源类型')
    }

    if (res.code === 200) {
      ElMessage.success('上传成功')

      // 添加到当前小标题
      const newResource = {
        pwd: res.data.lz.pwd,
        isNewd: res.data.lz.isNewd,
        fid: res.data.lz.fid,
        url: null
      }

      // 添加到资源列表
      currentSubtitle.value.resource.push(newResource)

      // 更新小标题内容（资源ID列表）
      const resourceIds = currentSubtitle.value.subtitleContent
        ? currentSubtitle.value.subtitleContent.split(',')
        : []
      resourceIds.push(res.data.resourceId.toString())
      currentSubtitle.value.subtitleContent = resourceIds.join(',')

      // 保存小标题
      await saveSubtitle(currentSubtitle.value)

      // 获取资源URL
      try {
        const urlRes = await getResourceByLzApi({
          pwd: newResource.pwd,
          isNewd: newResource.isNewd,
          fid: newResource.fid
        })
        if (urlRes.code === 200) {
          newResource.url = urlRes.data
        }
      } catch (error) {
        console.error('获取资源URL出错:', error)
      }

      uploadVisible.value = false
    } else {
      throw new Error(res.message || '上传失败')
    }
  } catch (error) {
    console.error('上传出错:', error)
    ElMessage.error(error.message || '上传失败')
  } finally {
    stopCountdown()
    uploadLoading.value = false
    closeUploadDialog()
  }
}

// 启动自动保存
const startAutoSave = () => {
  if (!autoSaveEnabled.value) return

  autoSaveInterval.value = setInterval(async () => {
    if (!isAutoSaving.value && activityData.value?.activityContent) {
      isAutoSaving.value = true
      try {
        for (const subtitle of activityData.value.activityContent) {
          await saveSubtitle(subtitle)
        }
        // 只在开发环境显示提示，避免干扰用户
        if (process.env.NODE_ENV === 'development') {
          ElMessage.success('自动保存成功')
        }
      } catch (error) {
        console.error('自动保存出错:', error)
      } finally {
        isAutoSaving.value = false
      }
    }
  }, 10000)
}

// 自动保存相关
const autoSaveInterval = ref(null)
const isAutoSaving = ref(false)

// 停止自动保存
const stopAutoSave = () => {
  if (autoSaveInterval.value) {
    clearInterval(autoSaveInterval.value)
    autoSaveInterval.value = null
  }
}
// 倒计时处理
const countdownVisible = ref(false)
const countdownSeconds = ref(0)
const initialCountdown = ref(0)
let countdownTimer = null

// 计算倒计时时间（1MB=2秒，最小1秒，最大120秒）
const calculateCountdown = (fileSize) => {
  const sizeInMB = fileSize / (1024 * 1024)
  let seconds = Math.ceil(sizeInMB * 2)
  if (seconds > 120) seconds = 120
  if (seconds < 1) seconds = 1
  return seconds
}

// 开始倒计时
const startCountdown = (seconds) => {
  countdownSeconds.value = seconds
  initialCountdown.value = seconds
  countdownVisible.value = true

  countdownTimer = setInterval(() => {
    countdownSeconds.value -= 1
    if (countdownSeconds.value <= 0) {
      clearInterval(countdownTimer)
    }
  }, 1000)
}

// 停止倒计时
const stopCountdown = () => {
  if (countdownTimer) {
    clearInterval(countdownTimer)
    countdownTimer = null
  }
  countdownVisible.value = false
}

// 播放视频
const videoVisible = ref(false)
const currentVideoUrl = ref('')

const playVideo = (resource) => {
  if (resource.url) {
    currentVideoUrl.value = resource.url
    videoVisible.value = true
  } else {
    ElMessage.warning('视频URL未加载，请稍后再试')
  }
}

// 下载文件
const downloadFile = (resource) => {
  if (resource.url) {
    const a = document.createElement('a')
    a.href = resource.url
    a.download = resource.url.substring(resource.url.lastIndexOf('/') + 1)
    document.body.appendChild(a)
    a.click()
    document.body.removeChild(a)
  } else {
    ElMessage.warning('文件URL未加载，请稍后再试')
  }
}

// 辅助函数
const getTypeText = (type) => {
  const types = { 0: '文本', 1: '图片', 2: '视频', 3: '文件' }
  return types[type] || '未知'
}

const getTagType = (type) => {
  const types = { 0: '', 1: 'warning', 2: 'danger', 3: 'success' }
  return types[type] || 'info'
}

const formatDateTime = (datetime) => {
  if (!datetime) return '未知'
  return datetime.replace('T', ' ').replace(/\.\d+Z$/, '')
}

const formatFileSize = (bytes) => {
  if (bytes === 0) return '0 B'
  const k = 1024
  const sizes = ['B', 'KB', 'MB', 'GB']
  const i = Math.floor(Math.log(bytes) / Math.log(k))
  return parseFloat((bytes / Math.pow(k, i)).toFixed(2) + ' ' + sizes[i])
}

// 返回上一页
const goBack = () => {
  router.go(-1)
}
const autoSaveEnabled = ref(true)

const toggleAutoSave = () => {
  autoSaveEnabled.value = !autoSaveEnabled.value
  if (autoSaveEnabled.value) {
    startAutoSave()
    ElMessage.success('已开启自动保存')
  } else {
    stopAutoSave()
    ElMessage.warning('已停止自动保存')
  }
}
// 删除资源ID
const removeResourceId = async (subtitle, idToRemove) => {
  try {
    await ElMessageBox.confirm('确定删除这个资源吗？此操作不可恢复！', '警告', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })

    // 更新资源ID列表
    const ids = subtitle.subtitleContent.split(',').filter(id => id !== idToRemove)
    subtitle.subtitleContent = ids.join(',')

    // 从资源列表中移除对应的资源
    const resourceIndex = subtitle.resource.findIndex(res =>
      res.fid === idToRemove || res.pwd === idToRemove
    )
    if (resourceIndex !== -1) {
      subtitle.resource.splice(resourceIndex, 1)
    }

    // 保存修改
    await saveSubtitle(subtitle)
    ElMessage.success('删除成功')
  } catch (error) {
    if (error !== 'cancel') {
      console.error('删除资源出错:', error)
      ElMessage.error('删除失败')
    }
  }
}
// 移动小标题
const moveSubtitle = async (subtitle, direction) => {
  try {
    const subtitleMove = direction === 'up' ? 1 : -1
    const res = await updateSubTitleApi({
      subtitleId: subtitle.subtitleId,
      subtitleMove: subtitleMove
    })

    if (res.code === 200) {
      // 更新本地数据
      const index = activityData.value.activityContent.findIndex(item => item.subtitleId === subtitle.subtitleId)
      if (index !== -1) {
        const newIndex = index + (direction === 'up' ? -1 : 1)
        if (newIndex >= 0 && newIndex < activityData.value.activityContent.length) {
          // 交换数组元素
          const temp = activityData.value.activityContent[index]
          activityData.value.activityContent.splice(index, 1)
          activityData.value.activityContent.splice(newIndex, 0, temp)
          ElMessage.success('移动成功')
        }
      }
    } else {
      ElMessage.error(res.message || '移动失败')
    }
  } catch (error) {
    console.error('移动小标题出错:', error)
    ElMessage.error('移动失败')
  }
}

// 组件挂载时获取数据并启动自动保存
onMounted(async() => {
  await fetchActivityDetail()
  startAutoSave()
})

// 组件卸载时销毁编辑器和停止自动保存
onBeforeUnmount(() => {
  stopAutoSave()
  Object.values(vditorInstances.value).forEach(vditor => {
    if (vditor) {
      vditor.destroy()
    }
  })
})
</script>

<style scoped>
.activity-editor {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.page-header {
  margin-bottom: 30px;
  padding-bottom: 20px;
  border-bottom: 1px solid #eee;
}

.header-content h1 {
  font-size: 24px;
  margin-bottom: 10px;
}

.sub-header {
  display: flex;
  gap: 10px;
  flex-wrap: wrap;
  align-items: center;
}
/* 自动保存状态动画 */
@keyframes pulse {
  0% { opacity: 1; }
  50% { opacity: 0.5; }
  100% { opacity: 1; }
}

.auto-saving {
  animation: pulse 1.5s infinite;
}

.loading-overlay {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 300px;
  color: #666;
}

.loading-icon {
  font-size: 40px;
  margin-bottom: 15px;
  animation: rotate 2s linear infinite;
}

@keyframes rotate {
  from { transform: rotate(0deg); }
  to { transform: rotate(360deg); }
}

.subtitle-card {
  position: relative; /* 添加相对定位 */
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.05);
  margin-bottom: 25px;
  overflow: hidden;
  border-left: 4px solid;
  padding-bottom: 60px; /* 为底部按钮留出空间 */
}

.subtitle-card.type-0 { border-left-color: #409eff; }
.subtitle-card.type-1 { border-left-color: #e6a23c; }
.subtitle-card.type-2 { border-left-color: #f56c6c; }
.subtitle-card.type-3 { border-left-color: #67c23a; }
/* 调整标题栏样式 */
.subtitle-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 15px;
  padding: 15px 20px;
  background: #f8fafc;
  border-bottom: 1px solid #eee;
}
.header-left {
  display: flex;
  align-items: center;
  gap: 15px;
  min-width: 0; /* 添加这行防止flex元素溢出 */
}

.header-right {
  flex-shrink: 0; /* 防止按钮被压缩 */
}

.title-input {
  flex: 0 0 300px; /* 固定宽度300px，不伸缩 */
  min-width: 0; /* 重要：防止输入框溢出 */
}
.actions {
  display: flex;
  gap: 10px;
  align-items: center;
}
/* 调整按钮样式 */
.header-right .el-button-group .el-button {
  padding: 8px 10px;
}

.header-right .el-button-group .el-icon {
  font-size: 14px;
}
.editor-container {
  padding: 15px;
}

.vditor-wrapper {
  border: 1px solid #dcdfe6;
  border-radius: 4px;
}

.resource-container {
  padding: 15px;
}

.resource-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 15px;
  margin-bottom: 15px;
}

.resource-card {
  border: 1px solid #ebeef5;
  border-radius: 6px;
  overflow: hidden;
  transition: all 0.3s;
}

.resource-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.resource-img {
  height: 150px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: #f8fafc;
}
/* 修改底部按钮样式 */
.subtitle-footer {
  position: absolute;
  right: 20px;
  bottom: 15px;
  display: flex;
  gap: 10px;
}
.resource-img .el-image {
  width: 100%;
  height: 100%;
}

.image-error, .image-loading {
  display: flex;
  flex-direction: column;
  align-items: center;
  color: #909399;
}

.image-error .el-icon, .image-loading .el-icon {
  font-size: 40px;
  margin-bottom: 5px;
}

.resource-info {
  padding: 10px;
  display: flex;
  justify-content: space-between;
}

.video-preview {
  height: 150px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background: #f0f9ff;
  cursor: pointer;
  transition: background 0.3s;
}

.video-preview:hover {
  background: #e0f0ff;
}

.video-preview .el-icon {
  font-size: 50px;
  color: #409eff;
  margin-bottom: 10px;
}

.file-list {
  margin-bottom: 15px;
}

.file-item {
  display: flex;
  align-items: center;
  padding: 12px 15px;
  border: 1px solid #ebeef5;
  border-radius: 6px;
  margin-bottom: 10px;
  transition: all 0.3s;
}

.file-item:hover {
  background: #f8fafc;
  border-color: #409eff;
}

.file-icon {
  font-size: 28px;
  margin-right: 15px;
  color: #409eff;
}

.file-info {
  flex: 1;
}

.file-name {
  font-weight: 500;
  margin-bottom: 5px;
}

.file-meta {
  font-size: 12px;
  color: #909399;
}

.upload-area {
  border: 2px dashed #dcdfe6;
  border-radius: 6px;
  padding: 10px;
  text-align: center;
  cursor: pointer;
  transition: all 0.3s;
  display: flex;
  flex-direction: column;
  align-items: center;
  color: #909399;
}

.upload-area:hover {
  border-color: #409eff;
  color: #409eff;
}

.upload-area .el-icon {
  font-size: 30px;
  margin-bottom: 10px;
}

.add-subtitle {
  display: flex;
  gap: 15px;
  padding: 20px;
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.05);
}

.type-select {
  flex: 1;
}

.file-info {
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
  padding: 8px;
  background: #f8fafc;
  border-radius: 4px;
}

.preview-container {
  margin-top: 15px;
  display: flex;
  justify-content: center;
}

.preview-image {
  max-width: 100%;
  max-height: 300px;
  border-radius: 4px;
}

.preview-video {
  max-width: 100%;
  max-height: 300px;
  border-radius: 4px;
}

.countdown-content {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.countdown-text {
  margin-top: 15px;
  text-align: center;
}

.countdown-number {
  font-size: 28px;
  font-weight: bold;
  color: #409eff;
}

.upload-tip {
  margin-top: 15px;
  color: #909399;
}
.upload-demo {
  width: 100%;
  min-height: 180px;
}

.upload-area, .preview-area {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 20px;
}

.file-preview {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
}

.file-name {
  margin-top: 10px;
  word-break: break-all;
  text-align: center;
}

.mt-10 {
  margin-top: 10px;
}
.video-player {
  width: 100%;
  max-height: 70vh;
  border-radius: 6px;
}
.resource-ids {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
  margin-bottom: 15px;
  padding: 10px;
  background: #f8fafc;
  border-radius: 4px;
}

.resource-ids .el-tag {
  margin-right: 5px;
  cursor: pointer;
}

.resource-ids .el-tag:hover {
  opacity: 0.8;
}
</style>
