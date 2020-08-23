package com.ruoyi.project.sjwflowsys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sjwflowsys.mapper.RoleFunctionMapper;
import com.ruoyi.project.sjwflowsys.domain.RoleFunction;
import com.ruoyi.project.sjwflowsys.service.IRoleFunctionService;

/**
 * 角色可访问功能Service业务层处理
 * 
 * @author littlemoneygod
 * @date 2020-08-23
 */
@Service
public class RoleFunctionServiceImpl implements IRoleFunctionService 
{
    @Autowired
    private RoleFunctionMapper roleFunctionMapper;

    /**
     * 查询角色可访问功能
     * 
     * @param id 角色可访问功能ID
     * @return 角色可访问功能
     */
    @Override
    public RoleFunction selectRoleFunctionById(String id)
    {
        return roleFunctionMapper.selectRoleFunctionById(id);
    }

    /**
     * 查询角色可访问功能列表
     * 
     * @param roleFunction 角色可访问功能
     * @return 角色可访问功能
     */
    @Override
    public List<RoleFunction> selectRoleFunctionList(RoleFunction roleFunction)
    {
        return roleFunctionMapper.selectRoleFunctionList(roleFunction);
    }

    /**
     * 新增角色可访问功能
     * 
     * @param roleFunction 角色可访问功能
     * @return 结果
     */
    @Override
    public int insertRoleFunction(RoleFunction roleFunction)
    {
        return roleFunctionMapper.insertRoleFunction(roleFunction);
    }

    /**
     * 修改角色可访问功能
     * 
     * @param roleFunction 角色可访问功能
     * @return 结果
     */
    @Override
    public int updateRoleFunction(RoleFunction roleFunction)
    {
        return roleFunctionMapper.updateRoleFunction(roleFunction);
    }

    /**
     * 批量删除角色可访问功能
     * 
     * @param ids 需要删除的角色可访问功能ID
     * @return 结果
     */
    @Override
    public int deleteRoleFunctionByIds(String[] ids)
    {
        return roleFunctionMapper.deleteRoleFunctionByIds(ids);
    }

    /**
     * 删除角色可访问功能信息
     * 
     * @param id 角色可访问功能ID
     * @return 结果
     */
    @Override
    public int deleteRoleFunctionById(String id)
    {
        return roleFunctionMapper.deleteRoleFunctionById(id);
    }

    /**
     * 批量添加用户功能
     * @param roleFunctions
     * @return
     */
    public int insertRoleFuncitons(List<RoleFunction> roleFunctions){
        return roleFunctionMapper.insertRoleFuncitons(roleFunctions);
    }

    /**
     * 根据角色ID获取功能
     * @param roleid
     * @return
     */
    public List<String> selectFunctionByRole(String roleid){
        return roleFunctionMapper.selectFunctionByRole(roleid);
    }
}
