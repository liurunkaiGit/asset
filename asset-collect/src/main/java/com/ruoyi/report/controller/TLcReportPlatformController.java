package com.ruoyi.report.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.report.domain.TLcReportPlatform;
import com.ruoyi.report.service.ITLcReportPlatformService;
import com.ruoyi.utils.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

/**
 * 【请填写功能名称】Controller
 *
 * @author liurunkai
 * @date 2020-08-04
 */
@Controller
@RequestMapping("/report/platform")
public class TLcReportPlatformController extends BaseController {
    private String prefix = "report/callLenNum/platform";

    @Autowired
    private ITLcReportPlatformService tLcReportPlatformService;

    @RequiresPermissions("report:platform:view")
    @GetMapping()
    public String platform(ModelMap modelMap) {
        String curDate = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, new Date());
        modelMap.put("reportDate", DateUtils.parseDate(curDate));
        return prefix + "/platform";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("report:platform:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcReportPlatform tLcReportPlatform) {
//        startPage();
        List<TLcReportPlatform> list = tLcReportPlatformService.selectTLcReportPlatformList(tLcReportPlatform);
        return getDataTable(list);
    }

    /**
     * 查询通时通次报表接口，共享管理
     */
    @PostMapping("/list/share")
    @ResponseBody
    public TableDataInfo listShare(@RequestBody TLcReportPlatform tLcReportPlatform) {
//        startPage();
        List<TLcReportPlatform> list = tLcReportPlatformService.selectTLcReportPlatformList(tLcReportPlatform);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("report:platform:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcReportPlatform tLcReportPlatform) {
        List<TLcReportPlatform> list = tLcReportPlatformService.selectTLcReportPlatformList(tLcReportPlatform);
        list.stream().forEach(platform -> {
            if (StringUtils.isNoneBlank(platform.getZjCallLen())) {
                BigDecimal bigDecimal = new BigDecimal(platform.getZjCallLen());
                platform.setZjCallLen(String.valueOf(bigDecimal.setScale(2, RoundingMode.HALF_UP)));
            }
            if (StringUtils.isNoneBlank(platform.getPaCallLen())) {
                BigDecimal bigDecimal = new BigDecimal(platform.getPaCallLen());
                platform.setPaCallLen(String.valueOf(bigDecimal.setScale(2, RoundingMode.HALF_UP)));
            }
            if (StringUtils.isNoneBlank(platform.getDyCallLen())) {
                BigDecimal bigDecimal = new BigDecimal(platform.getDyCallLen());
                platform.setDyCallLen(String.valueOf(bigDecimal.setScale(2, RoundingMode.HALF_UP)));
            }
            if (StringUtils.isNoneBlank(platform.getTotalCallLen())) {
                BigDecimal bigDecimal = new BigDecimal(platform.getTotalCallLen());
                platform.setTotalCallLen(String.valueOf(bigDecimal.setScale(2, RoundingMode.HALF_UP)));
            }
        });
        ExcelUtil<TLcReportPlatform> util = new ExcelUtil<TLcReportPlatform>(TLcReportPlatform.class);
        return util.exportExcel(list, "platform");
    }

}
