package com.ruoyi.shareproject.monthlytarget.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 【月度指标】对象 t_lp_monthly_target
 * 
 * @author gaohg
 * @date 2020-10-15
 */
@Data
public class TLpMonthlyTarget extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 年份 */
    @Excel(name = "年份")
    private Integer particularYear;

    /** 月份 */
    @Excel(name = "月份")
    private Integer particularMonth;

    /** 项目表id */
    private Long proId;

    /** 项目名称 */
    @Excel(name = "项目")
    private String proName;

    /** 分配席位 */
    @Excel(name = "分配席位")
    private BigDecimal allocationOfSeats;

    /** 委案批次 */
    @Excel(name = "委案批次")
    private Integer commissionBatch;

    /** 委案件数 */
    @Excel(name = "委案件数")
    private Integer numberOfCommission;

    /** 委案金额 */
    @Excel(name = "委案金额")
    private BigDecimal commissionAmount;

    /** 目标回款金额 */
    @Excel(name = "目标回款金额")
    private BigDecimal amountReceived;

    /** 创佣金额 */
    @Excel(name = "创佣金额")
    private BigDecimal amountCreated;

    /** 上月流入率 */
    @Excel(name = "上月流入率")
    private String inflowrateOfLastmonth;

    /** 本月目标流入率 */
    @Excel(name = "本月目标流入率")
    private String inflowrateOfMonth;

    /** 目标排名 */
    @Excel(name = "目标排名")
    private String targetRanking;

    /** 机构id */
    @Excel(name = "机构id")
    private Long orgId;

    /** 机构名称 */
    @Excel(name = "机构名称")
    private String orgName;

    /**
     * 账龄
     */
    @Excel(name = "账龄")
    private String transferType;

    private String proIdName;

}
