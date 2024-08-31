<template>
    <div class="people">



        <div class="header">
            <router-link to="/">
                <img width="200px" style="margin-left: 2em;" src="public/img/disImg/logo.png" alt="">
            </router-link>
            <span>人员介绍</span>
            <Sidebar v-if="!isMobile"></Sidebar>

        </div>


        <div class="main">

            <div class="leftparts">
                <div class="mobilesidemenu" @click="handleOpen">
                    <el-menu @select="handleSelect" class="el-menu-vertical-demo" :collapse="true">
                        <el-sub-menu index="1">
                            <template #title>
                                <svg t="1722740938293" class="icon svg" viewBox="0 0 1024 1024" version="1.1"
                                    xmlns="http://www.w3.org/2000/svg" p-id="4293" width="48" height="48">
                                    <path
                                        d="M384 250V134a70 70 0 0 0-70-70H134a70 70 0 0 0-70 70v116a70 70 0 0 0 70 70h180a70 70 0 0 0 70-70z m-80-10H144v-96h160zM890 704H710a70 70 0 0 0-70 70v116a70 70 0 0 0 70 70h180a70 70 0 0 0 70-70V774a70 70 0 0 0-70-70z m-10 176H720v-96h160zM890 384H710a70 70 0 0 0-70 70v116a70 70 0 0 0 70 70h180a70 70 0 0 0 70-70V454a70 70 0 0 0-70-70z m-10 176H720v-96h160zM536 504H264v-80a40 40 0 0 0-40-40 40 40 0 0 0-40 40v432a40 40 0 0 0 40 40h312a40 40 0 0 0 40-40 40 40 0 0 0-40-40H264V584h272a40 40 0 0 0 40-40 40 40 0 0 0-40-40z"
                                        fill="#999999" p-id="4294"></path>
                                </svg>
                                <span>部门</span>
                            </template>
                            <el-menu-item index="1-1">软件开发</el-menu-item>
                            <el-menu-item index="1-2">网络安全</el-menu-item>
                            <el-menu-item index="1-3">人工智能</el-menu-item>
                            <el-menu-item index="1-4">虚拟现实</el-menu-item>
                            <el-menu-item index="1-5">算法竞赛</el-menu-item>
                        </el-sub-menu>
                        <el-sub-menu index="2">
                            <template #title>
                                <svg t="1722740981043" class="icon svg" viewBox="0 0 1024 1024" version="1.1"
                                    xmlns="http://www.w3.org/2000/svg" p-id="6154" width="48" height="48">
                                    <path
                                        d="M284.057896 585.523375l-40.099824-50.954219c56.688617-42.393583 95.109082-99.12316 114.565074-170.721212l61.849575 11.468795c-4.833278 17.448953-10.567676 34.652146-17.203193 51.52766h345.415542v57.876457h-174.07993v73.89181h154.050498v56.115178h-154.091458v106.004437h191.324083v58.367977h-191.324083v122.060751h-60.170216v-122.019791H256.573747v-58.408937h88.186845v-162.119615h169.574332v-73.89181H374.49754a355.491698 355.491698 0 0 1-90.480604 100.843479v-0.04096z m120.831952 135.167946h109.404116v-105.963477H404.889848v105.963477zM781.312097 0.00041a26.951669 26.951669 0 0 1 27.115509 26.787829v27.074549h44.933102a170.844092 170.844092 0 0 1 170.393532 161.791935l0.24576 9.420797v628.039428A170.803132 170.803132 0 0 1 853.360708 1024H170.639701A170.926012 170.926012 0 0 1 0.00041 852.828228V224.7888a170.803132 170.803132 0 0 1 170.639291-170.926012l98.836441-0.04096V26.870159c0-13.189115 9.502716-24.16639 22.118391-26.50111L296.386851 0.00041c14.909434 0 26.992629 12.083195 26.992629 26.951669l-0.04096 26.910709H754.483308V26.788239a26.992629 26.992629 0 0 1 21.995511-26.33727L781.312097 0.00041zM56.852867 308.429086v544.399142a113.868754 113.868754 0 0 0 113.786834 114.073555h683.007727a113.827794 113.827794 0 0 0 113.459155-114.073555L967.065623 323.33852H82.289017a28.712949 28.712949 0 0 1-25.39519-14.909434h-0.04096z m212.582315-197.468081H170.393941a113.827794 113.827794 0 0 0-113.500114 114.114515V284.426536a28.712949 28.712949 0 0 1 25.39519-14.950394H967.065623V225.07552a113.950674 113.950674 0 0 0-105.635798-113.827795l-8.069117-0.24576-44.974062-0.04096v23.961591a26.992629 26.992629 0 0 1-22.200311 26.378229l-4.874238 0.4096a26.910709 26.910709 0 0 1-26.787829-27.156469l-0.04096-23.592951H323.33852v23.797751a26.992629 26.992629 0 0 1-22.036471 26.460149l-4.915198 0.49152a26.910709 26.910709 0 0 1-26.910709-26.951669v-23.797751z"
                                        fill="#999999" p-id="6155"></path>
                                </svg>
                                <span>年份</span>
                            </template>
