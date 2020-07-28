package com.ruoyi.duncase.controller;

import com.ruoyi.assetspackage.service.ICurAssetsPackageService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.custom.domain.TLcCustContact;
import com.ruoyi.custom.service.ITLcCustContactService;
import com.ruoyi.duncase.domain.AssetsRepayment;
import com.ruoyi.duncase.domain.TLcDuncase;
import com.ruoyi.duncase.domain.TLcDuncaseActionRecord;
import com.ruoyi.duncase.domain.TLcDuncaseAssign;
import com.ruoyi.duncase.service.ITLcDuncaseActionRecordService;
import com.ruoyi.duncase.service.ITLcDuncaseAssignService;
import com.ruoyi.duncase.service.ITLcHisDuncaseService;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.task.domain.TLcCallRecord;
import com.ruoyi.task.domain.TLcSelectRecord;
import com.ruoyi.task.domain.TLcTask;
import com.ruoyi.task.service.ITLcCallRecordService;
import com.ruoyi.task.service.ITLcSelectRecordService;
import com.ruoyi.task.service.ITLcTaskService;
import lombok.extern.slf4j.Slf4j;
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
 * 历史数据
 *
 * @author liurunkai
 * @date 2019-12-25
 */
@Slf4j
@Controller
@RequestMapping("/his/duncase")
public class TLcHisDuncaseController extends BaseController {
    private String prefix = "duncase/info";

    @Autowired
    private ITLcHisDuncaseService hisDuncaseService;
    @Autowired
    private ITLcDuncaseAssignService duncaseAssignService;
    @Autowired
    private ITLcCallRecordService tLcCallRecordService;
    @Autowired
    private ITLcDuncaseActionRecordService tLcDuncaseActionRecordService;
    @Autowired
    private ITLcCustContactService tLcCustContactService;
    @Autowired
    private ITLcTaskService tLcTaskService;
    @Autowired
    private ICurAssetsPackageService curAssetsPackageService;
    @Autowired
    private ITLcSelectRecordService tLcSelectRecordService;

    @RequiresPermissions("his:duncase:view")
    @GetMapping(value = "/view")
    public String duncase(HttpServletRequest request, ModelMap modelMap) {
        modelMap.put("orgId", ShiroUtils.getSysUser().getOrgId());
        return prefix + "/hisDuncase";
    }

    /**
     * 查询案件列表
     */
    @RequiresPermissions("his:duncase:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcDuncase tLcDuncase, HttpServletRequest request) {
        startPage();
        String callCodeHistoryListStr = request.getParameter("callCodeHistoryListStr");//历史电话码
        if (StringUtils.isNotEmpty(callCodeHistoryListStr)) {
            tLcDuncase.setCallCodeHistoryList(Arrays.asList(callCodeHistoryListStr.split(",")));
        }
        List<TLcDuncase> list = this.hisDuncaseService.selectTLcDuncaseByPage(tLcDuncase);
        return getDataTable(list);
    }

    /**
     * 导出案件列表
     */
    @RequiresPermissions("his:duncase:export")
    @Log(title = "案件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcDuncase tLcDuncase, HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (tLcDuncase.getEndRecentlyAllotDate() != null) {
            tLcDuncase.setEndRecentlyAllotDate(DateUtils.getEndOfDay(tLcDuncase.getEndRecentlyAllotDate()));
        }
        if (tLcDuncase.getEndRecentlyFollowUpDate() != null) {
            tLcDuncase.setEndRecentlyFollowUpDate(DateUtils.getEndOfDay(tLcDuncase.getEndRecentlyFollowUpDate()));
        }
        if (tLcDuncase.getEndEnterCollDate() != null) {
            tLcDuncase.setEndEnterCollDate(DateUtils.getEndOfDay(tLcDuncase.getEndEnterCollDate()));
        }
        //行动码 电话码 转多选
        String callCode = request.getParameter("callSign");//电话码
        if (StringUtils.isNotEmpty(callCode)) {
            tLcDuncase.setCallCodeList(Arrays.asList(callCode.split(",")));
            tLcDuncase.setCallSign(null);
        }
        String actionCode = request.getParameter("actionCode");//行动码
        if (StringUtils.isNotEmpty(actionCode)) {
            tLcDuncase.setActionCodeList(Arrays.asList(actionCode.split(",")));
            tLcDuncase.setActionCode(null);
        }
        String callCodeHistoryListStr = request.getParameter("callCodeHistoryListStr");//历史电话码
        if (StringUtils.isNotEmpty(callCodeHistoryListStr)) {
            tLcDuncase.setCallCodeHistoryList(Arrays.asList(callCodeHistoryListStr.split(",")));
        }
        List<TLcDuncase> list = hisDuncaseService.selectTLcDuncaseByPage(tLcDuncase);
        ExcelUtil<TLcDuncase> util = new ExcelUtil<TLcDuncase>(TLcDuncase.class);
        return util.exportExcel(list, "duncase");
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

