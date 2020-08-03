<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="文书名称" prop="filename">
        <el-input
          v-model="queryParams.filename"
          placeholder="请输入文书名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上传人" prop="username">
        <el-input
          v-model="queryParams.username"
          placeholder="上传人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['sjwflowbusiness:fileupload:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table
      v-loading="loading"
      :data="fileuploadList"
      @selection-change="handleSelectionChange"
      :height="tableHeight"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" :min-width="300" />
      <el-table-column label="名称" align="center" prop="filename" :min-width="200" />
      <el-table-column label="后缀" align="center" prop="fileextend" />
      <el-table-column label="上传人" align="center" prop="username" />
      <el-table-column label="签章状态" align="center" prop="applystamp" />
      <el-table-column label="文书编号" align="center" prop="code" />
      <el-table-column
        label="basepath"
        align="center"
        prop="basepath"
        :min-width="200"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="instanceid"
        align="center"
        prop="instanceid"
        :show-overflow-tooltip="true"
        :min-width="300"
      />
      <el-table-column label="拟文id" align="center" prop="niwenid" :min-width="300" />
      <el-table-column label="是否ofd" align="center" prop="isofd" :formatter="isofdFormat" />
      <el-table-column label="打印份数" align="center" prop="printnum" />
      <el-table-column label="是否删除" align="center" prop="isdeleted">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.isdeleted" @change="updateform(scope.row)"></el-switch>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
        fixed="right"
        :min-width="200"
      >
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-view" @click="fileView(scope.row)">查看</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-download"
            @click="fileDownload(scope.row.id)"
          >下载</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-folder-opened"
            @click="open=true;curfileid=scope.row.id"
            v-show="scope.row.hashistory==1"
          >历史记录</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改附件管理对话框 -->
    <el-dialog title="历史版本" :visible.sync="open" width="70%" append-to-body>
      <fileuploadtask :curfileid="curfileid"></fileuploadtask>
    </el-dialog>

    <el-dialog title="预览" :visible.sync="picmodal" width="70%" append-to-body>
      <img :src="picUrl" alt />
    </el-dialog>

    <viewer v-show="false" ref="viewer">
      <img :src="imgsrc" />
    </viewer>
  </div>
</template>

<script>
import {
  listFileupload,
  getFileupload,
  delFileupload,
  addFileupload,
  updateFileupload,
  exportFileupload,
  getFileDownloadMsg
} from "@/api/sjwflowbusiness/fileupload";
import fileuploadtask from "@/views/sjwflowbusiness/fileuploadtask/index";
import { openNtkoWindow } from "@/api/monitor/viewfile";
export default {
  name: "Fileupload",
  components: { fileuploadtask },
  props: ["curclueid", "isdialog"],
  data() {
    return {
      imgsrc: "@/assets/logo/logo.png",
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 附件管理表格数据
      fileuploadList: [],
      // 弹出层标题
      title: "",
      // 图片弹出层
      picmodal: false,
      // 是否显示弹出层
      open: false,
      // 图片地址
      picUrl: "",
      // 步骤阶段字典
      isofdOptions: [],
      //附件id
      curfileid: undefined,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 30,
        filename: undefined,
        username: undefined,
        clueid: this.curclueid
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        fileextend: [
          {
            required: true,
            message: "是否事项报告附件不能为空",
            trigger: "blur"
          }
        ],
        history: [
          {
            required: true,
            message: "是否事项报告附件不能为空",
            trigger: "blur"
          }
        ],
        isdeleted: [
          {
            required: true,
            message: "是否事项报告附件不能为空",
            trigger: "blur"
          }
        ],
        clueid: [
          {
            required: true,
            message: "是否事项报告附件不能为空",
            trigger: "blur"
          }
        ]
      },
      tableHeight:
        this.isdialog == true
          ? "600"
          : this.$store.getters.clientHeight - 200 + "px"
    };
  },
  created() {
    this.getList();
    this.getDicts("sjwflow_yes_no_num").then(response => {
      this.isofdOptions = response.data;
    });
  },
  watch: {
    curclueid(val) {
      this.queryParams.clueid = val;
      this.getList();
    }
  },
  methods: {
    /** 查询附件管理列表 */
    getList() {
      this.loading = true;
      listFileupload(this.queryParams).then(response => {
        this.fileuploadList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 是否事项报告附件字典翻译
    isofdFormat(row, column) {
      return this.selectDictLabel(this.isofdOptions, row.isofd);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        filename: undefined,
        basepath: undefined,
        savepath: undefined,
        fileextend: undefined,
        editor: undefined,
        addtime: undefined,
        history: "0",
        deptname: undefined,
        isdeleted: "0",
        dictid: undefined,
        clueid: undefined,
        userid: undefined,
        category: undefined,
        pdfid: undefined,
        stepid: undefined,
        filesize: undefined,
        filedata: undefined,
        stepname: undefined,
        username: undefined,
        state: undefined,
        applystamp: undefined,
        addstamp: undefined,
        ismeasure: undefined,
        instanceid: undefined,
        process: undefined,
        flowid: undefined,
        groupid: undefined,
        niwenid: undefined,
        code: undefined,
        hashistory: undefined,
        sourcetype: undefined,
        issync: undefined,
        wenshuid: undefined,
        stepstate: undefined,
        filepathid: undefined,
        docabbreviation: undefined,
        printnum: undefined,
        isofd: undefined,
        isoldspb: "0",
        isthing: "0"
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm("是否确认导出所有附件管理数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function() {
          return exportFileupload(queryParams);
        })
        .then(response => {
          this.download(response.msg);
        })
        .catch(function() {});
    },
    /** 更新form */
    updateform(data) {
      updateFileupload(data).then(res => {
        if (res.code == "200") {
          this.msgSuccess("修改成功");
        }
      });
    },
    fileDownload(id) {
      getFileDownloadMsg(id).then(res => {
        this.downloadbypath(res.data.filename, res.data.filepath);
      });
    },
    fileView(data) {
      getFileDownloadMsg(data.id).then(res => {
        const url = this.getdownloadbypath(
          res.data.filename,
          res.data.filepath
        );
        if (data.fileextend == ".doc" || data.fileextend == ".docx") {
          openNtkoWindow(url);
        } else if (data.fileextend == ".pdf") {
          this.openPdf(url);
        } else {
          this.imgsrc = url;
          this.$refs.viewer.$viewer.show();
        }
      });
    },
    openPdf(url) {
      const { href } = this.$router.resolve({
        path: "/pdf",
        query: {
          src: url
        }
      });
      window.open(href, "_blank");
    }
  }
};
</script>