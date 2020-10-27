package com.ruoyi.report.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.duncase.domain.AssetsRepayment;
import com.ruoyi.report.domain.TLcReportPersonal;
import com.ruoyi.report.service.ITLcReportPersonalService;
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
import java.math.RoundingMode;
import java.util.Date;
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
    public String personal(ModelMap modelMap) {
        String curDate = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, new Date());
        modelMap.put("reportDate", DateUtils.parseDate(curDate));
        return prefix + "/personal";
    }

    /**
     * 查询通时通次个人明细汇总报列表
     */
    @RequiresPermissions("report:personal:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcReportPersonal tLcReportPersonal) {
//        startPage();
        TableDataInfo rspData = new TableDataInfo();
        List<TLcReportPersonal> list = tLcReportPersonalService.selectTLcReportPersonalList(tLcReportPersonal);
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (null == pageDomain.getPageNum() || null == pageDomain.getPageSize()) {
            rspData.setRows(list);
            rspData.setTotal(list.size());
            return rspData;
        }
        Integer pageNum = (pageDomain.getPageNum() - 1) * pageDomain.getPageSize();
        Integer pageSize = pageDomain.getPageNum() * pageDomain.getPageSize();
        if (pageSize > list.size()) {
            pageSize = list.size();
        }
        rspData.setRows(list.subList(pageNum, pageSize));
        rspData.setTotal(list.size());
        return rspData;
//        return getDataTable(list);
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
        list.stream().forEach(personal -> {
            if (!personal.getTimePeriod().equals("合计")) {
                personal.setReportData(null);
                personal.setUserGroup(null);
                personal.setLoginName(null);
                personal.setUserName(null);
            }
            if (StringUtils.isNoneBlank(personal.getZjCallLen())) {
                BigDecimal bigDecimal = new BigDecimal(personal.getZjCallLen());
                personal.setZjCallLen(String.valueOf(bigDecimal.setScale(2, RoundingMode.HALF_UP)));
            }
            if (StringUtils.isNoneBlank(personal.getPaCallLen())) {
                BigDecimal bigDecimal = new BigDecimal(personal.getPaCallLen());
                personal.setPaCallLen(String.valueOf(bigDecimal.setScale(2, RoundingMode.HALF_UP)));
            }
            if (StringUtils.isNoneBlank(personal.getDyCallLen())) {
                BigDecimal bigDecimal = new BigDecimal(personal.getDyCallLen());
                personal.setDyCallLen(String.valueOf(bigDecimal.setScale(2, RoundingMode.HALF_UP)));
            }
            if (StringUtils.isNoneBlank(personal.getTotalCallLen())) {
                BigDecimal bigDecimal = new BigDecimal(personal.getTotalCallLen());
                personal.setTotalCallLen(String.valueOf(bigDecimal.setScale(2, RoundingMode.HALF_UP)));
            }
        });
        ExcelUtil<TLcReportPersonal> util = new ExcelUtil<TLcReportPersonal>(TLcReportPersonal.class);
        return util.exportExcel(list, "personal");
    }

}
