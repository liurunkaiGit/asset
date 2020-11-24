package com.ruoyi.robot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 已完成通话状态枚举
 * @author: liurunkai
 * @Date: 2020/2/11 16:41
 */
@Getter
@AllArgsConstructor
public enum FinishedCallStatus {

    ANSWER(0, "已接听", "R01","Search1-联系方式有效"),
    REJECT_ANSWER(1, "拒接", "JJ","Search1-联系方式有效"),
    UNAVAILABLE(2, "无法接通", "R02","Search1-联系方式有效"),
    CALL_NUMBER_NO_USE(3, "主叫号码不可用", "R03", "Search2-联系方式无效"),
    EMPTY_NUMBER(4, "空号", "KH", "Search2-联系方式无效"),
    SHUTDOWN(5, "关机", "GJ","Search1-联系方式有效"),
    BUSY(6, "占线忙音", "ZX","Search1-联系方式有效"),
    HALT(7, "停机", "R04", "Search2-联系方式无效"),
    NOT_ANSWER(8, "无人接听", "WRJT","Search1-联系方式有效"),
    CALL_ARREARS(9, "主叫欠费", "R05", "Search2-联系方式无效"),
    CALL_LOSS(10, "呼损", "R06", "Search2-联系方式无效"),
    BLACK_LIST(11, "黑名单", "R07", "Search2-联系方式无效"),
    LINE_BLIND(22, "线路盲区", "R08", "Search2-联系方式无效");

    private Integer code;
    private String desc;
    private String sign;
    private String actionCode;

    public static String getDescByCode(Integer code) {
        for (FinishedCallStatus finishedCallStatus : FinishedCallStatus.values()) {
            if (finishedCallStatus.getCode().equals(code)) {
                return finishedCallStatus.getDesc();
            }
        }
        return null;
    }

    public static String getSignByCode(Integer code) {
        for (FinishedCallStatus finishedCallStatus : FinishedCallStatus.values()) {
            if (finishedCallStatus.getCode().equals(code)) {
                return finishedCallStatus.getSign();
            }
        }
        return null;
    }

    public static String getActionCodeByCode(Integer code) {
        for (FinishedCallStatus finishedCallStatus : FinishedCallStatus.values()) {
            if (finishedCallStatus.getCode().equals(code)) {
                return finishedCallStatus.getActionCode();
            }
        }
        return null;
    }

    public static String getDescBySign(String sign) {
        for (FinishedCallStatus finishedCallStatus : FinishedCallStatus.values()) {
            if (finishedCallStatus.getSign().equals(sign)) {
                return finishedCallStatus.getDesc();
            }
        }
        return null;
    }

}
