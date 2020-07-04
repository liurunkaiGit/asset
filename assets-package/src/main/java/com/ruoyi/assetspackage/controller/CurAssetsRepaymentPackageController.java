package com.ruoyi.assetspackage.controller;

import com.alibaba.fastjson.JSON;
import com.ruoyi.assetspackage.domain.*;
import com.ruoyi.assetspackage.enums.ImportTypeEnum;
import com.ruoyi.assetspackage.enums.IsCloseCaseEnum;
import com.ruoyi.assetspackage.enums.IsNoEnum;
import com.ruoyi.assetspackage.exception.ImportDataExcepion;
import com.ruoyi.assetspackage.service.*;
import com.ruoyi.assetspackage.util.DataImportUtil;
import com.ruoyi.assetspackage.util.ParseExcelUtil;
import com.ruoyi.assetspackage.util.RepaymentDataImportUtil;
import com.ruoyi.assetspackage.util.Response;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.domain.CloseCase;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.RestTemplateUtil;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.DataPermissionUtils;
import com.ruoyi.framework.util.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * 资产还款Controller
 *
 * @author guozeqi
 * @date 2020-01-13
 */
@Slf4j
@Controller
@RequestMapping("/assetspackage/repaymentImport")
public class CurAssetsRepaymentPackageController extends BaseController {
    private String prefix = "assetspackage/repaymentImport";

    @Autowired
    private RepaymentImportDataMapping repaymentImportDataMapping;
    @Autowired
    private RemoteConfigure remoteConfigure;
    @Autowired
    private ICurAssetsRepaymentPackageService curAssetsRepaymentPackageService;
    @Autowired
    private ICurAssetsPackageService curAssetsPackageService;
    @Autowired
    private IOrgPackageService orgPackageService;
    @Autowired
    private IRepaymentTemplatesPackageService repaymentTemplatesPackageService;
    @Autowired
    private IRepaymentTemplateRelationPackageService repaymentTemplateRelationPackageService;
    @Autowired
    private ITemplatesPackageService templatesPackageService;
    @Autowired
    private ITemplateRelationPackageService templateRelationPackageService;
    @Autowired
    private ITLcImportFlowService tlcImportFlowService;
    @Autowired
    private RestTemplateUtil restTemplateUtil;
    @Autowired
    private DataPermissionUtils dataPermissionUtils;

    @RequiresPermissions("assetspackage:repaymentImport:view")
    @GetMapping()
    public String repaymentImport() {
        return prefix + "/repaymentImportView";
    }

    @GetMapping("toViewImportDetail")
    public String toViewImportDetail(String importBatchNo, ModelMap modelMap) {
        modelMap.put("importBatchNo", importBatchNo);
        return prefix + "/importDetail";
    }


    @GetMapping("toStatistics/{importBatchNo}")
    public String toStatistics(@PathVariable("importBatchNo") String importBatchNo, ModelMap modelMap) {
        modelMap.put("importBatchNo",importBatchNo);
        return prefix + "/statistics";
    }


    /**
     * 查询资产还款列表
     */
    @RequiresPermissions("assetspackage:repaymentImport:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CurAssetsRepaymentPackage curAssetsRepaymentPackage) {
        startPage();
        //如果参数有委托方则用参数的，如果没有则用当前用户的
//        if (curAssetsRepaymentPackage.getOrgId() == null || "".equals(curAssetsRepaymentPackage.getOrgId()) || "请选择".equals(curAssetsRepaymentPackage.getOrgId())) {
//            // 根据登录用户id获取机构id
//            String orgid = orgPackageService.selectOrgIdByUserId(ShiroUtils.getUserId());
//            curAssetsRepaymentPackage.setOrgId(orgid);
//        }
        Set<Long> deptIds = dataPermissionUtils.selectDataPer();
        curAssetsRepaymentPackage.setDeptIds(deptIds);
        List<CurAssetsRepaymentPackage> list = curAssetsRepaymentPackageService.selectCurAssetsRepaymentPackageList(curAssetsRepaymentPackage);
//        for (CurAssetsRepaymentPackage assetsRepayment : list) {
//            String jazt = assetsRepayment.getJazt();
//            if (jazt != null && IsCloseCaseEnum.CLOSE_CASE.getValue().equals(jazt)) {
//                assetsRepayment.setJazt("已结案");
//            } else {
//                assetsRepayment.setJazt("未结案");
//            }
//        }
        return getDataTable(list);
    }


