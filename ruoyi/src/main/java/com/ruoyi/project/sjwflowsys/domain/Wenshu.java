package com.ruoyi.project.sjwflowsys.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 文书管理对象 wenshu
 * 
 * @author littlemoneygod
 * @date 2020-08-04
 */
public class Wenshu extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String name;

    /** $column.columnComment */
    private String designation;

    /** $column.columnComment */
    private String process;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String url;

    /** $column.columnComment */
    private String sort;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String type;

    /** 需要签章的 */
    private Long qianzhang;

    /** $column.columnComment */
    private Boolean hascode;

    /** $column.columnComment */
    private String infotype;

    /** $column.columnComment */
    private String basetype;

    /** 秘级 */
    private String secretaries;

    /** 文书格式 */
    private String format;

    /** 文书描述 */
    @Excel(name = "文书描述")
    private String describe;

    /** 文书号缩写 */
    @Excel(name = "文书号缩写")
    private String docabbreviation;

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
    public void setDesignation(String designation) 
    {
        this.designation = designation;
    }

    public String getDesignation() 
    {
        return designation;
    }
    public void setProcess(String process) 
    {
        this.process = process;
    }

    public String getProcess() 
    {
        return process;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setSort(String sort) 
    {
        this.sort = sort;
    }

    public String getSort() 
    {
        return sort;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setQianzhang(Long qianzhang) 
    {
        this.qianzhang = qianzhang;
    }

    public Long getQianzhang() 
    {
        return qianzhang;
    }
    public void setHascode(Boolean hascode) 
    {
        this.hascode = hascode;
    }

    public Boolean getHascode() 
    {
        return hascode;
    }
    public void setInfotype(String infotype) 
    {
        this.infotype = infotype;
    }

    public String getInfotype() 
    {
        return infotype;
    }
    public void setBasetype(String basetype) 
    {
        this.basetype = basetype;
    }

    public String getBasetype() 
    {
        return basetype;
    }
    public void setSecretaries(String secretaries) 
    {
        this.secretaries = secretaries;
    }

    public String getSecretaries() 
    {
        return secretaries;
    }
    public void setFormat(String format) 
    {
        this.format = format;
    }

    public String getFormat() 
    {
        return format;
    }
    public void setDescribe(String describe) 
    {
        this.describe = describe;
    }

    public String getDescribe() 
    {
        return describe;
    }
    public void setDocabbreviation(String docabbreviation) 
    {
        this.docabbreviation = docabbreviation;
    }

    public String getDocabbreviation() 
    {
        return docabbreviation;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("designation", getDesignation())
            .append("process", getProcess())
            .append("url", getUrl())
            .append("sort", getSort())
            .append("type", getType())
            .append("qianzhang", getQianzhang())
            .append("hascode", getHascode())
            .append("infotype", getInfotype())
            .append("basetype", getBasetype())
            .append("secretaries", getSecretaries())
            .append("format", getFormat())
            .append("describe", getDescribe())
            .append("docabbreviation", getDocabbreviation())
            .toString();
    }
}
