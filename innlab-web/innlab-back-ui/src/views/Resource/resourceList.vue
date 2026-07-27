<template>
  <div class="p-20">
    <div class="flex justify-between mb-20">
      <CustomSearch
        class="flex-1"
        :searchConfig="searchConfig"
        @updateQueryData="updateQueryData"
      ></CustomSearch>

      <div class="ml-20">
        <el-button type="primary" @click="showUploadDialog(1)">添加图片</el-button>
        <el-button type="success" @click="showUploadDialog(2)">添加视频</el-button>
        <el-button type="warning" @click="showUploadDialog(3)">添加其他文件</el-button>
        <el-button type="info" @click="showUploadDialog(4)">添加PDF</el-button>
        <el-button type="danger" @click="showUploadDialog(5)">添加MP3</el-button>
      </div>
    </div>

    <CustomTable
      :tableConfig="tableConfig"
      :tableData="tableData"
      :total="total"
      @updateQueryData="updateQueryData"
      v-loading="tableLoading"
    >
      <!-- 资源类型列的插槽 -->
      <template #resourceType ="{ row }">
        <el-tag :type="getResourceTypeTagType(row.resourceType)">
          {{resourceTypeOptions.find(item => item.value === row.resourceType)?.label || '未知'}}
        </el-tag>
      </template>
      <!-- 预览列的插槽 -->
      <template #preview="{ row }">
        <div v-if="row.resourceType === 1" class="flex justify-center">
          <el-image
            v-if="row.resourceUrl"
            :src="row.resourceUrl"
            :preview-src-list="[row.resourceUrl]"
            fit="cover"
            style="width: 60px; height: 60px; cursor: pointer;"
            hide-on-click-modal
            preview-teleported
          />
          <span v-else>请点击详情预览</span>
        </div>
        <div v-else-if="row.resourceType === 2" class="video-preview">
          <el-icon :size="24"><VideoPlay /></el-icon>
        </div>
        <div v-else-if="row.resourceType === 4" class="pdf-preview">
          <el-icon :size="24"><Document /></el-icon>
        </div>
        <div v-else-if="row.resourceType === 5" class="mp3-preview">
          <el-icon :size="24"><VideoPlay /></el-icon>
        </div>
        <div v-else class="file-preview">
          <el-icon :size="24"><Document /></el-icon>
        </div>
      </template>
      <!-- 操作列的插槽 -->
      <template #handle="{ row }">
        <el-button type="primary" plain @click="handleDetail(row)">详情</el-button>
      </template>
    </CustomTable>

    <!-- 详情对话框 -->
    <el-dialog v-model="detailVisible" :title="`资源详情 - ${detailData.resourceName}`" width="700px">
      <el-descriptions :column="1" border>
        <el-descriptions-item label="资源ID">{{ detailData.resourceId }}</el-descriptions-item>
        <el-descriptions-item label="资源路径">{{ detailData.resourcePath}}</el-descriptions-item>
        <el-descriptions-item label="资源类型">
          <el-tag :type="getResourceTypeTagType(detailData.resourceType)">
            {{resourceTypeOptions.find(item => item.value === detailData.resourceType)?.label || '未知'}}
          </el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="资源描述">{{ detailData.resourceDescription || '无' }}</el-descriptions-item>
        <el-descriptions-item label="创建时间">{{ detailData.createTime }}</el-descriptions-item>
        <el-descriptions-item label="资源预览">
          <div v-if="loadingResourceUrl" class="flex justify-center">
            <el-icon class="is-loading" size="24"><Loading /></el-icon>
          </div>
          <div v-else>
            <!-- 图片预览 -->
            <el-image
              v-if="detailData.resourceType === 1 && resourceUrl"
              :src="resourceUrl"
              :preview-src-list="[resourceUrl]"
              fit="contain"
              style="max-width: 100%;"
            />
            <!-- 视频预览 -->
            <video
              v-else-if="detailData.resourceType === 2 && resourceUrl"
              :src="resourceUrl"
              controls
              style="max-height: 300px;"
            />
            <!-- PDF预览 -->
            <div v-else-if="detailData.resourceType === 4 && resourceUrl" class="flex items-center">
              <PDFView :pdfUrl="resourceUrl"
                       controls style="width: 100%;"/>
            </div>
            <!-- MP3预览 -->
            <div v-else-if="detailData.resourceType === 5 && resourceUrl" class="flex items-center">
              <audio :src="resourceUrl" controls style="width: 100%;"></audio>
            </div>
            <!-- 其他文件 -->
            <div v-else-if="detailData.resourceType === 3 && resourceUrl" class="flex items-center">
              <el-icon size="24" class="mr-10"><Document /></el-icon>
              <el-link :href="resourceUrl" target="_blank">点击下载文件</el-link>
            </div>
            <span v-else>无法预览</span>
          </div>
        </el-descriptions-item>
        <!-- 新增：资源直链 -->
        <el-descriptions-item label="资源直链">
          <div class="direct-link-container">

            <!-- 下载直链2 -->
            <div class="direct-link-item">
              <span class="link-label">下载直链：</span>
              <el-input
                v-model="directLink"
                readonly
                class="link-input"
              >
                <template #append>
                  <el-button @click="copyToClipboard(directLink)">
                    <el-icon><DocumentCopy /></el-icon>
                  </el-button>
                </template>
              </el-input>
            </div>

            <!-- 根据资源类型显示不同的链接格式 -->
            <div v-if="detailData.resourceType === 1" class="direct-link-item">
              <span class="link-label">图片链接1：</span>
              <el-input
                v-model="imageHtmlLink"
                readonly
                class="link-input"
              >
                <template #append>
                  <el-button @click="copyToClipboard(imageHtmlLink)">
                    <el-icon><DocumentCopy /></el-icon>
                  </el-button>
                </template>
              </el-input>
            </div>
            <div v-if="detailData.resourceType === 1" class="direct-link-item">
              <span class="link-label">图片链接2：</span>
              <el-input
                v-model="imageMarkdownLink"
                readonly
                class="link-input"
              >
                <template #append>
                  <el-button @click="copyToClipboard(imageMarkdownLink)">
                    <el-icon><DocumentCopy /></el-icon>
                  </el-button>
                </template>
              </el-input>
            </div>

            <div v-if="detailData.resourceType === 2" class="direct-link-item">
              <span class="link-label">HTML视频链接：</span>
              <el-input
                v-model="videoHtmlLink"
                readonly
                class="link-input"
              >
                <template #append>
                  <el-button @click="copyToClipboard(videoHtmlLink)">
                    <el-icon><DocumentCopy /></el-icon>
                  </el-button>
                </template>
              </el-input>
            </div>

            <div v-if="detailData.resourceType === 4" class="direct-link-item">
              <span class="link-label">PDF组件链接：</span>
              <el-input
                v-model="pdfComponentLink"
                readonly
                class="link-input"
              >
                <template #append>
                  <el-button @click="copyToClipboard(pdfComponentLink)">
                    <el-icon><DocumentCopy /></el-icon>
                  </el-button>
                </template>
              </el-input>
            </div>

            <div v-if="detailData.resourceType === 5" class="direct-link-item">
              <span class="link-label">HTML音频链接：</span>
              <el-input
                v-model="audioHtmlLink"
                readonly
                class="link-input"
              >
                <template #append>
                  <el-button @click="copyToClipboard(audioHtmlLink)">
                    <el-icon><DocumentCopy /></el-icon>
                  </el-button>
                </template>
              </el-input>
            </div>
          </div>
        </el-descriptions-item>
      </el-descriptions>

      <template #footer>
        <el-button type="primary" @click="detailVisible = false">关闭</el-button>
      </template>
    </el-dialog>
    <!-- 在template中添加 -->
    <el-dialog
      v-model="countdownVisible"
      title="文件上传中"
      width="400px"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      :show-close="false"
    >
      <div class="countdown-container">
        <el-progress
          type="circle"
          :percentage="100 - (countdownSeconds / (calculateCountdown(uploadForm.value?.file?.size || 0) / 100))"
          :color="countdownSeconds > 5 ? '#409eff' : '#f56c6c'"
        >
          <span class="countdown-text">{{ countdownSeconds }}秒</span>
        </el-progress>
        <div class="countdown-tip">
          预计剩余时间 {{ countdownSeconds }} 秒<br>
          文件大小约 {{ countdownSeconds/2 }} MB
        </div>
      </div>
      <template #footer>
        <el-button
          type="danger"
          @click="stopCountdown(); uploadLoading = false;"
          :disabled="!uploadLoading"
        >
          取消上传
        </el-button>
      </template>
    </el-dialog>
    <!-- 上传对话框 -->
    <el-dialog v-model="uploadVisible" :title="`添加${uploadTitle}`" width="500px">
      <el-form :model="uploadForm" label-width="80px" v-loading="uploadLoading">
        <el-form-item :label="`选择${uploadTitle}`" required>
          <el-upload
            class="upload-demo"
            drag
            :before-upload="beforeUpload"
            :on-change="handleFileChange"
            :show-file-list="false"
            :auto-upload="false"
            :accept="uploadAccept"
          >
            <div v-if="!uploadForm.file" class="upload-area">
              <el-icon class="el-icon--upload"><upload-filled /></el-icon>
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
          </el-upload>
        </el-form-item>
        <el-form-item label="文件描述">
          <el-input
            v-model="uploadForm.description"
            type="textarea"
            :rows="3"
            placeholder="请输入文件描述"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="uploadVisible = false" :disabled="uploadLoading">取消</el-button>
        <el-button
          type="primary"
          @click="submitUpload"
          :loading="uploadLoading"
        >
          {{ uploadLoading ? '上传中...' : '确认上传' }}
        </el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>


