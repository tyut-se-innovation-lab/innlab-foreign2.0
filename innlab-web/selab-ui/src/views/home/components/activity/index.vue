<template>
    <div class="homeActivity">
        <div class="outer">
            <div class="container">

                <div class="selabAc">
                    <router-link to="/activity">
                        <div class="selabAc_title">机构动态</div>
                    </router-link>
                    <div class="selab">

                        <el-carousel @change="onCarouselChange" class="slide active" ref="carousel" interval="8000"
                            direction="vertical" motion-blur indicator-position="outside">
                            <el-carousel-item v-for="(item, index) in ActivitiesList" :key="index">
                                <div v-if="currentIndex == index" class="seAcBig seAcforce">
                                    <div class="seAcBigImg">
                                        <img ref="carouselImage" data-aos="fade-left" data-aos-duration="600"
                                            data-aos-delay="100" @click="linkTo(item.activityId)"
                                            :src="item.headerImage" alt="">
                                        <div class="seAcBigImgBorder"></div>
                                    </div>
                                    <div class="seAcBigText" @click="linkTo(item.activityId)">

                                        <h5 data-aos="fade-left" data-aos-duration="800" data-aos-delay="200">
                                            {{ item.activityTitle }}
                                        </h5>
                                        <span class="seAcBigDes" data-aos="fade-left" data-aos-duration="200"
                                            data-aos-delay="500">{{ item.activityIntroduction }}</span>
                                        <span class="seAcBigTime" data-aos="fade-left" data-aos-duration="200"
                                            data-aos-delay="500">{{ item.createTime }}</span>


                                    </div>
                                </div>
                            </el-carousel-item>
                        </el-carousel>


                    </div>
                    <div class="mobileselab">

                        <el-carousel @change="onCarouselChange" arrow="never" class="slide active" ref="carousel"
                            interval="8000" motion-blur indicator-position="outside">
                            <el-carousel-item v-for="(item, index) in ActivitiesList" :key="index">
                                <div v-if="currentIndex == index" class="seAcBig seAcforce">
                                    <div class="seAcBigImg">
                                        <img ref="carouselImage" data-aos="fade-left" data-aos-duration="600"
                                            data-aos-delay="100" @click="linkTo(item.activityId)"
                                            :src="item.headerImage" alt="">
                                        <div class="seAcBigImgBorder"></div>
                                    </div>
                                    <div class="seAcBigText" @click="linkTo(item.activityId)">

                                        <h5 data-aos="fade-left" data-aos-duration="800" data-aos-delay="200">
                                            {{ item.activityTitle }}
                                        </h5>
                                        <span class="seAcBigDes" data-aos="fade-left" data-aos-duration="200"
                                            data-aos-delay="500">{{ item.activityIntroduction }}</span>
                                        <span class="seAcBigTime" data-aos="fade-left" data-aos-duration="200"
                                            data-aos-delay="500">{{ item.createTime }}</span>


                                    </div>
                                </div>
                            </el-carousel-item>
                        </el-carousel>


                    </div>
                </div>

                <div class="main">
                    <div class="partAc_title">部门动态</div>
                    <div id="partsAc" class="row">
                        <div class="part">

                            <h2>软件开发</h2>
                            <p class="subtitle">Software development</p>
                            <ul class="style1">
                                <li v-for="item in rkPros"><span href="#" @click="linkProTo(item.itemId)">{{
                                    item.itemTitle }}</span></li>

                            </ul>
                            <router-link :to="{ path: '/part', query: { name: '软件开发' } }">
                                <button class="cta">
                                    <span class="hover-underline-animation"> Read More </span>
                                    <svg id="arrow-horizontal" xmlns="http://www.w3.org/2000/svg" width="30" height="10"
                                        viewBox="0 0 46 16">
                                        <path id="Path_10" data-name="Path 10"
                                            d="M8,0,6.545,1.455l5.506,5.506H-30V9.039H12.052L6.545,14.545,8,16l8-8Z"
                                            transform="translate(30)" style="fill: white;"></path>
                                    </svg>
                                </button>

                            </router-link>

                        </div>
                        <div class="part">

                            <h2>网络安全</h2>
                            <p class="subtitle">Cybersecurity</p>
                            <ul class="style1">
                                <li v-for="item in waPros"><span @click="linkProTo(item.itemId)">{{
                                    item.itemTitle }}</span></li>
                            </ul>
                            <router-link :to="{ path: '/part', query: { name: '网络安全' } }">
                                <button class="cta">
                                    <span class="hover-underline-animation"> Read More </span>
                                    <svg id="arrow-horizontal" xmlns="http://www.w3.org/2000/svg" width="30" height="10"
                                        viewBox="0 0 46 16">
                                        <path id="Path_10" data-name="Path 10"
                                            d="M8,0,6.545,1.455l5.506,5.506H-30V9.039H12.052L6.545,14.545,8,16l8-8Z"
                                            transform="translate(30)" style="fill: white;"></path>
                                    </svg>
                                </button>
                            </router-link>

                        </div>
                        <div class="part">

                            <h2>人工智能</h2>
                            <p class="subtitle">artificial intelligence</p>
                            <ul class="style1">
                                <li v-for="item in aiPros"><span href="#" @click="linkProTo(item.itemId)">{{
                                    item.itemTitle }}</span></li>
                            </ul>
                            <router-link :to="{ path: '/part', query: { name: '人工智能' } }">
                                <button class="cta">
                                    <span class="hover-underline-animation"> Read More </span>
                                    <svg id="arrow-horizontal" xmlns="http://www.w3.org/2000/svg" width="30" height="10"
                                        viewBox="0 0 46 16">
                                        <path id="Path_10" data-name="Path 10"
                                            d="M8,0,6.545,1.455l5.506,5.506H-30V9.039H12.052L6.545,14.545,8,16l8-8Z"
                                            transform="translate(30)" style="fill: white;"></path>
                                    </svg>
                                </button>
                            </router-link>

                        </div>
                        <div class="part">

                            <h2>虚拟现实</h2>
                            <p class="subtitle">virtual reality</p>
                            <ul class="style1">
                                <li v-for="item in vrPros"><span href="#" @click="linkProTo(item.itemId)">{{
                                    item.itemTitle }}</span></li>
                            </ul>
                            <router-link :to="{ path: '/part', query: { name: '虚拟现实' } }">
                                <button class="cta">
                                    <span class="hover-underline-animation"> Read More </span>
                                    <svg id="arrow-horizontal" xmlns="http://www.w3.org/2000/svg" width="30" height="10"
                                        viewBox="0 0 46 16">
                                        <path id="Path_10" data-name="Path 10"
                                            d="M8,0,6.545,1.455l5.506,5.506H-30V9.039H12.052L6.545,14.545,8,16l8-8Z"
                                            transform="translate(30)" style="fill: white;"></path>
                                    </svg>
                                </button>
                            </router-link>

                        </div>
                        <div class="part" style="margin-bottom: 6em;">

                            <h2>算法竞赛</h2>
                            <p class="subtitle">Algorithm competition</p>
                            <ul class="style1">
                                <li v-for="item in sfPros"><span href="#" @click="linkProTo(item.itemId)">{{
                                    item.itemTitle }}</span></li>
                            </ul>
                            <router-link :to="{ path: '/part', query: { name: '算法竞赛' } }">
                                <button class="cta">
                                    <span class="hover-underline-animation"> Read More </span>
                                    <svg id="arrow-horizontal" xmlns="http://www.w3.org/2000/svg" width="30" height="10"
                                        viewBox="0 0 46 16">
                                        <path id="Path_10" data-name="Path 10"
                                            d="M8,0,6.545,1.455l5.506,5.506H-30V9.039H12.052L6.545,14.545,8,16l8-8Z"
                                            transform="translate(30)" style="fill: white;"></path>
                                    </svg>
                                </button>
                            </router-link>

                        </div>
                    </div>


                </div>

            </div>
        </div>



    </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from "vue"