<!--                            <el-menu-item index="2-1">2017</el-menu-item>-->
<!--                            <el-menu-item index="2-2">2018</el-menu-item>-->
<!--                            <el-menu-item index="2-3">2019</el-menu-item>-->
                            <el-menu-item index="2-4">2020</el-menu-item>
                            <el-menu-item index="2-4">2021</el-menu-item>
                            <el-menu-item index="2-6">2022</el-menu-item>
                            <el-menu-item index="2-7">2023</el-menu-item>
                        </el-sub-menu>
                    </el-menu>
                </div>
                <div class="sidemenu" @click="handleOpen">
                    <el-menu @select="handleSelect" class="el-menu-vertical-demo" :collapse="false">
                        <el-sub-menu index="1">
                            <template #title>
                                <svg t="1722740938293" class="icon svg" viewBox="0 0 1024 1024" version="1.1"
                                    xmlns="http://www.w3.org/2000/svg" p-id="4293" width="48" height="48">
                                    <path
                                        d="M384 250V134a70 70 0 0 0-70-70H134a70 70 0 0 0-70 70v116a70 70 0 0 0 70 70h180a70 70 0 0 0 70-70z m-80-10H144v-96h160zM890 704H710a70 70 0 0 0-70 70v116a70 70 0 0 0 70 70h180a70 70 0 0 0 70-70V774a70 70 0 0 0-70-70z m-10 176H720v-96h160zM890 384H710a70 70 0 0 0-70 70v116a70 70 0 0 0 70 70h180a70 70 0 0 0 70-70V454a70 70 0 0 0-70-70z m-10 176H720v-96h160zM536 504H264v-80a40 40 0 0 0-40-40 40 40 0 0 0-40 40v432a40 40 0 0 0 40 40h312a40 40 0 0 0 40-40 40 40 0 0 0-40-40H264V584h272a40 40 0 0 0 40-40 40 40 0 0 0-40-40z"
                                        fill="#999999" p-id="4294"></path>
                                </svg>
                                <span>部门</span>
                            </template>
                            <el-menu-item index="1-1">软件开发</el-menu-item>
                            <el-menu-item index="1-2">网络安全</el-menu-item>
                            <el-menu-item index="1-3">人工智能</el-menu-item>
                            <el-menu-item index="1-4">虚拟现实</el-menu-item>
                            <el-menu-item index="1-5">算法竞赛</el-menu-item>
                        </el-sub-menu>
                        <el-sub-menu index="2">
                            <template #title>
                                <svg t="1722740981043" class="icon svg" viewBox="0 0 1024 1024" version="1.1"
                                    xmlns="http://www.w3.org/2000/svg" p-id="6154" width="48" height="48">
                                    <path
                                        d="M284.057896 585.523375l-40.099824-50.954219c56.688617-42.393583 95.109082-99.12316 114.565074-170.721212l61.849575 11.468795c-4.833278 17.448953-10.567676 34.652146-17.203193 51.52766h345.415542v57.876457h-174.07993v73.89181h154.050498v56.115178h-154.091458v106.004437h191.324083v58.367977h-191.324083v122.060751h-60.170216v-122.019791H256.573747v-58.408937h88.186845v-162.119615h169.574332v-73.89181H374.49754a355.491698 355.491698 0 0 1-90.480604 100.843479v-0.04096z m120.831952 135.167946h109.404116v-105.963477H404.889848v105.963477zM781.312097 0.00041a26.951669 26.951669 0 0 1 27.115509 26.787829v27.074549h44.933102a170.844092 170.844092 0 0 1 170.393532 161.791935l0.24576 9.420797v628.039428A170.803132 170.803132 0 0 1 853.360708 1024H170.639701A170.926012 170.926012 0 0 1 0.00041 852.828228V224.7888a170.803132 170.803132 0 0 1 170.639291-170.926012l98.836441-0.04096V26.870159c0-13.189115 9.502716-24.16639 22.118391-26.50111L296.386851 0.00041c14.909434 0 26.992629 12.083195 26.992629 26.951669l-0.04096 26.910709H754.483308V26.788239a26.992629 26.992629 0 0 1 21.995511-26.33727L781.312097 0.00041zM56.852867 308.429086v544.399142a113.868754 113.868754 0 0 0 113.786834 114.073555h683.007727a113.827794 113.827794 0 0 0 113.459155-114.073555L967.065623 323.33852H82.289017a28.712949 28.712949 0 0 1-25.39519-14.909434h-0.04096z m212.582315-197.468081H170.393941a113.827794 113.827794 0 0 0-113.500114 114.114515V284.426536a28.712949 28.712949 0 0 1 25.39519-14.950394H967.065623V225.07552a113.950674 113.950674 0 0 0-105.635798-113.827795l-8.069117-0.24576-44.974062-0.04096v23.961591a26.992629 26.992629 0 0 1-22.200311 26.378229l-4.874238 0.4096a26.910709 26.910709 0 0 1-26.787829-27.156469l-0.04096-23.592951H323.33852v23.797751a26.992629 26.992629 0 0 1-22.036471 26.460149l-4.915198 0.49152a26.910709 26.910709 0 0 1-26.910709-26.951669v-23.797751z"
                                        fill="#999999" p-id="6155"></path>
                                </svg>
                                <span>年份</span>
                            </template>
