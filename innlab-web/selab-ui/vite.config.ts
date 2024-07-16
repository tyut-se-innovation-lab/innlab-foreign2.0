import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],
  server: {
    host: '127.0.0.1',//自定义主机名
    port: 5174,//自定义端口
    // host: '49.232.217.248',
    // port: 80,
    proxy: {
      '/reqmd': {
        target: 'https://down-load.lanrar.com',
        changeOrigin: true,
        rewrite: path => path.replace(/^\/reqmd/, ''),
      },
      '/getfile': {
        target: 'https://www.lanzouh.com',
        changeOrigin: true,
        rewrite: path => path.replace(/^\/getfile/, ''),
      },
    },
  },
  base: './',
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url)),
      'public': fileURLToPath(new URL('./public', import.meta.url)),
    }
  },
  build: {
    minify: false,
    // target:"es2020"
  }

}) 
