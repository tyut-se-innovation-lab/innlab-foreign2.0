import http from '../http'

const prefix = '/foreign'

//获取活动
export const getActivities = () => {
    return http.post(`${prefix}/showActivities`, {
        "pageNum": 1,
        "pageSize": 10
    })
}

