package com.ruoyi.robot.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/5/7 15:55
 */
@Data
public class CallbackCallDetail {
    /**
     * 客户手机号
     */
    private String customerTelephone;
    /**
     * 通话记录id
     */
    private Integer callInstanceId;
    /**
     * 任务id
     */
    private Integer callJobId;
    /**
     * 拨打时间
     */
    private Date callerTime;
}
