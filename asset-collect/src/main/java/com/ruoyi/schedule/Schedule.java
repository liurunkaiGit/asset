package com.ruoyi.schedule;

import com.ruoyi.callConfig.domain.TLcCallStrategyConfig;
import com.ruoyi.callConfig.service.ITLcCallStrategyConfigService;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.config.AppConfig;
import com.ruoyi.duncase.domain.TLcDuncaseActionRecord;
import com.ruoyi.duncase.service.ITLcDuncaseActionRecordService;
import com.ruoyi.enums.BusinessSceneEnum;
import com.ruoyi.enums.IsReCallEnum;
import com.ruoyi.enums.TaskStatusEnum;
import com.ruoyi.orgSpeechConf.domain.TLcOrgSpeechcraftConf;
import com.ruoyi.orgSpeechConf.service.ITLcOrgSpeechcraftConfService;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
    private ITLcCallStrategyConfigService callStrategyConfigService;
    @Autowired
    private RobotMethodUtil robotMethodUtil;
    @Autowired
    private LocalCacheUtil localCacheUtil;
    @Autowired
    private ITLcDuncaseActionRecordService duncaseActionRecordService;
    @Autowired
    private ITLcRobotTaskService tLcRobotTaskService;
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

}
