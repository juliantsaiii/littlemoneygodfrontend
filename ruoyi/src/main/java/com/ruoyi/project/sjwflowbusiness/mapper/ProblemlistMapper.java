package com.ruoyi.project.sjwflowbusiness.mapper;

import java.util.List;

import com.ruoyi.project.sjwflowbusiness.domain.CountMapper;
import com.ruoyi.project.sjwflowbusiness.domain.Problemlist;
import org.apache.ibatis.annotations.Param;

/**
 * 运维记录Mapper接口
 * 
 * @author littlemoneygod
 * @date 2020-07-22
 */
public interface ProblemlistMapper 
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
     * 删除运维记录
     * 
     * @param id 运维记录ID
     * @return 结果
     */
    public int deleteProblemlistById(String id);

    /**
     * 批量删除运维记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProblemlistByIds(String[] ids);

    /**
     * 获取运维类型统计
     * @return
     */
    public List<CountMapper> selectServiceTypeCount();


    /**
     * 获取运维类型统计
     * @return
     */
    public List<CountMapper> selectCountAreaCount();

    /**
     * 获取运维类型统计
     * @return
     */
    public List<CountMapper> selectCountMonthCount(@Param("handletype") String handletype, @Param("year") String year);
}
