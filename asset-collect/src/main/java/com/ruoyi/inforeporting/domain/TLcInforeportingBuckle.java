package com.ruoyi.inforeporting.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
/**
 * @Description: 上报信息-逾期划扣
 * @author: gaohg
 * @Date: 2020/8/12
 */
@Data
public class TLcInforeportingBuckle extends TLcInforeportingTemplate {
    private static final long serialVersionUID = 164661666616L;

    /**
     * 逾期天数
     */
    @Excel(name = "逾期天数")
    protected Long overdueDays;
    @Excel(name = "减免方式")
    private String reductionTypeName;
}