    /**
     * 查询当前用户拥有的数据权限与委托方的交集
     *
     * @param request
     * @return
     */
    @RequiresPermissions("assetspackage:repaymentImport:list")
    @PostMapping("/findOrgInfo")
    @ResponseBody
    public AjaxResult findOrgInfo(HttpServletRequest request) {
        List<Map<String, Long>> orgInfo = null;
        Long UserId = ShiroUtils.getUserId();
        String dataScope = orgPackageService.selectDataScopeByUserId(UserId);
        if ("1".equals(dataScope)) {
            orgInfo = orgPackageService.selectOrgInfoByUserId1(UserId);
        } else if ("2".equals(dataScope)) {
            orgInfo = orgPackageService.selectOrgInfoByUserId2(UserId);
        } else if ("4".equals(dataScope)) {
            orgInfo = orgPackageService.selectOrgInfoByUserId4(UserId);
        } else {
            orgInfo = orgPackageService.selectOrgInfoByUserId3(UserId);
        }
        String result = JSON.toJSONString(orgInfo);
        return AjaxResult.success(result);
    }


    /**
     * 导出资产还款列表
     */
    @RequiresPermissions("assetspackage:repaymentImport:export")
    @Log(title = "资产还款", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CurAssetsRepaymentPackage curAssetsRepaymentPackage) {
        List<CurAssetsRepaymentPackage> list = curAssetsRepaymentPackageService.selectCurAssetsRepaymentPackageList(curAssetsRepaymentPackage);
        ExcelUtil<CurAssetsRepaymentPackage> util = new ExcelUtil<CurAssetsRepaymentPackage>(CurAssetsRepaymentPackage.class);
        return util.exportExcel(list, "assetspackage");
    }

    /**
     * 新增资产还款
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存资产还款
     */
    @RequiresPermissions("assetspackage:repaymentImport:add")
    @Log(title = "资产还款", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CurAssetsRepaymentPackage curAssetsRepaymentPackage) {
        return toAjax(curAssetsRepaymentPackageService.insertCurAssetsRepaymentPackage(curAssetsRepaymentPackage));
    }

    /**
     * 修改资产还款
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        CurAssetsRepaymentPackage curAssetsRepaymentPackage = curAssetsRepaymentPackageService.selectCurAssetsRepaymentPackageById(id);
        mmap.put("curAssetsRepaymentPackage", curAssetsRepaymentPackage);
        return prefix + "/edit";
    }

    /**
     * 修改保存资产还款
     */
    @RequiresPermissions("assetspackage:repaymentImport:edit")
    @Log(title = "资产还款", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CurAssetsRepaymentPackage curAssetsRepaymentPackage) {
        return toAjax(curAssetsRepaymentPackageService.updateCurAssetsRepaymentPackage(curAssetsRepaymentPackage));
    }

    /**
     * 删除资产还款
     */
    @RequiresPermissions("assetspackage:repaymentImport:remove")
    @Log(title = "资产还款", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(curAssetsRepaymentPackageService.deleteCurAssetsRepaymentPackageByIds(ids));
    }

    /**
     * 查询模板
     *
     * @param request
     * @return
     */
    @RequiresPermissions("assetspackage:repaymentImport:import")
    @PostMapping("/findTemplate")
    @ResponseBody
    public AjaxResult findTemplate(HttpServletRequest request,String orgId) {
        orgId = String.valueOf(ShiroUtils.getSysUser().getOrgId());
        Map<String, String> map = new HashMap<String, String>();
        //查询所有的还款模板
        TemplatesPackage param = new TemplatesPackage();
        param.setType("2");//还款模板
        param.setOrgId(orgId);
        List<TemplatesPackage> templatesList = this.templatesPackageService.selectTemplatesPackageList(param);
        for (TemplatesPackage templateInfo : templatesList) {
            String id = templateInfo.getId();
            String name = templateInfo.getName();
            map.put(id, name);
        }

        String result = JSON.toJSONString(map);
        return AjaxResult.success(result);
    }

