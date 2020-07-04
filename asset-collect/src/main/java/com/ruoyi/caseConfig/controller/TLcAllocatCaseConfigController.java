package com.ruoyi.caseConfig.controller;

import com.ruoyi.caseConfig.domain.TLcAllocatCaseConfig;
import com.ruoyi.caseConfig.service.ITLcAllocatCaseConfigService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.service.ISysDictDataService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 智能分案配置Controller
 *
 * @author liurunkai
 * @date 2020-04-23
 */
@Controller
@RequestMapping("/case/config")
public class TLcAllocatCaseConfigController extends BaseController {
    private String prefix = "caseConfig";

    @Autowired
    private ITLcAllocatCaseConfigService tLcAllocatCaseConfigService;
    @Autowired
    private ISysDictDataService sysDictDataService;

    @RequiresPermissions("ruoyi:config:view")
    @GetMapping()
    public String config() {
        return prefix + "/config";
    }

    /**
     * 查询智能分案配置列表
     */
    @RequiresPermissions("ruoyi:config:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcAllocatCaseConfig tLcAllocatCaseConfig) {
        startPage();
        List<TLcAllocatCaseConfig> list = tLcAllocatCaseConfigService.selectTLcAllocatCaseConfigList(tLcAllocatCaseConfig);
        return getDataTable(list);
    }

    /**
     * 导出智能分案配置列表
     */
    @RequiresPermissions("ruoyi:config:export")
    @Log(title = "智能分案配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcAllocatCaseConfig tLcAllocatCaseConfig) {
        List<TLcAllocatCaseConfig> list = tLcAllocatCaseConfigService.selectTLcAllocatCaseConfigList(tLcAllocatCaseConfig);
        ExcelUtil<TLcAllocatCaseConfig> util = new ExcelUtil<TLcAllocatCaseConfig>(TLcAllocatCaseConfig.class);
        return util.exportExcel(list, "config");
    }

    /**
     * 新增智能分案配置
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存智能分案配置
     */
    @RequiresPermissions("ruoyi:config:add")
    @Log(title = "智能分案配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLcAllocatCaseConfig tLcAllocatCaseConfig) {
        return toAjax(tLcAllocatCaseConfigService.insertTLcAllocatCaseConfig(tLcAllocatCaseConfig));
    }

    /**
     * 修改智能分案配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        TLcAllocatCaseConfig tLcAllocatCaseConfig = tLcAllocatCaseConfigService.selectTLcAllocatCaseConfigById(id);
        mmap.put("tLcAllocatCaseConfig", tLcAllocatCaseConfig);
        return prefix + "/edit";
    }

    /**
     * 修改保存智能分案配置
     */
    @RequiresPermissions("ruoyi:config:edit")
    @Log(title = "智能分案配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLcAllocatCaseConfig tLcAllocatCaseConfig) {
        return toAjax(tLcAllocatCaseConfigService.updateTLcAllocatCaseConfig(tLcAllocatCaseConfig));
    }

    /**
     * 删除智能分案配置
     */
    @RequiresPermissions("ruoyi:config:remove")
    @Log(title = "智能分案配置", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tLcAllocatCaseConfigService.deleteTLcAllocatCaseConfigByIds(ids));
    }

    @ResponseBody
    @PostMapping("/initRuleEngine")
    public TableDataInfo initRuleEngine() {
        List<SysDictData> sysDictDataList = this.sysDictDataService.selectDictDataByType("rule_engine");
        List<Result> callRecordCodeList = sysDictDataList.stream()
                .map(sysDictData -> Result.builder().code(sysDictData.getDictValue()).message(sysDictData.getDictLabel()).build())
                .collect(Collectors.toList());
        return getDataTable(callRecordCodeList);
    }

    @ResponseBody
    @PostMapping("/initRobot")
    public TableDataInfo initRobot() {
        List<SysDictData> sysDictDataList = this.sysDictDataService.selectDictDataByType("robot");
        List<Result> callRecordCodeList = sysDictDataList.stream()
                .map(sysDictData -> Result.builder().code(sysDictData.getDictValue()).message(sysDictData.getDictLabel()).build())
                .collect(Collectors.toList());
        return getDataTable(callRecordCodeList);
    }

    @ResponseBody
    @PostMapping("/initCallPlatform")
    public TableDataInfo initCallPlatform() {
        List<SysDictData> sysDictDataList = this.sysDictDataService.selectDictDataByType("call_platform");
        List<Result> callRecordCodeList = sysDictDataList.stream()
                .map(sysDictData -> Result.builder().code(sysDictData.getDictValue()).message(sysDictData.getDictLabel()).build())
                .collect(Collectors.toList());
        return getDataTable(callRecordCodeList);
    }

    @ResponseBody
    @PostMapping("/initAllocatCaseStrategy")
    public TableDataInfo initAllocatCaseStrategy() {
        List<SysDictData> sysDictDataList = this.sysDictDataService.selectDictDataByType("collect_model");
        List<Result> callRecordCodeList = sysDictDataList.stream()
                .map(sysDictData -> Result.builder().code(sysDictData.getDictValue()).message(sysDictData.getDictLabel()).build())
                .collect(Collectors.toList());
        return getDataTable(callRecordCodeList);
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
class Result {
    private String code;
    private String message;
}
