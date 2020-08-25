package com.ruoyi.assetspackage.controller;

import java.util.List;

import com.ruoyi.assetspackage.domain.CertificateConfig;
import com.ruoyi.assetspackage.service.ICertificateConfigService;
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
 * 证件号码配置Controller
 * 
 * @author guozeqi
 * @date 2020-08-24
 */
@Controller
@RequestMapping("/certificate/config")
public class CertificateConfigController extends BaseController
{
    private String prefix = "certificate/config";

    @Autowired
    private ICertificateConfigService certificateConfigService;

    @RequiresPermissions("certificate:config:view")
    @GetMapping()
    public String config()
    {
        return prefix + "/config";
    }

    /**
     * 查询证件号码配置列表
     */
    @RequiresPermissions("certificate:config:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CertificateConfig certificateConfig)
    {
        startPage();
        List<CertificateConfig> list = certificateConfigService.selectCertificateConfigList(certificateConfig);
        return getDataTable(list);
    }

    /**
     * 查询list不分页
     */
//    @RequiresPermissions("certificate:config:getList")
    @PostMapping("/getList")
    @ResponseBody
    public List<CertificateConfig> getList(CertificateConfig certificateConfig)
    {
        List<CertificateConfig> list = certificateConfigService.selectCertificateConfigList(certificateConfig);
        return list;
    }

    /**
     * 导出证件号码配置列表
     */
    @RequiresPermissions("certificate:config:export")
    @Log(title = "证件号码配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CertificateConfig certificateConfig)
    {
        List<CertificateConfig> list = certificateConfigService.selectCertificateConfigList(certificateConfig);
        ExcelUtil<CertificateConfig> util = new ExcelUtil<CertificateConfig>(CertificateConfig.class);
        return util.exportExcel(list, "config");
    }

    /**
     * 新增证件号码配置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存证件号码配置
     */
    @RequiresPermissions("certificate:config:add")
    @Log(title = "证件号码配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CertificateConfig certificateConfig)
    {
        return toAjax(certificateConfigService.insertCertificateConfig(certificateConfig));
    }

    /**
     * 修改证件号码配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CertificateConfig certificateConfig = certificateConfigService.selectCertificateConfigById(id);
        mmap.put("certificateConfig", certificateConfig);
        return prefix + "/edit";
    }

    /**
     * 修改保存证件号码配置
     */
    @RequiresPermissions("certificate:config:edit")
    @Log(title = "证件号码配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CertificateConfig certificateConfig)
    {
        return toAjax(certificateConfigService.updateCertificateConfig(certificateConfig));
    }

    /**
     * 删除证件号码配置
     */
    @RequiresPermissions("certificate:config:remove")
    @Log(title = "证件号码配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(certificateConfigService.deleteCertificateConfigByIds(ids));
    }







}
