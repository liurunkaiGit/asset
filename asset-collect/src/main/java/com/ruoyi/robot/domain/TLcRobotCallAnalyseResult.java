package com.ruoyi.robot.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 【请填写功能名称】对象 t_lc_robot_call_analyse_result
 *
 * @author liurunkai
 * @date 2020-02-20
 */
@Data
@Accessors(chain = true)
public class TLcRobotCallAnalyseResult extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 通话记录结果 Id
     */
    @Excel(name = "通话记录结果 Id")
    private Integer sceneInstanceResultId;

    /**
     * 公司id
     */
    @Excel(name = "公司id")
    private Integer companyId;

    /**
     * 任务 ID
     */
    @Excel(name = "任务 ID")
    private Integer callJobId;

    /**
     * 通 话 记 录 Id( 对 应sceneInstanceId)
     */
    @Excel(name = "通 话 记 录 Id( 对 应sceneInstanceId)")
    private Integer inboundInstanceId;

    /**
     * 通 话 记 录 Id( 对 应sceneInstanceId)
     */
    @Excel(name = "通 话 记 录 Id( 对 应sceneInstanceId)")
    private Integer sceneInstanceId;

    /**
     * 通话记录结果类型名
     */
    @Excel(name = "通话记录结果类型名")
    private String resultName;

    /**
     * 通话记录结果值
     */
    @Excel(name = "通话记录结果值")
    private String resultValue;

    /**
     * 通话结果人工标注值（一般指人工标注意向等级）
     */
    @Excel(name = "通话结果人工标注值", readConverterExp = "一=般指人工标注意向等级")
    private String artificialResultValue;

    /**
     * 是否进行过人工标注修改，参数对应boolean
     */
    @Excel(name = "是否进行过人工标注修改，参数对应boolean")
    private Boolean artificialChanged;

    /**
     * 是否进行过人工标注修改，参数对应boolean
     */
    @Excel(name = "是否进行过人工标注修改，参数对应boolean")
    private Integer artificialChangedValue;

    /**
     * 结果描述
     */
    @Excel(name = "结果描述")
    private String resultDesc;

    /**
     * 分析结果别名(resultName 为【客户意向等级】 时标注值为意向级别 A,B,C,D,E,F)
     */
    @Excel(name = "分析结果别名(resultName 为【客户意向等级】 时标注值为意向级别 A,B,C,D,E,F)")
    private String resultValueAlias;

    /**
     * IntegerStringBO 对象中存储一个 int 类型参数， 一个 String类型参数， resultName 为【客户标签】 时存储客户标签
     */
    @Excel(name = "IntegerStringBO 对象中存储一个 int 类型参数， 一个 String类型参数， resultName 为【客户标签】 时存储客户标签")
    private Object resultLabels;

    /**
     * 客户意向等级的表述（文案与crm 对应）
     */
    @Excel(name = "客户意向等级的表述", readConverterExp = "文=案与crm,对=应")
    private String resultValueNew;

    /**
     * 回调签名（需联系开通）
     */
    @Excel(name = "回调签名", readConverterExp = "需=联系开通")
    private String sign;

    /**
     * GMT 格式日期
     */
    @Excel(name = "GMT 格式日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String dateTime;

    /**
     * 呼入回调标识：INBOUND_CALL_INSTANCE_RESULT
     */
    @Excel(name = "呼入回调标识：INBOUND_CALL_INSTANCE_RESULT")
    private String dataType;
}
