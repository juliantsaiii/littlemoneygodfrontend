<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryParams"
      :inline="true"
      label-width="100px"
    >
      <el-form-item label="接收人" prop="receivename">
        <el-input
          v-model="queryParams.receivename"
          placeholder="请输入接收人"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="instanceid" prop="instanceid">
        <el-input
          v-model="queryParams.instanceid"
          placeholder="请输入instanceid"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-s-promotion"
          size="mini"
          :disabled="multiple"
          @click="
            dialogTreeVisible = true;
            updatetype = 2;
          "
          >转交</el-button
        >
      </el-col>
    </el-row>

    <el-table
      border
      v-loading="loading"
      :data="workflowtaskList"
      @selection-change="handleSelectionChange"
      :height="tableHeight"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="是否删除" align="center">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.isdeleted"
            @change="updateform(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column
        label="id"
        align="center"
        prop="id"
        :show-overflow-tooltip="true"
        min-width="300"
      />
      <el-table-column
        label="流程名"
        align="center"
        prop="title"
        min-width="200"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="步骤名"
        align="center"
        prop="stepname"
        min-width="200"
        :show-overflow-tooltip="true"
      />
      <el-table-column label="发送人" align="center" prop="sendername" />
      <el-table-column label="接收人" align="center" prop="receivename" />
      <el-table-column
        label="接收时间"
        align="center"
        min-width="100"
        prop="receivetime"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.receivetime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="status" align="center" :min-width="100">
        <template slot-scope="scope">
          <el-select
            v-model="scope.row.status"
            placeholder
            @change="updateform(scope.row)"
          >
            <el-option
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="Number(dict.dictValue)"
            ></el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column
        label="instanceid"
        align="center"
        prop="instanceid"
        :show-overflow-tooltip="true"
        min-width="300"
      />
      <el-table-column
        label="clueid"
        align="center"
        prop="clueid"
        :show-overflow-tooltip="true"
        min-width="300"
      />
      <el-table-column label="note" align="center" prop="note" />

      <el-table-column label="步骤阶段" align="center" prop="stepstate" />
      <el-table-column
        label="databasename"
        align="center"
        prop="databasename"
        min-width="150"
      />
      <el-table-column label="infotype" align="center" prop="infotype" />

      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
        min-width="200"
        fixed="right"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-user"
            @click="openUpdateDialog(scope.row)"
            v-hasPermi="['sjwflowbusiness:workflowtask:edit']"
            >更换用户</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="openEditDialog(scope.row)"
            >编辑</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-document"
            @click="getSessionID(scope.row)"
            >表单</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <el-dialog
      title="更换接收人"
      :visible.sync="dialogTreeVisible"
      append-to-body
    >
      <dept-select-tree
        :pid="form.receiveid"
        :id="form.receiveid"
        :label="form.receivename"
        @selectterm="updatepSelectTreeValue"
        :type="'user'"
        :selectID="'receiveid'"
        :selectName="'receivename'"
      ></dept-select-tree>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogTreeVisible = false">取 消</el-button>
        <el-button
          type="primary"
          @click="updateReceiver"
          :disabled="receiveSubmit"
          >确 定</el-button
        >
      </div>
    </el-dialog>

    <el-dialog
      :title="title"
      :visible.sync="open"
      width="1000px"
      height="900px"
      :close-on-click-modal="false"
      append-to-body
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="流程名称" prop="title">
              <el-input v-model="form.title" placeholder="请输入流程名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="flowid" prop="flowid">
              <el-input v-model="form.flowid" placeholder="请输入flowid" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="步骤名称" prop="stepname">
              <el-input v-model="form.stepname" placeholder="请输入步骤名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="stepid" prop="stepid">
              <el-input v-model="form.stepid" placeholder="请输入stepid" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="步骤阶段" prop="stepstate">
              <el-input-number
                v-model="form.stepstate"
                placeholder="请输入步骤阶段"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="接收时间" prop="receivetime">
              <el-date-picker
                v-model="form.receivetime"
                type="datetime"
                placeholder="选择接收时间"
              ></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="databasename" prop="databasename">
              <el-input
                v-model="form.databasename"
                placeholder="请输入databasename"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="infotype" prop="infotype">
              <el-input v-model="form.infotype" placeholder="请输入infotype" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确定</el-button>
        <el-button @click="cancel">取消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import axios from "axios";
