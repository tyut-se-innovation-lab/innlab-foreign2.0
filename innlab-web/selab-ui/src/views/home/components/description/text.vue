<template>
    <div class="outer">
        <div class="background" ref="backgroundRef"><span class="headText">建立初衷</span></div>
        <h2 class="acShow">
            In winter, it is covered with snow and snow. When you climb to Jinding, you can see far and wide, and the
            scenery is very magnificent; West overlooking Aiai snow peak, Gongga Mountain and Wawu mountain, the
            mountains
            connect the sky; Looking south at the top of Ten Thousand Buddhas, the clouds are rolling and the momentum
            is
            magnificent; A panoramic view of the Pingchuan River in the north is as beautiful as a shop, with a
            panoramic
            view of the Dadu River and Qingyi River
        </h2>
    </div>
</template>

<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue';

const backgroundRef = ref<HTMLElement | null>(null);

const handleScroll = () => {
    const scrollY = window.scrollY;

    if (scrollY !== 0 && backgroundRef.value) {
        backgroundRef.value.style.backgroundPosition = `calc(50% + ${scrollY}px) calc(50% + ${scrollY}px)`;
    } else if (backgroundRef.value) {
        backgroundRef.value.style.backgroundPosition = '';
    }
};

onMounted(() => {
    document.addEventListener('scroll', handleScroll);
});

onUnmounted(() => {
    document.removeEventListener('scroll', handleScroll);
});
</script>

<style scoped>
.acShow {
    opacity: 0;
}

.acShow {
    transition: all 2.8s;
}


.outer {
    width: 100%;
    height: 100%;
    padding: 130px 120px;
    /* height: 200vh; */
    overflow-x: hidden;
    display: flex;
    justify-content: center;
    flex-direction: column;
    align-items: center;
}

.background {
    background-image: linear-gradient(to bottom right, #bce9fd, #99c0f9);
    background-size: cover;
    background-position: 50% 50%;
    /* height: 200vh; */
    font: 600 3rem '';
    line-height: 30vh;
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    position: relative;
    text-align: center;
    overflow: hidden;

}

.background::before {
    content: '';
    background-size: cover;
    background-image: inherit;
    background-position: 5% 5%;
    position: absolute;
    top: 0;
    left: 0;
    bottom: 0;
    right: 0;
    z-index: -99;
}

.headText {
    letter-spacing: 3px;
}

h2 {

    letter-spacing: 2px;
    /* width: 1200px; */
    color: #fff;
    padding: 30px;
    background-color: rgba(0, 0, 0, .3);
}
</style>