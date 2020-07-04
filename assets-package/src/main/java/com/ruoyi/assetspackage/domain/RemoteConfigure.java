package com.ruoyi.assetspackage.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


/**
 * @author guozeqi
 * @create 2019-12-26
 */
@Data
@Component
@PropertySource("classpath:configure/remoteConfigure.properties")
public class RemoteConfigure {

    @Value("${assetsInterfaceUrl}")
    private String assetsInterfaceUrl; //导入资产推送接口

    @Value("${assetsUpdateInterfaceUrl}")
    private String assetsUpdateInterfaceUrl; //导入更新资产推送接口

    @Value("${repaymentInterfaceUrl}")
    private String repaymentInterfaceUrl; //还款结案信息推送接口

    @Value("${telphoneProxyUrl}")
    private String telphoneProxyUrl; //话务平台获取代理地址

    @Value("${telphoneRecordUrl}")
    private String telphoneRecordUrl; //话务平台获取录音地址

    @Value("${recordUrl}")
    private String recordUrl; //录音播放地址

}
