package com.ruoyi.project.sjwflowsys.service.impl;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.sjwflowsys.domain.Wenshu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sjwflowsys.mapper.WenshusealMapper;
import com.ruoyi.project.sjwflowsys.domain.Wenshuseal;
import com.ruoyi.project.sjwflowsys.service.IWenshusealService;

/**
 * 盖章信息Service业务层处理
 * 
 * @author littlemoneygod
 * @date 2020-08-26
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class WenshusealServiceImpl implements IWenshusealService 
{
    @Autowired
    private WenshusealMapper wenshusealMapper;

    /**
     * 查询盖章信息
     * 
     * @param id 盖章信息ID
     * @return 盖章信息
     */
    @Override
    public Wenshuseal selectWenshusealById(String id)
    {
        List<Wenshuseal> wssList = wenshusealMapper.selectWenshusealById(id);
        Wenshuseal wss = new Wenshuseal();
        if(wssList.size()!=0){
            wss = wssList.get(0);
        }
        return wss;
    }

    /**
     * 查询盖章信息列表
     * 
     * @param wenshuseal 盖章信息
     * @return 盖章信息
     */
    @Override
    public List<Wenshuseal> selectWenshusealList(Wenshuseal wenshuseal)
    {
        return wenshusealMapper.selectWenshusealList(wenshuseal);
    }

    /**
     * 新增盖章信息
     * 
     * @param wenshuseal 盖章信息
     * @return 结果
     */
    @Override
    public int insertWenshuseal(Wenshuseal wenshuseal)
    {
        return wenshusealMapper.insertWenshuseal(wenshuseal);
    }

    /**
     * 修改盖章信息
     * 
     * @param wenshuseal 盖章信息
     * @return 结果
     */
    @Override
    public int updateWenshuseal(Wenshuseal wenshuseal)
    {
        return wenshusealMapper.updateWenshuseal(wenshuseal);
    }

    /**
     * 批量删除盖章信息
     * 
     * @param ids 需要删除的盖章信息ID
     * @return 结果
     */
    @Override
    public int deleteWenshusealByIds(String[] ids)
    {
        return wenshusealMapper.deleteWenshusealByIds(ids);
    }

    /**
     * 删除盖章信息信息
     * 
     * @param id 盖章信息ID
     * @return 结果
     */
    @Override
    public int deleteWenshusealById(String id)
    {
        return wenshusealMapper.deleteWenshusealById(id);
    }
}
