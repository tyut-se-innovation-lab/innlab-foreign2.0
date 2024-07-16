<template>
    <div class="blog">
        <div class="container">
            <div class="info">
                <section id="main"><!-- #main content and sidebar area -->
                    <section id="content"><!-- #content -->

                        <article>
                            <h2><a href="#">First Article Title</a></h2>
                            <img src="https://s1.aigei.com/src/img/jpg/10/1032253cf4904ccaae513fe7f5083b12.jpg?imageMogr2/auto-orient/thumbnail/!282x282r/gravity/Center/crop/282x282/quality/85/%7CimageView2/2/w/282&e=1735488000&token=P7S2Xpzfz11vAkASLTkfHN7Fw-oOZBecqeJaxypL:i5jzkB3YhbnxJseb7xxyI3LIvZ8="
                                alt="" class="alignleft" />
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent
                                libero. <a href="#">Duis sagittis ipsum</a>. Sed cursus ante dapibus diam. Sed nisi.
                                Nulla quis sem at nibh elementum imperdiet. Praesent mauris. Fusce nec tellus sed augue
                                semper porta. Mauris massa. Vestibulum lacinia arcu eget nulla. Class aptent taciti
                                sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Curabitur
                                sodales ligula in libero. Sed dignissim lacinia nunc. Duo partem graeco repudiare ne,
                                vix cu graeci nostrud blandit. Agam graecis omnesque usu ut, veniam commune ne sit, vide
                                lucilius complectitur at ius.</p>
                            <p>Eu eirmod interesset temporibus eum, sea cu amet reque quaestio. Qui erroribus assentior
                                disputando an, quo quot accumsan menandri te, accumsan aliquando dissentias mei ea.
                                Autem verear molestiae an mel, nominavi expetenda similique sit ei. Commodo utroque
                                scribentur id nam, an ferri eruditi perpetua his, et has esse facer nominavi. Mei et
                                essent dictas aliquip. Saepe eligendi phaedrum ne vix, sit putant eruditi eloquentiam
                                at.</p>
                        </article>

                        <article>
                            <h2><a href="#">Second Article Title</a></h2>
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent
                                libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum
                                imperdiet. Duis sagittis ipsum. Praesent mauris. Fusce nec tellus sed augue semper
                                porta. Mauris massa. Vestibulum lacinia arcu eget nulla. Class aptent taciti sociosqu ad
                                litora torquent per conubia nostra, per inceptos himenaeos. Curabitur sodales ligula in
                                libero. Sed dignissim lacinia nunc. Usu pericula argumentum repudiandae ea, ut vis
                                deserunt oportere, assum nominavi percipitur ei ius. Nam timeam atomorum philosophia te,
                                ut eum amet laoreet pertinax, an duo error aperiri reformidans. Et alii quas partiendo
                                mei, wisi quaestio convenire et vix.</p>
                        </article>

                    </section><!-- end of #content -->




                </section>
                <section class="action">
                    <ul class="acts">
                        <Action :initialLiked="false" :initialCount="25"
                            unclickSrc="../../../../src/assets/images/blog/赞_un.png"
                            clickedSrc="../../../../src/assets/images/blog/赞.png"></Action>
                        <Action :initialLiked="false" :initialCount="25"
                            unclickSrc="../../../../src/assets/images/blog/评论_un.png"
                            clickedSrc="../../../../src/assets/images/blog/评论.png"></Action>
                        <Action :initialLiked="false" :initialCount="25"
                            unclickSrc="../../../../src/assets/images/blog/分享_un.png"
                            clickedSrc="../../../../src/assets/images/blog/分享.png"></Action>

                    </ul>
                    <div class="comment">
                        <Comment :actId="actId"></Comment>
                    </div>



                </section>
            </div>
            <div class="about">
                <aside id="sidebar"><!-- sidebar -->
                    <h3>Things To Do</h3>
                    <ul>
                        <li><a href="#">Play Games</a></li>
                        <li><a href="#">Chat With Friends</a></li>
                        <li><a href="#">Swap Stories</a></li>
                        <li><a href="#">Sell Stuff</a></li>
                        <li><a href="#">Buy Stuff</a></li>
                        <li><a href="#">Trade Stuff</a></li>
                    </ul>

                    <h3>Sponsors</h3>
                    <img src="https://s1.aigei.com/src/img/jpg/10/1032253cf4904ccaae513fe7f5083b12.jpg?imageMogr2/auto-orient/thumbnail/!282x282r/gravity/Center/crop/282x282/quality/85/%7CimageView2/2/w/282&e=1735488000&token=P7S2Xpzfz11vAkASLTkfHN7Fw-oOZBecqeJaxypL:i5jzkB3YhbnxJseb7xxyI3LIvZ8="
                        alt="" /><br /><img
                        src="https://s1.aigei.com/src/img/jpg/10/1032253cf4904ccaae513fe7f5083b12.jpg?imageMogr2/auto-orient/thumbnail/!282x282r/gravity/Center/crop/282x282/quality/85/%7CimageView2/2/w/282&e=1735488000&token=P7S2Xpzfz11vAkASLTkfHN7Fw-oOZBecqeJaxypL:i5jzkB3YhbnxJseb7xxyI3LIvZ8="
                        alt="" /><br /><br />

                    <h3>Connect With Us</h3>
                    <ul>
                        <li><a href="#">Twitter</a></li>
                        <li><a href="#">Facebook</a></li>
                    </ul>

                </aside><!-- end of sidebar -->
            </div>
        </div>


    </div>
