<template>
    <div class="footer" id="6">
        <header>
            <div class="logo">
                <img src="@/assets/logo.png" alt="">
                <div class="text">
                    <span @click="linkTo(15)">侵权处理</span>
                    <span @click="linkTo(16)">版权声明</span>
                    <span @click="linkTo(21)">开发人员致谢</span>
                    <span @click="linkTo(27)">官网App</span>
                </div>
            </div>
        </header>

        <section>

            <div class="main">

                <div class="mainCon">
                    <h1 class="mainTitle">联系我们</h1>
                    <div class="mainMain">
                        <span>地址：中国山西省晋中市榆次区大学街209号太原理工大学行勉楼</span>
                        <!-- <span>联系方式：12345678900</span> -->
                        <span>邮箱：3135631305@qq.com</span>
                        <span>邮编：030600</span>
                    </div>
                </div>
            </div>

            <div class="contact">
                <h1 class="contactTitle">联系我们</h1>
                <div class="contactMain">
                    <span>地址：中国山西省晋中市榆次区大学街209号太原理工大学行勉楼</span>
                    <!-- <span>联系方式：12345678900</span> -->
                    <span>邮箱：3135631305@qq.com</span>
                    <span>邮编：030600</span>

                </div>
            </div>
        </section>


    </div>
    <div class="dinner">
        <p>版权所有：太原理工大学软件学院创新实验室</p>
        <a href="https://beian.miit.gov.cn/" class="a1" target="_blank">
            晋ICP备2024044169号</a>


    </div>
</template>

<script setup lang="ts">
import { ref, reactive } from "vue"
import router from '@/router';
// 点击跳转
const linkTo = (item: number) => {
    router.push({ path: '/blog', query: { actid: item } });
}



</script>

<style scoped>
.footer {
    width: 100%;
    display: flex;
    flex-direction: column;
    color: white;
    padding: 22px 120px;
    background: #002d72;
    z-index: 100;
}

header {
    width: 100%;
    border-bottom: 1px solid rgba(225, 225, 225, 0.3);
}

.logo {
    display: flex;
    align-items: center;
    justify-content: space-between;


}

.logo img {
    width: 160px;
}

.text {
    width: 23%;
    display: flex;
    justify-content: space-between;
    font-size: .9em;

}

.text span {
    cursor: pointer;
}

section {
    display: flex;
    margin-top: 20px;
    justify-content: space-between;
    /* justify-content: center; */
    /*  */
}

.main {
    display: flex;
    flex-direction: column;
}

.mainCon {
    text-align: left;
    margin-bottom: 2em;
}

.mainTitle {
    font-size: 1.3em;
    margin-bottom: 10px;
}

.mainMain {
    display: flex;
    /* flex-direction: column; */
    gap: 1.5em;
}


.contact {
    display: none;
    text-align: left;
}

.contactTitle {
    font-size: 1.5em;
    margin-bottom: 10px;
}

.contactMain {
    display: flex;
    flex-direction: column;
    gap: 0.5em;
}


span {
    margin-top: .5em;
}

.linkMain {
    display: flex;
    flex-direction: column;
}



.dinner {
    width: 100%;
    height: 50px;
    /* border-top: solid 1px #dedede; */
    /* line-height: 50px; */
    display: flex;
    justify-content: center;
    align-items: center;
    color: white;
    /* padding: 22px 120px; */
    z-index: 100;
    background: #092e65;
}

.dinner p {
    float: left;
    font-size: 12px;
    margin-right: 3%;
}

.dinner a {
    cursor: pointer;
    float: left;
    font-size: 12px;
    padding-right: 1.6rem;

    padding-left: 1.6rem;
    background: url(public/img/disImg/police.png) no-repeat left center;
}

.dinner .a2 {
    cursor: pointer;
    float: left;
    font-size: 12px;
    padding-left: 0;
    background: none;
}

/* 移动端适配 */
@media (max-width: 1248px) {
    .footer {
        width: 100%;

        /* height: 50px; */
        /* border-top: solid 1px #dedede; */
        /* line-height: 50px; */
        display: flex;
        flex-direction: column;
        color: white;
        padding: 2% 6%;
        background: #002d72;
        z-index: 100;
    }

    .logo {
        display: flex;
        align-items: center;
        justify-content: space-around;

    }

    .logo img {
        display: none;
    }

    .text {
        width: 100%;
        display: flex;
        justify-content: flex-start;
        font-size: 0.8em;
    }

    .text span {
        margin-right: 4%;
    }

    .main {
        display: none;
    }

    .contact {
        display: block;

    }

    .dinner {

        flex-direction: column;
    }

    .dinner a {
        display: none;
    }
}
</style>
