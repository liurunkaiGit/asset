package com.ruoyi.assetspackage.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author guozeqi
 * @create 2019-12-26
 */
@Component
public class ApplicationUtils implements ApplicationContextAware {


    private static ApplicationContext applicationContext = null;


    public static ApplicationContext getApplicationContext() {

        return applicationContext;

    }


    @SuppressWarnings("unchecked")

    public static <T> T getBean(String beanId) {

        return (T) applicationContext.getBean(beanId);

    }


    public static <T> T getBean(Class<T> requiredType) {

        return (T) applicationContext.getBean(requiredType);

    }

    /**
     * Spring容器启动后，会把 applicationContext 给自动注入进来，然后我们把 applicationContext 赋值到静态变量中，方便后续拿到容器
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        ApplicationUtils.applicationContext = applicationContext;

    }






}
