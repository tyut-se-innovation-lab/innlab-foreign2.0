<template>
    <div>
        <header class="header" :class="{ '': isAtTop, headOuter: !isAtTop }">
            <router-link to="/"> <img class="logo" width="200px" src="../../assets/logo.png" alt=""></router-link>

            <div class="social-media">
                <!-- <a href="#"><i class="bx bxl-twitter"></i></a>
                <a href="#"><i class="bx bxl-facebook"></i></a>
                <a href="#"><i class="bx bxl-linkedin"></i></a>
                <a href="#"><i class="bx bxl-tiktok"></i></a> -->
            </div>

            <nav class="navbar">
                <el-dropdown size="large" class="menus" v-for="item in menuList" :key="item.id">
                    <span @click="scrollTo(item.id, item.name)" class="el-dropdown-link"
                        style="color: white; padding: 10px;">
                        {{ item.name }}
                    </span>
                    <template #dropdown v-if="item.children && item.children.length > 0">
                        <el-dropdown-menu>
                            <el-dropdown-item v-for=" child in item.children " :key="child.id"
                                @click="scrollTo(child.id, child.name)"
                                style="width: 180px; height: 60px; display: flex; justify-content: center;">{{
                                    child.name
                                }}

                            </el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>

            </nav>
        </header>
    </div>
</template>

<script lang="ts" setup>
import { onMounted, onUnmounted, ref } from 'vue'
import { elementIdMap, menuList } from './menus'

import router from '@/router';
import bus from '@/eventBus';

// 实现滚动定位
const isAtTop = ref(true);
const handleScroll = () => {
    isAtTop.value = window.scrollY === 0;
};


const scrollTo = (elementId: number, elementName: string) => {
    const routeInfo = elementIdMap[elementId];
    if (routeInfo.path !== '/') {
        router.push({ path: routeInfo.path, query: { id: elementId, name: routeInfo.name } });
    } else {
        router.push({ path: routeInfo.path, query: { id: elementId, name: routeInfo.name } });
        bus.emit('loading', true);

        setTimeout(function () {
            window.location.reload();
        }, 100);

    }

    const element = document.getElementById(String(elementId));

    if (element) {
        element.scrollIntoView({ behavior: 'smooth' });
        return true; // 继续默认行为
    }
    return false; // 阻止默认行为
}

// 获取目录




onMounted(() => {
    window.addEventListener("scroll", handleScroll);

});
onUnmounted(() => {
    window.removeEventListener("scroll", handleScroll);
});


</script>

<style scoped>
.flex-grow {
    flex-grow: 1;
}

.outer {
    width: 100%;
    background-color: transparent;
    z-index: 100;
}

.header {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    padding: 30px 5%;
    background: #4d7190;
    display: flex;
    align-items: center;
    z-index: 100;
}

.headOuter {
    width: 100%;
    cursor: pointer;
    transition: all .5s ease-in-out;
    color: white;
    /* background-color: rgba(255, 255, 255, 0.3); */
    background-color: #4d7190;
    /* background-color: rgb(39, 153, 232); */
    position: fixed;
    z-index: 100;
    /* box-shadow: 0 0 10px 10px rgba(255, 255, 255, 0.3); */
    /* box-shadow: 0 0 20px 20px transparent; */
}

.headOuter:hover {
    background-color: rgb(31, 144, 224);
    box-shadow: none;
}

.logo {
    cursor: pointer;
    font-size: 30px;
    color: #fff;
    text-decoration: none;
    font-weight: 700;
}

.social-media {
    margin: 0 auto 0 50px;
}

.social-media a {
    display: inline-flex;
    justify-content: center;
    align-items: center;
    width: 40px;
    height: 40px;
    background: transparent;
    border: 2px solid #fff;
    border-radius: 6px;
    text-decoration: none;
    margin-right: 10px;
    transition: all .3s linear;
}

.social-media a:hover {
    background: #fff;
}

.social-media a i {
    font-size: 20px;
    color: #fff;
    transition: all .3s linear;
}

.social-media a:hover i {
    color: #2d2b2b;
}

.navbar {
    display: flex;
    border: none !important;
}

.navbar .menus {
    font-size: 18px;
    color: #fff;
    text-decoration: none;
    font-weight: 500;
    margin-left: 30px;
    text-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
    display: flex;
    padding: 10px;
}

.example-showcase .el-dropdown-link {
    cursor: pointer;
    color: var(--el-color-primary);
    display: flex;
    align-items: center;
}

.el-dropdown-link:focus {
    outline: none;
}
</style>