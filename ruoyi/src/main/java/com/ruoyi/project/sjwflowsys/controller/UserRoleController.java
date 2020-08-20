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
import com.ruoyi.project.sjwflowsys.domain.UserRole;
import com.ruoyi.project.sjwflowsys.service.IUserRoleService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 角色用户Controller
 * 
 * @author littlemoneygod
 * @date 2020-08-20
 */
@RestController
@RequestMapping("/sjwflowsys/userrole")
public class UserRoleController extends BaseController
{
    @Autowired
    private IUserRoleService userRoleService;

    /**
     * 查询角色用户列表
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:role:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserRole userRole)
    {
        startPage();
        List<UserRole> list = userRoleService.selectUserRoleList(userRole);
        return getDataTable(list);
    }

    /**
     * 导出角色用户列表
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:role:export')")
    @Log(title = "角色用户", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(UserRole userRole)
    {
        List<UserRole> list = userRoleService.selectUserRoleList(userRole);
        ExcelUtil<UserRole> util = new ExcelUtil<UserRole>(UserRole.class);
        return util.exportExcel(list, "role");
    }

    /**
     * 获取角色用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:role:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(userRoleService.selectUserRoleById(id));
    }

    /**
     * 新增角色用户
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:role:add')")
    @Log(title = "角色用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserRole userRole)
    {
        return toAjax(userRoleService.insertUserRole(userRole));
    }

    /**
     * 修改角色用户
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:role:edit')")
    @Log(title = "角色用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserRole userRole)
    {
        return toAjax(userRoleService.updateUserRole(userRole));
    }

    /**
     * 删除角色用户
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:role:remove')")
    @Log(title = "角色用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(userRoleService.deleteUserRoleByIds(ids));
    }
}
