package com.ruoyi.shareproject.hmrule.controller;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.shareproject.hmrule.domain.TLjRule;
import com.ruoyi.shareproject.hmrule.domain.TLjRuleDetails;
import com.ruoyi.shareproject.hmrule.service.ITLjRuleService;
import net.sf.json.JSONArray;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【居家规则】Controller
 * 
 * @author gaohg
 * @date 2020-11-02
 */
@Controller
@RequestMapping("/shareproject/hmrule")
public class TLjRuleController extends BaseController
{
    private String prefix = "shareproject/hmrule";

    @Autowired
    private ITLjRuleService tLjRuleService;

    @RequiresPermissions("shareproject:rule:view")
    @GetMapping()
    public String rule()
    {
        return prefix + "/rule";
    }

    /**
     * 查询【居家规则】列表
     */
    @RequiresPermissions("shareproject:rule:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLjRule tLjRule)
    {
        startPage();
        List<TLjRule> list = tLjRuleService.selectTLjRuleList(tLjRule);
        return getDataTable(list);
    }

    @Log(title = "【规则时间段是否可用】", businessType = BusinessType.EXPORT)
    @PostMapping("/guizeuse")
    @ResponseBody
    public AjaxResult guizeuse(TLjRule tLjRule)
    {
        return  toAjax(tLjRuleService.guizeuse(tLjRule));
    }

    /**
     * 导出【居家规则】列表
     */
    @RequiresPermissions("shareproject:rule:export")
    @Log(title = "【居家规则】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLjRule tLjRule)
    {
        List<TLjRule> list = tLjRuleService.selectTLjRuleList(tLjRule);
        ExcelUtil<TLjRule> util = new ExcelUtil<TLjRule>(TLjRule.class);
        return util.exportExcel(list, "rule");
    }

    /**
     * 新增【居家规则】
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        mmap.put("datamin",DateUtils.getDate());
        return prefix + "/add";
    }

    /**
     * 新增保存【居家规则】
     */
    @RequiresPermissions("shareproject:rule:add")
    @Log(title = "【居家规则】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLjRule tLjRule)
    {
        tLjRule.setCreateBy(ShiroUtils.getLoginName());
        tLjRule.setCreateTime(DateUtils.getNowDate());
        tLjRule.setUpdateBy(ShiroUtils.getLoginName());
        tLjRule.setUpdateTime(DateUtils.getNowDate());
        tLjRule.setOrgId(ShiroUtils.getSysUser().getOrgId());
        tLjRule.setOrgName(ShiroUtils.getSysUser().getOrgName());
        return toAjax(tLjRuleService.insertTLjRule(tLjRule));
    }

    /**
     * 修改【居家规则】
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TLjRule tLjRule = tLjRuleService.selectTLjRuleById(id);
        mmap.put("tLjRule", tLjRule);
        return prefix + "/edit";
    }

    /**
     * 修改保存【居家规则】
     */
    @RequiresPermissions("shareproject:rule:edit")
    @Log(title = "【居家规则】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLjRule tLjRule)
    {
        tLjRule.setUpdateBy(ShiroUtils.getLoginName());
        tLjRule.setUpdateTime(DateUtils.getNowDate());
        return toAjax(tLjRuleService.updateTLjRule(tLjRule));
    }

    /**
     * 删除【居家规则】
     */
    @RequiresPermissions("shareproject:rule:remove")
    @Log(title = "【居家规则】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tLjRuleService.deleteTLjRuleByIds(ids));
    }
}
