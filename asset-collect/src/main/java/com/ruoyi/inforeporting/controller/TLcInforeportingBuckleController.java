package com.ruoyi.inforeporting.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.enums.IsNoEnum;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.inforeporting.domain.TLcInforeportingBuckle;
import com.ruoyi.inforeporting.domain.TLcInforeportingReduction;
import com.ruoyi.inforeporting.service.TLcInforeportingBuckleService;
import com.ruoyi.inforeporting.service.TLcInforeportingReductionService;
import com.ruoyi.report.domain.TLcReportRecovery;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.task.domain.TLcTask;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
/**
 * 信息上报逾期划扣Controller
 *
 * @author gaohg
 * @date 2020-08-13
 */
@Slf4j
@Controller
@RequestMapping("/inforeporting/buckle")
public class TLcInforeportingBuckleController extends BaseController {
    private String prefix = "infoReporting/buckle";

    @Autowired
    private TLcInforeportingReductionService inforeportingReductionService;

    @Autowired
    private TLcInforeportingBuckleService inforeportingBuckleService;

    @Autowired
    private ISysUserService userService;

    /**
     * 信息上报逾期划扣查询
     */
    @GetMapping("/list")
    public String list() {
        return prefix + "/list";
    }

    /**
     * 信息上报逾期划扣数据集合
     */
    @PostMapping("/list")
    @RequiresPermissions("inforeporting:buckle:list")
    @ResponseBody
    public TableDataInfo list(TLcInforeportingBuckle inforeportingBuckle)
    {
        startPage();
        inforeportingBuckle.setCreateBy(ShiroUtils.getLoginName());
        inforeportingBuckle.setOrgId(ShiroUtils.getSysUser().getOrgId());
        List<TLcInforeportingBuckle> list = inforeportingBuckleService.selectTLcInforeportingBuckleList(inforeportingBuckle);
        return getDataTable(list);
    }

    /**
     * 信息上报划扣报表查询
     */
    @GetMapping("/listexp")
    public String listexp(ModelMap modelMap) {
        modelMap.put("isGroup", IsNoEnum.NO.getCode());
        return prefix + "/listexp";
    }

    /**
     * 信息上报减免数据集合
     */
    @PostMapping("/listexpData")
    @RequiresPermissions("inforeporting:buckle:listexpData")
    @ResponseBody
    public TableDataInfo listexpData(TLcInforeportingBuckle tLcInforeportingBuckle)
    {
        //组内案件
        if(tLcInforeportingBuckle.getIsGroup() != null && IsNoEnum.IS.getCode().equals(tLcInforeportingBuckle.getIsGroup())){
            List<String> userNames = userService.selectUserIdsSameGroup(ShiroUtils.getUserId());
            tLcInforeportingBuckle.setUserNames(userNames);
        }
        startPage();
        tLcInforeportingBuckle.setOrgId(ShiroUtils.getSysUser().getOrgId());
        List<TLcInforeportingBuckle> list = inforeportingBuckleService.selectTLcInforeportingBuckleList(tLcInforeportingBuckle);
        return getDataTable(list);
    }

    /**
     * 导出逾期划扣列表
     */
    @RequiresPermissions("inforeporting:buckle:export")
    @Log(title = "逾期划扣", businessType = BusinessType.EXPORT)
    @PostMapping(value = "/export",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public AjaxResult export(TLcInforeportingBuckle tLcInforeportingBuckle, HttpServletResponse response) {
        tLcInforeportingBuckle.setOrgId(ShiroUtils.getSysUser().getOrgId());
        return inforeportingBuckleService.exportExcel(tLcInforeportingBuckle);
    }

    /**
     * 新增【逾期划扣】
     */
    @GetMapping("/add")
    public String add(String caseNo, String orgId, String importBatchNo, ModelMap mmap) {
        TLcTask tk = inforeportingReductionService.selectTLcTaskByCaseNo(caseNo,orgId,importBatchNo);
        mmap.put("tk",tk);
        return prefix + "/add";
    }

    /**
     * 新增保存【逾期划扣】
     */
    @Log(title = "【上报信息】", businessType = BusinessType.INSERT)
    @RequiresPermissions("inforeporting:buckle:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated TLcInforeportingBuckle inforeportingBuckle) {
        inforeportingBuckle.setCreateBy(ShiroUtils.getLoginName());
        inforeportingBuckle.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(inforeportingBuckleService.insertTLcInforeportingBuckle(inforeportingBuckle));
    }

    @Log(title = "上报信息逾期划扣驳回", businessType = BusinessType.FORCE)
    @RequiresPermissions("inforeporting:buckle:reject")
    @PostMapping("/reject")
    @ResponseBody
    public AjaxResult reject(String ids)
    {
        return toAjax(inforeportingBuckleService.rejectTLcInforeportingBuckleByIds(ids));
    }

    /**
     * 跳转到组内划扣导出
     * @param modelMap
     * @return
     */
    @RequiresPermissions("inforeporting:buckle:group:listexp")
    @GetMapping(value = "/group/listexp")
    public String groupListxp(ModelMap modelMap) {
        // 是否组内查询：是
        modelMap.put("isGroup", IsNoEnum.IS.getCode());
        return prefix + "/listexp";
    }
}
