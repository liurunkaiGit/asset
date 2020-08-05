package com.ruoyi.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author guozeqi
 * @create 2020-08-06
 */

@Getter
@AllArgsConstructor
public enum LoginStatusEnum{
    on("1","已登录"),
    off("0","未登录");
    private String code;
    private String msg;
}