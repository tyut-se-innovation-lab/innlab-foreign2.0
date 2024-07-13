<template>
    <div class="homePart">

        <div class="parts">
            <div class="container">
                <div class="parts-top heading">
                    <img class="partTitle" src="public/img/titleImg/part.png" alt="">
                </div>
                <div class="parts-bottom">
                    <div class="partleft">
                        <div class="part" @click="LinkTo(10)">
                            <div class="partcontent">
                                <p class="partcontent_title">软件开发</p>
                                <p class="partcontent_text">开发小组</p>
                            </div>

                        </div>

                        <div class="part " @click="LinkTo(11)">
                            <div class="partcontent">
                                <p class="partcontent_title">人工智能</p>
                                <p class="partcontent_text">AI小组</p>
                            </div>
                        </div>
                    </div>
                    <div class="partmiddle">
                        <span>组织架构</span>
                    </div>
                    <div class="partright">
                        <div class="part " @click="LinkTo(12)">
                            <div class="partcontent">
                                <p class="partcontent_title">网络安全</p>
                                <p class="partcontent_text">网安小组</p>
                            </div>
                        </div>
                        <div class="part " @click="LinkTo(13)">
                            <div class="partcontent">
                                <p class="partcontent_title">虚拟现实</p>
                                <p class="partcontent_text">VR小组</p>
                            </div>
                        </div>
                    </div>

                    <div class="clearfix"> </div>
                </div>
            </div>
        </div>




    </div>
</template>

<script setup lang="ts">
import bus from "@/eventBus";
import router from "@/router";
import { ref, reactive, onMounted, onUnmounted } from "vue"

// 点击跳转
const elementIdMap: Record<number, { path: string; name: string }> = {
    2: { path: '/', name: '首页' },

    10: { path: '/part', name: '软件开发' },
    11: { path: '/part', name: '网络安全' },
    12: { path: '/part', name: '人工智能' },
    13: { path: '/part', name: '虚拟现实' },

};

const LinkTo = (elementId: number) => {
    const routeInfo = elementIdMap[elementId];
    if (routeInfo.path !== '/') {
        router.push({ path: routeInfo.path, query: { id: elementId, name: routeInfo.name } });
        bus.emit('loading', true);

        setTimeout(function () {
            window.location.reload();
        }, 100);

    }
}

</script>

<style scoped>
/* 渐显效果 */
.acShow {
    opacity: 0;
}

.acShow:nth-child(1) {
    transition: all .8s;
}

.acShow:nth-child(2) {
    transition: all 1.6s;
}

.acShow:nth-child(3) {
    transition: all 2.4s;
}

.acShow:nth-child(4) {
    transition: all 3.2s;
}

.homePart {
    display: flex;
    align-items: center;
}

/* parts starts */
.parts {
    width: 100%;
    /* background: url(https://ts1.cn.mm.bing.net/th/id/R-C.4c949f92fb80d24a8622991b9c2df356?rik=gixVWcy%2b2yRtXg&riu=http%3a%2f%2fup.desktx.net%2fpic%2f60%2fca%2fc8%2f60cac8dc968502afda63c6919ca0ba91.jpg&ehk=XknXsO9DM6KM%2b6hZe%2ftdpi4rS6VDItss7ccN8ZNjTqE%3d&risl=&pid=ImgRaw&r=0) fixed; */

    background-repeat: no-repeat;
    background-position: center;
    min-height: 620px;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    -ms-background-size: cover;
    background-size: cover;
    display: flex;
    justify-content: center;
    margin-top: 8%;
    position: relative;
    padding: 0 120px;

    .container {
        width: 100%;
        display: flex;
        flex-direction: column;
        z-index: 10;
    }
}



.parts-top {
    width: 100%;
    height: 20%;
    padding-right: 60px;
    display: flex;
    align-items: center;
    justify-content: center;
}

.partTitle {
    width: 900px;
    height: auto;
}


.parts-bottom {
    width: 100%;
    height: 68%;
    margin-top: 40px;
    display: flex;
    align-items: baseline;
    justify-content: center;
    /* margin-bottom: 80px; */
    padding-right: 60px;
}

.partmiddle {
    width: 50%;
    height: 80%;
    background-image: url(/public/img/partImg/middlebg.png);
    background-size: contain;
    background-position: center;
    display: flex;
    justify-content: center;
    align-items: center;
    color: white;
    font-weight: 600;
    /* padding-bottom: 20px; */
    margin: 0 30px;
    font-size: 180%;
}

.partleft {
    width: 56%;
    height: 100%;

    .part {
        width: 100%;
        height: 52%;
        margin-top: 10%;
        display: flex;
        flex-direction: column;
        align-items: center;

        .partcontent {
            width: 100%;
            height: 100%;
            background-image: url("public/img/partImg/leftbg.png");
            background-size: contain;
            position: relative;

            .partcontent_title {
                font-size: 33px;
                font-weight: 600;
                color: white;
                position: absolute;
                top: 19%;
                right: 26%;
            }

            .partcontent_text {
                font-size: 26px;
                font-weight: 600;
                color: white;
                position: absolute;
                top: 43%;
                right: 18%;
            }
        }
    }
}

.partright {
    width: 56%;
    height: 100%;

    .part {
        width: 100%;
        height: 52%;
        margin-top: 10%;
        display: flex;
        flex-direction: column;
        align-items: center;

        .partcontent {
            width: 100%;
            height: 100%;
            background-image: url("public/img/partImg/rightbg.png");
            background-size: contain;
            position: relative;

            .partcontent_title {
                font-size: 33px;
                font-weight: 600;
                color: white;
                position: absolute;
                top: 19%;
                left: 26%;
            }

            .partcontent_text {
                font-size: 26px;
                font-weight: 600;
                color: white;
                position: absolute;
                top: 43%;
                left: 18%;
            }
        }
    }
}

.part {
    cursor: pointer;
    transition: all .3s;
}

.part:hover {
    transform: scale(1.03);
}


@media (max-width: 1600px) {
    .partcontent_title {
        font-size: 28px !important;
        top: 14% !important;
        right: 26% !important;
    }

    .partcontent_text {
        font-size: 23px !important;
        top: 33% !important;
        right: 19% !important;
    }
}
</style>
