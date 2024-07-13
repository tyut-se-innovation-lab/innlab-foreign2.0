<template>
    <div class="part">

        <div class="info">
            <div class="introduction">
                <div class="introTitle">
                    <span>{{ partName }}</span>
                </div>
                <div class="introMain">

                    <div class="card">
                        <div class="text">
                            <span>软件开发作为创新实验室的一个部门，会不定期参与专业性
                                比赛、接手实验室负责老师提供的实践项目等活动。对于刚进入
                                开发小组的新生，主要以了解、学习专业知识为主。期间会有学
                                长学姐们专门指导，规划学习路线，提供实战项目来巩固同学们
                                的专业知识。随着学习的深入，按照学习情况与自身兴趣，会为
                                同学们提供更加明确的学习方向，主要分为后端、前端、运维、
                                算法以及产品 5 个方向，在学习一段时间后会提供实际开发项目，
                                以此来积累项目开发经验 、提升团队合作能力，实践项目也可
                                列在简历中作为项目经历。开发小组欢迎所有对学习抱有热情、
                                团结友爱的同学，无论你之前是否拥有技术基础和专业知识，只
                                要你抱有一颗勤奋学习的上进心，在开发小组你就可以学习到更
                                多软件开发知识，收获志同道合的朋友。</span>
                            <p class="subtitle">Vivamus nisi purus</p>
                        </div>
                     
                    </div>

                </div>

            </div>

            <Programs></Programs>


        </div>
        <div class="about">
            <div class="aboutTitle">
                <h1>About Us</h1>
            </div>
            <div class="aboutText">
                <span>联系方式：62137891840-3</span>
            </div>
        </div>



    </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted, nextTick } from "vue"
import Programs from '@/views/part/components/programs/programs.vue'
import { useRouter } from 'vue-router';
import { useRoute } from 'vue-router';
import { watch } from 'vue';


const router = useRouter();
const routeQuery = useRoute();
const partName = ref(router.currentRoute.value.query.name);




// 监视路由参数的变化
watch(
    () => routeQuery.query,
    () => {
        // 执行页面刷新操作
        router.go(0);
    },
    { deep: true } // 深度监视对象的变化
);

onMounted(() => {
    console.log(partName.value);
    window.scrollTo({ top: 0, behavior: 'smooth' });
});

</script>

<style scoped>
.part {
    display: flex;
    padding: 160px 200px;
    /* background-color: rgb(240, 240, 240); */
    background-color: #dedede;

}

.info {
    display: flex;
    flex-direction: column;
    width: 1100px;
    min-height: 1200px;
    margin-right: 120px;
    background-color: white;
}

.introduction {
    height: 400px;
    /* box-shadow: 0 0 10px 10px rgba(255, 255, 255, 0.3); */

}

.introTitle {
    height: 60px;
    background-color: white;
    font-size: 26px;
    font-weight: 700;
    display: flex;
    align-items: center;

    span {
        margin-left: 20px;
    }
}

.introMain {
    display: flex;
    justify-content: center;
    height: 340px;
    margin-top: 20px;
}

/* card */
.card {
    width: 1050px;
    height: 330px;
    border-radius: 15px;
    background: #eeeeee;
    display: flex;
    flex-direction: column;
    position: relative;
    overflow: hidden;
}

.card::before {
    content: "";
    height: 300px;
    width: 300px;
    position: absolute;
    top: -20%;
    left: 2%;
    border-radius: 50%;
    border: 35px solid rgba(255, 255, 255, 0.102);
    transition: all .8s ease;
    filter: blur(.5rem);
}

.text {
    flex-grow: 1;
    padding: 15px;
    display: flex;
    flex-direction: column;
    color: rgb(136, 136, 136);
    font-weight: 900;
    font-size: 1.2em;
}

.subtitle {
    font-size: .6em;
    font-weight: 300;
    color: rgba(240, 248, 255, 0.691);
}

.icons {
    display: flex;
    justify-items: center;
    align-items: center;
    width: 250px;
    border-radius: 0px 0px 15px 15px;
    overflow: hidden;
}

.btn {
    border: none;
    width: 84px;
    height: 35px;
    background-color: rgba(247, 234, 234, 0.589);
    display: flex;
    align-items: center;
    justify-content: center;
}

.svg-icon {
    width: 25px;
    height: 25px;
    stroke: rgb(38, 59, 126);
}

.btn:hover {
    background-color: rgb(247, 234, 234);
}

.card:hover::before {
    width: 140px;
    height: 140px;
    top: -30%;
    left: 70%;
    filter: blur(0rem);
}
</style>
