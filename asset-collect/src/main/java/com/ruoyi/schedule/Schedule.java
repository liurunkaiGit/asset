package com.ruoyi.schedule;

import com.ruoyi.assetspackage.domain.OrgPackage;
import com.ruoyi.assetspackage.service.IOrgPackageService;
import com.ruoyi.callConfig.domain.TLcCallStrategyConfig;
import com.ruoyi.callConfig.service.ITLcCallStrategyConfigService;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.config.AppConfig;
import com.ruoyi.custom.domain.TLcCustinfo;
import com.ruoyi.custom.service.ITLcCustinfoService;
import com.ruoyi.duncase.domain.TLcDuncaseActionRecord;
import com.ruoyi.duncase.service.ITLcDuncaseActionRecordService;
import com.ruoyi.enums.BusinessSceneEnum;
import com.ruoyi.enums.IsReCallEnum;
import com.ruoyi.enums.TaskStatusEnum;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.orgSpeechConf.domain.TLcOrgSpeechcraftConf;
import com.ruoyi.orgSpeechConf.service.ITLcOrgSpeechcraftConfService;
import com.ruoyi.report.domain.TLcReportCaseContact;
import com.ruoyi.report.domain.TLcReportDayProcess;
import com.ruoyi.report.domain.TLcReportRecovery;
import com.ruoyi.report.service.ITLcReportCaseContactService;
import com.ruoyi.report.service.ITLcReportDayProcessService;
import com.ruoyi.report.service.ITLcReportRecoveryService;
import com.ruoyi.robot.domain.TLcRobotTask;
import com.ruoyi.robot.enums.LocalRobotTaskStatus;
import com.ruoyi.robot.service.ITLcRobotTaskService;
import com.ruoyi.robot.utils.RobotMethodUtil;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.task.domain.TLcTask;
import com.ruoyi.task.service.ITLcTaskService;
import com.ruoyi.utils.LocalCacheKeyUtils;
import com.ruoyi.utils.LocalCacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: 定时器
 * @author: liurunkai
 * @Date: 2020/2/25 15:59
 */
@Slf4j
@Component
@Configuration
@EnableScheduling
public class Schedule {

    @Autowired
    private AppConfig appConfig;
    @Autowired
    private ITLcTaskService tLcTaskService;
    @Autowired
    private IOrgPackageService orgPackageService;
    @Autowired
    private ITLcCallStrategyConfigService callStrategyConfigService;
    @Autowired
    private RobotMethodUtil robotMethodUtil;
    @Autowired
    private LocalCacheUtil localCacheUtil;
    @Autowired
    private ITLcDuncaseActionRecordService duncaseActionRecordService;
    @Autowired
    private ITLcReportRecoveryService reportRecoveryService;
    @Autowired
    private ITLcReportDayProcessService reportDayProcessService;
    @Autowired
    private ITLcReportCaseContactService caseContactService;
    @Autowired
    private ITLcRobotTaskService tLcRobotTaskService;
    @Autowired
    private ITLcCustinfoService tLcCustinfoService;
    @Autowired
    private ISysDictDataService sysDictDataService;
    @Autowired
    private ITLcOrgSpeechcraftConfService orgSpeechcraftConfService;

