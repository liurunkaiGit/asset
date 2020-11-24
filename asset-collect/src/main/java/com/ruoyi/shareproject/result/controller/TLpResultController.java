package com.ruoyi.shareproject.result.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.shareproject.process.domain.TLpProcess;
import com.ruoyi.shareproject.projectinformation.domain.TLpProjectInformation;
import com.ruoyi.shareproject.projectinformation.service.ITLpProjectInformationService;
import com.ruoyi.shareproject.result.domain.TLpResult;
import com.ruoyi.shareproject.result.service.ITLpResultService;
import com.ruoyi.utils.Response;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 【请填写功能名称】Controller
 *
 * @author liurunkai
 * @date 2020-10-16
 */
@Controller
@RequestMapping("/project/result")
public class TLpResultController extends BaseController {
    private String prefix = "shareproject/result";

    @Autowired
    private ITLpResultService tLpResultService;
    @Autowired
    private ITLpProjectInformationService projectInformationService;

    @RequiresPermissions("project:result:view")
    @GetMapping()
    public String result(ModelMap modelMap) {
        List<TLpProjectInformation> projectList = this.projectInformationService.selectTLpProjectInformationList(new TLpProjectInformation());
        modelMap.put("projectList", projectList);
        return prefix + "/result";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @PostMapping("/selectById")
    @ResponseBody
    public Response selectById(Long id) {
        TLpProjectInformation projectInformation = projectInformationService.selectTLpProjectInformationById(id);
        return Response.success(projectInformation);
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("project:result:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLpResult tLpResult) {
        startPage();
        List<TLpResult> list = tLpResultService.selectTLpResultList(tLpResult);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("project:result:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLpResult tLpResult) {
        List<TLpResult> list = tLpResultService.selectTLpResultList(tLpResult);
        ExcelUtil<TLpResult> util = new ExcelUtil<TLpResult>(TLpResult.class);
        return util.exportExcel(list, "result");
    }

    /**
     * 新增【请填写功能名称】
     */
    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        List<TLpProjectInformation> projectList = this.projectInformationService.selectTLpProjectInformationList(new TLpProjectInformation());
        modelMap.put("projectList", projectList);
        modelMap.put("tdate", DateUtils.parseDateToStr("yyyy-MM-dd",new Date()));
        return prefix + "/add";
    }

    /**
     * 新增保存【请填写功能名称】
     */
    @RequiresPermissions("project:result:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLpResult tLpResult) {
        return toAjax(tLpResultService.insertTLpResult(tLpResult));
    }

    /**
     * 修改【请填写功能名称】
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        TLpResult tLpResult = tLpResultService.selectTLpResultById(id);
        mmap.put("tLpResult", tLpResult);
        mmap.put("transferTypeValue", tLpResult.getTransferType());
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("project:result:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLpResult tLpResult) {
        return toAjax(tLpResultService.updateTLpResult(tLpResult));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("project:result:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tLpResultService.deleteTLpResultByIds(ids));
    }

    /**
     * 查找唯一
     */
    @PostMapping("selectProjectResultUnique")
    @ResponseBody
    public Response selectProjectResultUnique(TLpResult tLpResult) {
        Integer count = this.tLpResultService.selectProjectResultUnique(tLpResult);
        return Response.success(count);
    }

    /**
     * 复制新增
     */
//    @RequiresPermissions("shareproject:monthlytarget:edit")
    @GetMapping("/copy")
    public String copyone(Long id, ModelMap mmap)
    {
        TLpResult tLpResult = this.tLpResultService.selectTLpResultById(id);
        mmap.put("tLpResult", tLpResult);
        mmap.put("transferTypeValue", tLpResult.getTransferType());
        return prefix + "/copy";
    }
}
