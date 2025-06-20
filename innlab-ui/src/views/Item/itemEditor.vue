<template>
  <div class="item-editor-container">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>{{ formData.itemTitle }}</span>
          <el-tag type="success">{{ formData.itemDepartment }}</el-tag>
        </div>
      </template>

      <!-- 新增项目详情信息区域 -->
      <div class="item-info">
        <el-descriptions :column="2" border>
          <el-descriptions-item label="项目简介" :span="2">
            {{ formData.itemIntroduction }}
          </el-descriptions-item>
          <el-descriptions-item label="创建作者">
            {{ formData.author }}
          </el-descriptions-item>
          <el-descriptions-item label="最后修改时间">
            {{ formatDateTime(formData.updateTime) }}
          </el-descriptions-item>
          <el-descriptions-item label="创建时间">
            {{ formatDateTime(formData.createTime) }}
          </el-descriptions-item>
          <el-descriptions-item label="最后修改人">
            {{ formData.auditor || '暂无' }}
          </el-descriptions-item>
        </el-descriptions>
      </div>

      <div class="editor-wrapper">
        <div id="vditor" ref="vditorRef"></div>
      </div>

      <div class="action-buttons">
        <el-button type="primary" @click="submitForm" :loading="loading">保存修改</el-button>
        <el-button @click="goBack">返回</el-button>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import Vditor from 'vditor'
import 'vditor/dist/index.css'
import { getItemMsgApi, updateItemApi } from '@/api/itemApi'
import { decodeBase64, encodeBase64 } from "@/utils/base64Utils.js"

const route = useRoute()
const router = useRouter()
const loading = ref(false)
const vditorRef = ref(null)
let vditorInstance = null

// 表单数据
const formData = ref({
  itemId: null,
  itemTitle: '',
  itemDepartment: '',
  itemIntroduction: '',
  markdownBase64: '',
  createTime: '',
  author: '',
  updateTime: '',
  auditor: ''
})

// 格式化日期时间
const formatDateTime = (datetime) => {
  if (!datetime) return '未知'
  return datetime.replace('T', ' ').replace(/\.\d+Z$/, '')
}

// 获取item详情
const fetchItemDetail = async () => {
  try {
    loading.value = true
    const res = await getItemMsgApi(route.params.itemId)

    if (res.code === 200) {
      formData.value = res.data
      // 解码base64的markdown内容并初始化编辑器
      const markdownContent = decodeBase64(res.data.markdownBase64)
      initVditor(markdownContent)
    } else {
      ElMessage.error(res.message || '获取项目详情失败')
    }
  } catch (error) {
    console.error('获取项目详情出错:', error)
    ElMessage.error('获取项目详情失败，请稍后重试')
  } finally {
    loading.value = false
  }
}

// 初始化Vditor编辑器
const initVditor = (content) => {
  if (vditorInstance) {
    vditorInstance.setValue(content)
    return
  }

  vditorInstance = new Vditor('vditor', {
    minHeight: 300,
    value: content,
    placeholder: '请输入Markdown内容...',
    toolbar: [
      'headings', 'bold', 'italic', 'strike', '|',
      'list', 'ordered-list', 'check', '|',
      'quote', 'line', 'code', 'inline-code', '|',
      'table', 'link', '|',
      'undo', 'redo', '|',
      'fullscreen'
    ],
    toolbarConfig: {
      pin: true,
    },
    cache: {
      enable: false
    },
    upload: {
      image: false,
      file: false,
      video: false,
      audio: false
    },
    after: () => {
      const editorElement = document.querySelector('#vditor .vditor-ir')
      if (editorElement) {
        editorElement.style.overflowY = 'visible'
      }
    }
  })
}

// 提交修改
const submitForm = async () => {
  if (!vditorInstance) return

  try {
    loading.value = true

    const markdownContent = vditorInstance.getValue()
    const encodedContent = encodeBase64(markdownContent)

    const res = await updateItemApi({
      itemId: formData.value.itemId,
      markdownBase64: encodedContent
    })

    if (res.code === 200) {
      ElMessage.success('修改成功')
      // 更新修改时间
      formData.value.updateTime = new Date().toISOString()
    } else {
      ElMessage.error(res.message || '修改失败')
    }
  } catch (error) {
    console.error('修改项目内容出错:', error)
    ElMessage.error('修改失败，请稍后重试')
  } finally {
    loading.value = false
  }
}

// 返回上一页
const goBack = () => {
  router.go(-1)
}

// 组件挂载时获取数据
onMounted(() => {
  fetchItemDetail()
})

// 组件卸载时销毁编辑器
onBeforeUnmount(() => {
  if (vditorInstance) {
    vditorInstance.destroy()
    vditorInstance = null
  }
})
</script>

<style scoped>
.item-editor-container {
  padding: 20px;
}

.box-card {
  margin: 0 auto;
  max-width: 1200px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

/* 新增项目信息区域样式 */
.item-info {
  margin-bottom: 20px;
  padding: 15px;
  background-color: #f8f8f8;
  border-radius: 4px;
}

.editor-wrapper {
  margin-bottom: 20px;
}

.action-buttons {
  display: flex;
  justify-content: flex-end;
  margin-top: 20px;
}

#vditor {
  height: auto !important;
  min-height: 300px;
  width: 100%;
}

#vditor .vditor-ir {
  height: auto !important;
  overflow-y: visible !important;
}

#vditor .vditor-content {
  height: auto !important;
  min-height: 300px;
}
</style>