// import { ref, onMounted, computed, watch } from 'vue';
import { DocumentCopy } from '@element-plus/icons-vue';
// 其他导入保持不变

const directLink = computed(() => {
  if (!detailData.value.fid || !detailData.value.pwd || !detailData.value.isNewd) {
    return '链接信息不完整';
  }
  return `https://lz.tyut.tech/lz?fid=${detailData.value.fid}&pwd=${detailData.value.pwd}&isNewd=${detailData.value.isNewd}`;
});

const imageHtmlLink = computed(() => {
  return `<img src="${directLink.value}" alt="${detailData.value.resourceName}">`;
});

const imageMarkdownLink = computed(() => {
  return `![](${directLink.value})`;
});
const videoHtmlLink = computed(() => {
  return `<video width="100%" controls><source src="${directLink.value}" type="video/mp4">您的浏览器不支持视频播放</video>`;
});

const pdfComponentLink = computed(() => {
  return `<PdfPreview pdfUrl="${directLink.value}" />`;
});

const audioHtmlLink = computed(() => {
  return `<audio width="100%" controls><source src="${directLink.value}" type="audio/mpeg">您的浏览器不支持MP3播放</audio>`;
});

// 新增：复制到剪贴板功能
const copyToClipboard = async (text) => {
  try {
    await navigator.clipboard.writeText(text);
    ElMessage.success('已复制到剪贴板');
  } catch (err) {
    console.error('复制失败:', err);
    // 备用方案
    const textArea = document.createElement('textarea');
    textArea.value = text;
    document.body.appendChild(textArea);
    textArea.select();
    try {
      document.execCommand('copy');
      ElMessage.success('已复制到剪贴板');
    } catch (backupErr) {
      ElMessage.error('复制失败');
    }
    document.body.removeChild(textArea);
  }
};
import { ref, onMounted, computed } from 'vue';
import CustomTable from "@/components/CustomTable/index.vue";
import CustomSearch from "@/components/CustomSearch/index.vue";
import { tableConfig, searchConfig, resourceTypeOptions } from "./resourceListConfig";
import PDFView from "@/components/PdfPreview/index.vue";
import touristHttp from "@/utils/touristHttp.js";

