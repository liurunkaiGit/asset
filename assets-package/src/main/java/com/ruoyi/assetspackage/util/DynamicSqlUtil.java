package com.ruoyi.assetspackage.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guozeqi
 * @create 2020-04-14
 */
@Slf4j
@Component
public class DynamicSqlUtil{
    @Autowired
    private Environment env;
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;


    public Map<String,Object> toQuery(String sql){
        Map<String,Object> result = new HashMap<String,Object>();
        List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
        List<String> heads = new ArrayList<String>();
        try {
            conn=getConnection();
            ps = conn.prepareStatement(sql);
            rs=ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int colum = metaData.getColumnCount();

            for (int i = 1; i <= colum; i++) {
                String columName = metaData.getColumnName(i);
                heads.add(columName);
            }

            while (rs.next()) {
                Map<String,Object> row = new HashMap<String,Object>();
                for (int i = 1; i <= colum; i++) {
                    String columName = metaData.getColumnName(i);
                    String columValue = rs.getString(columName);
                    row.put(columName,columValue);
                }
                datas.add(row);
            }

            result.put("datas",datas);
            result.put("heads",heads);
        } catch (Exception e) {
            log.error("执行toQuery方法异常"+e.getMessage(),e);
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        return result;
    }


    public int toExecute(String sql) throws Exception{
        int result = 0;
        try {
            conn=getConnection();
            ps = conn.prepareStatement(sql);
            result = ps.executeUpdate();
        } catch (Exception e) {
            log.error("执行toExcute方法异常"+e.getMessage(),e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            closeConnection();
        }

        return result;
    }


    private Connection getConnection() throws Exception {
        Class.forName(env.getProperty("spring.datasource.driverClassName"));
        String url = env.getProperty("spring.datasource.druid.master.url");
        String user = env.getProperty("spring.datasource.druid.master.username");
        String password = env.getProperty("spring.datasource.druid.master.password");
        conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    private void closeConnection(){
        if(rs!=null){
            try {
                rs.close();
            } catch (Exception e) {
                log.error("关闭结果集ResultSet异常！"+e.getMessage(), e);
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (Exception e) {
                log.error("预编译SQL语句对象PreparedStatement关闭异常！"+e.getMessage(), e);
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (Exception e) {
                log.error("关闭连接对象Connection异常！"+e.getMessage(), e);
            }
        }
    }


    public HSSFWorkbook getHSSFWorkbook(String sheetName,Map<String, Object> queryResult){
        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);
        //声明列对象
        HSSFCell cell = null;
        //设置默认行宽
        sheet.setDefaultColumnWidth(25);
        // 标题样式（加粗，垂直居中）
        HSSFCellStyle cellStyle = wb.createCellStyle();
        HSSFFont fontStyle = wb.createFont();
        fontStyle.setBold(true);   //加粗
        fontStyle.setFontHeightInPoints((short)14);  //设置标题字体大小
        cellStyle.setFont(fontStyle);

        //获取头
        /*Set<String> title = queryResult.get(0).keySet();
        List<String > headList = new ArrayList<String >();
        for (String tit : title) {
            headList.add(tit);

        }*/
        List<String > headList = (List<String>) queryResult.get("heads");
        //创建标题
        for(int i=0;i<headList.size();i++){
            cell = row.createCell(i);
            cell.setCellValue(headList.get(i));
            cell.setCellStyle(cellStyle);
        }

        //创建内容
        List<Map<String,Object>> datas = (List<Map<String,Object>>)queryResult.get("datas");
        for(int i=0;i<datas.size();i++){
            Map<String, Object> values = datas.get(i);
            row = sheet.createRow(i + 1);
            for(int j=0;j<headList.size();j++){
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue((String) values.get(headList.get(j)));
            }
        }
        return wb;
    }




}
