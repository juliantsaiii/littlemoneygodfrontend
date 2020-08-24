package com.ruoyi.project.sjwflowsys.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.file.SambFileUtil;
import com.ruoyi.framework.web.domain.ElTag;
import com.ruoyi.project.sjwflowbusiness.service.IWorkflowtaskService;
import com.ruoyi.project.sjwflowsys.domain.UserRole;
import com.ruoyi.project.sjwflowsys.service.IUserRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    @Value("${sjwflowbusiness.initpwd}")
    private String initpwd;

    @Autowired
    private IWorkflowtaskService workflowtaskService;

    @Autowired
    private SambFileUtil smbfileutil;

    @Autowired
    private IUserRoleService userRoleService;


    /**
     * 查询业务平台用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(User user, HttpServletRequest request)
    {
        startPage();
        List<User> list = userService.selectUserList(user);
        if(list.size()==1)
        {
            HttpSession session = request.getSession();
            session.setAttribute("info",list.get(0));
        }
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
    public AjaxResult edit(@RequestBody User user,String carrywaitlist)
    {
        User oldUser = userService.selectUserById(user.getId());
        if(oldUser == null){
            return AjaxResult.error("用户不存在！");
        }
        //判断部门是否相同，若更换部门，则将该用户设为未激活并且添加新用户
        if(!user.getDeptid().equals(oldUser.getDeptid()))
        {
            User userNew = new User();
            BeanUtils.copyProperties(oldUser,userNew);
            userNew.setId(UUID.randomUUID().toString());
            userService.insertUser(userNew);
            //携带签名图片
            try {
                smbfileutil.smbCopy("/Signature/"+oldUser.getId()+".png","/Signature/",userNew.getId()+".png");
            } catch (IOException e) {

            }

            Map<String,String> map = new HashMap<>();
            map.put("newuserid",userNew.getId());
            map.put("newusername",user.getFullname());
            map.put("olduserid",oldUser.getId());
            //如果携带待办事项移交待办事项
            if(carrywaitlist.equals("1")){
                workflowtaskService.changeReceverByUser(map);
            }
            //更换角色
            UserRole ur = new UserRole();
            ur.setUserid(oldUser.getId());
            ur.setDeleted(false);
            List<UserRole> urList = userRoleService.selectUserRoleList(ur);
            if(urList.size()==1){
                ur = urList.get(0);
                ur.setId(UUID.randomUUID().toString());
                ur.setUserid(userNew.getId());
                userRoleService.insertUserRole(ur);
            }


            oldUser.setActivated("0");
            userService.updateUser(oldUser);

            //更新新用户的其他信息
            user.setId(userNew.getId());
            user.setActivated("1");
            return toAjax(userService.updateUser(user));
        }
        else
        {
            return toAjax(userService.updateUser(user));
        }

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

    @PutMapping("/restpassword")
    public AjaxResult restpassword(String id){
        String pwdmd5 = DigestUtils.md5DigestAsHex(initpwd.getBytes());
        User user = new User();
        user.setPassword(pwdmd5);
        user.setId(id);
        return toAjax(userService.updateUser(user));
    }


    @PostMapping("/getUserByRole")
    public  AjaxResult getUserByRole(String roleid){
        List<User> users = userService.selectUserByRole(roleid);
        List<ElTag> elTags = users.stream().map(ElTag::new).collect(Collectors.toList());
        return AjaxResult.success(elTags);
    }
}
