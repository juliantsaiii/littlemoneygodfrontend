package com.ruoyi.project.sjwflowbusiness.mapper;

import java.util.List;
import com.ruoyi.project.sjwflowbusiness.domain.Workflowtask;

/**
 * 流程记录Mapper接口
 * 
 * @author littlemoneygod
 * @date 2020-07-18
 */
public interface WorkflowtaskMapper 
{
    /**
     * 查询流程记录
     * 
     * @param id 流程记录ID
     * @return 流程记录
     */
    public Workflowtask selectWorkflowtaskById(String id);

    /**
     * 查询流程记录列表
     * 
     * @param workflowtask 流程记录
     * @return 流程记录集合
     */
    public List<Workflowtask> selectWorkflowtaskList(Workflowtask workflowtask);

    /**
     * 新增流程记录
     * 
     * @param workflowtask 流程记录
     * @return 结果
     */
    public int insertWorkflowtask(Workflowtask workflowtask);

    /**
     * 修改流程记录
     * 
     * @param workflowtask 流程记录
     * @return 结果
     */
    public int updateWorkflowtask(Workflowtask workflowtask);

    /**
     * 删除流程记录
     * 
     * @param id 流程记录ID
     * @return 结果
     */
    public int deleteWorkflowtaskById(String id);

    /**
     * 批量删除流程记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWorkflowtaskByIds(String[] ids);
}
