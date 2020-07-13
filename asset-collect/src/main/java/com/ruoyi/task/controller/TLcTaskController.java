package com.ruoyi.task.controller;

import com.alibaba.fastjson.JSON;
import com.ruoyi.agent.domain.ExtPhone;
import com.ruoyi.agent.service.IExtPhoneService;
import com.ruoyi.assetspackage.domain.OrgPackage;
import com.ruoyi.assetspackage.domain.RemoteConfigure;
import com.ruoyi.assetspackage.service.IOrgPackageService;
import com.ruoyi.caseConfig.domain.TLcAllocatCaseConfig;
import com.ruoyi.caseConfig.service.ITLcAllocatCaseConfigService;
import com.ruoyi.columnQuery.domain.TLcColumnQuery;
import com.ruoyi.columnQuery.service.ITLcColumnQueryService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.TableEnum;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.RestTemplateUtil;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.custom.domain.TLcCustContact;
import com.ruoyi.custom.service.ITLcCustContactService;
import com.ruoyi.duncase.domain.AssetsRepayment;
import com.ruoyi.duncase.domain.TLcDuncaseActionRecord;
import com.ruoyi.duncase.domain.TLcDuncaseAssign;
import com.ruoyi.duncase.service.ITLcDuncaseActionRecordService;
import com.ruoyi.duncase.service.ITLcDuncaseAssignService;
import com.ruoyi.enums.AllocatRuleEnum;
import com.ruoyi.enums.TaskTypeEnum;
import com.ruoyi.exonNum.domain.TLcExonNum;
import com.ruoyi.exonNum.service.ITLcExonNumService;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.robot.domain.TLcRobotBlack;
import com.ruoyi.robot.enums.RobotBlackReasonEnum;
import com.ruoyi.robot.service.ITLcRobotBlackService;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.task.domain.CollJob;
import com.ruoyi.task.domain.TLcCallRecord;
import com.ruoyi.task.domain.TLcSelectRecord;
import com.ruoyi.task.domain.TLcTask;
import com.ruoyi.task.service.ITLcCallRecordService;
import com.ruoyi.task.service.ITLcSelectRecordService;
import com.ruoyi.task.service.ITLcTaskService;
import com.ruoyi.utils.CloseCaseUser;
import com.ruoyi.utils.DataPermissionUtil;
import com.ruoyi.utils.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 任务Controller
 *
 * @author liurunkai
 * @date 2019-12-25
 */
@Slf4j
@Controller
@RequestMapping("/collect/task")
public class TLcTaskController extends BaseController {
    private String prefix = "task";

    @Autowired
    private ISysDictDataService sysDictDataService;
    @Autowired
    private ITLcTaskService tLcTaskService;
    @Autowired
    private ITLcAllocatCaseConfigService caseConfigService;
    @Autowired
    private ITLcCustContactService tLcCustContactService;
    @Autowired
    private ITLcDuncaseAssignService tLcDuncaseAssignService;
    @Autowired
    private ITLcCallRecordService tLcCallRecordService;
    @Autowired
    private ITLcSelectRecordService tLcSelectRecordService;
    @Autowired
    private ITLcDuncaseActionRecordService tLcDuncaseActionRecordService;
    @Autowired
    private DataPermissionUtil dataPermissionUtil;
    @Autowired
    private ITLcExonNumService exonNumService;
    @Autowired
    private RestTemplateUtil restTemplateUtil;
    @Autowired
    private IExtPhoneService extPhoneService;
    @Autowired
    RemoteConfigure remoteConfigure;
    @Autowired
    private IOrgPackageService orgPackageService;
    @Autowired
    private ITLcRobotBlackService robotBlackService;


    @RequiresPermissions("collect:task:myTask")
    @GetMapping(value = "/myTask")
    public String toMyTask(ModelMap modelMap) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String sessionId = request.getRequestedSessionId();
        logger.info("进入我的任务页面sessionId="+sessionId);
        Long userId;
        try {
            userId = ShiroUtils.getUserId();
        } catch (Exception e) {
            log.error("获取用户信息失败");
            throw new RuntimeException("获取用户信息失败");
        }
        ExtPhone extPhone = new ExtPhone();
        extPhone.setIsused("0");
        extPhone.setSeatId(Integer.valueOf(String.valueOf(userId)));
        //暂时写平安，后续从session里面取
//        extPhone.setCallPlatform("PA");
        extPhone.setCallPlatform(ShiroUtils.getSysUser().getPlatform());
        List<ExtPhone> list = extPhoneService.selectExtPhoneList(extPhone);
        if (list != null && list.size() > 0) {
            // 分机号码
            modelMap.put("extPhone", list.get(0).getAgentid());
        }
        modelMap.put("ownerId", userId);
        modelMap.put("orgId", ShiroUtils.getSysUser().getOrgId());
        modelMap.put("callPlatform", ShiroUtils.getSysUser().getPlatform());

        return prefix + "/myTask";
    }

    @RequiresPermissions("collect:task:view")
    @GetMapping(value = "/view")
    public String task(ModelMap modelMap) {
//        TLcColumnQuery tLcColumnQuery = TLcColumnQuery.builder().orgId(ShiroUtils.getSysUser().getOrgId()).tableName(TableEnum.TASK.getTableName()).build();
//        List<TLcColumnQuery> columnQueryList = this.columnQueryService.selectTLcColumnQueryList(tLcColumnQuery);
//        modelMap.put("columnQueryList", JSON.toJSONString(columnQueryList));
        modelMap.put("orgId", ShiroUtils.getSysUser().getOrgId());
        return prefix + "/task";
    }

    @RequiresPermissions("collect:task:helpCollApprove")
    @GetMapping(value = "/helpCollApprove")
    public String helpCollApprove(HttpServletRequest request, ModelMap modelMap) {
        modelMap.put("orgId", ShiroUtils.getSysUser().getOrgId());
        return prefix + "/helpCollApprove";
    }

    /**
     * 跳转到停催页面，type=1 审批页面 type=2 激活页面
     *
     * @param request
     * @param modelMap
     * @return
     */
    @RequiresPermissions("collect:task:stopColl")
    @GetMapping(value = "/stopColl")
    public String toStopColl(HttpServletRequest request, ModelMap modelMap) {
        String type = request.getParameter("type");
        modelMap.put("type", type);
        modelMap.put("orgId", ShiroUtils.getSysUser().getOrgId());
        if (type.equals("1")) {
            return prefix + "/stopCollApply";
        } else if (type.equals("2")) {
            return prefix + "/stopCollLive";
        }
        throw new RuntimeException(String.format("类型不匹配，type is %s", type));
    }

    /**
     * 跳转到案件详细信息页面
     *
     * @return
     */
