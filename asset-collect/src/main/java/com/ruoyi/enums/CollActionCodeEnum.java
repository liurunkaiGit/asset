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
    TS("TS", "投诉"),
    NXT("NXT", "无还款诚意"),
    DYZG("DYZG", "答应转告"),
    ZCLX("ZCLX", "再次联系");

    private String code;
    private String message;
}
