<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="6">
        <div :style="treeheight">
          <el-tree
            :data="deptList"
            :props="props"
            :load="loadNode"
            lazy
            @node-click="refreshUserList"
          ></el-tree>
        </div>
      </el-col>
      <el-col :span="18">
        <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
          <el-form-item label="姓名" prop="fullname">
            <el-input
              v-model="queryParams.fullname"
              placeholder="请输入用户姓名"
              clearable
              size="small"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="人员类型" prop="usertype">
            <el-select
              v-model="queryParams.usertype"
              placeholder="人员类型"
              clearable
              size="small"
              style="width: 240px"
            >
              <el-option
                v-for="dict in usertypeOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
          <el-form-item style="float:right">
            <el-button
              plain
              type="success"
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
              v-hasPermi="['sjwflowsys:user:add']"
            >新增</el-button>
          </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
          <!-- <el-col :span="1.5">
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
          </el-col>-->
        </el-row>

        <el-table
          v-loading="loading"
          :data="userList"
          @selection-change="handleSelectionChange"
          :height="windowHeight-200"
        >
          <el-table-column label="部门名称" align="center" prop="deptname" />
          <el-table-column label="用户名" align="center" prop="name" />
          <el-table-column label="姓名" align="center" prop="fullname" />
          <el-table-column label="机构名" align="center" prop="companyname" />
          <el-table-column label="人员类型" align="center" prop="usertype" :formatter="usertypeFormat" />
          <el-table-column label="签名" align="center" prop="imageUrl">
            <template slot-scope="scope">
              <el-upload
                class="avatar-uploader"
                :action="uploadImgUrl"
                :headers="headers"
                :show-file-list="false"
                :on-success="(response, file, fileList) => handleAvatarSuccess(response, file, fileList, scope.row)"
                :before-upload="beforeAvatarUpload"
                :data="{'filename':scope.row.id + '.png','path':'/Signature/'}"
              >
                <img
                  v-if="1"
                  :src="url + '/common/downloadpath?fileName=' + scope.row.id + '.png&filePath=' + preurl +'/Signature/' + scope.row.id + '.png' + '?radomn=' + scope.row.imageUrl"
                  class="avatar"
                />
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
            </template>
          </el-table-column>
          <el-table-column label="激活" align="center" prop="activated">
            <template slot-scope="scope">
              <el-switch
                v-model="scope.row.activated"
                active-value="1"
                inactive-value="0"
                @change="updateActivated(scope.row)"
              ></el-switch>
            </template>
          </el-table-column>
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
      </el-col>
    </el-row>

    <!-- 添加或修改业务平台用户管理对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="open"
      width="600px"
      height="900px"
      :close-on-click-modal="false"
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="用户名" prop="name">
              <el-input v-model="form.name" placeholder="请输入用户名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="姓名" prop="fullname">
              <el-input v-model="form.fullname" placeholder="请输入姓名" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="密码" prop="password">
              <!-- <el-input v-model="form.password" placeholder="请输入密码" type="password" /> -->
              <template>
                <el-button type="warning" size="mini" @click="resetpassword">重置密码</el-button>
              </template>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="机构" prop="companyid">
              <dept-select-tree
                :pid="form.companyid"
                :id="form.companyid"
                :label="form.companyname"
                @selectterm="updatepSelectTreeValue"
                :type="'company'"
                :selectID="'companyid'"
                :selectName="'companyname'"
              ></dept-select-tree>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="部门" prop="deptid">
              <dept-select-tree
                :pid="form.deptid"
                :id="form.deptid"
                :label="form.deptname"
                @selectterm="updatepSelectTreeValue"
                :type="'dept'"
                :selectID="'deptid'"
                :selectName="'deptname'"
              ></dept-select-tree>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="排序码" prop="sortcode">
              <el-input-number
                v-model="form.sortcode"
                placeholder="请输入排序吗"
                controls-position="right"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="身份证" prop="idcard">
              <el-input v-model="form.idcard" placeholder="请输入身份证" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="手机号码" prop="phone">
              <el-input v-model="form.phone" placeholder="请输入手机号码" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="人员类型" prop="usertype">
              <el-select v-model="form.usertype" placeholder="请选择人员类型">
                <el-option
                  v-for="dict in usertypeOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="parseInt(dict.dictValue)"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="激活">
              <el-radio-group v-model="form.activated">
                <el-radio
                  v-for="dict in activatedOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue"
                >{{dict.dictLabel}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row v-show="showCarry">
          <el-col :span="12">
            <el-form-item label="携带待办">
              <el-radio-group v-model="carrywaitlist">
                <el-radio label="1">是</el-radio>
                <el-radio label="0">否</el-radio>
              </el-radio-group>
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
import { getToken } from "@/utils/auth";
import {
  listUser,
  getUser,
  delUser,
  addUser,
  updateUser,
  exportUser,
  resetPassword
} from "@/api/sjwflowsys/user";
import { listWorkflowtask } from "@/api/sjwflowbusiness/workflowtask";
import deptSelectTree from "@/views/sjwflowsys/dept/components/deptSelectTree";
import { getDeptTree } from "@/api/sjwflowsys/dept";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
export default {
  name: "User",
  components: { deptSelectTree },
  data() {
    //自定义密码校验规则
    var validatePassword = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        callback();
      }
    };
    return {
      //部门树
      deptList: [],
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
        fullname: undefined,
        usertype: undefined,
        deptid: undefined,
        companyid: undefined,
        pageNum: 1,
        pageSize: 30
      },
      deptQueryParams: {
        pid: "-1",
        type: "dept",
        selectType: "tree"
      },
      oldDeptID: "",
      carrywaitlist: "0",
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [{ required: true, message: "请输入用户名", trigger: "blur" }],
        fullname: [
          { required: true, message: "请输入真实姓名", trigger: "blur" }
        ],
        // password: [{ validator: validatePassword, trigger: "blur" }],
        companyid: [
          { required: true, message: "请选择组织机构", trigger: "blur" }
        ],
        deptid: [{ required: true, message: "请选择部门", trigger: "blur" }],
        usertype: [
          { required: true, message: "请选择人员类型", trigger: "blur" }
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
      tasksParams: {
        pageNum: 1,
        pageSize: 1,
        receiveid: undefined,
        status: 1,
        isdeleted: false
      },
      showCarry: false,
      url: process.env.VUE_APP_BASE_API,
      //图片地址
      uploadImgUrl: process.env.VUE_APP_BASE_API + "/common/sambUpload", // 上传的图片服务器地址
      headers: {
        Authorization: "Bearer " + getToken()
      },
      preurl: process.env.VUE_APP_FileServer_Path
    };
  },
  created() {
    this.getList();
    this.getDicts("sjwflow_user_usertype").then(response => {
      this.usertypeOptions = response.data;
    });
    this.getDicts("sjwflow_yes_no_num").then(response => {
      this.activatedOptions = response.data;
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
      this.ids = selection.map(item => item.id);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.form.activated = "1";
      this.form.isadmin = "0";
      this.form.deleted = false;
      this.open = true;
      this.title = "添加业务平台用户";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getUser(id).then(response => {
        this.form = response.data;
        this.carrywaitlist = "0";
        this.tasksParams.receiveid = this.form.id;
        this.oldDeptID = this.form.deptid;
        this.open = true;
        this.title = "修改业务平台用户";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            // 判断是否更换部门
            if (this.form.deptid != this.oldDeptID) {
              // 当前人员是否有待办事项
              this.haveWaitlist().then(data => {
                if (data) {
                  //判断是否携带待办事项
                  if (this.carrywaitlist == "1") {
                    this.updateUserMsg();
                  } else {
                    this.$confirm(
                      "当前用户存在未完成待办事项，若更换部门需要转交待办事项，是否进入转交界面？",
                      "提示"
                    ).then(() => {
                      //打开该用户的待办事项
                      this.open = false;
                      this.$router.push({
                        path: "/sjwflowbusiness/workflowtask",
                        query: { receiveid: this.form.id }
                      });
                    });
                  }
                } else {
                  // 无待办事项则更换承办部门
                  this.updateUserMsg();
                }
              });
            } else {
              this.updateUserMsg();
            }
          } else {
            addUser(this.form, this.carrywaitlist).then(response => {
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
    updateUserMsg() {
      updateUser(this.form, this.carrywaitlist).then(response => {
        if (response.code === 200) {
          this.msgSuccess("修改成功");
          this.open = false;
          this.getList();
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm(
        '是否确认删除业务平台用户管理编号为"' + ids + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(function() {
          return delUser(ids);
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
      this.$confirm("是否确认导出所有业务平台用户管理数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function() {
          return exportUser(queryParams);
        })
        .then(response => {
          this.download(response.msg);
        })
        .catch(function() {});
    },
    /** 懒加载树 */
    loadNode(node, resolve) {
      this.deptQueryParams.pid = node.data.id;
      this.deptQueryParams.selectType = "tree";
      getDeptTree(this.deptQueryParams).then(response => {
        resolve(response.data);
      });
    },
    /** 部门节点点击事件 */
    refreshUserList(data) {
      (data.category == "单位" && [
        (this.queryParams.companyid = data.id),
        (this.queryParams.deptid = undefined)
      ]) || [(this.queryParams.deptid = data.id)];
      listUser(this.queryParams).then(response => {
        this.userList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 更新激活状态 */
    updateActivated(data) {
      updateUser(data).then(response => {
        if (response.code === 200) {
          this.msgSuccess("修改成功");
        }
      });
    },
    /** 同步下拉树数据 */
    updatepSelectTreeValue(node, id, label) {
      this.form[id] = node.id;
      label != undefined && [(this.form[label] = node.label)];

      if ((this.id = "deptid")) {
        //携带待办
        if (this.form.deptid != this.oldDeptID) {
          this.showCarry = true;
        } else {
          this.showCarry = false;
        }
      }
    },
    /** 重置密码 */
    resetpassword() {
      this.$confirm("是否确认重置密码？", "提示", {}).then(() => {
        resetPassword(this.form.id).then(response => {
          if (response.code === 200) {
            this.msgSuccess("密码已重置");
          }
        });
      });
    },
    /** 查询该员是否有待办事项 */
    haveWaitlist() {
      return new Promise((resolve, reject) => {
        listWorkflowtask(this.tasksParams).then(response => {
          if (response.total > 0) {
            resolve(true);
          } else {
            resolve(false);
          }
        });
      });
    },
    /** 附件上传成功事件 */
    handleAvatarSuccess(res, file, filelist, row) {
      row.imageUrl = Math.random();
    },
    /** 签名上传前事件 */
    beforeAvatarUpload() {}
  }
};
</script>
<style scoped>
.el-tree-node__label {
  font-size: 17px;
  font-weight: bold;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 8px;
  color: #8c939d;
  width: 80px;
  height: 40px;
  line-height: 40px;
  text-align: center;
}
.avatar {
  width: 80px;
  height: 40px;
  display: block;
}
</style>