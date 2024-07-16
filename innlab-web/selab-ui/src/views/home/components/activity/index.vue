<template>
    <div class="homeActivity">
        <div class="container">
            <h1 class="acTitle">
                部门动态
            </h1>
            <div class="main">
                <div id="partsAc" class="row">

                    <div class="parts">
                        <div class="part">
                            <section class="box">
                                <h2>软件开发</h2>
                                <p class="subtitle">Condimentum nisl</p>
                                <ul class="style1">
                                    <li v-for="item in rkPros"><span href="#" @click="linkProTo(item.itemId)">{{
                                        item.itemTitle }}</span></li>

                                </ul>
                                <router-link :to="{ path: '/part', query: { name: '软件开发' } }">
                                    <button class="cta">
                                        <span class="hover-underline-animation"> Read More </span>
                                        <svg id="arrow-horizontal" xmlns="http://www.w3.org/2000/svg" width="30"
                                            height="10" viewBox="0 0 46 16">
                                            <path id="Path_10" data-name="Path 10"
                                                d="M8,0,6.545,1.455l5.506,5.506H-30V9.039H12.052L6.545,14.545,8,16l8-8Z"
                                                transform="translate(30)" style="fill: white;"></path>
                                        </svg>
                                    </button>

                                </router-link>
                            </section>
                        </div>
                        <div class="part">
                            <section class="box">
                                <h2>网络安全</h2>
                                <p class="subtitle">luctus lectus</p>
                                <ul class="style1">
                                    <li v-for="item in waPros"><span @click="linkProTo(item.itemId)">{{
                                        item.itemTitle }}</span></li>
                                </ul>
                                <router-link :to="{ path: '/part', query: { name: '网络安全' } }">
                                    <button class="cta">
                                        <span class="hover-underline-animation"> Read More </span>
                                        <svg id="arrow-horizontal" xmlns="http://www.w3.org/2000/svg" width="30"
                                            height="10" viewBox="0 0 46 16">
                                            <path id="Path_10" data-name="Path 10"
                                                d="M8,0,6.545,1.455l5.506,5.506H-30V9.039H12.052L6.545,14.545,8,16l8-8Z"
                                                transform="translate(30)" style="fill: white;"></path>
                                        </svg>
                                    </button>
                                </router-link>
                            </section>
                        </div>
                    </div>
                    <div class="parts">
                        <div class="part">
                            <section class="box">
                                <h2>人工智能</h2>
                                <p class="subtitle">luctus eleifend</p>
                                <ul class="style1">
                                    <li v-for="item in aiPros"><span href="#" @click="linkProTo(item.itemId)">{{
                                        item.itemTitle }}</span></li>
                                </ul>
                                <router-link :to="{ path: '/part', query: { name: '人工智能' } }">
                                    <button class="cta">
                                        <span class="hover-underline-animation"> Read More </span>
                                        <svg id="arrow-horizontal" xmlns="http://www.w3.org/2000/svg" width="30"
                                            height="10" viewBox="0 0 46 16">
                                            <path id="Path_10" data-name="Path 10"
                                                d="M8,0,6.545,1.455l5.506,5.506H-30V9.039H12.052L6.545,14.545,8,16l8-8Z"
                                                transform="translate(30)" style="fill: white;"></path>
                                        </svg>
                                    </button>
                                </router-link>
                            </section>
                        </div>
                        <div class="part">
                            <section class="box">
                                <h2>虚拟现实</h2>
                                <p class="subtitle">dictum metus</p>
                                <ul class="style1">
                                    <li v-for="item in vrPros"><span href="#" @click="linkProTo(item.itemId)">{{
                                        item.itemTitle }}</span></li>
                                </ul>
                                <router-link :to="{ path: '/part', query: { name: '虚拟现实' } }">
                                    <button class="cta">
                                        <span class="hover-underline-animation"> Read More </span>
                                        <svg id="arrow-horizontal" xmlns="http://www.w3.org/2000/svg" width="30"
                                            height="10" viewBox="0 0 46 16">
                                            <path id="Path_10" data-name="Path 10"
                                                d="M8,0,6.545,1.455l5.506,5.506H-30V9.039H12.052L6.545,14.545,8,16l8-8Z"
                                                transform="translate(30)" style="fill: white;"></path>
                                        </svg>
                                    </button>
                                </router-link>
                            </section>
                        </div>
                    </div>


                </div>

                <div class="selabAc">
                    <router-link to="/activity">
                        <div class="selabAc_title">机构动态</div>
                    </router-link>
                    <div class="selab" v-for="item in ActivitiesList">
                        <span class="seTitle" @click="linkTo(item.activityId)">{{ item.activityTitle }}</span>
                        <span class="seTime">{{ item.createTime }}</span>
                    </div>
                </div>
            </div>

        </div>



    </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from "vue"
