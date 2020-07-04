package com.ruoyi.robot.utils;

/**
 * @Description: 计算签名的工具类
 * @author: liurunkai
 * @Date: 2020/2/7 16:57
 */
public class AiccSignUtils {

    public static String generateSign(String date, String appKey, String appSecret) {
        String stringToSign = appKey + "\n" + date;
        return AiccHttpUtils.HMACSha1(stringToSign, appSecret);
    }
}
