package com.ruoyi.enums;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * @Description: 分配规则枚举类
 * @author: liurunkai
 * @Date: 2020/1/4 18:31
 */
@Getter
@AllArgsConstructor
public enum GrayQueueReasonEnum {

    ERR_TASK(1, "错误案件");

    private Integer code;
    private String message;
}
