package com.ruoyi.project.sjwflowbusiness.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sjwflowbusiness.mapper.ProblemlistMapper;
import com.ruoyi.project.sjwflowbusiness.domain.Problemlist;
import com.ruoyi.project.sjwflowbusiness.service.IProblemlistService;

/**
 * 运维记录Service业务层处理
 * 
 * @author littlemoneygod
 * @date 2020-07-22
 */
@Service
public class ProblemlistServiceImpl implements IProblemlistService 
{
    @Autowired
    private ProblemlistMapper problemlistMapper;

    /**
     * 查询运维记录
     * 
     * @param id 运维记录ID
     * @return 运维记录
     */
    @Override
    public Problemlist selectProblemlistById(String id)
    {
        return problemlistMapper.selectProblemlistById(id);
    }

    /**
     * 查询运维记录列表
     * 
     * @param problemlist 运维记录
     * @return 运维记录
     */
    @Override
    public List<Problemlist> selectProblemlistList(Problemlist problemlist)
    {
        return problemlistMapper.selectProblemlistList(problemlist);
    }

    /**
     * 新增运维记录
     * 
     * @param problemlist 运维记录
     * @return 结果
     */
    @Override
    public int insertProblemlist(Problemlist problemlist)
    {
        return problemlistMapper.insertProblemlist(problemlist);
    }

    /**
     * 修改运维记录
     * 
     * @param problemlist 运维记录
     * @return 结果
     */
    @Override
    public int updateProblemlist(Problemlist problemlist)
    {
        return problemlistMapper.updateProblemlist(problemlist);
    }

    /**
     * 批量删除运维记录
     * 
     * @param ids 需要删除的运维记录ID
     * @return 结果
     */
    @Override
    public int deleteProblemlistByIds(String[] ids)
    {
        return problemlistMapper.deleteProblemlistByIds(ids);
    }

    /**
     * 删除运维记录信息
     * 
     * @param id 运维记录ID
     * @return 结果
     */
    @Override
    public int deleteProblemlistById(String id)
    {
        return problemlistMapper.deleteProblemlistById(id);
    }
}
