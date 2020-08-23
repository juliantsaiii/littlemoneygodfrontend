package com.ruoyi.project.sjwflowsys.mapper;

import java.util.List;
import com.ruoyi.project.sjwflowsys.domain.RoleFunction;

/**
 * 角色可访问功能Mapper接口
 * 
 * @author littlemoneygod
 * @date 2020-08-23
 */
public interface RoleFunctionMapper 
{
    /**
     * 查询角色可访问功能
     * 
     * @param id 角色可访问功能ID
     * @return 角色可访问功能
     */
    public RoleFunction selectRoleFunctionById(String id);

    /**
     * 查询角色可访问功能列表
     * 
     * @param roleFunction 角色可访问功能
     * @return 角色可访问功能集合
     */
    public List<RoleFunction> selectRoleFunctionList(RoleFunction roleFunction);

    /**
     * 新增角色可访问功能
     * 
     * @param roleFunction 角色可访问功能
     * @return 结果
     */
    public int insertRoleFunction(RoleFunction roleFunction);

    /**
     * 修改角色可访问功能
     * 
     * @param roleFunction 角色可访问功能
     * @return 结果
     */
    public int updateRoleFunction(RoleFunction roleFunction);

    /**
     * 删除角色可访问功能
     * 
     * @param id 角色可访问功能ID
     * @return 结果
     */
    public int deleteRoleFunctionById(String id);

    /**
     * 批量删除角色可访问功能
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRoleFunctionByIds(String[] ids);

    /**
     * 批量添加用户功能
     * @param roleFunctions
     * @return
     */
    public int insertRoleFuncitons(List<RoleFunction> roleFunctions);

    /**
     * 根据角色ID获取功能
     * @param roleid
     * @return
     */
    public List<String> selectFunctionByRole(String roleid);
}
