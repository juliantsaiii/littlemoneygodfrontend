package com.ruoyi.project.tool.dynamic.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 口令对象 dynamictoken
 * 
 * @author littlemoneygod
 * @date 2020-07-22
 */
public class Dynamictoken extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 密码 */
    private String token;

    /** 创建时间 */
    private Date protime;

    /** 密码时效 */
    private Long prescription;

    /** 当前时间 */
    private Date currenttime;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setToken(String token) 
    {
        this.token = token;
    }

    public String getToken() 
    {
        return token;
    }
    public void setProtime(Date protime) 
    {
        this.protime = protime;
    }

    public Date getProtime() 
    {
        return protime;
    }
    public void setPrescription(Long prescription) 
    {
        this.prescription = prescription;
    }

    public Long getPrescription() 
    {
        return prescription;
    }
    public void setCurrenttime(Date currenttime) 
    {
        this.currenttime = currenttime;
    }

    public Date getCurrenttime() 
    {
        return currenttime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("token", getToken())
            .append("protime", getProtime())
            .append("prescription", getPrescription())
            .append("currenttime", getCurrenttime())
            .toString();
    }
}
