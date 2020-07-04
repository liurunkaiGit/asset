package com.ruoyi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2019/12/31 14:31
 */
@Getter
@AllArgsConstructor
public enum CertificateTypeEnum {
    CARD(1, "身份证"),
    PASSPORT(2, "护照"),
    OFFICIAL_CARD(3, "军官证"),
    MAINLAND_PASS(4, "港澳台居民往来大陆通行证"),
    HOUSEHOLD_REGISTER(5, "户口簿");
    private Integer code;
    private String certificateType;

    public static Integer getCodeByType(String type) {
        CertificateTypeEnum certificateTypeEnum = Arrays.stream(CertificateTypeEnum.values())
                .filter(ct -> ct.getCertificateType().equals(type))
                .findAny()
                .orElse(CertificateTypeEnum.CARD);
//                .orElseThrow(() -> new RuntimeException(String.format("证件类型不存在，证件类型是%s", type)));
        return certificateTypeEnum.getCode();
    }
}
