package com.ruoyi.project.sjwflowsys.service;

import java.util.List;
import com.ruoyi.project.sjwflowsys.domain.Roledata;

/**
 * 角色可访问数据Service接口
 * 
 * @author littlemoneygod
 * @date 2020-08-21
 */
public interface IRoledataService 
{
    /**
     * 查询角色可访问数据
     * 
     * @param id 角色可访问数据ID
     * @return 角色可访问数据
     */
    public Roledata selectRoledataById(String id);

    /**
     * 查询角色可访问数据列表
     * 
     * @param roledata 角色可访问数据
     * @return 角色可访问数据集合
     */
    public List<Roledata> selectRoledataList(Roledata roledata);

    /**
     * 新增角色可访问数据
     * 
     * @param roledata 角色可访问数据
     * @return 结果
     */
    public int insertRoledata(Roledata roledata);

    /**
     * 修改角色可访问数据
     * 
     * @param roledata 角色可访问数据
     * @return 结果
     */
    public int updateRoledata(Roledata roledata);

    /**
     * 批量删除角色可访问数据
     * 
     * @param ids 需要删除的角色可访问数据ID
     * @return 结果
     */
    public int deleteRoledataByIds(String[] ids);

    /**
     * 删除角色可访问数据信息
     * 
     * @param id 角色可访问数据ID
     * @return 结果
     */
    public int deleteRoledataById(String id);
}
