package com.ruoyi.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @Description: 性别枚举类
 * @author: liurunkai
 * @Date: 2019/12/16 18:14
 */
@Slf4j
@Getter
@AllArgsConstructor
public enum SexEnum {

    UN_MATCH(-1,"未知"),
    MAN(0, "男"),
    WOMAN(1, "女");

    private Integer code;
    private String message;

    // 通过性别获取编号
    public static Integer getCodeByMessage(String sex) {
        return Arrays.stream(SexEnum.values())
                .filter(s -> s.getMessage().equals(sex))
                .findFirst()
                .orElse(SexEnum.UN_MATCH)
//                .orElseThrow(() -> new RuntimeException(String.format("该性别不存在：%s", sex)))
                .getCode();
    }

    // 获取性别
    public static String getSexByCode(Integer code) {
        return Arrays.stream(SexEnum.values())
                .filter(s -> s.getCode().equals(code))
                .findFirst()
                .orElse(SexEnum.UN_MATCH)
//                .orElseThrow(() -> new RuntimeException(String.format("该性别不存在：%s", sex)))
                .getMessage();
    }

}
