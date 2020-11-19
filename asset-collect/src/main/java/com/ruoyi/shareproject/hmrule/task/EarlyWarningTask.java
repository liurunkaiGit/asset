package com.ruoyi.shareproject.hmrule.task;

import com.ruoyi.assetspackage.mapper.SysIpConfigMapper;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.shareproject.hmrule.domain.TLjRule;
import com.ruoyi.shareproject.hmrule.domain.TLjRuleDetails;
import com.ruoyi.shareproject.hmrule.domain.TLjRuleRange;
import com.ruoyi.shareproject.hmrule.mapper.TLjRuleDetailsMapper;
import com.ruoyi.shareproject.hmrule.mapper.TLjRuleRangeMapper;
import com.ruoyi.shareproject.hmrule.service.ITLjRuleService;
import com.ruoyi.shareproject.hmuserst.domain.TLjRuleUserLogs;
import com.ruoyi.shareproject.hmuserst.mapper.TLjRuleUserLogsMapper;
import com.ruoyi.system.domain.SysLoginStatus;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.mapper.SysLoginStatusMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.ISysLoginStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Slf4j
@Component("earlyWarningTask")
public class EarlyWarningTask {

    @Autowired
    private ITLjRuleService iTLjRuleService;
    @Autowired
    private TLjRuleDetailsMapper tLjRuleDetailsMapper;
    @Autowired
    private TLjRuleRangeMapper tLjRuleRangeMapper;
    @Autowired
    private TLjRuleUserLogsMapper tLjRuleUserLogsMapper;
    @Autowired
    private ISysLoginStatusService sysLoginStatusService;
//    @Autowired
//    private ISysUserService iSysUserService;
    @Autowired
    private SysIpConfigMapper sysIpConfigMapper;
    @Autowired
    private SysLoginStatusMapper sysLoginStatusMapper;

    @Autowired
    private SysUserMapper userMapper;

    public void findEwInfo(){
        log.info("开始执行居家规则预警，时间：{}", LocalDateTime.now(ZoneId.systemDefault()));
        TLjRule te = new TLjRule();
        te.setRuleStatus("2");
        List<TLjRule> lt = iTLjRuleService.selectTLjRuleList(te);
        List<String> ipList = sysIpConfigMapper.selectPartIpList();
        Calendar now = Calendar.getInstance();
        long nowtime = now.getTimeInMillis();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int fen = now.get(Calendar.MINUTE);
        int miao = now.get(Calendar.SECOND);
        long hm = hour*60*60*1000L+fen*60*1000L+miao*1000L;
        for(TLjRule tr: lt){
            long etime =  tr.getEndTime().getTime();
            //如果任务过时 则将任务状态置为失效
            if(nowtime > etime) {
                //任务超时 40 分钟就不会执行
//                if((nowtime-etime) < 40*60*1000L ){
                    //执行
                    zhixing(tr,hm,ipList);
//                }
                tr.setRuleStatus("3");
                iTLjRuleService.updateTLjRuleStatus(tr);
           }else{
                zhixing(tr,hm,ipList);
            }
        }
        log.info("完成执行居家规则预警，时间：{}", LocalDateTime.now(ZoneId.systemDefault()));
    }
    //执行预警方法
    private void zhixing(TLjRule tr,long nowHourhm,List<String> ipList){
        //查找任务对应的人员
        if(tr.getUserInfos() == null){
            List<SysUser> curUserInfos = new ArrayList<SysUser>(30);
            //根据规则id 查询部门或人员ids
            TLjRuleRange te = new TLjRuleRange();
            te.setRuleId(tr.getId());
            List<TLjRuleRange> dpOrUsLt = tLjRuleRangeMapper.selectTLjRuleRangeList(te);
            for(TLjRuleRange tee:dpOrUsLt){
                if("1".equals(tee.getTypes())){
                    //1=部门
                    SysUser sr = new SysUser();
                    sr.setDeptId(Long.parseLong(tee.getDporusId()));
                    List<SysUser> uList = userMapper.selectUserList(sr);
                    curUserInfos.addAll(uList);
                }else if("2".equals(tee.getTypes())){
                    //2=人员
                    curUserInfos.add(userMapper.selectUserByLoginName(tee.getDporusId()));
                }
            }
            //tr.setRrList(dpOrUsLt);
           //动态检索用户是居家还是职场
            String pz = tr.getOnthejobStatus();
            List<SysUser> curr = new ArrayList<>(curUserInfos.size());
            for(SysUser sr: curUserInfos){
                List<SysLoginStatus> ls = sysLoginStatusMapper.selIpByLoginName(sr.getLoginName());
                //如果没有获取到则将用户加入到检测队列中
                if(null == ls || ls.isEmpty()){
                    curr.add(sr);
                }else{
                    SysLoginStatus ss = ls.get(0);
                    if(ss == null || null == ss.getIpAddr() || "".equals(ss.getIpAddr())){
                        curr.add(sr);
                        continue;
                    }
                    String postStatus =  "1";
                    String ipAddr = ss.getIpAddr();//最后一次登录的ip
                    String [] arr = new String[3];
                    String[] split = ipAddr.split("\\.");
                    arr[0]=split[0];
                    arr[1]=split[1];
                    arr[2]=split[2];
                    String ipAddrPart = StringUtils.join(arr, ".");//最后一次登录的ip段
                    for (String confIp : ipList) {
                        if(confIp.equals(ipAddrPart)){
                            postStatus = "2";//职场
                            break;
                        }
                    }
                    //用户最新状态与配置的相同则加入队列
                    if(pz.equals(postStatus)){
                        curr.add(sr);
                    }
                }
            }
            if(curr.isEmpty()){
                log.debug("任务最新ip匹配 未找到到相关人员--任务id===="+tr.getId()+"--名字=="+tr.getRuleName());
                return;
            }
            tr.setUserInfos(curr);
        }
        if(tr.getUserInfos() == null || tr.getUserInfos().isEmpty()){
            log.debug("任务未找到相关人员--任务id===="+tr.getId()+"--名字=="+tr.getRuleName());
            return;
        }

        //查询指定规则
        TLjRuleDetails ts = new TLjRuleDetails();
        ts.setRuleId(tr.getId());
        List<TLjRuleDetails> dlt = tLjRuleDetailsMapper.selectTLjRuleDetailsList(ts);
        for(TLjRuleDetails tsOne: dlt){
            //判断是否到任务结束时间，任务超时在25分钟以内才会执行
          // if(nowHourhm > tsOne.getEndTimeHm() && (nowHourhm-tsOne.getEndTimeHm()) <= 25*60*1000){
            if(nowHourhm > tsOne.getEndTimeHm()){
               //查找任务日志表是否已经被执行
               TLjRuleUserLogs lls = new TLjRuleUserLogs();
               lls.setRuleId(tr.getId());
               lls.setDetailsId(tsOne.getId());
               lls.setDays(DateUtils.getNowDate());
               Long ct = tLjRuleUserLogsMapper.selectTLjRuleUserLogsByRuleIdAnddetailsId(lls);
               //如果存在记录说明已经被执行，执行下个子任务
               if(null != ct)continue;
               //检测在线时长 规则是否超标
               checkErrors(tr,tsOne);
           }
        }

    }


