package com.ruoyi.custom.controller;

import java.util.List;

import com.ruoyi.custom.domain.TLcCustinfo;
import com.ruoyi.custom.service.ITLcCustinfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 客户信息Controller
 *
 * @author ruoyi
 * @date 2019-12-26
 */
@Controller
@RequestMapping("/collect/custom/info")
public class TLcCustinfoController extends BaseController {
    private String prefix = "custom/custinfo";

    @Autowired
    private ITLcCustinfoService tLcCustinfoService;

    @RequiresPermissions("system:custinfo:view")
    @GetMapping()
    public String custinfo() {
        return prefix + "/custinfo";
    }

    /**
     * 查询客户信息列表
     */
    @RequiresPermissions("system:custinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcCustinfo tLcCustinfo) {
        startPage();
        List<TLcCustinfo> list = tLcCustinfoService.selectTLcCustinfoList(tLcCustinfo);
        return getDataTable(list);
    }

    /**
     * 导出客户信息列表
     */
    @RequiresPermissions("system:custinfo:export")
    @Log(title = "客户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcCustinfo tLcCustinfo) {
        List<TLcCustinfo> list = tLcCustinfoService.selectTLcCustinfoList(tLcCustinfo);
        ExcelUtil<TLcCustinfo> util = new ExcelUtil<TLcCustinfo>(TLcCustinfo.class);
        return util.exportExcel(list, "custinfo");
    }

    /**
     * 新增客户信息
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存客户信息
     */
    @RequiresPermissions("system:custinfo:add")
    @Log(title = "客户信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLcCustinfo tLcCustinfo) {
        return toAjax(tLcCustinfoService.insertTLcCustinfo(tLcCustinfo));
    }

    /**
     * 修改客户信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        TLcCustinfo tLcCustinfo = tLcCustinfoService.selectTLcCustinfoById(id);
        mmap.put("tLcCustinfo", tLcCustinfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户信息
     */
    @RequiresPermissions("system:custinfo:edit")
    @Log(title = "客户信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLcCustinfo tLcCustinfo) {
        return toAjax(tLcCustinfoService.updateTLcCustinfo(tLcCustinfo));
    }

    /**
     * 删除客户信息
     */
    @RequiresPermissions("system:custinfo:remove")
    @Log(title = "客户信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tLcCustinfoService.deleteTLcCustinfoByIds(ids));
    }
}