</template>
<script setup lang="ts">
import { ref, reactive, onMounted } from "vue"
import Action from "@/views/blog/components/action/index.vue"
import Comment from "@/views/blog/components/comment/index.vue"
import { useRouter } from 'vue-router';
import { getProInfo } from '@/api/pro/pro'
import { getActInfo } from '@/api/activity/activity'
import bus from '@/eventBus';

const route = useRouter();



// 根据路由参数获取对应活动id
const actId = ref(route.currentRoute.value.query.actid);
const partId = ref(route.currentRoute.value.query.id);
interface ActivityInfo {
    activityId: number;
    title: string;
    introduction: string;
    headerImage: number;
    pageView: number;
    comments: number;
    createTime: string;
    updateTime: string;
    activitySubtitleVos: any[];
}
const actInfo = ref<ActivityInfo | null>(null);
// 根据id获取活动详情
const getActvityInfo = async () => {
    try {
        const result = await getActInfo(actId.value);
        actInfo.value = result.data;
        console.log(result.data);


    } catch (error) {
        console.error('Error fetching data:');
    } finally {

    }
};

onMounted(() => {
    setTimeout(() => {
        bus.emit('loading', false);
    }, 200);

    window.scrollTo({ top: 0, behavior: 'smooth' });

    getActvityInfo();

});

</script>
<style scoped>
.blog {
    display: flex;
    padding: 160px 200px;
    /* background-color: #bfbfbf; */
    background-color: #dedede;
    background-image: url(public/img/homeImg/bg2.jpg);
}

.container {
    width: 100%;
    background-color: white;
    display: flex;
}

.info {
    display: flex;
    flex-direction: column;
    width: 1100px;
    /* height: 1200px; */
    padding: 60px;
    border-right: 5px solid rgb(240, 240, 240);

}

/* main */
#main {
    display: flex;
}

article img {
    border: none;
    -webkit-box-shadow: 3px 3px 7px #777;
    -moz-box-shadow: 3px 3px 7px #777;
}

#content p,
ul,
ol,
hr {
    margin-bottom: 24px;
}

#content ul ul,
ol ol,
ul ol,
ol ul {
    margin-bottom: 0;
}

#content h1,
h2,
h3,
h4,
h5,
h6 {
    color: #333;
    margin: 0 0 20px 0;
    line-height: 1.5em;
}

.alignleft,
img.alignleft {
    display: inline;
    float: left;
    margin-right: 10px;
}

.alignright,
img.alignright {
    display: inline;
    float: right;
    margin-left: 10px;
}

.aligncenter,
img.aligncenter {
    clear: both;
    display: block;
    margin-left: auto;
    margin-right: auto;
}

/* sidebar and footer widget blocks */

aside h3 {
    font-size: 18px;
}

aside ul {
    list-style: none;
    margin-bottom: 20px;
    margin-top: -15px;
}

/* about */
.about {
    padding: 30px;
    padding-top: 60px;
}

.action {
    margin-top: 200px;
    width: 100%;
}

.acts {
    display: flex;

    li {
        width: 80px;
        margin-right: 80px;
        display: flex;
        align-items: center;
        color: #bfbfbf;
        cursor: pointer;

        img {

            width: 30px;
            height: 30px;
            margin-right: 10px;
        }
    }

    li:hover {
        color: #4d7190;
    }

}

.liked {
    color: #4d7190 !important;
}

/* comment */
.comment {
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: space-between;
}
</style>
