<template>
  <div class="sealposition">
    <div style="height: 10%">
      <button @click="init()">初始化</button>
      <button @click="tiqu()">提取</button>
    </div>
		<div id="showofd" style="width: 100%; height: 90%">
			<embed id='SedOI' TYPE='application/SursenOFD-plugin'  WIDTH='0' HEIGHT='0'></embed>
			<object ref='TrivialObject' TYPE='trivial/very' WIDTH=100% HEIGHT=100% :path="path"></object>
		</div>
  </div>
</template> 

<script>
export default {
  data() {
    return {
      path: "C:/Users/Anshare/Desktop/调整章位置/111.ofd"
    };
  },
  props: ["wenshuid"],
  // created() {
  //   this.init();
  // },
  methods: {
    init: function() {
      var TrivialObject = this.$refs.TrivialObject;
      TrivialObject.openFile(this.path, false);
    },
    tiqu: function() {
      // var currentHandle = SedOI.S_OpenFile(path, "");
      // var sealInfo = SedOI.S_GetSealPosName();
      // SedOI.S_CloseFileByHandle(currentHandle);
      // var sinfo = "";
      // var ss = sealInfo.split(";");
      // var result = [];
      // for (var i = 0; i < ss.length; i++) {
      //   var sealmsg = {
      //     name: s[0],
      //     page: parseInt(s[1]) + 1,
      //     x: parseInt(s[2]),
      //     y: parseInt(s[3])
      //   };
      //   result.push(sealmsg);

      //   if (i != ss.length - 1) {
      //     sinfo += "\r\n";
      //   }
      // }

      let test = [
        {
          ename: "测试章",
          pagenum: "1",
          positionx: "100",
          positiony: "100",
          wenshuid: this.wenshuid
        }
      ];
      this.$emit("getPosition", test);
    }
  }
};
</script>

<style>
</style>