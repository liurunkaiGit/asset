package com.ruoyi.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 任务状态枚举类
 * @author: liurunkai
 * @Date: 2019/12/27 16:09
 */
@Getter
@AllArgsConstructor
public enum TaskStatusEnum {
    NO_ALLOCAT(1, "noAllocat", "未分配"),
    ALLOCATING(2, "allocating", "已分配"),
    CLOSE(3, "close", "已结案"),
    NO_ALLOCAT_RECYCLE(4, "noAllocatRecycle", "未分配-回收");

    private Integer status;
    private String message;
    private String des;
}
