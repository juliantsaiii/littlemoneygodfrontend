package com.ruoyi.project.sjwflowsys.mapper;

import java.util.List;
import com.ruoyi.project.sjwflowsys.domain.Role;

/**
 * 角色管理Mapper接口
 * 
 * @author littlemoneygod
 * @date 2020-08-19
 */
public interface RoleMapper 
{
    /**
     * 查询角色管理
     * 
     * @param id 角色管理ID
     * @return 角色管理
     */
    public Role selectRoleById(String id);

    /**
     * 查询角色管理列表
     * 
     * @param role 角色管理
     * @return 角色管理集合
     */
    public List<Role> selectRoleList(Role role);

    /**
     * 新增角色管理
     * 
     * @param role 角色管理
     * @return 结果
     */
    public int insertRole(Role role);

    /**
     * 修改角色管理
     * 
     * @param role 角色管理
     * @return 结果
     */
    public int updateRole(Role role);

    /**
     * 删除角色管理
     * 
     * @param id 角色管理ID
     * @return 结果
     */
    public int deleteRoleById(String id);

    /**
     * 批量删除角色管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRoleByIds(String[] ids);
}
