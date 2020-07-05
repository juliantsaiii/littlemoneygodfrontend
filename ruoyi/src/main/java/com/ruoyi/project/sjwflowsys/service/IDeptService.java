package com.ruoyi.project.sjwflowsys.service;

import java.util.List;
import com.ruoyi.project.sjwflowsys.domain.Dept;

/**
 * 部门Service接口
 * 
 * @author littlemoneygod
 * @date 2020-07-06
 */
public interface IDeptService 
{
    /**
     * 查询部门
     * 
     * @param id 部门ID
     * @return 部门
     */
    public Dept selectDeptById(String id);

    /**
     * 查询部门列表
     * 
     * @param dept 部门
     * @return 部门集合
     */
    public List<Dept> selectDeptList(Dept dept);

    /**
     * 新增部门
     * 
     * @param dept 部门
     * @return 结果
     */
    public int insertDept(Dept dept);

    /**
     * 修改部门
     * 
     * @param dept 部门
     * @return 结果
     */
    public int updateDept(Dept dept);

    /**
     * 批量删除部门
     * 
     * @param ids 需要删除的部门ID
     * @return 结果
     */
    public int deleteDeptByIds(String[] ids);

    /**
     * 删除部门信息
     * 
     * @param id 部门ID
     * @return 结果
     */
    public int deleteDeptById(String id);
}
