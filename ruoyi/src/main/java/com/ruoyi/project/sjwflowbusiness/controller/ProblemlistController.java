package com.ruoyi.project.sjwflowbusiness.controller;

import java.util.*;

import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.project.sjwflowbusiness.domain.CountMapper;
import org.apache.ibatis.annotations.Param;
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
import com.ruoyi.project.sjwflowbusiness.domain.Problemlist;
import com.ruoyi.project.sjwflowbusiness.service.IProblemlistService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 运维记录Controller
 * 
 * @author littlemoneygod
 * @date 2020-07-22
 */
@RestController
@RequestMapping("/sjwflowbusiness/problemlist")
public class ProblemlistController extends BaseController
{
    @Autowired
    private IProblemlistService problemlistService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 查询运维记录列表
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:problemlist:list')")
    @GetMapping("/list")
    public TableDataInfo list(Problemlist problemlist)
    {
        startPage();
        List<Problemlist> list = problemlistService.selectProblemlistList(problemlist);
        return getDataTable(list);
    }

    /**
     * 导出运维记录列表
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:problemlist:export')")
    @Log(title = "运维记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Problemlist problemlist)
    {
        List<Problemlist> list = problemlistService.selectProblemlistList(problemlist);
        ExcelUtil<Problemlist> util = new ExcelUtil<Problemlist>(Problemlist.class);
        return util.exportExcel(list, "problemlist");
    }

    /**
     * 获取运维记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:problemlist:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(problemlistService.selectProblemlistById(id));
    }

    /**
     * 新增运维记录
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:problemlist:add')")
    @Log(title = "运维记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Problemlist problemlist)
    {
        problemlist.setId(UUID.randomUUID().toString());
        return toAjax(problemlistService.insertProblemlist(problemlist));
    }

    /**
     * 修改运维记录
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:problemlist:edit')")
    @Log(title = "运维记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Problemlist problemlist)
    {
        return toAjax(problemlistService.updateProblemlist(problemlist));
    }

    /**
     * 删除运维记录
     */
    @PreAuthorize("@ss.hasPermi('sjwflowbusiness:problemlist:remove')")
    @Log(title = "运维记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(problemlistService.deleteProblemlistByIds(ids));
    }

    @PostMapping("/deleteRedis/{id}")
    public AjaxResult removeRedis(@PathVariable("id") String id)
    {
        redisCache.deleteObject(id,1);
        return toAjax(1);
    }

    @PostMapping("/selectServiceTypeCount")
    public AjaxResult selectServiceTypeCount()
    {
        return AjaxResult.success(problemlistService.selectServiceTypeCount());
    }

    @PostMapping("/selectCountAreaCount")
    public AjaxResult selectCountAreaCount()
    {
        return AjaxResult.success(problemlistService.selectCountAreaCount());
    }

    @PostMapping("/selectCountMonthCount")
    public AjaxResult selectCountMonthCount()
    {
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        List<CountMapper> phoneCount = problemlistService.selectCountMonthCount("电话解决",year);
        List<CountMapper> operateCount = problemlistService.selectCountMonthCount("运维解决",year);
        Map<String,List<CountMapper>> countData = new HashMap<>();
        countData.put("phoneCount",phoneCount);
        countData.put("operateCount",operateCount);
        return AjaxResult.success(countData);
    }
}