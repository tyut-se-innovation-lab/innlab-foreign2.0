<template>
  <div class="outContainer">
    <!-- 头部 -->
    <div class="header">
      <Header :islogoWhite="logoWhite" :color="headerColor"></Header>
    </div>

    <!-- banner -->
    <div class="banner" :class="{ 'hidden': isBannerHidden }">
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
                  <a href="javascript:;" class="btn" @click="linkTo()">More Details</a>
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

      <!-- part -->
      <Part id="part" class=""></Part>

      <!-- history -->
      <History id="history"></History>

      <!-- actvity -->
      <Act id="act"></Act>

      <!-- people -->
      <People id="people"></People>

    </div>

  </div>
</template>
<script setup lang="ts">
import { ref, reactive, onMounted, computed, onBeforeUnmount, nextTick } from "vue"
import Header from '@/components/header/header.vue'
import Banner from "@/components/banner/banner.vue"
import Desc from "@/views/home/components/description/index.vue"
import History from "@/views/home/components/history/index.vue"
import Act from "@/views/home/components/activity/index.vue"
import People from "@/views/home/components/people/index.vue"
import Part from "@/views/home/components/part/index.vue"
import bus from '@/eventBus';
import router from '@/router';
import AOS from 'aos'
import 'aos/dist/aos.css'


