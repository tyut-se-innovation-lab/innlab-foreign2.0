/* 
用户管理api（好好写注释，方便自己和他人）
*/
import { request } from '@/utils'

export default {
  //创建
  create: (params = {}) => request.post('/background/adm/user/adduser',  params ),
  //读取
  read: (params = {}) => request.post('/background/adm/user/allinformation', { params },{headers:{'Content-Type':'application/json'}}),
  //更新
  update: (params) => request.post('/background/adm/user/downstate',params ),
  //删除
  delete: (params) => request.post('/background/adm/user/delete',params),
  //重置密码
  resetPwd: (id, data) => request.patch(`/user/password/reset/${id}`, data),
  //分配角色
  assignRoles: (id) => request.post(`/background/adm/role/byuserid`, id),
  //获取用户信息
  getUserInfo: (id) => request.post('/background/adm/user/information/byuserid', id),
  //全部角色
  getAllRoles: (params = {pageNum: 1,pageSize: 10}) => request.post('/background/adm/role/list',params),
  //停用用户
  downUserState:(params) => request.post('/background/adm/user/downstate', params),
  //启用用户
  upUserState:(params) => request.post('/background/adm/user/upstate', params),
  //重置密码
  resetPwd:(params) => request.post('/background/adm/login/firstresetpassword', params),
}
