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
    default: "",
  },
  configData: {
    Type: Object,
    default: () => {
      return {
        xAxisData: [],
        series: [],
      };
    },
  },
});

onMounted(() => {
  const myChart = echarts.init(document.getElementById(porp.idName));
  let series = [];
  porp.configData.series.forEach((val) => {
    series.push({
      name: val.name,
      type: "line",
      smooth: true,
      symbol: "circle",
      symbolSize: 5,
      showSymbol: false,
      lineStyle: {
        normal: {
          width: 1,
        },
      },
      areaStyle: {
        color: new echarts.graphic.LinearGradient(
          0,
          0,
          0,
          1,
          [
            {
              offset: 0,
              color: `rgba(${val.color}, 0.8)`,
            },
            {
              offset: 0.8,
              color: `rgba(${val.color}, 0.3)`,
            },
          ],
          false
        ),
        shadowColor: "rgba(0, 0, 0, 0.1)",
        shadowBlur: 10,
      },
      itemStyle: {
        color: `rgb(${val.color})`,
        borderColor: `rgba(${val.color}, 0.27)`,
        borderWidth: 12,
      },
      data: val.data,
    });
  });
  const option = {
    backgroundColor: "transparent",

    tooltip: {
      trigger: "axis",
      axisPointer: {
        lineStyle: {
          color: "#57617B",
        },
      },
    },

    grid: {
      top: "10%",
      left: "10px",
      right: "10px",
      bottom: 0,
      containLabel: true,
    },
    xAxis: [
      {
        type: "time",
        boundaryGap: false,
        axisLabel: {
          show: true,
          textStyle: {
            color: "#d2d4da",
            fontSize: 12,
          },
        },
        axisTick: {
          show: false,
        },
        axisLine: {
          lineStyle: {
            color: "#04BBFF",
          },
        },
      },
    ],
    yAxis: [
      {
        type: "value",
        name: porp.configData.yAxisName,
        nameTextStyle: {
          color: "#d2d4da",
        },
        axisTick: {
          show: false,
        },
        axisLine: {
          show: false,
          lineStyle: {
            color: "#57617B",
          },
        },
        axisLabel: {
          textStyle: {
            color: "#d2d4da",
            fontSize: 9,
          },
        },
        splitLine: {
          lineStyle: {
            type: "dashed",
            color: ["rgba(4,187,255,0.2)"],
          },
        },
      },
    ],
    series: series,
  };
  // 绘制图表
  myChart.setOption(option);
});
</script>
  
  <style scoped>
.chart-content {
  box-sizing: border-box;
  border-radius: 10px;
  box-shadow: 0px 16px 25px rgba(78, 125, 150, 0.1);
  padding: 2%;
}
</style>