package com.ruoyi.enums;

import com.alibaba.druid.sql.visitor.functions.Isnull;
import com.ruoyi.assetspackage.domain.OrgPackage;
import com.ruoyi.callConfig.domain.TLcCallStrategyConfig;
import com.ruoyi.callConfig.service.impl.TLcCallStrategyConfigServiceImpl;
import com.ruoyi.caseConfig.domain.TLcAllocatCaseConfig;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.config.AppConfig;
import com.ruoyi.custom.domain.TLcCustContact;
import com.ruoyi.custom.service.impl.TLcCustContactServiceImpl;
import com.ruoyi.duncase.domain.TLcDuncase;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.orgSpeechConf.domain.TLcOrgSpeechcraftConf;
import com.ruoyi.orgSpeechConf.service.ITLcOrgSpeechcraftConfService;
import com.ruoyi.orgSpeechConf.service.impl.TLcOrgSpeechcraftConfServiceImpl;
import com.ruoyi.robot.domain.TLcRobotTask;
import com.ruoyi.robot.service.impl.TLcRobotTaskServiceImpl;
import com.ruoyi.robot.utils.RobotMethodUtil;
import com.ruoyi.system.domain.SysDictType;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.impl.SysDictDataServiceImpl;
import com.ruoyi.system.service.impl.SysDictTypeServiceImpl;
import com.ruoyi.task.domain.TLcTask;
import com.ruoyi.task.service.impl.TLcTaskServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 分配任务规则枚举
 * @author: liurunkai
 * @Date: 2019/12/18 14:12
 */
@Slf4j
@Getter
@Configuration
@NoArgsConstructor
@AllArgsConstructor
public enum AllocatTaskEnum {