import {
  getResourceListApi,
  getResourceByLzApi,
  addImageApi,
  addVideoApi,
  addResourceApi,
  addPdfApi,
  addMp3Api
} from "@/api/resourceApi";
import { ElMessage } from "element-plus";
import { UploadFilled, Document, Loading ,VideoPlay } from '@element-plus/icons-vue';

// 表格数据
const tableData = ref([]);
const total = ref(0);
const queryParams = ref({
  pageNum: 1,
  pageSize: 10,
  resourceType: null
});
// 上传相关状态
const uploadLoading = ref(false);
const localPreviewUrl = ref('');

// 根据类型设置accept属性
const uploadAccept = computed(() => {
  switch(uploadType.value) {
    case 1: return 'image/*';
    case 2: return 'video/*';
    case 4: return '.pdf';
    case 5: return '.mp3';
    default: return '*';
  }
});

// 获取资源类型标签样式
const getResourceTypeTagType = (resourceType) => {
  switch(resourceType) {
    case 1: return 'success';
    case 2: return 'primary';
    case 3: return 'danger';
    case 4: return 'warning';
    case 5: return 'info';
    default: return 'info';
  }
};

// 详情对话框
const detailVisible = ref(false);
const detailData = ref({});
const resourceUrl = ref('');
const loadingResourceUrl = ref(false);

