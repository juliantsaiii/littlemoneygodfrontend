package com.ruoyi.project.sjwflowsys.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 盖章信息对象 wenshuseal
 * 
 * @author littlemoneygod
 * @date 2020-08-26
 */
public class Wenshuseal extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String wenshuid;

    /** 章名 */
    @Excel(name = "章名")
    private String ename;

    /** 页码 */
    @Excel(name = "页码")
    private Long pagenum;

    /** x坐标 */
    @Excel(name = "x坐标")
    private String positionx;

    /** y坐标 */
    @Excel(name = "y坐标")
    private String positiony;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setWenshuid(String wenshuid) 
    {
        this.wenshuid = wenshuid;
    }

    public String getWenshuid() 
    {
        return wenshuid;
    }
    public void setEname(String ename) 
    {
        this.ename = ename;
    }

    public String getEname() 
    {
        return ename;
    }
    public void setPagenum(Long pagenum) 
    {
        this.pagenum = pagenum;
    }

    public Long getPagenum() 
    {
        return pagenum;
    }
    public void setPositionx(String positionx) 
    {
        this.positionx = positionx;
    }

    public String getPositionx() 
    {
        return positionx;
    }
    public void setPositiony(String positiony) 
    {
        this.positiony = positiony;
    }

    public String getPositiony() 
    {
        return positiony;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("wenshuid", getWenshuid())
            .append("ename", getEname())
            .append("pagenum", getPagenum())
            .append("positionx", getPositionx())
            .append("positiony", getPositiony())
            .toString();
    }
    public Wenshuseal(){
        this.pagenum = (long)1;
    }
}
