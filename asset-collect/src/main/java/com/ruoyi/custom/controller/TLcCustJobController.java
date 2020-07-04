package com.ruoyi.custom.controller;

import java.util.List;

import com.ruoyi.custom.domain.TLcCustJob;
import com.ruoyi.custom.service.ITLcCustJobService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 客户工作单位信息Controller
 *
 * @author ruoyi
 * @date 2019-12-26
 */
@Controller
@RequestMapping("/collect/cust/job")
public class TLcCustJobController extends BaseController {
    private String prefix = "custom/job";

    @Autowired
    private ITLcCustJobService tLcCustJobService;

    @RequiresPermissions("system:job:view")
    @GetMapping()
    public String job() {
        return prefix + "/job";
    }

    /**
     * 查询客户工作单位信息列表
     */
    @RequiresPermissions("system:job:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcCustJob tLcCustJob) {
        startPage();
        List<TLcCustJob> list = tLcCustJobService.selectTLcCustJobList(tLcCustJob);
        return getDataTable(list);
    }

    /**
     * 导出客户工作单位信息列表
     */
    @RequiresPermissions("system:job:export")
    @Log(title = "客户工作单位信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcCustJob tLcCustJob) {
        List<TLcCustJob> list = tLcCustJobService.selectTLcCustJobList(tLcCustJob);
        ExcelUtil<TLcCustJob> util = new ExcelUtil<TLcCustJob>(TLcCustJob.class);
        return util.exportExcel(list, "job");
    }

    /**
     * 新增客户工作单位信息
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存客户工作单位信息
     */
    @RequiresPermissions("system:job:add")
    @Log(title = "客户工作单位信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLcCustJob tLcCustJob) {
        return toAjax(tLcCustJobService.insertTLcCustJob(tLcCustJob));
    }

    /**
     * 修改客户工作单位信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        TLcCustJob tLcCustJob = tLcCustJobService.selectTLcCustJobById(id);
        mmap.put("tLcCustJob", tLcCustJob);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户工作单位信息
     */
    @RequiresPermissions("system:job:edit")
    @Log(title = "客户工作单位信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLcCustJob tLcCustJob) {
        return toAjax(tLcCustJobService.updateTLcCustJob(tLcCustJob));
    }

    /**
     * 删除客户工作单位信息
     */
    @RequiresPermissions("system:job:remove")
    @Log(title = "客户工作单位信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tLcCustJobService.deleteTLcCustJobByIds(ids));
    }
}
