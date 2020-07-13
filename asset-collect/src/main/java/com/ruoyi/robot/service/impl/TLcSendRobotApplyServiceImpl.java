package com.ruoyi.robot.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.duncase.service.ITLcDuncaseAssignService;
import com.ruoyi.enums.SendRobotApplyTaskStatusEnum;
import com.ruoyi.enums.TaskTypeEnum;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.robot.domain.TLcSendRobotApply;
import com.ruoyi.robot.mapper.TLcSendRobotApplyMapper;
import com.ruoyi.robot.service.ITLcSendRobotApplyService;
import com.ruoyi.task.domain.TLcTask;
import com.ruoyi.task.service.ITLcTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 推送机器人申请Service业务层处理
 *
 * @author liurunkai
 * @date 2020-07-13
 */
@Service
public class TLcSendRobotApplyServiceImpl implements ITLcSendRobotApplyService {

    @Autowired
    private TLcSendRobotApplyMapper tLcSendRobotApplyMapper;
    @Autowired
    private ITLcTaskService taskService;
    @Autowired
    private ITLcDuncaseAssignService duncaseAssignService;

    /**
     * 查询推送机器人申请
     *
     * @param id 推送机器人申请ID
     * @return 推送机器人申请
     */
    @Override
    public TLcSendRobotApply selectTLcSendRobotApplyById(Integer id) {
        return tLcSendRobotApplyMapper.selectTLcSendRobotApplyById(id);
    }

    /**
     * 查询推送机器人申请列表
     *
     * @param tLcSendRobotApply 推送机器人申请
     * @return 推送机器人申请
     */
    @Override
    public List<TLcSendRobotApply> selectTLcSendRobotApplyList(TLcSendRobotApply tLcSendRobotApply) {
        return tLcSendRobotApplyMapper.selectTLcSendRobotApplyList(tLcSendRobotApply);
    }

    /**
     * 新增推送机器人申请
     *
     * @param tLcSendRobotApply 推送机器人申请
     * @return 结果
     */
    @Override
    public int insertTLcSendRobotApply(TLcSendRobotApply tLcSendRobotApply) {
        return tLcSendRobotApplyMapper.insertTLcSendRobotApply(tLcSendRobotApply);
    }

    /**
     * 修改推送机器人申请
     *
     * @param tLcSendRobotApply 推送机器人申请
     * @return 结果
     */
    @Override
    public int updateTLcSendRobotApply(TLcSendRobotApply tLcSendRobotApply) {
        tLcSendRobotApply.setUpdateTime(DateUtils.getNowDate());
        return tLcSendRobotApplyMapper.updateTLcSendRobotApply(tLcSendRobotApply);
    }

    /**
     * 删除推送机器人申请对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLcSendRobotApplyByIds(String ids) {
        return tLcSendRobotApplyMapper.deleteTLcSendRobotApplyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除推送机器人申请信息
     *
     * @param id 推送机器人申请ID
     * @return 结果
     */
    @Override
    public int deleteTLcSendRobotApplyById(Integer id) {
        return tLcSendRobotApplyMapper.deleteTLcSendRobotApplyById(id);
    }

    @Override
    @Transactional
    public AjaxResult approveSendRobot(String sendRobotBatchNos, Integer status) {
        Arrays.stream(sendRobotBatchNos.split(",")).forEach(sendRobotBatchNo -> {
            // 修改推送机器人审批状态
            TLcSendRobotApply sendRobotApply = new TLcSendRobotApply();
            sendRobotApply.setSendRobotBatchNo(sendRobotBatchNo).setTaskStatus(status).setUpdateBy(ShiroUtils.getSysUser().getUserId().toString());
            this.tLcSendRobotApplyMapper.updateSendRobotStatusBySendRobotBatchNo(sendRobotApply);
            // 修改任务表任务类型
            Integer taskType = null;
            if (SendRobotApplyTaskStatusEnum.APPROVAL_REJECT.getCode().equals(status)) {
                taskType = TaskTypeEnum.SEND_ROBOT_APPLY_REJECT.getCode();
            } else if (SendRobotApplyTaskStatusEnum.APPROVAL_PASS.getCode().equals(status)) {
                taskType = TaskTypeEnum.SEND_ROBOT_APPLY_ALLOW.getCode();
            }
            TLcTask tLcTask = new TLcTask();
            tLcTask.setSendRobotBatchNo(sendRobotBatchNo).setTaskType(taskType).setUpdateBy(ShiroUtils.getSysUser().getUserId().toString());
            this.taskService.updateTLcTaskBySendRobotBatchNo(tLcTask);
            // 异步插入案件历史轨迹表
            List<TLcTask> taskList = this.taskService.selectTaskListBySendRobotBatchNo(sendRobotBatchNo);
            this.duncaseAssignService.batchInsertDuncaseAssign(taskList, ShiroUtils.getSysUser(), taskType);
        });
        return AjaxResult.success();
    }

    @Override
    public List<TLcSendRobotApply> selectSendRobotApplyListByStatus(Integer status) {
        return this.tLcSendRobotApplyMapper.selectSendRobotApplyListByStatus(status);
    }

    @Override
    public void updateSendRobotStatusBySendRobotBatchNo(TLcSendRobotApply sendRobotApply) {
        this.tLcSendRobotApplyMapper.updateSendRobotStatusBySendRobotBatchNo(sendRobotApply);
    }
}
