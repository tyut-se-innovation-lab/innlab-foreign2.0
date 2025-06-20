<template>
  <div class="w-p-100 h-p-100 chart-content flex flex-col">
    <div class="m-10">{{ title }}</div>
    <div class="flex1" :id="idName"></div>
  </div>
</template>
  
  <script setup>
import * as echarts from "echarts";
import { onMounted } from "vue";
const porp = defineProps({
  idName: {
    Type: String,
    default: () => "chart" + Math.floor(Math.random() * 100000000),
  },
  title: {
    Type: String,
    default: ''
  },
  data: {
    Type: Array,
    default: () => []
  },
  total: {
    Type: Number,
    default: 10
  },
  inverse: {
    Type: Boolean,
    default: true
  }
});

onMounted(() => {
  const myChart = echarts.init(document.getElementById(porp.idName));


  // 排序
  const data = porp.data.sort(function (a, b) {
    return b.value - a.value;
  }).slice(0, porp.total);
 
  const attackSourcesColor = [
    new echarts.graphic.LinearGradient(0, 1, 1, 1, [
      { offset: 0, color: "#EB3B5A" },
      { offset: 1, color: "#FE9C5A" },
    ]),
    new echarts.graphic.LinearGradient(0, 1, 1, 1, [
      { offset: 0, color: "#FA8231" },
      { offset: 1, color: "#FFD14C" },
    ]),
    new echarts.graphic.LinearGradient(0, 1, 1, 1, [
      { offset: 0, color: "#F7B731" },
      { offset: 1, color: "#FFEE96" },
    ]),
    new echarts.graphic.LinearGradient(0, 1, 1, 1, [
      { offset: 0, color: "#395CFE" },
      { offset: 1, color: "#2EC7CF" },
    ]),
  ];
  const attackSourcesColor1 = [
    "#EB3B5A",
    "#FA8231",
    "#F7B731",
    "#3860FC",
    "#1089E7",
    "#F57474",
    "#56D0E3",
    "#1089E7",
    "#F57474",
    "#1089E7",
    "#F57474",
    "#F57474",
  ];
  const attaData = [];
  const attaName = [];
  const topName = [];
  data.forEach((it, index) => {
    attaData[index] = it.value;
    attaName[index] = it.value;
    topName[index] = `${it.name}`;
  });
  function attackSourcesDataFmt(sData) {
    const sss = [];
    sData.forEach(function (item, i) {
      let itemStyle = {
        color: i > 3 ? attackSourcesColor[3] : attackSourcesColor[i],
      };
      sss.push({
        value: item,
        itemStyle: itemStyle,
      });
    });
    return sss;
  }

  const option = {
    backgroundColor: "#fff",
    tooltip: {
      show: false,
    },
    grid: {
      left: "40px",
      right: "40px",
      width: "80%",
      bottom: "10px",
      top: "10px",
    },
    xAxis: {
      type: "value",

      splitLine: {
        show: false,
      },
      axisLabel: {
        show: false,
      },
      axisTick: {
        show: false,
      },
      axisLine: {
        show: false,
      },
    },
    yAxis: [
      {
        type: "category",
        inverse: porp.inverse,
        axisLine: {
          show: false,
        },
        axisTick: {
          show: false,
        },
        axisPointer: {
          label: {
            show: true,
          },
        },
        pdaaing: [5, 0, 0, 0],
        data: attaName,
        axisLabel: {
          margin: 30,
          fontSize: 10,
          align: "left",
          padding: [2, 0, 0, 0],
          color: "#fff",
          rich: {
            nt1: {
              color: "#fff",
              backgroundColor: attackSourcesColor1[0],
              width: 16,
              height: 16,
              fontSize: 10,
              align: "center",
              borderRadius: 100,
              lineHeight: 16,
            },
            nt2: {
              color: "#fff",
              backgroundColor: attackSourcesColor1[1],
              width: 16,
              height: 16,
              fontSize: 10,
              align: "center",
              borderRadius: 100,
              lineHeight: 16,
            },
            nt3: {
              color: "#fff",
              backgroundColor: attackSourcesColor1[2],
              width: 16,
              height: 16,
              fontSize: 10,
              align: "center",
              borderRadius: 100,
            },
            nt: {
              color: "#fff",
              backgroundColor: attackSourcesColor1[3],
              width: 16,
              height: 16,
              fontSize: 10,
              align: "center",
              borderRadius: 100,
            },
          },
          formatter: function (value, index) {
            index = index + 1;
            if (index - 1 < 3) {
              return ["{nt" + index + "|" + index + "}"].join("\n");
            } else {
              return ["{nt|" + index + "}"].join("\n");
            }
          },
        },
      },
      {
        type: "category",
        inverse: porp.inverse,
        offset: -10,
        position: "left",
        axisLine: { show: false },
        axisTick: { show: false },
        axisLabel: {
          color: "#333",
          align: "left",
          verticalAlign: "bottom",
          lineHeight: 26,
          fontSize: 10,
        },
        data: topName,
      },
    ],
    series: [
      {
        zlevel: 1,
        type: "bar",
        barWidth: "15px",
        animationDuration: 1500,
        data: attackSourcesDataFmt(attaData),
        align: "center",
        itemStyle: {
          barBorderRadius: 10,
        },
        label: {
          show: true,
          fontSize: 10,
          color: "#000",
          textBorderWidth: 2,
          padding: [2, 0, 0, 0],
          position: "right",
        },
      },
    ],
  };
  myChart.setOption(option);
});
</script>

<style lang="scss" scoped>
.chart-content {
  box-sizing: border-box;
  border-radius: 10px;
  box-shadow: 0px 16px 25px rgba(78, 125, 150, 0.1);
  padding: 2%;
}
</style>