import {
  listWorkflowtask,
  getWorkflowtask,
  delWorkflowtask,
  addWorkflowtask,
  updateWorkflowtask,
  exportWorkflowtask,
  changeClueReceiver
} from "@/api/sjwflowbusiness/workflowtask";
import deptSelectTree from "@/views/sjwflowsys/dept/components/deptSelectTree";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import Treeselect from "@riophae/vue-treeselect";
export default {
  name: "Workflowtask",
  components: { Treeselect, deptSelectTree },
  props: ["curclueid", "isdialog", "isOpen"],
  data() {
    return {
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
      // 流程记录表格数据
      workflowtaskList: [],
      // 弹出层标题
      title: "修改task",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 30,
        clueid: this.curclueid,
        receivename: undefined,
        instanceid: undefined,
        receiveid: undefined,
        status: undefined
      },
      statusOptions: [],
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [{ required: true, message: "请输入流程名称", trigger: "blur" }],
        flowid: [{ required: true, message: "请输入流程id", trigger: "blur" }],
        stepname: [
          { required: true, message: "请输入步骤名称", trigger: "blur" }
        ],
        stepid: [{ required: true, message: "请输入步骤id", trigger: "blur" }],
        stepstate: [
          { required: true, message: "请输入步骤阶段", trigger: "blur" }
        ],
        receivetime: [
          { required: true, message: "请选择接收时间", trigger: "blur" }
        ],
        databasename: [
          { required: true, message: "请输入databasename", trigger: "blur" }
        ],
        stepstate: [
          { required: true, message: "请输入stepstate", trigger: "blur" }
        ],
        infotype: [
          { required: true, message: "请输入infotype", trigger: "blur" }
        ]
      },
      tableHeight:
        this.isdialog == true
          ? "600"
          : this.$store.getters.clientHeight - 200 + "px",
      dialogTreeVisible: false,
      //更换人确认按钮
      receiveSubmit: true,
      //更新类型
      updatetype: undefined
    };
  },
  watch: {
    curclueid(val) {
      this.queryParams.clueid = val;
    },
    isOpen(val) {
      if (val) {
        this.getList(this.queryParams);
      }
    }
  },
  created() {
    this.queryParams.clueid = this.$route.params && this.$route.query.id;
    this.queryParams.receiveid =
      this.$route.params && this.$route.query.receiveid;
    this.queryParams.status =
      this.$route.params && this.$route.query.receiveid && 1;
    this.queryParams.isdeleted =
      this.$route.params && this.$route.query.receiveid && 0;
    this.getDicts("sjwflow_task_status").then(response => {
      this.statusOptions = response.data;
    });
    this.getList(this.queryParams);
  },
  methods: {
    /** 查询流程记录列表 */
    getList() {
      this.loading = true;
      listWorkflowtask(this.queryParams).then(response => {
        this.workflowtaskList = response.rows;
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
        previd: undefined,
        prevstepid: undefined,
        flowid: undefined,
        stepid: undefined,
        stepname: undefined,
        instanceid: undefined,
        clueid: undefined,
        groupid: undefined,
        type: undefined,
        title: undefined,
        senderid: undefined,
        sendername: undefined,
        sendertime: undefined,
        receiveid: undefined,
        receivename: undefined,
        receivetime: undefined,
        touserid: undefined,
        tousername: undefined,
        opentime: undefined,
        completedtime: undefined,
        completedtime1: undefined,
        comment: undefined,
        issign: undefined,
        status: undefined,
        note: undefined,
        sort: undefined,
        subflowgroupid: undefined,
        handledeptid: undefined,
        subreceiveid: undefined,
        handlestaffid: undefined,
        handlestaffname: undefined,
        mainhandlestaffname: undefined,
        mainhandlestaffid: undefined,
        cluegroupid: undefined,
        rolltime: undefined,
        stepstate: undefined,
        databasename: undefined,
        ismodify: undefined,
        formid: undefined,
        oldreceiveid: undefined,
        oldreceivename: undefined,
        oldstepid: undefined,
        infotype: undefined,
        isdeleted: "0",
        spbid: undefined,
        isdeletd: "0"
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
      this.ids = selection.map(item => item.clueid);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm("是否确认导出所有流程记录数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function() {
          return exportWorkflowtask(queryParams);
        })
        .then(response => {
          this.download(response.msg);
        })
        .catch(function() {});
    },
    /** 更新form */
    updateform(data) {
      updateWorkflowtask(data).then(res => {
        if (res.code == "200") {
          this.msgSuccess("修改成功");
          this.getList();
        }
      });
    },
    /** 同步下拉树数据 */
    updatepSelectTreeValue(node, id, label) {
      if (node != null) {
        this.receiveSubmit = false;
        this.form[id] = node.id;
        label != undefined && [(this.form[label] = node.label)];
      } else {
        this.receiveSubmit = true;
      }
    },
    /** 打开换人框 */
    openUpdateDialog(data) {
      this.form = data;
      this.updatetype = 1;
      this.dialogTreeVisible = true;
    },
    /** 更新接收人 */
    updateReceiver() {
      if (this.updatetype == 1) {
        this.updateform(this.form);
      } else if (this.updatetype == 2) {
        this.changeReceiverBatch();
      }
      this.dialogTreeVisible = false;
    },
    /** 更换task基本信息 */
    openEditDialog(data) {
      this.form = data;
      this.open = true;
    },
    /** 提交修改 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          updateWorkflowtask(this.form).then(response => {
            if (response.code === 200) {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            }
          });
        }
      });
    },
    /** 打开表单 */
    getSessionID(task) {
      axios.defaults.withCredentials = true;
      axios
        .get(
          // `http://${process.env.VUE_APP_Product_URL}/api/exchange/getLoginSession?id=` +
          `/api/exchange/getLoginSession?id=` + task.receiveid
        )
        .then(response => {
          //获取sessionid
          const cookie = response.data;
          const readonly = task.status == 1 ? 0 : 1;
          const winopen = window.open(
            `http://${process.env.VUE_APP_Product_URL}/WorkFlow/WorkFlowRun/Index?workStatus=${task.status}&ReceiveID=${task.receiveid}&flowid=${task.flowid}&stepid=${task.stepid}&instanceid=${task.instanceid}&taskid=${task.id}&groupid=${task.groupid}5&clueid=${task.clueid}&isreadonly=${readonly}&appid=tab_${task.id}&tabid=tab_tab_${task.id}`
          );
          // document.cookie = `TiKuSession=${cookie}`;
          //定时器监听窗口关闭，调用删除session接口
          var winInterval = setInterval(function() {
            if (winopen.closed) {
              clearInterval(winInterval);
              axios.get("/api/exchange/removeLoginSession");
            }
          }, 1000);
        });
    },
    /**
     * 批量转交 */
    changeReceiverBatch() {
      this.$confirm(
        '是否确认将选中线索转交给<span class="el-tag">' +
          this.form.receivename +
          "</span>",
        { dangerouslyUseHTMLString: true }
      ).then(() => {
        const params = new FormData();
        params.append("ids", this.ids);
        params.append("receiveid", this.form.receiveid);
        params.append("receivename", this.form.receivename);
        changeClueReceiver(params).then(response => {
          if (response.code == "200") {
            this.msgSuccess("转交成功");
            this.form.receiveID = "";
            this.form.receiveName = "";
            this.receiveSubmit = true;
            this.getList();
          }
        });
      });
    }
  }
};
</script>