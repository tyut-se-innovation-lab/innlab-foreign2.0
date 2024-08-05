<template>
    <div class="outer">
        <!-- 侧栏 -->
        <div class="btn openBtn" v-show="!isOpen" @click="isOpen = !isOpen">
            <svg t="1722590723780" class="icon" viewBox="0 0 1137 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
                p-id="5338" width="38" height="38">
                <path
                    d="M0 455.111111h1137.777778v113.777778H0V455.111111z m0-455.111111h1137.777778v113.777778H0V0z m0 910.222222h1137.777778v113.777778H0V910.222222z"
                    fill="#ffffff" p-id="5339"></path>
            </svg>
        </div>
        <!-- × -->
        <div class="btn closeBtn" v-show="isOpen" @click="isOpen = !isOpen">
            <svg t="1722590675315" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
                p-id="4305" width="38" height="38">
                <path
                    d="M1023.600998 91.107232 933.290773 0.797007 512 422.130673 90.709227 0.797007 0.399002 91.107232 421.732668 512.399002 0.399002 933.731671l90.310224 90.267332L512 602.709227l421.290773 421.290773 90.310224-90.267332L602.267332 512.399002 1023.600998 91.107232z"
                    fill="#ffffff" p-id="4306"></path>
            </svg>
        </div>

        <div class="sideouter" v-if="isOpen" data-aos="fade-left" data-aos-duration="600" data-aos-delay="100">

            <div class="sidebar">
                <div @click="scrollTo(1, '首页')">
                    <img lass="logo" width="200px" style="margin: 2% 0;" rc="../../assets/logo.png" alt="">

                </div>
                <el-col :span="12">
                    <el-menu active-text-color="#ffd04b" background-color="transparent" class="el-menu-vertical-demo"
                        default-active="2" text-color="#fff" @open="handleOpen" @close="handleClose">
                        <el-menu-item class="menuItem" index="1" @click="scrollTo(1, '首页')">
                            <span class="title">首页</span>
                        </el-menu-item>
                        <el-menu-item class="menuItem" index="2" @click="scrollTo(2, '组织介绍')">
                            <span class="title">组织介绍</span>
                        </el-menu-item>
                        <el-sub-menu index="3">
                            <template #title>
                                <span class="title">组织架构</span>
                            </template>
                            <el-menu-item index="2" @click="scrollTo(4, '软件开发')">
                                <el-icon><icon-menu /></el-icon>
                                <span class="text">软件开发</span>
                            </el-menu-item>
                            <el-menu-item index="2" @click="scrollTo(5, '网络安全')">
                                <el-icon><icon-menu /></el-icon>
                                <span class="text">网络安全</span>
                            </el-menu-item>
                            <el-menu-item index="2" @click="scrollTo(6, '人工智能')">
                                <el-icon><icon-menu /></el-icon>
                                <span class="text">人工智能</span>
                            </el-menu-item>
                            <el-menu-item index="2" @click="scrollTo(7, '虚拟现实')">
                                <el-icon><icon-menu /></el-icon>
                                <span class="text">虚拟现实</span>
                            </el-menu-item>
                            <el-menu-item index="2" @click="scrollTo(8, '算法竞赛')">
                                <el-icon><icon-menu /></el-icon>
                                <span class="text">算法竞赛</span>
                            </el-menu-item>
                        </el-sub-menu>
                        <el-menu-item class="menuItem" index="4" @click="scrollTo(9, '机构动态')">
                            <span class="title">机构动态</span>
                        </el-menu-item>
                        <el-menu-item class="menuItem" index="5" @click="scrollTo(10, '联系我们')">
                            <span class="title">联系我们</span>
                        </el-menu-item>
                    </el-menu>
                </el-col>
            </div>


        </div>
        <div :class="{ unover: !isOpen, over: isOpen }"></div>
    </div>
    <div class="mobileouter">
        <div class="head">
            <router-link class="logo" to="/">
                <img src="public/img/disImg/logo.png" alt="">
            </router-link>
            <!-- 侧栏 -->
            <div class="btn openBtn" v-show="!isOpen" @click="isOpen = !isOpen">
                <svg t="1722590723780" class="icon" viewBox="0 0 1137 1024" version="1.1"
                    xmlns="http://www.w3.org/2000/svg" p-id="5338" width="38" height="38">
                    <path
                        d="M0 455.111111h1137.777778v113.777778H0V455.111111z m0-455.111111h1137.777778v113.777778H0V0z m0 910.222222h1137.777778v113.777778H0V910.222222z"
                        fill="#ffffff" p-id="5339"></path>
                </svg>
            </div>
            <!-- × -->
            <div class="btn closeBtn" v-show="isOpen" @click="isOpen = !isOpen">
                <svg t="1722590675315" class="icon" viewBox="0 0 1024 1024" version="1.1"
                    xmlns="http://www.w3.org/2000/svg" p-id="4305" width="38" height="38">
                    <path
                        d="M1023.600998 91.107232 933.290773 0.797007 512 422.130673 90.709227 0.797007 0.399002 91.107232 421.732668 512.399002 0.399002 933.731671l90.310224 90.267332L512 602.709227l421.290773 421.290773 90.310224-90.267332L602.267332 512.399002 1023.600998 91.107232z"
                        fill="#ffffff" p-id="4306"></path>
                </svg>
            </div>
        </div>

        <div class="sideouter" v-if="isOpen" data-aos="fade-left" data-aos-duration="600" data-aos-delay="100">

            <div class="sidebar">
                <div @click="scrollTo(1, '首页')">
                    <img lass="logo" width="200px" style="margin: 2% 0;" rc="../../assets/logo.png" alt="">

                </div>
                <el-col :span="12">
                    <el-menu active-text-color="#ffd04b" background-color="transparent" class="el-menu-vertical-demo"
                        default-active="2" text-color="#fff" @open="handleOpen" @close="handleClose">
                        <el-menu-item class="menuItem" index="1" @click="scrollTo(1, '首页')">
                            <span class="title">首页</span>
                        </el-menu-item>
                        <el-menu-item class="menuItem" index="2" @click="scrollTo(2, '组织介绍')">
                            <span class="title">组织介绍</span>
                        </el-menu-item>
                        <el-sub-menu index="3">
                            <template #title>
                                <span class="title">组织架构</span>
                            </template>
                            <el-menu-item index="2" @click="scrollTo(4, '软件开发')">
                                <el-icon><icon-menu /></el-icon>
                                <span class="text">软件开发</span>
                            </el-menu-item>
                            <el-menu-item index="2" @click="scrollTo(5, '网络安全')">
                                <el-icon><icon-menu /></el-icon>
                                <span class="text">网络安全</span>
                            </el-menu-item>
                            <el-menu-item index="2" @click="scrollTo(6, '人工智能')">
                                <el-icon><icon-menu /></el-icon>
                                <span class="text">人工智能</span>
                            </el-menu-item>
                            <el-menu-item index="2" @click="scrollTo(7, '虚拟现实')">
                                <el-icon><icon-menu /></el-icon>
                                <span class="text">虚拟现实</span>
                            </el-menu-item>
                            <el-menu-item index="2" @click="scrollTo(8, '算法竞赛')">
                                <el-icon><icon-menu /></el-icon>
                                <span class="text">算法竞赛</span>
                            </el-menu-item>
                        </el-sub-menu>
                        <el-menu-item class="menuItem" index="4" @click="scrollTo(9, '机构动态')">
                            <span class="title">机构动态</span>
                        </el-menu-item>
                        <el-menu-item class="menuItem" index="5" @click="scrollTo(10, '联系我们')">
                            <span class="title">联系我们</span>
                        </el-menu-item>
                    </el-menu>
                </el-col>
            </div>


        </div>
        <div :class="{ unover: !isOpen, over: isOpen }"></div>
    </div>
