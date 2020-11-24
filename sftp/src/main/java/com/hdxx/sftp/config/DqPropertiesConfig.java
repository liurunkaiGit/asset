package com.hdxx.sftp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/11/20 14:24
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "dq.uploadsftp")
public class DqPropertiesConfig {

    private String host;
    private Integer port;
    private String username;
    private String password;
    private String uploadPath;
    private String orgId;
    private String callRadioPath;

}
