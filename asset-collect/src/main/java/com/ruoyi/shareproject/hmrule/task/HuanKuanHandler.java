package com.ruoyi.shareproject.hmrule.task;

import com.ruoyi.assetspackage.util.ApplicationUtils;
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
 * 还款达成 异常检测
 * @author gaohg
 * @date 2020-11-02
 */
public class HuanKuanHandler extends  CheckHandler{

    @Override
    public void checkWarning(TLjRule tLjRule, SysUser user, TLjRuleDetails tLjRuleDetails, TLjRuleUserLogs userLog) {
        TLcReportDayProcessMapper tLcReportDayProcessMapper = (TLcReportDayProcessMapper) ApplicationUtils.getBean(TLcReportDayProcessMapper.class);
        Map<String, Object> param = new HashMap<>();
        param.put("userId",user.getUserId());
        List<TLcReportDayProcess>  list = tLcReportDayProcessMapper.selectTaskReach(param);
        userLog.setShijiError(0);
        userLog.setShijilvError(0);
        userLog.setShijilv(BigDecimal.valueOf(0));
        if(null !=list && !list.isEmpty()){
            TLcReportDayProcess ps = list.get(0);
            BigDecimal yinghuan = ps==null||ps.getAmountDueSum()==null?new BigDecimal(0):ps.getAmountDueSum();
            BigDecimal shiji = ps==null|| ps.getAmountActualSum()==null?new BigDecimal(0):ps.getAmountActualSum();
            userLog.setYinghuan(yinghuan);
            userLog.setShiji(shiji);
            if("1".equals(tLjRuleDetails.getSjRepayment())){
                userLog.setShijiError(tiaojian(tLjRuleDetails.getSjRepaymentCondition(),tLjRuleDetails.getSjRepaymentOne(),getTiaojianTwo(tLjRuleDetails.getSjRepaymentTwo()),shiji.doubleValue()));
            }
            Double sjl = getLv(shiji.doubleValue(),yinghuan.doubleValue());
            userLog.setShijilv(BigDecimal.valueOf(sjl));
            if("1".equals(tLjRuleDetails.getSjRepaymentRate())){
                userLog.setShijilvError(tiaojian(tLjRuleDetails.getSjRepaymentRateCondition(),tLjRuleDetails.getSjRepaymentRateOne(),getTiaojianTwo(tLjRuleDetails.getSjRepaymentRateTwo()),sjl));
            }
        }else{
            userLog.setShijiError(1);
            userLog.setShijilvError(1);
        }
        userLog.setHuankuanZong(userLog.getShijiError()+userLog.getShijilvError());
        if(getNextCheckHandler() != null)getNextCheckHandler().checkWarning(tLjRule,user,tLjRuleDetails,userLog);
    }
}
