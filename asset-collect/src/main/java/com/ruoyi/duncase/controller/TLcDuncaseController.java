package com.ruoyi.duncase.controller;

import com.alibaba.druid.sql.visitor.functions.Isnull;
import com.ruoyi.assetspackage.domain.CurAssetsPackage;
import com.ruoyi.assetspackage.domain.score.CollectionScoreRequest;
import com.ruoyi.assetspackage.domain.score.CollectionScoreResponse;
import com.ruoyi.assetspackage.domain.score.TLcScore;
import com.ruoyi.assetspackage.service.ICurAssetsPackageService;
import com.ruoyi.assetspackage.service.ITLcScoreService;
import com.ruoyi.assetspackage.util.CollectionScoreUtil;
import com.ruoyi.columnQuery.service.ITLcColumnQueryService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.custom.domain.TLcCustJob;
import com.ruoyi.custom.domain.TLcCustinfo;
import com.ruoyi.custom.service.ITLcCustJobService;
import com.ruoyi.custom.service.ITLcCustinfoService;
import com.ruoyi.duncase.domain.Assets;
import com.ruoyi.duncase.domain.TLcDuncase;
import com.ruoyi.duncase.service.ITLcDuncaseService;
import com.ruoyi.enums.IsNoEnum;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.task.domain.CollJob;
import com.ruoyi.task.domain.TLcTask;
import com.ruoyi.utils.DesensitizationUtil;
import com.ruoyi.utils.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 案件Controller
 *
 * @author liurunkai
 * @date 2019-12-25
 */
@Controller
@RequestMapping("/collect/duncase")
public class TLcDuncaseController extends BaseController {
    private String prefix = "duncase/info";

    @Autowired
    private ITLcDuncaseService tLcDuncaseService;
    @Autowired
    private ITLcCustinfoService tLcCustInfoService;
    @Autowired
    private ITLcCustJobService tLcCustJobService;
    @Autowired
    private ICurAssetsPackageService curAssetsPackageService;
    @Autowired
    private DesensitizationUtil desensitizationUtil;

    @RequiresPermissions("collect:duncase:view")
    @GetMapping(value = "/view")
    public String duncase(HttpServletRequest request, ModelMap modelMap) {
        modelMap.put("orgId", ShiroUtils.getSysUser().getOrgId());
        //查询是否脱敏
        boolean desensitization = desensitizationUtil.isDesensitization(String.valueOf(ShiroUtils.getSysUser().getOrgId()), ShiroUtils.getLoginName());
        modelMap.put("desensitization", desensitization);
        // 是否组内查询：否
        modelMap.put("isGroup", IsNoEnum.NO.getCode());
        return prefix + "/duncase";
    }

    /**
     * 跳转到组内案件综合查询
     * @param request
     * @param modelMap
     * @return
     */
    @RequiresPermissions("collect:duncase:group:view")
    @GetMapping(value = "/group/view")
    public String groupDuncase(HttpServletRequest request, ModelMap modelMap) {
        modelMap.put("orgId", ShiroUtils.getSysUser().getOrgId());
        //查询是否脱敏
        boolean desensitization = desensitizationUtil.isDesensitization(String.valueOf(ShiroUtils.getSysUser().getOrgId()), ShiroUtils.getLoginName());
        modelMap.put("desensitization", desensitization);
        // 是否组内查询：是
        modelMap.put("isGroup", IsNoEnum.IS.getCode());
        return prefix + "/duncase";
    }

