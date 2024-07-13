<template>


    <div class="footer" id="6">
        <div class="footercenter">
            <p>©2016 京ICP备13032113 京公网安备11010802013311</p>
            <div class="footerList">
                <a href="javascript:void(0)" class="say">意见反馈</a><span>|</span>
                <a href="aboutOur.html#history">发展历程</a><span>|</span>
                <a href="aboutOur.html#addour">加入我们</a><span>|</span>
                <a href="userprotocol.html">用户协议</a>
            </div>
        </div>
    </div>


</template>
<script setup lang="ts">
import { ref, reactive } from "vue"




</script>
<style scoped>
.footer {
    width: 100%;
    min-width: 1140px;
    height: 50px;
    /* border-top: solid 1px #dedede; */
    line-height: 50px;
    color: white;
    /* background: #4d7190; */
    background-color: #024170;
    z-index: 100;
}

.footercenter {
    width: 1140px;
    margin: 0 auto
}

.footercenter p {
    float: left;
    font-size: 12px;

}

.footerList {
    float: right;
    font-size: 12px
}

.footerList a {
    padding: 0 12px 0 0;

}

.footerList span {

    margin-right: 8px
}
</style>
