package com.ruoyi.robot.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.robot.domain.TLcRobotBlack;
import com.ruoyi.robot.service.ITLcRobotBlackService;
import com.ruoyi.utils.DesensitizationUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 机器人黑名单管理Controller
 *
 * @author liurunkai
 * @date 2020-06-24
 */
@Controller
@RequestMapping("/robot/black")
public class TLcRobotBlackController extends BaseController {
    private String prefix = "robot/black" ;

    @Autowired
    private ITLcRobotBlackService tLcRobotBlackService;

    @Autowired
    private DesensitizationUtil desensitizationUtil;

    @RequiresPermissions("robot:black:view")
    @GetMapping("/view")
    public String black(ModelMap modelMap) {
        //查询是否脱敏
        boolean desensitization = desensitizationUtil.isDesensitization(String.valueOf(ShiroUtils.getSysUser().getOrgId()), ShiroUtils.getLoginName());
        modelMap.put("desensitization", desensitization);
        return prefix + "/black" ;
    }

    /**
     * 查询机器人黑名单管理列表
     */
    @RequiresPermissions("robot:black:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcRobotBlack tLcRobotBlack) {
        startPage();
        List<TLcRobotBlack> list = tLcRobotBlackService.selectTLcRobotBlackList(tLcRobotBlack);
        return getDataTable(list);
    }

    /**
     * 导出机器人黑名单管理列表
     */
    @RequiresPermissions("robot:black:export")
    @Log(title = "机器人黑名单管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcRobotBlack tLcRobotBlack) {
        List<TLcRobotBlack> list = tLcRobotBlackService.selectTLcRobotBlackList(tLcRobotBlack);
        ExcelUtil<TLcRobotBlack> util = new ExcelUtil<TLcRobotBlack>(TLcRobotBlack.class);
        return util.exportExcel(list, "black");
    }

    /**
     * 新增机器人黑名单管理
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add" ;
    }

    /**
     * 新增保存机器人黑名单管理
     */
    @RequiresPermissions("robot:black:add")
    @Log(title = "机器人黑名单管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLcRobotBlack tLcRobotBlack) {
        return toAjax(tLcRobotBlackService.insertTLcRobotBlack(tLcRobotBlack));
    }

    /**
     * 修改机器人黑名单管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        TLcRobotBlack tLcRobotBlack = tLcRobotBlackService.selectTLcRobotBlackById(id);
        mmap.put("tLcRobotBlack", tLcRobotBlack);
        return prefix + "/edit" ;
    }

    /**
     * 修改保存机器人黑名单管理
     */
    @RequiresPermissions("robot:black:edit")
    @Log(title = "机器人黑名单管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLcRobotBlack tLcRobotBlack) {
        return toAjax(tLcRobotBlackService.updateTLcRobotBlack(tLcRobotBlack));
    }

    /**
     * 删除机器人黑名单管理
     */
    @RequiresPermissions("robot:black:remove")
    @Log(title = "机器人黑名单管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tLcRobotBlackService.deleteTLcRobotBlackByIds(ids));
    }
}
