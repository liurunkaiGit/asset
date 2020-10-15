package com.ruoyi.shareproject.projectinformation.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.shareproject.projectinformation.domain.TLpProjectInformation;
import com.ruoyi.shareproject.projectinformation.service.ITLpProjectInformationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 【共享信息管理-项目信息管理】Controller
 * @author gaohg
 * @date 2020-10-13
 */
@Controller
@RequestMapping("shareproject/projectinformation")
public class TLpProjectInformationController extends BaseController {

    private String prefix = "shareproject/projectinformation";

    @Autowired
    private ITLpProjectInformationService tLpProjectInformationService;

    @GetMapping()
    public String information()
    {
        return prefix + "/projectinformation";
    }

    /**
     * 查询【项目信息管理】列表
     */
    @RequiresPermissions("shareproject:projectinformation:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLpProjectInformation tLpProjectInformation)
    {
        startPage();
        List<TLpProjectInformation> list = tLpProjectInformationService.selectTLpProjectInformationList(tLpProjectInformation);
        return getDataTable(list);
    }
    /**
     * 查询【项目信息管理】列表 下拉
     */
    @RequiresPermissions("shareproject:projectinformation:list")
    @PostMapping("/listxl")
    @ResponseBody
    public List<TLpProjectInformation> listxl(TLpProjectInformation tLpProjectInformation)
    {
        return tLpProjectInformationService.selectTLpProjectInformationList(tLpProjectInformation);
    }

    /**
     * 导出【项目信息管理】列表
     */
    @RequiresPermissions("shareproject:projectinformation:export")
    @Log(title = "【项目信息管理】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLpProjectInformation tLpProjectInformation)
    {
        List<TLpProjectInformation> list = tLpProjectInformationService.selectTLpProjectInformationList(tLpProjectInformation);
        ExcelUtil<TLpProjectInformation> util = new ExcelUtil<TLpProjectInformation>(TLpProjectInformation.class);
        return util.exportExcel(list, "information");
    }

    /**
     * 新增【项目信息管理】
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存【项目信息管理】
     */
    @RequiresPermissions("shareproject:projectinformation:add")
    @Log(title = "【项目信息管理】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLpProjectInformation tLpProjectInformation)
    {
        tLpProjectInformation.setCreateBy(ShiroUtils.getLoginName());
        tLpProjectInformation.setCreateTime(new Date());
        tLpProjectInformation.setUpdateBy(ShiroUtils.getLoginName());
        tLpProjectInformation.setUpdateTime(new Date());
        return toAjax(tLpProjectInformationService.insertTLpProjectInformation(tLpProjectInformation));
    }

    /**
     * 修改【项目信息管理】
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TLpProjectInformation tLpProjectInformation = tLpProjectInformationService.selectTLpProjectInformationById(id);
        mmap.put("tLpProjectInformation", tLpProjectInformation);
        return prefix + "/edit";
    }

    /**
     * 修改保存【项目信息管理】
     */
    @RequiresPermissions("shareproject:projectinformation:edit")
    @Log(title = "【项目信息管理】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLpProjectInformation tLpProjectInformation)
    {
        tLpProjectInformation.setUpdateBy(ShiroUtils.getLoginName());
        tLpProjectInformation.setUpdateTime(new Date());
        return toAjax(tLpProjectInformationService.updateTLpProjectInformation(tLpProjectInformation));
    }

    /**
     * 删除【项目信息管理】
     */
    @RequiresPermissions("shareproject:projectinformation:remove")
    @Log(title = "【项目信息管理】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tLpProjectInformationService.deleteTLpProjectInformationByIds(ids));
    }
}
