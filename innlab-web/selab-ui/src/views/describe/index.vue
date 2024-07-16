<template>
    <div class="outer">
        <section>
            <div class="box">
                <h2 class="jarallax-title" data-jarallax-element="0">组 织 介 绍&nbsp;&nbsp;&nbsp;&nbsp;Selab Discription
                </h2>
                <div class="container">
                    <div class="imgBx jarallax">
                        <img class="jarallax-img" src="public/img/homeImg/descimg.png" alt="">
                    </div>
                    <div class="content jarallax-content" data-jarallax-element="-200 0">
                        <p>软件学院创新实验室位于软件学院行勉楼实验室一层，是隶属于软件学院的学生创新实践基地，宗旨在于丰富学生课外知识、提升学生创新实践能力。创新实验室内部有：软件开发、网络安全、人工智能与虚拟
                            现实四个学习方向小组。我们为意图提升专业技术能力的学生提
                            供一个良好的环境与平台；也是一个提供给学生交流技术的平台。
                            在这里，你可以找到志同道合的伙伴，可以尽情提升和发挥自己
                            的专业 能力，可以把学到的新奇的知识应用于实际中</p>
                    </div>
                </div>
            </div>
        </section>
        <section>
            <div class="box">
                <h2 class="jarallax-title" data-jarallax-element="0 200">建 立 初 衷&nbsp;&nbsp;&nbsp;&nbsp;Selab Intention
                </h2>
                <div class="container">
                    <div class="imgBx jarallax">
                        <img class="jarallax-img"
                            src="https://bpic.588ku.com/back_origin_min_pic/20/07/09/e857db8467b28ae518922a4935c08ea3.jpg"
                            alt="">
                    </div>
                    <div class="content jarallax-content" data-jarallax-element="-200 0">
                        <P>The Innovation Laboratory of the School of Software is located on the first
                            floor
                            of the Xingmian
                            Building Laboratory of the School of Software. It is a student innovation
                            practice base affiliated to
                            the School of Software. The purpose is to enrich students' extracurricular
                            knowledge and improve
                            students' innovative practice ability</P>

                    </div>
                </div>
            </div>
        </section>
        <section>
            <div class="box">
                <h2 class="jarallax-title" data-jarallax-element="0 -200">Awesome Parallax Scrolling</h2>
                <div class="container">
                    <div class="imgBx jarallax">
                        <img class="jarallax-img"
                            src="https://bpic.588ku.com/back_origin_min_pic/20/07/09/e857db8467b28ae518922a4935c08ea3.jpg"
                            alt="">
                    </div>
                    <div class="content jarallax-content" data-jarallax-element="-200 0">
                        <P>The Innovation Laboratory of the School of Software is located on the first
                            floor
                            of the Xingmian
                            Building Laboratory of the School of Software. It is a student innovation
                            practice base affiliated to
                            the School of Software. The purpose is to enrich students' extracurricular
                            knowledge and improve
                            students' innovative practice ability</P>

                    </div>
                </div>
            </div>
        </section>
        <section>
            <div class="box">
                <h2 class="jarallax-title" data-jarallax-element="0 200">Awesome Parallax Scrolling</h2>
                <div class="container">
                    <div class="imgBx jarallax">
                        <img class="jarallax-img"
                            src="https://bpic.588ku.com/back_origin_min_pic/20/07/09/e857db8467b28ae518922a4935c08ea3.jpg"
                            alt="">
                    </div>
                    <div class="content jarallax-content" data-jarallax-element="-200 0">
                        <P>The Innovation Laboratory of the School of Software is located on the first
                            floor
                            of the Xingmian
                            Building Laboratory of the School of Software. It is a student innovation
                            practice base affiliated to
                            the School of Software. The purpose is to enrich students' extracurricular
                            knowledge and improve
                            students' innovative practice ability</P>

                    </div>
                </div>
            </div>
        </section>
    </div>
</template>
<script setup lang="ts">
import { ref, reactive, onMounted } from "vue"
import bus from '@/eventBus';
// 页面滚动效果
import { jarallax } from "jarallax";
import 'jarallax/dist/jarallax.min.css';

onMounted(() => {



    // 获取所有的 jarallax 元素
    const jarallaxElements = document.querySelectorAll('.jarallax');

    // 初始化 jarallax 效果
    jarallax(jarallaxElements, {
        speed: 0.5,
        onScroll: function (calculations) {
            // 根据页面滚动计算并更新 h2 文字的位置
            let scrollX = window.scrollY * -0.5; // 可以根据需要调整滚动速度和方向
            document.querySelectorAll('.jarallax-title').forEach((title, index) => {

                switch (index) {
                    case 0:
                        scrollX = window.scrollY * -0.5;
                        title.style.transform = `translateX(${scrollX}px)`;
                        break;
                    case 1:
                        scrollX = window.scrollY * 0.25;
                        title.style.transform = `translateX(${scrollX}px)`;
                        break;
                    case 2:
                        scrollX = window.scrollY * -0.2;
                        title.style.transform = `translateX(${scrollX}px)`;
                        break;
                    case 3:
                        scrollX = window.scrollY * 0.08;
                        title.style.transform = `translateX(${scrollX}px)`;
                        break;

                    default:
                        break;
                }


            });

            // 根据页面滚动计算并更新 content 的位置
            let scrollY = window.scrollY * -0.2; // 可以根据需要调整滚动速度和方向
            document.querySelectorAll('.jarallax-content').forEach((title, index) => {

                switch (index) {
                    case 0:
                        scrollY = window.scrollY * -0.2;
                        title.style.transform = `translateY(${scrollY}px)`;
                        break;
                    case 1:
                        scrollY = window.scrollY * -0.03;
                        title.style.transform = `translateY(${scrollY}px)`;
                        break;
                    case 2:
                        scrollY = window.scrollY * -0.02;
                        title.style.transform = `translateY(${scrollY}px)`;
                        break;
                    case 3:
                        scrollY = window.scrollY * -0.01;
                        title.style.transform = `translateY(${scrollY}px)`;
                        break;

                    default:
                        break;
                }


            });
        }
    });

    // 加载动画完成后隐藏 loading
    setTimeout(() => {
        bus.emit('loading', false);
    }, 100);

    window.scrollTo({ top: 0, behavior: 'smooth' });
});

</script>
<style scoped>
/* @import url('../../utils/jarallax/jarallax.css'); */

* {
    margin: 0;
    padding: 0;
    font-family: 'Poppins', sans-serif;
}

.outer {
    background: linear-gradient(to bottom, #346195, #226b8d);
    padding-bottom: 300px;
    overflow-x: hidden;
}

.outer:before {
    content: 'Innvotaion Selab';
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    font-size: 10em;
    font-weight: 700;
    color: transparent;
    -webkit-text-stroke: 1px #555;
}

section {
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
}

section h2 {
    margin-left: 10%;
    font-size: 4em;
    color: #fff;
    margin-bottom: 20px;
}

section .container {
    position: relative;
    width: 1000px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

section .imgBx {
    position: relative;
    width: 900px;
    height: 450px;
    overflow: hidden;
    box-shadow: 0 25px 35px rgba(0, 0, 0, 0.1);
}

.imgBx img {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.content {
    position: absolute;
    right: 0;
    margin-top: 200px;
    margin-bottom: 40px;
    padding: 40px;
    background: #333;
    z-index: 1;
    max-width: 400px;
    color: #fff;
    box-shadow: 0 25px 35px rgba(0, 0, 0, 0.1);
    border-bottom: 6px solid #4ac7ff;
}
</style>