import { getActs } from '@/api/activity/activity'
import { getProTitles } from '@/api/pro/pro'
import router from '@/router';
import bus from "@/eventBus";
const actParams = ref({
    pageNum: 1,
    pageSize: 8,
    // activityType: '软件开发'
})
const rkParams = ref({
    pageNum: 1,
    pageSize: 4,
    department: '软件开发'
})
const waParams = ref({
    pageNum: 1,
    pageSize: 4,
    department: '网络安全'
})
const aiParams = ref({
    pageNum: 1,
    pageSize: 4,
    department: '人工智能'
})
const vrParams = ref({
    pageNum: 1,
    pageSize: 4,
    department: '虚拟现实'
})

const ActivitiesList = ref<Array<{ activityId: number; activityTitle: string; createTime: string }>>([]);
const getActivitiesList = async () => {
    try {
        const result = await getActs(actParams.value);
        ActivitiesList.value = result.data.records;

    } catch (error) {
        console.error('Error fetching data:');
    } finally {

    }
};

const rkPros = ref<Array<{ itemId: number; itemTitle: string; }>>([]);
const getRkPros = async () => {
    try {
        const result = await getProTitles(rkParams.value);
        rkPros.value = result.data.records;

    } catch (error) {
        console.error('Error fetching data:');
    } finally {

    }
};
const waPros = ref<Array<{ itemId: number; itemTitle: string; }>>([]);
const getWaPros = async () => {
    try {
        const result = await getProTitles(waParams.value);
        waPros.value = result.data.records;

    } catch (error) {
        console.error('Error fetching data:');
    } finally {

    }
};
const aiPros = ref<Array<{ itemId: number; itemTitle: string; }>>([]);
const getAiPros = async () => {
    try {
        const result = await getProTitles(aiParams.value);
        aiPros.value = result.data.records;

    } catch (error) {
        console.error('Error fetching data:');
    } finally {

    }
};
const vrPros = ref<Array<{ itemId: number; itemTitle: string; }>>([]);
const getVrPros = async () => {
    try {
        const result = await getProTitles(vrParams.value);
        vrPros.value = result.data.records;

    } catch (error) {
        console.error('Error fetching data:');
    } finally {

    }
};

// 点击跳转
const linkTo = (item: number) => {
    router.push({ path: '/blog', query: { actid: item } });

}
// 部门点击跳转
const linkProTo = (item: number) => {
    router.push({ path: '/proMd', query: { id: item } });

}

onMounted(() => {
    getActivitiesList();
    getRkPros();
    getWaPros();
    getAiPros();
    getVrPros();
});

</script>

<style scoped>
.homeActivity {
    width: 100%;
    /* padding: 150px 120px; */
    display: flex;
    padding-left: 10%;
    align-items: center;
    /* margin-top: 100px; */
    /* justify-content: center; */

}

.container {
    display: flex;
    align-items: center;

}

.acTitle {
    color: rgb(163, 163, 163);
    margin-left: -90px;
    margin-right: 40px;
    writing-mode: vertical-rl;
    letter-spacing: 10px;
}

.main {
    padding: 3em 0em 0em 0em;
    display: flex;
}

