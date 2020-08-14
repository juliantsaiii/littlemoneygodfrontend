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

    <el-dialog title="更新内容" :visible.sync="showUpdateMsgDialog" width="600px">
      <p v-html="msg" id="updateform"></p>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="updateUserReadStatus">我知道了</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import PanelGroup from "./dashboard/PanelGroup";
import LineChart from "./dashboard/LineChart";
import RaddarChart from "./dashboard/RaddarChart";
import PieChart from "./dashboard/PieChart";
import BarChart from "./dashboard/BarChart";
import { listLatestNotice } from "@/api/system/notice";
import { UpdateReadStatus } from "@/api/system/user";

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
    return {
      showUpdateMsgDialog: true,
      msg: ""
    };
  },
  methods: {
    getLatesdUpdateMsg() {
      listLatestNotice().then(response => {
        this.msg = response.msg;
      });
    },
    updateUserReadStatus() {
      UpdateReadStatus();
      this.showUpdateMsgDialog = false;
    }
  },
  created() {
    this.showUpdateMsgDialog =
      this.$store.getters.getReadUpdateMsg == "0" ? true : false;
    if (this.showUpdateMsgDialog) {
      this.getLatesdUpdateMsg();
    }
  }
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
<style lang="scss">
#updateform >>> li {
  margin-top: 10px;
}
#updateform >>> p {
  font-size: 16px;
  font-weight: bold;
}
</style>