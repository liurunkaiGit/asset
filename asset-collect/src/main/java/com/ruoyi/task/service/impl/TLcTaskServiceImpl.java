package com.ruoyi.task.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.assetspackage.domain.CurAssetsPackage;
import com.ruoyi.assetspackage.domain.CurAssetsRepaymentPackage;
import com.ruoyi.assetspackage.domain.RemoteConfigure;
import com.ruoyi.assetspackage.service.ICurAssetsPackageService;
import com.ruoyi.assetspackage.service.ICurAssetsRepaymentPackageService;
import com.ruoyi.assetspackage.service.IOrgPackageService;
import com.ruoyi.callConfig.domain.TLcCallStrategyConfig;
import com.ruoyi.caseConfig.domain.TLcAllocatCaseConfig;
import com.ruoyi.caseConfig.service.ITLcAllocatCaseConfigService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.domain.CloseCase;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.RestTemplateUtil;
import com.ruoyi.duncase.domain.Assets;
import com.ruoyi.duncase.domain.AssetsRepayment;
import com.ruoyi.duncase.domain.TLcDuncase;
import com.ruoyi.duncase.domain.TLcDuncaseAssign;
import com.ruoyi.duncase.mapper.TLcDuncaseAssignMapper;
import com.ruoyi.duncase.mapper.TLcDuncaseMapper;
import com.ruoyi.duncase.service.ITLcDuncaseAssignService;
import com.ruoyi.enums.*;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.orgSpeechConf.domain.TLcOrgSpeechcraftConf;
import com.ruoyi.orgSpeechConf.service.ITLcOrgSpeechcraftConfService;
import com.ruoyi.robot.domain.TLcRobotBlack;
import com.ruoyi.robot.domain.TLcRobotTask;
import com.ruoyi.robot.domain.TLcSendRobotApply;
import com.ruoyi.robot.enums.LocalRobotTaskStatus;
import com.ruoyi.robot.enums.RobotBlackReasonEnum;
import com.ruoyi.robot.service.ITLcRobotBlackService;
import com.ruoyi.robot.service.ITLcSendRobotApplyService;
import com.ruoyi.robot.utils.RobotMethodUtil;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.task.domain.CollJob;
import com.ruoyi.task.domain.TLcCallRecord;
import com.ruoyi.task.domain.TLcTask;
import com.ruoyi.task.mapper.TLcTaskMapper;
import com.ruoyi.task.service.ITLcCallRecordService;
import com.ruoyi.task.service.ITLcTaskService;
import com.ruoyi.utils.AllocatRuleUtil;
import com.ruoyi.utils.CloseCaseUser;
import com.ruoyi.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClientException;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 任务Service业务层处理
 *
 * @author liurunkai
 * @date 2019-12-25
 */
@Slf4j
@Service("com.ruoyi.task.service.impl.TLcTaskServiceImpl")
public class TLcTaskServiceImpl implements ITLcTaskService {

    private static final String COMPARE_METHOD = "compareMethod_";

    @Value("${asset.viewRepayHisUrl}")
    private String viewRepayHisUrl;
    @Value("${asset.closeCaseUrl}")
    private String closeCaseUrl;
    @Value("${asset.getAssetsUrl}")
    private String getAssetsUrl;

    @Autowired
    private TLcTaskMapper tLcTaskMapper;
    @Autowired
    private TLcDuncaseAssignMapper tLcDuncaseAssignMapper;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ITLcDuncaseAssignService duncaseAssignService;
    @Autowired
    private TLcDuncaseMapper tLcDuncaseMapper;
    @Autowired
    private IOrgPackageService orgPackageService;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RobotMethodUtil robotMethodUtil;
    @Autowired
    private ITLcCallRecordService tLcCallRecordService;
    @Autowired
    private ISysDictDataService sysDictDataService;
    @Autowired
    private RemoteConfigure remoteConfigure;
    @Autowired
    private ITLcAllocatCaseConfigService caseConfigService;
    @Autowired
    private ITLcOrgSpeechcraftConfService orgSpeechcraftConfService;
    @Autowired
    private ICurAssetsPackageService curAssetsPackageService;
    @Autowired
    private ITLcRobotBlackService robotBlackService;
    @Autowired
    private ICurAssetsRepaymentPackageService curAssetsRepaymentPackageService;
    @Autowired
    private ITLcSendRobotApplyService sendRobotApplyService;

    /**
     * 查询任务
     *
     * @param id 任务ID
     * @return 任务
     */
    @Override
    public TLcTask selectTLcTaskById(Long id) {
        return tLcTaskMapper.selectTLcTaskById(id);
    }

    /**
     * 查询任务列表
     *
     * @param tLcTask 任务
     * @return 任务
     */
    @Override
    public List<TLcTask> selectTLcTaskList(TLcTask tLcTask) {
        //tLcTask.setDeptIds(ShiroUtils.getSysUser().getDeptIds());
        if (tLcTask.getEndRecentlyAllotDate() != null) {
            tLcTask.setEndRecentlyAllotDate(DateUtils.getEndOfDay(tLcTask.getEndRecentlyAllotDate()));
        }
        if (tLcTask.getEndRecentlyFollowUpDate() != null) {
            tLcTask.setEndRecentlyFollowUpDate(DateUtils.getEndOfDay(tLcTask.getEndRecentlyFollowUpDate()));
        }
        return tLcTaskMapper.selectTLcTaskList(tLcTask);
    }

