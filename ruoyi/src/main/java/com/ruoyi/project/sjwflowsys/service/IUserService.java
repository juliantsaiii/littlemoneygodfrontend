package com.ruoyi.project.sjwflowsys.service;

import java.util.List;
import com.ruoyi.project.sjwflowsys.domain.User;

/**
 * 业务平台用户管理Service接口
 * 
 * @author littlemoneygod
 * @date 2020-07-15
 */
public interface IUserService 
{
    /**
     * 查询业务平台用户管理
     * 
     * @param id 业务平台用户管理ID
     * @return 业务平台用户管理
     */
    public User selectUserById(String id);

    /**
     * 查询业务平台用户管理列表
     * 
     * @param user 业务平台用户管理
     * @return 业务平台用户管理集合
     */
    public List<User> selectUserList(User user);

    /**
     * 新增业务平台用户管理
     * 
     * @param user 业务平台用户管理
     * @return 结果
     */
    public int insertUser(User user);

    /**
     * 修改业务平台用户管理
     * 
     * @param user 业务平台用户管理
     * @return 结果
     */
    public int updateUser(User user);

    /**
     * 批量删除业务平台用户管理
     * 
     * @param ids 需要删除的业务平台用户管理ID
     * @return 结果
     */
    public int deleteUserByIds(String[] ids);

    /**
     * 删除业务平台用户管理信息
     * 
     * @param id 业务平台用户管理ID
     * @return 结果
     */
    public int deleteUserById(String id);
}
