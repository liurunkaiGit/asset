package com.ruoyi.duncase.controller;

import java.util.List;

import com.ruoyi.duncase.domain.TLcDuncaseAssign;
import com.ruoyi.duncase.service.ITLcDuncaseAssignService;
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
 * 案件轨迹Controller
 * 
 * @author ruoyi
 * @date 2019-12-27
 */
@Controller
@RequestMapping("/system/assign")
public class TLcDuncaseAssignController extends BaseController
{
    private String prefix = "system/assign";

    @Autowired
    private ITLcDuncaseAssignService tLcDuncaseAssignService;

    @RequiresPermissions("system:assign:view")
    @GetMapping()
    public String assign()
    {
        return prefix + "/assign";
    }

    /**
     * 查询案件轨迹列表
     */
    @RequiresPermissions("system:assign:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcDuncaseAssign tLcDuncaseAssign)
    {
        startPage();
        List<TLcDuncaseAssign> list = tLcDuncaseAssignService.selectTLcDuncaseAssignList(tLcDuncaseAssign);
        return getDataTable(list);
    }

    /**
     * 导出案件轨迹列表
     */
    @RequiresPermissions("system:assign:export")
    @Log(title = "案件轨迹", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcDuncaseAssign tLcDuncaseAssign)
    {
        List<TLcDuncaseAssign> list = tLcDuncaseAssignService.selectTLcDuncaseAssignList(tLcDuncaseAssign);
        ExcelUtil<TLcDuncaseAssign> util = new ExcelUtil<TLcDuncaseAssign>(TLcDuncaseAssign.class);
        return util.exportExcel(list, "assign");
    }

    /**
     * 新增案件轨迹
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存案件轨迹
     */
    @RequiresPermissions("system:assign:add")
    @Log(title = "案件轨迹", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLcDuncaseAssign tLcDuncaseAssign)
    {
        return toAjax(tLcDuncaseAssignService.insertTLcDuncaseAssign(tLcDuncaseAssign));
    }

    /**
     * 修改案件轨迹
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TLcDuncaseAssign tLcDuncaseAssign = tLcDuncaseAssignService.selectTLcDuncaseAssignById(id);
        mmap.put("tLcDuncaseAssign", tLcDuncaseAssign);
        return prefix + "/edit";
    }

    /**
     * 修改保存案件轨迹
     */
    @RequiresPermissions("system:assign:edit")
    @Log(title = "案件轨迹", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLcDuncaseAssign tLcDuncaseAssign)
    {
        return toAjax(tLcDuncaseAssignService.updateTLcDuncaseAssign(tLcDuncaseAssign));
    }

    /**
     * 删除案件轨迹
     */
    @RequiresPermissions("system:assign:remove")
    @Log(title = "案件轨迹", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tLcDuncaseAssignService.deleteTLcDuncaseAssignByIds(ids));
    }
}
