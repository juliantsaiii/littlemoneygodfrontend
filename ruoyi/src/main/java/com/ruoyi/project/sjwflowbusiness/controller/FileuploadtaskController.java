package com.ruoyi.project.sjwflowbusiness.controller;

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
import com.ruoyi.project.sjwflowbusiness.domain.Fileuploadtask;
import com.ruoyi.project.sjwflowbusiness.service.IFileuploadtaskService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 附件历史Controller
 * 
 * @author littlemoneygod
 * @date 2020-07-21
 */
@RestController
@RequestMapping("/sjwflowbusiness/fileuploadtask")
public class FileuploadtaskController extends BaseController
{
    @Autowired
    private IFileuploadtaskService fileuploadtaskService;

    /**
     * 查询附件历史列表
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:fileuploadtask:list')")
    @GetMapping("/list")
    public TableDataInfo list(Fileuploadtask fileuploadtask)
    {
        startPage();
        List<Fileuploadtask> list = fileuploadtaskService.selectFileuploadtaskList(fileuploadtask);
        return getDataTable(list);
    }

    /**
     * 导出附件历史列表
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:fileuploadtask:export')")
    @Log(title = "附件历史", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Fileuploadtask fileuploadtask)
    {
        List<Fileuploadtask> list = fileuploadtaskService.selectFileuploadtaskList(fileuploadtask);
        ExcelUtil<Fileuploadtask> util = new ExcelUtil<Fileuploadtask>(Fileuploadtask.class);
        return util.exportExcel(list, "fileuploadtask");
    }

    /**
     * 获取附件历史详细信息
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:fileuploadtask:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(fileuploadtaskService.selectFileuploadtaskById(id));
    }

    /**
     * 新增附件历史
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:fileuploadtask:add')")
    @Log(title = "附件历史", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Fileuploadtask fileuploadtask)
    {
        return toAjax(fileuploadtaskService.insertFileuploadtask(fileuploadtask));
    }

    /**
     * 修改附件历史
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:fileuploadtask:edit')")
    @Log(title = "附件历史", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Fileuploadtask fileuploadtask)
    {
        return toAjax(fileuploadtaskService.updateFileuploadtask(fileuploadtask));
    }

    /**
     * 删除附件历史
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:fileuploadtask:remove')")
    @Log(title = "附件历史", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(fileuploadtaskService.deleteFileuploadtaskByIds(ids));
    }
}
