package com.ruoyi.shareproject.finance.commission.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 财务结佣对象 t_lp_finance_commission
 *
 * @author liurunkai
 * @date 2020-10-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TLpFinanceCommission extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 月份
     */
    @Excel(name = "月份")
    private String month;

    /**
     * 项目id
     */
    @Excel(name = "项目id")
    private Long projectId;

    /**
     * 项目名称
     */
    @Excel(name = "项目名称")
    private String projectName;

    /**
     * 总笔数
     */
    @Excel(name = "总笔数")
    private Integer totalNum;

    /**
     * 最新预计结佣
     */
    @Excel(name = "最新预计结佣")
    private BigDecimal latestPredictCommission;

    /**
     * 实际结佣
     */
    @Excel(name = "实际结佣")
    private BigDecimal actualCommission;

    /**
     * 结佣差异
     */
    @Excel(name = "结佣差异")
    private BigDecimal commissionDifferent;

    /**
     * 结费状态，1：已填写，2：未填写
     */
    @Excel(name = "结费状态", readConverterExp = "1=已填写,2=未填写")
    private Integer feeStatus;

    private List<String> idList;
    private String ids;

    private String startMonth;
    private String endMonth;

}
