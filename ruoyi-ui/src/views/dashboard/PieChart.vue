<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import echarts from "echarts";
require("echarts/theme/macarons"); // echarts theme
import resize from "./mixins/resize";
import { listServiceType } from "@/api/sjwflowbusiness/problemlist";
export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: "chart"
    },
    width: {
      type: String,
      default: "100%"
    },
    height: {
      type: String,
      default: "300px"
    }
  },
  data() {
    return {
      chart: null,
      servicetype: [],
      servicetypedata: []
    };
  },
  mounted() {
    this.$nextTick(() => {
      this.getDicts("operation_type").then(response => {
        for (let i = 0; i < response.data.length; i++) {
          this.servicetype.push(response.data[i].dictLabel);
        }
        listServiceType().then(response => {
          this.servicetypedata = response.data;
          this.initChart();
        });
      });
    });
  },
  beforeDestroy() {
    if (!this.chart) {
      return;
    }
    this.chart.dispose();
    this.chart = null;
  },
  methods: {
    initChart() {
      this.chart = echarts.init(this.$el, "macarons");

      this.chart.setOption({
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
          left: "center",
          bottom: "10",
          data: this.servicetype
        },
        series: [
          {
            name: "服务类型",
            type: "pie",
            roseType: "radius",
            radius: [15, 95],
            center: ["70%", "45%"],
            data: this.servicetypedata,
            animationEasing: "cubicInOut",
            animationDuration: 2600
          }
        ]
      });
    },
    getServiceType() {
      listServiceType().then(response => {
        this.servicetypedata = response.data;
      });
    }
  }
};
</script>
