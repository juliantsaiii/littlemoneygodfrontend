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
import com.ruoyi.project.sjwflowsys.domain.RoleFunction;
import com.ruoyi.project.sjwflowsys.service.IRoleFunctionService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 角色可访问功能Controller
 * 
 * @author littlemoneygod
 * @date 2020-08-23
 */
@RestController
@RequestMapping("/sjwflowsys/rolefunction")
public class RoleFunctionController extends BaseController
{
    @Autowired
    private IRoleFunctionService roleFunctionService;

    /**
     * 查询角色可访问功能列表
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:rolefunction:list')")
    @GetMapping("/list")
    public TableDataInfo list(RoleFunction roleFunction)
    {
        startPage();
        List<RoleFunction> list = roleFunctionService.selectRoleFunctionList(roleFunction);
        return getDataTable(list);
    }

    /**
     * 导出角色可访问功能列表
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:rolefunction:export')")
    @Log(title = "角色可访问功能", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(RoleFunction roleFunction)
    {
        List<RoleFunction> list = roleFunctionService.selectRoleFunctionList(roleFunction);
        ExcelUtil<RoleFunction> util = new ExcelUtil<RoleFunction>(RoleFunction.class);
        return util.exportExcel(list, "rolefunction");
    }

    /**
     * 获取角色可访问功能详细信息
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:rolefunction:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(roleFunctionService.selectRoleFunctionById(id));
    }

    /**
     * 新增角色可访问功能
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:rolefunction:add')")
    @Log(title = "角色可访问功能", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RoleFunction roleFunction)
    {
        return toAjax(roleFunctionService.insertRoleFunction(roleFunction));
    }

    /**
     * 修改角色可访问功能
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:rolefunction:edit')")
    @Log(title = "角色可访问功能", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RoleFunction roleFunction)
    {
        return toAjax(roleFunctionService.updateRoleFunction(roleFunction));
    }

    /**
     * 删除角色可访问功能
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:rolefunction:remove')")
    @Log(title = "角色可访问功能", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(roleFunctionService.deleteRoleFunctionByIds(ids));
    }

    /**
     * 根据roleid获取功能
     */
    @PostMapping("/getFunctionIDs")
    public AjaxResult getFunctionIDs(String roleid)
    {
        return AjaxResult.success(roleFunctionService.selectFunctionByRole(roleid));
    }
}
