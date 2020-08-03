package com.ruoyi.project.sjwflowbusiness.service.impl;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sjwflowbusiness.mapper.FileuploadtaskMapper;
import com.ruoyi.project.sjwflowbusiness.domain.Fileuploadtask;
import com.ruoyi.project.sjwflowbusiness.service.IFileuploadtaskService;

/**
 * 附件历史Service业务层处理
 * 
 * @author littlemoneygod
 * @date 2020-07-21
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class FileuploadtaskServiceImpl implements IFileuploadtaskService 
{
    @Autowired
    private FileuploadtaskMapper fileuploadtaskMapper;

    /**
     * 查询附件历史
     * 
     * @param id 附件历史ID
     * @return 附件历史
     */
    @Override
    public Fileuploadtask selectFileuploadtaskById(String id)
    {
        return fileuploadtaskMapper.selectFileuploadtaskById(id);
    }

    /**
     * 查询附件历史列表
     * 
     * @param fileuploadtask 附件历史
     * @return 附件历史
     */
    @Override
    public List<Fileuploadtask> selectFileuploadtaskList(Fileuploadtask fileuploadtask)
    {
        return fileuploadtaskMapper.selectFileuploadtaskList(fileuploadtask);
    }

    /**
     * 新增附件历史
     * 
     * @param fileuploadtask 附件历史
     * @return 结果
     */
    @Override
    public int insertFileuploadtask(Fileuploadtask fileuploadtask)
    {
        return fileuploadtaskMapper.insertFileuploadtask(fileuploadtask);
    }

    /**
     * 修改附件历史
     * 
     * @param fileuploadtask 附件历史
     * @return 结果
     */
    @Override
    public int updateFileuploadtask(Fileuploadtask fileuploadtask)
    {
        return fileuploadtaskMapper.updateFileuploadtask(fileuploadtask);
    }

    /**
     * 批量删除附件历史
     * 
     * @param ids 需要删除的附件历史ID
     * @return 结果
     */
    @Override
    public int deleteFileuploadtaskByIds(String[] ids)
    {
        return fileuploadtaskMapper.deleteFileuploadtaskByIds(ids);
    }

    /**
     * 删除附件历史信息
     * 
     * @param id 附件历史ID
     * @return 结果
     */
    @Override
    public int deleteFileuploadtaskById(String id)
    {
        return fileuploadtaskMapper.deleteFileuploadtaskById(id);
    }
}
