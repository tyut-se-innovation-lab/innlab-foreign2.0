<template>
    <div class="people" v-loading="loading">



        <div class="header">
            <router-link to="/">
                <img width="200px" src="public/img/disImg/logo.png" alt="">
            </router-link>
            <span>人员介绍</span>
            <Sidebar v-if="!isMobile"></Sidebar>

        </div>

        <div class="topTabs">
            <el-tabs v-model:active-name="activePartTab" @tab-click="handlePart" type="border-card" class="partTabs">
                <el-tab-pane name="软件开发" label="软件开发"></el-tab-pane>
                <el-tab-pane name="网络安全" label="网络安全"></el-tab-pane>
                <el-tab-pane name="人工智能" label="人工智能"></el-tab-pane>
                <el-tab-pane name="虚拟现实" label="虚拟现实"></el-tab-pane>
                <el-tab-pane name="算法竞赛" label="算法竞赛"></el-tab-pane>
            </el-tabs>
        </div>

        <div class="main">

            <div class="leftparts">
                <el-tabs v-model:active-name="activePeriodTab" @tab-click="handlePeriod" tab-position="left"
                    class="demo-tabs">
                    <el-tab-pane name="2019" label="2019"></el-tab-pane>
                    <el-tab-pane name="2020" label="2020"></el-tab-pane>
                    <el-tab-pane name="2021" label="2021"></el-tab-pane>
                    <el-tab-pane name="2022" label="2022"></el-tab-pane>
                </el-tabs>
            </div>

            <div class="info" v-if="showInfo">

                <div class="mainpeople">
                    <div class="top">
                        <div class="top_img">
                            <img :src="people.personnelAvatar" alt="">
                        </div>
                        <div class="top_title">
                            <div class="topline">
                                <p class="topline_title">
                                    姓名：
                                </p>
                                <span class="topline_content">
                                    {{ people.personnelName }}
                                </span>
                            </div>
                            <div class="topline">
                                <p class="topline_title">
                                    年代：
                                </p>
                                <span class="topline_content">
                                    {{ people.personnelPeriod }}
                                </span>
                            </div>
                            <div class="topline">
                                <p class="topline_title">
                                    部门：
                                </p>
                                <span class="topline_content">
                                    {{ people.personnelDepartment }}
                                </span>
                            </div>
                            <div class="topline">
                                <p class="topline_title">
                                    职位：
                                </p>
                                <span class="topline_content">
                                    {{ people.personnelPost }}
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="bottom">
                        <div class="bottomline">
                            <p class="bottomline_title">
                                贡献：
                            </p>
                            <p class="bottomline_content">
                                {{ people.personnelContribute ? people.personnelContribute : '暂无' }}
                            </p>
                        </div>
                        <div class="bottomline">
                            <p class="bottomline_title">
                                语录：
                            </p>
                            <p class="bottomline_content">
                                {{ people.personnelSaying ? people.personnelSaying : '暂无' }}
                            </p>
                        </div>
                    </div>
                </div>


            </div>
            <div class="more" v-if="showMore">

                <div class="cards">
                    <div class="card" v-for="item in PeopleList" @click="toDetail(item)">
                        <img :src="item.personnelAvatar" style="width:100%;" alt="">
                        <div class="card__content">
                            <p class="card__title">{{ item.personnelName }}</p>
                            <p class="card__description">开发组组长</p>
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                                eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>
                        </div>
                    </div>
                </div>
                <el-pagination v-model:current-page="PeopleListParams.pageParam.pageNum"
                    v-model:page-size="PeopleListParams.pageParam.pageSize" :page-sizes="[3]"
                    layout=" prev, pager, next" :total="total" @size-change="handleSizeChange"
                    @current-change="handleCurrentChange" />


            </div>
        </div>



    </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted, onUnmounted, nextTick } from "vue"
import { useRouter } from 'vue-router';
import { useRoute } from 'vue-router';
import { watch } from 'vue';
import Sidebar from '@/components/header/sidebar.vue'

import { getPeoples } from "@/api/people/people"
import { parseLanzouLink } from '@/utils/getFileByBackend';
import bus from '@/eventBus';
import type { TabsInstance } from 'element-plus'
import { decrypt, encrypt } from '@/utils/crypto';

