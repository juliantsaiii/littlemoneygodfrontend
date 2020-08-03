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
import com.ruoyi.project.sjwflowsys.domain.Wenshu;
import com.ruoyi.project.sjwflowsys.service.IWenshuService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 文书管理Controller
 * 
 * @author littlemoneygod
 * @date 2020-08-04
 */
@RestController
@RequestMapping("/sjwflowsys/wenshu")
public class WenshuController extends BaseController
{
    @Autowired
    private IWenshuService wenshuService;

    /**
     * 查询文书管理列表
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:wenshu:list')")
    @GetMapping("/list")
    public TableDataInfo list(Wenshu wenshu)
    {
        startPage();
        List<Wenshu> list = wenshuService.selectWenshuList(wenshu);
        return getDataTable(list);
    }

    /**
     * 导出文书管理列表
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:wenshu:export')")
    @Log(title = "文书管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Wenshu wenshu)
    {
        List<Wenshu> list = wenshuService.selectWenshuList(wenshu);
        ExcelUtil<Wenshu> util = new ExcelUtil<Wenshu>(Wenshu.class);
        return util.exportExcel(list, "wenshu");
    }

    /**
     * 获取文书管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:wenshu:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(wenshuService.selectWenshuById(id));
    }

    /**
     * 新增文书管理
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:wenshu:add')")
    @Log(title = "文书管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Wenshu wenshu)
    {
        return toAjax(wenshuService.insertWenshu(wenshu));
    }

    /**
     * 修改文书管理
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:wenshu:edit')")
    @Log(title = "文书管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Wenshu wenshu)
    {
        return toAjax(wenshuService.updateWenshu(wenshu));
    }

    /**
     * 删除文书管理
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:wenshu:remove')")
    @Log(title = "文书管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(wenshuService.deleteWenshuByIds(ids));
    }
}
