package com.ruoyi.assetspackage.controller;

import com.ruoyi.assetspackage.domain.TLcImportFlow;
import com.ruoyi.assetspackage.service.ITLcImportFlowService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.DataPermissionUtils;
import com.ruoyi.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 【请填写功能名称】Controller
 *
 * @author liurunkai
 * @date 2020-03-24
 */
@Controller
@RequestMapping("/assetspackage/import/flow")
public class TLcImportFlowController extends BaseController {
    private String prefix = "assetspackage/flow";

    @Autowired
    private ITLcImportFlowService tLcImportFlowService;

    @RequiresPermissions("ruoyi:flow:view")
    @GetMapping()
    public String flow() {
        return prefix + "/flow";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcImportFlow tLcImportFlow) {
        String orgId = String.valueOf(ShiroUtils.getSysUser().getOrgId());
        tLcImportFlow.setOrgId(orgId);
        startPage();
        List<TLcImportFlow> list = tLcImportFlowService.selectTLcImportFlowList(tLcImportFlow);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("ruoyi:flow:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcImportFlow tLcImportFlow) {
        List<TLcImportFlow> list = tLcImportFlowService.selectTLcImportFlowList(tLcImportFlow);
        ExcelUtil<TLcImportFlow> util = new ExcelUtil<TLcImportFlow>(TLcImportFlow.class);
        return util.exportExcel(list, "flow");
    }

    /**
     * 新增【请填写功能名称】
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存【请填写功能名称】
     */
    @RequiresPermissions("ruoyi:flow:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLcImportFlow tLcImportFlow) {
        return toAjax(tLcImportFlowService.insertTLcImportFlow(tLcImportFlow));
    }

    /**
     * 修改【请填写功能名称】
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        TLcImportFlow tLcImportFlow = tLcImportFlowService.selectTLcImportFlowById(id);
        mmap.put("tLcImportFlow", tLcImportFlow);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("ruoyi:flow:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLcImportFlow tLcImportFlow) {
        return toAjax(tLcImportFlowService.updateTLcImportFlow(tLcImportFlow));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("ruoyi:flow:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tLcImportFlowService.deleteTLcImportFlowByIds(ids));
    }
}
