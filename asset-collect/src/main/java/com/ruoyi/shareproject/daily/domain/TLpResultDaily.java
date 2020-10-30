package com.ruoyi.shareproject.daily.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 【日报管理-结果指标】对象 t_lp_result_daily
 * 
 * @author gaohg
 * @date 2020-10-27
 */
@Data
public class TLpResultDaily extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 报表日期 */
    @Excel(name = "报表日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date reportDate;

    /** 机构id */
    @Excel(name = "机构id")
    private Long orgId;

    /** 机构名称 */
    @Excel(name = "机构名称")
    private String orgName;

    /** 账龄 */
    @Excel(name = "账龄")
    private String transferType;

    /** 委案批次 */
    @Excel(name = "委案批次")
    private String importBatchNo;

    /** 佣金比例 */
    @Excel(name = "佣金比例")
    private String commissionProportion;

    /** 累计回收 */
    @Excel(name = "累计回收")
    private Long totalRecycle;

    /** 预计佣金 */
    @Excel(name = "预计佣金")
    private Long predictCommission;

    /** 回收率 */
    @Excel(name = "回收率")
    private String recycleRate;

    /** 目标排名 */
    @Excel(name = "目标排名")
    private String targetRank;
    /** 日报id */
    @Excel(name = "日报id")
    private Long dailyId;

    /** $column.columnComment */
    @Excel(name = "日报id")
    private String dailyName;

}
