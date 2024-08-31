<template>
    <div class="outer">
        <div class="part">


            <div v-if="partName == '软件开发'" class="content">
                <div class="content-r">
                    <div class="img" v-for="(item, index) in rkImgs">
                        <el-image :src="item" :key="index" preview-teleported :zoom-rate="1.2" :max-scale="7"
                            :min-scale="0.2" :preview-src-list="rkImgs" :initial-index="index" fit="cover" />
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
            <div v-if="partName == '网络安全'" class="content">
                <div class="content-r">
                    <div class="img" v-for="(item, index) in waImgs">
                        <el-image :src="item" :key="index" preview-teleported :zoom-rate="1.2" :max-scale="7"
                            :min-scale="0.2" :preview-src-list="waImgs" :initial-index="index" fit="cover" />

                    </div>

                </div>
                <div class="content-l">

                    <h2>{{ partName }}</h2>

                    <span> 网络安全是指用于防止网络攻击或减轻其影响的任何技术、措施或做法。
                      其旨在保护个人和组织的系统、应用程序、计算设备、敏感数据和金融资产免受
                      计算机病毒、复杂且代价高昂的 勒索软件 攻击等。如果用一个词说明学习网络
                      安全的意义，那一定是“守护”。 我们运用技术保护计算机网络不受攻击者侵害，
                      让网络世界也可 以拥有一片净土。创新实验室网络安全小组以“坚守网络红线，
                      为个人、企业、国家网络安全作保障”为目标，长期致力于渗透 测试、web 安全
                      、APT、攻防等网络空间安全领域的技术和治理研究。</span>
                    <div class="circle"></div>

                </div>

            </div>
            <div v-if="partName == '人工智能'" class="content">
                <div class="content-r">
                    <div class="img" v-for="(item, index) in aiImgs">
                        <el-image :src="item" :key="index" preview-teleported :zoom-rate="1.2" :max-scale="7"
                            :min-scale="0.2" :preview-src-list="aiImgs" :initial-index="index" fit="cover" />
                    </div>
                </div>
                <div class="content-l">

                    <h2>{{ partName }}</h2>

                    <span> 人工智能 (AI) 是一种使计算机和机器能够模拟人类智能和解决问题能力的技术。
                      它可以单独使用或与其他技术（例如，传感器、地理定位、机器人）
                      相结合，执行原本需要人类智能或人工干预的任务。数字助理、GPS 制导、自动驾驶汽车
                      和生成式 AI 工具（如 Open AI 的 Chat GPT）只是日常新闻和我们日常生活中 AI
                      的几个例子。 我们主要的研究方向有：机器学习/深度学习、计算机视觉方向。 具体的研
                      究领域有：AI智慧医疗、3D human、智慧电力调度等。 在这里，我们会为你提供系统性
                      的学习资源，学习路线；会有学长和老师带着你去探索各种前沿的知识；</span>
                    <div class="circle"></div>

                </div>

            </div>
            <div v-if="partName == '虚拟现实'" class="content">
                <div class="content-r">
                    <div class="img" v-for="(item, index) in vrImgs">
                        <el-image :src="item" :key="index" preview-teleported :zoom-rate="1.2" :max-scale="7"
                            :min-scale="0.2" :preview-src-list="vrImgs" :initial-index="index" fit="cover" />

                    </div>
                </div>
                <div class="content-l">

                    <h2>{{ partName }}</h2>

                    <span> 虚拟现实技术（Virtual Reality, VR），又称灵境技术，是20
                        世纪发展起来的一项全新的实用技术。虚拟现实技术囊括计算机、电子信息、仿真技术于一体，其基本实现方式是计算机模拟虚拟环境从而给人以环境沉浸感。随着社会生产力和科学技术的不断发展，各行各业对虚拟现实技术的需求日益旺盛。
                        学院拥有山西省虚拟现实技术研究生教育创新中心，并与山西云极视界科技有限公司签订了虚拟现实人才合作培养协议，为该方向学生专业学习、项目实习等提供了技术支持与实践指导。如果你对虚拟现实感兴趣，想要探索虚拟现实相关的技术与知识，欢迎你加入虚拟现实小组!</span>
                    <div class="circle"></div>

                </div>

            </div>
            <div v-if="partName == '算法竞赛'" class="content">
                <div class="content-r">
                    <div class="img" v-for="(item, index) in sfImgs">
                        <el-image :src="item" :key="index" preview-teleported :zoom-rate="1.2" :max-scale="7"
                            :min-scale="0.2" :preview-src-list="sfImgs" :initial-index="index" fit="cover" />

                    </div>
                </div>
                <div class="content-l">

                    <h2>{{ partName }}</h2>

                    <span>
                        什么是算法？第一次听到这个词是否大脑一片空白？算法是计算机处理信息的本质，学习算法可以让我们更高效地使用计算机来解决各种问题，包括但不限于数学问题，从各种排序问题，最优解问题到图论，博弈论等，随着学习的逐步深入，你会发现算法领域的博大，学习各种算法的原理，惊叹其巧妙的构思，享受ac算法的快乐。大学生算法竞赛,例如ICPC(国际大学生程序设计竞赛)、CCPC(中国大学生程序设计竞赛),蓝桥杯、天梯赛、百度之星等算法竞赛，是以数据结构与算法为主要考察对象的竞赛，其中
                        ICPC 竞赛（国际大学生程序设计竞赛，又称ACM）是公认为全球计算机领域规模最大、影响力最广的赛事之一，被誉为计算机软件领域的奥林匹克。</span>
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
                    <span>暂无内容...</span>
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
import { useRouter } from 'vue-router';
import { useRoute } from 'vue-router';
import { watch } from 'vue';
import bus from '@/eventBus';
import { getPros } from '@/api/pro/pro'
import { parseLanzouLink } from '@/utils/getFileByBackend';
import Bear from '@/components/animation/bear.vue';

