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
import com.ruoyi.project.sjwflowsys.domain.Function;
import com.ruoyi.project.sjwflowsys.service.IFunctionService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 功能Controller
 * 
 * @author littlemoneygod
 * @date 2020-08-23
 */
@RestController
@RequestMapping("/sjwflowsys/function")
public class FunctionController extends BaseController
{
    @Autowired
    private IFunctionService functionService;

    /**
     * 查询功能列表
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:function:list')")
    @GetMapping("/list")
    public TableDataInfo list(Function function)
    {
        startPage();
        List<Function> list = functionService.selectFunctionList(function);
        return getDataTable(list);
    }

    /**
     * 导出功能列表
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:function:export')")
    @Log(title = "功能", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Function function)
    {
        List<Function> list = functionService.selectFunctionList(function);
        ExcelUtil<Function> util = new ExcelUtil<Function>(Function.class);
        return util.exportExcel(list, "function");
    }

    /**
     * 获取功能详细信息
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:function:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(functionService.selectFunctionById(id));
    }

    /**
     * 新增功能
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:function:add')")
    @Log(title = "功能", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Function function)
    {
        return toAjax(functionService.insertFunction(function));
    }

    /**
     * 修改功能
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:function:edit')")
    @Log(title = "功能", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Function function)
    {
        return toAjax(functionService.updateFunction(function));
    }

    /**
     * 删除功能
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:function:remove')")
    @Log(title = "功能", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(functionService.deleteFunctionByIds(ids));
    }

    /**
     * 根据部门查company
     * @param
     * @return
     */
    @PostMapping("/getfunctionTree")
    public AjaxResult getfunctionTree(){
        Function f = new Function();
        f.setPid("-1");
        List<Function> curfunction = functionService.selectFunctionList(f);
        f.setPid("");
        f.setDeleted(false);
        List<Function> functionList = functionService.selectFunctionList(f);
        return AjaxResult.success(functionService.getFunctionTree(curfunction,functionList));
    }
}
