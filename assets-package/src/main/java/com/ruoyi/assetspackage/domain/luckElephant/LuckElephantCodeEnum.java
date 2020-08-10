package com.ruoyi.assetspackage.domain.luckElephant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author guozeqi
 * @create 2020-08-06
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum LuckElephantCodeEnum {
    success("0000","成功"),
    error("0001","失败");

    private String code;
    private String msg;

}
