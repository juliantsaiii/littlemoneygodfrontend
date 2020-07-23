<template>
  <div class="dashboard-editor-container">
    <panel-group />
    <el-card class="chart-wrapper">
      <div slot="header" class="clearfix">
        <span>地市运维量</span>
      </div>
      <bar-chart />
    </el-card>

    <el-row :gutter="32">
      <el-col :xs="24" :sm="24" :lg="10">
        <el-card class="chart-wrapper">
          <div slot="header" class="clearfix">
            <span>服务类型</span>
          </div>
          <pie-chart />
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="14">
        <el-card class="chart-wrapper">
          <div slot="header" class="clearfix">
            <span>月份统计</span>
          </div>
          <line-chart />
        </el-card>
      </el-col>
    </el-row>

    <!-- <el-row :gutter="32">
      <el-col :xs="24" :sm="24" :lg="10">
        <div class="chart-wrapper">
          <pie-chart />
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="14">
        <div class="chart-wrapper">
          <line-chart />
        </div>
      </el-col>
    </el-row>-->
  </div>
</template>

<script>
import PanelGroup from "./dashboard/PanelGroup";
import LineChart from "./dashboard/LineChart";
import RaddarChart from "./dashboard/RaddarChart";
import PieChart from "./dashboard/PieChart";
import BarChart from "./dashboard/BarChart";

export default {
  name: "Index",
  components: {
    PanelGroup,
    LineChart,
    RaddarChart,
    PieChart,
    BarChart
  },
  data() {
    return {};
  },
  methods: {}
};
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 20px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .chart-wrapper {
    background: #fff;
    margin-bottom: 20px;
  }
}

@media (max-width: 1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
