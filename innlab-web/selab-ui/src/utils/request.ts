import axios from 'axios'

const request = axios.create({
    // baseURL: 'http://192.168.1.134:8081',
    // baseURL: 'http://127.0.0.1:8081',
    baseURL: 'http://49.232.217.248:8081',
    timeout: 2000
})

// 请求拦截器
request.interceptors.request.use(config => {
    return config
})
// 响应拦截器
request.interceptors.response.use(res => {
    console.log("响应拦截res：", res)
    //错误码的处理
    let { code, message } = res.data;
    if (code !== 200) {
        console.log(message);

    }
    return res.data
}, error => {
    return Promise.reject(error)
})

export default request