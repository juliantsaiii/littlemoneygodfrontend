package com.ruoyi.framework.web.domain;

import com.ruoyi.project.sjwflowsys.domain.Dict;
import com.ruoyi.project.sjwflowsys.domain.Workflowinfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Tree基类
 * 
 * @author ruoyi
 */
public class TreeEntityStr extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    public TreeEntityStr()
    {

    }

    public TreeEntityStr(Dict dict){
        this.parentId = dict.getId();
        this.parentName = dict.getTitle();
        this.children = dict.getChildren();
    }

    public TreeEntityStr(Workflowinfo info){
        this.parentId = info.getType();
        this.parentName = info.getName();
        this.remark = info.getInfoname();
    }
    /** 父菜单名称 */
    private String parentName;

    /** 父菜单ID */
    private String parentId;

    /** 显示顺序 */
    private Integer orderNum;

    /** 祖级列表 */
    private String ancestors;

    /** 子部门 */
    private List<?> children = new ArrayList<>();

    /** 是否有子部门 */
    private Boolean hasChildren;
    /** 备注 */
    private String remark;

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    public String getParentName()
    {
        return parentName;
    }

    public void setParentName(String parentName)
    {
        this.parentName = parentName;
    }
    public Boolean getHasChildren()
    {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren)
    {
        this.hasChildren = hasChildren;
    }
    public String getParentId()
    {
        return parentId;
    }

    public void setParentId(String parentId)
    {
        this.parentId = parentId;
    }

    public Integer getOrderNum()
    {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum)
    {
        this.orderNum = orderNum;
    }

    public String getAncestors()
    {
        return ancestors;
    }

    public void setAncestors(String ancestors)
    {
        this.ancestors = ancestors;
    }

    public List<?> getChildren()
    {
        return children;
    }

    public void setChildren(List<?> children)
    {
        this.children = children;
    }
}