import { getActs } from '@/api/activity/activity'
import { getProTitles } from '@/api/pro/pro'
import { parseLanzouLink } from '@/utils/getFileByBackend';
import router from '@/router';
import bus from "@/eventBus";
import AOS from 'aos'
import 'aos/dist/aos.css'

const actParams = ref({
    pageNum: 1,
    pageSize: 8,
    // activityType: '热门'
})
const rkParams = ref({
    pageNum: 1,
    pageSize: 4,
    department: '软件开发'
})
const waParams = ref({
    pageNum: 1,
    pageSize: 4,
    department: '网络安全'
})
const aiParams = ref({
    pageNum: 1,
    pageSize: 4,
    department: '人工智能'
})
const vrParams = ref({
    pageNum: 1,
    pageSize: 4,
    department: '虚拟现实'
})
const sfParams = ref({
    pageNum: 1,
    pageSize: 4,
    department: '算法竞赛'
})

const ActivitiesList = ref<Array<{ activityId: number; activityTitle: string; createTime: string }>>([]);
const getActivitiesList = async () => {
    try {
        const result = await getActs(actParams.value);
        ActivitiesList.value = result.data.records;
        ActivitiesList.value.forEach(async e => {
            e.headerImage = await parseLanzouLink(e.headerImage);
        })

    } catch (error) {
        console.error('Error fetching data:');
    } finally {

    }
};

const rkPros = ref<Array<{ itemId: number; itemTitle: string; }>>([]);
const getRkPros = async () => {
    try {
        const result = await getProTitles(rkParams.value);
        rkPros.value = result.data.records;

    } catch (error) {
        console.error('Error fetching data:');
    } finally {

    }
};
const waPros = ref<Array<{ itemId: number; itemTitle: string; }>>([]);
const getWaPros = async () => {
    try {
        const result = await getProTitles(waParams.value);
        waPros.value = result.data.records;

    } catch (error) {
        console.error('Error fetching data:');
    } finally {

    }
};
const aiPros = ref<Array<{ itemId: number; itemTitle: string; }>>([]);
const getAiPros = async () => {
    try {
        const result = await getProTitles(aiParams.value);
        aiPros.value = result.data.records;

    } catch (error) {
        console.error('Error fetching data:');
    } finally {

    }
};
const vrPros = ref<Array<{ itemId: number; itemTitle: string; }>>([]);
const getVrPros = async () => {
    try {
        const result = await getProTitles(vrParams.value);
        vrPros.value = result.data.records;

    } catch (error) {
        console.error('Error fetching data:');
    } finally {

    }
};
const sfPros = ref<Array<{ itemId: number; itemTitle: string; }>>([]);
const getSfPros = async () => {
    try {
        const result = await getProTitles(sfParams.value);
        sfPros.value = result.data.records;

    } catch (error) {
        console.error('Error fetching data:');
    } finally {

    }
};

