package com.ruoyi.common.utils;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: restTemplate接口调用工具类
 * @author: liurunkai
 * @Date: 2020/1/10 17:51
 */
@Data
@Component
@Configuration
public class RestTemplateUtil {

    private static final Integer connectTimeout = 180000;
    private static final Integer readTimeout = 180000;

    private static RestTemplate restTemplate;

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        // 设置超时时间
        simpleClientHttpRequestFactory.setConnectTimeout(connectTimeout);
        simpleClientHttpRequestFactory.setReadTimeout(readTimeout);
        RestTemplateUtil.restTemplate = new RestTemplate(simpleClientHttpRequestFactory);
        return RestTemplateUtil.restTemplate;
    }
}
