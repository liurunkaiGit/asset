package com.ruoyi.quartz.task;

import com.alibaba.fastjson.JSON;
import com.ruoyi.robot.domain.RobotTask;
import com.ruoyi.robot.domain.TLcRobotTaskPandect;
import com.ruoyi.robot.enums.LocalRobotTaskStatus;
import com.ruoyi.robot.service.ITLcRobotTaskPandectService;
import com.ruoyi.robot.utils.RobotMethodUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * @Description: 机器人任务定时
 * @author: liurunkai
 * @Date: 2020/7/11 9:42
 */
@Slf4j
@Component("robotTaskSchedule")
public class RobotTaskSchedule {

    @Value("${isEnableTimer}")
    private Boolean isEnableTimer;
    @Autowired
    private ITLcRobotTaskPandectService robotTaskPandectService;
    @Autowired
    private RobotMethodUtil robotMethodUtil;

    public void updateRobotPandect() {
        if (isEnableTimer) {
            log.info("开始执行机器人呼叫统计定时任务，时间：{}", LocalDateTime.now(ZoneId.systemDefault()));
            // 查询所有正在外呼中的机器人任务
            List<TLcRobotTaskPandect> robotTaskPandectListt = this.robotTaskPandectService.selectRobotTaskPandectByRobotTaskStatus(LocalRobotTaskStatus.RUNNING.getCode());
            log.info("正在外呼中的机器人任务数量：{}", robotTaskPandectListt.size());
            robotTaskPandectListt.stream().forEach(robotTaskPandect -> {
                // 根据任务id获取任务详情
                RobotTask taskDetail = this.robotMethodUtil.getTaskDetail(robotTaskPandect.getRobotTaskId());
                log.info("任务id：{}的任务详情：{}", robotTaskPandect.getRobotTaskId(), JSON.toJSONString(taskDetail));
                // 更新机器人任务总览表
                updateRobotTaskPandect(robotTaskPandect, taskDetail);
                log.info("修改任务总览表成功...任务id{}", robotTaskPandect.getRobotTaskId());
            });
        } else {
            log.info("定时历史数据迁移任务未开启");
        }
    }

    /**
     * 修改机器人任务总览表
     *
     * @param robotTaskPandect
     * @param taskDetail
     */
    private void updateRobotTaskPandect(TLcRobotTaskPandect robotTaskPandect, RobotTask taskDetail) {
        robotTaskPandect.setPhoneNum(taskDetail.getPhoneNum());
        robotTaskPandect.setCallDoneCount(taskDetail.getDoneCount());
        robotTaskPandect.setCallCalledCount(taskDetail.getCalledCount());
        robotTaskPandect.setCallRejectedCount(taskDetail.getRejectedCount());
        robotTaskPandect.setCallUnavailableCount(taskDetail.getUnavailableCount());
        robotTaskPandect.setCallFromUnavailableCount(taskDetail.getFromUnavailableCount());
        robotTaskPandect.setCallBusyCount(taskDetail.getBusyCount());
        robotTaskPandect.setCallMissCount(taskDetail.getMissCount());
        robotTaskPandect.setCallBlankCount(taskDetail.getBlankCount());
        robotTaskPandect.setCallClosedCount(taskDetail.getClosedCount());
        robotTaskPandect.setCallDownCount(taskDetail.getDownCount());
        robotTaskPandect.setCallBlackCount(taskDetail.getBlacklistCount());
        robotTaskPandect.setCallFailCount(taskDetail.getFromUnavailableCount());
        robotTaskPandect.setCallLossCount(taskDetail.getLostCount());
        robotTaskPandect.setCallOverdueCount(taskDetail.getOverdueCount());
        this.robotTaskPandectService.updateTLcRobotTaskPandect(robotTaskPandect);
    }
}
