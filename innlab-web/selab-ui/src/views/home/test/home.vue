<template>
    <div class="outer">
        <!-- 右侧小圆点 -->
        <ul id="menu">
            <li data-menuanchor="firstPage" class="active"><a href="javascript:void(0)" @click="jp(1)"></a></li>
            <li data-menuanchor="secondPage"><a href="javascript:void(0)" @click="jp(2)"></a></li>
            <li data-menuanchor="3rdPage"><a href="javascript:void(0)" @click="jp(3)"></a></li>
            <li data-menuanchor="4thpage"><a href="javascript:void(0)" @click="jp(4)"></a></li>
            <li data-menuanchor="5fivpage"><a href="javascript:void(0)" @click="jp(5)"></a></li>
            <li data-menuanchor="lastpage"><a href="javascript:void(0)" @click="jp(6)"></a></li>
        </ul>

        <div class="lowceng">
            <div class="white"></div>
            <div class="low">
                <a href="javascript:if(confirm('http://www.google.cn/intl/zh-CN/chrome/browser/  \n\n���ļ�δ�� Teleport Pro ���أ���Ϊ ��λ����ʼ��ַ�����õı߽���������·���С�  \n\n����Ҫ�ӷ�����������?'))window.location='http://www.google.cn/intl/zh-CN/chrome/browser/'"
                    tppabs="http://www.google.cn/intl/zh-CN/chrome/browser/"></a>
            </div>
        </div>

        <!-- 头部 -->
        <div class="header">
            <Header :islogoWhite="logoWhite" :color="headerColor"></Header>
        </div>

        <div class="cengboxindex">
        </div>

        <div class="entrybox">
            <!-- <div class="scan">
                <div class="scancode"></div>
            </div>
            <div class="wexin">
                <div class="weixincode"></div>
            </div> -->
            <div class="top"></div>
        </div>

        <!-- 主页 -->
        <div id="fullpage" class="fullpage-wrapper">
            <!-- banner -->
            <div class="section fp-section" id="section0" data-anchor="firstPage">
                <section class="banner" style="background-color: #0d1621;">
                    <div class="slider">
                        <el-carousel ref="carousel" class="slide" motion-blur arrow="never" indicator-position="none">
                            <el-carousel-item v-for="item in actImgs" :key="item">
                                <img :src="item" />
                                <div class="left-info">
                                    <div class="penetrte-blur">
                                        <h1>Innov</h1>
                                    </div>
                                    <div class="content">
                                        <h3>The Innovation Laboratory</h3>
                                        <p>
                                            软件学院创新实验室位于软件学院行勉楼实验室一层,是隶属于软件学院的学生创新实践基地,宗旨在于丰富学生课外知识、提升学生创新实践能力。
                                        </p>
                                        <p>The Innovation Laboratory of the School of Software is located on the first
                                            floor
                                            of the Xingmian
                                            Building Laboratory of the School of Software. It is a student innovation
                                            practice base affiliated to
                                            the School of Software. The purpose is to enrich students' extracurricular
                                            knowledge and improve
                                            students' innovative practice ability.</p>
                                        <a href="#" class="btn">More Details</a>
                                    </div>
                                </div>
                                <div class="right-info">
                                    <h1>ation</h1>
                                    <h3>Laboratory</h3>
                                </div>
                            </el-carousel-item>
                        </el-carousel>
                        <button @click="prev" class="carousel-control prev">◀</button>
                        <button @click="next" class="carousel-control next">▶</button>
                    </div>


                </section>
            </div>
            <!-- description -->
            <Desc class="section fp-section" id="section1" data-anchor="secondPage"></Desc>

        </div>
        <!-- history -->
        <div class="section fp-section" id="section2" data-anchor="3rdPage">

            <div class="content3">

                <div class="timeline-container">
                    <div class="time-top">
                        <img class="timeTitle" src="public/img/titleImg/time.png" alt="">
                    </div>
                    <div class="timeline" ref="timeline">
                        <div class="historys" v-for="(item, index) in historyList">
                            {{ item.historyTime }}
                            <span class="arrow" v-if="index !== historyList.length - 1">⇾</span>
                            <div v-if="!isOdd(index)" class="history-up">
                                <img src="public/img/homeImg/timeline-up.png" alt="">
                                <Tooltip :title="item.historyTitle" :content="item.historyContent"></Tooltip>
                            </div>
                            <div v-if="isOdd(index)" class="history-down">
                                <img src="public/img/homeImg/timeline-down.png" alt="">
                                <Tooltip style="margin-top:50px;" :title="item.historyTitle"
                                    :content="item.historyContent"></Tooltip>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="scroll">
                    <button class="linemore" @click="scrollLeft">◀</button>
                    <button class="linemore" @click="scrollRight">▶</button>
                </div>
            </div>
        </div>
        <!-- part -->
        <div class="section fp-section" id="section3" data-anchor="4thpage">
            <Part class="ifadeIn"></Part>
        </div>
        <!-- actvity -->
        <div class="section fp-section" id="section4" data-anchor="5fivpage">
            <div class="center2">
                <Act></Act>
            </div>
        </div>
        <!-- people -->
        <div class="section fp-section" id="section5" data-anchor="lastpage">
            <People></People>

        </div>
        <Footer></Footer>

    </div>
