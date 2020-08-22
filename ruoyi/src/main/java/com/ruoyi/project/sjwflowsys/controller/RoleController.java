package com.ruoyi.project.sjwflowsys.controller;

import java.util.List;
import java.util.UUID;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.sjwflowsys.domain.Roledata;
import com.ruoyi.project.sjwflowsys.service.IRoledataService;
import io.netty.util.internal.StringUtil;
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
import com.ruoyi.project.sjwflowsys.domain.Role;
import com.ruoyi.project.sjwflowsys.service.IRoleService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 角色管理Controller
 * 
 * @author littlemoneygod
 * @date 2020-08-19
 */
@RestController
@RequestMapping("/sjwflowsys/role")
public class RoleController extends BaseController
{
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IRoledataService roledataService;
    /**
     * 查询角色管理列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Role role)
    {
        startPage();
        List<Role> list = roleService.selectRoleList(role);
        return getDataTable(list);
    }

    /**
     * 导出角色管理列表
     */
    @Log(title = "角色管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Role role)
    {
        List<Role> list = roleService.selectRoleList(role);
        ExcelUtil<Role> util = new ExcelUtil<Role>(Role.class);
        return util.exportExcel(list, "role");
    }

    /**
     * 获取角色管理详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(roleService.selectRoleById(id));
    }

    /**
     * 新增角色管理
     */
    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Role role)
    {
        role.setId(UUID.randomUUID().toString());
        if(role.getBelongDepts().length != 0){
            String deptIds = String.join(",",role.getBelongDepts());
            Roledata rd = new Roledata(role,deptIds);
            rd.setId(UUID.randomUUID().toString());
            roledataService.insertRoledata(rd);
        }
        return toAjax(roleService.insertRole(role));
    }

    /**
     * 修改角色管理
     */
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Role role)
    {

        String deptIds = String.join(",",role.getBelongDepts());
        roledataService.deleteRoledataById(role.getId());
        if(!StringUtil.isNullOrEmpty(deptIds)){
            Roledata rd = new Roledata(role,deptIds);
            rd.setId(UUID.randomUUID().toString());
            roledataService.insertRoledata(rd);
        }
        return toAjax(roleService.updateRole(role));
    }

    /**
     * 删除角色管理
     */
    @Log(title = "角色管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(roleService.deleteRoleByIds(ids));
    }
}
