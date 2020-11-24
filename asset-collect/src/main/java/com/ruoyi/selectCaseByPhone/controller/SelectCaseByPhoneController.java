package com.ruoyi.selectCaseByPhone.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.framework.web.domain.server.Sys;
import com.ruoyi.selectCaseByPhone.domain.SelectCaseByPhone;
import com.ruoyi.selectCaseByPhone.service.ISelectCaseByPhoneService;
import com.ruoyi.task.domain.TLcTask;
import com.ruoyi.utils.DesensitizationUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 根据手机号查询案件Controller
 * 
 * @author guozeqi
 * @date 2020-11-16
 */
@Controller
@RequestMapping("/collect/selectCaseByPhone")
public class SelectCaseByPhoneController extends BaseController
{
    private String prefix = "selectCaseByPhone";

    @Autowired
    private ISelectCaseByPhoneService selectCaseByPhoneService;

    @Autowired
    private DesensitizationUtil desensitizationUtil;

    @RequiresPermissions("collect:selectCaseByPhone:view")
    @GetMapping()
    public String config(ModelMap modelMap)
    {
        //查询是否脱敏
        boolean desensitization = desensitizationUtil.isDesensitization(String.valueOf(ShiroUtils.getSysUser().getOrgId()), ShiroUtils.getLoginName());
        modelMap.put("desensitization", desensitization);
        modelMap.put("orgId", ShiroUtils.getSysUser().getOrgId());
        return prefix + "/selectCaseByPhone";
    }

    /**
     * 查根据手机号查询案件列表
     */
    @RequiresPermissions("collect:selectCaseByPhone:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SelectCaseByPhone selectCaseByPhone)
    {
        startPage();
        List<SelectCaseByPhone> list = selectCaseByPhoneService.selectCaseByPhoneList(selectCaseByPhone);
        return getDataTable(list);
    }


    @GetMapping(value = "/collJobHisDetail")
    public String toCollJobHis(TLcTask tLcTask, String currentImportBatchNo, String currentCaseNo, ModelMap modelMap, String callCodeHistoryListStr, String toType) {

        modelMap.put("tLcTask", tLcTask);
        modelMap.put("currentCaseNo", currentCaseNo);
        modelMap.put("currentImportBatchNo", currentImportBatchNo);
        modelMap.put("phone", tLcTask.getPhone());


        Map<String, BigDecimal> resultMap = this.selectCaseByPhoneService.selectTotalCountMoney(tLcTask);
        modelMap.put("totalCaseNum", resultMap.get("totalCaseNum"));
        modelMap.put("totalArrears", resultMap.get("totalArrears"));
        //查询是否脱敏
        boolean desensitization = desensitizationUtil.isDesensitization(String.valueOf(ShiroUtils.getSysUser().getOrgId()), ShiroUtils.getLoginName());
        modelMap.put("desensitization", desensitization);
        return prefix + "/collJobHisDetail";
    }

    @PostMapping("/findTaskByOwner")
    @ResponseBody
    public List<TLcTask> findTaskByOwner(TLcTask tLcTask,HttpServletRequest request) {
        logger.info("查询客户列表开始ownerId="+tLcTask.getOwnerId());
        List<TLcTask> list = selectCaseByPhoneService.selectMyTaskList(tLcTask);
        logger.info("查询客户列表结束ownerId="+tLcTask.getOwnerId());
        return list;
    }

    /**
     * 导出根据手机号查询案件列表
     */
    @RequiresPermissions("collect:selectCaseByPhone:export")
    @Log(title = "证件号码配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SelectCaseByPhone selectCaseByPhone)
    {
        List<SelectCaseByPhone> list = selectCaseByPhoneService.selectCaseByPhoneList(selectCaseByPhone);
        ExcelUtil<SelectCaseByPhone> util = new ExcelUtil<SelectCaseByPhone>(SelectCaseByPhone.class);
        return util.exportExcel(list, "SelectCaseByPhone");
    }




}
