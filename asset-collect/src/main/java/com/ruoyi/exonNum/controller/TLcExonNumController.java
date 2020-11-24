package com.ruoyi.exonNum.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.exonNum.domain.TLcExonNum;
import com.ruoyi.exonNum.service.ITLcExonNumService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 【请填写功能名称】Controller
 *
 * @author liurunkai
 * @date 2020-04-21
 */
@Controller
@RequestMapping("/collect/exonNum")
public class TLcExonNumController extends BaseController {
    private String prefix = "exonNum";

    @Autowired
    private ITLcExonNumService tLcExonNumService;

    @RequiresPermissions("ruoyi:num:view")
    @GetMapping()
    public String num() {
        return prefix + "/num";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("ruoyi:num:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcExonNum tLcExonNum) {
        startPage();
        List<TLcExonNum> list = tLcExonNumService.selectTLcExonNumList(tLcExonNum);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("ruoyi:num:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcExonNum tLcExonNum) {
        List<TLcExonNum> list = tLcExonNumService.selectTLcExonNumList(tLcExonNum);
        ExcelUtil<TLcExonNum> util = new ExcelUtil<TLcExonNum>(TLcExonNum.class);
        return util.exportExcel(list, "num");
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
    @RequiresPermissions("ruoyi:num:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLcExonNum tLcExonNum) {
        return toAjax(tLcExonNumService.insertTLcExonNum(tLcExonNum));
    }

    /**
     * 修改【请填写功能名称】
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        TLcExonNum tLcExonNum = tLcExonNumService.selectTLcExonNumById(id);
        mmap.put("tLcExonNum", tLcExonNum);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("ruoyi:num:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLcExonNum tLcExonNum) {
        return toAjax(tLcExonNumService.updateTLcExonNum(tLcExonNum));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("ruoyi:num:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tLcExonNumService.deleteTLcExonNumByIds(ids));
    }

    @PostMapping(value = "/initExon")
    @ResponseBody
    public TableDataInfo initExon(TLcExonNum tLcExonNum) {
        List<TLcExonNum> exonNumList = this.tLcExonNumService.selectTLcExonNumList(tLcExonNum);
        return getDataTable(exonNumList);
    }

}