    /**
     * 查询案件列表
     */
    @RequiresPermissions("collect:duncase:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcDuncase tLcDuncase, HttpServletRequest request) {
        startPage();
        Map<String, String[]> parameterMap = request.getParameterMap();
//        if (tLcDuncase.getEndRecentlyAllotDate() != null) {
//            tLcDuncase.setEndRecentlyAllotDate(DateUtils.getEndOfDay(tLcDuncase.getEndRecentlyAllotDate()));
//        }
//        if (tLcDuncase.getEndRecentlyFollowUpDate() != null) {
//            tLcDuncase.setEndRecentlyFollowUpDate(DateUtils.getEndOfDay(tLcDuncase.getEndRecentlyFollowUpDate()));
//        }
//        if (tLcDuncase.getEndEnterCollDate() != null) {
//            tLcDuncase.setEndEnterCollDate(DateUtils.getEndOfDay(tLcDuncase.getEndEnterCollDate()));
//        }
        //行动码 电话码 转多选
//        String callCode = request.getParameter("callSign");//电话码
//        if(StringUtils.isNotEmpty(callCode)){
//            tLcDuncase.setCallCodeList(Arrays.asList(callCode.split(",")));
//            tLcDuncase.setCallSign(null);
//        }
//        String actionCode = request.getParameter("actionCode");//行动码
//        if(StringUtils.isNotEmpty(actionCode)){
//            tLcDuncase.setActionCodeList(Arrays.asList(actionCode.split(",")));
//            tLcDuncase.setActionCode(null);
//        }
        String callCodeHistoryListStr = request.getParameter("callCodeHistoryListStr");//历史电话码
        if(StringUtils.isNotEmpty(callCodeHistoryListStr)){
            tLcDuncase.setCallCodeHistoryList(Arrays.asList(callCodeHistoryListStr.split(",")));
        }
        String cityId = tLcDuncase.getCityId();
        if(cityId != null && !"".equals(cityId)){
            tLcDuncase.setProvinceId(null);
        }
        if (tLcDuncase.getIsGroup() != null && tLcDuncase.getIsGroup().equals(IsNoEnum.IS.getCode())) {
            tLcDuncase.setUserGroup(ShiroUtils.getSysUser().getUserGroup());
        }
        String caseNo = request.getParameter("caseNo");
        if(StringUtils.isNotBlank(caseNo)){
            tLcDuncase.setCaseNoList(Arrays.asList(caseNo.split(",")));
        }
        List<TLcDuncase> list = tLcDuncaseService.selectTLcDuncaseByPage(tLcDuncase);
        return getDataTable(list);
    }

    /**
     * 接收资产包传过来的案件信息及用户信息
     *
     * @param assetsPackageList
     */
    @PostMapping(value = "/acceptDuncase", consumes = "application/json")
    @ResponseBody
    public Response acceptDuncase(@RequestBody List<Assets> assetsPackageList) {
        return this.tLcDuncaseService.acceptDuncase(assetsPackageList);
    }

    @PostMapping(value = "/acceptDuncaseUpdate", consumes = "application/json")
    @ResponseBody
    public Response acceptDuncaseUpdate(@RequestBody List<Assets> assetsPackageList) {
        return this.tLcDuncaseService.acceptDuncaseUpdate(assetsPackageList);
    }

    /**
     * 导出案件列表
     */
    @RequiresPermissions("collect:duncase:export")
    @Log(title = "案件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcDuncase tLcDuncase, HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
//        if (tLcDuncase.getEndRecentlyAllotDate() != null) {
//            tLcDuncase.setEndRecentlyAllotDate(DateUtils.getEndOfDay(tLcDuncase.getEndRecentlyAllotDate()));
//        }
//        if (tLcDuncase.getEndRecentlyFollowUpDate() != null) {
//            tLcDuncase.setEndRecentlyFollowUpDate(DateUtils.getEndOfDay(tLcDuncase.getEndRecentlyFollowUpDate()));
//        }
//        if (tLcDuncase.getEndEnterCollDate() != null) {
//            tLcDuncase.setEndEnterCollDate(DateUtils.getEndOfDay(tLcDuncase.getEndEnterCollDate()));
//        }
//        //行动码 电话码 转多选
//        String callCode = request.getParameter("callSign");//电话码
//        if(StringUtils.isNotEmpty(callCode)){
//            tLcDuncase.setCallCodeList(Arrays.asList(callCode.split(",")));
//            tLcDuncase.setCallSign(null);
//        }
//        String actionCode = request.getParameter("actionCode");//行动码
//        if(StringUtils.isNotEmpty(actionCode)){
//            tLcDuncase.setActionCodeList(Arrays.asList(actionCode.split(",")));
//            tLcDuncase.setActionCode(null);
//        }
        String callCodeHistoryListStr = request.getParameter("callCodeHistoryListStr");//历史电话码
        if(StringUtils.isNotEmpty(callCodeHistoryListStr)){
            tLcDuncase.setCallCodeHistoryList(Arrays.asList(callCodeHistoryListStr.split(",")));
        }
        String cityId = tLcDuncase.getCityId();
        if(cityId != null && !"".equals(cityId)){
            tLcDuncase.setProvinceId(null);
        }
        tLcDuncase.setOrgId(String.valueOf(ShiroUtils.getSysUser().getOrgId()));
        List<TLcDuncase> list = tLcDuncaseService.selectTLcDuncaseByPage(tLcDuncase);
        ExcelUtil<TLcDuncase> util = new ExcelUtil<TLcDuncase>(TLcDuncase.class);
        return util.exportExcel(list, "duncase");
    }

