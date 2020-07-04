package com.ruoyi.report.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.report.domain.TLcReportCaseContact;
import com.ruoyi.report.service.ITLcReportCaseContactService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * 案件可联率报Controller
 *
 * @author liurunkai
 * @date 2020-04-09
 */
@Controller
@RequestMapping("/report/contact")
public class TLcReportCaseContactController extends BaseController {
    private String prefix = "report/contact";

    @Autowired
    private ITLcReportCaseContactService tLcReportCaseContactService;

    private static final DecimalFormat df = new DecimalFormat("0.0%");

    @RequiresPermissions("report:contact:view")
    @GetMapping()
    public String contact(ModelMap modelMap) {
        String curDate = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, new Date());
        modelMap.put("reportDate", DateUtils.parseDate(curDate));
        modelMap.put("updateTime", DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, new Date()));
        modelMap.put("reportName", "案件可联率");
        return prefix + "/contact";
    }

    /**
     * 查询案件可联率报列表
     */
    @RequiresPermissions("report:contact:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcReportCaseContact tLcReportCaseContact) {
        List<TLcReportCaseContact> list = this.tLcReportCaseContactService.selectTLcReportCaseContactList(tLcReportCaseContact);
        return getDataTable(list);
    }

    /**
     * 导出案件可联率报列表
     */
    @RequiresPermissions("report:contact:export")
    @Log(title = "案件可联率报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcReportCaseContact tLcReportCaseContact) {
        List<TLcReportCaseContact> list = this.tLcReportCaseContactService.selectTLcReportCaseContactList(tLcReportCaseContact);
        list.stream().forEach(contact -> {
            if (StringUtils.isNoneBlank(contact.getCustomerContactRecovery())) {
                contact.setCustomerContactRecovery(df.format(new BigDecimal(contact.getCustomerContactRecovery())));
            }
            if (StringUtils.isNoneBlank(contact.getCanContactCasePermetaRecovery())) {
                contact.setCanContactCasePermetaRecovery(df.format(new BigDecimal(contact.getCanContactCasePermetaRecovery())));
            }
        });
        ExcelUtil<TLcReportCaseContact> util = new ExcelUtil<TLcReportCaseContact>(TLcReportCaseContact.class);
        return util.exportExcel(list, "案件可联率");
    }

}
