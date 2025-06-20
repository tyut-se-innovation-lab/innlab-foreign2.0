<template>
  <div class="p-30">
    <div class="m-30">试着将鼠标移到卡片四个角上吧！</div>
    <div class="relative w-p-100 h-p-100">
      <CustomMoveCard  :idName = "'move' + 11" :width="width" :height="height" :top="50" :left="50" @changeSize="changeSize">
        <CustomNormalCard
          title="组织数量"
          :value="2345"
          width="100%"
          height="100%"
        >
         <IconOrganization :size="60"></IconOrganization>
        </CustomNormalCard>
      </CustomMoveCard>
      <CustomMoveCard  :idName = "'move' + 22" :width="width" :height="height" :top="50" :right="50" @changeSize="changeSize">
        <CustomNormalCard
          title="其他项目"
          :value="2345"
          width="100%"
          height="100%"
        >
         <IconDate :size="60" color="#727CB6"></IconDate>
        </CustomNormalCard>
      </CustomMoveCard>
    </div>
  </div>
</template>

<script setup>
import CustomMoveCard from "@/components/CustomMoveCard/index.vue";
import CustomNormalCard from "@/components/CustomCard/NormalCard.vue"
import IconOrganization from "@/components/icons/IconOrganization.vue";
import IconDate from "@/components/icons/IconDate.vue";
import { reactive, ref, shallowRef } from "vue";
const width = ref('250px')
const height = ref('120px')


</script>

<style scoped>

</style>