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
import com.ruoyi.task.domain.*;
import com.ruoyi.task.service.ITLcCallRecordHisService;
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
import java.util.*;

/**
 * 历史通话结果记录Controller
 *
 * @author gaohg
 * @date 2021-3-10
 */
@Controller
@RequestMapping("/call/recordhis")
public class TLcCallRecordHisController extends BaseController {
    private String prefix = "task/callRecordHis";
    private String monitorPrefix = "monitor/callRecordHis";

    @Autowired
    private ITLcCallRecordHisService tTLcCallRecordHisService;

    @Autowired
    private ISysConfigService sysConfigService;

    @RequiresPermissions("call:record:view")
    @GetMapping()
    public String record(ModelMap mmap) {
        String curDate = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, new Date());
        mmap.put("curDate", DateUtils.parseDate(curDate));
        return prefix + "/recordHis";
    }



    /**
     * 查询通话结果记录列表
     */
    @RequiresPermissions("call:record:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcCallRecordHis tLcCallRecordHis) {
        startPage();
        List<TLcCallRecordHis> list = tTLcCallRecordHisService.selectTLcCallRecordHisList(tLcCallRecordHis);
        return getDataTable(list);
    }


    /**
     * 导出通话结果记录列表
     */
    @RequiresPermissions("call:recordhis:export")
    @Log(title = "通话结果记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcCallRecordHis tLcCallRecord) {
        String configValue = sysConfigService.selectConfigByKey("orgId");
        String jxOrgId = sysConfigService.selectConfigByKey("jxOrgId");
        String dqOrgId = sysConfigService.selectConfigByKey("dqConfigOrgId");
        String orgId = tLcCallRecord.getOrgId();
        String caseNo = tLcCallRecord.getCaseNo();
        if(StringUtils.isNotBlank(caseNo)){
            tLcCallRecord.setCaseNoList(Arrays.asList(caseNo.split(",")));
        }
        if(configValue.equals(orgId)){//兴业导出
            List<TLcCallRecordForXY> list = tTLcCallRecordHisService.selectTLcCallRecordHisListForXY(tLcCallRecord);
            ExcelUtil<TLcCallRecordForXY> util = new ExcelUtil<TLcCallRecordForXY>(TLcCallRecordForXY.class);
            return util.exportExcel(list, "record", System.currentTimeMillis() + "record");
        }else if(jxOrgId.equals(orgId)){//捷信导出
            List<TLcCallRecordForJX> list = tTLcCallRecordHisService.selectTLcCallRecordListForJX(tLcCallRecord);
            ExcelUtil<TLcCallRecordForJX> util = new ExcelUtil<>(TLcCallRecordForJX.class);
            return util.exportExcel(list, "record", "捷信消金华道催记" + DateUtils.parseDateToStr(DateUtils.YYYYMMDDHHMMSS, new Date()));
        } else if (dqOrgId.equals(orgId)) {
            List<TLcCallRecordForDQ> list = tTLcCallRecordHisService.selectTLcCallRecordListForDQ(tLcCallRecord);
            ExcelUtil<TLcCallRecordForDQ> util = new ExcelUtil<>(TLcCallRecordForDQ.class);
            return util.exportExcel(list, "record", "东乔催记");
        }else {
            List<TLcCallRecordHis> list = tTLcCallRecordHisService.selectTLcCallRecordHisList(tLcCallRecord);
            ExcelUtil<TLcCallRecordHis> util = new ExcelUtil<TLcCallRecordHis>(TLcCallRecordHis.class);
            return util.exportExcel(list, "record",System.currentTimeMillis() + "record");
        }
    }
}