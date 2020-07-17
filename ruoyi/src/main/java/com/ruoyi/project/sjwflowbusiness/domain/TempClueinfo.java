package com.ruoyi.project.sjwflowbusiness.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 线索操作对象 temp_clueinfo
 * 
 * @author littlemoneygod
 * @date 2020-07-18
 */
public class TempClueinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 四种形态（案件4.0） */
    @Excel(name = "四种形态", readConverterExp = "案=件4.0")
    private String id;

    /** 创建者 */
    private String createuserid;

    /** $column.columnComment */
    private String createdeptid;

    /** $column.columnComment */
    private String userid;

    /** 创建者部门id */
    private String deptid;

    /** $column.columnComment */
    private String attachmentid;

    /** $column.columnComment */
    private String handledept;

    /** $column.columnComment */
    private String handledeptid;

    /** $column.columnComment */
    private String handlestaff;

    /** $column.columnComment */
    private String affix;

    /** 线索状态 */
    private String state;

    /** 单位代码 */
    private String unitcode;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private String unitname;

    /** 填报日期 */
    @Excel(name = "填报日期")
    private String reportdate;

    /** 案件4.0系统ID编号 */
    private String fourformno;

    /** 线索编码 */
    @Excel(name = "线索编码")
    private String cluecode;

    /** 人员编号 */
    private String personcode;

    /** 反映单位或个人 */
    private String isunit;

    /** $column.columnComment */
    private String personid;

    /** 被反映人 */
    @Excel(name = "被反映人")
    private String personname;

    /** 被反映人 */
    private String personidcard;

    /** 出生年月 */
    private String birthdate;

    /** 民族 */
    private String nation;

    /** 政治面貌 */
    private String politic;

    /** 政治面貌细节 */
    private String politicdetail;

    /** 入党时间 */
    private String partytime;

    /** 职级 */
    @Excel(name = "职级")
    private String personlevel;

    /** $column.columnComment */
    private String personlevel2;

    /** 干部管理权限 中管干部 省管干部 市管干部 县管干部 */
    private String secondlevel;

    /** 职务 */
    private String personduty;

    /** 工作职务 */
    @Excel(name = "工作职务")
    private String personunit;

    /** 反映人身份证 */
    private String idcard;

    /** 人大代表 */
    private String isdeputy;

    /** 政协委员 */
    private String iscppcc;

    /** 是否党代表 */
    private String isddb;

    /** 是否监察对象 */
    private String issupervision;

    /** 是否国家公务员 */
    private String iscivilservant;

    /** 线索来源 */
    @Excel(name = "线索来源")
    private String cluesource;

    /** 违纪行为 */
    private String illegalbehavior;

    /** 违法行为 */
    private String lawbehaviour;

    /** 办理机关 */
    private String handleunit;

    /** 受理时间 */
    private String accepttime;

    /** 处置方式1批准时间 */
    private String handle1time;

    /** 处置方式1统计时间 */
    private String handle1censustime;

    /** 处置方式1一级 */
    private String handletype1first;

    /** 处置方式1二级 */
    private String handletype1second;

    /** 处置方式2批准时间 */
    private String handle2time;

    /** 处置方式2统计时间 */
    private String handle2censustime;

    /** 处置方式2一级 */
    private String handletype2first;

    /** 处置方式2二级 */
    private String handletype2second;

    /** 处置方式3批准时间 */
    private String handle3time;

    /** 处置方式3统计时间 */
    private String handle3censustime;

    /** 处置方式3一级 */
    private String handletype3first;

    /** 处置方式3二级 */
    private String handletype3second;

    /** 处置方式4批准时间 */
    private String handle4time;

    /** 处置方式4统计时间 */
    private String handle4censusttime;

    /** 处置方式4一级 */
    private String handletype4first;

    /** 处置方式4二级 */
    private String handletype4second;

    /** 处置方式5批准时间 */
    private String handle5time;

    /** 处置方式5统计时间 */
    private String handle5census;

    /** 处置方式5一级 */
    private String handletype5first;

    /** 处置方式5二级 */
    private String handletype5second;

    /** 处置方式6批准时间 */
    private String handle6time;

    /** 处置方式6统计时间 */
    private String handle6censustime;

    /** 处置方式6一级 */
    private String handletype6first;

    /** 处置方式6二级 */
    private String handletype6second;

    /** 处置方式7批准时间 */
    private String handle7time;

    /** 处置方式7统计时间 */
    private String handle7censustime;

    /** 处置方式7一级 */
    private String handletype7first;

    /** 处置方式7二级 */
    private String handletype7second;

    /** 处置方式8批准时间 */
    private String handle8time;

    /** 处置方式8统计时间 */
    private String handle8censustime;

    /** 处置方式8一级 */
    private String handletype8first;

    /** 处置方式8二级 */
    private String handletype8second;

    /** 组织措施情况 */
    private String orgmeasure;

    /** 落实时间 */
    private String implementtime;

    /** 是否与本人核实 */
    private String ischecked;

    /** 是否主动交代问题 */
    private String iscomeclean;

    /** 挽回经济损失（万元） */
    private String retrieveeconomicloss;

    /** 收缴涉案金额（万元） */
    private String captureassetmoney;

    /** 没收金额6.0 */
    private String confiscatemoney;

    /** 责令赔偿金额6.0 */
    private String compensatemoney;

    /** 登记上交金额6.0 */
    private String handinmoney;

    /** 追缴失职渎职滥用职权造成的损失金额6.0 */
    private String lossmoney;

    /** 主要问题线索 */
    private String mainclue;

    /** 处置情况报告 */
    private String handlereport;

    /** 备注 */
    private String handleremark;

    /** 是否重复件 */
    @Excel(name = "是否重复件")
    private String isrepeated;

    /** 填表人 */
    private String reportstaff;

    /** 审核人 */
    private String checkstaff;

    /** $column.columnComment */
    @Excel(name = "是否重复件")
    private Boolean isdeleted;

    /** $column.columnComment */
    private Date timestamp;

    /** 反映人姓名 */
    private String reflectname;

    /** 反映人单位 */
    private String reflectunit;

    /** 反映人职务 */
    private String reflectpost;

    /** 反映人联系方式 */
    private String reflectphone;

    /** 反映人身份证号码 */
    private String reflectidcard;

    /** 性别 */
    private String personsex;

    /** $column.columnComment */
    private String status;

    /** 函号信访编号 */
    @Excel(name = "函号信访编号")
    private String clueno;

    /** 文化水平 */
    private String personeducation;

    /** $column.columnComment */
    private String personauthority;

    /** 工作时间 */
    private String jobtime;

    /** 是否党员 */
    private String isparty;

    /** 监察对象二级 */
    private String issupervision2;

    /** 是否非党员非监察对象 */
    private String issupervision3;

    /** 其他违法犯罪行为 */
    private String lawbehaviourother;

    /** 是否下一级党委主要负责人 */
    private String ischarge;

    /** 住址 */
    private String address;

    /** 籍贯 */
    private String nativeplace;

    /** 案件编码 */
    private String casecode;

    /** 联系方式 */
    private String personcontact;

    /** 接收情况 */
    private String receivestaut;

    /** 线索来源2级 */
    private String subcluesource;

    /** 处置状态 */
    @Excel(name = "处置状态")
    private String handlestate;

    /** 处置方式 */
    @Excel(name = "处置方式")
    private String handletype;

    /** 处置结果 */
    private String handleresultlev1;

    /** $column.columnComment */
    private String handleresultlev2;

    /** 处置时间 */
    private String handletime;

    /** 是否督办 */
    private String isduban;

    /** 是否措施线索 */
    @Excel(name = "是否措施线索")
    private Boolean ismeasureclue;

    /** $column.columnComment */
    @Excel(name = "是否措施线索")
    private String basehandledept;

    /** $column.columnComment */
    @Excel(name = "是否措施线索")
    private String basehandledeptid;

    /** 0：默认；1：信访；2：巡视 */
    private Long sourcetype;

    /** $column.columnComment */
    private String statisticaltime;

    /** $column.columnComment */
    private Long storagestate;

    /** $column.columnComment */
    private String handletypechange;

    /** $column.columnComment */
    @Excel(name = "是否措施线索")
    private String mainclueid;

    /** $column.columnComment */
    @Excel(name = "是否措施线索")
    private String createcompanyid;

    /** $column.columnComment */
    @Excel(name = "是否措施线索")
    private String iseliminateevil;

    /** $column.columnComment */
    private String isaccountability;

    /** 加急 */
    @Excel(name = "加急")
    private String urgent;

    /** 处分决定时间 */
    private String handledecisiontime;

    /** 问题摘要 */
    private String problemsummary;

    /** 是否旧件 */
    private String isoldclue;

    /** 是否为简易案件 */
    private String iseasyclue;

    /** 案件类型 */
    private String clueproperty;

    /** 同级党委正职领导干部 */
    private String samelevel;

    /** 同级党委主要负责人意见 */
    private String samelevelopinion;

    /** $column.columnComment */
    private String samelevelopinion2;

    /** $column.columnComment */
    private String handlemethodtype;

    /** 派驻地点 */
    private String paizhuarea;

    /** 是否属上报 是否属于向上级纪委监委报告范围6.0 */
    private String isup;

    /** 是否属上报线索状态 1：县区，2：市区，3：省 */
    private String isupstatus;

    /** 上报线索单位 */
    private String updept;

    /** 添加多人主线索ID */
    @Excel(name = "添加多人主线索ID")
    private String addpeoplemainid;

    /** $column.columnComment */
    private String samelevelopinionaffix;

    /** 是否主动投案6.0 */
    private String issurrender;

    /** 是否纪检监察干部6.0 */
    private String isjwcadre;

    /** 案管室首次分办承办室时间 */
    private String agsdistributetime;

    /** 第一次转办时间案管室和承办室都记录 */
    private String firsttransfertime;

    /** 第一次转办时间案管室和承办室都记录 */
    private String firstdistributetime;

    /** 用于线索查询是否进行逾期判断的标志 */
    private Integer yuqistatus;

    /** $column.columnComment */
    private String isexport;

    /** 办理要求 */
    private Long handlerequire;

    /** 干部管理权限 */
    private String cadreauthority;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setCreateuserid(String createuserid) 
    {
        this.createuserid = createuserid;
    }

    public String getCreateuserid() 
    {
        return createuserid;
    }
    public void setCreatedeptid(String createdeptid) 
    {
        this.createdeptid = createdeptid;
    }

    public String getCreatedeptid() 
    {
        return createdeptid;
    }
    public void setUserid(String userid) 
    {
        this.userid = userid;
    }

    public String getUserid() 
    {
        return userid;
    }
    public void setDeptid(String deptid) 
    {
        this.deptid = deptid;
    }

    public String getDeptid() 
    {
        return deptid;
    }
    public void setAttachmentid(String attachmentid) 
    {
        this.attachmentid = attachmentid;
    }

    public String getAttachmentid() 
    {
        return attachmentid;
    }
    public void setHandledept(String handledept) 
    {
        this.handledept = handledept;
    }

    public String getHandledept() 
    {
        return handledept;
    }
    public void setHandledeptid(String handledeptid) 
    {
        this.handledeptid = handledeptid;
    }

    public String getHandledeptid() 
    {
        return handledeptid;
    }
    public void setHandlestaff(String handlestaff) 
    {
        this.handlestaff = handlestaff;
    }

    public String getHandlestaff() 
    {
        return handlestaff;
    }
    public void setAffix(String affix) 
    {
        this.affix = affix;
    }

    public String getAffix() 
    {
        return affix;
    }
    public void setState(String state) 
    {
        this.state = state;
    }

    public String getState() 
    {
        return state;
    }
    public void setUnitcode(String unitcode) 
    {
        this.unitcode = unitcode;
    }

    public String getUnitcode() 
    {
        return unitcode;
    }
    public void setUnitname(String unitname) 
    {
        this.unitname = unitname;
    }

    public String getUnitname() 
    {
        return unitname;
    }
    public void setReportdate(String reportdate) 
    {
        this.reportdate = reportdate;
    }

    public String getReportdate() 
    {
        return reportdate;
    }
    public void setFourformno(String fourformno) 
    {
        this.fourformno = fourformno;
    }

    public String getFourformno() 
    {
        return fourformno;
    }
    public void setCluecode(String cluecode) 
    {
        this.cluecode = cluecode;
    }

    public String getCluecode() 
    {
        return cluecode;
    }
    public void setPersoncode(String personcode) 
    {
        this.personcode = personcode;
    }

    public String getPersoncode() 
    {
        return personcode;
    }
    public void setIsunit(String isunit) 
    {
        this.isunit = isunit;
    }

    public String getIsunit() 
    {
        return isunit;
    }
    public void setPersonid(String personid) 
    {
        this.personid = personid;
    }

    public String getPersonid() 
    {
        return personid;
    }
    public void setPersonname(String personname) 
    {
        this.personname = personname;
    }

    public String getPersonname() 
    {
        return personname;
    }
    public void setPersonidcard(String personidcard) 
    {
        this.personidcard = personidcard;
    }

    public String getPersonidcard() 
    {
        return personidcard;
    }
    public void setBirthdate(String birthdate) 
    {
        this.birthdate = birthdate;
    }

    public String getBirthdate() 
    {
        return birthdate;
    }
    public void setNation(String nation) 
    {
        this.nation = nation;
    }

    public String getNation() 
    {
        return nation;
    }
    public void setPolitic(String politic) 
    {
        this.politic = politic;
    }

    public String getPolitic() 
    {
        return politic;
    }
    public void setPoliticdetail(String politicdetail) 
    {
        this.politicdetail = politicdetail;
    }

    public String getPoliticdetail() 
    {
        return politicdetail;
    }
    public void setPartytime(String partytime) 
    {
        this.partytime = partytime;
    }

    public String getPartytime() 
    {
        return partytime;
    }
    public void setPersonlevel(String personlevel) 
    {
        this.personlevel = personlevel;
    }

    public String getPersonlevel() 
    {
        return personlevel;
    }
    public void setPersonlevel2(String personlevel2) 
    {
        this.personlevel2 = personlevel2;
    }

    public String getPersonlevel2() 
    {
        return personlevel2;
    }
    public void setSecondlevel(String secondlevel) 
    {
        this.secondlevel = secondlevel;
    }

    public String getSecondlevel() 
    {
        return secondlevel;
    }
    public void setPersonduty(String personduty) 
    {
        this.personduty = personduty;
    }

    public String getPersonduty() 
    {
        return personduty;
    }
    public void setPersonunit(String personunit) 
    {
        this.personunit = personunit;
    }

    public String getPersonunit() 
    {
        return personunit;
    }
    public void setIdcard(String idcard) 
    {
        this.idcard = idcard;
    }

    public String getIdcard() 
    {
        return idcard;
    }
    public void setIsdeputy(String isdeputy) 
    {
        this.isdeputy = isdeputy;
    }

    public String getIsdeputy() 
    {
        return isdeputy;
    }
    public void setIscppcc(String iscppcc) 
    {
        this.iscppcc = iscppcc;
    }

    public String getIscppcc() 
    {
        return iscppcc;
    }
    public void setIsddb(String isddb) 
    {
        this.isddb = isddb;
    }

    public String getIsddb() 
    {
        return isddb;
    }
    public void setIssupervision(String issupervision) 
    {
        this.issupervision = issupervision;
    }

    public String getIssupervision() 
    {
        return issupervision;
    }
    public void setIscivilservant(String iscivilservant) 
    {
        this.iscivilservant = iscivilservant;
    }

    public String getIscivilservant() 
    {
        return iscivilservant;
    }
    public void setCluesource(String cluesource) 
    {
        this.cluesource = cluesource;
    }

    public String getCluesource() 
    {
        return cluesource;
    }
    public void setIllegalbehavior(String illegalbehavior) 
    {
        this.illegalbehavior = illegalbehavior;
    }

    public String getIllegalbehavior() 
    {
        return illegalbehavior;
    }
    public void setLawbehaviour(String lawbehaviour) 
    {
        this.lawbehaviour = lawbehaviour;
    }

    public String getLawbehaviour() 
    {
        return lawbehaviour;
    }
    public void setHandleunit(String handleunit) 
    {
        this.handleunit = handleunit;
    }

    public String getHandleunit() 
    {
        return handleunit;
    }
    public void setAccepttime(String accepttime) 
    {
        this.accepttime = accepttime;
    }

    public String getAccepttime() 
    {
        return accepttime;
    }
    public void setHandle1time(String handle1time) 
    {
        this.handle1time = handle1time;
    }

    public String getHandle1time() 
    {
        return handle1time;
    }
    public void setHandle1censustime(String handle1censustime) 
    {
        this.handle1censustime = handle1censustime;
    }

    public String getHandle1censustime() 
    {
        return handle1censustime;
    }
    public void setHandletype1first(String handletype1first) 
    {
        this.handletype1first = handletype1first;
    }

    public String getHandletype1first() 
    {
        return handletype1first;
    }
    public void setHandletype1second(String handletype1second) 
    {
        this.handletype1second = handletype1second;
    }

    public String getHandletype1second() 
    {
        return handletype1second;
    }
    public void setHandle2time(String handle2time) 
    {
        this.handle2time = handle2time;
    }

    public String getHandle2time() 
    {
        return handle2time;
    }
    public void setHandle2censustime(String handle2censustime) 
    {
        this.handle2censustime = handle2censustime;
    }

    public String getHandle2censustime() 
    {
        return handle2censustime;
    }
    public void setHandletype2first(String handletype2first) 
    {
        this.handletype2first = handletype2first;
    }

    public String getHandletype2first() 
    {
        return handletype2first;
    }
    public void setHandletype2second(String handletype2second) 
    {
        this.handletype2second = handletype2second;
    }

    public String getHandletype2second() 
    {
        return handletype2second;
    }
    public void setHandle3time(String handle3time) 
    {
        this.handle3time = handle3time;
    }

    public String getHandle3time() 
    {
        return handle3time;
    }
    public void setHandle3censustime(String handle3censustime) 
    {
        this.handle3censustime = handle3censustime;
    }

    public String getHandle3censustime() 
    {
        return handle3censustime;
    }
    public void setHandletype3first(String handletype3first) 
    {
        this.handletype3first = handletype3first;
    }

    public String getHandletype3first() 
    {
        return handletype3first;
    }
    public void setHandletype3second(String handletype3second) 
    {
        this.handletype3second = handletype3second;
    }

    public String getHandletype3second() 
    {
        return handletype3second;
    }
    public void setHandle4time(String handle4time) 
    {
        this.handle4time = handle4time;
    }

    public String getHandle4time() 
    {
        return handle4time;
    }
    public void setHandle4censusttime(String handle4censusttime) 
    {
        this.handle4censusttime = handle4censusttime;
    }

    public String getHandle4censusttime() 
    {
        return handle4censusttime;
    }
    public void setHandletype4first(String handletype4first) 
    {
        this.handletype4first = handletype4first;
    }

    public String getHandletype4first() 
    {
        return handletype4first;
    }
    public void setHandletype4second(String handletype4second) 
    {
        this.handletype4second = handletype4second;
    }

    public String getHandletype4second() 
    {
        return handletype4second;
    }
    public void setHandle5time(String handle5time) 
    {
        this.handle5time = handle5time;
    }

    public String getHandle5time() 
    {
        return handle5time;
    }
    public void setHandle5census(String handle5census) 
    {
        this.handle5census = handle5census;
    }

    public String getHandle5census() 
    {
        return handle5census;
    }
    public void setHandletype5first(String handletype5first) 
    {
        this.handletype5first = handletype5first;
    }

    public String getHandletype5first() 
    {
        return handletype5first;
    }
    public void setHandletype5second(String handletype5second) 
    {
        this.handletype5second = handletype5second;
    }

    public String getHandletype5second() 
    {
        return handletype5second;
    }
    public void setHandle6time(String handle6time) 
    {
        this.handle6time = handle6time;
    }

    public String getHandle6time() 
    {
        return handle6time;
    }
    public void setHandle6censustime(String handle6censustime) 
    {
        this.handle6censustime = handle6censustime;
    }

    public String getHandle6censustime() 
    {
        return handle6censustime;
    }
    public void setHandletype6first(String handletype6first) 
    {
        this.handletype6first = handletype6first;
    }

    public String getHandletype6first() 
    {
        return handletype6first;
    }
    public void setHandletype6second(String handletype6second) 
    {
        this.handletype6second = handletype6second;
    }

    public String getHandletype6second() 
    {
        return handletype6second;
    }
    public void setHandle7time(String handle7time) 
    {
        this.handle7time = handle7time;
    }

    public String getHandle7time() 
    {
        return handle7time;
    }
    public void setHandle7censustime(String handle7censustime) 
    {
        this.handle7censustime = handle7censustime;
    }

    public String getHandle7censustime() 
    {
        return handle7censustime;
    }
    public void setHandletype7first(String handletype7first) 
    {
        this.handletype7first = handletype7first;
    }

    public String getHandletype7first() 
    {
        return handletype7first;
    }
    public void setHandletype7second(String handletype7second) 
    {
        this.handletype7second = handletype7second;
    }

    public String getHandletype7second() 
    {
        return handletype7second;
    }
    public void setHandle8time(String handle8time) 
    {
        this.handle8time = handle8time;
    }

    public String getHandle8time() 
    {
        return handle8time;
    }
    public void setHandle8censustime(String handle8censustime) 
    {
        this.handle8censustime = handle8censustime;
    }

    public String getHandle8censustime() 
    {
        return handle8censustime;
    }
    public void setHandletype8first(String handletype8first) 
    {
        this.handletype8first = handletype8first;
    }

    public String getHandletype8first() 
    {
        return handletype8first;
    }
    public void setHandletype8second(String handletype8second) 
    {
        this.handletype8second = handletype8second;
    }

    public String getHandletype8second() 
    {
        return handletype8second;
    }
    public void setOrgmeasure(String orgmeasure) 
    {
        this.orgmeasure = orgmeasure;
    }

    public String getOrgmeasure() 
    {
        return orgmeasure;
    }
    public void setImplementtime(String implementtime) 
    {
        this.implementtime = implementtime;
    }

    public String getImplementtime() 
    {
        return implementtime;
    }
    public void setIschecked(String ischecked) 
    {
        this.ischecked = ischecked;
    }

    public String getIschecked() 
    {
        return ischecked;
    }
    public void setIscomeclean(String iscomeclean) 
    {
        this.iscomeclean = iscomeclean;
    }

    public String getIscomeclean() 
    {
        return iscomeclean;
    }
    public void setRetrieveeconomicloss(String retrieveeconomicloss) 
    {
        this.retrieveeconomicloss = retrieveeconomicloss;
    }

    public String getRetrieveeconomicloss() 
    {
        return retrieveeconomicloss;
    }
    public void setCaptureassetmoney(String captureassetmoney) 
    {
        this.captureassetmoney = captureassetmoney;
    }

    public String getCaptureassetmoney() 
    {
        return captureassetmoney;
    }
    public void setConfiscatemoney(String confiscatemoney) 
    {
        this.confiscatemoney = confiscatemoney;
    }

    public String getConfiscatemoney() 
    {
        return confiscatemoney;
    }
    public void setCompensatemoney(String compensatemoney) 
    {
        this.compensatemoney = compensatemoney;
    }

    public String getCompensatemoney() 
    {
        return compensatemoney;
    }
    public void setHandinmoney(String handinmoney) 
    {
        this.handinmoney = handinmoney;
    }

    public String getHandinmoney() 
    {
        return handinmoney;
    }
    public void setLossmoney(String lossmoney) 
    {
        this.lossmoney = lossmoney;
    }

    public String getLossmoney() 
    {
        return lossmoney;
    }
    public void setMainclue(String mainclue) 
    {
        this.mainclue = mainclue;
    }

    public String getMainclue() 
    {
        return mainclue;
    }
    public void setHandlereport(String handlereport) 
    {
        this.handlereport = handlereport;
    }

    public String getHandlereport() 
    {
        return handlereport;
    }
    public void setHandleremark(String handleremark) 
    {
        this.handleremark = handleremark;
    }

    public String getHandleremark() 
    {
        return handleremark;
    }
    public void setIsrepeated(String isrepeated) 
    {
        this.isrepeated = isrepeated;
    }

    public String getIsrepeated() 
    {
        return isrepeated;
    }
    public void setReportstaff(String reportstaff) 
    {
        this.reportstaff = reportstaff;
    }

    public String getReportstaff() 
    {
        return reportstaff;
    }
    public void setCheckstaff(String checkstaff) 
    {
        this.checkstaff = checkstaff;
    }

    public String getCheckstaff() 
    {
        return checkstaff;
    }
    public void setIsdeleted(Boolean isdeleted) 
    {
        this.isdeleted = isdeleted;
    }

    public Boolean getIsdeleted() 
    {
        return isdeleted;
    }
    public void setTimestamp(Date timestamp) 
    {
        this.timestamp = timestamp;
    }

    public Date getTimestamp() 
    {
        return timestamp;
    }
    public void setReflectname(String reflectname) 
    {
        this.reflectname = reflectname;
    }

    public String getReflectname() 
    {
        return reflectname;
    }
    public void setReflectunit(String reflectunit) 
    {
        this.reflectunit = reflectunit;
    }

    public String getReflectunit() 
    {
        return reflectunit;
    }
    public void setReflectpost(String reflectpost) 
    {
        this.reflectpost = reflectpost;
    }

    public String getReflectpost() 
    {
        return reflectpost;
    }
    public void setReflectphone(String reflectphone) 
    {
        this.reflectphone = reflectphone;
    }

    public String getReflectphone() 
    {
        return reflectphone;
    }
    public void setReflectidcard(String reflectidcard) 
    {
        this.reflectidcard = reflectidcard;
    }

    public String getReflectidcard() 
    {
        return reflectidcard;
    }
    public void setPersonsex(String personsex) 
    {
        this.personsex = personsex;
    }

    public String getPersonsex() 
    {
        return personsex;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setClueno(String clueno) 
    {
        this.clueno = clueno;
    }

    public String getClueno() 
    {
        return clueno;
    }
    public void setPersoneducation(String personeducation) 
    {
        this.personeducation = personeducation;
    }

    public String getPersoneducation() 
    {
        return personeducation;
    }
    public void setPersonauthority(String personauthority) 
    {
        this.personauthority = personauthority;
    }

    public String getPersonauthority() 
    {
        return personauthority;
    }
    public void setJobtime(String jobtime) 
    {
        this.jobtime = jobtime;
    }

    public String getJobtime() 
    {
        return jobtime;
    }
    public void setIsparty(String isparty) 
    {
        this.isparty = isparty;
    }

    public String getIsparty() 
    {
        return isparty;
    }
    public void setIssupervision2(String issupervision2) 
    {
        this.issupervision2 = issupervision2;
    }

    public String getIssupervision2() 
    {
        return issupervision2;
    }
    public void setIssupervision3(String issupervision3) 
    {
        this.issupervision3 = issupervision3;
    }

    public String getIssupervision3() 
    {
        return issupervision3;
    }
    public void setLawbehaviourother(String lawbehaviourother) 
    {
        this.lawbehaviourother = lawbehaviourother;
    }

    public String getLawbehaviourother() 
    {
        return lawbehaviourother;
    }
    public void setIscharge(String ischarge) 
    {
        this.ischarge = ischarge;
    }

    public String getIscharge() 
    {
        return ischarge;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setNativeplace(String nativeplace) 
    {
        this.nativeplace = nativeplace;
    }

    public String getNativeplace() 
    {
        return nativeplace;
    }
    public void setCasecode(String casecode) 
    {
        this.casecode = casecode;
    }

    public String getCasecode() 
    {
        return casecode;
    }
    public void setPersoncontact(String personcontact) 
    {
        this.personcontact = personcontact;
    }

    public String getPersoncontact() 
    {
        return personcontact;
    }
    public void setReceivestaut(String receivestaut) 
    {
        this.receivestaut = receivestaut;
    }

    public String getReceivestaut() 
    {
        return receivestaut;
    }
    public void setSubcluesource(String subcluesource) 
    {
        this.subcluesource = subcluesource;
    }

    public String getSubcluesource() 
    {
        return subcluesource;
    }
    public void setHandlestate(String handlestate) 
    {
        this.handlestate = handlestate;
    }

    public String getHandlestate() 
    {
        return handlestate;
    }
    public void setHandletype(String handletype) 
    {
        this.handletype = handletype;
    }

    public String getHandletype() 
    {
        return handletype;
    }
    public void setHandleresultlev1(String handleresultlev1) 
    {
        this.handleresultlev1 = handleresultlev1;
    }

    public String getHandleresultlev1() 
    {
        return handleresultlev1;
    }
    public void setHandleresultlev2(String handleresultlev2) 
    {
        this.handleresultlev2 = handleresultlev2;
    }

    public String getHandleresultlev2() 
    {
        return handleresultlev2;
    }
    public void setHandletime(String handletime) 
    {
        this.handletime = handletime;
    }

    public String getHandletime() 
    {
        return handletime;
    }
    public void setIsduban(String isduban) 
    {
        this.isduban = isduban;
    }

    public String getIsduban() 
    {
        return isduban;
    }
    public void setIsmeasureclue(Boolean ismeasureclue) 
    {
        this.ismeasureclue = ismeasureclue;
    }

    public Boolean getIsmeasureclue() 
    {
        return ismeasureclue;
    }
    public void setBasehandledept(String basehandledept) 
    {
        this.basehandledept = basehandledept;
    }

    public String getBasehandledept() 
    {
        return basehandledept;
    }
    public void setBasehandledeptid(String basehandledeptid) 
    {
        this.basehandledeptid = basehandledeptid;
    }

    public String getBasehandledeptid() 
    {
        return basehandledeptid;
    }
    public void setSourcetype(Long sourcetype) 
    {
        this.sourcetype = sourcetype;
    }

    public Long getSourcetype() 
    {
        return sourcetype;
    }
    public void setStatisticaltime(String statisticaltime) 
    {
        this.statisticaltime = statisticaltime;
    }

    public String getStatisticaltime() 
    {
        return statisticaltime;
    }
    public void setStoragestate(Long storagestate) 
    {
        this.storagestate = storagestate;
    }

    public Long getStoragestate() 
    {
        return storagestate;
    }
    public void setHandletypechange(String handletypechange) 
    {
        this.handletypechange = handletypechange;
    }

    public String getHandletypechange() 
    {
        return handletypechange;
    }
    public void setMainclueid(String mainclueid) 
    {
        this.mainclueid = mainclueid;
    }

    public String getMainclueid() 
    {
        return mainclueid;
    }
    public void setCreatecompanyid(String createcompanyid) 
    {
        this.createcompanyid = createcompanyid;
    }

    public String getCreatecompanyid() 
    {
        return createcompanyid;
    }
    public void setIseliminateevil(String iseliminateevil) 
    {
        this.iseliminateevil = iseliminateevil;
    }

    public String getIseliminateevil() 
    {
        return iseliminateevil;
    }
    public void setIsaccountability(String isaccountability) 
    {
        this.isaccountability = isaccountability;
    }

    public String getIsaccountability() 
    {
        return isaccountability;
    }
    public void setUrgent(String urgent) 
    {
        this.urgent = urgent;
    }

    public String getUrgent() 
    {
        return urgent;
    }
    public void setHandledecisiontime(String handledecisiontime) 
    {
        this.handledecisiontime = handledecisiontime;
    }

    public String getHandledecisiontime() 
    {
        return handledecisiontime;
    }
    public void setProblemsummary(String problemsummary) 
    {
        this.problemsummary = problemsummary;
    }

    public String getProblemsummary() 
    {
        return problemsummary;
    }
    public void setIsoldclue(String isoldclue) 
    {
        this.isoldclue = isoldclue;
    }

    public String getIsoldclue() 
    {
        return isoldclue;
    }
    public void setIseasyclue(String iseasyclue) 
    {
        this.iseasyclue = iseasyclue;
    }

    public String getIseasyclue() 
    {
        return iseasyclue;
    }
    public void setClueproperty(String clueproperty) 
    {
        this.clueproperty = clueproperty;
    }

    public String getClueproperty() 
    {
        return clueproperty;
    }
    public void setSamelevel(String samelevel) 
    {
        this.samelevel = samelevel;
    }

    public String getSamelevel() 
    {
        return samelevel;
    }
    public void setSamelevelopinion(String samelevelopinion) 
    {
        this.samelevelopinion = samelevelopinion;
    }

    public String getSamelevelopinion() 
    {
        return samelevelopinion;
    }
    public void setSamelevelopinion2(String samelevelopinion2) 
    {
        this.samelevelopinion2 = samelevelopinion2;
    }

    public String getSamelevelopinion2() 
    {
        return samelevelopinion2;
    }
    public void setHandlemethodtype(String handlemethodtype) 
    {
        this.handlemethodtype = handlemethodtype;
    }

    public String getHandlemethodtype() 
    {
        return handlemethodtype;
    }
    public void setPaizhuarea(String paizhuarea) 
    {
        this.paizhuarea = paizhuarea;
    }

    public String getPaizhuarea() 
    {
        return paizhuarea;
    }
    public void setIsup(String isup) 
    {
        this.isup = isup;
    }

    public String getIsup() 
    {
        return isup;
    }
    public void setIsupstatus(String isupstatus) 
    {
        this.isupstatus = isupstatus;
    }

    public String getIsupstatus() 
    {
        return isupstatus;
    }
    public void setUpdept(String updept) 
    {
        this.updept = updept;
    }

    public String getUpdept() 
    {
        return updept;
    }
    public void setAddpeoplemainid(String addpeoplemainid) 
    {
        this.addpeoplemainid = addpeoplemainid;
    }

    public String getAddpeoplemainid() 
    {
        return addpeoplemainid;
    }
    public void setSamelevelopinionaffix(String samelevelopinionaffix) 
    {
        this.samelevelopinionaffix = samelevelopinionaffix;
    }

    public String getSamelevelopinionaffix() 
    {
        return samelevelopinionaffix;
    }
    public void setIssurrender(String issurrender) 
    {
        this.issurrender = issurrender;
    }

    public String getIssurrender() 
    {
        return issurrender;
    }
    public void setIsjwcadre(String isjwcadre) 
    {
        this.isjwcadre = isjwcadre;
    }

    public String getIsjwcadre() 
    {
        return isjwcadre;
    }
    public void setAgsdistributetime(String agsdistributetime) 
    {
        this.agsdistributetime = agsdistributetime;
    }

    public String getAgsdistributetime() 
    {
        return agsdistributetime;
    }
    public void setFirsttransfertime(String firsttransfertime) 
    {
        this.firsttransfertime = firsttransfertime;
    }

    public String getFirsttransfertime() 
    {
        return firsttransfertime;
    }
    public void setFirstdistributetime(String firstdistributetime) 
    {
        this.firstdistributetime = firstdistributetime;
    }

    public String getFirstdistributetime() 
    {
        return firstdistributetime;
    }
    public void setYuqistatus(Integer yuqistatus) 
    {
        this.yuqistatus = yuqistatus;
    }

    public Integer getYuqistatus() 
    {
        return yuqistatus;
    }
    public void setIsexport(String isexport) 
    {
        this.isexport = isexport;
    }

    public String getIsexport() 
    {
        return isexport;
    }
    public void setHandlerequire(Long handlerequire) 
    {
        this.handlerequire = handlerequire;
    }

    public Long getHandlerequire() 
    {
        return handlerequire;
    }
    public void setCadreauthority(String cadreauthority) 
    {
        this.cadreauthority = cadreauthority;
    }

    public String getCadreauthority() 
    {
        return cadreauthority;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createuserid", getCreateuserid())
            .append("createdeptid", getCreatedeptid())
            .append("userid", getUserid())
            .append("deptid", getDeptid())
            .append("attachmentid", getAttachmentid())
            .append("handledept", getHandledept())
            .append("handledeptid", getHandledeptid())
            .append("handlestaff", getHandlestaff())
            .append("affix", getAffix())
            .append("state", getState())
            .append("unitcode", getUnitcode())
            .append("unitname", getUnitname())
            .append("reportdate", getReportdate())
            .append("fourformno", getFourformno())
            .append("cluecode", getCluecode())
            .append("personcode", getPersoncode())
            .append("isunit", getIsunit())
            .append("personid", getPersonid())
            .append("personname", getPersonname())
            .append("personidcard", getPersonidcard())
            .append("birthdate", getBirthdate())
            .append("nation", getNation())
            .append("politic", getPolitic())
            .append("politicdetail", getPoliticdetail())
            .append("partytime", getPartytime())
            .append("personlevel", getPersonlevel())
            .append("personlevel2", getPersonlevel2())
            .append("secondlevel", getSecondlevel())
            .append("personduty", getPersonduty())
            .append("personunit", getPersonunit())
            .append("idcard", getIdcard())
            .append("isdeputy", getIsdeputy())
            .append("iscppcc", getIscppcc())
            .append("isddb", getIsddb())
            .append("issupervision", getIssupervision())
            .append("iscivilservant", getIscivilservant())
            .append("cluesource", getCluesource())
            .append("illegalbehavior", getIllegalbehavior())
            .append("lawbehaviour", getLawbehaviour())
            .append("handleunit", getHandleunit())
            .append("accepttime", getAccepttime())
            .append("handle1time", getHandle1time())
            .append("handle1censustime", getHandle1censustime())
            .append("handletype1first", getHandletype1first())
            .append("handletype1second", getHandletype1second())
            .append("handle2time", getHandle2time())
            .append("handle2censustime", getHandle2censustime())
            .append("handletype2first", getHandletype2first())
            .append("handletype2second", getHandletype2second())
            .append("handle3time", getHandle3time())
            .append("handle3censustime", getHandle3censustime())
            .append("handletype3first", getHandletype3first())
            .append("handletype3second", getHandletype3second())
            .append("handle4time", getHandle4time())
            .append("handle4censusttime", getHandle4censusttime())
            .append("handletype4first", getHandletype4first())
            .append("handletype4second", getHandletype4second())
            .append("handle5time", getHandle5time())
            .append("handle5census", getHandle5census())
            .append("handletype5first", getHandletype5first())
            .append("handletype5second", getHandletype5second())
            .append("handle6time", getHandle6time())
            .append("handle6censustime", getHandle6censustime())
            .append("handletype6first", getHandletype6first())
            .append("handletype6second", getHandletype6second())
            .append("handle7time", getHandle7time())
            .append("handle7censustime", getHandle7censustime())
            .append("handletype7first", getHandletype7first())
            .append("handletype7second", getHandletype7second())
            .append("handle8time", getHandle8time())
            .append("handle8censustime", getHandle8censustime())
            .append("handletype8first", getHandletype8first())
            .append("handletype8second", getHandletype8second())
            .append("orgmeasure", getOrgmeasure())
            .append("implementtime", getImplementtime())
            .append("ischecked", getIschecked())
            .append("iscomeclean", getIscomeclean())
            .append("retrieveeconomicloss", getRetrieveeconomicloss())
            .append("captureassetmoney", getCaptureassetmoney())
            .append("confiscatemoney", getConfiscatemoney())
            .append("compensatemoney", getCompensatemoney())
            .append("handinmoney", getHandinmoney())
            .append("lossmoney", getLossmoney())
            .append("mainclue", getMainclue())
            .append("handlereport", getHandlereport())
            .append("handleremark", getHandleremark())
            .append("isrepeated", getIsrepeated())
            .append("reportstaff", getReportstaff())
            .append("checkstaff", getCheckstaff())
            .append("isdeleted", getIsdeleted())
            .append("timestamp", getTimestamp())
            .append("reflectname", getReflectname())
            .append("reflectunit", getReflectunit())
            .append("reflectpost", getReflectpost())
            .append("reflectphone", getReflectphone())
            .append("reflectidcard", getReflectidcard())
            .append("personsex", getPersonsex())
            .append("status", getStatus())
            .append("clueno", getClueno())
            .append("personeducation", getPersoneducation())
            .append("personauthority", getPersonauthority())
            .append("jobtime", getJobtime())
            .append("isparty", getIsparty())
            .append("issupervision2", getIssupervision2())
            .append("issupervision3", getIssupervision3())
            .append("lawbehaviourother", getLawbehaviourother())
            .append("ischarge", getIscharge())
            .append("address", getAddress())
            .append("nativeplace", getNativeplace())
            .append("casecode", getCasecode())
            .append("personcontact", getPersoncontact())
            .append("receivestaut", getReceivestaut())
            .append("subcluesource", getSubcluesource())
            .append("handlestate", getHandlestate())
            .append("handletype", getHandletype())
            .append("handleresultlev1", getHandleresultlev1())
            .append("handleresultlev2", getHandleresultlev2())
            .append("handletime", getHandletime())
            .append("isduban", getIsduban())
            .append("ismeasureclue", getIsmeasureclue())
            .append("basehandledept", getBasehandledept())
            .append("basehandledeptid", getBasehandledeptid())
            .append("sourcetype", getSourcetype())
            .append("statisticaltime", getStatisticaltime())
            .append("storagestate", getStoragestate())
            .append("handletypechange", getHandletypechange())
            .append("mainclueid", getMainclueid())
            .append("createcompanyid", getCreatecompanyid())
            .append("iseliminateevil", getIseliminateevil())
            .append("isaccountability", getIsaccountability())
            .append("urgent", getUrgent())
            .append("handledecisiontime", getHandledecisiontime())
            .append("problemsummary", getProblemsummary())
            .append("isoldclue", getIsoldclue())
            .append("iseasyclue", getIseasyclue())
            .append("clueproperty", getClueproperty())
            .append("samelevel", getSamelevel())
            .append("samelevelopinion", getSamelevelopinion())
            .append("samelevelopinion2", getSamelevelopinion2())
            .append("handlemethodtype", getHandlemethodtype())
            .append("paizhuarea", getPaizhuarea())
            .append("isup", getIsup())
            .append("isupstatus", getIsupstatus())
            .append("updept", getUpdept())
            .append("addpeoplemainid", getAddpeoplemainid())
            .append("samelevelopinionaffix", getSamelevelopinionaffix())
            .append("issurrender", getIssurrender())
            .append("isjwcadre", getIsjwcadre())
            .append("agsdistributetime", getAgsdistributetime())
            .append("firsttransfertime", getFirsttransfertime())
            .append("firstdistributetime", getFirstdistributetime())
            .append("yuqistatus", getYuqistatus())
            .append("isexport", getIsexport())
            .append("handlerequire", getHandlerequire())
            .append("cadreauthority", getCadreauthority())
            .toString();
    }
}
