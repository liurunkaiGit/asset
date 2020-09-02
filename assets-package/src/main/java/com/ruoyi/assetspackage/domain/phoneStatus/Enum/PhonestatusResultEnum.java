package com.ruoyi.assetspackage.domain.phoneStatus.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author guozeqi
 * @create 2020-08-28
 */
@Getter
@AllArgsConstructor
public enum PhonestatusResultEnum {
    /**
     *
     0 -> 正在识别
     1 -> 待接听铃声
     2 -> 彩铃
     4 -> 空号
     5 -> 停机
     6 -> 暂停服务
     10 -> 大概率彩铃（90%）
     11 -> 识别失败（疑似小号或携号转网）
     12 -> 识别失败
     21 -> 包含广告语，运营商铃声广告
     22 -> 音乐
     31 -> 关机
     32 -> 通话中
     33 -> 无法接通，无法接听，不在服务区
     34 -> 呼入限制
     35 -> 已设置来电提醒
     36 -> 呼叫转移
     91-> 识别失败
     92-> 识别失败
     */
    ZZSB("0","正在识别"),
    DJTLS("1","待接听铃声"),
    CL("2","彩铃"),
    KH("4","空号"),
    TJ("5","停机"),
    ZTFW("6","暂停服务"),
    DGLCL("10","大概率彩铃（90%）"),
    SBSB1("11","识别失败（疑似小号或携号转网）"),
    SBSB2("12","识别失败"),
    BHGGY("21","包含广告语，运营商铃声广告"),
    YY("22","音乐"),
    GJ("31","关机"),
    THZ("32","通话中"),
    WFJT("33","无法接通，无法接听，不在服务区"),
    HRXZ("34","呼入限制"),
    YSZLDTX("35","已设置来电提醒"),
    HJZY("36","呼叫转移"),
    SBSB3("91","识别失败"),
    SBSB4("92","识别失败");

    private String code;
    private String msg;
}