<!--                          <el-menu-item index="2-1">2017</el-menu-item>-->
<!--                          <el-menu-item index="2-2">2018</el-menu-item>-->
<!--                          <el-menu-item index="2-3">2019</el-menu-item>-->
                          <el-menu-item index="2-4">2020</el-menu-item>
                          <el-menu-item index="2-5">2021</el-menu-item>
                          <el-menu-item index="2-6">2022</el-menu-item>
                          <el-menu-item index="2-7">2023</el-menu-item>
                        </el-sub-menu>
                    </el-menu>
                </div>
            </div>
            <div class="rightparts">
                <el-breadcrumb v-if="!isCheckAll" :separator-icon="ArrowRight">
                    <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item>人员介绍</el-breadcrumb-item>
                    <el-breadcrumb-item>{{ PeopleListParams.department }}</el-breadcrumb-item>
                    <el-breadcrumb-item>{{ PeopleListParams.period }}</el-breadcrumb-item>
                    <el-breadcrumb-item v-if="people.personnelName">{{ people.personnelName }}</el-breadcrumb-item>

                </el-breadcrumb>
                <el-breadcrumb v-else :separator-icon="ArrowRight">
                    <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item>人员介绍</el-breadcrumb-item>
                    <el-breadcrumb-item>全部</el-breadcrumb-item>


                </el-breadcrumb>

                <div class="info" v-if="showInfo">

                    <div class="mainpeople">
                        <h1><span class="style2">个人介绍 </span> <span class="style1">Personal resume</span></h1>
                        <h2>基本信息</h2>
                        <table class="style4">
                            <tr>
                                <td class="peoImg" rowspan="5">
                                    <img alt="" :src="people.personnelAvatar" />
                                </td>
                                <td class="style6">
                                    <p> 姓名：{{ people.personnelName }}</p>
                                    <p> 部门：{{ people.personnelDepartment }}</p>
                                    <p> 年份：{{ people.personnelPeriod }}</p>
                                    <p> 职位：{{ people.personnelPost }}</p>
                                </td>


                            </tr>

                        </table>
                        <h2>贡献</h2>
                        <p class="style5">{{ people.personnelContribute ? people.personnelContribute : '暂无' }}</p>
                        <h2>语录</h2>
                        <p class="style5">
                            {{ people.personnelSaying ? people.personnelSaying : '暂无' }}</p>



                    </div>

                </div>
                <div class="more" v-if="showMore">

                    <div class="persons">
                        <div class="person" v-for="item in PeopleList" @click="toDetail(item)">
                            <div class="pImg">
                                <img :src="item.personnelAvatar" alt="">
                                <i class="block"></i>
                            </div>
                            <div class="pText">
                                <h5>{{ item.personnelName }}</h5>
                                <span>{{ item.personnelPeriod }}年</span>
                                <span>{{ item.personnelDepartment }}</span>
                            </div>
                        </div>

                    </div>

                    <div v-if="total == 0" class="botlistnone">
                        <span>暂无内容...</span>
                    </div>


                    <el-pagination v-if="total !== 0" v-model:current-page="PeopleListParams.pageNum"
                        v-model:page-size="PeopleListParams.pageSize" :page-sizes="[3]" layout=" prev, pager, next"
                        :total="total" @size-change="handleSizeChange" @current-change="handleCurrentChange" />


                </div>
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