</template>
<script setup lang="ts">
import { ref, reactive, onMounted, computed } from "vue"
import Header from '@/components/header/header.vue'
import Footer from '@/components/footer/index.vue'
import Banner from "@/components/banner/banner.vue"
import Desc from "@/views/home/components/description/index.vue"
import Act from "@/views/home/components/activity/index.vue"
import People from "@/views/home/components/people/index.vue"
import Part from "@/views/home/components/part/index.vue"
import Tooltip from '@/components/tooltip/index.vue'


// 页面滚动
import './js/jquery.min_78bc357.js';
import './js/jquery.fullPage.min_688fc6d.js';
import './js/jquery.easings.min_80a8980.js'
import './js/page_3b93263.js'
import './js/jquery.cookie_119dc0f.js';

import bus from '@/eventBus';

import { getHistoryList } from '@/api/history/history.ts';

interface JQuery {
    fullpage(options?: any): void;
}

const actImgs = ref([
    "https://tg-image.com/file/d19d49389f231ab624ea8.jpg",
    "https://ts1.cn.mm.bing.net/th/id/R-C.c535692088f20b46fe10d2033fdd1e9c?rik=gyunSGzwxyqvaA&riu=http%3a%2f%2fseopic.699pic.com%2fphoto%2f50064%2f5999.jpg_wh1200.jpg&ehk=CWiOffIICuMAgVLeEN8Mj2s57mCFCAR%2fl5wjTmaGjrs%3d&risl=&pid=ImgRaw&r=0",
    "https://ts1.cn.mm.bing.net/th/id/R-C.dadca894207a4d0ed84d8f660aecbe9d?rik=1NY%2fCtVnHKJ%2fPA&riu=http%3a%2f%2fres.dps.cn%2fres%2fbgimg%2f18674%2fk1-1.jpg&ehk=U8DrQVfzEHUrFj3YU4L1Gj2Lp4uF3MTGHUQznsg7Qok%3d&risl=&pid=ImgRaw&r=0",
])

// 轮播图切换
const carousel = ref();

const next = () => {
    carousel.value?.next();
};

const prev = () => {
    carousel.value?.prev();
};

const headerColor = ref('white');
const logoWhite = ref(true);

const isOdd = (index) => {
    return index % 2 === 1;
}

const historyList = ref<Array<{ historyTime: string; historyTitle: string; historyContent: string }>>([]);
historyList.value = [
    { historyTime: '2000', historyTitle: 'Title 1', historyContent: 'Content 1Content 1' },
    { historyTime: '2001', historyTitle: 'Title 2', historyContent: 'Content 2' },
    { historyTime: '2002', historyTitle: 'Title 3', historyContent: 'Content 3' },
    { historyTime: '2000', historyTitle: 'Title 1', historyContent: 'Content 1' },
    { historyTime: '2001', historyTitle: 'Title 2', historyContent: 'Content 2' },
    { historyTime: '2002', historyTitle: 'Title 3', historyContent: 'Content 3' },
    { historyTime: '2000', historyTitle: 'Title 1', historyContent: 'Content 1' },
    { historyTime: '2001', historyTitle: 'Title 2', historyContent: 'Content 2' },
    { historyTime: '2001', historyTitle: 'Title 2', historyContent: 'Content 2' },
    { historyTime: '2002', historyTitle: 'Title 3', historyContent: 'Content 3' },

];
const scrollPosition = ref(0);
const timeline = ref<HTMLDivElement | null>(null);

const isAtLeft = ref(true);
const isAtRight = ref(false);

