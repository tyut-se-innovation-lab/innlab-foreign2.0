<template>
    <div class="outer">
        <div class="card">
            <div class="card__img">
                <svg xmlns="http://www.w3.org/2000/svg" width="100%">
                    <rect fill="#ffffff" width="540" height="450"></rect>
                    <defs>
                        <linearGradient id="gradient" x1="0%" y1="0%" x2="100%" y2="0%">
                            <stop offset="0%" style="stop-color: #244c7a"></stop>
                            <stop offset="100%" style="stop-color: #ffffff"></stop>
                        </linearGradient>
                    </defs>
                    <rect x="0" y="0" fill="url(#gradient)" width="100%" height="70%"></rect>
                </svg>
            </div>
            <div class="card__avatar">
                <img v-if="avatarSrc" :src="avatarSrc" style="width: 90%; height:90%; border-radius: 50%;" alt="">
            </div>
            <div class="card__title">{{ props.name }}</div>
            <div class="card__subtitle">
                <span>{{ props.period }}</span>
                <span style="margin-left:8px">{{ props.post }}</span>
            </div>
            <div class="card__subtitle">部门：{{ props.department }}</div>
            <div class="card__subtitle">{{ props.say }}</div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, watchEffect } from "vue";
import { parseLanzouLink } from '@/utils/getFileByBackend';

const props = defineProps<{
    name: string,
    avatar: string,
    period: string,
    department: string,
    post: string,
    contribute: string,
    say: string,
}>();

const avatarSrc = ref('');

const parseLink = async (file: string) => {
    const result = await parseLanzouLink(file);
    console.log('pe: ', result);
    return result;
};

watchEffect(async () => {
    avatarSrc.value = await parseLink(props.avatar);
});
</script>

<style scoped>
.outer {
    width: 100%;
    height: 100%;
}

.card {
    --main-color: #000;
    --submain-color: #78858F;
    --bg-color: #fff;
    font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
    position: relative;
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    border-radius: 20px;
    border: 2px solid #4d8cb8;
    background: var(--bg-color);
}

.card__img {
    height: 192px;
    width: 100%;
}

.card__img svg {
    height: 100%;
    border-radius: 20px 20px 0 0;
}

.card__avatar {
    position: absolute;
    width: 114px;
    height: 114px;
    background: var(--bg-color);
    border-radius: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    top: calc(30% - 57px);
}

.card__avatar img {
    width: 100px;
    height: 100px;
}

.card__title {
    margin-top: 10px;
    font-weight: 500;
    font-size: 18px;
    color: var(--main-color);
}

.card__subtitle {
    margin-top: 10px;
    font-weight: 400;
    font-size: 15px;
    color: var(--submain-color);
}

.card__btn {
    margin-top: 15px;
    width: 76px;
    height: 31px;
    border: 2px solid var(--main-color);
    border-radius: 4px;
    font-weight: 700;
    font-size: 11px;
    color: var(--main-color);
    background: var(--bg-color);
    text-transform: uppercase;
    transition: all 0.3s;
}

.card__btn-solid {
    background: var(--main-color);
    color: var(--bg-color);
}

.card__btn:hover {
    background: var(--main-color);
    color: var(--bg-color);
}

.card__btn-solid:hover {
    background: var(--bg-color);
    color: var(--main-color);
}
</style>