//    @RequiresPermissions("collect:task:collJob")
    @GetMapping(value = "/collJobHis")
    public String toCollJobHis(TLcTask tLcTask, ModelMap modelMap) {
//        modelMap.put("ownerId", this.tLcTaskService.selectTaskByCaseNo(tLcTask.getCaseNo()).getOwnerId());
//        modelMap.put("orgId", this.tLcTaskService.selectTaskByCaseNo(tLcTask.getCaseNo()).getOrgId());
//        modelMap.put("certificateNo", this.tLcTaskService.selectTaskByCaseNo(tLcTask.getCaseNo()).getCertificateNo());
        modelMap.put("caseNo", tLcTask.getCaseNo());
        modelMap.put("orgId", tLcTask.getOrgId());
        modelMap.put("importBatchNo", tLcTask.getImportBatchNo());
        return prefix + "/collJobHis";
    }

    /**
     * 跳转到作业催收页面-通过按钮不传参数
     *
     * @return
     */
    @GetMapping(value = "/toCollJob")
    public String toCollJob(TLcTask tLcTask, String currentImportBatchNo, String currentCaseNo, ModelMap modelMap,String callCodeHistoryListStr) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String sessionId = request.getRequestedSessionId();
        logger.info("催收作业页面查询数据开始sessionId="+sessionId);
        modelMap.put("tLcTask", tLcTask);
        modelMap.put("currentCaseNo", currentCaseNo);
        modelMap.put("currentImportBatchNo", currentImportBatchNo);
        modelMap.put("callCodeHistoryListStr", callCodeHistoryListStr);

        ExtPhone extPhone = new ExtPhone();
        extPhone.setIsused("0");
        extPhone.setSeatId(Integer.valueOf(String.valueOf(tLcTask.getOwnerId())));
        //暂时写平安，后续从session里面取
//        extPhone.setCallPlatform("PA");
        extPhone.setCallPlatform(ShiroUtils.getSysUser().getPlatform());
        logger.info("查询分机号码开始");
        List<ExtPhone> list = extPhoneService.selectExtPhoneList(extPhone);
        List<String> extNumList = new ArrayList<>();
                logger.info("查询分机号码结束");
        if (list != null && list.size() > 0) {
            // 分机号码
            modelMap.put("extPhone", list.get(0));
            // 查询外显号码
            logger.info("查询外显号码开始");
            extNumList = this.extPhoneService.selectExtNumBySeat(String.valueOf(ShiroUtils.getSysUser().getUserId()), list.get(0).getAgentid(), ShiroUtils.getSysUser().getPlatform());
            logger.info("查询外显号码结束");
            modelMap.put("extNumList",StringUtils.join(extNumList,","));
        } else {
            modelMap.put("extPhone", extPhone);
            modelMap.put("extNumList",StringUtils.join(extNumList,","));
        }
        modelMap.put("callPlatform", ShiroUtils.getSysUser().getPlatform());
        // 查询总的金额及总的件数
        Map<String, BigDecimal> resultMap = this.tLcTaskService.selectTotalCountMoney(tLcTask);
        modelMap.put("totalCaseNum", resultMap.get("totalCaseNum"));
        modelMap.put("totalArrears", resultMap.get("totalArrears"));
        logger.info("催收作业页面查询数据结束、进入页面sessionId="+sessionId);
        return prefix + "/collJob";
    }

    /**
     * 跳转到作业催收页面-根据任务id
     *
     * @return
     */
    @RequiresPermissions("collect:task:collJob")
    @GetMapping(value = "/collJob")
    public String collJob(TLcTask tLcTask, ModelMap modelMap) {
        modelMap.put("certificateNo", this.tLcTaskService.selectTLcTaskById(tLcTask.getId()).getCertificateNo());
        return prefix + "/collJob";
    }

//    /**
//     * 跳转到作业催收页面-根据案件号
//     *
//     * @return
//     */
//    @RequiresPermissions("collect:task:collJob")
//    @GetMapping(value = "/collJobByCaseNo")
//    public String collJobByCaseNo(TLcTask tLcTask, ModelMap modelMap) {
//        modelMap.put("certificateNo", this.tLcTaskService.selectTaskByCaseNo(tLcTask.getCaseNo()).getCertificateNo());
//        return prefix + "/collJob";
//    }

    /**
     * 查询任务列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TLcTask tLcTask, HttpServletRequest request, ModelMap modelMap) {
        startPage();
        String callCodeHistoryListStr = request.getParameter("callCodeHistoryListStr");//历史电话码
        if(StringUtils.isNotEmpty(callCodeHistoryListStr) && !"null".equals(callCodeHistoryListStr)){
            tLcTask.setCallCodeHistoryList(Arrays.asList(callCodeHistoryListStr.split(",")));
        }
        List<TLcTask> list = tLcTaskService.selectTLcTaskByPage(tLcTask);
        return getDataTable(list);
    }

    /**
     * 查询任务列表
     */
    @PostMapping("/sendRobotApprovalList")
    @ResponseBody
    public TableDataInfo sendRobotApprovalList(TLcTask tLcTask) {
        startPage();
        tLcTask.setTaskType(TaskTypeEnum.SEND_ROBOT_APPLY.getCode());
        tLcTask.setOrgId(ShiroUtils.getSysUser().getOrgId().toString());
        List<TLcTask> list = tLcTaskService.selectTLcTaskByPage(tLcTask);
        return getDataTable(list);
    }

    /**
     * 操作全部数据
     */
    @PostMapping("/opreAllFindData")
    @ResponseBody
    public TableDataInfo opreAllFindData(TLcTask tLcTask) {
//        startPage();
        List<TLcTask> list = this.tLcTaskService.opreAllFindData(tLcTask);
        return getDataTable(list);
    }

    /**
     * 查询停催任务列表
     */