    /**
     * 查询任务列表
     *
     * @param tLcTask 任务
     * @return 任务
     */
    @Override
    public List<TLcTask> selectTLcTaskByPage(TLcTask tLcTask) {
//        setCustomSql(tLcTask, request);

        return tLcTaskMapper.selectTLcTaskByPage(tLcTask);
    }

//    private void setCustomSql(TLcTask tLcTask, HttpServletRequest request) {
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        if (tLcTask.getEndRecentlyAllotDate() != null) {
//            tLcTask.setEndRecentlyAllotDate(DateUtils.getEndOfDay(tLcTask.getEndRecentlyAllotDate()));
//        }
//        if (tLcTask.getEndRecentlyFollowUpDate() != null) {
//            tLcTask.setEndRecentlyFollowUpDate(DateUtils.getEndOfDay(tLcTask.getEndRecentlyFollowUpDate()));
//        }
//        StringBuilder sb = new StringBuilder();
//        if (tLcTask.getStart_arrears_total() != null) {
//            sb.append(" and t.arrears_total >= " + tLcTask.getStart_arrears_total());
//        }
//        if (tLcTask.getEnd_arrears_total() != null) {
//            sb.append(" and t.arrears_total <= " + tLcTask.getEnd_arrears_total());
//        }
//        if (tLcTask.getStart_close_case_yhje() != null) {
//            sb.append(" and t.close_case_yhje >= " + tLcTask.getStart_close_case_yhje());
//        }
//        if (tLcTask.getEnd_close_case_yhje() != null) {
//            sb.append(" and t.close_case_yhje <= " + tLcTask.getEnd_close_case_yhje());
//        }
//        if (StringUtils.isNoneBlank(tLcTask.getTransfer_type())) {
//            for (Map.Entry<String, String[]> map : parameterMap.entrySet()) {
//                if (map.getKey().equals("compareMethod_transfer_type")) {
//                    List<String> values = Arrays.asList(map.getValue());
//                    if (values.get(0).equals("equal")) {
//                        sb.append(" and t.transfer_type = '" + tLcTask.getTransfer_type() + "'");
//                    }
//                    if (values.get(0).equals("like")) {
//                        sb.append(" and t.transfer_type like '%" + tLcTask.getTransfer_type() + "%'");
//                    }
//                    break;
//                }
//            }
//        }
//        if (StringUtils.isNoneBlank(tLcTask.getPhone())) {
//            for (Map.Entry<String, String[]> map : parameterMap.entrySet()) {
//                if (map.getKey().equals("compareMethod_phone")) {
//                    List<String> values = Arrays.asList(map.getValue());
//                    if (values.get(0).equals("equal")) {
//                        sb.append(" and t.phone = " + tLcTask.getPhone());
//                    }
//                    if (values.get(0).equals("like")) {
//                        sb.append(" and t.phone like '%" + tLcTask.getPhone() + "%'");
//                    }
//                    break;
//                }
//            }
//        }
//        if (StringUtils.isNoneBlank(tLcTask.getOwner_name())) {
//            for (Map.Entry<String, String[]> map : parameterMap.entrySet()) {
//                if (map.getKey().equals("compareMethod_owner_name")) {
//                    List<String> values = Arrays.asList(map.getValue());
//                    if (values.get(0).equals("equal")) {
//                        sb.append(" and t.owner_name = '" + tLcTask.getOwner_name() + "'");
//                    }
//                    if (values.get(0).equals("like")) {
//                        sb.append(" and t.owner_name like '%" + tLcTask.getOwner_name() + "%'");
//                    }
//                    break;
//                }
//            }
//        }
//        if (StringUtils.isNoneBlank(tLcTask.getCall_sign())) {
//            sb.append(" and t.call_sign = '" + tLcTask.getCall_sign() + "'");
//        }
//        if (StringUtils.isNoneBlank(tLcTask.getAction_code())) {
//            sb.append(" and t.action_code = '" + tLcTask.getAction_code() + "'");
//        }
//        if (StringUtils.isNoneBlank(tLcTask.getTask_type())) {
//            sb.append(" and t.task_type = " + tLcTask.getTask_type());
//        }
//        if (tLcTask.getStart_enter_coll_date() != null) {
//            sb.append(" and t.enter_coll_date >= '" + DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, tLcTask.getStart_enter_coll_date()) + "'");
//        }
//        if (tLcTask.getEnd_enter_coll_date() != null) {
//            sb.append(" and t.enter_coll_date <= '" + DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, DateUtils.getEndOfDay(tLcTask.getEnd_enter_coll_date())) + "'");
//        }
//        if (tLcTask.getStart_recently_allot_date() != null) {
//            sb.append(" and t.recently_allot_date >= '" + DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, tLcTask.getStart_recently_allot_date()) + "'");
//        }
//        if (tLcTask.getEnd_recently_allot_date() != null) {
//            sb.append(" and t.recently_allot_date <= '" + DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, DateUtils.getEndOfDay(tLcTask.getEnd_recently_allot_date())) + "'");
//        }
//        if (tLcTask.getStart_recently_follow_up_date() != null) {
//            sb.append(" and t.recently_follow_up_date >= '" + DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, tLcTask.getStart_recently_follow_up_date()) + "'");
//        }
//        if (tLcTask.getEnd_recently_follow_up_date() != null) {
//            sb.append(" and t.recently_follow_up_date <= '" + DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, DateUtils.getEndOfDay(tLcTask.getEnd_recently_follow_up_date())) + "'");
//        }
//        log.info("动态查询的sql是{}", sb.toString());
//        if (StringUtils.isNoneBlank(sb.toString())) {
//            tLcTask.setSql(sb.toString());
//        }
//    }

    /**
     * 新增任务
     *
     * @param tLcTask 任务
     * @return 结果
     */
    @Override
    public int insertTLcTask(TLcTask tLcTask) {
        tLcTask.setCreateTime(DateUtils.getNowDate());
        return tLcTaskMapper.insertTLcTask(tLcTask);
    }

    /**
     * 修改任务
     *
     * @param tLcTask 任务
     * @return 结果
     */
    @Override
    public int updateTLcTask(TLcTask tLcTask) {
        return tLcTaskMapper.updateTLcTask(tLcTask);
    }

    /**
     * 删除任务对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcTaskByIds(String ids) {
        return tLcTaskMapper.deleteTLcTaskByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除任务信息
     *
     * @param id 任务ID
     * @return 结果
     */
    @Override
    public int deleteTLcTaskById(Long id) {
        return tLcTaskMapper.deleteTLcTaskById(id);
    }

    @Override
    public void batchInsertTask(List<TLcTask> taskList) {
        this.tLcTaskMapper.batchInsertTask(taskList);
    }

    /**
     * 根据证件号码查询对应的任务详情
     *
     * @param certificateNo
     * @return
     */
    @Override
    public TLcTask findTaskByCertificateNo(String certificateNo) {
        return this.tLcTaskMapper.findTaskByCertificateNo(certificateNo);
    }

    @Override
    @Transactional
    public void reAllocat(String userId, String taskIds, String orgId) {
        // 修改任务的owner_id为userId
        this.tLcTaskMapper.updateOwnerById(Arrays.stream(taskIds.split(",")).collect(Collectors.toList()), userId);
        // 构建案件历史轨迹对象
        List<TLcDuncaseAssign> duncaseAssignList = Arrays.stream(taskIds.split(","))
                .map(taskId -> {
                    TLcTask tLcTask = this.tLcTaskMapper.selectTLcTaskById(Long.valueOf(taskId));
                    TLcDuncaseAssign tLcDuncaseAssign = TLcDuncaseAssign.builder()
                            .ownerId(Long.valueOf(userId))
                            .taskId(taskId)
                            .operationId(ShiroUtils.getUserId())
                            .customName(tLcTask.getCustomName())
                            .collectTeamName(tLcTask.getCollectTeamName())
                            .collectTeamId(tLcTask.getCollectTeamId())
                            .certificateNo(tLcTask.getCertificateNo())
                            .caseNo(tLcTask.getCaseNo())
                            .operationName(ShiroUtils.getSysUser().getUserName())
                            .transferType(TaskTypeEnum.RE_ALLOCAT.getCode())
                            .orgId(orgId)
                            .taskStatus(tLcTask.getTaskStatus())
                            .validateStatus(IsNoEnum.IS.getCode())
                            .build();
                    return tLcDuncaseAssign;
                }).collect(Collectors.toList());
        // 将该任务添加到案件历史轨迹表
        this.tLcDuncaseAssignMapper.batchInsertDuncaseAssign(duncaseAssignList);
    }

    /**
     * 重新分配
     *
     * @param userIds
     * @param taskIds
     * @param orgId
     * @param allocatNum
     * @param allocatRule
     */
    @Override
    public AjaxResult reAllocat(String userIds, String taskIds, String orgId, Integer allocatNum, Integer allocatRule) {
        // 判断需要分配全部任务还是随机分配指定数量的任务
        Set<Long> randomTaskSet = getAllocatTaskSet(taskIds, allocatNum);
        List<TLcTask> taskList = randomTaskSet.stream()
                .map(taskId -> {
                    TLcTask tLcTask = this.tLcTaskMapper.selectTLcTaskById(taskId);
                    tLcTask.setTaskType(TaskTypeEnum.RE_ALLOCAT.getCode());
                    tLcTask.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus());
                    tLcTask.setModifyOwnerTime(LocalDateTime.now(ZoneId.systemDefault()));
                    tLcTask.setRecentlyAllotDate(new Date());
                    return tLcTask;
                }).collect(Collectors.toList());
        List<SysUser> userList = Arrays.stream(userIds.split(","))
                .map(userId -> this.sysUserService.selectUserById(Long.valueOf(userId)))
                .collect(Collectors.toList());
        // 分配任务
        taskList = allocatTask(allocatRule, taskList, userList);
        this.tLcTaskMapper.batchUpdateTask(taskList);
//        this.asyncITLcDuncaseService.updateDuncase(taskList,TaskTypeEnum.RE_ALLOCAT.getCode(),TaskStatusEnum.ALLOCATING.getStatus());
//        // 修改案件表
//        updateDuncase(taskList,TaskTypeEnum.RE_ALLOCAT.getCode(),TaskStatusEnum.ALLOCATING.getStatus());
//        // 添加到案件轨迹表中
        insertDuncaseAssign(taskList, ShiroUtils.getSysUser());
        return AjaxResult.success();
    }

