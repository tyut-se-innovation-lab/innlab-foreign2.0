<template>
    <div class="programs">

        <div class="progs">
            <router-link v-for="item in ActivitiesList" :to="getlink(item.activityId)">

                <div class="notification">
                    <div class="notiglow"></div>
                    <div class="notiborderglow"></div>
                    <div class="notiimg"><img :src="item.headerImage" alt=""></div>
                    <div class="notitext">
                        <div class="notititle">{{ item.activityTitle }}</div>
                        <div class="notibody">{{ item.activityIntroduction }}</div>
                        <div class="time">{{ item.createTime }}</div>
                    </div>

                </div>
            </router-link>


            <el-pagination background layout="prev, pager, next" v-model:current-page="params.pageNum"
                v-model:page-size="params.pageSize" :total="total" @size-change="handleSizeChange"
                @current-change="handleCurrentChange" />
        </div>



    </div>
</template>
<script setup lang="ts">
import { ref, onMounted } from "vue"
import { getActs } from '@/api/activity/activity'
import { parseLanzouLink } from '@/utils/getFileByBackend';

const props = defineProps<{
    part: string
}>()
const total = ref(10)
const params = ref({
    pageNum: 1,
    pageSize: 10,
    // activityType: '软件开发'
})

const ActivitiesList = ref<Array<{ activityId: number; activityTitle: string; activityIntroduction: string; createTime: string }>>([]);
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

onMounted(() => {
    getActivitiesList();
})

const getlink = (id: any) => {
    return `/blog/?actid=${id}`
}

const handleSizeChange = () => {
    getActivitiesList();
}
const handleCurrentChange = () => {
    getActivitiesList();
}


</script>
<style scoped>
.programs {
    /* padding: 20px 50px; */
    margin: 1em 0;
    display: flex;

}


.progs {
    width: 100%;
    padding-top: 2%;
    /* margin-left: 6%; */
}


.notification {
    display: flex;
    /* flex-direction: column; */
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
    background: linear-gradient(to right, #33709e, #5c9fd9);
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

.notititle {
    /* color: var(--color); */
    color: white;
    padding: 0.65rem 0.25rem 0.4rem 1.25rem;
    font-weight: 500;
    font-size: 1.1rem;
    transition: transform 300ms ease;
    z-index: 5;
}

.notification:hover .notititle {
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

.notiimg {
    position: relative;
    width: 15rem;
    height: 8rem;
    z-index: 10;
    padding-left: 1em;
    overflow: hidden;


}

.notiimg img {
    width: 100% !important;
    height: 100% !important;
    object-fit: cover !important;
}

.notitext,
.notititle,
.notibody,
.time {
    position: relative;
}

.notitext {}



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
:deep(.el-pagination) {
    justify-content: flex-end;
}

:deep(.el-pagination.is-background .el-pager li:not(.is-disabled).is-active) {
    background-color: #579ad3;
}
</style>
