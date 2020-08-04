<template>
  <div class="app-container">
    <!-- <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>-->

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['sjwflowbusiness:problemlist:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['sjwflowbusiness:problemlist:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['sjwflowbusiness:problemlist:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table
      v-loading="loading"
      :data="problemlistList"
      @selection-change="handleSelectionChange"
      border
      fit
      :height="tableHeight"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="处理日期" align="center" prop="createdate" min-width="150" />
      <el-table-column label="处理者" align="center" prop="createusername" />
      <el-table-column label="所在地区" align="center" prop="parea" />
      <el-table-column label="所在机关" align="center" prop="poffice" min-width="150" />
      <el-table-column label="所在科室" align="center" prop="pclass" min-width="150" />
      <el-table-column
        label="服务类型"
        align="center"
        prop="servicetype"
        show-overflow-tooltip
        min-width="150"
      />
      <el-table-column label="联系人姓名" align="center" prop="pname" min-width="150" />
      <el-table-column
        label="联系方式"
        align="center"
        prop="pphone"
        min-width="150"
        show-overflow-tooltip
      />
      <el-table-column
        label="线索编号/信访编号"
        align="center"
        prop="cluecode"
        min-width="150"
        show-overflow-tooltip
      />
      <el-table-column label="处理方式" align="center" prop="handletype" />
      <el-table-column label="处理状态" align="center" prop="handlestate" />
      <el-table-column label="是否上报" align="center" prop="isup" />
      <el-table-column label="问题描述" prop="problem" min-width="300" show-overflow-tooltip />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
        fixed="right"
        min-width="150"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['sjwflowbusiness:problemlist:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['sjwflowbusiness:problemlist:remove']"
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

    <!-- 添加或修改运维记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="80%" append-to-body>
      <problem @addproblem="submitForm" :problemid="form.id"></problem>
    </el-dialog>
  </div>
</template>

<script>
import {
  listProblemlist,
  getProblemlist,
  delProblemlist,
  addProblemlist,
  updateProblemlist,
  exportProblemlist
} from "@/api/sjwflowbusiness/problemlist";
import problem from "@/views/sjwflowbusiness/problemlist/problem";
export default {
  name: "Problemlist",
  components: { problem },
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
      // 运维记录表格数据
      problemlistList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 30
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      tableHeight: this.$store.getters.clientHeight - 140 + "px"
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询运维记录列表 */
    getList() {
      this.loading = true;
      listProblemlist(this.queryParams).then(response => {
        this.problemlistList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      // this.reset();
    },
    // 表单重置
    // reset() {
    //   this.form = {
    //     id: undefined,
    //     mainid: undefined,
    //     createuserid: undefined,
    //     createusername: undefined,
    //     createdeptid: undefined,
    //     createdeptname: undefined,
    //     number: undefined,
    //     createdate: undefined,
    //     parea: undefined,
    //     poffice: undefined,
    //     pclass: undefined,
    //     pname: undefined,
    //     pphone: undefined,
    //     servicetype: undefined,
    //     problem: undefined,
    //     cluecode: undefined,
    //     handletype: undefined,
    //     handlestate: undefined,
    //     isup: undefined,
    //     handlestaff: undefined,
    //     currentstep: undefined,
    //     status: "0",
    //     timestamp: undefined,
    //     isdeleted: "0"
    //   };
    //   this.resetForm("form");
    // },
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
      this.form.id = "";
      this.open = true;
      this.title = "添加运维记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.form.id = row.id;
      this.open = true;
      this.title = "修改运维记录";
    },
    /** 提交按钮 */
    submitForm: function(data) {
      this.form = data;
      if (this.form.id != undefined) {
        updateProblemlist(this.form).then(response => {
          if (response.code === 200) {
            this.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          }
        });
      } else {
        addProblemlist(this.form).then(response => {
          if (response.code === 200) {
            this.msgSuccess("新增成功");
            this.open = false;
            this.getList();
          }
        });
      }
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm(
        '是否确认删除运维记录编号为"' + ids + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(function() {
          return delProblemlist(ids);
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
      this.$confirm("是否确认导出所有运维记录数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function() {
          return exportProblemlist(queryParams);
        })
        .then(response => {
          this.download(response.msg);
        })
        .catch(function() {});
    }
  }
};
</script>