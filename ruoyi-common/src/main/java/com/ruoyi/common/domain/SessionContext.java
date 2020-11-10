package com.ruoyi.common.domain;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guozeqi
 * @create 2020-11-02
 */
public class SessionContext {
    private static SessionContext instance;
    private Map<String, HttpSession> sessionMap;

    private SessionContext() {
        sessionMap = new HashMap<String, HttpSession>();
    }

    public static SessionContext getInstance() {
        if (instance == null) {
            instance = new SessionContext();
        }
        return instance;
    }

    public synchronized void addSession(HttpSession session) {
        if (session != null) {
            sessionMap.put(session.getId(), session);
        }
    }

    public synchronized void delSession(HttpSession session) {
        if (session != null) {
            sessionMap.remove(session.getId());
        }
    }

    public synchronized HttpSession getSession(String sessionId) {
        if (sessionId == null)
            return null;
        return sessionMap.get(sessionId);
    }
}