    /**
     * 跳转到案件详细信息页面
     *
     * @return
     */
    @GetMapping(value = "/hisDuncaseCcollJob")
    public String toHisDuncaseCollJobHis(TLcTask tLcTask, ModelMap modelMap) {
//        modelMap.put("ownerId", this.tLcTaskService.selectTaskByCaseNo(tLcTask.getCaseNo()).getOwnerId());
//        modelMap.put("orgId", this.tLcTaskService.selectTaskByCaseNo(tLcTask.getCaseNo()).getOrgId());
//        modelMap.put("certificateNo", this.tLcTaskService.selectTaskByCaseNo(tLcTask.getCaseNo()).getCertificateNo());
        modelMap.put("caseNo", tLcTask.getCaseNo());
        modelMap.put("orgId", tLcTask.getOrgId());
        modelMap.put("importBatchNo", tLcTask.getImportBatchNo());
        return prefix + "/hisDuncaseCollJob";
    }

    /**
     * 读取案件历史轨迹
     */
    @PostMapping("/hisDuncaseAssignList")
    @ResponseBody
    public List<TLcDuncaseAssign> hisDuncaseAssignList(String caseNo) {
        logger.info("查询案件历史轨迹开始caseNo="+caseNo);
        List<TLcDuncaseAssign> duncaseAssignList = this.duncaseAssignService.findHisDuncaseAssignByCaseNo(caseNo);
        logger.info("查询案件历史轨迹结束caseNo="+caseNo);
        return duncaseAssignList;
    }

    /**
     * 查询电催记录
     */
    @PostMapping("/hisDuncaseCallRecordList")
    @ResponseBody
    public List<TLcCallRecord> hisDuncaseCallRecordList(String caseNo) {
        log.info("查询电催记录开始caseNo：{}",caseNo);
        List<TLcCallRecord> callRecordList = this.tLcCallRecordService.findHisDuncaseCallRecordByCaseNo(caseNo);
        log.info("查询电催记录结束caseNo：{}",caseNo);
        return callRecordList;
    }

    /**
     * 查询行动记录
     */
    @PostMapping("/hisDuncaseActionRecordList")
    @ResponseBody
    public List<TLcDuncaseActionRecord> actionRecordList(String caseNo) {
        logger.info("查询行动历史开始caseNo="+caseNo);
        TLcDuncaseActionRecord actionRecord = TLcDuncaseActionRecord.builder().caseNo(caseNo).build();
        List<TLcDuncaseActionRecord> tLcDuncaseActionRecordList = this.tLcDuncaseActionRecordService.selectTLcHisDuncaseActionRecordList(actionRecord);
        logger.info("查询行动历史结束caseNo="+caseNo);
        return tLcDuncaseActionRecordList;
    }

    /**
     * 读取联系人记录
     */
    @PostMapping("/hisDuncaseCustContactList")
    @ResponseBody
    public List<TLcCustContact> hisDuncaseCustContactList(String caseNo, String orgId, String importBatchNo) {
        logger.info("查询联系人开始caseNo="+caseNo);
        List<TLcCustContact> custContactList = this.tLcCustContactService.findAllHisDuncaseCustContactByCaseNo(caseNo, orgId, importBatchNo);
        logger.info("查询联系人结束caseNo="+caseNo);
        return custContactList;
    }

    /**
     * 读取还款历史记录
     */
    @PostMapping("/hisRepaymentList")
    @ResponseBody
    public List<AssetsRepayment> repaymentList(String caseNo) {
        logger.info("查询还款历史记录开始caseNo="+caseNo);
        List<AssetsRepayment> repayHisList = this.tLcTaskService.viewHisRepayHis(caseNo);
        logger.info("查询还款历史记录结束caseNo="+caseNo);
        return repayHisList;
    }

    /**
     * 查询自由导入信息
     * @param caseNo
     * @return
     */
    @PostMapping("/selectHisFreeImportList")
    @ResponseBody
    public List<Map<String,String>> selectFreeImportList(String caseNo) throws Exception {
        logger.info("查询补充信息开始caseNo="+caseNo);
        List<Map<String,String>> freeImportList = null;
        try {
            freeImportList = this.curAssetsPackageService.selectHisFreeImportByCaseno(caseNo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询自由导入信息失败：{}",e);
        }
        logger.info("查询补充信息结束caseNo="+caseNo);
        return freeImportList;
    }

    /**
     * 查询 查找记录
     */
    @PostMapping("/selectHisRecordList")
    @ResponseBody
    public List<TLcSelectRecord> selectRecordList(String caseNo) {
        logger.info("查询查找记录开始caseNo="+caseNo);
        List<TLcSelectRecord> selectRecordList = this.tLcSelectRecordService.findHisSelectRecordByCaseNo(caseNo);
        logger.info("查询查找记录结束caseNo="+caseNo);
        return selectRecordList;
    }

    /**
     * 查询委案总金额及当前已还总金额
     */
    @PostMapping("/searchHisDuncaseTotalMoney")
    @ResponseBody
    public Map<String, BigDecimal> searchHisDuncaseTotalMoney(TLcDuncase tLcDuncase, HttpServletRequest request) {
        String callCodeHistoryListStr = request.getParameter("callCodeHistoryListStr");//历史电话码
        if(StringUtils.isNotEmpty(callCodeHistoryListStr)){
            tLcDuncase.setCallCodeHistoryList(Arrays.asList(callCodeHistoryListStr.split(",")));
        }
        tLcDuncase.setOrgId(ShiroUtils.getSysUser().getOrgId().toString());
        Map<String, BigDecimal> resultMap = this.hisDuncaseService.searchHisDuncaseTotalMoney(tLcDuncase);
        return resultMap;
    }

}
