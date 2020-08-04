package com.ruoyi.project.sjwflowsys.mapper;

import java.util.List;
import com.ruoyi.project.sjwflowsys.domain.Dict;

/**
 * 业务字典Mapper接口
 * 
 * @author littlemoneygod
 * @date 2020-08-04
 */
public interface DictMapper 
{
    /**
     * 查询业务字典
     * 
     * @param id 业务字典ID
     * @return 业务字典
     */
    public Dict selectDictById(String id);

    /**
     * 查询业务字典列表
     * 
     * @param dict 业务字典
     * @return 业务字典集合
     */
    public List<Dict> selectDictList(Dict dict);

    /**
     * 新增业务字典
     * 
     * @param dict 业务字典
     * @return 结果
     */
    public int insertDict(Dict dict);

    /**
     * 修改业务字典
     * 
     * @param dict 业务字典
     * @return 结果
     */
    public int updateDict(Dict dict);

    /**
     * 删除业务字典
     * 
     * @param id 业务字典ID
     * @return 结果
     */
    public int deleteDictById(String id);

    /**
     * 批量删除业务字典
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDictByIds(String[] ids);
}
