<template>
    <div class="homePeople">
        <h1 style="color: white;">人员介绍</h1>
        <el-carousel :interval="4000" type="card" height="500px" style="height: 500px;margin-top: 30px;">
            <el-carousel-item v-for="item in PeopleList" :key="item">
                <div class="people">
                    <Avatar :name="item.personnelName" :avatar="item.personnelAvatar" :period="item.personnelPeriod"
                        :department="item.personnelDepartment" :post="item.personnelPost" :say="item.personnelSaying"
                        :contribute="item.personnelContribute"></Avatar>
                    <ReadMore class="readmore" :link="linkprop" :period="item.personnelPeriod"
                        :part="item.personnelDepartment"></ReadMore>

                </div>
            </el-carousel-item>
        </el-carousel>
    </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import Avatar from '@/components/people/avatar.vue'
import ReadMore from '@/components/readmore/index.vue'
import { getPeoples } from "@/api/people/people"

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
    period: 2021
})
const getPeopleList = async () => {
    try {
        const result = await getPeoples(PeopleListParams.value);
        PeopleList.value = result.data.records;

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
    height: 900px;
    overflow: hidden;
    padding: 0 120px;
    margin-top: 100px;
    padding-top: 130px;

}

/* 头像 */
.avatar {
    width: 158px;
    height: 158px;
    border-radius: 50%;
}

.people {
    width: 100%;
    height: 100%;
    display: flex;
}

.readmore {
    position: absolute;
    bottom: 22px;
    right: 10px;
}



@keyframes load89234 {
    100% {
        background-position: -100% 0;
    }
}
</style>
