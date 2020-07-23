<template>
  <el-row :gutter="40" class="panel-group">
    <el-col :span="8">
      <el-card class="box-card">
        <h3>刷新登录缓存</h3>
        <el-input v-model="refreshuserid" placeholder="请输入用户ID">
          <el-button slot="append" icon="el-icon-refresh" @click="refreshRedis"></el-button>
        </el-input>
      </el-card>
    </el-col>
    <el-col :span="8">
      <el-card class="box-card">
        <h3>刷新线索查询</h3>
        <el-input v-model="clueid" placeholder="请输入线索ID">
          <el-button slot="append" icon="el-icon-refresh" @click="refreshCore"></el-button>
        </el-input>
      </el-card>
    </el-col>
    <el-col :span="8">
      <el-card class="box-card" style="
  align-items: center;
  display: flex;">
        <div class="box-item">
          <li
            :class="{'number-item':item, 'mark-item': item}"
            v-for="(item,index) in orderNum"
            :key="index"
          >
            <span v-if="item">
              <i ref="numberItem">0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ</i>
            </span>
            <span class="comma" v-else>{{item}}</span>
          </li>
        </div>
        <!-- <i class="el-icon-document-copy" v-clipboard:copy="code" v-clipboard:success="onCopy"></i> -->
        <i class="el-icon-refresh" @click="refreshAndCopy"></i>
      </el-card>
    </el-col>
  </el-row>
</template>
<script>
import axios from "axios";
import { getDyCode } from "@/api/login";
import { refreshLoginRedis } from "@/api/sjwflowbusiness/cleanredis";
export default {
  data() {
    return {
      orderNum: ["0", "0", "0", "0", "0", "0", "0", "0"], // 默认订单总数
      orderSort: [
        "a",
        "b",
        "c",
        "d",
        "e",
        "f",
        "g",
        "h",
        "i",
        "j",
        "k",
        "l",
        "m",
        "n",
        "o",
        "p",
        "q",
        "r",
        "s",
        "t",
        "u",
        "v",
        "w",
        "x",
        "y",
        "z",
        "0",
        "1",
        "2",
        "3",
        "4",
        "5",
        "6",
        "7",
        "8",
        "9",
        "A",
        "B",
        "C",
        "D",
        "E",
        "F",
        "G",
        "H",
        "I",
        "J",
        "K",
        "L",
        "M",
        "N",
        "O",
        "P",
        "Q",
        "R",
        "S",
        "T",
        "U",
        "V",
        "W",
        "X",
        "Y",
        "Z"
      ],
      code: "",
      timer: undefined,
      refreshuserid: undefined,
      clueid: undefined
    };
  },
  mounted() {
    this.$nextTick(() => {
      // setInterval(() => {
      //   this.getInfo();
      // }, 1000000);
      // this.getInfo();
    });
  },
  watch: {
    code(val) {
      this.increaseNumber();
    }
  },
  methods: {
    // 定时增长数字
    increaseNumber() {
      this.orderNum = this.code.split(""); // 这里输入数字即可调用
      let self = this;
      this.timer = setInterval(() => {
        self.setNumberTransform();
      }, 1000);
    },
    // 设置文字滚动
    setNumberTransform() {
      const numberItems = this.$refs.numberItem; // 拿到数字的ref，计算元素数量

      // 结合CSS 对数字字符进行滚动,显示订单数量
      for (let index = 0; index < numberItems.length; index++) {
        const elem = numberItems[index];
        const numberArr = this.findeindex(this.orderNum[index]);
        elem.style.transform = `translate(-50%, -${numberArr * 1.61290322}%)`;
      }
    },
    getRandomNumber(min, max) {
      return Math.floor(Math.random() * (max - min + 1) + min);
    },
    onCopy() {
      this.msgSuccess("已复制到剪切板");
    },
    /** 获取字符下标 */
    findeindex(num) {
      return this.orderSort.findIndex(v => {
        return v == num;
      });
    },
    getInfo() {
      getDyCode().then(res => {
        this.code = res.msg;
      });
    },
    refreshAndCopy() {
      getDyCode().then(res => {
        this.code = res.msg;
        this.$copyText(this.code).then(
          e => {
            this.msgSuccess("已复制到剪切板");
          },
          e => {
            this.msgError("复制失败");
          }
        );
      });
    },
    /** 刷新用户缓存 */
    refreshRedis() {
      if (!!this.refreshuserid) {
        refreshLoginRedis(this.refreshuserid).then(response => {
          this.msgSuccess("刷新完成");
        });
      }
    },
    /** 刷新core表 */
    refreshCore() {
      if (!!this.clueid) {
        axios.get("/refresh?clueid=" + this.clueid).then(() => {
          this.msgSuccess("刷新完成");
        });
      }
    }
  },
  beforeDestroy() {
    clearInterval(this.timer);
  }
};
</script>
<style scoped lang='scss'>
.el-icon-refresh:hover {
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
      top: 13px;
      left: 50%;
      transform: translate(-50%, 0);
      transition: transform 1s ease-in-out;
      letter-spacing: 15px;
    }
  }
}
.number-item:last-child {
  margin-right: 0;
}
.box-card {
  height: 130px;
}
</style>