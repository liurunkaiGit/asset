package com.ruoyi.shareproject.hmrule.task;

import com.ruoyi.assetspackage.util.ApplicationUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.shareproject.hmrule.domain.TLjRule;
import com.ruoyi.shareproject.hmrule.domain.TLjRuleDetails;
import com.ruoyi.shareproject.hmrule.domain.TLjRuleRange;
import com.ruoyi.shareproject.hmuserst.domain.TLjRuleUserLogs;
import com.ruoyi.shareproject.hmuserst.mapper.TLjRuleUserLogsMapper;
import com.ruoyi.system.domain.SysLoginStatus;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysLoginStatusService;
import com.ruoyi.task.domain.TLcCallRecord;
import com.ruoyi.task.domain.TlcCallRecordTotal;
import com.ruoyi.task.mapper.TLcCallRecordMapper;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 通话时长 异常检测
 * @author gaohg
 * @date 2020-11-02
 */
public class TonghuaTimeCheckHandler extends CheckHandler{


    @Override
    public void checkWarning(TLjRule tLjRule, SysUser user, TLjRuleDetails tLjRuleDetails, TLjRuleUserLogs userLog) {
        TLcCallRecordMapper tr = (TLcCallRecordMapper) ApplicationUtils.getBean(TLcCallRecordMapper.class);
        TLcCallRecord td = new TLcCallRecord();
        td.setStartCallStartTime(zhixingTime(tLjRuleDetails.getStartTime()));
        td.setEndCallStartTime(zhixingTime(tLjRuleDetails.getEndTime()));
        userLog.setJietonglv(BigDecimal.valueOf(0));
        td.setCreateBy(user.getUserId().toString());
        //根据配置的时间、用户id统计通话 数据
        TlcCallRecordTotal mm = tr.selectUserTotal(td);
        if(null == mm){
            mm = new TlcCallRecordTotal();
            mm.setConnections(0L);
            mm.setDurations(0D);
            mm.setFrequencys(0L);
        }else{
            if(null == mm.getConnections())mm.setConnections(0L);
            if(null == mm.getDurations())mm.setDurations(0D);
            if(null == mm.getFrequencys())mm.setFrequencys(0L);
        }
        userLog.setTonghuaDuration(BigDecimal.valueOf(load(mm.getDurations())));
        //通话时长勾选了 就判断是否存在异常
        userLog.setTonghuaError(0);
        userLog.setTonghuacsError(0);
        userLog.setJietonglvError(0);
        if("1".equals(tLjRuleDetails.getConversationTime())){
            userLog.setTonghuaError(tiaojian(tLjRuleDetails.getConversationCondition(),tLjRuleDetails.getConversationOne()*1000L*60L,getTiaojianTwo(tLjRuleDetails.getConversationTwo())*1000L*60L,mm.getDurations()));
        }
        userLog.setTonghuacs(mm.getFrequencys().intValue());

        if("1".equals(tLjRuleDetails.getConversationCishu())){
            userLog.setTonghuacsError(tiaojian(tLjRuleDetails.getConversationCishuCondition(),tLjRuleDetails.getConversationCishuOne(),getTiaojianTwo(tLjRuleDetails.getConversationCishuTwo()),mm.getFrequencys().doubleValue()));
        }
        userLog.setJietongcs(mm.getConnections().intValue());
        Double jtl =getLv(mm.getConnections().doubleValue(),mm.getFrequencys().doubleValue());
        userLog.setJietonglv(BigDecimal.valueOf(jtl));
        if("1".equals(tLjRuleDetails.getEngRate())){
            userLog.setJietonglvError(tiaojian(tLjRuleDetails.getEngRateCondition(),tLjRuleDetails.getEngRateOne(),getTiaojianTwo(tLjRuleDetails.getEngRateTwo()),jtl));
        }
        userLog.setTonghuaZong(userLog.getTonghuaError()+userLog.getTonghuacsError()+userLog.getJietonglvError());
        if(getNextCheckHandler() != null)getNextCheckHandler().checkWarning(tLjRule,user,tLjRuleDetails,userLog);
    }

}