// 点击跳转
const linkTo = (item: number) => {
    router.push({ path: '/blog', query: { actid: item } });

}
// 部门点击跳转
const linkProTo = (item: number) => {
    router.push({ path: '/proMd', query: { id: item } });

}

const currentIndex = ref(0);
const onCarouselChange = (index: number) => {
    console.log('^%^: ', index);

    currentIndex.value = index;
    AOS.refreshHard(); // 强制刷新AOS，立即触发动画
};



onMounted(() => {
    getActivitiesList();
    getRkPros();
    getWaPros();
    getAiPros();
    getVrPros();
    getSfPros();
    AOS.init();
});

</script>

<style scoped>
.homeActivity {
    width: 100%;
    height: 100%;
    position: relative;
    background-image: url(https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/d906489631dca5ebd66e04bd524e24c0a4a7ac0443359556e72fc68a79eebebb60f34f8fe050c9c71645df6e72b6a7de?pictype=scale&from=30013&version=3.3.3.3&fname=actBg1.jpg&size=750);
    background-size: 500px 500px;
    background-color: #dddbd9;
    background-position: 50%;
    background-repeat: repeat;

}

.homeActivity:before {
    content: '';
    display: block;
    width: 100%;
    height: 7.8em;
    background-image: url(/public/img/disImg/torn-paper-wall.png);
    background-repeat: no-repeat;
    background-size: cover;
    position: absolute;
    top: -1.5%;
    left: 0;
    z-index: 1;
}

.homeActivity:after {
    content: '';
    display: block;
    width: 100%;
    height: 7.8em;
    background-image: url(public/img/disImg/torn-paper-wall.png);
    background-repeat: no-repeat;
    background-size: cover;
    position: absolute;
    bottom: -3.5%;
    left: 0;
    z-index: 1;
}



