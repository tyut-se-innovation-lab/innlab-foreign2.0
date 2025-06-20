<template>
    <div class="card-container flex-center" :style="{width:width,height:height,background:background}">
      <div class="icon">
        <slot></slot>
    </div>
      <div class="flex-center flex-col justif-between align-center ml-30 h-p-80">
        <div :style="{color:titleColor,fontSize:titleFontSize+'px'}">{{ title }}</div>
        <div class="text-bold" :style="{color:valueColor,fontSize:valueFontSize+'px'}">{{ value }}</div>
      </div>
    </div>
</template>

<script setup>
const prop = defineProps({
    width: {
        Type: String,
        default: '250px'
    },
    height: {
        Type: String,
        default: '120px'
    },
    background: {
        Type: String,
        default: '#fff'
    },
    title: {
       Type: String,
       default: '这是标题'
    },
    value: {
       Type: String | Number,
       default: 12345
    },
    titleColor: {
        Type: String,
        default: '#4E7D96'
    },
    titleFontSize: {
        Type: Number,
        default: 16
    },
    valueColor: {
        Type: String,
        default: '#000'
    },
    valueFontSize: {
        Type:Number,
        default: 20
    }

})

</script>

<style lang="scss" scoped>
.card-container {
    box-sizing: border-box;
    border-radius: 10px;
    box-shadow: 0px 16px 25px  rgba(78, 125, 150, 0.1);
    padding:2%;
}

</style>