package com.ruoyi.robot.model;


import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 向任务中导入客户实体类
 */
@Data
public class ImportTaskCustomerVO {
    //公司id
    private Integer companyId;

    //任务id
    private Integer TaskId;

    //是否强制获取客户
    Integer forceTransferCustomer;

    //是否加密
    boolean encryptionPhone;

    //是否是https
    Integer isHttps;

    //导入的客户信息
    private List<CustomerInfoExtVO> customerInfoList;

    // 话术变量
    private Map<String,Object> properties;

}
