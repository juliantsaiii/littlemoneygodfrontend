package com.ruoyi.project.sjwflowsys.mapper;

import java.util.List;

import com.ruoyi.project.sjwflowsys.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 业务平台用户管理Mapper接口
 * 
 * @author littlemoneygod
 * @date 2020-07-15
 */
@Mapper
public interface UserMapper 
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
     * 删除业务平台用户管理
     * 
     * @param id 业务平台用户管理ID
     * @return 结果
     */
    public int deleteUserById(String id);

    /**
     * 批量删除业务平台用户管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserByIds(String[] ids);

    /**
     * 账号判重
     * @param UserName
     * @return
     */
    public int checkUserNameUnique(String UserName);

    /**
     * 根据角色id找用户
     * @param roleid
     * @return
     */
    public List<User> selectUserByRole(String roleid);
}
