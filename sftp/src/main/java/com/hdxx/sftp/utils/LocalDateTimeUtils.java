package com.hdxx.sftp.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/11/20 16:17
 */
public class LocalDateTimeUtils {

    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * 获取某一天最小时间
     */
    public static Date getMinDate(LocalDate localDate) {
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.MIN);
        Date minDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return minDate;
    }

    /**
     * 获取某一天最大时间
     */
    public static Date getMaxDate(LocalDate localDate) {
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.MAX);
        Date maxDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return maxDate;
    }

    /**
     * localDateTime时间格式化
     *
     * @param localDateTime
     * @param format
     * @return
     */
    public static String format(LocalDateTime localDateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(localDateTime);
    }
}
