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
import com.ruoyi.project.sjwflowsys.domain.User;
import com.ruoyi.project.sjwflowsys.service.IUserService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

import static com.ruoyi.common.utils.security.Md5Utils.hash;

/**
 * 业务平台用户管理Controller
 * 
 * @author littlemoneygod
 * @date 2020-07-15
 */
@RestController
@RequestMapping("/sjwflowsys/user")
public class UserController extends BaseController
{
    @Autowired
    private IUserService userService;

    /**
     * 查询业务平台用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(User user)
    {
        startPage();
        List<User> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    /**
     * 导出业务平台用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:user:export')")
    @Log(title = "业务平台用户管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(User user)
    {
        List<User> list = userService.selectUserList(user);
        ExcelUtil<User> util = new ExcelUtil<User>(User.class);
        return util.exportExcel(list, "user");
    }

    /**
     * 获取业务平台用户管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:user:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(userService.selectUserById(id));
    }

    /**
     * 新增业务平台用户管理
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:user:add')")
    @Log(title = "业务平台用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody User user)
    {
        if (userService.checkUserNameUnique(user.getName())==1)
        {
            return AjaxResult.error("新增用户'" + user.getName() + "'失败，该账号已存在");
        }
        user.setId(UUID.randomUUID().toString());
        user.setPassword(hash(user.getPassword()));
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改业务平台用户管理
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:user:edit')")
    @Log(title = "业务平台用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody User user)
    {
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除业务平台用户管理
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:user:remove')")
    @Log(title = "业务平台用户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(userService.deleteUserByIds(ids));
    }

}