// 上传相关
const uploadVisible = ref(false);
const uploadType = ref(1); // 1:图片 2:视频 3:其他 4:PDF 5:MP3
const uploadForm = ref({
  file: null,
  description: ''
});
const fileList = ref([]);
const uploadRef = ref();

const uploadTitle = computed(() => {
  return resourceTypeOptions.find(item => item.value === uploadType.value)?.label || '资源';
});

const uploadAction = computed(() => {
  return ''; // 不使用自动上传，改为手动上传
});

// 表格加载状态
const tableLoading = ref(false);

// 修改获取资源列表方法
const fetchResourceList = async () => {
  try {
    tableLoading.value = true;
    const res = await getResourceListApi(queryParams.value);
    if (res.code === 200) {
      tableData.value = res.data.records;
      total.value = res.data.total;
    }
  } finally {
    tableLoading.value = false;
  }
};

// 更新查询参数并重新加载数据
const updateQueryData = (params, shouldFetch = true) => {
  if (params.pageNum !== undefined) {
    queryParams.value.pageNum = params.pageNum;
  }
  if (params.pageSize !== undefined) {
    queryParams.value.pageSize = params.pageSize;
  }
  if (params.resourceType !== undefined) {
    queryParams.value.resourceType = params.resourceType;
  }

  Object.assign(queryParams.value, params);

  if (shouldFetch) {
    fetchResourceList();
  }
};

// 查看详情
const handleDetail = async (row) => {
  detailData.value = row;
  resourceUrl.value = row.resourceUrl || '';
  loadingResourceUrl.value = false;

  /**
   * 旧版接口直链获取
   */
  // // 如果资源URL为空，需要调用接口获取
  // if (!row.resourceUrl) {
  //   try {
  //     loadingResourceUrl.value = true;
  //     const res = await getResourceByLzApi({
  //       pwd: row.pwd,
  //       isNewd: row.isNewd,
  //       fid: row.fid
  //     });
  //
  //     if (res.code === 200) {
  //       resourceUrl.value = res.data;
  //     } else {
  //       ElMessage.error(res.message || '获取资源URL失败');
  //     }
  //   } catch (error) {
  //     console.error('获取资源URL出错:', error);
  //     ElMessage.error('获取资源URL失败');
  //   } finally {
  //     loadingResourceUrl.value = false;
  //   }
  // }

  // 如果资源URL为空，需要调用接口获取
  if (!row.resourceUrl) {
    try {
      loadingResourceUrl.value = true;
      // 构建查询参数
      const params = {
        pwd: row.pwd,
        isNewd: row.isNewd,
        fId: row.fid
      };

      const queryParams = new URLSearchParams(params).toString();

      let baseURL = touristHttp.defaults.baseURL;
      resourceUrl.value = `${baseURL}/foreign/getResource?${queryParams}`;

      console.log(resourceUrl.value);
    } catch (error) {
      console.error('获取资源URL出错:', error);
      ElMessage.error('获取资源URL失败');
    } finally {
      loadingResourceUrl.value = false;
    }
  }

  detailVisible.value = true;
};

// 显示上传对话框
const showUploadDialog = (type) => {
  console.log('打开上传对话框，类型:', type);
  uploadType.value = type;
  uploadForm.value = {
    file: null,
    description: ''
  };
  fileList.value = [];
  uploadVisible.value = true;
};

// 处理文件选择
const handleFileChange = (file) => {
  uploadForm.value.file = file.raw;

  // 生成本地预览URL
  if (uploadType.value === 1 || uploadType.value === 2) {
    localPreviewUrl.value = URL.createObjectURL(file.raw);
  }

  ElMessage.success('文件已选择，请填写描述信息');
};

// 清空已选文件
const clearFile = () => {
  uploadForm.value.file = null;
  if (localPreviewUrl.value) {
    URL.revokeObjectURL(localPreviewUrl.value);
    localPreviewUrl.value = '';
  }
};

