package com.ruoyi.shareproject.hmuserst.service.impl;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.shareproject.hmrule.task.CheckHandler;
import com.ruoyi.shareproject.hmuserst.domain.TLjRuleUserLogs;
import com.ruoyi.shareproject.hmuserst.mapper.TLjRuleUserLogsMapper;
import com.ruoyi.shareproject.hmuserst.service.ITLjRuleUserLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

/**
 * 【员工状态】Service业务层处理
 * 
 * @author gaohg
 * @date 2020-11-05
 */
@Service
public class TLjRuleUserLogsServiceImpl implements ITLjRuleUserLogsService
{
    @Autowired
    private TLjRuleUserLogsMapper tLjRuleUserLogsMapper;


    private String loadFen(Double miao,Double m){
        if(miao == null || 0 == miao)return "0";
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        return  numberFormat.format(miao /  m );
    }
    /**
     * 查询【员工状态】
     * 
     * @param id 【员工状态】ID
     * @return 【员工状态】
     */
    @Override
    public TLjRuleUserLogs selectTLjRuleUserLogsById(Long id)
    {
        TLjRuleUserLogs ts = tLjRuleUserLogsMapper.selectTLjRuleUserLogsById(id);
        //在线时长 秒转化分
        ts.setOnlineTimeStr(loadFen(ts.getOnlineTime().doubleValue(),60D));
        ts.setTonghuaDurationStr(loadFen(ts.getTonghuaDuration().doubleValue(),1000D*60D));
        ts.setJiangeStr(loadFen(ts.getJiange().doubleValue(),60D));
        //在线时长
        Map<String,Object> anjm = loadBaifen(ts.getOnlineCondition(),ts.getOnlineOne(),ts.getOnlineTwo(),ts.getOnlineTime().doubleValue());
        ts.setOnlineBaifen(anjm.get("baifen").toString());
        ts.setOnlineZhibiao(Integer.parseInt(anjm.get("zhibiao").toString()));
        //退出次数
        Map<String,Object> outm = loadBaifen(ts.getOutCondition(),ts.getOutOne(),ts.getOutTwo(),ts.getOutCishu().doubleValue());
        ts.setOutCishuBaifen(outm.get("baifen").toString());
        ts.setOutCishuZhibiao(Integer.parseInt(outm.get("zhibiao").toString()));
        //间隔时长
        if(ts.getJiange()!=null && ts.getJiange().floatValue()>0){
            //将秒转化分
            ts.setJiange(ts.getJiange().divide(new BigDecimal(60D),2,BigDecimal.ROUND_HALF_UP));
        }
        Map<String,Object> jianm = loadBaifen(ts.getIntervalsCondition(),ts.getIntervalsOne(),ts.getIntervalsTwo(),ts.getJiange().doubleValue());
        ts.setJiangeBaifen(jianm.get("baifen").toString());
        ts.setJiangeZhibiao(Integer.parseInt(jianm.get("zhibiao").toString()));

        //通话时长
        if(ts.getTonghuaDuration()!=null && ts.getTonghuaDuration().floatValue()>0){
            //将毫秒转化分
            ts.setTonghuaDuration(ts.getTonghuaDuration().divide(new BigDecimal(1000*60),2,BigDecimal.ROUND_HALF_UP));
        }
        Map<String,Object> thm = loadBaifen(ts.getConversationCondition(),ts.getConversationOne(),ts.getConversationTwo(),ts.getTonghuaDuration().doubleValue());
        ts.setTonghuaDurationBaifen(thm.get("baifen").toString());
        ts.setTonghuaDurationZhibiao(Integer.parseInt(thm.get("zhibiao").toString()));
        //通话次数
        Map<String,Object> csm = loadBaifen(ts.getConversationCishuCondition(),ts.getConversationCishuOne(),ts.getConversationCishuTwo(),ts.getTonghuacs().doubleValue());
        ts.setTonghuacsBaifen(csm.get("baifen").toString());
        ts.setTonghuacsZhibiao(Integer.parseInt(csm.get("zhibiao").toString()));
        //接通率
        Map<String,Object> jsm = loadBaifen(ts.getEngRateCondition(),ts.getEngRateOne(),ts.getEngRateTwo(),ts.getJietonglv().doubleValue());
        ts.setJietongcsBaifen(jsm.get("baifen").toString());
        ts.setJietongcsZhibiao(Integer.parseInt(jsm.get("zhibiao").toString()));

        //案件处理
        Map<String,Object> ajm = loadBaifen(ts.getCaseNumbersCondition(),ts.getCaseNumbersOne(),ts.getCaseNumbersTwo(),ts.getAnjianyichuli().doubleValue());
        ts.setAnjianDurationBaifen(ajm.get("baifen").toString());
        ts.setAnjianDurationZhibiao(Integer.parseInt(ajm.get("zhibiao").toString()));
        //案件处理率
        Map<String,Object> ajlm = loadBaifen(ts.getCaseRateCondition(),ts.getCaseRateOne(),ts.getCaseRateTwo(),ts.getAnjianlv().doubleValue());
        ts.setAnjianyichuliBaifen(ajlm.get("baifen").toString());
        ts.setAnjianyichuliZhibiao(Integer.parseInt(ajlm.get("zhibiao").toString()));

        //实际还款
        Map<String,Object> sjm = loadBaifen(ts.getSjRepaymentCondition(),ts.getSjRepaymentOne(),ts.getSjRepaymentTwo(),ts.getShiji().doubleValue());
        ts.setYinghuanBaifen(sjm.get("baifen").toString());
        ts.setYinghuanZhibiao(Integer.parseInt(sjm.get("zhibiao").toString()));
        //实际还款率
        Map<String,Object> sjlm = loadBaifen(ts.getSjRepaymentRateCondition(),ts.getSjRepaymentRateOne(),ts.getSjRepaymentRateTwo(),ts.getShijilv().doubleValue());
        ts.setShijiBaifen(sjlm.get("baifen").toString());
        ts.setShijiZhibiao(Integer.parseInt(sjlm.get("zhibiao").toString()));

        return ts;
    }

