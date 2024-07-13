<template>
    <div class="outer">

        <div class="topTitle">
            <img src="public/img/titleImg/part.png" alt="">
        </div>

        <main>
            <div class="container">
                <div class="hotAct">
                    <div class="hotActImg">
                        <img src="https://www.tyut.edu.cn/__local/2/58/7C/81814115BDCC19B5719DD3A2578_2E723A7C_F12B.jpg"
                            alt="">
                    </div>
                    <div class="hotActText">
                        <h1 class="hotActText_title">省委副书记张春林莅临我校调研指导工作</h1>
                        <span
                            class="hotActText_text">3月8日上午，省委副书记张春林莅临我校调研指导工作。省委副秘书长（正厅长级）王成禹，省教育厅党组书记、厅长马骏参加调研，我校党委书记沈兴全，校党委副书记、副校长（主持行政工作）孙宏斌陪同调研。调研中...
                        </span>
                        <span class="hotActText_time">2024-03-12</span>
                    </div>
                    <!-- 详情 -->
                    <!-- <div class="detail">详情</div> -->
                </div>
                <div class="acts">
                    <div class="act">
                        <div class="ag-courses_item">
                            <a class="ag-courses-item_link" href="#">
                                <div class="ag-courses-item_bg"></div>
                                <div class="ag-courses-item_title">You Welcome In Uiverse</div>
                                <div class="ag-courses-item_date-box">
                                    Start:
                                    <span class="ag-courses-item_date"> 14.11.2023 </span>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="act">
                        <div class="ag-courses_item">
                            <a class="ag-courses-item_link" href="#">
                                <div class="ag-courses-item_bg"></div>
                                <div class="ag-courses-item_title">You Welcome In Uiverse</div>
                                <div class="ag-courses-item_date-box">
                                    Start:
                                    <span class="ag-courses-item_date"> 14.11.2023 </span>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="act">
                        <div class="ag-courses_item">
                            <a class="ag-courses-item_link" href="#">
                                <div class="ag-courses-item_bg"></div>
                                <div class="ag-courses-item_title">You Welcome In Uiverse</div>
                                <div class="ag-courses-item_date-box">
                                    Start:
                                    <span class="ag-courses-item_date"> 14.11.2023 </span>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
                <Acts></Acts>
            </div>
            <div class="menus">
                <div class="title">
                    <span>{{ props.part ? props.part : '站内公告' }}</span>
                </div>
                <div class="content">
                    <span>pro</span>
                </div>
            </div>
        </main>


    </div>
</template>
<script setup lang="ts">
import { ref, reactive, onMounted, defineProps } from "vue"
import bus from '@/eventBus';
import Acts from './components/activitys.vue'
onMounted(() => {
    setTimeout(() => {
        bus.emit('loading', false);
    }, 1200); // 将 setTimeout 的延迟设置为 0 毫秒，确保在下一个事件循环中执行
    window.scrollTo({ top: 0, behavior: 'smooth' });
})

const props = defineProps<{
    part: string
}>()


</script>
<style scoped>
.outer {
    /* margin-top: 120px; */
    display: flex;
    flex-direction: column;
    justify-content: center;

    background-image: url(public/img/homeImg/bg9.png);
}

header {
    display: flex;
    justify-content: space-between;
}

.topTitle {


    width: 100%;
    height: 268px;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 4%;

    img {
        width: 65%;
        height: auto;
    }

}

