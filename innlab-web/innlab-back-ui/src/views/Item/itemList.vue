<!--<template>-->
<!--  <div class="p-20">-->
<!--    &lt;!&ndash; 搜索和添加按钮容器 &ndash;&gt;-->
<!--    <div class="search-and-add-container">-->
<!--      <CustomSearch-->
<!--        class="search-box"-->
<!--        :searchConfig="searchConfig"-->
<!--        @updateQueryData="updateQueryData"-->
<!--      ></CustomSearch>-->
<!--      <el-button-->
<!--        type="primary"-->
<!--        class="add-button"-->
<!--        @click="handleAdd"-->
<!--      >-->
<!--        添加项目-->
<!--      </el-button>-->
<!--    </div>-->

<!--    <CustomTable-->
<!--      :tableConfig="tableConfig"-->
<!--      :tableData="tableData"-->
<!--      :total="total"-->
<!--      @updateQueryData="updateQueryData"-->
<!--      @handleEdit="handleEdit"-->
<!--      @handleDelete="handleDelete"-->
<!--      @handleStatusChange="handleStatusChange"-->
<!--    >-->
<!--      &lt;!&ndash; 封面图列的插槽 &ndash;&gt;-->
<!--      <template #headerImage="{ row }">-->
<!--        <el-image-->
<!--          style="width: 100px; height: 60px"-->
<!--          :src="row.headerImageUrl"-->
<!--          :preview-src-list="[row.headerImageUrl]"-->
<!--          fit="cover"-->
<!--          preview-teleported-->
<!--        />-->
<!--      </template>-->

<!--      &lt;!&ndash; 状态列的插槽 &ndash;&gt;-->
<!--      <template #status="{ row }">-->
<!--        <el-switch-->
<!--          v-model="row.state"-->
<!--          :active-value="true"-->
<!--          :inactive-value="false"-->
<!--          active-text="启用"-->
<!--          inactive-text="停用"-->
<!--          inline-prompt-->
<!--          style="&#45;&#45;el-switch-on-color: #13ce66; &#45;&#45;el-switch-off-color: #ff4949"-->
<!--          @change="handleStatusChange(row)"-->
<!--        />-->
<!--      </template>-->

<!--      &lt;!&ndash; 置顶列的插槽 &ndash;&gt;-->
<!--      <template #isTop="{ row }">-->
<!--        <el-switch-->
<!--          v-model="row.isTop"-->
<!--          :active-value="true"-->
<!--          :inactive-value="false"-->
<!--          active-text="置顶"-->
<!--          inactive-text="普通"-->
<!--          inline-prompt-->
<!--          style="&#45;&#45;el-switch-on-color: #409EFF; &#45;&#45;el-switch-off-color: #909399"-->
<!--          @change="handleTopChange(row)"-->
<!--        />-->
<!--      </template>-->

<!--      &lt;!&ndash; 操作列的插槽 &ndash;&gt;-->
<!--      <template #handle="{ row }">-->
<!--        <el-button type="primary" plain @click="handleEdit(row)">编辑</el-button>-->
<!--        <el-button type="primary" plain @click="handleContentEdit(row)">编辑内容</el-button>-->
<!--        <el-button type="danger" plain @click="handleDelete(row)">删除</el-button>-->
<!--      </template>-->
<!--    </CustomTable>-->

<!--    &lt;!&ndash; 编辑对话框 &ndash;&gt;-->
<!--    <el-dialog v-model="editVisible" title="编辑项目" width="800px">-->
<!--      <el-form :model="formData" label-width="100px" v-loading="loading">-->
<!--        <el-form-item label="部门" required>-->
<!--          <el-select v-model="formData.department" placeholder="请选择部门" style="width: 100%">-->
<!--            <el-option label="软件开发" value="软件开发" />-->
<!--            <el-option label="人工智能" value="人工智能" />-->
<!--            <el-option label="网络安全" value="网络安全" />-->
<!--            <el-option label="虚拟现实" value="虚拟现实" />-->
<!--            <el-option label="算法竞赛" value="算法竞赛" />-->
<!--          </el-select>-->
<!--        </el-form-item>-->

<!--        <el-form-item label="项目标题" required>-->
<!--          <el-input v-model="formData.itemTitle" />-->
<!--        </el-form-item>-->

