<template>
    <div class="outer">
        <!-- 头部 -->
        <div class="header">
            <Header :islogoWhite="logoWhite" :color="headerColor"></Header>
        </div>

        <!-- banner -->

        <div class="banner" :class="{ 'hidden': isBannerHidden }" :style="{ height: isBannerHidden ? '0' : '100vh' }"
            style="background-color: #0d1621;">
            <div class="slider">
                <div>
                    <el-carousel class="slide active" ref="carousel" motion-blur indicator-position="none">
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
                </div>
            </div>


        </div>

        <div id="main">
            <!-- description -->
            <Desc id="desc" ref="desc"></Desc>

            <!-- history -->
            <History id="history"></History>

            <!-- part -->
            <Part class="ifadeIn" id="part"></Part>

            <!-- actvity -->
            <Act id="act"></Act>

            <!-- people -->
            <People id="people"></People>

            <Footer></Footer>
        </div>

    </div>
</template>
<script setup lang="ts">
import { ref, reactive, onMounted, computed, onBeforeUnmount } from "vue"
import Header from '@/components/header/header.vue'
import Footer from '@/components/footer/index.vue'
import Banner from "@/components/banner/banner.vue"
import Desc from "@/views/home/components/description/index.vue"
import History from "@/views/home/components/history/index.vue"
import Act from "@/views/home/components/activity/index.vue"
import People from "@/views/home/components/people/index.vue"
import Part from "@/views/home/components/part/index.vue"
import bus from '@/eventBus';



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



// banner展开收起动画
const isBannerHidden = ref(false);
const lastScrollY = ref(0);

const handleScroll = () => {
    const currentScroll = window.scrollY;
    const descElement = document.getElementById('desc');


    if (descElement) {
        const bannerHeight = document.querySelector('.banner')?.clientHeight ?? 0;
        const descOffset = descElement.getBoundingClientRect().top + window.scrollY - bannerHeight;

        if (currentScroll > lastScrollY.value + 0.1) {
            isBannerHidden.value = true;

            // smoothScrollToDesc(descOffset);
        } else if (currentScroll <= descOffset) {

            isBannerHidden.value = false;

            // smoothScrollToTop();
        }
        lastScrollY.value = currentScroll;
    }
};



const smoothScrollToTop = () => {
    const scrollDuration = 800; // duration in ms
    const scrollStep = -window.scrollY / (scrollDuration / 15);
    const scrollInterval = setInterval(() => {
        if (window.scrollY != 0) {
            window.scrollBy(0, scrollStep);
        } else {
            clearInterval(scrollInterval);
        }
    }, 15);
};
const smoothScrollToDesc = (descOffset: number) => {

    const scrollDuration = 800; // duration in ms
    const scrollStep = -window.scrollY / (scrollDuration / 15);
    const scrollInterval = setInterval(() => {
        if (window.scrollY == 0) {
            window.scrollBy(descOffset, scrollStep);
        } else {
            clearInterval(scrollInterval);
        }
    }, 15);

};


onMounted(() => {
    //加载动画
    setTimeout(() => {
        bus.emit('loading', false);
    }, 800);

    window.addEventListener('scroll', handleScroll);


});
onBeforeUnmount(() => {
    window.removeEventListener('scroll', handleScroll);
});



</script>
<style scoped>
@import url('./homebase.css');

.outer {
    display: flex;
    flex-direction: column;
    min-height: 100vh;
    /* 父容器最小高度为视口高度 */
    /* overflow-x: hidden; */
}

.banner {
    position: relative;
    width: 100vw;
    height: 100vh;
    overflow: hidden;
    transition: all 0.8s ease-in-out;

}

.banner.hidden {
    /* pointer-events: none; */
}

#main {}

#desc,
#history,
#part,
#act,
#people {
    min-height: 100vh;
}

/* desc */
#desc {
    background-image: url(public/img/homeImg/bg2.jpg);
}

/* history */
#history {
    background-image: url(public/img/homeImg/bg3.jpg);
    background-size: cover;
}

/* part */
#part {
    background-image: url(public/img/homeImg/bg3.jpg);
}

/* act */
#act {
    background-image: url(public/img/homeImg/bg3.jpg);
}

/* people */
#people {
    background-image: url(public/img/homeImg/bg3.jpg);
}

/* 轮播图切换 */
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


/* 时间轴 */
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
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    margin-top: auto;
}
</style>
