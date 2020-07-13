package com.ruoyi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/7/13 11:32
 */
@Getter
@AllArgsConstructor
public enum SendRobotApplyTaskStatusEnum {

    WAIT_APPROVAL(0, "待审批"),
    APPROVAL_PASS(1, "审批通过"),
    APPROVAL_REJECT(2, "审批拒绝");

    private Integer code;
    private String message;
}
