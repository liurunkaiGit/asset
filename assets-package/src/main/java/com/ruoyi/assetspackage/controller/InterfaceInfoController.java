package com.ruoyi.assetspackage.controller;

import java.util.List;

import com.ruoyi.assetspackage.domain.luckElephant.InterfaceInfo;
import com.ruoyi.assetspackage.service.IInterfaceInfoService;
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
 * 接口信息记录Controller
 * 
 * @author guozeqi
 * @date 2020-08-07
 */
@Controller
@RequestMapping("/ruoyi/info")
public class InterfaceInfoController extends BaseController
{
    private String prefix = "ruoyi/info";

    @Autowired
    private IInterfaceInfoService interfaceInfoService;

    @RequiresPermissions("ruoyi:info:view")
    @GetMapping()
    public String info()
    {
        return prefix + "/info";
    }

    /**
     * 查询接口信息记录列表
     */
    @RequiresPermissions("ruoyi:info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(InterfaceInfo interfaceInfo)
    {
        startPage();
        List<InterfaceInfo> list = interfaceInfoService.selectInterfaceInfoList(interfaceInfo);
        return getDataTable(list);
    }

    /**
     * 导出接口信息记录列表
     */
    @RequiresPermissions("ruoyi:info:export")
    @Log(title = "接口信息记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(InterfaceInfo interfaceInfo)
    {
        List<InterfaceInfo> list = interfaceInfoService.selectInterfaceInfoList(interfaceInfo);
        ExcelUtil<InterfaceInfo> util = new ExcelUtil<InterfaceInfo>(InterfaceInfo.class);
        return util.exportExcel(list, "info");
    }

    /**
     * 新增接口信息记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存接口信息记录
     */
    @RequiresPermissions("ruoyi:info:add")
    @Log(title = "接口信息记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(InterfaceInfo interfaceInfo)
    {
        return toAjax(interfaceInfoService.insertInterfaceInfo(interfaceInfo));
    }

    /**
     * 修改接口信息记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        InterfaceInfo interfaceInfo = interfaceInfoService.selectInterfaceInfoById(id);
        mmap.put("interfaceInfo", interfaceInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存接口信息记录
     */
    @RequiresPermissions("ruoyi:info:edit")
    @Log(title = "接口信息记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(InterfaceInfo interfaceInfo)
    {
        return toAjax(interfaceInfoService.updateInterfaceInfo(interfaceInfo));
    }

    /**
     * 删除接口信息记录
     */
    @RequiresPermissions("ruoyi:info:remove")
    @Log(title = "接口信息记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(interfaceInfoService.deleteInterfaceInfoByIds(ids));
    }
}
