<template>
    <div class="homePeople">
        <h1>人员介绍</h1>

        <div class="peoples">
            <div class="people" v-for="item in PeopleList">
                <div class="pImg">
                    <img :src="item.personnelAvatar" alt="">
                    <i class="block"></i>
                </div>
                <div class="pText">
                    <h5>{{ item.personnelName }}</h5>
                    <span>{{ item.personnelPeriod }}年</span>
                    <span>{{ item.personnelDepartment }}</span>
                    <ReadMore class="readmore" color="#6e7272" :people="item" :link="linkprop"
                        :period="item.personnelPeriod" :part="item.personnelDepartment"></ReadMore>
                </div>
            </div>

        </div>

        <!-- <el-carousel :interval="4000" indicator-position="outside" type="card" height="500px"
            style="height: 500px;margin-top: 30px;">
            <el-carousel-item v-for="item in PeopleList" :key="item">
                <div class="people">
                    <Avatar :name="item.personnelName" :avatar="item.personnelAvatar" :period="item.personnelPeriod"
                        :department="item.personnelDepartment" :post="item.personnelPost" :say="item.personnelSaying"
                        :contribute="item.personnelContribute"></Avatar>
                    <ReadMore class="readmore" :people="item" :link="linkprop" :period="item.personnelPeriod"
                        :part="item.personnelDepartment"></ReadMore>

                </div>
            </el-carousel-item>
        </el-carousel> -->
    </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import Avatar from '@/components/people/avatar.vue'
import ReadMore from '@/components/readmore/index.vue'
import { getPeoples } from "@/api/people/people"
import { parseLanzouLink } from '@/utils/getFileByBackend';

const linkprop = ref('/people')



const PeopleList = ref<Array<{
    personnelName: string;
    personnelAvatar: string;
    personnelPeriod: string;
    personnelDepartment: string;
    personnelPost: string;
    personnelContribute: string;
    personnelSaying: string;
    personnelOrder: number;
}>>([]);

const PeopleListParams = ref({
    pageNum: 1,
    pageSize: 10,
    department: "软件开发",
    period: 2022
})
const getPeopleList = async () => {
    try {
        const result = await getPeoples(PeopleListParams.value);
        PeopleList.value = result.data.records;
        PeopleList.value.forEach(async e => {
            e.personnelAvatar = await parseLanzouLink(e.personnelAvatar);
        })

    } catch (error) {
        console.error('Error fetching data:');
    } finally {

    }
};

onMounted(() => {
    getPeopleList();
})

</script>

<style scoped>
.el-carousel__item h3 {
    color: #475669;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
    text-align: center;
}

.el-carousel__item:nth-child(2n) {
    /* background-color: #adb8c8; */
}

.el-carousel__item:nth-child(2n + 1) {
    /* background-color: #d3dce6; */
}


.homePeople {
    display: flex;
    /* align-items: center; */
    /* justify-content: center; */
    flex-direction: column;
    height: 100%;
    overflow: hidden;
    padding: 0 11em;
    padding-bottom: 10%;
    /* margin-top: 100px; */
    padding-top: 130px;
    background-color: white;

    h1 {
        color: black;
        margin: 1% 0 5% 0;
    }
}

.peoples {
    display: flex;
    width: 100%;
    overflow: hidden;
    word-spacing: normal;
    flex-wrap: wrap;
}

.people {
    width: 25%;
    float: left;
    height: 22em;
    padding-left: 32px;
    box-sizing: border-box;
    position: relative;
    background: #FBFBFB;
    cursor: pointer;
    transition: all .4s;
}

.people:hover {
    background: #F2F3F5;
}

.people:hover .block {
    left: 0px;
    bottom: 0px;
}

.pImg {
    position: absolute;
    right: 0;
    top: 0;

    img {
        width: 120px;
        height: 120px;
    }
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

    h5 {
        font-size: 20px;
        line-height: 30px;
        color: #0052d9;
        margin-bottom: .6em;
    }

    span {
        width: 100%;
        margin-bottom: .6em;
        font-size: 17px;
        line-height: 21px;
        color: #5f6464;
    }
}


/* 头像 */
.avatar {
    width: 158px;
    height: 158px;
    border-radius: 50%;
}



.readmore {
    position: absolute;
    bottom: 22px;
    left: 32px;
}



@keyframes load89234 {
    100% {
        background-position: -100% 0;
    }
}

/* 移动端适配 */
@media (max-width: 1248px) {
    .homePeople {
        display: flex;
        /* align-items: center; */
        /* justify-content: center; */
        flex-direction: column;
        height: 100%;
        overflow: hidden;
        padding: 2% 6%;
        padding-top: 20%;
        background-color: white;
    }

    .peoples {
        flex-direction: column;
    }

    .people {
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
    }
}
</style>
