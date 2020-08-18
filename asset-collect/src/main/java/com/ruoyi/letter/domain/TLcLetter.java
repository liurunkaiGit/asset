package com.ruoyi.letter.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 【请填写功能名称】对象 t_lc_letter
 *
 * @author liurunkai
 * @date 2020-08-17
 */
@Data
public class TLcLetter extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 机构案件号
     */
    @Excel(name = "机构案件号")
    private String caseNo;

    /**
     * 导入批次号
     */
    private String importBatchNo;

    /**
     * 机构id
     */
    private Long orgId;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 任务id
     */
    private Long tastId;

    /**
     * 客户姓名
     */
    @Excel(name = "客户姓名")
    private String customName;

    /**
     * 业务归属人id
     */
    private Long ownerId;

    /**
     * 业务归属人姓名
     */
    @Excel(name = "业务归属人")
    private String ownerName;

    /**
     * 手别
     */
    @Excel(name = "手别")
    private String transferType;

    /**
     * 委案金额
     */
    @Excel(name = "委案金额")
    private Double arrearsTotal;

    /**
     * 结案应还金额
     */
    @Excel(name = "结案应还金额")
    private Double closeCaseYhje;

    /**
     * 借款日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "借款日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date loanDate;

    /**
     * 信函类型，1：催缴函，2：律师函
     */
    @Excel(name = "信函类型", readConverterExp = "1=催缴函,2=律师函")
    private Integer letterType;

    /**
     * 审批状态，1：待审批，2：审批通过，3：审批拒绝
     */
    @Excel(name = "审批状态", readConverterExp = "1=待审批,2=审批通过,3=审批拒绝")
    private Integer applyStatus;

    private Date startCreateTime;
    private Date endCreateTime;

    private String letterIds;
}