    /**
     * 新增案件
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存案件
     */
    @RequiresPermissions("collect:duncase:add")
    @Log(title = "案件", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLcDuncase tLcDuncase) {
        return toAjax(tLcDuncaseService.insertTLcDuncase(tLcDuncase));
    }

    /**
     * 修改案件
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        TLcDuncase tLcDuncase = tLcDuncaseService.selectTLcDuncaseById(id);
        mmap.put("tLcDuncase", tLcDuncase);
        return prefix + "/edit";
    }

    @GetMapping(value = "/detail")
    public String toDuncaseDetail(String id, ModelMap modelMap) {
//        TLcDuncase tLcDuncase = tLcDuncaseService.selectTLcDuncaseById(id);
//        modelMap.put("tLcDuncase", tLcDuncase);
//        modelMap.put("tlcCustinfo", this.tLcCustInfoService.findCustByCaseNo(tLcDuncase.getCaseNo(),tLcDuncase.getOrgId(),tLcDuncase.getImportBatchNo()));
//        modelMap.put("tlcCustJob", this.tLcCustJobService.findCustJobByCaseNo(tLcDuncase.getCaseNo(),tLcDuncase.getOrgId(),tLcDuncase.getImportBatchNo()));
        modelMap.put("id", id);
        return prefix + "/detail";
    }

    @PostMapping("/allDetail")
    @ResponseBody
    public CollJob allDetail(Long id) {
        TLcDuncase tLcDuncase = tLcDuncaseService.selectTLcDuncaseById(id);
        TLcCustinfo tLcCustInfo = this.tLcCustInfoService.findCustByCaseNo(tLcDuncase.getCaseNo(), tLcDuncase.getOrgId(), tLcDuncase.getImportBatchNo());
        TLcCustJob tlcCustJob = this.tLcCustJobService.findCustJobByCaseNo(tLcDuncase.getCaseNo(), tLcDuncase.getOrgId(), tLcDuncase.getImportBatchNo());
        //tLcTaskService.callRemote()
        CollJob collJob = new CollJob();
        collJob.setTLcCustinfo(tLcCustInfo);
        collJob.setTLcDuncase(tLcDuncase);
        collJob.setTLcCustJob(tlcCustJob);
        return collJob;
    }

    /**
     * 修改保存案件
     */
    @RequiresPermissions("collect:duncase:edit")
    @Log(title = "案件", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLcDuncase tLcDuncase) {
        return toAjax(tLcDuncaseService.updateTLcDuncase(tLcDuncase));
    }

//    @ResponseBody
//    @PostMapping("/initColumnQuery")
//    public TableDataInfo initColumnQuery() {
//        TLcColumnQuery tLcColumnQuery = TLcColumnQuery.builder().orgId(ShiroUtils.getSysUser().getOrgId()).tableName(TableEnum.DUNCASE.getTableName()).build();
//        List<TLcColumnQuery> columnQueryList = this.columnQueryService.selectTLcColumnQueryList(tLcColumnQuery);
//        return getDataTable(columnQueryList);
//    }


