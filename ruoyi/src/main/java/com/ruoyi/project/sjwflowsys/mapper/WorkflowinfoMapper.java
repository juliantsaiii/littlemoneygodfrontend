package com.ruoyi.project.sjwflowsys.mapper;

import java.util.List;
import com.ruoyi.project.sjwflowsys.domain.Workflowinfo;

/**
 * 流程管理Mapper接口
 * 
 * @author littlemoneygod
 * @date 2020-08-04
 */
public interface WorkflowinfoMapper 
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
     * 删除流程管理
     * 
     * @param id 流程管理ID
     * @return 结果
     */
    public int deleteWorkflowinfoById(String id);

    /**
     * 批量删除流程管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWorkflowinfoByIds(String[] ids);
}
