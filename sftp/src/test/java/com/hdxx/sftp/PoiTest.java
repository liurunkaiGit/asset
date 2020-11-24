package com.hdxx.sftp;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/11/24 11:11
 */
public class PoiTest {

    @Test
    public void createExcel() throws IOException {
        String fileName = "导出Excel.xlsx"; //创建名称
        String rpath = "D:";
        String rpathfinal = rpath + "/" + fileName;//路径

        SXSSFWorkbook wb = new SXSSFWorkbook(1024); // 这里1024是在内存中的数量，如果大于此数量时，会写到硬盘，以避免在内存导致内存溢出
        Sheet sh = wb.createSheet();
        for (int i = 0; i < 3 + 1; i++) {
            sh.setColumnWidth(i, 4500);
            Row row = sh.createRow(i);
            if (i == 0) {
                row.createCell(0).setCellValue("xxxx");
                row.createCell(1).setCellValue("aaaa");
                row.createCell(2).setCellValue("bbbb");
                row.createCell(3).setCellValue("cccc");
            } else {
                row.createCell(0).setCellValue("belong");
                row.createCell(1).setCellValue("dqbj");
                row.createCell(2).setCellValue("datasource");
                row.createCell(3).setCellValue("tablename");
            }
        }
        FileOutputStream output = new FileOutputStream(rpathfinal);
        wb.write(output);
        output.close();
    }
}
