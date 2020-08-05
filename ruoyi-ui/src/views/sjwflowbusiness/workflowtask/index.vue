<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryParams" :inline="true" label-width="80px">
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
          v-hasPermi="['sjwflowbusiness:workflowtask:export']"
        >导出</el-button>
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
      <el-table-column label="接收时间" align="center" min-width="100" prop="receivetime">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.receivetime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="status" align="center" :min-width="100">
        <template slot-scope="scope">
          <el-select v-model="scope.row.status" placeholder @change="updateform(scope.row)">
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
      <el-table-column label="databasename" align="center" prop="databasename" min-width="150" />
      <el-table-column label="infotype" align="center" prop="infotype" />
      <el-table-column label="是否删除" align="center">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.isdeleted" @change="updateform(scope.row)"></el-switch>
        </template>
      </el-table-column>
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
            icon="el-icon-edit"
            @click="openUpdateDialog(scope.row)"
            v-hasPermi="['sjwflowbusiness:workflowtask:edit']"
          >更换用户</el-button>
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

    <el-dialog title="更换接收人" :visible.sync="dialogTreeVisible">
      <dept-select-tree
        :pid="form.receiveid"
        :label="form.receivename"
        @selectterm="updatepSelectTreeValue"
        :type="'user'"
        :selectID="'receiveid'"
        :selectName="'receivename'"
      ></dept-select-tree>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogTreeVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateReceiver">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listWorkflowtask,
  getWorkflowtask,
  delWorkflowtask,
  addWorkflowtask,
  updateWorkflowtask,
  exportWorkflowtask
} from "@/api/sjwflowbusiness/workflowtask";
import deptSelectTree from "@/views/sjwflowsys/dept/components/deptSelectTree";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import Treeselect from "@riophae/vue-treeselect";
export default {
  name: "Workflowtask",
  components: { Treeselect, deptSelectTree },
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
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 30,
        clueid: undefined,
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
        rolltime: [
          {
            required: true,
            message: "调换科室原接收人所在步骤不能为空",
            trigger: "blur"
          }
        ]
      },
      tableHeight: this.$store.getters.clientHeight - 200 + "px",
      dialogTreeVisible: false
    };
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
        status: "0",
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
      this.ids = selection.map(item => item.id);
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
        }
      });
    } /** 同步下拉树数据 */,
    updatepSelectTreeValue(node, id, label) {
      this.form[id] = node.id;
      label != undefined && [(this.form[label] = node.label)];
    },
    /** 打开换人框 */
    openUpdateDialog(data) {
      this.form = data;
      this.dialogTreeVisible = true;
    },
    /** 更新接收人 */
    updateReceiver() {
      this.updateform(this.form);
      this.dialogTreeVisible = false;
      this.getList();
    }
  }
};
</script>