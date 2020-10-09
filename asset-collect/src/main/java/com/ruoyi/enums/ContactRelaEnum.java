package com.ruoyi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @Description: 联系人与客户之间的关系枚举类
 * @author: liurunkai
 * @Date: 2020/1/2 16:49
 */
@Getter
@AllArgsConstructor
public enum ContactRelaEnum {

    UN_MATCH(-1,"unmatch","其它"),
    SELE(1, "self", "本人"),
    LINEAL(2, "lineal", "直系"),
    RELATIVE(3, "relative", "亲戚"),
    FRIEND(4, "friend", "朋友"),
    PARENT(5, "parent", "父母"),
    MATE(6, "mate", "配偶"),
    BROTHER(7, "brother", "兄弟"),
    SISITER(8, "sister", "姐妹"),
    BROTHER_GEGE(9, "brother_gege", "哥哥"),
    BROTHER_XZ(10, "brother_xz", "兄长"),
    BROTHER_DD(11, "brother_dd", "弟弟"),
    SISITER_JJ(12, "sister_jj", "姐姐"),
    SISITER_MM(13, "sister_mm", "妹妹 "),
    FAMILY(14, "family", "家人"),
    HUBBAND(15, "husband", "老公"),
    WIFE(16, "wife", "老婆"),
    COLLEAGUE(17, "colleague", "同事"),
    ORGAN(18, "organ", "单位"),
    CLASS_MATE(19, "class_mage", "同学"),
    FATHER(20, "father", "父亲"),
    MOTHER(21, "mother", "母亲");

    private Integer code;
    private String relation;
    private String des;

    public static Integer getCodeByRela(String desc) {
        ContactRelaEnum contactRelaEnum = Arrays.stream(ContactRelaEnum.values())
                .filter(contact -> contact.getDes().equals(desc))
                .findAny()
                .orElse(ContactRelaEnum.UN_MATCH);
//                .orElseThrow(() -> new RuntimeException(String.format("联系人与客户之间关系类型不匹配，关系是%s", desc)));
        return contactRelaEnum.getCode();
    }
}
