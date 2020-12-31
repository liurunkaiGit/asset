package com.ruoyi.task.domain.preTestCall.createTask;

import com.alibaba.fastjson.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author guozeqi
 * @create 2020-12-17
 */

public class ConvertUtil {

    /**
     * 时间转换
     * @param time
     * @return
     */
    public static String convertDate(Long time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        date.setTime(time);
        String format = simpleDateFormat.format(date);
        return format;
    }

    public static Date str2Date(String date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date format = null;
        try {
            format = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format;
    }

    /**
     * 计划状态转换
     * @param status
     * @return
     */
    public static String convertStatus(String status){
        if("UPLOADING".equals(status)){
            status = "上传中";
        }else if("NOT_STARTED".equals(status)){
            status = "未开始";
        }else if("IN_PROGRESS".equals(status)){
            status = "执行中";
        }else if("PAUSED".equals(status)){
            status = "已暂停";
        }else if("CANCELLED".equals(status)){
            status = "已取消";
        }else if("FINISHED".equals(status)){
            status = "已完成";
        }
        return status;
    }




}
