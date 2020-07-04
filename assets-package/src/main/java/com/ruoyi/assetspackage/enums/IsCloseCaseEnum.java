package com.ruoyi.assetspackage.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**是否结案
 * @author guozeqi
 * @create 2020-01-16
 */
@Getter
@AllArgsConstructor
public enum IsCloseCaseEnum {

    /**
     * 未结案
     */
    NOT_CLOSE_CASE("0"),

    /**
     * 已结案
     */
    CLOSE_CASE("1");

    private String value;
}
