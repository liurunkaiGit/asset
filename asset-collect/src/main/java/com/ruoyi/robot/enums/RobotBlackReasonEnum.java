package com.ruoyi.robot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/6/24 11:04
 */
@Getter
@AllArgsConstructor
public enum RobotBlackReasonEnum {

    STOP_APPLY("停催"),
    STOP_CALL("停拨"),
    COMPLAINT("投诉"),
    PAYED("已还款");

    private String reason;
}
