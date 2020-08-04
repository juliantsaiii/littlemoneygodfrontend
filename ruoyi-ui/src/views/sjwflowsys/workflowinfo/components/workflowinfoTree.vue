<template>
  <el-tree :data="infotree" :props="props" @node-click="treeClick"></el-tree>
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
      },
      infotree: [],
    };
  },
  created() {
    this.initTree();
  },
  methods: {
    initTree() {
      getWorkflowinfoTree().then((response) => {
        this.infotree = response.data;
      });
    },
    treeClick(data) {
      this.$emit("changevalue", data.parentId, data.remark);
    },
  },
};
</script>

<style>
</style>