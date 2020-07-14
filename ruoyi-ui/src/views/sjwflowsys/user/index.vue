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
          v-hasPermi="['sjwflowsys:user:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['sjwflowsys:user:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['sjwflowsys:user:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['sjwflowsys:user:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="部门名称" align="center" prop="deptname" />
      <el-table-column label="用户名" align="center" prop="name" />
      <el-table-column label="姓名" align="center" prop="fullname" />
      <el-table-column label="机构名" align="center" prop="companyname" />
      <el-table-column label="人员类型" align="center" prop="usertype" :formatter="usertypeFormat" />
      <el-table-column label="激活" align="center" prop="activated" :formatter="activatedFormat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['sjwflowsys:user:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['sjwflowsys:user:remove']"
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

    <!-- 添加或修改业务平台用户管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="" prop="pid">
          <el-input v-model="form.pid" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="" prop="unitid">
          <el-input v-model="form.unitid" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="部门ID" prop="deptid">
          <el-input v-model="form.deptid" placeholder="请输入部门ID" />
        </el-form-item>
        <el-form-item label="部门名称" prop="deptname">
          <el-input v-model="form.deptname" placeholder="请输入部门名称" />
        </el-form-item>
        <el-form-item label="用户名" prop="name">
          <el-input v-model="form.name" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" placeholder="请输入密码" />
        </el-form-item>
        <el-form-item label="姓名" prop="fullname">
          <el-input v-model="form.fullname" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="身份证" prop="idcard">
          <el-input v-model="form.idcard" placeholder="请输入身份证" />
        </el-form-item>
        <el-form-item label="机构ID" prop="companyid">
          <el-input v-model="form.companyid" placeholder="请输入机构ID" />
        </el-form-item>
        <el-form-item label="机构名" prop="companyname">
          <el-input v-model="form.companyname" placeholder="请输入机构名" />
        </el-form-item>
        <el-form-item label="排序吗" prop="sortcode">
          <el-input v-model="form.sortcode" placeholder="请输入排序吗" />
        </el-form-item>
        <el-form-item label="修改时间" prop="edittime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.edittime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择修改时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="过渡页显示" prop="guoduye">
          <el-input v-model="form.guoduye" placeholder="请输入过渡页显示" />
        </el-form-item>
        <el-form-item label="是否删除">
          <el-radio-group v-model="form.deleted">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="手机号码" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入手机号码" />
        </el-form-item>
        <el-form-item label="人员类型">
          <el-select v-model="form.usertype" placeholder="请选择人员类型">
            <el-option
              v-for="dict in usertypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="激活">
          <el-radio-group v-model="form.activated">
            <el-radio
              v-for="dict in activatedOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="激活" prop="analysisroot">
          <el-input v-model="form.analysisroot" placeholder="请输入激活" />
        </el-form-item>
        <el-form-item label="是否管理员">
          <el-radio-group v-model="form.isadmin">
            <el-radio
              v-for="dict in isadminOptions"
              :key="dict.dictValue"
              :label="parseInt(dict.dictValue)"
            >{{dict.dictLabel}}</el-radio>
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
import { listUser, getUser, delUser, addUser, updateUser, exportUser } from "@/api/sjwflowsys/user";

export default {
  name: "User",
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
      // 业务平台用户管理表格数据
      userList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 人员类型字典
      usertypeOptions: [],
      // 激活字典
      activatedOptions: [],
      // 是否管理员字典
      isadminOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        deleted: [
          { required: true, message: "是否删除不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("sjwflow_user_usertype").then(response => {
      this.usertypeOptions = response.data;
    });
    this.getDicts("sys_yes_no").then(response => {
      this.activatedOptions = response.data;
    });
    this.getDicts("sys_yes_no").then(response => {
      this.isadminOptions = response.data;
    });
  },
  methods: {
    /** 查询业务平台用户管理列表 */
    getList() {
      this.loading = true;
      listUser(this.queryParams).then(response => {
        this.userList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 人员类型字典翻译
    usertypeFormat(row, column) {
      return this.selectDictLabel(this.usertypeOptions, row.usertype);
    },
    // 激活字典翻译
    activatedFormat(row, column) {
      return this.selectDictLabel(this.activatedOptions, row.activated);
    },
    // 是否管理员字典翻译
    isadminFormat(row, column) {
      return this.selectDictLabel(this.isadminOptions, row.isadmin);
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
        pid: undefined,
        unitid: undefined,
        deptid: undefined,
        deptname: undefined,
        name: undefined,
        password: undefined,
        fullname: undefined,
        idcard: undefined,
        companyid: undefined,
        companyname: undefined,
        sortcode: undefined,
        edittime: undefined,
        guoduye: undefined,
        deleted: "0",
        phone: undefined,
        usertype: undefined,
        activated: "0",
        analysisroot: undefined,
        isadmin: "0"
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加业务平台用户管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getUser(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改业务平台用户管理";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateUser(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addUser(this.form).then(response => {
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
      this.$confirm('是否确认删除业务平台用户管理编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delUser(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有业务平台用户管理数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportUser(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>