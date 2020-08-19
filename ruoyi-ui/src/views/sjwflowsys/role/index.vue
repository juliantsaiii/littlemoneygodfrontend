<template>
  <div class="app-container">
    <el-row>
      <el-col :span="6">
        <div :style="treeheight">
          <el-tree
            :data="deptList"
            :props="props"
            :load="loadNode"
            lazy
            @node-click="refreshRoleList"
          ></el-tree>
        </div>
      </el-col>
      <el-col :span="18">
        <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
          <el-form-item label="角色名" prop="name">
            <el-input
              v-model="queryParams.name"
              placeholder="请输入角色名"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
          <el-form-item style="float:right">
            <el-button
              type="success"
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
              v-hasPermi="['sjwflowsys:role:add']"
            >新增</el-button>
          </el-form-item>
        </el-form>

        <el-table
          v-loading="loading"
          :data="roleList"
          @selection-change="handleSelectionChange"
          :height="windowHeight-200"
        >
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="id" align="center" prop="id" />
          <el-table-column label="companyid" align="center" prop="companyid" />
          <el-table-column label="角色名" align="center" prop="name" />
          <el-table-column label="排序码" align="center" prop="sortcode" />
          <el-table-column label="角色分类" align="center" prop="category" />
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['sjwflowsys:role:edit']"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['sjwflowsys:role:remove']"
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
      </el-col>
    </el-row>

    <!-- 添加或修改角色管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="companyid" prop="companyid">
          <el-input v-model="form.companyid" placeholder="请输入companyid" />
        </el-form-item>
        <el-form-item label="name" prop="name">
          <el-input v-model="form.name" placeholder="请输入角色名" />
        </el-form-item>
        <el-form-item label="排序码" prop="sortcode">
          <el-input v-model="form.sortcode" placeholder="请输入排序码" />
        </el-form-item>
        <el-form-item label="角色分类">
          <el-radio-group v-model="form.deleted">
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
  listRole,
  getRole,
  delRole,
  addRole,
  updateRole,
  exportRole
} from "@/api/sjwflowsys/role";
import { getDeptTree } from "@/api/sjwflowsys/dept";

export default {
  name: "Role",
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
      // 角色管理表格数据
      roleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 30,
        name: undefined,
        companyid: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        deleted: [
          { required: true, message: "$comment不能为空", trigger: "blur" }
        ]
      },
      props: {
        label: "name",
        children: "children",
        isLeaf: "hasChildren"
      },
      windowHeight: this.$store.getters.clientHeight,
      treeheight: {
        height: this.$store.getters.clientHeight - 100 + "px",
        overflow: "auto"
      },
      rolequeryParams: {
        pid: "-1",
        type: "role",
        selectType: "tree",
        deptType: ""
      },
      deptList: []
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询角色管理列表 */
    getList() {
      this.loading = true;
      listRole(this.queryParams).then(response => {
        this.roleList = response.rows;
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
        pid: undefined,
        companyid: undefined,
        name: undefined,
        sortcode: undefined,
        category: undefined,
        deleted: "0"
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
      this.title = "添加角色管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getRole(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改角色管理";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateRole(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addRole(this.form).then(response => {
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
        '是否确认删除角色管理编号为"' + ids + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(function() {
          return delRole(ids);
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
      this.$confirm("是否确认导出所有角色管理数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function() {
          return exportRole(queryParams);
        })
        .then(response => {
          this.download(response.msg);
        })
        .catch(function() {});
    },
    /** 懒加载树 */
    loadNode(node, resolve) {
      this.rolequeryParams.pid = node.data.id;
      this.rolequeryParams.selectType = "role";
      getDeptTree(this.rolequeryParams).then(response => {
        console.log(response);
        resolve(response.data);
      });
    },
    /** 部门节点点击事件 */
    refreshRoleList(data) {
      this.queryParams.companyid = data.id;
      listRole(this.queryParams).then(response => {
        this.roleList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    }
  }
};
</script>