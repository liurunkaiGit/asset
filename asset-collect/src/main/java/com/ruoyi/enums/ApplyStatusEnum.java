package com.ruoyi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 审批状态枚举类
 * @author: liurunkai
 * @Date: 2020/8/17 16:29
 */
@Getter
@AllArgsConstructor
public enum  ApplyStatusEnum {

    WAIT_APPLY(1,"待审批"),
    APPLY_AGREE(2,"审批通过"),
    APPLY_REFUSE(3,"审批拒绝");

    private Integer code;
    private String message;
}
