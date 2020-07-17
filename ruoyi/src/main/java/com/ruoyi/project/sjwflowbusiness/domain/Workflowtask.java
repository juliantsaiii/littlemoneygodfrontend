package com.ruoyi.project.sjwflowbusiness.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 流程记录对象 workflowtask
 * 
 * @author littlemoneygod
 * @date 2020-07-18
 */
public class Workflowtask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String id;

    /** $column.columnComment */
    private String previd;

    /** $column.columnComment */
    private String prevstepid;

    /** $column.columnComment */
    private String flowid;

    /** $column.columnComment */
    private String stepid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String stepname;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String instanceid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String clueid;

    /** $column.columnComment */
    private String groupid;

    /** $column.columnComment */
    private Long type;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String title;

    /** $column.columnComment */
    private String senderid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String sendername;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date sendertime;

    /** $column.columnComment */
    private String receiveid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String receivename;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date receivetime;

    /** $column.columnComment */
    private String touserid;

    /** $column.columnComment */
    private String tousername;

    /** $column.columnComment */
    private Date opentime;

    /** $column.columnComment */
    private Date completedtime;

    /** $column.columnComment */
    private Date completedtime1;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String comment;

    /** $column.columnComment */
    private Long issign;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long status;

    /** $column.columnComment */
    private String note;

    /** $column.columnComment */
    private Long sort;

    /** $column.columnComment */
    private String subflowgroupid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String handledeptid;

    /** $column.columnComment */
    private String subreceiveid;

    /** $column.columnComment */
    private String handlestaffid;

    /** $column.columnComment */
    private String handlestaffname;

    /** $column.columnComment */
    private String mainhandlestaffname;

    /** $column.columnComment */
    private String mainhandlestaffid;

    /** 线索组ID 措施中步骤监控需要使用 */
    private String cluegroupid;

    /** $column.columnComment */
    private Date rolltime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long stepstate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String databasename;

    /** $column.columnComment */
    private Long ismodify;

    /** $column.columnComment */
    private String formid;

    /** 调换科室原接收人或发送人（senderId或ReceiveId） */
    private String oldreceiveid;

    /** 调换科室原接收人或发送人（senderName或ReceiveName） */
    private String oldreceivename;

    /** 调换科室原接收人所在步骤（stepId） */
    private String oldstepid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String infotype;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Boolean isdeleted;

    /** $column.columnComment */
    private String spbid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Boolean isdeletd;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setPrevid(String previd) 
    {
        this.previd = previd;
    }

    public String getPrevid() 
    {
        return previd;
    }
    public void setPrevstepid(String prevstepid) 
    {
        this.prevstepid = prevstepid;
    }

    public String getPrevstepid() 
    {
        return prevstepid;
    }
    public void setFlowid(String flowid) 
    {
        this.flowid = flowid;
    }

    public String getFlowid() 
    {
        return flowid;
    }
    public void setStepid(String stepid) 
    {
        this.stepid = stepid;
    }

    public String getStepid() 
    {
        return stepid;
    }
    public void setStepname(String stepname) 
    {
        this.stepname = stepname;
    }

    public String getStepname() 
    {
        return stepname;
    }
    public void setInstanceid(String instanceid) 
    {
        this.instanceid = instanceid;
    }

    public String getInstanceid() 
    {
        return instanceid;
    }
    public void setClueid(String clueid) 
    {
        this.clueid = clueid;
    }

    public String getClueid() 
    {
        return clueid;
    }
    public void setGroupid(String groupid) 
    {
        this.groupid = groupid;
    }

    public String getGroupid() 
    {
        return groupid;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setSenderid(String senderid) 
    {
        this.senderid = senderid;
    }

    public String getSenderid() 
    {
        return senderid;
    }
    public void setSendername(String sendername) 
    {
        this.sendername = sendername;
    }

    public String getSendername() 
    {
        return sendername;
    }
    public void setSendertime(Date sendertime) 
    {
        this.sendertime = sendertime;
    }

    public Date getSendertime() 
    {
        return sendertime;
    }
    public void setReceiveid(String receiveid) 
    {
        this.receiveid = receiveid;
    }

    public String getReceiveid() 
    {
        return receiveid;
    }
    public void setReceivename(String receivename) 
    {
        this.receivename = receivename;
    }

    public String getReceivename() 
    {
        return receivename;
    }
    public void setReceivetime(Date receivetime) 
    {
        this.receivetime = receivetime;
    }

    public Date getReceivetime() 
    {
        return receivetime;
    }
    public void setTouserid(String touserid) 
    {
        this.touserid = touserid;
    }

    public String getTouserid() 
    {
        return touserid;
    }
    public void setTousername(String tousername) 
    {
        this.tousername = tousername;
    }

    public String getTousername() 
    {
        return tousername;
    }
    public void setOpentime(Date opentime) 
    {
        this.opentime = opentime;
    }

    public Date getOpentime() 
    {
        return opentime;
    }
    public void setCompletedtime(Date completedtime) 
    {
        this.completedtime = completedtime;
    }

    public Date getCompletedtime() 
    {
        return completedtime;
    }
    public void setCompletedtime1(Date completedtime1) 
    {
        this.completedtime1 = completedtime1;
    }

    public Date getCompletedtime1() 
    {
        return completedtime1;
    }
    public void setComment(String comment) 
    {
        this.comment = comment;
    }

    public String getComment() 
    {
        return comment;
    }
    public void setIssign(Long issign) 
    {
        this.issign = issign;
    }

    public Long getIssign() 
    {
        return issign;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setNote(String note) 
    {
        this.note = note;
    }

    public String getNote() 
    {
        return note;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setSubflowgroupid(String subflowgroupid) 
    {
        this.subflowgroupid = subflowgroupid;
    }

    public String getSubflowgroupid() 
    {
        return subflowgroupid;
    }
    public void setHandledeptid(String handledeptid) 
    {
        this.handledeptid = handledeptid;
    }

    public String getHandledeptid() 
    {
        return handledeptid;
    }
    public void setSubreceiveid(String subreceiveid) 
    {
        this.subreceiveid = subreceiveid;
    }

    public String getSubreceiveid() 
    {
        return subreceiveid;
    }
    public void setHandlestaffid(String handlestaffid) 
    {
        this.handlestaffid = handlestaffid;
    }

    public String getHandlestaffid() 
    {
        return handlestaffid;
    }
    public void setHandlestaffname(String handlestaffname) 
    {
        this.handlestaffname = handlestaffname;
    }

    public String getHandlestaffname() 
    {
        return handlestaffname;
    }
    public void setMainhandlestaffname(String mainhandlestaffname) 
    {
        this.mainhandlestaffname = mainhandlestaffname;
    }

    public String getMainhandlestaffname() 
    {
        return mainhandlestaffname;
    }
    public void setMainhandlestaffid(String mainhandlestaffid) 
    {
        this.mainhandlestaffid = mainhandlestaffid;
    }

    public String getMainhandlestaffid() 
    {
        return mainhandlestaffid;
    }
    public void setCluegroupid(String cluegroupid) 
    {
        this.cluegroupid = cluegroupid;
    }

    public String getCluegroupid() 
    {
        return cluegroupid;
    }
    public void setRolltime(Date rolltime) 
    {
        this.rolltime = rolltime;
    }

    public Date getRolltime() 
    {
        return rolltime;
    }
    public void setStepstate(Long stepstate) 
    {
        this.stepstate = stepstate;
    }

    public Long getStepstate() 
    {
        return stepstate;
    }
    public void setDatabasename(String databasename) 
    {
        this.databasename = databasename;
    }

    public String getDatabasename() 
    {
        return databasename;
    }
    public void setIsmodify(Long ismodify) 
    {
        this.ismodify = ismodify;
    }

    public Long getIsmodify() 
    {
        return ismodify;
    }
    public void setFormid(String formid) 
    {
        this.formid = formid;
    }

    public String getFormid() 
    {
        return formid;
    }
    public void setOldreceiveid(String oldreceiveid) 
    {
        this.oldreceiveid = oldreceiveid;
    }

    public String getOldreceiveid() 
    {
        return oldreceiveid;
    }
    public void setOldreceivename(String oldreceivename) 
    {
        this.oldreceivename = oldreceivename;
    }

    public String getOldreceivename() 
    {
        return oldreceivename;
    }
    public void setOldstepid(String oldstepid) 
    {
        this.oldstepid = oldstepid;
    }

    public String getOldstepid() 
    {
        return oldstepid;
    }
    public void setInfotype(String infotype) 
    {
        this.infotype = infotype;
    }

    public String getInfotype() 
    {
        return infotype;
    }
    public void setIsdeleted(Boolean isdeleted) 
    {
        this.isdeleted = isdeleted;
    }

    public Boolean getIsdeleted() 
    {
        return isdeleted;
    }
    public void setSpbid(String spbid) 
    {
        this.spbid = spbid;
    }

    public String getSpbid() 
    {
        return spbid;
    }
    public void setIsdeletd(Boolean isdeletd) 
    {
        this.isdeletd = isdeletd;
    }

    public Boolean getIsdeletd() 
    {
        return isdeletd;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("previd", getPrevid())
            .append("prevstepid", getPrevstepid())
            .append("flowid", getFlowid())
            .append("stepid", getStepid())
            .append("stepname", getStepname())
            .append("instanceid", getInstanceid())
            .append("clueid", getClueid())
            .append("groupid", getGroupid())
            .append("type", getType())
            .append("title", getTitle())
            .append("senderid", getSenderid())
            .append("sendername", getSendername())
            .append("sendertime", getSendertime())
            .append("receiveid", getReceiveid())
            .append("receivename", getReceivename())
            .append("receivetime", getReceivetime())
            .append("touserid", getTouserid())
            .append("tousername", getTousername())
            .append("opentime", getOpentime())
            .append("completedtime", getCompletedtime())
            .append("completedtime1", getCompletedtime1())
            .append("comment", getComment())
            .append("issign", getIssign())
            .append("status", getStatus())
            .append("note", getNote())
            .append("sort", getSort())
            .append("subflowgroupid", getSubflowgroupid())
            .append("handledeptid", getHandledeptid())
            .append("subreceiveid", getSubreceiveid())
            .append("handlestaffid", getHandlestaffid())
            .append("handlestaffname", getHandlestaffname())
            .append("mainhandlestaffname", getMainhandlestaffname())
            .append("mainhandlestaffid", getMainhandlestaffid())
            .append("cluegroupid", getCluegroupid())
            .append("rolltime", getRolltime())
            .append("stepstate", getStepstate())
            .append("databasename", getDatabasename())
            .append("ismodify", getIsmodify())
            .append("formid", getFormid())
            .append("oldreceiveid", getOldreceiveid())
            .append("oldreceivename", getOldreceivename())
            .append("oldstepid", getOldstepid())
            .append("infotype", getInfotype())
            .append("isdeleted", getIsdeleted())
            .append("spbid", getSpbid())
            .append("isdeletd", getIsdeletd())
            .toString();
    }
}
