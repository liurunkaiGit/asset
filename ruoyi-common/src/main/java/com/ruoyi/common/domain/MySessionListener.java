package com.ruoyi.common.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author guozeqi
 * @create 2020-11-02
 */
public class MySessionListener implements HttpSessionListener  {
    Logger logger = LoggerFactory.getLogger(MySessionListener.class);

    private SessionContext context = SessionContext.getInstance();

    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        logger.info("===================创建 session 成功======================");
        context.addSession(sessionEvent.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        logger.info("===================销毁 session 成功======================");
        HttpSession session = sessionEvent.getSession();
        context.delSession(session);
    }
}
