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
public class TLcInforeportingReduction extends TLcInforeportingTemplate {
    private static final long serialVersionUID = 164661616L;
    @Excel(name = "划扣金额")
    private Double deductionAmount;
    @Excel(name = "减免方式")
    private String reductionTypeName;
}
