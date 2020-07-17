<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['sjwflowbusiness:workflowtask:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['sjwflowbusiness:workflowtask:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['sjwflowbusiness:workflowtask:remove']"
        >删除</el-button>
      </el-col>
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
      v-loading="loading"
      :data="workflowtaskList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="${comment}" align="center" prop="id" />
      <el-table-column label="${comment}" align="center" prop="stepname" />
      <el-table-column label="${comment}" align="center" prop="instanceid" />
      <el-table-column label="${comment}" align="center" prop="clueid" />
      <el-table-column label="${comment}" align="center" prop="title" />
      <el-table-column label="${comment}" align="center" prop="sendername" />
      <el-table-column label="${comment}" align="center" prop="sendertime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.sendertime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="${comment}" align="center" prop="receivename" />
      <el-table-column label="${comment}" align="center" prop="receivetime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.receivetime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="${comment}" align="center" prop="comment" />
      <el-table-column label="${comment}" align="center" prop="status" />
      <el-table-column label="${comment}" align="center" prop="handledeptid" />
      <el-table-column label="线索组ID 措施中步骤监控需要使用" align="center" prop="stepstate" />
      <el-table-column label="线索组ID 措施中步骤监控需要使用" align="center" prop="databasename" />
      <el-table-column label="调换科室原接收人所在步骤" align="center" prop="infotype" />
      <el-table-column label="调换科室原接收人所在步骤" align="center" prop="isdeleted" />
      <el-table-column label="调换科室原接收人所在步骤" align="center" prop="isdeletd" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['sjwflowbusiness:workflowtask:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['sjwflowbusiness:workflowtask:remove']"
          >删除</el-button>
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

    <!-- 添加或修改流程记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="调换科室原接收人所在步骤" prop="previd">
          <el-input v-model="form.previd" placeholder="请输入调换科室原接收人所在步骤" />
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="prevstepid">
          <el-input v-model="form.prevstepid" placeholder="请输入调换科室原接收人所在步骤" />
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="flowid">
          <el-input v-model="form.flowid" placeholder="请输入调换科室原接收人所在步骤" />
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="stepid">
          <el-input v-model="form.stepid" placeholder="请输入调换科室原接收人所在步骤" />
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="stepname">
          <el-input v-model="form.stepname" placeholder="请输入调换科室原接收人所在步骤" />
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="instanceid">
          <el-input v-model="form.instanceid" placeholder="请输入调换科室原接收人所在步骤" />
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="clueid">
          <el-input v-model="form.clueid" placeholder="请输入调换科室原接收人所在步骤" />
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="groupid">
          <el-input v-model="form.groupid" placeholder="请输入调换科室原接收人所在步骤" />
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤">
          <el-select v-model="form.type" placeholder="请选择调换科室原接收人所在步骤">
            <el-option label="请选择字典生成" value />
          </el-select>
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="title">
          <el-input v-model="form.title" placeholder="请输入调换科室原接收人所在步骤" />
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="senderid">
          <el-input v-model="form.senderid" placeholder="请输入调换科室原接收人所在步骤" />
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="sendername">
          <el-input v-model="form.sendername" placeholder="请输入调换科室原接收人所在步骤" />
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="sendertime">
          <el-date-picker
            clearable
            size="small"
            style="width: 200px"
            v-model="form.sendertime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择调换科室原接收人所在步骤"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="receiveid">
          <el-input v-model="form.receiveid" placeholder="请输入调换科室原接收人所在步骤" />
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="receivename">
          <el-input v-model="form.receivename" placeholder="请输入调换科室原接收人所在步骤" />
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="receivetime">
          <el-date-picker
            clearable
            size="small"
            style="width: 200px"
            v-model="form.receivetime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择调换科室原接收人所在步骤"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="touserid">
          <el-input v-model="form.touserid" placeholder="请输入调换科室原接收人所在步骤" />
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="tousername">
          <el-input v-model="form.tousername" placeholder="请输入调换科室原接收人所在步骤" />
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="opentime">
          <el-date-picker
            clearable
            size="small"
            style="width: 200px"
            v-model="form.opentime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择调换科室原接收人所在步骤"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="completedtime">
          <el-date-picker
            clearable
            size="small"
            style="width: 200px"
            v-model="form.completedtime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择调换科室原接收人所在步骤"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="completedtime1">
          <el-date-picker
            clearable
            size="small"
            style="width: 200px"
            v-model="form.completedtime1"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择调换科室原接收人所在步骤"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="comment">
          <el-input v-model="form.comment" placeholder="请输入调换科室原接收人所在步骤" />
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="issign">
          <el-input v-model="form.issign" placeholder="请输入调换科室原接收人所在步骤" />
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤">
          <el-radio-group v-model="form.status">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="note">
          <el-input v-model="form.note" placeholder="请输入调换科室原接收人所在步骤" />
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="sort">
          <el-input v-model="form.sort" placeholder="请输入调换科室原接收人所在步骤" />
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="subflowgroupid">
          <el-input v-model="form.subflowgroupid" placeholder="请输入调换科室原接收人所在步骤" />
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="handledeptid">
          <el-input v-model="form.handledeptid" placeholder="请输入调换科室原接收人所在步骤" />
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="subreceiveid">
          <el-input v-model="form.subreceiveid" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="handlestaffid">
          <el-input v-model="form.handlestaffid" placeholder="请输入调换科室原接收人所在步骤" />
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="handlestaffname">
          <el-input v-model="form.handlestaffname" placeholder="请输入调换科室原接收人所在步骤" />
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="mainhandlestaffname">
          <el-input v-model="form.mainhandlestaffname" placeholder="请输入调换科室原接收人所在步骤" />
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="mainhandlestaffid">
          <el-input v-model="form.mainhandlestaffid" placeholder="请输入调换科室原接收人所在步骤" />
        </el-form-item>
        <el-form-item label="线索组ID 措施中步骤监控需要使用" prop="cluegroupid">
          <el-input v-model="form.cluegroupid" placeholder="请输入线索组ID 措施中步骤监控需要使用" />
        </el-form-item>
        <el-form-item label="线索组ID 措施中步骤监控需要使用" prop="rolltime">
          <el-date-picker
            clearable
            size="small"
            style="width: 200px"
            v-model="form.rolltime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择线索组ID 措施中步骤监控需要使用"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="线索组ID 措施中步骤监控需要使用" prop="stepstate">
          <el-input v-model="form.stepstate" placeholder="请输入线索组ID 措施中步骤监控需要使用" />
        </el-form-item>
        <el-form-item label="线索组ID 措施中步骤监控需要使用" prop="databasename">
          <el-input v-model="form.databasename" placeholder="请输入线索组ID 措施中步骤监控需要使用" />
        </el-form-item>
        <el-form-item label="线索组ID 措施中步骤监控需要使用" prop="ismodify">
          <el-input v-model="form.ismodify" placeholder="请输入线索组ID 措施中步骤监控需要使用" />
        </el-form-item>
        <el-form-item label="线索组ID 措施中步骤监控需要使用" prop="formid">
          <el-input v-model="form.formid" placeholder="请输入线索组ID 措施中步骤监控需要使用" />
        </el-form-item>
        <el-form-item label="调换科室原接收人或发送人" prop="oldreceiveid">
          <el-input v-model="form.oldreceiveid" placeholder="请输入调换科室原接收人或发送人" />
        </el-form-item>
        <el-form-item label="调换科室原接收人或发送人" prop="oldreceivename">
          <el-input v-model="form.oldreceivename" placeholder="请输入调换科室原接收人或发送人" />
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="oldstepid">
          <el-input v-model="form.oldstepid" placeholder="请输入调换科室原接收人所在步骤" />
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤">
          <el-select v-model="form.infotype" placeholder="请选择调换科室原接收人所在步骤">
            <el-option label="请选择字典生成" value />
          </el-select>
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤">
          <el-radio-group v-model="form.isdeleted">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤" prop="spbid">
          <el-input v-model="form.spbid" placeholder="请输入调换科室原接收人所在步骤" />
        </el-form-item>
        <el-form-item label="调换科室原接收人所在步骤">
          <el-radio-group v-model="form.isdeletd">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
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

export default {
  name: "Workflowtask",
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
        pageSize: 10,
        clueid: undefined
      },
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
      }
    };
  },
  created() {
    this.queryParams.clueid = this.$route.params && this.$route.query.id;
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加流程记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getWorkflowtask(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改流程记录";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateWorkflowtask(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addWorkflowtask(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm(
        '是否确认删除流程记录编号为"' + ids + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(function() {
          return delWorkflowtask(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
        .catch(function() {});
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
    }
  }
};
</script>