.outer {
    height: 100%;
    width: 100%;
    padding: 8%;
    display: flex;
    /* padding-left: 10%; */
    /* margin-top: 100px; */
    justify-content: center;
    align-items: center;
    background-image: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHN2ZyBpZD0iTGF5ZXJfMSIgZGF0YS1uYW1lPSJMYXllciAxIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB2aWV3Qm94PSIwIDAgMTgwMCAxMTAwIj4KICA8ZGVmcz4KICAgIDxzdHlsZT4KICAgICAgLmNscy0xIHsKICAgICAgICBmaWxsOiB1cmwoI2xpbmVhci1ncmFkaWVudC03KTsKICAgICAgICBvcGFjaXR5OiAuNjU7CiAgICAgIH0KCiAgICAgIC5jbHMtMSwgLmNscy0yLCAuY2xzLTMsIC5jbHMtNCwgLmNscy01LCAuY2xzLTYsIC5jbHMtNywgLmNscy04IHsKICAgICAgICBzdHJva2Utd2lkdGg6IDBweDsKICAgICAgfQoKICAgICAgLmNscy0yIHsKICAgICAgICBmaWxsOiB1cmwoI2xpbmVhci1ncmFkaWVudCk7CiAgICAgICAgb3BhY2l0eTogLjI7CiAgICAgIH0KCiAgICAgIC5jbHMtMyB7CiAgICAgICAgZmlsbDogbm9uZTsKICAgICAgfQoKICAgICAgLmNscy00IHsKICAgICAgICBmaWxsOiB1cmwoI2xpbmVhci1ncmFkaWVudC02KTsKICAgICAgICBvcGFjaXR5OiAuNjsKICAgICAgfQoKICAgICAgLmNscy01IHsKICAgICAgICBmaWxsOiB1cmwoI2xpbmVhci1ncmFkaWVudC0yKTsKICAgICAgICBvcGFjaXR5OiAuMzsKICAgICAgfQoKICAgICAgLmNscy02IHsKICAgICAgICBmaWxsOiB1cmwoI2xpbmVhci1ncmFkaWVudC01KTsKICAgICAgICBvcGFjaXR5OiAuNTU7CiAgICAgIH0KCiAgICAgIC5jbHMtNyB7CiAgICAgICAgZmlsbDogdXJsKCNsaW5lYXItZ3JhZGllbnQtNCk7CiAgICAgICAgb3BhY2l0eTogLjU7CiAgICAgIH0KCiAgICAgIC5jbHMtOCB7CiAgICAgICAgZmlsbDogdXJsKCNsaW5lYXItZ3JhZGllbnQtMyk7CiAgICAgICAgb3BhY2l0eTogLjQ7CiAgICAgIH0KICAgIDwvc3R5bGU+CiAgICA8bGluZWFyR3JhZGllbnQgaWQ9ImxpbmVhci1ncmFkaWVudCIgeDE9Ii0zNjAzLjA0IiB5MT0iNDQzMC40OCIgeDI9Ii0yMTMzLjM2IiB5Mj0iNDQzMC40OCIgZ3JhZGllbnRUcmFuc2Zvcm09InRyYW5zbGF0ZSgyMDg0LjM2IC00Mzk4Ljc3KSByb3RhdGUoLTIzLjM5KSIgZ3JhZGllbnRVbml0cz0idXNlclNwYWNlT25Vc2UiPgogICAgICA8c3RvcCBvZmZzZXQ9IjAiIHN0b3AtY29sb3I9IiM0NTkxZDAiLz4KICAgICAgPHN0b3Agb2Zmc2V0PSIxIiBzdG9wLWNvbG9yPSIjODFiOWU2Ii8+CiAgICA8L2xpbmVhckdyYWRpZW50PgogICAgPGxpbmVhckdyYWRpZW50IGlkPSJsaW5lYXItZ3JhZGllbnQtMiIgeDE9Ii0zNDk1LjcyIiB5MT0iNDQ1Mi45IiB4Mj0iLTIxODYuMzEiIHkyPSI0NDUyLjkiIGdyYWRpZW50VHJhbnNmb3JtPSJ0cmFuc2xhdGUoMjA4NC4zNiAtNDM5OC43Nykgcm90YXRlKC0yMy4zOSkiIGdyYWRpZW50VW5pdHM9InVzZXJTcGFjZU9uVXNlIj4KICAgICAgPHN0b3Agb2Zmc2V0PSIwIiBzdG9wLWNvbG9yPSIjMjM3N2JiIi8+CiAgICAgIDxzdG9wIG9mZnNldD0iMSIgc3RvcC1jb2xvcj0iIzQxOTVkYSIvPgogICAgPC9saW5lYXJHcmFkaWVudD4KICAgIDxsaW5lYXJHcmFkaWVudCBpZD0ibGluZWFyLWdyYWRpZW50LTMiIHgxPSItMzM5NC4xOCIgeTE9IjQ0ODEuOTYiIHgyPSItMjIzNi4wNiIgeTI9IjQ0ODEuOTYiIHhsaW5rOmhyZWY9IiNsaW5lYXItZ3JhZGllbnQtMiIvPgogICAgPGxpbmVhckdyYWRpZW50IGlkPSJsaW5lYXItZ3JhZGllbnQtNCIgeDE9Ii0zMjk3LjA1IiB5MT0iNDUyMC45NyIgeDI9Ii0yMjc5LjQ0IiB5Mj0iNDUyMC45NyIgZ3JhZGllbnRUcmFuc2Zvcm09InRyYW5zbGF0ZSgyMDg0LjM2IC00Mzk4Ljc3KSByb3RhdGUoLTIzLjM5KSIgZ3JhZGllbnRVbml0cz0idXNlclNwYWNlT25Vc2UiPgogICAgICA8c3RvcCBvZmZzZXQ9IjAiIHN0b3AtY29sb3I9IiMwMDVjYTYiLz4KICAgICAgPHN0b3Agb2Zmc2V0PSIxIiBzdG9wLWNvbG9yPSIjMDA3MmNlIi8+CiAgICA8L2xpbmVhckdyYWRpZW50PgogICAgPGxpbmVhckdyYWRpZW50IGlkPSJsaW5lYXItZ3JhZGllbnQtNSIgeDE9Ii0zMTAzLjY0IiB5MT0iNDUxNy4zIiB4Mj0iLTIzMTAuMTYiIHkyPSI0NTE3LjMiIGdyYWRpZW50VHJhbnNmb3JtPSJ0cmFuc2xhdGUoMjA4NC4zNiAtNDM5OC43Nykgcm90YXRlKC0yMy4zOSkiIGdyYWRpZW50VW5pdHM9InVzZXJTcGFjZU9uVXNlIj4KICAgICAgPHN0b3Agb2Zmc2V0PSIwIiBzdG9wLWNvbG9yPSIjMDA0Nzg4Ii8+CiAgICAgIDxzdG9wIG9mZnNldD0iMSIgc3RvcC1jb2xvcj0iIzAwNWJhZiIvPgogICAgPC9saW5lYXJHcmFkaWVudD4KICAgIDxsaW5lYXJHcmFkaWVudCBpZD0ibGluZWFyLWdyYWRpZW50LTYiIHgxPSItMjkxMS45MyIgeTE9IjQ1MTQuMzgiIHgyPSItMjM0MC44NCIgeTI9IjQ1MTQuMzgiIGdyYWRpZW50VHJhbnNmb3JtPSJ0cmFuc2xhdGUoMjA4NC4zNiAtNDM5OC43Nykgcm90YXRlKC0yMy4zOSkiIGdyYWRpZW50VW5pdHM9InVzZXJTcGFjZU9uVXNlIj4KICAgICAgPHN0b3Agb2Zmc2V0PSIwIiBzdG9wLWNvbG9yPSIjMDAzMzZiIi8+CiAgICAgIDxzdG9wIG9mZnNldD0iMSIgc3RvcC1jb2xvcj0iIzAwNDQ5MSIvPgogICAgPC9saW5lYXJHcmFkaWVudD4KICAgIDxsaW5lYXJHcmFkaWVudCBpZD0ibGluZWFyLWdyYWRpZW50LTciIHgxPSItMjcyMi43MSIgeTE9IjQ0OTQuMTgiIHgyPSItMjM3MS40OCIgeTI9IjQ0OTQuMTgiIGdyYWRpZW50VHJhbnNmb3JtPSJ0cmFuc2xhdGUoMjA4NC4zNiAtNDM5OC43Nykgcm90YXRlKC0yMy4zOSkiIGdyYWRpZW50VW5pdHM9InVzZXJTcGFjZU9uVXNlIj4KICAgICAgPHN0b3Agb2Zmc2V0PSIwIiBzdG9wLWNvbG9yPSIjMDAxZTRkIi8+CiAgICAgIDxzdG9wIG9mZnNldD0iMSIgc3RvcC1jb2xvcj0iIzAwMmQ3MiIvPgogICAgPC9saW5lYXJHcmFkaWVudD4KICA8L2RlZnM+CiAgPHJlY3QgY2xhc3M9ImNscy0zIiB5PSIwIiB3aWR0aD0iMTgwMCIgaGVpZ2h0PSIxMTAwIi8+CiAgPHBhdGggY2xhc3M9ImNscy0yIiBkPSJtMTE0Mi4xOCwxNjI5LjA0YzEzMzIuNzYtMTE1LjYzLDY5OC45OS0xMzA2Ljg3LDU0OS4yNy0xNDU2LjExLTE0OS43Mi0xNDkuMjMtMzI3Ljk3LDEzNS4zMy0zNTkuMTMsMTc5LjM4LTMxLjE3LDQ0LjA1LTEzNy43OSwxODYuMjYtMzc2LjgyLTY4LjQ2LTIzOS4wMy0yNTQuNzItNjAxLjk0LTE1LjE1LTYyMy44OCwxNzguMTgtMjYuMzIsMjMxLjk0LDExMy44NSwzNTkuMDksMTgyLjA5LDUzNy4wMiw2OC4yNCwxNzcuOTMsMTYxLjIzLDY3MC41Miw2MjguNDcsNjI5Ljk4Wk00NjUuNDQsNTU0LjczYzE4Ljg4LTE2Ni4zNiwzMzAuOC0zNDEuODMsNTU2Ljc5LTE2Ni4xNCwyMjIuNTQsMTczLjAyLDMxMy45Nyw1OS4yMywzNDEuMzEsMjEuNzEsNDIuMTktNTcuOTEsMjEyLjk2LTI2NC44OCwzMzQuODgtMTMyLjI1LDEyMi41NCwxMzMuNTcsNTYxLjAyLDEwNTAuMjktNTAyLjE1LDEyNDAuMTctNDUxLjA1LDgwLjU2LTQ4MC4xNS0zNjQuOTctNTM4Ljg3LTUxOC4wOC01OC43Mi0xNTMuMS0yMTQuNi0yNDUuODMtMTkxLjk2LTQ0NS40MVoiLz4KICA8cGF0aCBjbGFzcz0iY2xzLTUiIGQ9Im0xMTk2LjI2LDE1MTguMjFjMTA2My4xNi0xODkuODgsNjI0LjY5LTExMDYuNiw1MDIuMTUtMTI0MC4xNy0xMjEuOTItMTMyLjYzLTI5Mi42OSw3NC4zNC0zMzQuODgsMTMyLjI1LTI3LjM0LDM3LjUzLTExOC43NiwxNTEuMzEtMzQxLjMxLTIxLjcxLTIyNS45OS0xNzUuNjktNTM3LjkxLS4yMi01NTYuNzksMTY2LjE0LTIyLjY1LDE5OS41OCwxMzMuMjQsMjkyLjMsMTkxLjk2LDQ0NS40MSw1OC43MiwxNTMuMTEsODcuODIsNTk4LjY0LDUzOC44Nyw1MTguMDhabS01OTcuMDEtODcwLjc5YzE1LjgyLTEzOS4zOCwyNzUuNjktMjUwLjEyLDQ4OS42OS0xNTQuMDksMjA1LDkxLjk5LDI4Mi41Miw2LjEyLDMwNS44LTI1LjA1LDUzLjQ2LTcxLjU5LDIxNi41MS0yMDEuMTUsMzEwLjYyLTg1LjEyLDk1LjM3LDExNy45MiwzNDAuMDksNzg4LjE2LTQ1NS4wMiwxMDI0LjIzLTQzNS4wNSwxMjkuMTctNDAwLjA3LTI3Ny45LTQ0OS4yNy00MDYuMTgtNDkuMi0xMjguMjgtMjIwLjc5LTE4Ni41OC0yMDEuODItMzUzLjc5WiIvPgogIDxwYXRoIGNsYXNzPSJjbHMtOCIgZD0ibTEyNTAuMzQsMTQwNy4zOWM3OTUuMTEtMjM2LjA3LDU1MC4zOS05MDYuMzIsNDU1LjAyLTEwMjQuMjMtOTQuMTEtMTE2LjAzLTI1Ny4xNiwxMy41My0zMTAuNjIsODUuMTItMjMuMjgsMzEuMTctMTAwLjgsMTE3LjA0LTMwNS44LDI1LjA1LTIxNC05Ni4wMy00NzMuODcsMTQuNzEtNDg5LjY5LDE1NC4wOS0xOC45NywxNjcuMjIsMTUyLjYyLDIyNS41MSwyMDEuODIsMzUzLjc5LDQ5LjIsMTI4LjI4LDE0LjIyLDUzNS4zNCw0NDkuMjcsNDA2LjE4Wm0tNTE3LjI3LTY2Ny4yOGMxMi43NS0xMTIuNDEsMjE5Ljk4LTE1Ni4yLDQyMi42LTE0Mi4wNSwxODYuODksMTMuMDUsMjUxLjE5LTQ2LjksMjcwLjI5LTcxLjgsNjUuMTUtODQuOTYsMjIwLjA2LTEzNy40MiwyODYuMzctMzcuOTksNjguMTksMTAyLjI2LDEyOS43OSw1NTIuOTMtNDA3LjksODA4LjMtNDExLjk1LDE5NS42NS0zMTkuOTktMTkwLjgyLTM1OS42Ny0yOTQuMjgtMzkuNjgtMTAzLjQ2LTIyNi45OS0xMjcuMzItMjExLjY4LTI2Mi4xOFoiLz4KICA8cGF0aCBjbGFzcz0iY2xzLTciIGQ9Im05NDQuNzUsMTAwMi4yOWMzOS42OCwxMDMuNDYtNTIuMjgsNDg5LjkyLDM1OS42NywyOTQuMjgsNTM3LjY5LTI1NS4zNiw0NzYuMDktNzA2LjA0LDQwNy45LTgwOC4zLTY2LjMtOTkuNDMtMjIxLjIyLTQ2Ljk3LTI4Ni4zNywzNy45OS0xOS4wOSwyNC45LTgzLjQsODQuODUtMjcwLjI5LDcxLjgtMjAyLjYxLTE0LjE1LTQwOS44NCwyOS42NC00MjIuNiwxNDIuMDUtMTUuMywxMzQuODYsMTcyLjAxLDE1OC43MiwyMTEuNjgsMjYyLjE4Wm0tMTIuNTQtMjcwLjQ0YzUuOTktODYuMjMsMTY3LjE2LTExMywzMTUuMzMtMTA5LjE1LDE0My4wMSwzLjcyLDE5My4zNi00NS41NywyMDkuNjItNzAuMDgsNDkuMTUtNzQuMDYsMTc3LjUxLTEyMC40OCwyMzQuNzEtMzQuMDcsODIuOTQsMTI2LjIsNDMuMDIsNDkwLjMtMzI1Ljg0LDY1MS4wOC0yODUuOSwxMjQuNjItMjAzLjIyLTE1My4yOS0yMzcuNC0yNDIuNDMtMzQuMTktODkuMTQtMjAzLjctOTIuMzMtMTk2LjQyLTE5NS4zNloiLz4KICA8cGF0aCBjbGFzcz0iY2xzLTYiIGQ9Im0xMTI4LjYzLDkyNy4yMWMzNC4xOSw4OS4xNC00OC41LDM2Ny4wNSwyMzcuNCwyNDIuNDMsMzY4Ljg2LTE2MC43OCw0MDguNzgtNTI0Ljg4LDMyNS44NC02NTEuMDgtNTcuMi04Ni40MS0xODUuNTctMzkuOTktMjM0LjcxLDM0LjA3LTE2LjI2LDI0LjUxLTY2LjYxLDczLjc5LTIwOS42Miw3MC4wOC0xNDguMTctMy44NS0zMDkuMzUsMjIuOTItMzE1LjMzLDEwOS4xNS03LjI4LDEwMy4wMywxNjIuMjQsMTA2LjIxLDE5Ni40MiwxOTUuMzZabTIxMC43OS0yNzkuODZjOTkuMzItNi4xMSwxMzUuNzMtNDQuMDUsMTQ4Ljk1LTY4LjM2LDMzLjk3LTYyLjQ3LDEzNC45Ni0xMDMuNTQsMTgzLjA2LTMwLjE0LDk3LjY5LDE1MC4xNS00NC40Nyw0MjcuMzMtMjQzLjc4LDQ5My44Ni0xNjAuNDcsNTMuNTctODYuNDQtMTE1Ljc2LTExNS4xNC0xOTAuNTktMjguNy03NC44My0xODAuNDEtNTcuMzMtMTgxLjE2LTEyOC41My0uNzgtNjAuMDQsMTE0LjUtNzAuNDgsMjA4LjA3LTc2LjI0WiIvPgogIDxwYXRoIGNsYXNzPSJjbHMtNCIgZD0ibTEzMTIuNTIsODUyLjEyYzI4LjcsNzQuODMtNDUuMzMsMjQ0LjE1LDExNS4xNCwxOTAuNTksMTk5LjMxLTY2LjUzLDM0MS40Ny0zNDMuNzEsMjQzLjc4LTQ5My44Ni00OC4xLTczLjQtMTQ5LjA5LTMyLjMzLTE4My4wNiwzMC4xNC0xMy4yMiwyNC4zLTQ5LjYzLDYyLjI1LTE0OC45NSw2OC4zNi05My41Nyw1Ljc2LTIwOC44NSwxNi4yLTIwOC4wNyw3Ni4yNC43NSw3MS4yLDE1Mi40Nyw1My43LDE4MS4xNiwxMjguNTNabTExOC43OS0xODAuMTNjNTUuMzMtMTguNDgsNzguNjItNDIuMyw4OC4yOC02Ni42NCwxOS45Ny01MC4zNCw5Mi40LTg2LjYxLDEzMS40MS0yNi4yMSwxMTIuNDQsMTc0LjA5LTEzNS4wMSwzNTQuODQtMTYxLjcyLDMzNi42NC0zNy4yMi0yNS4zNiwzMC4zMy03OC4yMyw3LjEzLTEzOC43NC0yMy4yMS02MC41MS0xNTcuMTMtMjIuMzQtMTY1LjktNjEuNzEtNy41NS0zMy44Niw2MS42Mi0zMC4yNSwxMDAuODEtNDMuMzRaIi8+CiAgPHBhdGggY2xhc3M9ImNscy0xIiBkPSJtMTQ5Ni40LDc3Ny4wM2MyMy4yMSw2MC41MS00NC4zNSwxMTMuMzgtNy4xMywxMzguNzQsMjYuNzEsMTguMiwyNzQuMTYtMTYyLjU1LDE2MS43Mi0zMzYuNjQtMzktNjAuMzktMTExLjQzLTI0LjEzLTEzMS40MSwyNi4yMS05LjY2LDI0LjM0LTMyLjk1LDQ4LjE2LTg4LjI4LDY2LjY0LTM5LjE5LDEzLjA5LTEwOC4zNiw5LjQ4LTEwMC44MSw0My4zNCw4Ljc4LDM5LjM3LDE0Mi43LDEuMiwxNjUuOSw2MS43MVoiLz4KPC9zdmc+);
}

