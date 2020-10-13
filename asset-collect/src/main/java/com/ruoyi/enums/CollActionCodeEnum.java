package com.ruoyi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 催收行动码枚举类
 * @author: liurunkai
 * @Date: 2020/1/3 18:58
 */
@Getter
@AllArgsConstructor
public enum CollActionCodeEnum {

    ALPA("ALPA", "已还款"),
    CNHK("CNHK", "查到客户还款不足以退案"),
    ZTGJ("ZTGJ", "死亡账户已发送死亡证明并报备"),
    NXT("NXT", "无还款诚意"),
    DYZG("DYZG", "答应转告"),
    ZCLX("ZCLX", "再次联系"),

    FRESH("Fresh","Fresh-新任务"),
    SEARCH1("Search1","Search1-联系方式有效"),
    SEARCH2("Search2","Search2-联系方式无效"),
    FOUND1("Found1","Found1-找到本人"),
    FOUND2("Found2","Found2-找到联系人"),
    PTP("PTP","PTP-承诺还款"),
    CHECK("Check","Check-检查付款"),
    TS("TS", "TS-投诉"),
    QT("QT", "QT-其它");

    private String code;
    private String message;
}
