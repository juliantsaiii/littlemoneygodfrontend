package com.ruoyi.project.sjwflowsys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.project.sjwflowsys.domain.Roledata;
import com.ruoyi.project.sjwflowsys.service.IRoledataService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;

/**
 * 角色可访问数据Controller
 * 
 * @author littlemoneygod
 * @date 2020-08-21
 */
@RestController
@RequestMapping("/sjwflowsys/roledata")
public class RoledataController extends BaseController
{
    @Autowired
    private IRoledataService roledataService;


    @PostMapping("/getDeptIDs")
    public AjaxResult getRoleDept(String roleid){
        String[] roles = {};
        Roledata roledata = roledataService.selectRoledataById(roleid);
        if(roledata!=null){
            roles = roledata.getBelongdepts().split(",");
        }
        return AjaxResult.success(roles);
    }

}
