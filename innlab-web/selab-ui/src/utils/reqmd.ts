import axios from 'axios'

const request = axios.create({
    // baseURL: 'http://192.168.1.134:8081',
    baseURL: '/reqmd',
    timeout: 2000,
    followRedirects: true, // 添加跟随重定向选项
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