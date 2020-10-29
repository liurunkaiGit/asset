package com.ruoyi.shareproject.finance.commission.controller;

import com.ruoyi.assetspackage.domain.TLcImportFlow;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.shareproject.finance.commission.domain.TLpFinanceCommission;
import com.ruoyi.shareproject.finance.commission.service.ITLpFinanceCommissionService;
import com.ruoyi.shareproject.projectinformation.domain.TLpProjectInformation;
import com.ruoyi.shareproject.projectinformation.service.ITLpProjectInformationService;
import com.ruoyi.shareproject.result.domain.TLpResult;
import com.ruoyi.shareproject.result.service.ITLpResultService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 财务结佣Controller
 *
 * @author liurunkai
 * @date 2020-10-27
 */
@Controller
@RequestMapping("/finance/commission")
public class TLpFinanceCommissionController extends BaseController {
    private String prefix = "shareproject/finance/commission";

    @Autowired
    private ITLpFinanceCommissionService tLpFinanceCommissionService;
    @Autowired
    private ITLpProjectInformationService projectInformationService;

    @RequiresPermissions("finance:commission:view")
    @GetMapping()
    public String commission(ModelMap modelMap) {
        List<TLpProjectInformation> projectList = this.projectInformationService.selectTLpProjectInformationList(new TLpProjectInformation());
        modelMap.put("projectList", projectList);
        return prefix + "/commission";
    }

    /**
     * 查询财务结佣列表
     */
    @RequiresPermissions("finance:commission:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLpFinanceCommission tLpFinanceCommission) {
        startPage();
        List<TLpFinanceCommission> list = tLpFinanceCommissionService.selectTLpFinanceCommissionList(tLpFinanceCommission);
        return getDataTable(list);
    }

    /**
     * 导出财务结佣列表
     */
    @RequiresPermissions("finance:commission:export")
    @Log(title = "财务结佣", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLpFinanceCommission tLpFinanceCommission) {
        if (StringUtils.isNotBlank(tLpFinanceCommission.getIds())) {
            tLpFinanceCommission.setIdList(Arrays.asList(tLpFinanceCommission.getIds().split(",")));
        }
        List<TLpFinanceCommission> list = tLpFinanceCommissionService.selectTLpFinanceCommissionList(tLpFinanceCommission);
        ExcelUtil<TLpFinanceCommission> util = new ExcelUtil<TLpFinanceCommission>(TLpFinanceCommission.class);
        return util.exportExcel(list, "commission");
    }

    /**
     * 新增财务结佣
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存财务结佣
     */
    @RequiresPermissions("finance:commission:add")
    @Log(title = "财务结佣", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLpFinanceCommission tLpFinanceCommission) {
        return toAjax(tLpFinanceCommissionService.insertTLpFinanceCommission(tLpFinanceCommission));
    }

    /**
     * 修改财务结佣
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        TLpFinanceCommission tLpFinanceCommission = tLpFinanceCommissionService.selectTLpFinanceCommissionById(id);
        mmap.put("tLpFinanceCommission", tLpFinanceCommission);
        return prefix + "/edit";
    }

    /**
     * 修改保存财务结佣
     */
    @RequiresPermissions("finance:commission:edit")
    @Log(title = "财务结佣", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLpFinanceCommission tLpFinanceCommission) {
        return toAjax(tLpFinanceCommissionService.updateTLpFinanceCommission(tLpFinanceCommission));
    }

    /**
     * 删除财务结佣
     */
    @RequiresPermissions("finance:commission:remove")
    @Log(title = "财务结佣", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tLpFinanceCommissionService.deleteTLpFinanceCommissionByIds(ids));
    }

    /**
     * 设置实际结佣
     */
    @GetMapping("/setActualCommission")
    @ResponseBody
    public AjaxResult setActualCommission(Long id, BigDecimal actualCommission, BigDecimal latestPredictCommission, Integer totalNum) {
        return this.tLpFinanceCommissionService.setActualCommission(id, actualCommission, latestPredictCommission, totalNum);
    }

    /**
     * 跳转财务结佣详情页
     *
     * @param month
     * @param projectId
     * @param modelMap
     * @return
     */
    @GetMapping("/toShowFinanceCommissionDetail")
    public String showFinanceCommissionDetail(String month, Long projectId, ModelMap modelMap) {
        modelMap.put("month", month);
        modelMap.put("projectId", projectId);
        return prefix + "/showFinanceCommissionDetail";
    }

    /**
     * 查询财务结佣详情
     *
     * @param financeCommission
     * @return
     */
    @PostMapping("/showFinanceCommissionDetail")
    @ResponseBody
    public TableDataInfo showFinanceCommissionDetail(TLpFinanceCommission financeCommission) {
        startPage();
        List<TLpResult> list = this.tLpFinanceCommissionService.showFinanceCommissionDetail(financeCommission);
        return getDataTable(list);
    }

}
