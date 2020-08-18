package com.ruoyi.project.sjwflowbusiness.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.sjwflowbusiness.domain.FileuploadDownload;
import com.ruoyi.project.sjwflowsys.domain.Dept;
import com.ruoyi.project.sjwflowsys.service.IDeptService;
import org.springframework.beans.factory.annotation.Value;
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
import com.ruoyi.project.sjwflowbusiness.domain.Fileupload;
import com.ruoyi.project.sjwflowbusiness.service.IFileuploadService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 附件管理Controller
 * 
 * @author littlemoneygod
 * @date 2020-07-21
 */
@RestController
@RequestMapping("/sjwflowbusiness/fileupload")
public class FileuploadController extends BaseController
{
    @Autowired
    private IFileuploadService fileuploadService;
    @Autowired
    private IDeptService deptService;
    //使用value注入
    @Value("${samba.path}")
    private String basepath;

    /**
     * 查询附件管理列表
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:fileupload:list')")
    @GetMapping("/list")
    public TableDataInfo list(Fileupload fileupload)
    {
        startPage();
        List<Fileupload> list = fileuploadService.selectFileuploadList(fileupload);
        return getDataTable(list);
    }

    /**
     * 导出附件管理列表
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:fileupload:export')")
    @Log(title = "附件管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Fileupload fileupload)
    {
        List<Fileupload> list = fileuploadService.selectFileuploadList(fileupload);
        ExcelUtil<Fileupload> util = new ExcelUtil<Fileupload>(Fileupload.class);
        return util.exportExcel(list, "fileupload");
    }

    /**
     * 获取附件管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:fileupload:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(fileuploadService.selectFileuploadById(id));
    }

    /**
     * 新增附件管理
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:fileupload:add')")
    @Log(title = "附件管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Fileupload fileupload)
    {
        return toAjax(fileuploadService.insertFileupload(fileupload));
    }

    /**
     * 修改附件管理
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:fileupload:edit')")
    @Log(title = "附件管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Fileupload fileupload)
    {
        return toAjax(fileuploadService.updateFileupload(fileupload));
    }

    /**
     * 删除附件管理
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:fileupload:remove')")
    @Log(title = "附件管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(fileuploadService.deleteFileuploadByIds(ids));
    }

    @GetMapping("/getfilepath/{id}")
    public AjaxResult getfilepath(@PathVariable String id)
    {
        FileuploadDownload fd = fileuploadService.selectDownloadMsg(id);
        if(fd == null){
           return AjaxResult.error("文件不存在！");
        }else{
            String companyid = fd.companyid;
            //若company不是省纪委则要取父级ID
            if(!fd.companyid.equals("a6eb2005-3bb0-49b1-9e2e-793ede3c367d")){
                Dept dept = deptService.selectDeptById(companyid);
                companyid = dept.getPid();
            }
            String path = fileuploadService.selectMapperIPAddress(companyid);
            if(StringUtils.isEmpty(path))
            {
                return AjaxResult.error("未找到该文件的创建者！");
            }else{
                path = path.replace("\\\\","").replace("\\","/") + basepath + fd.basepath + fd.getId() + fd.getFileextend();
                Map<String,String> map = new HashMap<>();
                map.put("filename",fd.getFilename() + fd.getFileextend());
                map.put("filepath",path);
                return AjaxResult.success(map);
            }
        }
    }
}
