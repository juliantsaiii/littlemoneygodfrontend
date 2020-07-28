<template>
  <treeselect
    v-model="value"
    :options="deptOptions"
    :load-options="loadOptions"
    placeholder="请选择父级ID"
    @select="changeValue"
    :disable-branch-nodes="queryType"
  />
</template>
<script>
import Treeselect from "@riophae/vue-treeselect";
import { LOAD_CHILDREN_OPTIONS } from "@riophae/vue-treeselect";
import { listDept, getDeptTree } from "@/api/sjwflowsys/dept";
export default {
  components: { Treeselect },
  props: ["pid", "label", "type", "selectID", "selectName"],
  data() {
    return {
      value: this.pid,
      sellabel: this.label,
      deptOptions: [],
      queryType: this.type == "dept" ? false : true,
      queryParams: { pid: undefined, type: this.type, selectTree: "select" },
      childinit: { id: this.pid, label: this.label }
    };
  },
  created() {
    this.getTreeselect();
  },
  watch: {
    /** 更换默认值 */
    pid(val) {
      this.value = val;
      this.deptOptions.splice(0, 1, { id: val, label: this.label });
      console.log(val, this.label);
    }
  },
  methods: {
    getTreeselect() {
      listDept().then(response => {
        this.queryParams.pid = "-1";
        listDept(this.queryParams).then(response => {
          let pNode = response.data[0];
          this.deptOptions = [
            this.childinit,
            { id: pNode.id, label: pNode.name, children: null }
          ];
        });
      });
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
    changeValue(node) {
      this.value = node.id;
      this.$emit("selectterm", node, this.selectID, this.selectName);
    }
  }
};
</script>