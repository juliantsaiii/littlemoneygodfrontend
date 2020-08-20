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
import com.ruoyi.project.sjwflowsys.domain.Leadersignature;
import com.ruoyi.project.sjwflowsys.service.ILeadersignatureService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 签名管理Controller
 * 
 * @author littlemoneygod
 * @date 2020-08-20
 */
@RestController
@RequestMapping("/sjwflowsys/leadersignature")
public class LeadersignatureController extends BaseController
{
    @Autowired
    private ILeadersignatureService leadersignatureService;

    /**
     * 查询签名管理列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Leadersignature leadersignature)
    {
        startPage();
        List<Leadersignature> list = leadersignatureService.selectLeadersignatureList(leadersignature);
        return getDataTable(list);
    }

    /**
     * 导出签名管理列表
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:leadersignature:export')")
    @Log(title = "签名管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Leadersignature leadersignature)
    {
        List<Leadersignature> list = leadersignatureService.selectLeadersignatureList(leadersignature);
        ExcelUtil<Leadersignature> util = new ExcelUtil<Leadersignature>(Leadersignature.class);
        return util.exportExcel(list, "leadersignature");
    }

    /**
     * 获取签名管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:leadersignature:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(leadersignatureService.selectLeadersignatureById(id));
    }

    /**
     * 新增签名管理
     */
    @PostMapping("/add")
    public AjaxResult add(String userid)
    {
        Leadersignature lea = new Leadersignature();
        lea.setName(userid);
        List<Leadersignature> leas = leadersignatureService.selectLeadersignatureList(lea);
        if(leas.size()==0){
            lea.setId(UUID.randomUUID().toString());
            lea.setPath("~/Signature/" + userid + ".png");
            leadersignatureService.insertLeadersignature(lea);
        }
        return AjaxResult.success();
    }

    /**
     * 修改签名管理
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:leadersignature:edit')")
    @Log(title = "签名管理", businessType = BusinessType.UPDATE)
    @PostMapping
    public AjaxResult edit(@RequestBody Leadersignature leadersignature)
    {
        return toAjax(leadersignatureService.updateLeadersignature(leadersignature));
    }

    /**
     * 删除签名管理
     */
    @PreAuthorize("@ss.hasPermi('sjwflowsys:leadersignature:remove')")
    @Log(title = "签名管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(leadersignatureService.deleteLeadersignatureByIds(ids));
    }
}
