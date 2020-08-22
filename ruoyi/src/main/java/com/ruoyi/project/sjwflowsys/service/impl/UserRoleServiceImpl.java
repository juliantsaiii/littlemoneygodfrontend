package com.ruoyi.project.sjwflowsys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sjwflowsys.mapper.UserRoleMapper;
import com.ruoyi.project.sjwflowsys.domain.UserRole;
import com.ruoyi.project.sjwflowsys.service.IUserRoleService;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 角色用户Service业务层处理
 * 
 * @author littlemoneygod
 * @date 2020-08-20
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class UserRoleServiceImpl implements IUserRoleService 
{
    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 查询角色用户
     * 
     * @param id 角色用户ID
     * @return 角色用户
     */
    @Override
    public UserRole selectUserRoleById(String id)
    {
        return userRoleMapper.selectUserRoleById(id);
    }

    /**
     * 查询角色用户列表
     * 
     * @param userRole 角色用户
     * @return 角色用户
     */
    @Override
    public List<UserRole> selectUserRoleList(UserRole userRole)
    {
        return userRoleMapper.selectUserRoleList(userRole);
    }

    /**
     * 新增角色用户
     * 
     * @param userRole 角色用户
     * @return 结果
     */
    @Override
    public int insertUserRole(UserRole userRole)
    {
        return userRoleMapper.insertUserRole(userRole);
    }

    /**
     * 修改角色用户
     * 
     * @param userRole 角色用户
     * @return 结果
     */
    @Override
    public int updateUserRole(UserRole userRole)
    {
        return userRoleMapper.updateUserRole(userRole);
    }

    /**
     * 批量删除角色用户
     * 
     * @param ids 需要删除的角色用户ID
     * @return 结果
     */
    @Override
    public int deleteUserRoleByIds(String[] ids)
    {
        return userRoleMapper.deleteUserRoleByIds(ids);
    }

    /**
     * 删除角色用户信息
     * 
     * @param id 角色用户ID
     * @return 结果
     */
    @Override
    public int deleteUserRoleById(String id)
    {
        return userRoleMapper.deleteUserRoleById(id);
    }

    /**
     * 更换用户的角色
     * @param map
     * @return
     */
    public int updateUserRoleByUser(Map<String,String> map){
        return userRoleMapper.updateUserRoleByUser(map);
    }

    /**
     * 批量插入role
     * @param urList
     * @return
     */
    public int insertUserRoles(List<UserRole> urList){
        return userRoleMapper.insertUserRoles(urList);
    }
}
