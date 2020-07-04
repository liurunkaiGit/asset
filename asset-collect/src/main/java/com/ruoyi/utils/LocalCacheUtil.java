package com.ruoyi.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.ruoyi.config.LocalCacheConfig;
import com.ruoyi.robot.domain.TLcRobotCallRecordMeteData;
import com.ruoyi.task.domain.TLcTask;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 本地缓存工具类
 * @author: liurunkai
 * @Date: 2020/2/25 15:14
 */
@Data
@Component
public class LocalCacheUtil {

    public static Cache<String, Object> cache = null;

    @Autowired
    private LocalCacheConfig localCacheConfig;

    /**
     * 初始化cache
     */
    @PostConstruct
    public void initCache() {
        cache = CacheBuilder.newBuilder()
                .concurrencyLevel(localCacheConfig.getConcurrencyLevel())
                .maximumSize(localCacheConfig.getMaxSize())
//                .expireAfterWrite(localCacheConfig.getExpireAfterWrite(), TimeUnit.DAYS)
                .build();
    }

    /**
     * 获取手机号当天呼叫次数
     *
     * @param key
     * @return
     */
    public Integer getPhoneCurDayCallNums(String key) {
        Integer callNums = (Integer) cache.getIfPresent(key);
        if (callNums == null) {
            callNums = 0;
        }
        return callNums;
    }

    /**
     * 设置手机号当天呼叫次数
     *
     * @param key
     * @return
     */
    public void setPhoneCurDayCallNums(String key, Integer callNum) {
        cache.put(key, callNum);
    }

    /**
     * 获取手机号连续呼叫天数
     *
     * @param key
     * @return
     */
    public Integer getPhoneContinueCallDays(String key) {
        Integer callDays = (Integer) cache.getIfPresent(key);
        if (callDays == null) {
            callDays = 0;
        }
        return callDays;
    }

    /**
     * 设置手机号连续呼叫天数
     *
     * @param key
     * @return
     */
    public void setPhoneContinueCallDays(String key, Integer callDay) {
        cache.put(key, callDay);
    }

    /**
     * 获取第二天待启动的任务列表
     *
     * @param key
     */
    public List<TLcRobotCallRecordMeteData> getPhoneToStart(String key) {
        List<TLcRobotCallRecordMeteData> robotTaskList = (List<TLcRobotCallRecordMeteData>) cache.getIfPresent(key);
        if (robotTaskList == null || robotTaskList.size() == 0) {
            robotTaskList = new ArrayList<>();
        }
        return robotTaskList;
    }

    /**
     * 将需要启动的任务添加到第二天待启动的任务列表中
     *
     * @param key
     * @param robotTaskList
     */
    public void addPhoneToStart(String key, List<TLcRobotCallRecordMeteData> robotTaskList) {
        cache.put(key, robotTaskList);
    }

    /**
     * 获取当天需要继续呼叫的号码列表
     *
     * @param key
     */
    public List<TLcTask> getPhoneToCallCurDay(String key) {
        List<TLcTask> robotTaskList = (List<TLcTask>) cache.getIfPresent(key);
        if (robotTaskList == null || robotTaskList.size() == 0) {
            robotTaskList = new ArrayList<>();
        }
        return robotTaskList;
    }

    /**
     * 将需要启动的任务添加到第二天待启动的任务列表中
     *
     * @param key
     * @param continueCallTaskList
     */
    public void addPhoneToCallCurDay(String key, List<TLcTask> continueCallTaskList) {
        cache.put(key, continueCallTaskList);
    }

    /**
     * 删除key
     *
     * @param key
     */
    public void deleteKey(String key) {
        cache.invalidate(key);
    }
}
