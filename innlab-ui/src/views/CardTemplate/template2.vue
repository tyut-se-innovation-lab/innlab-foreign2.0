<template>
  <div class="w-p-100 h-p-40 flex">
    <div class="w-p-30 h-p-100 mr-10">
      <CustomChartBar
        title="报警排行(前五)"
        :data="data"
        :total="5"
      ></CustomChartBar>
    </div>
    <div class="w-p-30 h-p-100">
      <CustomChartPie
        title="报警统计(饼图)"
        :data="data.slice(0, 4)"
      ></CustomChartPie>
    </div>
  </div>
  <div class="w-p-100 h-p-60 flex">
    <div class="w-p-40 h-p-100 mr-10">
      <CustomChartBar
        title="报警排行(前十)"
        :data="data"
        :total="10"
      ></CustomChartBar>
    </div>
    <div class="w-p-40 h-p-100">
      <CustomChartLine
        title="报警统计(折线图)"
        :configData="lineChartConfigData"
      ></CustomChartLine>
    </div>
  </div>
</template>

<script setup>
import CustomChartBar from "@/components/CustomChartBar/index.vue";
import CustomChartPie from "@/components/CustomChartPie/index.vue";
import CustomChartLine from "@/components/CustomChartLine/index.vue";
const data = [
  { name: "参考位置1", value: 345 },
  { name: "参考位置2", value: 223 },
  { name: "参考位置3", value: 124 },
  { name: "参考位置4", value: 564 },
  { name: "参考位置5", value: 678 },
  { name: "参考位置6", value: 456 },
  { name: "参考位置7", value: 234 },
  { name: "参考位置8", value: 124 },
  { name: "参考位置9", value: 123 },
  { name: "参考位置10", value: 456 },
];
const lineData = [
  {
    id: 11,
    name: "参考位置1",
    color: '244, 229, 35',
    data: [
      [0, 1735201866687],
      [11, 1735201871286],
      [27, 1735201876165],
      [38, 1735201881206],
      [37, 1735201886160],
      [21, 1735201891155],
      [22, 1735201896150],
      [22, 1735201897060],
      [20, 1735201901155],
      [16, 1735201906150],
      [18, 1735201911301],
      [15, 1735201916170],
      [15, 1735201926195],
      [14, 1735201927525],
      [12, 1735201931165],
      [16, 1735201936144],
      [15, 1735201941155],
      [16, 1735201946165],
      [13, 1735201951164],
      [11, 1735201956165],
      [10, 1735201957935],
      [9, 1735201961200],
      [7, 1735201966159],
      [7, 1735201976165],
      [6, 1735201981222],
      [5, 1735201986155],
      [4, 1735201988375],
      [2, 1735201992154],
      [0, 1735201997155],
    ],
  },
  {
    id: 22,
    name: "参考位置2",
    color: '244, 116, 34',
    data: [
      [0, 1735201866687],
      [41, 1735201871286],
      [27, 1735201876165],
      [68, 1735201881206],
      [37, 1735201886160],
      [11, 1735201891155],
      [22, 1735201896150],
      [82, 1735201897060],
      [20, 1735201901155],
      [36, 1735201906150],
      [18, 1735201911301],
      [15, 1735201916170],
      [65, 1735201926195],
      [14, 1735201927525],
      [12, 1735201931165],
      [16, 1735201936144],
      [15, 1735201941155],
      [96, 1735201946165],
      [13, 1735201951164],
      [11, 1735201956165],
      [40, 1735201957935],
      [9, 1735201961200],
      [7, 1735201966159],
      [7, 1735201976165],
      [6, 1735201981222],
      [50, 1735201986155],
      [4, 1735201988375],
      [2, 1735201992154],
      [0, 1735201997155],
    ],
  },
];
lineData.forEach(val => {
    val.data = val.data.map((pair) => [pair[1], pair[0]]);
})

const lineChartConfigData = {
  yAxisName: "数据(单位)",
  series: lineData
};
</script>

<style scoped>
</style>