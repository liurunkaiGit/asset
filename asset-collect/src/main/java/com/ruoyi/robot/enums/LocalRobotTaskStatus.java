package com.ruoyi.robot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/4/15 11:57
 */
@Getter
@AllArgsConstructor
public enum LocalRobotTaskStatus {
    RUNNING(1, "外呼中"),
    FINISHED(2, "已完成"),
    USER_PAUSE(4, "暂停"),
    STOP(6, "停止"),
    PULL_BACK(50, "拉回");

    private Integer code;
    private String desc;
}
