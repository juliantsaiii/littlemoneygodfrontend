package com.ruoyi.project.sjwflowsys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sjwflowsys.mapper.RoledataMapper;
import com.ruoyi.project.sjwflowsys.domain.Roledata;
import com.ruoyi.project.sjwflowsys.service.IRoledataService;

/**
 * 角色可访问数据Service业务层处理
 * 
 * @author littlemoneygod
 * @date 2020-08-21
 */
@Service
public class RoledataServiceImpl implements IRoledataService 
{
    @Autowired
    private RoledataMapper roledataMapper;

    /**
     * 查询角色可访问数据
     * 
     * @param id 角色可访问数据ID
     * @return 角色可访问数据
     */
    @Override
    public Roledata selectRoledataById(String id)
    {
        return roledataMapper.selectRoledataById(id);
    }

    /**
     * 查询角色可访问数据列表
     * 
     * @param roledata 角色可访问数据
     * @return 角色可访问数据
     */
    @Override
    public List<Roledata> selectRoledataList(Roledata roledata)
    {
        return roledataMapper.selectRoledataList(roledata);
    }

    /**
     * 新增角色可访问数据
     * 
     * @param roledata 角色可访问数据
     * @return 结果
     */
    @Override
    public int insertRoledata(Roledata roledata)
    {
        return roledataMapper.insertRoledata(roledata);
    }

    /**
     * 修改角色可访问数据
     * 
     * @param roledata 角色可访问数据
     * @return 结果
     */
    @Override
    public int updateRoledata(Roledata roledata)
    {
        return roledataMapper.updateRoledata(roledata);
    }

    /**
     * 批量删除角色可访问数据
     * 
     * @param ids 需要删除的角色可访问数据ID
     * @return 结果
     */
    @Override
    public int deleteRoledataByIds(String[] ids)
    {
        return roledataMapper.deleteRoledataByIds(ids);
    }

    /**
     * 删除角色可访问数据信息
     * 
     * @param id 角色可访问数据ID
     * @return 结果
     */
    @Override
    public int deleteRoledataById(String id)
    {
        return roledataMapper.deleteRoledataById(id);
    }
}
