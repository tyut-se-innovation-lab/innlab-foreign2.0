<template>
  <div class="cache-img-container">
    <div class="header">
      <h2>缓存图片管理</h2>
      <div class="action-buttons">
        <el-button
          type="primary"
          @click="downloadAllImages"
          :disabled="!imageUrls.length"
        >
          <el-icon><Download /></el-icon>
          下载全部图片
        </el-button>
        <el-button
          type="danger"
          @click="deleteAllCache"
          :disabled="!imageUrls.length"
          :loading="deleting"
        >
          <el-icon><Delete /></el-icon>
          删除全部缓存
        </el-button>
      </div>
    </div>

    <!-- 新增JSON输入区域 -->
    <div class="json-input-area">
      <el-input
        v-model="jsonInput"
        type="textarea"
        :rows="5"
        placeholder="请输入JSON数据"
        class="json-textarea"
      ></el-input>
      <el-button
        type="primary"
        @click="handleJsonSubmit"
        :loading="parsingJson"
        class="submit-btn"
      >
        <el-icon><Upload /></el-icon>
        提取并上传URL
      </el-button>
    </div>

    <div class="image-grid">
      <div
        v-for="(url, index) in imageUrls"
        :key="index"
        class="image-card"
      >
        <el-image
          :src="url"
          fit="cover"
          :preview-src-list="imageUrls"
          :initial-index="index"
          hide-on-click-modal
          class="image-preview"
        >
          <template #error>
            <div class="image-error">
              <el-icon><Picture /></el-icon>
              <span>图片加载失败</span>
            </div>
          </template>
        </el-image>
        <div class="image-actions">
          <el-button
            size="small"
            @click="downloadImage(url)"
            type="primary"
            plain
          >
            <el-icon><Download /></el-icon>
            下载
          </el-button>
        </div>
      </div>
    </div>

    <div v-if="loading" class="loading-overlay">
      <el-icon class="loading-icon"><Loading /></el-icon>
      <span>加载中...</span>
    </div>

    <div v-if="!loading && !imageUrls.length" class="empty-state">
      <el-empty description="暂无缓存图片" />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus';
import { Download, Picture, Loading, Delete, Upload } from '@element-plus/icons-vue';
import { getCacheImgApi, deleteAllCacheApi, wyImgUploadApi } from '@/api/resourceApi';

const imageUrls = ref([]);
const loading = ref(false);
const deleting = ref(false);
const jsonInput = ref('');
const parsingJson = ref(false);

// 获取缓存图片
const fetchCacheImages = async () => {
  try {
    loading.value = true;
    const response = await getCacheImgApi();
    if (response.code === 200) {
      imageUrls.value = response.data || [];
    } else {
      ElMessage.error(response.message || '获取图片失败');
    }
  } catch (error) {
    console.error('获取缓存图片出错:', error);
    ElMessage.error('获取图片失败');
  } finally {
    loading.value = false;
  }
};

// 处理JSON输入并提取URL
const handleJsonSubmit = async () => {
  if (!jsonInput.value.trim()) {
    ElMessage.warning('请输入JSON数据');
    return;
  }

  try {
    parsingJson.value = true;

    // 解析JSON
    const jsonData = JSON.parse(jsonInput.value);

    // 提取所有https_url和thumb_url
    const urls = extractUrlsFromJson(jsonData);

    if (urls.size === 0) {
      ElMessage.warning('未找到有效的图片URL');
      return;
    }

    // 调用上传接口
    await wyImgUploadApi(urls);
    ElMessage.success(`成功上传 ${urls.size} 个图片URL`);

    // 清空输入框
    jsonInput.value = '';

    // 刷新图片列表
    await fetchCacheImages();
  } catch (error) {
    console.error('处理JSON出错:', error);
    ElMessage.error(`处理失败: ${error.message}`);
  } finally {
    parsingJson.value = false;
  }
};