//    @RequiresPermissions("collect:task:stopCollList")
    @PostMapping("/stopCollList")
    @ResponseBody
    public TableDataInfo stopCollList(TLcTask tLcTask, Integer type) {
        startPage();
        Set<Integer> taskTypes = new HashSet<>();
        if (type == 1) {
            taskTypes.add(TaskTypeEnum.STOP_COLLECT_APPLY.getCode());
        } else {
            taskTypes.add(TaskTypeEnum.STOP_COLLECT.getCode());
        }
        tLcTask.setTaskTypes(taskTypes);
        List<TLcTask> list = tLcTaskService.selectTLcTaskList(tLcTask);
        return getDataTable(list);
    }

    /**
     * 协催审核
     *
     * @param taskIds
     * @return
     */
    @ResponseBody
    @GetMapping("/approveHelpColl")
    public AjaxResult approveHelpColl(String taskIds, Integer status) {
        this.tLcTaskService.approveHelpColl(taskIds, status);
        return AjaxResult.success();
    }

    /**
     * 停催审核、激活
     *
     * @param taskIds
     * @return
     */
    @ResponseBody
    @GetMapping("/approveStopColl")
    public AjaxResult approveStopColl(String taskIds, Integer status) {
        this.tLcTaskService.approveStopColl(taskIds, status);
        return AjaxResult.success();
    }

    /**
     * 停催申请
     *
     * @param taskId
     * @return
     */
    @ResponseBody
    @GetMapping("/stopCollApply")
    public AjaxResult stopCollApply(String taskId) {
        this.tLcTaskService.stopCollApply(taskId);
        return AjaxResult.success();
    }

    /**
     * 查询我的任务列表
     *
     * @param tLcTask
     * @return
     */
    @RequiresPermissions("collect:task:myTaskList")
    @PostMapping("/myTaskList")
    @ResponseBody
    public TableDataInfo myTaskList(TLcTask tLcTask,HttpServletRequest request) {
        logger.info("我的任务页面查询list开始");
        startPage();
        String callCodeHistoryListStr = request.getParameter("callCodeHistoryListStr");//历史电话码
        if(StringUtils.isNotEmpty(callCodeHistoryListStr)&& !"null".equals(callCodeHistoryListStr)){
            tLcTask.setCallCodeHistoryList(Arrays.asList(callCodeHistoryListStr.split(",")));
        }
        List<TLcTask> list = tLcTaskService.selectMyTaskList(tLcTask);
        logger.info("我的任务页面查询list结束");
        return getDataTable(list);
    }

    @PostMapping("/findTaskByOwner")
    @ResponseBody
    public List<TLcTask> findTaskByOwner(TLcTask tLcTask,HttpServletRequest request) {
        logger.info("查询客户列表开始ownerId="+tLcTask.getOwnerId());
        String callCodeHistoryListStr = request.getParameter("callCodeHistoryListStr");//历史电话码
        if(StringUtils.isNotEmpty(callCodeHistoryListStr) && !"null".equals(callCodeHistoryListStr)){
            tLcTask.setCallCodeHistoryList(Arrays.asList(callCodeHistoryListStr.split(",")));
        }
        startPageCustom(Integer.valueOf(request.getParameter("startNum")), Integer.valueOf(request.getParameter("endNum")));
        List<TLcTask> list = tLcTaskService.selectMyTaskList(tLcTask);
        logger.info("查询客户列表结束ownerId="+tLcTask.getOwnerId());
        return list;
    }

    /**
     * 查询电催记录
     */
    @PostMapping("/callRecordList")
    @ResponseBody
    public List<TLcCallRecord> callRecordList(String caseNo) {
        logger.info("查询电催记录开始caseNo="+caseNo);
        List<TLcCallRecord> callRecordList = this.tLcCallRecordService.findCallRecordByCaseNo(caseNo);
        logger.info("查询电催记录结束caseNo="+caseNo);
        return callRecordList;
    }

    /**
     * 查询 查找记录
     */
    @PostMapping("/selectRecordList")
    @ResponseBody
    public List<TLcSelectRecord> selectRecordList(String caseNo) {
        logger.info("查询查找记录开始caseNo="+caseNo);
        List<TLcSelectRecord> selectRecordList = this.tLcSelectRecordService.findSelectRecordByCaseNo(caseNo);
        logger.info("查询查找记录结束caseNo="+caseNo);
        return selectRecordList;
    }

    /**
     * 新增电催记录
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/addSelectRecord")
    public Response addSelectRecord(TLcSelectRecord tLcSelectRecord) {
        tLcSelectRecord.setCreateBy(String.valueOf(ShiroUtils.getUserId()));
        tLcSelectRecord.setCreateName(ShiroUtils.getSysUser().getUserName());
        return this.tLcSelectRecordService.insertTLcCallRecord(tLcSelectRecord);
    }

    /**
     * 查询行动记录
     */
    @PostMapping("/actionRecordList")
    @ResponseBody
    public List<TLcDuncaseActionRecord> actionRecordList(String caseNo) {
        logger.info("查询行动历史开始caseNo="+caseNo);
        TLcDuncaseActionRecord actionRecord = TLcDuncaseActionRecord.builder().caseNo(caseNo).build();
        List<TLcDuncaseActionRecord> tLcDuncaseActionRecordList = this.tLcDuncaseActionRecordService.selectTLcDuncaseActionRecordList(actionRecord);
        logger.info("查询行动历史结束caseNo="+caseNo);
        return tLcDuncaseActionRecordList;
    }

    /**
     * 读取联系人记录
     */
    @PostMapping("/custContactList")
    @ResponseBody
    public List<TLcCustContact> custContactList(String caseNo, String orgId, String importBatchNo) {
        logger.info("查询联系人开始caseNo="+caseNo);
        List<TLcCustContact> custContactList = this.tLcCustContactService.findAllCustContactByCaseNo(caseNo, orgId, importBatchNo);
        logger.info("查询联系人结束caseNo="+caseNo);
        return custContactList;
    }

    /**
     * 读取案件历史轨迹
     */
    @PostMapping("/duncaseAssignList")
    @ResponseBody
    public List<TLcDuncaseAssign> duncaseAssignList(String caseNo) {
        logger.info("查询案件历史轨迹开始caseNo="+caseNo);
        List<TLcDuncaseAssign> duncaseAssignList = this.tLcDuncaseAssignService.findDuncaseAssignByCaseNo(caseNo);
        logger.info("查询案件历史轨迹结束caseNo="+caseNo);
        return duncaseAssignList;
    }

    /**
     * 读取还款历史记录
     */
    @PostMapping("/repaymentList")
    @ResponseBody
    public List<AssetsRepayment> repaymentList(String caseNo) {
        logger.info("查询还款历史记录开始caseNo="+caseNo);
        List<AssetsRepayment> repayHisList = this.tLcTaskService.viewRepayHis(caseNo);
        logger.info("查询还款历史记录结束caseNo="+caseNo);
        return repayHisList;
    }


    @PostMapping("/collJobDetail")
    @ResponseBody
    public CollJob collJobDetail(String caseNo, String orgId, String importBatchNo) {
        logger.info("查询详细信息开始caseNo="+caseNo);
        CollJob collJob = this.tLcTaskService.collJobDetail(caseNo, orgId, importBatchNo);
        logger.info("查询详细信息结束caseNo="+caseNo);
        return collJob;
    }

    @PostMapping("/hisDuncaseCollJobDetail")
    @ResponseBody
    public CollJob hisDuncaseCollJobDetail(String caseNo, String orgId, String importBatchNo) {
        logger.info("查询详细信息开始caseNo="+caseNo);
        CollJob collJob = this.tLcTaskService.hisDuncaseCollJobDetail(caseNo, orgId, importBatchNo);
        logger.info("查询详细信息结束caseNo="+caseNo);
        return collJob;
    }

    /**
     * 催收作业历史页面
     */
    @PostMapping("/collHisDetail")
    @ResponseBody
    public CollJob collHisDetail(String caseNo, String orgId, String importBatchNo) {
        return this.tLcTaskService.collJobDetail(caseNo, orgId, importBatchNo);
    }

