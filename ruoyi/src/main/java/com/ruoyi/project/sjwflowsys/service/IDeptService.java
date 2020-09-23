package com.ruoyi.project.sjwflowsys.service;

import java.util.List;

import com.ruoyi.framework.web.domain.TreeSelectStr;
import com.ruoyi.project.sjwflowsys.domain.Dept;
import com.ruoyi.project.sjwflowsys.domain.User;

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
    public List<Dept> selectDeptList(Dept dept,String type,List<String> parentids);

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

    /**
     * 根据当前节点递归获取部门树
     * @param ID
     * @param list
     * @return
     */
    public TreeSelectStr getDeptTree(String ID, List<TreeSelectStr> list,boolean isCompany,List<String> parentids,String type);

    /**
     * 判断节点是否是省纪委、市纪委、县区纪委
     * @param deptType
     * @return
     */
    public boolean JudgeDeptType(String deptType);

    /**
     * 根据条件设置部门树的haschildren
     * @param selectType
     * @param Type
     * @param deptList
     * @return
     */
    public List<Dept> setHasChildren(String selectType,String Type,List<Dept> deptList);

    /**
     * 更新用户表部门id
     * @param deptid
     * @param deptname
     * @return
     */
    public int updateDeptname(String deptid, String deptname);

    /**
     * 根据companyid生成部门树
     * @param depts
     * @param users
     * @param result
     * @return
     */
    public List<Dept> UserTreeByCompany(List<Dept> depts, List<User> users,  List<Dept> result);

    /**
     * 根据companyid 查部门
     * @param id
     * @return
     */
    public List<Dept> selectDeptByCompanyID(String id);


    /**
     * 查找所有parentid
     * @return
     */
    public List<String> findAllparentId();
}