// 上传前校验
const beforeUpload = (file) => {
  const isImage = file.type.startsWith('image/')
  if (!isImage) {
    ElMessage.error('只能上传图片文件!')
    return false
  }
  return false // 必须返回false以禁用自动上传
}

// 提交上传
const submitUpload = async () => {
  if (!uploadForm.value.file) {
    ElMessage.warning('请选择要上传的文件');
    return;
  }

  uploadLoading.value = true;

  try {
    const file = uploadForm.value.file;
    const description = uploadForm.value.description || '';

    // 计算并显示倒计时
    const countdownTime = calculateCountdown(file.size);
    startCountdown(countdownTime);

    console.log('文件大小:', file.size, '倒计时:', countdownTime, '秒');

    let res;
    switch (uploadType.value) {
      case 1:
        res = await addImageApi(file, description);
        break;
      case 2:
        res = await addVideoApi(file, description);
        break;
      case 3:
        res = await addResourceApi(file, description);
        break;
      case 4:
        res = await addPdfApi(file, description);
        break;
      case 5:
        res = await addMp3Api(file, description);
        break;
      default:
        throw new Error('未知的资源类型');
    }

    if (res.code === 200) {
      ElMessage.success('上传成功');
      uploadVisible.value = false;
      await fetchResourceList();
    } else {
      throw new Error(res.message || '上传失败');
    }
  } catch (error) {
    console.error('上传出错:', error);
    ElMessage.error(error.message || '上传失败');
  } finally {
    stopCountdown(); // 无论成功失败都关闭倒计时
    uploadLoading.value = false;
    clearFile();
  }
};

// 倒计时弹窗
const countdownVisible = ref(false);
const countdownSeconds = ref(0);
let countdownTimer = null;

// 计算倒计时时间（1MB=2秒，最小1秒，最大60秒）
const calculateCountdown = (fileSize) => {
  const sizeInMB = fileSize / (1024 * 1024);
  let seconds = Math.ceil(sizeInMB * 2);
  if (seconds>10){
    ElMessage.success('文件过大，上传缓慢，请耐心等待！');
  }
  return Math.max(seconds, 1);
};

// 开始倒计时
const startCountdown = (seconds) => {
  countdownSeconds.value = seconds;
  countdownVisible.value = true;

  countdownTimer = setInterval(() => {
    countdownSeconds.value -= 1;
    if (countdownSeconds.value <= 0) {
      clearInterval(countdownTimer);
    }
  }, 1000);
};

// 停止倒计时
const stopCountdown = () => {
  if (countdownTimer) {
    clearInterval(countdownTimer);
    countdownTimer = null;
  }
  countdownVisible.value = false;
};

// 上传成功处理
const handleUploadSuccess = (response) => {
  if (response.code === 200) {
    ElMessage.success('上传成功');
    uploadVisible.value = false;
    fetchResourceList();
  } else {
    ElMessage.error(response.message || '上传失败');
  }
};


// 上传失败处理
const handleUploadError = (error) => {
  console.error('上传失败:', error);
  ElMessage.error('上传失败');
};

// 组件挂载时加载数据
onMounted(() => {
  fetchResourceList();
});
</script>

<style scoped>
.upload-demo {
  width: 100%;
}
.video-preview, .file-preview {
  width: 60px;
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: #f5f7fa;
  color: var(--el-color-primary);
}
</style>
<style scoped>
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

/* 加载动画 */
:deep(.el-loading-spinner) {
  background: rgba(255, 255, 255, 0.7);
  border-radius: 4px;
  padding: 20px;
}
</style>
<style scoped>
.countdown-container {
  text-align: center;
  padding: 20px;
}

.countdown-text {
  font-size: 24px;
  font-weight: bold;
}

.countdown-tip {
  margin-top: 20px;
  color: #666;
  line-height: 1.6;
}
.direct-link-container {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.direct-link-item {
  display: flex;
  align-items: center;
  gap: 8px;
}

.link-label {
  min-width: 100px;
  font-weight: 500;
  color: #606266;
}

.link-input {
  flex: 1;
}

:deep(.link-input .el-input__inner) {
  font-family: monospace;
  font-size: 12px;
}
</style>