.topImg {
    width: 1300px;
    height: 400px;
    background-image: url(https://www.tyut.edu.cn/images/c-ban02.jpg);
    background-repeat: no-repeat;
    background-size: cover;
}

/* main */
main {
    width: 100%;
    min-height: 1200px;
    display: flex;
    /* justify-content: center; */
    /* margin: 60px 0; */
    margin: 40px 110px;

}

.container {
    width: 60%;
    padding: 20px;

    background: rgba(4, 89, 144, .26);

    box-shadow: -1px 0.208vw 1.562vw 1px rgba(62, 81, 110, .26);
    border-radius: .52vw;
}

.hotAct {
    height: 320px;
    display: flex;
    position: relative;
    margin-bottom: 60px;
    /* background-color: #4d7190; */
    background-color: #33709e;
    border-radius: 28px;
    padding: 30px;
    transition: all .5s;
    cursor: pointer;
}

.hotAct:hover {
    transform: scale(1.02);
}

.hotActImg {
    width: 90%;
    height: 100%;
    cursor: pointer;
    margin-right: 30px;
    transition: all .2s;

    img {
        width: 96%;
        height: 99%;
        border-radius: 16px;
    }

}



.hotActText {
    height: 100%;
    padding-top: 15px;
    display: flex;
    flex-direction: column;

}

.hotActText_title {
    font-size: 1.145vw;
    margin-bottom: 1.354vw;
    padding-bottom: 30px;
    border-bottom: 1px solid #647e9e;
}

.hotActText_text {
    display: flex;
    align-items: center;
    height: 160px;
    font-size: 14px;
    margin-bottom: 40px;
}

.hotActText_time {
    font-size: 14px;
    color: #999999;
    line-height: 27px;

}

.detail {
    position: absolute;
    bottom: 0;
    right: 0;
}

.acts {
    display: flex;
    justify-content: space-between;
    flex-wrap: wrap;
    width: 100%;
}

.act {
    width: 31%;

}

/* 引入act卡片 */
.ag-courses_item {
    width: 100%;
    margin: auto;
    overflow: hidden;
    border-radius: 28px;

}

.ag-courses-item_link {
    display: block;
    min-height: 210px;
    padding: 30px 20px;
    background-color: #33709e;
    overflow: hidden;
    position: relative;
    text-decoration: none;
    cursor: pointer;
}

.ag-courses-item_link:hover,
.ag-courses-item_link:hover .ag-courses-item_date {
    color: #4d7190;
}

.ag-courses-item_link:hover .ag-courses-item_bg {
    -webkit-transform: scale(10);
    -ms-transform: scale(10);
    transform: scale(10);
}

.ag-courses-item_title {
    min-height: 30px;
    margin: 0 0 30px;
    overflow: hidden;
    font-weight: bold;
    font-size: 20px;
    color: rgb(255, 255, 255);
    z-index: 2;
    position: relative;
}

.ag-courses-item_date-box {
    font-size: 18px;
    color: #fff;
    z-index: 2;
    position: relative;
}

.ag-courses-item_date {
    font-weight: bold;
    color: #a3c9eb;
    -webkit-transition: color 0.5s ease;
    -o-transition: color 0.5s ease;
    transition: color 0.5s ease;
}

.ag-courses-item_bg {
    height: 130px;
    width: 120px;
    background-color: #a3c9eb;
    z-index: 1;
    position: absolute;
    top: -75px;
    right: -75px;
    border-radius: 50%;
    -webkit-transition: all 0.5s ease;
    -o-transition: all 0.5s ease;
    transition: all 0.5s ease;
}

.menus {
    width: 26%;
    color: white;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    /* position: fixed;
    right: 3%; */

    .title {
        width: 90%;
        background-image: url(public/img/partImg/rightbg.png);
        padding: 20px;
        display: flex;
        justify-content: center;
        align-items: center;
        font-size: 26px;
        font-weight: 600;

        span {
            /* margin-left: 20%; */
            margin-top: 3%;
        }
    }

    .content {
        width: 86%;
        height: 100%;
        display: flex;
        flex-direction: column;
        background-image: url(public/img/disImg/border5.png);
        background-size: 100% 100%;
        display: flex;
        padding: 12% 13%;
        align-items: flex-start;

        font-size: 19px;
        font-weight: 600;

        span {
            margin-bottom: 8%;
        }
    }
}
</style>
