package com.ruoyi.shareproject.daily.controller;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.shareproject.daily.domain.*;
import com.ruoyi.shareproject.daily.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
 * 【项目日报】Controller
 * 
 * @author gaohg
 * @date 2020-10-26
 */
@Controller
@RequestMapping("/shareproject/daily")
public class TLpProjectDailyController extends BaseController
{
    private String prefix = "shareproject/daily";

    @Autowired
    private ITLpProjectDailyService tLpProjectDailyService;
    @Autowired
    private ITLpProjectInformationDailyService tLpProjectInformationDailyService;
    @Autowired
    private ITLpAttendanceDailyService tLpAttendanceDailyService;
    @Autowired
    private ITLpMonthlyTargetDailyService tLpMonthlyTargetDailyService;
    @Autowired
    private ITLpResultDailyService tLpResultDailyService;
    @Autowired
    private ITLpProcessDailyService tLpProcessDailyService;

    @RequiresPermissions("shareproject:daily:view")
    @GetMapping()
    public String daily()
    {
        return prefix + "/daily";
    }

    /**
     * 查询【项目日报】列表
     */
    @RequiresPermissions("shareproject:daily:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLpProjectDaily tLpProjectDaily)
    {
        startPage();
        List<TLpProjectDaily> list = tLpProjectDailyService.selectTLpProjectDailyList(tLpProjectDaily);
        return getDataTable(list);
    }

    /**
     * 查询【项目日报-项目信息】列表
     */
    @PostMapping("/listproinfo/{dailyId}")
    @ResponseBody
    public List<TLpProjectInformationDaily> listxm(TLpProjectInformationDaily lpProjectInformationDaily)
    {
        List<TLpProjectInformationDaily> list = tLpProjectInformationDailyService.selectTLpProjectInformationDailyList(lpProjectInformationDaily);
        return list;
    }

    /**
     * 查询【项目日报-月度指标】列表
     */
    @PostMapping("/listmonth/{dailyId}")
    @ResponseBody
    public List<TLpMonthlyTargetDaily> listmonth(TLpMonthlyTargetDaily tLpMonthlyTargetDaily)
    {
        List<TLpMonthlyTargetDaily> list = tLpMonthlyTargetDailyService.selectTLpMonthlyTargetDailyList(tLpMonthlyTargetDaily);
        return list;
    }

    /**
     * 查询【项目日报-结果指标】列表
     */
    @PostMapping("/listjieguo/{dailyId}")
    @ResponseBody
    public List<TLpResultDaily> listjieguo(TLpResultDaily tLpResultDaily)
    {
        List<TLpResultDaily> list = tLpResultDailyService.selectTLpResultDailyList(tLpResultDaily);
        return list;
    }

    /**
     * 查询【项目日报-过程指标】列表
     */
    @PostMapping("/listguocheng/{dailyId}")
    @ResponseBody
    public List<TLpProcessDaily> listguocheng(TLpProcessDaily tpProcessDaily)
    {
        List<TLpProcessDaily> list = tLpProcessDailyService.selectTLpProcessDailyList(tpProcessDaily);
        return list;
    }

    /**
     * 查询【项目日报-出勤信息】列表
     */
    @PostMapping("/listattendanceDaily/{dailyId}")
    @ResponseBody
    public List<TLpAttendanceDaily> listAttendanceDaily(TLpAttendanceDaily tLpAttendanceDaily)
    {
        List<TLpAttendanceDaily> list = tLpAttendanceDailyService.selectTLpAttendanceDailyList(tLpAttendanceDaily);
        return list;
    }

    /**
     * 导出【项目日报】列表
     */
    @RequiresPermissions("shareproject:daily:export")
    @Log(title = "【项目日报】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLpProjectDaily tLpProjectDaily)
    {
        List<TLpProjectDaily> list = tLpProjectDailyService.selectTLpProjectDailyList(tLpProjectDaily);
        ExcelUtil<TLpProjectDaily> util = new ExcelUtil<TLpProjectDaily>(TLpProjectDaily.class);
        return util.exportExcel(list, "daily");
    }

    /**
     * 新增【项目日报】
     */
    @GetMapping("/add")
    public String add( ModelMap mmap)
    {
        mmap.put("tdate",DateUtils.parseDateToStr("yyyy-MM-dd",new Date()));
        return prefix + "/add";
    }

    @GetMapping("/lookups/{id}")
    public String lookups(@PathVariable("id") Long id, ModelMap mmap)
    {
        TLpProjectDaily ty = tLpProjectDailyService.selectTLpProjectDailyById(id);
        mmap.put("ty", ty);
        return prefix + "/lookups";
    }

    /**
     * 新增保存【项目日报】
     */
    @RequiresPermissions("shareproject:daily:add")
    @Log(title = "【项目日报】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLpProjectDaily tLpProjectDaily)
    {
        try{
            tLpProjectDaily.setCreateBy(ShiroUtils.getLoginName());
            tLpProjectDaily.setCreateTime(new Date());
            tLpProjectDaily.setUpdateBy(ShiroUtils.getLoginName());
            tLpProjectDaily.setUpdateTime(new Date());
            tLpProjectDaily.setOrgId(ShiroUtils.getSysUser().getOrgId());
            tLpProjectDaily.setOrgName(ShiroUtils.getSysUser().getOrgName());
            return toAjax(tLpProjectDailyService.insertTLpProjectDaily(tLpProjectDaily));
        }catch (Exception e) {
            if(e instanceof DuplicateKeyException){
                throw new DuplicateKeyException("项目名称和日期(不能重复)已经存在");
            }else{
                throw e;
            }
        }
    }

    /**
     * 修改【项目日报】
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TLpProjectDaily tLpProjectDaily = tLpProjectDailyService.selectTLpProjectDailyById(id);
        mmap.put("tLpProjectDaily", tLpProjectDaily);
        return prefix + "/edit";
    }

    /**
     * 修改【项目日报】
     */
    @PostMapping("/findsone")
    @ResponseBody
    public TLpProjectDaily finds(TLpProjectDaily tLpProjectDaily)
    {
        List<TLpProjectDaily> lt = tLpProjectDailyService.selectTLpProjectDailyList(tLpProjectDaily);
        if(null == lt || lt.isEmpty())return new TLpProjectDaily();
        return lt.get(0);
    }
    /**
     * 修改保存【项目日报】
     */
    @RequiresPermissions("shareproject:daily:edit")
    @Log(title = "【项目日报】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLpProjectDaily tLpProjectDaily)
    {
        try{
            tLpProjectDaily.setUpdateBy(ShiroUtils.getLoginName());
            tLpProjectDaily.setUpdateTime(new Date());
            return toAjax(tLpProjectDailyService.updateTLpProjectDaily(tLpProjectDaily));
        }catch (Exception e) {
            if(e instanceof DuplicateKeyException){
                throw new DuplicateKeyException("项目名称和日期(不能重复)已经存在");
            }else{
                throw e;
            }
        }
    }

    @RequiresPermissions("shareproject:daily:zhibiao")
    @Log(title = "【项目日报】", businessType = BusinessType.UPDATE)
    @PostMapping("/zhibiao")
    @ResponseBody
    public int zhibiao(TLpProjectDaily tLpProjectDaily)
    {
        return tLpProjectDailyService.findZhibiao(tLpProjectDaily.getProId(),tLpProjectDaily.getDailyTime());
    }



    /**
     * 删除【项目日报】
     */
    @RequiresPermissions("shareproject:daily:remove")
    @Log(title = "【项目日报】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tLpProjectDailyService.deleteTLpProjectDailyByIds(ids));
    }
}