//    /**
//     * 催收作业页面_优化之后
//     */
//    @PostMapping("/collJobDetail")
//    @ResponseBody
//    public TLcDuncase collJobDetail(String certificateNo) {
//        TLcDuncase tLcDuncase = this.tLcDuncaseService.findCollJobDetail(certificateNo);
//        return tLcDuncase;
//    }

    /**
     * 跳转到新增电催页面
     *
     * @return
     */
    @GetMapping(value = "/toAddCallRecord")
    public String toAddCallRecord(String certificateNo, String contactName, String phone, Integer relation, ModelMap modelMap) {
        TLcCallRecord tLcCallRecord = TLcCallRecord.builder()
                .certificateNo(certificateNo)
                .contactName(contactName)
                .phone(phone)
                .contactRelation(relation)
                .build();
        modelMap.put("tLcCallRecord", tLcCallRecord);
        return prefix + "/callRecord/add";
    }

    /**
     * 新增电催记录
     *
     * @return
     */
/*
    @ResponseBody
    @PostMapping(value = "/addCallRecord")
    public Response addCallRecord(@RequestParam(value = "file", required = false) MultipartFile file, String fileName, TLcCallRecord tLcCallRecord,String callStartTime,String callEndTime) {
        // 上传文件路径
        String filePath = Global.getUploadPath();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date startTime = sdf.parse(callStartTime);
            Date endTime = sdf.parse(callEndTime);
            tLcCallRecord.setCallStartTime(startTime);
            tLcCallRecord.setCallEndTime(endTime);
            // 上传并返回新文件名称
            //fileName = FileUploadUtils.upload(filePath, file);
            FileUploadUtils.upload(filePath, file);
        } catch (Exception e) {
            log.error("上传文件失败，error is {}", e);
        }
        // todo 获取打电话的时长
        // todo 获取通话录音
        return tLcCallRecordService.insertTLcCallRecord(tLcCallRecord) > 0 ? Response.success(tLcCallRecord.getCertificateNo()) : Response.failure("新增失败", null);
    }
*/

    /**
     * 跳转到重新分派页面
     *
     * @param taskIds
     * @param modelMap
     * @return
     */
    @GetMapping("/toReAllocat")
    public String toFindReAllocatUser(String taskIds, ModelMap modelMap) {
//        SysUser sysUser = new SysUser();
//        sysUser.setDeptId(Long.valueOf(orgId));
//        modelMap.put("user", sysUser);
        modelMap.put("orgId", ShiroUtils.getSysUser().getOrgId());
        modelMap.put("taskIds", taskIds);
        return prefix + "/reAllocat";
    }

    /**
     * 跳转到重新分派页面
     *
     * @param tLcTask
     * @param modelMap
     * @return
     */
    @GetMapping("/allDataToReAllocat")
    public String toReAllocat(TLcTask tLcTask, ModelMap modelMap, HttpServletRequest request) {
//        modelMap.put("tLcTask", JSONObject.parseObject(JSON.toJSONString(tLcTask)));
        String callCodeHistoryListStr = request.getParameter("callCodeHistoryListStr");//历史电话码

        modelMap.put("tLcTask", tLcTask);
        modelMap.put("callCodeHistoryListStr", callCodeHistoryListStr);
        return prefix + "/allDataReAllocat";
    }

    /**
     * 跳转到协助催收页面
     *
     * @param orgId    组织机构id
     * @param taskIds  协助的任务id
     * @param ownerIds 记录当前催收人员
     * @param modelMap
     * @return
     */
    @GetMapping("/toHelpColl")
    public String toHelpColl(String orgId, String taskIds, String ownerIds, ModelMap modelMap) {
        modelMap.put("orgId", orgId);
        modelMap.put("taskIds", taskIds);
        modelMap.put("ownerIds", ownerIds);
        return prefix + "/helpColl";
    }

    /**
     * 跳转到查看还款记录页面
     *
     * @param caseNo   组织机构id
     * @param modelMap
     * @return
     */
    @GetMapping("/toViewRepayHis")
    public String toViewRepayHis(String caseNo, ModelMap modelMap) {
        modelMap.put("caseNo", caseNo);
        return prefix + "/viewRepayHis";
    }

    /**
     * 跳转到查看还款记录页面
     *
     * @param caseNo 组织机构id
     * @returnv
     */
    @PostMapping("/viewRepayHis")
    @ResponseBody
    public TableDataInfo viewRepayHis(String caseNo) {
        TableDataInfo rspData = new TableDataInfo();
        List<AssetsRepayment> repayHisList = this.tLcTaskService.viewRepayHis(caseNo);
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (null == pageDomain.getPageNum() || null == pageDomain.getPageSize()) {
            rspData.setRows(repayHisList);
            rspData.setTotal(repayHisList.size());
            return rspData;
        }
        Integer pageNum = (pageDomain.getPageNum() - 1) * 10;
        Integer pageSize = pageDomain.getPageNum() * 10;
        if (pageSize > repayHisList.size()) {
            pageSize = repayHisList.size();
        }
        rspData.setRows(repayHisList.subList(pageNum, pageSize));
        rspData.setTotal(repayHisList.size());
        return rspData;
    }

    @ResponseBody
    @PostMapping("/findReAllocatUser")
    public TableDataInfo list(String orgId, String loginName, String phonenumber) {
        TableDataInfo rspData = new TableDataInfo();
        SysUser sysUser = new SysUser();
        sysUser.setDeptId(ShiroUtils.getSysUser().getOrgId());
        sysUser.setLoginName(loginName);
        sysUser.setPhonenumber(phonenumber);
        List<SysUser> userList = this.tLcTaskService.searchUserByDeptAndHaveDept(sysUser);
        /*PageDomain pageDomain = TableSupport.buildPageRequest();
        if (null == pageDomain.getPageNum() || null == pageDomain.getPageSize()) {
            rspData.setRows(userList);
            rspData.setTotal(userList.size());
            return rspData;
        }
        Integer pageNum = (pageDomain.getPageNum() - 1) * 10;
        Integer pageSize = pageDomain.getPageNum() * 10;
        if (pageSize > userList.size()) {
            pageSize = userList.size();
        }
        rspData.setRows(userList.subList(pageNum, pageSize));
        rspData.setTotal(userList.size());*/
        rspData.setRows(userList);
        return  rspData;
    }

    @PostMapping("/findOrgInfo")
    @ResponseBody
    public AjaxResult findOrgInfo(HttpServletRequest request, String orgId) {
        List<Map<String, Object>> orgInfo = new ArrayList<Map<String, Object>>();
        OrgPackage orgPackage = new OrgPackage();
        orgPackage.setDeptId(Long.valueOf(orgId));
        String orgName = this.orgPackageService.selectOrgPackageList(orgPackage).get(0).getOrgName();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orgId", orgId);
        map.put("orgName", orgName);
        orgInfo.add(map);
        String result = JSON.toJSONString(orgInfo);
        return AjaxResult.success(result);
    }


    /**
     * 只给一个人分配案件的情况
     *
     * @param userId
     * @param taskIds
     * @param orgId
     */
