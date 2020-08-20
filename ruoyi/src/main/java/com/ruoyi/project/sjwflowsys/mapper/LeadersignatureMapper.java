package com.ruoyi.project.sjwflowsys.mapper;

import java.util.List;
import com.ruoyi.project.sjwflowsys.domain.Leadersignature;

/**
 * 签名管理Mapper接口
 * 
 * @author littlemoneygod
 * @date 2020-08-20
 */
public interface LeadersignatureMapper 
{
    /**
     * 查询签名管理
     * 
     * @param id 签名管理ID
     * @return 签名管理
     */
    public Leadersignature selectLeadersignatureById(String id);

    /**
     * 查询签名管理列表
     * 
     * @param leadersignature 签名管理
     * @return 签名管理集合
     */
    public List<Leadersignature> selectLeadersignatureList(Leadersignature leadersignature);

    /**
     * 新增签名管理
     * 
     * @param leadersignature 签名管理
     * @return 结果
     */
    public int insertLeadersignature(Leadersignature leadersignature);

    /**
     * 修改签名管理
     * 
     * @param leadersignature 签名管理
     * @return 结果
     */
    public int updateLeadersignature(Leadersignature leadersignature);

    /**
     * 删除签名管理
     * 
     * @param id 签名管理ID
     * @return 结果
     */
    public int deleteLeadersignatureById(String id);

    /**
     * 批量删除签名管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLeadersignatureByIds(String[] ids);
}
