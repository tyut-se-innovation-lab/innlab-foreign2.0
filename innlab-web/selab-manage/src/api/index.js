
import { request } from '@/utils'

export default {
  // 获取用户信息
  getUser: () => request.post('/background/adm/user/self/information',),
  //获取自己的角色
  getRole:() =>request.post('/background/adm/role/self',),
  // 刷新token
  refreshToken: () => request.get('/auth/refresh/token'),
  // 退出登录
  logout: () => request.post('/background/adm/logout'),
  // 切换当前角色
  switchCurrentRole: (role) => request.post(`/background/adm/role/${userId}`),
  // 获取角色权限
  getRolePermissions: () => request.post('/background/adm/directory/self'),
  // 验证菜单路径
  // validateMenuPath: (path) => request.get(`/permission/menu/validate?path=${path}`),
    //上传图片
    upPhoto: (formData) => request.post('/background/adm/resource/addImage',formData,{
      headers:{
          'Content-Type': 'multipart/form-data'
      }
    }),
    //获取资源
    getResource: (resourceId) => request.post('/background/adm/resource/getresource', {
      resourceId
    })
}
