<template>
  <div class="outer">
    <div class="descTitle">
      <h2>组织介绍</h2>
      <span>INTRODUCTION</span>
    </div>
    <div class="content">

      <main>
        <div class="img">
          <img class="imgit" data-aos="fade-right" data-aos-duration="800" src="public/img/homeImg/descimg.png" alt="">

        </div>
        <div class="text" data-aos="fade-left" data-aos-duration="800" data-aos-delay="500">
          <span>&nbsp;&nbsp;&nbsp;&nbsp;软件学院创新实验室位于软件学院行勉楼实验室一层，是隶属于软件学院的学生创新实践基地，宗旨在于丰富学生课外知识、提升学生创新实践能力。创新实验室内部有：软件开发、网络安全、人工智能与虚拟
            现实四个学习方向小组。我们为意图提升专业技术能力的学生提
            供一个良好的环境与平台；也是一个提供给学生交流技术的平台。
            在这里，你可以找到志同道合的伙伴，可以尽情提升和发挥自己
            的专业 能力，可以把学到的新奇的知识应用于实际中
          </span>
          <Readmore link="/blog" actid="18" text="查看详情" color="white" style="margin-left: 30%;"></Readmore>
        </div>

      </main>

    </div>


  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from "vue"
import Readmore from '@/components/readmore/index.vue'
import AOS from 'aos'
import 'aos/dist/aos.css'

onMounted(() => {
  AOS.init();

});

</script>

<style scoped>
.outer {
  /* padding: 0 120px; */
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;

  position: relative;
}



.descTitle {
  /* border-left: 3px solid #729bba; */
  padding: 0 4%;
  margin: 6%;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.descTitle h2 {
  font-size: 42px;
  color: #333;
  font-weight: bold;
  margin-top: 0;
}

.descTitle span {
  font-size: 16px;
  color: #333;
  margin-top: 10px;
}

.content {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;

}



.title {
  width: 45%;
  margin-bottom: 6%;


}

.title img {
  width: 86%;
  height: auto;
}


main {
  width: 100%;
  height: 60%;
  position: relative;
  padding: 6% 2%;
  display: flex;
  align-items: center;
  justify-content: flex-end;
  background-color: #02406f;
  background: linear-gradient(to right, #99b7ce, #002d72);


}

main .text {
  width: 50%;
  font-size: 20px;
  font-weight: 500;
  color: white;

}

.img {
  width: 42%;
  height: 120%;
  position: absolute;
  top: -10%;
  left: 4%;
}

.imgit {
  width: 100%;
  height: 100%;
}




.text {
  display: flex;
  flex-direction: column;
  font-size: 20px;
  /* font-family: 'STFangsong'; */
  color: white;
  /* text-shadow: 2px 2px 2px #ffffff; */

}

/* 移动端适配 */
@media (max-width: 1248px) {
  .descTitle h2 {
    font-size: 33px;
  }

  .content {
    padding: 2em;

  }



  main {
    width: 100%;
    justify-content: center;
    flex-direction: column;

  }

  .text {
    width: 100% !important;
    padding: 3% !important;
    font-size: .8em !important;
    margin-top: 20px;
  }

  .bigtitle {
    width: 100%;
    font-size: 1.5em;
    /* background: linear-gradient(to right, #5e88a9, white, #5e88a9); */
  }

  .bigtitle span:nth-child(2) {
    font-size: .7em;
  }

  .img {
    width: 100% !important;
    position: relative !important;
    top: 0% !important;
    left: 0% !important;
  }

  .imgit {
    width: 100%;
    height: 100%;
  }
}
</style>