import { getPeoples, getAllPeoples } from "@/api/people/people"
import { parseLanzouLink } from '@/utils/getFileByBackend';
import bus from '@/eventBus';
import type { TabsInstance } from 'element-plus'
import { decrypt, encrypt } from '@/utils/crypto';
import {
    Document,
    Menu as IconMenu,
    Location,
    Setting,
} from '@element-plus/icons-vue'
import { ArrowRight } from '@element-plus/icons-vue'

const tabPosition = ref<TabsInstance['tabPosition']>('left')
const loading = ref(false);
const router = useRouter();
const routeQuery = useRoute();


const isDetail = ref(true);
const showInfo = ref(false);
const showMore = ref(false);

const updateVisibility = async () => {
    await nextTick();

    showInfo.value = isDetail.value;
    showMore.value = !isDetail.value;

};

watch(isDetail, async () => {
    await updateVisibility();
});

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
    PeopleListParams.value.period = period;
    PeopleListParams.value.department = department;

    if (!isCheckAll.value) {
        await getPeopleList();
    } else {
        await getAllPeopleList();
    }

    const detail = sessionStorage.getItem('isDetail');
    showInfo.value = isDetail.value;
    if (detail === 'false') {
        isDetail.value = false;
    }
};


const isCheckAll = ref(false);
const people = ref<Record<string, any>>({});
const PeopleList = ref<Array<{}>>([]);

