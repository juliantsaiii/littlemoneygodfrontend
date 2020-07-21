<template>
  <el-row :gutter="40" class="panel-group">
    <el-col :span="8"></el-col>
    <el-col style="width: 34.5%">
      <el-card class="box-card">
        <div class="box-item">
          <li
            :class="{'number-item': !isNaN(item), 'mark-item': isNaN(item) }"
            v-for="(item,index) in orderNum"
            :key="index"
          >
            <span v-if="!isNaN(item)">
              <i ref="numberItem">0123456789</i>
            </span>
            <span class="comma" v-else>{{item}}</span>
          </li>
        </div>
        <i class="el-icon-document-copy" v-clipboard:copy="code" v-clipboard:success="onCopy"></i>
      </el-card>
    </el-col>
  </el-row>
</template>
<script>
export default {
  data() {
    return {
      orderNum: ["0", "0", "0", "0", "0", "0", "0", "0", "0"], // 默认订单总数
      code: "535322652"
    };
  },
  mounted() {
    this.$nextTick(() => {
      this.orderNum = this.code.split(""); // 这里输入数字即可调用
      this.increaseNumber();
    });
  },
  methods: {
    // 定时增长数字
    increaseNumber() {
      let self = this;
      this.timer = setInterval(() => {
        self.newNumber = self.newNumber + this.getRandomNumber(1, 100);
        self.setNumberTransform();
      }, 3000);
    },
    // 设置文字滚动
    setNumberTransform() {
      const numberItems = this.$refs.numberItem; // 拿到数字的ref，计算元素数量
      const numberArr = this.orderNum.filter(item => !isNaN(item));
      // 结合CSS 对数字字符进行滚动,显示订单数量
      for (let index = 0; index < numberItems.length; index++) {
        const elem = numberItems[index];
        elem.style.transform = `translate(-50%, -${numberArr[index] * 10}%)`;
      }
    },
    getRandomNumber(min, max) {
      return Math.floor(Math.random() * (max - min + 1) + min);
    },
    onCopy() {
      this.msgSuccess("已复制到剪切板");
    }
  }
};
</script>
<style scoped lang='scss'>
.el-icon-document-copy:hover {
  color: #5cb6ff;
  cursor: pointer;
}

.panel-group {
  margin-bottom: 32px;
}

/*订单总量滚动数字设置*/
.box-item {
  display: inline-block;
  position: relative;
  font-size: 54px;
  line-height: 41px;
  text-align: center;
  list-style: none;
  color: #2d7cff;
  writing-mode: vertical-lr;
  text-orientation: upright;
  /*文字禁止编辑*/
  -moz-user-select: none; /*火狐*/
  -webkit-user-select: none; /*webkit浏览器*/
  -ms-user-select: none; /*IE10*/
  -khtml-user-select: none; /*早期浏览器*/
  user-select: none;
  /* overflow: hidden; */
}
/* 默认逗号设置 */
.mark-item {
  width: 10px;
  height: 100px;
  margin-right: 5px;
  line-height: 10px;
  font-size: 48px;
  position: relative;
  & > span {
    position: absolute;
    width: 100%;
    bottom: 0;
    writing-mode: vertical-rl;
    text-orientation: upright;
  }
}
/*滚动数字设置*/
.number-item {
  width: 41px;
  height: 75px;
  background: #ccc;
  list-style: none;
  margin-right: 5px;
  background: rgba(250, 250, 250, 1);
  border-radius: 4px;
  border: 1px solid rgba(221, 221, 221, 1);
  & > span {
    position: relative;
    display: inline-block;
    margin-right: 10px;
    width: 100%;
    height: 100%;
    writing-mode: vertical-rl;
    text-orientation: upright;
    overflow: hidden;
    & > i {
      font-style: normal;
      position: absolute;
      top: 11px;
      left: 50%;
      transform: translate(-50%, 0);
      transition: transform 1s ease-in-out;
      letter-spacing: 10px;
    }
  }
}
.number-item:last-child {
  margin-right: 0;
}
</style>