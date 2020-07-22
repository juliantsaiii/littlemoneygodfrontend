package com.ruoyi.project.sjwflowbusiness.service;

import java.util.List;
import com.ruoyi.project.sjwflowbusiness.domain.Problemlist;

/**
 * 运维记录Service接口
 * 
 * @author littlemoneygod
 * @date 2020-07-22
 */
public interface IProblemlistService 
{
    /**
     * 查询运维记录
     * 
     * @param id 运维记录ID
     * @return 运维记录
     */
    public Problemlist selectProblemlistById(String id);

    /**
     * 查询运维记录列表
     * 
     * @param problemlist 运维记录
     * @return 运维记录集合
     */
    public List<Problemlist> selectProblemlistList(Problemlist problemlist);

    /**
     * 新增运维记录
     * 
     * @param problemlist 运维记录
     * @return 结果
     */
    public int insertProblemlist(Problemlist problemlist);

    /**
     * 修改运维记录
     * 
     * @param problemlist 运维记录
     * @return 结果
     */
    public int updateProblemlist(Problemlist problemlist);

    /**
     * 批量删除运维记录
     * 
     * @param ids 需要删除的运维记录ID
     * @return 结果
     */
    public int deleteProblemlistByIds(String[] ids);

    /**
     * 删除运维记录信息
     * 
     * @param id 运维记录ID
     * @return 结果
     */
    public int deleteProblemlistById(String id);
}