    /**
     * 每天晚上0点定时定时创建并启动机器人任务
     */
    @Scheduled(cron = "${cron.startRobotTask}")
    private void cleanPhonCurDayCallNums() {
        if (!appConfig.getStartRobotTask()) {
            log.info("定时创建并启动机器人任务未开启");
            return;
        }
        // 获取前一天的0点和24点
        // 查询前一天任务状态为已完成并且是第二天呼叫的任务
        TLcRobotTask tLcRobotTask = new TLcRobotTask();
        tLcRobotTask.setRobotTaskStatus(LocalRobotTaskStatus.FINISHED.getCode());
        tLcRobotTask.setIsRecall(IsReCallEnum.NEXT_DAY_CALL.getCode());
        tLcRobotTask.setStartCreateTime(DateUtils.getStartOfDay(new Date()));
        tLcRobotTask.setEndCreateTime(DateUtils.getEndOfDay(new Date()));
        List<TLcRobotTask> robotTaskList = this.tLcRobotTaskService.selectTLcRobotTaskList(tLcRobotTask);
        log.info("开始执行定时启动机器人任务，需要启动的任务是{}", localCacheUtil.getPhoneToStart(LocalCacheKeyUtils.taskWaitStartList()));
//        List<TLcRobotCallRecordMeteData> robotTaskList = localCacheUtil.getPhoneToStart(LocalCacheKeyUtils.taskWaitStartList());
        ArrayList<TLcTask> tlcTaskList = new ArrayList<>();
        robotTaskList.stream()
                .forEach(robotTask -> {
                    // 当天的呼叫次数清0
//                    localCacheUtil.setPhoneCurDayCallNums(LocalCacheKeyUtils.phoneCurDayCallNumsKey(sceneInstance.getCustomerTelephone()), 0);
                    // 连续呼叫天数+1
//                    Integer continueCallDays = localCacheUtil.getPhoneContinueCallDays(LocalCacheKeyUtils.phoneContinueCallDaysKey(sceneInstance.getCustomerTelephone()));
//                    localCacheUtil.setPhoneContinueCallDays(LocalCacheKeyUtils.phoneContinueCallDaysKey(sceneInstance.getCustomerTelephone()), ++continueCallDays);
                    TLcTask tLcTask = this.tLcTaskService.selectTaskListByRobotTaskIdAndPhone(String.valueOf(robotTask.getTaskId()),robotTask.getPhone()).get(0);
                    TLcDuncaseActionRecord tLcDuncaseActionRecord = this.duncaseActionRecordService.selectTLcDuncaseActionRecordByTaskId(tLcTask.getId());
                    if (!TaskStatusEnum.CLOSE.getStatus().equals(tLcTask.getTaskStatus()) && !tLcDuncaseActionRecord.getActionCode().equals("bjhk")) {
                        tlcTaskList.add(tLcTask);
                    }
                });
        // 创建新的任务并启动
        Map<String, List<TLcTask>> taskListMap = tlcTaskList.stream().collect(Collectors.groupingBy(TLcTask::getOrgId));
        // 获取单次推送到机器人的号码数
        String taskCallNum = this.sysDictDataService.selectDictLabel("robot_call_config", "task_call_num");
        for (Map.Entry<String, List<TLcTask>> map : taskListMap.entrySet()) {
            TLcCallStrategyConfig tLcCallStrategyConfig = this.callStrategyConfigService.selectCallStrategyConfigByOrgIdAndBusinessScene(map.getKey(), BusinessSceneEnum.NEW_DUNCASE.getCode());
            // 获取ai坐席数
            TLcOrgSpeechcraftConf orgSpeechcraftConf = this.orgSpeechcraftConfService.selectTLcOrgSpeechcraftConfByOrgId(Long.valueOf(tLcCallStrategyConfig.getOrgId()));
            TLcRobotTask tLcRobotTask1 = this.tLcRobotTaskService.selectRobotTaskByRobotTaskIdAndTaskId(map.getValue().get(0).getRobotTaskId(), map.getValue().get(0).getId());
            if (map.getValue().size() <= Integer.valueOf(taskCallNum)) {
                Integer robotTaskId = this.robotMethodUtil.createTask(map.getValue(), tLcCallStrategyConfig, tLcRobotTask1.getContinueDays() + 1,1, orgSpeechcraftConf, DateUtils.parseDateToStr(DateUtils.YYYYMMDDHHMMSS,new Date()));
            } else {
                Integer taskNums = map.getValue().size() / Integer.valueOf(taskCallNum);
                for (int i = 0; i < taskNums; i++) {
                    List subTaskIdList = map.getValue().subList(i*Integer.valueOf(taskCallNum), (i+1)*Integer.valueOf(taskCallNum));
                    Integer robotTaskId = this.robotMethodUtil.createTask(subTaskIdList, tLcCallStrategyConfig, tLcRobotTask1.getContinueDays() + 1,1, orgSpeechcraftConf, DateUtils.parseDateToStr(DateUtils.YYYYMMDDHHMMSS,new Date()));
                }
                if (map.getValue().size() % Integer.valueOf(taskCallNum) != 0) {
                    List subTaskIdList = map.getValue().subList(Integer.valueOf(taskCallNum) * taskNums,map.getValue().size());
                    Integer robotTaskId = this.robotMethodUtil.createTask(subTaskIdList, tLcCallStrategyConfig, tLcRobotTask1.getContinueDays() + 1,1, orgSpeechcraftConf, DateUtils.parseDateToStr(DateUtils.YYYYMMDDHHMMSS,new Date()));
                }
            }
        }
        localCacheUtil.addPhoneToStart(LocalCacheKeyUtils.taskWaitStartList(), new ArrayList<>());
        log.info("完成执行定时启动机器人任务...");
    }

