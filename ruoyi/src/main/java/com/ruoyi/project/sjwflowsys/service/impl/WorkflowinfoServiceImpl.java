package com.ruoyi.project.sjwflowsys.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.framework.web.domain.TreeEntity;
import com.ruoyi.framework.web.domain.TreeEntityStr;
import com.ruoyi.framework.web.domain.TreeSelect;
import com.ruoyi.project.sjwflowsys.domain.Dict;
import com.ruoyi.project.sjwflowsys.mapper.DictMapper;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sjwflowsys.mapper.WorkflowinfoMapper;
import com.ruoyi.project.sjwflowsys.domain.Workflowinfo;
import com.ruoyi.project.sjwflowsys.service.IWorkflowinfoService;

/**
 * 流程管理Service业务层处理
 * 
 * @author littlemoneygod
 * @date 2020-08-04
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class WorkflowinfoServiceImpl implements IWorkflowinfoService 
{
    @Autowired
    private WorkflowinfoMapper workflowinfoMapper;

    @Autowired
    private DictMapper dictMapper;
    /**
     * 查询流程管理
     * 
     * @param id 流程管理ID
     * @return 流程管理
     */
    @Override
    public Workflowinfo selectWorkflowinfoById(String id)
    {
        return workflowinfoMapper.selectWorkflowinfoById(id);
    }

    /**
     * 查询流程管理列表
     * 
     * @param workflowinfo 流程管理
     * @return 流程管理
     */
    @Override
    public List<Workflowinfo> selectWorkflowinfoList(Workflowinfo workflowinfo)
    {
        return workflowinfoMapper.selectWorkflowinfoList(workflowinfo);
    }

    /**
     * 新增流程管理
     * 
     * @param workflowinfo 流程管理
     * @return 结果
     */
    @Override
    public int insertWorkflowinfo(Workflowinfo workflowinfo)
    {
        return workflowinfoMapper.insertWorkflowinfo(workflowinfo);
    }

    /**
     * 修改流程管理
     * 
     * @param workflowinfo 流程管理
     * @return 结果
     */
    @Override
    public int updateWorkflowinfo(Workflowinfo workflowinfo)
    {
        return workflowinfoMapper.updateWorkflowinfo(workflowinfo);
    }

    /**
     * 批量删除流程管理
     * 
     * @param ids 需要删除的流程管理ID
     * @return 结果
     */
    @Override
    public int deleteWorkflowinfoByIds(String[] ids)
    {
        return workflowinfoMapper.deleteWorkflowinfoByIds(ids);
    }

    /**
     * 删除流程管理信息
     * 
     * @param id 流程管理ID
     * @return 结果
     */
    @Override
    public int deleteWorkflowinfoById(String id)
    {
        return workflowinfoMapper.deleteWorkflowinfoById(id);
    }

    /**
     * workflowinfotree
     * @param infos
     * @return
     */
    public List<TreeEntityStr> buildWorkflowInfoTreeSelect(List<Workflowinfo> infos){
        Dict dict = new Dict();
        List<TreeEntityStr> treeEntities = new ArrayList<>();
        dict.setParentid("10adcf4d-4d19-4a10-8e56-60c48356232d");
        List<Dict> dicts = dictMapper.selectDictList(dict);
        treeEntities = dicts.stream().map(TreeEntityStr::new).collect(Collectors.toList());
        for(TreeEntityStr ts : treeEntities){
            List<Workflowinfo> ifs = infos.stream().filter(item -> item.getType().equals(ts.getParentId())).collect(Collectors.toList());
            List<TreeEntityStr> infoEntities = ifs.stream().map(TreeEntityStr::new).collect(Collectors.toList());
            ts.setChildren(infoEntities);
        }
        return treeEntities;
    }

}
