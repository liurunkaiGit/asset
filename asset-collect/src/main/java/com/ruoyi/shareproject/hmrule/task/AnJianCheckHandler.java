package com.ruoyi.shareproject.hmrule.task;

import com.ruoyi.assetspackage.util.ApplicationUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.report.domain.TLcReportDayProcess;
import com.ruoyi.report.mapper.TLcReportDayProcessMapper;
import com.ruoyi.shareproject.hmrule.domain.TLjRule;
import com.ruoyi.shareproject.hmrule.domain.TLjRuleDetails;
import com.ruoyi.shareproject.hmuserst.domain.TLjRuleUserLogs;
import com.ruoyi.system.domain.SysUser;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 案件处理 异常检测
 * @author gaohg
 * @date 2020-11-02
 */
public class AnJianCheckHandler extends  CheckHandler{

    @Override
    public void checkWarning(TLjRule tLjRule, SysUser user, TLjRuleDetails tLjRuleDetails, TLjRuleUserLogs userLog) {
        TLcReportDayProcessMapper tLcReportDayProcessMapper = (TLcReportDayProcessMapper) ApplicationUtils.getBean(TLcReportDayProcessMapper.class);
        Map<String, Object> param = new HashMap<>();
        param.put("startDate",zhixingTime(tLjRuleDetails.getStartTime()));
        param.put("endDate",zhixingTime(tLjRuleDetails.getEndTime()));
        param.put("createBy",user.getUserId());
        List<TLcReportDayProcess>  list = tLcReportDayProcessMapper.selectDayProcessTask(param);
        userLog.setAnjianError(0);
        userLog.setAnjianlvError(0);
        if(null != list && !list.isEmpty()){
            TLcReportDayProcess tps = list.get(0);
            Integer ajl = tps==null || tps.getDealWithConsumerCount()==null?0:tps.getDealWithConsumerCount();
            Integer ycl = tps==null || tps.getUserCoverNum()==null?0:tps.getUserCoverNum();
            userLog.setAnjianDuration(ajl);
            userLog.setAnjianyichuli(ycl);
            //是否勾选案件处理量
            if("1".equals(tLjRuleDetails.getCaseNumbers())){
                userLog.setAnjianError(tiaojian(tLjRuleDetails.getCaseNumbersCondition(),tLjRuleDetails.getCaseNumbersOne(),getTiaojianTwo(tLjRuleDetails.getCaseNumbersTwo()),ajl.doubleValue()));
            }
            Double clv = getLv(ycl.doubleValue(),ajl.doubleValue());
            userLog.setAnjianlv(BigDecimal.valueOf(clv));
            if("1".equals(tLjRuleDetails.getCaseRate())){
                userLog.setAnjianlvError(tiaojian(tLjRuleDetails.getCaseRateCondition(),tLjRuleDetails.getCaseRateOne(),getTiaojianTwo(tLjRuleDetails.getCaseRateTwo()),clv));
            }
        }else{
            userLog.setAnjianDuration(0);
            userLog.setAnjianyichuli(0);
            userLog.setAnjianlv(BigDecimal.valueOf(0));
            if("1".equals(tLjRuleDetails.getCaseNumbers())){
                userLog.setAnjianError(1);
            }
            if("1".equals(tLjRuleDetails.getCaseRate())){
                userLog.setAnjianlvError(1);
            }
        }
        userLog.setAnjianZong(userLog.getAnjianError()+userLog.getAnjianlvError());
        if(getNextCheckHandler() != null)getNextCheckHandler().checkWarning(tLjRule,user,tLjRuleDetails,userLog);
    }
}
