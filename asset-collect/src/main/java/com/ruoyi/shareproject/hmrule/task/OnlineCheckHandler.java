package com.ruoyi.shareproject.hmrule.task;

import com.ruoyi.assetspackage.util.ApplicationUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.shareproject.hmrule.domain.TLjRule;
import com.ruoyi.shareproject.hmrule.domain.TLjRuleDetails;
import com.ruoyi.shareproject.hmuserst.domain.TLjRuleUserLogs;
import com.ruoyi.system.domain.SysLoginStatus;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.mapper.SysLoginStatusMapper;

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
        return DateUtils.dateTime("yyyy-MM-dd HH:mm:ss",hour);
    }

    public static void main(String[] args) {
        DateUtils.dateTime("yyyy-MM-dd hh:mm:ss","2020-11-06 00:00:00");
    }
    @Override
    public void checkWarning(TLjRule tLjRule, SysUser user, TLjRuleDetails tLjRuleDetails, TLjRuleUserLogs userLog) {
        SysLoginStatus sysLoginStatus = new SysLoginStatus();
        sysLoginStatus.setLoginName(user.getLoginName());
        Date taskStart = zhixingTime(tLjRuleDetails.getStartTime());
        Date taskEnd = zhixingTime(tLjRuleDetails.getEndTime());
        sysLoginStatus.setStartCensusDate(taskStart);
        sysLoginStatus.setEndCensusDate(taskEnd);
//        sysLoginStatus.setStartCensusDate(zhixingTimeLL("2020-11-06 00:00:00"));
//        sysLoginStatus.setEndCensusDate(zhixingTimeLL("2020-11-07 00:00:00"));

        SysLoginStatusMapper sysLoginStatusMapper = ApplicationUtils.getBean(SysLoginStatusMapper.class);
        //获取当天所有登录数据
        List<SysLoginStatus> list = sysLoginStatusMapper.selectSysLoginStatusListTask(sysLoginStatus);
        List<SysLoginStatus> listOne = sysLoginStatusMapper.selectSysLoginStatusListTaskOne(sysLoginStatus);
        BigDecimal online = new BigDecimal(0);
        BigDecimal jiange = new BigDecimal(0);
        int outCishu=0;
        userLog.setJiangeError(0);
        userLog.setOutError(0);
        userLog.setOnlineError(0);
        long ttst = taskStart.getTime();
        long ttend = taskEnd.getTime();
        long mm = 0;
        if(null != list && !list.isEmpty()){
            //登录时长
            mm = onlineTime(list,ttst,ttend);
            Double ol = Double.valueOf(mm);
            online = BigDecimal.valueOf(mm);

            //判断登录时长是否勾选
            if("1".equals(tLjRuleDetails.getOnlineTime())){
                userLog.setOnlineError(tiaojian(tLjRuleDetails.getOnlineCondition(),tLjRuleDetails.getOnlineOne()*60L,getTiaojianTwo(tLjRuleDetails.getOnlineTwo())*60L,ol));
            }
        }else{
            userLog.setOnlineError(1);
        }

        if(null != listOne && !listOne.isEmpty()){
            SysLoginStatus ss = listOne.get(0);
            //退出次数
            outCishu  = ss.getLoginNum()==null || "".equals(ss.getLoginNum()) ? 0:ss.getLoginNum();
            //判断退出次数是否勾选
            if("1".equals(tLjRuleDetails.getOutTime())){
                userLog.setOutError(tiaojian(tLjRuleDetails.getOutCondition(),tLjRuleDetails.getOutOne(),getTiaojianTwo(tLjRuleDetails.getOutTwo()),Double.valueOf(outCishu)));
            }
            //间隔时长
            String jg = ss.getIntervalTime()==null || "".equals(ss.getIntervalTime()) ? "0":ss.getIntervalTime();
            Double jgd = Double.parseDouble(jg);
            jiange =  BigDecimal.valueOf(Double.parseDouble(jg));
            if("1".equals(tLjRuleDetails.getIntervals())){
                userLog.setJiangeError(tiaojian(tLjRuleDetails.getIntervalsCondition(),tLjRuleDetails.getIntervalsOne()*60L,getTiaojianTwo(tLjRuleDetails.getIntervalsTwo())*60L,jgd));
            }
        }else{
            userLog.setJiangeError(1);
            userLog.setOutError(1);
        }
        userLog.setOnlineTime(online);
        userLog.setOutCishu(outCishu);
        userLog.setJiange(jiange);
        userLog.setLoginZong(userLog.getOnlineError()+userLog.getOutError()+userLog.getJiangeError());
        if(getNextCheckHandler() != null)getNextCheckHandler().checkWarning(tLjRule,user,tLjRuleDetails,userLog);
    }

    private long onlineTime( List<SysLoginStatus> list,Long taskStart,Long taskEnd){
        long fen = 0;
        for(SysLoginStatus login:list){
            long logst = login.getStartTime().getTime();
            if(logst >= taskStart && logst < taskEnd){
                //此种情况 正常数据 直接获取时长字段
                if(null != login.getEndTime() && login.getEndTime().getTime() < taskEnd){
                    fen+=Long.parseLong(login.getOnlineLen());
                }else if(null==login.getEndTime() || login.getEndTime().getTime() >= taskEnd){
                    fen+=(taskEnd - logst)/1000L;
                    break;
                }
            }else if(logst < taskStart ){
                //此种情况 登录时间没有在范围内 但是登录出时间在范围内
                if(null == login.getEndTime() || (null!=login.getEndTime() && login.getEndTime().getTime() >=taskEnd) ){
                    fen+=(taskEnd - taskStart)/1000L;
                    break;
                }else  {
                    if(login.getEndTime().getTime() > taskStart){
                        fen+=(login.getEndTime().getTime() - taskStart)/1000L;
                    }
                }
            }
        }
        return fen;
    }

}
