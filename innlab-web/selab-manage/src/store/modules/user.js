/**********************************
 * @Author: Ronnie Zhang
 * @LastEditor: Ronnie Zhang
 * @LastEditTime: 2023/12/05 21:25:59
 * @Email: zclzone@outlook.com
 * Copyright © 2023 Ronnie Zhang(大脸怪) | https://isme.top
 **********************************/

import { defineStore } from 'pinia'

export const useUserStore = defineStore('user', {
  state: () => ({
    userInfo: null,
    userRole:null
  }),
  getters: {
    userId() {
      return this.userInfo?.id
    },
    username() {
      return this.userInfo?.username
    },
    nickName() {
      return this.userInfo?.nickName
    },
    avatar() {
      return this.userInfo?.avatar
    },
    currentRole() {
      return this.userInfo?.currentRole || {}
    },
    roles() {
      return this.userInfo?.roles || [13]
    },
  },
  actions: {
    setUser(user) {
      this.userInfo = user
    },
    //设置角色
    setRole(roles){
      this.userRole = roles
    },
    resetUser() {
      this.$reset()
    },
  },
})
