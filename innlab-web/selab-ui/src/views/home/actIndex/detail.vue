<template>
    <div class="outer">
        <!-- 右侧小圆点 -->
        <ul id="menu">
            <li data-menuanchor="firstPage" class="active"><a href="javascript:void(0)" @click="jp(1)"></a></li>
            <li data-menuanchor="secondPage"><a href="javascript:void(0)" @click="jp(2)"></a></li>
            <!-- <li data-menuanchor="3rdPage"><a href="javascript:void(0)" @click="jp(3)"></a></li> -->
        </ul>

        <div class="lowceng">
            <div class="white"></div>
            <div class="low">
                <a href="javascript:if(confirm('http://www.google.cn/intl/zh-CN/chrome/browser/  \n\n���ļ�δ�� Teleport Pro ���أ���Ϊ ��λ����ʼ��ַ�����õı߽���������·���С�  \n\n����Ҫ�ӷ�����������?'))window.location='http://www.google.cn/intl/zh-CN/chrome/browser/'"
                    tppabs="http://www.google.cn/intl/zh-CN/chrome/browser/"></a>
            </div>
        </div>

        <!-- 头部 -->
        <div>
            <Header :islogoWhite="logoWhite" :color="headerColor"></Header>
        </div>

        <div class="cengboxindex">
        </div>

        <div class="entrybox">

            <div class="top"></div>
        </div>

        <!-- 主页 -->
        <div id="fullpage" class="fullpage-wrapper">

            <!-- banner -->
            <!-- <div class="section fp-section" id="section0" data-anchor="firstPage">
                <div class="outer">

                    <div class="content">
                        <div class="img">
                            <img src="../../../../public/img/homeImg/banner.png" alt="">
                        </div>
                        <div class="text">

                        </div>
                    </div>
                </div>
            </div> -->

            <!-- <div class="section fp-section" id="section1" data-anchor="secondPage">
                <Inten></Inten>
            </div> -->

            <!-- intention -->
            <!-- <div class="section fp-section" id="section2" data-anchor="3rdPage"
                style="height: 100%;background-size: cover; background-color: #071428;">
                <div class="center">
                    <div class="title">
                        <h1 class="topTitle">建立初衷
                        </h1>
                       
                    </div>
                    <div class="service">
                        <ul class="servicelist">
                            <li>
                                <div class="Icobox">
                                    <div class="serviceIco serviceIco1 step step3 scaleBig" data-delay="800">
                                        <span>知识</span>
                                        <span>积累</span>
                                    </div>
                                </div>
                                <div class="servicebox step step4 ifadeIn" data-delay="1800">
                                    <p class="intentionText">实验室提供多样化的知识学习机会，让学生能够接触到更广泛的领域和技术，积累丰富的知识储备，为未来的发展打下坚实的基础。
                                    </p>
                                </div>
                            </li>
                            <li>
                                <div class="Icobox">
                                    <div class="serviceIco serviceIco2 step step3 scaleBig" data-delay="1000">
                                        <span>交流</span>
                                        <span>平台</span>
                                    </div>
                                </div>
                                <div class="servicebox step step5 ifadeIn" data-delay="1800">
                                    <p class="intentionText">实验室为学生提供了一个交流和合作的平台，让他们可以与同学、指导老师进行技术交流和合作，共同学习、成长和进步。</p>
                                </div>
                            </li>
                            <li>
                                <div class="Icobox">
                                    <div class="serviceIco serviceIco3 step step3 scaleBig" data-delay="1200">
                                        <span>思维</span>
                                        <span>培养</span>
                                    </div>
                                </div>
                                <div class="servicebox step step6 ifadeIn" data-delay="1800">
                                    <p class="intentionText">实验室鼓励学生积极思考、探索创新，培养学生的创新思维和创造力，引导他们在实践中寻找新的解决方案和应用。</p>
                                </div>
                            </li>
                            <li>
                                <div class="Icobox">
                                    <div class="serviceIco serviceIco4 step step3 scaleBig" data-delay="1400">
                                        <span>技能</span>
                                        <span>提升</span>
                                    </div>
                                </div>
                                <div class="servicebox step step7 ifadeIn" data-delay="1800">
                                    <p class="intentionText">实验室注重培养学生的实际操作能力和解决问题的技能，通过项目实践和团队合作，让学生在实践中不断提升技术和解决问题的能力。
                                    </p>
                                </div>
                            </li>
                            <li>
                                <div class="Icobox">
                                    <div class="serviceIco serviceIco5 step step3 scaleBig" data-delay="1600">
                                        <span>专业</span>
                                        <span>提升</span>
                                    </div>
                                </div>
                                <div class="servicebox step step8 ifadeIn" data-delay="1800">
                                    <p class="intentionText">旨在为学生提供一个良好的学习环境和平台，以提升他们的专业技术能力，使其更好地适应社会需求。</p>
                                </div>
                            </li>
                            <li>
                                <div class="Icobox">
                                    <div class="serviceIco serviceIco6 step step3 scaleBig" data-delay="1800">
                                        <span>结识</span>
                                        <span>好友</span>
                                    </div>
                                </div>
                                <div class="servicebox step step9 ifadeIn" data-delay="1800">
                                    <p class="intentionText">提供一个聚集志同道合伙伴的平台，让学生们在共同兴趣和目标下共同成长。</p>
                                </div>
                            </li>
                            <li>
                                <div class="Icobox">
                                    <div class="serviceIco serviceIco7 step step3 scaleBig" data-delay="2000">
                                        <span>实践</span>
                                        <span>应用</span>
                                    </div>
                                </div>
                                <div class="servicebox step step9 ifadeIn" data-delay="2000">
                                    <p class="intentionText">鼓励学生将学到的知识应用于实际场景中，培养解决问题的能力和创新思维。</p>
                                </div>
                            </li>

                        </ul>
                    </div>
                </div>
            </div> -->


            <div class="section fp-section" id="section0" data-anchor="firstPage">
                <Inten></Inten>
            </div>

            <div class="section fp-section" id="section1" data-anchor="secondPage">
                <div class="center">
                    <div class="title">
                        <h1 class="topTitle">建立初衷
                        </h1>
                        <!-- <p>全方位的家庭服务，一键下单，尽享清闲生活</p> -->
                    </div>
                    <div class="service">
                        <ul class="servicelist">
                            <li>
                                <div class="Icobox">
                                    <div class="serviceIco serviceIco1 step step3 scaleBig" data-delay="800">
                                        <span>知识</span>
                                        <span>积累</span>
                                    </div>
                                </div>
                                <div class="servicebox step step4 ifadeIn" data-delay="1800">
                                    <p class="intentionText">实验室提供多样化的知识学习机会，让学生能够接触到更广泛的领域和技术，积累丰富的知识储备，为未来的发展打下坚实的基础。
                                    </p>
                                </div>
                            </li>
                            <li>
                                <div class="Icobox">
                                    <div class="serviceIco serviceIco2 step step3 scaleBig" data-delay="1000">
                                        <span>交流</span>
                                        <span>平台</span>
                                    </div>
                                </div>
                                <div class="servicebox step step5 ifadeIn" data-delay="1800">
                                    <p class="intentionText">实验室为学生提供了一个交流和合作的平台，让他们可以与同学、指导老师进行技术交流和合作，共同学习、成长和进步。</p>
                                </div>
                            </li>
                            <li>
                                <div class="Icobox">
                                    <div class="serviceIco serviceIco3 step step3 scaleBig" data-delay="1200">
                                        <span>思维</span>
                                        <span>培养</span>
                                    </div>
                                </div>
                                <div class="servicebox step step6 ifadeIn" data-delay="1800">
                                    <p class="intentionText">实验室鼓励学生积极思考、探索创新，培养学生的创新思维和创造力，引导他们在实践中寻找新的解决方案和应用。</p>
                                </div>
                            </li>
                            <li>
                                <div class="Icobox">
                                    <div class="serviceIco serviceIco4 step step3 scaleBig" data-delay="1400">
                                        <span>技能</span>
                                        <span>提升</span>
                                    </div>
                                </div>
                                <div class="servicebox step step7 ifadeIn" data-delay="1800">
                                    <p class="intentionText">实验室注重培养学生的实际操作能力和解决问题的技能，通过项目实践和团队合作，让学生在实践中不断提升技术和解决问题的能力。
                                    </p>
                                </div>
                            </li>
                            <li>
                                <div class="Icobox">
                                    <div class="serviceIco serviceIco5 step step3 scaleBig" data-delay="1600">
                                        <span>专业</span>
                                        <span>提升</span>
                                    </div>
                                </div>
                                <div class="servicebox step step8 ifadeIn" data-delay="1800">
                                    <p class="intentionText">旨在为学生提供一个良好的学习环境和平台，以提升他们的专业技术能力，使其更好地适应社会需求。</p>
                                </div>
                            </li>
                            <li>
                                <div class="Icobox">
                                    <div class="serviceIco serviceIco6 step step3 scaleBig" data-delay="1800">
                                        <span>结识</span>
                                        <span>好友</span>
                                    </div>
                                </div>
                                <div class="servicebox step step9 ifadeIn" data-delay="1800">
                                    <p class="intentionText">提供一个聚集志同道合伙伴的平台，让学生们在共同兴趣和目标下共同成长。</p>
                                </div>
                            </li>
                            <li>
                                <div class="Icobox">
                                    <div class="serviceIco serviceIco7 step step3 scaleBig" data-delay="2000">
                                        <span>实践</span>
                                        <span>应用</span>
                                    </div>
                                </div>
                                <div class="servicebox step step9 ifadeIn" data-delay="2000">
                                    <p class="intentionText">鼓励学生将学到的知识应用于实际场景中，培养解决问题的能力和创新思维。</p>
                                </div>
                            </li>

                        </ul>
                    </div>
                </div>
            </div>

        </div>



    </div>
