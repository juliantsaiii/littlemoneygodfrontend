<template>
  <div class="page-container">
    <div class="main">
      <pdf
        :src="src"
        :page="currentPage"
        @progress="loadedRatio = $event"
        @loaded="loadPdfHandler"
        @num-pages="pageCount=$event"
        @page-loaded="currentPage=$event"
        ref="wrapper"
        class="pdf"
      ></pdf>
    </div>

    <div class="footers">
      <el-button-group>
        <el-button type="primary" size="mini" icon="el-icon-zoom-in" @click="scaleD">放大</el-button>
        <el-button type="primary" size="mini" @click="scaleX">
          缩小
          <i class="el-icon-arrow-right el-icon-zoom-out"></i>
        </el-button>
        <el-button
          type="primary"
          size="mini"
          icon="el-icon-arrow-left"
          @click="changePdfPage(0)"
        >上一页</el-button>
        <el-button type="primary" size="mini" @click="changePdfPage(1)">
          下一页
          <i class="el-icon-arrow-right el-icon--right"></i>
        </el-button>
      </el-button-group>
      <p>当前第{{ currentPage }}页/共{{ pageCount }}页</p>
    </div>
  </div>
</template>
 
<script>
import pdf from "vue-pdf";
export default {
  data() {
    return {
      currentPage: 1, // 当前页码
      pageCount: 0, // 总页码
      scale: 100,
      idx: -1,
      loadedRatio: 0,
      src: ""
    };
  },
  created() {
    this.src = this.$route.query.src;
  },
  components: {
    pdf
  },
  methods: {
    // 改变PDF页码,val传过来区分上一页下一页的值,0上一页,1下一页
    changePdfPage(val) {
      if (val === 0 && this.currentPage > 1) {
        this.currentPage--;
      }
      if (val === 1 && this.currentPage < this.pageCount) {
        this.currentPage++;
      }
      console.log(this.currentPage);
      console.log(this.pageCount);
    },
    goBack() {
      this.$router.go(-1);
    },
    // pdf加载时
    loadPdfHandler(e) {
      this.currentPage = 1; // 加载的时候先加载第一页
    },
    //放大
    scaleD() {
      this.scale += 5;
      this.$refs.wrapper.$el.style.width = parseInt(this.scale) + "%";
    },

    //缩小
    scaleX() {
      if (this.scale == 100) {
        return;
      }
      this.scale += -5;
      this.$refs.wrapper.$el.style.width = parseInt(this.scale) + "%";
    }
  }
};
</script>
 
<style scoped>
.main {
  overflow: hidden;
}
.footers {
  position: fixed;
  top: 0;
  width: 100%;
  display: flex;
  z-index: 100;
  color: #333;
  border-top: 1px solid #f0f0f0;
  line-height: 10px;
  height: 30px;
}
.li {
  text-align: center;
  flex: 1;
  padding: 5px;
  cursor: pointer;
}
.ul {
  list-style: none;
}
.more-p {
  border-right: 1px solid #f0f0f0;
  margin-right: 40px;
  cursor: pointer;
}
.small-p {
  margin-right: 40px;
  cursor: pointer;
}
.up-p {
  margin-right: 40px;
  cursor: pointer;
}
.down-p {
  border-radius: 0 none;
  cursor: pointer;
  margin-right: 50px;
}
</style>

