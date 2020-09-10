package com.ruoyi.project.sjwflowsys.controller;

import java.util.List;
import java.util.UUID;

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
import com.ruoyi.project.sjwflowsys.domain.Wenshuseal;
import com.ruoyi.project.sjwflowsys.service.IWenshusealService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 盖章信息Controller
 * 
 * @author littlemoneygod
 * @date 2020-08-26
 */
@RestController
@RequestMapping("/sjwflowsys/wenshuseal")
public class WenshusealController extends BaseController
{
    @Autowired
    private IWenshusealService wenshusealService;

    /**
     * 查询盖章信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Wenshuseal wenshuseal)
    {
        startPage();
        List<Wenshuseal> list = wenshusealService.selectWenshusealList(wenshuseal);
        return getDataTable(list);
    }

    /**
     * 导出盖章信息列表
     */
    @Log(title = "盖章信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Wenshuseal wenshuseal)
    {
        List<Wenshuseal> list = wenshusealService.selectWenshusealList(wenshuseal);
        ExcelUtil<Wenshuseal> util = new ExcelUtil<Wenshuseal>(Wenshuseal.class);
        return util.exportExcel(list, "wenshuseal");
    }

    /**
     * 获取盖章信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(wenshusealService.selectWenshusealById(id));
    }

    /**
     * 新增盖章信息
     */
    @Log(title = "盖章信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Wenshuseal wenshuseal)
    {
        wenshuseal.setId(UUID.randomUUID().toString());
        return toAjax(wenshusealService.insertWenshuseal(wenshuseal));
    }

    /**
     * 批量新增盖章信息
     */
    @Log(title = "盖章信息", businessType = BusinessType.INSERT)
    @PostMapping("/addBatch")
    public AjaxResult addBatch(@RequestBody List<Wenshuseal> list)
    {
        wenshusealService.deleteWenshusealByWenshuID(list.get(0).getWenshuid());
        list.stream().forEach(s->s.setId(UUID.randomUUID().toString()));
        return toAjax(wenshusealService.insertWenshusealBatch(list));
    }


    /**
     * 修改盖章信息
     */
    @Log(title = "盖章信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Wenshuseal wenshuseal)
    {
        return toAjax(wenshusealService.updateWenshuseal(wenshuseal));
    }

    /**
     * 删除盖章信息
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:wenshuseal:remove')")
    @Log(title = "盖章信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(wenshusealService.deleteWenshusealByIds(ids));
    }
}
