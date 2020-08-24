package com.ruoyi.project.sjwflowsys.service.impl;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sjwflowsys.mapper.UserMapper;
import com.ruoyi.project.sjwflowsys.domain.User;
import com.ruoyi.project.sjwflowsys.service.IUserService;

/**
 * 业务平台用户管理Service业务层处理
 * 
 * @author littlemoneygod
 * @date 2020-07-15
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class UserServiceImpl implements IUserService 
{
    @Autowired
    private UserMapper userMapper;

    /**
     * 查询业务平台用户管理
     * 
     * @param id 业务平台用户管理ID
     * @return 业务平台用户管理
     */
    @Override
    public User selectUserById(String id)
    {
        return userMapper.selectUserById(id);
    }

    /**
     * 查询业务平台用户管理列表
     * 
     * @param user 业务平台用户管理
     * @return 业务平台用户管理
     */
    @Override
    public List<User> selectUserList(User user)
    {
        return userMapper.selectUserList(user);
    }

    /**
     * 新增业务平台用户管理
     * 
     * @param user 业务平台用户管理
     * @return 结果
     */
    @Override
    public int insertUser(User user)
    {
        return userMapper.insertUser(user);
    }

    /**
     * 修改业务平台用户管理
     * 
     * @param user 业务平台用户管理
     * @return 结果
     */
    @Override
    public int updateUser(User user)
    {
        return userMapper.updateUser(user);
    }

    /**
     * 批量删除业务平台用户管理
     * 
     * @param ids 需要删除的业务平台用户管理ID
     * @return 结果
     */
    @Override
    public int deleteUserByIds(String[] ids)
    {
        return userMapper.deleteUserByIds(ids);
    }

    /**
     * 删除业务平台用户管理信息
     * 
     * @param id 业务平台用户管理ID
     * @return 结果
     */
    @Override
    public int deleteUserById(String id)
    {
        return userMapper.deleteUserById(id);
    }

    /**
     * 账号判重
     * @param UserName
     * @return
     */
    public int checkUserNameUnique(String UserName){
        return userMapper.checkUserNameUnique(UserName);
    }

    /**
     * 根据角色id找用户
     * @param roleid
     * @return
     */
    public List<User> selectUserByRole(String roleid){
        return userMapper.selectUserByRole(roleid);
    }
}