//    @ResponseBody
//    @PostMapping("/reAllocat")
//    public void reAllocat(String userId, String taskIds, String orgId) {
//        this.tLcTaskService.reAllocat(userId, taskIds, orgId);
//    }

    /**
     * 重新分配案件给多个人
     *
     * @param userId
     * @param taskIds
     * @param orgId
     */
    @ResponseBody
    @PostMapping("/reAllocat")
    public AjaxResult reAllocat(String userId, String taskIds, String orgId, Integer allocatNum, Integer allocatRule) {
        return this.tLcTaskService.reAllocat(userId, taskIds, orgId, allocatNum, allocatRule);
    }

    /**
     * 重新分配案件给多个人
     *
     * @param userId
     * @param tLcTask
     */
    @ResponseBody
    @PostMapping("/allDataReAllocat")
    public AjaxResult allDataReAllocat(String userId, TLcTask tLcTask, Integer allocatNum, Integer allocatRule, HttpServletRequest request) {

        String callCodeHistoryListStr = request.getParameter("callCodeHistoryListStr");//历史电话码
        if(StringUtils.isNotEmpty(callCodeHistoryListStr) && !"null".equals(callCodeHistoryListStr)){
            tLcTask.setCallCodeHistoryList(Arrays.asList(callCodeHistoryListStr.split(",")));
        }
        return this.tLcTaskService.allDataReAllocat(userId, tLcTask, allocatNum, allocatRule);
    }

    /**
     * 查询已结案的任务数量
     *
     * @param tLcTask
     */
    @ResponseBody
    @PostMapping("/isHasCloseCaseTask")
    public Response isHasCloseCaseTask(TLcTask tLcTask, HttpServletRequest request) {
        Integer count = this.tLcTaskService.isHasCTSTask(tLcTask);
        return Response.success(count);
    }

    /**
     * 协助催收申请
     *
     * @param userId
     * @param taskIds
     * @param ownerIds
     */
    @ResponseBody
    @PostMapping("/helpColl")
    public AjaxResult helpColl(String userId, String taskIds, String ownerIds) {
        this.tLcTaskService.helpColl(userId, taskIds, ownerIds);
        return AjaxResult.success();
    }

    /**
     * 跳转到临时代理页面
     *
     * @param taskIds
     * @param ownerIds
     * @param modelMap
     * @return
     */
    @GetMapping("/toTempAgent")
    public String toTempAgent(String taskIds, String ownerIds, ModelMap modelMap) {
        modelMap.put("orgId", ShiroUtils.getSysUser().getOrgId());
        modelMap.put("taskIds", taskIds);
        modelMap.put("ownerIds", ownerIds);
        return prefix + "/tempAgent";
    }

    /**
     * 临时代理
     *
     * @param userId
     * @param taskIds
     * @param orgId
     */
    @ResponseBody
    @PostMapping("/tempAgent")
    public AjaxResult tempAgent(String userId, String taskIds, String orgId, String oldOwnerIds) {
        this.tLcTaskService.tempAgent(userId, taskIds, orgId, oldOwnerIds);
        return AjaxResult.success();
    }

    /**
     * 临时代理回收
     *
     * @param oldOwnerIds
     * @param taskIds
     */
    @ResponseBody
    @PostMapping("/tempAgentRecycle")
    public AjaxResult tempAgentRecycle(String oldOwnerIds, String taskIds) {
        this.tLcTaskService.tempAgentRecycle(oldOwnerIds, taskIds);
        return AjaxResult.success();
    }

    /**
     * 结案
     *
     * @param taskIds
     */
    @ResponseBody
    @GetMapping("/closeCase")
    public AjaxResult closeCase(String taskIds, String closeCaseType) {
        this.tLcTaskService.closeCase(taskIds, closeCaseType);
        return AjaxResult.success();
    }

    @ResponseBody
    @GetMapping("/closeAllCase")
    public AjaxResult closeCase(TLcTask tLcTask, String closeCaseType, HttpServletRequest request) {
        String callCodeHistoryListStr = request.getParameter("callCodeHistoryListStr");//历史电话码
        if(StringUtils.isNotEmpty(callCodeHistoryListStr) && !"null".equals(callCodeHistoryListStr)){
            tLcTask.setCallCodeHistoryList(Arrays.asList(callCodeHistoryListStr.split(",")));
        }
        this.tLcTaskService.closeAllCase(tLcTask, closeCaseType);
        return AjaxResult.success();
    }

    /**
     * 发送给机器人，机器人协催
     *
     * @param taskIds
     */
    @ResponseBody
    @PostMapping("/sendRobot")
    public AjaxResult sendRobot(String taskIds, String orgId, String speechcraftIdAndSceneDefId, Integer callLineId, String sendRobotBatchNos) {
        try {
            return this.tLcTaskService.sendRobot(taskIds, ShiroUtils.getSysUser().getOrgId().toString(), speechcraftIdAndSceneDefId, callLineId, sendRobotBatchNos);
        } catch (Exception e) {
            log.error("推送到机器人失败，error is {}", e);
            return AjaxResult.success(AjaxResult.Type.ERROR, "推送失败", null);
        }
    }

    /**
     * 发送给机器人，机器人协催
     *
     * @param tLcTask
     */
    @ResponseBody
    @PostMapping("/allDataSendRobot")
    public AjaxResult allDataSendRobot(String speechcraftIdAndSceneDefId, TLcTask tLcTask, Integer callLineId, HttpServletRequest request) {
        String callCodeHistoryListStr = request.getParameter("callCodeHistoryListStr");//历史电话码
        if(StringUtils.isNotEmpty(callCodeHistoryListStr) && !"null".equals(callCodeHistoryListStr)){
            tLcTask.setCallCodeHistoryList(Arrays.asList(callCodeHistoryListStr.split(",")));
        }
        return this.tLcTaskService.allDataSendRobot(speechcraftIdAndSceneDefId, tLcTask, callLineId);
    }

    /**
     * 跳转到机器人协催页面
     *
     * @param taskIds
     */
    @GetMapping("/toSendRobot")
    public String toSendRobot(String taskIds, String orgId, ModelMap modelMap,String sendRobotBatchNos) {
        modelMap.put("taskIds", taskIds);
        modelMap.put("orgId", orgId);
        modelMap.put("sendRobotBatchNos", sendRobotBatchNos);
        return prefix + "/sendRobot";
    }

    /**
     * 跳转到机器人协催页面
     *
     * @param tLcTask
     * @param modelMap
     * @return
     */
    @GetMapping("/allDataToSendRobot")
    public String allDataToSendRobot(TLcTask tLcTask, ModelMap modelMap, HttpServletRequest request) {
        String callCodeHistoryListStr = request.getParameter("callCodeHistoryListStr");//历史电话码

        modelMap.put("callCodeHistoryListStr", callCodeHistoryListStr);
        modelMap.put("tLcTask", tLcTask);
        return prefix + "/allDataSendRobot";
    }

    /**
     * 还款接口-给资产管理提供接口
     *
     * @param closeCaseUser
     */
    @ResponseBody
    @PostMapping("/closeCaseInteface")
    public Response closeCaseInteface(@RequestBody CloseCaseUser closeCaseUser) {
        this.tLcTaskService.closeCaseByCaseNo(closeCaseUser);
        return Response.success(Response.ResponseStatusEnum.SUCCESS.getStatus(), "结案成功", null);
    }

    @ResponseBody
    @PostMapping("/initOrg")
    public TableDataInfo initOrg() {
        Set<Long> deptIds = dataPermissionUtil.selectDataPer();
        OrgPackage orgPackage = new OrgPackage();
        orgPackage.setDeptIds(deptIds);
        List<OrgPackage> sysDeptList = this.orgPackageService.selectOrgPackageList(orgPackage);
        return getDataTable(sysDeptList);
    }

    @ResponseBody
    @PostMapping("/initCallRecordCode")
    public TableDataInfo initCallRecordCode() {
        List<SysDictData> sysDictDataList = this.sysDictDataService.selectDictDataByType("call_record_code");
        List<ActionCodeObj> callRecordCodeList = sysDictDataList.stream()
                .map(sysDictData -> ActionCodeObj.builder().code(sysDictData.getDictValue()).message(sysDictData.getDictLabel()).build())
                .collect(Collectors.toList());
        return getDataTable(callRecordCodeList);
    }

    @ResponseBody
    @PostMapping("/getDictCallCode")
    public String getDictCallCode(SysDictData sysDictData) {
        String dictLabel = this.sysDictDataService.selectDictLabel(sysDictData.getDictType(), sysDictData.getDictValue());
        return dictLabel;
    }

    @ResponseBody
    @PostMapping("/initCallCode")
    public TableDataInfo initCallCode() {
        List<SysDictData> sysDictDataList = this.sysDictDataService.selectDictDataByType("call_record_code");
        List<ActionCodeObj> callRecordCodeList = sysDictDataList.stream()
                .map(sysDictData -> ActionCodeObj.builder().code(sysDictData.getDictValue()).message(sysDictData.getDictLabel()).build())
                .collect(Collectors.toList());
        return getDataTable(callRecordCodeList);
    }

