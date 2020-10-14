package com.ruoyi.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 客户联系人数据来源
 * @author: liurunkai
 * @Date: 2019/12/26 14:44
 */
@Getter
@AllArgsConstructor
public enum ContactOriginEnum {

    ASSET_IMPORT(0, "资产导入"),
    PAGE_ADD(1, "页面添加");

    private Integer code;
    private String type;
}
