package com.ruoyi.project.sjwflowsys.mapper;

import java.util.List;
import com.ruoyi.project.sjwflowsys.domain.Dept;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 部门Mapper接口
 * 
 * @author littlemoneygod
 * @date 2020-07-06
 */
public interface DeptMapper 
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
     * 删除部门
     * 
     * @param id 部门ID
     * @return 结果
     */
    public int deleteDeptById(String id);

    /**
     * 批量删除部门
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDeptByIds(String[] ids);

    /**
     * 账号判重
     * @param UserName
     * @return
     */
    public int checkUserNameUnique(String UserName);

    /**
     * 更新用户表部门id
     * @param deptid
     * @param deptname
     * @return
     */
    @Update("update user set deptname = #{deptname} where deptid = #{deptid}")
    public int updateDeptname(@Param("deptid") String deptid, @Param("deptname") String deptname);

    /**
     * 根据companyid 查部门
     * @param id
     * @return
     */
    public List<Dept> selectDeptByCompanyID(String id);
}
