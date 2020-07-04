package com.ruoyi.robot.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.config.RobotAppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Description: 发送请求工具类
 * @author: liurunkai
 * @Date: 2020/2/7 16:57
 */
@Component("com.ruoyi.robot.utils.AiccHttpUtils")
public class AiccHttpUtils {

    @Autowired
    private RobotAppConfig robotAppConfig;

    public RobotResponse sendGet(String url) {
        HttpURLConnection conn = null;
        PrintWriter out = null;
        InputStream in = null;
        RobotResponse robotResponse = null;
        try {
            String newURL = getNewUrlByUrlEncodeParam(url);
            URL realUrl = new URL(newURL);
            /*
             * http header 参数
             */
            String method = "GET";
            String accept = "application/json";
            String content_type = "application/json";
            String date = toGMTString(new Date());
            String sign = AiccSignUtils.generateSign(date, robotAppConfig.getAppKey(), robotAppConfig.getAppSecret());
            // 打开和URL之间的连接
            conn = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestMethod(method);
            conn.setRequestProperty("accept", accept);
            conn.setRequestProperty("content-type", content_type);
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setRequestProperty("datetime", date);
            conn.setRequestProperty("appkey", robotAppConfig.getAppKey());
            conn.setRequestProperty("sign", sign);
            conn.setConnectTimeout(10 * 1000);
            conn.setReadTimeout(600 * 1000);

            // 建立实际的连接
            conn.connect();
            int rc = conn.getResponseCode();
            if (rc == 200) {
                in = conn.getInputStream();
            } else {
                in = conn.getErrorStream();
            }
            String result = changeInputStream(in, "UTF-8");
            robotResponse = resultRever(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            closeConnection(conn, out, in);
        }
        return robotResponse;
    }


    public RobotResponse sendPost(String url, Object object) {
        HttpURLConnection conn = null;
        Writer out = null;
        InputStream in = null;
        RobotResponse robotResponse = null;
        try {
            String newURL = getNewUrlByUrlEncodeParam(url);
            URL realUrl = new URL(newURL);
            /*
             * http header 参数
             */
            String method = "POST";
            String accept = "application/json";
            String content_type = "application/json";
            String date = toGMTString(new Date());
            String sign = AiccSignUtils.generateSign(date, robotAppConfig.getAppKey(), robotAppConfig.getAppSecret());

            // 打开和URL之间的连接
            conn = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestMethod(method);
            conn.setRequestProperty("accept", accept);
            conn.setRequestProperty("content-type", content_type);
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setRequestProperty("datetime", date);
            conn.setRequestProperty("appkey", robotAppConfig.getAppKey());
            conn.setRequestProperty("sign", sign);

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
            // 发送请求参数
            out.write(JSON.toJSONString(object));
            // flush输出流的缓冲
            out.flush();
            int rc = conn.getResponseCode();
            if (rc == 200) {
                in = conn.getInputStream();
            } else {
                in = conn.getErrorStream();
            }
            String result = changeInputStream(in, "UTF-8");
            robotResponse = resultRever(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            closeConnection(conn, out, in);
        }
        return robotResponse;
    }

    /**
     * 将json字符串转对象
     *
     * @param result
     * @return
     */
    private RobotResponse resultRever(String result) {
        JSONObject jsonObject = JSON.parseObject(result);
        RobotResponse robotResponse = new RobotResponse();
        robotResponse.setCode(jsonObject.getInteger("code"))
                .setData(jsonObject.get("data"))
                .setErrorStackTrace(jsonObject.getString("errorStackTrace"))
                .setResultMsg(jsonObject.getString("resultMsg"))
                .setRequestId(jsonObject.getString("requestId"));
        return robotResponse;
    }

    private static void closeConnection(HttpURLConnection conn, Writer out, InputStream in) {
        try {
            if (conn != null) {
                conn.disconnect();
            }
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static String changeInputStream(InputStream inputStream,
                                            String encode) {
        // ByteArrayOutputStream 一般叫做内存流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        String result = "";
        if (inputStream != null) {
            try {
                while ((len = inputStream.read(data)) != -1) {
                    byteArrayOutputStream.write(data, 0, len);
                }
                result = new String(byteArrayOutputStream.toByteArray(), encode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String HMACSha1(String data, String key) {
        String result;
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data.getBytes());
            result = (new BASE64Encoder()).encode(rawHmac);
        } catch (Exception e) {
            throw new Error("Failed to generate HMAC : " + e.getMessage());
        }
        return result;
    }

    public static String toGMTString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z", Locale.UK);
        df.setTimeZone(new java.util.SimpleTimeZone(0, "GMT"));
        return df.format(date);
    }

    /**
     * UrlEncode URL's Param for resolve
     *
     * @param url 原url 包含
     * @return newURL
     * @throws UnsupportedEncodingException
     */
    private static String getNewUrlByUrlEncodeParam(String url) throws UnsupportedEncodingException {
        StringBuilder urlForEncode = new StringBuilder();
        String newURL = "";
        if (url.contains("=")) {
            String[] urlBeforeEncode = url.split("=");
            for (int i = 1; i < urlBeforeEncode.length; i++) {
                if (urlBeforeEncode[i].contains("&")) {
                    String[] parmsplit = urlBeforeEncode[i].split("&");
                    URLEncoder.encode(parmsplit[0], "utf-8");
                    urlBeforeEncode[i] = parmsplit[0] + "&" + parmsplit[1];
                }
                if (i == urlBeforeEncode.length - 1) {
                    urlBeforeEncode[i] = URLEncoder.encode(urlBeforeEncode[i], "utf-8");
                }
                urlForEncode = urlForEncode.append(urlBeforeEncode[i]);
                if (i != urlBeforeEncode.length - 1) {
                    urlForEncode = urlForEncode.append("=");
                }
            }
            newURL = urlBeforeEncode[0] + "=" + urlForEncode.toString();
        } else {
            newURL = url;
        }
        return newURL;
    }

}
