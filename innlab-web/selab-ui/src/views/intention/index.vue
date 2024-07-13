<template>
    <div class="outer">
        <div class="title">
            <h1>建立初衷</h1>
        </div>
        <div class="content">
            <div class="img">
                <img src="public/img/homeImg/banner.png" alt="">
            </div>
            <div class="text">

            </div>
        </div>
    </div>

</template>
<script setup lang="ts">
import { ref, reactive, onMounted } from "vue"
import bus from '@/eventBus';


onMounted(() => {

    setTimeout(() => {
        bus.emit('loading', false);
    }, 200);


    window.scrollTo({ top: 0, behavior: 'smooth' });




});



</script>
<style scoped>
.outer {
    background-image: url(public/img/homeImg/bg7.jpg);
    background-size: cover;
    height: 100vh;
}
</style>