const scrollRight = () => {
    if (timeline.value) {
        const scrollAmount = 300 // Adjust scroll amount as needed
        console.log('scrollPosition', scrollPosition.value, -(timeline.value.clientWidth * 0.6), scrollPosition.value >= -(timeline.value.clientWidth * 0.6));
        if (scrollPosition.value >= -(timeline.value.clientWidth * 0.35)) {
            scrollPosition.value -= scrollAmount
            timeline.value.style.transform = `translateX(${scrollPosition.value}px)`
        }

    }
}
const scrollLeft = () => {
    if (timeline.value) {
        const scrollAmount = 300 // Adjust scroll amount as needed
        console.log('scrollPosition', scrollPosition.value, timeline.value.clientWidth);

        if (scrollPosition.value !== 0) {
            scrollPosition.value += scrollAmount
            timeline.value.style.transform = `translateX(${scrollPosition.value}px)`
        }
    }


}



const getHistorys = async () => {
    try {
        const result = await getHistoryList();
        if (result.code == 200) {
            historyList.value = result.data.records;
        }

    } catch (error) {
        console.error('Error fetching data:');
    } finally {

    }
};

onMounted(() => {
    //加载动画
    setTimeout(() => {
        bus.emit('loading', false);
    }, 800);

    getHistorys();

    // 页面滚动
    $('#fullpage').fullpage({
        sectionsColor: ['#fff', '#fff', '#fff', '#fff', '#fff', '#fff'],
        lockAnchors: true,
        menu: '#menu',
        css3: true,
        scrollingSpeed: 800,
        afterLoad: function (anchorLink: any, index: number) {
            console.log('index: ', index);

            if (index == 1) {
                headerColor.value = 'white'
                logoWhite.value = true
                $(".top").hide();
            } else {
                $(".top").show();
            }

            if (index == 2 || index == 3 || index == 4 || index == 5 || index == 6) {
                headerColor.value = '#4474c4'
                logoWhite.value = false
            }
        }
    });

    $('.top').click(function () {
        $.fn.fullpage.moveTo(1);
    });


    window.addEventListener('scroll', handleScroll);


});

function jp(index: any) {
    $.fn.fullpage.moveTo(index);
}


// 滚动到part部分时渐显
// 监听 fullpage 的滚动事件，根据滚动到第三页的条件来控制元素的显示
function handleScroll() {
    const thirdPageElement = document.querySelector('#section2');
    const elements = document.querySelectorAll<HTMLElement>('.acShow');

    if (thirdPageElement && isElementInViewport(thirdPageElement)) {
        elements.forEach((element: HTMLElement) => {
            element.style.opacity = "1";
        });
    } else {
        elements.forEach((element: HTMLElement) => {
            element.style.opacity = "0";
        });
    }
}

// 判断元素是否在视口内
function isElementInViewport(el: HTMLElement) {
    const rect = el.getBoundingClientRect();
    return (
        rect.top >= 0 &&
        rect.left >= 0 &&
        rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) &&
        rect.right <= (window.innerWidth || document.documentElement.clientWidth)
    );
}

</script>
<style scoped>
@import url('./homebase.css');

.outer {
    display: flex;
    flex-direction: column;
    min-height: 100vh;
    /* 父容器最小高度为视口高度 */
}

#fullpage {
    /* flex: 1; */
    /* 填充剩余空间 */
}

.carousel-control {
    width: 6%;
    height: 10%;
    position: absolute;
    top: 90%;
    transform: translateY(-50%);
    background-color: rgba(255, 255, 255, 0.5);
    color: #ffffff;
    border: none;
    padding: 10px;
    cursor: pointer;
    z-index: 10;
    transition: all .2s;
    font-size: 52px;
}

.carousel-control.prev {
    right: 12%;
}

.carousel-control.next {
    right: 5%;
}

.carousel-control:hover {
    background-color: rgba(255, 255, 255, 0.8);
    color: rgb(181, 181, 181);
}

.time-top {
    width: 100%;
    height: 40%;
    padding-right: 60px;
    display: flex;
    align-items: center;
    justify-content: center;
    margin-bottom: 8%;
}

.timeTitle {
    width: 900px;
    height: auto;
}

/* 首页footer */
.footer {
    width: 100%;
    min-width: 1140px;
    height: 45px;
    /* border-top: solid 1px #dedede; */
    /* line-height: 225%; */
    color: white;
    background: #02406f;
    z-index: 100;
    position: fixed;
    bottom: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    margin-top: auto;
}
</style>
