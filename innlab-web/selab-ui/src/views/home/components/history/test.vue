<template>
    <div class="clockmain">
        <div class="clockMainTitle">
            发展历史
        </div>
        <div class="changebtn">
            <!-- 向上箭头 -->
            <svg t="1721216671697" class="icon iconfont icon-arrow-up-bold" id="up-btn" @click="slideToNext()"
                viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4265" width="48"
                height="48">
                <path
                    d="M966.4 668.8l-435.2-432c-9.6-9.6-25.6-9.6-35.2 0l-441.6 432c-9.6 9.6-9.6 25.6 0 35.2 9.6 9.6 25.6 9.6 35.2 0l425.6-416 416 416c9.6 9.6 25.6 9.6 35.2 0S976 678.4 966.4 668.8z"
                    p-id="4266" fill="#ffffff"></path>
            </svg>
            <!-- 向下箭头 -->
            <svg t="1721216777901" class="icon iconfont icon-arrow-down-bold" id="down-btn" @click="slideToPrev()"
                viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1424" width="48"
                height="48">
                <path
                    d="M966.4 323.2c-9.6-9.6-25.6-9.6-35.2 0l-416 416-425.6-416c-9.6-9.6-25.6-9.6-35.2 0-9.6 9.6-9.6 25.6 0 35.2l441.6 432c9.6 9.6 25.6 9.6 35.2 0l435.2-432C976 345.6 976 332.8 966.4 323.2z"
                    p-id="1425" fill="#ffffff"></path>
            </svg>
        </div>
        <div class="mobilechangebtn">
            <!-- 向左箭头 -->
            <svg t="1722680567377" class="icon" id="up-btn" @click="slideToNext()" viewBox="0 0 1024 1024" version="1.1"
                xmlns="http://www.w3.org/2000/svg" p-id="4245" width="48" height="48">
                <path
                    d="M401.066667 512l302.933333 302.933333-59.733333 59.733334L341.333333 571.733333 281.6 512 341.333333 452.266667l302.933334-302.933334 59.733333 59.733334L401.066667 512z"
                    fill="#ffffff" p-id="4246"></path>
            </svg>
            <!-- 向右箭头 -->
            <svg t="1722680652664" class="icon" id="down-btn" @click="slideToPrev()" viewBox="0 0 1024 1024"
                version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="6083" width="48" height="48">
                <path
                    d="M761.055557 532.128047c0.512619-0.992555 1.343475-1.823411 1.792447-2.848649 8.800538-18.304636 5.919204-40.703346-9.664077-55.424808L399.935923 139.743798c-19.264507-18.208305-49.631179-17.344765-67.872168 1.888778-18.208305 19.264507-17.375729 49.631179 1.888778 67.872168l316.960409 299.839269L335.199677 813.631716c-19.071845 18.399247-19.648112 48.767639-1.247144 67.872168 9.407768 9.791372 21.984142 14.688778 34.560516 14.688778 12.000108 0 24.000215-4.479398 33.311652-13.439914l350.048434-337.375729c0.672598-0.672598 0.927187-1.599785 1.599785-2.303346 0.512619-0.479935 1.056202-0.832576 1.567101-1.343475C757.759656 538.879828 759.199462 535.391265 761.055557 532.128047z"
                    fill="#ffffff" p-id="6084"></path>
            </svg>
        </div>
        <div id="clockcontent" data-aos="fade-in" data-aos-duration="800" data-aos-delay="400">
            <div class="clockcard" v-for="(item, index) in historyList" :key="index"
                :style="{ transform: `translateY(${(index - offset) * 100}%)` }">
                <div class="clockcard-time">
                    {{ item.historyTime }}
                </div>
                <div class="clockcard-title">
                    {{ item.historyTitle }}
                </div>
                <div class="clockcard-passage">
                    <br>
                    {{ item.historyContent }}
                    <br>
                    <br>
                    If you stop, it's rock bottom. If you're still going, it's uphill. That's the best statement I've
                    ever heard
                    about a rock bottom.
                </div>
            </div>
        </div>

        <div id="clock" data-aos="fade-left" data-aos-duration="800" data-aos-delay="600">
            <div id="clock-center"></div>
            <div id="clock-pointer"></div>
            <div id="clock-table"></div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import { getHistoryList } from '@/api/history/history.ts';
import AOS from 'aos'
import 'aos/dist/aos.css'
// 定义响应式变量
const offset = ref(0);
const maxOffset = ref(0);
const minOffset = ref(0);
const clock = ref<HTMLElement | null>(null); // 时钟表盘元素

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

// 动态计算最小偏移量和最大偏移量
maxOffset.value = historyList.value.length - 1;

const getHistorys = async () => {
    try {
        const result = await getHistoryList();
        if (result.code == 200) {
            historyList.value = result.data.records; // 逆序数组
            maxOffset.value = historyList.value.length - 1; // 更新最大偏移量
        }
    } catch (error) {
        console.error('Error fetching data:', error);
    }
};

// 在组件挂载时进行初始化操作
onMounted(async () => {

    await getHistorys();
    AOS.init();
    // 获取时钟表盘元素
    clock.value = document.querySelector("#clock-table");

    // 计算每个粗刻度之间的角度
    const totalDegrees = 360;
    const interval = totalDegrees / historyList.value.length;

    // 初始化时钟刻度和粗刻度
    for (let i = 0; i < historyList.value.length; i++) {
        const degree = i * interval;
        addClockScale(degree);
        addThickClockScale(degree, historyList.value[i].historyTime);
    }
});

