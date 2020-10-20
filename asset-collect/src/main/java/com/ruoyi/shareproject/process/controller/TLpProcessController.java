package com.ruoyi.shareproject.process.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.report.domain.TLcReportDayProcess;
import com.ruoyi.report.service.ITLcReportDayProcessService;
import com.ruoyi.shareproject.monthlytarget.domain.TLpMonthlyTarget;
import com.ruoyi.shareproject.process.domain.TLpProcess;
import com.ruoyi.shareproject.process.service.ITLpProcessService;
import com.ruoyi.shareproject.projectinformation.domain.TLpProjectInformation;
import com.ruoyi.shareproject.projectinformation.service.ITLpProjectInformationService;
import com.ruoyi.utils.Response;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 【请填写功能名称】Controller
 *
 * @author liurunkai
 * @date 2020-10-16
 */
@Controller
@RequestMapping("/project/process")
public class TLpProcessController extends BaseController {
    private String prefix = "shareproject/process";

    @Autowired
    private ITLpProcessService tLpProcessService;
    @Autowired
    private ITLpProjectInformationService projectInformationService;


    @RequiresPermissions("project:process:view")
    @GetMapping()
    public String process(ModelMap modelMap) {
        List<TLpProjectInformation> projectList = this.projectInformationService.selectTLpProjectInformationList(new TLpProjectInformation());
        modelMap.put("projectList", projectList);
        return prefix + "/process";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("project:process:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLpProcess tLpProcess) {
        startPage();
        List<TLpProcess> list = tLpProcessService.selectTLpProcessList(tLpProcess);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("project:process:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLpProcess tLpProcess) {
        List<TLpProcess> list = tLpProcessService.selectTLpProcessList(tLpProcess);
        ExcelUtil<TLpProcess> util = new ExcelUtil<TLpProcess>(TLpProcess.class);
        return util.exportExcel(list, "process");
    }

    /**
     * 新增【请填写功能名称】
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存【请填写功能名称】
     */
    @RequiresPermissions("project:process:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLpProcess tLpProcess) {
        return toAjax(tLpProcessService.insertTLpProcess(tLpProcess));
    }

    /**
     * 修改【请填写功能名称】
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        TLpProcess tLpProcess = tLpProcessService.selectTLpProcessById(id);
        mmap.put("tLpProcess", tLpProcess);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("project:process:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLpProcess tLpProcess) {
        return toAjax(tLpProcessService.updateTLpProcess(tLpProcess));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("project:process:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tLpProcessService.deleteTLpProcessByIds(ids));
    }

    @PostMapping("selectDayProcess")
    @ResponseBody
    public Response selectDayProcess(TLpProcess tLpProcess) {
//        Map<String, Object> result = new HashMap<>();
        tLpProcess = this.tLpProcessService.selectDayProcess(tLpProcess);
        return Response.success(tLpProcess);
    }

    /**
     * 复制新增
     */
//    @RequiresPermissions("shareproject:monthlytarget:edit")
    @GetMapping("/copy")
    public String copyone(Long id, ModelMap mmap)
    {
        TLpProcess tLpProcess = this.tLpProcessService.selectTLpProcessById(id);
        mmap.put("tLpProcess", tLpProcess);
        return prefix + "/copy";
    }
}
