package com.ruoyi.robot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 通话实例状态枚举
 * @author: liurunkai
 * @Date: 2020/2/11 16:41
 */
@Getter
@AllArgsConstructor
public enum CallInstStatus {

    NOT_START(0, "未开始"),
    RUNNING(1, "进行中"),
    FINISHED(2, "已完成");

    private Integer code;
    private String desc;
}