    /**
     * 删除临时表
     * @param importBatchNo
     * @return
     */
    @GetMapping("deleteTempTable/{importBatchNo}")
    @ResponseBody
    public AjaxResult deleteTempTable(@PathVariable("importBatchNo") String importBatchNo) {
        try {
            this.curAssetsRepaymentPackageService.deleteTempTable(importBatchNo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("清空临时表异常",e);
            return error("清空临时表异常");
        }
        return success();
    }

    @GetMapping("toStatisticsDetail/{toType}")
    public String toStatisticsDetail( @PathVariable("toType")String toType, String importBatchNo, ModelMap modelMap) {
        modelMap.put("importBatchNo",importBatchNo);
        if("1".equals(toType)){
            return prefix + "/statisticsDetail";
        }else if("2".equals(toType)){
            return prefix + "/statisticsDetail2";
        }else {return prefix + "/statisticsDetail3";}
    }
    /**
     * 显示异常详情列表
     * @return
     */
    @PostMapping("/exceptionList/{toType}")
    @ResponseBody
    public TableDataInfo  exceptionList(@PathVariable("toType")String toType, String importBatchNo, HttpServletRequest request) {
        List<Map<String, String>> exectionList = null;
        try {
            if("1".equals(toType)){
                exectionList =(List<Map<String, String>>)request.getSession().getAttribute("exectionList");
            }else if("2".equals(toType)){
                exectionList = this.curAssetsRepaymentPackageService.selectNotExistsInfo(importBatchNo);
            }else { exectionList = this.curAssetsRepaymentPackageService.selectCloseCaseInfo(importBatchNo);}

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("显示异常详情列表失败"+e.getMessage());
        }
        return getDataTable(exectionList);
    }


    /**
     * 更新主表记录
     * @return
     */
    @PostMapping("/insert")
    @ResponseBody
    @Transactional
    public AjaxResult insert(HttpServletRequest request,String importBatchNo) {
        try {
            //批量添加还款记录表
            this.curAssetsRepaymentPackageService.batchAddRepaymentTable(importBatchNo);
            //查询需要操作的正常数据
            CurAssetsRepaymentPackage param = new CurAssetsRepaymentPackage();
            param.setImportBatchNo(importBatchNo);
            List<CurAssetsRepaymentPackage> repaymentList = this.curAssetsRepaymentPackageService.selectCurAssetsRepaymentPackageList(param);
            // 插入流水表
            TLcImportFlow tLcImportFlow = new TLcImportFlow();
            tLcImportFlow.setImportBatchNo(importBatchNo)
                    .setImportType(ImportTypeEnum.REPAYMENT_TEMPLETE.getCode())
                    .setOrgId(repaymentList.get(0).getOrgId())
                    .setOrgName(repaymentList.get(0).getOrgName())
                    .setTotalNum(repaymentList.size())
                    .setCreateBy(String.valueOf(ShiroUtils.getUserId()));
            this.tlcImportFlowService.insertTLcImportFlow(tLcImportFlow);
            //结案操作
//            AjaxResult result = this.curAssetsRepaymentPackageService.callRemote(repaymentList, importBatchNo);
            AjaxResult result = this.curAssetsRepaymentPackageService.callRemote2(repaymentList, importBatchNo);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加失败"+e.getMessage(),e);
            return AjaxResult.success("添加失败");
        }
    }


    /**
     * 数据校验
     * @param request
     * @return
     */
    @PostMapping("/checkData")
    @ResponseBody
    public AjaxResult checkData(HttpServletRequest request,String importBatchNo) {
        Map<String, String> map = new HashMap<String, String>();
        List<Map<String, String>> exectionList = null;
        try {
            map = this.curAssetsRepaymentPackageService.checkData(request, importBatchNo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("还款导入数据检查异常"+e.getMessage(),e);
            return AjaxResult.error(e.getMessage());
        }
        String result = JSON.toJSONString(map);
        return AjaxResult.success(result);
    }




    /**
     * 导入
     * @param request
     * @param file
     * @return
     */
    @RequiresPermissions("assetspackage:repaymentImport:import")
    @PostMapping("/import")
    @ResponseBody
    @Log(title = "数据导入")
    public AjaxResult upload(HttpServletRequest request, @RequestParam("file") MultipartFile file, String templateId,String orgId) {
        String importBatchNo ="";
        try {
            importBatchNo = this.curAssetsRepaymentPackageService.handler(request, file, templateId, orgId);
        } catch (Exception e) {
            e.printStackTrace();
            return error("数据导入失败：" + e.getMessage());
        }
        return AjaxResult.success("导入临时表成功",importBatchNo);
    }







}
