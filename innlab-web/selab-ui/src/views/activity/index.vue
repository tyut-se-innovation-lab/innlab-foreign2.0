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
                            <el-carousel-item v-for="item in HotActivitiesList" :key="item">
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
                            <p>站内公告</p>
                            <p>所有的伟大，都源于一个勇敢的开始</p>
                        </li>

                        <ul class="nots">
                            <li class="not" v-for="item in NoticeList">
                                <a href="javascript:;" @click="linkTo(item.activityId)">
                                    <b>{{ item.activityTitle }}：</b>
                                    <span>{{ item.activityIntroduction }}</span>
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
                                { label: '全部' },
                                { label: '日常活动' },
                                { label: '技术分享' },
                                { label: '资料宝库' },
                                { label: '软件分享' },
                            ]" :key="item.label" @click="handleTag(index, item.label)" type="info" effect="light"
                                round>
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

                                <h3>{{ item.activityTitle }}</h3>
                                <p>{{ item.activityIntroduction }}
                                </p>
                                <div class="botDinner">
                                    <div class="botWriter">
                                        <span>作者：</span>
                                        <span>{{ item.author ? item.author : '匿名' }}</span>
                                    </div>
                                    <div class="botTime"><span>{{ item.createTime }}</span></div>
                                </div>
                            </div>
                        </div>
                        <div v-if="total == 0" class="botlistnone">
                            <span>暂无内容...</span>
                        </div>
                    </div>
                    <el-pagination v-show="total !== 0" background layout="prev, pager, next"
                        v-model:current-page="params.pageNum" v-model:page-size="params.pageSize" :total="total"
                        @size-change="handleSizeChange" @current-change="handleCurrentChange" />
                </div>

            </div>

        </main>

    </div>
</template>
<script setup lang="ts">
import { ref, reactive, onMounted, defineProps } from "vue"
import Header from '@/components/header/header.vue'
import bus from '@/eventBus';
import { getAllActs, getActs } from '@/api/activity/activity'
import { parseLanzouLink } from '@/utils/getFileByBackend';
import router from '@/router';

// 热门动态
const hotparams = ref({
    pageNum: 1,
    pageSize: 10,
    activityType: '热门'
})
const HotActivitiesList = ref<Array<{ itemId: number; itemTitle: string; itemIntroduction: string; createTime: string }>>([]);
HotActivitiesList.value = [{}];
const getHotActivitiesList = async () => {
    try {
        const result = await getActs(hotparams.value);
        HotActivitiesList.value = result.data.records;
        HotActivitiesList.value.forEach(async e => {
            e.headerImage = await parseLanzouLink(e.headerImage);
        })
    } catch (error) {
        console.error('Error fetching data:');
    } finally {

    }
};

// 公告
// 热门动态
const notparams = ref({
    pageNum: 1,
    pageSize: 6,
    activityType: '站内公告'
})
const NoticeList = ref<Array<{ itemId: number; itemTitle: string; itemIntroduction: string; createTime: string }>>([]);
NoticeList.value = [{}];
const getNoticeList = async () => {
    try {
        const result = await getActs(notparams.value);
        NoticeList.value = result.data.records;
        NoticeList.value.forEach(async e => {
            e.headerImage = await parseLanzouLink(e.headerImage);
        })
    } catch (error) {
        console.error('Error fetching data:');
    } finally {

    }
};

// 分类活动
const params = ref({
    pageNum: 1,
    pageSize: 10,
    activityType: '全部'
})
const total = ref(10);

