package com.ruoyi.orgSpeechConf.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.orgSpeechConf.domain.TLcOrgSpeechcraftConf;
import com.ruoyi.orgSpeechConf.service.ITLcOrgSpeechcraftConfService;
import com.ruoyi.robot.domain.Robot;
import com.ruoyi.robot.utils.RobotMethodUtil;
import com.ruoyi.system.service.ISysDictDataService;
import org.apache.commons.lang3.StringUtils;
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
 * @date 2020-05-18
 */
@Controller
@RequestMapping("/orgspeech/conf")
public class TLcOrgSpeechcraftConfController extends BaseController {
    private String prefix = "orgSpeechConf";

    @Autowired
    private ITLcOrgSpeechcraftConfService tLcOrgSpeechcraftConfService;
    @Autowired
    private ISysDictDataService sysDictDataService;
    @Autowired
    private RobotMethodUtil robotMethodUtil;

    @RequiresPermissions("orgspeech:conf:view")
    @GetMapping()
    public String conf() {
        return prefix + "/conf";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("orgspeech:conf:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcOrgSpeechcraftConf tLcOrgSpeechcraftConf) {
        startPage();
        List<TLcOrgSpeechcraftConf> list = tLcOrgSpeechcraftConfService.selectTLcOrgSpeechcraftConfList(tLcOrgSpeechcraftConf);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("orgspeech:conf:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcOrgSpeechcraftConf tLcOrgSpeechcraftConf) {
        List<TLcOrgSpeechcraftConf> list = tLcOrgSpeechcraftConfService.selectTLcOrgSpeechcraftConfList(tLcOrgSpeechcraftConf);
        ExcelUtil<TLcOrgSpeechcraftConf> util = new ExcelUtil<TLcOrgSpeechcraftConf>(TLcOrgSpeechcraftConf.class);
        return util.exportExcel(list, "conf");
    }

    /**
     * 新增【请填写功能名称】
     */
    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        // 查询剩余并发量
        String robotNum = this.sysDictDataService.selectDictLabel("robot_call_config", "robot_num");
        Integer usedTotalConcurrentValue = this.tLcOrgSpeechcraftConfService.selectUsedTotalConcurrentValue();
        usedTotalConcurrentValue = usedTotalConcurrentValue == null ? 0 : usedTotalConcurrentValue;
        modelMap.put("surPlusConcurrentValue", Integer.valueOf(robotNum) - usedTotalConcurrentValue);
        // 查询机器人话术列表
        List<Robot> speechcraftNameList = this.robotMethodUtil.getRobots();
        speechcraftNameList.stream().forEach(s -> {
            s.setRobotDefIdAndName(String.valueOf(s.getRobotDefId()).concat("|").concat(s.getRobotName()).concat("|").concat(String.valueOf(s.getSceneDefId())));
        });
        modelMap.put("speechcraftNameList", speechcraftNameList);
        return prefix + "/add";
    }

    /**
     * 新增保存【请填写功能名称】
     */
    @RequiresPermissions("orgspeech:conf:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLcOrgSpeechcraftConf tLcOrgSpeechcraftConf) {
        return toAjax(tLcOrgSpeechcraftConfService.insertTLcOrgSpeechcraftConf(tLcOrgSpeechcraftConf));
    }

    /**
     * 修改【请填写功能名称】
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap modelMap) {
        TLcOrgSpeechcraftConf tLcOrgSpeechcraftConf = tLcOrgSpeechcraftConfService.selectTLcOrgSpeechcraftConfById(id);
        // 查询剩余并发量
        String robotNum = this.sysDictDataService.selectDictLabel("robot_call_config", "robot_num");
        Integer usedTotalConcurrentValue = this.tLcOrgSpeechcraftConfService.selectUsedTotalConcurrentValue();
        usedTotalConcurrentValue = usedTotalConcurrentValue == null ? 0 : usedTotalConcurrentValue;
        modelMap.put("surPlusConcurrentValue", Integer.valueOf(robotNum) - usedTotalConcurrentValue);
        modelMap.put("waitEditConcurrentValue", tLcOrgSpeechcraftConf.getConcurrentValue());
        // 查询机器人话术列表
        List<Robot> speechcraftNameList = this.robotMethodUtil.getRobots();
        if (StringUtils.isNoneBlank(tLcOrgSpeechcraftConf.getSpeechcraftId())) {
            String[] speechcraftIds = tLcOrgSpeechcraftConf.getSpeechcraftId().split(",");
            for (int i = 0; i < speechcraftIds.length; i++) {
                for (Robot robot : speechcraftNameList) {
                    if (Integer.valueOf(speechcraftIds[i]).equals(robot.getRobotDefId())) {
                        robot.setFlag(true);
                        continue;
                    }
                }
            }
        }
        speechcraftNameList.stream().forEach(s -> {
            s.setRobotDefIdAndName(String.valueOf(s.getRobotDefId()).concat("|").concat(s.getRobotName()).concat("|").concat(String.valueOf(s.getSceneDefId())));
        });
        modelMap.put("speechcraftNameList", speechcraftNameList);
        modelMap.put("tLcOrgSpeechcraftConf", tLcOrgSpeechcraftConf);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("orgspeech:conf:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLcOrgSpeechcraftConf tLcOrgSpeechcraftConf) {
        return toAjax(tLcOrgSpeechcraftConfService.updateTLcOrgSpeechcraftConf(tLcOrgSpeechcraftConf));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("orgspeech:conf:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tLcOrgSpeechcraftConfService.deleteTLcOrgSpeechcraftConfByIds(ids));
    }

    /**
     * 验证机构唯一性
     */
    @PostMapping("/checkOrgIdUnique")
    @ResponseBody
    public String checkOrgIdUnique(String orgId) {
        return tLcOrgSpeechcraftConfService.checkOrgIdUnique(orgId);
    }
}
