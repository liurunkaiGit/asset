package com.ruoyi.robot.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.robot.domain.TLcRobotTaskPandect;
import com.ruoyi.robot.service.ITLcRobotTaskPandectService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 【请填写功能名称】Controller
 *
 * @author liurunkai
 * @date 2020-05-09
 */
@Controller
@RequestMapping("/robot/pandect")
public class TLcRobotTaskPandectController extends BaseController {
    private String prefix = "robot/pandect";

    @Autowired
    private ITLcRobotTaskPandectService tLcRobotTaskPandectService;

    @RequiresPermissions("robot:pandect:view")
    @GetMapping("/view")
    public String pandect(HttpServletRequest request, ModelMap modelMap) {
        modelMap.put("orgId", ShiroUtils.getSysUser().getOrgId());
        return prefix + "/pandect";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("robot:pandect:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcRobotTaskPandect tLcRobotTaskPandect) {
        startPage();
        List<TLcRobotTaskPandect> list = tLcRobotTaskPandectService.selectTLcRobotTaskPandectList(tLcRobotTaskPandect);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("robot:pandect:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcRobotTaskPandect tLcRobotTaskPandect) {
        List<TLcRobotTaskPandect> list = tLcRobotTaskPandectService.selectTLcRobotTaskPandectList(tLcRobotTaskPandect);
        ExcelUtil<TLcRobotTaskPandect> util = new ExcelUtil<TLcRobotTaskPandect>(TLcRobotTaskPandect.class);
        return util.exportExcel(list, "pandect");
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
    @RequiresPermissions("robot:pandect:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLcRobotTaskPandect tLcRobotTaskPandect) {
        return toAjax(tLcRobotTaskPandectService.insertTLcRobotTaskPandect(tLcRobotTaskPandect));
    }

    /**
     * 修改【请填写功能名称】
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        TLcRobotTaskPandect tLcRobotTaskPandect = tLcRobotTaskPandectService.selectTLcRobotTaskPandectById(id);
        mmap.put("tLcRobotTaskPandect", tLcRobotTaskPandect);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("robot:pandect:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLcRobotTaskPandect tLcRobotTaskPandect) {
        return toAjax(tLcRobotTaskPandectService.updateTLcRobotTaskPandect(tLcRobotTaskPandect));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("robot:pandect:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tLcRobotTaskPandectService.deleteTLcRobotTaskPandectByIds(ids));
    }
}
