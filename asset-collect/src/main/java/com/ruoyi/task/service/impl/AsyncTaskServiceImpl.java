package com.ruoyi.task.service.impl;

import com.ruoyi.enums.CollActionCodeEnum;
import com.ruoyi.robot.enums.RobotBlackReasonEnum;
import com.ruoyi.robot.service.ITLcRobotBlackService;
import com.ruoyi.task.domain.TLcCallRecord;
import com.ruoyi.task.domain.TLcTask;
import com.ruoyi.task.mapper.TLcTaskMapper;
import com.ruoyi.task.service.AsyncTaskService;
import com.ruoyi.task.service.ITLcTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/7/27 10:44
 */
@Slf4j
@Service
public class AsyncTaskServiceImpl implements AsyncTaskService {

    @Autowired
    private TLcTaskMapper tLcTaskMapper;

    @Autowired
    private ITLcTaskService tLcTaskService;

    @Autowired
    private ITLcRobotBlackService robotBlackService;

    /**
     * 更新任务表数据--新增或者修改电催记录时
     *
     * @param tLcCallRecord
     * @param importBatchNo
     */
    @Async
    @Override
    public void updateTask(TLcCallRecord tLcCallRecord, String importBatchNo) {
        /*TLcTask tLcTask = TLcTask.builder()
                .caseNo(tLcCallRecord.getCaseNo())
                .importBatchNo(importBatchNo)
                .orgId(tLcCallRecord.getOrgId())
                .callSign(tLcCallRecord.getCallSign())
                .callSignValue(tLcCallRecord.getCallResult())
                .recentlyFollowUpDate(new Date())
                .actionCode(tLcCallRecord.getActionCode())
                .build();
        this.tLcTaskMapper.updateTaskByCaseNoAndImportBatchNoAndOrgId(tLcTask);*/

        if (CollActionCodeEnum.TS.getMessage().equals(tLcCallRecord.getActionCode())) {
            this.tLcTaskService.stopCollApply(tLcCallRecord.getTaskId());
        }
        TLcTask tLcTask = this.tLcTaskService.selectTaskByCaseNo(tLcCallRecord.getCaseNo(),tLcCallRecord.getOrgId(),importBatchNo);
        tLcTask.setRecentlyFollowUpDate(new Date());
        tLcTask.setActionCode(tLcCallRecord.getActionCode());
//        tLcTask.setActionCodeValue(tLcCallRecord.getActionCode());
        tLcTask.setCallSign(tLcCallRecord.getCallSign());
        tLcTask.setCallSignValue(tLcCallRecord.getCallResult());
        tLcTask.setModifyTime(new Date());
        this.tLcTaskService.updateTLcTask(tLcTask);
        // 行动码如果是ALPA(已还款)和TS(投诉)则添加到机器人黑名单管理
        if (CollActionCodeEnum.TS.getMessage().equals(tLcCallRecord.getActionCode())) {
            this.robotBlackService.insertTLcRobotBlack(tLcTask, RobotBlackReasonEnum.COMPLAINT.getReason(), tLcTask.getPhone());
        }
        if (CollActionCodeEnum.ALPA.getCode().equals(tLcCallRecord.getActionCode())) {
            this.robotBlackService.insertTLcRobotBlack(tLcTask, RobotBlackReasonEnum.PAYED.getReason(), tLcTask.getPhone());
        }
    }
}
