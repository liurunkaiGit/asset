package com.ruoyi.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.druid.util.StringUtils;



/**
 * 文件操作
 */
public class CSVUtils {

    private static String Separator = "|";
    /**
     * 每个文件的数据条数
     */
    private static int pageSize = 1000000;


    /**
     * 功能说明：获取UTF-8编码文本文件开头的BOM签名。
     * BOM(Byte Order Mark)，是UTF编码方案里用于标识编码的标准标记。例：接收者收到以EF BB BF开头的字节流，就知道是UTF-8编码。
     * @return UTF-8编码文本文件开头的BOM签名
     */
    public static String getBOM() {

        byte b[] = {(byte)0xEF, (byte)0xBB, (byte)0xBF};
        return new String(b);
    }

    /**
     * 生成CVS文件
     * @param exportData
     *       源数据List
     * @param map
     *       csv文件的列表头map
     * @param outPutPath
     *       文件路径
     * @param fileName
     *       文件名称
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static File createCSVFile(List<Map<String,Object>> exportData, LinkedHashMap map, String outPutPath,
                                     String fileName) {
        File csvFile = null;
        BufferedWriter csvFileOutputStream = null;
        try {
            File file = new File(outPutPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            //定义文件名格式并创建
            csvFile =new File(outPutPath+fileName);
            file.createNewFile();
            // UTF-8使正确读取分隔符","
            //如果生产文件乱码，windows下用gbk，linux用UTF-8
            csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    csvFile), "UTF-8"), 1024);

            //写入前段字节流，防止乱码
//            csvFileOutputStream.write(getBOM());
            // 写入文件头部
            if(map != null){
                for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext();) {
                    java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
                    csvFileOutputStream.write((String) propertyEntry.getValue() != null ? (String) propertyEntry.getValue() : "" );
                    if (propertyIterator.hasNext()) {
                        csvFileOutputStream.write(Separator);
                    }
                }
                csvFileOutputStream.newLine();
            }

            // 写入文件内容
            if(exportData != null && exportData.size() > 0){
                for( int i = 0; i < exportData.size(); i++ ){
                    Map<String,Object> row = exportData.get(i);
                    for(Iterator propertyIterator = row.entrySet().iterator(); propertyIterator.hasNext();){
                        java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator
                                .next();
                        String str = row != null?((String)(row).get( propertyEntry.getKey())):"";
                        if(StringUtils.isEmpty(str)){
                            str="";
                        }else{
                            str=str.replaceAll("\"","\"\"");
                            if(str.indexOf(",")>=0){
                                str="\""+str+"\"";
                            }
                        }
                        csvFileOutputStream.write(str);
                        if (propertyIterator.hasNext()) {
                            csvFileOutputStream.write(Separator);
                        }
                    }
                    csvFileOutputStream.newLine();
                }
                /*for (Iterator iterator = exportData.iterator(); iterator.hasNext();) {
                    Object row = (Object) iterator.next();
                    for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator
                            .hasNext();) {
                        java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator
                                .next();
                        String str = row != null?((String)((Map)row).get( propertyEntry.getKey())):"";
                        if(StringUtils.isEmpty(str)){
                            str="";
                        }else{
                            str=str.replaceAll("\"","\"\"");
                            if(str.indexOf(",")>=0){
                                str="\""+str+"\"";
                            }
                        }
                        csvFileOutputStream.write(str);
                        if (propertyIterator.hasNext()) {
                            csvFileOutputStream.write(Separator);
                        }
                    }
                    if (iterator.hasNext()) {
                        csvFileOutputStream.newLine();
                    }
                }*/
            }
            csvFileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvFileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }

    /**
     *     生成并下载csv文件
     * @param response
     * @param exportData
     * @param map
     * @param outPutPath
     * @param fileName
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public static void exportDataFile(HttpServletResponse response,List exportData, LinkedHashMap map, String outPutPath,String fileName) throws IOException{
        File csvFile = null;
        BufferedWriter csvFileOutputStream = null;
        try {
            File file = new File(outPutPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            //定义文件名格式并创建
            csvFile =new File(outPutPath+fileName+".csv");
            if(csvFile.exists()){
                csvFile.delete();
            }
            csvFile.createNewFile();
            // UTF-8使正确读取分隔符","
            //如果生产文件乱码，windows下用gbk，linux用UTF-8
            csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "UTF-8"), 1024);
            //写入前段字节流，防止乱码
            csvFileOutputStream.write(getBOM());
            // 写入文件头部
            for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext();) {
                java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
                csvFileOutputStream.write((String) propertyEntry.getValue() != null ? (String) propertyEntry.getValue() : "" );
                if (propertyIterator.hasNext()) {
                    csvFileOutputStream.write(",");
                }
            }
            csvFileOutputStream.newLine();
            // 写入文件内容
            for (Iterator iterator = exportData.iterator(); iterator.hasNext();) {
                Object row = (Object) iterator.next();
                for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator
                        .hasNext();) {
                    java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator
                            .next();
                    String str=row!=null?((String)((Map)row).get( propertyEntry.getKey())):"";
                    if(StringUtils.isEmpty(str)){
                        str="";
                    }else{
                        str=str.replaceAll("\"","\"\"");
                        if(str.indexOf(",")>=0){
                            str="\""+str+"\"";
                        }
                    }
                    csvFileOutputStream.write(str);
                    if (propertyIterator.hasNext()) {
                        csvFileOutputStream.write(",");
                    }
                }
                if (iterator.hasNext()) {
                    csvFileOutputStream.newLine();
                }
            }
            csvFileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvFileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




        InputStream in = null;
        try {
            in = new FileInputStream(outPutPath+fileName+".csv");
            int len = 0;
            byte[] buffer = new byte[1024];

            OutputStream out = response.getOutputStream();
            response.reset();

            response.setContentType("application/csv;charset=UTF-8");
            response.setHeader("Content-Disposition","attachment; filename=" + URLEncoder.encode(fileName+".csv", "UTF-8"));
            response.setCharacterEncoding("UTF-8");
            while ((len = in.read(buffer)) > 0) {
                out.write(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF });
                out.write(buffer, 0, len);
            }
            out.close();
        } catch (FileNotFoundException e) {
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    /**
     * 删除该目录filePath下的所有文件
     * @param filePath
     *      文件目录路径
     */
    public static void deleteFiles(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    files[i].delete();
                }
            }
        }
    }

    /**
     * 删除单个文件
     * @param filePath
     *     文件目录路径
     * @param fileName
     *     文件名称
     */
    public static void deleteFile(String filePath, String fileName) {
        File file = new File(filePath);
        if (file.exists()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    if (files[i].getName().equals(fileName)) {
                        files[i].delete();
                        return;
                    }
                }
            }
        }
    }

    /**
     * 得到一共需要生成几个文件
     * @param countSize 数据的总条数
     * @return
     */
    public static int getCountNum(int countSize){
        int a = countSize/pageSize;
        return a + 1 ;
    }

    /**
     * 得到一共需要生成几个文件
     * @param countSize 数据的总条数
     * @param pageSize  每个文件的数据
     * @return
     */
    public static int getCountNum(int countSize,int pageSize){
        int a = countSize/pageSize;
        return a + 1 ;
    }

    /**
     * 得到文件的尾号  如：001
     * @param no
     * @return
     */
    public static String getNo(String no){
        int length = no.length();
        if(length == 1){
            return "00"+no;
        }else if(length == 2){
            return "0"+no;
        }else if(length == 3){
            return no;
        }

        return null;
    }

    /**
     * 测试数据
     * @param args
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        List exportData = new ArrayList<Map>();
        Map row1 = new LinkedHashMap<String, String>();
        row1.put("1", "11");
        row1.put("2", "12");
        row1.put("3", "13");
        row1.put("4", "14");
        exportData.add(row1);
        for(int i = 0; i < 1000;i++){
            row1 = new LinkedHashMap<String, String>();
            row1.put("1", "21");
            row1.put("2", "22");
            row1.put("3", "23");
            row1.put("4", "24");
            exportData.add(row1);
        }

        LinkedHashMap map = new LinkedHashMap();

        //设置列名
        map.put("1", "第一列名称");
        map.put("2", "第二列名称");
        map.put("3", "第三列名称");
        map.put("4", "第四列名称");
        //这个文件上传到路径，可以配置在数据库从数据库读取，这样方便一些！
        String path = "E:/";

        //文件名=生产的文件名称+时间戳
        String fileName = "文件导出";
        File file = CSVUtils.createCSVFile(exportData, null, path, fileName);
        String fileName2 = file.getName();
        System.out.println("文件名称：" + fileName2);
        long t2 = System.currentTimeMillis();
        System.out.println("CSV: " + (t2 - t1));
    }
}
