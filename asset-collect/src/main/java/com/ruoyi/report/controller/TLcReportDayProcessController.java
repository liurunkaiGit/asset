package com.ruoyi.report.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.report.domain.TLcReportDayProcess;
import com.ruoyi.report.service.ITLcReportDayProcessService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * 每日过程指标报Controller
 *
 * @author liurunkai
 * @date 2020-04-09
 */
@Controller
@RequestMapping("/report/process")
public class TLcReportDayProcessController extends BaseController {
    private String prefix = "report/process";

    private static final DecimalFormat df = new DecimalFormat("0.0%");

    @Autowired
    private ITLcReportDayProcessService tLcReportDayProcessService;

    @RequiresPermissions("report:process:view")
    @GetMapping()
    public String process(ModelMap modelMap) {
        String curDate = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, new Date());
        modelMap.put("reportDate", DateUtils.parseDate(curDate));
        modelMap.put("updateTime", DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, new Date()));
        modelMap.put("reportName", "每日过程指标");
        return prefix + "/process";
    }

    /**
     * 查询每日过程指标报列表
     */
    @RequiresPermissions("report:process:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcReportDayProcess tLcReportDayProcess) {
        List<TLcReportDayProcess> list = this.tLcReportDayProcessService.selectTLcReportDayProcessList(tLcReportDayProcess);
        return getDataTable(list);
    }

    /**
     * 查询每日过程指标报列表
     */
    @PostMapping("/share/list")
    @ResponseBody
    public TableDataInfo shareList(@Validated @RequestBody TLcReportDayProcess tLcReportDayProcess) {
        List<TLcReportDayProcess> list = this.tLcReportDayProcessService.selectTLcReportDayProcessList(tLcReportDayProcess);
        return getDataTable(list);
    }

    /**
     * 导出每日过程指标报列表
     */
    @RequiresPermissions("report:process:export")
    @Log(title = "每日过程指标报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcReportDayProcess tLcReportDayProcess) {
        List<TLcReportDayProcess> list = this.tLcReportDayProcessService.selectTLcReportDayProcessList(tLcReportDayProcess);
        list.stream().forEach(process -> {
            if (StringUtils.isNoneBlank(process.getCallConnectedRecovery())) {
                process.setCallConnectedRecovery(df.format(new BigDecimal(process.getCallConnectedRecovery())));
            }
            if (StringUtils.isNoneBlank(process.getCallActionCodeRecovery())) {
                BigDecimal bigDecimal = new BigDecimal(process.getCallActionCodeRecovery());
                process.setCallActionCodeRecovery(String.valueOf(bigDecimal.setScale(2, RoundingMode.HALF_UP)));
            }
            if (StringUtils.isNoneBlank(process.getAverageCallCode())) {
                BigDecimal bigDecimal = new BigDecimal(process.getAverageCallCode());
                process.setAverageCallCode(String.valueOf(bigDecimal.setScale(2, RoundingMode.HALF_UP)));
            }
            if (StringUtils.isNoneBlank(process.getAverageActionCode())) {
                BigDecimal bigDecimal = new BigDecimal(process.getAverageActionCode());
                process.setAverageActionCode(String.valueOf(bigDecimal.setScale(2, RoundingMode.HALF_UP)));
            }
            if (StringUtils.isNoneBlank(process.getAverageEffectiveCallCodeNum())) {
                BigDecimal bigDecimal = new BigDecimal(process.getAverageEffectiveCallCodeNum());
                process.setAverageEffectiveCallCodeNum(String.valueOf(bigDecimal.setScale(2, RoundingMode.HALF_UP)));
            }
            if (StringUtils.isNoneBlank(process.getCallLen())) {
                double callLen = Math.round(Double.valueOf(process.getCallLen()) * 100 ) * 0.01d;
                process.setCallLen(String.valueOf(callLen));
            }
        });
        ExcelUtil<TLcReportDayProcess> util = new ExcelUtil<TLcReportDayProcess>(TLcReportDayProcess.class);
        return util.exportExcel(list, "每日过程指标");
    }
}
