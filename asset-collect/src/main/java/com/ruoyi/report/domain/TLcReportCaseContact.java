package com.ruoyi.report.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 案件可联率报对象 t_lc_report_case_contact
 *
 * @author liurunkai
 * @date 2020-04-09
 */
@Data
@Accessors(chain = true)
public class TLcReportCaseContact extends BaseEntity {
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
     * 委案金额区间
     */
    @Excel(name = "委案金额区间")
    private String appointCaseMoneySection;

    /**
     * 总户数
     */
    @Excel(name = "总户数")
    private Long totalCustomer;

    /**
     * 可联户数
     */
    @Excel(name = "可联户数")
    private Long canContactCustomer;

    /**
     * 账户联系率
     */
    @Excel(name = "账户联系率")
    private String customerContactRecovery;

    /**
     * 可联次数
     */
    @Excel(name = "可联次数")
    private Long canContactCount;

    /**
     * 可联案件渗透率
     */
    @Excel(name = "可联案件渗透率")
    private String canContactCasePermetaRecovery;

    /**
     * 所属机构
     */
    private String orgId;

    /**
     * 机构名称
     */
    private String orgName;

}
