<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="线索ID" prop="id">
        <el-input
          v-model="queryParams.id"
          placeholder="请输入clueid"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="单位" prop="unitname">
        <el-input
          v-model="queryParams.unitname"
          placeholder="请输入单位"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="线索编号" prop="cluecode">
        <el-input
          v-model="queryParams.cluecode"
          placeholder="请输入线索编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="姓名" prop="personname">
        <el-input
          v-model="queryParams.personname"
          placeholder="请输入被反映人姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="职务" prop="personunit">
        <el-input
          v-model="queryParams.personunit"
          placeholder="请输入工作职务"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="线索来源" prop="cluesource">
        <el-input
          v-model="queryParams.cluesource"
          placeholder="请输入线索来源"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="信访编号" prop="clueno">
        <el-input
          v-model="queryParams.clueno"
          placeholder="请输入信访编号"
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

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['sjwflowbusiness:tempclueinfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['sjwflowbusiness:tempclueinfo:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table
      v-loading="loading"
      :data="tempclueinfoList"
      @selection-change="handleSelectionChange"
      :max-height="tableHeight"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" :show-overflow-tooltip="true" />
      <el-table-column label="姓名" align="center" prop="personname" />
      <el-table-column label="单位" align="center" prop="unitname" :show-overflow-tooltip="true" />
      <el-table-column label="职务" align="center" prop="personunit" :show-overflow-tooltip="true" />
      <el-table-column label="职级" align="center" prop="personlevel" :show-overflow-tooltip="true" />
      <el-table-column label="编号" align="center" prop="cluecode" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <span>{{scope.row.cluecode}}/{{scope.row.clueno}}</span>
        </template>
      </el-table-column>
      <el-table-column label="填报日期" align="center" prop="reportdate" />
      <el-table-column label="线索来源" align="center" prop="cluesource" :show-overflow-tooltip="true" />
      <el-table-column
        label="承办部门"
        align="center"
        prop="basehandledept"
        :show-overflow-tooltip="true"
      />
      <el-table-column label="状态" align="center" prop="handlestate" />
      <el-table-column label="是否重复件" align="center" prop="isrepeated" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <span>{{scope.row.isrepeated?scope.row.isrepeated:'否'}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="是否添加多人件"
        align="center"
        prop="addpeoplemainid"
        :show-overflow-tooltip="true"
      />

      <el-table-column label="是否删除" align="center">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.isdeleted" @change="updateform(scope.row)"></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="是否措施线索">
        <template slot-scope="scope">
          <span>{{scope.row.ismeasureclue?'是':'否'}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
        fixed="right"
        :min-width="200"
      >
        <template slot-scope="scope">
          <!-- <router-link :to="'/sjwflowbusiness/workflowtask/'" class="link-type"> -->
          <router-link
            :to="{path:'/sjwflowbusiness/workflowtask/',query: {id: scope.row.id}}"
            class="link-type"
          >
            <el-button size="mini" type="text" icon="el-icon-edit">步骤</el-button>
          </router-link>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['sjwflowbusiness:tempclueinfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['sjwflowbusiness:tempclueinfo:remove']"
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

    <!-- 添加或修改线索操作对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="创建者" prop="createuserid">
          <el-input v-model="form.createuserid" placeholder="请输入创建者" />
        </el-form-item>
        <el-form-item label="创建者" prop="createdeptid">
          <el-input v-model="form.createdeptid" placeholder="请输入创建者" />
        </el-form-item>
        <el-form-item label="创建者" prop="userid">
          <el-input v-model="form.userid" placeholder="请输入创建者" />
        </el-form-item>
        <el-form-item label="创建者部门id" prop="deptid">
          <el-input v-model="form.deptid" placeholder="请输入创建者部门id" />
        </el-form-item>
        <el-form-item label="创建者部门id" prop="attachmentid">
          <el-input v-model="form.attachmentid" placeholder="请输入创建者部门id" />
        </el-form-item>
        <el-form-item label="创建者部门id" prop="handledept">
          <el-input v-model="form.handledept" placeholder="请输入创建者部门id" />
        </el-form-item>
        <el-form-item label="创建者部门id" prop="handledeptid">
          <el-input v-model="form.handledeptid" placeholder="请输入创建者部门id" />
        </el-form-item>
        <el-form-item label="创建者部门id" prop="handlestaff">
          <el-input v-model="form.handlestaff" placeholder="请输入创建者部门id" />
        </el-form-item>
        <el-form-item label="创建者部门id" prop="affix">
          <el-input v-model="form.affix" placeholder="请输入创建者部门id" />
        </el-form-item>
        <el-form-item label="线索状态" prop="state">
          <el-input v-model="form.state" placeholder="请输入线索状态" />
        </el-form-item>
        <el-form-item label="单位代码" prop="unitcode">
          <el-input v-model="form.unitcode" placeholder="请输入单位代码" />
        </el-form-item>
        <el-form-item label="单位名称" prop="unitname">
          <el-input v-model="form.unitname" placeholder="请输入单位名称" />
        </el-form-item>
        <el-form-item label="填报日期" prop="reportdate">
          <el-input v-model="form.reportdate" placeholder="请输入填报日期" />
        </el-form-item>
        <el-form-item label="案件4.0系统ID编号" prop="fourformno">
          <el-input v-model="form.fourformno" placeholder="请输入案件4.0系统ID编号" />
        </el-form-item>
        <el-form-item label="线索编码" prop="cluecode">
          <el-input v-model="form.cluecode" placeholder="请输入线索编码" />
        </el-form-item>
        <el-form-item label="人员编号" prop="personcode">
          <el-input v-model="form.personcode" placeholder="请输入人员编号" />
        </el-form-item>
        <el-form-item label="反映单位或个人" prop="isunit">
          <el-input v-model="form.isunit" placeholder="请输入反映单位或个人" />
        </el-form-item>
        <el-form-item label="反映单位或个人" prop="personid">
          <el-input v-model="form.personid" placeholder="请输入反映单位或个人" />
        </el-form-item>
        <el-form-item label="被反映人" prop="personname">
          <el-input v-model="form.personname" placeholder="请输入被反映人" />
        </el-form-item>
        <el-form-item label="被反映人" prop="personidcard">
          <el-input v-model="form.personidcard" placeholder="请输入被反映人" />
        </el-form-item>
        <el-form-item label="出生年月" prop="birthdate">
          <el-input v-model="form.birthdate" placeholder="请输入出生年月" />
        </el-form-item>
        <el-form-item label="民族" prop="nation">
          <el-input v-model="form.nation" placeholder="请输入民族" />
        </el-form-item>
        <el-form-item label="政治面貌" prop="politic">
          <el-input v-model="form.politic" placeholder="请输入政治面貌" />
        </el-form-item>
        <el-form-item label="政治面貌细节" prop="politicdetail">
          <el-input v-model="form.politicdetail" placeholder="请输入政治面貌细节" />
        </el-form-item>
        <el-form-item label="入党时间" prop="partytime">
          <el-input v-model="form.partytime" placeholder="请输入入党时间" />
        </el-form-item>
        <el-form-item label="职级" prop="personlevel">
          <el-input v-model="form.personlevel" placeholder="请输入职级" />
        </el-form-item>
        <el-form-item label="职级" prop="personlevel2">
          <el-input v-model="form.personlevel2" placeholder="请输入职级" />
        </el-form-item>
        <el-form-item label="干部管理权限 中管干部 省管干部 市管干部 县管干部" prop="secondlevel">
          <el-input v-model="form.secondlevel" placeholder="请输入干部管理权限 中管干部 省管干部 市管干部 县管干部" />
        </el-form-item>
        <el-form-item label="职务" prop="personduty">
          <el-input v-model="form.personduty" placeholder="请输入职务" />
        </el-form-item>
        <el-form-item label="工作职务" prop="personunit">
          <el-input v-model="form.personunit" placeholder="请输入工作职务" />
        </el-form-item>
        <el-form-item label="反映人身份证" prop="idcard">
          <el-input v-model="form.idcard" placeholder="请输入反映人身份证" />
        </el-form-item>
        <el-form-item label="人大代表" prop="isdeputy">
          <el-input v-model="form.isdeputy" placeholder="请输入人大代表" />
        </el-form-item>
        <el-form-item label="政协委员" prop="iscppcc">
          <el-input v-model="form.iscppcc" placeholder="请输入政协委员" />
        </el-form-item>
        <el-form-item label="是否党代表" prop="isddb">
          <el-input v-model="form.isddb" placeholder="请输入是否党代表" />
        </el-form-item>
        <el-form-item label="是否监察对象" prop="issupervision">
          <el-input v-model="form.issupervision" placeholder="请输入是否监察对象" />
        </el-form-item>
        <el-form-item label="是否国家公务员" prop="iscivilservant">
          <el-input v-model="form.iscivilservant" placeholder="请输入是否国家公务员" />
        </el-form-item>
        <el-form-item label="线索来源" prop="cluesource">
          <el-input v-model="form.cluesource" placeholder="请输入线索来源" />
        </el-form-item>
        <el-form-item label="违纪行为" prop="illegalbehavior">
          <el-input v-model="form.illegalbehavior" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="违法行为" prop="lawbehaviour">
          <el-input v-model="form.lawbehaviour" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="办理机关" prop="handleunit">
          <el-input v-model="form.handleunit" placeholder="请输入办理机关" />
        </el-form-item>
        <el-form-item label="受理时间" prop="accepttime">
          <el-input v-model="form.accepttime" placeholder="请输入受理时间" />
        </el-form-item>
        <el-form-item label="处置方式1批准时间" prop="handle1time">
          <el-input v-model="form.handle1time" placeholder="请输入处置方式1批准时间" />
        </el-form-item>
        <el-form-item label="处置方式1统计时间" prop="handle1censustime">
          <el-input v-model="form.handle1censustime" placeholder="请输入处置方式1统计时间" />
        </el-form-item>
        <el-form-item label="处置方式1一级" prop="handletype1first">
          <el-input v-model="form.handletype1first" placeholder="请输入处置方式1一级" />
        </el-form-item>
        <el-form-item label="处置方式1二级" prop="handletype1second">
          <el-input v-model="form.handletype1second" placeholder="请输入处置方式1二级" />
        </el-form-item>
        <el-form-item label="处置方式2批准时间" prop="handle2time">
          <el-input v-model="form.handle2time" placeholder="请输入处置方式2批准时间" />
        </el-form-item>
        <el-form-item label="处置方式2统计时间" prop="handle2censustime">
          <el-input v-model="form.handle2censustime" placeholder="请输入处置方式2统计时间" />
        </el-form-item>
        <el-form-item label="处置方式2一级" prop="handletype2first">
          <el-input v-model="form.handletype2first" placeholder="请输入处置方式2一级" />
        </el-form-item>
        <el-form-item label="处置方式2二级" prop="handletype2second">
          <el-input v-model="form.handletype2second" placeholder="请输入处置方式2二级" />
        </el-form-item>
        <el-form-item label="处置方式3批准时间" prop="handle3time">
          <el-input v-model="form.handle3time" placeholder="请输入处置方式3批准时间" />
        </el-form-item>
        <el-form-item label="处置方式3统计时间" prop="handle3censustime">
          <el-input v-model="form.handle3censustime" placeholder="请输入处置方式3统计时间" />
        </el-form-item>
        <el-form-item label="处置方式3一级" prop="handletype3first">
          <el-input v-model="form.handletype3first" placeholder="请输入处置方式3一级" />
        </el-form-item>
        <el-form-item label="处置方式3二级" prop="handletype3second">
          <el-input v-model="form.handletype3second" placeholder="请输入处置方式3二级" />
        </el-form-item>
        <el-form-item label="处置方式4批准时间" prop="handle4time">
          <el-input v-model="form.handle4time" placeholder="请输入处置方式4批准时间" />
        </el-form-item>
        <el-form-item label="处置方式4统计时间" prop="handle4censusttime">
          <el-input v-model="form.handle4censusttime" placeholder="请输入处置方式4统计时间" />
        </el-form-item>
        <el-form-item label="处置方式4一级" prop="handletype4first">
          <el-input v-model="form.handletype4first" placeholder="请输入处置方式4一级" />
        </el-form-item>
        <el-form-item label="处置方式4二级" prop="handletype4second">
          <el-input v-model="form.handletype4second" placeholder="请输入处置方式4二级" />
        </el-form-item>
        <el-form-item label="处置方式5批准时间" prop="handle5time">
          <el-input v-model="form.handle5time" placeholder="请输入处置方式5批准时间" />
        </el-form-item>
        <el-form-item label="处置方式5统计时间" prop="handle5census">
          <el-input v-model="form.handle5census" placeholder="请输入处置方式5统计时间" />
        </el-form-item>
        <el-form-item label="处置方式5一级" prop="handletype5first">
          <el-input v-model="form.handletype5first" placeholder="请输入处置方式5一级" />
        </el-form-item>
        <el-form-item label="处置方式5二级" prop="handletype5second">
          <el-input v-model="form.handletype5second" placeholder="请输入处置方式5二级" />
        </el-form-item>
        <el-form-item label="处置方式6批准时间" prop="handle6time">
          <el-input v-model="form.handle6time" placeholder="请输入处置方式6批准时间" />
        </el-form-item>
        <el-form-item label="处置方式6统计时间" prop="handle6censustime">
          <el-input v-model="form.handle6censustime" placeholder="请输入处置方式6统计时间" />
        </el-form-item>
        <el-form-item label="处置方式6一级" prop="handletype6first">
          <el-input v-model="form.handletype6first" placeholder="请输入处置方式6一级" />
        </el-form-item>
        <el-form-item label="处置方式6二级" prop="handletype6second">
          <el-input v-model="form.handletype6second" placeholder="请输入处置方式6二级" />
        </el-form-item>
        <el-form-item label="处置方式7批准时间" prop="handle7time">
          <el-input v-model="form.handle7time" placeholder="请输入处置方式7批准时间" />
        </el-form-item>
        <el-form-item label="处置方式7统计时间" prop="handle7censustime">
          <el-input v-model="form.handle7censustime" placeholder="请输入处置方式7统计时间" />
        </el-form-item>
        <el-form-item label="处置方式7一级" prop="handletype7first">
          <el-input v-model="form.handletype7first" placeholder="请输入处置方式7一级" />
        </el-form-item>
        <el-form-item label="处置方式7二级" prop="handletype7second">
          <el-input v-model="form.handletype7second" placeholder="请输入处置方式7二级" />
        </el-form-item>
        <el-form-item label="处置方式8批准时间" prop="handle8time">
          <el-input v-model="form.handle8time" placeholder="请输入处置方式8批准时间" />
        </el-form-item>
        <el-form-item label="处置方式8统计时间" prop="handle8censustime">
          <el-input v-model="form.handle8censustime" placeholder="请输入处置方式8统计时间" />
        </el-form-item>
        <el-form-item label="处置方式8一级" prop="handletype8first">
          <el-input v-model="form.handletype8first" placeholder="请输入处置方式8一级" />
        </el-form-item>
        <el-form-item label="处置方式8二级" prop="handletype8second">
          <el-input v-model="form.handletype8second" placeholder="请输入处置方式8二级" />
        </el-form-item>
        <el-form-item label="组织措施情况" prop="orgmeasure">
          <el-input v-model="form.orgmeasure" placeholder="请输入组织措施情况" />
        </el-form-item>
        <el-form-item label="落实时间" prop="implementtime">
          <el-input v-model="form.implementtime" placeholder="请输入落实时间" />
        </el-form-item>
        <el-form-item label="是否与本人核实" prop="ischecked">
          <el-input v-model="form.ischecked" placeholder="请输入是否与本人核实" />
        </el-form-item>
        <el-form-item label="是否主动交代问题" prop="iscomeclean">
          <el-input v-model="form.iscomeclean" placeholder="请输入是否主动交代问题" />
        </el-form-item>
        <el-form-item label="挽回经济损失" prop="retrieveeconomicloss">
          <el-input v-model="form.retrieveeconomicloss" placeholder="请输入挽回经济损失" />
        </el-form-item>
        <el-form-item label="收缴涉案金额" prop="captureassetmoney">
          <el-input v-model="form.captureassetmoney" placeholder="请输入收缴涉案金额" />
        </el-form-item>
        <el-form-item label="没收金额6.0" prop="confiscatemoney">
          <el-input v-model="form.confiscatemoney" placeholder="请输入没收金额6.0" />
        </el-form-item>
        <el-form-item label="责令赔偿金额6.0" prop="compensatemoney">
          <el-input v-model="form.compensatemoney" placeholder="请输入责令赔偿金额6.0" />
        </el-form-item>
        <el-form-item label="登记上交金额6.0" prop="handinmoney">
          <el-input v-model="form.handinmoney" placeholder="请输入登记上交金额6.0" />
        </el-form-item>
        <el-form-item label="追缴失职渎职滥用职权造成的损失金额6.0" prop="lossmoney">
          <el-input v-model="form.lossmoney" placeholder="请输入追缴失职渎职滥用职权造成的损失金额6.0" />
        </el-form-item>
        <el-form-item label="主要问题线索" prop="mainclue">
          <el-input v-model="form.mainclue" placeholder="请输入主要问题线索" />
        </el-form-item>
        <el-form-item label="处置情况报告" prop="handlereport">
          <el-input v-model="form.handlereport" placeholder="请输入处置情况报告" />
        </el-form-item>
        <el-form-item label="备注" prop="handleremark">
          <el-input v-model="form.handleremark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="是否重复件" prop="isrepeated">
          <el-input v-model="form.isrepeated" placeholder="请输入是否重复件" />
        </el-form-item>
        <el-form-item label="填表人" prop="reportstaff">
          <el-input v-model="form.reportstaff" placeholder="请输入填表人" />
        </el-form-item>
        <el-form-item label="审核人" prop="checkstaff">
          <el-input v-model="form.checkstaff" placeholder="请输入审核人" />
        </el-form-item>
        <el-form-item label="审核人">
          <el-radio-group v-model="form.isdeleted">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="审核人" prop="timestamp">
          <el-date-picker
            clearable
            size="small"
            style="width: 200px"
            v-model="form.timestamp"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择审核人"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="反映人姓名" prop="reflectname">
          <el-input v-model="form.reflectname" placeholder="请输入反映人姓名" />
        </el-form-item>
        <el-form-item label="反映人单位" prop="reflectunit">
          <el-input v-model="form.reflectunit" placeholder="请输入反映人单位" />
        </el-form-item>
        <el-form-item label="反映人职务" prop="reflectpost">
          <el-input v-model="form.reflectpost" placeholder="请输入反映人职务" />
        </el-form-item>
        <el-form-item label="反映人联系方式" prop="reflectphone">
          <el-input v-model="form.reflectphone" placeholder="请输入反映人联系方式" />
        </el-form-item>
        <el-form-item label="反映人身份证号码" prop="reflectidcard">
          <el-input v-model="form.reflectidcard" placeholder="请输入反映人身份证号码" />
        </el-form-item>
        <el-form-item label="性别">
          <el-select v-model="form.personsex" placeholder="请选择性别">
            <el-option label="请选择字典生成" value />
          </el-select>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="form.status">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="函号信访编号" prop="clueno">
          <el-input v-model="form.clueno" placeholder="请输入函号信访编号" />
        </el-form-item>
        <el-form-item label="文化水平" prop="personeducation">
          <el-input v-model="form.personeducation" placeholder="请输入文化水平" />
        </el-form-item>
        <el-form-item label="文化水平" prop="personauthority">
          <el-input v-model="form.personauthority" placeholder="请输入文化水平" />
        </el-form-item>
        <el-form-item label="工作时间" prop="jobtime">
          <el-input v-model="form.jobtime" placeholder="请输入工作时间" />
        </el-form-item>
        <el-form-item label="是否党员" prop="isparty">
          <el-input v-model="form.isparty" placeholder="请输入是否党员" />
        </el-form-item>
        <el-form-item label="监察对象二级" prop="issupervision2">
          <el-input v-model="form.issupervision2" placeholder="请输入监察对象二级" />
        </el-form-item>
        <el-form-item label="是否非党员非监察对象" prop="issupervision3">
          <el-input v-model="form.issupervision3" placeholder="请输入是否非党员非监察对象" />
        </el-form-item>
        <el-form-item label="其他违法犯罪行为" prop="lawbehaviourother">
          <el-input v-model="form.lawbehaviourother" placeholder="请输入其他违法犯罪行为" />
        </el-form-item>
        <el-form-item label="是否下一级党委主要负责人" prop="ischarge">
          <el-input v-model="form.ischarge" placeholder="请输入是否下一级党委主要负责人" />
        </el-form-item>
        <el-form-item label="住址" prop="address">
          <el-input v-model="form.address" placeholder="请输入住址" />
        </el-form-item>
        <el-form-item label="籍贯" prop="nativeplace">
          <el-input v-model="form.nativeplace" placeholder="请输入籍贯" />
        </el-form-item>
        <el-form-item label="案件编码" prop="casecode">
          <el-input v-model="form.casecode" placeholder="请输入案件编码" />
        </el-form-item>
        <el-form-item label="联系方式" prop="personcontact">
          <el-input v-model="form.personcontact" placeholder="请输入联系方式" />
        </el-form-item>
        <el-form-item label="接收情况" prop="receivestaut">
          <el-input v-model="form.receivestaut" placeholder="请输入接收情况" />
        </el-form-item>
        <el-form-item label="线索来源2级" prop="subcluesource">
          <el-input v-model="form.subcluesource" placeholder="请输入线索来源2级" />
        </el-form-item>
        <el-form-item label="处置状态" prop="handlestate">
          <el-input v-model="form.handlestate" placeholder="请输入处置状态" />
        </el-form-item>
        <el-form-item label="处置方式">
          <el-select v-model="form.handletype" placeholder="请选择处置方式">
            <el-option label="请选择字典生成" value />
          </el-select>
        </el-form-item>
        <el-form-item label="处置结果" prop="handleresultlev1">
          <el-input v-model="form.handleresultlev1" placeholder="请输入处置结果" />
        </el-form-item>
        <el-form-item label="处置结果" prop="handleresultlev2">
          <el-input v-model="form.handleresultlev2" placeholder="请输入处置结果" />
        </el-form-item>
        <el-form-item label="处置时间" prop="handletime">
          <el-input v-model="form.handletime" placeholder="请输入处置时间" />
        </el-form-item>
        <el-form-item label="是否督办" prop="isduban">
          <el-input v-model="form.isduban" placeholder="请输入是否督办" />
        </el-form-item>
        <el-form-item label="是否措施线索">
          <el-radio-group v-model="form.ismeasureclue">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否措施线索" prop="basehandledept">
          <el-input v-model="form.basehandledept" placeholder="请输入是否措施线索" />
        </el-form-item>
        <el-form-item label="是否措施线索" prop="basehandledeptid">
          <el-input v-model="form.basehandledeptid" placeholder="请输入是否措施线索" />
        </el-form-item>
        <el-form-item label="0：默认；1：信访；2：巡视">
          <el-select v-model="form.sourcetype" placeholder="请选择0：默认；1：信访；2：巡视">
            <el-option label="请选择字典生成" value />
          </el-select>
        </el-form-item>
        <el-form-item label="0：默认；1：信访；2：巡视" prop="statisticaltime">
          <el-input v-model="form.statisticaltime" placeholder="请输入0：默认；1：信访；2：巡视" />
        </el-form-item>
        <el-form-item label="0：默认；1：信访；2：巡视" prop="storagestate">
          <el-input v-model="form.storagestate" placeholder="请输入0：默认；1：信访；2：巡视" />
        </el-form-item>
        <el-form-item label="0：默认；1：信访；2：巡视" prop="handletypechange">
          <el-input v-model="form.handletypechange" placeholder="请输入0：默认；1：信访；2：巡视" />
        </el-form-item>
        <el-form-item label="0：默认；1：信访；2：巡视" prop="mainclueid">
          <el-input v-model="form.mainclueid" placeholder="请输入0：默认；1：信访；2：巡视" />
        </el-form-item>
        <el-form-item label="0：默认；1：信访；2：巡视" prop="createcompanyid">
          <el-input v-model="form.createcompanyid" placeholder="请输入0：默认；1：信访；2：巡视" />
        </el-form-item>
        <el-form-item label="0：默认；1：信访；2：巡视" prop="iseliminateevil">
          <el-input v-model="form.iseliminateevil" placeholder="请输入0：默认；1：信访；2：巡视" />
        </el-form-item>
        <el-form-item label="0：默认；1：信访；2：巡视" prop="isaccountability">
          <el-input v-model="form.isaccountability" placeholder="请输入0：默认；1：信访；2：巡视" />
        </el-form-item>
        <el-form-item label="加急" prop="urgent">
          <el-input v-model="form.urgent" placeholder="请输入加急" />
        </el-form-item>
        <el-form-item label="处分决定时间" prop="handledecisiontime">
          <el-input v-model="form.handledecisiontime" placeholder="请输入处分决定时间" />
        </el-form-item>
        <el-form-item label="问题摘要" prop="problemsummary">
          <el-input v-model="form.problemsummary" placeholder="请输入问题摘要" />
        </el-form-item>
        <el-form-item label="是否旧件" prop="isoldclue">
          <el-input v-model="form.isoldclue" placeholder="请输入是否旧件" />
        </el-form-item>
        <el-form-item label="是否为简易案件" prop="iseasyclue">
          <el-input v-model="form.iseasyclue" placeholder="请输入是否为简易案件" />
        </el-form-item>
        <el-form-item label="案件类型" prop="clueproperty">
          <el-input v-model="form.clueproperty" placeholder="请输入案件类型" />
        </el-form-item>
        <el-form-item label="同级党委正职领导干部" prop="samelevel">
          <el-input v-model="form.samelevel" placeholder="请输入同级党委正职领导干部" />
        </el-form-item>
        <el-form-item label="同级党委主要负责人意见" prop="samelevelopinion">
          <el-input v-model="form.samelevelopinion" placeholder="请输入同级党委主要负责人意见" />
        </el-form-item>
        <el-form-item label="同级党委主要负责人意见" prop="samelevelopinion2">
          <el-input v-model="form.samelevelopinion2" placeholder="请输入同级党委主要负责人意见" />
        </el-form-item>
        <el-form-item label="同级党委主要负责人意见">
          <el-select v-model="form.handlemethodtype" placeholder="请选择同级党委主要负责人意见">
            <el-option label="请选择字典生成" value />
          </el-select>
        </el-form-item>
        <el-form-item label="派驻地点" prop="paizhuarea">
          <el-input v-model="form.paizhuarea" placeholder="请输入派驻地点" />
        </el-form-item>
        <el-form-item label="是否属上报 是否属于向上级纪委监委报告范围6.0" prop="isup">
          <el-input v-model="form.isup" placeholder="请输入是否属上报 是否属于向上级纪委监委报告范围6.0" />
        </el-form-item>
        <el-form-item label="是否属上报线索状态 1：县区，2：市区，3：省">
          <el-radio-group v-model="form.isupstatus">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="上报线索单位" prop="updept">
          <el-input v-model="form.updept" placeholder="请输入上报线索单位" />
        </el-form-item>
        <el-form-item label="添加多人主线索ID" prop="addpeoplemainid">
          <el-input v-model="form.addpeoplemainid" placeholder="请输入添加多人主线索ID" />
        </el-form-item>
        <el-form-item label="添加多人主线索ID" prop="samelevelopinionaffix">
          <el-input v-model="form.samelevelopinionaffix" placeholder="请输入添加多人主线索ID" />
        </el-form-item>
        <el-form-item label="是否主动投案6.0" prop="issurrender">
          <el-input v-model="form.issurrender" placeholder="请输入是否主动投案6.0" />
        </el-form-item>
        <el-form-item label="是否纪检监察干部6.0" prop="isjwcadre">
          <el-input v-model="form.isjwcadre" placeholder="请输入是否纪检监察干部6.0" />
        </el-form-item>
        <el-form-item label="案管室首次分办承办室时间" prop="agsdistributetime">
          <el-input v-model="form.agsdistributetime" placeholder="请输入案管室首次分办承办室时间" />
        </el-form-item>
        <el-form-item label="第一次转办时间案管室和承办室都记录" prop="firsttransfertime">
          <el-input v-model="form.firsttransfertime" placeholder="请输入第一次转办时间案管室和承办室都记录" />
        </el-form-item>
        <el-form-item label="第一次转办时间案管室和承办室都记录" prop="firstdistributetime">
          <el-input v-model="form.firstdistributetime" placeholder="请输入第一次转办时间案管室和承办室都记录" />
        </el-form-item>
        <el-form-item label="用于线索查询是否进行逾期判断的标志">
          <el-radio-group v-model="form.yuqistatus">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="用于线索查询是否进行逾期判断的标志" prop="isexport">
          <el-input v-model="form.isexport" placeholder="请输入用于线索查询是否进行逾期判断的标志" />
        </el-form-item>
        <el-form-item label="办理要求" prop="handlerequire">
          <el-input v-model="form.handlerequire" placeholder="请输入办理要求" />
        </el-form-item>
        <el-form-item label="干部管理权限" prop="cadreauthority">
          <el-input v-model="form.cadreauthority" placeholder="请输入干部管理权限" />
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
  listTempclueinfo,
  getTempclueinfo,
  delTempclueinfo,
  addTempclueinfo,
  updateTempclueinfo,
  exportTempclueinfo
} from "@/api/sjwflowbusiness/tempclueinfo";

export default {
  name: "Tempclueinfo",
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
      // 线索操作表格数据
      tempclueinfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 30,
        id: undefined,
        unitname: undefined,
        cluecode: undefined,
        personname: undefined,
        personunit: undefined,
        cluesource: undefined,
        clueno: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      tableHeight: this.$store.getters.clientHeight - 250 + "px"
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询线索操作列表 */
    getList() {
      this.loading = true;
      listTempclueinfo(this.queryParams).then(response => {
        this.tempclueinfoList = response.rows;
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
        createuserid: undefined,
        createdeptid: undefined,
        userid: undefined,
        deptid: undefined,
        attachmentid: undefined,
        handledept: undefined,
        handledeptid: undefined,
        handlestaff: undefined,
        affix: undefined,
        state: undefined,
        unitcode: undefined,
        unitname: undefined,
        reportdate: undefined,
        fourformno: undefined,
        cluecode: undefined,
        personcode: undefined,
        isunit: undefined,
        personid: undefined,
        personname: undefined,
        personidcard: undefined,
        birthdate: undefined,
        nation: undefined,
        politic: undefined,
        politicdetail: undefined,
        partytime: undefined,
        personlevel: undefined,
        personlevel2: undefined,
        secondlevel: undefined,
        personduty: undefined,
        personunit: undefined,
        idcard: undefined,
        isdeputy: undefined,
        iscppcc: undefined,
        isddb: undefined,
        issupervision: undefined,
        iscivilservant: undefined,
        cluesource: undefined,
        illegalbehavior: undefined,
        lawbehaviour: undefined,
        handleunit: undefined,
        accepttime: undefined,
        handle1time: undefined,
        handle1censustime: undefined,
        handletype1first: undefined,
        handletype1second: undefined,
        handle2time: undefined,
        handle2censustime: undefined,
        handletype2first: undefined,
        handletype2second: undefined,
        handle3time: undefined,
        handle3censustime: undefined,
        handletype3first: undefined,
        handletype3second: undefined,
        handle4time: undefined,
        handle4censusttime: undefined,
        handletype4first: undefined,
        handletype4second: undefined,
        handle5time: undefined,
        handle5census: undefined,
        handletype5first: undefined,
        handletype5second: undefined,
        handle6time: undefined,
        handle6censustime: undefined,
        handletype6first: undefined,
        handletype6second: undefined,
        handle7time: undefined,
        handle7censustime: undefined,
        handletype7first: undefined,
        handletype7second: undefined,
        handle8time: undefined,
        handle8censustime: undefined,
        handletype8first: undefined,
        handletype8second: undefined,
        orgmeasure: undefined,
        implementtime: undefined,
        ischecked: undefined,
        iscomeclean: undefined,
        retrieveeconomicloss: undefined,
        captureassetmoney: undefined,
        confiscatemoney: undefined,
        compensatemoney: undefined,
        handinmoney: undefined,
        lossmoney: undefined,
        mainclue: undefined,
        handlereport: undefined,
        handleremark: undefined,
        isrepeated: undefined,
        reportstaff: undefined,
        checkstaff: undefined,
        isdeleted: "0",
        timestamp: undefined,
        reflectname: undefined,
        reflectunit: undefined,
        reflectpost: undefined,
        reflectphone: undefined,
        reflectidcard: undefined,
        personsex: undefined,
        status: "0",
        clueno: undefined,
        personeducation: undefined,
        personauthority: undefined,
        jobtime: undefined,
        isparty: undefined,
        issupervision2: undefined,
        issupervision3: undefined,
        lawbehaviourother: undefined,
        ischarge: undefined,
        address: undefined,
        nativeplace: undefined,
        casecode: undefined,
        personcontact: undefined,
        receivestaut: undefined,
        subcluesource: undefined,
        handlestate: undefined,
        handletype: undefined,
        handleresultlev1: undefined,
        handleresultlev2: undefined,
        handletime: undefined,
        isduban: undefined,
        ismeasureclue: "0",
        basehandledept: undefined,
        basehandledeptid: undefined,
        sourcetype: undefined,
        statisticaltime: undefined,
        storagestate: undefined,
        handletypechange: undefined,
        mainclueid: undefined,
        createcompanyid: undefined,
        iseliminateevil: undefined,
        isaccountability: undefined,
        urgent: undefined,
        handledecisiontime: undefined,
        problemsummary: undefined,
        isoldclue: undefined,
        iseasyclue: undefined,
        clueproperty: undefined,
        samelevel: undefined,
        samelevelopinion: undefined,
        samelevelopinion2: undefined,
        handlemethodtype: undefined,
        paizhuarea: undefined,
        isup: undefined,
        isupstatus: "0",
        updept: undefined,
        addpeoplemainid: undefined,
        samelevelopinionaffix: undefined,
        issurrender: undefined,
        isjwcadre: undefined,
        agsdistributetime: undefined,
        firsttransfertime: undefined,
        firstdistributetime: undefined,
        yuqistatus: "0",
        isexport: undefined,
        handlerequire: undefined,
        cadreauthority: undefined
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
      this.open = true;
      this.title = "添加线索操作";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getTempclueinfo(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改线索操作";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateTempclueinfo(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addTempclueinfo(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$confirm(
        '是否确认删除线索操作编号为"' + ids + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(function() {
          return delTempclueinfo(ids);
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
      this.$confirm("是否确认导出所有线索操作数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function() {
          return exportTempclueinfo(queryParams);
        })
        .then(response => {
          this.download(response.msg);
        })
        .catch(function() {});
    },
    /** 更新form */
    updateform(data) {
      updateTempclueinfo(data).then(res => {
        if (res.code == "200") {
          this.msgSuccess("修改成功");
        }
      });
    }
  }
};
</script>