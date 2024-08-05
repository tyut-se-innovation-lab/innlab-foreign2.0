<template>
    <div class="outer">
        <!-- 头部 -->
        <div class="header">
            <Header></Header>
        </div>

        <nav class="navbar">
            <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal">
                <el-menu-item v-for="item in menuList" :index="item.id" @click="scrollTo(item.id)">{{ item.name
                    }}</el-menu-item>
            </el-menu>
        </nav>
        <main>
            <div class="item" id="50">
                <h1 class="item_title">支持与服务</h1>
                <div class="contents">
                    <div class="content">
                        <h2 class="content_title">云服务</h2>
                        <div class="content_text">
                            <p class="intitle">联系邮箱</p>
                            <p class="intext">云市场咨询：partner@selabcloud.com</p>
                        </div>
                    </div>
                    <div class="content">
                        <h2 class="content_title">云服务</h2>
                        <div class="content_text">
                            <p class="intitle">联系邮箱</p>
                            <p class="intext">云市场咨询：partner@selabcloud.com</p>
                        </div>
                    </div>
                    <div class="content">
                        <h2 class="content_title">云服务</h2>
                        <div class="content_text">
                            <p class="intitle">联系邮箱</p>
                            <p class="intext">云市场咨询：partner@selabcloud.com</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="item" id="51">
                <h1 class="item_title">商业合作</h1>
                <div class="contents">
                    <div class="content">
                        <h2 class="content_title">云服务</h2>
                        <div class="content_text">
                            <p class="intitle">联系邮箱</p>
                            <p class="intext">云市场咨询：partner@selabcloud.com</p>
                        </div>
                    </div>
                    <div class="content">
                        <h2 class="content_title">云服务</h2>
                        <div class="content_text">
                            <p class="intitle">联系邮箱</p>
                            <p class="intext">云市场咨询：partner@selabcloud.com</p>
                        </div>
                    </div>
                    <div class="content">
                        <h2 class="content_title">云服务</h2>
                        <div class="content_text">
                            <p class="intitle">联系邮箱</p>
                            <p class="intext">云市场咨询：partner@selabcloud.com</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="item" id="52">
                <h1 class="item_title">开发咨询</h1>
                <div class="contents">
                    <div class="content">
                        <h2 class="content_title">云服务</h2>
                        <div class="content_text">
                            <p class="intitle">联系邮箱</p>
                            <p class="intext">云市场咨询：partner@selabcloud.com</p>
                        </div>
                    </div>
                    <div class="content">
                        <h2 class="content_title">云服务</h2>
                        <div class="content_text">
                            <p class="intitle">联系邮箱</p>
                            <p class="intext">云市场咨询：partner@selabcloud.com</p>
                        </div>
                    </div>
                    <div class="content">
                        <h2 class="content_title">云服务</h2>
                        <div class="content_text">
                            <p class="intitle">联系邮箱</p>
                            <p class="intext">云市场咨询：partner@selabcloud.com</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="item" id="53" style="border: none;">
                <h1 class="item_title">联系我们</h1>
                <div class="contents">
                    <div class="content">
                        <h2 class="content_title">云服务</h2>
                        <div class="content_text">
                            <p class="intitle">联系邮箱</p>
                            <p class="intext">云市场咨询：partner@selabcloud.com</p>
                        </div>
                    </div>
                    <div class="content">
                        <h2 class="content_title">云服务</h2>
                        <div class="content_text">
                            <p class="intitle">联系邮箱</p>
                            <p class="intext">云市场咨询：partner@selabcloud.com</p>
                        </div>
                    </div>
                    <div class="content">
                        <h2 class="content_title">云服务</h2>
                        <div class="content_text">
                            <p class="intitle">联系邮箱</p>
                            <p class="intext">云市场咨询：partner@selabcloud.com</p>
                        </div>
                    </div>
                </div>
            </div>
        </main>



    </div>
</template>
<script setup lang="ts">
import { ref, reactive, onMounted } from "vue"
import { useRouter } from 'vue-router';
import Header from '@/components/header/header.vue'
import bus from '@/eventBus';

const route = useRouter();

const activeIndex = ref('50');
const menuList = [
    { id: 50, name: '支持与服务' },
    { id: 51, name: '商业合作' },
    { id: 52, name: '开发咨询' },
    { id: 53, name: '联系我们' },
]

// const scrollTo = (elementId: number) => {

//     let element = document.getElementById(String(elementId));

//     if (element) {

//         element.scrollIntoView({ behavior: 'smooth', block: 'start' });
//         return true; // 继续默认行为
//     }
//     return false; // 阻止默认行为
// }

const scrollTo = (elementId: number) => {
    let element = document.getElementById(String(elementId));
    let navbar = document.querySelector('.navbar');

    if (element && navbar) {
        let navbarHeight = navbar.getBoundingClientRect().height;

        // 滚动到元素顶部的位置减去 navbar 的高度
        let scrollToPosition = element.getBoundingClientRect().top - navbarHeight - 160;
        console.log('^', window.scrollY + scrollToPosition);


        window.scrollTo({
            top: window.scrollY + scrollToPosition,
            behavior: 'smooth'
        });

        return true; // 继续默认行为
    }
    return false; // 阻止默认行为
}



onMounted(() => {
    setTimeout(() => {
        bus.emit('loading', false);
    }, 200);

    window.scrollTo({ top: 0, behavior: 'smooth' });



})


</script>
<style scoped>
.outer {
    /* height: 1200px; */
    margin: 100px 0;
    padding-bottom: 320px;
}

.navbar {
    display: flex;
    border: none !important;

}

:deep(.el-menu) {
    width: 100%;
    margin: 20px;
    margin-top: 0;
    padding: 0 200px;
    display: flex;
    justify-content: space-evenly;
    position: fixed;
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

:deep(.el-menu--horizontal .el-menu-item:not(.is-disabled):focus, .el-menu--horizontal .el-menu-item:not(.is-disabled):hover) {
    background-color: none !important;
}

/* main */
main {
    width: 100%;
    padding: 60px 200px;

}

.item {
    margin-top: 60px;
    border-bottom: 3px solid rgb(209, 209, 209);
    padding-bottom: 80px;
}

.item_title {
    width: fit-content;
    border-bottom: 1px solid #4d7190;
    margin-bottom: 36px;
    padding-bottom: 2px;
}

.contents {
    display: flex;
    justify-content: space-between;
}

.content {
    width: 22%;
}

.content_title {
    /* width: fit-content; */
    border-bottom: 1px solid #c8d0d6;
    padding-bottom: 2px;
}

.intitle {
    font-weight: bold;
    font-size: 1.1em;
    margin-top: 25px;
    margin-bottom: 3px;
    line-height: 1.875em;
}

.intext {
    color: #666;
    font-size: 1em;
    line-height: 1.95em;
}

/* 移动端适配 */
@media (max-width: 1284px) {
    .header {
        display: none;
    }

    .navbar {
        display: none;
    }

    main {
        width: 100%;
        padding: 3em;
        padding-top: .1em;
    }

    .contents {
        display: flex;
        justify-content: space-between;
        flex-direction: column;
    }

    .content {
        width: 100%;
        margin: 2% 0;
    }
}
</style>
