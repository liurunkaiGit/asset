package com.ruoyi.robot.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 推送机器人申请对象 t_lc_send_robot_apply
 *
 * @author liurunkai
 * @date 2020-07-13
 */
@Data
@Accessors(chain = true)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TLcSendRobotApply extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 任务名称
     */
    @Excel(name = "任务名称")
    private String sendRobotBatchNo;

    /**
     * 机构id
     */
    @Excel(name = "机构id")
    private Long orgId;

    /**
     * 机构名称
     */
    @Excel(name = "机构名称")
    private String orgName;

    /**
     * 任务数量
     */
    @Excel(name = "任务数量")
    private Integer taskNum;

    /**
     * 业务归属人id
     */
    @Excel(name = "业务归属人id")
    private Long ownerId;

    /**
     * 业务归属人姓名
     */
    @Excel(name = "业务归属人姓名")
    private String ownerName;

    /**
     * 任务状态，0：待审批，1：审批通过，2：审批拒绝
     */
    @Excel(name = "任务状态，0：待审批，1：审批通过，2：审批拒绝")
    private Integer taskStatus;

    private Date startCreateTime;
    private Date endCreateTime;

}
