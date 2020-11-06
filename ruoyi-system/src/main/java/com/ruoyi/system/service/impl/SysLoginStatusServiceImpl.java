package com.ruoyi.system.service.impl;

import com.ruoyi.common.enums.LoginStatusEnum;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysLoginStatus;
import com.ruoyi.system.mapper.SysLoginStatusMapper;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysLoginStatusService;
import org.apache.poi.xdgf.geom.Dimension2dDouble;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



/**
 * 登录状态Service业务层处理
 * 
 * @author guozeqi
 * @date 2020-08-06
 */
@Service
public class SysLoginStatusServiceImpl implements ISysLoginStatusService
{
    @Autowired
    private SysLoginStatusMapper sysLoginStatusMapper;

    /**
     * 查询登录状态
     * 
     * @param id 登录状态ID
     * @return 登录状态
     */
    @Override
    public SysLoginStatus selectSysLoginStatusById(String id)
    {
        return sysLoginStatusMapper.selectSysLoginStatusById(id);
    }

    /**
     * 查询登录状态列表
     * 
     * @param sysLoginStatus 登录状态
     * @return 登录状态
     */
    @Override
    public List<SysLoginStatus> selectSysLoginStatusList(SysLoginStatus sysLoginStatus,List<String> ipList)
    {
        List<SysLoginStatus> list = sysLoginStatusMapper.selectSysLoginStatusList(sysLoginStatus);
        Date curDate = new Date();
        for (SysLoginStatus loginStatus : list) {
            String status = loginStatus.getStatus();
            String onlineLen = loginStatus.getOnlineLen();
            Date censusDate = loginStatus.getCensusDate();
            String intervalTime = loginStatus.getIntervalTime();

            //1.处理用户的在线时长
            if(onlineLen != null && !"".equals(onlineLen)){//不是第一次登录
                if(LoginStatusEnum.on.getCode().equals(status)){
                    Date lastStartTime = loginStatus.getLastStartTime();//最后一次登录时间
                    long lastLen =  (curDate.getTime() - lastStartTime.getTime()) / 1000;
                    loginStatus.setOnlineLen(this.calcLength(onlineLen,lastLen));
                }else{
                    loginStatus.setOnlineLen(this.calcLength2(onlineLen));
                }
            }else {//第一次登录
                if(LoginStatusEnum.on.getCode().equals(status)){
                    Date lastStartTime = loginStatus.getLastStartTime();//最后一次登录时间
                    long lastLen =  (curDate.getTime() - lastStartTime.getTime()) / 1000;
                    loginStatus.setOnlineLen(this.calcLength2(String.valueOf(lastLen)));
                }
            }
            //2.处理岗位状态(ip地址)
            String postStatus = null;
            String ipAddr = loginStatus.getIpAddr();//最后一次登录的ip
            String [] arr = new String[3];
            String[] split = ipAddr.split("\\.");
            arr[0]=split[0];
            arr[1]=split[1];
            arr[2]=split[2];
            String ipAddrPart = StringUtils.join(arr, ".");//最后一次登录的ip段
            for (String confIp : ipList) {
                if(confIp.equals(ipAddrPart)){
                    postStatus = "职场";
                }else{
                    postStatus = "居家";
                }
            }
            loginStatus.setPostStatus(postStatus);
            //3.处理不是当天的登录状态
            if(!this.compareDate(curDate,censusDate)){
                loginStatus.setStatus(null);
            }
            //4.间隔时间处理
            loginStatus.setIntervalTime(this.calcLength2(intervalTime));
        }
        return list;
    }


    //两个时间段相加再转换为分钟数
    private String calcLength(String onlineLen,long lastLen){
        BigDecimal len1 = new BigDecimal(onlineLen);
        BigDecimal len2 = new BigDecimal(lastLen);
        BigDecimal divide = len1.add(len2).divide(new BigDecimal(60), 2, RoundingMode.HALF_UP);
        return divide.toString();
    }

    //转换为分钟数
    private String calcLength2(String onlineLen){
        BigDecimal len = new BigDecimal(onlineLen);
        BigDecimal divide = len.divide(new BigDecimal(60), 2, RoundingMode.HALF_UP);
        return divide.toString();
    }

    //比较两个日期是否相等
    private boolean compareDate(Date curDate, Date censusDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String curDateStr = sdf.format(curDate);
        String censusDateStr = sdf.format(censusDate);
        if(curDateStr.equals(censusDateStr)){
            return true;
        }else {
            return false;
        }
    }



    /**
     * 新增登录状态
     * 
     * @param sysLoginStatus 登录状态
     * @return 结果
     */
    @Override
    public int insertSysLoginStatus(SysLoginStatus sysLoginStatus)
    {
        return sysLoginStatusMapper.insertSysLoginStatus(sysLoginStatus);
    }

    /**
     * 修改登录状态
     * 
     * @param sysLoginStatus 登录状态
     * @return 结果
     */
    @Override
    public int updateSysLoginStatus(SysLoginStatus sysLoginStatus)
    {
        return sysLoginStatusMapper.updateSysLoginStatus(sysLoginStatus);
    }

    /**
     * 查询用户当天最后一次登录信息
     * @return
     */
    @Override
    public SysLoginStatus selectSysLoginStatus(String orgId,String loginName) {
        return sysLoginStatusMapper.selectSysLoginStatus(orgId,loginName);
    }

    /**
     *查询用户当天最大的退出次数
     * @return
     */
    @Override
    public Integer selectMaxLogoutCount(String orgId, String loginName) {
        return sysLoginStatusMapper.selectMaxLogoutCount(orgId,loginName);
    }

    /**
     * 查询当天未退出系统的登录信息
     * @return
     */
    @Override
    public List<SysLoginStatus> selectNotLogoutStatus(String hostAddr) {
        return sysLoginStatusMapper.selectNotLogoutStatus(hostAddr);
    }


}
