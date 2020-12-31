package com.ruoyi.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "duyan")
@Data
public class DuYanConfig {
    /** 度言 账号 apikey */
    private  String apikey;
    /** 度言 获取登录token地址 */
    private  String tokenUrl;
    /** 度言 获取通话详情地址 */
    private  String logUrl;
    /** 度言 录音地址 */
    private  String soundRecordingUrl;
    /** 预测式url */
    private  String createUrl;
    private  String statusUrl;
    private  String selectBaseUrl;
    private  String selectResultUrl;
    private  String deleteUrl;
    private  String selectSingleRecordUrl;


}
