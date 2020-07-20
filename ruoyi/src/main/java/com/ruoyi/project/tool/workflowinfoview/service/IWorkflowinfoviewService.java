package com.ruoyi.project.tool.workflowinfoview.service;

import java.util.List;
import com.ruoyi.project.tool.workflowinfoview.domain.Workflowinfoview;

/**
 * 流程图设计Service接口
 * 
 * @author littlemoneygod
 * @date 2020-07-20
 */
public interface IWorkflowinfoviewService 
{
    /**
     * 查询流程图设计
     * 
     * @param id 流程图设计ID
     * @return 流程图设计
     */
    public Workflowinfoview selectWorkflowinfoviewById(String id);

    /**
     * 查询流程图设计列表
     * 
     * @param workflowinfoview 流程图设计
     * @return 流程图设计集合
     */
    public List<Workflowinfoview> selectWorkflowinfoviewList(Workflowinfoview workflowinfoview);

    /**
     * 新增流程图设计
     * 
     * @param workflowinfoview 流程图设计
     * @return 结果
     */
    public int insertWorkflowinfoview(Workflowinfoview workflowinfoview);

    /**
     * 修改流程图设计
     * 
     * @param workflowinfoview 流程图设计
     * @return 结果
     */
    public int updateWorkflowinfoview(Workflowinfoview workflowinfoview);

    /**
     * 批量删除流程图设计
     * 
     * @param ids 需要删除的流程图设计ID
     * @return 结果
     */
    public int deleteWorkflowinfoviewByIds(String[] ids);

    /**
     * 删除流程图设计信息
     * 
     * @param id 流程图设计ID
     * @return 结果
     */
    public int deleteWorkflowinfoviewById(String id);
}
