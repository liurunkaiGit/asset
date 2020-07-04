package com.ruoyi.assetspackage.enums;

/**
 * 是否打包
 * @author guozeqi
 * @create 2020-01-09
 */

public enum PackageFlagEnum {
    /**
     * 未打包
     */
    NOT_PACKAGE("0"),
    /**
     * 已打包
     */
    IS_PACKAGE("1");

    private String value;

    private PackageFlagEnum(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

}
