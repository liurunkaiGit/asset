package com.ruoyi.assetspackage.controller;

import com.alibaba.fastjson.JSON;
import com.ruoyi.assetspackage.domain.*;
import com.ruoyi.assetspackage.service.IOrgPackageService;
import com.ruoyi.assetspackage.service.ITemplateRelationPackageService;
import com.ruoyi.assetspackage.service.ITemplatesPackageService;
import com.ruoyi.assetspackage.service.IcollectionRecordImprotService;
import com.ruoyi.assetspackage.util.ParseExcelUtil;
import com.ruoyi.assetspackage.util.RecordDataImportUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
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
import java.util.*;

/**
 * 催收记录controller
 * @author guozeqi
 * @create 2020-05-09
 */

@Controller
@RequestMapping("/assetspackage/CollectionRecordImprot")
public class CollectionRecordImprotController extends BaseController{
    private String prefix = "assetspackage/collectionRecordImprot";

    @Autowired
    private IcollectionRecordImprotService collectionRecordImprotService;

    @Autowired
    private RecordImportDataMapping recordImportDataMapping;

    @Autowired
    private ITemplatesPackageService templatesPackageService;

    @Autowired
    private IOrgPackageService orgPackageService;

    @Autowired
    private ITemplateRelationPackageService templateRelationPackageService;


    @RequiresPermissions("assetspackage:CollectionRecordImprot:view")
    @GetMapping()
    public String assetspackage() {
        return prefix + "/collectionRecordImprotView";
    }


    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CollectionRecordImportTemp collectionRecordImportTemp) throws Exception{
        String orgId = String.valueOf(ShiroUtils.getSysUser().getOrgId());
        collectionRecordImportTemp.setOrgId(orgId);
        startPage();
        List<CollectionrecordimprotFlow> list = this.collectionRecordImprotService.selectTLcCollectionrecordimprotFlowList(collectionRecordImportTemp);
        return getDataTable(list);
    }

    @GetMapping("deleteTempTable/{importBatchNo}")
    @ResponseBody
    public AjaxResult deleteTempTable(@PathVariable("importBatchNo") String importBatchNo) {
        try {
            this.collectionRecordImprotService.deleteTempTable(importBatchNo);
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
     * 显示异常详情列表
     * @return
     */
    @PostMapping("/exceptionList")
    @ResponseBody
    public TableDataInfo exceptionList(HttpServletRequest request) {
        List<Map<String, String>> exectionList = null;
        try {
            exectionList =(List<Map<String, String>>)request.getSession().getAttribute("RecordExectionList");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("显示异常详情列表失败"+e.getMessage());
        }
        return getDataTable(exectionList);
    }

    @GetMapping("toNotExistsStatisticsDetail")
    public String toNotExistsStatisticsDetail(String importBatchNo, ModelMap modelMap) {
        return prefix + "/notExistsStatisticsDetail";
    }

    /**
     * 显示案件不存在详情列表
     * @return
     */
    @PostMapping("/notExitsList")
    @ResponseBody
    public TableDataInfo notExitsList(HttpServletRequest request) {
        List<Map<String, String>> notExistsList = null;
        try {
            notExistsList =(List<Map<String, String>>)request.getSession().getAttribute("RecordNotExistsList");
            for (Map<String, String> rowMap : notExistsList) {
                rowMap.put("msg","该案件不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("显示案件不存在详情列表失败"+e.getMessage());
        }
        return getDataTable(notExistsList);
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
     * 查询模板
     *
     * @param request
     * @return
     */
    @PostMapping("/findTemplate")
    @ResponseBody
    public AjaxResult findTemplate(HttpServletRequest request, String orgId) {
        orgId = String.valueOf(ShiroUtils.getSysUser().getOrgId());
        Map<String, String> map = new HashMap<String, String>();
        //查询所有的资产模板
        TemplatesPackage param = new TemplatesPackage();
        param.setType("3");
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
     * 新增记录
     * @return
     */
    @PostMapping("/insert")
    @ResponseBody
    public AjaxResult insert(HttpServletRequest request,String importBatchNo) {
        AjaxResult ret = null;
        try {
            ret = this.collectionRecordImprotService.batchExecute(importBatchNo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("新增失败"+e.getMessage(),e);
            return AjaxResult.success("新增失败");
        }
        return ret;
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
        try {
            map = this.collectionRecordImprotService.checkData(request, importBatchNo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("资产导入数据检查异常"+e.getMessage(),e);
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
    @RequiresPermissions("import:assets:upload")
    @PostMapping("/upload")
    @ResponseBody
    @Log(title = "数据导入")
    public AjaxResult upload(HttpServletRequest request, @RequestParam("file") MultipartFile file, String templateId, String orgId, String type) {
        String importBatchNo ="";
        try {
            importBatchNo = this.collectionRecordImprotService.handler(request, file, templateId, orgId, type);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("数据导入失败:{}",e);
            return error("数据导入失败：" + e.getMessage());
        }
        return AjaxResult.success("导入临时表成功",importBatchNo);
    }



}
