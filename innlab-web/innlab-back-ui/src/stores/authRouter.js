import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useAuthRouterStore = defineStore('authRouter', () => {
  const routerList = ref([])
  function addRouterList(list) {
    routerList.value = list
  }

  return { addRouterList, routerList }
})
