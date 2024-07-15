import http from '../http'

const prefix = '/foreign'

//获取活动
export const getActivities = () => {
    return http.post(`${prefix}/showActivitys`, {
        "pageNum": 1,
        "pageSize": 10
    })
}

//获取活动
export const getActs = (params: { pageNum: number; pageSize: number; activityType: string }) => {
    return http.post(`${prefix}/showActivitys`, {
        pageNum: params.pageNum,
        pageSize: params.pageSize,
        activityType: params.activityType
    })
}


//获取活动详情
export const getActInfo = (id: any) => {
    return http.post(`${prefix}/getOneActivity`, {
        "activityId": id
    })
}