// 添加时钟刻度
function addClockScale(degree: number): void {
    if (clock.value) {
        const invisibleClockTable = document.createElement("div");
        invisibleClockTable.className = "invisible-table";
        invisibleClockTable.style.transform = `rotate(${degree}deg)`;

        const clockScale = document.createElement("div");
        clockScale.className = "clock-scale";

        invisibleClockTable.appendChild(clockScale);
        clock.value.appendChild(invisibleClockTable);
    }
}

// 添加粗刻度
function addThickClockScale(degree: number, time: string): void {
    if (clock.value) {
        const invisibleClockTable = document.createElement("div");
        invisibleClockTable.className = "invisible-table";
        invisibleClockTable.style.transform = `rotate(${degree}deg)`;

        const thickClockScale = document.createElement("div");
        thickClockScale.className = "clock-thick";

        const scaleContent = document.createElement("span");
        scaleContent.textContent = `${time}`;

        thickClockScale.appendChild(scaleContent);
        invisibleClockTable.appendChild(thickClockScale);
        clock.value.appendChild(invisibleClockTable);
    }
}

// 向前切换卡片
function slideToPrev(): void {
    if (offset.value > 0) {
        offset.value -= 1;
        updateClock();
    }
}

// 向后切换卡片
function slideToNext(): void {
    if (offset.value < maxOffset.value) {
        offset.value += 1;
        updateClock();
    }
}

// 更新钟表
function updateClock(): void {
    const totalDegrees = 360;
    const interval = totalDegrees / historyList.value.length;
    const degree = -offset.value * interval;
    if (clock.value) {
        clock.value.style.transform = `rotate(${degree}deg)`;
    }
}
</script>

<style>
.clockmain {
    width: 100vw;
    height: 100vh;
    display: flex;
    position: relative;
    /* background-color: #5053fc; */
    overflow: hidden;
}

.clockMainTitle {
    color: white;
    position: absolute;
    top: 3%;
    left: 2%;
    font-size: 32px;
}

.changebtn {
    display: block;
}

.mobilechangebtn {
    display: none;
}

.iconfont {
    width: 80px;
    height: 80px;
    position: absolute;
    left: 27%;
    z-index: 2;
    font: 900 80px '';
    color: #fff;
}

#up-btn {
    top: 12%;
}

#down-btn {
    bottom: 12%;
}

#clockcontent {
    width: 30%;
    height: 100%;
    position: absolute;
    left: 15%;
    overflow: hidden;
}

.clockcard {
    width: 100%;
    height: 100%;
    position: absolute;
    display: flex;
    flex-direction: column;
    justify-content: center;
    color: white;
    transition: transform .8s ease-in-out;
}

.clockcard-time {
    font-size: 40px;
    font-weight: 700;
}

.clockcard-title {
    font-size: 50px;
    font-weight: 500;
    padding-bottom: 10px;
    border-bottom: 1px solid white;
    margin-bottom: 10px;
}

.clockcard-passage {
    font-size: 24px;
    font-weight: 300;
}

#clock {
    height: 106%;
    /* 纵横比为1:1 */
    aspect-ratio: 1 / 1;
    position: absolute;
    right: -35%;
    top: -4%;
    border-radius: 50%;
    background-color: white;
    border: #214d98 30px solid;
}



#clock-table {
    width: 96%;
    height: 96%;
    border-radius: 50%;
    position: absolute;
    top: 2%;
    left: 2%;
    transition: transform .8s ease-in-out;
}

.invisible-table {
    width: 100%;
    height: 100%;
    border-radius: 50%;
    transform-origin: 50% 50%;
    position: absolute;
}

.clock-thick {
    width: 6%;
    height: 6px;
    background-color: #025db3;
    position: absolute;
    top: calc(50% - 3px);
    left: 0px;
}

.clock-thick span {
    font-size: 40px;
    position: absolute;
    left: 140%;
    top: calc(50% - 30px);
    color: #025db3;
}

.clock-scale {
    width: 4%;
    height: 2px;
    background-color: #025db3;
    position: absolute;
    top: calc(50% - .5px);
    left: 0px;
}

@media(max-width:1284px) {
    .clockmain {
        width: 100vw;
        height: 55vh;
        min-height: 55vh !important;
        display: flex;
        align-items: center;
    }

    #clock {

        display: none;
    }

    .changebtn {
        display: none;
    }

    .mobilechangebtn {
        width: 100%;
        height: 100%;
        display: block;
        position: relative;
    }

    #up-btn {
        top: 50%;
        left: 0;
        position: absolute;
    }

    #down-btn {
        top: 50%;
        right: 0;
        position: absolute;
    }

    #clockcontent {
        width: 75%;
        left: 15%;
    }

    .iconfont {

        left: 39%;

    }

    .clockMainTitle {
        color: white;
        position: absolute;
        top: 3%;
        left: 5%;
        font-size: 22px;
    }

    .clockcard {
        width: 89%;
    }

    .clockcard-time {
        font-size: 25px;
    }

    .clockcard-title {
        font-size: 35px;
    }

    .clockcard-passage {
        font-size: 18px;
        font-weight: 300;
    }
}
</style>
