<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="6">
        <div :style="treeheight">
          <workflowinfoTree @changevalue="changevalue"></workflowinfoTree>
        </div>
      </el-col>
      <el-col :span="18">
        <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="100px">
          <el-form-item label="文书号缩写" prop="name">
            <el-input
              v-model="queryParams.name"
              placeholder="请输入文书号缩写"
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

        <el-table
          v-loading="loading"
          :data="wenshuList"
          @selection-change="handleSelectionChange"
          :height="800"
        >
          <!-- <el-table-column type="selection" width="55" align="center" /> -->
          <el-table-column label="序号" align="center" type="index" />
          <el-table-column label="文书名" align="center" prop="name" />
          <el-table-column label="路径" align="center" prop="url" />
          <el-table-column label="类型" align="center" prop="type" />
          <el-table-column label="描述" align="center" prop="describe" />
          <el-table-column label="文号头" align="center" prop="docabbreviation" />
          <el-table-column label="是否编号" align="center" prop="hascode">
            <template slot-scope="scope">
              <el-switch v-model="scope.row.hascode" @change="changeCode(scope.row)"></el-switch>
            </template>
          </el-table-column>
          <el-table-column
            label="操作"
            align="center"
            class-name="small-padding fixed-width"
            width="200"
          >
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-notebook-2"
                @click="openWenshu(scope.row)"
                v-hasPermi="['sjwflowsys:wenshu:remove']"
              >书签</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['sjwflowsys:wenshu:edit']"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-coordinate"
                @click="sealMsg(scope.row)"
                v-hasPermi="['sjwflowsys:wenshu:edit']"
              >章</el-button>
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

    <!-- 添加或修改文书管理对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="open"
      width="800px"
      append-to-body
      :close-on-click-modal="false"
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="文书名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="缩写" prop="designation">
          <el-input v-model="form.designation" placeholder="缩写" />
        </el-form-item>
        <el-form-item label="流程" prop="process">
          <el-input v-model="form.process" placeholder="请输入流程名" />
        </el-form-item>
        <el-form-item label="路径" prop="url">
          <el-input v-model="form.url" placeholder="请输入路径" />
        </el-form-item>
        <!-- <el-form-item label="排序" prop="sort">
          <el-input v-model="form.sort" placeholder="请输入排序" />
        </el-form-item>-->
        <el-form-item label="类型">
          <el-select v-model="form.type" placeholder="请选择文书类型" clearable>
            <el-option
              v-for="item in typeoptions"
              :key="item.value"
              :value="item.value"
              :label="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否编号">
          <el-switch v-model="form.hascode"></el-switch>
        </el-form-item>
        <!-- <el-form-item label="流程类型">
          <el-select v-model="form.infotype" placeholder="请选择流程类型">
            <el-option label="请选择字典生成" value />
          </el-select>
        </el-form-item>-->
        <el-form-item label="需要签章的">
          <el-select v-model="form.basetype" placeholder="请选择需要签章的">
            <el-option label="请选择字典生成" value />
          </el-select>
        </el-form-item>
        <el-form-item label="文书描述" prop="describe">
          <el-input v-model="form.describe" placeholder="请输入文书描述" />
        </el-form-item>
        <el-form-item label="文号头" prop="docabbreviation">
          <el-input v-model="form.docabbreviation" placeholder="请输入文号头" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog
      title="签章信息"
      :visible.sync="sealOpen"
      width="800px"
      append-to-body
      :close-on-click-modal="false"
    >
      <el-form ref="sealform" :model="sealform" :rules="sealRules" label-width="100px">
        <el-form-item label="章名" prop="ename">
          <el-input v-model="sealform.ename" placeholder="请输入章名"></el-input>
        </el-form-item>
        <el-form-item label="页码" prop="pagenum">
          <el-input-number v-model="sealform.pagenum" placeholder="请输入盖章页码"></el-input-number>
        </el-form-item>
        <el-form-item label="横坐标" prop="positionx">
          <el-input-number v-model="sealform.positionx" placeholder="请输入横坐标"></el-input-number>
        </el-form-item>
        <el-form-item label="纵坐标" prop="positiony">
          <el-input-number v-model="sealform.positiony" placeholder="请输入纵坐标"></el-input-number>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitSealForm">确 定</el-button>
        <el-button @click="sealCancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listWenshu,
  getWenshu,
  delWenshu,
  addWenshu,
  updateWenshu,
  exportWenshu
} from "@/api/sjwflowsys/wenshu";
import workflowinfoTree from "@/views/sjwflowsys/workflowinfo/components/workflowinfoTree";
import { openNtkoWindow } from "@/api/monitor/viewfile";
import {
  getWenshuseal,
  addWenshuseal,
  updateWenshuseal
} from "@/api/sjwflowsys/wenshu";
export default {
  name: "Wenshu",
  components: { workflowinfoTree },
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
      // 文书管理表格数据
      wenshuList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: undefined,
        infotype: undefined,
        process: undefined
      },
      treeheight: {
        height: this.$store.getters.clientHeight - 100 + "px",
        overflow: "auto"
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "文书名称不能为空", trigger: "blur" }
        ],
        designation: [
          { required: true, message: "缩写不能为空", trigger: "blur" }
        ],
        process: [
          { required: true, message: "流程名不能为空", trigger: "blur" }
        ],
        url: [{ required: true, message: "文书路径不能为空", trigger: "blur" }]
      },
      typeoptions: [
        { value: "决定书" },
        { value: "审批表" },
        { value: "备案表" },
        { value: "隐藏" }
      ],
      sealOpen: false,
      sealform: {},
      sealRules: {
        ename: [{ required: true, message: "章名不能为空", trigger: "blur" }],
        pagenum: [{ required: true, message: "页码不能为空", trigger: "blur" }],
        positionx: [
          { required: true, message: "横坐标不能为空", trigger: "blur" }
        ],
        positiony: [
          { required: true, message: "纵坐标不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询文书管理列表 */
    getList() {
      this.loading = true;
      listWenshu(this.queryParams).then(response => {
        this.wenshuList = response.rows;
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
        name: undefined,
        designation: undefined,
        process: undefined,
        url: undefined,
        sort: undefined,
        type: undefined,
        qianzhang: undefined,
        hascode: "0",
        infotype: undefined,
        basetype: undefined,
        secretaries: undefined,
        format: undefined,
        describe: undefined,
        docabbreviation: undefined
      };
      this.resetForm("form");
    },
    sealCancel() {
      this.sealOpen = false;
      this.sealReset();
    },
    sealReset() {
      this.sealform = {
        id: undefined,
        wenshuid: undefined,
        ename: undefined,
        pagenum: 1,
        positionx: undefined,
        positiony: undefined
      };
      this.resetForm("sealform");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.queryParams.infotype = undefined;
      this.queryParams.process = undefined;
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
      this.title = "添加文书管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getWenshu(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改文书管理";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateWenshu(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addWenshu(this.form).then(response => {
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
    submitSealForm: function() {
      this.$refs["sealform"].validate(valid => {
        if (valid) {
          if (this.sealform.id != undefined) {
            updateWenshuseal(this.sealform).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.sealOpen = false;
              }
            });
          } else {
            this.sealform.wenshuid = this.form.id;
            addWenshuseal(this.sealform).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.sealOpen = false;
              }
            });
          }
        }
      });
    },
    //根据流程树切换文书查询范围
    changevalue(id, infoname) {
      this.queryParams.infotype = id;
      this.queryParams.process = infoname;
      this.getList();
    },
    //编辑是否编号字段
    changeCode(data) {
      updateWenshu(data).then(response => {
        if (response.code === 200) {
          this.msgSuccess("修改成功");
        }
      });
    },
    openWenshu(data) {
      const savepath = process.env.VUE_APP_FileServer_Path + data.url;
      const filePath = data.url.replace(data.designation + ".doc", "");
      const params =
        "&saveName=" + data.designation + ".doc" + "&savePath=" + filePath;
      const url = this.getdownloadbypath(
        data.designation + ".doc",
        savepath,
        params
      );

      openNtkoWindow(url, false);
    },
    sealMsg(data) {
      this.form = data;
      getWenshuseal(this.form.id).then(response => {
        this.sealform = response.data;
        this.sealOpen = true;
      });
    }
  }
};
</script>