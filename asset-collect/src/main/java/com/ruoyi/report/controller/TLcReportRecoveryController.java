package com.ruoyi.report.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.report.domain.TLcReportRecovery;
import com.ruoyi.report.domain.TLcReportZyRecovery;
import com.ruoyi.report.service.ITLcReportRecoveryService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 回收率报Controller
 *
 * @author liurunkai
 * @date 2020-04-09
 */
@Controller
@RequestMapping("/report/recovery")
public class TLcReportRecoveryController extends BaseController {
    private String prefix = "report/recovery";

    private static final DecimalFormat df = new DecimalFormat("0.0%");

    @Autowired
    private ITLcReportRecoveryService tLcReportRecoveryService;

    @RequiresPermissions("report:recovery:view")
    @GetMapping()
    public String recovery(ModelMap modelMap) {
        String curDate = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, new Date());
        modelMap.put("reportDate", DateUtils.parseDate(curDate));
        modelMap.put("updateTime", DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, new Date()));
        modelMap.put("reportName", "回收率报表");
        return prefix + "/recovery";
    }

    @RequiresPermissions("report:recovery:zy:view")
    @GetMapping("/zy/view")
    public String zyRecovery() {
        return prefix + "/zyRecovery";
    }

    /**
     * 查询回收率报列表
     */
    @RequiresPermissions("report:recovery:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcReportRecovery tLcReportRecovery) {
        List<TLcReportRecovery> list = this.tLcReportRecoveryService.selectTLcReportRecoveryList(tLcReportRecovery);
        return getDataTable(list);
    }

    /**
     * 查询回收率报列表
     */
    @RequiresPermissions("report:recovery:zy:list")
    @PostMapping("/zy/list")
    @ResponseBody
    public TableDataInfo zyList(TLcReportZyRecovery zyRecovery) {
        List<TLcReportZyRecovery> list = this.tLcReportRecoveryService.selectTLcReportZyRecoveryList(zyRecovery);
        return getDataTable(list);
    }

    /**
     * 导出回收率报列表
     */
    @RequiresPermissions("report:recovery:export")
    @Log(title = "回收率报", businessType = BusinessType.EXPORT)
    @PostMapping(value = "/export",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public AjaxResult export(TLcReportRecovery tLcReportRecovery, HttpServletResponse response) {
        List<TLcReportRecovery> list = this.tLcReportRecoveryService.selectTLcReportRecoveryList(tLcReportRecovery);
        list.stream().forEach(recovery -> {
            if (StringUtils.isNoneBlank(recovery.getCaseRecovery())) {
                recovery.setCaseRecovery(df.format(new BigDecimal(recovery.getCaseRecovery())));
            }
            if (StringUtils.isNoneBlank(recovery.getMoneyRecovery())) {
                recovery.setMoneyRecovery(df.format(new BigDecimal(recovery.getMoneyRecovery())));
            }
        });
        ExcelUtil<TLcReportRecovery> util = new ExcelUtil<>(TLcReportRecovery.class);
        return util.exportExcel(list, "回收率报表");
    }

    /**
     * 导出回收率报列表
     */
    @RequiresPermissions("report:recovery:zy:export")
    @Log(title = "回收率报", businessType = BusinessType.EXPORT)
    @PostMapping(value = "/zy/export",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public AjaxResult zyExport(TLcReportZyRecovery zyRecovery) {
        List<TLcReportZyRecovery> list = this.tLcReportRecoveryService.selectTLcReportZyRecoveryList(zyRecovery);
        if (list != null && list.size() > 0) {
            list.stream().forEach(recovery -> {
                recovery.setMEa(getMea(recovery.getmEaOdClBa(), recovery.getmEaWoNrPr()));
                recovery.setMEn(getMen(recovery.getmEnOdClBa(),recovery.getmEnWoNrPr()));
                BigDecimal divide = new BigDecimal("0.00");
                if (!new BigDecimal("0.00").equals(recovery.getmEa())) {
                    divide = recovery.getmEn().divide(recovery.getmEa(), 2, BigDecimal.ROUND_HALF_UP);
                }
                BigDecimal subtract = new BigDecimal(1).subtract(divide);
                BigDecimal multiply = subtract.multiply(new BigDecimal(100));
                recovery.setRecovery(multiply.toString() + "%");
            });
        }
        ExcelUtil<TLcReportZyRecovery> util = new ExcelUtil<>(TLcReportZyRecovery.class);
        return util.exportExcel(list, "回收率报表");
    }

    private BigDecimal getMen(BigDecimal getmEnOdClBa, BigDecimal getmEnWoNrPr) {
        if (getmEnOdClBa == null && getmEnWoNrPr == null) {
            return null;
        } else if (getmEnOdClBa == null) {
            return getmEnWoNrPr;
        } else if (getmEnWoNrPr == null) {
            return getmEnOdClBa;
        }
        return getmEnOdClBa.add(getmEnWoNrPr);
    }

    private BigDecimal getMea(BigDecimal mEaOdClBa, BigDecimal getmEaWoNrPr) {
        if (mEaOdClBa == null && getmEaWoNrPr == null) {
            return null;
        } else if (mEaOdClBa == null) {
            return getmEaWoNrPr;
        } else if (getmEaWoNrPr == null) {
            return mEaOdClBa;
        }
        return mEaOdClBa.add(getmEaWoNrPr);
    }

}
