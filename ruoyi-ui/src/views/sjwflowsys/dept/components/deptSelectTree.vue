<template>
  <treeselect
    v-model="value"
    :options="deptOptions"
    :load-options="loadOptions"
    placeholder="请选择"
    @select="changeValue"
    @input="changeInput"
    :disable-branch-nodes="queryType"
  />
</template>
<script>
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import Treeselect from "@riophae/vue-treeselect";
import { LOAD_CHILDREN_OPTIONS } from "@riophae/vue-treeselect";
import {
  listDept,
  getDeptTree,
  getDeptTreebyNode
} from "@/api/sjwflowsys/dept";
export default {
  components: { Treeselect },
  props: ["pid", "label", "type", "selectID", "selectName", "id"],
  data() {
    return {
      initvalue: this.pid,
      value: this.pid,
      sellabel: undefined,
      deptOptions: [],
      queryType: this.type == "user" ? false : true,
      queryParams: {
        pid: undefined,
        type: this.type,
        selectType: "select",
        childID: this.id
      },
      childinit: {}
    };
  },
  created() {
    this.getTreeselect();
  },
  watch: {
    id(val) {
      if (!!val) {
        this.queryParams.childID = val;
        this.getTreeselect();
      }
    },
    pid(val) {
      if (!!val) {
        this.value = val;
      }
    }
  },
  methods: {
    getTreeselect() {
      if (!!this.value) {
        getDeptTreebyNode(this.queryParams).then(response => {
          this.deptOptions = response.data;
        });
      } else {
        this.queryParams.pid = "-1";
        listDept(this.queryParams).then(response => {
          let pNode = response.data[0];
          this.deptOptions = [
            { id: pNode.id, label: pNode.name, children: null }
          ];
        });
      }
    },
    loadOptions({ action, parentNode, callback }) {
      if (action === LOAD_CHILDREN_OPTIONS) {
        this.queryParams.pid = parentNode.id;
        getDeptTree(this.queryParams).then(response => {
          let resData = response.data;
          let arr = [];
          resData.forEach(item => {
            let objData = {};
            objData.id = item.id;
            objData.label = item.name;
            objData.children = null;
            if (item.hasChildren === false) {
              delete objData.children; //有无子节点判断，树节点前面是否有箭头问题
            }
            arr.push(objData);
          });
          parentNode.children = arr;
          callback();
        });
      }
    },
    //input框监听输入值
    changeValue(node) {
      //如果值不为空传值过去
      if (node != undefined) {
        this.value = node.id;
        this.$emit("selectterm", node, this.selectID, this.selectName);
      }
      //为空则传空值
      else {
        this.$emit("selectterm", null);
      }
    },
    changeInput(val) {
      if (val == undefined || val == "") {
        this.$emit("selectterm", null);
      }
    }
  }
};
</script>