#partsAc {
    overflow: hidden;
    display: flex;
    flex-direction: column;
    border-right: 1px solid rgb(208 208 208);
    margin-right: 120px;
}

#partsAc h2 {
    margin: 0;
    padding: 0;
    text-transform: uppercase;
    font-size: 1.6em;
    font-weight: 700;
    color: #4474c4;
}

#partsAc .subtitle {
    margin: 0 0 1em 0;
    padding: 0 0 1em 0;
    border-bottom: 1px solid #b2b2b2;
    text-transform: uppercase;
    font-size: 1.25em;
    font-weight: 400;
    /* color: #3D3D3D; */
    color: white;
}

#partsAc .box {
    padding: 0 2em;
}

.parts {
    margin: 25px;
    display: flex;
}

.part {
    min-width: 300px;
    margin-right: 142px;
}

.part:nth-child(2n) {
    margin-right: 42px;

}

.selabAc {
    margin-top: 40px;
}

.selabAc_title {
    margin: 0;
    padding: 0;
    text-transform: uppercase;
    font-size: 1.75em;
    font-weight: 700;
    color: #4474c4;
}

.selab {
    display: flex;
    justify-content: space-between;
    width: 520px;
    height: 60px;
    border-bottom: 1px solid gray;
    margin-top: 20px;
}

.seTitle {
    cursor: pointer;
    transition: all .2s;
    color: white;
}

.seTitle:hover {
    transform: translateX(6px);
}

.seTime {
    color: white;
}

/* List Style 1 */
/* ul li {

    list-style: disc !important;
    list-style-position: outside !important;
} */

ul.style1 {}

ul.style1 li {
    padding: 0.75em 0 0.75em 0em;
    /* background: url(images/bullet-icon.png) no-repeat left 50%; */
}

ul.style1 span {
    cursor: pointer;
    display: block;
    /* padding-left: 0.5em; */
    text-decoration: none;
    /* color: #676767; */
    color: white;
}

ul.style1 span:hover {
    text-decoration: underline !important;
}

.button {
    margin-top: 30px;
    cursor: pointer;
    color: white;
    font-size: 600;
}

.button:hover {
    cursor: pointer;
    text-decoration: underline !important;
}

.cta {
    border: none;
    background: none;
    cursor: pointer;
    margin-top: 30px;
}

.cta span {
    padding-bottom: 7px;
    letter-spacing: 4px;
    font-size: 14px;
    padding-right: 15px;
    text-transform: uppercase;
}

.cta svg {
    transform: translateX(-8px);
    transition: all 0.3s ease;
}

.cta:hover svg {
    transform: translateX(0);
}

.cta:active svg {
    transform: scale(0.9);
}

.hover-underline-animation {
    position: relative;
    /* color: #767676; */
    color: white;
    padding-bottom: 20px;
}

.hover-underline-animation:after {
    content: "";
    position: absolute;
    width: 100%;
    transform: scaleX(0);
    height: 2px;
    bottom: 0;
    left: 0;
    background-color: #797979;
    transform-origin: bottom right;
    transition: transform 0.25s ease-out;
}

.cta:hover .hover-underline-animation:after {
    transform: scaleX(1);
    transform-origin: bottom left;
}



/* 当视口宽度小于1200px时调整样式 */
@media (max-width: 1600px) {
    .acTitle {
        font-size: 2vw;
        margin-left: -90px;
        margin-right: 40px;
    }

    #partsAc h2 {
        font-size: 1.2em;
    }

    #partsAc .subtitle {
        font-size: 1em;
    }

    ul.style1 li {
        padding: 0.5em 0 0.5em 0em;
    }

    .parts {
        margin: 10px;
    }

    .part {
        min-width: 300px;
        margin-right: 20px;
    }

    .selabAc_title {
        font-size: 1.75em;
    }

    .selab {
        width: 520px;
        height: 60px;
    }

    .seTitle {
        font-size: 1.1em;
    }

    .seTime {
        font-size: 1.1em;
    }

    .selabAc {
        margin-top: 0;
    }
}
</style>
