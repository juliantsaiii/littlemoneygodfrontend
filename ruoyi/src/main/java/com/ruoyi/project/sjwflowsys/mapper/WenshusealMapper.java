package com.ruoyi.project.sjwflowsys.mapper;

import java.util.List;
import com.ruoyi.project.sjwflowsys.domain.Wenshuseal;

/**
 * 盖章信息Mapper接口
 * 
 * @author littlemoneygod
 * @date 2020-08-26
 */
public interface WenshusealMapper 
{
    /**
     * 查询盖章信息
     * 
     * @param id 盖章信息ID
     * @return 盖章信息
     */
    public List<Wenshuseal> selectWenshusealById(String id);

    /**
     * 查询盖章信息列表
     * 
     * @param wenshuseal 盖章信息
     * @return 盖章信息集合
     */
    public List<Wenshuseal> selectWenshusealList(Wenshuseal wenshuseal);

    /**
     * 新增盖章信息
     * 
     * @param wenshuseal 盖章信息
     * @return 结果
     */
    public int insertWenshuseal(Wenshuseal wenshuseal);

    /**
     * 修改盖章信息
     * 
     * @param wenshuseal 盖章信息
     * @return 结果
     */
    public int updateWenshuseal(Wenshuseal wenshuseal);

    /**
     * 删除盖章信息
     * 
     * @param id 盖章信息ID
     * @return 结果
     */
    public int deleteWenshusealById(String id);

    /**
     * 批量删除盖章信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWenshusealByIds(String[] ids);
}
