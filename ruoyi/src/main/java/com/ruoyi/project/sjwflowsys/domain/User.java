package com.ruoyi.project.sjwflowsys.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 业务平台用户管理对象 user
 * 
 * @author littlemoneygod
 * @date 2020-07-15
 */
public class User extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /**  */
    private String pid;

    /** $column.columnComment */
    private String unitid;

    /** 部门ID */
    private String deptid;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String deptname;

    /** 用户名 */
    @Excel(name = "用户名")
    private String name;

    /** 密码 */
    private String password;

    /** 姓名 */
    @Excel(name = "姓名")
    private String fullname;

    /** 身份证 */
    private String idcard;

    /** 机构ID */
    private String companyid;

    /** 机构名 */
    @Excel(name = "机构名")
    private String companyname;

    /** 排序吗 */
    private Long sortcode;

    /** 修改时间 */
    private Date edittime;

    /** 过渡页显示 */
    private String guoduye;

    /** 是否删除 */
    private Boolean deleted;

    /** 手机号码 */
    private String phone;

    /** 人员类型 */
    @Excel(name = "人员类型")
    private Long usertype;

    /** 激活 */
    @Excel(name = "激活")
    private String activated;

    /** $column.columnComment */
    private String analysisroot;

    /** 是否管理员 */
    private Long isadmin;

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
    public void setUnitid(String unitid) 
    {
        this.unitid = unitid;
    }

    public String getUnitid() 
    {
        return unitid;
    }
    public void setDeptid(String deptid) 
    {
        this.deptid = deptid;
    }

    public String getDeptid() 
    {
        return deptid;
    }
    public void setDeptname(String deptname) 
    {
        this.deptname = deptname;
    }

    public String getDeptname() 
    {
        return deptname;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setFullname(String fullname) 
    {
        this.fullname = fullname;
    }

    public String getFullname() 
    {
        return fullname;
    }
    public void setIdcard(String idcard) 
    {
        this.idcard = idcard;
    }

    public String getIdcard() 
    {
        return idcard;
    }
    public void setCompanyid(String companyid) 
    {
        this.companyid = companyid;
    }

    public String getCompanyid() 
    {
        return companyid;
    }
    public void setCompanyname(String companyname) 
    {
        this.companyname = companyname;
    }

    public String getCompanyname() 
    {
        return companyname;
    }
    public void setSortcode(Long sortcode) 
    {
        this.sortcode = sortcode;
    }

    public Long getSortcode() 
    {
        return sortcode;
    }
    public void setEdittime(Date edittime) 
    {
        this.edittime = edittime;
    }

    public Date getEdittime() 
    {
        return edittime;
    }
    public void setGuoduye(String guoduye) 
    {
        this.guoduye = guoduye;
    }

    public String getGuoduye() 
    {
        return guoduye;
    }
    public void setDeleted(Boolean deleted) 
    {
        this.deleted = deleted;
    }

    public Boolean getDeleted() 
    {
        return deleted;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setUsertype(Long usertype) 
    {
        this.usertype = usertype;
    }

    public Long getUsertype() 
    {
        return usertype;
    }
    public void setActivated(String activated) 
    {
        this.activated = activated;
    }

    public String getActivated() 
    {
        return activated;
    }
    public void setAnalysisroot(String analysisroot) 
    {
        this.analysisroot = analysisroot;
    }

    public String getAnalysisroot() 
    {
        return analysisroot;
    }
    public void setIsadmin(Long isadmin) 
    {
        this.isadmin = isadmin;
    }

    public Long getIsadmin() 
    {
        return isadmin;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String imageUrl;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pid", getPid())
            .append("unitid", getUnitid())
            .append("deptid", getDeptid())
            .append("deptname", getDeptname())
            .append("name", getName())
            .append("password", getPassword())
            .append("fullname", getFullname())
            .append("idcard", getIdcard())
            .append("companyid", getCompanyid())
            .append("companyname", getCompanyname())
            .append("sortcode", getSortcode())
            .append("edittime", getEdittime())
            .append("guoduye", getGuoduye())
            .append("deleted", getDeleted())
            .append("phone", getPhone())
            .append("usertype", getUsertype())
            .append("activated", getActivated())
            .append("analysisroot", getAnalysisroot())
            .append("isadmin", getIsadmin())
            .toString();
    }
}
