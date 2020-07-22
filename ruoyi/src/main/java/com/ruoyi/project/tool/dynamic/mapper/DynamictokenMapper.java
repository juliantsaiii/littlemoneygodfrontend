package com.ruoyi.project.tool.dynamic.mapper;

import java.util.List;
import com.ruoyi.project.tool.dynamic.domain.Dynamictoken;

/**
 * 口令Mapper接口
 * 
 * @author littlemoneygod
 * @date 2020-07-22
 */
public interface DynamictokenMapper 
{
    /**
     * 查询口令
     * 
     * @param id 口令ID
     * @return 口令
     */
    public Dynamictoken selectDynamictokenById(String id);

    /**
     * 查询口令列表
     * 
     * @param dynamictoken 口令
     * @return 口令集合
     */
    public List<Dynamictoken> selectDynamictokenList(Dynamictoken dynamictoken);

    /**
     * 新增口令
     * 
     * @param dynamictoken 口令
     * @return 结果
     */
    public int insertDynamictoken(Dynamictoken dynamictoken);

    /**
     * 修改口令
     * 
     * @param dynamictoken 口令
     * @return 结果
     */
    public int updateDynamictoken(Dynamictoken dynamictoken);

    /**
     * 删除口令
     * 
     * @param id 口令ID
     * @return 结果
     */
    public int deleteDynamictokenById(String id);

    /**
     * 批量删除口令
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDynamictokenByIds(String[] ids);
}
