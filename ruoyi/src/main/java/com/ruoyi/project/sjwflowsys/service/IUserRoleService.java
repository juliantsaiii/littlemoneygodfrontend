package com.ruoyi.project.sjwflowsys.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.sjwflowsys.domain.UserRole;

/**
 * 角色用户Service接口
 * 
 * @author littlemoneygod
 * @date 2020-08-20
 */
public interface IUserRoleService 
{
    /**
     * 查询角色用户
     * 
     * @param id 角色用户ID
     * @return 角色用户
     */
    public UserRole selectUserRoleById(String id);

    /**
     * 查询角色用户列表
     * 
     * @param userRole 角色用户
     * @return 角色用户集合
     */
    public List<UserRole> selectUserRoleList(UserRole userRole);

    /**
     * 新增角色用户
     * 
     * @param userRole 角色用户
     * @return 结果
     */
    public int insertUserRole(UserRole userRole);

    /**
     * 修改角色用户
     * 
     * @param userRole 角色用户
     * @return 结果
     */
    public int updateUserRole(UserRole userRole);

    /**
     * 批量删除角色用户
     * 
     * @param ids 需要删除的角色用户ID
     * @return 结果
     */
    public int deleteUserRoleByIds(String[] ids);

    /**
     * 删除角色用户信息
     * 
     * @param id 角色用户ID
     * @return 结果
     */
    public int deleteUserRoleById(String id);

    /**
     * 更换用户的角色
     * @param map
     * @return
     */
    public int updateUserRoleByUser(Map<String,String> map);
}
