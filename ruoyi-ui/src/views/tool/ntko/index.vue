<template>
  <div>
    <el-button type="primary" style="width: 100%" @click="open()">打开office插件</el-button>

    <ntko v-if="ntko" :flag="ntko" @close="close()"></ntko>
  </div>
</template>

 

<script>
import Ntko from "@/views/tool/ntko/ntko";

export default {
  components: { Ntko },

  data() {
    return {
      ntko: false
    };
  },

  computed: {},

  methods: {
    open() {
      // 定义ntkoBrowser

      // let ntkoBrowser = require("./ntkobackground.min.js").ntkoBrowser;

      let ntkoed = ntkoBrowser.ExtensionInstalled(); //alert(window.location.origin);

      // 打开控件 这块在路由中配置对应的组件，如果部署tomcat，需要在Context中指定该路由的项目是该vue项目

      if (!ntkoed) {
        ntkoBrowser.openWindow(window.location.origin + "/ntko");
      } else {
        window.open("http://localhost:8080/ntko");
      }

      // 注册回调方法

      window.ntkoCloseEvent = function() {
        alert("跨浏览器插件应用程序窗口已关闭!");
      };
    },

    close() {
      this.ntko = false;
    }
  }
};
</script>
