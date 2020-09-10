package com.ruoyi.project.sjwflowsys.service.impl;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sjwflowsys.mapper.WenshuMapper;
import com.ruoyi.project.sjwflowsys.domain.Wenshu;
import com.ruoyi.project.sjwflowsys.service.IWenshuService;

/**
 * 文书管理Service业务层处理
 * 
 * @author littlemoneygod
 * @date 2020-08-04
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class WenshuServiceImpl implements IWenshuService 
{
    @Autowired
    private WenshuMapper wenshuMapper;

    /**
     * 查询文书管理
     * 
     * @param id 文书管理ID
     * @return 文书管理
     */
    @Override
    public Wenshu selectWenshuById(String id)
    {
        return wenshuMapper.selectWenshuById(id);
    }

    /**
     * 查询文书管理列表
     * 
     * @param wenshu 文书管理
     * @return 文书管理
     */
    @Override
    public List<Wenshu> selectWenshuList(Wenshu wenshu)
    {
        return wenshuMapper.selectWenshuList(wenshu);
    }

    /**
     * 新增文书管理
     * 
     * @param wenshu 文书管理
     * @return 结果
     */
    @Override
    public int insertWenshu(Wenshu wenshu)
    {
        return wenshuMapper.insertWenshu(wenshu);
    }

    /**
     * 修改文书管理
     * 
     * @param wenshu 文书管理
     * @return 结果
     */
    @Override
    public int updateWenshu(Wenshu wenshu)
    {
        return wenshuMapper.updateWenshu(wenshu);
    }

    /**
     * 批量删除文书管理
     * 
     * @param ids 需要删除的文书管理ID
     * @return 结果
     */
    @Override
    public int deleteWenshuByIds(String[] ids)
    {
        return wenshuMapper.deleteWenshuByIds(ids);
    }

    /**
     * 删除文书管理信息
     * 
     * @param id 文书管理ID
     * @return 结果
     */
    @Override
    public int deleteWenshuById(String id)
    {
        return wenshuMapper.deleteWenshuById(id);
    }



}
