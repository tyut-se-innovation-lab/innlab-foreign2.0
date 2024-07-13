import http from '../http'

const prefix = '/foreign'

//获取活动
export const getHistoryList = () => {
    return http.post(`${prefix}/showHistory`, {
        "pageNum": 1,
        "pageSize": 10
    })
}

