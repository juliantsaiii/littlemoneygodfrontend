package com.ruoyi.project.sjwflowsys.service.impl;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sjwflowsys.mapper.LeadersignatureMapper;
import com.ruoyi.project.sjwflowsys.domain.Leadersignature;
import com.ruoyi.project.sjwflowsys.service.ILeadersignatureService;

import javax.annotation.Resource;

/**
 * 签名管理Service业务层处理
 * 
 * @author littlemoneygod
 * @date 2020-08-20
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class LeadersignatureServiceImpl implements ILeadersignatureService 
{
    @Autowired
    private LeadersignatureMapper leadersignatureMapper;

    /**
     * 查询签名管理
     * 
     * @param id 签名管理ID
     * @return 签名管理
     */
    @Override
    public Leadersignature selectLeadersignatureById(String id)
    {
        return leadersignatureMapper.selectLeadersignatureById(id);
    }

    /**
     * 查询签名管理列表
     * 
     * @param leadersignature 签名管理
     * @return 签名管理
     */
    @Override
    public List<Leadersignature> selectLeadersignatureList(Leadersignature leadersignature)
    {
        return leadersignatureMapper.selectLeadersignatureList(leadersignature);
    }

    /**
     * 新增签名管理
     * 
     * @param leadersignature 签名管理
     * @return 结果
     */
    @Override
    public int insertLeadersignature(Leadersignature leadersignature)
    {
        return leadersignatureMapper.insertLeadersignature(leadersignature);
    }

    /**
     * 修改签名管理
     * 
     * @param leadersignature 签名管理
     * @return 结果
     */
    @Override
    public int updateLeadersignature(Leadersignature leadersignature)
    {
        return leadersignatureMapper.updateLeadersignature(leadersignature);
    }

    /**
     * 批量删除签名管理
     * 
     * @param ids 需要删除的签名管理ID
     * @return 结果
     */
    @Override
    public int deleteLeadersignatureByIds(String[] ids)
    {
        return leadersignatureMapper.deleteLeadersignatureByIds(ids);
    }

    /**
     * 删除签名管理信息
     * 
     * @param id 签名管理ID
     * @return 结果
     */
    @Override
    public int deleteLeadersignatureById(String id)
    {
        return leadersignatureMapper.deleteLeadersignatureById(id);
    }
}
