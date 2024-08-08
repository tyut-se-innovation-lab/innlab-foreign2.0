import http from '../http'

const prefix = '/foreign'

//获取活动
// export const getPeoples = (params: { pageParam: { pageNum: number; pageSize: number }; personnelPeriod: string; personnelDepartment: string }) => {
//     return http.post(`${prefix}/personnel/byperiodanddepartment`, params)
// }

export const getPeoples = (params: { pageNum: number; pageSize: number; department: string; period: number }) => {
    return http.post(`${prefix}/showPersonnel`, params)
}
export const getAllPeoples = (params: { pageNum: number; pageSize: number; }) => {
    return http.post(`${prefix}/showPersonnel`, {
        pageNum: params.pageNum,
        pageSize: params.pageSize
    })
}

