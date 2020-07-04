package com.ruoyi.assetspackage.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @Description: 是否属性枚举类
 * @author: liurunkai
 * @Date: 2019/12/26 11:46
 */
@Getter
@AllArgsConstructor
public enum IsNoEnum {
    IS(1, "IS", "是"),
    NO(2, "NO", "否");

    private Integer code;
    private String result;
    private String des;

    public static Integer getCodeByDes(String des) {
        IsNoEnum isNoEnum = Arrays.stream(IsNoEnum.values())
                .filter(isNo -> isNo.getDes().equals(des))
                .findAny()
                .orElseThrow(() -> new RuntimeException(String.format("值错误(是/否)，值是%s", des)));
        return isNoEnum.getCode();
    }
}