    MANUAL(1, "MANUAL", "手动", "1001") {

        TLcTaskServiceImpl tLcTaskService = (TLcTaskServiceImpl) SpringUtils.getBean("com.ruoyi.task.service.impl.TLcTaskServiceImpl");
        AppConfig appConfig = (AppConfig) SpringUtils.getBean("com.ruoyi.config.AppConfig");

        @Override
        public void allocatTask(List<TLcDuncase> manualList, TLcAllocatCaseConfig caseConfig) {
            log.info("执行手动分配任务方法开始.....");
            // 查询当前机构下所有的催收人员
            SysUser sysUser = new SysUser();
            sysUser.setDeptId(Long.valueOf(manualList.get(0).getOrgId()));
            List<SysUser> userList = this.tLcTaskService.searchUserByDept(sysUser);
            //构建任务对象
            List<TLcTask> taskList = manualList.stream()
                    .map(duncase -> createTask(duncase, AllocatTaskEnum.MANUAL.getAllocatCode()))
                    .collect(Collectors.toList());
            // 判断是否自动分配任务
            if (caseConfig.getAutoAllocatCase().equals(IsNoEnum.IS.getCode())) {
                // 如果开启自动分配任务就将任务状态改为分配中，否则默认未分配
                taskList.stream().forEach(task -> task.setTaskStatus(TaskStatusEnum.ALLOCATING.getStatus()));
                manualList.stream().forEach(duncase -> duncase.setCaseStatus(TaskStatusEnum.ALLOCATING.getStatus()));
                if (caseConfig.getAllocatCaseRule().equals(AllocatRuleEnum.DUNCASE_NUM_AVERAGE.getCode())) {
                    // 利用取模法按照数量平均分配任务
                    averageAllocatTaskByNum(manualList, userList);
                } else if (caseConfig.getAllocatCaseRule().equals(AllocatRuleEnum.DUNCASE_MONEY_AVERAGE.getCode())) {
                    // 按照逾期金额平均分配任务
                    averageAllocatTaskByMoney(manualList, userList);
                }
            }
            // 对任务标的数据进行更新即将案件插入到任务表中
            if (taskList != null && taskList.size() > 0) {
                this.tLcTaskService.batchInsertTask(taskList);
            }
            log.info("执行手动分配任务方法完成......");
        }
    },
    OUTSOURCE(2, "OUTSOURCE", "委外", "") {

        TLcTaskServiceImpl tLcTaskService = (TLcTaskServiceImpl) SpringUtils.getBean("com.ruoyi.task.service.impl.TLcTaskServiceImpl");

        @Override
        public void allocatTask(List<TLcDuncase> manualList,TLcAllocatCaseConfig caseConfig) {
            log.info("执行委外分配任务方法开始......");
            //构建任务对象
            List<TLcTask> taskList = manualList.stream()
                    .map(duncase -> createTask(duncase, AllocatTaskEnum.OUTSOURCE.getAllocatCode()))
                    .collect(Collectors.toList());
            // 对任务标的数据进行更新即将案件插入到任务表中
            if (taskList != null && taskList.size() > 0) {
                this.tLcTaskService.batchInsertTask(taskList);
            }
            log.info("执行委外分配任务方法完成......");
        }
    },
    ROBOT(3, "ROBOT", "机器人", "1000") {

        TLcTaskServiceImpl tLcTaskService = (TLcTaskServiceImpl) SpringUtils.getBean("com.ruoyi.task.service.impl.TLcTaskServiceImpl");
        SysDictDataServiceImpl sysDictDataService = (SysDictDataServiceImpl) SpringUtils.getBean("com.ruoyi.system.service.impl.SysDictDataServiceImpl");
        TLcCallStrategyConfigServiceImpl callStrategyConfigService = (TLcCallStrategyConfigServiceImpl) SpringUtils.getBean("com.ruoyi.callConfig.service.impl.TLcCallStrategyConfigServiceImpl");
        TLcCustContactServiceImpl custContactService = (TLcCustContactServiceImpl) SpringUtils.getBean("com.ruoyi.custom.service.impl.TLcCustContactServiceImpl");
        RobotMethodUtil robotMethodUtil = (RobotMethodUtil) SpringUtils.getBean("com.ruoyi.robot.utils.RobotMethodUtil");
        TLcRobotTaskServiceImpl robotTaskService = (TLcRobotTaskServiceImpl) SpringUtils.getBean("com.ruoyi.robot.service.impl.TLcRobotTaskServiceImpl");
        TLcOrgSpeechcraftConfServiceImpl orgSpeechcraftConfService = (TLcOrgSpeechcraftConfServiceImpl) SpringUtils.getBean("com.ruoyi.orgSpeechConf.service.impl.TLcOrgSpeechcraftConfServiceImpl");

        @Override
        public void allocatTask(List<TLcDuncase> manualList,TLcAllocatCaseConfig caseConfig) {
            log.info("进入了机器人分配案件方法......");
            //构建任务对象--本地保存一份机器人任务的数据
            List<TLcTask> taskList = manualList.stream()
                    .map(duncase -> createTask(duncase, AllocatTaskEnum.ROBOT.getAllocatCode()))
                    .collect(Collectors.toList());
            log.info("执行机器人分配任务方法开始......");
            // 获取新案机器人呼叫策略规则
            TLcCallStrategyConfig callStrategyConfig = TLcCallStrategyConfig.builder().businessScene(BusinessSceneEnum.NEW_DUNCASE.getCode()).status(IsNoEnum.IS.getCode()).orgId(String.valueOf(caseConfig.getOrgId())).build();
            TLcCallStrategyConfig tLcCallStrategyConfig = this.callStrategyConfigService.selectTLcCallStrategyConfigList(callStrategyConfig).get(0);
            TLcOrgSpeechcraftConf orgSpeechcraftConf = this.orgSpeechcraftConfService.selectTLcOrgSpeechcraftConfByOrgId(caseConfig.getOrgId());
            // 获取单次推送到机器人的号码数
            String taskCallNum = this.sysDictDataService.selectDictLabel("robot_call_config", "task_call_num");
            if (caseConfig.getRobot().equals("BR")) {
                if (taskList.size() <= Integer.valueOf(taskCallNum)) {
                    Integer robotTaskId = this.robotMethodUtil.createTask(taskList, callStrategyConfig, tLcCallStrategyConfig.getContinueCallDays(), tLcCallStrategyConfig.getCallFrequencyDay(), orgSpeechcraftConf);
                    taskList.stream().forEach(task -> {
                        task.setRobotCallStrategyId(tLcCallStrategyConfig.getId());
                        task.setRobotTaskId(robotTaskId);
                    });
                    this.tLcTaskService.batchInsertTask(taskList);
                } else {
                    Integer taskNums = taskList.size() / Integer.valueOf(taskCallNum);
                    for (int i = 0; i < taskNums; i++) {
                        List subTaskIdList = taskList.subList(i * Integer.valueOf(taskCallNum), (i + 1) * Integer.valueOf(taskCallNum));
                        Integer robotTaskId = this.robotMethodUtil.createTask(subTaskIdList, callStrategyConfig, tLcCallStrategyConfig.getContinueCallDays(), tLcCallStrategyConfig.getCallFrequencyDay(), orgSpeechcraftConf);
                        taskList.stream().forEach(task -> {
                            task.setRobotCallStrategyId(tLcCallStrategyConfig.getId());
                            task.setRobotTaskId(robotTaskId);
                        });
                        this.tLcTaskService.batchInsertTask(taskList);
                    }
                    if (taskList.size() % Integer.valueOf(taskCallNum) != 0) {
                        List subTaskIdList = taskList.subList(Integer.valueOf(taskCallNum) * taskNums, taskList.size());
                        Integer robotTaskId = this.robotMethodUtil.createTask(subTaskIdList, callStrategyConfig, tLcCallStrategyConfig.getContinueCallDays(), tLcCallStrategyConfig.getCallFrequencyDay(), orgSpeechcraftConf);
                        taskList.stream().forEach(task -> {
                            task.setRobotCallStrategyId(tLcCallStrategyConfig.getId());
                            task.setRobotTaskId(robotTaskId);
                        });
                        this.tLcTaskService.batchInsertTask(taskList);
                    }
                }
            }
            // 创建任务
//            taskList.stream()
//                    .forEach(task -> {
//                        task.setRobotCallStrategyId(tLcCallStrategyConfig.getId());
//                        List<TLcCustContact> custContactList = this.custContactService.selectTLcCustContactList(TLcCustContact.builder().caseNo(task.getCaseNo()).relation(ContactRelaEnum.SELE.getCode()).build());
//                        Integer robotTaskId = null;
//                        if (caseConfig.getRobot().equals("BR")) {
//                            robotTaskId = robotMethodUtil.createTask(task, tLcCallStrategyConfig, custContactList, orgSpeechcraftConf.getConcurrentValue());
//                            // 创建机器人任务并添加到机器人任务列表
//                            String speechCraftName = sysDictDataService.selectDictLabel("robot_speech_craft", String.valueOf(tLcCallStrategyConfig.getSpeechcraftId()));
//                            TLcRobotTask tLcRobotTask = createRobotTask(task, robotTaskId, speechCraftName, "BR");
//                            this.robotTaskService.insertTLcRobotTask(tLcRobotTask);
//                            // 开启机器人任务
//                            robotMethodUtil.startTask(robotTaskId);
//                        }
//                        task.setRobotTaskId(robotTaskId);
//                        // 对任务标的数据进行更新即将案件插入到任务表中
//                        this.tLcTaskService.insertTLcTask(task);
//                    });
            log.info("执行机器人分配任务方法完成......");
        }
    },
    LAW(4, "LAW", "法催", "") {

        TLcTaskServiceImpl tLcTaskService = (TLcTaskServiceImpl) SpringUtils.getBean("com.ruoyi.task.service.impl.TLcTaskServiceImpl");

        @Override
        public void allocatTask(List<TLcDuncase> manualList,TLcAllocatCaseConfig caseConfig) {
            log.info("执行法催分配任务方法开始......");
            //构建任务对象
            List<TLcTask> taskList = manualList.stream()
                    .map(duncase -> createTask(duncase, AllocatTaskEnum.LAW.getAllocatCode()))
                    .collect(Collectors.toList());
            // 对任务标的数据进行更新即将案件插入到任务表中
            if (taskList != null && taskList.size() > 0) {
                this.tLcTaskService.batchInsertTask(taskList);
            }
            log.info("执行法催分配任务方法完成......");
        }
    };

