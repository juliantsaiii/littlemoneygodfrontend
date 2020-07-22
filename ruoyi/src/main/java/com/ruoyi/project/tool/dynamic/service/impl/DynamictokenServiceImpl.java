package com.ruoyi.project.tool.dynamic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.tool.dynamic.mapper.DynamictokenMapper;
import com.ruoyi.project.tool.dynamic.domain.Dynamictoken;
import com.ruoyi.project.tool.dynamic.service.IDynamictokenService;

/**
 * 口令Service业务层处理
 * 
 * @author littlemoneygod
 * @date 2020-07-22
 */
@Service
public class DynamictokenServiceImpl implements IDynamictokenService 
{
    @Autowired
    private DynamictokenMapper dynamictokenMapper;

    /**
     * 查询口令
     * 
     * @param id 口令ID
     * @return 口令
     */
    @Override
    public Dynamictoken selectDynamictokenById(String id)
    {
        return dynamictokenMapper.selectDynamictokenById(id);
    }

    /**
     * 查询口令列表
     * 
     * @param dynamictoken 口令
     * @return 口令
     */
    @Override
    public List<Dynamictoken> selectDynamictokenList(Dynamictoken dynamictoken)
    {
        return dynamictokenMapper.selectDynamictokenList(dynamictoken);
    }

    /**
     * 新增口令
     * 
     * @param dynamictoken 口令
     * @return 结果
     */
    @Override
    public int insertDynamictoken(Dynamictoken dynamictoken)
    {
        return dynamictokenMapper.insertDynamictoken(dynamictoken);
    }

    /**
     * 修改口令
     * 
     * @param dynamictoken 口令
     * @return 结果
     */
    @Override
    public int updateDynamictoken(Dynamictoken dynamictoken)
    {
        return dynamictokenMapper.updateDynamictoken(dynamictoken);
    }

    /**
     * 批量删除口令
     * 
     * @param ids 需要删除的口令ID
     * @return 结果
     */
    @Override
    public int deleteDynamictokenByIds(String[] ids)
    {
        return dynamictokenMapper.deleteDynamictokenByIds(ids);
    }

    /**
     * 删除口令信息
     * 
     * @param id 口令ID
     * @return 结果
     */
    @Override
    public int deleteDynamictokenById(String id)
    {
        return dynamictokenMapper.deleteDynamictokenById(id);
    }
}
