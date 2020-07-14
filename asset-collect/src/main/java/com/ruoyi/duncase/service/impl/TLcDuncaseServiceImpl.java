package com.ruoyi.duncase.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.assetspackage.domain.OrgPackage;
import com.ruoyi.assetspackage.domain.score.TLcScore;
import com.ruoyi.assetspackage.service.IOrgPackageService;
import com.ruoyi.callConfig.domain.TLcCallStrategyConfig;
import com.ruoyi.callConfig.service.ITLcCallStrategyConfigService;
import com.ruoyi.caseConfig.domain.TLcAllocatCaseConfig;
import com.ruoyi.caseConfig.service.ITLcAllocatCaseConfigService;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.RestTemplateUtil;
import com.ruoyi.custom.domain.TLcCustContact;
import com.ruoyi.custom.domain.TLcCustJob;
import com.ruoyi.custom.domain.TLcCustinfo;
import com.ruoyi.custom.mapper.TLcCustContactMapper;
import com.ruoyi.custom.mapper.TLcCustJobMapper;
import com.ruoyi.custom.mapper.TLcCustinfoMapper;
import com.ruoyi.custom.service.ITLcCustContactService;
import com.ruoyi.duncase.domain.AllocatTaskInvokeRuleEngin;
import com.ruoyi.duncase.domain.Assets;
import com.ruoyi.duncase.domain.TLcDuncase;
import com.ruoyi.duncase.domain.TLcDuncaseAssign;
import com.ruoyi.duncase.mapper.TLcDuncaseAssignMapper;
import com.ruoyi.duncase.mapper.TLcDuncaseMapper;
import com.ruoyi.duncase.service.ITLcDuncaseService;
import com.ruoyi.enums.*;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.orgSpeechConf.domain.TLcOrgSpeechcraftConf;
import com.ruoyi.orgSpeechConf.service.ITLcOrgSpeechcraftConfService;
import com.ruoyi.robot.domain.TLcRobotTask;
import com.ruoyi.robot.service.ITLcRobotTaskService;
import com.ruoyi.robot.utils.RobotMethodUtil;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.task.domain.TLcTask;
import com.ruoyi.task.mapper.TLcTaskMapper;
import com.ruoyi.task.service.ITLcTaskService;
import com.ruoyi.utils.AllocatRuleUtil;
import com.ruoyi.utils.DataPermissionUtil;
import com.ruoyi.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 案件Service业务层处理
 *
 * @author liurunkai
 * @date 2019-12-25
 */
@Slf4j
@Service
public class TLcDuncaseServiceImpl implements ITLcDuncaseService {

    @Value("${ruleEngine.allocatTaskUrl}")
    private String allocatTaskUrl;
    @Value("${ruleEngine.rulePackage}")
    private String rulePackage;
//    @Value("${ruleEngine.processId}")
//    private String processId;

    @Autowired
    private TLcDuncaseMapper tLcDuncaseMapper;
    @Autowired
    private TLcCustinfoMapper tLcCustinfoMapper;
    @Autowired
    private TLcCustJobMapper tLcCustJobMapper;
    @Autowired
    private TLcCustContactMapper tLcCustContactMapper;
    @Autowired
    private TLcDuncaseAssignMapper tLcDuncaseAssignMapper;
    @Autowired
    private RestTemplateUtil restTemplateUtil;
    @Autowired
    private TLcTaskMapper tLcTaskMapper;
    @Autowired
    private ITLcCallStrategyConfigService callStrategyConfigService;
    @Autowired
    private ITLcTaskService tLcTaskService;
    @Autowired
    private ITLcAllocatCaseConfigService caseConfigService;
    @Autowired
    private RobotMethodUtil robotMethodUtil;
    @Autowired
    private ISysDictDataService sysDictDataService;
    @Autowired
    private IOrgPackageService orgPackageService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ITLcOrgSpeechcraftConfService orgSpeechcraftConfService;

    /**
     * 查询案件
     *
     * @param id 案件ID
     * @return 案件
     */
    @Override
    public TLcDuncase selectTLcDuncaseById(Long id) {
        return tLcDuncaseMapper.selectTLcDuncaseById(id);
    }

    /**
     * 查询案件列表
     *
     * @param tLcDuncase 案件
     * @return 案件
     */
    @Override
    public List<TLcDuncase> selectTLcDuncaseList(TLcDuncase tLcDuncase) {
        tLcDuncase.setDeptIds(ShiroUtils.getSysUser().getDeptIds());
        if (tLcDuncase.getEndRecentlyAllotDate() != null) {
            tLcDuncase.setEndRecentlyAllotDate(DateUtils.getEndOfDay(tLcDuncase.getEndRecentlyAllotDate()));
        }
        if (tLcDuncase.getEndRecentlyFollowUpDate() != null) {
            tLcDuncase.setEndRecentlyFollowUpDate(DateUtils.getEndOfDay(tLcDuncase.getEndRecentlyFollowUpDate()));
        }
        return tLcDuncaseMapper.selectTLcDuncaseList(tLcDuncase);
    }

    /**
     * 查询案件列表
     *
     * @param tLcDuncase 案件
     * @return 案件
     */
    @Override
    public List<TLcDuncase> selectTLcDuncaseByPage(TLcDuncase tLcDuncase) {

//        StringBuilder sb = new StringBuilder();
//        if (tLcDuncase.getStart_arrears_total() != null) {
//            sb.append(" and t.appoint_case_balance >= " + tLcDuncase.getStart_arrears_total());
//        }
//        if (tLcDuncase.getEnd_arrears_total() != null) {
//            sb.append(" and t.appoint_case_balance <= " + tLcDuncase.getEnd_arrears_total());
//        }
//        if (tLcDuncase.getStart_close_case_yhje() != null) {
//            sb.append(" and t.close_case_yhje >= " + tLcDuncase.getStart_close_case_yhje());
//        }
//        if (tLcDuncase.getEnd_close_case_yhje() != null) {
//            sb.append(" and t.close_case_yhje <= " + tLcDuncase.getEnd_close_case_yhje());
//        }
//        if (StringUtils.isNoneBlank(tLcDuncase.getTransfer_type())) {
//            for (Map.Entry<String, String[]> map : parameterMap.entrySet()) {
//                if (map.getKey().equals("compareMethod_transfer_type")) {
//                    List<String> values = Arrays.asList(map.getValue());
//                    if (values.get(0).equals("equal")) {
//                        sb.append(" and t.transfer_type = '"+tLcDuncase.getTransfer_type()+"'");
//                    }
//                    if (values.get(0).equals("like")) {
//                        sb.append(" and t.transfer_type like '%"+tLcDuncase.getTransfer_type()+"%'");
//                    }
//                    break;
//                }
//            }
//        }
//        if (StringUtils.isNoneBlank(tLcDuncase.getPhone())) {
//            for (Map.Entry<String, String[]> map : parameterMap.entrySet()) {
//                if (map.getKey().equals("compareMethod_phone")) {
//                    List<String> values = Arrays.asList(map.getValue());
//                    if (values.get(0).equals("equal")) {
//                        sb.append(" and t.custom_phone = " + tLcDuncase.getPhone());
//                    }
//                    if (values.get(0).equals("like")) {
//                        sb.append(" and t.custom_phone like '%"+tLcDuncase.getPhone()+"%'");
//                    }
//                    break;
//                }
//            }
//        }
//        if (StringUtils.isNoneBlank(tLcDuncase.getOwner_name())) {
//            for (Map.Entry<String, String[]> map : parameterMap.entrySet()) {
//                if (map.getKey().equals("compareMethod_owner_name")) {
//                    List<String> values = Arrays.asList(map.getValue());
//                    if (values.get(0).equals("equal")) {
//                        sb.append(" and ta.owner_name = '" + tLcDuncase.getOwner_name()+"'");
//                    }
//                    if (values.get(0).equals("like")) {
//                        sb.append(" and ta.owner_name like '%"+tLcDuncase.getOwner_name()+"%'");
//                    }
//                    break;
//                }
//            }
//        }
//        if (StringUtils.isNoneBlank(tLcDuncase.getCall_sign())) {
//            sb.append(" and ta.call_sign = '" + tLcDuncase.getCall_sign()+"'");
//        }
//        if (StringUtils.isNoneBlank(tLcDuncase.getAction_code())) {
//            sb.append(" and ta.action_code = '" + tLcDuncase.getAction_code()+"'");
//        }
//        if (StringUtils.isNoneBlank(tLcDuncase.getTask_type())) {
//            sb.append(" and ta.task_type = " + tLcDuncase.getTask_type());
//        }
//        if (tLcDuncase.getStart_enter_coll_date() != null) {
//            sb.append(" and t.enter_coll_date >= '" + DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,tLcDuncase.getStart_enter_coll_date())+"'");
//        }
//        if (tLcDuncase.getEnd_enter_coll_date() != null) {
//            sb.append(" and t.enter_coll_date <= '" + DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,DateUtils.getEndOfDay(tLcDuncase.getEnd_enter_coll_date()))+"'");
//        }
//        if (tLcDuncase.getStart_recently_allot_date() != null) {
//            sb.append(" and ta.recently_allot_date >= '" + DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,tLcDuncase.getStart_recently_allot_date())+"'");
//        }
//        if (tLcDuncase.getEnd_recently_allot_date() != null) {
//            sb.append(" and ta.recently_allot_date <= '" + DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,DateUtils.getEndOfDay(tLcDuncase.getEnd_recently_allot_date()))+"'");
//        }
//        if (tLcDuncase.getStart_recently_follow_up_date() != null) {
//            sb.append(" and ta.recently_follow_up_date >= '" + DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,tLcDuncase.getStart_recently_follow_up_date())+"'");
//        }
//        if (tLcDuncase.getEnd_recently_follow_up_date() != null) {
//            sb.append(" and ta.recently_follow_up_date <= '" + DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,DateUtils.getEndOfDay(tLcDuncase.getEnd_recently_follow_up_date()))+"'");
//        }
//        if (StringUtils.isNoneBlank(sb.toString())) {
//            tLcDuncase.setSql(sb.toString());
//        }
//        log.info("动态查询的sql是{}", tLcDuncase.getSql());
        return tLcDuncaseMapper.selectTLcDuncaseByPage(tLcDuncase);
    }