//    @ResponseBody
//    @PostMapping("/initColumnQuery")
//    public TableDataInfo initColumnQuery() {
//        TLcColumnQuery tLcColumnQuery = TLcColumnQuery.builder().orgId(ShiroUtils.getSysUser().getOrgId()).tableName(TableEnum.TASK.getTableName()).build();
//        List<TLcColumnQuery> columnQueryList = this.columnQueryService.selectTLcColumnQueryList(tLcColumnQuery);
//        return getDataTable(columnQueryList);
//    }

    @ResponseBody
    @PostMapping("/initActionCode")
    public TableDataInfo initActionCode() {
//        List<ActionCodeObj> actionCodeList = Arrays.stream(CollActionCodeEnum.values())
//                .map(actionCode -> ActionCodeObj.builder().code(actionCode.getCode()).message(actionCode.getMessage()).build())
//                .collect(Collectors.toList());
        List<SysDictData> sysDictDataList = this.sysDictDataService.selectDictDataByType("action_code");
        List<ActionCodeObj> actionCodeList = sysDictDataList.stream()
                .map(sysDictData -> ActionCodeObj.builder().code(sysDictData.getDictValue()).message(sysDictData.getDictLabel()).build())
                .collect(Collectors.toList());
        return getDataTable(actionCodeList);
    }

    @ResponseBody
    @PostMapping("/initTaskType")
    public TableDataInfo initTaskType() {
        List<ActionCodeObj> taskTypeList = Arrays.stream(TaskTypeEnum.values())
                .map(taskType -> ActionCodeObj.builder().code(taskType.getCode().toString()).message(taskType.getMessage()).build())
                .collect(Collectors.toList());
        return getDataTable(taskTypeList);
    }

    @ResponseBody
    @PostMapping("/initAllocatRule")
    public TableDataInfo initAllocatRule() {
        List<ActionCodeObj> actionCodeList = Arrays.stream(AllocatRuleEnum.values())
                .map(actionRule -> ActionCodeObj.builder().code(actionRule.getCode().toString()).message(actionRule.getMessage()).build())
                .collect(Collectors.toList());
        return getDataTable(actionCodeList);
    }

    /**
     * 导出任务列表
     */
    @RequiresPermissions("collect:task:export")
    @Log(title = "任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TLcTask tLcTask, HttpServletRequest request) {
        if (tLcTask.getEndRecentlyAllotDate() != null) {
            tLcTask.setEndRecentlyAllotDate(DateUtils.getEndOfDay(tLcTask.getEndRecentlyAllotDate()));
        }
        if (tLcTask.getEndRecentlyFollowUpDate() != null) {
            tLcTask.setEndRecentlyFollowUpDate(DateUtils.getEndOfDay(tLcTask.getEndRecentlyFollowUpDate()));
        }
        if (tLcTask.getEndEnterCollDate() != null) {
            tLcTask.setEndEnterCollDate(DateUtils.getEndOfDay(tLcTask.getEndEnterCollDate()));
        }
        //行动码 电话码 转多选
        String callCode = request.getParameter("callSign");//电话码
        if(StringUtils.isNotEmpty(callCode)){
            tLcTask.setCallCodeList(Arrays.asList(callCode.split(",")));
            tLcTask.setCallCode(null);
            tLcTask.setCallSign(null);
        }
        String actionCode = request.getParameter("actionCode");//行动码
        if(StringUtils.isNotEmpty(actionCode)){
            tLcTask.setActionCodeList(Arrays.asList(actionCode.split(",")));
            tLcTask.setActionCode(null);
        }
        String callCodeHistoryListStr = request.getParameter("callCodeHistoryListStr");//历史电话码
        if(StringUtils.isNotEmpty(callCodeHistoryListStr) && !"null".equals(callCodeHistoryListStr)){
            tLcTask.setCallCodeHistoryList(Arrays.asList(callCodeHistoryListStr.split(",")));
        }
        List<TLcTask> list = tLcTaskService.selectTLcTaskByPage(tLcTask);
        ExcelUtil<TLcTask> util = new ExcelUtil<TLcTask>(TLcTask.class);
        return util.exportExcel(list, "task");
    }

    /**
     * 坐席推送机器人申请
     */
    @PostMapping("/sendRobotApply")
    @ResponseBody
    public Response sendRobotApply(String taskIds) {
        return this.tLcTaskService.sendRobotApply(taskIds);
    }

    /**
     * 新增任务
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存任务
     */
    @RequiresPermissions("collect:task:add")
    @Log(title = "任务", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TLcTask tLcTask) {
        return toAjax(tLcTaskService.insertTLcTask(tLcTask));
    }

    /**
     * 修改任务
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        TLcTask tLcTask = tLcTaskService.selectTLcTaskById(id);
        mmap.put("tLcTask", tLcTask);
        return prefix + "/edit";
    }

    /**
     * 修改保存任务
     */
    @RequiresPermissions("collect:task:edit")
    @Log(title = "任务", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TLcTask tLcTask) {
        return toAjax(tLcTaskService.updateTLcTask(tLcTask));
    }

    /**
     * 删除任务
     */
    @RequiresPermissions("collect:task:remove")
    @Log(title = "任务", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tLcTaskService.deleteTLcTaskByIds(ids));
    }


    /**
     * 跳转初始化页面
     */

    @GetMapping("/phoneInit")
    public String phoneInit() {
        return prefix + "/phoneInit";
    }

    /**
     * 跳转签入页面
     */

    @PostMapping("/getProxyUrl")
    @ResponseBody
    public String getProxyUrl(String agentId) {
        String url = "https://unicom.hisancc.net:10094/config/agent?agentId=" + agentId;
        String forObject = restTemplateUtil.getRestTemplate().getForObject(url, String.class);
        return forObject;
    }


    /**
     * 跳转签入页面
     */
    @GetMapping("/login")
    public String login(String agentId, String proxyUrl, String skillDesc, String dialPrefix, String dialCaller, ModelMap mmap) {
        mmap.put("agentId", agentId);
        mmap.put("proxyUrl", proxyUrl);
        mmap.put("skillDesc", skillDesc);
        mmap.put("dialPrefix", dialPrefix);
        mmap.put("dialCaller", dialCaller);
        return prefix + "/phoneOpt";
    }

    /**
     * 查询所有分机号码
     *
     * @return
     */
    @PostMapping("/selectExtPhoneList")
    @ResponseBody
    public AjaxResult selectExtPhoneList() {
        ExtPhone extPhone = new ExtPhone();
        extPhone.setIsused("0");
        List<ExtPhone> list = extPhoneService.selectExtPhoneList(extPhone);
        String result = JSON.toJSONString(list);
        return AjaxResult.success(result);
    }

    /**
     * 查询所有分机号码
     *
     * @return
     */
    @PostMapping("/selectExtNumBySeat")
    @ResponseBody
    public AjaxResult selectExtNumBySeat(String seatId, String agentId, String callPlatform) {
        List<String> exonNumGroupList = this.extPhoneService.selectExtNumBySeat(seatId, agentId, callPlatform);
        String result = JSON.toJSONString(exonNumGroupList);
        return AjaxResult.success(result);
    }

    /**
     * 根据分机号码查询分机初始化信息
     *
     * @param agentId
     * @return
     */
    @PostMapping("/selectExtPhone")
    @ResponseBody
    public AjaxResult selectExtPhone(String agentId) {
        ExtPhone extPhone = new ExtPhone();
        extPhone.setAgentid(agentId);
        List<ExtPhone> list = extPhoneService.selectExtPhoneList(extPhone);
        String result = JSON.toJSONString(list);
        return AjaxResult.success(result);
    }

    /**
     * 跳转签入页面
     */
    @GetMapping("/recordAudio")
    public String recordAudio(String id, ModelMap mmap) {
        TLcCallRecord tLcCallRecord = tLcCallRecordService.selectTLcCallRecordById(Long.valueOf(id));
        String callRadioLocation = tLcCallRecord.getCallRadioLocation();
//        mmap.put("callRadioLocation", remoteConfigure.getRecordUrl() + callRadioLocation);
        mmap.put("callRadioLocation", callRadioLocation);
        return prefix + "/recordAudio";
    }