const actImgs = ref([
  "https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/6fd8182ad2deeef079926afb865af7ea29a6d545ef19bd1a76f1815eae30e932b56e67e9f36cddab99d50dafabe1534a?pictype=scale&from=30013&version=3.3.3.3&fname=banner3.jpg&size=750",
  // "public/img/banner.jpg",
  "https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/ec5f6cb7a3e8492f3648c2688b82b81b31d32bb27926ec114a5e34190ca350accc59fc5def77c588fff2bb7f2a2a5eb1?pictype=scale&from=30013&version=3.3.3.3&fname=banner2.jpg&size=750",
  "https://free2.yunpng.top/2024/08/17/66c03df81e748.jpg",
  "https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/0b812143bfbbf673dfe8b16704c7ab7728aeac73c1039e31955cba84abe71fd0c3d17ccdd08a3f885bd5fb6fe36db22b?pictype=scale&from=30113&version=3.3.3.3&fname=jingtaiziyuan.jpg&size=750",
  "https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/c951d90e6913552328b07448020ec877a6f70a1024901065ff8530221a02c88c21bb7cf66c754b9566af68fb483e0c54?pictype=scale&from=30113&version=3.3.3.3&fname=154928-yi_shu-chuang_kou-guang-zi_se_de-azure-1366x768.jpg&size=750",
  "https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/f670b4b15d9186612e895d916bca6fd49caf113a22ee951cbd2b6640dd99c08603c40c531acc75571bc5a172bfe5499b?pictype=scale&from=30113&version=3.3.3.3&fname=shouye.jpg&size=750",
  "https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/8e62988df6d5300d965775e34ef70400ca4fa14c3f65b6680a8ce09585af2ef04379d3e1e79daa8dff43f1c8682ba2d3?pictype=scale&from=30113&version=3.3.3.3&fname=start.jpg&size=750",

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

const linkTo = () => {
  router.push({
    path: '/blog',
    query: {
      actid: 18
    }
  });
}

// banner展开收起动画
const isBannerHidden = ref(false);
const lastScrollY = ref(0);


onMounted(() => {
  //加载动画
  setTimeout(() => {
    bus.emit('loading', false);
  }, 800);
  AOS.init();

  // window.addEventListener('scroll', handleScroll);
  //   reset: true,
  // });

});
onBeforeUnmount(() => {
  // window.removeEventListener('scroll', handleScroll);
});



</script>
<style scoped>
/* @import url('./homebase.css'); */

.outContainer {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  /* 父容器最小高度为视口高度 */
  /* overflow-x: hidden; */

}

/* header */
.header {
  width: 100%;
  height: 100px;
  display: flex;
  align-items: center;
  /* background: #fff; */
  background-color: transparent;
  /* border-bottom: solid 1px #015bb1; */
  overflow: hidden;
  position: fixed;
  top: 0;
  left: 0;
  z-index: 100;
  opacity: 0;
  opacity: 1\9;
  -webkit-animation: slideTop 1s ease 1.3s forwards;
  -moz-animation: slideTop 1s ease 1.3s forwards;
  -ms-animation: slideTop 1s ease 1.3s forwards;
  -o-animation: slideTop 1s ease 1.3s forwards;
  animation: slideTop 1s ease 1.3s forwards
}


@-webkit-keyframes slideTop {
  0% {
    opacity: 0;
    -webkit-transform: translateY(-100%);
    -moz-transform: translateY(-100%);
    -ms-transform: translateY(-100%);
    transform: translateY(-100%)
  }

  100% {
    opacity: 1;
    -webkit-transform: none;
    -moz-transform: none;
    -ms-transform: none;
    transform: none
  }
}

@-moz-keyframes slideTop {
  0% {
    opacity: 0;
    -webkit-transform: translateY(-100%);
    -moz-transform: translateY(-100%);
    -ms-transform: translateY(-100%);
    transform: translateY(-100%)
  }

  100% {
    opacity: 1;
    -webkit-transform: none;
    -moz-transform: none;
    -ms-transform: none;
    transform: none
  }
}

@-ms-keyframes slideTop {
  0% {
    opacity: 0;
    -webkit-transform: translateY(-100%);
    -moz-transform: translateY(-100%);
    -ms-transform: translateY(-100%);
    transform: translateY(-100%)
  }

  100% {
    opacity: 1;
    -webkit-transform: none;
    -moz-transform: none;
    -ms-transform: none;
    transform: none
  }
}

@-o-keyframes slideTop {
  0% {
    opacity: 0;
    -webkit-transform: translateY(-100%);
    -moz-transform: translateY(-100%);
    -ms-transform: translateY(-100%);
    transform: translateY(-100%)
  }

  100% {
    opacity: 1;
    -webkit-transform: none;
    -moz-transform: none;
    -ms-transform: none;
    transform: none
  }
}

@keyframes slideTop {
  0% {
    opacity: 0;
    -webkit-transform: translateY(-100%);
    -moz-transform: translateY(-100%);
    -ms-transform: translateY(-100%);
    transform: translateY(-100%)
  }

  100% {
    opacity: 1;
    -webkit-transform: none;
    -moz-transform: none;
    -ms-transform: none;
    transform: none
  }
}


/* banner */
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

.slider {
  width: 100%;
  height: 100%;
}

:deep(.el-carousel) {
  width: 100%;
  height: 100%;
}

:deep(.el-carousel-item) {
  width: 100%;
  height: 100%;
}

:deep(.el-carousel__container) {
  width: 100%;
  height: 100%;
}

.bannerImg {
  width: 100%;
  height: 100%;
}

.slider .slide {
  position: absolute;
  width: 100%;
  height: 100%;
}

.slide img {
  position: absolute;
  width: 100%;
  height: 100%;
  object-fit: cover;
  pointer-events: none;
  opacity: 0;
  transition: .5s ease;
}

.slide.active img {
  opacity: 1;
}

.slide .left-info {
  position: absolute;
  top: 0;
  left: 0;
  width: 50%;
  height: 100%;
  transform: translateX(-100%);
  transition: 0;
}

.slide.active .left-info {
  transform: translateX(0);
  z-index: 1;
  transition: 1s ease;
}

.left-info .penetrte-blur {
  position: absolute;
  width: 100%;
  height: 100%;
  background: rgba(255, 255, 255, .1);
  display: flex;
  justify-content: flex-end;
  align-items: center;
  backdrop-filter: blur(20px);
  -webkit-backdrop-filter: blur(20px);
  -webkit-mask: linear-gradient(#000 0 0),
    linear-gradient(#000 0 0);
  -webkit-mask-clip: text, padding-box;
  -webkit-mask-composite: xor;
  padding-right: 15px;
}

.penetrte-blur h1 {
  font-size: 250px;
  text-shadow: 0 0 10px rgba(255, 255, 255, 0.8);
}

.left-info .content {
  position: absolute;
  bottom: 8%;
  left: 10%;
  color: #fff;
}

.content h3 {
  font-size: 20px;
}

.content p {
  font-size: 16px;
  margin: 10px 10px 15px 0px;
}

.content .btn {
  cursor: pointer;
  display: inline-block;
  padding: 13px 28px;
  background: #fff;
  border: 2px solid #fff;
  border-radius: 6px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  text-decoration: none;
  font-weight: 600;
  color: #444;
  font-size: 16px;
  transition: all .3s linear;
}

.content .btn:hover {
  background: transparent;
  color: #fff;
}

.slide .right-info {
  position: absolute;
  top: 0;
  right: 0;
  width: 50%;
  height: 100%;
  display: flex;
  align-items: center;
  transform: translateX(100%);
  transition: 0s;
}

.slide.active .right-info {
  transform: translateX(0);
  transition: 1s ease;
}


.right-info h1 {
  font-size: 250px;
  color: #fff;
  text-shadow:
    0 1px 0 #ccc,
    0 2px 0 #c9c9c9,
    0 3px 0 #bbb,
    0 4px 0 #b9b9b9,
    0 5px 0 #aaa,
    0 6px 1px rgba(0, 0, 0, .1),
    0 0px 5px rgba(0, 0, 0, .1),
    0 1px 3px rgba(0, 0, 0, .3),
    0 3px 5px rgba(0, 0, 0, .2),
    0 5px 10px rgba(0, 0, 0, .25),
    0 10px 10px rgba(0, 0, 0, .2),
    0 20px 20px rgba(0, 0, 0, .15);
}

.right-info h3 {
  position: absolute;
  font-size: 80px;
  color: #fff;
  text-shadow: 0 0 10px rgba(0, 0, 0, .5);
  transform: translateY(150%);
  margin-left: 13px;
}


/* main */
#main {
  background-color: white;
  background-size: cover;
  background-repeat: no-repeat;
  background-position: 0 0;
  /* padding-top: 200px; */
}

#desc,
#history,
#part,
#act,
#people {
  min-height: 100vh;
}

#desc {
  min-height: 86vh;
  background-color: white;
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


/* 响应式 */
@media (max-width: 1600px) {

  /* banner left title */
  .penetrte-blur h1 {
    font-size: 220px;
  }

  .right-info h1 {
    font-size: 220px;
  }

  .right-info h3 {}

  .content .btn {
    display: none;
  }

}

/* 移动端适配 */
@media (max-width: 1284px) {

  /* header */
  .header {
    display: none;
  }

  /* banner */
  .banner {
    height: 28vh;
    margin-top: 4em;
  }

  .left-info .content {
    display: none;
  }

  .left-info .penetrte-blur h1,
  .right-info h1 {
    font-size: 4em;
  }

  .right-info h3 {
    font-size: 30px;
  }

  .content h3 {
    font-size: 18px;
  }

  .content p {
    font-size: 12px;
  }

  .content .btn {
    font-size: 10px;
    padding: 3px 8px;
  }

  #desc {
    min-height: 60vh;
    font-size: 14px;
  }
}
</style>