</template>

<script lang="ts" setup>
import { ref, reactive, onMounted, nextTick } from "vue"
import { Document, Menu as IconMenu, Location, Setting, } from '@element-plus/icons-vue'
import { elementIdMap, menuList } from './menus'
import router from '@/router';
import AOS from 'aos'
import 'aos/dist/aos.css'

const isOpen = ref(false);


const handleOpen = (key: string, keyPath: string[]) => {
    console.log(key, keyPath)
}
const handleClose = (key: string, keyPath: string[]) => {
    console.log(key, keyPath)
}

const scrollTo = (elementId: number, elementName: string) => {
    const routeInfo = elementIdMap[elementId];

    if (!routeInfo.actId) {
        router.push({ path: routeInfo.path, query: { id: elementId, name: routeInfo.name } });
    } else {
        router.push({ path: routeInfo.path, query: { id: elementId, name: routeInfo.name, actid: routeInfo.actId } });
    }
    isOpen.value = false;
}

onMounted(() => {
    AOS.init();
});
</script>
<style scoped>
.mobileouter {
    display: none;
    width: 100%;
    z-index: 300;
    position: fixed;
    top: 0;
    right: 0;
}

.outer {
    width: 100%;
    z-index: 300;
    position: fixed;
    top: 0;
    right: 0;
}

