package com.ruoyi.project.sjwflowsys.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.TreeEntity;
import com.ruoyi.framework.web.domain.TreeSelectStr;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.sjwflowsys.domain.User;
import com.ruoyi.project.sjwflowsys.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
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
    @Autowired
    private IUserService userService;
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
     * 查询部门树带节点
     */
    @GetMapping("/listTreeNode")
    public AjaxResult listTreeNode(@RequestParam(defaultValue = "dept") String type,@RequestParam String childID)
    {
        TreeSelectStr ts = new TreeSelectStr();
        if(type.equals("user"))
        {
            User user = userService.selectUserById(childID);
            User query = new User();
            query.setDeptid(user.getDeptid());
            List<User> userList = userService.selectUserList(query);
            List<TreeSelectStr> usertrees = userList.stream().map(TreeSelectStr::new).collect(Collectors.toList());
            ts = deptService.getDeptTree(user.getDeptid(),usertrees);
        }else{
            ts =  deptService.getDeptTree(childID,new ArrayList<>());
        }

        List<TreeSelectStr>  list = new ArrayList<>();
        list.add(ts);
        return AjaxResult.success(list);
    }

    /**
     * 查询部门树
     * @param pid
     * @param type "user"：查人树，"dept"：查部门树
     * @return
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:dept:list')")
    @GetMapping("/listTree")
    public AjaxResult listTree(@RequestParam(value="pid",required=false,defaultValue="-1")String pid,@RequestParam(value="type",defaultValue="dept")String type,@RequestParam(value="selectType" ,defaultValue="select")String selectType)
    {
        Dept dept = new Dept();
        if(!StringUtils.isNotEmpty(pid))
        {
            dept.setPid("-1");
        }else{
            dept.setPid(pid);
        }
        List<Dept> list = deptService.selectDeptList(dept);

        //查的是人，把用户转成部门加入集合
        if(type.equals("user"))
        {
            for(Dept d : list)
            {
                d.setHasChildren(true);
            }
            User user = new User();
            user.setDeptid(pid);
            List<User> userList = userService.selectUserList(user);
            for(User u : userList)
            {
                Dept d = new Dept();
                d.setId(u.getId());
                d.setName(u.getFullname());
                d.setHasChildren(false);
                list.add(d);
            }
        }
        else //查询的是部门，并且展示方式是tree，haschildren值与selecttree相反，
        {
            if(selectType.equals("tree"))
            {
                for(Dept d : list)
                {
                    d.setHasChildren(!d.getHasChildren());
                }
            }

        }
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
