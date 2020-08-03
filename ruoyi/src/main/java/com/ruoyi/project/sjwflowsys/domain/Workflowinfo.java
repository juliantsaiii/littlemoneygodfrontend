package com.ruoyi.project.sjwflowsys.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 流程管理对象 workflowinfo
 * 
 * @author littlemoneygod
 * @date 2020-08-04
 */
public class Workflowinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String name;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String type;

    /** $column.columnComment */
    private String manager;

    /** $column.columnComment */
    private String instancemanager;

    /** $column.columnComment */
    private Date createdate;

    /** $column.columnComment */
    private String createuserid;

    /** $column.columnComment */
    private String designjson;

    /** $column.columnComment */
    private Date installdate;

    /** $column.columnComment */
    private String installuserid;

    /** $column.columnComment */
    private String runjson;

    /** $column.columnComment */
    private Long status;

    /** $column.columnComment */
    private String infoname;

    /** $column.columnComment */
    private String infotype;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setManager(String manager) 
    {
        this.manager = manager;
    }

    public String getManager() 
    {
        return manager;
    }
    public void setInstancemanager(String instancemanager) 
    {
        this.instancemanager = instancemanager;
    }

    public String getInstancemanager() 
    {
        return instancemanager;
    }
    public void setCreatedate(Date createdate) 
    {
        this.createdate = createdate;
    }

    public Date getCreatedate() 
    {
        return createdate;
    }
    public void setCreateuserid(String createuserid) 
    {
        this.createuserid = createuserid;
    }

    public String getCreateuserid() 
    {
        return createuserid;
    }
    public void setDesignjson(String designjson) 
    {
        this.designjson = designjson;
    }

    public String getDesignjson() 
    {
        return designjson;
    }
    public void setInstalldate(Date installdate) 
    {
        this.installdate = installdate;
    }

    public Date getInstalldate() 
    {
        return installdate;
    }
    public void setInstalluserid(String installuserid) 
    {
        this.installuserid = installuserid;
    }

    public String getInstalluserid() 
    {
        return installuserid;
    }
    public void setRunjson(String runjson) 
    {
        this.runjson = runjson;
    }

    public String getRunjson() 
    {
        return runjson;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setInfoname(String infoname) 
    {
        this.infoname = infoname;
    }

    public String getInfoname() 
    {
        return infoname;
    }
    public void setInfotype(String infotype) 
    {
        this.infotype = infotype;
    }

    public String getInfotype() 
    {
        return infotype;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("type", getType())
            .append("manager", getManager())
            .append("instancemanager", getInstancemanager())
            .append("createdate", getCreatedate())
            .append("createuserid", getCreateuserid())
            .append("designjson", getDesignjson())
            .append("installdate", getInstalldate())
            .append("installuserid", getInstalluserid())
            .append("runjson", getRunjson())
            .append("status", getStatus())
            .append("infoname", getInfoname())
            .append("infotype", getInfotype())
            .toString();
    }
}
