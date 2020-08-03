package com.ruoyi.project.sjwflowsys.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.sjwflowsys.domain.Workflowinfo;
import com.ruoyi.project.sjwflowsys.service.IWorkflowinfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 流程管理Controller
 * 
 * @author littlemoneygod
 * @date 2020-08-04
 */
@RestController
@RequestMapping("/sjwflowsys/workflowinfo")
public class WorkflowinfoController extends BaseController
{
    @Autowired
    private IWorkflowinfoService workflowinfoService;

    /**
     * 查询流程管理列表
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:workflowinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(Workflowinfo workflowinfo)
    {
        startPage();
        List<Workflowinfo> list = workflowinfoService.selectWorkflowinfoList(workflowinfo);
        return getDataTable(list);
    }

    /**
     * 导出流程管理列表
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:workflowinfo:export')")
    @Log(title = "流程管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Workflowinfo workflowinfo)
    {
        List<Workflowinfo> list = workflowinfoService.selectWorkflowinfoList(workflowinfo);
        ExcelUtil<Workflowinfo> util = new ExcelUtil<Workflowinfo>(Workflowinfo.class);
        return util.exportExcel(list, "workflowinfo");
    }

    /**
     * 获取流程管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:workflowinfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(workflowinfoService.selectWorkflowinfoById(id));
    }

    /**
     * 新增流程管理
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:workflowinfo:add')")
    @Log(title = "流程管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Workflowinfo workflowinfo)
    {
        return toAjax(workflowinfoService.insertWorkflowinfo(workflowinfo));
    }

    /**
     * 修改流程管理
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:workflowinfo:edit')")
    @Log(title = "流程管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Workflowinfo workflowinfo)
    {
        return toAjax(workflowinfoService.updateWorkflowinfo(workflowinfo));
    }

    /**
     * 删除流程管理
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:workflowinfo:remove')")
    @Log(title = "流程管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(workflowinfoService.deleteWorkflowinfoByIds(ids));
    }
}
