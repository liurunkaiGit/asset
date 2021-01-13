package com.ruoyi.task.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.robot.domain.CallContent;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.task.domain.TLcCallRecord;
import com.ruoyi.task.domain.TLcCallRecordForDQ;
import com.ruoyi.task.domain.TLcCallRecordForJX;
import com.ruoyi.task.domain.TLcCallRecordForXY;
import com.ruoyi.task.service.ITLcCallRecordService;
import com.ruoyi.utils.CSVUtils;
import com.ruoyi.utils.DesensitizationUtil;
import com.ruoyi.utils.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    private DesensitizationUtil desensitizationUtil;

    /**
     * 查看录音
     * @return
     */
    @PostMapping("/selectCallRadioByOrgId")
    @ResponseBody
    public Response selectCallRadioByOrgId(@RequestBody TLcCallRecord callRecord) {
        List<TLcCallRecord> callRecordList = this.tLcCallRecordService.selectCallRecordListByOrgIdAndTime(callRecord);
        return Response.success(callRecordList);
    }

    @RequiresPermissions("call:record:view")
    @GetMapping()
    public String record(ModelMap mmap) {
        String curDate = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, new Date());
        mmap.put("curDate", DateUtils.parseDate(curDate));
        return prefix + "/record";
    }

    @RequiresPermissions("call:record:view")
    @GetMapping("/recordForXY")
    public String recordForXY(ModelMap mmap) {
        String curDate = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, new Date());
        mmap.put("curDate", DateUtils.parseDate(curDate));
        return prefix + "/recordForXY";
    }


    @RequiresPermissions("call:record:view")
    @GetMapping("/listenRecord")
    public String listenRecord(ModelMap modelMap) {
        //查询是否脱敏
        boolean desensitization = desensitizationUtil.isDesensitization(String.valueOf(ShiroUtils.getSysUser().getOrgId()), ShiroUtils.getLoginName());
        modelMap.put("desensitization", desensitization);
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
     * 查询兴业通话结果记录列表
     */
    @RequiresPermissions("call:record:List")
    @PostMapping("/XYList")
    @ResponseBody
    public TableDataInfo getXYList(TLcCallRecord tLcCallRecord) {
        String configValue = sysConfigService.selectConfigByKey("orgId");
        tLcCallRecord.setOrgId(configValue);
        startPage();
        List<TLcCallRecord> list = tLcCallRecordService.selectTLcCallRecordXYList(tLcCallRecord);
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
        String jxOrgId = sysConfigService.selectConfigByKey("jxOrgId");
        String dqOrgId = sysConfigService.selectConfigByKey("dqConfigOrgId");
        String orgId = tLcCallRecord.getOrgId();
        String caseNo = tLcCallRecord.getCaseNo();
        if(StringUtils.isNotBlank(caseNo)){
            tLcCallRecord.setCaseNoList(Arrays.asList(caseNo.split(",")));
        }
        if(configValue.equals(orgId)){//兴业导出
            List<TLcCallRecordForXY> list = tLcCallRecordService.selectTLcCallRecordListForXY(tLcCallRecord);
            ExcelUtil<TLcCallRecordForXY> util = new ExcelUtil<TLcCallRecordForXY>(TLcCallRecordForXY.class);
            return util.exportExcel(list, "record", System.currentTimeMillis() + "record");
        }else if(jxOrgId.equals(orgId)){//捷信导出
            List<TLcCallRecordForJX> list = tLcCallRecordService.selectTLcCallRecordListForJX(tLcCallRecord);
            ExcelUtil<TLcCallRecordForJX> util = new ExcelUtil<>(TLcCallRecordForJX.class);
            return util.exportExcel(list, "record", "捷信消金华道催记" + DateUtils.parseDateToStr(DateUtils.YYYYMMDDHHMMSS, new Date()));
        } else if (dqOrgId.equals(orgId)) {
            List<TLcCallRecordForDQ> list = tLcCallRecordService.selectTLcCallRecordListForDQ(tLcCallRecord);
            ExcelUtil<TLcCallRecordForDQ> util = new ExcelUtil<>(TLcCallRecordForDQ.class);
            return util.exportExcel(list, "record", "东乔催记");
        }else {
            List<TLcCallRecord> list = tLcCallRecordService.selectTLcCallRecordList(tLcCallRecord);
            ExcelUtil<TLcCallRecord> util = new ExcelUtil<TLcCallRecord>(TLcCallRecord.class);
            return util.exportExcel(list, "record",System.currentTimeMillis() + "record");
        }
    }


    /**
     * 兴业导出通话结果记录列表
     */
    @RequiresPermissions("call:record:export")
    @Log(title = "兴业通话记录", businessType = BusinessType.EXPORT)
    @GetMapping("/XYExport")
    @ResponseBody
    public void getXYExport(HttpServletRequest request, HttpServletResponse response,TLcCallRecord tLcCallRecord) {
        String xyOutPath = sysConfigService.selectConfigByKey("xyOutPath");
        String configValue = sysConfigService.selectConfigByKey("orgId");
        tLcCallRecord.setOrgId(configValue);
        List<Map<String,Object>> list = tLcCallRecordService.selectTLcCallRecordListForXY2(tLcCallRecord);
        LinkedHashMap map = new LinkedHashMap();
        map.put("序号","序号");
        map.put("贷款合同号","贷款合同号");
        map.put("业务部门","业务部门");
        map.put("外包经办","外包经办");
        map.put("客户姓名","客户姓名");
        map.put("产品名称","产品名称");
        map.put("催收动作","催收动作");
        map.put("催收时间","催收时间");
        map.put("催收结果","催收结果");
        map.put("联络人","联络人");
        map.put("联络方式","联络方式");
        map.put("催收详细情况","催收详细情况");
        map.put("委案时间","委案时间");
        map.put("到期时间","到期时间");
        CSVUtils.createAndDownLoadCSVFile(request ,response ,list ,
                map ,xyOutPath ,System.currentTimeMillis() + "兴业催记.csv");

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
