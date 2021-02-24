package com.ruoyi.task.controller;

import java.math.BigInteger;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.task.domain.TLcTaskInfoup;
import com.ruoyi.task.service.ITLcTaskInfoupService;
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
 * 信息更新Controller
 * 
 * @author gaohg
 * @date 2021-02-04
 */
@Controller
@RequestMapping("/task/infoup")
public class TLcTaskInfoupController extends BaseController
{
    private String prefix = "task/infoup";

    @Autowired
    private ITLcTaskInfoupService tLcTaskInfoupService;

    /**
     * 信息更新 审批
     * @return
     */
    @RequiresPermissions("taskinfoup:infoup:view")
    @GetMapping()
    public String infoup()
    {
        return prefix + "/list";
    }

    /**
     * 更新岗 更新信息
     * @return
     */
    @RequiresPermissions("taskinfoup:infoup:listGx")
    @GetMapping("/infoupGx")
    public String infoupGx()
    {
        return prefix + "/listGx";
    }

    /**
     * 信息更新查询
     * @return
     */
    @RequiresPermissions("taskinfoup:infoup:listGxSer")
    @GetMapping("/infoupGxSer")
    public String infoupGxSer()
    {
        return prefix + "/listGxSer";
    }
    /**
     * 查询信息更新列表
     */
    @RequiresPermissions("taskinfoup:infoup:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcTaskInfoup tLcTaskInfoup)
    {
        startPage();
        tLcTaskInfoup.setOrgId(ShiroUtils.getSysUser().getOrgId().toString());
        List<TLcTaskInfoup> list = tLcTaskInfoupService.selectTLcTaskInfoupList(tLcTaskInfoup);
        return getDataTable(list);
    }

    /**
     * 查询信息更新列表-信息更新岗
     */
    @RequiresPermissions("taskinfoup:infoup:listGx")
    @PostMapping("/listGx")
    @ResponseBody
    public TableDataInfo listGx(TLcTaskInfoup tLcTaskInfoup)
    {
        startPage();
        tLcTaskInfoup.setInfoupAproStatus(1);
        List<TLcTaskInfoup> list = tLcTaskInfoupService.selectTLcTaskInfoupList(tLcTaskInfoup);
        return getDataTable(list);
    }

    @RequiresPermissions("taskinfoup:infoup:listGxSer")
    @PostMapping("/listGxSer")
    @ResponseBody
    public TableDataInfo listGxSer(TLcTaskInfoup tLcTaskInfoup)
    {
        startPage();
        tLcTaskInfoup.setInfoupAproStatus(1);
        tLcTaskInfoup.setOrgId(ShiroUtils.getSysUser().getOrgId().toString());
        List<TLcTaskInfoup> list = tLcTaskInfoupService.selectTLcTaskInfoupList(tLcTaskInfoup);
        return getDataTable(list);
    }

    /**
     * 导出信息更新列表
     */
    @Log(title = "信息更新", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcTaskInfoup tLcTaskInfoup)
    {
        tLcTaskInfoup.setInfoupAproStatus(1);
        tLcTaskInfoup.setOrgId(ShiroUtils.getSysUser().getOrgId().toString());
        List<TLcTaskInfoup> list = tLcTaskInfoupService.selectTLcTaskInfoupListExp(tLcTaskInfoup);
        ExcelUtil<TLcTaskInfoup> util = new ExcelUtil<TLcTaskInfoup>(TLcTaskInfoup.class);
        return util.exportExcel(list, "infoup");
    }

    /**
     * 新增信息更新
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存信息更新
     */
    @RequiresPermissions("taskinfoup:infoup:add")
    @Log(title = "信息更新", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLcTaskInfoup tLcTaskInfoup)
    {
        return toAjax(tLcTaskInfoupService.insertTLcTaskInfoup(tLcTaskInfoup));
    }

    /**
     * 批量更新 信息更新状态
     * @param ids
     * @return
     */
    @PostMapping("/infoupStatusSub")
    @ResponseBody
    public AjaxResult infoupStatusSub(BigInteger[] ids) {
       return toAjax(tLcTaskInfoupService.insertBatchTLcTaskInfoup(ids));
    }

    @GetMapping("/lookups/{id}")
    public String lookups(@PathVariable("id") Long id, ModelMap mmap)
    {
        TLcTaskInfoup tLcTaskInfoup = tLcTaskInfoupService.selectTLcTaskInfoupById(id);
        mmap.put("tLcTaskInfoup", tLcTaskInfoup);
        return prefix + "/lookups";
    }

    @GetMapping("/lookupser/{id}")
    public String lookupser(@PathVariable("id") Long id, ModelMap mmap)
    {
        TLcTaskInfoup tLcTaskInfoup = tLcTaskInfoupService.selectTLcTaskInfoupById(id);
        mmap.put("tLcTaskInfoup", tLcTaskInfoup);
        return prefix + "/lookupser";
    }

    @RequiresPermissions("taskinfoup:infoup:updateStatus")
    @Log(title = "信息审批更新", businessType = BusinessType.UPDATE)
    @PostMapping("/updateStatus")
    @ResponseBody
    public AjaxResult updateStatus(TLcTaskInfoup tLcTaskInfoup)
    {
        tLcTaskInfoup.setModifyBy(ShiroUtils.getSysUser().getLoginName());
        tLcTaskInfoup.setModifyTime(DateUtils.getNowDate());
        return toAjax(tLcTaskInfoupService.updateStatus(tLcTaskInfoup));
    }


    @RequiresPermissions("taskinfoup:infoup:updateStatusGx")
    @Log(title = "信息更新状态", businessType = BusinessType.UPDATE)
    @PostMapping("/updateStatusGx")
    @ResponseBody
    public AjaxResult updateStatusGx(TLcTaskInfoup tLcTaskInfoup)
    {
        tLcTaskInfoup.setInfoupBy(ShiroUtils.getSysUser().getLoginName());
        tLcTaskInfoup.setInfoupTime(DateUtils.getNowDate());
        return toAjax(tLcTaskInfoupService.updateStatusGx(tLcTaskInfoup));
    }
}
