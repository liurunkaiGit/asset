package com.ruoyi.web.controller.tool;

import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysDeptService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author guozeqi
 * @create 2020-05-20
 */

@Aspect
@Component
public class LoginAspect {
    @Autowired
    private ISysDeptService deptService;

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
    }

}
