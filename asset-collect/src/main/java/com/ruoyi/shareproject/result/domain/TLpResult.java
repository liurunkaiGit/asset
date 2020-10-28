package com.ruoyi.shareproject.result.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 【请填写功能名称】对象 t_lp_result
 *
 * @author liurunkai
 * @date 2020-10-16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TLpResult extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 报表日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报表日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reportDate;

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

    private String projectIdName;

    /**
     * 账龄
     */
    @Excel(name = "账龄")
    private String transferType;

    /**
     * 委案批次
     */
    @Excel(name = "委案批次")
    private String importBatchNo;

    /**
     * 佣金比例
     */
    @Excel(name = "佣金比例")
    private String commissionProportion;

    /**
     * 累计回收
     */
    @Excel(name = "累计回收")
    private BigDecimal totalRecycle;

    /**
     * 预计佣金
     */
    @Excel(name = "预计佣金")
    private BigDecimal predictCommission;

    /**
     * 回收率
     */
    @Excel(name = "回收率")
    private String recycleRate;

    /**
     * 目标排名
     */
    @Excel(name = "目标排名")
    private Integer targetRank;

    private Date startReportDate;
    private Date endReportDate;

}
