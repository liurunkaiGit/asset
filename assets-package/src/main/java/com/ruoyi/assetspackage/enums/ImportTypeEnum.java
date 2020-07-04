package com.ruoyi.assetspackage.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/3/24 10:58
 */
@Getter
@AllArgsConstructor
public enum ImportTypeEnum {

    ASSET_TEMPLETE(1, "资产模板"),
    REPAYMENT_TEMPLETE(2, "还款模板"),
    RECORD_TEMPLETE(3, "催收记录模板"),
    UPDATE_TEMPLETE(4,"更新模板");

    private Integer code;
    private String message;
}
