package com.ruoyi.stationletter.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.enums.IsNoEnum;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.stationletter.domain.TLcStationLetter;
import com.ruoyi.stationletter.domain.TLcStationLetterAgent;
import com.ruoyi.stationletter.service.ITLcStationLetterService;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.task.domain.TLcTask;
import com.ruoyi.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private ISysUserService sysUserService;

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
        // 指定用户的时候如果没有选择自己需要将自己添加上
        if (tLcStationLetter.getSendRange().equals(IsNoEnum.NO.getCode())) {
            List<String> userIds = new ArrayList(Arrays.asList(tLcStationLetter.getUserIds().split(",")));
            if (!userIds.contains(ShiroUtils.getSysUser().getUserId().toString())) {
                userIds.add(ShiroUtils.getSysUser().getUserId().toString());
                tLcStationLetter.setUserIds(userIds.stream().collect(Collectors.joining(",")));
            }
        }
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
        if (StringUtils.isNotBlank(tLcStationLetter.getUserIds())) {
            List<String> userIds = Arrays.asList(tLcStationLetter.getUserIds().split(","));
            List<SysUser> sysUsers = this.sysUserService.selectUserListByUserIds(userIds);
            String userNameList = sysUsers.stream().map(sysUser -> sysUser.getUserName()).collect(Collectors.joining(","));
            mmap.put("userNameList", userNameList);
        }
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
    public String toSelectUser(ModelMap modelMap, String userIds) {
        modelMap.put("orgId", ShiroUtils.getSysUser().getOrgId());
        modelMap.put("userIds",userIds);
        return prefix + "/selectUser";
    }

    /**
     * 查询案件回收选择的用户
     */
    @ResponseBody
    @PostMapping("/selectSendLetterUser")
    public TableDataInfo selectSendLetterUser(SysUser sysUser, Long orgId, String userIds) {
        TableDataInfo rspData = new TableDataInfo();
        sysUser.setDeptId(orgId);
        List<SysUser> userList = this.tLcStationLetterService.selectSendLetterUser(sysUser);
        rspData.setRows(userList);
        return  rspData;
    }

    /**
     * 站内信回复
     *
     * @return
     */
    @PostMapping("/sendReply")
    @ResponseBody
    public Response sendReply(TLcStationLetter tLcStationLetter) {
        this.tLcStationLetterService.sendReplyStationLetter(tLcStationLetter);
        return Response.success(null);
    }
}
