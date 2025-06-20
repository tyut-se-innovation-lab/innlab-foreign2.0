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
    color: {
        Type: Array,
        default: () => ['#5470c6', '#91cc75', '#fac858', '#ee6666', '#73c0de', '#3ba272', '#fc8452', '#9a60b4', '#ea7ccc']
    }
  });
  
  onMounted(() => {
    const myChart = echarts.init(document.getElementById(porp.idName));
  
  const  option = {
    backgroundColor: '#fff',
    color:porp.color,
    tooltip: {
        show: false,
        trigger: 'item',
        formatter: "{b}: {c} ({d}%)"
    },
    legend: {
        orient: 'horizontal',
        bottom: '0%',
        itemWidth:10,
        itemHeight:10
    },
    series: [{
        type: 'pie',
        center:['50%','40%'],
        selectedMode: 'single',
        radius: ['0%', '68%'],
        label: {
                position: 'outer',
                formatter: '{d}%',

                textStyle: {
                    color: '#000',
                    fontWeight: 'bold',
                    fontSize: 14
                },
                alignTo:'labelLine'
        },
        labelLine: {
            show:true,
            smooth:true
        },
        data: porp.data
    }]
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