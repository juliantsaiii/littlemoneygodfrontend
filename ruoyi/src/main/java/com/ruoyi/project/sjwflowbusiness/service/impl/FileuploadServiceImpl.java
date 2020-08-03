package com.ruoyi.project.sjwflowbusiness.service.impl;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.sjwflowbusiness.domain.FileuploadDownload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sjwflowbusiness.mapper.FileuploadMapper;
import com.ruoyi.project.sjwflowbusiness.domain.Fileupload;
import com.ruoyi.project.sjwflowbusiness.service.IFileuploadService;

/**
 * 附件管理Service业务层处理
 * 
 * @author littlemoneygod
 * @date 2020-07-21
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class FileuploadServiceImpl implements IFileuploadService 
{
    @Autowired
    private FileuploadMapper fileuploadMapper;

    /**
     * 查询附件管理
     * 
     * @param id 附件管理ID
     * @return 附件管理
     */
    @Override
    public Fileupload selectFileuploadById(String id)
    {
        return fileuploadMapper.selectFileuploadById(id);
    }

    /**
     * 查询附件管理列表
     * 
     * @param fileupload 附件管理
     * @return 附件管理
     */
    @Override
    public List<Fileupload> selectFileuploadList(Fileupload fileupload)
    {
        return fileuploadMapper.selectFileuploadList(fileupload);
    }

    /**
     * 新增附件管理
     * 
     * @param fileupload 附件管理
     * @return 结果
     */
    @Override
    public int insertFileupload(Fileupload fileupload)
    {
        return fileuploadMapper.insertFileupload(fileupload);
    }

    /**
     * 修改附件管理
     * 
     * @param fileupload 附件管理
     * @return 结果
     */
    @Override
    public int updateFileupload(Fileupload fileupload)
    {
        return fileuploadMapper.updateFileupload(fileupload);
    }

    /**
     * 批量删除附件管理
     * 
     * @param ids 需要删除的附件管理ID
     * @return 结果
     */
    @Override
    public int deleteFileuploadByIds(String[] ids)
    {
        return fileuploadMapper.deleteFileuploadByIds(ids);
    }

    /**
     * 删除附件管理信息
     * 
     * @param id 附件管理ID
     * @return 结果
     */
    @Override
    public int deleteFileuploadById(String id)
    {
        return fileuploadMapper.deleteFileuploadById(id);
    }


    /**
     * 根据companyid查询文件服务器路径
     * @param id
     * @return
     */
    public String selectMapperIPAddress(String id){return fileuploadMapper.selectMapperIPAddress(id);};

    /**
     * 根据fileid获取文件下载信息
     * @param id
     * @return
     */
    public FileuploadDownload selectDownloadMsg(String id){return fileuploadMapper.selectDownloadMsg(id);};
}
