package com.ruoyi.radioQc.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.radioQc.domain.TLcSendRadioQcRecord;
import com.ruoyi.radioQc.service.ITLcSendRadioQcRecordService;
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
 * @date 2020-03-10
 */
@Controller
@RequestMapping("/ruoyi/record")
public class TLcSendRadioQcRecordController extends BaseController {
    private String prefix = "ruoyi/record";

    @Autowired
    private ITLcSendRadioQcRecordService tLcSendRadioQcRecordService;

    @RequiresPermissions("ruoyi:record:view")
    @GetMapping()
    public String record() {
        return prefix + "/record";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("ruoyi:record:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcSendRadioQcRecord tLcSendRadioQcRecord) {
        startPage();
        List<TLcSendRadioQcRecord> list = tLcSendRadioQcRecordService.selectTLcSendRadioQcRecordList(tLcSendRadioQcRecord);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("ruoyi:record:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcSendRadioQcRecord tLcSendRadioQcRecord) {
        List<TLcSendRadioQcRecord> list = tLcSendRadioQcRecordService.selectTLcSendRadioQcRecordList(tLcSendRadioQcRecord);
        ExcelUtil<TLcSendRadioQcRecord> util = new ExcelUtil<TLcSendRadioQcRecord>(TLcSendRadioQcRecord.class);
        return util.exportExcel(list, "record");
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
    @RequiresPermissions("ruoyi:record:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLcSendRadioQcRecord tLcSendRadioQcRecord) {
        return toAjax(tLcSendRadioQcRecordService.insertTLcSendRadioQcRecord(tLcSendRadioQcRecord));
    }

    /**
     * 修改【请填写功能名称】
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        TLcSendRadioQcRecord tLcSendRadioQcRecord = tLcSendRadioQcRecordService.selectTLcSendRadioQcRecordById(id);
        mmap.put("tLcSendRadioQcRecord", tLcSendRadioQcRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("ruoyi:record:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLcSendRadioQcRecord tLcSendRadioQcRecord) {
        return toAjax(tLcSendRadioQcRecordService.updateTLcSendRadioQcRecord(tLcSendRadioQcRecord));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("ruoyi:record:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tLcSendRadioQcRecordService.deleteTLcSendRadioQcRecordByIds(ids));
    }
}
