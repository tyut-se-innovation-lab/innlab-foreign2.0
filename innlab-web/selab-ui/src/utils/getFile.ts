import axios from 'axios'
const request = axios.create({
    baseURL: '/getfile',
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
    console.log('data: ', data, data.url);

    // 如果 url 不为空或空字符串，直接返回 url
    if (data.url && data.url !== '') {
        return data.url;
    }

    const { pwd, isNewd, fid } = data;

    try {
        // 进行 GET 请求获取页面内容
        const getUrl = `${isNewd}/${fid}`;
        const getResponse = await request.get(fid);
        console.log('getResponse', getResponse);



        // 使用正则表达式提取 url 的值
        const urlMatch = /url\s+:\s+'([^']+)'/.exec(getResponse);
        console.log('urlMatch', urlMatch);

        if (!urlMatch) {
            throw new Error('Failed to extract sign');
        }
        const fileurl = urlMatch[1];


        // 使用正则表达式提取 sign 的值
        const signMatch = /var skdklds = '([\w=]+)'/g.exec(getResponse);
        if (!signMatch) {
            throw new Error('Failed to extract sign');
        }
        const sign = signMatch[1];

        // 使用正则表达式提取 action 的值
        const actionMatch = /data\s*:\s*\{\s*'action'\s*:\s*'(\w+)'/g.exec(getResponse);
        if (!actionMatch) {
            throw new Error('Failed to extract action');
        }
        const action = actionMatch[1];

        // 使用正则表达式提取 kd 的值
        const kdMatch = /'kd'\s*:\s*(\d+)/g.exec(getResponse);
        const kd = kdMatch ? kdMatch[1] : '0'; // 默认为 '0'

        const postUrl = `/ajaxm.php?file=${fid}`;
        const postData = new URLSearchParams();
        postData.append('action', action);
        postData.append('sign', sign);
        postData.append('p', pwd);
        postData.append('kd', kd);

        const postResponse = await request.post<LanzouDownloadResponse>(postUrl, postData, {
            headers: {
                'Referer': getUrl,
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        });


        if (postResponse.zt == '1') {
            return `${postResponse.dom}/file/${postResponse.url}`;
        } else {
            throw new Error(postResponse.data.inf);
        }
    } catch (error) {
        console.error('Error parsing Lanzou link:', error);
        throw new Error('Failed to parse Lanzou link');
    }
};


