package com.ruoyi.assetspackage.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.assetspackage.domain.*;
import com.ruoyi.assetspackage.enums.ImportTypeEnum;
import com.ruoyi.assetspackage.enums.IsCloseCaseEnum;
import com.ruoyi.assetspackage.enums.IsNoEnum;
import com.ruoyi.assetspackage.enums.PackageFlagEnum;
import com.ruoyi.assetspackage.exception.ImportDataExcepion;
import com.ruoyi.assetspackage.service.*;
import com.ruoyi.assetspackage.util.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.domain.CloseCase;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 客户资产Controller
 *
 * @author guozeqi
 * @date 2019-12-25
 */
@Controller
@RequestMapping("/import/assets")
public class CurAssetsPackageController extends BaseController {
    private String prefix = "assetspackage/importassets";
    private String updatePrefix = "assetspackage/updateassets";

    @Autowired
    ImportDataMapping importDataMapping;

    @Autowired
    RemoteConfigure remoteConfigure;

    @Autowired
    private ICurAssetsPackageService curAssetsPackageService;

    @Autowired
    private ITemplatesPackageService templatesPackageService;

    @Autowired
    private IOrgPackageService orgPackageService;

    @Autowired
    private ITemplateRelationPackageService templateRelationPackageService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ITLcImportFlowService tlcImportFlowService;

    @Autowired
    private PackageDataPermissionUtil dataPermissionUtil;

    @RequiresPermissions("import:assets:view")
    @GetMapping()
    public String assetspackage() {
        try {
//            this.curAssetsPackageService.deleteTempCurAssetsPackage();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("清空临时表异常"+e.getMessage());
        }
        return prefix + "/importAssets";
    }

