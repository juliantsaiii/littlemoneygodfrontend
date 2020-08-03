package com.ruoyi.project.sjwflowbusiness.service.impl;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sjwflowbusiness.mapper.TempClueinfoMapper;
import com.ruoyi.project.sjwflowbusiness.domain.TempClueinfo;
import com.ruoyi.project.sjwflowbusiness.service.ITempClueinfoService;

/**
 * 线索操作Service业务层处理
 * 
 * @author littlemoneygod
 * @date 2020-07-18
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class TempClueinfoServiceImpl implements ITempClueinfoService 
{
    @Autowired
    private TempClueinfoMapper tempClueinfoMapper;

    /**
     * 查询线索操作
     * 
     * @param id 线索操作ID
     * @return 线索操作
     */
    @Override
    public TempClueinfo selectTempClueinfoById(String id)
    {
        return tempClueinfoMapper.selectTempClueinfoById(id);
    }

    /**
     * 查询线索操作列表
     * 
     * @param tempClueinfo 线索操作
     * @return 线索操作
     */
    @Override
    public List<TempClueinfo> selectTempClueinfoList(TempClueinfo tempClueinfo)
    {
        return tempClueinfoMapper.selectTempClueinfoList(tempClueinfo);
    }

    /**
     * 新增线索操作
     * 
     * @param tempClueinfo 线索操作
     * @return 结果
     */
    @Override
    public int insertTempClueinfo(TempClueinfo tempClueinfo)
    {
        return tempClueinfoMapper.insertTempClueinfo(tempClueinfo);
    }

    /**
     * 修改线索操作
     * 
     * @param tempClueinfo 线索操作
     * @return 结果
     */
    @Override
    public int updateTempClueinfo(TempClueinfo tempClueinfo)
    {
        return tempClueinfoMapper.updateTempClueinfo(tempClueinfo);
    }

    /**
     * 批量删除线索操作
     * 
     * @param ids 需要删除的线索操作ID
     * @return 结果
     */
    @Override
    public int deleteTempClueinfoByIds(String[] ids)
    {
        return tempClueinfoMapper.deleteTempClueinfoByIds(ids);
    }

    /**
     * 删除线索操作信息
     * 
     * @param id 线索操作ID
     * @return 结果
     */
    @Override
    public int deleteTempClueinfoById(String id)
    {
        return tempClueinfoMapper.deleteTempClueinfoById(id);
    }
}
