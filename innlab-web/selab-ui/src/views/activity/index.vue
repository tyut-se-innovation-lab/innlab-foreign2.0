<template>
    <div class="outer">
        <!-- 头部 -->
        <div class="header">
            <Header></Header>
        </div>

        <main>
            <div class="container">

                <div class="top">

                    <div class="hotActs">
                        <el-carousel ref="carousel" motion-blur indicator-position="none">
                            <el-carousel-item v-for="item in ActivitiesList" :key="item">
                                <div class="hotAc" @click="linkTo(item.activityId)">
                                    <img :src="item.headerImage" alt="">
                                    <div class="hotAcText">
                                        <h5>{{ item.activityTitle }}</h5>
                                        <span class="hotAcDes">{{ item.activityIntroduction }}</span>
                                        <span class="hotAcTime">{{ item.createTime }}</span>
                                    </div>
                                </div>
                            </el-carousel-item>
                        </el-carousel>

                    </div>

                    <ul class="notices">
                        <li class="notTitle">
                            <p>通知公告</p>
                            <p>所有的伟大，都源于一个勇敢的开始</p>
                        </li>

                        <ul class="nots">
                            <li class="not">
                                <a href="javascript:;">
                                    <b>通知：</b>
                                    <span>系统升级到2.0</span>
                                </a>
                            </li>
                            <li class="not">
                                <a href="javascript:;">
                                    <b>通知：</b>
                                    <span>系统升级到2.0</span>
                                </a>
                            </li>
                            <li class="not">
                                <a href="javascript:;">
                                    <b>通知：</b>
                                    <span>系统升级到2.0</span>
                                </a>
                            </li>
                            <li class="not">
                                <a href="javascript:;">
                                    <b>通知：</b>
                                    <span>系统升级到2.0</span>
                                </a>
                            </li>
                            <li class="not">
                                <a href="javascript:;">
                                    <b>通知：</b>
                                    <span>系统升级到2.0</span>
                                </a>
                            </li>
                            <li class="not">
                                <a href="javascript:;">
                                    <b>通知：</b>
                                    <span>系统升级到2.0</span>
                                </a>
                            </li>
                        </ul>


                    </ul>
                </div>

                <div class="middle">

                </div>

                <div class="bottom">
                    <div class="botHead">
                        <h3>列表</h3>
                        <div class="botTags">
                            <el-tag :class="{ 'el-tag-act': index == actTag }" v-for="(item, index) in [
                                { label: 'Tag 1' },
                                { label: 'Tag 2' },
                                { label: 'Tag 3' },
                                { label: 'Tag 4' },
                                { label: 'Tag 5' },
                            ]" :key="item.label" @click="handleTag(index)" type="info" effect="light" round>
                                {{ item.label }}
                            </el-tag>
                        </div>
                    </div>
                    <div class="botlists">
                        <div class="botlist" v-for="item in ActivitiesList" @click="linkTo(item.activityId)">
                            <div class="botImg">
                                <img :src="item.headerImage" alt="">
                            </div>
                            <div class="botText">
                                <div class="botTextTag">
                                    <span>分类</span>
                                </div>
                                <h3>{{ item.activityTitle }}</h3>
                                <p>{{ item.activityIntroduction }}
                                </p>
                                <div class="botDinner">
                                    <div class="botWriter">
                                        <img src="https://static.7b2.com/wp-content/uploads/2023/01/StockSnap_BTFENA5E5V_1_avatar_aQMZX13321.jpg?x-oss-process=image/resize,m_fill,h_120,w_120/sharpen,120/format,webp"
                                            alt="">
                                        <span>作者</span>
                                    </div>
                                    <div class="botTime"><span>{{ item.createTime }}</span></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <el-pagination background layout="prev, pager, next" v-model:current-page="params.pageNum"
                        v-model:page-size="params.pageSize" :total="total" @size-change="handleSizeChange"
                        @current-change="handleCurrentChange" />
                </div>

            </div>

        </main>


    </div>
