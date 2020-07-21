package com.ruoyi.project.sjwflowbusiness.mapper;

import java.util.List;
import com.ruoyi.project.sjwflowbusiness.domain.Fileupload;

/**
 * 附件管理Mapper接口
 * 
 * @author littlemoneygod
 * @date 2020-07-21
 */
public interface FileuploadMapper 
{
    /**
     * 查询附件管理
     * 
     * @param id 附件管理ID
     * @return 附件管理
     */
    public Fileupload selectFileuploadById(String id);

    /**
     * 查询附件管理列表
     * 
     * @param fileupload 附件管理
     * @return 附件管理集合
     */
    public List<Fileupload> selectFileuploadList(Fileupload fileupload);

    /**
     * 新增附件管理
     * 
     * @param fileupload 附件管理
     * @return 结果
     */
    public int insertFileupload(Fileupload fileupload);

    /**
     * 修改附件管理
     * 
     * @param fileupload 附件管理
     * @return 结果
     */
    public int updateFileupload(Fileupload fileupload);

    /**
     * 删除附件管理
     * 
     * @param id 附件管理ID
     * @return 结果
     */
    public int deleteFileuploadById(String id);

    /**
     * 批量删除附件管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFileuploadByIds(String[] ids);
}
