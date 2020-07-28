<template>
  <div class="app-container">
    <el-table
      v-loading="loading"
      :data="deptList"
      row-key="id"
      lazy
      ref="table"
      :load="load"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="部门名称" prop="name" />
      <!-- <el-table-column label="是否删除" align="center" prop="deleted" />
      <el-table-column label="流程类型" align="center" prop="flowinfotype" :formatter="flowinfotypeFormat" />
      <el-table-column label="部门类型" align="center" prop="depttype" :formatter="depttypeFormat" />
      <el-table-column label="分管常委姓名" align="center" prop="chargepersonname" />
      <el-table-column label="分管副书记姓名" align="center" prop="chargeleadername" />
      <el-table-column label="书记姓名" align="center" prop="mainleadername" />
      <el-table-column label="派驻副组长姓名" align="center" prop="paizhusubleadername" />
      <el-table-column label="派驻组长姓名" align="center" prop="paizhumainleadername" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['sjwflowsys:dept:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            @click="handleAdd(scope.row)"
            v-hasPermi="['system:dept:add']"
          >新增</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['sjwflowsys:dept:remove']"
            v-if="scope.row.pid != -1"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改部门对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="900px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="150px">
        <el-form-item label="上级部门" prop="pid">
          <dept-select-tree
            :pid="form.pid"
            :label="form.parentName"
            @selectterm="updatepSelectTreeValue"
            :type="'dept'"
            :selectID="'pid'"
          ></dept-select-tree>
        </el-form-item>
        <el-form-item label="部门名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入部门名称" />
        </el-form-item>
        <el-form-item label="部门分类" prop="category">
          <el-select v-model="form.category" placeholder="请选择部门属性">
            <el-option
              v-for="item in categoryOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="排序" prop="sortcode">
          <el-input-number v-model="form.sortcode" placeholder="请输入排序" controls-position="right" />
        </el-form-item>
        <el-form-item label="流程类型" prop="flowinfotype">
          <el-select v-model="form.flowinfotype" placeholder="请选择流程类型">
            <el-option
              v-for="dict in flowinfotypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="部门类型" prop="depttype">
          <el-select v-model="form.depttype" placeholder="请选择部门类型">
            <el-option
              v-for="dict in depttypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="线索编号代码" prop="xscode">
          <el-input v-model="form.xscode" placeholder="用于线索编号生成" />
        </el-form-item>
        <el-form-item label="派驻地点" prop="paizhuarea">
          <el-input v-model="form.paizhuarea" placeholder="用于市级派驻文书生成标题" />
        </el-form-item>
        <el-form-item label="分管常委" prop="chargepersonid">
          <dept-select-tree
            :pid="form.chargepersonid"
            :label="form.chargepersonname"
            :type="'user'"
            @selectterm="updatepSelectTreeValue"
            :selectID="'chargepersonid'"
            :selectName="'chargepersonname'"
          ></dept-select-tree>
        </el-form-item>
        <el-form-item label="分管副书记" prop="chargeleadername">
          <dept-select-tree
            :pid="form.chargeleaderid"
            :label="form.chargeleadername"
            :type="'user'"
            @selectterm="updatepSelectTreeValue"
            :selectID="'chargeleaderid'"
            :selectName="'chargeleadername'"
          ></dept-select-tree>
        </el-form-item>
        <el-form-item label="书记" prop="mainleaderid">
          <dept-select-tree
            :pid="form.mainleaderid"
            :label="form.mainleadername"
            :type="'user'"
            @selectterm="updatepSelectTreeValue"
            :selectID="'mainleaderid'"
            :selectName="'mainleadername'"
          ></dept-select-tree>
        </el-form-item>
        <el-form-item label="派驻副组长" prop="paizhusubleaderid">
          <dept-select-tree
            :pid="form.paizhusubleaderid"
            :label="form.paizhusubleadername"
            :type="'user'"
            @selectterm="updatepSelectTreeValue"
            :selectID="'paizhusubleaderid'"
            :selectName="'paizhusubleadername'"
          ></dept-select-tree>
        </el-form-item>
        <el-form-item label="派驻组长" prop="paizhumainleaderid">
          <dept-select-tree
            :pid="form.paizhumainleaderid"
            :label="form.paizhumainleadername"
            :type="'user'"
            @selectterm="updatepSelectTreeValue"
            :selectID="'paizhumainleaderid'"
            :selectName="'paizhumainleadername'"
          ></dept-select-tree>
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
  listDept,
  getDept,
  delDept,
  addDept,
  updateDept,
  exportDept
} from "@/api/sjwflowsys/dept";
import Treeselect from "@riophae/vue-treeselect";
import deptSelectTree from "@/views/sjwflowsys/dept/components/deptSelectTree";
import { LOAD_CHILDREN_OPTIONS } from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
const simulateAsyncOperation = fn => {
  setTimeout(fn, 2000);
};
export default {
  name: "Dept",
  components: { Treeselect, deptSelectTree },
  data() {
    return {
      a: 1,
      // 遮罩层
      loading: true,
      // 部门表格数据
      deptList: [],
      // 部门树选项
      deptOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 流程类型字典
      flowinfotypeOptions: [],
      // 部门类型字典
      depttypeOptions: [],
      // 查询参数
      queryParams: {
        pid: undefined,
        name: undefined,
        depttype: undefined,
        chargepersonname: undefined,
        chargeleadername: undefined,
        mainleadername: undefined,
        paizhusubleadername: undefined,
        paizhumainleadername: undefined
      },
      loadNodeMap: new Map(),
      // 部门属性字典
      categoryOptions: [
        {
          value: "区域",
          label: "区域"
        },
        {
          value: "单位",
          label: "单位"
        },
        {
          value: "部门",
          label: "部门"
        },
        {
          value: "工作组",
          label: "工作组"
        }
      ],
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        pid: [{ required: true, message: "上级部门不能为空", trigger: "blur" }],
        name: [
          { required: true, message: "部门名称不能为空", trigger: "blur" }
        ],
        category: [
          { required: true, message: "部门分类不能为空", trigger: "blur" }
        ],
        sortcode: [
          { required: true, message: "排序不能为空", trigger: "blur" }
        ],
        infotype: [
          { required: true, message: "流程类型不能为空", trigger: "blur" }
        ],
        depttype: [
          { required: true, message: "部门类型不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("sjwflow_dept_flowinfotype").then(response => {
      this.flowinfotypeOptions = response.data;
    });
    this.getDicts("sjwflow_dept_type").then(response => {
      this.depttypeOptions = response.data;
    });
  },
  methods: {
    /** 查询部门列表 */
    getList() {
      this.loading = true;
      listDept(this.queryParams).then(response => {
        this.deptList = this.handleTree(
          response.data,
          "id",
          "pid",
          "children",
          "0",
          "1"
        );
        this.loading = false;
      });
    },
    /** 懒加载树 */
    load(tree, treeNode, resolve) {
      this.loadNodeMap.set(tree.id, { tree, treeNode, resolve });
      this.queryParams.pid = tree.id;
      listDept(this.queryParams).then(response => {
        resolve(response.data);
      });
    },
    /** 转换部门数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.id,
        label: node.name,
        children: node.children
      };
    },
    // 流程类型字典翻译
    flowinfotypeFormat(row, column) {
      return this.selectDictLabel(this.flowinfotypeOptions, row.flowinfotype);
    },
    // 部门类型字典翻译
    depttypeFormat(row, column) {
      return this.selectDictLabel(this.depttypeOptions, row.depttype);
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
        name: undefined,
        category: undefined,
        sortcode: undefined,
        serialnumber: undefined,
        deleted: "0",
        flowinfotype: undefined,
        depttype: undefined,
        xscode: undefined,
        paizhuarea: undefined,
        chargepersonid: undefined,
        chargepersonname: undefined,
        chargeleaderid: undefined,
        chargeleadername: undefined,
        mainleaderid: undefined,
        mainleadername: undefined,
        paizhusubleaderid: undefined,
        paizhusubleadername: undefined,
        paizhumainleaderid: undefined,
        paizhumainleadername: undefined,
        parentName: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      if (row != undefined) {
        this.form.pid = row.id;
        this.form.hasChildren = false;
        this.form.deleted = false;
      }
      this.open = true;
      this.title = "添加部门";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      getDept(row.id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改部门";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateDept(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                // this.getList();
              }
            });
          } else {
            addDept(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                // this.getList();
              }
            });
          }
          //懒加载刷新当前级

          if (this.loadNodeMap.has(this.form.pid)) {
            this.refreshNode(this.form.pid);
          } else {
            this.form.hasChildren = true;
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      this.$confirm('是否确认删除-"' + row.name + '"-的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function() {
          return delDept(row.id);
        })
        .then(() => {
          this.refreshNode(row.pid);
          this.msgSuccess("删除成功");
        })
        .catch(function() {});
    },
    /** 刷新子节点 */
    refreshNode(id) {
      const { tree, treeNode, resolve } = this.loadNodeMap.get(id);
      this.$set(this.$refs.table.store.states.lazyTreeNodeMap, id, []);
      this.queryParams.pid = id;
      listDept(this.queryParams).then(response => {
        resolve(response.data);
      });
    },
    /** 同步下拉树数据 */
    updatepSelectTreeValue(node, id, label) {
      this.form[id] = node.id;
      label != undefined && [(this.form[label] = node.label)];
    }
  }
};
</script>