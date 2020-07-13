package com.ruoyi.project.sjwflowsys.controller;

import java.util.List;
import java.util.UUID;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
import com.ruoyi.project.sjwflowsys.domain.Dept;
import com.ruoyi.project.sjwflowsys.service.IDeptService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 部门Controller
 * 
 * @author littlemoneygod
 * @date 2020-07-06
 */
@Api("部门管理")
@RestController
@RequestMapping("/sjwflowsys/dept")
public class DeptController extends BaseController
{
    @Autowired
    private IDeptService deptService;

    /**
     * 查询部门列表
     */
    @ApiOperation("查询部门")
    @PreAuthorize("@ss.hasPermi('sjwflowsys:dept:list')")
    @GetMapping("/list")
    public AjaxResult list(Dept dept)
    {
        if(!StringUtils.isNotEmpty(dept.getPid()))
        {
            dept.setPid("-1");
        }
        List<Dept> list = deptService.selectDeptList(dept);
        return AjaxResult.success(list);
    }


    /**
     * 导出部门列表
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:dept:export')")
    @Log(title = "部门", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Dept dept)
    {
        List<Dept> list = deptService.selectDeptList(dept);
        ExcelUtil<Dept> util = new ExcelUtil<Dept>(Dept.class);
        return util.exportExcel(list, "dept");
    }

    /**
     * 获取部门详细信息
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:dept:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(deptService.selectDeptById(id));
    }

    /**
     * 新增部门
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:dept:add')")
    @Log(title = "部门", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Dept dept)
    {
        dept.setId(UUID.randomUUID().toString());
        Dept pdept = deptService.selectDeptById(dept.getPid());
        //父级设为有子节点
        if(!pdept.getHasChild())
        {
            pdept.setHasChild(true);
            deptService.updateDept(pdept);
        }
        return toAjax(deptService.insertDept(dept));
    }

    /**
     * 修改部门
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:dept:edit')")
    @Log(title = "部门", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Dept dept)
    {
        return toAjax(deptService.updateDept(dept));
    }

    /**
     * 删除部门
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:dept:remove')")
    @Log(title = "部门", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(deptService.deleteDeptByIds(ids));
    }
}
