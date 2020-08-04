package com.ruoyi.project.sjwflowsys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sjwflowsys.mapper.DictMapper;
import com.ruoyi.project.sjwflowsys.domain.Dict;
import com.ruoyi.project.sjwflowsys.service.IDictService;

/**
 * 业务字典Service业务层处理
 * 
 * @author littlemoneygod
 * @date 2020-08-04
 */
@Service
public class DictServiceImpl implements IDictService 
{
    @Autowired
    private DictMapper dictMapper;

    /**
     * 查询业务字典
     * 
     * @param id 业务字典ID
     * @return 业务字典
     */
    @Override
    public Dict selectDictById(String id)
    {
        return dictMapper.selectDictById(id);
    }

    /**
     * 查询业务字典列表
     * 
     * @param dict 业务字典
     * @return 业务字典
     */
    @Override
    public List<Dict> selectDictList(Dict dict)
    {
        return dictMapper.selectDictList(dict);
    }

    /**
     * 新增业务字典
     * 
     * @param dict 业务字典
     * @return 结果
     */
    @Override
    public int insertDict(Dict dict)
    {
        return dictMapper.insertDict(dict);
    }

    /**
     * 修改业务字典
     * 
     * @param dict 业务字典
     * @return 结果
     */
    @Override
    public int updateDict(Dict dict)
    {
        return dictMapper.updateDict(dict);
    }

    /**
     * 批量删除业务字典
     * 
     * @param ids 需要删除的业务字典ID
     * @return 结果
     */
    @Override
    public int deleteDictByIds(String[] ids)
    {
        return dictMapper.deleteDictByIds(ids);
    }

    /**
     * 删除业务字典信息
     * 
     * @param id 业务字典ID
     * @return 结果
     */
    @Override
    public int deleteDictById(String id)
    {
        return dictMapper.deleteDictById(id);
    }
}