.container {
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;


}

.acTitle {
    color: rgb(163, 163, 163);
    margin-left: -90px;
    margin-right: 40px;
    writing-mode: vertical-rl;
    letter-spacing: 10px;
}

.main {
    /* padding: 3em 0em 0em 0em; */
    display: flex;
    width: 120%;
    margin-top: 10%;
    flex-direction: column;
    align-items: center;
}

/* part */
.partAc_title {
    text-transform: uppercase;
    font-size: 2.2em;
    font-weight: 700;
    color: #4474c4;
    margin-bottom: 5em;
    letter-spacing: 1em;
    /* margin-left: 1em; */
}

#partsAc {
    overflow: hidden;
    display: flex;
    width: 100%;
}


.part {
    /* min-width: 19em; */
    width: calc(100%/5);
    min-height: 16em;
    height: 32em;
    padding: 2em 2em;
    background: linear-gradient(45deg, #a2c3df, transparent);
    background-color: rgba(255, 255, 255, .3);
}

.part:nth-child(2n+1) {
    /* background-color: #18507a; */
    background-color: rgb(3 83 148 / 30%);
}

.part:nth-child(2n) {
    margin-top: 13%;
}

#partsAc h2 {
    margin: 0;
    padding: 0;
    text-transform: uppercase;
    font-size: 1.6em;
    font-weight: 700;
    color: #4474c4;
}