</template>
<script setup lang="ts">
import { ref, reactive, onMounted } from "vue"
import Header from '@/components/header/header.vue'
import Banner from "@/components/banner/banner.vue"
import Desc from "@/views/home/components/description/index.vue"
import Act from "@/views/home/components/activity/index.vue"
import People from "@/views/home/components/people/index.vue"
import Part from "@/views/home/components/part/index.vue"
import Tooltip from '@/components/tooltip/index.vue'
import Inten from '@/views/home/components/intention/index.vue'

// 页面滚动
import './js/jquery.min_78bc357.js';
import './js/jquery.fullPage.min_688fc6d.js';
import './js/jquery.easings.min_80a8980.js'
import './js/page_3b93263.js'
import './js/jquery.cookie_119dc0f.js';

import bus from '@/eventBus';

import { getHistoryList } from '@/api/history/history.ts';

interface JQuery {
    fullpage(options?: any): void;
}

const actImgs = ref([
    "public/img/homeImg/bg1.jpg",
    "https://ts1.cn.mm.bing.net/th/id/R-C.c535692088f20b46fe10d2033fdd1e9c?rik=gyunSGzwxyqvaA&riu=http%3a%2f%2fseopic.699pic.com%2fphoto%2f50064%2f5999.jpg_wh1200.jpg&ehk=CWiOffIICuMAgVLeEN8Mj2s57mCFCAR%2fl5wjTmaGjrs%3d&risl=&pid=ImgRaw&r=0",

    "https://ts1.cn.mm.bing.net/th/id/R-C.dadca894207a4d0ed84d8f660aecbe9d?rik=1NY%2fCtVnHKJ%2fPA&riu=http%3a%2f%2fres.dps.cn%2fres%2fbgimg%2f18674%2fk1-1.jpg&ehk=U8DrQVfzEHUrFj3YU4L1Gj2Lp4uF3MTGHUQznsg7Qok%3d&risl=&pid=ImgRaw&r=0",
])

