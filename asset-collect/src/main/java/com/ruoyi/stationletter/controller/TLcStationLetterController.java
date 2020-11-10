package com.ruoyi.stationletter.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.stationletter.domain.TLcStationLetter;
import com.ruoyi.stationletter.service.ITLcStationLetterService;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.task.domain.TLcTask;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 站内信Controller
 *
 * @author liurunkai
 * @date 2020-10-30
 */
@Slf4j
@Controller
@RequestMapping("/station/letter")
public class TLcStationLetterController extends BaseController {
    private String prefix = "stationletter";

    @Autowired
    private ITLcStationLetterService tLcStationLetterService;

    @RequiresPermissions("station:letter:view")
    @GetMapping()
    public String letter() {
        return prefix + "/letter";
    }

    /**
     * 查询站内信列表
     */
    @RequiresPermissions("station:letter:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcStationLetter tLcStationLetter) {
        startPage();
        List<TLcStationLetter> list = tLcStationLetterService.selectTLcStationLetterList(tLcStationLetter);
        return getDataTable(list);
    }

    /**
     * 导出站内信列表
     */
    @RequiresPermissions("station:letter:export")
    @Log(title = "站内信", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcStationLetter tLcStationLetter) {
        List<TLcStationLetter> list = tLcStationLetterService.selectTLcStationLetterList(tLcStationLetter);
        ExcelUtil<TLcStationLetter> util = new ExcelUtil<TLcStationLetter>(TLcStationLetter.class);
        return util.exportExcel(list, "letter");
    }

    /**
     * 新增站内信
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存站内信
     */
    @RequiresPermissions("station:letter:add")
    @Log(title = "站内信", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLcStationLetter tLcStationLetter) {
        return toAjax(tLcStationLetterService.insertTLcStationLetter(tLcStationLetter));
    }

    /**
     * 修改站内信
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        TLcStationLetter tLcStationLetter = tLcStationLetterService.selectTLcStationLetterById(id);
        mmap.put("tLcStationLetter", tLcStationLetter);
        return prefix + "/edit";
    }

    /**
     * 站内信详情
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap) {
        TLcStationLetter tLcStationLetter = tLcStationLetterService.selectTLcStationLetterById(id);
        mmap.put("tLcStationLetter", tLcStationLetter);
        return prefix + "/detail";
    }

    /**
     * 修改保存站内信
     */
    @RequiresPermissions("station:letter:edit")
    @Log(title = "站内信", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLcStationLetter tLcStationLetter) {
        return toAjax(tLcStationLetterService.updateTLcStationLetter(tLcStationLetter));
    }

    /**
     * 删除站内信
     */
    @RequiresPermissions("station:letter:remove")
    @Log(title = "站内信", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tLcStationLetterService.deleteTLcStationLetterByIds(ids));
    }

    @GetMapping("/toSelectUser")
    public String toSelectUser(ModelMap modelMap) {
        modelMap.put("orgId", ShiroUtils.getSysUser().getOrgId());
        return prefix + "/selectUser";
    }

    /**
     * 查询案件回收选择的用户
     */
    @ResponseBody
    @PostMapping("/selectSendLetterUser")
    public TableDataInfo selectSendLetterUser(SysUser sysUser, Long orgId) {
        TableDataInfo rspData = new TableDataInfo();
        sysUser.setDeptId(orgId);
        List<SysUser> userList = this.tLcStationLetterService.selectSendLetterUser(sysUser);
        rspData.setRows(userList);
        return  rspData;
    }
}