    @Override
    public AjaxResult allDataReAllocat(String userIds, TLcTask tLcTask, Integer allocatNum, Integer allocatRule) {
        // 查询所有的任务
        List<TLcTask> taskList = this.tLcTaskMapper.selectTaskList(tLcTask);
        if (allocatNum != null) {
            // 随机获取要分配 allocatNum 个的任务
            Set<TLcTask> randomTaskSet = getAllocatTaskSet(taskList, allocatNum);
            // set 转 list
            taskList = randomTaskSet.stream().collect(Collectors.toList());
        }
        List<SysUser> userList = Arrays.stream(userIds.split(","))
                .map(userId -> this.sysUserService.selectUserById(Long.valueOf(userId)))
                .collect(Collectors.toList());
        // 分配任务
        taskList = allocatTask(allocatRule, taskList, userList);
        if (taskList != null && taskList.size() > 0) {
            this.tLcTaskMapper.batchUpdateTask(taskList);
            // 异步更新案件表信息
//        this.asyncITLcDuncaseService.updateDuncase(taskList,TaskTypeEnum.RE_ALLOCAT.getCode(),TaskStatusEnum.ALLOCATING.getStatus());
//        // 修改案件表
//        updateDuncase(taskList,TaskTypeEnum.RE_ALLOCAT.getCode(),TaskStatusEnum.ALLOCATING.getStatus());
//        // 添加到案件轨迹表中
            insertDuncaseAssign(taskList, ShiroUtils.getSysUser());
        }

        return AjaxResult.success();
    }

    /**
     * 修改案件信息
     *
     * @param taskList
     */
    private void updateDuncase(List<TLcTask> taskList, Integer taskType, Integer taskStatus) {
        List<TLcDuncase> duncaseList = taskList.stream().map(tLcTask -> {
            TLcDuncase tLcDuncase = this.tLcDuncaseMapper.findDuncaseByCaseNoAndImportBatchNo(tLcTask.getCaseNo(), tLcTask.getOrgId(), tLcTask.getImportBatchNo());
            tLcDuncase.setOwnerId(tLcTask.getOwnerId());
            tLcDuncase.setOwnerName(tLcTask.getOwnerName());
            tLcDuncase.setRecentlyAllotDate(new Date());
            tLcDuncase.setTaskType(taskType);
            tLcDuncase.setCaseStatus(taskStatus);
            return tLcDuncase;
        }).collect(Collectors.toList());
        this.tLcDuncaseMapper.batchUpdateDuncase(duncaseList);
    }

    /**
     * 当任务责任人变更之后修改案件表中的业务归属人
     *
     * @param taskList
     */
    private void updateDuncaseOwner(List<TLcTask> taskList) {
        List<TLcDuncase> duncaseList = taskList.stream().map(tLcTask -> {
            TLcDuncase tLcDuncase = this.tLcDuncaseMapper.findDuncaseByCaseNoAndImportBatchNo(tLcTask.getCaseNo(), tLcTask.getOrgId(), tLcTask.getImportBatchNo());
            tLcDuncase.setOwnerId(tLcTask.getOwnerId());
            tLcDuncase.setOwnerName(tLcTask.getOwnerName());
            return tLcDuncase;
        }).collect(Collectors.toList());
        this.tLcDuncaseMapper.batchUpdateDuncase(duncaseList);
    }

