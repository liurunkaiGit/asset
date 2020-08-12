package com.ruoyi.web.controller.tool;

import com.ruoyi.common.enums.LoginStatusEnum;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysLoginStatus;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysLoginStatusService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
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
        Date curTime = new Date();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        Long orgId = sysUser.getOrgId();
        String orgName = sysUser.getOrgName();
        String loginName = sysUser.getLoginName();
        String userName = sysUser.getUserName();
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
                .build();
        sysLoginStatusService.insertSysLoginStatus(sysLoginStatus);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().setAttribute("loginStatusId",uuid);
    }

}
