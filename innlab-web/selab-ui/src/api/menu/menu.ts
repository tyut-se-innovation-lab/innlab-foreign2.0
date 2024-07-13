import http from '../http'

const prefix = '/foreign'

//获取目录
export const getMenu = () => {
    return http.post(`${prefix}/directory`)
}

