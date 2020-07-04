package com.ruoyi.utils;

import org.springframework.stereotype.Component;

/**
 * @Description: 存放本地缓存key的工具类
 * @author: liurunkai
 * @Date: 2020/2/25 15:24
 */
@Component
public class LocalCacheKeyUtils {

    public static final String UNDERLINE = "_";

    /**
     * 手机号连续呼叫天数的key
     *
     * @param phone
     * @return
     */
    public static String phoneContinueCallDaysKey(String phone) {
        return "CALL_DAYS" + LocalCacheKeyUtils.UNDERLINE + phone;
    }

    /**
     * 手机号当天呼叫次数key
     *
     * @param phone
     * @return
     */
    public static String phoneCurDayCallNumsKey(String phone) {
        return "CALL_NUMS" + LocalCacheKeyUtils.UNDERLINE + phone;
    }

    /**
     * 需要第二天启动的任务key
     *
     * @return
     */
    public static String taskWaitStartList() {
        return "TASK_WAIT_START_LIST";
    }

    /**
     * 当天继续呼叫的任务key
     */
    public static String phoneContinueCallCurDay(Integer robotTaskId) {
        return "CONTINUE_CALL_CUR_DAY" + LocalCacheKeyUtils.UNDERLINE + robotTaskId;
    }
}
