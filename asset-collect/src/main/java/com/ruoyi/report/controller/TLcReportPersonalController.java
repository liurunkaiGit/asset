package com.ruoyi.report.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.report.domain.TLcReportPersonal;
import com.ruoyi.report.service.ITLcReportPersonalService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 通时通次个人明细汇总报Controller
 *
 * @author liurunkai
 * @date 2020-08-05
 */
@Controller
@RequestMapping("/report/personal")
public class TLcReportPersonalController extends BaseController {
    private String prefix = "report/callLenNum/personal";

    @Autowired
    private ITLcReportPersonalService tLcReportPersonalService;

    @RequiresPermissions("report:personal:view")
    @GetMapping()
    public String personal() {
        return prefix + "/personal";
    }

    /**
     * 查询通时通次个人明细汇总报列表
     */
    @RequiresPermissions("report:personal:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcReportPersonal tLcReportPersonal) {
        startPage();
        List<TLcReportPersonal> list = tLcReportPersonalService.selectTLcReportPersonalList(tLcReportPersonal);
        return getDataTable(list);
    }

    /**
     * 导出通时通次个人明细汇总报列表
     */
    @RequiresPermissions("report:personal:export")
    @Log(title = "通时通次个人明细汇总报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcReportPersonal tLcReportPersonal) {
        List<TLcReportPersonal> list = tLcReportPersonalService.selectTLcReportPersonalList(tLcReportPersonal);
        ExcelUtil<TLcReportPersonal> util = new ExcelUtil<TLcReportPersonal>(TLcReportPersonal.class);
        return util.exportExcel(list, "personal");
    }

}