    /**
     * 新增案件
     *
     * @param tLcDuncase 案件
     * @return 结果
     */
    @Override
    public int insertTLcDuncase(TLcDuncase tLcDuncase) {
        tLcDuncase.setCreateTime(DateUtils.getNowDate());
        return tLcDuncaseMapper.insertTLcDuncase(tLcDuncase);
    }

    /**
     * 修改案件
     *
     * @param tLcDuncase 案件
     * @return 结果
     */
    @Override
    public int updateTLcDuncase(TLcDuncase tLcDuncase) {
        return tLcDuncaseMapper.updateTLcDuncase(tLcDuncase);
    }

    /**
     * 接收资产包传过来的案件信息及用户信息
     *
     * @param curAssetsPackageList
     */
   /* @Override
    @Transactional
    public Response acceptDuncase(List<Assets> curAssetsPackageList) {
        if (curAssetsPackageList == null || curAssetsPackageList.size() <= 0) {
            log.error("从资产包过来的案件信息集合为空");
            throw new RuntimeException("从资产包过来的案件信息集合为空");
        }
        try {
            //获取当前用户登录信息
            // 创建案件集合、客户集合、客户单位集合、客户联系人集合
            List<TLcDuncase> duncaseInsertList = new ArrayList<>(curAssetsPackageList.size());
            List<TLcDuncase> duncaseUpdateList = new ArrayList<>(curAssetsPackageList.size());
            List<TLcCustinfo> custInsertList = new ArrayList<>(curAssetsPackageList.size());
            List<TLcCustinfo> custUpdateList = new ArrayList<>(curAssetsPackageList.size());
            List<TLcCustJob> jobInsertList = new ArrayList<>(curAssetsPackageList.size());
            List<TLcCustJob> jobUpdateList = new ArrayList<>(curAssetsPackageList.size());
            List<TLcCustContact> contactInsertList = new ArrayList<>(curAssetsPackageList.size() * 3);
            List<TLcCustContact> contactUpdateList = new ArrayList<>(curAssetsPackageList.size() * 3);
            curAssetsPackageList.stream().forEach(assetsPackage -> {
                //创建借据信息对象、客户信息对象、客户单位信息对象、客户联系人信息对象并分别加入到对应的集合中
                TLcDuncase tLcDuncase = createTLcDuncase(assetsPackage);
                TLcCustinfo tLcCustinfo = createCustinfo(assetsPackage);
                TLcCustJob tLcCustJob = createJobInfo(assetsPackage);
                List<TLcCustContact> contacts = createContactList(assetsPackage);
//                // 根据案件号、机构判断该借据是否存在，如果存在则修改，反之新增
//                TLcDuncase duncase = this.tLcDuncaseMapper.findDuncaseByCaseNo(assetsPackage.getOrgCasno(), assetsPackage.getOrgId());
                if (StringUtils.isNoneBlank(assetsPackage.getOperFlag()) && assetsPackage.getOperFlag().equals("1")) {
                    // 根据案件号、机构、导入批次号判断该借据是否存在，如果存在则修改，反之新增
                    TLcDuncase duncase = this.tLcDuncaseMapper.findDuncaseByCaseNoAndImportBatchNo(assetsPackage.getOrgCasno(), assetsPackage.getOrgId(), assetsPackage.getImportBatchNo());
                    if (duncase != null) {
                        duncaseUpdateList.add(tLcDuncase);
                        custUpdateList.add(tLcCustinfo);
                        jobUpdateList.add(tLcCustJob);
                        contacts.stream().forEach(contact -> contactUpdateList.add(contact));
                    }
                } else {
                    duncaseInsertList.add(tLcDuncase);
                    custInsertList.add(tLcCustinfo);
                    jobInsertList.add(tLcCustJob);
                    contacts.stream().forEach(contact -> contactInsertList.add(contact));
                }
            });
            TLcAllocatCaseConfig caseConfig = this.caseConfigService.selectTLcAllocatCaseConfigByOrgId(curAssetsPackageList.get(0).getOrgId());
            if (duncaseInsertList != null && duncaseInsertList.size() > 0) {
                if (StringUtils.isNoneBlank(caseConfig.getRuleEngine()) && caseConfig.getAllocatCaseStartegy().equals(AllocatCaseStartegyEnum.ROBOT_PERSON.getCode())) {
                    // 开始调用规则引擎分配任务、并向任务表同步任务：只有新增的案件需要分配
                    allocatTaskAndSyncTask(duncaseInsertList, caseConfig);
                } else {
                    // 不调用规则引擎系统
                    SysUser sysUser = new SysUser();
                    sysUser.setDeptId(this.tLcTaskService.findDeptIdByOrgId(duncaseInsertList.get(0).getOrgId()));
                    List<SysUser> userList = this.tLcTaskService.searchAllUser(sysUser);
                    // 创建任务
                    List<TLcTask> taskList = duncaseInsertList.stream()
                            .map(duncase -> createInsertTask(duncase))
                            .collect(Collectors.toList());
                    taskList.stream().forEach(task -> task.setAllotType(AllocatTaskEnum.MANUAL.getAllocatCode()));
                    duncaseInsertList.stream().forEach(duncase -> duncase.setAllocatType(AllocatTaskEnum.MANUAL.getAllocatCode()));
                    // 判断是否自动分配任务
                    if (caseConfig.getAllocatCaseStartegy().equals(AllocatCaseStartegyEnum.ALL_PERSON.getCode())) {
                        if (caseConfig.getAutoAllocatCase().equals(IsNoEnum.IS.getCode())) {
                            // 如果开启自动分配任务就将任务状态改为分配中，否则默认未分配
                            taskList.stream().forEach(task -> task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus()));
                            duncaseInsertList.stream().forEach(duncase -> duncase.setCaseStatus(TaskStatusEnum.ALLOCATING.getStatus()));
                            if (caseConfig.getAllocatCaseRule().equals(AllocatRuleEnum.DUNCASE_NUM_AVERAGE.getCode())) {
                                // 利用取模法按照数量平均分配任务
                                AllocatRuleUtil.averageAllocatTaskByNum(taskList, userList);
                            } else if (caseConfig.getAllocatCaseRule().equals(AllocatRuleEnum.DUNCASE_MONEY_AVERAGE.getCode())) {
                                // 按照逾期金额平均分配任务
                                AllocatRuleUtil.averageAllocatTaskByMoney(taskList, userList);
                            }
                        }
                    } else if (caseConfig.getAllocatCaseStartegy().equals(AllocatCaseStartegyEnum.ALL_ROBOT.getCode())) {
                        // 获取新案机器人呼叫策略规则
                        TLcCallStrategyConfig callStrategyConfig = TLcCallStrategyConfig.builder().businessScene(BusinessSceneEnum.NEW_DUNCASE.getCode()).status(IsNoEnum.IS.getCode()).orgId(String.valueOf(caseConfig.getOrgId())).build();
                        TLcCallStrategyConfig tLcCallStrategyConfig = this.callStrategyConfigService.selectTLcCallStrategyConfigList(callStrategyConfig).get(0);
                        taskList.stream().forEach(task -> {
                            task.setRobotCallStrategyId(tLcCallStrategyConfig.getId());
                            List<TLcCustContact> custContactList = this.custContactService.selectTLcCustContactList(TLcCustContact.builder().caseNo(task.getCaseNo()).relation(ContactRelaEnum.SELE.getCode()).build());
                            Integer robotTaskId = null;
                            if (caseConfig.getRobot().equals("BR")) {
                                robotTaskId = robotMethodUtil.createTask(task, tLcCallStrategyConfig, custContactList);
                                // 创建机器人任务并添加到机器人任务列表
                                String speechCraftName = sysDictDataService.selectDictLabel("robot_speech_craft", String.valueOf(tLcCallStrategyConfig.getSpeechcraftId()));
                                TLcRobotTask tLcRobotTask = createRobotTask(task, robotTaskId, speechCraftName, "BR");
                                this.robotTaskService.insertTLcRobotTask(tLcRobotTask);
                                // 开启机器人任务
                                robotMethodUtil.startTask(robotTaskId);
                            }
                            task.setRobotTaskId(robotTaskId);
                            // 对任务标的数据进行更新即将案件插入到任务表中
                            this.tLcTaskService.insertTLcTask(task);
                        });
                    }
                    this.tLcTaskMapper.batchInsertTask(taskList);
                }
            } else {
                // 修改任务表中的数据
                if (duncaseUpdateList != null && duncaseUpdateList.size() > 0) {
                    List<TLcTask> taskList = duncaseUpdateList.stream()
                            .map(duncase -> createUpdateTask(duncase))
                            .collect(Collectors.toList());
                    this.tLcTaskMapper.batchUpdateTask(taskList);
                }
            }
            // 分别插入借据信息表，客户信息表,客户工作表，客户联系人表
            insertData(duncaseInsertList, duncaseUpdateList, custInsertList, custUpdateList, jobInsertList, jobUpdateList, contactInsertList, contactUpdateList);
        } catch (Exception e) {
            throw new RuntimeException("接收到案件信息处理错误，error is {}", e);
        }
        return Response.success(null);
    }
*/
    @Override
    @Transactional
    public Response acceptDuncase(List<Assets> curAssetsPackageList) {
        if (curAssetsPackageList == null || curAssetsPackageList.size() <= 0) {
            log.error("从资产包过来的案件信息集合为空");
            throw new RuntimeException("从资产包过来的案件信息集合为空");
        }
        try {
            //获取当前用户登录信息
            // 创建案件集合、客户集合、客户单位集合、客户联系人集合
            List<TLcDuncase> duncaseInsertList = new ArrayList<>(curAssetsPackageList.size());
            List<TLcCustinfo> custInsertList = new ArrayList<>(curAssetsPackageList.size());
            List<TLcCustJob> jobInsertList = new ArrayList<>(curAssetsPackageList.size());
            List<TLcCustContact> contactInsertList = new ArrayList<>(curAssetsPackageList.size() * 3);
            curAssetsPackageList.stream().forEach(assetsPackage -> {
                //创建借据信息对象、客户信息对象、客户单位信息对象、客户联系人信息对象并分别加入到对应的集合中
                TLcDuncase tLcDuncase = createTLcDuncase(assetsPackage);
                TLcCustinfo tLcCustinfo = createCustinfo(assetsPackage);
                TLcCustJob tLcCustJob = createJobInfo(assetsPackage);
                List<TLcCustContact> contacts = createContactList(assetsPackage);
//                // 根据案件号、机构判断该借据是否存在，如果存在则修改，反之新增
//                TLcDuncase duncase = this.tLcDuncaseMapper.findDuncaseByCaseNo(assetsPackage.getOrgCasno(), assetsPackage.getOrgId());
                duncaseInsertList.add(tLcDuncase);
                custInsertList.add(tLcCustinfo);
                jobInsertList.add(tLcCustJob);
                contacts.stream().forEach(contact -> contactInsertList.add(contact));
            });
            TLcAllocatCaseConfig caseConfig = this.caseConfigService.selectTLcAllocatCaseConfigByOrgId(curAssetsPackageList.get(0).getOrgId());
            if (duncaseInsertList != null && duncaseInsertList.size() > 0) {
                if (StringUtils.isNoneBlank(caseConfig.getRuleEngine()) && caseConfig.getAllocatCaseStartegy().equals(AllocatCaseStartegyEnum.ROBOT_PERSON.getCode())) {
                    // 开始调用规则引擎分配任务、并向任务表同步任务：只有新增的案件需要分配
                    allocatTaskAndSyncTask(duncaseInsertList, caseConfig);
                } else {
                    // 不调用规则引擎系统
                    SysUser sysUser = new SysUser();
                    sysUser.setDeptId(this.tLcTaskService.findDeptIdByOrgId(duncaseInsertList.get(0).getOrgId()));
                    List<SysUser> userList = this.tLcTaskService.searchAllUser(sysUser);
                    // 创建任务
                    List<TLcTask> taskList = duncaseInsertList.stream()
                            .map(duncase -> createInsertTask(duncase))
                            .collect(Collectors.toList());
                    taskList.stream().forEach(task -> task.setAllotType(AllocatTaskEnum.MANUAL.getAllocatCode()));
                    duncaseInsertList.stream().forEach(duncase -> duncase.setAllocatType(AllocatTaskEnum.MANUAL.getAllocatCode()));
                    // 判断是否全人工
                    if (caseConfig.getAllocatCaseStartegy().equals(AllocatCaseStartegyEnum.ALL_PERSON.getCode())) {
                        if (caseConfig.getAutoAllocatCase().equals(IsNoEnum.IS.getCode())) {
                            // 如果开启自动分配任务就将任务状态改为分配中，否则默认未分配
                            taskList.stream().forEach(task -> task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus()));
                            duncaseInsertList.stream().forEach(duncase -> duncase.setCaseStatus(TaskStatusEnum.ALLOCATING.getStatus()));
                            if (caseConfig.getAllocatCaseRule().equals(AllocatRuleEnum.DUNCASE_NUM_AVERAGE.getCode())) {
                                // 利用取模法按照数量平均分配任务
                                AllocatRuleUtil.averageAllocatTaskByNum(taskList, userList);
                            } else if (caseConfig.getAllocatCaseRule().equals(AllocatRuleEnum.DUNCASE_MONEY_AVERAGE.getCode())) {
                                // 按照逾期金额平均分配任务
                                AllocatRuleUtil.averageAllocatTaskByMoney(taskList, userList);
                            }
                        }
                        this.tLcTaskMapper.batchInsertTask(taskList);
                    } else if (caseConfig.getAllocatCaseStartegy().equals(AllocatCaseStartegyEnum.ALL_ROBOT.getCode())) {
                        // 获取新案机器人呼叫策略规则
                        TLcCallStrategyConfig callStrategyConfig = TLcCallStrategyConfig.builder().businessScene(BusinessSceneEnum.NEW_DUNCASE.getCode()).status(IsNoEnum.IS.getCode()).orgId(String.valueOf(caseConfig.getOrgId())).build();
                        TLcCallStrategyConfig tLcCallStrategyConfig = this.callStrategyConfigService.selectTLcCallStrategyConfigList(callStrategyConfig).get(0);
                        // 获取单次推送到机器人的号码数
                        String taskCallNum = this.sysDictDataService.selectDictLabel("robot_call_config", "task_call_num");
                        // 获取ai坐席数
                        TLcOrgSpeechcraftConf orgSpeechcraftConf = this.orgSpeechcraftConfService.selectTLcOrgSpeechcraftConfByOrgId(caseConfig.getOrgId());
                        if (caseConfig.getRobot().equals("BR")) {
                            if (taskList.size() <= Integer.valueOf(taskCallNum)) {
                                Integer robotTaskId = this.robotMethodUtil.createTask(taskList, callStrategyConfig, tLcCallStrategyConfig.getContinueCallDays(), tLcCallStrategyConfig.getCallFrequencyDay(), orgSpeechcraftConf, DateUtils.parseDateToStr(DateUtils.YYYYMMDDHHMMSS,new Date()));
                                taskList.stream().forEach(task -> {
                                    task.setRobotCallStrategyId(tLcCallStrategyConfig.getId());
                                    task.setRobotTaskId(robotTaskId);
                                });
                                this.tLcTaskMapper.batchInsertTask(taskList);
                            } else {
                                Integer taskNums = taskList.size() / Integer.valueOf(taskCallNum);
                                for (int i = 0; i < taskNums; i++) {
                                    List subTaskIdList = taskList.subList(i * Integer.valueOf(taskCallNum), (i + 1) * Integer.valueOf(taskCallNum));
                                    Integer robotTaskId = this.robotMethodUtil.createTask(subTaskIdList, callStrategyConfig, tLcCallStrategyConfig.getContinueCallDays(), tLcCallStrategyConfig.getCallFrequencyDay(), orgSpeechcraftConf, DateUtils.parseDateToStr(DateUtils.YYYYMMDDHHMMSS,new Date()));
                                    taskList.stream().forEach(task -> {
                                        task.setRobotCallStrategyId(tLcCallStrategyConfig.getId());
                                        task.setRobotTaskId(robotTaskId);
                                    });
                                    this.tLcTaskMapper.batchInsertTask(taskList);
                                }
                                if (taskList.size() % Integer.valueOf(taskCallNum) != 0) {
                                    List subTaskIdList = taskList.subList(Integer.valueOf(taskCallNum) * taskNums, taskList.size());
                                    Integer robotTaskId = this.robotMethodUtil.createTask(subTaskIdList, callStrategyConfig, tLcCallStrategyConfig.getContinueCallDays(), tLcCallStrategyConfig.getCallFrequencyDay(), orgSpeechcraftConf, DateUtils.parseDateToStr(DateUtils.YYYYMMDDHHMMSS,new Date()));
                                    taskList.stream().forEach(task -> {
                                        task.setRobotCallStrategyId(tLcCallStrategyConfig.getId());
                                        task.setRobotTaskId(robotTaskId);
                                    });
                                    this.tLcTaskMapper.batchInsertTask(taskList);
                                }
                            }
                        }
                    }
                }
            }
            // 分别插入借据信息表，客户信息表,客户工作表，客户联系人表
            insertData(duncaseInsertList, custInsertList, jobInsertList, contactInsertList);
        } catch (Exception e) {
            throw new RuntimeException("接收到案件信息处理错误，error is {}", e);
        }
        return Response.success(null);
    }


    @Override
    @Transactional
    public Response acceptDuncaseUpdate(List<Assets> curAssetsPackageList) {
        if (curAssetsPackageList == null || curAssetsPackageList.size() <= 0) {
            log.error("从资产包过来的案件信息集合为空");
            throw new RuntimeException("从资产包过来的案件信息集合为空");
        }
        try {
            //获取当前用户登录信息
            // 创建案件集合、客户集合、客户单位集合、客户联系人集合
            List<TLcDuncase> duncaseUpdateList = new ArrayList<>(curAssetsPackageList.size());
            List<TLcCustinfo> custUpdateList = new ArrayList<>(curAssetsPackageList.size());
            List<TLcCustJob> jobUpdateList = new ArrayList<>(curAssetsPackageList.size());
            List<TLcCustContact> contactUpdateList = new ArrayList<>(curAssetsPackageList.size() * 3);
            curAssetsPackageList.stream().forEach(assetsPackage -> {
                //创建借据信息对象、客户信息对象、客户单位信息对象、客户联系人信息对象并分别加入到对应的集合中
                TLcDuncase tLcDuncase = createTLcDuncase(assetsPackage);
                TLcCustinfo tLcCustinfo = createCustinfo(assetsPackage);
                TLcCustJob tLcCustJob = createJobInfo(assetsPackage);
                List<TLcCustContact> contacts = createContactList(assetsPackage);
                // 根据案件号、机构、导入批次号判断该借据是否存在，如果存在则修改，反之新增
                TLcDuncase duncase = this.tLcDuncaseMapper.findDuncaseByCaseNoAndImportBatchNo(assetsPackage.getOrgCasno(), assetsPackage.getOrgId(), assetsPackage.getImportBatchNo());
                if (duncase != null) {
                    duncaseUpdateList.add(tLcDuncase);
                    custUpdateList.add(tLcCustinfo);
                    jobUpdateList.add(tLcCustJob);
                    contacts.stream().forEach(contact -> contactUpdateList.add(contact));
                }
            });

            // 修改任务表中的数据
            if (duncaseUpdateList != null && duncaseUpdateList.size() > 0) {
                List<TLcTask> taskList = duncaseUpdateList.stream()
                        .map(duncase -> createUpdateTask(duncase))
                        .collect(Collectors.toList());
                this.tLcTaskMapper.batchUpdateTask(taskList);
            }
            // 分别插入借据信息表，客户信息表,客户工作表，客户联系人表
            updateData(duncaseUpdateList, custUpdateList, jobUpdateList, contactUpdateList);
        } catch (Exception e) {
            throw new RuntimeException("接收到案件信息处理错误，error is {}", e);
        }
        return Response.success(null);
    }


    /**
     * 创建机器人任务
     *
     * @param tLcTask
     * @param robotTaskId
     * @return
     */
    private TLcRobotTask createRobotTask(TLcTask tLcTask, Integer robotTaskId, String speechCraftName, String robotType) {
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
                .setRobot(robotType)
                .setCreateBy(String.valueOf(ShiroUtils.getUserId()));
        return tLcRobotTask;
    }

    private TLcTask createUpdateTask(TLcDuncase duncase) {
        TLcTask tLcTask = this.tLcTaskService.selectTaskByCaseNo(duncase.getCaseNo(), duncase.getOrgId(), duncase.getImportBatchNo());
        tLcTask.setCaseNo(duncase.getCaseNo())
                .setCertificateNo(duncase.getCertificateNo())
                .setCertificateType(duncase.getCertificateType())
                .setCustomCode(duncase.getCustomNo())
                .setCustomName(duncase.getCustomName())
                .setArrearsTotal(duncase.getAppointCaseBalance())
                .setModifyOwnerTime(LocalDateTime.now(ZoneId.systemDefault()))
                .setOverdueDays(duncase.getOverdueDays())
                .setCollectTimeLimit(null)
                .setCollectLastTime(null)
                .setCollectTeamId(null)
                .setCollectTeamName(null)
                .setOwnerId(duncase.getOwnerId())
                .setOwnerName(duncase.getOwnerName())
                .setOrgId(duncase.getOrgId())
                .setOrgName(duncase.getOrgName())
                .setOldOwnerId(null)
                .setCloseDate(null)
                .setValidateStatus(IsNoEnum.IS.getCode())
                .setModifyBy(duncase.getModifyBy())
                .setTransferType(duncase.getTransferType()) //手别
                .setEnterCollDate(duncase.getEnterCollDate()) // 入催日
                .setCloseCaseYhje(duncase.getCloseCaseYhje()) // 结案应还金额
                .setImportBatchNo(duncase.getImportBatchNo())
                .setUpdateBy(duncase.getCreateBy());
        return tLcTask;
    }

    private TLcTask createInsertTask(TLcDuncase duncase) {
        TLcTask task = TLcTask.builder()
                .caseNo(duncase.getCaseNo())
                .certificateNo(duncase.getCertificateNo())
                .certificateType(duncase.getCertificateType())
                .customCode(duncase.getCustomNo())
                .customName(duncase.getCustomName())
                .arrearsTotal(duncase.getAppointCaseBalance())
                .taskStatus(TaskStatusEnum.NO_ALLOCAT.getStatus())
                .modifyOwnerTime(LocalDateTime.now(ZoneId.systemDefault()))
                .overdueDays(duncase.getOverdueDays())
                .collectTimeLimit(null)
                .collectLastTime(null)
                .collectTeamId(null)
                .collectTeamName(null)
                .ownerId(duncase.getOwnerId())
                .ownerName(duncase.getOwnerName())
                .orgId(duncase.getOrgId())
                .orgName(duncase.getOrgName())
                .oldOwnerId(null)
                .closeDate(null)
                .validateStatus(IsNoEnum.IS.getCode())
                .allotType(duncase.getAllocatType())
                .taskType(TaskTypeEnum.FIRST_CREATE.getCode())
                .modifyBy(duncase.getModifyBy())
                .transferType(duncase.getTransferType()) //手别
                .enterCollDate(duncase.getEnterCollDate()) // 入催日
                .closeCaseYhje(duncase.getCloseCaseYhje()) // 结案应还金额
                .importBatchNo(duncase.getImportBatchNo())
                .phone(duncase.getCustomPhone())
                .build();
        task.setCreateBy(duncase.getCreateBy());
        return task;
    }

    @Override
    public TLcDuncase findDuncaseByCaseNo(String caseNo, String orgId) {
        return this.tLcDuncaseMapper.findDuncaseByCaseNo(caseNo, orgId);
    }

    @Override
    public TLcDuncase findDuncaseByCaseNoAndImportBatchNo(String caseNo, String orgId, String importBatchNo) {
        return this.tLcDuncaseMapper.findDuncaseByCaseNoAndImportBatchNo(caseNo, orgId, importBatchNo);
    }

    /**
     * 将案件信息插入到案件轨迹表
     *
     * @param duncaseInsertList
     */
    private void batchInsertDuncaseAssign(List<TLcDuncase> duncaseInsertList) {
        SysUser sysUser = this.sysUserService.selectUserById(Long.valueOf(duncaseInsertList.get(0).getCreateBy()));
        List<TLcDuncaseAssign> duncaseAssignList = duncaseInsertList.stream()
                .map(duncase -> {
                    TLcDuncaseAssign tLcDuncaseAssign = TLcDuncaseAssign.builder()
                            .caseNo(duncase.getCaseNo())
                            .certificateNo(duncase.getCertificateNo())
                            .collectTeamId(null)
                            .collectTeamName(null)
                            .customName(duncase.getCustomName())
                            .operationId(sysUser.getUserId())
                            .operationName(sysUser.getUserName())
                            .ownerId(duncase.getOwnerId())
                            .orgName(duncase.getOwnerName())
                            .orgId(duncase.getOrgId())
                            .orgName(duncase.getOrgName())
                            .taskId(null)
                            .taskStatus(2)
                            .transferType(TaskTypeEnum.FIRST_CREATE.getCode())
                            .validateStatus(IsNoEnum.IS.getCode())
                            .build();
                    tLcDuncaseAssign.setCreateBy(notNullToString(sysUser.getUserId()));
                    return tLcDuncaseAssign;
                }).collect(Collectors.toList());
        this.tLcDuncaseAssignMapper.batchInsertDuncaseAssign(duncaseAssignList);
    }

    /**
     * 分别插入或者修改借据信息表，客户信息表,客户工作表，客户联系人表、生成数据到案件表
     *
     * @param duncaseUpdateList
     * @param custUpdateList
     * @param jobUpdateList
     * @param contactUpdateList
     */
    private void updateData(List<TLcDuncase> duncaseUpdateList, List<TLcCustinfo> custUpdateList, List<TLcCustJob> jobUpdateList, List<TLcCustContact> contactUpdateList) {
        //如果原案件、用户存在，则修改，反之新增
        if (duncaseUpdateList != null && duncaseUpdateList.size() > 0) {
            this.tLcDuncaseMapper.batchUpdateDuncase(duncaseUpdateList);
            this.tLcCustinfoMapper.batchUpdateCustinfo(custUpdateList);
            this.tLcCustJobMapper.batchUpdateCustJob(jobUpdateList);
            // this.tLcCustContactMapper.batchUpdateContact(contactUpdateList);
        }
    }


    private void insertData(List<TLcDuncase> duncaseInsertList, List<TLcCustinfo> custInsertList, List<TLcCustJob> jobInsertList, List<TLcCustContact> contactInsertList) {
        if (duncaseInsertList != null && duncaseInsertList.size() > 0) {
            this.tLcDuncaseMapper.batchInsertDuncase(duncaseInsertList);
            this.tLcCustinfoMapper.batchInsertCustinfo(custInsertList);
            this.tLcCustJobMapper.batchInsertCustJob(jobInsertList);
            this.tLcCustContactMapper.batchInsertContact(contactInsertList);
            // 将案件信息插入到案件轨迹表
            batchInsertDuncaseAssign(duncaseInsertList);
        }
    }

    /**
     * 同步任务表数据并分配任务
     *
     * @param duncaseInsertList
     */
    private void allocatTaskAndSyncTask(List<TLcDuncase> duncaseInsertList, TLcAllocatCaseConfig caseConfig) {
        log.info("调用规则引擎任务分配规则的接口是{}", allocatTaskUrl);
        OrgPackage orgPackage = this.orgPackageService.selectOrgPackageByOrgId(duncaseInsertList.get(0).getOrgId());
        // 构建所需参数
        duncaseInsertList = duncaseInsertList.stream().map(duncase -> {
            AllocatTaskInvokeRuleEngin allocatTaskInvokeRuleEngin = createAllocatTaskInvokeRuleEngin(orgPackage, duncase, caseConfig);
            Map caseInfoDTOMap = getAllocatTaskCodeByRuleEngine(allocatTaskInvokeRuleEngin);
            if (caseInfoDTOMap == null) {
                duncase.setAllocatType(AllocatTaskEnum.MANUAL.getAllocatCode());
                duncase.setHitRuleDesc("调用规则引擎接口异常，没有命中对应的规则，案件自动分配到人工");
                log.error("调用规则引擎接口异常，案件自动分配到人工，案件编号是{}", duncase.getCaseNo());
            } else {
                log.info("规则引擎分配案件的编码是{}", caseInfoDTOMap.get("distributionStrategy"));
                Integer distributionStrategy = AllocatTaskEnum.getAllocatTypeByRule((String) caseInfoDTOMap.get("distributionStrategy"));
                if (distributionStrategy == null) {
                    duncase.setAllocatType(AllocatTaskEnum.MANUAL.getAllocatCode());
                    log.error("规则引擎接口返回的分配策略不存在，案件自动分配到人工，案件编号是{}，分配策略是{}", duncase.getCaseNo(), caseInfoDTOMap.get("distributionStrategy"));
                }
                duncase.setAllocatType(distributionStrategy);
                duncase.setHitRule((String) caseInfoDTOMap.get("hitRule"));
                duncase.setHitRuleDesc((String) caseInfoDTOMap.get("hitRuleDesc"));
                duncase.setDistributionStrategy((String) caseInfoDTOMap.get("distributionStrategy"));
            }
            return duncase;
        }).collect(Collectors.toList());
        // 根据分配类型不同，生成不同的任务集合
        Map<Integer, List<TLcDuncase>> duncaseMap = duncaseInsertList.stream()
                .collect(Collectors.groupingBy(TLcDuncase::getAllocatType));
        // 遍历map，然后根据规则引擎返回的分配方式进行分配任务
        for (Map.Entry<Integer, List<TLcDuncase>> duncase : duncaseMap.entrySet()) {
            AllocatTaskEnum.valueOf(AllocatTaskEnum.getAllocatType(duncase.getKey())).allocatTask(duncase.getValue(), caseConfig);
        }
    }

    /**
     * 创建调用规则引擎接口对象
     *
     * @param orgPackage
     * @param duncase
     * @param caseConfig
     * @return
     */
    private AllocatTaskInvokeRuleEngin createAllocatTaskInvokeRuleEngin(OrgPackage orgPackage, TLcDuncase duncase, TLcAllocatCaseConfig caseConfig) {
        AllocatTaskInvokeRuleEngin allocatTaskInvokeRuleEngin = new AllocatTaskInvokeRuleEngin();
        allocatTaskInvokeRuleEngin.setSysCode(orgPackage.getOrgCode())
                .setFrontTransNo(duncase.getCaseNo())
                .setFrontTransTime(LocalDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern(DateUtils.YYYY_MM_DD_HH_MM_SS)))
                .setRulePackage(rulePackage + caseConfig.getRuleEngine())
                .setProcessId(caseConfig.getRuleEngine())
                .setFacts(getFact(duncase));
        return allocatTaskInvokeRuleEngin;
    }

    /**
     * 根据调用规则引擎接口获取相应的分案策略
     *
     * @param allocatTaskInvokeRuleEngin
     * @return
     */
    private Map getAllocatTaskCodeByRuleEngine(AllocatTaskInvokeRuleEngin allocatTaskInvokeRuleEngin) {
        log.info("调用分案规则接口的参数：{}", JSONObject.toJSONString(allocatTaskInvokeRuleEngin));
        ResponseEntity<Map> response = restTemplateUtil.getRestTemplate().postForEntity(allocatTaskUrl, allocatTaskInvokeRuleEngin, Map.class);
        if (response.getStatusCodeValue() != HttpStatus.OK.value()) {
            log.error("调用分案规则接口错误，response.getStatusCodeValue is {}", response.getStatusCodeValue());
            return null;
        }
        String retCode = (String) response.getBody().get("retCode");
        log.info("allocatTask retCode is {}", retCode);
        if (!"0000".equals(retCode)) {
            log.error("调用分案规则接口错误，retCode{}", retCode);
            return null;
        }
        Map responseBodyMap = (Map) response.getBody().get("responseBody");
        Map factsMap = (Map) responseBodyMap.get("facts");
        return (Map) factsMap.get("caseInfoDTO");
    }

    private AllocatTaskInvokeRuleEngin.Fact getFact(TLcDuncase tLcDuncase) {
        AllocatTaskInvokeRuleEngin.Fact fact = new AllocatTaskInvokeRuleEngin().new Fact();
        fact.setCaseInfoDTO(getCaseInfo(tLcDuncase));
        return fact;
    }

    private AllocatTaskInvokeRuleEngin.CaseInfoDTO getCaseInfo(TLcDuncase tLcDuncase) {
        AllocatTaskInvokeRuleEngin.CaseInfoDTO caseInfoDTO = new AllocatTaskInvokeRuleEngin().new CaseInfoDTO();
        caseInfoDTO.setCaseCd(tLcDuncase.getCaseNo())
                .setCustomerName(tLcDuncase.getCustomName())
                .setCityName(tLcDuncase.getWwCityName())
                .setHands(tLcDuncase.getHandSeparation())
                .setBlk(tLcDuncase.getBlk())
                .setStartDate(notNullToString(tLcDuncase.getWwQsrq()))
                .setEndDate(notNullToString(tLcDuncase.getWwJhEnddate()))
                .setSex(tLcDuncase.getCurSex())
                .setOverdueDay(notNullToString(tLcDuncase.getOverdueDays()))
                .setBalanceRMB(notNullToString(tLcDuncase.getBalanceRmb()))
                .setLastPayRMB(notNullToString(tLcDuncase.getLowestPaymentRmb()))
                .setBalanceDollar(notNullToString(tLcDuncase.getDollarYe()))
                .setLastPayDollar(notNullToString(tLcDuncase.getDollarZdyjkje()))
                .setBillDay(notNullToString(tLcDuncase.getMonthRepayDay()));
        return caseInfoDTO;
    }

    /**
     * 如果不为空转字符串
     *
     * @param object
     * @return
     */
    private String notNullToString(Object object) {
        if (Objects.nonNull(object)) {
            return object.toString();
        }
        return null;
    }

    /**
     * 创建客户单位信息对象
     *
     * @param assetsPackage
     * @return
     */
    private TLcCustJob createJobInfo(Assets assetsPackage) {
        TLcCustJob tLcCustJob = TLcCustJob.builder()
                .customCode(assetsPackage.getCustomerNo())
                .companyTel(assetsPackage.getWorkTel())
                .companyName(assetsPackage.getWorkName())
                .companyAddress(assetsPackage.getWorkAddress())
                .companyPostcode(assetsPackage.getWorkPostcode())
                .companyIndustry(assetsPackage.getIndust())
                .profession(assetsPackage.getJob())
                .certificateNo(assetsPackage.getCertificateNo())
                .modifyBy(assetsPackage.getSendOptId())
                .validateStatus(IsNoEnum.IS.getCode())
                .caseNo(assetsPackage.getOrgCasno())
                .modifyBy(assetsPackage.getUpdateId())
                .orgId(assetsPackage.getOrgId()) //业务归属机构
                .orgName(this.tLcDuncaseMapper.selectOrgNameByOrgId(assetsPackage.getOrgId())) //业务归属机构名称
                .importBatchNo(assetsPackage.getImportBatchNo())
                .build();
        tLcCustJob.setCreateBy(notNullToString(assetsPackage.getSendOptId()));
        return tLcCustJob;
    }

    /**
     * 根据从资产包传过来的借据信息创建逾期客户联系人对象
     *
     * @param assetsPackage
     * @return
     */
    private List<TLcCustContact> createContactList(Assets assetsPackage) {
        List<TLcCustContact> tLcCustContactList = new ArrayList<>();
        // 本人
        TLcCustContact selfContact = buildContactCommon(assetsPackage);
        selfContact = selfContact.setContactName(assetsPackage.getCurName())
                .setRelation(ContactRelaEnum.SELE.getCode())
                .setPhone(assetsPackage.getCustomerMobile())
                .setTel(assetsPackage.getCustomerHomeTel());
        tLcCustContactList.add(selfContact);
        // 第一联系人手机
        if (StringUtils.isNotEmpty(assetsPackage.getFirstLiaisonName()) && StringUtils.isNotEmpty(assetsPackage.getFirstLiaisonMobile())) {
            TLcCustContact firstContact = buildContactCommon(assetsPackage);
            firstContact = firstContact.setContactName(assetsPackage.getFirstLiaisonName())
                    .setRelation(StringUtils.isNotEmpty(assetsPackage.getFirstLiaisonRelation()) ? ContactRelaEnum.getCodeByRela(assetsPackage.getFirstLiaisonRelation()) : ContactRelaEnum.UN_MATCH.getCode())
                    .setPhone(assetsPackage.getFirstLiaisonMobile())
                    .setTel(assetsPackage.getFirstLiaisonTel());
            tLcCustContactList.add(firstContact);
        }
        // 第二联系人
        if (StringUtils.isNotEmpty(assetsPackage.getSecondLiaisonName()) && StringUtils.isNotEmpty(assetsPackage.getSecondLiaisonMobile())) {
            TLcCustContact secondContact = buildContactCommon(assetsPackage);
            secondContact = secondContact.setContactName(assetsPackage.getSecondLiaisonName())
                    .setRelation(StringUtils.isNotEmpty(assetsPackage.getSecondLiaisonRelation()) ? ContactRelaEnum.getCodeByRela(assetsPackage.getSecondLiaisonRelation()) : ContactRelaEnum.UN_MATCH.getCode())
                    .setPhone(assetsPackage.getSecondLiaisonMobile())
                    .setTel(assetsPackage.getSecondLiaisonTel());
            tLcCustContactList.add(secondContact);
        }
        // 第三联系人
        if (StringUtils.isNotEmpty(assetsPackage.getThreeLiaisonName()) && StringUtils.isNotEmpty(assetsPackage.getThreeLiaisonMobile())) {
            TLcCustContact thirdContact = buildContactCommon(assetsPackage);
            thirdContact = thirdContact.setContactName(assetsPackage.getThreeLiaisonName())
                    .setRelation(StringUtils.isNotEmpty(assetsPackage.getThreeLiaisonRelation()) ? ContactRelaEnum.getCodeByRela(assetsPackage.getThreeLiaisonRelation()) : ContactRelaEnum.UN_MATCH.getCode())
                    .setPhone(assetsPackage.getThreeLiaisonMobile())
                    .setTel(assetsPackage.getThreeLiaisonTel());
            tLcCustContactList.add(thirdContact);
        }
        // 第四联系人
        if (StringUtils.isNotEmpty(assetsPackage.getFourthLiaisonName()) && StringUtils.isNotEmpty(assetsPackage.getFourthLiaisonMobile())) {
            TLcCustContact fouthContact = buildContactCommon(assetsPackage);
            fouthContact = fouthContact.setContactName(assetsPackage.getFourthLiaisonName())
                    .setRelation(StringUtils.isNotEmpty(assetsPackage.getFourthLiaisonRelation()) ? ContactRelaEnum.getCodeByRela(assetsPackage.getFourthLiaisonRelation()) : ContactRelaEnum.UN_MATCH.getCode())
                    .setPhone(assetsPackage.getFourthLiaisonMobile());
            tLcCustContactList.add(fouthContact);
        }
        // 第5联系人
        if (StringUtils.isNotEmpty(assetsPackage.getFifthLiaisonName()) && StringUtils.isNotEmpty(assetsPackage.getFifthLiaisonMobile())) {
            TLcCustContact fifthContact = buildContactCommon(assetsPackage);
            fifthContact = fifthContact.setContactName(assetsPackage.getFifthLiaisonName())
                    .setRelation(StringUtils.isNotEmpty(assetsPackage.getFifthLiaisonRelation()) ? ContactRelaEnum.getCodeByRela(assetsPackage.getFifthLiaisonRelation()) : ContactRelaEnum.UN_MATCH.getCode())
                    .setPhone(assetsPackage.getFifthLiaisonMobile());
            tLcCustContactList.add(fifthContact);
        }
        // 单位联系人
        if (StringUtils.isNotEmpty(assetsPackage.getWorkTel())) {
            TLcCustContact organContact = buildContactCommon(assetsPackage);
            organContact = organContact.setContactName("单位电话")
                    .setRelation(ContactRelaEnum.getCodeByRela("单位"))
                    .setPhone(assetsPackage.getWorkTel());
            tLcCustContactList.add(organContact);
        }
        // 客户电话2
        if (StringUtils.isNotEmpty(assetsPackage.getCustomerMobile2()) && !assetsPackage.getCustomerMobile2().equals(assetsPackage.getCustomerMobile())) {
            TLcCustContact selfContact2 = buildContactCommon(assetsPackage);
            selfContact2 = selfContact2.setContactName(assetsPackage.getCurName())
                    .setRelation(ContactRelaEnum.SELE.getCode())
                    .setPhone(assetsPackage.getCustomerMobile2());
            tLcCustContactList.add(selfContact2);
        }
        // 客户电话3
        if (StringUtils.isNotEmpty(assetsPackage.getCustomerMobile3())  && !assetsPackage.getCustomerMobile3().equals(assetsPackage.getCustomerMobile())) {
            TLcCustContact selfContact3 = buildContactCommon(assetsPackage);
            selfContact3 = selfContact3.setContactName(assetsPackage.getCurName())
                    .setRelation(ContactRelaEnum.SELE.getCode())
                    .setPhone(assetsPackage.getCustomerMobile3());
            tLcCustContactList.add(selfContact3);
        }
        // 客户电话4
        if (StringUtils.isNotEmpty(assetsPackage.getCustomerMobile4())  && !assetsPackage.getCustomerMobile4().equals(assetsPackage.getCustomerMobile())) {
            TLcCustContact selfContact4 = buildContactCommon(assetsPackage);
            selfContact4 = selfContact4.setContactName(assetsPackage.getCurName())
                    .setRelation(ContactRelaEnum.SELE.getCode())
                    .setPhone(assetsPackage.getCustomerMobile4());
            tLcCustContactList.add(selfContact4);
        }
        return tLcCustContactList;
    }

    /**
     * 构建客户联系人公共属性
     *
     * @param assetsPackage
     * @return
     */
    private TLcCustContact buildContactCommon(Assets assetsPackage) {
        TLcCustContact contact = TLcCustContact.builder()
                .certificateNo(assetsPackage.getCertificateNo())
                .modifyBy(assetsPackage.getSendOptId())
                .address(null)
                .validateStatus(IsNoEnum.IS.getCode())
                .origin(ContactOriginEnum.ASSET_IMPORT.getCode())
                .caseNo(assetsPackage.getOrgCasno())
                .modifyBy(assetsPackage.getUpdateId())
                .orgId(assetsPackage.getOrgId()) //业务归属机构
                .orgName(this.tLcDuncaseMapper.selectOrgNameByOrgId(assetsPackage.getOrgId())) //业务归属机构名称
                .importBatchNo(assetsPackage.getImportBatchNo())
                .build();
        contact.setCreateBy(notNullToString(assetsPackage.getSendOptId()));
        return contact;
    }

    /**
     * 根据从资产包传过来的借据信息创建逾期客户对象
     *
     * @param assetsPackage
     * @return
     */
    private TLcCustinfo createCustinfo(Assets assetsPackage) {
        TLcCustinfo tLcCustinfo = TLcCustinfo.builder()
                .customCode(assetsPackage.getCustomerNo()) //客户编号
                .certificateAddress(assetsPackage.getCertificateAddress()) //证件地址
                .censusAddress(assetsPackage.getRegistAddress()) //户籍地址
                .profession(assetsPackage.getJob()) //职业身份
                .city(assetsPackage.getCity())
                .education(assetsPackage.getEducation())
                .phone(assetsPackage.getCustomerMobile())
                .tel(assetsPackage.getCustomerHomeTel())
                .email(assetsPackage.getEmail())
                .incomeYear(assetsPackage.getCurIncome())
                .marrageStatus(StringUtils.isNoneBlank(assetsPackage.getMarriage()) ? MarriageStatusEnum.getCodeByDes(assetsPackage.getMarriage()) : null)
                .hasChild(null)
                .hasHouse(null)
                .hasCar(null)
                .orgId(assetsPackage.getOrgId())
                .orgName(this.tLcDuncaseMapper.selectOrgNameByOrgId(assetsPackage.getOrgId()))
                .address(assetsPackage.getCustomerHomeAddress())
                .modifyBy(assetsPackage.getSendOptId())
                .validateStatus(IsNoEnum.IS.getCode())
                .certificateNo(assetsPackage.getCertificateNo())
                .customName(assetsPackage.getCurName())
                .customSex(SexEnum.getCodeByMessage(assetsPackage.getCurSex()))
                .birthday(assetsPackage.getBirthday())
                .certificateType(CertificateTypeEnum.getCodeByType(assetsPackage.getCertificateType()))
                .caseNo(assetsPackage.getOrgCasno())
                .modifyBy(assetsPackage.getUpdateId())
                .importBatchNo(assetsPackage.getImportBatchNo())
                .build();
        tLcCustinfo.setCreateBy(notNullToString(assetsPackage.getSendOptId()));
        return tLcCustinfo;
    }

    /**
     * 根据从资产包传过来的借据信息创建借据信息对象
     *
     * @param assetsPackage
     * @return
     */
    private TLcDuncase createTLcDuncase(Assets assetsPackage) {
        TLcDuncase tLcDuncase = TLcDuncase.builder()
                .caseNo(assetsPackage.getOrgCasno()) //案件号
                .customName(assetsPackage.getCurName())   //客户姓名
                .certificateNo(assetsPackage.getCertificateNo()) //身份证号
                .customPhone(assetsPackage.getCustomerMobile()) //客户手机号
                .monthRepayDay(stringConverLong(assetsPackage.getAccountDate())) //账单日
                .overdueDays(stringConverLong(assetsPackage.getOverdueDays())) //逾期天数
                .repayAccountNo(assetsPackage.getRevertCardNo()) //还款卡号
                .repayBank(assetsPackage.getRevertCardBlank()) //还款银行
                .modifyBy(assetsPackage.getUpdateId()) //修改人
                .orgId(assetsPackage.getOrgId()) //业务归属机构
                .orgName(this.tLcDuncaseMapper.selectOrgNameByOrgId(assetsPackage.getOrgId())) //业务归属机构名称
                .caseStatus(TaskStatusEnum.NO_ALLOCAT.getStatus()) //案件状态==任务状态
                .firstOverdueTime(assetsPackage.getFirstYqDate()) //首次逾期时间
                .maxOverdueDay(stringConverLong(assetsPackage.getMaxYqtsHis())) //历史最大逾期天数
                .repayDate(assetsPackage.getFirstYqjcDate()) //应还日期-取首次逾期解除日期
                .borrowLine(assetsPackage.getBorrowEd()) //借款额度
                .borrowCardNo(assetsPackage.getBorrowNo()) //借款卡号
                .borrowBank(assetsPackage.getBorrowBlank()) //借款卡银行
                .totalExpensesPayableRmb(assetsPackage.getRmbYhfyzje()) //人民币账户应还费用总金额
                .balanceRmb(assetsPackage.getRmbYe()) //人民币账户余额
                .totalDefaultInterestRmb(assetsPackage.getRmbYhfxzje()) //人民币账户应还罚息总额
                .fixLimitRmb(assetsPackage.getRmbGded()) //人民币账户额度固定额度
                .lastRepayAmountRmb(assetsPackage.getRmbLastJkje()) //人民币账户最后缴款金额
                .lastRepayDateRmb(assetsPackage.getRmbZhycjkr()) //人民币账户最后一次缴款日
                .totalPrincipalRmb(assetsPackage.getRmbYhbjzje()) // 人民币账户应还本金总额
                .lowestPaymentRmb(assetsPackage.getRmbZdyhje()) // 人民币账户最低应还金额
                .totalDebtAmountRmb(assetsPackage.getRmbQkzje()) // 人民币欠款总金额
                .totalInterestRmb(assetsPackage.getRmbYhlizje()) // 应还利息
                .appointCaseBalance(assetsPackage.getRmbYe()) //委案金额
                .transferType(assetsPackage.getTransfertype()) //手别
                .enterCollDate(assetsPackage.getRcr()) // 入催日
                .closeCaseYhje(assetsPackage.getWaYe()) // 结案应还金额
                .overdueFine(assetsPackage.getZnj())
                .city(assetsPackage.getWwCityName())
                .area(assetsPackage.getAreaCenter())
                .recommendVendor(assetsPackage.getTjFirm())
                .recommendWebsite(assetsPackage.getTjWd())
                .productName(assetsPackage.getCpmc())
                .repayMethod(assetsPackage.getHkType())
                .agingPeriods(assetsPackage.getFz())
                .billAddress(assetsPackage.getBillAddress())
                .yearInterestRate(assetsPackage.getYearRates())
                .dayInterestRate(assetsPackage.getDayRates())
                .firstOverdueSign(assetsPackage.getFirstYqFlag())
                .totalOverdueDay(assetsPackage.getSumYqtsHis())
                .overdueFrequency(assetsPackage.getSumYqcsHis())
                .importBatchNo(assetsPackage.getImportBatchNo())
                .packNo(assetsPackage.getPackNo())
                .backCaseDate(assetsPackage.getTar())
                .loanType(assetsPackage.getDklx())
                .stayCaseFlag(assetsPackage.getLaFlag())
                .riskFlag(assetsPackage.getFxFlag())
                .contractType(assetsPackage.getHtlx())
                .reductionFlag(assetsPackage.getJmbq())
                .legalFlag(assetsPackage.getFcbq())
                .ljyhje(assetsPackage.getLjyhje())//累计已还金额
                .build();
        tLcDuncase.setCreateBy(notNullToString(assetsPackage.getSendOptId()));
        return tLcDuncase;
    }

    /**
     * string类型转Long类型
     *
     * @param accountDate
     * @return
     */
    private Long stringConverLong(String accountDate) {
        if (StringUtils.isEmpty(accountDate)) {
            return null;
        }
        return Long.valueOf(accountDate);
    }

    /**
     * 根据逾期天数判断逾期账龄
     *
     * @param overdueDays
     * @return
     */
    private String getOverterm(Long overdueDays) {
        if (overdueDays == null) {
            return null;
        }
        String overterm = null;
        if (overdueDays <= 30) {
            overterm = "M1";
        } else if (overdueDays > 30 && overdueDays <= 60) {
            overterm = "M2";
        } else if (overdueDays > 60 && overdueDays <= 90) {
            overterm = "M3";
        } else if (overdueDays > 90 && overdueDays <= 120) {
            overterm = "M4";
        } else if (overdueDays > 120 && overdueDays <= 150) {
            overterm = "M5";
        } else if (overdueDays > 150) {
            overterm = "M6";
        }
        return overterm;
    }

    @Override
    public List<Map<String,Object>> selectDuncaseByTime(Date createTime, int pageNum, int pageSize) {
        return this.tLcDuncaseMapper.selectDuncaseByTime(createTime,pageNum,pageSize);
    }

    @Override
    public Integer selectDuncaseCount(Date createTime) {

        return tLcDuncaseMapper.selectDuncaseCount(createTime);
    }

    @Override
    public void updateScore(List<TLcScore> TLcScoreList) {
         tLcDuncaseMapper.updateScore(TLcScoreList);
    }

}