<!--        <el-form-item label="项目简介" required>-->
<!--          <el-input-->
<!--            v-model="formData.itemIntroduction"-->
<!--            type="textarea"-->
<!--            :rows="6"-->
<!--            placeholder="请输入项目简介"-->
<!--          />-->
<!--        </el-form-item>-->

<!--        <el-form-item label="封面图">-->
<!--          <el-image-->
<!--            style="width: 200px; height: 120px; margin-right: 20px"-->
<!--            :src="formData.headerImageUrl"-->
<!--            fit="cover"-->
<!--            v-if="formData.headerImageUrl"-->
<!--          />-->
<!--          <el-upload-->
<!--            :auto-upload="false"-->
<!--            :show-file-list="false"-->
<!--            :on-change="handleHeaderImageChange"-->
<!--          >-->
<!--            <el-button type="primary">{{ formData.headerImageUrl ? '更换封面' : '上传封面' }}</el-button>-->
<!--          </el-upload>-->
<!--        </el-form-item>-->
<!--      </el-form>-->

<!--      <template #footer>-->
<!--        <el-button @click="editVisible = false">取消</el-button>-->
<!--        <el-button type="primary" @click="submitForm">保存</el-button>-->
<!--      </template>-->
<!--    </el-dialog>-->

<!--    &lt;!&ndash; 添加对话框 &ndash;&gt;-->
<!--    <el-dialog v-model="addVisible" title="添加项目" width="800px">-->
<!--      <el-form :model="addFormData" label-width="100px" v-loading="loading">-->
<!--        <el-form-item label="部门" required>-->
<!--          <el-select v-model="addFormData.department" placeholder="请选择部门" style="width: 100%">-->
<!--            <el-option label="软件开发" value="软件开发" />-->
<!--            <el-option label="人工智能" value="人工智能" />-->
<!--            <el-option label="网络安全" value="网络安全" />-->
<!--            <el-option label="虚拟现实" value="虚拟现实" />-->
<!--            <el-option label="算法竞赛" value="算法竞赛" />-->
<!--          </el-select>-->
<!--        </el-form-item>-->

<!--        <el-form-item label="项目标题" required>-->
<!--          <el-input v-model="addFormData.itemTitle" />-->
<!--        </el-form-item>-->

<!--        <el-form-item label="项目简介" required>-->
<!--          <el-input-->
<!--            v-model="addFormData.itemIntroduction"-->
<!--            type="textarea"-->
<!--            :rows="6"-->
<!--            placeholder="请输入项目简介"-->
<!--          />-->
<!--        </el-form-item>-->

<!--        <el-form-item label="封面图">-->
<!--          <el-image-->
<!--            style="width: 200px; height: 120px; margin-right: 20px"-->
<!--            :src="addFormData.headerImageUrl"-->
<!--            fit="cover"-->
<!--            v-if="addFormData.headerImageUrl"-->
<!--          />-->
<!--          <el-upload-->
<!--            :auto-upload="false"-->
<!--            :show-file-list="false"-->
<!--            :on-change="handleAddHeaderImageChange"-->
<!--          >-->
<!--            <el-button type="primary">{{ addFormData.headerImageUrl ? '更换封面' : '上传封面' }}</el-button>-->
<!--          </el-upload>-->
<!--        </el-form-item>-->
<!--      </el-form>-->

<!--      <template #footer>-->
<!--        <el-button @click="addVisible = false">取消</el-button>-->
<!--        <el-button type="primary" @click="submitAddForm">确认添加</el-button>-->
<!--      </template>-->
<!--    </el-dialog>-->
<!--  </div>-->
<!--</template>-->

<!--<script setup>-->
<!--import CustomTable from "@/components/CustomTable/index.vue";-->
<!--import CustomSearch from "@/components/CustomSearch/index.vue";-->
<!--import { ref, onMounted } from "vue";-->
<!--import { tableConfig, searchConfig } from "./itemListConfig.js";-->
<!--import {-->
<!--  getItemListApi,-->
<!--  deleteItemApi,-->
<!--  updateItemApi,-->
<!--  addItemApi-->
<!--} from "@/api/itemApi";-->
<!--import {ElMessage, ElMessageBox} from "element-plus";-->
<!--import { addImageApi, getResourceByLzApi } from "@/api/resourceApi.js";-->
<!--import router from "@/router"-->

<!--// 表格数据-->
<!--const tableData = ref([]);-->
<!--const total = ref(0);-->
<!--const queryParams = ref({-->
<!--  pageNum: 1,-->
<!--  pageSize: 10,-->
<!--  department: null-->
<!--});-->

