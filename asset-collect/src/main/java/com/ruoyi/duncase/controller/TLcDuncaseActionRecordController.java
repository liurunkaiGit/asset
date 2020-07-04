package com.ruoyi.duncase.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.duncase.domain.TLcDuncaseActionRecord;
import com.ruoyi.duncase.service.ITLcDuncaseActionRecordService;
import com.ruoyi.utils.Response;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 案件行动码记录Controller
 *
 * @author ruoyi
 * @date 2020-01-04
 */
@Controller
@RequestMapping("/collect/duncase/action")
public class TLcDuncaseActionRecordController extends BaseController {
    private String prefix = "system/record";

    @Autowired
    private ITLcDuncaseActionRecordService tLcDuncaseActionRecordService;

    @RequiresPermissions("system:record:view")
    @GetMapping()
    public String record() {
        return prefix + "/record";
    }

    /**
     * 查询案件行动码记录列表
     */
    @RequiresPermissions("system:record:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcDuncaseActionRecord tLcDuncaseActionRecord) {
        startPage();
        List<TLcDuncaseActionRecord> list = tLcDuncaseActionRecordService.selectTLcDuncaseActionRecordList(tLcDuncaseActionRecord);
        return getDataTable(list);
    }

    /**
     * 导出案件行动码记录列表
     */
    @RequiresPermissions("system:record:export")
    @Log(title = "案件行动码记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcDuncaseActionRecord tLcDuncaseActionRecord) {
        List<TLcDuncaseActionRecord> list = tLcDuncaseActionRecordService.selectTLcDuncaseActionRecordList(tLcDuncaseActionRecord);
        ExcelUtil<TLcDuncaseActionRecord> util = new ExcelUtil<TLcDuncaseActionRecord>(TLcDuncaseActionRecord.class);
        return util.exportExcel(list, "record");
    }

    /**
     * 新增案件行动码记录
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

//    /**
//     * 新增保存案件行动码记录
//     */
//    @RequiresPermissions("system:record:add")
//    @Log(title = "案件行动码记录", businessType = BusinessType.INSERT)
//    @PostMapping("/add")
//    @ResponseBody
//    public AjaxResult addSave(TLcDuncaseActionRecord tLcDuncaseActionRecord) {
//        return toAjax(tLcDuncaseActionRecordService.insertTLcDuncaseActionRecord(tLcDuncaseActionRecord));
//    }

    /**
     * 新增保存案件行动码记录
     */
    @PostMapping("/addActionCode")
    @ResponseBody
    public Response addActionCode(TLcDuncaseActionRecord tLcDuncaseActionRecord, String certificateNo, String orgId, String importBatchNo) {
        return tLcDuncaseActionRecordService.insertTLcDuncaseActionRecord(tLcDuncaseActionRecord,orgId,importBatchNo) > 0 ? Response.success(certificateNo) : Response.failure("新增失败", null);
    }

    /**
     * 新增保存案件行动码记录
     */
    @GetMapping("/batchAddActionCode")
    @ResponseBody
    public AjaxResult batchAddActionCode(String taskIds, String actionCode) {
        return tLcDuncaseActionRecordService.batchAddActionCode(taskIds, actionCode);
    }

    /**
     * 修改案件行动码记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        TLcDuncaseActionRecord tLcDuncaseActionRecord = tLcDuncaseActionRecordService.selectTLcDuncaseActionRecordById(id);
        mmap.put("tLcDuncaseActionRecord", tLcDuncaseActionRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存案件行动码记录
     */
    @RequiresPermissions("system:record:edit")
    @Log(title = "案件行动码记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLcDuncaseActionRecord tLcDuncaseActionRecord) {
        return toAjax(tLcDuncaseActionRecordService.updateTLcDuncaseActionRecord(tLcDuncaseActionRecord));
    }

    /**
     * 删除案件行动码记录
     */
    @RequiresPermissions("system:record:remove")
    @Log(title = "案件行动码记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tLcDuncaseActionRecordService.deleteTLcDuncaseActionRecordByIds(ids));
    }
}
