package com.ruoyi.inforeporting.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * @Description: 上报信息 模板
 * @author: gaohg
 * @Date: 2020/8/12
 */
@Data
public class TLcInforeportingTemplate extends BaseEntity {
    /**
     * 主键
     */
    protected Long id;
    /**
     * 类型
     */
    protected Integer types;
    /**
     * 部门id
     */
    protected Long orgId;
    /**
     * 部门名称
     */
    protected String orgName;
    /**
     * 产品
     */
    @Excel(name = "产品")
    protected String productName;
    /**
     * 合同号
     */
    @Excel(name = "合同号")
    protected String caseNo;
    /**
     * 批次号
     */
    protected String importBatchNo;
    /**
     * 客户姓名
     */
    @Excel(name = "客户姓名")
    protected String customName;
    /**
     * 逾期天数
     */
    protected Long overdueDays;
    /**
     * 逾期天数
     */
    protected Integer reductionTypeCode;
    /**
     * 减免方式
     */
    protected String reductionTypeName;
    /**
     * 件号
     */
    protected String certificateNo;
    /**
     * 委案金额
     */
    protected Double arrearsTotal;
}
