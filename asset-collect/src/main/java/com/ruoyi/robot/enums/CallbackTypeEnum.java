package com.ruoyi.robot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 百融机器人回调类型枚举
 * @author: liurunkai
 * @Date: 2020/5/7 14:43
 */
@Getter
@AllArgsConstructor
public enum CallbackTypeEnum {

    OUT_CALL(0, "呼出结果回调"),
    IN_CALL(1, "呼入结果回调");

    private Integer code;
    private String message;
}
