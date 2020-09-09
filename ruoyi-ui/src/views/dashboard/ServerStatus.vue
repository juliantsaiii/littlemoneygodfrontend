<template>
  <div class="serverstatus" v-loading="loading">
    <el-row :gutter="20">
      <el-col :span="3" v-for="(item,i) in servers" :key="i" style="margin-bottom:20px">
        <el-popover
          placement="bottom"
          width="400"
          trigger="hover"
          :open-delay="500"
          @show="getdetail(item.monitor)"
        >
          <div>
            <h3>{{item.name}}</h3>
            <span :style="{color:item.status?'#1afa29':'red'}">{{item.status?"正常":"超时"}}</span>
            <hr />
            <template v-if="!!item.monitor">
              <dl>
                <dt style>服务器ip：</dt>
                <dd>{{detail.sys.computerIp}}</dd>
                <dt style>操作系统：</dt>
                <dd>{{detail.sys.osName}}</dd>
                <dt style>系统架构：</dt>
                <dd>{{detail.sys.osArch}}</dd>
              </dl>
              <hr />
              <h4>Cpu</h4>
              <dl>
                <dt style>核心数：</dt>
                <dd>{{detail.cpu.cpuNum}}</dd>
                <dt style>用户使用率：</dt>
                <dd>{{detail.cpu.sys}}%</dd>
                <dt style>系统使用率：</dt>
                <dd>{{detail.cpu.used}}%</dd>
                <dt style>空闲率：</dt>
                <dd>{{detail.cpu.free}}%</dd>
              </dl>
              <hr />
              <h4>内存</h4>
              <dl>
                <dt style>总内存：</dt>
                <dd>{{detail.mem.total}}G</dd>
                <dt style>以用内存：</dt>
                <dd>{{detail.mem.used}}G</dd>
                <dt style>剩余内存：</dt>
                <dd>{{detail.mem.free}}G</dd>
                <dt style>使用率：</dt>
                <dd>{{detail.mem.usage}}G</dd>
              </dl>
            </template>
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
import axios from "axios";

export default {
  name: "serverstatus",
  data() {
    return {
      servers: [],
      loading: true,
      detail: {
        cpu: {
          cpuNum: "",
          sys: "",
          used: "",
          free: ""
        },
        mem: {
          total: "",
          used: "",
          free: "",
          usage: ""
        },
        sys: {
          computerName: "",
          computerIp: "",
          userDir: "",
          osName: "",
          osArch: ""
        }
      }
    };
  },
  methods: {
    getServerMsgs() {
      getConnectionStatus().then(respose => {
        this.servers = respose.data;
        this.loading = false;
      });
    },
    getdetail(url) {
      if (url != "") {
        axios.get(url + "/servermonitor/monitor/server").then(respose => {
          this.detail = respose.data.data;
        });
      }
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
  width: 150px;
  text-align: right;
  float: left;
  font-size: 13px;
}
</style>