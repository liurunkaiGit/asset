package com.ruoyi.assetspackage.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否封包
 * @author guozeqi
 * @create 2020-01-09
 */
@Getter
@AllArgsConstructor
public enum IsClosePackageEnum {

    /**
     * 空包
     */
    NOT_CLOSE_PACKAGE("0"),
    /**
     * 已封包
     */
    CLOSE_PACKAGE("1"),
    /**
     * 已分发
     */
    SEND_PACKAGE("2"),

    /**
     * 已拉回
     */
    PULL_BACK("3");

    private String value;
}
