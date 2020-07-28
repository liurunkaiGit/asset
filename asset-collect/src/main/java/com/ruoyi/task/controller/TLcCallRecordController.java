package com.ruoyi.task.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.robot.domain.CallContent;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.task.domain.TLcCallRecord;
import com.ruoyi.task.domain.TLcCallRecordForXY;
import com.ruoyi.task.service.ITLcCallRecordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 通话结果记录Controller
 *
 * @author liurunkai
 * @date 2019-12-31
 */
@Controller
@RequestMapping("/call/record")
public class TLcCallRecordController extends BaseController {
    private String prefix = "task/callRecord";
    private String monitorPrefix = "monitor/callRecord";

    @Autowired
    private ITLcCallRecordService tLcCallRecordService;

    @Autowired
    private ISysConfigService sysConfigService;

    @RequiresPermissions("call:record:view")
    @GetMapping()
    public String record() {
        return prefix + "/record";
    }


    @RequiresPermissions("call:record:view")
    @GetMapping("/listenRecord")
    public String listenRecord() {
        return monitorPrefix + "/listenRecord";
    }

    /**
     * 查询通话结果记录列表
     */
    @RequiresPermissions("call:record:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcCallRecord tLcCallRecord) {
        startPage();
        List<TLcCallRecord> list = tLcCallRecordService.selectTLcCallRecordList(tLcCallRecord);
        return getDataTable(list);
    }

    /**
     * 导出通话结果记录列表
     */
    @RequiresPermissions("call:record:export")
    @Log(title = "通话结果记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcCallRecord tLcCallRecord) {
        String configValue = sysConfigService.selectConfigByKey("orgId");
        String orgId = tLcCallRecord.getOrgId();
        if(configValue.equals(orgId)){//兴业导出
            List<TLcCallRecordForXY> list = tLcCallRecordService.selectTLcCallRecordListForXY(tLcCallRecord);
            ExcelUtil<TLcCallRecordForXY> util = new ExcelUtil<TLcCallRecordForXY>(TLcCallRecordForXY.class);
            return util.exportExcel(list, "record");
        }else {
            List<TLcCallRecord> list = tLcCallRecordService.selectTLcCallRecordList(tLcCallRecord);
            ExcelUtil<TLcCallRecord> util = new ExcelUtil<TLcCallRecord>(TLcCallRecord.class);
            return util.exportExcel(list, "record");
        }
    }

    /**
     * 新增通话结果记录
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存通话结果记录
     */
    @RequiresPermissions("call:record:add")
    @Log(title = "通话结果记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLcCallRecord tLcCallRecord) {
        tLcCallRecordService.insertTLcCallRecord(tLcCallRecord);
        return AjaxResult.success();
    }

    /**
     * 修改通话结果记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        TLcCallRecord tLcCallRecord = tLcCallRecordService.selectTLcCallRecordById(id);
        mmap.put("tLcCallRecord", tLcCallRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存通话结果记录
     */
    @RequiresPermissions("call:record:edit")
    @Log(title = "通话结果记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLcCallRecord tLcCallRecord) {
        return toAjax(tLcCallRecordService.updateTLcCallRecord(tLcCallRecord));
    }

    /**
     * 删除通话结果记录
     */
    @RequiresPermissions("call:record:remove")
    @Log(title = "通话结果记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tLcCallRecordService.deleteTLcCallRecordByIds(ids));
    }

    /**
     * 跳转到查看通话内容页面，从点催记录列表里面获取
     *
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/viewCallContentFromCallRecord")
    public TableDataInfo viewCallContentFromCallRecord(String id) {
        TableDataInfo rspData = new TableDataInfo();
        List<CallContent> callContentList = this.tLcCallRecordService.viewCallContent(id);
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (null == pageDomain.getPageNum() || null == pageDomain.getPageSize()) {
            rspData.setRows(callContentList);
            rspData.setTotal(callContentList.size());
            return rspData;
        }
        Integer pageNum = (pageDomain.getPageNum() - 1) * 10;
        Integer pageSize = pageDomain.getPageNum() * 10;
        if (pageSize > callContentList.size()) {
            pageSize = callContentList.size();
        }
        rspData.setRows(callContentList.subList(pageNum, pageSize));
        rspData.setTotal(callContentList.size());
        return rspData;
    }


    @PostMapping("/findListenCallRecord")
    @ResponseBody
    public TableDataInfo findListenCallRecord(TLcCallRecord tLcCallRecord) {
        startPage();
        Long orgId = ShiroUtils.getSysUser().getOrgId();
        tLcCallRecord.setOrgId(String.valueOf(orgId));
        List<TLcCallRecord> list = tLcCallRecordService.findListenCallRecord(tLcCallRecord);
        return getDataTable(list);
    }

    /**
     * 跳转收听页面
     */
    @GetMapping("/recordAudio")
    public String recordAudio(String id, ModelMap mmap) {
        TLcCallRecord tLcCallRecord = tLcCallRecordService.selectTLcCallRecordById(Long.valueOf(id));
        String callRadioLocation = tLcCallRecord.getCallRadioLocation();
        tLcCallRecordService.updateStar(tLcCallRecord);
        mmap.put("callRadioLocation", callRadioLocation);
        return monitorPrefix + "/recordAudio";
    }

    @GetMapping("/downRecord")
    @ResponseBody
    public void downRecord(HttpServletRequest request, HttpServletResponse response, String ids) {
        tLcCallRecordService.downRecord(request,response,ids);
    }

}
