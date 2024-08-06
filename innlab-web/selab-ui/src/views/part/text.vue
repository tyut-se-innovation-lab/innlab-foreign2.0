<template>
    <div class="outer">
        <div class="part">


            <div class="content">
                <div class="content-r">
                    <div class="img">
                        <img src="https://tse3-mm.cn.bing.net/th/id/OIP-C.u_8q93mlpEMKqdpRRazXfQHaDc?w=319&h=162&c=7&r=0&o=5&cb=10&dpr=1.5&pid=1.7"
                            alt="">
                    </div>
                    <div class="img">
                        <img src="https://tse1-mm.cn.bing.net/th/id/OIP-C.Ds8ploTR614jC_Hmbg_3YgHaEm?w=273&h=180&c=7&r=0&o=5&cb=10&dpr=1.5&pid=1.7"
                            alt="">
                    </div>
                    <div class="img">
                        <img src="https://tse2-mm.cn.bing.net/th/id/OIP-C.HtdQx6r5hLlk_TSEn1pC2wHaDW?w=320&h=180&c=7&r=0&o=5&cb=10&dpr=1.5&pid=1.7"
                            alt="">
                    </div>
                    <div class="img">
                        <img src="https://tse2-mm.cn.bing.net/th/id/OIP-C.Sy-2NQoygS0cKqAyrMYadAHaEJ?w=312&h=180&c=7&r=0&o=5&cb=10&dpr=1.5&pid=1.7"
                            alt="">
                    </div>
                </div>
                <div class="content-l">

                    <h2>{{ partName }}</h2>

                    <span> 软件开发作为创新实验室的一个部门，会不定期参与专业性
                        比赛、接手实验室负责老师提供的实践项目等活动。对于刚进入
                        开发小组的新生，主要以了解、学习专业知识为主。期间会有学
                        长学姐们专门指导，规划学习路线，提供实战项目来巩固同学们
                        的专业知识。随着学习的深入，按照学习情况与自身兴趣，会为
                        同学们提供更加明确的学习方向，主要分为后端、前端、运维、
                        算法以及产品 5 个方向，在学习一段时间后会提供实际开发项目，
                        以此来积累项目开发经验 、提升团队合作能力，实践项目也可
                        列在简历中作为项目经历。</span>
                    <div class="circle"></div>

                </div>

            </div>


        </div>
        <div class="pros">
            <h1 class="prosTitle">日常动态</h1>



            <div class="bottom">
                <div class="botHead">
                    <h3>项目列表</h3>

                </div>
                <div v-if="total !== 0" class="botlists">
                    <div class="botlist" v-for="item in ProList" @click="linkTo(item.itemId)">
                        <div class="botImg">
                            <img :src="item.headerImage" alt="">
                        </div>
                        <div class="botText">
                            <div class="botTextTag">
                                <span>分类</span>
                            </div>
                            <h3>{{ item.itemTitle }}</h3>
                            <p>{{ item.itemIntroduction }}
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
                </div>
                <div v-else class="botlistnone">
                    <span>暂无</span>
                </div>
                <el-pagination v-if="total !== 0" background layout="prev, pager, next"
                    v-model:current-page="params.pageNum" v-model:page-size="params.pageSize" :total="total"
                    @size-change="handleSizeChange" @current-change="handleCurrentChange" />
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
import bus from '@/eventBus';
import { getPros } from '@/api/pro/pro'
import { parseLanzouLink } from '@/utils/getFileByBackend';

const router = useRouter();
const routeQuery = useRoute();
const partName = ref(router.currentRoute.value.query.name);


