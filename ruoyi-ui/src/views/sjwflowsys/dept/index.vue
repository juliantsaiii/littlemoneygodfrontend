<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="父级ID" prop="pid">
        <el-input
          v-model="queryParams.pid"
          placeholder="请输入父级ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="部门名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入部门名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="部门类型" prop="depttype">
        <el-select v-model="queryParams.depttype" placeholder="请选择部门类型" clearable size="small">
          <el-option
            v-for="dict in depttypeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="分管常委姓名" prop="chargepersonname">
        <el-input
          v-model="queryParams.chargepersonname"
          placeholder="请输入分管常委姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分管副书记姓名" prop="chargeleadername">
        <el-input
          v-model="queryParams.chargeleadername"
          placeholder="请输入分管副书记姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="书记姓名" prop="mainleadername">
        <el-input
          v-model="queryParams.mainleadername"
          placeholder="请输入书记姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="派驻副组长姓名" prop="paizhusubleadername">
        <el-input
          v-model="queryParams.paizhusubleadername"
          placeholder="请输入派驻副组长姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="派驻组长姓名" prop="paizhumainleadername">
        <el-input
          v-model="queryParams.paizhumainleadername"
          placeholder="请输入派驻组长姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          class="filter-item"
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
        >搜索</el-button>
        <el-button
          class="filter-item"
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['sjwflowsys:dept:add']"
        >新增</el-button>
      </el-form-item>
    </el-form>

    <el-table
      v-loading="loading"
      :data="deptList"
      row-key="id"
      lazy
      :load="load"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="部门名称"  prop="name" />
      <!-- <el-table-column label="是否删除" align="center" prop="deleted" />
      <el-table-column label="流程类型" align="center" prop="flowinfotype" :formatter="flowinfotypeFormat" />
      <el-table-column label="部门类型" align="center" prop="depttype" :formatter="depttypeFormat" />
      <el-table-column label="分管常委姓名" align="center" prop="chargepersonname" />
      <el-table-column label="分管副书记姓名" align="center" prop="chargeleadername" />
      <el-table-column label="书记姓名" align="center" prop="mainleadername" />
      <el-table-column label="派驻副组长姓名" align="center" prop="paizhusubleadername" />
      <el-table-column label="派驻组长姓名" align="center" prop="paizhumainleadername" /> -->
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
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['sjwflowsys:dept:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>


   
    <!-- 添加或修改部门对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="父级ID" prop="pid">
          <treeselect v-model="form.pid" :options="deptOptions" :normalizer="normalizer" placeholder="请选择父级ID" />
        </el-form-item>
        <el-form-item label="部门名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入部门名称" />
        </el-form-item>
        <el-form-item label="部门属性" prop="category">
          <el-input v-model="form.category" placeholder="请输入部门属性" />
        </el-form-item>
        <el-form-item label="排序" prop="sortcode">
          <el-input v-model="form.sortcode" placeholder="请输入排序" />
        </el-form-item>
        <el-form-item label="编码" prop="serialnumber">
          <el-input v-model="form.serialnumber" placeholder="请输入编码" />
        </el-form-item>
        <el-form-item label="是否删除">
          <el-radio-group v-model="form.deleted">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="流程类型">
          <el-select v-model="form.flowinfotype" placeholder="请选择流程类型">
            <el-option
              v-for="dict in flowinfotypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="部门类型">
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
          <el-input v-model="form.xscode" placeholder="请输入线索编号代码" />
        </el-form-item>
        <el-form-item label="派驻地点" prop="paizhuarea">
          <el-input v-model="form.paizhuarea" placeholder="请输入派驻地点" />
        </el-form-item>
        <el-form-item label="分管常委ID" prop="chargepersonid">
          <el-input v-model="form.chargepersonid" placeholder="请输入分管常委ID" />
        </el-form-item>
        <el-form-item label="分管常委姓名" prop="chargepersonname">
          <el-input v-model="form.chargepersonname" placeholder="请输入分管常委姓名" />
        </el-form-item>
        <el-form-item label="分管副书记ID" prop="chargeleaderid">
          <el-input v-model="form.chargeleaderid" placeholder="请输入分管副书记ID" />
        </el-form-item>
        <el-form-item label="分管副书记姓名" prop="chargeleadername">
          <el-input v-model="form.chargeleadername" placeholder="请输入分管副书记姓名" />
        </el-form-item>
        <el-form-item label="书记ID" prop="mainleaderid">
          <el-input v-model="form.mainleaderid" placeholder="请输入书记ID" />
        </el-form-item>
        <el-form-item label="书记姓名" prop="mainleadername">
          <el-input v-model="form.mainleadername" placeholder="请输入书记姓名" />
        </el-form-item>
        <el-form-item label="派驻副组长ID" prop="paizhusubleaderid">
          <el-input v-model="form.paizhusubleaderid" placeholder="请输入派驻副组长ID" />
        </el-form-item>
        <el-form-item label="派驻副组长姓名" prop="paizhusubleadername">
          <el-input v-model="form.paizhusubleadername" placeholder="请输入派驻副组长姓名" />
        </el-form-item>
        <el-form-item label="派驻组长ID" prop="paizhumainleaderid">
          <el-input v-model="form.paizhumainleaderid" placeholder="请输入派驻组长ID" />
        </el-form-item>
        <el-form-item label="派驻组长姓名" prop="paizhumainleadername">
          <el-input v-model="form.paizhumainleadername" placeholder="请输入派驻组长姓名" />
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
import { listDept, getDept, delDept, addDept, updateDept, exportDept } from "@/api/sjwflowsys/dept";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Dept",
  components: { Treeselect },
  data() {
    return {
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
      // 部门属性字典
      categoryOptions: [],
      // 流程类型字典
      flowinfotypeOptions: [],
      // 部门类型字典
      depttypeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        pid: undefined,
        name: undefined,
        depttype: undefined,
        chargepersonname: undefined,
        chargeleadername: undefined,
        mainleadername: undefined,
        paizhusubleadername: undefined,
        paizhumainleadername: undefined
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
    this.getDicts("sjwflow_dept_category").then(response => {
      this.categoryOptions = response.data;
    });
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
        this.deptList = this.handleTree(response.data, "id","pid","children","0","1");
        this.loading = false;
      });
    },
    /** 懒加载树 */
   load (tree, treeNode, resolve) {
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
	/** 查询部门下拉树结构 */
    getTreeselect() {
      listDept().then(response => {
        this.deptOptions = [];
        const data = { id: 0, pid: '顶级节点', children: [] };
        data.children = this.handleTree(response.data, "id", "pid");
        this.deptOptions.push(data);
      });
    },
    // 部门属性字典翻译
    categoryFormat(row, column) {
      return this.selectDictLabel(this.categoryOptions, row.category);
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
        paizhumainleadername: undefined
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
    handleAdd() {
      this.reset();
	  this.getTreeselect();
      this.open = true;
      this.title = "添加部门";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
	  this.getTreeselect();
      if (row != undefined) {
        this.form.pid = row.id;
      }
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
                this.getList();
              }
            });
          } else {
            addDept(this.form).then(response => {
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
      this.$confirm('是否确认删除部门编号为"' + row.id + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delDept(row.id);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    }
  }
};
</script>