    /**
     * 临时代理
     *
     * @param userId
     * @param taskIds
     * @param orgId
     * @param oldOwnerIds
     */
    @Override
    public void tempAgent(String userId, String taskIds, String orgId, String oldOwnerIds) {
        List<TLcTask> taskList = Arrays.stream(taskIds.split(","))
                .map(taskId -> {
                    TLcTask tLcTask = this.tLcTaskMapper.selectTLcTaskById(Long.valueOf(taskId));
                    tLcTask.setTaskType(TaskTypeEnum.TEMP_AGENT.getCode())
                            .setOldOwnerId(Long.valueOf(tLcTask.getOwnerId()))
                            .setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus())
                            .setOwnerId(Long.valueOf(userId))
                            .setRecentlyAllotDate(new Date())
                            .setOwnerName(this.sysUserService.selectUserById(Long.valueOf(userId)).getUserName());
                    return tLcTask;
                }).collect(Collectors.toList());
        this.tLcTaskMapper.batchUpdateTask(taskList);
//        updateDuncaseOwner(taskList);
        // 插入案件历史轨迹
        insertDuncaseAssign(taskList, ShiroUtils.getSysUser());
    }

    /**
     * 临时代理回收
     *
     * @param oldOwnerIds
     * @param taskIds
     */
    @Override
    public void tempAgentRecycle(String oldOwnerIds, String taskIds) {
        List<TLcTask> taskList = Arrays.stream(taskIds.split(","))
                .map(taskId -> {
                    TLcTask tLcTask = this.tLcTaskMapper.selectTLcTaskById(Long.valueOf(taskId));
                    tLcTask.setTaskType(TaskTypeEnum.TEMP_AGENT_RECYCLE.getCode())
                            .setOwnerId(tLcTask.getOldOwnerId())
                            .setRecentlyAllotDate(new Date())
                            .setOwnerName(this.sysUserService.selectUserById(Long.valueOf(tLcTask.getOldOwnerId())).getUserName())
                            .setOldOwnerId(null);
                    return tLcTask;
                }).collect(Collectors.toList());
        this.tLcTaskMapper.batchUpdateTask(taskList);
//        updateDuncaseOwner(taskList);
        // 插入案件历史轨迹
        insertDuncaseAssign(taskList, ShiroUtils.getSysUser());
    }

    /**
     * 协助催收申请
     *
     * @param userId
     * @param taskIds
     * @param ownerIds
     */
    @Override
    public void helpColl(String userId, String taskIds, String ownerIds) {
        List<TLcTask> taskList = Arrays.stream(taskIds.split(","))
                .map(taskId -> {
                    TLcTask tLcTask = this.tLcTaskMapper.selectTLcTaskById(Long.valueOf(taskId));
                    tLcTask.setTaskType(TaskTypeEnum.HELP_COLLECT_APPLY.getCode())
                            .setOldOwnerId(tLcTask.getOwnerId())
                            .setOwnerId(Long.valueOf(userId))
                            .setOwnerName(this.sysUserService.selectUserById(Long.valueOf(userId)).getUserName())
                            .setOldOwnerName(this.sysUserService.selectUserById(Long.valueOf(tLcTask.getOldOwnerId())).getUserName());
//                    TLcDuncase tLcDuncase = this.tLcDuncaseMapper.findDuncaseByCaseNoAndImportBatchNo(tLcTask.getCaseNo(), tLcTask.getOrgId(), tLcTask.getImportBatchNo());
//                    tLcDuncase.setTaskType(TaskTypeEnum.HELP_COLLECT_APPLY.getCode());
//                    this.tLcDuncaseMapper.updateTLcDuncase(tLcDuncase);
                    return tLcTask;
                }).collect(Collectors.toList());
        this.tLcTaskMapper.batchUpdateTask(taskList);
//        updateDuncaseOwner(taskList);
        // 插入案件历史轨迹
        insertDuncaseAssign(taskList, ShiroUtils.getSysUser());
    }

    @Override
    public Long findDeptIdByOrgId(String orgId) {
        return this.tLcTaskMapper.findDeptIdByOrgId(orgId);
    }

    @Override
    @Transactional
    public void closeCase(String taskIds, String closeCaseType) {
        ArrayList<CloseCase> closeCaseList = new ArrayList<>();
        ArrayList<String> caseNoList = new ArrayList<>();
        List<TLcTask> tLcTaskList = Arrays.stream(taskIds.split(","))
                .map(taskId -> {
                    TLcTask tLcTask = this.tLcTaskMapper.selectTLcTaskById(Long.valueOf(taskId));
                    tLcTask.setTaskStatus(TaskStatusEnum.CLOSE.getStatus());
                    tLcTask.setTaskType(TaskTypeEnum.CLOSE_CASE_TRANSFER.getCode());
                    tLcTask.setCloseDate(new Date());
                    tLcTask.setModifyBy(ShiroUtils.getUserId());
//                    CloseCase closeCase = CloseCase.builder().caseNo(tLcTask.getCaseNo()).orgId(tLcTask.getOrgId()).importBatchNo(tLcTask.getImportBatchNo()).isExitCollect(IsNoEnum.NO.getCode().toString()).build();
                    CloseCase closeCase = CloseCase.builder().caseNo(tLcTask.getCaseNo()).orgId(tLcTask.getOrgId()).importBatchNo(tLcTask.getImportBatchNo()).isExitCollect(closeCaseType).isClose(TaskStatusEnum.CLOSE.getStatus()).build();
                    closeCaseList.add(closeCase);
                    caseNoList.add(tLcTask.getCaseNo());
                    return tLcTask;
                }).collect(Collectors.toList());
        this.tLcTaskMapper.batchUpdateTask(tLcTaskList);
        curAssetsPackageService.closeCase(closeCaseList);
        this.robotBlackService.batchDeleteRobotBlackByCaseNo(caseNoList);
        insertDuncaseAssign(tLcTaskList, ShiroUtils.getSysUser());
    }

    @Override
    @Transactional
    public void closeAllCase(TLcTask param, String closeCaseType) {
        ArrayList<CloseCase> closeCaseList = new ArrayList<>();
        List<TLcTask> tLcTaskList = this.tLcTaskMapper.selectTaskList(param);
        List<String> caseNoList = tLcTaskList.stream().map(tlcTask -> {
            tlcTask.setTaskStatus(TaskStatusEnum.CLOSE.getStatus());
            tlcTask.setTaskType(TaskTypeEnum.CLOSE_CASE_TRANSFER.getCode());
            tlcTask.setCloseDate(new Date());
            tlcTask.setModifyBy(ShiroUtils.getUserId());
            CloseCase closeCase = CloseCase.builder().caseNo(tlcTask.getCaseNo()).orgId(tlcTask.getOrgId()).importBatchNo(tlcTask.getImportBatchNo()).isExitCollect(closeCaseType).isClose(TaskStatusEnum.CLOSE.getStatus()).build();
            closeCaseList.add(closeCase);
            return tlcTask.getCaseNo();
        }).collect(Collectors.toList());
        this.tLcTaskMapper.batchUpdateTask(tLcTaskList);
        curAssetsPackageService.closeCase(closeCaseList);
        // 从机器人黑名单管理移除对应的数据
        this.robotBlackService.batchDeleteRobotBlackByCaseNo(caseNoList);
        insertDuncaseAssign(tLcTaskList, ShiroUtils.getSysUser());
    }

    @Override
    @Transactional
    public void closeCaseByCaseNo(CloseCaseUser closeCaseUser) {
        List<TLcTask> tLcTaskList = closeCaseUser.getCloseCaseList().stream()
                .map(closeCase -> {
                    TLcTask tLcTask = this.tLcTaskMapper.selectTaskByCaseNo(closeCase.getCaseNo(), closeCase.getOrgId(), closeCase.getImportBatchNo());
                    Assert.notNull(tLcTask, String.format("案件号不存在，案件号是%s", closeCase.getCaseNo()));
                    if (closeCase.getIsClose() != null && TaskStatusEnum.CLOSE.getStatus().equals(closeCase.getIsClose())) {
                        tLcTask.setTaskStatus(TaskStatusEnum.CLOSE.getStatus());
                        tLcTask.setTaskType(TaskTypeEnum.CLOSE_CASE_TRANSFER.getCode());
                        tLcTask.setCloseDate(new Date());
                    }
                    if (closeCase.getRepayMoney() != null) {
                        tLcTask.setCloseCaseYhje(tLcTask.getCloseCaseYhje().subtract(closeCase.getRepayMoney()));
                    }
                    tLcTask.setModifyBy(closeCaseUser.getSysUser().getUserId());
                    return tLcTask;
                }).collect(Collectors.toList());
        this.tLcTaskMapper.batchUpdateTask(tLcTaskList);
        insertDuncaseAssign(tLcTaskList, closeCaseUser.getSysUser());
    }

    /**
     * 添加到案件轨迹表中
     *
     * @param taskList
     */
    @Override
    public void insertDuncaseAssign(List<TLcTask> taskList, SysUser sysUser) {
        List<TLcDuncaseAssign> duncaseAssignList = taskList.stream()
                .map(task -> {
                    TLcDuncaseAssign tLcDuncaseAssign = TLcDuncaseAssign.builder()
                            .ownerId(task.getOwnerId())
                            .ownerName(task.getOwnerName())
                            .taskId(task.getId().toString())
                            .operationId(sysUser.getUserId())
                            .customName(task.getCustomName())
                            .collectTeamName(task.getCollectTeamName())
                            .collectTeamId(task.getCollectTeamId())
                            .certificateNo(task.getCertificateNo())
                            .caseNo(task.getCaseNo())
                            .operationName(sysUser.getUserName())
                            .transferType(task.getTaskType())
                            .orgId(task.getOrgId())
                            .orgName(task.getOrgName())
                            .taskStatus(task.getTaskStatus())
                            .validateStatus(IsNoEnum.IS.getCode())
                            .build();
                    return tLcDuncaseAssign;
                }).collect(Collectors.toList());
        // 将该任务添加到案件历史轨迹表
        this.tLcDuncaseAssignMapper.batchInsertDuncaseAssign(duncaseAssignList);
    }

   /* @Override
    public List<AssetsRepayment> viewRepayHis(String caseNo) {
        Map<String, Object> map = new HashMap<>();
        map.put("orgCasNoStr", caseNo);
        ResponseEntity<Map> response = restTemplateUtil.getRestTemplate().postForEntity(viewRepayHisUrl, map, Map.class);
        if (response.getStatusCodeValue() != HttpStatus.OK.value()) {
            log.error("调用查询还款记录错误");
            throw new RuntimeException("调用查询还款记录错误");
        }
        if ((Integer) response.getBody().get("code") != HttpStatus.OK.value()) {
            log.error("调用查询还款记录错误");
            throw new RuntimeException("调用查询还款记录错误");
    }
        return (List<AssetsRepayment>) response.getBody().get("data");
    }*/

    @Override
    public List<AssetsRepayment> viewRepayHis(String caseNo) {
        log.info("查询还款明细参数：" + caseNo);
        List<AssetsRepayment> list = new ArrayList<>();
        //根据机构案件号查询还款明细
        CurAssetsRepaymentPackage curAssetsRepaymentPackage = new CurAssetsRepaymentPackage();
        curAssetsRepaymentPackage.setOrgCasno(caseNo);
        List<CurAssetsRepaymentPackage> resultList = curAssetsRepaymentPackageService.selectCurAssetsRepaymentPackageList(curAssetsRepaymentPackage);
        if (resultList.size() > 0) {
            for (CurAssetsRepaymentPackage repayment : resultList) {
                String jsonStr = JSON.toJSONString(repayment);
                AssetsRepayment AssetsRepayment = JSONObject.parseObject(jsonStr, AssetsRepayment.class);
                list.add(AssetsRepayment);
            }
        }
        return list;
    }

    /**
     * 查询我的任务列表
     *
     * @param tLcTask
     * @return
     */
    @Override
    public List<TLcTask> selectMyTaskList(TLcTask tLcTask) {
        if (tLcTask.getEndRecentlyAllotDate() != null) {
            tLcTask.setEndRecentlyAllotDate(DateUtils.getEndOfDay(tLcTask.getEndRecentlyAllotDate()));
        }
        if (tLcTask.getEndRecentlyFollowUpDate() != null) {
            tLcTask.setEndRecentlyFollowUpDate(DateUtils.getEndOfDay(tLcTask.getEndRecentlyFollowUpDate()));
        }
        return this.tLcTaskMapper.selectMyTaskList(tLcTask);
    }

    /**
     * 查询我的任务列表
     *
     * @param
     * @return
     */
    @Override
    public List<TLcTask> findTaskByOwner(String ownerId) {
        return this.tLcTaskMapper.findTaskByOwner(ownerId);
    }

    /**
     * 根据不同的分配规则分配任务
     *
     * @param allocatRule
     * @param taskList
     * @param userList
     * @return
     */
    private List<TLcTask> allocatTask(Integer allocatRule, List<TLcTask> taskList, List<SysUser> userList) {
        // 根据不同的分配策略进行不同的任务分配
        if (allocatRule.equals(AllocatRuleEnum.DUNCASE_NUM_AVERAGE.getCode())) {
            // 案件数量平均分配
            taskList = AllocatRuleUtil.averageAllocatTaskByNum(taskList, userList);
        } else if (allocatRule.equals(AllocatRuleEnum.DUNCASE_MONEY_AVERAGE.getCode())) {
            // 案件金额平均分配
            taskList = AllocatRuleUtil.averageAllocatTaskByMoney(taskList, userList);
        }
        return taskList;
    }

    /**
     * 将待分配的任务id存放到set里面
     *
     * @param taskIds
     * @param allocatNum
     * @return
     */
    private Set<Long> getAllocatTaskSet(String taskIds, Integer allocatNum) {
        Set<Long> randomTaskSet = new HashSet<>(allocatNum); // 这里用set，因为随机选择的时候会重复，set可以去重
        if (allocatNum < taskIds.split(",").length) {
            Random random = new Random();
            int i = 0; //变量尽量不要循环定义
            while (randomTaskSet.size() < allocatNum) {
                i = random.nextInt(taskIds.split(",").length);
                randomTaskSet.add(Long.valueOf(taskIds.split(",")[i]));
            }
        } else {
            randomTaskSet = Arrays.stream(taskIds.split(",")).map(taskId -> Long.valueOf(taskId)).collect(Collectors.toSet());
        }
        return randomTaskSet;
    }

    /**
     * 将待分配的任务id存放到set里面
     *
     * @param taskList
     * @param allocatNum
     * @return
     */
    private Set<TLcTask> getAllocatTaskSet(List<TLcTask> taskList, Integer allocatNum) {
        Set<TLcTask> randomTaskSet = new HashSet<>(allocatNum); // 这里用set，因为随机选择的时候会重复，set可以去重
        if (allocatNum < taskList.size()) {
            Random random = new Random();
            int i = 0; //变量尽量不要循环定义
            while (randomTaskSet.size() < allocatNum) {
                i = random.nextInt(taskList.size());
                randomTaskSet.add(taskList.get(i));
            }
        } else {
            randomTaskSet = taskList.stream().collect(Collectors.toSet());
        }
        return randomTaskSet;
    }

    @Override
    public List<SysUser> searchAllUser(SysUser sysUser) {
        return this.sysUserMapper.selectUserList(sysUser);
    }

    @Override
    public void approveHelpColl(String taskIds, Integer status) {
        List<TLcTask> taskList = Arrays.stream(taskIds.split(","))
                .map(taskId -> {
                    TLcTask tLcTask = this.tLcTaskMapper.selectTLcTaskById(Long.valueOf(taskId));
                    // 修改案件信息
//                    TLcDuncase duncase = this.tLcDuncaseService.findDuncaseByCaseNoAndImportBatchNo(tLcTask.getCaseNo(), tLcTask.getOrgId(), tLcTask.getImportBatchNo());
                    if (status == 1) {//同意
                        tLcTask.setTaskType(TaskTypeEnum.HELP_COLLECT.getCode());
//                        duncase.setTaskType(TaskTypeEnum.HELP_COLLECT.getCode());
                    } else if (status == 2) {//拒绝
                        tLcTask.setOwnerId(tLcTask.getOldOwnerId())
                                .setOwnerName(this.sysUserService.selectUserById(Long.valueOf(tLcTask.getOldOwnerId())).getUserName())
                                .setOldOwnerId(null)
                                .setOldOwnerName(null)
                                .setTaskType(TaskTypeEnum.HELP_COLLECT_REFUSE.getCode());
//                        duncase.setTaskType(TaskTypeEnum.HELP_COLLECT_REFUSE.getCode());
                    }
//                    this.tLcDuncaseService.updateTLcDuncase(duncase);
                    return tLcTask;
                }).collect(Collectors.toList());
        this.tLcTaskMapper.batchUpdateTask(taskList);
        if (status == 1) {
            updateDuncaseOwner(taskList);
        }
        // 插入案件历史轨迹
        insertDuncaseAssign(taskList, ShiroUtils.getSysUser());
    }

    @Override
    public void stopCollApply(String taskId) {
        TLcTask tLcTask = this.tLcTaskMapper.selectTLcTaskById(Long.valueOf(taskId));
        tLcTask.setTaskType(TaskTypeEnum.STOP_COLLECT_APPLY.getCode());
        this.tLcTaskMapper.updateTLcTask(tLcTask);
        // 插入到机器人黑名单管理
        this.robotBlackService.insertTLcRobotBlack(tLcTask, RobotBlackReasonEnum.STOP_APPLY.getReason(), tLcTask.getPhone());
        // 插入案件历史轨迹
        ArrayList<TLcTask> taskList = new ArrayList<>(1);
        taskList.add(tLcTask);
        insertDuncaseAssign(taskList, ShiroUtils.getSysUser());
    }

    @Override
    public void approveStopColl(String taskIds, Integer status) {
        List<TLcTask> taskList = Arrays.stream(taskIds.split(","))
                .map(taskId -> {
                    TLcTask tLcTask = this.tLcTaskMapper.selectTLcTaskById(Long.valueOf(taskId));
//                    TLcDuncase duncase = this.tLcDuncaseService.findDuncaseByCaseNoAndImportBatchNo(tLcTask.getCaseNo(), tLcTask.getOrgId(), tLcTask.getImportBatchNo());
                    if (status == 1) {//同意
                        tLcTask.setTaskType(TaskTypeEnum.STOP_COLLECT.getCode());
                    } else if (status == 2) {//拒绝
                        tLcTask.setTaskType(TaskTypeEnum.STOP_COLLECT_REFUSE.getCode());
                        //从机器人黑名单管理移除
                        TLcRobotBlack robotBlack = TLcRobotBlack.builder().caseNo(tLcTask.getCaseNo()).importBatchNo(tLcTask.getImportBatchNo()).phone(tLcTask.getPhone()).reason(RobotBlackReasonEnum.STOP_APPLY.getReason()).build();
                        this.robotBlackService.deleteobotBlackByCaseReason(robotBlack);
                    } else if (status == 3) {//激活
                        tLcTask.setTaskType(TaskTypeEnum.STOP_COLLECT_LIVE.getCode());
                        //从机器人黑名单管理移除
                        TLcRobotBlack robotBlack = TLcRobotBlack.builder().caseNo(tLcTask.getCaseNo()).importBatchNo(tLcTask.getImportBatchNo()).phone(tLcTask.getPhone()).reason(RobotBlackReasonEnum.STOP_APPLY.getReason()).build();
                        this.robotBlackService.deleteobotBlackByCaseReason(robotBlack);
                    }
                    return tLcTask;
                }).collect(Collectors.toList());
        this.tLcTaskMapper.batchUpdateTask(taskList);
        insertDuncaseAssign(taskList, ShiroUtils.getSysUser());
    }

    @Override
    public TLcTask selectTaskByCaseNo(String caseNo, String orgId, String importBatchNo) {
        return this.tLcTaskMapper.selectTaskByCaseNo(caseNo, orgId, importBatchNo);
    }

    @Override
    public TLcTask selectHisTaskByCaseNo(String caseNo, String orgId, String importBatchNo) {
        return this.tLcTaskMapper.selectHisTaskByCaseNo(caseNo, orgId, importBatchNo);
    }

    @Override
    public TLcTask selectTaskByRobotTaskId(String robotTaskId) {
        return this.tLcTaskMapper.selectTaskByRobotTaskId(robotTaskId);
    }

    @Override
    @Transactional
    public AjaxResult sendRobot(String taskIds, String orgId, String speechcraftIdAndSceneDefId, Integer callLineId, String sendRobotBatchNos) {
        if (StringUtils.isNotBlank(taskIds)) {
            List<TLcTask> taskList = this.tLcTaskMapper.selectTLcTaskByIdsNotExistRobotBlack(taskIds.split(","));
            // 查询机器人在黑名单数量
            Long blackCount = this.tLcTaskMapper.selectCountByIdsNotExistRobotBlack(taskIds.split(","));
            sendRobotByList(orgId, speechcraftIdAndSceneDefId, callLineId, taskList, DateUtils.parseDateToStr(DateUtils.YYYYMMDDHHMMSS, new Date()));
            return AjaxResult.success(AjaxResult.Type.SUCCESS, "推送成功", taskList.size() + "," + blackCount);
        } else {
            String successCount = "";
            if (StringUtils.isNotBlank(sendRobotBatchNos)) {
                successCount = String.valueOf(sendRobotBatchNos.split(",").length);
                Arrays.stream(sendRobotBatchNos.split(",")).forEach(sendRobotBatchNo -> {
                    List<TLcTask> taskList = this.tLcTaskMapper.selectTaskListBySendRobotBatchNo(sendRobotBatchNo);
                    sendRobotByList(orgId, speechcraftIdAndSceneDefId, callLineId, taskList, sendRobotBatchNo.split("_")[0]);
                    // 修改推送机器人申请状态为已审批、任务状态并插入案件历史轨迹
                    updateSendRobotStatusTaskType(sendRobotBatchNo, taskList);
                });
            } else {
                // 查询所有待审批的任务
                List<TLcSendRobotApply> sendRobotApplyList = this.sendRobotApplyService.selectSendRobotApplyListByStatus(SendRobotApplyTaskStatusEnum.WAIT_APPROVAL.getCode());
                successCount = String.valueOf(sendRobotApplyList.size());
                sendRobotApplyList.stream().forEach(sendRobotApply -> {
                    List<TLcTask> taskList = this.tLcTaskMapper.selectTaskListBySendRobotBatchNo(sendRobotApply.getSendRobotBatchNo());
                    sendRobotByList(orgId, speechcraftIdAndSceneDefId, callLineId, taskList, sendRobotApply.getSendRobotBatchNo().split("_")[0]);
                    // 修改推送机器人申请状态为已审批、任务状态并插入案件历史轨迹
                    updateSendRobotStatusTaskType(sendRobotApply.getSendRobotBatchNo(), taskList);
                });
            }
            return AjaxResult.success(AjaxResult.Type.SUCCESS, "推送成功", successCount);
        }
    }

    private void updateSendRobotStatusTaskType(String sendRobotBatchNo, List<TLcTask> taskList) {
        if (taskList != null && taskList.size() > 0) {
            // 修改推送机器人审批状态
            TLcSendRobotApply sendRobotApply = new TLcSendRobotApply();
            sendRobotApply.setSendRobotBatchNo(sendRobotBatchNo).setTaskStatus(SendRobotApplyTaskStatusEnum.APPROVAL_PASS.getCode()).setUpdateBy(ShiroUtils.getSysUser().getUserId().toString());
            this.sendRobotApplyService.updateSendRobotStatusBySendRobotBatchNo(sendRobotApply);
            // 修改任务表任务类型
            TLcTask tLcTask = new TLcTask();
            tLcTask.setSendRobotBatchNo(sendRobotBatchNo).setTaskType(TaskTypeEnum.SEND_ROBOT_APPLY_ALLOW.getCode()).setUpdateBy(ShiroUtils.getSysUser().getUserId().toString());
            this.tLcTaskMapper.updateTLcTaskBySendRobotBatchNo(tLcTask);
            // 异步插入案件历史轨迹表
            this.duncaseAssignService.batchInsertDuncaseAssign(taskList, ShiroUtils.getSysUser(), TaskTypeEnum.SEND_ROBOT_APPLY_ALLOW.getCode());
        }
    }

    private void sendRobotByList(String orgId, String speechcraftIdAndSceneDefId, Integer callLineId, List<TLcTask> taskList, String taskName) {
        if (taskList != null && taskList.size() > 0) {
            // 推送到机器人
            TLcOrgSpeechcraftConf orgSpeechcraftConf = this.orgSpeechcraftConfService.selectTLcOrgSpeechcraftConfByOrgId(Long.valueOf(orgId));
            String[] speechcraftIdAndSceneDefIds = speechcraftIdAndSceneDefId.split(",");
            TLcCallStrategyConfig tLcCallStrategyConfig = TLcCallStrategyConfig.builder()
                    .id(-1L) // 如果是协催的话，策略id为-1，任务的呼叫策略配置id
                    .startCallDate(orgSpeechcraftConf.getStartCallTime())
                    .stopCallDate(orgSpeechcraftConf.getEndCallTime())
                    .speechcraftId(Integer.valueOf(speechcraftIdAndSceneDefIds[0]))
                    .sceneDefId(Integer.valueOf(speechcraftIdAndSceneDefIds[1]))
                    .speechcraftName(speechcraftIdAndSceneDefIds[2])
                    .callLineId(String.valueOf(callLineId)).build();
            TLcAllocatCaseConfig caseConfig = this.caseConfigService.selectTLcAllocatCaseConfigByOrgId(orgId);
            // 获取单次推送到机器人的号码数
            String taskCallNum = this.sysDictDataService.selectDictLabel("robot_call_config", "task_call_num");
            if (caseConfig.getRobot().equals("BR")) {
                if (taskList.size() <= Integer.valueOf(taskCallNum)) {
                    this.robotMethodUtil.createTask(taskList, tLcCallStrategyConfig, 1, 1, orgSpeechcraftConf, taskName);
                } else {
                    Integer taskNums = taskList.size() / Integer.valueOf(taskCallNum);
                    for (int i = 0; i < taskNums; i++) {
                        List subTaskIdList = taskList.subList(i * Integer.valueOf(taskCallNum), (i + 1) * Integer.valueOf(taskCallNum));
                        this.robotMethodUtil.createTask(subTaskIdList, tLcCallStrategyConfig, 1, 1, orgSpeechcraftConf, taskName);
                    }
                    if (taskList.size() % Integer.valueOf(taskCallNum) != 0) {
                        List subTaskIdList = taskList.subList(Integer.valueOf(taskCallNum) * taskNums, taskList.size());
                        this.robotMethodUtil.createTask(subTaskIdList, tLcCallStrategyConfig, 1, 1, orgSpeechcraftConf, taskName);
                    }
                }
            }
            // 异步将任务插入到案件历史轨迹表
            this.duncaseAssignService.batchInsertDuncaseAssign(taskList, ShiroUtils.getSysUser(), TaskTypeEnum.HELP_COLLECT_ROBOT.getCode());
        }
    }

    /**
     * 创建机器人任务
     *
     * @param tLcTask
     * @param robotTaskId
     * @return
     */
    @Override
    public TLcRobotTask createRobotTask(TLcTask tLcTask, Integer robotTaskId, Integer speechcraftId, String speechCraftName, String robot, Integer continueCallDays, Integer continueCallFrequency) {
        TLcRobotTask tLcRobotTask = new TLcRobotTask();
        tLcRobotTask.setTaskId(tLcTask.getId())
                .setRobotTastId(robotTaskId)
                .setTaskName(tLcTask.getCustomName() + tLcTask.getCaseNo())
                .setOwnerName(tLcTask.getOwnerName())
                .setTransferType(tLcTask.getTransferType())
                .setArrearsTotal(tLcTask.getArrearsTotal())
                .setSpeechCraftName(speechCraftName)
                .setTaskStatus(tLcTask.getTaskStatus())
                .setTaskType(tLcTask.getTaskType())
                .setOrgId(tLcTask.getOrgId())
                .setOrgName(tLcTask.getOrgName())
                .setCurName(tLcTask.getCustomName())
                .setPhone(tLcTask.getPhone())
                .setRobot(robot)
                .setContinueDays(continueCallDays)
                .setContinueFrequency(continueCallFrequency)
                .setRobotTaskStatus(LocalRobotTaskStatus.RUNNING.getCode())
                .setCreateBy(String.valueOf(ShiroUtils.getUserId()));
        return tLcRobotTask;
    }

    @Override
    public TLcTask selectTaskByRobotTaskIdAndPhone(String robotTaskId, String phone) {
        return this.tLcTaskMapper.selectTaskByRobotTaskIdAndPhone(robotTaskId, phone);
    }

    @Override
    public List<TLcTask> selectTaskListByRobotTaskIdAndPhone(String robotTaskId, String phone) {
        return this.tLcTaskMapper.selectTaskListByRobotTaskIdAndPhone(robotTaskId, phone);
    }

    @Override
    public Integer isHasCloseCaseTask(TLcTask tLcTask, HttpServletRequest request) {
//        setCustomSql(tLcTask, request);
        return this.tLcTaskMapper.isHasCloseCaseTask(tLcTask);
    }

    @Override
    public AjaxResult allDataSendRobot(String speechcraftIdAndSceneDefId, TLcTask tLcTask, Integer callLineId) {
//        setCustomSql(tLcTask, request);
//        List<TLcTask> taskList = this.tLcTaskMapper.selectTaskList(tLcTask);
        List<TLcTask> taskList = this.tLcTaskMapper.selectTaskListNotExistRobotBlack(tLcTask);
        // 查询在黑名单里的任务数量
        Long blackCount = this.tLcTaskMapper.selectCountExistRobotBlack(tLcTask);
        sendRobotByList(tLcTask.getOrgId(), speechcraftIdAndSceneDefId, callLineId, taskList, DateUtils.parseDateToStr(DateUtils.YYYYMMDDHHMMSS, new Date()));
        return AjaxResult.success(AjaxResult.Type.SUCCESS, "推送成功", taskList.size() + "," + blackCount);
    }

    @Override
    public Integer isHasCTSTask(TLcTask tLcTask) {
//        setCustomSql(tLcTask, request);
        return this.tLcTaskMapper.isHasCTSTask(tLcTask);
    }

    @Override
    public void updateTLcTaskByRobotTaskIdAndPhone(TLcTask tLcTask) {
        this.tLcTaskMapper.updateTLcTaskByRobotTaskIdAndPhone(tLcTask);
    }

    @Override
    public List<SysUser> searchUserByDeptAndHaveDept(SysUser sysUser) {
        return this.sysUserMapper.searchUserByDeptAndHaveDept(sysUser);
    }

    @Override
    public TLcTask selectTaskByCaseAndNoCloseCase(String caseNo) {
        return this.tLcTaskMapper.selectTaskByCaseAndNoCloseCase(caseNo);
    }

    @Override
    public CollJob collJobDetail(String caseNo, String orgId, String importBatchNo) {
        log.info("caseNo is {}" + caseNo);
        CollJob collJob = new CollJob();
        // 通过身份证号查询任务详情
        TLcTask tLcTask = selectTaskByCaseNo(caseNo, orgId, importBatchNo);
        // 通过身份证号查询案件基本信息
        //TLcDuncase tLcDuncase = this.tLcDuncaseService.findDuncaseByCaseNoAndImportBatchNo(caseNo, tLcTask.getOrgId(), tLcTask.getImportBatchNo());
        // 通过案件编号查询客户基本信息
        //TLcCustinfo tLcCustinfo = this.tLcCustinfoService.findCustByCaseNo(caseNo, orgId, importBatchNo);
        // 通过身份证号查询客户单位信息
        //TLcCustJob tLcCustJob = this.tLcCustJobService.findCustJobByCaseNo(caseNo, orgId, importBatchNo);
        // 通过案件号查询还款历史
        //List<AssetsRepayment> repayHisList = viewRepayHis(tLcTask.getCaseNo());
        //资产接口调用
        Assets assets = callRemote(caseNo, importBatchNo);
        return collJob.setTLcTask(tLcTask)
                .setAssets(assets);

//                .setRepaymentList(repayHisList)
//                .setTLcDuncaseAssignList(duncaseAssignList)
//                .setTLcCustContactList(custContactList)
//                .setCallRecordList(callRecordList)
//                .setActionRecordList(tLcDuncaseActionRecordList);
//                .setTLcTask(tLcTask);
//                .setTLcDuncaseAssignList(duncaseAssignList)
//                .setTLcCustContactList(custContactList)
//                .setCallRecordList(callRecordList)
//                .setActionRecordList(tLcDuncaseActionRecordList);
    }

    @Override
    public CollJob hisDuncaseCollJobDetail(String caseNo, String orgId, String importBatchNo) {
        log.info("caseNo is {}" + caseNo);
        CollJob collJob = new CollJob();
        // 通过案件编号、机构编号、导入批次号查询任务详情
        TLcTask tLcTask = selectHisTaskByCaseNo(caseNo, orgId, importBatchNo);
        //资产接口调用
        Assets assets = callRemoteHis(caseNo, importBatchNo);
        return collJob.setTLcTask(tLcTask)
                .setAssets(assets);
    }

    @Override
    public Response addCallRecord(TLcCallRecord tLcCallRecord, String importBatchNo, String callStartTime, String callEndTime) {
        log.info("添加通话记录开始");
        try {
            // 设置通话录音地址
            if (StringUtils.isNoneBlank(tLcCallRecord.getCallRadioLocation())) {
                if (tLcCallRecord.getPlatform().equals("PA")) {
                    String urlpath = remoteConfigure.getTelphoneRecordUrl() + tLcCallRecord.getCallRadioLocation();
                    tLcCallRecord.setCallRadioLocation(urlpath);
                }
            }
            // 设置通话开始和通话结束时间
            if (StringUtils.isNotEmpty(callStartTime)) {
                callStartTime = callStartTime.replace("T", " ");
                tLcCallRecord.setCallStartTime(DateUtils.stringConvertDate(callStartTime, DateUtils.YYYY_MM_DD_HH_MM_SS));
            }
            if (StringUtils.isNotEmpty(callEndTime)) {
                callEndTime = callEndTime.replace("T", " ");
                tLcCallRecord.setCallEndTime(DateUtils.stringConvertDate(callEndTime, DateUtils.YYYY_MM_DD_HH_MM_SS));
            }
            // 新增或者修改电催记录
            if (tLcCallRecord.getId() == null) {
                tLcCallRecord.setAgentName(ShiroUtils.getSysUser().getUserName());
                tLcCallRecordService.insertTLcCallRecord(tLcCallRecord);
            } else {
                log.info("通话记录id不为空：{},话务平台：{},案件编号：{},录音地址：{},联系人手机号：{}",
                        tLcCallRecord.getId(), tLcCallRecord.getPlatform(), tLcCallRecord.getCaseNo(), tLcCallRecord.getCallRadioLocation(), tLcCallRecord.getPhone());
                try {
                    // 如果是自建话务平台计算通话时长
                    calCallLen(tLcCallRecord);
                } catch (Exception e) {
                    log.error("电催记录{}计算通话时长错误：{}", tLcCallRecord.getId(), e);
                } finally {
                    tLcCallRecordService.updateTLcCallRecord(tLcCallRecord);
                }
            }
            // 更新任务表数据
            updateTask(tLcCallRecord, importBatchNo);
            return Response.success(tLcCallRecord.getId());
        } catch (Exception e) {
            log.error("添加记录失败，error is {}", e);
            throw new RuntimeException("添加记录失败");
        }
    }

    /**
     * 自建话务平台计算通话时长并且设置通话结束时间
     *
     * @param tLcCallRecord
     */
    private void calCallLen(TLcCallRecord tLcCallRecord) {
        if (StringUtils.isNoneBlank(tLcCallRecord.getPlatform()) && tLcCallRecord.getPlatform().equals("ZJ")) {
            log.info("通话开始时间：{}", tLcCallRecord.getCallStartTime());
            if (tLcCallRecord.getCallStartTime() != null) {
                if (StringUtils.isBlank(tLcCallRecord.getCallLen())) {
                    if (tLcCallRecord.getCallEndTime() == null) {
                        long callLen = System.currentTimeMillis() - tLcCallRecord.getCallStartTime().getTime();
                        log.info("通话id：{}的通话时长为：{}", tLcCallRecord.getId(), callLen);
                        tLcCallRecord.setCallLen(String.valueOf(callLen));
                        tLcCallRecord.setCallEndTime(new Date());
                    } else {
                        long callLen = tLcCallRecord.getCallEndTime().getTime() - tLcCallRecord.getCallStartTime().getTime();
                        log.info("通话id：{}的通话时长为：{}", tLcCallRecord.getId(), callLen);
                        tLcCallRecord.setCallLen(String.valueOf(callLen));
                    }
                } else {
                    if (tLcCallRecord.getCallEndTime() == null) {
                        tLcCallRecord.setCallEndTime(new Date());
                    }
                }
            }
        }
    }

    /**
     * 更新任务表数据--新增或者修改电催记录时
     *
     * @param tLcCallRecord
     * @param importBatchNo
     */
    private void updateTask(TLcCallRecord tLcCallRecord, String importBatchNo) {
        TLcTask tLcTask = TLcTask.builder()
                .caseNo(tLcCallRecord.getCaseNo())
                .importBatchNo(importBatchNo)
                .orgId(tLcCallRecord.getOrgId())
                .callSign(tLcCallRecord.getCallSign())
                .callSignValue(tLcCallRecord.getCallResult())
                .recentlyFollowUpDate(new Date())
                .build();
        this.tLcTaskMapper.updateTaskByCaseNoAndImportBatchNoAndOrgId(tLcTask);
    }

    @Override
    public List<TLcTask> opreAllFindData(TLcTask tLcTask) {
        List<TLcTask> list = selectTLcTaskList(tLcTask);
        Map<String, List<TLcTask>> map = list.stream().collect(Collectors.groupingBy(TLcTask::getOrgId));
        if (map.size() > 1) {
            throw new BusinessException("请选择同一机构下的数据");
        }
        if (tLcTask.getCheckAllType().equals(TaskTypeEnum.TEMP_AGENT.getCode())) {
            TLcTask tLcTask1 = list.stream()
                    .filter(task -> TaskTypeEnum.TEMP_AGENT.getCode().equals(task.getTaskType()) || TaskTypeEnum.HELP_COLLECT.getCode().equals(task.getTaskType()))
                    .findAny()
                    .get();
            if (tLcTask1 != null) {
                throw new BusinessException("已经是临时代理的任务或者协助催收的任务不能进行临时代理");
            }
        } else if (tLcTask.getCheckAllType().equals(TaskTypeEnum.TEMP_AGENT_RECYCLE.getCode())) {
            TLcTask tLcTask1 = list.stream()
                    .filter(task -> !TaskTypeEnum.TEMP_AGENT.getCode().equals(task.getTaskType()))
                    .findAny()
                    .get();
            if (tLcTask1 != null) {
                throw new BusinessException("只有是临时代理的任务才能进行回收");
            }
        }
        return list;
    }

    @Override
    public List<SysUser> searchUserByDept(SysUser sysUser) {
        return this.sysUserMapper.searchUserByDept(sysUser);
    }


    /**
     * 资产接口调用
     *
     * @param orgCaseNo
     * @param importBatchNo
     * @return
     */
   /* private Assets callRemote(String orgCaseNo, String importBatchNo) {
        Assets assets = null;
        String url = getAssetsUrl + "?orgCaseNo=" + orgCaseNo + "&importBatchNo=" + importBatchNo;
        try {
            Map remoteResult = restTemplateUtil.getRestTemplate().postForObject(url, "", Map.class);
            if (200 == (int) remoteResult.get("code")) {
                Map data = (Map) remoteResult.get("data");
                assets = JSONObject.parseObject(JSONObject.toJSONString(data), Assets.class);
            }
        } catch (RestClientException e) {
            e.printStackTrace();
            log.error("调用资产接口获取资产失败{}", e);
        }
        return assets;
    }*/
    private Assets callRemote(String orgCaseNo, String importBatchNo) {
        log.info("查询单条资产参数：orgCaseNo=" + orgCaseNo + ",importBatchNo=" + importBatchNo);
        Assets assets = null;
        try {
            CurAssetsPackage result = this.curAssetsPackageService.selectAsset(orgCaseNo, importBatchNo);
            String jsonStr = JSON.toJSONString(result);
            assets = JSONObject.parseObject(jsonStr, Assets.class);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询单条资产异常{}", e);
        }
        return assets;
    }

    private Assets callRemoteHis(String orgCaseNo, String importBatchNo) {
        log.info("查询单条资产参数：orgCaseNo=" + orgCaseNo + ",importBatchNo=" + importBatchNo);
        Assets assets = null;
        try {
            CurAssetsPackage result = this.curAssetsPackageService.selectHisAsset(orgCaseNo, importBatchNo);
            String jsonStr = JSON.toJSONString(result);
            assets = JSONObject.parseObject(jsonStr, Assets.class);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询单条资产异常{}", e);
        }
        return assets;
    }

    /**
     * 根据时间只查询 案件表--定时任务同步数据中心 用
     * 2020-06-24 封志涛添加
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> selectTaskByTime(Date createTime, int pageNum, int pageSize) {

        return tLcTaskMapper.selectTaskByTime(createTime, pageNum, pageSize);
    }

    @Override
    public Integer selectTaskCount(Date createTime) {

        return tLcTaskMapper.selectTaskCount(createTime);
    }

    @Override
    public Map<String, BigDecimal> selectTotalCountMoney(TLcTask tLcTask) {
        return this.tLcTaskMapper.selectTotalCountMoney(tLcTask);
    }

    @Override
    public Response sendRobotApply(String taskIds) {
        List<TLcTask> taskList = this.tLcTaskMapper.selectTLcTaskByIdsNotExistRobotBlack(taskIds.split(","));
        // 查询机器人在黑名单数量
        Long blackCount = this.tLcTaskMapper.selectCountByIdsNotExistRobotBlack(taskIds.split(","));
        if (taskList != null && taskList.size() > 0) {
            // 生成批次号
            String sendRobotBatchNo = DateUtils.parseDateToStr(DateUtils.YYYYMMDDHHMMSS, new Date()) + "_" + UUID.randomUUID().toString().replaceAll("-", "");
            // 修改任务表数据
            taskList = taskList.stream().map(task -> {
                task.setTaskType(TaskTypeEnum.SEND_ROBOT_APPLY.getCode()).setSendRobotBatchNo(sendRobotBatchNo);
                return task;
            }).collect(Collectors.toList());
            this.tLcTaskMapper.batchUpdateTask(taskList);
            // 插入推送机器人申请表
            createSendRobotApply(taskList.size(), sendRobotBatchNo);
            // 异步将案件插入轨迹表
            this.duncaseAssignService.batchInsertDuncaseAssign(taskList, ShiroUtils.getSysUser(), TaskTypeEnum.SEND_ROBOT_APPLY.getCode());
        }
        return Response.success(Response.ResponseStatusEnum.SUCCESS.getStatus(), "推送成功", taskList.size() + "," + blackCount);
    }

    @Override
    public List<TLcTask> selectTaskListBySendRobotBatchNo(String sendRobotBatchNo) {
        return this.tLcTaskMapper.selectTaskListBySendRobotBatchNo(sendRobotBatchNo);
    }

    @Override
    public void updateTLcTaskBySendRobotBatchNo(TLcTask tLcTask) {
        this.tLcTaskMapper.updateTLcTaskBySendRobotBatchNo(tLcTask);
    }

    /**
     * 插入推送机器人申请表
     *
     * @param taskNum
     * @param sendRobotBatchNo
     */
    private void createSendRobotApply(Integer taskNum, String sendRobotBatchNo) {
        String orgName = this.orgPackageService.selectOrgPackageByOrgId(ShiroUtils.getSysUser().getOrgId().toString()).getOrgName();
        TLcSendRobotApply sendRobotApply = new TLcSendRobotApply();
        sendRobotApply.setSendRobotBatchNo(sendRobotBatchNo)
                .setOrgId(ShiroUtils.getSysUser().getOrgId())
                .setOrgName(orgName)
                .setTaskNum(taskNum)
                .setOwnerId(ShiroUtils.getSysUser().getUserId())
                .setOwnerName(ShiroUtils.getSysUser().getUserName())
                .setTaskStatus(SendRobotApplyTaskStatusEnum.WAIT_APPROVAL.getCode())
                .setCreateBy(ShiroUtils.getSysUser().getUserId().toString());
        this.sendRobotApplyService.insertTLcSendRobotApply(sendRobotApply);
    }

}

class AssetsRepayResponse {
    private Integer code;
    private String message;
    private AssetsRepayment data;
}
