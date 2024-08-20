import axios from 'axios'
const request = axios.create({
    baseURL: 'http://49.232.217.248:8087',
    timeout: 2000,
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


interface LanzouResponse {
    pwd: string;
    isNewd: string;
    url: string | null;
    fid: string;
}

interface LanzouDownloadResponse {
    dom: string;
    url: string;
    zt: string;
    inf: string;
}

export const parseLanzouLink = async (data: LanzouResponse): Promise<string> => {
    console.log('data: ', data);

    // 如果 url 不为空或空字符串，直接返回 url
    if (!data.pwd) {
        console.log('return data');

        return data;
    }
    if (data.url && data.url !== '') {
        console.log('return data.url');
        return data.url;
    }

    const { pwd, isNewd, fid } = data;

    try {
        // 进行 GET 请求获取页面内容
        const getUrl = `${isNewd}/${fid}`;
        const getResponse = await request.post(`/background/resource/getResourceByLz`, {
            "pwd": pwd,
            "isNewd": isNewd,
            "fid": fid
        });
        console.log('getResponse', getResponse);

        return `${getResponse.data}`;

    } catch (error) {
        console.error('Error parsing Lanzou link:', error);
        throw new Error('Failed to parse Lanzou link');
    }
};


