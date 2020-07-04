package com.ruoyi.robot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 任务类型枚举
 * @author: liurunkai
 * @Date: 2020/2/11 16:41
 */
@Getter
@AllArgsConstructor
public enum TaskType {

    SCHEDULE(1, "定时启动任务"),
    MANUAL(2, "手动启动任务");

    private Integer code;
    private String desc;
}
