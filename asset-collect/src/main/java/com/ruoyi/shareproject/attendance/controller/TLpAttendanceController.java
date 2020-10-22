package com.ruoyi.shareproject.attendance.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.shareproject.attendance.domain.TLpAttendance;
import com.ruoyi.shareproject.attendance.service.ITLpAttendanceService;
import com.ruoyi.shareproject.projectinformation.domain.TLpProjectInformation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
/**
 * 【出勤信息管理】Controller
 *
 * @author gaohg
 * @date 2020-10-15
 */
@Controller
@RequestMapping("/shareproject/attendance")
public class TLpAttendanceController extends BaseController {
    private String prefix = "shareproject/attendance";

    @Autowired
    private ITLpAttendanceService tLpAttendanceService;

    @RequiresPermissions("shareproject:attendance:view")
    @GetMapping()
    public String attendance()
    {
        return prefix + "/attendance";
    }

    /**
     * 查询【出勤信息管理】列表
     */
    @RequiresPermissions("shareproject:attendance:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLpAttendance tLpAttendance)
    {
        startPage();
        List<TLpAttendance> list = tLpAttendanceService.selectTLpAttendanceList(tLpAttendance);
        return getDataTable(list);
    }

    /**
     * 导出【出勤信息管理】列表
     */
    @RequiresPermissions("shareproject:attendance:export")
    @Log(title = "【出勤信息管理】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLpAttendance tLpAttendance)
    {
        List<TLpAttendance> list = tLpAttendanceService.selectTLpAttendanceList(tLpAttendance);
        transferTLpProjectInformation(list);
        ExcelUtil<TLpAttendance> util = new ExcelUtil<TLpAttendance>(TLpAttendance.class);
        return util.exportExcel(list, "attendance");
    }
    private void transferTLpProjectInformation(List<TLpAttendance> list){
        if(null != list && !list.isEmpty()){
            for(int i=0;i<list.size();i++){
                TLpAttendance te = list.get(i);
                te.setId((long)(i+1));
            }
        }
    }
    /**
     * 新增【出勤信息管理】
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存【出勤信息管理】
     */
    @RequiresPermissions("shareproject:attendance:add")
    @Log(title = "【出勤信息管理】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLpAttendance tLpAttendance)
    {
        try{
            tLpAttendance.setCreateBy(ShiroUtils.getLoginName());
            tLpAttendance.setCreateTime(new Date());
            tLpAttendance.setUpdateBy(ShiroUtils.getLoginName());
            tLpAttendance.setUpdateTime(new Date());
            tLpAttendance.setOrgId(ShiroUtils.getSysUser().getOrgId());
            tLpAttendance.setOrgName(ShiroUtils.getSysUser().getOrgName());
            return toAjax(tLpAttendanceService.insertTLpAttendance(tLpAttendance));
        }catch (Exception e) {
            if(e instanceof DuplicateKeyException){
                throw new DuplicateKeyException("项目名称和日期(不能重复)已经存在");
            }else{
                throw e;
            }
        }
    }

    /**
     * 修改【出勤信息管理】
     */
    @RequiresPermissions("shareproject:attendance:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TLpAttendance tLpAttendance = tLpAttendanceService.selectTLpAttendanceById(id);
        mmap.put("tLpAttendance", tLpAttendance);
        return prefix + "/edit";
    }

    /**
     * 修改保存【出勤信息管理】
     */
    @RequiresPermissions("shareproject:attendance:edit")
    @Log(title = "【出勤信息管理】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLpAttendance tLpAttendance)
    {
        try {
            tLpAttendance.setUpdateBy(ShiroUtils.getLoginName());
            tLpAttendance.setUpdateTime(new Date());
            return toAjax(tLpAttendanceService.updateTLpAttendance(tLpAttendance));
        }catch (Exception e) {
            if(e instanceof DuplicateKeyException){
                throw new DuplicateKeyException("项目名称和日期(不能重复)已经存在");
            }else{
                throw e;
            }
        }
    }

    /**
     * 删除【出勤信息管理】
     */
    @RequiresPermissions("shareproject:attendance:remove")
    @Log(title = "【出勤信息管理】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tLpAttendanceService.deleteTLpAttendanceByIds(ids));
    }
}
