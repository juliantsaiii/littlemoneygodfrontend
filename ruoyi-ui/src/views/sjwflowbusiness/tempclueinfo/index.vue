<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="线索ID" prop="id">
        <el-input
          v-model="queryParams.id"
          placeholder="请输入clueid"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="单位" prop="unitname">
        <el-input
          v-model="queryParams.unitname"
          placeholder="请输入单位"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="线索编号" prop="cluecode">
        <el-input
          v-model="queryParams.cluecode"
          placeholder="请输入线索编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="姓名" prop="personname">
        <el-input
          v-model="queryParams.personname"
          placeholder="请输入被反映人姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="职务" prop="personunit">
        <el-input
          v-model="queryParams.personunit"
          placeholder="请输入工作职务"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="线索来源" prop="cluesource">
        <el-input
          v-model="queryParams.cluesource"
          placeholder="请输入线索来源"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="信访编号" prop="clueno">
        <el-input
          v-model="queryParams.clueno"
          placeholder="请输入信访编号"
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
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          v-hasPermi="['sjwflowbusiness:tempclueinfo:remove']"
          @click="deleteBatch"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-s-promotion"
          size="mini"
          :disabled="multiple"
          v-hasPermi="['sjwflowbusiness:tempclueinfo:remove']"
          @click="dialogTreeVisible=true"
        >转交</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['sjwflowbusiness:tempclueinfo:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table
      v-loading="loading"
      :data="tempclueinfoList"
      @selection-change="handleSelectionChange"
      :max-height="tableHeight"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
        label="ID"
        align="center"
        prop="id"
        :show-overflow-tooltip="true"
        :min-width="300"
      />
      <el-table-column label="姓名" align="center" prop="personname" :min-width="150" />
      <el-table-column
        label="单位"
        align="center"
        prop="unitname"
        :show-overflow-tooltip="true"
        :min-width="150"
      />
      <el-table-column
        label="职务"
        align="center"
        prop="personunit"
        :show-overflow-tooltip="true"
        :min-width="150"
      />
      <el-table-column
        label="职级"
        align="center"
        prop="personlevel"
        :show-overflow-tooltip="true"
        :min-width="150"
      />
      <el-table-column
        label="编号"
        align="center"
        prop="cluecode"
        :show-overflow-tooltip="true"
        :min-width="200"
      >
        <template slot-scope="scope">
          <span>{{scope.row.cluecode}}/{{scope.row.clueno}}</span>
        </template>
      </el-table-column>
      <el-table-column label="填报日期" align="center" prop="reportdate" />
      <el-table-column label="线索来源" align="center" prop="cluesource" :show-overflow-tooltip="true" />
      <el-table-column
        label="承办部门"
        align="center"
        prop="basehandledept"
        :show-overflow-tooltip="true"
      />
      <el-table-column label="状态" align="center" prop="handlestate" />
      <el-table-column label="是否重复件" align="center" prop="isrepeated" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <span>{{scope.row.isrepeated?scope.row.isrepeated:'否'}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="是否添加多人件"
        align="center"
        prop="addpeoplemainid"
        :show-overflow-tooltip="true"
      />

      <el-table-column label="是否删除" align="center">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.isdeleted" @change="updateform(scope.row)"></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="是否措施线索">
        <template slot-scope="scope">
          <span>{{scope.row.ismeasureclue?'是':'否'}}</span>
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
          <el-button
            size="mini"
            type="text"
            icon="el-icon-files"
            @click="fileuploaddialog = true;openviewdialog(scope.row.id)"
          >附件</el-button>
          <router-link
            :to="{path:'/sjwflowbusiness/workflowtask/',query: {id: scope.row.id}}"
            class="link-type"
          >
            <el-button size="mini" type="text" icon="el-icon-edit">步骤</el-button>
          </router-link>

          <el-button
            size="mini"
            type="text"
            icon="el-icon-location-information"
            @click="infoviewdialog = true;openviewdialog(scope.row.id)"
          >跳转</el-button>
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

    <el-dialog title="流程" :visible.sync="infoviewdialog" width="80%">
      <template>
        <displayview :curclueid="currentclueid"></displayview>
      </template>
    </el-dialog>

    <el-dialog title="附件" :visible.sync="fileuploaddialog" width="80%">
      <template>
        <fileupload :curclueid="currentclueid" :isdialog="true"></fileupload>
      </template>
    </el-dialog>

    <el-dialog title="选择转交人" :visible.sync="dialogTreeVisible">
      <dept-select-tree @selectterm="getReceiverMsg" :type="'user'"></dept-select-tree>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogTreeVisible = false">取 消</el-button>
        <el-button type="primary" @click="changeReceiver" :disabled="receiveSubmit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listTempclueinfo,
  getTempclueinfo,
  delTempclueinfo,
  addTempclueinfo,
  updateTempclueinfo,
  exportTempclueinfo,
  fakedelclueinfo
} from "@/api/sjwflowbusiness/tempclueinfo";
import deptSelectTree from "@/views/sjwflowsys/dept/components/deptSelectTree";
import displayview from "@/views/tool/go/displayview";
import fileupload from "@/views/sjwflowbusiness/fileupload/index";
export default {
  components: { displayview, fileupload, deptSelectTree },
  name: "Tempclueinfo",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      //选中人名
      personnames: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 线索操作表格数据
      tempclueinfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 30,
        id: undefined,
        unitname: undefined,
        cluecode: undefined,
        personname: undefined,
        personunit: undefined,
        cluesource: undefined,
        clueno: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      tableHeight: this.$store.getters.clientHeight - 250 + "px",
      //流程图框
      infoviewdialog: false,
      //附件框
      fileuploaddialog: false,
      //选中clueid
      currentclueid: undefined,
      //选人树
      dialogTreeVisible: false,
      //更换人确认按钮
      receiveSubmit: true,
      //更换接收人ID
      receiveID: undefined,
      //更换接收人name
      receiveName: undefined
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询线索操作列表 */
    getList() {
      this.loading = true;
      listTempclueinfo(this.queryParams).then(response => {
        this.tempclueinfoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
        createuserid: undefined,
        createdeptid: undefined,
        userid: undefined,
        deptid: undefined,
        attachmentid: undefined,
        handledept: undefined,
        handledeptid: undefined,
        handlestaff: undefined,
        affix: undefined,
        state: undefined,
        unitcode: undefined,
        unitname: undefined,
        reportdate: undefined,
        fourformno: undefined,
        cluecode: undefined,
        personcode: undefined,
        isunit: undefined,
        personid: undefined,
        personname: undefined,
        personidcard: undefined,
        birthdate: undefined,
        nation: undefined,
        politic: undefined,
        politicdetail: undefined,
        partytime: undefined,
        personlevel: undefined,
        personlevel2: undefined,
        secondlevel: undefined,
        personduty: undefined,
        personunit: undefined,
        idcard: undefined,
        isdeputy: undefined,
        iscppcc: undefined,
        isddb: undefined,
        issupervision: undefined,
        iscivilservant: undefined,
        cluesource: undefined,
        illegalbehavior: undefined,
        lawbehaviour: undefined,
        handleunit: undefined,
        accepttime: undefined,
        handle1time: undefined,
        handle1censustime: undefined,
        handletype1first: undefined,
        handletype1second: undefined,
        handle2time: undefined,
        handle2censustime: undefined,
        handletype2first: undefined,
        handletype2second: undefined,
        handle3time: undefined,
        handle3censustime: undefined,
        handletype3first: undefined,
        handletype3second: undefined,
        handle4time: undefined,
        handle4censusttime: undefined,
        handletype4first: undefined,
        handletype4second: undefined,
        handle5time: undefined,
        handle5census: undefined,
        handletype5first: undefined,
        handletype5second: undefined,
        handle6time: undefined,
        handle6censustime: undefined,
        handletype6first: undefined,
        handletype6second: undefined,
        handle7time: undefined,
        handle7censustime: undefined,
        handletype7first: undefined,
        handletype7second: undefined,
        handle8time: undefined,
        handle8censustime: undefined,
        handletype8first: undefined,
        handletype8second: undefined,
        orgmeasure: undefined,
        implementtime: undefined,
        ischecked: undefined,
        iscomeclean: undefined,
        retrieveeconomicloss: undefined,
        captureassetmoney: undefined,
        confiscatemoney: undefined,
        compensatemoney: undefined,
        handinmoney: undefined,
        lossmoney: undefined,
        mainclue: undefined,
        handlereport: undefined,
        handleremark: undefined,
        isrepeated: undefined,
        reportstaff: undefined,
        checkstaff: undefined,
        isdeleted: "0",
        timestamp: undefined,
        reflectname: undefined,
        reflectunit: undefined,
        reflectpost: undefined,
        reflectphone: undefined,
        reflectidcard: undefined,
        personsex: undefined,
        status: "0",
        clueno: undefined,
        personeducation: undefined,
        personauthority: undefined,
        jobtime: undefined,
        isparty: undefined,
        issupervision2: undefined,
        issupervision3: undefined,
        lawbehaviourother: undefined,
        ischarge: undefined,
        address: undefined,
        nativeplace: undefined,
        casecode: undefined,
        personcontact: undefined,
        receivestaut: undefined,
        subcluesource: undefined,
        handlestate: undefined,
        handletype: undefined,
        handleresultlev1: undefined,
        handleresultlev2: undefined,
        handletime: undefined,
        isduban: undefined,
        ismeasureclue: "0",
        basehandledept: undefined,
        basehandledeptid: undefined,
        sourcetype: undefined,
        statisticaltime: undefined,
        storagestate: undefined,
        handletypechange: undefined,
        mainclueid: undefined,
        createcompanyid: undefined,
        iseliminateevil: undefined,
        isaccountability: undefined,
        urgent: undefined,
        handledecisiontime: undefined,
        problemsummary: undefined,
        isoldclue: undefined,
        iseasyclue: undefined,
        clueproperty: undefined,
        samelevel: undefined,
        samelevelopinion: undefined,
        samelevelopinion2: undefined,
        handlemethodtype: undefined,
        paizhuarea: undefined,
        isup: undefined,
        isupstatus: "0",
        updept: undefined,
        addpeoplemainid: undefined,
        samelevelopinionaffix: undefined,
        issurrender: undefined,
        isjwcadre: undefined,
        agsdistributetime: undefined,
        firsttransfertime: undefined,
        firstdistributetime: undefined,
        yuqistatus: "0",
        isexport: undefined,
        handlerequire: undefined,
        cadreauthority: undefined
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
      this.personnames = selection.map(item => item.personname);
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm("是否确认导出所有线索操作数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function() {
          return exportTempclueinfo(queryParams);
        })
        .then(response => {
          this.download(response.msg);
        })
        .catch(function() {});
    },
    /** 更新form */
    updateform(data) {
      updateTempclueinfo(data).then(res => {
        if (res.code == "200") {
          this.msgSuccess("修改成功");
        }
      });
    },
    /** 打开流程图框 */
    openviewdialog(id) {
      this.currentclueid = id;
    },
    /** 批量删除 */
    deleteBatch() {
      this.$confirm(
        "是否确认删除被反映人为<span class='el-tag el-tag--success'>" +
          this.personnames +
          "</span>的线索"
      ).then(() => {
        fakedelclueinfo(this.ids).then(response => {
          if (response.code == "200") {
            this.msgSuccess("删除成功");
            this.getList();
            this.ids = "";
            this.personnames = "";
          }
        });
      });
    },
    //批量换承办人
    getReceiverMsg(node) {
      this.receiveID = node.id;
      this.receiveName = node.label;
      receiveSubmit = false;
    },
    changeReceiver() {
      this.$confirm(
        "是否确认将被反映人为<span class='el-tag el-tag--success'>" +
          this.personnames +
          '</span>的线索转交给<span class="el-tag">' +
          this.receiveName +
          "</span>",
        { dangerouslyUseHTMLString: true }
      ).then(() => {});
    }
  }
};
</script>
<style scoped>
.el-tag {
  background-color: #ecf5ff;
  display: inline-block;
  height: 32px;
  padding: 0 10px;
  line-height: 30px;
  font-size: 12px;
  color: #409eff;
  border: 1px solid #d9ecff;
  border-radius: 4px;
  box-sizing: border-box;
  white-space: nowrap;
}

.el-tag.el-tag--success {
  background-color: #f0f9eb;
  border-color: #e1f3d8;
  color: #67c23a;
}
</style>