import http from '../http'

const prefix = '/foreign'

//获取活动
export const getPros = (params: { current: number; size: number; branchId: number }) => {
    return http.parampost(`${prefix}/showItems`, {
        current: params.current,
        size: params.size,
        branchId: params.branchId
    })
}


//获取活动详情
export const getActInfo = (id: any) => {
    return http.post(`${prefix}/getOneActivity`, {
        "activityId": id
    })
}

