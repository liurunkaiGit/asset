package com.ruoyi.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/4/24 10:45
 */
@Getter
@AllArgsConstructor
public enum AllocatCaseStartegyEnum {
    ALL_ROBOT("all_robot"),
    ALL_PERSON("all_person"),
    ROBOT_PERSON("robot_person");

    private String code;
}
