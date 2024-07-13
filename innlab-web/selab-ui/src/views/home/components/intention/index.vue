<template>
    <div class="outer">
        <div class="content">
            <div class="main">
                <div class="title">
                    <img src="public/img/disImg/dis3.png" alt="">
                    建立初衷
                </div>
                <main>
                    <div class="img">
                        <img src="public/img/homeImg/descimg.png" alt="">

                    </div>
                    <div class="text">
                        <h3>创新实验室</h3>
                        <span>&nbsp;&nbsp;&nbsp;&nbsp;软件学院创新实验室位于软件学院行勉楼实验室一层，是隶属于软件学院的学生创新实践基地，宗旨在于丰富学生课外知识、提升学生创新实践能力。创新实验室内部有：软件开发、网络安全、人工智能与虚拟
                            现实四个学习方向小组。我们为意图提升专业技术能力的学生提
                            供一个良好的环境与平台；也是一个提供给学生交流技术的平台。
                            在这里，你可以找到志同道合的伙伴，可以尽情提升和发挥自己
                            的专业 能力，可以把学到的新奇的知识应用于实际中
                        </span>
                    </div>

                </main>
            </div>

        </div>


    </div>
</template>

<script setup lang="ts">
import { ref, reactive } from "vue"
import Readmore from '@/components/readmore/index.vue'



</script>

<style scoped>
.outer {
    width: 100%;
    height: 100%;
    padding: 0 120px;
    display: flex;
    align-items: center;
    justify-content: center;
}

.content {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}

.main {
    width: 100%;
    padding: 10px 100px;
    display: flex;
    flex-direction: column;
    /* align-items: center; */
    position: relative;
}



.title {
    color: white;
    font-size: 50px;
    font-weight: 600;
    font-family: 'STFangsong';
    z-index: 10;
    position: relative;

    img {
        width: 8%;
        height: auto;
        position: absolute;
        top: -18%;
        left: -5%;
        z-index: -10;
    }
}


main {
    width: 100%;
    display: flex;
    justify-content: space-between;
    margin-top: 6%;

    .img {
        width: 70%;
        height: auto;
        margin-right: 10%;

        img {
            width: 100%;
            height: 100%;
        }
    }

    .text {
        width: 100%;
        margin-top: 10px;
        font-size: 26px;
        font-weight: 500;
        color: white;

        h3 {
            font-size: 38px;
            text-align: center;
        }

        span {
            background-image: url(public/img/disImg/border1.png);
            background-size: 100% 100%;
            padding: 30px;
        }
    }
}


.text {
    display: flex;
    flex-direction: column;
    font-size: 20px;
    font-family: 'STFangsong';
    color: white;
    text-shadow: 2px 2px 2px #ffffff;

    span {
        margin-top: 20px;
    }
}
</style>