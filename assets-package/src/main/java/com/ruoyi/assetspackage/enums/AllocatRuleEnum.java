package com.ruoyi.assetspackage.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 分配规则枚举类
 * @author: liurunkai
 * @Date: 2020/1/4 18:31
 */
@Getter
@AllArgsConstructor
public enum AllocatRuleEnum {

    DUNCASE_NUM_AVERAGE(1, "案件数量平均分配"),
    DUNCASE_MONEY_AVERAGE(2, "案件金额平均分配"),
    DUNCASE_MONEY_NUM_AVERAGE(3, "案件金额和数量平均分配");

    private Integer code;
    private String message;
}