#partsAc .subtitle {
    margin: 0 0 1em 0;
    padding: 0 0 1em 0;
    border-bottom: 1px solid #b2b2b2;
    text-transform: uppercase;
    font-size: 1.25em;
    font-weight: 400;
    /* color: #3D3D3D; */
    color: white;
}





/* selab act */
.selabAc {
    width: 100%;
    height: 100%;
}

.selabAc_title {
    text-transform: uppercase;
    font-size: 2.2em;
    font-weight: 700;
    color: #4474c4;
    margin-bottom: 3em;
}

.selab {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    width: 100%;
    height: 70vh;
    margin-top: 20px;
}

.mobileselab {
    display: none;
    flex-direction: column;
    justify-content: space-between;
    width: 100%;
    height: 70vh;
    margin-top: 20px;
}


:deep(.el-carousel) {
    display: flex;
    align-items: center;
    height: 99% !important;
}

:deep(.el-carousel__indicators--outside) {
    height: fit-content;
    transform: none;
    z-index: 21;
}

:deep(.el-carousel__indicator--vertical .el-carousel__button) {
    background-color: #02406f;
}

.seAcBig {
    width: 100%;
    height: 62vh;
    display: flex;
    position: relative;
}

.seAcBigImg {
    width: 100%;
    height: 100%;
    position: relative;
    overflow: hidden;
    transition: all .2s;

}

