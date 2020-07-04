package com.ruoyi.assetspackage.enums;

/**
 * 资产包是否空包
 * @author guozeqi
 * @create 2020-01-09
 */

public enum IsNullPackageEnum {
    /**
     * 空包
     */
    NULL_PACKAGE("0"),
    /**
     * 非空包
     */
    NOT_NULL_PACKAGE("1");

    private String value;

    private IsNullPackageEnum(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
