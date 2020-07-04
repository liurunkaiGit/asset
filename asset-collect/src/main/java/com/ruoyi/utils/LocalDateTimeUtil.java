package com.ruoyi.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @Description: LocalDateTimeUtil工具类
 * @author: liurunkai
 * @Date: 2020/3/16 14:18
 */
public class LocalDateTimeUtil {

    /**
     * LocalDate 转 Date
     *
     * @param localDate
     * @return
     */
    public static Date date(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }
}
