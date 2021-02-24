package com.ruoyi.task.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.task.domain.TLcInfoup;
import com.ruoyi.task.service.ITLcInfoupService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 【联系人增加】Controller
 * 
 * @author gaohg
 * @date 2021-02-05
 */
@Controller
@RequestMapping("/task/infouplog")
public class TLcInfouplogController extends BaseController
{
    private String prefix = "task/infouplog";

    @Autowired
    private ITLcInfoupService tLcInfoupService;

    @GetMapping()
    public String infoup()
    {
        return prefix + "/infoup";
    }

    /**
     * 查询【联系人增加】列表
     */
    @RequiresPermissions("infouplog:infoup:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcInfoup tLcInfoup)
    {
        startPage();
        List<TLcInfoup> list = tLcInfoupService.selectTLcInfoupList(tLcInfoup);
        return getDataTable(list);
    }

    @PostMapping("/listPid")
    @ResponseBody
    public List<TLcInfoup> listPid(@RequestBody TLcInfoup tLcInfoup)
    {
        List<TLcInfoup> list = tLcInfoupService.selectTLcInfoupList(tLcInfoup);
        return list;
    }

    /**
     * 新增【联系人增加】
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") Long id,ModelMap mmap)
    {
        mmap.put("id",id);
        return prefix + "/add";
    }

    /**
     * 新增保存【联系人增加】
     */
    @RequiresPermissions("infouplog:infoup:add")
    @Log(title = "【联系人增加】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLcInfoup tLcInfoup)
    {
        return toAjax(tLcInfoupService.insertTLcInfoup(tLcInfoup));
    }

    /**
     * 修改【联系人增加】
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TLcInfoup tLcInfoup = tLcInfoupService.selectTLcInfoupById(id);
        mmap.put("tLcInfoup", tLcInfoup);
        return prefix + "/edit";
    }

    /**
     * 修改保存【联系人增加】
     */
    @RequiresPermissions("infouplog:infoup:edit")
    @Log(title = "【联系人增加】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLcInfoup tLcInfoup)
    {
        return toAjax(tLcInfoupService.updateTLcInfoup(tLcInfoup));
    }
}