/*

    @RequestMapping(value = "/downRemoteRecord")
    @ResponseBody
    public AjaxResult downTemplate(HttpServletRequest request,HttpServletResponse response,String recordFile,String startTime,String endTime, String recordDuration) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            String[] split = recordFile.split("/");
            String fileName = split[split.length-1];
            Date callStartTime = sdf.parse(startTime);
            Date callEndTime = sdf.parse(endTime);

            String urlpath = "https://unicom.hisancc.net:10092"+recordFile;
            String path = "E:\\usr\\local\\temp\\recordFile" + File.separatorChar + fileName;
            File file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

           */
/* URL url = new URL(urlpath);
            URLConnection  urlconn = url.openConnection();
            HttpURLConnection conn = (HttpURLConnection) urlconn;
            conn.setRequestProperty("Content-type", "application/x-java-serialized-object");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.connect();
            InputStream inputStream = conn.getInputStream();
*//*

            InputStream inputStream = httpsUtil.getInputStreamFromUrlHttps(urlpath);
            OutputStream outStream =  new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int count = -1;
            while ((count = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, count);
            }
            outStream.flush();
            outStream.close();
            inputStream.close();
            //上传fastDFS
            String fastUrl = FastDFSUtil.UploadFile(path);
            //file.delete();
            //保存通话记录
            Map<String,String> map = new HashMap<String,String>();
            map.put("callStartTime",startTime);
            map.put("callEndTime",endTime);
            map.put("callLen",recordDuration);
            map.put("callRadioLocation",fastUrl);
            String result = JSON.toJSONString(map);
            return AjaxResult.success(result);
        } catch (Exception e) {
            logger.error("执行方法downRecord异常：", e);
            return AjaxResult.success("失败");
        }


    }

*/


    /**
     * 新增电催记录
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/addCallRecord")
    public Response addCallRecord(TLcCallRecord tLcCallRecord, String importBatchNo, String callStartTime, String callEndTime) {
        return this.tLcTaskService.addCallRecord(tLcCallRecord, importBatchNo, callStartTime, callEndTime);
    }

    /**
     * 从平安下载录音
     *
     * @param request
     * @param response
     * @param id
     */
    @GetMapping("/downLoad")
    @ResponseBody
    public void downLoad(HttpServletRequest request, HttpServletResponse response, String id) {
        this.tLcCallRecordService.downLoadRadio(request, response, id);
    }

