package com.ruoyi.duncase.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.duncase.domain.TLcDuncaseActionRecord;
import com.ruoyi.duncase.mapper.TLcDuncaseActionRecordMapper;
import com.ruoyi.duncase.service.ITLcDuncaseActionRecordService;
import com.ruoyi.enums.CollActionCodeEnum;
import com.ruoyi.enums.IsNoEnum;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.robot.domain.TLcRobotBlack;
import com.ruoyi.robot.enums.RobotBlackReasonEnum;
import com.ruoyi.robot.service.ITLcRobotBlackService;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.task.domain.TLcTask;
import com.ruoyi.task.mapper.TLcTaskMapper;
import com.ruoyi.task.service.ITLcTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 案件行动码记录Service业务层处理
 *
 * @author liurunkai
 * @date 2020-01-04
 */
@Slf4j
@Service
public class TLcDuncaseActionRecordServiceImpl implements ITLcDuncaseActionRecordService {
    @Autowired
    private TLcDuncaseActionRecordMapper tLcDuncaseActionRecordMapper;
    @Autowired
    private ITLcRobotBlackService robotBlackService;
    @Autowired
    private ITLcTaskService tLcTaskService;
    @Autowired
    private ISysDictDataService sysDictDataService;
    @Autowired
    private TLcTaskMapper tLcTaskMapper;

    /**
     * 查询案件行动码记录
     *
     * @param id 案件行动码记录ID
     * @return 案件行动码记录
     */
    @Override
    public TLcDuncaseActionRecord selectTLcDuncaseActionRecordById(Long id) {
        return tLcDuncaseActionRecordMapper.selectTLcDuncaseActionRecordById(id);
    }

    /**
     * 查询案件行动码记录列表
     *
     * @param tLcDuncaseActionRecord 案件行动码记录
     * @return 案件行动码记录
     */
    @Override
    public List<TLcDuncaseActionRecord> selectTLcDuncaseActionRecordList(TLcDuncaseActionRecord tLcDuncaseActionRecord) {
        return tLcDuncaseActionRecordMapper.selectTLcDuncaseActionRecordList(tLcDuncaseActionRecord);
    }

    /**
     * 新增案件行动码记录
     *
     * @param tLcDuncaseActionRecord 案件行动码记录
     * @return 结果
     */
    @Override
    public int insertTLcDuncaseActionRecord(TLcDuncaseActionRecord tLcDuncaseActionRecord, String orgId, String importBatchNo) {
        log.info("添加行动码开始");
        tLcDuncaseActionRecord.setCreateBy(ShiroUtils.getUserId().toString());
        tLcDuncaseActionRecord.setModifyBy(ShiroUtils.getUserId());
        tLcDuncaseActionRecord.setValidateStatus(IsNoEnum.IS.getCode());
        // 修改任务表和案件表中的最近跟进时间及行动码
        String actionCodeValue = this.sysDictDataService.selectDictLabel("action_code", tLcDuncaseActionRecord.getActionCode());
        if (CollActionCodeEnum.TS.getCode().equals(tLcDuncaseActionRecord.getActionCode())) {
            this.tLcTaskService.stopCollApply(tLcDuncaseActionRecord.getTaskId());
        }
        TLcTask tLcTask = this.tLcTaskService.selectTaskByCaseNo(tLcDuncaseActionRecord.getCaseNo(),orgId,importBatchNo);
        tLcTask.setRecentlyFollowUpDate(new Date());
        tLcTask.setActionCode(tLcDuncaseActionRecord.getActionCode());
        tLcTask.setActionCodeValue(actionCodeValue);
        this.tLcTaskService.updateTLcTask(tLcTask);
        // 行动码如果是ALPA(已还款)和TS(投诉)则添加到机器人黑名单管理
        if (CollActionCodeEnum.TS.getCode().equals(tLcDuncaseActionRecord.getActionCode())) {
            this.robotBlackService.insertTLcRobotBlack(tLcTask, RobotBlackReasonEnum.COMPLAINT.getReason(), tLcTask.getPhone());
        }
        if (CollActionCodeEnum.ALPA.getCode().equals(tLcDuncaseActionRecord.getActionCode())) {
            this.robotBlackService.insertTLcRobotBlack(tLcTask, RobotBlackReasonEnum.PAYED.getReason(), tLcTask.getPhone());
        }
        int res = tLcDuncaseActionRecordMapper.insertTLcDuncaseActionRecord(tLcDuncaseActionRecord);
        log.info("添加行动码结束");
        return res;
    }

    /**
     * 修改案件行动码记录
     *
     * @param tLcDuncaseActionRecord 案件行动码记录
     * @return 结果
     */
    @Override
    public int updateTLcDuncaseActionRecord(TLcDuncaseActionRecord tLcDuncaseActionRecord) {
        return tLcDuncaseActionRecordMapper.updateTLcDuncaseActionRecord(tLcDuncaseActionRecord);
    }

    /**
     * 删除案件行动码记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcDuncaseActionRecordByIds(String ids) {
        return tLcDuncaseActionRecordMapper.deleteTLcDuncaseActionRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除案件行动码记录信息
     *
     * @param id 案件行动码记录ID
     * @return 结果
     */
    @Override
    public int deleteTLcDuncaseActionRecordById(Long id) {
        return tLcDuncaseActionRecordMapper.deleteTLcDuncaseActionRecordById(id);
    }

    @Override
    public AjaxResult batchAddActionCode(String taskIds, String actionCode) {
        List<TLcTask> taskList = Arrays.stream(taskIds.split(","))
                .map(taskId -> {
                    TLcTask tLcTask = this.tLcTaskService.selectTLcTaskById(Long.valueOf(taskId));
                    TLcDuncaseActionRecord actionRecord = TLcDuncaseActionRecord.builder().caseNo(tLcTask.getCaseNo()).taskId(taskId).actionCode(actionCode).validateStatus(IsNoEnum.IS.getCode()).build();
                    actionRecord.setCreateBy(ShiroUtils.getUserId().toString());
                    // 修改任务表和案件表中的最近跟进时间及行动码
                    String actionCodeValue = this.sysDictDataService.selectDictLabel("action_code", actionCode);
                    if ("QXBJ".equals(actionCode)) {
                        actionCodeValue = "取消标记";
                        // 取消标记，从机器人黑名单移除
                        TLcRobotBlack robotBlack = TLcRobotBlack.builder().caseNo(tLcTask.getCaseNo()).importBatchNo(tLcTask.getImportBatchNo()).phone(tLcTask.getPhone()).reason(RobotBlackReasonEnum.PAYED.getReason()).build();
                        this.robotBlackService.deleteobotBlackByCaseReason(robotBlack);
                    } else if ("ALPA".equals(actionCode)) {
                        // 标记还款，添加到机器人黑名单
                        this.robotBlackService.insertTLcRobotBlack(tLcTask, RobotBlackReasonEnum.PAYED.getReason(), tLcTask.getPhone());
                    }
                    tLcTask.setRecentlyFollowUpDate(new Date());
                    tLcTask.setActionCode(actionCode);
                    tLcTask.setActionCodeValue(actionCodeValue);
                    tLcDuncaseActionRecordMapper.insertTLcDuncaseActionRecord(actionRecord);
                    return tLcTask;
                }).collect(Collectors.toList());
        this.tLcTaskMapper.batchUpdateTask(taskList);
        return AjaxResult.success();
    }

    @Override
    public TLcDuncaseActionRecord selectTLcDuncaseActionRecordByTaskId(Long taskId) {
        return this.tLcDuncaseActionRecordMapper.selectTLcDuncaseActionRecordByTaskId(taskId);
    }
}
