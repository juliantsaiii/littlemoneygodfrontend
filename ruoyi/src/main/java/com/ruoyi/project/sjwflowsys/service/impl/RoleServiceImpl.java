package com.ruoyi.project.sjwflowsys.service.impl;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sjwflowsys.mapper.RoleMapper;
import com.ruoyi.project.sjwflowsys.domain.Role;
import com.ruoyi.project.sjwflowsys.service.IRoleService;

/**
 * 角色管理Service业务层处理
 * 
 * @author littlemoneygod
 * @date 2020-08-19
 */
@Service
//@DataSource(value = DataSourceType.SLAVE)
public class RoleServiceImpl implements IRoleService 
{
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询角色管理
     * 
     * @param id 角色管理ID
     * @return 角色管理
     */
    @Override
    public Role selectRoleById(String id)
    {
        return roleMapper.selectRoleById(id);
    }

    /**
     * 查询角色管理列表
     * 
     * @param role 角色管理
     * @return 角色管理
     */
    @Override
    public List<Role> selectRoleList(Role role)
    {
        return roleMapper.selectRoleList(role);
    }

    /**
     * 新增角色管理
     * 
     * @param role 角色管理
     * @return 结果
     */
    @Override
    public int insertRole(Role role)
    {
        return roleMapper.insertRole(role);
    }

    /**
     * 修改角色管理
     * 
     * @param role 角色管理
     * @return 结果
     */
    @Override
    public int updateRole(Role role)
    {
        return roleMapper.updateRole(role);
    }

    /**
     * 批量删除角色管理
     * 
     * @param ids 需要删除的角色管理ID
     * @return 结果
     */
    @Override
    public int deleteRoleByIds(String[] ids)
    {
        return roleMapper.deleteRoleByIds(ids);
    }

    /**
     * 删除角色管理信息
     * 
     * @param id 角色管理ID
     * @return 结果
     */
    @Override
    public int deleteRoleById(String id)
    {
        return roleMapper.deleteRoleById(id);
    }
}
