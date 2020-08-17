package com.ruoyi.framework.web.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.project.sjwflowsys.domain.Dept;
import com.ruoyi.project.sjwflowsys.domain.User;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Treeselect树结构实体类
 * 
 * @author ruoyi
 */
public class TreeSelectStr implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 节点ID */
    private String id;

    /** 节点名称 */
    private String label;

    /** 子节点 */
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = TreeSelectStrFilter.class)
    private List<TreeSelectStr> children;

    public TreeSelectStr()
    {

    }

    public TreeSelectStr(Dept dept,List<TreeSelectStr> tree)
    {
        this.id = dept.getId();
        this.label = dept.getName();
        this.children = tree;
    }

    public TreeSelectStr(User user)
    {
        this.id = user.getId();
        this.label = user.getFullname();
        this.children = new ArrayList<>();
    }



    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public List<TreeSelectStr> getChildren()
    {
        return children;
    }

    public void setChildren(List<TreeSelectStr> children)
    {
        this.children = children;
    }
}
