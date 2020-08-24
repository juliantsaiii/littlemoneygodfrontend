<template>
  <div class="serverstatus" v-loading="loading">
    <el-row :gutter="20">
      <el-col :span="4" v-for="(item,i) in servers" :key="i" style="margin-bottom:20px">
        <el-popover placement="bottom" width="400" trigger="hover" open-delay="500">
          <div>
            {{item.name}}
            <hr />
            <dl>
              <dt style>地址：</dt>
              <dd>{{item.url}}</dd>
              <dt style>状态：</dt>
              <dd :style="{color:item.status?'#1afa29':'red'}">{{item.status?"正常":"超时"}}</dd>
            </dl>
          </div>
          <el-card
            body-style="padding:0 10px; display: flex;"
            :style="{'background-color':item.status?'':'rgb(253, 226, 226)'}"
            slot="reference"
          >
            <div class="iconbox">
              <svg-icon
                icon-class="connsucsess"
                class-name="connicon"
                :style="{color:item.status?'#1afa29':'red'}"
              />
            </div>
            <div class="msgbox">
              <ul>
                <li>
                  <strong>{{item.name}}</strong>
                </li>
              </ul>
            </div>
            <div class="circle"></div>
          </el-card>
        </el-popover>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { getConnectionStatus } from "@/api/tool/serverconnection";

export default {
  name: "serverstatus",
  data() {
    return {
      servers: [],
      loading: true
    };
  },
  methods: {
    getServerMsgs() {
      getConnectionStatus().then(respose => {
        this.servers = respose.data;
        this.loading = false;
      });
    }
  },
  created() {
    this.getServerMsgs();
  }
};
</script>

<style lang="less" scoped>
.connicon {
  width: 40px !important;
  height: 40px !important;
}
.serverstatus {
  height: 190px;
  div {
    height: 50px;
  }
  ul {
    list-style-type: none;
    padding: 0px;
  }
}
.iconbox {
  align-items: center;
  display: flex;
}
.msgbox {
  margin-left: 20px;
  font-size: 15px;
  align-items: center;
  display: flex;
}
</style>
<style >
.el-popover dl {
  display: inline-block;
  width: 100%;
  position: relative;
  min-height: 16px;
  margin: 3px 0;
  table-layout: fixed;
  font-size: 13px;
}
.el-popover dt {
  width: 100px;
  text-align: right;
  float: left;
  font-size: 13px;
}
</style>