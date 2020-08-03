package com.ruoyi.project.sjwflowsys.mapper;

import java.util.List;
import com.ruoyi.project.sjwflowsys.domain.Wenshu;

/**
 * 文书管理Mapper接口
 * 
 * @author littlemoneygod
 * @date 2020-08-04
 */
public interface WenshuMapper 
{
    /**
     * 查询文书管理
     * 
     * @param id 文书管理ID
     * @return 文书管理
     */
    public Wenshu selectWenshuById(String id);

    /**
     * 查询文书管理列表
     * 
     * @param wenshu 文书管理
     * @return 文书管理集合
     */
    public List<Wenshu> selectWenshuList(Wenshu wenshu);

    /**
     * 新增文书管理
     * 
     * @param wenshu 文书管理
     * @return 结果
     */
    public int insertWenshu(Wenshu wenshu);

    /**
     * 修改文书管理
     * 
     * @param wenshu 文书管理
     * @return 结果
     */
    public int updateWenshu(Wenshu wenshu);

    /**
     * 删除文书管理
     * 
     * @param id 文书管理ID
     * @return 结果
     */
    public int deleteWenshuById(String id);

    /**
     * 批量删除文书管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWenshuByIds(String[] ids);
}
