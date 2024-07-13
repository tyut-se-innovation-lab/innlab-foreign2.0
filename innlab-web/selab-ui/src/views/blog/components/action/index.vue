<template>

    <li :class="{ liked: isLiked }" @mouseover="overAction" @mouseout="outAction" @click="clickAction">
        <img :src="imageSrc" alt="">
        <span>{{ count }}</span>
    </li>

</template>
<script setup lang="ts">
import { ref, reactive, defineProps } from "vue"

const props = defineProps({
    initialLiked: {
        type: Boolean,
        default: false
    },
    initialCount: {
        type: Number,
        default: 0
    },
    unclickSrc: {
        type: String
    },
    clickedSrc: {
        type: String
    }
});

const isLiked = ref(props.initialLiked);
const count = ref(props.initialCount);
const imageSrc = ref(props.unclickSrc);

const overAction = () => {
    imageSrc.value = isLiked.value ? props.unclickSrc : props.clickedSrc;
};

const outAction = () => {
    imageSrc.value = isLiked.value ? props.clickedSrc : props.unclickSrc;
};

const clickAction = () => {
    if (isLiked.value) {
        imageSrc.value = props.unclickSrc;
        count.value--;
    } else {
        imageSrc.value = props.clickedSrc;
        count.value++;
    }
    isLiked.value = !isLiked.value;
};


</script>
<style scoped>
li {
    width: 80px;
    margin-right: 80px;
    display: flex;
    align-items: center;
    color: #bfbfbf;
    cursor: pointer;

    img {

        width: 30px;
        height: 30px;
        margin-right: 10px;
    }
}

li:hover {
    color: #4d7190;
}

.liked {
    color: #4d7190 !important;
}
</style>
