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
public class TLcInforeportingReduction extends TLcInforeportingTemplate {
    private static final long serialVersionUID = 164661616L;
    @Excel(name = "划扣金额")
    private Double deductionAmount;
    @Excel(name = "减免方式")
    private String reductionTypeName;
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
     * 减免后金额
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
