<template>
    <div class="blog">
        <div class="container">
            <div class="info">

                <section id="content">
                    <h3>{{ proTitle }}</h3>

                    <!-- <v-md-editor v-model="mdContent"></v-md-editor> -->
                    <v-md-preview :text="mdContent"></v-md-preview>
                </section>

            </div>

            <div class="menus">
                <div class="title">
                    <span>{{ proPart }}</span>
                </div>
                <div class="content">
                    <span>pro</span>
                </div>
            </div>

        </div>


    </div>
</template>
<script setup lang="ts">
import { ref, reactive, onMounted, watch } from "vue"
import Action from "@/views/blog/components/action/index.vue"
import Comment from "@/views/blog/components/comment/index.vue"
import { useRouter } from 'vue-router';
import { getActInfo, getPartActInfo, getMd } from '@/api/blog/blog'
import bus from '@/eventBus';
import axios from "axios";

const proPart = ref('')
const mdContent = ref()
const proTitle = ref('')
// watch(mdContent, (newValue, oldValue) => {
//     console.log('mdContent changed:', newValue);
//     // 在这里可以执行你需要的操作，比如更新 UI、发送请求等
// });

const route = useRouter();
const props = defineProps<{
    part: string
}>()


// 根据路由参数获取对应活动id
const actId = ref(route.currentRoute.value.query.actid);
const partId = ref(route.currentRoute.value.query.id);
interface proInfo {
    itemId: number;
    itemTitle: string;
    itemIntroduction: string;
    headerImage: number;
    contentMarkdown: string;
    isTop: boolean;
    createTime: string;
    updateTime: string;

}
const actInfo = ref<proInfo | null>(null);
// 根据id获取活动详情
const getActvityInfo = async () => {
    try {
        const result = await getPartActInfo(partId.value);

        actInfo.value = result.data;
        proTitle.value = result.data.itemTitle;
        proPart.value = result.data.itemIntroduction;

        const base64Content = result.data.contentMarkdown;
        const byteArray = Uint8Array.from(atob(base64Content), c => c.charCodeAt(0));
        const decoder = new TextDecoder('utf-8');
        const decodedContent = decoder.decode(byteArray);
        console.log('Decoded Content:', decodedContent);
        mdContent.value = decodedContent;




    } catch (error) {
        console.error('Error fetching data:');
    } finally {

    }
};

onMounted(async () => {
    setTimeout(() => {
        bus.emit('loading', false);
    }, 200);
    proPart.value = props.part ? props.part : '项目列表'
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
    background-image: url(public/img/homeImg/bg5.jpg);
    background-size: 100% 100%;
    padding-top: 10%;
}

.container {
    width: 100%;
    /* background-color: white; */
    display: flex;
}

.info {
    display: flex;
    flex-direction: column;
    width: 1100px;
    min-height: 1000px;
    padding: 80px;
    /* background-color: rgba(28, 71, 119, .9); */
    background-image: url(public/img/disImg/back1.png);
    background-size: 100% 100%;
    color: white
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

#content {
    display: flex;
    flex-direction: column;
    align-items: center;
}

#content h3 {
    color: white;
    font-size: 30px;
    letter-spacing: 3px;

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



/* about */
.about {
    padding: 30px;
    padding-top: 60px;
}

.menus {
    width: 26%;
    color: white;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    /* position: fixed;
    right: 3%; */

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
        padding: 12% 13%;
        align-items: flex-start;
        padding-top: 20%;
        font-size: 22px;
        font-weight: 600;

        span {
            margin-bottom: 8%;
        }
    }
}
</style>
