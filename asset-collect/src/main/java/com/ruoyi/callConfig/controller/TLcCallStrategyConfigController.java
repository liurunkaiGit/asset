package com.ruoyi.callConfig.controller;

import com.ruoyi.callConfig.domain.TLcCallStrategyConfig;
import com.ruoyi.callConfig.service.ITLcCallStrategyConfigService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.enums.BusinessSceneEnum;
import com.ruoyi.enums.StopCallConditionEnum;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.orgSpeechConf.domain.TLcOrgSpeechcraftConf;
import com.ruoyi.orgSpeechConf.service.ITLcOrgSpeechcraftConfService;
import com.ruoyi.robot.domain.Robot;
import com.ruoyi.robot.domain.RobotPhone;
import com.ruoyi.robot.enums.FinishedCallStatus;
import com.ruoyi.robot.utils.RobotMethodUtil;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysDictDataService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 【请填写功能名称】Controller
 *
 * @author liurunkai
 * @date 2020-02-06
 */
@Controller
@RequestMapping("/call/config")
public class TLcCallStrategyConfigController extends BaseController {
    private String prefix = "callConfig";

    @Autowired
    private ITLcCallStrategyConfigService tLcCallStrategyConfigService;
    @Autowired
    private ISysDictDataService dictDataService;
    @Autowired
    private RobotMethodUtil robotMethodUtil;
    @Autowired
    private ITLcOrgSpeechcraftConfService orgSpeechcraftConfService;

    @RequiresPermissions("call:config:view")
    @GetMapping()
    public String config() {
        return prefix + "/config";
    }

