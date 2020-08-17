package com.ruoyi.inforeporting.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.inforeporting.domain.TLcInforeportingBuckle;
import com.ruoyi.inforeporting.domain.TLcInforeportingReduction;
import com.ruoyi.inforeporting.domain.TLcInforeportingTemplate;
import com.ruoyi.inforeporting.service.TLcInforeportingReductionService;
import com.ruoyi.task.domain.TLcTask;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 信息上报减免Controller
 *
 * @author gaohg
 * @date 2020-08-13
 */
@Slf4j
@Controller
@RequestMapping("/inforeporting/reduction")
public class TLcInforeportingReductionController extends BaseController {
    private String prefix = "infoReporting/reduction";

    @Autowired
    private TLcInforeportingReductionService inforeportingReductionService;

    /**
     * 信息上报减免查询
     */
    @GetMapping("/list")
    public String list(String caseNo, String orgId, String importBatchNo, ModelMap mmap) {
        return prefix + "/list";
    }

    /**
     * 信息上报减免数据集合
     */
    @PostMapping("/list")
    @RequiresPermissions("inforeporting:reduction:list")
    @ResponseBody
    public TableDataInfo list(TLcInforeportingReduction inforeportingReduction)
    {
        startPage();
        inforeportingReduction.setCreateBy(ShiroUtils.getLoginName());
        List<TLcInforeportingReduction> list = inforeportingReductionService.selectTLcInforeportingReductionList(inforeportingReduction);
        return getDataTable(list);
    }

    /**
     * 信息上报所有类型数据集合
     */
    @PostMapping("/listAll")
//    @RequiresPermissions("inforeporting:reduction:listAll")
    @ResponseBody
    public List<TLcInforeportingTemplate> listAll(TLcInforeportingTemplate tLcInforeportingTemplate)
    {
        tLcInforeportingTemplate.setCreateBy(ShiroUtils.getLoginName());
        return inforeportingReductionService.selectTLcInforeportingAllList(tLcInforeportingTemplate);
    }
    /**
     * 信息上报减免报表查询
     */
    @GetMapping("/listexp")
    public String listexp() {
        return prefix + "/listexp";
    }

    /**
     * 新增【减免】
     */
    @GetMapping("/add")
    public String add(String caseNo, String orgId, String importBatchNo, ModelMap mmap) {
        TLcTask tk = inforeportingReductionService.selectTLcTaskByCaseNo(caseNo,orgId,importBatchNo);
        mmap.put("tk",tk);
        return prefix + "/add";
    }

    /**
     * 新增保存【减免】
     */
    @Log(title = "【上报信息减免新增】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @RequiresPermissions("inforeporting:reduction:add")
    @ResponseBody
    public AjaxResult addSave(@Validated TLcInforeportingReduction inforeportingReduction) {
        inforeportingReduction.setCreateBy(ShiroUtils.getLoginName());
        inforeportingReduction.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(inforeportingReductionService.insertTLcInforeportingReduction(inforeportingReduction));
    }

    @PostMapping("/loadTLcTask")
    @ResponseBody
    public TLcTask loadTLcTask(String caseNo, String orgId, String importBatchNo){
       return inforeportingReductionService.selectTLcTaskByCaseNo(caseNo,orgId,importBatchNo);
    }

    /**
     * 信息上报减免数据集合
     */
    @PostMapping("/listexpData")
    @RequiresPermissions("inforeporting:reduction:listexpData")
    @ResponseBody
    public TableDataInfo listexpData(TLcInforeportingReduction inforeportingReduction)
    {
        startPage();
        inforeportingReduction.setOrgId(ShiroUtils.getSysUser().getOrgId());
        List<TLcInforeportingReduction> list = inforeportingReductionService.selectTLcInforeportingReductionList(inforeportingReduction);
        return getDataTable(list);
    }

    /**
     * 导出减免列表
     */
    @RequiresPermissions("inforeporting:reduction:export")
    @Log(title = "减免", businessType = BusinessType.EXPORT)
    @PostMapping(value = "/export",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public AjaxResult export(TLcInforeportingReduction tLcInforeportingReduction, HttpServletResponse response) {
        tLcInforeportingReduction.setOrgId(ShiroUtils.getSysUser().getOrgId());
        List<TLcInforeportingReduction> list = inforeportingReductionService.selectTLcInforeportingReductionList(tLcInforeportingReduction);
        ExcelUtil<TLcInforeportingReduction> util = new ExcelUtil<>(TLcInforeportingReduction.class);
        return util.exportExcel(list, "减免");
    }

    @Log(title = "上报信息减免驳回", businessType = BusinessType.FORCE)
    @RequiresPermissions("inforeporting:reduction:reject")
    @PostMapping("/reject")
    @ResponseBody
    public AjaxResult reject(String ids)
    {
        return toAjax(inforeportingReductionService.rejectTLcInforeportingReductionByIds(ids));
    }


}
