package com.ruoyi.inforeporting.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.inforeporting.domain.TLcInforeportingCompany;
import com.ruoyi.inforeporting.domain.TLcInforeportingCompanyExp;
import com.ruoyi.inforeporting.service.TLcInforeportingCompanyService;
import com.ruoyi.inforeporting.service.TLcInforeportingReductionService;
import com.ruoyi.task.domain.TLcTask;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 信息上报对公入账Controller
 *
 * @author gaohg
 * @date 2020-08-13
 */
@Slf4j
@Controller
@RequestMapping("/inforeporting/company")
public class TLcInforeportingCompanyController extends BaseController {
    private String prefix = "infoReporting/company";

    @Autowired
    private TLcInforeportingReductionService inforeportingReductionService;

    @Autowired
    private TLcInforeportingCompanyService inforeportingCompanyService;

    /**
     * 信息上报逾期划扣查询
     */
    @GetMapping("/list")
    public String list() {
        return prefix + "/list";
    }

    /**
     * 信息上报逾期划扣数据集合
     */
    @PostMapping("/list")
    @RequiresPermissions("inforeporting:company:list")
    @ResponseBody
    public TableDataInfo list(TLcInforeportingCompany inforeportingCompany)
    {
        startPage();
        inforeportingCompany.setCreateBy(ShiroUtils.getLoginName());
        List<TLcInforeportingCompany> list = inforeportingCompanyService.selectTLcInforeportingCompanyList(inforeportingCompany);
        return getDataTable(list);
    }

    /**
     * 信息上报划扣报表查询
     */
    @GetMapping("/listexp")
    public String listexp() {
        return prefix + "/listexp";
    }

    /**
     * 信息上报减免数据集合
     */
    @PostMapping("/listexpData")
    @RequiresPermissions("inforeporting:company:listexpData")
    @ResponseBody
    public TableDataInfo listexpData(TLcInforeportingCompany tLcInforeportingCompany)
    {
        startPage();
        tLcInforeportingCompany.setOrgId(ShiroUtils.getSysUser().getOrgId());
        List<TLcInforeportingCompany> list = inforeportingCompanyService.selectTLcInforeportingCompanyList(tLcInforeportingCompany);
        return getDataTable(list);
    }

    /**
     * 导出逾期划扣列表
     */
    @RequiresPermissions("inforeporting:company:export")
    @Log(title = "逾期划扣", businessType = BusinessType.EXPORT)
    @PostMapping(value = "/export",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public AjaxResult export(TLcInforeportingCompany tLcInforeportingCompany, HttpServletResponse response) {
        tLcInforeportingCompany.setOrgId(ShiroUtils.getSysUser().getOrgId());
        return inforeportingCompanyService.exportExcel(tLcInforeportingCompany);
    }



    /**
     * 新增【逾期划扣】
     */
    @GetMapping("/add")
    public String add(String caseNo, String orgId, String importBatchNo, ModelMap mmap) {
        TLcTask tk = inforeportingReductionService.selectTLcTaskByCaseNo(caseNo,orgId,importBatchNo);
        mmap.put("tk",tk);
        return prefix + "/add";
    }

    /**
     * 新增保存【逾期划扣】
     */
    @Log(title = "【上报信息】", businessType = BusinessType.INSERT)
    @RequiresPermissions("inforeporting:company:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated TLcInforeportingCompany inforeportingCompany) {
        inforeportingCompany.setCreateBy(ShiroUtils.getLoginName());
        inforeportingCompany.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(inforeportingCompanyService.insertTLcInforeportingCompany(inforeportingCompany));
    }

    @Log(title = "上报信息逾期划扣驳回", businessType = BusinessType.FORCE)
    @RequiresPermissions("inforeporting:company:reject")
    @PostMapping("/reject")
    @ResponseBody
    public AjaxResult reject(String ids)
    {
        return toAjax(inforeportingCompanyService.rejectTLcInforeportingCompanyByIds(ids));
    }


}
