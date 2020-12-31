package com.ruoyi.task.domain.preTestCall.taskResult;

import com.ruoyi.task.domain.preTestCall.createTask.PreTestCallStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author guozeqi
 * @create 2020-12-25
 */

@Getter
@AllArgsConstructor
public enum PreTestCallResultEnum {
    CG("SUCCESS", "成功"),
    SB("FAIL", "无人接听"),
    ZM("USER_BUSY", "正忙"),
    YHGJ("POWER_OFF", "用户关机"),
    YHTJ("SUSPENDED", "用户停机"),
    YHKH("NOT_EXIST", "用户空号"),
    HMD("BLACK", "黑名单"),
    JTXP("CALL_LIMIT", "接通限频"),
    MRSX("EVERYDAY_LIMIT", "每日上限"),
    HMCW("ERROR_PHONE", "号码错误"),
    QT("OTHER", "其他");

    private String code;
    private String name;

    public static String getName(String code) {
        PreTestCallResultEnum result = Arrays.stream(PreTestCallResultEnum.values())
                .filter(obj -> obj.getCode().equals(code))
                .findAny()
                .orElseThrow(() -> new RuntimeException(String.format("值错误(是/否)，值是%s", code)));
        return result.getName();
    }
}