const tabPosition = ref<TabsInstance['tabPosition']>('left')
const loading = ref(false);
const router = useRouter();
const routeQuery = useRoute();


const isDetail = ref(true);
const showInfo = ref(false);
const showMore = ref(false);

const updateVisibility = async () => {
    await nextTick();
    console.log('iiis: ', isDetail.value);

    showInfo.value = isDetail.value;
    console.log('iiisshowInfo: ', showInfo.value);
    showMore.value = !isDetail.value;
    console.log('iiisshowMore: ', showMore.value);
};

watch(isDetail, async () => {
    await updateVisibility();
});

const actImgs = ref([
    "img/bannerImg/banner1.jpg",
    "img/bannerImg/banner2.jpg",
    "img/bannerImg/banner3.jpg",
    "https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/0b812143bfbbf673dfe8b16704c7ab7728aeac73c1039e31955cba84abe71fd0c3d17ccdd08a3f885bd5fb6fe36db22b?pictype=scale&from=30113&version=3.3.3.3&fname=jingtaiziyuan.jpg&size=750",
    "https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/c951d90e6913552328b07448020ec877a6f70a1024901065ff8530221a02c88c21bb7cf66c754b9566af68fb483e0c54?pictype=scale&from=30113&version=3.3.3.3&fname=154928-yi_shu-chuang_kou-guang-zi_se_de-azure-1366x768.jpg&size=750",
    "https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/f670b4b15d9186612e895d916bca6fd49caf113a22ee951cbd2b6640dd99c08603c40c531acc75571bc5a172bfe5499b?pictype=scale&from=30113&version=3.3.3.3&fname=shouye.jpg&size=750",
    "https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/8e62988df6d5300d965775e34ef70400ca4fa14c3f65b6680a8ce09585af2ef04379d3e1e79daa8dff43f1c8682ba2d3?pictype=scale&from=30113&version=3.3.3.3&fname=start.jpg&size=750",
])


// 初始标签
const activePartTab = ref(''); // 保存当前选中的部门标签
const activePeriodTab = ref(''); // 保存当前选中的年代标签

const initializeTabs = async () => {
    await nextTick(); // 等待 DOM 更新完成

    // 从路由查询中获取参数
    const department = router.currentRoute.value.query.part || '软件开发';
    const period = router.currentRoute.value.query.period || '2022';

    // 设置标签组件的选中项
    activePartTab.value = department;
    activePeriodTab.value = period;

    // 执行获取人员列表的操作
    PeopleListParams.value.personnelPeriod = period;
    PeopleListParams.value.personnelDepartment = department;
    await getPeopleList();
    const detail = sessionStorage.getItem('isDetail');
    showInfo.value = isDetail.value;
    if (detail === 'false') {
        isDetail.value = false;
    }
    console.log('detaie: ', isDetail.value);
};

const people = ref<Record<string, any>>({});
const PeopleList = ref<Array<{}>>([]);

const total = ref(10)
const PeopleListParams = ref({
    pageParam: {
        pageNum: 1,
        pageSize: 10
    },
    personnelPeriod: "2022",
    personnelDepartment: "软件开发"
})
const getPeopleList = async () => {
    try {
        const result = await getPeoples(PeopleListParams.value);
        PeopleList.value = result.data.records;
        PeopleList.value.forEach(async e => {
            e.personnelAvatar = await parseLanzouLink(e.personnelAvatar);
        })
        total.value = result.data.total;
    } catch (error) {
        console.error('Error fetching data:');
    } finally {

    }
};
const handleSizeChange = () => {
    getPeopleList();
}
const handleCurrentChange = () => {
    getPeopleList();
}




// 切换部门
const handlePart = async (part: any) => {
    PeopleListParams.value.personnelDepartment = part.props.label;
    isDetail.value = false;
    await getPeopleList();
    loading.value = true;
    router.push({
        query: {
            part: part.props.label,
            period: PeopleListParams.value.personnelPeriod
        },
    });
    sessionStorage.setItem('isDetail', JSON.stringify(isDetail.value));

}
const handlePeriod = async (period: any) => {

    PeopleListParams.value.personnelPeriod = period.props.label;
    isDetail.value = false;
    await getPeopleList();
    router.push({
        query: {
            part: PeopleListParams.value.personnelDepartment,
            period: period.props.label
        },
    });
    sessionStorage.setItem('isDetail', JSON.stringify(isDetail.value));
}

