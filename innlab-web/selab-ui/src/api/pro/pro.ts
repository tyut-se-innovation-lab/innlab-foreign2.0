import http from '../http'
import reqmd from '../reqmd'

const prefix = '/foreign'

//获取部门项目
export const getPros = (params: { pageNum: number; pageSize: number; department: string }) => {
    return http.post(`${prefix}/showItems`, {
        pageNum: params.pageNum,
        pageSize: params.pageSize,
        department: params.department
    })
}

//获取部门项目标题
export const getProTitles = (params: { pageNum: number; pageSize: number; department: string }) => {
    return http.post(`${prefix}/showItemsTitle`, {
        pageNum: params.pageNum,
        pageSize: params.pageSize,
        department: params.department
    })
}

//获取部门项目详情
export const getProInfo = (id: any) => {

    return http.parampost(`${prefix}/getOneItem`, {
        itemId: id
    })
}

export const getMd = (url: any) => {
    return reqmd.get(url)
}