    /**
     * 创建机器人任务
     *
     * @param tLcTask
     * @param robotTaskId
     * @return
     */
    private TLcRobotTask createRobotTask(TLcTask tLcTask, Integer robotTaskId, String speechCraftName) {
        TLcCustinfo custinfo = this.tLcCustinfoService.findCustByCaseNo(tLcTask.getCaseNo(),tLcTask.getOrgId(),tLcTask.getImportBatchNo());
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
                .setPhone(custinfo.getPhone())
                .setCreateBy(String.valueOf(ShiroUtils.getUserId()));
        return tLcRobotTask;
    }

    /**
     * 每天晚上0点定时生成报表任务
     */
    @Scheduled(cron = "${cron.createReport}")
    private void createReport() {
        if (!appConfig.getCreateReport()) {
            log.info("定时生成报表任务任务未开启");
            return;
        }
        log.info("开始定时生成报表任务任务");
        // 查找所有的委托方
        List<OrgPackage> orgPackageList = this.orgPackageService.selectOrgPackageList(new OrgPackage());
        // 查询回收率报表对应的数据
        Map<String, Object> recoveryParam = new HashMap<>();
        recoveryParam.put("startDate", DateUtils.getFirstDay());
        recoveryParam.put("day", 0);
        // 生成每个机构的回收率报表数据和合计数据
        if (orgPackageList != null && orgPackageList.size() > 0) {
            orgPackageList.stream().forEach(orgPackage -> {
                recoveryParam.put("orgId",orgPackage.getDeptId());
                List<TLcReportRecovery> reportRecoveryList = this.reportRecoveryService.selectRecoveryByPayment(recoveryParam);
                reportRecoveryList.stream().forEach(reportRecovery -> this.reportRecoveryService.insertTLcReportRecovery(reportRecovery));
                log.info("{}生成回收率报表成功,{}",orgPackage.getOrgName(),DateUtils.getNowDate());
            });
        }
        // 查询可联案件渗透率报表数据
        Map<String, Object> contactParam = new HashMap<>();
        contactParam.put("startDate", DateUtils.getFirstDay());
        contactParam.put("day", 0);
        List<TLcReportCaseContact> caseContactList = this.caseContactService.selectCaseContactList(contactParam);
        caseContactList.stream().forEach(caseContact -> this.caseContactService.insertTLcReportCaseContact(caseContact));
        log.info("生成案件可联率报表成功,{}",DateUtils.getNowDate());
        // 查询每日过程指标数据
        Map<String, Object> processParam = new HashMap<>();
        processParam.put("startDate", DateUtils.getStartOfDay(new Date()));
        processParam.put("endDate", DateUtils.getEndOfDay(new Date()));
        processParam.put("day", 0);
        if (orgPackageList != null && orgPackageList.size() > 0) {
            orgPackageList.stream().forEach(orgPackage -> {
                processParam.put("orgId",orgPackage.getDeptId());
                List<TLcReportDayProcess> reportDayProcessList = this.reportDayProcessService.selectDayProcess(processParam);
                reportDayProcessList.stream().forEach(reportRecovery -> this.reportDayProcessService.insertTLcReportDayProcess(reportRecovery));
                log.info("{}生成每日过程指标报表成功,{}",orgPackage.getOrgName(),DateUtils.getNowDate());
            });
        }
        log.info("完成定时生成报表任务任务...");
    }

}