<!--// 编辑对话框-->
<!--const editVisible = ref(false);-->
<!--const formData = ref({-->
<!--  itemId: null,-->
<!--  department: null,-->
<!--  itemTitle: null,-->
<!--  itemIntroduction: null,-->
<!--  headerImage: null,-->
<!--  state: true,-->
<!--  isTop: false,-->
<!--  headerImageUrl: ""-->
<!--});-->

<!--// 添加对话框-->
<!--const addVisible = ref(false);-->
<!--const addFormData = ref({-->
<!--  department: null,-->
<!--  itemTitle: null,-->
<!--  itemIntroduction: null,-->
<!--  headerImage: null,-->
<!--  headerImageUrl: ""-->
<!--});-->

<!--const loading = ref(false);-->

<!--// 获取封面图URL-->
<!--const getHeaderImageUrl = async (headerImage) => {-->
<!--  if (!headerImage) return '';-->

<!--  if (headerImage.url) {-->
<!--    return headerImage.url;-->
<!--  }-->

<!--  try {-->
<!--    const res = await getResourceByLzApi({-->
<!--      pwd: headerImage.pwd,-->
<!--      isNewd: headerImage.isNewd,-->
<!--      fid: headerImage.fid-->
<!--    });-->

<!--    if (res.code === 200) {-->
<!--      return res.data;-->
<!--    }-->
<!--  } catch (error) {-->
<!--    console.error('获取封面图失败:', error);-->
<!--  }-->

<!--  return '';-->
<!--};-->

<!--// 获取项目列表-->
<!--const fetchItemList = async () => {-->
<!--  try {-->
<!--    loading.value = true;-->
<!--    const res = await getItemListApi(queryParams.value);-->
<!--    if (res.code === 200) {-->
<!--      const recordsWithImage = await Promise.all(res.data.records.map(async (item) => {-->
<!--        const headerImageUrl = await getHeaderImageUrl(item.headerImage);-->
<!--        return {-->
<!--          ...item,-->
<!--          headerImageUrl: headerImageUrl-->
<!--        };-->
<!--      }));-->
<!--      tableData.value = recordsWithImage;-->
<!--      total.value = res.data.total;-->
<!--    } else {-->
<!--      ElMessage.error(res.message || '获取项目列表失败');-->
<!--    }-->
<!--  } catch (error) {-->
<!--    console.error('获取项目列表出错:', error);-->
<!--    ElMessage.error('获取项目列表失败，请稍后重试');-->
<!--  } finally {-->
<!--    loading.value = false;-->
<!--  }-->
<!--};-->

<!--// 更新查询参数并重新加载数据-->
<!--const updateQueryData = (params, shouldFetch = true) => {-->
<!--  if (params.pageNum !== undefined) {-->
<!--    queryParams.value.pageNum = params.pageNum;-->
<!--  }-->
<!--  if (params.pageSize !== undefined) {-->
<!--    queryParams.value.pageSize = params.pageSize;-->
<!--  }-->
<!--  if (params.department !== undefined) {-->
<!--    queryParams.value.department = params.department;-->
<!--  }-->
<!--  Object.assign(queryParams.value, params);-->
<!--  if (shouldFetch) {-->
<!--    fetchItemList();-->
<!--  }-->
<!--};-->

<!--// 编辑项目-->
<!--const handleEdit = async (row) => {-->
<!--  const headerImageUrl = await getHeaderImageUrl(row.headerImage);-->
<!--  formData.value = {-->
<!--    itemId: row.itemId,-->
<!--    department: row.department,-->
<!--    itemTitle: row.itemTitle,-->
<!--    itemIntroduction: row.itemIntroduction,-->
<!--    headerImage: row.headerImage,-->
<!--    state: row.state,-->
<!--    isTop: row.isTop,-->
<!--    headerImageUrl: headerImageUrl-->
<!--  };-->
<!--  editVisible.value = true;-->
<!--};-->