// 从JSON数据中递归提取URL
const extractUrlsFromJson = (data, urlSet = new Set()) => {
  if (!data) return urlSet;

  // 如果是对象，递归检查每个属性
  if (typeof data === 'object' && data !== null) {
    for (const key in data) {
      // 检查是否是URL字段
      if (key === 'https_url' || key === 'thumb_url') {
        const url = data[key];
        if (typeof url === 'string' && url.trim() !== '') {
          urlSet.add(url);
        }
      } else {
        // 递归处理子属性
        extractUrlsFromJson(data[key], urlSet);
      }
    }
  }
  // 如果是数组，递归处理每个元素
  else if (Array.isArray(data)) {
    data.forEach(item => extractUrlsFromJson(item, urlSet));
  }

  return urlSet;
};



// 下载单张图片
const downloadImage = (url) => {
  try {
    // 提取文件名（从URL最后部分获取）
    const fileName = url.split('/').pop() || 'image.jpg';

    // 创建隐藏的下载链接
    const a = document.createElement('a');
    a.href = url;
    a.download = fileName;
    a.style.display = 'none';

    // 添加到DOM并触发点击
    document.body.appendChild(a);
    a.click();

    // 清理DOM
    setTimeout(() => {
      document.body.removeChild(a);
      URL.revokeObjectURL(url); // 释放内存
    }, 100);

    ElMessage.success(`开始下载: ${fileName}`);
  } catch (error) {
    console.error('下载图片出错:', error);
    ElMessage.error(`下载失败: ${error.message}`);
  }
};

// 下载全部图片（带延时防止浏览器阻止）
const downloadAllImages = async () => {
  try {
    await ElMessageBox.confirm(`确定要下载全部 ${imageUrls.value.length} 张图片吗？`, '确认下载', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    });

    // 分批下载（每300ms一个）
    imageUrls.value.forEach((url, index) => {
      setTimeout(() => downloadImage(url), index * 300);
    });

    ElMessage.success(`已开始下载 ${imageUrls.value.length} 张图片`);
  } catch (error) {
    if (error !== 'cancel') {
      ElMessage.info('已取消下载');
    }
  }
};

// 删除全部缓存
const deleteAllCache = async () => {
  try {
    await ElMessageBox.confirm('确定要删除全部缓存图片吗？', '警告', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'error'
    });

    deleting.value = true;
    const response = await deleteAllCacheApi();

    if (response.code === 200) {
      ElMessage.success('缓存删除成功');
      imageUrls.value = [];
    } else {
      ElMessage.error(response.message || '删除失败');
    }
  } catch (error) {
    if (error !== 'cancel') {
      ElMessage.error('删除过程中出错');
    }
  } finally {
    deleting.value = false;
  }
};

// 组件挂载时获取数据
onMounted(async () => {
  try {
    loading.value = true;
    const response = await getCacheImgApi();

    if (response.code === 200) {
      imageUrls.value = response.data || [];
    } else {
      ElMessage.error(response.message || '获取图片失败');
    }
  } catch (error) {
    ElMessage.error('网络错误，无法获取图片');
  } finally {
    loading.value = false;
  }
});

</script>
<style scoped>
.cache-img-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.action-buttons {
  display: flex;
  gap: 10px;
}

.json-input-area {
  margin: 20px 0;
}

.json-textarea {
  margin-bottom: 10px;
}

.submit-btn {
  width: 100%;
}

.image-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(240px, 1fr));
  gap: 20px;
}

.image-card {
  border: 1px solid #ebeef5;
  border-radius: 8px;
  overflow: hidden;
  transition: all 0.3s;
  display: flex;
  flex-direction: column;
}

.image-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.image-preview {
  width: 100%;
  height: 200px;
  background-color: #f8fafc;
}

.image-actions {
  padding: 10px;
  display: flex;
  justify-content: center;
  border-top: 1px solid #ebeef5;
}

.loading-overlay {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 200px;
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

.empty-state {
  margin-top: 50px;
}

.image-error {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100%;
  color: #909399;
}

.image-error .el-icon {
  font-size: 40px;
  margin-bottom: 10px;
}
</style>
