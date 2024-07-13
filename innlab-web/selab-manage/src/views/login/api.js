
import { request } from '@/utils'

// export default {
//   toggleRole: (data) => request.post('/auth/role/toggle', data),
//   login: (data) => request.post('/auth/login', data, { noNeedToken: true }),
//   getUser: () => request.get('/user/detail'),
// }
export default{
    reLogin:(data) =>request.post('background/adm/login/before',data,{noNeedToken: true }),
    login:(data) => request.post('/background/adm/login',data,{noNeedToken: true }),
    getUsers:(data) =>request.post('/background/adm/user/self/information',data),

}
