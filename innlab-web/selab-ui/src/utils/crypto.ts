// utils/crypto.ts
import CryptoJS from 'crypto-js';

// 16 字符的密钥和初始向量
const SECRET_KEY = CryptoJS.enc.Utf8.parse('1234567890123456');
const IV = CryptoJS.enc.Utf8.parse('1234567890123456');

export const encrypt = (text: string) => {
    const encrypted = CryptoJS.AES.encrypt(text, SECRET_KEY, {
        iv: IV,
        mode: CryptoJS.mode.CBC,
        padding: CryptoJS.pad.Pkcs7
    });
    return CryptoJS.enc.Base64.stringify(encrypted.ciphertext);
};

export const decrypt = (ciphertext: string) => {
    const encryptedHexStr = CryptoJS.enc.Base64.parse(ciphertext);
    const encryptedBase64Str = CryptoJS.enc.Base64.stringify(encryptedHexStr);
    const decrypted = CryptoJS.AES.decrypt(encryptedBase64Str, SECRET_KEY, {
        iv: IV,
        mode: CryptoJS.mode.CBC,
        padding: CryptoJS.pad.Pkcs7
    });
    return decrypted.toString(CryptoJS.enc.Utf8);
};
