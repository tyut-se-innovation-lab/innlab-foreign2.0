<template>
  <div class="p-30">
    <div class="flex justify-around">
        <CustomNormalCard
          v-for="(item, index) in cardObject"
          :key="item.id || index"
          :title="item.title"
          :value="item.value"
        >
          <component
            :is="item.icon"
            :color="item.iconColor"
            :size="item.iconSize"
            :item="item"
          ></component>
        </CustomNormalCard>
    </div>
  </div>
</template>

<script setup>
import CustomNormalCard from "@/components/CustomCard/NormalCard.vue";
import IconOrganization from "@/components/icons/IconOrganization.vue";
import IconDate from "@/components/icons/IconDate.vue";
import { reactive, ref, shallowRef } from "vue";

const cardObject = ref([
  {
    id: 1,
    title: "组织数量",
    value: 2345,
    icon: IconOrganization,
    iconSize: 60,
  },
  {
    id: 2,
    title: "主要项目",
    value: 4567,
    icon: IconDate,
    iconSize: 60,
    iconColor: "#727CB6",
  },
  {
    id: 3,
    title: "其他项目",
    value: 2331,
    icon: IconOrganization,
    iconSize: 60,
    iconColor: "#FF606B",
  },
  {
    id: 4,
    title: "网关数量",
    value: 8766,
    icon: IconDate,
    iconSize: 60,
    iconColor: "#FF844B",
  },
]);

</script>

<style scoped>

</style>