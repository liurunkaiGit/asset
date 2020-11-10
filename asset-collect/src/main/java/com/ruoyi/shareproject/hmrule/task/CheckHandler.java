package com.ruoyi.shareproject.hmrule.task;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.shareproject.hmrule.domain.TLjRule;
import com.ruoyi.shareproject.hmrule.domain.TLjRuleDetails;
import com.ruoyi.shareproject.hmuserst.domain.TLjRuleUserLogs;
import com.ruoyi.system.domain.SysUser;

import java.text.NumberFormat;
import java.util.Date;

/**
 * 检测是否预警接口
 */
public abstract  class CheckHandler {
    protected  CheckHandler nextCheckHandler;
    public abstract void checkWarning(TLjRule tLjRule, SysUser user, TLjRuleDetails tLjRuleDetails, TLjRuleUserLogs userLog);
    public void  setNextCheckHandler(CheckHandler nextCheckHandler){
        this.nextCheckHandler = nextCheckHandler;
    }
    public CheckHandler getNextCheckHandler(){
        return nextCheckHandler;
    }
    protected Date zhixingTime(String hour){
        String datestr = DateUtils.getDate()+" "+hour;
        return DateUtils.dateTime("yyyy-MM-dd hh:mm:ss",datestr);
    }

    public static int tiaojian(String tiaoJian, Integer one, Integer two, Double biaozhun){
        if(biaozhun==null)return 1;
        if("0".equals(tiaoJian)){
            if(one == null || null == two){
                return 0;
            }
            if(biaozhun < one || biaozhun> two){
                return 1;
            }
        }else if("1".equals(tiaoJian)){
            if(biaozhun < one){
                return 1;
            }
        }else if("2".equals(tiaoJian)){
            if(biaozhun > one){
                return 1;
            }
        }
        return 0;
    }

    public static int tiaojian(String tiaoJian, Long one, Long two, Double biaozhun){
        if(biaozhun==null)return 1;
        if("0".equals(tiaoJian)){
            if(one == null || null == two){
                return 0;
            }
            if(biaozhun < one || biaozhun> two){
                return 1;
            }
        }else if("1".equals(tiaoJian)){
            if(biaozhun < one){
                return 1;
            }
        }else if("2".equals(tiaoJian)){
            if(biaozhun > one){
                return 1;
            }
        }
        return 0;
    }


    public static Double getLv(Double min,Double max){
        if(null == min || null == max ||0 == min || 0 == max )return new Double(0);
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format(min /  max * 100);
        return new Double(result);
    }

   public static double load(Double ir){
        if(null == ir)return 0;
        return ir;
    }
}