.seAcBigImg img {
    width: 60%;
    height: 95%;
    object-fit: cover;
    margin-left: 5%;
}

.seAcBigImgBorder {
    border: 5px solid #4474c4;
    width: 64%;
    height: 80%;
    position: absolute;
    top: 8%;
    left: 3%;
    /* transform: translateZ(0) scale(1.1);
    transition: opacity 125ms ease-in-out; */
    z-index: 10;
}

.seAcforce:after {
    clear: both;
    content: "";
    height: 0;
    visibility: hidden;
}

.seAcBigText {
    cursor: pointer;
    width: 35%;
    height: 21em;
    z-index: 20;
    padding: 2em;
    background: #fff;
    position: absolute;
    top: 14%;
    right: 5%;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    border-left: 8px solid #4474c4;
    border-bottom: 8px solid #4474c4;
    transition: all .2s;


}

.seAcBigText h5 {
    color: black;
    cursor: pointer;
    margin-top: .10546875em;
    font-size: 2.2352941176em;
    font-family: alternate-gothic-extra-cond, tahoma, sans-serif;
    line-height: 1.2;
    display: block;
    padding-bottom: 0;
    padding-top: 0;
    transition: all .1s;
}

.seAcBigText h5:hover {
    color: #4474c4;
}

.seAcBigText:hover {
    transform: scale(1.01)
}