let index = 0;
document.querySelector('.content-l button')?.addEventListener('click', () => {
    index++;
    if (index % 2 === 1) {
        document.querySelector('.content-l h2')?.setAttribute("style",
            "transform: translate(-180px, 10px); width: 50%;");
        document.querySelector('.content-l span')?.setAttribute("style",
            "transform: translate(-180px, 10px); width: 50%; font: 400 25px 'Gochi Hand'; line-height: 1.5; ");
        document.querySelector('.content-l button')?.setAttribute("style",
            "width: 300px; height: 55px; top: 20%; background-position-x: 70px; transform: rotate(180deg);");
        document.querySelector('.circle')?.setAttribute("style",
            "width: 400px;height: 400px;left: 57%;top: 50%;border: 23px #ffffff dashed; ");
        document.querySelector('.content-l img')?.setAttribute("style",
            "bottom: 0px;");
        document.querySelector('.content-l')?.setAttribute("style",
            "width: 63%;");
        document.querySelector('.content-r')?.setAttribute("style",
            "width: 37%;");
        document.querySelector('.img')?.setAttribute("style",
            "padding: 10px; transform: scale(1);");
        document.querySelector('.img img')?.setAttribute("style",
            "transform: rotate(0);width: 100%;height: 100%;box-shadow: none;border-radius: 30px;");

        // 伪元素
        let style = document.createElement('style');
        style.innerHTML = `
            .content::before {
                width: 900px;
                height: 900px;
                left: 49%;
                bottom: -27%;
            }
        `;
        document.head.appendChild(style);

        style = document.createElement('style');
        style.innerHTML = `
            .content::after {
                opacity: 0;
            }
        `;
        document.head.appendChild(style);

    } else {
        document.querySelector('.content-l h2')?.setAttribute("style",
            " transform: translateY(-30px); width: 80%;");
        document.querySelector('.content-l span')?.setAttribute("style",
            "transform: translateY(-30px); width: 80%; font: 100 20px 'Gochi Hand'; line-height: 2; ");
        document.querySelector('.content-l button')?.setAttribute("style",
            "width: 150px; height: 50px; top: 80%; background-position-x: 0; transform: rotate(0deg);");
        document.querySelector('.circle')?.setAttribute("style",
            "width: 800px;height: 800px;left: -440px;bottom: -440px;border: 43px #fff dashed; ");
        document.querySelector('.content-l img')?.setAttribute("style",
            "bottom: -500px;");
        document.querySelector('.content-l')?.setAttribute("style",
            "width: 40%;");
        document.querySelector('.content-r')?.setAttribute("style",
            "width: 60%;");
        document.querySelector('.img')?.setAttribute("style",
            "padding: 0px;  ");
        document.querySelector('.img img')?.setAttribute("style",
            "width: 60%;height: 90%;box-shadow: 10px 20px 28px #0c48888c;border-radius: 0px;");

        // 伪元素
        let style = document.createElement('style');
        style.innerHTML = `
            .content::before {
                width: 700px;
                height: 700px;
                left: -420px;
                bottom:-420px;
            }
        `;
        document.head.appendChild(style);

        style = document.createElement('style');
        style.innerHTML = `
            .content::after {
                opacity: 1;
            }
        `;
        document.head.appendChild(style);
    }
});

// 点击跳转
const linkTo = (item: number) => {
    router.push({ path: '/proMd', query: { id: item, part: partName.value } });
}

const total = ref(0)
const params = ref({
    pageNum: 1,
    pageSize: 10,
    department: partName.value
})

const ProList = ref<Array<{ itemId: number; itemTitle: string; itemIntroduction: string; headerImage: object; createTime: string }>>([]);


