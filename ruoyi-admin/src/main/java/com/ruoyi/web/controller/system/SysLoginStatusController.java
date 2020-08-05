package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysLoginStatus;
import com.ruoyi.system.service.ISysLoginStatusService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
 * 登录状态Controller
 * 
 * @author guozeqi
 * @date 2020-08-06
 */
@Controller
@RequestMapping("/system/status")
public class SysLoginStatusController extends BaseController
{
    private String prefix = "system/status";

    @Autowired
    private ISysLoginStatusService sysLoginStatusService;

    @RequiresPermissions("system:status:view")
    @GetMapping()
    public String status()
    {
        return prefix + "/loginStatus";
    }

    /**
     * 查询登录状态列表
     */
    @RequiresPermissions("system:status:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysLoginStatus sysLoginStatus)
    {
        sysLoginStatus.setOrgId(String.valueOf(ShiroUtils.getSysUser().getOrgId()));
        startPage();
        List<SysLoginStatus> list = sysLoginStatusService.selectSysLoginStatusList(sysLoginStatus);
        return getDataTable(list);
    }

    /**
     * 导出登录状态列表
     */
    @RequiresPermissions("system:status:export")
    @Log(title = "登录状态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysLoginStatus sysLoginStatus)
    {
        sysLoginStatus.setOrgId(String.valueOf(ShiroUtils.getSysUser().getOrgId()));
        List<SysLoginStatus> list = sysLoginStatusService.selectSysLoginStatusList(sysLoginStatus);
        ExcelUtil<SysLoginStatus> util = new ExcelUtil<SysLoginStatus>(SysLoginStatus.class);
        return util.exportExcel(list, "status");
    }


}
