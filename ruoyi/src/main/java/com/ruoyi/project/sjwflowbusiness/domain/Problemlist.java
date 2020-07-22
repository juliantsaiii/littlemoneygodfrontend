package com.ruoyi.project.sjwflowbusiness.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 运维记录对象 problemlist
 * 
 * @author littlemoneygod
 * @date 2020-07-22
 */
public class Problemlist extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 该表为问题整改清单主表 */
    private String id;

    /** 主问题ID */
    private String mainid;

    /** 创建用户ID */
    private String createuserid;

    /** 创建者 */
    @Excel(name = "创建者")
    private String createusername;

    /** 创建部门ID */
    private String createdeptid;

    /** 创建部门 */
    @Excel(name = "创建部门")
    private String createdeptname;

    /** 序号 */
    private String number;

    /** 创建日期 */
    @Excel(name = "创建日期")
    private String createdate;

    /** 联系人所在地区 */
    @Excel(name = "联系人所在地区")
    private String parea;

    /** 所在机关 */
    @Excel(name = "所在机关")
    private String poffice;

    /** 所在科室 */
    @Excel(name = "所在科室")
    private String pclass;

    /** 联系人姓名 */
    @Excel(name = "联系人姓名")
    private String pname;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String pphone;

    /** 服务类型 */
    @Excel(name = "服务类型")
    private String servicetype;

    /** 问题描述 */
    @Excel(name = "问题描述")
    private String problem;

    /** 线索编号/信访编号 */
    @Excel(name = "线索编号/信访编号")
    private String cluecode;

    /** 处理方式 */
    @Excel(name = "处理方式")
    private String handletype;

    /** 处理状态 */
    @Excel(name = "处理状态")
    private String handlestate;

    /** 是否上报 */
    @Excel(name = "是否上报")
    private String isup;

    /** 处理者 */
    @Excel(name = "处理者")
    private String handlestaff;

    /** 0:内勤创建,1:到达主任,3:到达党风室,2:党风室已读 */
    @Excel(name = "0:内勤创建,1:到达主任,3:到达党风室,2:党风室已读")
    private Long currentstep;

    /** 0:为完成；1：完成 */
    @Excel(name = "0:为完成；1：完成")
    private Long status;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "0:为完成；1：完成", width = 30, dateFormat = "yyyy-MM-dd")
    private Date timestamp;

    /** $column.columnComment */
    private Boolean isdeleted;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setMainid(String mainid) 
    {
        this.mainid = mainid;
    }

    public String getMainid() 
    {
        return mainid;
    }
    public void setCreateuserid(String createuserid) 
    {
        this.createuserid = createuserid;
    }

    public String getCreateuserid() 
    {
        return createuserid;
    }
    public void setCreateusername(String createusername) 
    {
        this.createusername = createusername;
    }

    public String getCreateusername() 
    {
        return createusername;
    }
    public void setCreatedeptid(String createdeptid) 
    {
        this.createdeptid = createdeptid;
    }

    public String getCreatedeptid() 
    {
        return createdeptid;
    }
    public void setCreatedeptname(String createdeptname) 
    {
        this.createdeptname = createdeptname;
    }

    public String getCreatedeptname() 
    {
        return createdeptname;
    }
    public void setNumber(String number) 
    {
        this.number = number;
    }

    public String getNumber() 
    {
        return number;
    }
    public void setCreatedate(String createdate) 
    {
        this.createdate = createdate;
    }

    public String getCreatedate() 
    {
        return createdate;
    }
    public void setParea(String parea) 
    {
        this.parea = parea;
    }

    public String getParea() 
    {
        return parea;
    }
    public void setPoffice(String poffice) 
    {
        this.poffice = poffice;
    }

    public String getPoffice() 
    {
        return poffice;
    }
    public void setPclass(String pclass) 
    {
        this.pclass = pclass;
    }

    public String getPclass() 
    {
        return pclass;
    }
    public void setPname(String pname) 
    {
        this.pname = pname;
    }

    public String getPname() 
    {
        return pname;
    }
    public void setPphone(String pphone) 
    {
        this.pphone = pphone;
    }

    public String getPphone() 
    {
        return pphone;
    }
    public void setServicetype(String servicetype) 
    {
        this.servicetype = servicetype;
    }

    public String getServicetype() 
    {
        return servicetype;
    }
    public void setProblem(String problem) 
    {
        this.problem = problem;
    }

    public String getProblem() 
    {
        return problem;
    }
    public void setCluecode(String cluecode) 
    {
        this.cluecode = cluecode;
    }

    public String getCluecode() 
    {
        return cluecode;
    }
    public void setHandletype(String handletype) 
    {
        this.handletype = handletype;
    }

    public String getHandletype() 
    {
        return handletype;
    }
    public void setHandlestate(String handlestate) 
    {
        this.handlestate = handlestate;
    }

    public String getHandlestate() 
    {
        return handlestate;
    }
    public void setIsup(String isup) 
    {
        this.isup = isup;
    }

    public String getIsup() 
    {
        return isup;
    }
    public void setHandlestaff(String handlestaff) 
    {
        this.handlestaff = handlestaff;
    }

    public String getHandlestaff() 
    {
        return handlestaff;
    }
    public void setCurrentstep(Long currentstep) 
    {
        this.currentstep = currentstep;
    }

    public Long getCurrentstep() 
    {
        return currentstep;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setTimestamp(Date timestamp) 
    {
        this.timestamp = timestamp;
    }

    public Date getTimestamp() 
    {
        return timestamp;
    }
    public void setIsdeleted(Boolean isdeleted) 
    {
        this.isdeleted = isdeleted;
    }

    public Boolean getIsdeleted() 
    {
        return isdeleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("mainid", getMainid())
            .append("createuserid", getCreateuserid())
            .append("createusername", getCreateusername())
            .append("createdeptid", getCreatedeptid())
            .append("createdeptname", getCreatedeptname())
            .append("number", getNumber())
            .append("createdate", getCreatedate())
            .append("parea", getParea())
            .append("poffice", getPoffice())
            .append("pclass", getPclass())
            .append("pname", getPname())
            .append("pphone", getPphone())
            .append("servicetype", getServicetype())
            .append("problem", getProblem())
            .append("cluecode", getCluecode())
            .append("handletype", getHandletype())
            .append("handlestate", getHandlestate())
            .append("isup", getIsup())
            .append("handlestaff", getHandlestaff())
            .append("currentstep", getCurrentstep())
            .append("status", getStatus())
            .append("timestamp", getTimestamp())
            .append("isdeleted", getIsdeleted())
            .toString();
    }
}
