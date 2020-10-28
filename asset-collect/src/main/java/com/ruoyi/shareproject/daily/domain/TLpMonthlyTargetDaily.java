package com.ruoyi.shareproject.daily.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 【项目日报-月度信息】对象 t_lp_monthly_target_daily
 * 
 * @author gaohg
 * @date 2020-10-26
 */
@Data
public class TLpMonthlyTargetDaily extends BaseEntity
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
    @Excel(name = "项目表id")
    private Long proId;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String proName;

    /** 分配席位 */
    @Excel(name = "分配席位")
    private Integer allocationOfSeats;

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

    /** 日报id */
    @Excel(name = "日报id")
    private Long dailyId;

    /** $column.columnComment */
    @Excel(name = "日报id")
    private String dailyName;

}