    /**
     * 查询呼叫配置列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcCallStrategyConfig tLcCallStrategyConfig) {
        startPage();
        List<TLcCallStrategyConfig> list = tLcCallStrategyConfigService.selectTLcCallStrategyConfigList(tLcCallStrategyConfig);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("call:config:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcCallStrategyConfig tLcCallStrategyConfig) {
        List<TLcCallStrategyConfig> list = tLcCallStrategyConfigService.selectTLcCallStrategyConfigList(tLcCallStrategyConfig);
        ExcelUtil<TLcCallStrategyConfig> util = new ExcelUtil<TLcCallStrategyConfig>(TLcCallStrategyConfig.class);
        return util.exportExcel(list, "config");
    }

    /**
     * 新增【请填写功能名称】
     */
    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        /*List<StopCallConditionEnum> stopCallContionList = new ArrayList<StopCallConditionEnum>(StopCallConditionEnum.values().length);
        Collections.addAll(stopCallContionList, StopCallConditionEnum.values());*/
        // 任务停止呼叫条件
        List<ActionCodeObj> stopCallContionList = Arrays.stream(StopCallConditionEnum.values())
                .map(actionCode -> ActionCodeObj.builder().code(actionCode.getCode()).message(actionCode.getMessage()).build())
                .collect(Collectors.toList());
        modelMap.put("stopCallConditionList", stopCallContionList);
        // 任务当天停止呼叫条件
        List<ActionCodeObj> curDayStopCallContionList = Arrays.stream(FinishedCallStatus.values())
                .map(actionCode -> ActionCodeObj.builder().code(actionCode.getCode()).message(actionCode.getDesc()).build())
                .collect(Collectors.toList());
        modelMap.put("curDayStopCallContionList", curDayStopCallContionList);
        return prefix + "/add";
    }

    /**
     * 新增保存【请填写功能名称】
     */
    @RequiresPermissions("call:config:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLcCallStrategyConfig tLcCallStrategyConfig) {
        return toAjax(tLcCallStrategyConfigService.insertTLcCallStrategyConfig(tLcCallStrategyConfig));
    }

    /**
     * 修改【请填写功能名称】
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        TLcCallStrategyConfig tLcCallStrategyConfig = tLcCallStrategyConfigService.selectTLcCallStrategyConfigById(id);
        mmap.put("tLcCallStrategyConfig", tLcCallStrategyConfig);
        // 回显停止任务呼叫条件
        List<ActionCodeObj> stopCallContionList = Arrays.stream(StopCallConditionEnum.values())
                .map(actionCode -> ActionCodeObj.builder().code(actionCode.getCode()).message(actionCode.getMessage()).flag(false).build())
                .collect(Collectors.toList());
        if (StringUtils.isNoneBlank(tLcCallStrategyConfig.getStopCallCondition())) {
            String[] stopCallConditions = tLcCallStrategyConfig.getStopCallCondition().split(",");
            for (int i = 0; i < stopCallConditions.length; i++) {
                for (ActionCodeObj actionCodeObj : stopCallContionList) {
                    if (Integer.valueOf(stopCallConditions[i]).equals(actionCodeObj.getCode())) {
                        actionCodeObj.setFlag(true);
                        continue;
                    }
                }
            }
        }
        mmap.put("stopCallConditionList", stopCallContionList);
        // 回显停止当天任务呼叫条件
        List<ActionCodeObj> curDayStopCallContionList = Arrays.stream(FinishedCallStatus.values())
                .map(actionCode -> ActionCodeObj.builder().code(actionCode.getCode()).message(actionCode.getDesc()).build())
                .collect(Collectors.toList());
        if (StringUtils.isNoneBlank(tLcCallStrategyConfig.getStopCallCurDayCondition())) {
            String[] stopCurCallConditions = tLcCallStrategyConfig.getStopCallCurDayCondition().split(",");
            for (int i = 0; i < stopCurCallConditions.length; i++) {
                for (ActionCodeObj actionCodeObj : curDayStopCallContionList) {
                    if (Integer.valueOf(stopCurCallConditions[i]).equals(actionCodeObj.getCode())) {
                        actionCodeObj.setFlag(true);
                        continue;
                    }
                }
            }
        }
        mmap.put("curDayStopCallContionList", curDayStopCallContionList);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("call:config:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLcCallStrategyConfig tLcCallStrategyConfig) {
        return toAjax(tLcCallStrategyConfigService.updateTLcCallStrategyConfig(tLcCallStrategyConfig));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("call:config:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tLcCallStrategyConfigService.deleteTLcCallStrategyConfigByIds(ids));
    }

    @ResponseBody
    @PostMapping("/initBusinessScene")
    public TableDataInfo initActionCode() {
        List<ActionCodeObj> actionCodeList = Arrays.stream(BusinessSceneEnum.values())
                .map(actionCode -> ActionCodeObj.builder().code(actionCode.getCode()).message(actionCode.getMessage()).build())
                .collect(Collectors.toList());
        return getDataTable(actionCodeList);
    }

    /**
     * 规则是否生效修改
     */
    @Log(title = "呼叫规则配置管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("call:config:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(TLcCallStrategyConfig tLcCallStrategyConfig) {
        return toAjax(tLcCallStrategyConfigService.updateTLcCallStrategyConfig(tLcCallStrategyConfig));
    }

    /**
     * 根据业务场景查询是否有开启的配置
     */
    @PostMapping("/findEnableByBusinessScene")
    @ResponseBody
    public TableDataInfo findEnableByBusinessScene(TLcCallStrategyConfig tLcCallStrategyConfig) {
        List<TLcCallStrategyConfig> list = this.tLcCallStrategyConfigService.selectTLcCallStrategyConfigList(tLcCallStrategyConfig);
        return getDataTable(list);
    }

    @ResponseBody
    @PostMapping("/initSpeechCraft")
    public TableDataInfo initSpeechCraft() {
//        List<SysDictData> sysDictDataList = this.dictDataService.selectDictDataByType("robot_speech_craft");
        List<Robot> robots = this.robotMethodUtil.getRobots();
        return getDataTable(robots);
    }

    @ResponseBody
    @PostMapping("/initSpeechCraftByOrgId")
    public TableDataInfo initSpeechCraftByOrgId(Long orgId) {
        List<Robot> robots = new ArrayList<>();
        TLcOrgSpeechcraftConf tLcOrgSpeechcraftConf = this.orgSpeechcraftConfService.selectTLcOrgSpeechcraftConfByOrgId(ShiroUtils.getSysUser().getOrgId());
        if (tLcOrgSpeechcraftConf != null) {
            String[] ids = tLcOrgSpeechcraftConf.getSpeechcraftId().split(",");
            String[] names = tLcOrgSpeechcraftConf.getSpeechcraftName().split(",");
            String[] scendDefIds = tLcOrgSpeechcraftConf.getSceneDefId().split(",");
            for (int i = 0; i < ids.length; i++) {
                Robot robot = new Robot();
                robot.setRobotDefId(Integer.valueOf(ids[i]));
                robot.setRobotName(names[i]);
                robot.setSceneDefId(Integer.valueOf(scendDefIds[i]));
                robots.add(robot);
            }
        }
        return getDataTable(robots);
    }

    @ResponseBody
    @PostMapping("/initCallIntervalTime")
    public TableDataInfo initCallIntervalTime() {
        List<SysDictData> sysDictDataList = this.dictDataService.selectDictDataByType("call_interval_time");
        return getDataTable(sysDictDataList);
    }

    @ResponseBody
    @PostMapping("/initCallLine")
    public TableDataInfo initCallLine() {
        List<RobotPhone> callLineList = this.tLcCallStrategyConfigService.selectCallLine();
        return getDataTable(callLineList);
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
class ActionCodeObj {
    private Integer code;
    private String message;
    private boolean flag;
}
