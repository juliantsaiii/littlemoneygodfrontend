<template>
  <el-tree :data="infotree" :props="props" @node-click="treeClick">
    <span slot-scope="{ node, data }">
      <i :class="data.icon"></i>
      <span style="padding-left: 4px;">{{node.label}}</span>
    </span>
  </el-tree>
</template>

<script>
import { getWorkflowinfoTree } from "@/api/sjwflowsys/workflowinfo";
export default {
  name: "workflowinfotree",
  data() {
    return {
      props: {
        children: "children",
        label: "parentName",
        icon: "icon"
      },
      infotree: []
    };
  },
  created() {
    this.initTree();
  },
  methods: {
    initTree() {
      getWorkflowinfoTree().then(response => {
        this.infotree = response.data;
      });
    },
    treeClick(data) {
      this.$emit("changevalue", data.parentId, data.remark);
    }
  }
};
</script>

<style>
</style>