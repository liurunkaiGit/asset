package com.ruoyi.shareproject.hmuserst.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 【员工状态】对象 t_lj_rule_user_logs
 * 
 * @author gaohg
 * @date 2020-11-05
 */
@Data
public class TLjRuleUserLogs extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 登录id */
    @Excel(name = "登录id")
    private String loginName;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    private String userName;

    /** 规则id */
    @Excel(name = "规则id")
    private Long ruleId;

    /** $column.columnComment */
    @Excel(name = "规则id")
    private Long detailsId;

    /** 开始时间段 */
    @Excel(name = "开始时间段")
    private String startTime;

    /** 结束时间段 */
    @Excel(name = "结束时间段")
    private String endTime;

    /** 异常数 */
    @Excel(name = "异常数")
    private Integer errors;

    /** 时间-天 */
    @Excel(name = "时间-天", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date days;

    /** 在线时长异常 */
    @Excel(name = "在线时长异常")
    private Integer onlineError;

    /** 在线时长 */
    @Excel(name = "在线时长")
    private BigDecimal onlineTime;

    private Integer onlineZhibiao;
    private String onlineBaifen;

    /** $column.columnComment */
    @Excel(name = "在线时长")
    private Integer outError;

    /** 退出次数 */
    @Excel(name = "退出次数")
    private Integer outCishu;
    private Integer outCishuZhibiao;
    private String outCishuBaifen;

    /** 间隔时长 */
    @Excel(name = "间隔时长")
    private Integer jiangeError;

    /** $column.columnComment */
    @Excel(name = "间隔时长")
    private BigDecimal jiange;
    private Integer jiangeZhibiao;
    private String jiangeBaifen;

    /** 通话时长 */
    @Excel(name = "通话时长")
    private Integer tonghuaError;

    /** $column.columnComment */
    @Excel(name = "通话时长")
    private BigDecimal tonghuaDuration;
    private Integer tonghuaDurationZhibiao;
    private String tonghuaDurationBaifen;

    /** $column.columnComment */
    @Excel(name = "通话时长")
    private Integer tonghuacsError;

    /** 通话次数 */
    @Excel(name = "通话次数")
    private Integer tonghuacs;
    private Integer tonghuacsZhibiao;
    private String tonghuacsBaifen;
    /** 接通率 */
    @Excel(name = "接通率")
    private Integer jietonglvError;

    /** 接通次数 */
    @Excel(name = "接通次数")
    private Integer jietongcs;
    private BigDecimal jietonglv;
    private Integer jietongcsZhibiao;
    private String jietongcsBaifen;

    /** 案件量 */
    @Excel(name = "案件量")
    private Integer anjianError;

    /** 案件处理量 */
    @Excel(name = "案件处理量")
    private Integer anjianDuration;
    private Integer anjianDurationZhibiao;
    private String anjianDurationBaifen;

    /** 案件处理率 */
    @Excel(name = "案件处理率")
    private Integer anjianlvError;

    /** $column.columnComment */
    @Excel(name = "案件处理率")
    private Integer anjianyichuli;
    private BigDecimal anjianlv;
    private Integer anjianyichuliZhibiao;
    private String anjianyichuliBaifen;
    /** 实际 */
    @Excel(name = "实际")
    private Integer shijiError;

    /** $column.columnComment */
    @Excel(name = "实际")
    private BigDecimal yinghuan;
    private Integer yinghuanZhibiao;
    private String yinghuanBaifen;

    /** 实际率 */
    @Excel(name = "实际率")
    private Integer shijilvError;
    private BigDecimal shijilv;

    /** $column.columnComment */
    @Excel(name = "实际率")
    private BigDecimal shiji;
    private Integer shijiZhibiao;
    private String shijiBaifen;

    /** 登录异常总 */
    @Excel(name = "登录异常总")
    private Integer loginZong;

    /** 还款异常总 */
    @Excel(name = "还款异常总")
    private Integer huankuanZong;
    /** 通话异常总 */
    @Excel(name = "通话异常总")
    private Integer tonghuaZong;

    /** 案件异常总 */
    @Excel(name = "案件异常总")
    private Integer anjianZong;

    /** 机构id */
    @Excel(name = "机构id")
    private Long orgId;

    /** 机构名称 */
    @Excel(name = "机构名称")
    private String orgName;

    @Excel(name = "在线时长条件")
    private String onlineCondition;
    /** 在线时长-条件1 */
    @Excel(name = "在线时长-条件1")
    private Integer onlineOne;

    /** 在线时长-条件2 */
    @Excel(name = "在线时长-条件2")
    private Integer onlineTwo;

    /** 退出条件 */
    @Excel(name = "退出条件")
    private String outCondition;

    /** 退出条件1 */
    @Excel(name = "退出条件1")
    private Integer outOne;

    /** 退出条件2 */
    @Excel(name = "退出条件2")
    private Integer outTwo;

    /** 间隔条件 */
    @Excel(name = "间隔条件")
    private String intervalsCondition;

    /** 间隔条件1 */
    @Excel(name = "间隔条件1")
    private Integer intervalsOne;

    /** 间隔条件2 */
    @Excel(name = "间隔条件2")
    private Integer intervalsTwo;

    /** 通话时长条件 */
    @Excel(name = "通话时长条件")
    private String conversationCondition;

    /** 通过条件1 */
    @Excel(name = "通过条件1")
    private Integer conversationOne;

    /** 通话条件2 */
    @Excel(name = "通话条件2")
    private Integer conversationTwo;

    /** 通话次数条件 */
    @Excel(name = "通话次数条件")
    private String conversationCishuCondition;

    /** 通话次数1 */
    @Excel(name = "通话次数1")
    private Integer conversationCishuOne;

    /** 通话次数2 */
    @Excel(name = "通话次数2")
    private Integer conversationCishuTwo;

    /** 接通条件 */
    @Excel(name = "接通条件")
    private String engRateCondition;

    /** 接通率条件1 */
    @Excel(name = "接通率条件1")
    private Integer engRateOne;

    /** 接通率条件2 */
    @Excel(name = "接通率条件2")
    private Integer engRateTwo;

    /** 案件处理量条件 */
    @Excel(name = "案件处理量条件")
    private String caseNumbersCondition;

    /** 案件数量条件1 */
    @Excel(name = "案件数量条件1")
    private Integer caseNumbersOne;

    /** 案件数量条件2 */
    @Excel(name = "案件数量条件2")
    private Integer caseNumbersTwo;

    /** 案件处理率条件 */
    @Excel(name = "案件处理率条件")
    private String caseRateCondition;

    /** 案件处理率条件1 */
    @Excel(name = "案件处理率条件1")
    private BigDecimal caseRateOne;

    /** 案件处理率条件2 */
    @Excel(name = "案件处理率条件2")
    private BigDecimal caseRateTwo;

    /** 实际还款条件 */
    @Excel(name = "实际还款条件")
    private String sjRepaymentCondition;

    /** 实际还款条件1 */
    @Excel(name = "实际还款条件1")
    private BigDecimal sjRepaymentOne;

    /** 实际还款条件2 */
    @Excel(name = "实际还款条件2")
    private BigDecimal sjRepaymentTwo;

    /** 实际还款率条件 */
    @Excel(name = "实际还款率条件")
    private String sjRepaymentRateCondition;

    /** 实际还款率条件1 */
    @Excel(name = "实际还款率条件1")
    private BigDecimal sjRepaymentRateOne;

    /** 实际还款率条件2 */
    @Excel(name = "实际还款率条件2")
    private BigDecimal sjRepaymentRateTwo;
}
