package com.ruoyi.project.sjwflowsys.controller;

import java.lang.reflect.Array;
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
import com.ruoyi.project.sjwflowsys.domain.Roledata;
import com.ruoyi.project.sjwflowsys.service.IRoledataService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

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