const ActivitiesList = ref<Array<{ itemId: number; itemTitle: string; itemIntroduction: string; createTime: string }>>([]);
ActivitiesList.value = [{}];
const getAllActivitiesList = async () => {
    try {
        const result = await getAllActs(params.value);
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

const actTag = ref(0);
const handleTag = (index, type) => {
    actTag.value = index;
    params.value.activityType = type;

    if (type == '全部') {
        getAllActivitiesList();
    } else {
        getActivitiesList();
    }
}


const handleSizeChange = () => {
    if (params.value.activityType == '全部') {
        getAllActivitiesList();
    } else {
        getActivitiesList();
    }

}
const handleCurrentChange = () => {
    if (params.value.activityType == '全部') {
        getAllActivitiesList();
    } else {
        getActivitiesList();
    }
}


// 点击跳转
const linkTo = (item: number) => {
    router.push({ path: '/blog', query: { actid: item } });

}

onMounted(() => {
    setTimeout(() => {
        bus.emit('loading', false);
    }, 1200); // 将 setTimeout 的延迟设置为 0 毫秒，确保在下一个事件循环中执行
    window.scrollTo({ top: 0, behavior: 'smooth' });
    getHotActivitiesList();
    getNoticeList();
    getAllActivitiesList();
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

}

.hotAc img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    border-radius: 4px;
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

}

.hotAcText h5 {
    font-size: 1.4em;
}

.hotAcText .hotAcDes {
    font-size: .96em;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
}

.hotAcText .hotAcTime {
    font-size: .8em;
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

}

.not {
    width: 100%;
    margin: 3% 0;
}

.not a {
    display: block;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    font-size: 12px;
    position: relative;
    padding-left: 17px;
    transition: all .2s;
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

.not:hover a {
    cursor: pointer;
    transform: translateX(.3em);
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
    min-height: 4em;
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 1px solid #f5f6f7;


}

.botHead h3 {
    font-size: 1.5em;
    font-weight: normal;
}

.botTags {
    width: 40%;
    min-height: 100%;
    display: flex;
    justify-content: space-evenly;
    align-items: center;
    flex-wrap: wrap;
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
    border-bottom: 1px solid #ebebeb;
    padding: 1% 0;
}

.botImg {
    width: 25%;
    height: 100%;

}

.botImg img {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.botText {
    width: 85%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    padding: 1% 3%;


}

.botText .botTextTag {
    width: 100%;
    display: flex;

}

.botText .botTextTag span {
    color: rgb(79, 157, 218);
    letter-spacing: 1px;
}

.botText h3 {
    font-size: 18px;
    font-weight: normal;
}

.botText p {
    width: 100%;
    font-size: 14px;
    color: #797C80;
    /* 超过两行省略 */
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    overflow: hidden;
}

.botText .botDinner {
    width: 100%;
    display: flex;
    justify-content: space-between;
    align-items: center;




}

.botText .botDinner .botWriter {
    width: 30%;
    display: flex;
    align-items: center;



}

.botText .botDinner .botWriter img {
    width: 1.5em;
    height: 1.5em;
    border-radius: 50%;
}

.botText .botDinner .botWriter span {
    color: #797C80;
    font-size: 12px;
    /* margin-left: .6em; */
}

.botText .botDinner .botTime {
    min-width: 15%;
    color: #797C80;
    font-size: 12px;
    letter-spacing: 1px;
}

.botlistnone {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 1.3em;
    margin: 3% 0;
    /* background-color: #f1f1f1; */
}



/* 分页 */
:deep(.el-pagination) {
    justify-content: flex-end;
    margin: 2% 0;
}

:deep(.el-pagination.is-background .el-pager li:not(.is-disabled).is-active) {
    background-color: #579ad3;
}

/* 移动端适配 */
@media (max-width: 1284px) {
    .header {
        display: none;
    }

    .container {
        width: 100%;
        height: 100%;
        padding: 6%;
    }

    .top {
        width: 100%;
        height: 100%;
        display: flex;
        justify-content: space-between;
        flex-direction: column;
    }

    .hotActs {
        width: 100%;
        height: 18em;
        margin: 2% 0 6% 0;
    }

    .notices {
        width: 100%;
        height: 70%;
        margin: 2% 0 6% 0;
    }

    .not {
        margin: 3% 0;
    }

    .botHead h3 {

        display: none;
    }

    .botTags {
        width: 100%;
        /* min-height: 100%; */
        justify-content: flex-start;
        margin: .1em;
    }

    :deep(.el-tag) {

        max-width: 5em;
        height: 2.8em;
        /* margin-right: 0.7em; */
        margin: .6em;
    }

    .botlistnone {
        width: 100%;
        height: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
        font-size: 1.5em;
        margin: 11% 0;
    }

    .botlist {

        height: 23em;
        padding: 1% 0;
        flex-direction: column;
        margin: 1em 0;
    }

    .botImg {
        width: 100%;
        height: 12em;
    }

    .botText {
        width: 100%;
        height: 100%;

    }
}
</style>
