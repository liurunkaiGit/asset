package com.ruoyi.assetspackage.domain.phoneStatus;

import lombok.Data;

/**
 * @author guozeqi
 * @create 2020-08-28
 */
@Data
public class PhoneStatusRequestData {
    private static final long serialVersionUID = 1L;

    private String cell;//手机号
    private String name;//姓名
    private String id;//身份证

}
