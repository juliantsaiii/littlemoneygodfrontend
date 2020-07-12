package com.ruoyi.project.sjwflowsys.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.TreeEntity;

/**
 * 部门对象 dept
 * 
 * @author littlemoneygod
 * @date 2020-07-06
 */
public class Dept extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 父级ID */
    private String pid;

    /** 是否有子节点*/
    private Boolean hasChildren;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String name;

    /** 部门属性 */
    private String category;

    /** 排序 */
    private Long sortcode;

    /** 编码 */
    private String serialnumber;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Boolean deleted;

    /** 流程类型 */
    @Excel(name = "流程类型")
    private String flowinfotype;

    /** 部门类型 */
    @Excel(name = "部门类型")
    private String depttype;

    /** 线索编号代码 */
    private String xscode;

    /** 派驻地点 */
    private String paizhuarea;

    /** 分管常委ID */
    private String chargepersonid;

    /** 分管常委姓名 */
    @Excel(name = "分管常委姓名")
    private String chargepersonname;

    /** 分管副书记ID */
    private String chargeleaderid;

    /** 分管副书记姓名 */
    @Excel(name = "分管副书记姓名")
    private String chargeleadername;

    /** 书记ID */
    private String mainleaderid;

    /** 书记姓名 */
    @Excel(name = "书记姓名")
    private String mainleadername;

    /** 派驻副组长ID */
    private String paizhusubleaderid;

    /** 派驻副组长姓名 */
    @Excel(name = "派驻副组长姓名")
    private String paizhusubleadername;

    /** 派驻组长ID */
    private String paizhumainleaderid;

    /** 派驻组长姓名 */
    @Excel(name = "派驻组长姓名")
    private String paizhumainleadername;

    public void setId(String id) 
    {
        this.id = id;
    }
    public String getId()
    {
        return id;
    }
    public Boolean getHasChildren()
    {
        return hasChildren;
    }
    public void setHasChildren(Boolean hasChildren)
    {
        this.hasChildren = hasChildren;
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
    public void setCategory(String category) 
    {
        this.category = category;
    }

    public String getCategory() 
    {
        return category;
    }
    public void setSortcode(Long sortcode) 
    {
        this.sortcode = sortcode;
    }

    public Long getSortcode() 
    {
        return sortcode;
    }
    public void setSerialnumber(String serialnumber) 
    {
        this.serialnumber = serialnumber;
    }

    public String getSerialnumber() 
    {
        return serialnumber;
    }
    public void setDeleted(Boolean deleted) 
    {
        this.deleted = deleted;
    }

    public Boolean getDeleted() 
    {
        return deleted;
    }
    public void setFlowinfotype(String flowinfotype) 
    {
        this.flowinfotype = flowinfotype;
    }

    public String getFlowinfotype() 
    {
        return flowinfotype;
    }
    public void setDepttype(String depttype) 
    {
        this.depttype = depttype;
    }

    public String getDepttype() 
    {
        return depttype;
    }
    public void setXscode(String xscode) 
    {
        this.xscode = xscode;
    }

    public String getXscode() 
    {
        return xscode;
    }
    public void setPaizhuarea(String paizhuarea) 
    {
        this.paizhuarea = paizhuarea;
    }

    public String getPaizhuarea() 
    {
        return paizhuarea;
    }
    public void setChargepersonid(String chargepersonid) 
    {
        this.chargepersonid = chargepersonid;
    }

    public String getChargepersonid() 
    {
        return chargepersonid;
    }
    public void setChargepersonname(String chargepersonname) 
    {
        this.chargepersonname = chargepersonname;
    }

    public String getChargepersonname() 
    {
        return chargepersonname;
    }
    public void setChargeleaderid(String chargeleaderid) 
    {
        this.chargeleaderid = chargeleaderid;
    }

    public String getChargeleaderid() 
    {
        return chargeleaderid;
    }
    public void setChargeleadername(String chargeleadername) 
    {
        this.chargeleadername = chargeleadername;
    }

    public String getChargeleadername() 
    {
        return chargeleadername;
    }
    public void setMainleaderid(String mainleaderid) 
    {
        this.mainleaderid = mainleaderid;
    }

    public String getMainleaderid() 
    {
        return mainleaderid;
    }
    public void setMainleadername(String mainleadername) 
    {
        this.mainleadername = mainleadername;
    }

    public String getMainleadername() 
    {
        return mainleadername;
    }
    public void setPaizhusubleaderid(String paizhusubleaderid) 
    {
        this.paizhusubleaderid = paizhusubleaderid;
    }

    public String getPaizhusubleaderid() 
    {
        return paizhusubleaderid;
    }
    public void setPaizhusubleadername(String paizhusubleadername) 
    {
        this.paizhusubleadername = paizhusubleadername;
    }

    public String getPaizhusubleadername() 
    {
        return paizhusubleadername;
    }
    public void setPaizhumainleaderid(String paizhumainleaderid) 
    {
        this.paizhumainleaderid = paizhumainleaderid;
    }

    public String getPaizhumainleaderid() 
    {
        return paizhumainleaderid;
    }
    public void setPaizhumainleadername(String paizhumainleadername) 
    {
        this.paizhumainleadername = paizhumainleadername;
    }

    public String getPaizhumainleadername() 
    {
        return paizhumainleadername;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pid", getPid())
            .append("name", getName())
            .append("category", getCategory())
            .append("sortcode", getSortcode())
            .append("serialnumber", getSerialnumber())
            .append("deleted", getDeleted())
            .append("flowinfotype", getFlowinfotype())
            .append("depttype", getDepttype())
            .append("xscode", getXscode())
            .append("paizhuarea", getPaizhuarea())
            .append("chargepersonid", getChargepersonid())
            .append("chargepersonname", getChargepersonname())
            .append("chargeleaderid", getChargeleaderid())
            .append("chargeleadername", getChargeleadername())
            .append("mainleaderid", getMainleaderid())
            .append("mainleadername", getMainleadername())
            .append("paizhusubleaderid", getPaizhusubleaderid())
            .append("paizhusubleadername", getPaizhusubleadername())
            .append("paizhumainleaderid", getPaizhumainleaderid())
            .append("paizhumainleadername", getPaizhumainleadername())
            .toString();
    }
}
