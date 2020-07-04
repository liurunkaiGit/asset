package com.ruoyi.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description: 本地缓存配置，从application.yml中读取
 * @author: liurunkai
 * @Date: 2020/2/25 15:11
 */
@Data
@Component
public class LocalCacheConfig {

    @Value("${google.local.cache.concurrencyLevel:10}")
    private Integer concurrencyLevel;
    @Value("${google.local.cache.maxSize:100}")
    private Integer maxSize;
    @Value("${google.local.cache.expireAfterWrite:30}")
    private Integer expireAfterWrite;
}
