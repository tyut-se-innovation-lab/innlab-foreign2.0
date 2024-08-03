import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

// 评论组件
import UndrawUi from 'undraw-ui'
import 'undraw-ui/dist/style.css'


// md文档
// markdown编辑器
import VMdEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
import '@kangc/v-md-editor/lib/theme/style/github.css';
import hljs from 'highlight.js'; // highlightjs

// 页面滑动效果
// import { jarallax, jarallaxVideo } from "jarallax";
// import 'jarallax/dist/jarallax.min.css';
// Optional video extension
// jarallaxVideo();

// social-share
import vshare from 'vshare'

VMdEditor.use(githubTheme, {
    Hljs: hljs,
});

// markdown 预览 markdown解析需要的配置项目
import VMdPreview from '@kangc/v-md-editor/lib/preview';
// import '@kangc/v-md-editor/lib/style/preview.css'
// import githubTheme from '@kangc/v-md-editor/lib/theme/github';
import '@kangc/v-md-editor/lib/theme/style/github.css';
// import hljs from 'highlight.js';

VMdPreview.use(githubTheme, {
    Hljs: hljs,
});


const app = createApp(App)
app.use(ElementPlus)
app.use(UndrawUi)
app.use(createPinia())
app.use(router)
app.use(VMdEditor)
app.use(VMdPreview)
app.use(vshare)

app.mount('#app')
