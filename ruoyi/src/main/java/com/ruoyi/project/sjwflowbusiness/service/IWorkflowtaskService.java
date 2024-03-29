package com.ruoyi.project.sjwflowbusiness.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.sjwflowbusiness.domain.Workflowtask;

/**
 * 流程记录Service接口
 * 
 * @author littlemoneygod
 * @date 2020-07-18
 */
public interface IWorkflowtaskService 
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
     * 批量删除流程记录
     * 
     * @param ids 需要删除的流程记录ID
     * @return 结果
     */
    public int deleteWorkflowtaskByIds(String[] ids);

    /**
     * 删除流程记录信息
     * 
     * @param id 流程记录ID
     * @return 结果
     */
    public int deleteWorkflowtaskById(String id);

    /**
     * 获取workflowtask最新步骤所在流程
     * @param id
     * @return
     */
    public List<Workflowtask> selectWorkflowtaskSteps(String id);

    /**
     * 更换承办人
     * @param map
     * @return
     */
    public int changeRecever(Map<String,Object> map);

    /**
     * 根据clueid获取待办事项
     * @param id
     * @return
     */
    public Workflowtask getWaitTaskByClueID(String id);

    /**
     * 更换一个人的所有待办事项
     * @param map
     * @return
     */
    public int changeReceverByUser(Map<String,String> map);
}
