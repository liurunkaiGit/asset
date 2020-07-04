package com.ruoyi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 催收行动码枚举类
 * @author: liurunkai
 * @Date: 2020/1/3 18:58
 */
@Getter
@AllArgsConstructor
public enum BusinessSceneEnum {

    NEW_DUNCASE(1, "新案");
    /*HELP_COLL(2, "协催"),
    OLD_DUNCASE(3, "旧案");*/

    private Integer code;
    private String message;
}
