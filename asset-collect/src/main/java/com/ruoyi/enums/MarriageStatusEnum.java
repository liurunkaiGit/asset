package com.ruoyi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 是否属性枚举类
 * @author: liurunkai
 * @Date: 2019/12/26 11:46
 */
@Getter
@AllArgsConstructor
public enum MarriageStatusEnum {
    IS(1, "IS", "是"),
    NO(2, "NO", "否"),
    MARRAGED(3, "MARRAGED", "已婚"),
    NO_MARRAGE(4, "NO_MARRAGE", "未婚"),
    DIVORCE(5, "DIVORCE", "离异"),
    DIVORCE2(6, "DIVORCE2", "离婚"),
    WIDOW(7, "WIDOW", "丧偶");

    private Integer code;
    private String result;
    private String des;

    public static Integer getCodeByDes(String des) {
        for (MarriageStatusEnum marriageStatusEnum : MarriageStatusEnum.values()) {
            if (marriageStatusEnum.getDes().equals(des)) {
                return marriageStatusEnum.getCode();
            }
        }
        return -1;
    }
}
