package com.ruoyi.inforeporting.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description: 上报信息-逾期划扣
 * @author: gaohg
 * @Date: 2020/8/12
 */
@Data
public class TLcInforeportingBuckle extends BaseEntity {
    private static final long serialVersionUID = 164661666616L;

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
    private String productName;
    /**
     * 合同号
     */
    @Excel(name = "合同号")
    private String caseNo;
    /**
     * 批次号
     */
    private String importBatchNo;
    /**
     * 客户姓名
     */
    @Excel(name = "客户姓名")
    private String customName;
    @Excel(name = "划扣金额")
    private Double deductionAmount;
    /**
     * 逾期天数
     */
    @Excel(name = "逾期天数")
    private String overdueDays;
    @Excel(name = "部份划扣/一次性结清/减免金额")
    private String reductionTypeName;
    /**
     * 减免方式code
     */
    private Integer reductionTypeCode;
    /**
     * 件号
     */
    private String certificateNo;
    /**
     * 委案金额
     */
    private Double arrearsTotal;
    /**
     * 地区
     */
    private String area;
    /**
     * 备注
     */
    private String remarks;
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
    /**
     * 是否组内
     */
    private Integer isGroup;

    private List<String> userNames;
}
