package com.ruoyi.project.tool.workflowinfoview.service.impl;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.tool.workflowinfoview.mapper.WorkflowinfoviewMapper;
import com.ruoyi.project.tool.workflowinfoview.domain.Workflowinfoview;
import com.ruoyi.project.tool.workflowinfoview.service.IWorkflowinfoviewService;

/**
 * 流程图设计Service业务层处理
 * 
 * @author littlemoneygod
 * @date 2020-07-20
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class WorkflowinfoviewServiceImpl implements IWorkflowinfoviewService 
{
    @Autowired
    private WorkflowinfoviewMapper workflowinfoviewMapper;

    /**
     * 查询流程图设计
     * 
     * @param id 流程图设计ID
     * @return 流程图设计
     */
    @Override
    public Workflowinfoview selectWorkflowinfoviewById(String id)
    {
        return workflowinfoviewMapper.selectWorkflowinfoviewById(id);
    }

    /**
     * 查询流程图设计列表
     * 
     * @param workflowinfoview 流程图设计
     * @return 流程图设计
     */
    @Override
    public List<Workflowinfoview> selectWorkflowinfoviewList(Workflowinfoview workflowinfoview)
    {
        return workflowinfoviewMapper.selectWorkflowinfoviewList(workflowinfoview);
    }

    /**
     * 新增流程图设计
     * 
     * @param workflowinfoview 流程图设计
     * @return 结果
     */
    @Override
    public int insertWorkflowinfoview(Workflowinfoview workflowinfoview)
    {
        return workflowinfoviewMapper.insertWorkflowinfoview(workflowinfoview);
    }

    /**
     * 修改流程图设计
     * 
     * @param workflowinfoview 流程图设计
     * @return 结果
     */
    @Override
    public int updateWorkflowinfoview(Workflowinfoview workflowinfoview)
    {
        return workflowinfoviewMapper.updateWorkflowinfoview(workflowinfoview);
    }

    /**
     * 批量删除流程图设计
     * 
     * @param ids 需要删除的流程图设计ID
     * @return 结果
     */
    @Override
    public int deleteWorkflowinfoviewByIds(String[] ids)
    {
        return workflowinfoviewMapper.deleteWorkflowinfoviewByIds(ids);
    }

    /**
     * 删除流程图设计信息
     * 
     * @param id 流程图设计ID
     * @return 结果
     */
    @Override
    public int deleteWorkflowinfoviewById(String id)
    {
        return workflowinfoviewMapper.deleteWorkflowinfoviewById(id);
    }
}
