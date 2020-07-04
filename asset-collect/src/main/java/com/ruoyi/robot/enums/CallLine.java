package com.ruoyi.robot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 主叫号码(线路)类型枚举
 * @author: liurunkai
 * @Date: 2020/2/11 16:41
 */
@Getter
@AllArgsConstructor
public enum CallLine {

    PHONE(0, "手机号"),
    FIX_PHONE(1, "固话"),
    NO_CALL_LINE(2, "无主叫线路");

    private Integer code;
    private String desc;
}
