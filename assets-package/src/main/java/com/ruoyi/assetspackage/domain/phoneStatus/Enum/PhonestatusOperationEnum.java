package com.ruoyi.assetspackage.domain.phoneStatus.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author guozeqi
 * @create 2020-08-28
 */
@Getter
@AllArgsConstructor
public enum PhonestatusOperationEnum {
    DX("1","电信"),
    LT("2","电信"),
    YD("3","电信"),
    QT("4","电信");
    private String code;
    private String msg;
}
