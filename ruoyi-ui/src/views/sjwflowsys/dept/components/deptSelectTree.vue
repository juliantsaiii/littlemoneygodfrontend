<template>
<treeselect v-model="value" :options="deptOptions" :load-options="loadOptions" placeholder="请选择父级ID" />
</template>
<script>
import Treeselect from "@riophae/vue-treeselect";
import { LOAD_CHILDREN_OPTIONS } from '@riophae/vue-treeselect'
import {listDept}  from "@/api/sjwflowsys/dept";
export default {
    components: { Treeselect },
    props: ['pid'],
     model: {
        prop: 'pid',
        event: 'selectterm'
    },
    data(){
        return {
            value:this.pid,
            deptOptions: [],
            queryParams:{pid: undefined},
        }
    },
    watch: {
            value: function (newval) {
                this.$emit('selectterm',newval);
            }
        },
    created() {
        this.getTreeselect();
    }, 
    methods:{
        getTreeselect(){
            listDept().then(response => {
                this.queryParams.pid = "-1";
                listDept(this.queryParams).then(response=>{
                let pNode = response.data[0];
                this.deptOptions = [{ id: pNode.id, label: pNode.name,children: null }];
                })
            });
        }, 
        loadOptions({ action, parentNode, callback }) {
        // Typically, do the AJAX stuff here.
        // Once the server has responded,
        // assign children options to the parent node & call the callback.
        if (action === LOAD_CHILDREN_OPTIONS) {
            this.queryParams.pid=parentNode.id;
            listDept(this.queryParams).then(response=>{
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
            })
        }
    },
  }
}
</script>