<!--// 删除项目-->
<!--const handleDelete = async (row) => {-->
<!--  try {-->
<!--    await ElMessageBox.confirm(-->
<!--      `确定要删除【${row.itemTitle}】吗？此操作不可恢复！`,-->
<!--      '删除确认',-->
<!--      {-->
<!--        confirmButtonText: '确定',-->
<!--        cancelButtonText: '取消',-->
<!--        type: 'warning'-->
<!--      }-->
<!--    );-->
<!--    loading.value = true;-->
<!--    const res = await deleteItemApi(row.itemId);-->
<!--    if (res.code === 200) {-->
<!--      ElMessage.success('删除成功');-->
<!--      fetchItemList();-->
<!--    } else {-->
<!--      ElMessage.error(res.message || '删除失败');-->
<!--    }-->
<!--  } catch (error) {-->
<!--    console.error('删除项目失败:', error);-->
<!--    ElMessage.error('删除失败');-->
<!--  } finally {-->
<!--    loading.value = false;-->
<!--  }-->
<!--};-->

<!--// 状态切换-->
<!--const handleStatusChange = async (row) => {-->
<!--  try {-->
<!--    loading.value = true;-->
<!--    const res = await updateItemApi({-->
<!--      itemId: row.itemId,-->
<!--      state: row.state-->
<!--    });-->
<!--    if (res.code === 200) {-->
<!--      ElMessage.success('状态更新成功');-->
<!--    } else {-->
<!--      row.state = !row.state;-->
<!--      ElMessage.error(res.message || '状态更新失败');-->
<!--    }-->
<!--  } catch (error) {-->
<!--    console.error('状态更新失败:', error);-->
<!--    row.state = !row.state;-->
<!--    ElMessage.error('状态更新失败');-->
<!--  } finally {-->
<!--    loading.value = false;-->
<!--  }-->
<!--};-->

<!--// 置顶切换-->
<!--const handleTopChange = async (row) => {-->
<!--  try {-->
<!--    loading.value = true;-->
<!--    const res = await updateItemApi({-->
<!--      itemId: row.itemId,-->
<!--      isTop: row.isTop-->
<!--    });-->
<!--    if (res.code === 200) {-->
<!--      ElMessage.success('置顶状态更新成功');-->
<!--    } else {-->
<!--      row.isTop = !row.isTop;-->
<!--      ElMessage.error(res.message || '置顶状态更新失败');-->
<!--    }-->
<!--  } catch (error) {-->
<!--    console.error('置顶状态更新失败:', error);-->
<!--    row.isTop = !row.isTop;-->
<!--    ElMessage.error('置顶状态更新失败');-->
<!--  } finally {-->
<!--    loading.value = false;-->
<!--  }-->
<!--};-->

<!--// 封面图上传处理-->
<!--const handleHeaderImageChange = async (file) => {-->
<!--  const localUrl = URL.createObjectURL(file.raw);-->
<!--  formData.value.headerImageUrl = localUrl;-->

<!--  try {-->
<!--    loading.value = true;-->
<!--    const res = await addImageApi(file.raw, '项目封面图');-->
<!--    if (res.code === 200) {-->
<!--      formData.value.headerImage = res.data.resourceId;-->
<!--      const newImageUrl = await getHeaderImageUrl(res.data.lz);-->
<!--      formData.value.headerImageUrl = newImageUrl;-->
<!--      ElMessage.success('封面图上传成功');-->
<!--    } else {-->
<!--      ElMessage.error(res.message || '上传封面图失败');-->
<!--      formData.value.headerImageUrl = '';-->
<!--    }-->
<!--  } catch (error) {-->
<!--    console.error('上传封面图失败:', error);-->
<!--    ElMessage.error('上传封面图失败');-->
<!--    formData.value.headerImageUrl = '';-->
<!--  } finally {-->
<!--    loading.value = false;-->
<!--  }-->
<!--};-->

<!--// 提交表单（编辑）-->
<!--const submitForm = async () => {-->
<!--  try {-->
<!--    loading.value = true;-->

<!--    // 验证必填字段-->
<!--    if (!formData.value.department || !formData.value.itemTitle || !formData.value.itemIntroduction) {-->
<!--      ElMessage.warning('请填写部门、项目标题和简介');-->
<!--      return;-->
<!--    }-->

<!--    const res = await updateItemApi({-->
<!--      itemId: formData.value.itemId,-->
<!--      department: formData.value.department,-->
<!--      itemTitle: formData.value.itemTitle,-->
<!--      itemIntroduction: formData.value.itemIntroduction,-->
<!--      headerImage: typeof formData.value.headerImage=== 'number'-->
<!--        ? formData.value.headerImage-->
<!--        : null,-->
<!--      state: formData.value.state,-->
<!--      isTop: formData.value.isTop-->
<!--    });-->
<!--    console.log(111,formData.value.headerImage=== 'number')-->
<!--    if (res.code === 200) {-->
<!--      ElMessage.success('保存成功');-->
<!--      editVisible.value = false;-->
<!--      await fetchItemList();-->
<!--    } else {-->
<!--      ElMessage.error(res.message || '保存失败');-->
<!--    }-->
<!--  } catch (error) {-->
<!--    console.error('保存失败:', error);-->
<!--    ElMessage.error('保存失败');-->
<!--  } finally {-->
<!--    loading.value = false;-->
<!--  }-->
<!--};-->