    /**
     * 查询催收评分
     */
    @RequiresPermissions("collect:duncase:findScore")
    @PostMapping("/findScore")
    @ResponseBody
    public TableDataInfo findScore(TLcDuncase tLcDuncase, HttpServletRequest request,String ids) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String[] split = ids.split(",");
        List<TLcScore> updateList = new ArrayList<>();
        for (String id : split) {
            //根据id获取案件信息
            TLcDuncase duncase = this.tLcDuncaseService.selectTLcDuncaseById(Long.valueOf(id));
            //获取资产信息
            CurAssetsPackage assetParam = new CurAssetsPackage();
            assetParam.setImportBatchNo(duncase.getImportBatchNo());
            assetParam.setOrgCasno(duncase.getCaseNo());
            assetParam.setOrgId(duncase.getOrgId());
            List<CurAssetsPackage> curAssetsPackageList = this.curAssetsPackageService.selectCurAssetsPackageList(assetParam);
            CurAssetsPackage curAsset = null;
            if(curAssetsPackageList.size()>0){
               curAsset = curAssetsPackageList.get(0);
            }
            if(curAsset==null){
                logger.error("获取资产信息失败,参数为orgId="+duncase.getOrgId()+",caseNo="+duncase.getCaseNo()+",importBatchNo="+duncase.getImportBatchNo());
                continue;
            }
            //查询度小满获取评分
            Map<String,String> map = new HashMap<>();
            map.put("curName",curAsset.getCurName());
            map.put("certificateNo",curAsset.getCertificateNo());
            map.put("customerMobile",curAsset.getCustomerMobile());
            if(curAsset.getOverdueDays() != null && !"".equals(curAsset.getOverdueDays())){
                map.put("overdueDays",curAsset.getOverdueDays());
            }
            if(curAsset.getWaYe() != null){
                map.put("waYe",curAsset.getWaYe().toPlainString());
            }
            if(curAsset.getFirstYqDate() != null){
                map.put("firstYqDate",sdf.format(curAsset.getFirstYqDate()));
            }
            map.put("orgName",curAsset.getOrg());
            try {
                CollectionScoreRequest collectionScoreRequest = CollectionScoreUtil.buildParam(map);
                logger.info("获取度小满评分请求参数{}",collectionScoreRequest);
                CollectionScoreResponse result = CollectionScoreUtil.doPost(collectionScoreRequest);
                logger.info("获取度小满评分结果{}",result);
                Long score = CollectionScoreUtil.getScore(result);
                TLcScore tLcScore = new TLcScore();
                tLcScore.setScore(score);
//                tLcScore.setUpdateBy(ShiroUtils.getLoginName());
//                tLcScore.setUpdateTime(new Date());
                tLcScore.setOrgCasno(duncase.getCaseNo());
                tLcScore.setOrgId(duncase.getOrgId());
                tLcScore.setImportBatchNo(duncase.getImportBatchNo());
                updateList.add(tLcScore);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("获取度小满评分失败{}",e);
            }
        }
        //更新案件表评分
        if(updateList.size()>0){
            this.tLcDuncaseService.updateScore(updateList);
        }
//        startPage();
//        String callCodeHistoryListStr = request.getParameter("callCodeHistoryListStr");//历史电话码
//        if(StringUtils.isNotEmpty(callCodeHistoryListStr)){
//            tLcDuncase.setCallCodeHistoryList(Arrays.asList(callCodeHistoryListStr.split(",")));
//        }
//        List<TLcDuncase> list = tLcDuncaseService.selectTLcDuncaseByPage(tLcDuncase);
        return new TableDataInfo();
    }

    /**
     * 查询委案总金额及当前已还总金额
     */
    @PostMapping("/searchAllDuncaseTotalMoney")
    @ResponseBody
    public Map<String, BigDecimal> searchAllDuncaseTotalMoney(TLcDuncase tLcDuncase, HttpServletRequest request) {
        String callCodeHistoryListStr = request.getParameter("callCodeHistoryListStr");//历史电话码
        if(StringUtils.isNotEmpty(callCodeHistoryListStr)){
            tLcDuncase.setCallCodeHistoryList(Arrays.asList(callCodeHistoryListStr.split(",")));
        }
        if (tLcDuncase.getIsGroup() != null && tLcDuncase.getIsGroup().equals(IsNoEnum.IS.getCode())) {
            tLcDuncase.setUserGroup(ShiroUtils.getSysUser().getUserGroup());
        }
        tLcDuncase.setOrgId(ShiroUtils.getSysUser().getOrgId().toString());
        Map<String, BigDecimal> resultMap = this.tLcDuncaseService.searchAllDuncaseTotalMoney(tLcDuncase);
        return resultMap;
    }

}
