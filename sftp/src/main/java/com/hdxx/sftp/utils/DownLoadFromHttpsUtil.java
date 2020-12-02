package com.hdxx.sftp.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.net.ssl.*;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @Description: 从https的url下载文件 从http的url下载文件
 * @author: liurunkai
 * @Date: 2020/2/27 15:41
 */
@Slf4j
public class DownLoadFromHttpsUtil implements X509TrustManager {

    /**
     * 从https的地址下载文件
     *
     * @param url 下载地址https
     */
    public static void downLoadFromHttps(HttpServletResponse response, String url) {
        log.info("开始下载，下载地址{}", url);
        if (!(url == null || StringUtils.equals("", url))) {
            if (!(url == null || StringUtils.equals("", url))) {
                String[] arr1 = url.split("/");
                if (arr1.length > 0) {
                    String fileName = arr1[arr1.length - 1];
                    fileName = (fileName != null && fileName.indexOf("?") == -1) ? fileName : (fileName.substring(0, fileName.indexOf("?")));
                    log.info("文件名{}", fileName);
                    try {
                        downLoadFromUrlHttps(response, url, fileName);
                        log.info("下载完成，下载地址{}", url);
                    } catch (Exception e) {
                        log.error("下载异常：{}", e);
                    }
                }
            }
        }
    }

    /**
     * 从https地址下载文件
     *
     * @param urlStr
     * @param fileName
     * @throws Exception
     */
    public static void downLoadFromUrlHttps(HttpServletResponse response, String urlStr, String fileName) throws Exception {
        // 创建SSLContext
        SSLContext sslContext = SSLContext.getInstance("SSL");
        TrustManager[] tm = {new DownLoadFromHttpsUtil()};
        // 初始化
        sslContext.init(null, tm, new java.security.SecureRandom());
        // 获取SSLSocketFactory对象
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        // url对象
        URL url = new URL(urlStr);
        // 打开连接
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        /**
         * 这一步的原因: 当访问HTTPS的网址。您可能已经安装了服务器证书到您的JRE的keystore
         * 但是服务器的名称与证书实际域名不相等。这通常发生在你使用的是非标准网上签发的证书。
         *
         * 解决方法：让JRE相信所有的证书和对系统的域名和证书域名。
         *
         * 如果少了这一步会报错:java.io.IOException: HTTPS hostname wrong: should be <localhost>
         */
        conn.setHostnameVerifier(new DownLoadFromHttpsUtil().new TrustAnyHostnameVerifier());
        // 设置一些参数
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        // 设置当前实例使用的SSLSoctetFactory
        conn.setSSLSocketFactory(ssf);
        conn.connect();

        response.reset();
        response.setHeader("Content-Type", "application/octet-stream");
        response.setHeader("Content-Transfer-Encoding", "chunked");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/OCTET-STREAM");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename="
                + new String(fileName.getBytes("gb2312"), "iso-8859-1"));

        // 得到输入流
        InputStream inputStream = conn.getInputStream();
        byte[] getData = readInputStream(inputStream);
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(getData);
        if (outputStream != null) {
            outputStream.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }
    }

    /**
     *      * 下载文件到本地
     *      * 封志涛添加  录音文件下载到本地
     *      * @param fileUrl   远程地址
     *      * @param localFilePath 本地路径
     *
     * @param fileName 文件名称
     *                      * @throws Exception
     *                     
     */
    public static void downloadFileForHttps(String fileUrl, String localFilePath, String fileName) throws Exception {
        SSLContext sslcontext = SSLContext.getInstance("SSL", "SunJSSE");
        TrustManager[] tm = {new DownLoadFromHttpsUtil()};
        sslcontext.init(null, tm, new java.security.SecureRandom());
        URL url = new URL(fileUrl);
        HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
            public boolean verify(String s, SSLSession sslsession) {
                System.out.println("WARNING: Hostname is not matched for cert.");
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
        HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());
        HttpsURLConnection urlCon = (HttpsURLConnection) url.openConnection();
        urlCon.setConnectTimeout(6000);
        urlCon.setReadTimeout(6000);
        int code = urlCon.getResponseCode();
        if (code != HttpURLConnection.HTTP_OK) {
//            throw new Exception("文件读取失败");
            log.error("文件读取失败");
        }

        // 得到输入流
        InputStream inputStream = urlCon.getInputStream();
        byte[] getData = readInputStream(inputStream);
        // 文件保存位置
        File saveDir = new File(localFilePath);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }
        // 输出流
        File file = new File(saveDir + File.separator + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        //保存文件
        bos.write(getData);
        bos.close();
        if (fos != null) {
            fos.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }
    }

    /**
     * 从网络http类型Url中下载文件
     *
     * @param urlStr
     * @param fileName
     * @param savePath
     * @throws IOException
     */
    public static void downLoadFromUrlHttp(String urlStr, String savePath, String fileName) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 设置超时间为3秒
        conn.setConnectTimeout(3 * 1000);
        // 防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent",
                "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        conn.connect();

        // 得到输入流
        InputStream inputStream = conn.getInputStream();
        byte[] getData = readInputStream(inputStream);
        // 文件保存位置
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }
        // 输出流
        File file = new File(saveDir + File.separator + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if (fos != null) {
            fos.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }
    }


    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream)
            throws IOException {
        byte[] b = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(b)) != -1) {
            bos.write(b, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }


    /***
     * 校验https网址是否安全
     *
     * @author solexit06
     *
     */
    public class TrustAnyHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            // 直接返回true:默认所有https请求都是安全的
            return true;
        }
    }

    /**
     * implements X509TrustManager的实现方法，空方法：认为所有的链接都为安全链接
     *
     * @param x509Certificates
     * @param s
     * @throws CertificateException
     */
    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

    }

    /**
     * implements X509TrustManager的实现方法，空方法：认为所有的链接都为安全链接
     *
     * @param x509Certificates
     * @param s
     * @throws CertificateException
     */
    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

    }

    /**
     * implements X509TrustManager的实现方法，空方法：认为所有的链接都为安全链接
     *
     * @return
     */
    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }


}
