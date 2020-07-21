package com.ruoyi.project.sjwflowbusiness.mapper;

import java.util.List;
import com.ruoyi.project.sjwflowbusiness.domain.Fileuploadtask;

/**
 * 附件历史Mapper接口
 * 
 * @author littlemoneygod
 * @date 2020-07-21
 */
public interface FileuploadtaskMapper 
{
    /**
     * 查询附件历史
     * 
     * @param id 附件历史ID
     * @return 附件历史
     */
    public Fileuploadtask selectFileuploadtaskById(String id);

    /**
     * 查询附件历史列表
     * 
     * @param fileuploadtask 附件历史
     * @return 附件历史集合
     */
    public List<Fileuploadtask> selectFileuploadtaskList(Fileuploadtask fileuploadtask);

    /**
     * 新增附件历史
     * 
     * @param fileuploadtask 附件历史
     * @return 结果
     */
    public int insertFileuploadtask(Fileuploadtask fileuploadtask);

    /**
     * 修改附件历史
     * 
     * @param fileuploadtask 附件历史
     * @return 结果
     */
    public int updateFileuploadtask(Fileuploadtask fileuploadtask);

    /**
     * 删除附件历史
     * 
     * @param id 附件历史ID
     * @return 结果
     */
    public int deleteFileuploadtaskById(String id);

    /**
     * 批量删除附件历史
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFileuploadtaskByIds(String[] ids);
}