    private static Map<String,Object> loadBaifen(String tiaoJian, Integer one, Integer two, Double zhibiao){
        Map<String,Object>  m = new HashMap<String,Object>();
        if(zhibiao==null){//指标为空或0则置为低于指标
            m.put("zhibiao",-1);
            m.put("baifen",100);
            return m;
        }
        if("0".equals(tiaoJian)){
            if(one == null || null == two){
                m.put("zhibiao",0);
                m.put("baifen",0);
                return m;
            }
            if(zhibiao < one ){
                //低于指标
                m.put("zhibiao",-1);
                m.put("baifen",baifen(one-zhibiao,one.doubleValue()));
                return m;
            }
            if(zhibiao> two){
                //高于指标
                m.put("zhibiao",1);
                String d = baifen(zhibiao-two,two.doubleValue());
                m.put("baifen",d);
                return m;
            }
        }else if("1".equals(tiaoJian)){
            if(one == null ){
                m.put("zhibiao",0);
                m.put("baifen",0);
                return m;
            }
            if(zhibiao < one){
                m.put("zhibiao",-1);
                m.put("baifen",baifen(one-zhibiao,one.doubleValue()));
                return m;
            }else{
                m.put("zhibiao",1);
                m.put("baifen",baifen(zhibiao-one,one.doubleValue()));
                return m;
            }
        }else if("2".equals(tiaoJian)){
            if(one == null ){
                m.put("zhibiao",0);
                m.put("baifen",0);
                return m;
            }
            if(zhibiao > one){
                m.put("zhibiao",1);
                m.put("baifen",baifen(zhibiao-one,one.doubleValue()));
                return m;
            }else{
                m.put("zhibiao",-1);
                m.put("baifen",baifen(one-zhibiao,one.doubleValue()));
                return m;
            }
        }
        m.put("zhibiao",0);
        m.put("baifen",0);
        return m;
    }