const router = useRouter();
const routeQuery = useRoute();
const partName = ref(router.currentRoute.value.query.name);

const rkImgs = [
    "https://tse3-mm.cn.bing.net/th/id/OIP-C.u_8q93mlpEMKqdpRRazXfQHaDc?w=319&h=162&c=7&r=0&o=5&cb=10&dpr=1.5&pid=1.7",
    "https://tse1-mm.cn.bing.net/th/id/OIP-C.Ds8ploTR614jC_Hmbg_3YgHaEm?w=273&h=180&c=7&r=0&o=5&cb=10&dpr=1.5&pid=1.7",
    "https://tse2-mm.cn.bing.net/th/id/OIP-C.HtdQx6r5hLlk_TSEn1pC2wHaDW?w=320&h=180&c=7&r=0&o=5&cb=10&dpr=1.5&pid=1.7",
    "https://tse2-mm.cn.bing.net/th/id/OIP-C.Sy-2NQoygS0cKqAyrMYadAHaEJ?w=312&h=180&c=7&r=0&o=5&cb=10&dpr=1.5&pid=1.7"
]
const waImgs = [
  "https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/45052fe1833fb71b2eadce0795e723fd78b9ec626319ff81b12b6b981ba324d1cbc15a016f95c8a0a0b7572765c3a862?pictype=scale&from=30013&version=3.3.3.3&fname=3aed018196054a80e4e383073eb9fec4.jpg&size=750",
    "https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/e4e9f5b45ea4a4e817a2964fa91d33e92eb8af98a7860b161ef070abc15b9713bb30e43b2cb5a43c51862d6877d14f6e?pictype=scale&from=30013&version=3.3.3.3&fname=61c53297d40593326d1ef55474ed634b.jpg&size=750",
    "https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/0e1eb7deae2928ca0b57028d00dc73e64483ebfeacfc93b6ac3b0502e823ed7412c55550d6f7e739818ef8faf44ed756?pictype=scale&from=30013&version=3.3.3.3&fname=06ab0ffa5d84ad0344d92c6513aee428.png&size=750",
    "https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/b9683369e5ec7af4ba4314d233fbceba00d065b7ca70359886e8cd0b6e373b6bd4192bfa79d9870e396c16f3135a4839?pictype=scale&from=30013&version=3.3.3.3&fname=35af17adfdc3addf3264b8b6ba4ac513.jpg&size=750",
]
const aiImgs = [
    "https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/f87efe4b2a526e04a385dd674612f45853c4632719ad3706e3f009180778ff5c0eb4f4fd491a3dffa4e8ccb6c30d5121?pictype=scale&from=30013&version=3.3.3.3&fname=%E5%9B%BE%E7%89%879.png&size=750",
    "https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/6b198156a6c0683511c307b68236cc9b41194377d837a3ec916ba1805cf8feefd62f5c5e53c2e6916e55c8a8067c3e1d?pictype=scale&from=30013&version=3.3.3.3&fname=%E5%9B%BE%E7%89%8715.png&size=750",
    "https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/148cf6bdc75e9e480592e44d95cf37a22c389543b510f86d5528628b5ea993fdce81ab95e343d45ca5e55cbed3e438cb?pictype=scale&from=30013&version=3.3.3.3&fname=%E5%9B%BE%E7%89%878.png&size=750",
    "https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/83c3039c09b29b68277d92ef1ed07937b421cc480ffb20314bc9602230cce69514acb9a95b018402c2241bb53feb86eb?pictype=scale&from=30013&version=3.3.3.3&fname=%E5%9B%BE%E7%89%8711.png&size=750"
]
const vrImgs = [
    "https://tse3-mm.cn.bing.net/th/id/OIP-C.u_8q93mlpEMKqdpRRazXfQHaDc?w=319&h=162&c=7&r=0&o=5&cb=10&dpr=1.5&pid=1.7",
    "https://tse1-mm.cn.bing.net/th/id/OIP-C.Ds8ploTR614jC_Hmbg_3YgHaEm?w=273&h=180&c=7&r=0&o=5&cb=10&dpr=1.5&pid=1.7",
    "https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/dbf1519ff8e8fe16069b4ee8549a23a33c12ac6e0a787436c856bb3e251433ca8ef0c7a192b299d41d4cebdea645fb22?pictype=scale&from=30013&version=3.3.3.3&fname=%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20240808210647.jpg&size=750",
    "https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/566c234b3f518ecf80f3fb64b98fd7f2028759813081d4ee253049aa5497e2e44cf289cbeb3340f08c8c54fff2d72566?pictype=scale&from=30013&version=3.3.3.3&fname=%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20240808210609.jpg&size=750"
]
const sfImgs = [
    "https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/4f76917a5eba48a2f72043ca12eb1e9944fedc47f119529a6f0a9e84023b9eea10e76975a1c9c72c4061396ed86b0cf8?pictype=scale&from=30013&version=3.3.3.3&fname=QQ%E5%9B%BE%E7%89%8720240808211416.jpg&size=750",
    "https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/ee31a3ebcbff65f8ac34f4912243863876c357adcc66f427074119fe36f878691aca0ecb515f29d0cebbc196b94bbe67?pictype=scale&from=30013&version=3.3.3.3&fname=1722659793527.jpg&size=750",
    "https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/15b2e7561d18cbee0a071545bbb1314000693b2e90eb83cdc02bd3b97a046911576c8bdab461cf73dfef6da1a57f26c1?pictype=scale&from=30013&version=3.3.3.3&fname=1722660004702.jpg&size=750",
    "https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/c7b691f47255ae4e38e502dd3d1939846f5368044c8def9d3a8f4427b7c831d1ee092305f892155027f264bc404f27c1?pictype=scale&from=30013&version=3.3.3.3&fname=1722659889209.jpg&size=750"
]



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
    background-image: url(https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/218e0fb9ffff54547f62b13153c99abc4f36ee95ab29697d12dc78bfe38faa88f5e740f957cbef9707363b7ed826d05f?pictype=scale&from=30013&version=3.3.3.3&fname=bg5.jpg&size=750);
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

:deep(.img:nth-child(3) .el-image) {
    transform: rotateY(-35deg);

}

:deep(.img:nth-child(4) .el-image) {
    transform: rotateY(-35deg);

}

:deep(.img .el-image) {
    transform: rotateY(35deg);
    position: relative;
    width: 70%;
    height: 90%;
    border: 10px #fff solid;
    box-shadow: 10px 20px 28px #0c48888c;
    object-fit: cover;
}

:deep(.img .el-image:hover) {
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
    font-size: 1.3em;
    margin: 3% 0;
    /* background-color: #f1f1f1; */
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
        height: 12em;
    }

    .botText {
        width: 100%;
        height: 100%;

    }

}
</style>
