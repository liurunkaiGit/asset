package com.ruoyi.clientConfig.controller;

import com.ruoyi.clientConfig.domain.TLcClientConfig;
import com.ruoyi.clientConfig.service.ITLcClientConfigService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 【请填写功能名称】Controller
 *
 * @author liurunkai
 * @date 2020-03-11
 */
@Controller
@RequestMapping("/collect/client/config")
public class TLcClientConfigController extends BaseController {
    private String prefix = "clientConfig";

    @Autowired
    private ITLcClientConfigService tLcClientConfigService;

    @RequiresPermissions("collect:client:config")
    @GetMapping()
    public String config() {
        return prefix + "/config";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("collect:client:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcClientConfig tLcClientConfig) {
        startPage();
        List<TLcClientConfig> list = tLcClientConfigService.selectTLcClientConfigList(tLcClientConfig);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("collect:client:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcClientConfig tLcClientConfig) {
        List<TLcClientConfig> list = tLcClientConfigService.selectTLcClientConfigList(tLcClientConfig);
        ExcelUtil<TLcClientConfig> util = new ExcelUtil<TLcClientConfig>(TLcClientConfig.class);
        return util.exportExcel(list, "config");
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
    @RequiresPermissions("collect:client:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLcClientConfig tLcClientConfig) {
        return toAjax(tLcClientConfigService.insertTLcClientConfig(tLcClientConfig));
    }

    /**
     * 修改【请填写功能名称】
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        TLcClientConfig tLcClientConfig = tLcClientConfigService.selectTLcClientConfigById(id);
        mmap.put("tLcClientConfig", tLcClientConfig);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("collect:client:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLcClientConfig tLcClientConfig) {
        return toAjax(tLcClientConfigService.updateTLcClientConfig(tLcClientConfig));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("collect:client:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tLcClientConfigService.deleteTLcClientConfigByIds(ids));
    }
}
