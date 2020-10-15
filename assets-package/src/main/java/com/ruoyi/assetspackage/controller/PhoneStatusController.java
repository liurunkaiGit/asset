package com.ruoyi.assetspackage.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ruoyi.assetspackage.domain.phoneStatus.PhoneStatus;
import com.ruoyi.assetspackage.service.IPhoneStatusService;
import com.ruoyi.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
 * 号码状态Controller
 * 
 * @author guozeqi
 * @date 2020-08-31
 */
@Controller
@RequestMapping("/phone/status")
public class PhoneStatusController extends BaseController
{
    private String prefix = "assetspackage/phoneStatus";

    @Autowired
    private IPhoneStatusService phoneStatusService;

    @RequiresPermissions("phone:status:view")
    @GetMapping()
    public String status()
    {
        return prefix + "/status";
    }

    /**
     * 查询号码状态列表
     */
    @RequiresPermissions("phone:status:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PhoneStatus phoneStatus)
    {
        startPage();
        phoneStatus.setOrgId(String.valueOf(ShiroUtils.getSysUser().getOrgId()));
        List<PhoneStatus> list = phoneStatusService.selectPhoneStatusList(phoneStatus);
        return getDataTable(list);
    }

    /**
     * 导出号码状态列表
     */
    @RequiresPermissions("phone:status:export")
    @Log(title = "号码状态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PhoneStatus phoneStatus)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = "号码状态"+sdf.format( new Date());
        phoneStatus.setOrgId(String.valueOf(ShiroUtils.getSysUser().getOrgId()));
        List<PhoneStatus> list = phoneStatusService.selectPhoneStatusList(phoneStatus);
        ExcelUtil<PhoneStatus> util = new ExcelUtil<PhoneStatus>(PhoneStatus.class);
        return util.exportExcel(list, "status",fileName);
    }

    /**
     * 新增号码状态
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存号码状态
     */
    @RequiresPermissions("phone:status:add")
    @Log(title = "号码状态", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PhoneStatus phoneStatus)
    {
        return toAjax(phoneStatusService.insertPhoneStatus(phoneStatus));
    }

    /**
     * 修改号码状态
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PhoneStatus phoneStatus = phoneStatusService.selectPhoneStatusById(id);
        mmap.put("phoneStatus", phoneStatus);
        return prefix + "/edit";
    }

    /**
     * 修改保存号码状态
     */
    @RequiresPermissions("phone:status:edit")
    @Log(title = "号码状态", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PhoneStatus phoneStatus)
    {
        return toAjax(phoneStatusService.updatePhoneStatus(phoneStatus));
    }

    /**
     * 删除号码状态
     */
    @RequiresPermissions("phone:status:remove")
    @Log(title = "号码状态", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(phoneStatusService.deletePhoneStatusByIds(ids));
    }
}
