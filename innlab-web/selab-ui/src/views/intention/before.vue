<template>

    <div class="outer">
        <div class="section fp-section" id="section1" data-anchor="secondPage" style="height: 100vh;">
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




</template>
<script setup lang="ts">
import { ref, reactive, onMounted } from "vue"
import bus from '@/eventBus';
// 页面滚动
import '@/views/home/actIndex/js/jquery.min_78bc357.js';
import '@/views/home/actIndex/js/jquery.fullPage.min_688fc6d.js';
import '@/views/home/actIndex/js/jquery.easings.min_80a8980.js'
import '@/views/home/actIndex/js/page_3b93263.js'
import '@/views/home/actIndex/js/jquery.cookie_119dc0f.js';
interface JQuery {
    fullpage(options?: any): void;
}

onMounted(() => {

    setTimeout(() => {
        bus.emit('loading', false);
    }, 200);


    window.scrollTo({ top: 0, behavior: 'smooth' });

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

                $(".top").hide();
            } else {
                $(".top").show();
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
@import url('@/views/home/actIndex/homebase.css');

.outer{
    background-image: ;
}
</style>
