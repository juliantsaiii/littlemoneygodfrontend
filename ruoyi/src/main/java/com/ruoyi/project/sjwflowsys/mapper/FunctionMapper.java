package com.ruoyi.project.sjwflowsys.mapper;

import java.util.List;
import com.ruoyi.project.sjwflowsys.domain.Function;

/**
 * 功能Mapper接口
 * 
 * @author littlemoneygod
 * @date 2020-08-23
 */
public interface FunctionMapper 
{
    /**
     * 查询功能
     * 
     * @param id 功能ID
     * @return 功能
     */
    public Function selectFunctionById(String id);

    /**
     * 查询功能列表
     * 
     * @param function 功能
     * @return 功能集合
     */
    public List<Function> selectFunctionList(Function function);

    /**
     * 新增功能
     * 
     * @param function 功能
     * @return 结果
     */
    public int insertFunction(Function function);

    /**
     * 修改功能
     * 
     * @param function 功能
     * @return 结果
     */
    public int updateFunction(Function function);

    /**
     * 删除功能
     * 
     * @param id 功能ID
     * @return 结果
     */
    public int deleteFunctionById(String id);

    /**
     * 批量删除功能
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFunctionByIds(String[] ids);
}
