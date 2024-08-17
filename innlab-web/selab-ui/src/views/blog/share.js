// share.js

// url - 需要分享的页面地址（当前页面地址）
// title - 分享的标题（文章标题）

export function toQQzone(url, title) {
    url = encodeURIComponent(url)
    title = encodeURIComponent(title)
    window.open(`https://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url=${url}&title=${title}&desc=${title}&summary=${title}&site=${url}`)
}

export function toQQ(url, title) {
    url = encodeURIComponent(url)
    title = encodeURIComponent(title)

    window.open(`http://connect.qq.com/widget/shareqq/index.html?url=${url}&sharesource=qzone&title=${title}&source=${url}&desc=${title}`)
}

export function toWeibo(url, title) {
    url = encodeURIComponent(url)
    title = encodeURIComponent(title)
    window.open(`https://service.weibo.com/share/share.php?url=${url}&title=${title}&pic=&appkey=&sudaref=`)
}

export function toWechat(url, title) {
    url = encodeURIComponent(url)
    title = encodeURIComponent(title)
    // console.log(21, process.env.NUXT_ENV_APISERVER)
    window.open(`https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=${url}`);
    // window.open(`./components/share/wxShare.html?url=${url}&title=${title}`);
    // window.open(`${window.location.origin}/components/share/wxShare.html?url=${url}&title=${title}`);
}
