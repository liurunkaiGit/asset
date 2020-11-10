package com.ruoyi.framework.config;

import com.ruoyi.common.domain.MySessionListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author guozeqi
 * @create 2020-11-02
 */
//@Configuration
public class SessionConfig extends WebMvcConfigurerAdapter {


//   @Bean
   public ServletListenerRegistrationBean  servletListenerRegistrationBean() {
       ServletListenerRegistrationBean slrBean = new ServletListenerRegistrationBean();
       slrBean.setListener(new MySessionListener());
       return slrBean;
   }
}
