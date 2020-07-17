package com.ruoyi.project.sjwflowbusiness.service;

import java.util.List;
import com.ruoyi.project.sjwflowbusiness.domain.TempClueinfo;

/**
 * 线索操作Service接口
 * 
 * @author littlemoneygod
 * @date 2020-07-18
 */
public interface ITempClueinfoService 
{
    /**
     * 查询线索操作
     * 
     * @param id 线索操作ID
     * @return 线索操作
     */
    public TempClueinfo selectTempClueinfoById(String id);

    /**
     * 查询线索操作列表
     * 
     * @param tempClueinfo 线索操作
     * @return 线索操作集合
     */
    public List<TempClueinfo> selectTempClueinfoList(TempClueinfo tempClueinfo);

    /**
     * 新增线索操作
     * 
     * @param tempClueinfo 线索操作
     * @return 结果
     */
    public int insertTempClueinfo(TempClueinfo tempClueinfo);

    /**
     * 修改线索操作
     * 
     * @param tempClueinfo 线索操作
     * @return 结果
     */
    public int updateTempClueinfo(TempClueinfo tempClueinfo);

    /**
     * 批量删除线索操作
     * 
     * @param ids 需要删除的线索操作ID
     * @return 结果
     */
    public int deleteTempClueinfoByIds(String[] ids);

    /**
     * 删除线索操作信息
     * 
     * @param id 线索操作ID
     * @return 结果
     */
    public int deleteTempClueinfoById(String id);
}
