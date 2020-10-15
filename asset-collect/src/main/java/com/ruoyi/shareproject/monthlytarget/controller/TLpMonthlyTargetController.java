package com.ruoyi.shareproject.monthlytarget.controller;

import java.util.Date;
import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.shareproject.monthlytarget.domain.TLpMonthlyTarget;
import com.ruoyi.shareproject.monthlytarget.service.ITLpMonthlyTargetService;
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
 * 【月度指标】Controller
 * 
 * @author gaohg
 * @date 2020-10-15
 */
@Controller
@RequestMapping("/shareproject/monthlytarget")
public class TLpMonthlyTargetController extends BaseController
{
    private String prefix = "shareproject/monthlytarget";

    @Autowired
    private ITLpMonthlyTargetService tLpMonthlyTargetService;

    @RequiresPermissions("shareproject:monthlytarget:view")
    @GetMapping()
    public String target()
    {
        return prefix + "/monthlytarget";
    }

    /**
     * 查询【月度指标】列表
     */
    @RequiresPermissions("shareproject:monthlytarget:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLpMonthlyTarget tLpMonthlyTarget)
    {
        startPage();
        List<TLpMonthlyTarget> list = tLpMonthlyTargetService.selectTLpMonthlyTargetList(tLpMonthlyTarget);
        return getDataTable(list);
    }

    /**
     * 导出【月度指标】列表
     */
    @RequiresPermissions("shareproject:monthlytarget:export")
    @Log(title = "【月度指标】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLpMonthlyTarget tLpMonthlyTarget)
    {
        List<TLpMonthlyTarget> list = tLpMonthlyTargetService.selectTLpMonthlyTargetList(tLpMonthlyTarget);
        ExcelUtil<TLpMonthlyTarget> util = new ExcelUtil<TLpMonthlyTarget>(TLpMonthlyTarget.class);
        return util.exportExcel(list, "target");
    }

    /**
     * 新增【月度指标】
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存【月度指标】
     */
    @RequiresPermissions("shareproject:monthlytarget:add")
    @Log(title = "【月度指标】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLpMonthlyTarget tLpMonthlyTarget)
    {
        try{
            tLpMonthlyTarget.setCreateBy(ShiroUtils.getLoginName());
            tLpMonthlyTarget.setCreateTime(new Date());
            tLpMonthlyTarget.setUpdateBy(ShiroUtils.getLoginName());
            tLpMonthlyTarget.setUpdateTime(new Date());
            tLpMonthlyTarget.setOrgId(ShiroUtils.getSysUser().getOrgId());
            tLpMonthlyTarget.setOrgName(ShiroUtils.getSysUser().getOrgName());
            return toAjax(tLpMonthlyTargetService.insertTLpMonthlyTarget(tLpMonthlyTarget));
        }catch (Exception e) {
            if(e instanceof DuplicateKeyException){
                throw new DuplicateKeyException("年、月和项目(不能重复)已经存在");
            }else{
                throw e;
            }
        }
    }

    /**
     * 修改【月度指标】
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TLpMonthlyTarget tLpMonthlyTarget = tLpMonthlyTargetService.selectTLpMonthlyTargetById(id);
        mmap.put("tLpMonthlyTarget", tLpMonthlyTarget);
        return prefix + "/edit";
    }

    /**
     * 复制新增【月度指标】
     */
    @GetMapping("/copyone/{id}")
    public String copyone(@PathVariable("id") Long id, ModelMap mmap)
    {
        TLpMonthlyTarget tLpMonthlyTarget = tLpMonthlyTargetService.selectTLpMonthlyTargetById(id);
        mmap.put("tLpMonthlyTarget", tLpMonthlyTarget);
        return prefix + "/copyone";
    }

    /**
     * 修改保存【月度指标】
     */
    @RequiresPermissions("shareproject:monthlytarget:edit")
    @Log(title = "【月度指标】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLpMonthlyTarget tLpMonthlyTarget)
    {
        try{
            tLpMonthlyTarget.setUpdateBy(ShiroUtils.getLoginName());
            tLpMonthlyTarget.setUpdateTime(new Date());
            return toAjax(tLpMonthlyTargetService.updateTLpMonthlyTarget(tLpMonthlyTarget));
        }catch (Exception e) {
            if(e instanceof DuplicateKeyException){
                throw new DuplicateKeyException("年、月和项目(不能重复)已经存在");
            }else{
                throw e;
            }
        }

    }

    /**
     * 删除【月度指标】
     */
    @RequiresPermissions("shareproject:monthlytarget:remove")
    @Log(title = "【月度指标】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tLpMonthlyTargetService.deleteTLpMonthlyTargetByIds(ids));
    }
}