const toDetail = (peopleitem) => {

    isDetail.value = true;

    router.push({
        query: {
            part: PeopleListParams.value.personnelDepartment,
            period: PeopleListParams.value.personnelPeriod,
            people: encrypt(JSON.stringify(peopleitem))
        },
    });
    sessionStorage.setItem('isDetail', JSON.stringify(isDetail.value));
}



// 监视路由参数的变化
// watch(
//     () => routeQuery.query,
//     () => {
//         // 执行页面刷新操作
//         // router.go(0);
//     },
//     { deep: true } // 深度监视对象的变化
// );
watch(() => routeQuery.query, async () => {
    await initializeTabs();
});

// 保存是否是移动设备的状态
const isMobile = ref(window.innerWidth <= 1248);
// 定义处理窗口大小变化的函数
function handleResize() {
    isMobile.value = window.innerWidth <= 1248;
}
onMounted(async () => {

    setTimeout(() => {
        bus.emit('loading', false);
    }, 1200);
    loading.value = false;
    // window.scrollTo({ top: 0, behavior: 'smooth' });
    // 在挂载时添加窗口大小变化的事件监听器
    window.addEventListener('resize', handleResize);

    if (routeQuery.query.people) {


        try {
            const encryptedPeople = routeQuery.query.people as string;
            people.value = JSON.parse(decrypt(encryptedPeople));


            people.value.personnelAvatar = await parseLanzouLink(people.value.personnelAvatar);

        } catch (error) {
            console.error('Error parsing people parameter:', error);
        }
    }

    await initializeTabs(); // 初始化标签选中项


});
onUnmounted(() => {
    // 在卸载时移除窗口大小变化的事件监听器
    window.removeEventListener('resize', handleResize);
});



</script>

<style scoped>
.people {
    display: flex;
    flex-direction: column;
    /* padding: 160px 80px; */
    /* background-color: rgb(240, 240, 240); */
    background-color: white;
    /* background-image: url(public/img/homeImg/bg2.jpg); */
    background-size: cover;
    /* padding-top: 10%; */
}

.header {
    width: 100%;
    height: 8em;
    background-color: #002d72;
    display: flex;
    align-items: center;
    position: relative;
    margin-bottom: 8%;

    a {
        margin: 0 2% 0 10%;
    }

    span {
        padding-left: 2%;
        border-left: .0625em solid hsla(0, 0%, 100%, .2);
        color: #fff;
        font-size: 2em;
        font-weight: 500;

    }
}

.header:after {
    content: '';
    display: block;
    width: 100%;
    height: 32px;
    background-image: url(public/img/disImg/torn-paper-wall.png);
    background-repeat: no-repeat;
    background-size: cover;
    position: absolute;
    bottom: 0;
    left: 0;
    z-index: 1;
}

/* banner */
.banner {
    width: 100%;
    height: 560px;
    overflow: hidden;
}

:deep(.el-carousel) {
    width: 100%;
    height: 100%;
}

:deep(.el-carousel-item) {
    width: 100%;
    height: 100%;
}

.elimg {
    width: 100%;
    height: 100%;
}

:deep(.el-carousel__container) {
    width: 100%;
    height: 100%;
}

/* tabs */

:deep(.el-tabs--border-card) {
    border: none;
}

.topTabs {

    /* 确保 el-tabs 标签均分宽度 */
    :deep(.el-tabs__nav) {
        width: 100%;
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        align-items: center;
        padding: 0;
    }

    :deep(.el-tabs__item) {
        height: 6rem;
        flex: 1;
        text-align: center;
        margin: 0;
        padding: 0;
        font-size: 1.3rem;
    }
}


/* main */
.main {
    padding: 5% 10%;
    display: flex;
}

.leftparts {
    width: 16rem;

    :deep(.el-tabs--left) {
        width: 100%;
        height: 50%;
    }

    :deep(.el-tabs--left.is-left) {
        width: 62%;
        height: 30%;
    }


    :deep(.el-tabs__item) {
        height: 4rem;
        font-size: 1.1rem;
    }

}



