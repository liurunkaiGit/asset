package com.ruoyi.robot.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 【请填写功能名称】对象 t_lc_robot_task_pandect
 *
 * @author liurunkai
 * @date 2020-05-09
 */
@Data
@Accessors(chain = true)
public class TLcRobotTaskPandect extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 机器人任务id
     */
    @Excel(name = "机器人任务id")
    private Integer robotTaskId;

    /**
     * 任务名称
     */
    @Excel(name = "任务名称")
    private String taskName;

    /**
     * 机器人话术id
     */
    @Excel(name = "机器人话术id")
    private Integer speechCraftNameId;

    /**
     * 机器人话术名称
     */
    @Excel(name = "机器人话术名称")
    private String speechCraftName;

    /**
     * 机器人任务状态
     */
    @Excel(name = "机器人任务状态")
    private Integer robotTaskStatus;

    /**
     * 机构编号
     */
    @Excel(name = "机构编号")
    private Long orgId;

    /**
     * 机构名称
     */
    @Excel(name = "机构名称")
    private String orgName;

    /**
     * 所属机器人
     */
    @Excel(name = "所属机器人")
    private String robot;

    /**
     * 任务拨打的号码总数
     */
    @Excel(name = "任务拨打的号码总数")
    private Integer callTotalCount;

    /**
     * 任务已完成拨打的号码总数
     */
    @Excel(name = "任务已完成拨打的号码总数")
    private Integer callDoneCount;

    /**
     * 任务已完成呼通的号码总数
     */
    @Excel(name = "任务已完成呼通的号码总数")
    private Integer callCalledCount;

    /**
     * 占线数量
     */
    private Integer callBusyCount;

    /**
     * 失联数量
     */
    private Integer callMissCount;

    /**
     * 任务呼叫被拒接的号码总数
     */
    @Excel(name = "任务呼叫被拒接的号码总数")
    private Integer callRejectedCount;

    /**
     * 任务呼叫无法接通的号码总数
     */
    @Excel(name = "任务呼叫无法接通的号码总数")
    private Integer callUnavailableCount;

    /**
     * 任务主叫号码不可用的号码总
     */
    @Excel(name = "任务主叫号码不可用的号码总")
    private Integer callFromUnavailableCount;

    /**
     * 任务开始时间
     */
    @Excel(name = "任务开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date taskStartTime;

    /**
     * 任务结束时间
     */
    @Excel(name = "任务结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date taskEndTime;

    /**
     * 空号总数
     */
    @Excel(name = "空号总数")
    private Integer callBlankCount;

    /**
     * 关机总数
     */
    @Excel(name = "关机总数")
    private Integer callClosedCount;

    /**
     * 停机总数
     */
    @Excel(name = "停机总数")
    private Integer callDownCount;

    /**
     * 黑名单总数
     */
    @Excel(name = "黑名单总数")
    private Integer callBlackCount;

    /**
     * 外呼失败总数
     */
    @Excel(name = "外呼失败总数")
    private Integer callFailCount;

    /**
     * 呼损总数
     */
    @Excel(name = "呼损总数")
    private Integer callLossCount;

    /**
     * 主叫欠费总数
     */
    @Excel(name = "主叫欠费总数")
    private Integer callOverdueCount;

    private Date startCreateTime;
    private Date endCreateTime;
    private Date startTaskStartTime;
    private Date endTaskStartTime;
    private Date startTaskEndTime;
    private Date endTaskEndTime;

}