</template>
<script setup lang="ts">
import { ref, reactive, onMounted, defineProps } from "vue"
import Header from '@/components/header/header.vue'
import bus from '@/eventBus';
import { getActs } from '@/api/activity/activity'
import { parseLanzouLink } from '@/utils/getFileByBackend';
import router from '@/router';

const params = ref({
    pageNum: 1,
    pageSize: 10,
    // activityType: '软件开发'
})
const total = ref(10);
const ActivitiesList = ref<Array<{ itemId: number; itemTitle: string; itemIntroduction: string; createTime: string }>>([]);
ActivitiesList.value = [{
    "activityId": 6,
    "activityTitle": "觉醒年代",
    "activityIntroduction": "民国的故事不只是军阀阔太，才子佳人 ，更是那些撕裂黑暗的熊熊焰火。",
    "headerImage": {
        "pwd": "gqr7",
        "isNewd": "https://www.lanzouh.com",
        "url": null,
        "fid": "i8wCU24r5hkb"
    },
    "pageView": 0,
    "createTime": "2024-07-17 22:15:22"
}]
const getActivitiesList = async () => {
    try {
        const result = await getActs(params.value);
        ActivitiesList.value = result.data.records;
        ActivitiesList.value.forEach(async e => {
            e.headerImage = await parseLanzouLink(e.headerImage);
        })
        total.value = result.data.total;

    } catch (error) {
        console.error('Error fetching data:');
    } finally {

    }
};

const handleSizeChange = () => {
    getActivitiesList();
}
const handleCurrentChange = () => {
    getActivitiesList();
}


// 点击跳转
const linkTo = (item: number) => {
    router.push({ path: '/blog', query: { actid: item } });

}

const actTag = ref(0);
const handleTag = (index) => {
    actTag.value = index;
}