.head {
    width: 100%;
    height: 4.3em;
    background-color: #002d72;
}

.logo {
    width: 15em;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
}

.logo img {
    width: auto;
    height: 80%;
}

.btn {
    width: 4.3em;
    height: 4.3em;
    background-color: #002d72;
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    z-index: 400;
    top: 0%;
    right: 0%;
    transition-delay: 0ms, 0ms, 0ms;
    transition-duration: .75s, 0ms, 375ms;
    transition-timing-function: cubic-bezier(.175, .885, .32, 1.14), linear, ease-in-out;
}

.btn:hover {
    background-color: #2b508a;
    cursor: pointer;
}

.unover {
    width: 100%;
    background-color: rgba(43, 80, 138, .9);
    position: fixed;
    top: 0;
    bottom: 0;
    right: 100%;
    z-index: 200;
    transition-delay: 0ms, 0ms, 0ms;
    transition-duration: .75s, 0ms, 375ms;
    transition-timing-function: cubic-bezier(.175, .885, .32, 1.14), linear, ease-in-out;
}

.over {
    background-color: rgba(43, 80, 138, .9);
    position: fixed;
    top: 0;
    bottom: 0;
    left: 0;
    right: 2em;
    z-index: 200;
    transition-delay: 0ms, 0ms, 0ms;
    transition-duration: .75s, 0ms, 375ms;
    transition-timing-function: cubic-bezier(.175, .885, .32, 1.14), linear, ease-in-out;
}

.sideouter {
    width: 30vw;
    height: 100vh;
    padding: 5em 4em;
    z-index: 300;
    position: fixed;
    top: 0;
    right: 0;
    background-color: #002d72;
    border: 1px solid rgba(100, 103, 105, 0.1);
    box-shadow: 10px 10px 20px rgba(69, 85, 103, 0.1);
    overflow-y: scroll;
}


.sidebar {
    width: 100%;
    height: 100%;
    z-index: 300;
}

:deep(.el-col-12) {
    width: 100%;
    height: 100%;
    max-width: 100%;
}

:deep(.el-menu) {
    border: none;
}

:deep(.el-sub-menu) {
    padding: .5% 0;
    margin-left: 0;
    border-bottom: solid #506f87;
    border-width: 0 0 .125em;
}

:deep(.el-menu-item) {
    padding: 8% 0;
    margin-left: 10%;
    padding-left: 0;
    border-top: .125em solid #506f87;
}

:deep(.menuItem) {
    padding: .5% 0;
    margin-left: 0 !important;
    border-bottom: solid #506f87;
    border-width: 0 0 .125em;
}


:deep(.title) {
    color: white;
    font-size: 1.7em;
    font-weight: 600;
    letter-spacing: .4em;
    margin: 0 1%;
}

:deep(.text) {
    color: #AAB9D0;
    font-size: 1.5em;
    font-weight: 600;
    letter-spacing: .4em;
    margin: 0 1%;
}

/* 移动端适配 */
@media (max-width: 1248px) {
    .mobileouter {
        display: block;
    }

    .sideouter {
        width: 100vw;
        height: 100vh;
        padding: 5em 4em;
        z-index: 300;
        position: fixed;
        top: 0;
        left: 0;
        background-color: #002d72;
        border: 1px solid rgba(100, 103, 105, 0.1);
        box-shadow: 10px 10px 20px rgba(69, 85, 103, 0.1);
    }

    :deep(.el-menu-item) {
        padding: 6% 0;
        margin-left: 10%;
        padding-left: 0;
        border-top: .125em solid #506f87;
    }

    .btn {
        width: 4.3em;
        height: 4.3em;

        top: 0%;
        right: 3%;

    }
}
</style>