package com.ruoyi.shareproject.hmrule.controller;

import java.util.List;

import com.ruoyi.shareproject.hmrule.domain.TLjRuleDetails;
import com.ruoyi.shareproject.hmrule.service.ITLjRuleDetailsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【规则详情】Controller
 * 
 * @author gaohg
 * @date 2020-11-02
 */
@Controller
@RequestMapping("/shareproject/details")
public class TLjRuleDetailsController extends BaseController
{
    private String prefix = "shareproject/details";

    @Autowired
    private ITLjRuleDetailsService tLjRuleDetailsService;

    @RequiresPermissions("shareproject:details:view")
    @GetMapping("view/{id}")
    public String details(@PathVariable("id") Long id, ModelMap mmap)
    {
        mmap.put("ruleId",id);
        return prefix + "/details";
    }

    /**
     * 查询【规则详情】列表
     */
    @RequiresPermissions("shareproject:details:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLjRuleDetails tLjRuleDetails)
    {
        startPage();
        List<TLjRuleDetails> list = tLjRuleDetailsService.selectTLjRuleDetailsList(tLjRuleDetails);
        return getDataTable(list);
    }

    /**
     * 导出【规则详情】列表
     */
    @RequiresPermissions("shareproject:details:export")
    @Log(title = "【规则详情】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLjRuleDetails tLjRuleDetails)
    {
        List<TLjRuleDetails> list = tLjRuleDetailsService.selectTLjRuleDetailsList(tLjRuleDetails);
        ExcelUtil<TLjRuleDetails> util = new ExcelUtil<TLjRuleDetails>(TLjRuleDetails.class);
        return util.exportExcel(list, "details");
    }


    @Log(title = "【规则详情-时间段是否可用】", businessType = BusinessType.EXPORT)
    @PostMapping("/tmisuse")
    @ResponseBody
    public AjaxResult tmisuse(TLjRuleDetails tLjRuleDetails)
    {
        return  toAjax(tLjRuleDetailsService.tmisuse(tLjRuleDetails));
    }

    /**
     * 新增【规则详情】
     */
    @GetMapping("/add")
    public String add(Long id, ModelMap mmap)
    {
        mmap.put("ruleId",id);
        return prefix + "/add";
    }

    /**
     * 新增保存【规则详情】
     */
    @RequiresPermissions("shareproject:details:add")
    @Log(title = "【规则详情】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLjRuleDetails tLjRuleDetails)
    {
        return toAjax(tLjRuleDetailsService.insertTLjRuleDetails(tLjRuleDetails));
    }

    /**
     * 修改【规则详情】
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TLjRuleDetails tLjRuleDetails = tLjRuleDetailsService.selectTLjRuleDetailsById(id);
        mmap.put("tLjRuleDetails", tLjRuleDetails);
        return prefix + "/edit";
    }

    /**
     * 修改保存【规则详情】
     */
    @RequiresPermissions("shareproject:details:edit")
    @Log(title = "【规则详情】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLjRuleDetails tLjRuleDetails)
    {
        return toAjax(tLjRuleDetailsService.updateTLjRuleDetails(tLjRuleDetails));
    }

    /**
     * 删除【规则详情】
     */
    @RequiresPermissions("shareproject:details:remove")
    @Log(title = "【规则详情】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tLjRuleDetailsService.deleteTLjRuleDetailsByIds(ids));
    }
}
