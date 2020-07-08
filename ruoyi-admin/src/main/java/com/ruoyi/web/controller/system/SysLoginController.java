package com.ruoyi.web.controller.system;

import com.alibaba.fastjson.JSON;
import com.ruoyi.agent.domain.ExtPhone;
import com.ruoyi.agent.service.IExtPhoneService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.DataPermissionUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@Controller
public class SysLoginController extends BaseController
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private DataPermissionUtils dataPermissionUtil;

    @Autowired
    private ISysDeptService deptService;
    @Autowired
    private IExtPhoneService extPhoneService;

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response)
    {
        logger.info("================进入登录页面开始============================="+new Date());
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request))
        {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }

        logger.info("================进入登录页面结束============================="+new Date());
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe,Long orgId, String platform)
    {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String sessionId = request.getRequestedSessionId();
            logger.info("================开始登录============================="+new Date()+"=====sessionId="+sessionId);
            if(orgId == null || "".equals(orgId)){
                SysUser user = new SysUser();
                user.setLoginName(username);
                SysUser sysUser = userService.selectUserList(user).get(0);
                orgId = sysUser.getDeptId();
            }
            subject.login(token);
            logger.info("================登录成功============================="+new Date());
            return success();
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    @GetMapping("/unauth")
    public String unauth()
    {
        return "error/unauth";
    }

    @PostMapping("/findPermission")
    @ResponseBody
    public AjaxResult findPermission(String username){
        Long deptId = null;
        List<SysDept> list = new ArrayList<SysDept>();
        try {
            //根据用户账号查询用户信息
            SysUser user = new SysUser();
            user.setLoginName(username);
            SysUser sysUser = userService.selectUserList(user).get(0);
            deptId = sysUser.getDeptId();
            //根据用户查询拥有的部门权限
            Set<Long> deptIds = dataPermissionUtil.selectDataPerNew(sysUser);
            if(deptIds != null && !deptIds.contains(deptId)){
                deptIds.add(deptId);
            }
            SysDept deptParam = new SysDept();
            deptParam.setDeptIds(deptIds);
            List<SysDept> sysDepts = deptService.selectDeptList(deptParam);
            for (SysDept sysDept : sysDepts) {
                if(!"MVP Group".equals(sysDept.getDeptName()) && sysDept.getDeptId() !=100){
                    if(deptId.equals(sysDept.getDeptId())){
                        list.add(0,sysDept);
                    }else{
                        list.add(sysDept);
                    }
                }
            }
            String result = JSON.toJSONString(list);
            return AjaxResult.success("成功",result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取权限列表失败{}",e);
            return error("失败");
        }
    }

    @PostMapping("/findPlatform")
    @ResponseBody
    public AjaxResult findPlatform(String username){
        try {
            List<Map<String, Object>> list = this.extPhoneService.selectExtPhoneListByUsername(username);
            String result = JSON.toJSONString(list);
            return AjaxResult.success("成功",result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取用户配置的话务平台失败{}",e);
            return error("获取用户配置的话务平台失败");
        }
    }

}
