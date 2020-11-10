package com.ruoyi.shareproject.hmrule.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigInteger;

/**
 * 【居家规则详情】对象 t_lj_rule_details
 * 
 * @author gaohg
 * @date 2020-11-02
 */
@Data
public class TLjRuleDetails extends BaseEntity
{
    private static final long serialVersionUID = 1855855L;

    /** $column.columnComment */
    private Long id;

    private Long ruleId;
    /** 开始 */
    @Excel(name = "开始")
    private String startTime;
    private Long startTimeHm;

    /** 结束时间 */
    @Excel(name = "结束时间")
    private String endTime;
    private Long endTimeHm;

    /** 在线时长0=不选择1=选择 */
    @Excel(name = "在线时长0=不选择1=选择")
    private String onlineTime;

    /** $column.columnComment */
    @Excel(name = "在线时长0=不选择1=选择")
    private String onlineCondition;

    /** 在线时长-条件1 */
    @Excel(name = "在线时长-条件1")
    private Integer onlineOne;

    /** 在线时长-条件2 */
    @Excel(name = "在线时长-条件2")
    private Integer onlineTwo;

    /** 退出次数 */
    @Excel(name = "退出次数")
    private String outTime;

    /** 退出条件 */
    @Excel(name = "退出条件")
    private String outCondition;

    /** 退出条件1 */
    @Excel(name = "退出条件1")
    private Integer outOne;

    /** 退出条件2 */
    @Excel(name = "退出条件2")
    private Integer outTwo;

    /** $column.columnComment */
    @Excel(name = "退出条件2")
    private String intervals;

    /** $column.columnComment */
    @Excel(name = "退出条件2")
    private String intervalsCondition;

    /** 间隔条件1 */
    @Excel(name = "间隔条件1")
    private Integer intervalsOne;

    /** 间隔条件2 */
    @Excel(name = "间隔条件2")
    private Integer intervalsTwo;

    /** 通话时长 */
    @Excel(name = "通话时长")
    private String conversationTime;

    /** $column.columnComment */
    @Excel(name = "通话时长")
    private String conversationCondition;

    /** 通过条件1 */
    @Excel(name = "通过条件1")
    private Integer conversationOne;

    /** 通话条件2 */
    @Excel(name = "通话条件2")
    private Integer conversationTwo;

    /** 通话次数 */
    @Excel(name = "通话次数")
    private String conversationCishu;

    /** 通话次数条件 */
    @Excel(name = "通话次数条件")
    private String conversationCishuCondition;

    /** 通话次数1 */
    @Excel(name = "通话次数1")
    private Integer conversationCishuOne;

    /** 通话次数2 */
    @Excel(name = "通话次数2")
    private Integer conversationCishuTwo;

    /** 接通率 */
    @Excel(name = "接通率")
    private String engRate;

    /** 接通条件 */
    @Excel(name = "接通条件")
    private String engRateCondition;

    /** 接通率条件1 */
    @Excel(name = "接通率条件1")
    private Integer engRateOne;

    /** 接通率条件2 */
    @Excel(name = "接通率条件2")
    private Integer engRateTwo;

    /** 案件数量 */
    @Excel(name = "案件数量")
    private String caseNumbers;

    /** 案件处理量条件 */
    @Excel(name = "案件处理量条件")
    private String caseNumbersCondition;

    /** 案件数量条件1 */
    @Excel(name = "案件数量条件1")
    private Integer caseNumbersOne;

    /** 案件数量条件2 */
    @Excel(name = "案件数量条件2")
    private Integer caseNumbersTwo;

    /** 案件处理率 */
    @Excel(name = "案件处理率")
    private String caseRate;

    /** 案件处理率条件 */
    @Excel(name = "案件处理率条件")
    private String caseRateCondition;

    /** 案件处理率条件1 */
    @Excel(name = "案件处理率条件1")
    private Integer caseRateOne;

    /** 案件处理率条件2 */
    @Excel(name = "案件处理率条件2")
    private Integer caseRateTwo;

    /** 实际还款 */
    @Excel(name = "实际还款")
    private String sjRepayment;

    /** 实际还款条件 */
    @Excel(name = "实际还款条件")
    private String sjRepaymentCondition;

    /** 实际还款条件1 */
    @Excel(name = "实际还款条件1")
    private Integer sjRepaymentOne;

    /** 实际还款条件2 */
    @Excel(name = "实际还款条件2")
    private Integer sjRepaymentTwo;

    /** 实际还款率 */
    @Excel(name = "实际还款率")
    private String sjRepaymentRate;

    /** 实际还款率条件 */
    @Excel(name = "实际还款率条件")
    private String sjRepaymentRateCondition;

    /** 实际还款率条件1 */
    @Excel(name = "实际还款率条件1")
    private Integer sjRepaymentRateOne;

    /** 实际还款率条件2 */
    @Excel(name = "实际还款率条件2")
    private Integer sjRepaymentRateTwo;

    /** 机构id */
    @Excel(name = "机构id")
    private Long orgId;

    /** 机构名称 */
    @Excel(name = "机构名称")
    private String orgName;


}
