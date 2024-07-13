<template>
    <div class="footer" id="6">

        <header>
            <div class="logo">
                <img src="@/assets/logo.png" alt="">
            </div>
        </header>

        <section>
            <!-- <div class="link">
                <h1 class="linkTitle">友情链接</h1>
                <div class="linkMain">
                    <span>中华人民共和国教育部</span>
                    <span>中华人民共和国科学技术部</span>
                    <span>国家自然科学基金委</span>
                    <span>国家国防科工局</span>
                    <span>武器装备信息网</span>
                </div>
            </div> -->

            <div class="contact">
                <h1 class="contactTitle">联系我们</h1>
                <div class="contactMain">
                    <span>迎西校区：中国山西省太原市万柏林区迎泽西大街79号</span>
                    <span>邮编：030024</span>
                    <span>明向校区：中国山西省晋中市榆次区大学街209号</span>
                    <span>邮编：030600</span>
                    <span>邮箱：tyutxcb@tyut.edu.cn</span>
                </div>
            </div>
        </section>

    </div>
</template>

<script setup lang="ts">
import { ref, reactive } from "vue"




</script>

<style scoped>
.footer {
    /* background-color: #4474c4; */
    background-color: #1c4277;
    display: flex;
    flex-direction: column;
    color: white;
    padding: 22px 120px;
}

header {
    width: 100%;
    border-bottom: 1px solid rgba(229, 229, 229, 0.6);
}

.logo {
    img {
        width: 160px;
    }
}


section {
    display: flex;
    margin-top: 20px;
}

.linkMain {
    display: flex;
    flex-direction: column;
}

.contactMain {
    display: flex;
    flex-direction: column;
}
</style>
