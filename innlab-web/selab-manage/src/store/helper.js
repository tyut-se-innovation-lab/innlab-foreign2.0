import { basePermissions } from '@/settings'
import api from '@/api'
import { ITEM_RENDER_EVT } from 'element-plus/es/components/virtual-list/src/defaults';

export async function getUserInfo() {
  const res = await api.getUser()
  const role =res.data.role.join(',')
  const { userId, userName,userAccount,userSex, userAvatar,userPhone,userEmail} = res.data || {}
  return {
    id:userId,
    username:userAccount,
    avatar: userAvatar,
    nickName: userName,
    gender: userSex,
    address: userPhone,
    email: userEmail,
    roles:role,
    currentRole:role,
  }
}

export async function getPermissions() {
  let asyncPermissions = []
  try {
    const res = await api.getRolePermissions()
    asyncPermissions = res?.data || []
    console.log(asyncPermissions);
  } catch (error) {
    console.error(error)
  }
  return basePermissions.concat(asyncPermissions)
}
