package com.ruoyi.project.sjwflowsys.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 角色可访问数据对象 roledata
 * 
 * @author littlemoneygod
 * @date 2020-08-21
 */
public class Roledata extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String roleid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String belongcompanys;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String belongdepts;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String excludedepts;

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
    public void setBelongcompanys(String belongcompanys) 
    {
        this.belongcompanys = belongcompanys;
    }

    public String getBelongcompanys() 
    {
        return belongcompanys;
    }
    public void setBelongdepts(String belongdepts) 
    {
        this.belongdepts = belongdepts;
    }

    public String getBelongdepts() 
    {
        return belongdepts;
    }
    public void setExcludedepts(String excludedepts) 
    {
        this.excludedepts = excludedepts;
    }

    public String getExcludedepts() 
    {
        return excludedepts;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("roleid", getRoleid())
            .append("belongcompanys", getBelongcompanys())
            .append("belongdepts", getBelongdepts())
            .append("excludedepts", getExcludedepts())
            .toString();
    }
    public Roledata(){
    }

    public Roledata(Role role,String belongdepts){
        this.roleid = role.getId();
        this.belongcompanys = role.getCompanyid();
        this.belongdepts = belongdepts;
    }
}