    @GetMapping("deleteTempTable/{importBatchNo}")
    @ResponseBody
    public AjaxResult deleteTempTable(@PathVariable("importBatchNo") String importBatchNo) {
        try {
            this.curAssetsPackageService.deleteTempCurAssetsPackage(importBatchNo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("清空临时表异常",e);
            return error("清空临时表异常");
        }
        return success();
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

    @GetMapping("toStatisticsDetail")
    public String toStatisticsDetail(String importBatchNo, ModelMap modelMap) {
        return prefix + "/statisticsDetail";
    }
    /**
     * 查询当前用户拥有的数据权限与委托方的交集
     *
     * @param request
     * @return
     */
    @RequiresPermissions("import:assets:list")
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
     * 更新信息导入页面
     */

    @GetMapping("updateAssets")
    public String updateAssets() {
        return updatePrefix + "/updateAssets";
    }


    @GetMapping("toViewUpdateDetail")
    public String toViewUpdateDetail(String importBatchNo, ModelMap modelMap) {
        modelMap.put("importBatchNo", importBatchNo);
        return updatePrefix + "/updateDetail";
    }

    @GetMapping("toUpdateStatistics/{importBatchNo}")
    public String toUpdateStatistics(@PathVariable("importBatchNo") String importBatchNo, ModelMap modelMap) {
        modelMap.put("importBatchNo",importBatchNo);
        return updatePrefix + "/statistics";
    }

    @GetMapping("toUpdateStatisticsDetail/{type}/{importBatchNo}")
    public String toUpdateStatisticsDetail(@PathVariable("type") String type, @PathVariable("importBatchNo")String importBatchNo, ModelMap modelMap) {
        modelMap.put("importBatchNo",importBatchNo);
        if("1".equals(type)){
            return updatePrefix + "/notexistsDetail";
        }else{
            return updatePrefix + "/exceptionDetail";
        }
    }


    /**
     * 显示更新导入异常详情列表
     * @return
     */
    @PostMapping("/updateExceptionList")
    @ResponseBody
    public TableDataInfo  updateExceptionList(HttpServletRequest request,String importBatchNo) {
        List<Map<String, String>> exectionList = null;
        try {
            exectionList =(List<Map<String, String>>)request.getSession().getAttribute("exectionList");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("显示更新导入异常详情列表失败"+e.getMessage());
        }
        return getDataTable(exectionList);
    }

    /**
     * 显示更新导入不存在详情列表
     * @return
     */
    @PostMapping("/updateNotExistsList")
    @ResponseBody
    public TableDataInfo updateNotExistsList(HttpServletRequest request,String importBatchNo) {
        List<Map<String, String>> notExistsList = null;
        try {
            notExistsList = this.curAssetsPackageService.findNotExistsList(importBatchNo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("显示更新导入不存在详情列表失败"+e.getMessage());
        }
        return getDataTable(notExistsList);
    }




//    /**
//     * 结案接口-给催收管理模块使用
//     *
//     * @param caseList
//     * @return
//     */
//    @ResponseBody
//    @PostMapping(value = "/closeCase", consumes = "application/json")
//    public Response closeCase(@RequestBody List<CloseCase> caseList) {
//        return this.curAssetsPackageService.closeCase(caseList);
//    }

    /**
     * 查询客户资产列表
     */
    @RequiresPermissions("import:assets:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CurAssetsPackage curAssetsPackage) {
        //Set<Long> deptIds = dataPermissionUtil.selectDataPer();
        //curAssetsPackage.setDeptIds(deptIds);
        startPage();
        List<CurAssetsPackage> list = curAssetsPackageService.selectCurAssetsPackageByPage(curAssetsPackage);
        return getDataTable(list);
    }

    /**
     * 新增客户资产
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存客户资产
     */
    @RequiresPermissions("import:assets:add")
    @Log(title = "客户资产", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CurAssetsPackage curAssetsPackage) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        curAssetsPackage.setId(uuid);
        return toAjax(curAssetsPackageService.insertCurAssetsPackage(curAssetsPackage));
    }

    /**
     * 修改客户资产
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        CurAssetsPackage curAssetsPackage = curAssetsPackageService.selectCurAssetsPackageById(id);
        mmap.put("curAssetsPackage", curAssetsPackage);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户资产
     */
    @RequiresPermissions("import:assets:edit")
    @Log(title = "客户资产", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CurAssetsPackage curAssetsPackage) {
        return toAjax(curAssetsPackageService.updateCurAssetsPackage(curAssetsPackage));
    }

    /**
     * 删除客户资产
     */
    @RequiresPermissions("import:assets:remove")
    @Log(title = "客户资产", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(curAssetsPackageService.deleteCurAssetsPackageByIds(ids));
    }


    /**
     * 导出客户资产列表
     */
    @RequiresPermissions("import:assets:export")
    @Log(title = "客户资产", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CurAssetsPackage curAssetsPackage) {
        List<CurAssetsPackage> list = curAssetsPackageService.selectCurAssetsPackageList(curAssetsPackage);
        ExcelUtil<CurAssetsPackage> util = new ExcelUtil<CurAssetsPackage>(CurAssetsPackage.class);
        return util.exportExcel(list, "assetspackage");
    }

    /**
     * 查询模板
     * @param request
     * @return
     */
    @RequiresPermissions("import:assets:upload")
    @PostMapping("/findTemplate")
    @ResponseBody
    public AjaxResult findTemplate(HttpServletRequest request,String orgId) {
        orgId = String.valueOf(ShiroUtils.getSysUser().getOrgId());
        Map<String, String> map = new HashMap<String, String>();
        //查询所有的资产模板
        TemplatesPackage param = new TemplatesPackage();
        param.setType("1");//资产模板
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

    @PostMapping("/findUpdateTemplate")
    @ResponseBody
    public AjaxResult findUpdateTemplate(HttpServletRequest request,String orgId) {
        orgId = String.valueOf(ShiroUtils.getSysUser().getOrgId());
        Map<String, String> map = new HashMap<String, String>();
        //查询所有的资产模板
        TemplatesPackage param = new TemplatesPackage();
        param.setType("4");//更新模板
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
     * 显示异常详情列表
     * @return
     */
    @PostMapping("/exceptionList")
    @ResponseBody
    public TableDataInfo  exceptionList(HttpServletRequest request) {
        List<Map<String, String>> exectionList = null;
        try {
            exectionList =(List<Map<String, String>>)request.getSession().getAttribute("exectionList");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("显示异常详情列表失败"+e.getMessage());
        }
        return getDataTable(exectionList);
    }

    /**
     * 新增主表记录
     * @return
     */
    @PostMapping("/insert")
    @ResponseBody
    public AjaxResult insert(HttpServletRequest request,String importBatchNo) {
        try {
            List<TempCurAssetsPackage> insertList = this.curAssetsPackageService.selectInsertList(importBatchNo);
            this.curAssetsPackageService.batchAddAssets(insertList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("新增失败"+e.getMessage(),e);
            return AjaxResult.success("新增失败");
        }
        return AjaxResult.success("新增成功");
    }

    /**
     * 更新主表记录
     * @return
     */
//    @PostMapping("/update")
//    @ResponseBody
//    @Transactional
//    public AjaxResult update(HttpServletRequest request,String importBatchNo) {
//        try {
//            List<CurAssetsPackage> remoteList = new ArrayList<CurAssetsPackage>();
//            List<TempCurAssetsPackage> updateList = this.curAssetsPackageService.selectUpdateList2(importBatchNo);
//            for (TempCurAssetsPackage tempCurAssetsPackage : updateList) {
//                this.curAssetsPackageService.updateCurAssetsPackage2(tempCurAssetsPackage,remoteList);
//            }
//            this.curAssetsPackageService.callRemoteUpdate(remoteList);
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("更新失败"+e.getMessage(),e);
//            return AjaxResult.error("更新失败");
//        }
//        return AjaxResult.success("更新成功");
//    }

    /**
     * 更新主表记录
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    @Transactional
    public AjaxResult update(HttpServletRequest request,String importBatchNo) {
        try {
            this.curAssetsPackageService.updateHandler(request,importBatchNo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("更新失败"+e.getMessage(),e);
            return AjaxResult.error("更新失败");
        }
        return AjaxResult.success("更新成功");
    }


    /**
     * 数据校验
     * @param request
     * @return
     */
    @PostMapping("/checkData")
    @ResponseBody
    public AjaxResult checkData(HttpServletRequest request,String importBatchNo) {
        Map<String, String> map = null;
        String result = "";
        try {
            map = this.curAssetsPackageService.checkData(request, importBatchNo);
            result = JSON.toJSONString(map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("资产导入数据检查异常"+e.getMessage(),e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(result);
    }

    @PostMapping("/checkUpdateData")
    @ResponseBody
    public AjaxResult checkUpdateData(HttpServletRequest request,String importBatchNo) {
        Map<String, String> map = null;
        String result = "";
        try {
            map = this.curAssetsPackageService.checkUpdateData(request, importBatchNo);
            result = JSON.toJSONString(map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("资产导入数据检查异常"+e.getMessage(),e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(result);
    }

    /**
     * 导入
     * @param request
     * @param file
     * @return
     */
    @RequiresPermissions("import:assets:upload")
    @PostMapping("/upload")
    @ResponseBody
    @Log(title = "数据导入")
    public AjaxResult upload(HttpServletRequest request, @RequestParam("file") MultipartFile file, String templateId,String orgId,String type) {
        String importBatchNo ="";
        try {
            importBatchNo = this.curAssetsPackageService.handler(request, file, templateId, orgId, type);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("数据导入失败:{}",e);
            return AjaxResult.success("数据导入失败",e.getMessage());
//            return AjaxResult.success("数据导入失败","数据导入失败");
        }
        return AjaxResult.success("导入临时表成功",importBatchNo);
    }


    /**
     * 更新导入处理
     * @return
     */
    @PostMapping("/updateImortHandler")
    @ResponseBody
    @Transactional
    public AjaxResult updateImortHandler(HttpServletRequest request,String importBatchNo){
        try {
            this.curAssetsPackageService.updateImortHandler(request,importBatchNo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("更新失败"+e.getMessage(),e);
            return AjaxResult.error("更新失败");
        }

        return AjaxResult.success("更新成功");
    }

    /**
     * 查询自由导入信息
     * @param caseNo
     * @return
     */
    @PostMapping("/selectFreeImportList")
    @ResponseBody
    public List<Map<String,String>> selectFreeImportList(String caseNo) {
        logger.info("查询补充信息开始caseNo="+caseNo);
        List<Map<String,String>> freeImportList = null;
        try {
            freeImportList = this.curAssetsPackageService.selectFreeImportByCaseno(caseNo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询自由导入信息失败：{}",e);
        }
        logger.info("查询补充信息结束caseNo="+caseNo);
        return freeImportList;
    }


}
