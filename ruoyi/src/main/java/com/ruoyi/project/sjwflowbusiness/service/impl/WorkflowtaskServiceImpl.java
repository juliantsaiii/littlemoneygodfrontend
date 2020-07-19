package com.ruoyi.project.sjwflowbusiness.service.impl;

import java.util.List;
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
}