package com.ruoyi.assetspackage.domain.phoneStatus.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author guozeqi
 * @create 2020-08-28
 */
@Getter
@AllArgsConstructor
public enum PhonestatusEnum {
    success1("0","查询成功，无数据"),
    success2("1","查询成功，有数据"),
    error("99","查询失败");

    private String code;
    private String msg;
}
