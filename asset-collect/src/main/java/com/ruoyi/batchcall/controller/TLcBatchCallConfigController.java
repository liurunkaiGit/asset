package com.ruoyi.batchcall.controller;

import com.ruoyi.batchcall.domain.TLcBatchCallConfig;
import com.ruoyi.batchcall.service.ITLcBatchCallConfigService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.service.ISysDeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 批量外呼配置Controller
 * 
 * @author fengzhitao
 * @date 2020-06-28
 */
@Controller
@RequestMapping("/batchcallconfig")
public class TLcBatchCallConfigController extends BaseController
{
    private String prefix = "batchcall";

    @Autowired
    private ITLcBatchCallConfigService tLcBatchCallConfigService;
    @Autowired
    private ISysDeptService sysDeptService;

    @RequiresPermissions("ruoyi:batchcallconfig:view")
    @GetMapping()
    public String batchcallconfigconfig()
    {
        return prefix + "/config/batchcallconfig";
    }

    /**
     * 查询批量外呼配置列表
     */
    @RequiresPermissions("ruoyi:batchcallconfig:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcBatchCallConfig tLcBatchCallConfig)
    {
        startPage();
        String orgId = ShiroUtils.getSysUser().getOrgId()+"";
        tLcBatchCallConfig.setOrgId(orgId);
        List<TLcBatchCallConfig> list = tLcBatchCallConfigService.selectTLcBatchCallConfigList(tLcBatchCallConfig);
        return getDataTable(list);
    }

    /**
     * 导出批量外呼配置列表
     */
    @RequiresPermissions("ruoyi:batchcallconfig:export")
    @Log(title = "批量外呼配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcBatchCallConfig tLcBatchCallConfig)
    {
        List<TLcBatchCallConfig> list = tLcBatchCallConfigService.selectTLcBatchCallConfigList(tLcBatchCallConfig);
        ExcelUtil<TLcBatchCallConfig> util = new ExcelUtil<TLcBatchCallConfig>(TLcBatchCallConfig.class);
        return util.exportExcel(list, "batchcallconfig");
    }

    /**
     * 新增批量外呼配置
     */
    @GetMapping("/add")
    public String add(ModelMap map)
    {
        SysDept dept = sysDeptService.selectDeptById(ShiroUtils.getSysUser().getOrgId());
        map.put("dept",dept);
        return prefix + "/config/add";
    }

    /**
     * 新增保存批量外呼配置
     */
    @RequiresPermissions("ruoyi:batchcallconfig:add")
    @Log(title = "批量外呼配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLcBatchCallConfig tLcBatchCallConfig)
    {
        TLcBatchCallConfig tcc = this.tLcBatchCallConfigService.selectTLcBatchCallConfigByOrgId(tLcBatchCallConfig.getOrgId());
        if(tcc != null){//该机构已经存在
            return error("该机构已经存在过配置信息，不能重复配置");
        }
        return toAjax(tLcBatchCallConfigService.insertTLcBatchCallConfig(tLcBatchCallConfig));
    }

    /**
     * 修改批量外呼配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap map)
    {
        TLcBatchCallConfig tLcBatchCallConfig = tLcBatchCallConfigService.selectTLcBatchCallConfigById(id);
        int times = 0;
        if(StringUtils.isNotEmpty(tLcBatchCallConfig.getStartTime2())){
            times = 1;
        }
        if(StringUtils.isNotEmpty(tLcBatchCallConfig.getStartTime3())){
            times = 2;
        }
        map.put("times",times);
        map.put("tLcBatchCallConfig", tLcBatchCallConfig);
        return prefix + "/config/edit";
    }

    /**
     * 修改保存批量外呼配置
     */
    @RequiresPermissions("ruoyi:batchcallconfig:edit")
    @Log(title = "批量外呼配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLcBatchCallConfig tLcBatchCallConfig)
    {
        return toAjax(tLcBatchCallConfigService.updateTLcBatchCallConfig(tLcBatchCallConfig));
    }

    /**
     * 删除批量外呼配置
     */
    @RequiresPermissions("ruoyi:batchcallconfig:remove")
    @Log(title = "批量外呼配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tLcBatchCallConfigService.deleteTLcBatchCallConfigByIds(ids));
    }
}
