package com.ruoyi.report.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 回收率报对象 t_lc_report_recovery
 *
 * @author liurunkai
 * @date 2020-04-09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Accessors(chain = true)
public class TLcReportRecovery extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 报表日期
     */
    @Excel(name = "报表日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date reportDate;

    /**
     * 坐席所在组
     */
    @Excel(name = "坐席所在组")
    private String seatGroup;

    /**
     * 坐席编号
     */
    @Excel(name = "坐席编号")
    private String seatNum;

    /**
     * 坐席姓名
     */
    @Excel(name = "坐席姓名")
    private String seatName;

    /**
     * 在催案件数
     */
    @Excel(name = "在催案件数")
    private Long collingCaseNum;

    /**
     * 已确认回收案件数
     */
    @Excel(name = "已确认回收案件数")
    private Long confirmedRecycleCaseNum;

    /**
     * 待确认回收案件数
     */
    @Excel(name = "待确认回收案件数")
    private Long unconfirmedRecycleCaseNum;

    /**
     * 案件回收率
     */
    @Excel(name = "案件回收率")
    private String caseRecovery;

    /**
     * 在催案件金额
     */
    @Excel(name = "在催案件金额")
    private BigDecimal collingCaseMoney;

    /**
     * 已确认回收案件金额
     */
    @Excel(name = "已确认回收案件金额")
    private BigDecimal confirmedRecycleCaseMoney;

    /**
     * 待确认回收案件金额
     */
    @Excel(name = "待确认回收案件金额")
    private BigDecimal unconfirmedRecycleCaseMoney;

    /**
     * 金额回收率
     */
    @Excel(name = "金额回收率")
    private String moneyRecovery;

    /**
     * 所属机构
     */
    private String orgId;

    /**
     * 机构名称
     */
    private String orgName;

}