const total = ref(0)
const PeopleListParams = ref({
    pageNum: 1,
    pageSize: 8,
    period: "2022",
    department: "软件开发"
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

const getAllPeopleList = async () => {
    try {
        const result = await getAllPeoples(PeopleListParams.value);
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
    if (!isCheckAll.value) {
        getPeopleList();
    } else {
        getAllPeopleList();
    }

}
const handleCurrentChange = () => {
    if (!isCheckAll.value) {
        getPeopleList();
    } else {
        getAllPeopleList();
    }
}


// 侧栏菜单
const isCollapse = ref(true)
const handleOpen = (key: string, keyPath: string[]) => {
    isCollapse.value = !isCollapse.value;
}
const handleClose = (key: string, keyPath: string[]) => {
    isCollapse.value = true;
}

const handleSelect = (param) => {
    switch (param) {
        case '1-1':
            handlePart('软件开发')
            break;
        case '1-2':
            handlePart('网络安全')
            break;
        case '1-3':
            handlePart('人工智能')
            break;
        case '1-4':
            handlePart('虚拟现实')
            break;
        case '1-5':
            handlePart('算法竞赛')
            break;
        // case '2-1':
        //     handlePeriod(2017)
        //     break;
        // case '2-2':
        //     handlePeriod(2018)
        //     break;
        // case '2-3':
        //     handlePeriod(2019)
        //     break;
        case '2-4':
            handlePeriod(2020)
            break;
        case '2-5':
            handlePeriod(2021)
            break;
        case '2-6':
            handlePeriod(2022)
            break;
        case '2-7':
            handlePeriod(2023)
            break;
        default:
            break;
    }

}

// 切换部门
const handlePart = async (part: any) => {
    PeopleListParams.value.department = part;
    isDetail.value = false;
    isCheckAll.value = false;
    router.push({
        query: {
            part: part,
            period: PeopleListParams.value.period
        },
    });
    await getPeopleList();
    loading.value = true;

    sessionStorage.setItem('isDetail', JSON.stringify(isDetail.value));

}
const handlePeriod = async (period: any) => {

    PeopleListParams.value.period = period;
    isDetail.value = false;
    isCheckAll.value = false;

    router.push({
        query: {
            part: PeopleListParams.value.department,
            period: period
        },
    });

    await getPeopleList();

    sessionStorage.setItem('isDetail', JSON.stringify(isDetail.value));
}

const toDetail = (peopleitem) => {

    isDetail.value = true;
    isCheckAll.value = false;

    router.push({
        query: {
            part: PeopleListParams.value.department,
            period: PeopleListParams.value.period,
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

    // 根据路由获取查询参数
    if (routeQuery.query.part) {
        PeopleListParams.value.department = routeQuery.query.part;
    }
    if (routeQuery.query.period) {
        PeopleListParams.value.period = routeQuery.query.period;
    }
    if (routeQuery.query.people) {
        try {
            const encryptedPeople = routeQuery.query.people as string;
            people.value = JSON.parse(decrypt(encryptedPeople));

            people.value.personnelAvatar = await parseLanzouLink(people.value.personnelAvatar);

        } catch (error) {
            console.error('Error parsing people parameter:', error);
        }
    }
    // 没有查看人员详情时
    if (!routeQuery.query.people) {
        isDetail.value = false;
        sessionStorage.setItem('isDetail', JSON.stringify(isDetail.value));
    }
    // 查看全部
    if (!routeQuery.query.part && !routeQuery.query.period && !routeQuery.query.people) {
        isCheckAll.value = true;
        isDetail.value = false;
        sessionStorage.setItem('isDetail', JSON.stringify(isDetail.value));
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
    width: 100%;
    min-height: 100vh;
    display: flex;
    flex-direction: column;
    /* padding: 160px 80px; */
    /* background-color: rgb(240, 240, 240); */
    background-color: white;
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






/* main */
.main {
    width: 100%;
    padding: 5% 10%;
    display: flex;
}

.leftparts {
    width: 16%;
    min-height: 400px;
}

.rightparts {
    width: 80%;
    padding: .5em 3em;
}

:deep(.el-breadcrumb) {
    font-size: 1.1em;
    line-height: 1;
    margin-bottom: 2%;
}

/* menu */
.mobilesidemenu {
    display: none;
}

/* :deep(.el-menu-vertical-demo:not(.el-menu--collapse)) {
    width: 200px;
    min-height: 400px;
} */

.svg {
    width: 30%;
    height: 40%;
}



/* info */
.info {
    display: flex;
    flex-direction: column;
    width: 100%;
}

.mainpeople {
    width: 100%;
    /* min-height: 1000px; */
    border: #002d72 1px solid;
    margin: 0 auto;
    /*边框颜色，大小实线*/
    margin-top: 2%;
    margin-bottom: 2%;
    /*底部边框大小*/
    padding: 2em;

}

.mainpeople table {
    border-style: none;
    border-color: inherit;
    /*表格边框样式，颜色宽度*/
    border-width: 5px;
    width: 100%;
    height: 13em;
    padding: 5px;
    /*表格边距为5px*/
}

.mainpeople h2 {
    padding: .1em .3em;
    color: #000000;
    /* background-color: #7aa0d9; */
    margin: .5em 0;
    background: linear-gradient(45deg, #cedaed, #7aa0d9);
}

.style1

/*class为style1的样式，字体大小为large，字体颜色为橙黄*/
    {
    font-size: large;
    color: #002d72;
}

.style2 {
    color: #002d72;
    /*class为style2的样式，字体颜色为橙黄*/
}

.style5 {
    font-family: 黑体;
}

.style6 {
    width: 50%;
    height: 100%;
    font-family: 黑体;
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
}

.peoImg {
    width: 40%;
    height: 100%;
}

.peoImg img {
    width: 12em;
    height: 100%;
}



.more {
    width: 108%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
}

.persons {
    display: flex;
    width: 100%;
    overflow: hidden;
    word-spacing: normal;
    flex-wrap: wrap;
}

.person {
    /* width: 16em;
    border: 1px solid rgba(0, 0, 0, 0.1);
    box-shadow: 0px 0px 2px rgba(69, 85, 103, 0.1); */

    width: 25%;
    float: left;
    height: 20em;
    padding-left: 32px;
    box-sizing: border-box;
    position: relative;
    background: #FBFBFB;
    cursor: pointer;
    transition: all .4s;
}

.person:hover {
    background: #F2F3F5;
}

.person:hover .block {
    left: 0px;
    bottom: 0px;
}

.pImg {
    position: absolute;
    right: 0;
    top: 0;


}

.pImg img {
    width: 120px;
    height: 120px;
}

.block {
    width: 28px;
    height: 28px;
    background-color: #0052d9;
    position: absolute;
    left: -14px;
    bottom: -14px;
    transition: all .4s;
}

.pText {
    float: left;
    margin-top: 50%;
    width: 100%;
    display: flex;
    flex-direction: column;
}

.pText h5 {
    font-size: 20px;
    line-height: 30px;
    color: #0052d9;
    margin-bottom: .6em;
}

.pText span {
    width: 100%;
    margin-bottom: .6em;
    font-size: 17px;
    line-height: 21px;
    color: #5f6464;
}



.botlistnone {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 1.5em;
    margin: 3% 0;
}

/* 分页 */
:deep(.el-pagination) {
    justify-content: flex-end;
    margin: 4em 0;
}

/* 移动端适配 */
@media (max-width: 1284px) {

    .header {
        display: none;
    }

    .main {
        width: 100%;
        height: 100%;
        padding-top: 8em;
        display: flex;
        position: relative;
    }

    /* menu */
    .mobilesidemenu {
        display: block;
        position: absolute;
        top: 8em;
        left: 0;
    }

    .svg {
        width: 92%;
        height: 40%;
    }

    .sidemenu {
        display: none;
    }

    .leftparts {
        width: 0;
    }

    .rightparts {
        width: 100%;
        padding: .5em 0em .5em 2.2em;
    }

    :deep(.el-breadcrumb) {
        font-size: 1.1em;
        line-height: 2;
        margin-bottom: 2%;
    }

    .mainpeople {

        padding: .2em;
    }

    .mainpeople h1 {
        display: flex;
        flex-direction: column;
    }

    .mainpeople h2 {
        padding: .1em .3em;
        color: #000000;
        margin: .5em 0;
        font-size: 1em;
    }

    .peoImg {
        width: 8em;
        height: 9em;
    }

    .peoImg img {
        width: 100%;
        height: 100%;
    }

    .style2 {
        font-size: .7em;
    }

    .style1 {

        font-size: .5em;
    }

    .style6 {
        width: 100%;
        /* align-items: center;*/
    }


    /* people list */
    .persons {
        flex-direction: column;
    }

    .person {
        width: 100%;
        margin: 3% 0;
        float: left;
        height: 22em;
        padding-left: 32px;
        box-sizing: border-box;
        position: relative;
        background: #FBFBFB;
        cursor: pointer;
        transition: all .4s;
        display: flex;
        align-items: center;
    }

    .pText {
        float: none;
        margin: 0;
    }

    .botlistnone {
        width: 80%;
        height: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
        font-size: 1.5em;
        margin: 11% 0;
    }
}
</style>