const getProList = async () => {
    console.log('partpara: ', params.value);
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

const handleSizeChange = () => {
    getProList();
}
const handleCurrentChange = () => {
    getProList();
}

const actTag = ref(0);
const handleTag = (index) => {
    actTag.value = index;
}

// 监视路由参数的变化
watch(
    () => routeQuery.query,
    () => {
        // 执行页面刷新操作
        router.go(0);
    },
    { deep: true } // 深度监视对象的变化
);

onMounted(async () => {
    setTimeout(() => {
        bus.emit('loading', false);
    }, 200);

    window.scrollTo({ top: 0, behavior: 'smooth' });

    await getProList();
});

</script>

<style scoped>
.outer {
    background-image: url(public/img/homeImg/bg5.jpg);
    background-position: 10%;
    background-repeat: no-repeat;
    background-size: 100% 100%;

    padding-top: 60px;

}

* {
    padding: 0;
    margin: 0;
    box-sizing: border-box;
    list-style: none;
    text-decoration: none;
    transition: all 520ms cubic-bezier(0.000, 1.650, 0.635, 0.785);
}

::after,
::before {
    transition: .3s;
}

.part {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    overflow: hidden;
    padding: 5%;
}

.content {
    display: flex;
    width: 90%;
    height: 760px;

    border-radius: 10px;
    position: relative;
}

/* .content::after {
    content: '';
    width: 100px;
    height: 200vh;
    position: absolute;
    top: -100vh;
    right: 230px;
    border: 100px solid #fff;
    z-index: -999;
} */

.content::before {
    content: '';
    width: 770px;
    height: 770px;
    position: absolute;
    bottom: -420px;
    left: -420px;
    border-radius: 50%;
    border: 43px #074c97 dotted;
    animation: rotate 100s infinite linear;
    z-index: 0;
}

.content-l,
.content-r {
    position: relative;
    transform-origin: center;
    display: flex;
    justify-content: center;
    align-items: center;
    overflow: hidden;
}

.content-l {
    width: 40%;
    background: linear-gradient(180deg, #76b5d5, #024794);
    flex-direction: column;
    color: #fff;
}

.content-l img {
    position: absolute;
    right: 20px;
    width: 300px;
    bottom: -500px;
    transform: rotateY(180deg);
}

.content-r {
    flex-wrap: wrap;
    width: 60%;
    background-color: #87c2e6b4;
}

.nav {
    display: flex;
    width: 95%;
    height: 40px;
    justify-content: space-evenly;
    position: absolute;
    top: 0px;
    transform: translateX(-2%);
}

.nav li {
    position: relative;
    width: 25%;
    height: 100%;
    text-align: center;
    border-right: 1px dashed #000;
    font: 100 24px 'Gochi Hand';
}

.nav li a {
    display: block;
    width: 100%;
    height: 100%;
    line-height: 40px;
    color: #fff;
}

.nav li:hover a {
    color: #6AA8EC;
    background-color: #ffffff;
}

.nav li:nth-child(4) {
    border: none;
}



.content-l h2 {
    font: 200 80px '';
    font-family: 'Fredoka One', sans-serif;
    position: relative;
}

.content-l span {
    color: white;
    font: 100 20px 'Gochi Hand';
    line-height: 2;
    transition: .3s;
}

.content-l h2,
.content-l span {
    z-index: 10;
    width: 80%;
    transform: translateY(-30px);

}


.content-l button {
    width: 130px;
    height: 50px;
    border: #ffffffc0 solid 3px;
    background-color: #0c48887a;
    border-radius: 3px;
    position: absolute;
    right: 40px;
    top: 80%;
    background-image: url(./img/button.png);
    background-size: contain;
    background-repeat: no-repeat;
}

.content-l button:hover {
    background-color: #ffffff7a;
}

.circle {
    width: 800px;
    height: 800px;
    position: absolute;
    bottom: -440px;
    left: -440px;
    border-radius: 50%;
    border: 43px #4486b9 dashed;
    animation: rotate 100s infinite linear;
}


@keyframes rotate {
    from {
        transform: rotate(0deg);
    }

    to {
        transform: rotate(360deg);
    }
}



.img {
    perspective: 800px;
    position: relative;
    width: 50%;
    height: 50%;
    transform-style: preserve-3d;
    display: flex;
    justify-content: center;
    align-items: center;
}

.img:nth-child(1) {
    transform: scale(1.3) translate(0px, 80px);
}

.img:nth-child(2) {
    transform: translate(-120px, 100px);
}

.img:nth-child(3) {
    transform: translate(120px, -100px);
}

.img:nth-child(4) {
    transform: scale(1.3) translate(-0px, -80px);
}

.img:nth-child(3) img {
    transform: rotateY(-35deg);

}

.img:nth-child(4) img {
    transform: rotateY(-35deg);

}

.img img {
    transform: rotateY(35deg);
    position: relative;
    width: 60%;
    height: 90%;
    border: 10px #fff solid;
    box-shadow: 10px 20px 28px #0c48888c;
}

.img img:hover {
    border: 10px #71aeee dashed;
}

.pros {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    margin: 0 auto;
    padding: 2em 12em;
}

.prosTitle {
    color: white;
    margin: 0 auto;
}

/* bottom list */
.bottom {
    width: 100%;
    min-height: 16em;
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

.botlistnone {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 2em;
    margin: 3% 0;
}

.botHead {
    width: 100%;
    height: 4em;
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
    height: 100%;
    display: flex;
    justify-content: space-evenly;
    align-items: center;
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
    .content {

        width: 90%;
        height: 100%;
        flex-direction: column;
    }

    .content-r {

        width: 100%;
        height: 20em;

    }

    .content-l {
        width: 100%;

    }

    .content-l h2 {
        z-index: 10;
        width: 80%;
        transform: none;
        font-size: 2em;
        text-align: center;
        margin: 0.3em;
    }

    .content-l span {
        z-index: 10;
        width: 82%;
        transform: none;
        font-size: 1em;
        text-align: center;
        margin-bottom: 1em;
    }

    .pros {

        padding: 2em 2em;
    }

    .botHead h3 {
        font-size: 1.2em;
    }

    .botTags {
        width: 100%;
    }

    :deep(.el-tag) {

        width: 4em;
        height: 2.8em;

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

    }

    .botText {
        width: 100%;
        height: 100%;

    }

}
</style>
