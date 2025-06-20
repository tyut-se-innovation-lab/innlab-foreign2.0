// src/utils/authorizationHttp.js
import axios from 'axios'; // 改为直接从 axios 库导入

const authorizationHttp = axios.create({
  baseURL: 'http://49.232.217.248:8087',
  // baseURL: 'http://localhost:8081',
  timeout: 10000,
});

// 请求拦截器
authorizationHttp.interceptors.request.use(config => {
  // 添加安全检查
  config.headers.Authorization = sessionStorage.token;
  return config;
}, error => {
  return Promise.reject(error);
});

// 响应拦截器
authorizationHttp.interceptors.response.use(response => {
  return response.data;
}, error => {
  // 错误处理逻辑
  return Promise.reject(error);
});

export default authorizationHttp;
