package com.ruoyi.inforeporting.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 上报信息-减免
 * @author: gaohg
 * @Date: 2020/8/12
 */
@Data
public class TLcInforeportingReductionJiexin extends BaseEntity {
    private static final long serialVersionUID = 1646626261616L;
    /**
     * 合同号
     */
    @Excel(name = "合同号")
    protected String caseNo;
    /**
     * 客户姓名
     */
    @Excel(name = "姓名")
    protected String customName;
    /**
     * 委案金额
     */
    @Excel(name = "委案金额")
    protected Double arrearsTotal;

    /**
     * 逾期天数
     */
    @Excel(name = "逾期天数")
    private String overdueDays;
    /**
     * 划扣金额
     */
    @Excel(name = "划扣金额")
    private Double deductionAmount;
    /**
     * 减免后金额
     */
    @Excel(name = "减免后金额")
    private Double reductionAfterAmount;

    /**
     * 还款比例
     */
    @Excel(name = "还款比例")
    private String repaymentRatio;

    /**
     * 地区
     */
    @Excel(name = "地区")
    private String area;
    /**
     * 地区
     */
    @Excel(name = "机构")
    private String orgName = "华道";
    /**
     * 联系方式
     */
    @Excel(name = "减免联系方式")
    private String telephone;

    /**
     * 与本人关系
     */
    @Excel(name = "与本人关系")
    private String relationship;
    /**
     * 减免失效日
     */
    @Excel(name = "减免失效日")
    private Date expirationDate;
}