//    /**
//     * 从文件服务器下载录音
//     * @param request
//     * @param response
//     * @param id
//     */
//    @GetMapping("/downLoad")
//    @ResponseBody
//    public void downLoad(HttpServletRequest request, HttpServletResponse response,String id){
//        try {
//            //查询录音地址
//            TLcCallRecord tLcCallRecord = tLcCallRecordService.selectTLcCallRecordById(Long.valueOf(id));
//            String callRadioLocation = tLcCallRecord.getCallRadioLocation();
//            String phone = tLcCallRecord.getPhone();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//            Date date = new Date();
//            if(callRadioLocation != null && !"".equals(callRadioLocation)){
//                String url = callRadioLocation;
////                String[] split = callRadioLocation.split("/");
////                String fileName = split[split.length-1];
//                String fileName = sdf.format(date)+"_"+phone+".mp3";
//                response.reset();
//                response.setContentType("text/plain");
//                response.setCharacterEncoding("UTF-8");
//                response.setHeader("Content-Disposition", "attachment; filename="
//                        + new String(fileName.getBytes("gb2312"),"iso-8859-1"));
//
//                OutputStream outStream = response.getOutputStream();
//                byte[] bytes = FastDFSUtil.DownloadFile(url);
//                ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
//                byte[] buffer = new byte[1024];
//                int count = -1;
//                while ((count = inputStream.read(buffer)) != -1) {
//                    outStream.write(buffer, 0, count);
//
//                }
//                outStream.flush();
//                outStream.close();
//                inputStream.close();
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            logger.error("执行方法downLoad异常：", e);
//        } catch (MyException e) {
//            e.printStackTrace();
//        }
//
//    }


    /**
     * 更新停拨标识
     */
    @PostMapping("/updateIsClose/{isClose}")
    @ResponseBody
    public AjaxResult updateIsClose(@PathVariable("isClose") String isClose, String caseNo, String phone) {
        Map<String, String> updateMap = new HashMap<String, String>();
//        updateMap.put("isClose", isClose);
//        updateMap.put("caseNo", caseNo);
//        updateMap.put("phone", phone);
        TLcCustContact tLcCustContact = new TLcCustContact();
        tLcCustContact.setCaseNo(caseNo);
        tLcCustContact.setIsClose(isClose);
        tLcCustContact.setPhone(phone);
        TLcTask tLcTask = this.tLcTaskService.selectTaskByCaseAndNoCloseCase(caseNo);
        if (isClose.equals("1")) {
            // 停止拨号，添加到机器人黑名单
            this.robotBlackService.insertTLcRobotBlack(tLcTask, RobotBlackReasonEnum.STOP_CALL.getReason(), phone);
        } else if (isClose.equals("0")) {
            // 取消停拨，从机器人黑名单移除
            TLcRobotBlack robotBlack = TLcRobotBlack.builder().caseNo(tLcTask.getCaseNo()).importBatchNo(tLcTask.getImportBatchNo()).phone(phone).reason(RobotBlackReasonEnum.STOP_CALL.getReason()).build();
            this.robotBlackService.deleteobotBlackByCaseReason(robotBlack);
        }
        int i = tLcCustContactService.updateTLcCustContact(tLcCustContact);
        return toAjax(i);
    }

    @GetMapping(value = "/selfBuild")
    public String selfBuild() {
        return prefix + "/demo";
    }
}


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
class ActionCodeObj {
    private String code;
    private String message;
}