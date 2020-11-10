package com.ruoyi.assetspackage.task;

import com.ruoyi.common.enums.LoginStatusEnum;
import com.ruoyi.common.utils.IpUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.framework.shiro.session.OnlineSession;
import com.ruoyi.framework.shiro.web.session.OnlineWebSessionManager;
import com.ruoyi.system.domain.SysLoginStatus;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysLoginStatusService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author guozeqi
 * @create 2020-11-05
 */
@Slf4j
@Component("logoutTimer")
public class logoutTimer {

    @Autowired
    private ISysLoginStatusService sysLoginStatusService;

    @Autowired
    private ISysConfigService sysConfigService;


    /**
     * 定时补全 异常退出系统的用户的信息
     */
    public void autoLogout()
    {
        OnlineWebSessionManager sessionManager = SpringUtils.getBean(OnlineWebSessionManager.class);
        String hostAddress = null;//服务端ip
        OnlineSession session = null;
        Date curTime = new Date();
        try {
            hostAddress = IpUtils.getHostIp();
            List<SysLoginStatus> sysLoginStatuses = sysLoginStatusService.selectNotLogoutStatus(hostAddress);
            for (SysLoginStatus sysLoginStatus : sysLoginStatuses) {
                //获取session
                try {
                    String sessionId = sysLoginStatus.getSessionId();
                    session = sessionManager.getOnlineSession(new DefaultSessionKey(sessionId));
                } catch (Exception e) {
                }
                //判断session是否失效
                if(session != null){
                    Date lastAccessTime = session.getLastAccessTime();
                    if(calcDate(lastAccessTime,curTime)){
                        //退出系统
                        this.logoutOpt(sysLoginStatus,lastAccessTime,"admin",curTime);
                    }
                }else{
                    //退出系统
                    this.logoutOpt(sysLoginStatus,curTime,"admin",curTime);
                }
            }
        } catch (Exception e) {
           log.error("执行定时退出系统任务异常{}",e);
        }
    }


    private void logoutOpt(SysLoginStatus sysLoginStatus, Date logoutTime, String updateBy, Date updateTime){
        long startTime = sysLoginStatus.getStartTime().getTime();
        long length = logoutTime.getTime() - startTime;
        Integer logoutNum = sysLoginStatusService.selectMaxLogoutCount(sysLoginStatus.getOrgId(), sysLoginStatus.getLoginName());//当天最大的退出次数
        logoutNum = logoutNum + 1;

        SysLoginStatus updateParam = new SysLoginStatus();
        updateParam.setEndTime(logoutTime);
        updateParam.setLogoutNum(logoutNum);
        updateParam.setOnlineLen(String.valueOf(length/1000));
        updateParam.setStatus(LoginStatusEnum.off.getCode());
        updateParam.setUpdateBy(updateBy);
        updateParam.setUpdateTime(updateTime);
        updateParam.setId(sysLoginStatus.getId());
        sysLoginStatusService.updateSysLoginStatus(updateParam);
    }

    /** 计算是否超过15分钟 */
    private boolean calcDate(Date startTime,Date endTime){
        long subTime = endTime.getTime() - startTime.getTime();
        long compareTime = 15 * 60 * 1000;
        if(subTime >= compareTime){
            return true;
        }else{
            return false;
        }
    }


}
