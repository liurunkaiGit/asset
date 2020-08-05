package com.ruoyi.common.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author guozeqi
 * @create 2020-08-06
 */

public class AesUtils {
     private static final String TOKEN = "JV7evvhcvxTRqYHa";//我们的token

     /**
     * 加密
     *
     * @param content
     *消息内容
     * @param token
     *密钥
     * @return 密文
     * @throws Exception
     *抛异常*/
    public static String encrypt(String content, String token)throws Exception{
        if (token == null || token.length() != 16 || content == null){
            return null;
        }
        byte[] aesKey = token.getBytes("UTF-8");
        SecretKeySpec skeySpec = new SecretKeySpec(aesKey, "AES");
        // 使用CBC 模式，需要一个向量iv，可增加加密算法的强度
        IvParameterSpec iv = new IvParameterSpec(aesKey, 0, 16);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(content.getBytes("UTF-8"));
        //此处使用BASE64 做转码。
        return Base64.getEncoder().encodeToString(encrypted);

    }
    /**
     * 解密
     *
     * @param content
     *密文
     * @param token
     * 密钥
     * @return 消息内容
     * @throws Exception
     * 抛异常
     */
    public static String decrypt(String content, String token) throws Exception{
        if (token== null || token.length() != 16 || content == null){
            return null;
        }
        content = formatContent(content);
        byte[] aesKey = token.getBytes("UTF-8");
        SecretKeySpec skeySpec = new SecretKeySpec(aesKey, "AES");
        // 使用CBC 模式，需要一个向量iv，可增加加密算法的强度
        IvParameterSpec iv = new IvParameterSpec(aesKey, 0, 16);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        // 先用base64 解密
        byte[] encrypted1 = Base64.getDecoder().decode(content);
        byte[] original = cipher.doFinal(encrypted1);
        String originalString = new String(original, "UTF-8");
        return originalString;
    }

    /**
     * 格式化加密的消息体去除换行符
     ** @param content
     * 加密的消息体
     * @return 格式化后加密的消息体
     */
    public static String formatContent(String content) {
        String s = content;
        // 1.去掉引号
        if (content.contains("\"")) {
            s = s.substring(1, s.length() - 1);
        }
        // 2.替换\r 已经被转义的字符
        if (content.contains("\\r")) {
            s = s.replace("\\r", "\r");
        }
        // 3.替换\n 已经被转义的字符
        if (content.contains("\\n")) {
            s = s.replace("\\n", "\n");
        }
        return s;

    }
    /**
     * 用SHA1 算法生成安全签名
     *
     * @param token
     * 票据
     * @param timestamp
     * 时间戳
     * @param nonce
     * 随机字符串
     * @param encrypt
     * 密文
     * @return 安全签名
     * @throws Exception
     */
    public static String getSHA1(String token, String timestamp, String nonce, String encrypt) throws Exception{
        String[] array = new String[] { token, timestamp, nonce, encrypt};
        StringBuilder sb = new StringBuilder();
        // 字符串排
        Arrays.sort(array);
        for (int i = 0; i < 4; i++) {
            sb.append(array[i]);
        }
        String str = sb.toString();
        // SHA1 签名生成
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(str.getBytes());
        byte[] digest = md.digest();
        StringBuilder hexstr = new StringBuilder();
        String shaHex = "";
        for (int i = 0; i < digest.length; i++) {
            shaHex = Integer.toHexString(digest[i] & 0xFF);
            if (shaHex.length() < 2) {
                hexstr.append(0);
            }
            hexstr.append(shaHex);
        }
        return hexstr.toString();
    }



    public static void main(String[] args) throws Exception {
        String content = "春天到了，桃花绽开了美丽的笑脸，杜鹃花也向我们展示着它最艳丽的花儿，不过花中皇后要数茉莉花了，它们开着一朵朵小喇叭似的花朵，有紫的、淡紫的、白的……五彩缤纷。美丽的茉莉花在春风里频频点头为我们送来阵阵清香……";
        //生成16位随机字符串
        String token= RandomStringUtils.randomAlphanumeric(16);
        System.out.println(token);
        System.out.println(token.length());
        //加密
        String encrypt = AesUtils.encrypt(content, token);
        System.out.println(encrypt);
        //解密
        String decrypt = AesUtils.decrypt(encrypt, token);
        System.out.println(decrypt);

    }


}