    private static Map<String,Object> loadBaifen(String tiaoJian, BigDecimal one, BigDecimal two, Double zhibiao){
        Map<String,Object>  m = new HashMap<String,Object>();
        if(zhibiao==null){//指标为空或0则置为低于指标
            m.put("zhibiao",-1);
            m.put("baifen",100);
            return m;
        }
        if("0".equals(tiaoJian)){
            if(one == null || null == two){
                m.put("zhibiao",0);
                m.put("baifen",0);
                return m;
            }
            if(zhibiao < one.doubleValue() ){
                //低于指标
                m.put("zhibiao",-1);
                m.put("baifen",baifen(one.doubleValue()-zhibiao,one.doubleValue()));
                return m;
            }
            if(zhibiao> two.doubleValue()){
                //高于指标
                m.put("zhibiao",1);
                String d = baifen(zhibiao-two.doubleValue(),two.doubleValue());
                m.put("baifen",d);
                return m;
            }
        }else if("1".equals(tiaoJian)){
            if(zhibiao < one.doubleValue()){
                m.put("zhibiao",-1);
                m.put("baifen",baifen(one.doubleValue()-zhibiao,one.doubleValue()));
                return m;
            }else{
                m.put("zhibiao",1);
                m.put("baifen",baifen(zhibiao-one.doubleValue(),one.doubleValue()));
                return m;
            }
        }else if("2".equals(tiaoJian)){
            if(zhibiao > one.doubleValue()){
                m.put("zhibiao",1);
                m.put("baifen",baifen(zhibiao-one.doubleValue(),one.doubleValue()));
                return m;
            }else{
                m.put("zhibiao",-1);
                m.put("baifen",baifen(one.doubleValue()-zhibiao,one.doubleValue()));
                return m;
            }
        }
        m.put("zhibiao",0);
        m.put("baifen",0);
        return m;
    }

    public static String baifen(Double min,Double max){
        if(null == min || null == max ||0 == min || 0 == max )return "0";
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format((min /  max) * 100);
        return result;
    }

    /**
     * 查询【员工状态】列表
     * 
     * @param tLjRuleUserLogs 【员工状态】
     * @return 【员工状态】
     */
    @Override
    public List<TLjRuleUserLogs> selectTLjRuleUserLogsList(TLjRuleUserLogs tLjRuleUserLogs)
    {
        return tLjRuleUserLogsMapper.selectTLjRuleUserLogsList(tLjRuleUserLogs);
    }

    /**
     * 新增【员工状态】
     * 
     * @param tLjRuleUserLogs 【员工状态】
     * @return 结果
     */
    @Override
    public int insertTLjRuleUserLogs(TLjRuleUserLogs tLjRuleUserLogs)
    {
        tLjRuleUserLogs.setCreateTime(DateUtils.getNowDate());
        return tLjRuleUserLogsMapper.insertTLjRuleUserLogs(tLjRuleUserLogs);
    }

    /**
     * 修改【员工状态】
     * 
     * @param tLjRuleUserLogs 【员工状态】
     * @return 结果
     */
    @Override
    public int updateTLjRuleUserLogs(TLjRuleUserLogs tLjRuleUserLogs)
    {
        tLjRuleUserLogs.setUpdateTime(DateUtils.getNowDate());
        return tLjRuleUserLogsMapper.updateTLjRuleUserLogs(tLjRuleUserLogs);
    }

    /**
     * 删除【员工状态】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTLjRuleUserLogsByIds(String ids)
    {
        return tLjRuleUserLogsMapper.deleteTLjRuleUserLogsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【员工状态】信息
     * 
     * @param id 【员工状态】ID
     * @return 结果
     */
    @Override
    public int deleteTLjRuleUserLogsById(Long id)
    {
        return tLjRuleUserLogsMapper.deleteTLjRuleUserLogsById(id);
    }
}
