import http from '../http'

const prefix = '/foreign'

//获取活动
export const getPros = (params: { pageNum: number; pageSize: number; department: string }) => {
    return http.post(`${prefix}/showItems`, {
        pageNum: params.pageNum,
        pageSize: params.pageSize,
        department: params.department
    })
}


//获取活动详情
export const getActInfo = (id: any) => {
    return http.post(`${prefix}/getOneActivity`, {
        "activityId": id
    })
}

