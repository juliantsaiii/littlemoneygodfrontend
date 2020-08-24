package com.ruoyi.project.sjwflowsys.domain;

import com.ruoyi.framework.web.domain.TreeEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 功能对象 function
 * 
 * @author littlemoneygod
 * @date 2020-08-23
 */
public class Function extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String pid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String name;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String url;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String sortcode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String controlid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String systemtypeid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Boolean deleted;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setPid(String pid) 
    {
        this.pid = pid;
    }

    public String getPid() 
    {
        return pid;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setSortcode(String sortcode) 
    {
        this.sortcode = sortcode;
    }

    public String getSortcode() 
    {
        return sortcode;
    }
    public void setControlid(String controlid) 
    {
        this.controlid = controlid;
    }

    public String getControlid() 
    {
        return controlid;
    }
    public void setSystemtypeid(String systemtypeid) 
    {
        this.systemtypeid = systemtypeid;
    }

    public String getSystemtypeid() 
    {
        return systemtypeid;
    }
    public void setDeleted(Boolean deleted) 
    {
        this.deleted = deleted;
    }

    public Boolean getDeleted() 
    {
        return deleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pid", getPid())
            .append("name", getName())
            .append("url", getUrl())
            .append("sortcode", getSortcode())
            .append("controlid", getControlid())
            .append("systemtypeid", getSystemtypeid())
            .append("deleted", getDeleted())
            .toString();
    }
}