const headerColor = ref('white');
const logoWhite = ref(true);

const isOdd = (index) => {
    return index % 2 === 1;
}

const historyList = ref<Array<{ historyTime: string; historyTitle: string; historyContent: string }>>([]);
const getHistorys = async () => {
    try {
        const result = await getHistoryList();
        console.log('*', result.data);
        historyList.value = result.data.records;

    } catch (error) {
        console.error('Error fetching data:');
    } finally {

    }
};

onMounted(() => {
    //加载动画
    setTimeout(() => {
        bus.emit('loading', false);
    }, 100);

    getHistorys();

    // 页面滚动
    $('#fullpage').fullpage({
        sectionsColor: ['#fff', '#fff', '#fff', '#fff', '#fff', '#fff'],
        lockAnchors: true,
        menu: '#menu',
        css3: true,
        scrollingSpeed: 800,
        afterLoad: function (anchorLink: any, index: number) {
            console.log('index: ', index);

            if (index == 1) {
                headerColor.value = 'white'
                logoWhite.value = true
                $(".top").hide();
            } else {
                $(".top").show();
            }

            if (index == 2 || index == 3 || index == 4 || index == 5 || index == 6) {
                headerColor.value = '#4474c4'
                logoWhite.value = false
            }
        }
    });

    $('.top').click(function () {
        $.fn.fullpage.moveTo(1);
    });




});

function jp(index: any) {
    $.fn.fullpage.moveTo(index);

}


</script>
<style scoped>
@import url('./homebase.css');

.outer {
    /* background-image: url(../../../public/img/homeImg/bg7.jpg); */
    /* background-color: #091627; */
    background-size: cover;
    background-attachment: fixed;
    /* 让背景固定 */
    height: 100vh;
    /* 设置容器高度为视口高度 */

}

#section0 {
    background-image: url(public/img/homeImg/bg1.jpg);

    .outer {
        width: 100%;
        height: 100%;
        display: flex;
        flex-direction: column;
        justify-content: center;

        padding: 0 200px;

        .firstTitle {
            margin-top: 16%;
            display: flex;
            color: white;
        }

        .content {
            height: 60%;
            display: flex;
            flex-direction: column;
            align-items: center;

            .img {

                img {
                    width: 90%;
                    height: auto;
                }
            }
        }
    }
}

#section1 {
    width: 100%;
    height: 100% !important;
}
</style>
