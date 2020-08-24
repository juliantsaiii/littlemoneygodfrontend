package com.ruoyi.project.tool.dynamic.controller;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
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
import com.ruoyi.project.tool.dynamic.domain.Dynamictoken;
import com.ruoyi.project.tool.dynamic.service.IDynamictokenService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.utils.security.Md5Utils;
/**
 * 口令Controller
 * 
 * @author littlemoneygod
 * @date 2020-07-22
 */
@RestController
@RequestMapping("/tool/dynamictoken")
public class DynamictokenController extends BaseController
{
    @Autowired
    private IDynamictokenService dynamictokenService;

    /**
     * 查询口令列表
     */
    @PreAuthorize("@ss.hasPermi('tool:dynamictoken:list')")
    @GetMapping("/list")
    public TableDataInfo list(Dynamictoken dynamictoken)
    {
        startPage();
        List<Dynamictoken> list = dynamictokenService.selectDynamictokenList(dynamictoken);
        return getDataTable(list);
    }

    /**
     * 导出口令列表
     */
    @PreAuthorize("@ss.hasPermi('tool:dynamictoken:export')")
    @Log(title = "口令", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Dynamictoken dynamictoken)
    {
        List<Dynamictoken> list = dynamictokenService.selectDynamictokenList(dynamictoken);
        ExcelUtil<Dynamictoken> util = new ExcelUtil<Dynamictoken>(Dynamictoken.class);
        return util.exportExcel(list, "dynamictoken");
    }

    /**
     * 获取口令详细信息
     */
    @GetMapping
    public AjaxResult getInfo()
    {
        String id = "63c9ff11-f3dc-11e9-88ab-000c2956aa28";
        Dynamictoken dy = dynamictokenService.selectDynamictokenById(id);
        Instant now = Instant.now();
        Instant last = dy.getCurrenttime().toInstant();
        long seconds =  Duration.between(last,now).getSeconds();
        if(seconds > dy.getPrescription())
        {
            dy.setToken(Md5Utils.generateShortUuid());
            dy.setCurrenttime(new Date());
            dynamictokenService.updateDynamictoken(dy);
        }
        return AjaxResult.success(dy.getToken());
    }

    /**
     * 新增口令
     */
    @PreAuthorize("@ss.hasPermi('tool:dynamictoken:add')")
    @Log(title = "口令", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Dynamictoken dynamictoken)
    {
        return toAjax(dynamictokenService.insertDynamictoken(dynamictoken));
    }

    /**
     * 修改口令
     */
    @PreAuthorize("@ss.hasPermi('tool:dynamictoken:edit')")
    @Log(title = "口令", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Dynamictoken dynamictoken)
    {
        return toAjax(dynamictokenService.updateDynamictoken(dynamictoken));
    }

    /**
     * 删除口令
     */
    @PreAuthorize("@ss.hasPermi('tool:dynamictoken:remove')")
    @Log(title = "口令", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(dynamictokenService.deleteDynamictokenByIds(ids));
    }
}