<!--// 添加项目按钮点击事件-->
<!--const handleAdd = () => {-->
<!--  addFormData.value = {-->
<!--    department: null,-->
<!--    itemTitle: null,-->
<!--    itemIntroduction: null,-->
<!--    headerImage: null,-->
<!--    headerImageUrl: ''-->
<!--  };-->
<!--  addVisible.value = true;-->
<!--};-->

<!--// 添加项目封面图上传处理-->
<!--const handleAddHeaderImageChange = async (file) => {-->
<!--  const localUrl = URL.createObjectURL(file.raw);-->
<!--  addFormData.value.headerImageUrl = localUrl;-->

<!--  try {-->
<!--    loading.value = true;-->
<!--    const res = await addImageApi(file.raw, '项目封面图');-->
<!--    if (res.code === 200) {-->
<!--      addFormData.value.headerImage = res.data.resourceId;-->
<!--      const newImageUrl = await getHeaderImageUrl(res.data.lz);-->
<!--      addFormData.value.headerImageUrl = newImageUrl;-->
<!--      ElMessage.success('封面图上传成功');-->
<!--    } else {-->
<!--      ElMessage.error(res.message || '上传封面图失败');-->
<!--      addFormData.value.headerImageUrl = '';-->
<!--    }-->
<!--  } catch (error) {-->
<!--    console.error('上传封面图失败:', error);-->
<!--    ElMessage.error('上传封面图失败');-->
<!--    addFormData.value.headerImageUrl = '';-->
<!--  } finally {-->
<!--    loading.value = false;-->
<!--  }-->
<!--};-->

<!--// 提交添加表单-->
<!--const submitAddForm = async () => {-->
<!--  try {-->
<!--    loading.value = true;-->

<!--    // 验证必填字段-->
<!--    if (!addFormData.value.department || !addFormData.value.itemTitle || !addFormData.value.itemIntroduction) {-->
<!--      ElMessage.warning('请填写部门、项目标题和简介');-->
<!--      return;-->
<!--    }-->

<!--    const res = await addItemApi({-->
<!--      department: addFormData.value.department,-->
<!--      itemTitle: addFormData.value.itemTitle,-->
<!--      itemIntroduction: addFormData.value.itemIntroduction,-->
<!--      headerImage: addFormData.value.headerImage-->
<!--    });-->

<!--    if (res.code === 200) {-->
<!--      ElMessage.success('添加成功');-->
<!--      addVisible.value = false;-->
<!--      fetchItemList();-->
<!--    } else {-->
<!--      ElMessage.error(res.message || '添加失败');-->
<!--    }-->
<!--  } catch (error) {-->
<!--    console.error('添加失败:', error);-->
<!--    ElMessage.error('添加失败');-->
<!--  } finally {-->
<!--    loading.value = false;-->
<!--  }-->
<!--};-->

<!--// 编辑内容跳转-->
<!--const handleContentEdit = (row) => {-->
<!--  console.log('跳转参数:', row.itemId) // 确认有值-->
<!--  router.push({-->
<!--    name: 'itemEditor',-->
<!--    params: {-->
<!--      itemId: row.itemId-->
<!--    }-->
<!--  });-->
<!--};-->

<!--// 组件挂载时加载数据-->
<!--onMounted(() => {-->
<!--  fetchItemList();-->
<!--});-->
<!--</script>-->

<!--<style scoped>-->
<!--.search-and-add-container {-->
<!--  display: flex;-->
<!--  justify-content: space-between;-->
<!--  align-items: center;-->
<!--  margin-bottom: 20px;-->
<!--}-->

<!--.search-box {-->
<!--  flex: 1;-->
<!--  max-width: calc(100% - 120px);-->
<!--}-->

<!--.add-button {-->
<!--  margin-left: 20px;-->
<!--  white-space: nowrap;-->
<!--}-->
<!--</style>-->
