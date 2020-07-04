package com.ruoyi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 是否重复呼叫枚举
 * @author: liurunkai
 * @Date: 2020/2/26 11:58
 */
@Getter
@AllArgsConstructor
public enum IsReCallEnum {

    RE_CALL(1, "重复呼叫"),
    STOP_CALL(2, "停止呼叫"),
    NEXT_DAY_CALL(3, "第二天呼叫");

    private Integer code;
    private String message;
}
