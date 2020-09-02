package com.ruoyi.assetspackage.domain.phoneStatus;

import lombok.Data;

/**
 * @author guozeqi
 * @create 2020-08-28
 */
@Data
public class PhoneStatusRequest {
    private static final long serialVersionUID = 1L;

    private String apiCode;
    private String appKey;
    private String jsonData;
    private String checkCode;
}