.seAcBigTime {
    font-size: .8947058824em;
}

.seAcBigDes {
    font-size: 1em;
}

.seAcs {
    width: 100%;
    height: 100%;
    display: flex;
}

.seAc {
    width: 40%;
    height: 100%;
    display: flex;
}

.seAcImg {
    width: 100%;
    height: 100%;


}

.seAcImg img {
    width: 100%;
    height: 100%;
}

.seAcText {
    width: 80%;
    height: 100%;
    padding: 0 5%;
    display: flex;
    flex-direction: column;
}




.seTitle {
    cursor: pointer;
    transition: all .2s;
    color: white;
}

.seTitle:hover {
    transform: translateX(6px);
}

.seTime {
    color: white;
}

/* List Style 1 */
/* ul li {

    list-style: disc !important;
    list-style-position: outside !important;
} */




ul.style1 {}

ul.style1 li {
    padding: 0.75em 0 0.75em 0em;
}

ul.style1 span {
    cursor: pointer;
    display: block;
    /* padding-left: 0.5em; */
    text-decoration: none;
    /* color: #676767; */
    color: white;
    font-size: 1.1em;
}

ul.style1 span:hover {
    text-decoration: underline !important;
}

.button {
    margin-top: 30px;
    cursor: pointer;
    color: white;
    font-size: 600;
}

.button:hover {
    cursor: pointer;
    text-decoration: underline !important;
}

.cta {
    border: none;
    background: none;
    cursor: pointer;
    margin-top: 30px;
}

.cta span {
    padding-bottom: 7px;
    letter-spacing: 4px;
    font-size: 14px;
    padding-right: 15px;
    text-transform: uppercase;
}

.cta svg {
    transform: translateX(-8px);
    transition: all 0.3s ease;
}

.cta:hover svg {
    transform: translateX(0);
}

.cta:active svg {
    transform: scale(0.9);
}

.hover-underline-animation {
    position: relative;
    /* color: #767676; */
    color: white;
    padding-bottom: 20px;
}

.hover-underline-animation:after {
    content: "";
    position: absolute;
    width: 100%;
    transform: scaleX(0);
    height: 2px;
    bottom: 0;
    left: 0;
    background-color: white;
    transform-origin: bottom right;
    transition: transform 0.25s ease-out;
}

.cta:hover .hover-underline-animation:after {
    transform: scaleX(1);
    transform-origin: bottom left;
}



/* 当视口宽度小于1200px时调整样式 */
@media (max-width: 1600px) {
    .acTitle {
        font-size: 2vw;
        margin-left: -90px;
        margin-right: 40px;
    }

    #partsAc h2 {
        font-size: 1.2em;
    }

    #partsAc .subtitle {
        font-size: 1em;
    }

    ul.style1 li {
        padding: 0.5em 0 0.5em 0em;
    }

    /* #partsAc {
        margin: 10px;
    } */


    .selabAc_title {
        font-size: 1.75em;
    }

    .partAc_title {
        font-size: 1.75em;
    }


    .seTitle {
        font-size: 1.1em;
    }

    .seTime {
        font-size: 1.1em;
    }

    .selabAc {
        margin-top: 0;
    }
}


/* 移动端适配 */
@media (max-width: 1248px) {
    .homeActivity:before {

        top: -1%;

    }

    .homeActivity:after {

        bottom: -1.9%;

    }

    .selab {
        display: none;
    }

    .mobileselab {
        display: flex;
    }

    .main {
        /* padding: 3em 0em 0em 0em; */
        display: flex;
        width: 100%;
        margin-top: 10%;
        flex-direction: column;
    }

    .partAc_title {

        margin-bottom: .3em;
    }

    .selabAc {
        margin: 10% 0;
    }

    .el-carousel {
        flex-direction: column;
    }

    :deep(.el-carousel__indicators--outside button) {
        background-color: #052a74;
    }

    .seAcBig {
        width: 100%;
        height: 62vh;
        display: flex;
        position: relative;
        flex-direction: column;
        align-items: center;
    }

    .seAcBigImg {
        width: 100%;
        height: 40%;


    }

    .seAcBigImg img {
        width: 100%;
        height: 100%;
        margin-left: 0%;
    }

    .seAcBigImgBorder {
        display: none;
    }

    .seAcBigText {

        width: 100%;
        min-height: 15em;
        position: relative;
        top: 0;
        right: 0%;
        border: none;

    }

    #partsAc {
        display: flex;
        flex-direction: column;

        padding: 1% 6%;

    }

    .part {
        /* min-width: 19em; */
        width: 100%;
        height: 26em;
        padding: 2em 2em;
        margin-top: 13%;
    }

}
</style>
