package org.jianzhao.java.excel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;

public final class PoiWriteDemo {

    public static void main(String... args) throws Exception {
        var workbook = new XSSFWorkbook();
        var sheet = workbook.createSheet("s1");
        var r0 = sheet.createRow(0);
        r0.createCell(0).setCellValue("名字");
        r0.createCell(1).setCellValue("年龄");
        var os = new FileOutputStream("x.xlsx");
        workbook.write(os);
    }
}
