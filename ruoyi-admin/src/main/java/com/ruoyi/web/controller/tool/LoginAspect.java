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
        String ipAddr = IpUtils.getIpAddr(request);
        String hostAddress = IpUtils.getHostIp();

        Date curTime = new Date();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        Long orgId = sysUser.getOrgId();
        String orgName = sysUser.getOrgName();
        String loginName = sysUser.getLoginName();
        String userName = sysUser.getUserName();
        String intervalTime = null;

        SysLoginStatus uploginInfo = sysLoginStatusService.selectSysLoginStatus(loginName);//此用户当天是否登录
        Date upEndTime = null;
        if(uploginInfo != null){//当天已登录
            SysLoginStatus upNotLogout = sysLoginStatusService.selectNotLogout(loginName);//当天未退出的记录
            if(upNotLogout != null){//当天有未退出的
                //把上次退出
                Integer logoutNum = sysLoginStatusService.selectMaxLogoutCount(upNotLogout.getOrgId(), loginName);//当天最大的退出次数
                upEndTime = curTime;
                this.updateLogout(upNotLogout, upEndTime, logoutNum, loginName, curTime);//把上次退出
            }
        }
        //计算间隔时间
        if(upEndTime != null){//上次有未退出
            intervalTime = String.valueOf( (curTime.getTime() - upEndTime.getTime()) / 1000 );
        }else{//上次没有未退出
            SysLoginStatus lastLogin = sysLoginStatusService.selectLastLogin(String.valueOf(orgId), loginName);
            if(lastLogin != null && lastLogin.getEndTime() != null){
                intervalTime = String.valueOf( (curTime.getTime() - lastLogin.getEndTime().getTime()) / 1000 );
            }
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

    private void updateLogout(SysLoginStatus sysLoginStatus, Date upEndTime, Integer logoutNum, String loginName, Date curTime){
        long startTime = sysLoginStatus.getStartTime().getTime();
        long length = upEndTime.getTime() - startTime;
        logoutNum = logoutNum + 1;

        SysLoginStatus updateParam = new SysLoginStatus();
        updateParam.setEndTime(upEndTime);
        updateParam.setLogoutNum(logoutNum);
        updateParam.setOnlineLen(String.valueOf(length/1000));
        updateParam.setStatus(LoginStatusEnum.off.getCode());
        updateParam.setUpdateBy(loginName);
        updateParam.setUpdateTime(curTime);
        updateParam.setId(sysLoginStatus.getId());
        sysLoginStatusService.updateSysLoginStatus(updateParam);
    }



}
