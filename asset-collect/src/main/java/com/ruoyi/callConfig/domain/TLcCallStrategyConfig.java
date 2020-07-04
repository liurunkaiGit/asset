package com.ruoyi.callConfig.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 【请填写功能名称】对象 t_lc_call_strategy_config
 *
 * @author liurunkai
 * @date 2020-02-06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TLcCallStrategyConfig extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键自增
     */
    private Long id;

    /**
     * 业务场景 1：新案；2：协催；3：旧案
     */
    @Excel(name = "业务场景 1：新案；2：协催；3：旧案")
    private Integer businessScene;

    /**
     * 每天呼叫次数
     */
    @Excel(name = "每天呼叫次数")
    private Integer callFrequencyDay;

    /**
     * 连续呼叫天数
     */
    @Excel(name = "连续呼叫天数")
    private Integer continueCallDays;

    /**
     * 当天停止呼叫条件
     */
    @Excel(name = "当天停止呼叫条件")
    private String stopCallCurDayCondition;

    /**
     * 停止呼叫任务条件
     */
    @Excel(name = "停止呼叫任务条件")
    private String stopCallCondition;

//    /**
//     * 呼叫起始时间
//     */
//    @Excel(name = "呼叫起始时间", width = 30, dateFormat = "HH:mm:ss")
//    private Date startCallDate;
//
//    /**
//     * 呼叫停止时间
//     */
//    @Excel(name = "呼叫停止时间", width = 30, dateFormat = "HH:mm:ss")
//    private Date stopCallDate;
    /**
     * 呼叫起始时间
     */
    @Excel(name = "呼叫起始时间")
    private String startCallDate;

    /**
     * 呼叫停止时间
     */
    @Excel(name = "呼叫停止时间")
    private String stopCallDate;

    /**
     * 配置是否生效0：不生效，1：生效
     */
    @Excel(name = "配置是否生效")
    private Integer status;

    /**
     * 所属机构id
     */
    @Excel(name = "所属机构id")
    private String orgId;

    /**
     * 机构名称
     */
    @Excel(name = "机构名称")
    private String orgName;

    /**
     * 机器人话术 id
     */
    @Excel(name = "机器人话术 id")
    private Integer speechcraftId;

    /**
     * 场景 id
     */
    @Excel(name = "场景 id")
    private Integer sceneDefId;

    /**
     * 机器人话术列表
     */
    @Excel(name = "机器人话术列表")
    private String speechcraftName;

    /**
     * 通话间隔时间id
     */
    private String callIntervalTimeId;

    /**
     * 通话间隔时间
     */
    @Excel(name = "通话间隔时间")
    private String callIntervalTime;

    /**
     * 通话线路id
     */
    private String callLineId;

    /**
     * 通话线路名称
     */
    @Excel(name = "通话线路名称")
    private String callLineName;

    private String callLineIdAndName;

    /**
     * 机器人话术id和场景id用逗号分隔
     */
    private String speechcraftIdAndSceneDefId;

}
