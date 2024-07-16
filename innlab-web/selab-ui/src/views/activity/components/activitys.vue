<template>
    <div class="programs">

        <div class="progs">
            <router-link v-for="item in ActivitiesList" :to="getlink(item.activityId)">

                <div class="notification">
                    <div class="notiglow"></div>
                    <div class="notiborderglow"></div>
                    <div class="notititle">{{ item.activityTitle }}</div>
                    <div class="notibody">{{ item.activityIntroduction }}</div>
                    <div class="time">{{ item.createTime }}</div>
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
import { getActs } from '@/api/activity/activity'

const props = defineProps<{
    part: string
}>()
const total = ref(10)
const params = ref({
    pageNum: 1,
    pageSize: 10,
    // activityType: '软件开发'
})

const ActivitiesList = ref<Array<{ itemId: number; itemTitle: string; itemIntroduction: string; createTime: string }>>([]);
const getActivitiesList = async () => {
    try {
        const result = await getActs(params.value);
        ActivitiesList.value = result.data.records;
        // ActivitiesList.value = [{
        //     "itemId": 2,
        //     "itemTitle": "测试md",
        //     "itemIntroduction": "测试md的简介",
        //     "markdownBase64": "IyDorr7lpIfkuIrkvKANCg0KDQoNCiMjIOaWueahiOS4gO+8mg0KDQojIyMg5o6l5Y+jMe+8muiuvuWkh+S4iuS8oOS/oeaBr+e7keWumg0KDQrkuIrkvKDkv6Hmga9qc29u5YyF5ousDQoNCuiuvuWkh+W6j+WIl+WPtyvorr7lpIdpcA0KDQrov5nkupvkv6Hmga/lj6/ku6Xnuqblrprlpb3nlKjlk6rnp43lj4zlkJHliqDlr4bmlrnlvI8NCg0K5LqR56uv5Y+q5Lya6L+U5Zue5LiK5Lyg5aSx6LSl5oiW6ICF5LiK5Lyg5oiQ5Yqf77yaDQoNCui/meWdl+S6keerr+S8muWBmuagoemqjO+8jOS+i+Wmgu+8mg0KDQrorr7lpIflt7Lnu4/nu5Hlrprov4dJUOWQjuS4jeWPr+S7peS4iuS8oO+8jOWmguaenOS4gOWumuimgemHjeWkjee7keWumuW/hemhu+eUseeuoeeQhuWRmOS/ruaUueiuvuWkh+eKtuaAgeaJjeiDvemHjeWkjee7keWumg0KDQojIyMg5o6l5Y+jMu+8muiuvuWkh+S4iuS8oOS/oeaBrw0KDQrkuIrkvKDkv6Hmga/vvJrorr7lpIfluo/liJflj7cr6K6+5aSH5pWw5o2u5L+h5oGvDQoNCuWQjOagt+aYr+eUqOWPjOWQkeWKoOWvhuadpeWKoOWvhuaVsOaNrg0KDQoNCg0KIyMg5pa55qGI5LqM77yaDQoNCiMjIyDmjqXlj6Mx77ya6K6+5aSH5LiK5Lyg5L+h5oGv57uR5a6aDQoNCuS4iuS8oOS/oeaBr2pzb27ljIXmi6wNCg0K6K6+5aSH5bqP5YiX5Y+3K+iuvuWkh21hY+WcsOWdgCvorr7lpIdpcA0KDQrov5nkupvkv6Hmga/lj6/ku6Xnuqblrprlpb3nlKjlk6rnp43lj4zlkJHliqDlr4bmlrnlvI8NCg0K5LqR56uv5Lya6L+U5Zue5LiK5Lyg5aSx6LSl5oiW6ICF5LiK5Lyg5oiQ5YqfDQoNCuWmguaenOS4iuS8oOaIkOWKn+S8mue7meiuvuWkh+S4i+WPkeS4gOauteWtl+espuS4su+8jOS/l+ensOWFrOmSpQ0KDQrorr7lpIfpnIDorrDkvY/ov5nmrrXlrZfnrKbkuLLmiY3ooYwNCg0KIyMjIOaOpeWPozLvvJrorr7lpIfkuIrkvKDkv6Hmga8NCg0K6K6+5aSH5bqP5YiX5Y+3K+iuvuWkh+aVsOaNruS/oeaBryvmlbDlrZfnrb7lkI0NCg0K5ZCM5qC35piv5Y+M5ZCR5Yqg5a+G5Yqg5a+G5pWw5o2uDQoNCuiuvuWkh+S4iuS8oOaVsOaNrumcgOimgeeUqOWTiOW4jOeul+azleeul+WHuuS4gOauteWtl+espuS4su+8jOS5i+WQjueUseaOpeWPozHkuIvlj5HnmoTlhazpkqXov5vooYzliqDlr4blvpflh7rmlbDlrZfnrb7lkI0NCg0K5LqR56uv6I635Y+W5pWw5o2u5ZCO6Zmk5LqG5Lya5a+5SVDov5vooYzmoKHpqozvvIzov5jkvJrlr7nop6Plr4blkI7nmoTorr7lpIfkuIrkvKDmlbDmja7kuI7mlbDlrZfnrb7lkI3ov5vooYzlk4jluIzmoKHpqowNCg0K",
        //     "createTime": "2024-07-11 17:50:30",
        //     "author": "GM",
        //     "updateTime": "2024-07-14 08:56:38",
        //     "auditor": "GM"
        // }]
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
    margin: 60px 0;
    display: flex;

}


.progs {
    width: 100%;
    padding-top: 2%;
    /* margin-left: 6%; */
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
    justify-content: flex-start;

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
