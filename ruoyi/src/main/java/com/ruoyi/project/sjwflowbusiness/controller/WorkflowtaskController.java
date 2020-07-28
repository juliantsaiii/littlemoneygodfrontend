package com.ruoyi.project.sjwflowbusiness.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.tool.workflowinfoview.domain.Workflowinfoview;
import com.ruoyi.project.tool.workflowinfoview.service.IWorkflowinfoviewService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.sjwflowbusiness.domain.Workflowtask;
import com.ruoyi.project.sjwflowbusiness.service.IWorkflowtaskService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 流程记录Controller
 * 
 * @author littlemoneygod
 * @date 2020-07-18
 */
@RestController
@RequestMapping("/sjwflowbusiness/workflowtask")
public class WorkflowtaskController extends BaseController
{
    @Autowired
    private IWorkflowtaskService workflowtaskService;
    @Autowired
    private IWorkflowinfoviewService workflowinfoviewService;

    /**
     * 查询流程记录列表
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:workflowtask:list')")
    @GetMapping("/list")
    public TableDataInfo list(Workflowtask workflowtask)
    {
        startPage();
        List<Workflowtask> list = workflowtaskService.selectWorkflowtaskList(workflowtask);
        return getDataTable(list);
    }

    /**
     * 导出流程记录列表
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:workflowtask:export')")
    @Log(title = "流程记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Workflowtask workflowtask)
    {
        List<Workflowtask> list = workflowtaskService.selectWorkflowtaskList(workflowtask);
        ExcelUtil<Workflowtask> util = new ExcelUtil<Workflowtask>(Workflowtask.class);
        return util.exportExcel(list, "workflowtask");
    }

    /**
     * 获取流程记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:workflowtask:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(workflowtaskService.selectWorkflowtaskById(id));
    }

    /**
     * 新增流程记录
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:workflowtask:add')")
    @Log(title = "流程记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Workflowtask workflowtask)
    {
        return toAjax(workflowtaskService.insertWorkflowtask(workflowtask));
    }

    /**
     * 修改流程记录
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:workflowtask:edit')")
    @Log(title = "流程记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Workflowtask workflowtask)
    {
        return toAjax(workflowtaskService.updateWorkflowtask(workflowtask));
    }

    /**
     * 删除流程记录
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:workflowtask:remove')")
    @Log(title = "流程记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remopubve(@PathVariable String[] ids)
    {
        return toAjax(workflowtaskService.deleteWorkflowtaskByIds(ids));
    }

    /**
     * 根据clueid获取最新流程所有task记录
     * @param clueid
     * @return
     */
    @RequestMapping(value = "/getCurrentSteps{clueid}")
    public AjaxResult getCurrentSteps(@PathVariable String clueid)
    {
        List<Workflowtask> list = workflowtaskService.selectWorkflowtaskSteps(clueid);
        String stepIds = "";
        String flowid = "";
        for(Workflowtask wk : list)
        {
            stepIds += wk.getStepid()+",";
        }
        if(stepIds!="")
        {
            stepIds = stepIds.substring(0,stepIds.length()-1);
            flowid = list.get(0).getFlowid();
        }
        Workflowinfoview wf = workflowinfoviewService.selectWorkflowinfoviewById(flowid);
        Map<String,String> reMap = new HashMap<String,String>();
        reMap.put("stepIds",stepIds);
        reMap.put("stepjson",wf.getJson());
        return AjaxResult.success(reMap);
    }

}
