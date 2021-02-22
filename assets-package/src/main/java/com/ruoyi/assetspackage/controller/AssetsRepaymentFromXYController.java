package com.ruoyi.assetspackage.controller;

import com.alibaba.fastjson.JSON;
import com.ruoyi.assetspackage.domain.*;
import com.ruoyi.assetspackage.enums.ImportTypeEnum;
import com.ruoyi.assetspackage.service.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.RestTemplateUtil;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.DataPermissionUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.service.ISysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 资产还款Controller
 *
 * @author guozeqi
 * @date 2020-07-27
 */
@Slf4j
@Controller
@RequestMapping("/assetspackage/xyRepayment")
public class AssetsRepaymentFromXYController extends BaseController {
    private String prefix = "assetspackage/xyRepayment";

    @Autowired
    private IAssetsRepaymenFromXYService assetsRepaymenFromXYService;
    @Autowired
    private ICurAssetsRepaymentPackageService curAssetsRepaymentPackageService;
    @Autowired
    private ITemplatesPackageService templatesPackageService;
    @Autowired
    private ITLcImportFlowService tlcImportFlowService;
    @Autowired
    private DataPermissionUtils dataPermissionUtils;
    @Autowired
    private ISysConfigService sysConfigService;

    @RequiresPermissions("assetspackage:xyRepayment:view")
    @GetMapping()
    public String repaymentImport() {
        return prefix + "/xyRepaymentImport";
    }


    @GetMapping("toStatistics/{importBatchNo}")
    public String toStatistics(@PathVariable("importBatchNo") String importBatchNo, ModelMap modelMap) {
        modelMap.put("importBatchNo",importBatchNo);
        return prefix + "/statistics";
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
     * 查询资产还款列表
     */
    @RequiresPermissions("assetspackage:xyRepayment:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CurAssetsRepaymentPackage curAssetsRepaymentPackage) {
        startPage();
        /*Set<Long> deptIds = dataPermissionUtils.selectDataPer();
        curAssetsRepaymentPackage.setDeptIds(deptIds);*/
        Long orgId = ShiroUtils.getSysUser().getOrgId();
        curAssetsRepaymentPackage.setOrgId(String.valueOf(orgId));
        List<CurAssetsRepaymentPackage> list = assetsRepaymenFromXYService.findCurAssetsRepaymentList(curAssetsRepaymentPackage);
        return getDataTable(list);
    }


    /**
     * 导出资产还款列表
     */
    @RequiresPermissions("assetspackage:xyRepayment:export")
    @Log(title = "资产还款", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CurAssetsRepaymentPackage curAssetsRepaymentPackage) {
        /*Set<Long> deptIds = dataPermissionUtils.selectDataPer();
        curAssetsRepaymentPackage.setDeptIds(deptIds);*/
        Long orgId = ShiroUtils.getSysUser().getOrgId();
        curAssetsRepaymentPackage.setOrgId(String.valueOf(orgId));
        String xyOrgId = this.sysConfigService.selectConfigByKey("xyOrgId");
        if(xyOrgId.equals(orgId.toString())){
            //兴业
            List<CurAssetsRepaymentPackageXy> list = assetsRepaymenFromXYService.findCurAssetsRepaymentXyList(curAssetsRepaymentPackage);
            ExcelUtil<CurAssetsRepaymentPackageXy> util = new ExcelUtil<CurAssetsRepaymentPackageXy>(CurAssetsRepaymentPackageXy.class);
            return util.exportExcel(list, "repayment");
        }else{
            List<CurAssetsRepaymentPackage> list = assetsRepaymenFromXYService.findCurAssetsRepaymentList(curAssetsRepaymentPackage);
            ExcelUtil<CurAssetsRepaymentPackage> util = new ExcelUtil<CurAssetsRepaymentPackage>(CurAssetsRepaymentPackage.class);
            return util.exportExcel(list, "repayment");
        }
    }




    /**
     * 查询模板
     *
     * @param request
     * @return
     */
    @RequiresPermissions("assetspackage:xyRepayment:import")
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
            String xyOrgId = this.sysConfigService.selectConfigByKey("xyOrgId");
            String orgId = repaymentList.get(0).getOrgId();
            if(!xyOrgId.equals(orgId)){
                AjaxResult result = this.curAssetsRepaymentPackageService.callRemote2(repaymentList, importBatchNo);
                return result;
            }else{
                return AjaxResult.success();
            }
//            AjaxResult result = this.curAssetsRepaymentPackageService.callRemote2(repaymentList, importBatchNo);
//            return result;
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
    @RequiresPermissions("assetspackage:xyRepayment:import")
    @PostMapping("/import")
    @ResponseBody
    @Log(title = "数据导入")
    public AjaxResult upload(HttpServletRequest request, @RequestParam("file") MultipartFile file, String templateId,String orgId) {
        String importBatchNo ="";
        try {
            importBatchNo = this.curAssetsRepaymentPackageService.handler(request, file, templateId, orgId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("还款导入失败{}",e);
            return AjaxResult.error("error","数据导入失败：" + e.getMessage());
        }
        return AjaxResult.success("导入临时表成功",importBatchNo);
    }







}
