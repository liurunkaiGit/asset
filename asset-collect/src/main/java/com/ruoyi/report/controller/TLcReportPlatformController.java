package com.ruoyi.report.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.report.domain.TLcReportPlatform;
import com.ruoyi.report.service.ITLcReportPlatformService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequiresPermissions("ruoyi:platform:view")
    @GetMapping()
    public String platform() {
        return prefix + "/platform";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("report:platform:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcReportPlatform tLcReportPlatform) {
        startPage();
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
        ExcelUtil<TLcReportPlatform> util = new ExcelUtil<TLcReportPlatform>(TLcReportPlatform.class);
        return util.exportExcel(list, "platform");
    }

}