onMounted(() => {
    setTimeout(() => {
        bus.emit('loading', false);
    }, 1200); // 将 setTimeout 的延迟设置为 0 毫秒，确保在下一个事件循环中执行
    window.scrollTo({ top: 0, behavior: 'smooth' });
    getActivitiesList();
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
    align-items: center;
    /* margin: 100px 0; */
    padding-top: 100px;
    padding-bottom: 100px;
    /* background-image: url(/public/img/homeImg/mohu.jpg); */
    background-color: #f5f6f7;
    background-image: linear-gradient(180deg, #f6f1ef 0, #f0f1f6 42%, #ebf3fb 100%);
}



/* main */
main {
    width: 100%;
    min-height: 1200px;
    display: flex;
    justify-content: center;
    /* margin: 60px 0; */
    margin: 2% 0;

}

.container {
    width: 75%;
    padding: 20px;

    /* background: rgba(4, 89, 144, .26);
    box-shadow: -1px 0.208vw 1.562vw 1px rgba(62, 81, 110, .26);
    border-radius: .52vw; */
}

.top {
    width: 100%;
    height: 25em;
    display: flex;
    justify-content: space-between;
}

.hotActs {
    width: 65%;
    height: 100%;
    border-radius: 4px;
}

:deep(.el-carousel--horizontal) {
    height: 100%;
}

:deep(.el-carousel__container) {
    height: 100%;
}


.hotAc {
    width: 100%;
    height: 100%;

    position: relative;
    display: flex;
    align-items: center;
    justify-content: center;
    border: 1px solid rgba(100, 103, 105, 0.1);
    box-shadow: 0px 0px 2px rgba(69, 85, 103, 0.1);

    img {
        width: 100%;
        height: 100%;
        object-fit: cover;
        border-radius: 4px;
    }
}

.hotAcText {
    width: 100%;
    height: 35%;
    padding: 2%;
    color: white;
    border-radius: 4px;
    display: flex;
    flex-direction: column;
    justify-content: space-around;

    background: linear-gradient(1turn, rgba(9, 15, 29, .4), transparent);

    position: absolute;
    bottom: 0;
    left: 0;

    h5 {
        font-size: 1.4em;
    }

    .hotAcDes {
        font-size: .96em;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
    }

    .hotAcTime {
        font-size: .8em;
    }
}

.notices {
    width: 32%;
    height: 100%;
    background: white;
    border-radius: 4px;
    border: 1px solid rgba(100, 103, 105, 0.1);
    box-shadow: 0px 0px 2px rgba(69, 85, 103, 0.1);
}

.notTitle {
    position: relative;
    width: 100%;
    height: 30%;
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
    font-size: 14px;
    padding: 24px 20px;
    background-image: -webkit-gradient(linear, left top, right top, from(#fff2ec), color-stop(42%, #f0f1f6), to(#e1f0ff));
    background-image: linear-gradient(90deg, #fff2ec 0, #f0f1f6 42%, #e1f0ff 100%);
    position: relative;

    p:nth-child(1) {
        color: #0066ff;
        display: block;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
        position: relative;
        z-index: 2;
    }
}

.notTitle::after {
    content: '';
    position: absolute;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    background-image: -webkit-gradient(linear, left bottom, left top, from(#fff), to(rgba(247, 248, 249, 0)));
    background-image: linear-gradient(0deg, #fff 0, rgba(247, 248, 249, 0) 100%);
}

.nots {
    height: 68%;
    display: flex;
    padding: 0 5%;
    flex-direction: column;
    justify-content: space-around;
}

.not {
    width: 100%;

    a {
        display: block;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
        font-size: 12px;
        position: relative;
        padding-left: 17px;
    }
}

.not a::before {
    background-color: #0066ff;
    content: '';
    width: 6px;
    height: 6px;
    position: absolute;
    left: 0;
    top: 5px;
    border-radius: 10px;
}



.bottom {
    width: 100%;
    min-height: 20%;
    background-color: white;
    display: flex;
    flex-direction: column;
    padding: 2% 3%;
    border-radius: 4px;
    margin: 2% 0;
    padding-top: 1%;
    border: 1px solid rgba(100, 103, 105, 0.1);
    box-shadow: 0px 0px 2px rgba(69, 85, 103, 0.1);
}

.botHead {
    width: 100%;
    height: 4em;
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 1px solid #f5f6f7;

    h3 {
        font-size: 1.5em;
        font-weight: normal;
    }
}

.botTags {
    width: 40%;
    height: 100%;
    display: flex;
    justify-content: space-evenly;
    align-items: center;
}

:deep(.el-tag) {
    cursor: pointer;
    width: 6em;
    height: 2.8em;
    transition: all .2s;
}

:deep(.el-tag):hover {
    color: white;
    background-color: #5e8ed6;
}

:deep(.el-tag-act) {
    color: white;
    background-color: #5e8ed6;
}

.botlists {
    width: 100%;
    /* height: 80%; */
    display: flex;
    flex-direction: column;

}

.botlist {
    cursor: pointer;
    width: 100%;
    height: 13em;
    display: flex;
    border-bottom: 1px solid #F5F6F7;
    padding: 1% 0;
}

.botImg {
    width: 25%;
    height: 100%;

    img {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }
}

.botText {
    width: 85%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    padding: 1% 3%;

    .botTextTag {
        width: 100%;
        display: flex;

        span {
            color: rgb(79, 157, 218);
            letter-spacing: 1px;
        }
    }

    h3 {
        font-size: 18px;
        font-weight: normal;
    }

    p {
        width: 100%;
        font-size: 14px;
        color: #797C80;
        /* 超过两行省略 */
        display: -webkit-box;
        -webkit-line-clamp: 2;
        -webkit-box-orient: vertical;
        overflow: hidden;
    }

    .botDinner {
        width: 100%;
        display: flex;
        justify-content: space-between;
        align-items: center;

        .botWriter {
            width: 30%;
            display: flex;
            align-items: center;

            img {
                width: 1.5em;
                height: 1.5em;
                border-radius: 50%;
            }

            span {
                color: #797C80;
                font-size: 12px;
                margin-left: .6em;
            }
        }

        .botTime {
            min-width: 15%;
            color: #797C80;
            font-size: 12px;
            letter-spacing: 1px;
        }
    }
}

/* 分页 */
:deep(.el-pagination) {
    justify-content: flex-end;
    margin: 2% 0;
}

:deep(.el-pagination.is-background .el-pager li:not(.is-disabled).is-active) {
    background-color: #579ad3;
}
</style>
