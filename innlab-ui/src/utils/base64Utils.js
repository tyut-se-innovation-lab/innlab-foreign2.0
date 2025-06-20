// Base64 decode utility
// base64解码函数
export function decodeBase64(base64Str) {
    try {
        // 将 base64 字符串解码为字节数组
        const byteArray = Uint8Array.from(atob(base64Str), c => c.charCodeAt(0));
        const decoder = new TextDecoder('utf-8');

        // 将字节数组解码为字符串
        const decodedContent = decoder.decode(byteArray);
        console.log('decodedContent', decodedContent);
        return decodedContent;
    } catch (error) {
        console.log('decoderror: ', error);
        return base64Str; // 如果解码失败，返回原始字符串
    }
}

// Base64 encode utility
// 转换为base64编码函数
export function encodeBase64(str) {
    // 将字符串转换为 UTF-8 字节数组
    let utf8Bytes = new TextEncoder().encode(str);

    // 将字节数组转换为二进制字符串
    let binaryString = String.fromCharCode.apply(null, utf8Bytes);

    // 将二进制字符串转换为 base64 编码
    return btoa(binaryString);
}
