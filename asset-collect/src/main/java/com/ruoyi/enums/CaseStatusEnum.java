package com.ruoyi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2019/12/26 11:51
 */
@Getter
@AllArgsConstructor
public enum CaseStatusEnum {
    OPEN(1, "OPEN", "开启"),
    CLOSE(2, "CLOSE", "关闭");

    private Integer code;
    private String status;
    private String des;
}
