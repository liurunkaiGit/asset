package com.ruoyi.shareproject.hmrule.task;

import com.ruoyi.assetspackage.util.ApplicationUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.shareproject.hmrule.domain.TLjRule;
import com.ruoyi.shareproject.hmrule.domain.TLjRuleDetails;
import com.ruoyi.shareproject.hmrule.domain.TLjRuleRange;
import com.ruoyi.shareproject.hmuserst.domain.TLjRuleUserLogs;
import com.ruoyi.system.domain.SysLoginStatus;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.mapper.SysLoginStatusMapper;
import com.ruoyi.system.service.ISysLoginStatusService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 在线时长 异常检测
 * @author gaohg
 * @date 2020-11-02
 */
public class OnlineCheckHandler extends CheckHandler{
    protected Date zhixingTimeLL(String hour){
       // String datestr = DateUtils.getDate()+" "+hour;
        return DateUtils.dateTime("yyyy-MM-dd hh:mm:ss",hour);
    }

    public static void main(String[] args) {
        DateUtils.dateTime("yyyy-MM-dd hh:mm:ss","2020-11-06 00:00:00");
    }
    @Override
    public void checkWarning(TLjRule tLjRule, SysUser user, TLjRuleDetails tLjRuleDetails, TLjRuleUserLogs userLog) {
        SysLoginStatus sysLoginStatus = new SysLoginStatus();
        sysLoginStatus.setLoginName(user.getLoginName());
        sysLoginStatus.setStartCensusDate(zhixingTime(tLjRuleDetails.getStartTime()));
        sysLoginStatus.setEndCensusDate(zhixingTime(tLjRuleDetails.getEndTime()));
//        sysLoginStatus.setStartCensusDate(zhixingTimeLL("2020-11-06 00:00:00"));
//        sysLoginStatus.setEndCensusDate(zhixingTimeLL("2020-11-07 00:00:00"));
        SysLoginStatusMapper sysLoginStatusMapper = ApplicationUtils.getBean(SysLoginStatusMapper.class);
        List<SysLoginStatus> list = sysLoginStatusMapper.selectSysLoginStatusListTask(sysLoginStatus);
        List<SysLoginStatus> listOne = sysLoginStatusMapper.selectSysLoginStatusListTaskOne(sysLoginStatus);
        BigDecimal online = new BigDecimal(0);
        BigDecimal jiange = new BigDecimal(0);
        int outCishu=0;
        userLog.setJiangeError(0);
        userLog.setOutError(0);
        userLog.setOnlineError(0);
        if(null != list && !list.isEmpty()){
            SysLoginStatus ss = list.get(0);
            //登录时长
            String sl = ss.getOnlineLen()==null || "".equals(ss.getOnlineLen())?"0":ss.getOnlineLen();
            Double ol = Double.parseDouble(sl);
            online = BigDecimal.valueOf(Double.parseDouble(sl));
            //标识登录次数是否-1
            int out = 0;
            SysLoginStatus one = null;
            if(null != listOne && !listOne.isEmpty()){
                one = listOne.get(0);
            }
            if(one !=null){
                //如果为空 时长=当前时间-创建时间
                if(one.getOnlineLen()==null || "".equals(one.getOnlineLen())){
                    out =1;
                    Date zhixing = zhixingTime(tLjRuleDetails.getEndTime());
                    long ont = (zhixing.getTime()-one.getCreateTime().getTime())/1000L;
                    if(ont>0){
                        online = BigDecimal.valueOf(online.doubleValue()+Double.parseDouble(ont+""));
                    }
                }
            }
            //onlineTime = Integer.parseInt(sl);

            //判断登录时长是否勾选
            if("1".equals(tLjRuleDetails.getOnlineTime())){
                userLog.setOnlineError(tiaojian(tLjRuleDetails.getOnlineCondition(),tLjRuleDetails.getOnlineOne()*60L,getTiaojianTwo(tLjRuleDetails.getOnlineTwo())*60L,ol));
            }
            //退出次数
            outCishu  = ss.getLoginNum()==null?0:ss.getLoginNum();
            if(outCishu>0){
                outCishu = outCishu - out;
            }
            //判断退出次数是否勾选
            if("1".equals(tLjRuleDetails.getOutTime())){
                userLog.setOutError(tiaojian(tLjRuleDetails.getOutCondition(),tLjRuleDetails.getOutOne(),getTiaojianTwo(tLjRuleDetails.getOutTwo()),Double.valueOf(outCishu)));
            }
            //间隔时长
            String jg = ss.getIntervalTime()==null || "".equals(ss.getIntervalTime())?"0":ss.getIntervalTime();
            Double jgd = Double.parseDouble(jg);
            jiange =  BigDecimal.valueOf(Double.parseDouble(jg));
            if("1".equals(tLjRuleDetails.getIntervals())){
                userLog.setJiangeError(tiaojian(tLjRuleDetails.getIntervalsCondition(),tLjRuleDetails.getIntervalsOne()*60L,getTiaojianTwo(tLjRuleDetails.getIntervalsTwo())*60L,jgd));
            }
        }else{
            userLog.setJiangeError(1);
            userLog.setOutError(1);
            userLog.setOnlineError(1);
        }
        userLog.setOnlineTime(online);
        userLog.setOutCishu(outCishu);
        userLog.setJiange(jiange);
        userLog.setLoginZong(userLog.getOnlineError()+userLog.getOutError()+userLog.getJiangeError());
        if(getNextCheckHandler() != null)getNextCheckHandler().checkWarning(tLjRule,user,tLjRuleDetails,userLog);
    }

}
