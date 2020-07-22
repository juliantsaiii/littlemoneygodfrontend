<template>
  <div>
    <el-row>
      <el-form
        ref="problem"
        :model="form"
        :rules="rules"
        size="medium"
        label-width="150px"
        style="height:500px;"
      >
        <el-col :span="12">
          <el-row>
            <el-col :span="24">
              <el-form-item label="填写日期" prop="createdate">
                <el-date-picker
                  type="datetime"
                  v-model="form.createdate"
                  format="yyyy/MM/dd HH:mm:ss"
                  value-format="yyyy/MM/dd HH:mm:ss"
                  :style="{width: '100%'}"
                  placeholder="请输入填写日期"
                ></el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
        </el-col>
        <el-col :span="12">
          <el-row>
            <el-col :span="24">
              <el-form-item label="处理人" prop="createusername">
                <el-input
                  v-model="form.createusername"
                  placeholder="请输入处理人"
                  readonly
                  :style="{width: '100%'}"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-col>
        <el-col :span="24">
          <el-row>
            <el-col :span="24">
              <el-form-item label="服务类型" prop="servicetype">
                <el-select
                  v-model="form.servicetype"
                  placeholder="请选择服务类型"
                  clearable
                  :style="{width: '100%'}"
                >
                  <el-option
                    v-for="(item, index) in ServicetypeOptions"
                    :key="index"
                    :label="item.dictLabel"
                    :value="item.dictValue"
                    :disabled="item.disabled"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
        </el-col>
        <el-col :span="24">
          <el-row>
            <el-col :span="24">
              <el-form-item label="线索编号/信访编号" prop="cluecode">
                <el-input
                  v-model="form.cluecode"
                  placeholder="请输入线索编号/信访编号"
                  clearable
                  :style="{width: '100%'}"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-col :span="24">
            <el-row>
              <el-col :span="8">
                <el-form-item label="联系人地区" prop="parea">
                  <el-select
                    v-model="form.parea"
                    placeholder="请选择联系人地区"
                    clearable
                    :style="{width: '100%'}"
                  >
                    <el-option
                      v-for="(item, index) in PAreaOptions"
                      :key="index"
                      :label="item.dictLabel"
                      :value="item.dictValue"
                      :disabled="item.disabled"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="所在机关" prop="poffice">
                  <el-input
                    v-model="form.poffice"
                    placeholder="请输入所在机关"
                    clearable
                    :style="{width: '100%'}"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="所在科室" prop="pclass">
                  <el-input
                    v-model="form.pclass"
                    placeholder="请输入所在科室"
                    clearable
                    :style="{width: '100%'}"
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
          </el-col>
        </el-col>
        <el-col :span="24">
          <el-row>
            <el-col :span="8">
              <el-form-item label="联系人" prop="pname">
                <el-input
                  v-model="form.pname"
                  placeholder="请输入联系人"
                  clearable
                  :style="{width: '100%'}"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="联系方式" prop="pphone">
                <el-input
                  v-model="form.pphone"
                  placeholder="请输入联系方式"
                  clearable
                  :style="{width: '100%'}"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-col>
        <el-col :span="24">
          <el-row>
            <el-col :span="8">
              <el-form-item label="处理方式" prop="handletype">
                <el-select
                  v-model="form.handletype"
                  placeholder="请选择处理方式"
                  clearable
                  :style="{width: '100%'}"
                >
                  <el-option
                    v-for="(item, index) in HandleTypeOptions"
                    :key="index"
                    :label="item.dictLabel"
                    :value="item.dictValue"
                    :disabled="item.disabled"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="处理状态" prop="handlestate">
                <el-select
                  v-model="form.handlestate"
                  placeholder="请选择处理状态"
                  clearable
                  :style="{width: '100%'}"
                >
                  <el-option
                    v-for="(item, index) in HandleStateOptions"
                    :key="index"
                    :label="item.dictLabel"
                    :value="item.dictValue"
                    :disabled="item.disabled"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="是否上报" prop="isup">
                <el-select
                  v-model="form.isup"
                  placeholder="请选择是否上报"
                  clearable
                  :style="{width: '100%'}"
                >
                  <el-option
                    v-for="(item, index) in IsUpOptions"
                    :key="index"
                    :label="item.dictLabel"
                    :value="item.dictValue"
                    :disabled="item.disabled"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
        </el-col>
        <el-col :span="24">
          <el-row>
            <el-form-item label="问题描述">
              <Editor v-model="form.problem" />
            </el-form-item>
          </el-row>
        </el-col>
        <el-col :span="24" style="margin-top:50px;  text-align: center;">
          <el-button type="primary" @click="submitForm">提交</el-button>
          <el-button @click="reset">重置</el-button>
        </el-col>
      </el-form>
    </el-row>
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
import Editor from "@/components/Editor";
import { getUserProfile } from "@/api/system/user";
export default {
  name: "probblem",
  components: { Editor },
  props: ["problemid"],
  data() {
    return {
      form: {},
      rules: {
        createdate: [],
        createusername: [],
        servicetype: [
          {
            required: true,
            message: "请选择服务类型",
            trigger: "change"
          }
        ],
        parea: [
          {
            required: true,
            message: "请选择联系人地区",
            trigger: "change"
          }
        ],
        poffice: [
          {
            required: true,
            message: "请输入所在机关",
            trigger: "blur"
          }
        ],
        pclass: [
          {
            required: true,
            message: "请输入所在科室",
            trigger: "blur"
          }
        ],
        cluecode: [],
        pname: [
          {
            required: true,
            message: "请输入联系人",
            trigger: "blur"
          }
        ],
        pphone: [],
        handletype: [
          {
            required: true,
            message: "请选择处理方式",
            trigger: "change"
          }
        ],
        handlestate: [
          {
            required: true,
            message: "请选择处理状态",
            trigger: "change"
          }
        ],
        isup: [
          {
            required: true,
            message: "请选择是否上报",
            trigger: "change"
          }
        ]
      },
      ServicetypeOptions: [],
      PAreaOptions: [],
      HandleTypeOptions: [],
      HandleStateOptions: [],
      IsUpOptions: [],
      user: {},
      id: this.problemid
    };
  },
  computed: {},
  watch: {
    problemid(val) {
      this.id = val;
      this.getproblem();
    }
  },
  created() {
    this.getUser();
    this.getproblem();
    this.getDicts("sjwflow_area").then(response => {
      this.PAreaOptions = response.data;
    });
    this.getDicts("sjwflow_yes_no").then(response => {
      this.IsUpOptions = response.data;
    });
    this.getDicts("operation_handletype").then(response => {
      this.HandleTypeOptions = response.data;
    });
    this.getDicts("operation_handlestate").then(response => {
      this.HandleStateOptions = response.data;
    });
    this.getDicts("operation_type").then(response => {
      this.ServicetypeOptions = response.data;
    });
  },
  mounted() {},
  methods: {
    submitForm() {
      this.$refs["problem"].validate(valid => {
        if (valid) {
          this.$emit("addproblem", this.form);
        }
      });
    },
    //处理默认选中当前日期
    getNowTime() {
      var now = new Date();
      var year = now.getFullYear(); //得到年份
      var month = now.getMonth(); //得到月份
      var date = now.getDate(); //得到日期
      var hour = now.getHours();
      var minute = now.getMinutes();
      var second = now.getSeconds();
      month = month + 1;
      month = month.toString().padStart(2, "0");
      date = date.toString().padStart(2, "0");
      hour = hour.toString().padStart(2, "0");
      minute = minute.toString().padStart(2, "0");
      second = second.toString().padStart(2, "0");
      var defaultDate = `${year}/${month}/${date} ${hour}:${minute}:${second}`;
      return defaultDate;
    },
    getUser() {
      getUserProfile().then(response => {
        this.form.createusername = response.data.userName;
        this.form.createuserid = response.data.id;
        this.user = response.data;
        console.log(this.user);
      });
    },
    getproblem() {
      if (this.id != "") {
        getProblemlist(this.id).then(response => {
          this.form = response.data;
        });
      } else {
        this.reset();
      }
    },
    // 表单重置
    reset() {
      console.log(this.user);
      this.problemid = "";
      this.resetForm("problem");
      this.form = {
        id: undefined,
        mainid: undefined,
        createuserid: this.user.userId,
        createusername: this.user.userName,
        createdeptid: undefined,
        createdeptname: undefined,
        number: undefined,
        createdate: this.getNowTime(),
        parea: undefined,
        poffice: undefined,
        pclass: undefined,
        pname: undefined,
        pphone: undefined,
        servicetype: undefined,
        problem: undefined,
        cluecode: undefined,
        handletype: undefined,
        handlestate: undefined,
        isup: undefined,
        handlestaff: undefined,
        currentstep: undefined,
        status: "0",
        timestamp: undefined,
        isdeleted: false
      };
    }
  }
};
</script>
<style scoped>
</style>
