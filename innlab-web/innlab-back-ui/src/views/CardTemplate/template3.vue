<template>
  <div class="p-30">
    <div class="m-30">试着拖拽它们交换卡片顺序吧！</div>
    <div class="flex justify-around">
      <TransitionGroup name="list">
        <CustomNormalCard
          v-for="(item, index) in cardObject"
          :key="item.id || index"
          :title="item.title"
          :value="item.value"
          :draggable="true"
          @dragstart="dragstart(index)"
          @dragenter = "dragenter($event, index)"
          @dragover="dragover($event)"
        >
          <component
            :is="item.icon"
            :color="item.iconColor"
            :size="item.iconSize"
            :item="item"
          ></component>
        </CustomNormalCard>
      </TransitionGroup>
    </div>
  </div>
</template>

<script setup>
import CustomNormalCard from "@/components/CustomCard/NormalCard.vue";
import IconOrganization from "@/components/icons/IconOrganization.vue";
import IconDate from "@/components/icons/IconDate.vue";
import { ref } from "vue";

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

const dragIndex = ref();
const targetIndex = ref();
const dragstart = (index) => {
  dragIndex.value = index;
};

const dragover = (e) => {
    e.preventDefault()

}

let timeout = null;
const dragenter = (e, index) => {
  e.preventDefault();
  
  if (timeout) {
    clearTimeout(timeout);
  }

  // 设置简单防抖
  timeout = setTimeout(() => {
    targetIndex.value = index;
    let obj = cardObject.value[dragIndex.value];
    if (targetIndex.value != dragIndex.value) {
        console.log(targetIndex.value,dragIndex.value);
      cardObject.value.splice(dragIndex.value, 1);
      cardObject.value.splice(targetIndex.value, 0, obj);
      dragIndex.value = index;
    }
  },200);
};
</script>

<style scoped>
.list-move, /* 对移动中的元素应用的过渡 */
.list-enter-active,
.list-leave-active {
  transition: all 0.5s ease;
}

/* 确保将离开的元素从布局流中删除
  以便能够正确地计算移动的动画。 */
.list-leave-active {
  position: absolute;
}
</style>