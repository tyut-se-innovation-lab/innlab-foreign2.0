import http from '../http'
import reqmd from '../reqmd'

const prefix = '/foreign'

//获取活动
export const getActInfo = (id: any) => {
    return http.post(`${prefix}/getOneActivity`, {
        "activityId": id
    })
}

//获取部门活动详情
export const getPartActInfo = (id: any) => {
    console.log("##", id);

    return http.post(`${prefix}/getOneItem`, {
        itemId: id
    })
}

export const getMd = (url: any) => {
    return reqmd.get(url)
}
//获取活动评论
export const getComments = (id: any) => {
    return http.post(`${prefix}/showComments`, {
        "activityId": id
    })
}

