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
            node-key="id"
            @node-click="refreshRoleList"
            :default-expanded-keys="['00000000-0000-1000-0000-000000000000']"
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
    <el-dialog :title="title" :visible.sync="open" width="90%" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="6">
            <el-form-item label="父级" prop="companyid">
              <dept-select-tree
                :pid="form.companyid"
                :id="form.companyid"
                @selectterm="updatepSelectTreeValue"
                :type="'company'"
                :selectID="'companyid'"
              ></dept-select-tree>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="角色名" prop="name">
              <el-input v-model="form.name" placeholder="请输入角色名" />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="排序码" prop="sortcode">
              <el-input-number v-model="form.sortcode" placeholder="请输入排序码" />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="角色分类">
              <el-select v-model="form.category" placeholder="请选择角色类型">
                <el-option
                  v-for="item in roletypeoptions"
                  :key="item.dictValue"
                  :value="item.dictValue"
                  :label="item.dictLabel"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="50">
          <el-col :span="8">
            <el-form-item label="菜单">
              <div class="box-container"></div>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="数据权限">
              <div class="box-container">
                <el-tree
                  ref="companytree"
                  :data="currentDeptTree"
                  :props="props"
                  show-checkbox
                  node-key="id"
                  :default-checked-keys="roleDates"
                ></el-tree>
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="当前人员">
              <div class="box-container">
                <el-tag
                  v-for="tag in roleusers"
                  :key="tag.name"
                  closable
                  :type="tag.type"
                  @close="removeuser(tag)"
                >{{tag.name}}</el-tag>
              </div>
            </el-form-item>
          </el-col>
        </el-row>
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
  exportRole,
  delUserRole,
  getRoleData
} from "@/api/sjwflowsys/role";
import { getUserByRole } from "@/api/sjwflowsys/user";
import { getDeptTree } from "@/api/sjwflowsys/dept";
import deptSelectTree from "@/views/sjwflowsys/dept/components/deptSelectTree";
export default {
  name: "Role",
  components: { deptSelectTree },
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
        type: "company",
        selectType: "tree",
        deptType: ""
      },
      deptList: [],
      roletypeoptions: [],
      //动态展开树
      dataidArr: [],
      //角色对应用户
      roleusers: [],
      //当前company部门树
      currentDeptTree: [],
      //角色对应数据权限
      roleDates: []
    };
  },
  created() {
    this.getList();
    this.getDicts("sjwflow_role_type").then(response => {
      this.roletypeoptions = response.data;
    });
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
        deleted: false
      };
      this.resetForm("form");
      this.roleDates = [];
      this.roleusers = [];
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
        this.getUsers(id);
        this.getDeptByCompany(this.form.companyid);
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          let roledatas = this.$refs.companytree.getCheckedKeys();
          this.form.belongDepts = roledatas;
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
      getDeptTree(this.rolequeryParams).then(response => {
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
    },
    /** 同步下拉树数据 */
    updatepSelectTreeValue(node, id, label) {
      this.form[id] = node.id;
      label != undefined && [(this.form[label] = node.label)];
      this.getDeptByCompany(node.id);
    },
    /** 移除角色中的用户 */
    removeuser(note) {
      this.$confirm(
        `是否确认从<strong>${this.form.name}</strong>中移除<strong>${note.name}</strong>`,
        "提示",
        {
          dangerouslyUseHTMLString: true
        }
      ).then(() => {
        delUserRole(note.id).then(response => {
          if (response.code == "200") {
            this.msgSuccess("移除成功");
            this.getUsers(this.form.id);
          }
        });
      });
    },
    /** 获取用户 */
    getUsers(id) {
      getUserByRole(id).then(response => {
        this.roleusers = response.data;
      });
    },
    /** 获取当前角色company部门 */
    getDeptByCompany(id) {
      this.rolequeryParams.pid = id;
      this.rolequeryParams.type = "tree";
      getDeptTree(this.rolequeryParams).then(response => {
        this.currentDeptTree = response.data;
        getRoleData(this.form.id).then(res => {
          this.roleDates = res.data;
        });
      });
    }
  }
};
</script>
<style>
.box-container {
  height: 600px;
  border: 1px solid #dcdfe6;
  border-radius: 3px;
  padding: 20px;
  overflow: auto;
}
.el-tag {
  margin-left: 10px;
}
</style>