package com.ruoyi.inforeporting.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.inforeporting.domain.TLcInforeportingSet;
import com.ruoyi.inforeporting.service.TLcInforeportingSetService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * 任务Controller
 *
 * @author gaohg
 * @date 2020-08-13
 */
@Slf4j
@Controller
@RequestMapping("/inforeporting/set")
public class TLcInforeportingSetController extends BaseController {
    private String prefix = "infoReporting/set";
    @Autowired
    private TLcInforeportingSetService inforeportingSetService;
    /**
     * 信息上报设置
     */
    @GetMapping("/list")
    public String list() {
        return prefix + "/list";
    }

    @PostMapping("/list")
    @RequiresPermissions("inforeporting:set:list")
    @ResponseBody
    public TableDataInfo list(TLcInforeportingSet inforeportingSet)
    {
        startPage();
        List<TLcInforeportingSet> list = inforeportingSetService.selectTLcInforeportingSetList(inforeportingSet);
        return getDataTable(list);
    }

    /**
     * 信息上报设置对应机构查询
     */
    @PostMapping("/listOrg")
    @RequiresPermissions("inforeporting:set:listOrg")
    @ResponseBody
    public List<TLcInforeportingSet> listOrg(TLcInforeportingSet inforeportingSet)
    {
        List<TLcInforeportingSet> list = inforeportingSetService.selectTLcInforeportingSetByOrgIdAndTypeList(inforeportingSet);
        return list;
    }
    /**
     * 新增【请填写功能名称】
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存【信息上报设置】
     */
    @Log(title = "【上报信息】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated TLcInforeportingSet inforeportingSet) {
        try
        {
            inforeportingSet.setCreateBy(ShiroUtils.getLoginName());
            inforeportingSet.setUpdateBy(ShiroUtils.getLoginName());
            return toAjax(inforeportingSetService.insertTLcInforeportingSet(inforeportingSet));
        }
        catch (Exception e)
        {
            return error("数据重复,请重试");
        }

    }

    /**
     * 编辑【信息上报设置】
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long dictId, ModelMap mmap)
    {
        mmap.put("inforeportingSet", inforeportingSetService.selectTLcInforeportingSetById(dictId));
        return prefix + "/edit";
    }

    /**
     * 编辑保存【信息上报设置】
     */
    @RequiresPermissions("inforeporting:set:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated TLcInforeportingSet inforeportingSet)
    {
        inforeportingSet.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(inforeportingSetService.updateTLcInforeportingSet(inforeportingSet));
    }

    /**
     * 删除【信息上报设置】
     */
    @RequiresPermissions("inforeporting:set:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        try
        {
            return toAjax(inforeportingSetService.deleteTLcInforeportingSetByIds(ids));
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }
}
