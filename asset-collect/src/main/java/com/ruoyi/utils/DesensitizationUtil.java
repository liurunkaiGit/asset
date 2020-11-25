package com.ruoyi.utils;

import com.ruoyi.assetspackage.domain.OrgPackage;
import com.ruoyi.assetspackage.service.IOrgPackageService;
import com.ruoyi.assetspackage.service.ISysIpConfigService;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysLoginStatus;
import com.ruoyi.system.mapper.SysLoginStatusMapper;
import com.ruoyi.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author guozeqi
 * @create 2020-11-16
 */

@Component
public class DesensitizationUtil {
    
    @Autowired
    private IOrgPackageService orgPackageService;

    @Autowired
    private ISysConfigService sysConfigService;

    @Autowired
    private ISysIpConfigService sysIpConfigService;

    @Autowired
    private SysLoginStatusMapper sysLoginStatusMapper;

    private static final String CONFIG_KEY = "notDesensitizationUser";


    /**
     * 判断是否脱敏
     * @param orgId
     * @param loginName
     * @return (true:脱敏,false:不脱敏)
     */
    public boolean isDesensitization(String orgId,String loginName){
        OrgPackage orgPackage = orgPackageService.selectOrgPackageByOrgId(orgId);
        if(orgPackage != null){
            String isDesensitization = orgPackage.getIsDesensitization();
            if("1".equals(isDesensitization)){//全部脱敏
                //判断是否配置此用户
                if(this.isContainsUser(loginName)){
                    return false;
                }
                return true;
            }else if("2".equals(isDesensitization)){//仅职场脱敏
                //判断此用户当前是否在职场
                if(this.isWorkplace(loginName)){
                    //判断是否配置此用户
                    if(this.isContainsUser(loginName)){
                        return false;
                    }
                    return true;
                }else{
                    return false;
                }
            }else if("3".equals(isDesensitization)){//职场外脱敏
                //判断此用户当前是否不在职场
                if(!this.isWorkplace(loginName)){
                    //判断是否配置此用户
                    if(this.isContainsUser(loginName)){
                        return false;
                    }
                    return true;
                }else{
                    return false;
                }
            }else{
                //不脱敏
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     * 配置数据是否包含此用户
     * @param loginName
     * @return (true:包含,false:不包含)
     */
    private boolean isContainsUser(String loginName){
        String users = sysConfigService.selectConfigByKey(CONFIG_KEY);
        if(users != null && !"".equals(users)){
            String[] userArr = users.split(",");
            for (String user : userArr) {
                if(loginName.equals(user)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 当前用户最后一次登录是否在职场
     * @param loginName
     * @return (true:在职场,false:不在职场)
     */
    private boolean isWorkplace(String loginName){
        List<String> ipList = sysIpConfigService.selectPartIpList();
        SysLoginStatus sysLoginStatuse = sysLoginStatusMapper.selIpByLoginName(loginName).get(0);
        String ipAddr = sysLoginStatuse.getIpAddr();

        String [] arr = new String[3];
        String[] split = ipAddr.split("\\.");
        arr[0]=split[0];
        arr[1]=split[1];
        arr[2]=split[2];
        String ipAddrPart = StringUtils.join(arr, ".");//最后一次登录的ip段
        for (String confIp : ipList) {
            if(confIp.equals(ipAddrPart)){
                return true;
            }
        }
        return false;
    }




}
