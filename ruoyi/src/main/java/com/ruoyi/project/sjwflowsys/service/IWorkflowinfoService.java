package com.ruoyi.project.sjwflowsys.service;

import java.util.List;

import com.ruoyi.framework.web.domain.TreeEntityStr;
import com.ruoyi.framework.web.domain.TreeSelect;
import com.ruoyi.project.sjwflowsys.domain.Workflowinfo;

/**
 * 流程管理Service接口
 * 
 * @author littlemoneygod
 * @date 2020-08-04
 */
public interface IWorkflowinfoService 
{
    /**
     * 查询流程管理
     * 
     * @param id 流程管理ID
     * @return 流程管理
     */
    public Workflowinfo selectWorkflowinfoById(String id);

    /**
     * 查询流程管理列表
     * 
     * @param workflowinfo 流程管理
     * @return 流程管理集合
     */
    public List<Workflowinfo> selectWorkflowinfoList(Workflowinfo workflowinfo);

    /**
     * 新增流程管理
     * 
     * @param workflowinfo 流程管理
     * @return 结果
     */
    public int insertWorkflowinfo(Workflowinfo workflowinfo);

    /**
     * 修改流程管理
     * 
     * @param workflowinfo 流程管理
     * @return 结果
     */
    public int updateWorkflowinfo(Workflowinfo workflowinfo);

    /**
     * 批量删除流程管理
     * 
     * @param ids 需要删除的流程管理ID
     * @return 结果
     */
    public int deleteWorkflowinfoByIds(String[] ids);

    /**
     * 删除流程管理信息
     * 
     * @param id 流程管理ID
     * @return 结果
     */
    public int deleteWorkflowinfoById(String id);

    /**
     * 构建流程树
     * @param infos
     * @return
     */
    public List<TreeEntityStr> buildWorkflowInfoTreeSelect(List<Workflowinfo> infos);

}
