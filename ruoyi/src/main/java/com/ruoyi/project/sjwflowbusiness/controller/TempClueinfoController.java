package com.ruoyi.project.sjwflowbusiness.controller;

import java.util.List;

import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.security.service.TokenService;
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
import com.ruoyi.project.sjwflowbusiness.domain.TempClueinfo;
import com.ruoyi.project.sjwflowbusiness.service.ITempClueinfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 线索操作Controller
 * 
 * @author littlemoneygod
 * @date 2020-07-18
 */
@RestController
@RequestMapping("/sjwflowbusiness/tempclueinfo")
public class TempClueinfoController extends BaseController
{
    @Autowired
    private ITempClueinfoService tempClueinfoService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询线索操作列表
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:tempclueinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(TempClueinfo tempClueinfo)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if(!loginUser.getUser().getArea().contains("00000000-0000-1000-0000-000000000000")){
            tempClueinfo.setCreatecompanyid(loginUser.getUser().getArea());
        }
        List<TempClueinfo> list = tempClueinfoService.selectTempClueinfoList(tempClueinfo);
        return getDataTable(list);
    }

    /**
     * 导出线索操作列表
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:tempclueinfo:export')")
    @Log(title = "线索操作", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TempClueinfo tempClueinfo)
    {
        List<TempClueinfo> list = tempClueinfoService.selectTempClueinfoList(tempClueinfo);
        ExcelUtil<TempClueinfo> util = new ExcelUtil<TempClueinfo>(TempClueinfo.class);
        return util.exportExcel(list, "tempclueinfo");
    }

    /**
     * 获取线索操作详细信息
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:tempclueinfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(tempClueinfoService.selectTempClueinfoById(id));
    }

    /**
     * 新增线索操作
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:tempclueinfo:add')")
    @Log(title = "线索操作", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TempClueinfo tempClueinfo)
    {
        return toAjax(tempClueinfoService.insertTempClueinfo(tempClueinfo));
    }

    /**
     * 修改线索操作
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:tempclueinfo:edit')")
    @Log(title = "线索操作", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TempClueinfo tempClueinfo)
    {
        return toAjax(tempClueinfoService.updateTempClueinfo(tempClueinfo));
    }

    /**
     * 删除线索操作
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:tempclueinfo:remove')")
    @Log(title = "线索操作", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tempClueinfoService.deleteTempClueinfoByIds(ids));
    }
}
