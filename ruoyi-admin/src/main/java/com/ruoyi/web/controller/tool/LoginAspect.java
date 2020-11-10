package com.ruoyi.web.controller.tool;

import com.ruoyi.common.enums.LoginStatusEnum;
import com.ruoyi.common.utils.IpUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.framework.shiro.session.OnlineSession;
import com.ruoyi.framework.shiro.web.session.OnlineWebSessionManager;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysLoginStatus;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysLoginStatusService;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.UUID;

/**
 * @author guozeqi
 * @create 2020-05-20
 */

@Aspect
@Component
public class LoginAspect {
    @Autowired
    private ISysDeptService deptService;
    @Autowired
    private ISysLoginStatusService sysLoginStatusService;

    private static final Logger log = LoggerFactory.getLogger(LoginAspect.class);


    @Pointcut("execution(* com.ruoyi.web.controller.system.SysLoginController.ajaxLogin(..)) && args(username,password,rememberMe,orgId,platform)")
    public void loginPointCut(String username, String password, Boolean rememberMe,Long orgId, String platform)
    {

    }

    @After("loginPointCut(username,password,rememberMe,orgId,platform)")
    public void doAfter(String username, String password, Boolean rememberMe,Long orgId, String platform) throws Exception
    {
        doHandler(orgId,platform);
    }

    private void doHandler(Long orgId,String platform)throws Exception{
        SysDept sysDept = deptService.selectDeptById(orgId);
        SysUser sysUser = ShiroUtils.getSysUser();
        if(sysUser==null){
            return;
        }
        sysUser.setOrgId(orgId);
        sysUser.setOrgName(sysDept.getDeptName());
        sysUser.setPlatform(platform);
        ShiroUtils.setSysUser(sysUser);
        loginStatus(sysUser);
    }

    private void loginStatus(SysUser sysUser){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String sessionId = request.getRequestedSessionId();
//        String ipAddr = IpUtils.getIpAddr(request);
        String ipAddr = this.getClientIp();
        String hostAddress = IpUtils.getHostIp();

        Date curTime = new Date();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        Long orgId = sysUser.getOrgId();
        String orgName = sysUser.getOrgName();
        String loginName = sysUser.getLoginName();
        String userName = sysUser.getUserName();
        String intervalTime = null;

        SysLoginStatus uploginInfo = sysLoginStatusService.selectSysLoginStatus(String.valueOf(orgId), loginName);//此用户当天是否登录
        Date upEndTime = null;
        if(uploginInfo != null){
            upEndTime = uploginInfo.getEndTime();
            if(upEndTime == null){//上次没有退出 -> 把上次退出
                Integer logoutNum = sysLoginStatusService.selectMaxLogoutCount(String.valueOf(orgId), loginName);//当天最大的退出次数
                OnlineWebSessionManager bean = SpringUtils.getBean(OnlineWebSessionManager.class);
                try {
                    OnlineSession session = bean.getOnlineSession(new DefaultSessionKey(uploginInfo.getSessionId()));
                    upEndTime = session.getLastAccessTime();
                } catch (Exception e) {
                    //服务器宕机或关闭 使会话失效 上次退出时间取当前登录时间
                    upEndTime = curTime;
                }
                this.updateLogout(uploginInfo,upEndTime,logoutNum,loginName,curTime);//把上次退出
            }
        }
        //不是当天第一次登录的 计算间隔时间
        if(upEndTime != null){
            intervalTime = String.valueOf( (curTime.getTime() - upEndTime.getTime()) / 1000 );
        }
        SysLoginStatus sysLoginStatus = SysLoginStatus.builder()
                .id(uuid)
                .orgId(String.valueOf(orgId))
                .orgName(orgName)
                .loginName(loginName)
                .userName(userName)
                .startTime(curTime)
                .status(LoginStatusEnum.on.getCode())
                .createBy(loginName)
                .createTime(curTime)
                .sessionId(sessionId)
                .ipAddr(ipAddr)
                .hostAddr(hostAddress)
                .intervalTime(intervalTime)
                .build();
        sysLoginStatusService.insertSysLoginStatus(sysLoginStatus);
        request.getSession().setAttribute("loginStatusId",uuid);
    }

    private void updateLogout(SysLoginStatus sysLoginStatus, Date lastAccessTime, Integer logoutNum, String loginName, Date curTime){
        long startTime = sysLoginStatus.getStartTime().getTime();
        long length = lastAccessTime.getTime() - startTime;
        logoutNum = logoutNum + 1;

        SysLoginStatus updateParam = new SysLoginStatus();
        updateParam.setEndTime(lastAccessTime);
        updateParam.setLogoutNum(logoutNum);
        updateParam.setOnlineLen(String.valueOf(length/1000));
        updateParam.setStatus(LoginStatusEnum.off.getCode());
        updateParam.setUpdateBy(loginName);
        updateParam.setUpdateTime(curTime);
        updateParam.setId(sysLoginStatus.getId());
        sysLoginStatusService.updateSysLoginStatus(updateParam);
    }


    private static String getClientIp() {
        String hostAddress = "127.0.0.1";
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            hostAddress = localHost.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return hostAddress;
    }

}
