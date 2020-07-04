package com.ruoyi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @Description: 停止呼叫条件枚举
 * @author: liurunkai
 * @Date: 2020/1/3 18:58
 */
@Getter
@AllArgsConstructor
public enum StopCallConditionEnum {

    A(1, "A"),
    B(2, "B"),
    C(3, "C"),
    D(4, "D"),
    E(5, "E");

    private Integer code;
    private String message;

    public static Integer getCodeByMessage(String message) {
        for (StopCallConditionEnum stopCallConditionEnum : StopCallConditionEnum.values()) {
            if (stopCallConditionEnum.getMessage().equals(message)) {
                return stopCallConditionEnum.getCode();
            }
        }
        return -1;
    }
}
