package com.ruoyi.project.sjwflowbusiness.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sjwflowbusiness.mapper.WorkflowtaskMapper;
import com.ruoyi.project.sjwflowbusiness.domain.Workflowtask;
import com.ruoyi.project.sjwflowbusiness.service.IWorkflowtaskService;

/**
 * 流程记录Service业务层处理
 * 
 * @author littlemoneygod
 * @date 2020-07-18
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class WorkflowtaskServiceImpl implements IWorkflowtaskService 
{
    @Autowired
    private WorkflowtaskMapper workflowtaskMapper;

    /**
     * 查询流程记录
     * 
     * @param id 流程记录ID
     * @return 流程记录
     */
    @Override
    public Workflowtask selectWorkflowtaskById(String id)
    {
        return workflowtaskMapper.selectWorkflowtaskById(id);
    }

    /**
     * 查询流程记录列表
     * 
     * @param workflowtask 流程记录
     * @return 流程记录
     */
    @Override
    public List<Workflowtask> selectWorkflowtaskList(Workflowtask workflowtask)
    {
        return workflowtaskMapper.selectWorkflowtaskList(workflowtask);
    }

    /**
     * 新增流程记录
     * 
     * @param workflowtask 流程记录
     * @return 结果
     */
    @Override
    public int insertWorkflowtask(Workflowtask workflowtask)
    {
        return workflowtaskMapper.insertWorkflowtask(workflowtask);
    }

    /**
     * 修改流程记录
     * 
     * @param workflowtask 流程记录
     * @return 结果
     */
    @Override
    public int updateWorkflowtask(Workflowtask workflowtask)
    {
        return workflowtaskMapper.updateWorkflowtask(workflowtask);
    }

    /**
     * 批量删除流程记录
     * 
     * @param ids 需要删除的流程记录ID
     * @return 结果
     */
    @Override
    public int deleteWorkflowtaskByIds(String[] ids)
    {
        return workflowtaskMapper.deleteWorkflowtaskByIds(ids);
    }

    /**
     * 删除流程记录信息
     * 
     * @param id 流程记录ID
     * @return 结果
     */
    @Override
    public int deleteWorkflowtaskById(String id)
    {
        return workflowtaskMapper.deleteWorkflowtaskById(id);
    }

    /**
     * 获取workflowtask最新步骤所在流程
     * @param id
     * @return
     */
    @Override
    public List<Workflowtask> selectWorkflowtaskSteps(String id)
    {
        return workflowtaskMapper.selectWorkflowtaskSteps(id);
    };

    /**
     * 更换承办人
     * @param map
     * @return
     */
    public int changeRecever(Map<String,Object> map){
        return workflowtaskMapper.changeRecever(map);
    }

    /**
     * 根据clueid获取待办事项
     * @param id
     * @return
     */
    public Workflowtask getWaitTaskByClueID(String id){
        return workflowtaskMapper.getWaitTaskByClueID(id);
    }

    /**
     * 更换一个人的所有待办事项
     * @param map
     * @return
     */
    public int changeReceverByUser(Map<String,String> map){
        return workflowtaskMapper.changeReceverByUser(map);
    }
}
