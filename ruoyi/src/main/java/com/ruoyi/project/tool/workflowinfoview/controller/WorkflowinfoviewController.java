package com.ruoyi.project.tool.workflowinfoview.controller;

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
import com.ruoyi.project.tool.workflowinfoview.domain.Workflowinfoview;
import com.ruoyi.project.tool.workflowinfoview.service.IWorkflowinfoviewService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 流程图设计Controller
 * 
 * @author littlemoneygod
 * @date 2020-07-20
 */
@RestController
@RequestMapping("/tool/workflowinfoview")
public class WorkflowinfoviewController extends BaseController
{
    @Autowired
    private IWorkflowinfoviewService workflowinfoviewService;

    /**
     * 查询流程图设计列表
     */
    @PreAuthorize("@ss.hasPermi('tool:workflowinfoview:list')")
    @GetMapping("/list")
    public TableDataInfo list(Workflowinfoview workflowinfoview)
    {
        startPage();
        List<Workflowinfoview> list = workflowinfoviewService.selectWorkflowinfoviewList(workflowinfoview);
        return getDataTable(list);
    }

    /**
     * 导出流程图设计列表
     */
    @PreAuthorize("@ss.hasPermi('tool:workflowinfoview:export')")
    @Log(title = "流程图设计", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Workflowinfoview workflowinfoview)
    {
        List<Workflowinfoview> list = workflowinfoviewService.selectWorkflowinfoviewList(workflowinfoview);
        ExcelUtil<Workflowinfoview> util = new ExcelUtil<Workflowinfoview>(Workflowinfoview.class);
        return util.exportExcel(list, "workflowinfoview");
    }

    /**
     * 获取流程图设计详细信息
     */
    @PreAuthorize("@ss.hasPermi('tool:workflowinfoview:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(workflowinfoviewService.selectWorkflowinfoviewById(id));
    }

    /**
     * 新增流程图设计
     */
    @PreAuthorize("@ss.hasPermi('tool:workflowinfoview:add')")
    @Log(title = "流程图设计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Workflowinfoview workflowinfoview)
    {
        return toAjax(workflowinfoviewService.insertWorkflowinfoview(workflowinfoview));
    }

    /**
     * 修改流程图设计
     */
    @PreAuthorize("@ss.hasPermi('tool:workflowinfoview:edit')")
    @Log(title = "流程图设计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Workflowinfoview workflowinfoview)
    {
        return toAjax(workflowinfoviewService.updateWorkflowinfoview(workflowinfoview));
    }

    /**
     * 删除流程图设计
     */
    @PreAuthorize("@ss.hasPermi('tool:workflowinfoview:remove')")
    @Log(title = "流程图设计", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(workflowinfoviewService.deleteWorkflowinfoviewByIds(ids));
    }
}
