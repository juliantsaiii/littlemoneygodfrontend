package com.ruoyi.project.sjwflowsys.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sjwflowsys.mapper.FunctionMapper;
import com.ruoyi.project.sjwflowsys.domain.Function;
import com.ruoyi.project.sjwflowsys.service.IFunctionService;

/**
 * 功能Service业务层处理
 * 
 * @author littlemoneygod
 * @date 2020-08-23
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class FunctionServiceImpl implements IFunctionService 
{
    @Autowired
    private FunctionMapper functionMapper;

    /**
     * 查询功能
     * 
     * @param id 功能ID
     * @return 功能
     */
    @Override
    public Function selectFunctionById(String id)
    {
        return functionMapper.selectFunctionById(id);
    }

    /**
     * 查询功能列表
     * 
     * @param function 功能
     * @return 功能
     */
    @Override
    public List<Function> selectFunctionList(Function function)
    {
        return functionMapper.selectFunctionList(function);
    }

    /**
     * 新增功能
     * 
     * @param function 功能
     * @return 结果
     */
    @Override
    public int insertFunction(Function function)
    {
        return functionMapper.insertFunction(function);
    }

    /**
     * 修改功能
     * 
     * @param function 功能
     * @return 结果
     */
    @Override
    public int updateFunction(Function function)
    {
        return functionMapper.updateFunction(function);
    }

    /**
     * 批量删除功能
     * 
     * @param ids 需要删除的功能ID
     * @return 结果
     */
    @Override
    public int deleteFunctionByIds(String[] ids)
    {
        return functionMapper.deleteFunctionByIds(ids);
    }

    /**
     * 删除功能信息
     * 
     * @param id 功能ID
     * @return 结果
     */
    @Override
    public int deleteFunctionById(String id)
    {
        return functionMapper.deleteFunctionById(id);
    }

    /**
     * 生成function树
     * @param result
     * @param functions
     * @return
     */
    public List<Function> getFunctionTree(List<Function> result,List<Function> functions){
        for(Function fun : result){
            List<Function> functionList = functions.stream().filter(f-> StringUtils.isNotNull(f.getPid())&&f.getPid().equals(fun.getId())).collect(Collectors.toList());
            fun.setChildren(functionList);
            fun.setHasChildren(true);
            getFunctionTree(functionList,functions);
        }
        return result;
    }
}
