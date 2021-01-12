package com.ruoyi.report.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.enums.IsNoEnum;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.report.domain.TLcReportDayProcess;
import com.ruoyi.report.domain.TLcReportMonthProcess;
import com.ruoyi.report.service.ITLcReportDayProcessService;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
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
import java.util.stream.Collectors;

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
    @Autowired
    private ISysUserService sysUserService;

    @RequiresPermissions("report:process:view")
    @GetMapping()
    public String process(ModelMap modelMap) {
        String curDate = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, new Date());
        modelMap.put("reportDate", DateUtils.parseDate(curDate));
        modelMap.put("updateTime", DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, new Date()));
        modelMap.put("reportName", "每日过程指标");
        // 是否组内查询：否
        modelMap.put("isGroup", IsNoEnum.NO.getCode());
        return prefix + "/process";
    }

    @RequiresPermissions("report:process:group:view")
    @GetMapping(value = "/group/view")
    public String groupProcess(ModelMap modelMap) {
        String curDate = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, new Date());
        modelMap.put("reportDate", DateUtils.parseDate(curDate));
        modelMap.put("updateTime", DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, new Date()));
        modelMap.put("reportName", "组内每日过程指标");
        // 是否组内查询：是
        modelMap.put("isGroup", IsNoEnum.IS.getCode());
        return prefix + "/process";
    }

    @RequiresPermissions("report:process:month:view")
    @GetMapping(value = "/month/view")
    public String monthProcess(ModelMap modelMap) {
        String curDate = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, new Date());
        modelMap.put("reportDate", DateUtils.parseDate(curDate));
        modelMap.put("updateTime", DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, new Date()));
        modelMap.put("reportName", "组内每日过程指标");
        // 是否组内查询：是
        modelMap.put("isGroup", IsNoEnum.IS.getCode());
        return prefix + "/monthProcess";
    }

    /**
     * 查询每日过程指标报列表
     */
    @RequiresPermissions("report:process:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcReportDayProcess tLcReportDayProcess) {
        startPage();
        List<TLcReportDayProcess> list = this.tLcReportDayProcessService.selectTLcReportDayProcessList(tLcReportDayProcess);
        return getDataTable(list);
    }

    /**
     * 查询月度过程指标列表
     */
    @RequiresPermissions("report:process:month:list")
    @PostMapping("/monthList")
    @ResponseBody
    public TableDataInfo monthList(TLcReportDayProcess tLcReportDayProcess) {
        startPage();
        List<TLcReportDayProcess> list = this.tLcReportDayProcessService.selectTLcReportMonthProcessList(tLcReportDayProcess);
        return getDataTable(list);
    }

    /**
     * 查询每日过程指标报列表
     */
    @PostMapping("/share/list")
    @ResponseBody
    public List<TLcReportDayProcess> shareList(@Validated @RequestBody TLcReportDayProcess tLcReportDayProcess) {
        List<TLcReportDayProcess> list = this.tLcReportDayProcessService.selectTLcReportDayProcessList(tLcReportDayProcess);
        return list;
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
        ExcelUtil<TLcReportDayProcess> util = new ExcelUtil<TLcReportDayProcess>(TLcReportDayProcess.class);
        return util.exportExcel(list, "每日过程指标");
    }

    /**
     * 导出月度过程指标列表
     */
    @RequiresPermissions("report:process:month:export")
    @Log(title = "月度过程指标", businessType = BusinessType.EXPORT)
    @PostMapping("/monthExport")
    @ResponseBody
    public AjaxResult monthExport(TLcReportDayProcess tLcReportDayProcess) {
        List<TLcReportDayProcess> list = this.tLcReportDayProcessService.selectTLcReportMonthProcessList(tLcReportDayProcess);
        final List<TLcReportMonthProcess> collect = list.stream().map(dayProcess -> {
            TLcReportMonthProcess tLcReportMonthProcess = new TLcReportMonthProcess();
            BeanUtils.copyProperties(dayProcess, tLcReportMonthProcess);
            return tLcReportMonthProcess;
        }).collect(Collectors.toList());
        ExcelUtil<TLcReportMonthProcess> util = new ExcelUtil<>(TLcReportMonthProcess.class);
        return util.exportExcel(collect, "月度过程指标");
    }
}
