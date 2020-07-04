package com.ruoyi.assetspackage.util;


import lombok.extern.slf4j.Slf4j;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.slf4j.Logger;

import java.io.*;

/**
 * @author guozeqi
 * @create 2020-01-17
 */
@Slf4j
public class FastDFSUtil {
    private static TrackerClient trackerClient = null;
    private static TrackerServer trackerServer = null;
    private static StorageServer storageServer = null;
    private static StorageClient storageClient = null;


    static {
        try {
            ClientGlobal.init("configure");
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageClient = new StorageClient(trackerServer, storageServer);
        } catch (IOException | MyException e) {
            throw new RuntimeException("FastDfs工具类初始化失败!");
        }
    }

    /**
     *
     * @Title: fdfsUpload
     * @Description: 通过文件流上传文件
     * @param @param inputStream 文件流
     * @param @param filename 文件名称
     * @param @return
     * @param @throws IOException
     * @param @throws MyException
     * @return String 返回文件在FastDfs的存储路径
     * @throws
     */
    public static String UploadFile(InputStream inputStream, String filename) throws IOException, MyException{
        String suffix = ""; //后缀名
        try{
            suffix = filename.substring(filename.lastIndexOf(".")+1);
        }catch (Exception e) {
            throw new RuntimeException("参数filename不正确!格式例如：a.png");
        }
        String savepath = ""; //FastDfs的存储路径
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buff)) != -1) {
            swapStream.write(buff, 0, len);
        }
        byte[] in2b = swapStream.toByteArray();
        String[] strings = storageClient.upload_file(in2b, suffix, null); //上传文件
        for (String str : strings) {
            savepath += "/" + str; //拼接路径
        }
        return savepath;
    }

    /**
     *
     * @Title: fdfsUpload
     * @Description: 本地文件上传
     * @param @param filepath 本地文件路径
     * @param @return
     * @param @throws IOException
     * @param @throws MyException
     * @return String 返回文件在FastDfs的存储路径
     * @throws
     */
    public static String UploadFile(String filepath) throws IOException, MyException{
        String suffix = ""; //后缀名
        String savepath = ""; //FastDfs的存储路径
        try{
            suffix = filepath.substring(filepath.lastIndexOf(".")+1);
            String[] strings = storageClient.upload_file(filepath, suffix, null); //上传文件
            for (String str : strings) {
                savepath += "/" + str; //拼接路径
            }
        }catch (Exception e) {
            log.info("=================fastdfs异常："+e);
            e.printStackTrace();
            throw new RuntimeException("上传的不是文件!");

        }

        return savepath;
    }


    /**
     *
     * @Title: fdfsDownload
     * @Description: 返回文件输入流
     * @param @param savepath 文件存储路径
     * @param @return
     * @param @throws IOException
     * @param @throws MyException
     * @return InputStream
     * @throws
     */
    public static byte[] DownloadFile(String savepath) throws IOException, MyException{
        byte[] bs = null;
        String group = ""; //存储组
        String path = ""; //存储路径
        try{
            int secondindex = savepath.indexOf("/", 2);
            group = savepath.substring(1, secondindex);
            path = savepath.substring(secondindex + 1);
        }catch (Exception e) {
            throw new RuntimeException("传入文件存储路径不正确");
        }
        bs = storageClient.download_file(group, path); //
        return bs;
    }



}