    /**
     * 创建机器人任务
     *
     * @param tLcTask
     * @param robotTaskId
     * @return
     */
    private static TLcRobotTask createRobotTask(TLcTask tLcTask, Integer robotTaskId, String speechCraftName, String robotType) {
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

    /**
     * 利用取模法按照数量平均分配任务
     *
     * @param manualList
     * @param userList
     */
    public static void averageAllocatTaskByNum(List<TLcDuncase> manualList, List<SysUser> userList) {
        for (int i = 0; i < manualList.size(); i++) {
            //为了保证案件从第一个人开始分配，需要对i % userList.size()做处理，因为i % userList.size() = 0的情况
            //Integer userIndex = i % userList.size() == 0 ? i % userList.size() : i % userList.size();
            Integer userIndex = i % userList.size();
            SysUser sysUser = userList.get(userIndex);
            manualList.get(i).setOwnerId(sysUser.getUserId());
            manualList.get(i).setOwnerName(sysUser.getUserName());
        }
    }

    /**
     * 按照金额进行平均分配任务
     *
     * @param duncaseList
     * @param userList
     * @return
     */
    public static List<TLcDuncase> averageAllocatTaskByMoney(List<TLcDuncase> duncaseList, List<SysUser> userList) {
        // 先对案件根据金额进行排序
        List<TLcDuncase> sortDuncaseList = duncaseList.stream().sorted(Comparator.comparing(TLcDuncase::getTotalDebtAmountRmb)).collect(Collectors.toList());
        int uid = 0;
        for (int i = 1; i <= sortDuncaseList.size(); i++) {
            if ((i + (2 * userList.size() - 1)) % (2 * userList.size()) > userList.size() - 1) {
                uid = (2 * userList.size() - 1) - (i + (2 * userList.size() - 1)) % (2 * userList.size());
                sortDuncaseList.get(i - 1).setOwnerId(userList.get(uid).getUserId());
                sortDuncaseList.get(i - 1).setOwnerName(userList.get(uid).getUserName());
            } else {
                uid = (i + (userList.size() * 2 - 1)) % (2 * userList.size());
                sortDuncaseList.get(i - 1).setOwnerId(userList.get(uid).getUserId());
                sortDuncaseList.get(i - 1).setOwnerName(userList.get(uid).getUserName());
            }
        }
        return sortDuncaseList;
    }

    private static TLcTask createTask(TLcDuncase duncase, Integer allocatCode) {
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
                .allotType(allocatCode)
                .taskType(TaskTypeEnum.FIRST_CREATE.getCode())
                .modifyBy(duncase.getModifyBy())
                .transferType(duncase.getTransferType()) //手别
                .enterCollDate(duncase.getEnterCollDate()) // 入催日
                .closeCaseYhje(duncase.getCloseCaseYhje()) // 结案应还金额
                .hitRule(duncase.getHitRule())
                .hitRuleDesc(duncase.getHitRuleDesc())
                .distributionStrategy(duncase.getDistributionStrategy())
                .importBatchNo(duncase.getImportBatchNo())
                .phone(duncase.getCustomPhone())
                .build();
        task.setCreateBy(duncase.getCreateBy());
        return task;
    }

    /**
     * 任务分配编码
     */
    private Integer allocatCode;
    /**
     * 任务分配方式
     */
    private String allocatType;
    /**
     * 任务分配方式描述
     */
    private String description;
    /**
     * 规则引擎任务分配编码
     */
    private String ruleEnginAlloCode;

    /**
     * 根据任务分配编码获取任务分配方式
     *
     * @param code
     * @return
     */
    public static String getAllocatType(Integer code) {
        AllocatTaskEnum allocatTaskEnum = Arrays.stream(AllocatTaskEnum.values())
                .filter(allocatTask -> allocatTask.getAllocatCode().equals(code))
                .findAny()
                .orElseThrow(() -> new RuntimeException(String.format("code is not exist,code is %s", code)));
        return allocatTaskEnum.getAllocatType();
    }

    /**
     * 根据规则引擎任务分配编码获取任务分配催收系统任务编码
     *
     * @param code
     * @return
     */
    public static Integer getAllocatTypeByRule(String code) {
        AllocatTaskEnum allocatTaskEnum = Arrays.stream(AllocatTaskEnum.values())
                .filter(allocatTask -> allocatTask.getRuleEnginAlloCode().equals(code))
                .findAny()
                .orElse(null);
//                .orElseThrow(() -> new RuntimeException(String.format("code is not exist,code is %s", code)));
        if (allocatTaskEnum == null) {
            return null;
        }
        return allocatTaskEnum.getAllocatCode();
    }

    /**
     * 定义一个抽象方法实现任务分配
     *
     */
    public abstract void allocatTask(List<TLcDuncase> manualList, TLcAllocatCaseConfig caseConfig);

}