/* info */
.info {
    display: flex;
    flex-direction: column;
    width: 66%;
}

.mainpeople {
    box-sizing: border-box;
    width: 100%;
    min-height: 1000px;
    /* background: rgba(217, 217, 217, 0.58); */
    /* background-color: rgba(210, 204, 204, .8); */
    /* background-image: url(/public/img/disImg/back1.png); */
    /* border: 1px solid white; */
    box-shadow: 12px 17px 51px rgba(0, 0, 0, 0.22);
    backdrop-filter: blur(6px);
    border-radius: 17px;
    text-align: center;
    cursor: pointer;
    transition: all 0.5s;
    display: flex;
    flex-direction: column;
    align-items: center;
    /* justify-content: center; */
    user-select: none;
    font-weight: bolder;
    color: black;
    margin: 0 auto;
    /* position: fixed; */
    margin-left: 2%;

    .top {
        margin: 60px;
        margin-bottom: 0;
        width: 80%;
        height: 38%;
        display: flex;

        .top_img {
            width: 40%;
            height: 80%;
            margin-right: 10%;

            img {
                width: 100%;
                height: 100%;
            }
        }

        .top_title {
            width: 60%;
            height: 80%;
            color: white;
            font-size: 26px;

            .topline {
                width: 100%;
                height: 19%;
                display: flex;
                align-items: center;
                background-image: url(/public/img/disImg/border6.png);
                padding: 0 30px;
                border-radius: 10px;
                margin-bottom: 5%;
            }

            .topline_content {
                font-size: 22px;
                margin-left: 5%;
            }
        }
    }

    .bottom {
        margin: 60px;
        margin-top: 0;
        width: 80%;
        height: 40%;
        display: flex;
        flex-direction: column;
        justify-content: flex-start;

        .bottomline {
            width: 100%;
            height: 100%;
            display: flex;
            align-items: flex-start;
            flex-direction: column;

            .bottomline_title {
                color: white;
                font-size: 30px;
            }

            .bottomline_content {
                width: 100%;
                min-height: 50%;
                background-image: url(/public/img/disImg/back1.png);
                display: flex;
                justify-content: flex-start;
                align-items: flex-start;
                padding: 10px 30px;
                color: white;
                font-size: 20px;
            }
        }
    }

}

.mainpeople:hover {
    /* border: 1px solid black; */
    transform: scale(1.01);
}

.mainpeople:active {
    transform: scale(0.95) rotateZ(1.7deg);
}



.more {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
}

.cards {
    width: 100%;
    display: flex;
    flex-wrap: wrap;
}

.card {
    position: relative;
    width: 21%;
    height: 100%;
    padding: 2% 0;
    margin: 2%;
    background-color: #5386ad;
    /* background-image: url(/public/img/disImg/back1.png); */
    border-radius: 10px;
    display: flex;
    align-items: center;
    justify-content: center;
    overflow: hidden;
    perspective: 1000px;
    box-shadow: 0 0 0 5px #ffffff80;
    transition: all 0.6s cubic-bezier(0.175, 0.885, 0.32, 1.275);
}

.card svg {
    width: 48px;
    fill: #333;
    transition: all 0.6s cubic-bezier(0.175, 0.885, 0.32, 1.275);
}

.card:hover {
    transform: scale(1.05);
    box-shadow: 0 8px 16px rgba(255, 255, 255, 0.2);
}

.card__content {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    padding: 20px;
    box-sizing: border-box;
    /* background-color: #f2f2f2; */
    background-color: #095875;
    transform: rotateX(-90deg);
    transform-origin: bottom;
    transition: all 0.6s cubic-bezier(0.175, 0.885, 0.32, 1.275);
    color: white;
}

.card:hover .card__content {
    transform: rotateX(0deg);
}

.card__title {
    margin: 0;
    font-size: 24px;
    /* color: #333; */
    font-weight: 700;
}

.card:hover svg {
    scale: 0;
}

.card__description {
    margin: 10px 0 0;
    font-size: 14px;
    /* color: #777; */
    line-height: 1.4;
}

/* 分页 */
:deep(.el-pagination) {
    justify-content: flex-end;
    margin: 2% 0;
}

/* 移动端适配 */
@media (max-width: 1284px) {
    .header {
        display: none;
    }
}
</style>




