package com.ruoyi.shareproject.information.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.custom.domain.TLcCustContact;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.framework.web.domain.server.Sys;
import com.ruoyi.letter.domain.TLcLetter;
import com.ruoyi.shareproject.information.domain.TLpInformationCenter;
import com.ruoyi.shareproject.information.service.CenterInformationService;
import com.ruoyi.system.domain.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 共享信息管理-基础信息管理-中心信息
 *
 * @auther 麻超
 *
 * @date 2020-10-12
 */
@Controller
@RequestMapping("/information/center")
public class CenterInformationController extends BaseController {
    private String prefix = "shareproject/information/center";

    @Autowired
    private CenterInformationService centerInformationService;

    /**
     * 中心信息查询
     */
    @GetMapping()
    @RequiresPermissions("information:center:view")
    public String list() {
        return prefix + "/list";
    }

    /**
     * 中心信息查询数据集合
     */
    @PostMapping("/list")
    @RequiresPermissions("information:center:list")
    @ResponseBody
    public TableDataInfo list(TLpInformationCenter informationCenter) {
        startPage();
        //informationCenter.setCreateBy(ShiroUtils.getLoginName());
        List<TLpInformationCenter> list = centerInformationService.selectCenterInformationList(informationCenter);
        setSeatRate(list);
        return getDataTable(list);
    }
    /**
     * 中心信息查询数据集合 下拉
     */
    @PostMapping("/listxl")
    @RequiresPermissions("information:center:list")
    @ResponseBody
    public List<TLpInformationCenter> listXL(TLpInformationCenter informationCenter) {
        List<TLpInformationCenter> list = centerInformationService.selectCenterInformationList(informationCenter);
        setSeatRate(list);
        return list;
    }

    /**
     * 新增中心信息数据
     */
    @GetMapping("/add")
    public String add() {
       return prefix + "/add";
    }

    /**
     * 新增中心信息数据
     */
    @RequiresPermissions("information:center:add")
    @Log(title = "中心信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLpInformationCenter informationCenter) {
        return toAjax(centerInformationService.insertCenterInformation(informationCenter));
    }

    /**
     * 中心名称是否唯一
     */
    @PostMapping("/checkCenterNameUnique")
    @ResponseBody
    public String checkLoginNameUnique(TLpInformationCenter informationCenter) {
        return String.valueOf(centerInformationService.checkCenterNameUnique(informationCenter.getCenterName(),informationCenter.getId()));
    }

    /**
     * 根据ID查询中心信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap modelMap) {
        TLpInformationCenter tLpInformationCenter = centerInformationService.selectCenterInformationById(id);
        setSeatRate(tLpInformationCenter);
        modelMap.put("centerInformationEntry", tLpInformationCenter);
        return prefix + "/edit";
    }

    /**
     * 修改中心信息
     */
    @RequiresPermissions("information:center:edit")
    @Log(title = "中心信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLpInformationCenter informationCenter) {
        return toAjax(centerInformationService.updateInformationCenter(informationCenter));
    }




    /**
     * 导出中心信息列表
     */
    @RequiresPermissions("information:center:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLpInformationCenter informationCenter) {
        List<TLpInformationCenter> list;
        if (StringUtils.isNotBlank(informationCenter.getCenterIds())) {
            list = centerInformationService.selectCenterInformationByIds(informationCenter.getCenterIds());
        } else {
            list = centerInformationService.selectCenterInformationList(informationCenter);
        }
        setSeatRate(list);
        ExcelUtil<TLpInformationCenter> util = new ExcelUtil<>(TLpInformationCenter.class);
        return util.exportExcel(list, "中心信息");
    }

    /**
     * 删除中心信息
     */
    @RequiresPermissions("information:center:export")
    @Log(title = "中心信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(centerInformationService.deleteCenterInformation(ids));
    }


    /**
     * 设置满席率
     * @param informationCenters
     */
    private void setSeatRate(List<TLpInformationCenter> informationCenters){
        informationCenters.stream().forEach(s -> setSeatRate(s) );
    }

    /**
     * 设置满席率
     * @param informationCenter
     */
    private void setSeatRate(TLpInformationCenter informationCenter){
            Double agentNum = Double.valueOf(informationCenter.getAgentNum());
            Double workSeatNum =Double.valueOf(informationCenter.getWorkSeatNum());
            if(agentNum == 0 && workSeatNum == 0){
                informationCenter.setSeatRate("100%");
            }else{
                informationCenter.setSeatRate(String.format("%.2f", agentNum/workSeatNum*100)+"%");
            }
    }

}
