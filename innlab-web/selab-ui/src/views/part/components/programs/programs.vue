<template>
    <div class="programs">
        <div class="menus">
            <div class="title">
                <span>{{ props.part ? props.part : '项目列表' }}</span>
            </div>
            <div class="content">
                <span v-for="item in ProList"> {{ item.itemTitle }}</span>
            </div>
        </div>
        <div class="progs">
            <router-link v-for="item in ProList" :to="getlink(item.itemId)">

                <div class="notification">
                    <div class="notiglow"></div>
                    <div class="notiborderglow"></div>

                    <div class="notmain">

                        <div class="notimg">
                            <img :src="item.headerImage" alt="">
                        </div>
                        <div class="notbody">
                            <div class="notititle">{{ item.itemTitle }}</div>
                            <div class="notibody">{{ item.itemIntroduction }}</div>
                            <div class="time">{{ item.createTime }}</div>
                        </div>
                    </div>
                </div>
            </router-link>

            <div class="demo-pagination-block">

                <el-pagination v-model:current-page="params.pageNum" v-model:page-size="params.pageSize"
                    :page-sizes="[3]" layout=" prev, pager, next" :total="total" @size-change="handleSizeChange"
                    @current-change="handleCurrentChange" />
            </div>
        </div>



    </div>
</template>
<script setup lang="ts">
import { ref, onMounted } from "vue"
import { getPros } from '@/api/pro/pro'
import { parseLanzouLink } from '@/utils/getFileByBackend';

const props = defineProps<{
    part: string
}>()
const total = ref(10)
const params = ref({
    pageNum: 1,
    pageSize: 3,
    department: props.part
})

const ProList = ref<Array<{ itemId: number; itemTitle: string; itemIntroduction: string; headerImage: object; createTime: string }>>([]);
const getProList = async () => {
    try {
        const result = await getPros(params.value);
        ProList.value = result.data.records;

        ProList.value.forEach(async e => {

            e.headerImage = await parseLanzouLink(e.headerImage);
            console.log('e.headerImage: ', e.headerImage);

        })

        total.value = result.data.total;

    } catch (error) {
        console.error('Error fetching data:');
    } finally {

    }
};

onMounted(() => {
    getProList();
})

const getlink = (id: any) => {
    return `/proMd/?id=${id}`
}

const handleSizeChange = () => {
    getProList();
}
const handleCurrentChange = () => {
    getProList();
}


</script>
<style scoped>
.programs {
    padding: 20px 50px;
    margin: 60px 0;
    display: flex;

}

.menus {
    width: 26%;
    color: white;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

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

.progs {
    width: 66%;
    padding-top: 2%;
    margin-left: 6%;
}


.notification {
    display: flex;
    flex-direction: column;
    isolation: isolate;
    position: relative;
    /* width: 18rem; */
    min-height: 8rem;
    border: 1px solid rgb(210, 210, 210);
    cursor: pointer;
    /* background: #29292c; */
    background-color: rgb(241, 241, 241);
    border-radius: 1rem;
    margin-bottom: 30px;
    padding: 20px;
    padding-bottom: 25px;
    overflow: hidden;
    font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
    font-size: 16px;
    --gradient: linear-gradient(to bottom, #2eadff, #3d83ff, #7e61ff);
    --color: #32a6ff
}

.notification:before {
    position: absolute;
    content: "";
    inset: 0.0625rem;
    border-radius: 0.9375rem;
    /* background: #18181b; */
    /* background-color: white; */
    background: linear-gradient(to right, #1c457c, #0b204b);
    z-index: 2
}

.notification:after {
    position: absolute;
    content: "";
    width: 0.25rem;
    inset: 0.65rem auto 0.65rem 0.5rem;
    border-radius: 0.125rem;
    background: var(--gradient);
    transition: transform 300ms ease;
    z-index: 4;
}

.notification:hover:after {
    transform: translateX(0.15rem)
}

.notmain {

    z-index: 10;
    display: flex;
}

.notimg {

    width: 22%;
    height: 80%;
    transition: transform 300ms ease;

    img {
        width: 100%;
        height: 150px;
        border-radius: 6px;
    }
}

.notititle {
    /* color: var(--color); */
    color: white;
    padding: 0.65rem 0.25rem 0.4rem 1.25rem;
    font-weight: 500;
    font-size: 1.1rem;
    transition: transform 300ms ease;
    z-index: 5;
}

.notification:hover .notititle,
.notimg>img {
    transform: translateX(0.15rem)
}

.notification:hover .notimg {
    transform: translateX(0.15rem)
}


.notibody {
    color: #99999d;
    padding: 0 1.25rem;
    transition: transform 300ms ease;
    z-index: 5;
}

.notification:hover .notibody {
    transform: translateX(0.25rem)
}

.notiglow,
.notiborderglow {
    position: absolute;
    width: 20rem;
    height: 20rem;
    transform: translate(-50%, -50%);
    background: radial-gradient(circle closest-side at center, white, transparent);
    opacity: 0;
    transition: opacity 300ms ease;
}

.notiglow {
    z-index: 3;
}

.notiborderglow {
    z-index: 1;
}

.notification:hover .notiglow {
    opacity: 0.1
}

.notification:hover .notiborderglow {
    opacity: 0.1
}

.note {
    color: var(--color);
    position: fixed;
    top: 80%;
    left: 50%;
    transform: translateX(-50%);
    text-align: center;
    font-size: 0.9rem;
    width: 75%;
}

.time {
    color: #99999d;
    padding: 0 1.25rem;
    margin-top: 15px;
    transition: transform 300ms ease;
    z-index: 5;
}

.notification:hover .time {
    transform: translateX(0.35rem)
}


/* 分页 */
.demo-pagination-block+.demo-pagination-block {
    width: 100%;
    margin-top: 10px;
    display: flex;
    justify-content: flex-end;

}

.demo-pagination-block .demonstration {
    margin-bottom: 16px;
}

:deep(.el-pager) {
    margin: 0 6px;
}

:deep(.el-pagination) {
    justify-content: flex-end;
}
</style>