    /**
     * 在线时长预警 查询所有用户在线时长并记录
     * @param tLjRule
     * @param tLjRuleDetails
     */
    private void checkErrors(TLjRule tLjRule,TLjRuleDetails tLjRuleDetails){
        CheckHandler cr = ChainHandler.loadChain(tLjRuleDetails);
        //实例化空日期对象
        TLjRuleUserLogs userLog = new TLjRuleUserLogs();
        //记录当前规则
        BeanUtils.copyProperties(tLjRuleDetails,userLog);
        //记录规则id
        userLog.setRuleId(null);
        userLog.setRuleName(tLjRule.getRuleName());
        userLog.setDetailsId(tLjRuleDetails.getId());
        userLog.setDays(DateUtils.getNowDate());
        userLog.setCreateTime(DateUtils.getNowDate());
        userLog.setUpdateTime(DateUtils.getNowDate());
        //根据人逐条检索数据并 检测是否异常
        int i=0;
        for(SysUser user:tLjRule.getUserInfos()){
            //检测到最后一个用户的时候将主规则id填写上去
            if(i == (tLjRule.getUserInfos().size()-1)){
                userLog.setRuleId(tLjRuleDetails.getRuleId());
            }
            cr.checkWarning(tLjRule,user,tLjRuleDetails,userLog);
            userLog.setLoginName(user.getLoginName());
            userLog.setUserName(user.getUserName());
            //userLog.setErrors(loadZong(userLog.getLoginZong())+loadZong(userLog.getAnjianZong())+loadZong(userLog.getTonghuaZong())+loadZong(userLog.getHuankuanZong()));
            userLog.setErrors(loadZong(userLog.getLoginZong())+loadZong(userLog.getAnjianZong())+loadZong(userLog.getTonghuaZong()));
            tLjRuleUserLogsMapper.insertTLjRuleUserLogs(userLog);
            i++;
        }
    }

   private static int loadZong(Integer ir){
        if(null == ir)return 0;
        return ir;
    }

    private  static class ChainHandler{
        static CheckHandler loadChain(TLjRuleDetails tLjRuleDetails){
            List<CheckHandler> list = new ArrayList<CheckHandler>(10);
            list.add(new OnlineCheckHandler());
            list.add(new TonghuaTimeCheckHandler());
            list.add(new AnJianCheckHandler());
            //list.add(new HuanKuanHandler());
            return loadHandle(list);
        }
        static CheckHandler loadHandle(List<CheckHandler> list){
            for(int i=1;i<list.size();i++){
                list.get(i-1).setNextCheckHandler(list.get(i));
            }
            return list.get(0);
        }
    }
}
