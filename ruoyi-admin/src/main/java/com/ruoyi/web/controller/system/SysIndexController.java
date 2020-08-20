package com.ruoyi.web.controller.system;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.agent.domain.ExtPhone;
import com.ruoyi.agent.service.IExtPhoneService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysMenu;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysMenuService;

/**
 * 首页 业务处理
 * 
 * @author ruoyi
 */
@Controller
public class SysIndexController extends BaseController
{
    @Autowired
    private ISysMenuService menuService;
    @Autowired
    private IExtPhoneService extPhoneService;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        SysUser user = ShiroUtils.getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("copyrightYear", Global.getCopyrightYear());
        mmap.put("demoEnabled", Global.isDemoEnabled());

        ExtPhone extPhone = new ExtPhone();
        extPhone.setIsused("0");
        extPhone.setSeatId(new Long(ShiroUtils.getSysUser().getUserId()).intValue());
        extPhone.setCallPlatform(ShiroUtils.getSysUser().getPlatform());
        logger.info("查询分机号码开始");
        List<ExtPhone> list = extPhoneService.selectExtPhoneList(extPhone);
        List<String> extNumList = new ArrayList<>();
        logger.info("查询分机号码结束");
        if (list != null && list.size() > 0) {
            // 分机号码
            mmap.put("extPhone", list.get(0));
            // 查询外显号码
            logger.info("查询外显号码开始");
            extNumList = this.extPhoneService.selectExtNumBySeat(String.valueOf(ShiroUtils.getSysUser().getUserId()), list.get(0).getAgentid(), ShiroUtils.getSysUser().getPlatform());
            logger.info("查询外显号码结束");
            mmap.put("extNumList", StringUtils.join(extNumList,","));
        } else {
            mmap.put("extPhone", extPhone);
            mmap.put("extNumList",StringUtils.join(extNumList,","));
        }
        mmap.put("callPlatform", ShiroUtils.getSysUser().getPlatform());
        return "index";
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin(ModelMap mmap)
    {
        return "skin";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", Global.getVersion());
        return "main";
    }
}
