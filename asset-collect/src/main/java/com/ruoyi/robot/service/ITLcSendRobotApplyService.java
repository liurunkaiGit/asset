package com.ruoyi.robot.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.robot.domain.TLcSendRobotApply;

import java.util.List;

/**
 * 推送机器人申请Service接口
 *
 * @author liurunkai
 * @date 2020-07-13
 */
public interface ITLcSendRobotApplyService {
    /**
     * 查询推送机器人申请
     *
     * @param id 推送机器人申请ID
     * @return 推送机器人申请
     */
    public TLcSendRobotApply selectTLcSendRobotApplyById(Integer id);

    /**
     * 查询推送机器人申请列表
     *
     * @param tLcSendRobotApply 推送机器人申请
     * @return 推送机器人申请集合
     */
    public List<TLcSendRobotApply> selectTLcSendRobotApplyList(TLcSendRobotApply tLcSendRobotApply);

    /**
     * 新增推送机器人申请
     *
     * @param tLcSendRobotApply 推送机器人申请
     * @return 结果
     */
    public int insertTLcSendRobotApply(TLcSendRobotApply tLcSendRobotApply);

    /**
     * 修改推送机器人申请
     *
     * @param tLcSendRobotApply 推送机器人申请
     * @return 结果
     */
    public int updateTLcSendRobotApply(TLcSendRobotApply tLcSendRobotApply);

    /**
     * 批量删除推送机器人申请
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTLcSendRobotApplyByIds(String ids);

    /**
     * 删除推送机器人申请信息
     *
     * @param id 推送机器人申请ID
     * @return 结果
     */
    public int deleteTLcSendRobotApplyById(Integer id);

    AjaxResult approveSendRobot(String sendRobotBatchNos, Integer status);

    List<TLcSendRobotApply> selectSendRobotApplyListByStatus(Integer code);

    void updateSendRobotStatusBySendRobotBatchNo(TLcSendRobotApply sendRobotApply);
}
