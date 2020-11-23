package com.ruoyi.shareproject.hmuserst.controller;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.shareproject.hmuserst.domain.TLjRuleUserLogs;
import com.ruoyi.shareproject.hmuserst.service.ITLjRuleUserLogsService;
import com.ruoyi.shareproject.projectinformation.domain.TLpProjectInformation;
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
 * 【员工状态】Controller
 * 
 * @author gaohg
 * @date 2020-11-05
 */
@Controller
@RequestMapping("/shareproject/userlogs")
public class TLjRuleUserLogsController extends BaseController
{
    private String prefix = "shareproject/userlogs";

    @Autowired
    private ITLjRuleUserLogsService tLjRuleUserLogsService;

    @RequiresPermissions("shareproject:userlogs:view")
    @GetMapping()
    public String logs(ModelMap mmap)
    {
        mmap.put("datamin", DateUtils.getDate());
        return prefix + "/userlogs";
    }

    /**
     * 查询【员工状态】列表
     */
    @RequiresPermissions("shareproject:userlogs:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLjRuleUserLogs tLjRuleUserLogs)
    {
        startPage();
        List<TLjRuleUserLogs> list = tLjRuleUserLogsService.selectTLjRuleUserLogsList(tLjRuleUserLogs);
        return getDataTable(list);
    }

    @GetMapping("/lookups/{id}")
    public String lookups(@PathVariable("id") Long id, ModelMap mmap)
    {
        TLjRuleUserLogs tLjRuleUserLogs = tLjRuleUserLogsService.selectTLjRuleUserLogsById(id);
        mmap.put("tLjRuleUserLogs", tLjRuleUserLogs);
        return prefix + "/lookups";
    }

    /**
     * 导出【员工状态】列表
     */
    @RequiresPermissions("shareproject:userlogs:export")
    @Log(title = "【员工状态】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLjRuleUserLogs tLjRuleUserLogs)
    {
        List<TLjRuleUserLogs> list = tLjRuleUserLogsService.selectTLjRuleUserLogsList(tLjRuleUserLogs);
        ExcelUtil<TLjRuleUserLogs> util = new ExcelUtil<TLjRuleUserLogs>(TLjRuleUserLogs.class);
        return util.exportExcel(list, "logs");
    }

    /**
     * 新增【员工状态】
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存【员工状态】
     */
    @RequiresPermissions("shareproject:userlogs:add")
    @Log(title = "【员工状态】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLjRuleUserLogs tLjRuleUserLogs)
    {
        return toAjax(tLjRuleUserLogsService.insertTLjRuleUserLogs(tLjRuleUserLogs));
    }

    /**
     * 修改【员工状态】
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TLjRuleUserLogs tLjRuleUserLogs = tLjRuleUserLogsService.selectTLjRuleUserLogsById(id);
        mmap.put("tLjRuleUserLogs", tLjRuleUserLogs);
        return prefix + "/edit";
    }

    /**
     * 修改保存【员工状态】
     */
    @RequiresPermissions("shareproject:userlogs:edit")
    @Log(title = "【员工状态】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLjRuleUserLogs tLjRuleUserLogs)
    {
        return toAjax(tLjRuleUserLogsService.updateTLjRuleUserLogs(tLjRuleUserLogs));
    }

    /**
     * 删除【员工状态】
     */
    @RequiresPermissions("shareproject:userlogs:remove")
    @Log(title = "【员工状态】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tLjRuleUserLogsService.deleteTLjRuleUserLogsByIds(ids));
    }
}
