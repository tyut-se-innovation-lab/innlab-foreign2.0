<template>
    <div class="introduction" id="3">
        <div class="main">
            <Intention></Intention>
            <Part id="4"></Part>
            <History id="9"></History>
            <Activity id="5"></Activity>
            <People id="8"></People>
        </div>



    </div>
</template>

<script setup lang="ts">
import { ref, reactive } from "vue"
import Intention from "@/views/home/components/intention/index.vue"
import Part from "@/views/home/components/part/index.vue"
import History from "@/views/home/components/history/index.vue"
import Activity from "@/views/home/components/activity/index.vue"
import People from "@/views/home/components/people/index.vue"


</script>

<style scoped>
.introduction {
    width: 100%;
    /* height: 2800px; */
    /* margin-top: 60px; */

    background: url(@/assets/rainbow.jpg) center center no-repeat;
    background-size: cover;
}

header {
    width: 100%;
    height: 40px;
    display: flex;
    justify-content: center;

    .title {}
}

.main {
    width: 100%;
    display: flex;
    flex-direction: column;

    /* margin: 20px 0; */
}
</style>
