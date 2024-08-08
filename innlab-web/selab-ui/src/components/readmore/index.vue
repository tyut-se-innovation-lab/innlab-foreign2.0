<template>
    <div class="outer" @click="linkTo">

        <button class="cta">
            <span class="hover-underline-animation" :style="{ color: props.color || 'black' }"> {{ props.text ?
                props.text : 'Read More' }}
            </span>

            <svg id="arrow-horizontal" xmlns="http://www.w3.org/2000/svg" width="30" height="10" viewBox="0 0 46 16">
                <path id="Path_10" data-name="Path 10"
                    d="M8,0,6.545,1.455l5.506,5.506H-30V9.039H12.052L6.545,14.545,8,16l8-8Z" transform="translate(30)"
                    :style="{ fill: props.color || 'black' }">
                </path>
            </svg>
        </button>

    </div>
</template>
<script setup lang="ts">
import { ref, reactive, defineProps, onMounted } from "vue"
import router from '@/router';
import { encrypt } from '@/utils/crypto';

const props = defineProps<{
    link: string,
    actid: number,
    period: string,
    part: string,
    people: Record<string, any>,
    text: string,
    color: string
}>();

const linkTo = () => {
    if (props.people) {
        const encryptedPeople = encrypt(JSON.stringify(props.people));//参数加密
        router.push({
            path: props.link,
            query: {
                actid: props.actid,
                period: props.period,
                part: props.part,
                people: encryptedPeople
            }
        });
        sessionStorage.setItem('isDetail', 'true');
    } else {
        router.push({
            path: props.link,
            query: {
                actid: props.actid
            }
        });
    }
}

const getarrowStyle = () => {
    return 'fill: white;'
}

onMounted(() => {
    console.log('props', props);

})

</script>
<style scoped>
.cta {
    border: none;
    background: none;
    cursor: pointer;
    margin-top: 30px;
}

.cta span {
    padding-bottom: 7px;
    letter-spacing: 4px;
    font-size: 14px;
    padding-right: 15px;
    text-transform: uppercase;
}

.cta svg {
    transform: translateX(-8px);
    transition: all 0.3s ease;
}

.cta:hover svg {
    transform: translateX(0);
}

.cta:active svg {
    transform: scale(0.9);
}

.hover-underline-animation {
    position: relative;
    color: #767676;
    padding-bottom: 20px;
}

.hover-underline-animation:after {
    content: "";
    position: absolute;
    width: 100%;
    transform: scaleX(0);
    height: 2px;
    bottom: 0;
    left: 0;
    background-color: #797979;
    transform-origin: bottom right;
    transition: transform 0.25s ease-out;
}

.cta:hover .hover-underline-animation:after {
    transform: scaleX(1);
    transform-origin: bottom left;
}
</style>
