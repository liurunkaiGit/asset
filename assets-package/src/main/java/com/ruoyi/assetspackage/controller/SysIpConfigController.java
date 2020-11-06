package com.ruoyi.assetspackage.controller;

import java.util.List;

import com.ruoyi.assetspackage.domain.SysIpConfig;
import com.ruoyi.assetspackage.service.ISysIpConfigService;
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
 * ip段配置Controller
 * 
 * @author guozeqi
 * @date 2020-11-02
 */
@Controller
@RequestMapping("/ip/config")
public class SysIpConfigController extends BaseController
{
    private String prefix = "assetspackage/ipConfig";

    @Autowired
    private ISysIpConfigService sysIpConfigService;

    @RequiresPermissions("ip:config:view")
    @GetMapping()
    public String config()
    {
        return prefix + "/config";
    }

    /**
     * 查询ip段配置列表
     */
    @RequiresPermissions("ip:config:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysIpConfig sysIpConfig)
    {
        startPage();
        List<SysIpConfig> list = sysIpConfigService.selectSysIpConfigList(sysIpConfig);
        return getDataTable(list);
    }

    /**
     * 导出ip段配置列表
     */
    @RequiresPermissions("ip:config:export")
    @Log(title = "ip段配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysIpConfig sysIpConfig)
    {
        List<SysIpConfig> list = sysIpConfigService.selectSysIpConfigList(sysIpConfig);
        ExcelUtil<SysIpConfig> util = new ExcelUtil<SysIpConfig>(SysIpConfig.class);
        return util.exportExcel(list, "config");
    }

    /**
     * 新增ip段配置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存ip段配置
     */
    @RequiresPermissions("ip:config:add")
    @Log(title = "ip段配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysIpConfig sysIpConfig)
    {
        return toAjax(sysIpConfigService.insertSysIpConfig(sysIpConfig));
    }

    /**
     * 修改ip段配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SysIpConfig sysIpConfig = sysIpConfigService.selectSysIpConfigById(id);
        mmap.put("sysIpConfig", sysIpConfig);
        return prefix + "/edit";
    }

    /**
     * 修改保存ip段配置
     */
    @RequiresPermissions("ip:config:edit")
    @Log(title = "ip段配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysIpConfig sysIpConfig)
    {
        return toAjax(sysIpConfigService.updateSysIpConfig(sysIpConfig));
    }

    /**
     * 删除ip段配置
     */
    @RequiresPermissions("ip:config:remove")
    @Log(title = "ip段配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysIpConfigService.deleteSysIpConfigByIds(ids));
    }
}
