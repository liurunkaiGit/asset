package com.ruoyi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2021/1/13 11:23
 */
@Getter
@AllArgsConstructor
public enum StationLetterEnum {
    HAVA_READ(1, "已读"),
    WAIT_READ(2, "未读"),
    HAVE_REPLY(-1, "已回复");

    private Integer status;
    private String message;
}
