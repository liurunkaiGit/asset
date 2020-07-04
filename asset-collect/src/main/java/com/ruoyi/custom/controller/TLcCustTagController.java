package com.ruoyi.custom.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.custom.domain.TLcCustTag;
import com.ruoyi.custom.service.ITLcCustTagService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 客户标签Controller
 *
 * @author liurunkai
 * @date 2020-01-09
 */
@Controller
@RequestMapping("/collect/cust/tag")
public class TLcCustTagController extends BaseController {
    private String prefix = "custom/tag";

    @Autowired
    private ITLcCustTagService tLcCustTagService;

    @RequiresPermissions("cust:tag:view")
    @GetMapping()
    public String tag() {
        return prefix + "/tag";
    }

    @GetMapping("/view")
    public String tag(ModelMap modelMap, HttpServletRequest request) {
        modelMap.put("certificateNo",request.getParameter("certificateNo"));
        modelMap.put("customName",request.getParameter("customName"));
        return prefix + "/tag";
    }

    /**
     * 查询客户标签列表
     */
    @RequiresPermissions("ruoyi:tag:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcCustTag tLcCustTag) {
        startPage();
        List<TLcCustTag> list = tLcCustTagService.selectTLcCustTagList(tLcCustTag);
        return getDataTable(list);
    }

    /**
     * 导出客户标签列表
     */
    @RequiresPermissions("ruoyi:tag:export")
    @Log(title = "客户标签", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcCustTag tLcCustTag) {
        List<TLcCustTag> list = tLcCustTagService.selectTLcCustTagList(tLcCustTag);
        ExcelUtil<TLcCustTag> util = new ExcelUtil<TLcCustTag>(TLcCustTag.class);
        return util.exportExcel(list, "tag");
    }

    /**
     * 新增客户标签
     */
    @GetMapping("/add")
    public String add(ModelMap modelMap, HttpServletRequest request) {
        modelMap.put("certificateNo",request.getParameter("certificateNo"));
        modelMap.put("customName",request.getParameter("customName"));
        return prefix + "/add";
    }

    /**
     * 新增保存客户标签
     */
    @RequiresPermissions("ruoyi:tag:add")
    @Log(title = "客户标签", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLcCustTag tLcCustTag) {
        return toAjax(tLcCustTagService.insertTLcCustTag(tLcCustTag));
    }

    /**
     * 修改客户标签
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        TLcCustTag tLcCustTag = tLcCustTagService.selectTLcCustTagById(id);
        mmap.put("tLcCustTag", tLcCustTag);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户标签
     */
    @RequiresPermissions("ruoyi:tag:edit")
    @Log(title = "客户标签", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLcCustTag tLcCustTag) {
        return toAjax(tLcCustTagService.updateTLcCustTag(tLcCustTag));
    }

    /**
     * 删除客户标签
     */
    @RequiresPermissions("ruoyi:tag:remove")
    @Log(title = "客户标签", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tLcCustTagService.deleteTLcCustTagByIds(ids));
    }
}
