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
import com.ruoyi.project.sjwflowsys.domain.Dict;
import com.ruoyi.project.sjwflowsys.service.IDictService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 业务字典Controller
 * 
 * @author littlemoneygod
 * @date 2020-08-04
 */
@RestController
@RequestMapping("/sjwflowsys/dict")
public class DictController extends BaseController
{
    @Autowired
    private IDictService dictService;

    /**
     * 查询业务字典列表
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:dict:list')")
    @GetMapping("/list")
    public TableDataInfo list(Dict dict)
    {
        startPage();
        List<Dict> list = dictService.selectDictList(dict);
        return getDataTable(list);
    }

    /**
     * 导出业务字典列表
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:dict:export')")
    @Log(title = "业务字典", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Dict dict)
    {
        List<Dict> list = dictService.selectDictList(dict);
        ExcelUtil<Dict> util = new ExcelUtil<Dict>(Dict.class);
        return util.exportExcel(list, "dict");
    }

    /**
     * 获取业务字典详细信息
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:dict:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(dictService.selectDictById(id));
    }

    /**
     * 新增业务字典
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:dict:add')")
    @Log(title = "业务字典", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Dict dict)
    {
        return toAjax(dictService.insertDict(dict));
    }

    /**
     * 修改业务字典
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:dict:edit')")
    @Log(title = "业务字典", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Dict dict)
    {
        return toAjax(dictService.updateDict(dict));
    }

    /**
     * 删除业务字典
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:dict:remove')")
    @Log(title = "业务字典", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(dictService.deleteDictByIds(ids));
    }
}
