package com.ruoyi.robot.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/2/12 17:47
 */
@Data
public class RobotPhone {
    private Integer userPhoneId;
    private String callerAccountId;
    private String phone;
    private String account;
    private String phoneName;
    private Integer phoneType;
    private Boolean available;
    private Boolean useAvailable;
    private Integer totalConcurrencyQuota;
    private Integer usedConcurrencyQuota;
    private Integer billPeriod;
    private Date validityBegin;
    private Date validityEnd;
    private Integer sceneType;
    private String rateType;
    private String billMode;
    private String nonlocalSellingRate;
    private String localSellingRate;
    private String lineAmount;
    private String useRange;
    private String weightType;
    private String sortedActualVoipData;
}
