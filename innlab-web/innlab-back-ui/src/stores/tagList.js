import { nextTick, reactive, ref } from 'vue'
import { defineStore } from 'pinia'
import { useRoute } from 'vue-router'



export const useTagStore = defineStore('tagList', () => {
  const route = useRoute()
  const alive = ref(true)
  const tagList = reactive([
    {
      name: '/dashboard',
      breadcrumbName: '首页'
    }
  ])
  if (route.path != '/dashboard') {
    tagList.push({
      name: route.path,
      breadcrumbName: route.meta.breadcrumbName
    })
  }
  function addTagList(obj) {
    const object = tagList.find(item => item.name === obj.name);
    // 如果不存在，则添加新项
    if (!object) {
      tagList.push(obj);
    }
  }

  function deleteTagList(index) {
    tagList.splice(index, 1)

  }

  function closeTagOther(list) {
    tagList.length = 1
    if(list.name !== '/dashboard') {
      tagList.push(list)
    }
  }

  function closeAllTag() {
    tagList.length = 1
  }

  function reload() {
    alive.value = false
    nextTick(() => {
      alive.value = true
    })
  }

  return { addTagList, deleteTagList, closeTagOther, closeAllTag, tagList, alive, reload }
})
