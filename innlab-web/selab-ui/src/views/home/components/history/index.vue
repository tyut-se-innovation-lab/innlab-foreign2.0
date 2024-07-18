<template>
    <div class="homeHistory">
        <div class="container">
            <div class="timeline-container">
                <div class="time-top">
                    <img data-aos="fade-in" data-aos-duration="800" class="timeTitle" src="public/img/titleImg/time.png"
                        alt="">
                </div>
                <div class="timeline" ref="timeline">
                    <div class="historys" v-for="(item, index) in historyList" data-aos="fade-in"
                        data-aos-duration="1000" :data-aos-delay="index * 100">
                        {{ item.historyTime }}
                        <span class="arrow" v-if="index !== historyList.length - 1">⇾</span>
                        <div v-if="!isOdd(index)" class="history-up">
                            <img src="public/img/homeImg/timeline-up.png" alt="">
                            <Tooltip :title="item.historyTitle" :content="item.historyContent"></Tooltip>
                        </div>
                        <div v-if="isOdd(index)" class="history-down">
                            <img src="public/img/homeImg/timeline-down.png" alt="">
                            <Tooltip style="margin-top:50px;" :title="item.historyTitle" :content="item.historyContent">
                            </Tooltip>
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
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from "vue"
import { getHistoryList } from '@/api/history/history.ts';
import Tooltip from '@/components/tooltip/index.vue'
import AOS from 'aos'
import 'aos/dist/aos.css'

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
    AOS.init();
    getHistorys();

})

</script>

<style scoped>
.homeHistory {
    width: 100%;
    /* height: 600px; */
    padding: 0 120px;
    /* background-image: url(); */

}

.container {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    overflow: hidden;
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

.timeline-container {
    width: 90%;
    /* overflow-x: auto; */
    /* 允许水平滚动 */
    overflow: hidden;
    white-space: nowrap;
    /* 防止内容换行 */
    padding-bottom: 120px;
    /* margin-top: 90px; */
    position: relative;
    cursor: pointer;
}

.timeline-container::-webkit-scrollbar {
    height: 8px;
}

.timeline-container::-webkit-scrollbar-track {
    background-color: #09284e;
    /* 设置滑块背景色 */
    border-radius: 5px;
    /* 设置滑块圆角 */
}

.timeline-container::-webkit-scrollbar-thumb {
    width: 20px;
    background: linear-gradient(80deg, #5dccf8, #1a3d68);
    /* 设置滑块背景色 */
    border-radius: 5px;
    /* 设置滑块圆角 */
}


.timeline {
    min-width: 1700px;
    width: fit-content;
    height: 50px;
    background-image: url(public/img/homeImg/timeline.png);
    background-repeat: no-repeat;
    background-size: 100% 160px;
    background-position: 1%;
    display: flex;
    align-items: center;
    padding-left: 6%;
    padding-right: 15%;
    transition: transform 0.3s ease;
    margin-top: 12%;
    margin-bottom: 5%;
}

.historys {
    display: flex;
    font-size: 20px;
    color: white;
    padding-left: 160px;
    position: relative;
}

.arrow {
    position: absolute;
    left: 126%;
}

.history-up {
    width: 220px;
    height: 130px;
    position: absolute;
    bottom: 36px;
    left: 140px;
    display: flex;
}

.history-down {
    width: 220px;
    height: 130px;
    position: absolute;
    top: 36px;
    left: 140px;
    display: flex;
}

.scroll {
    width: 100%;
    height: 6%;
    display: flex;
    justify-content: flex-end;
    padding-right: 6%;
}

.linemore {
    width: 6%;
    height: 100%;
    background-color: #1f78a7;
}

.linemore:nth-child(1) {
    margin-right: 2%;
}
</style>
