package com.ruoyi.assetspackage.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author guozeqi
 * @create 2019-12-13
 */

public class ParseExcelUtil {

    public static List<Map<String,String>> resolveExcel(String fileUrl,int headRowNum,int dataRowNum) throws Exception{
        Workbook wb =null;
        Sheet sheet = null;
        Row row = null;
        String cellData = null;
        List<String> head = new ArrayList<String>();
        List<Map<String,String>> data = new ArrayList<Map<String,String>>();
        wb = readExcel(fileUrl);
        if(wb != null){
            //获取第一个sheet
            sheet = wb.getSheetAt(0);
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            row = sheet.getRow(headRowNum-1);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            //存放表头
            for(int l=0;l<colnum;l++){
                head.add((String) getCellFormatValue(row.getCell(l)));
            }
            for (int i = dataRowNum-1; i<rownum; i++) {
                //存放每一列的名值对
                Map<String,String> map = new LinkedHashMap<String,String>();
                row = sheet.getRow(i);
                if(row !=null){
                    for (int j=0;j<colnum;j++){
                        Cell cell = row.getCell(j);
//                        if (cell != null) {
//                            cell.setCellType(CellType.STRING);
//                        }
                        cellData = getCellFormatValue(cell);
                        map.put(head.get(j), cellData);
                    }
                }else{
                    break;
                }
                data.add(map);
            }
        }
        wb.close();
        return data;
    }

    //读取excel
    private static Workbook readExcel(String filePath) throws Exception{
        Workbook wb = null;
        if(filePath==null){
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        is = new FileInputStream(filePath);
        if (".xls".equals(extString)) {
            return wb = new HSSFWorkbook(is);
        } else if (".xlsx".equals(extString)) {
            return wb = new XSSFWorkbook(is);
        }
        return wb;
    }

    private static String getCellFormatValue(Cell cell){
        String cellValue = null;
        if(cell!=null){
            //判断cell类型
            switch(cell.getCellTypeEnum()){
                case NUMERIC:{
                   /* cellValue = String.valueOf(cell.getNumericCellValue());*/
//                    DecimalFormat format = new DecimalFormat("#.00");
//                    cellValue = format.format(cell.getNumericCellValue());
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date dateCellValue = cell.getDateCellValue();
                        try {
                            cellValue = DateFormatUtils.format(dateCellValue, "yyyy/MM/dd");
                        } catch (Exception e) {
                            try {
                                cellValue = DateFormatUtils.format(dateCellValue, "yyyy-MM-dd");
                            } catch (Exception ex) {
                                cellValue = DateFormatUtils.format(dateCellValue, "yyyyMMdd");
                            }
                        }
                    } else {
                        DataFormatter dataFormatter = new DataFormatter();

                        cellValue = dataFormatter.formatCellValue(cell);
//                        cellValue = String.valueOf(cell.getNumericCellValue());
//                        if (String.valueOf(cellValue).substring(String.valueOf(cellValue).length() - 3,String.valueOf(cellValue).length() - 2).equals(".")) {
//                            DecimalFormat format = new DecimalFormat("#.00");
//                            cellValue = format.format(cell.getNumericCellValue());
//                        } else {
//                            DecimalFormat format = new DecimalFormat("0");
//                            cellValue = format.format(cell.getNumericCellValue());
//                        }
                    }
                    break;
                }
                case STRING:{
//                    cellValue = cell.getRichStringCellValue().getString();
                    cellValue = cell.getStringCellValue();
                    break;
                }
                default:
                    cell.setCellType(CellType.STRING);
                    cellValue = cell.getStringCellValue();
            }
        }else{
            cellValue = "";
        }
        return cellValue;
    }




    public static List<String> resolveExcelHead(String fileUrl) throws Exception{
        Workbook wb =null;
        Sheet sheet = null;
        Row row = null;
        List<String> head = new ArrayList<String>();
        wb = readExcel(fileUrl);
        if(wb != null){
            //获取第一个sheet
            sheet = wb.getSheetAt(0);
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            //存放表头
            for(int l=0;l<colnum;l++){
                head.add((String) getCellFormatValue(row.getCell(l)));
            }

        }
        wb.close();
        return head;
    }


}
