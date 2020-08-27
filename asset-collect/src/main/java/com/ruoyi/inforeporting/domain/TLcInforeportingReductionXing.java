package com.ruoyi.inforeporting.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * @Description: 上报信息-减免
 * @author: gaohg
 * @Date: 2020/8/12
 */
@Data
public class TLcInforeportingReductionXing extends BaseEntity {
    private static final long serialVersionUID = 1646626261616L;
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
     * 序号
     */
    @Excel(name = "序号")
    private int xh;
    /**
     * 产品
     */
    @Excel(name = "产品")
    protected String productName;
    /**
     * 合同号
     */
    @Excel(name = "借据号")
    protected String caseNo;
    /**
     * 逾期天数
     */
    @Excel(name = "逾期天数")
    private String overdueDays;
    /**
     * 划扣金额
     */
    private Double deductionAmount;
    /**
     * 客户姓名
     */
    @Excel(name = "客户姓名")
    protected String customName;

    /**
     * 批次号
     */
    protected String importBatchNo;
    /**
     * 减免方式编码
     */
    protected Integer reductionTypeCode;
    /**
     * 减免方式
     */
    @Excel(name = "部份划扣/一次性结清/减免金额")
    protected String reductionTypeName;
    /**
     * 件号
     */
    protected String certificateNo;
    /**
     * 委案金额
     */
    protected Double arrearsTotal;
    /**
     * 区域
     */
    protected String area;

    protected String remarks;


    /**
     * 手别
     */
    private String transferType;
    /**
     * 呆账核销日期
     */
    private String dzhxrq;
    /**
     * 呆账核销日期
     */
    private Double reductionAfterAmount;
    /**
     * 还款比例
     */
    private String repaymentRatio;
    /**
     * 联系方式
     */
    private String telephone;
    /**
     * 与本人关系
     */
    private String relationship;
    /**
     * 减免失效日
     */
    private String expirationDate;
}
