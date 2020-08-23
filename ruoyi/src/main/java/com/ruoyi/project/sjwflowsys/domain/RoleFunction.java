package com.ruoyi.project.sjwflowsys.domain;

import com.ruoyi.common.core.lang.UUID;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 角色可访问功能对象 role_function
 * 
 * @author littlemoneygod
 * @date 2020-08-23
 */
public class RoleFunction extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String roleid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String functionid;

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
    public void setRoleid(String roleid) 
    {
        this.roleid = roleid;
    }

    public String getRoleid() 
    {
        return roleid;
    }
    public void setFunctionid(String functionid) 
    {
        this.functionid = functionid;
    }

    public String getFunctionid() 
    {
        return functionid;
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
            .append("roleid", getRoleid())
            .append("functionid", getFunctionid())
            .append("deleted", getDeleted())
            .toString();
    }

    public RoleFunction(){};
    public RoleFunction(String roleid,String functionid){
        this.roleid = roleid;
        this.functionid = functionid;
        this.id = UUID.randomUUID().toString();
    }
}
