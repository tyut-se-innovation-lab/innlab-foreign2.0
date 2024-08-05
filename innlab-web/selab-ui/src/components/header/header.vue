<template>
    <div class="heading">

        <header class="header" :class="{ 'header': isAtTop, 'headUnTop': !isAtTop || isBlue }">
            <div @click="scrollTo(1, '首页')">
                <img lass="logo" width="200px" src="../../assets/logo.png" alt="">
                <!-- <img v-show="!props.islogoWhite" lass="logo" width="200px" src="../../assets/logo/logo_blue.png" alt=""> -->
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
        <!-- <div class="crumb" v-show="currentPath !== '' && currentPath !== 'home'">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/' }" style="color: white !important;">返回</el-breadcrumb-item>
                <el-breadcrumb-item>
                    promotion management
                </el-breadcrumb-item>

            </el-breadcrumb>
        </div> -->

    </div>

</template>

<script lang="ts" setup>
import { onMounted, onUnmounted, ref, defineProps } from 'vue'
import { elementIdMap, menuList } from './menus'
import router from '@/router';
import bus from '@/eventBus';
import { useRouter } from 'vue-router';
const route = useRouter();

const props = defineProps<{
    // islogoWhite: boolean;
}>();
const isBlue = ref(false);
const currentPath = ref('')

// 实现滚动定位
const isAtTop = ref(true);
const handleScroll = () => {
    isAtTop.value = window.scrollY === 0;
};

const scrollTo = (elementId: number, elementName: string) => {
    const routeInfo = elementIdMap[elementId];

    if (!routeInfo.actId) {
        router.push({ path: routeInfo.path, query: { id: elementId, name: routeInfo.name } });
    } else {
        router.push({ path: routeInfo.path, query: { id: elementId, name: routeInfo.name, actid: routeInfo.actId } });
    }
    // bus.emit('loading', true);

    // setTimeout(function () {
    //     window.location.reload();
    // }, 100);
}




onMounted(() => {
    window.addEventListener("scroll", handleScroll);

    currentPath.value = route.currentRoute.value.name;
    if (route.currentRoute.value.name == 'about' || route.currentRoute.value.name == 'activity' || route.currentRoute.value.name == 'blog' || route.currentRoute.value.name == 'proMd') {
        isBlue.value = true;
    }
});
onUnmounted(() => {
    window.removeEventListener("scroll", handleScroll);
});


</script>

<style scoped>
.heading {}

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
    padding: 18px 5%;
    /* background: rgba(51, 183, 231, .2); */
    display: flex;
    align-items: center;
    z-index: 100;
    transition: all .6s;
}

.headUnTop {
    background-color: #002d72;
}

.headOuter {
    width: 100%;
    cursor: pointer;
    transition: all .5s ease-in-out;
    color: white;
    /* background-color: rgba(255, 255, 255, 0.3); */
    /* background-color: #4d7190; */
    /* background-color: #5cbdeb; */
    background-color: transparent;
    /* background-color: rgb(39, 153, 232); */
    position: fixed;
    z-index: 100;
    /* box-shadow: 0 0 10px 10px rgba(255, 255, 255, 0.3); */
    /* box-shadow: 0 0 20px 20px transparent; */
}

.headOuter:hover {
    /* background-color: rgb(31, 144, 224); */
    background-color: #02406f;
    box-shadow: none;
}

.logo {
    cursor: pointer;
    font-size: 30px;
    color: #fff;
    text-decoration: none;
    font-weight: 700;
}



.navbar {
    display: flex;
    border: none !important;
    margin-left: 40%;
    position: absolute;
    right: 6%;
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

/* crumb */
.crumb {
    position: absolute;
    top: 11%;
    left: 5%;
    color: white;
    /* min-height: 220px; */
}

:deep(.el-breadcrumb__item) {
    color: white !important;
    font-size: 20px;
}

.el-breadcrumb ::v-deep .el-breadcrumb__inner {
    color: white;
    font-weight: 500 !important;
}

:deep(.el-breadcrumb__item:last-child .el-breadcrumb__inner, .el-breadcrumb__item:last-child .el-breadcrumb__inner a, .el-breadcrumb__item:last-child .el-breadcrumb__inner a:hover, .el-breadcrumb__item:last-child .el-breadcrumb__inner:hover) {
    color: white !important;
    font-size: 20px;
}

:deep(.el-breadcrumb__inner a, .el-breadcrumb__inner.is-link) {
    color: #2467a7 !important;
    font-size: 20px;
}
</style>