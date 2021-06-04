package org.jianzhao.payroll.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class PayrollService {

    private static final String SERIAL_NUMBER = "\u5e8f\u53f7";
    private static final int HEADER_SIZE = 2;
    private static final int SERIAL_NUMBER_COLUMN = 0;
    private static final int NAME_COLUMN = 1;

    public byte[] splitToZip(InputStream inputStream) throws IOException {
        XSSFWorkbook workbook = (XSSFWorkbook) WorkbookFactory.create(inputStream);

        List<Row> headerRows = new ArrayList<>();
        List<Row> dataRows = new ArrayList<>();

        this.read(workbook, headerRows, dataRows);
        return this.write(headerRows, dataRows);
    }

    private void read(XSSFWorkbook workbook, List<Row> headerRows, List<Row> dataRows) {
        XSSFSheet sheet = workbook.getSheetAt(0);
        int start = Integer.MAX_VALUE;
        Iterator<Row> rowIterator = sheet.rowIterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Cell cell = row.getCell(0);
            String s = cell.toString().trim();
            if (start == Integer.MAX_VALUE && SERIAL_NUMBER.equals(s)) {
                start = row.getRowNum();
            }
            if (row.getRowNum() <= start) {
                headerRows.add(row);
            } else {
                dataRows.add(row);
            }
        }
    }

    private byte[] write(List<Row> headerRows, List<Row> dataRows) throws IOException {
        var bookBuffers = new HashMap<String, ByteArrayOutputStream>();
        for (Row dataRow : dataRows) {
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet st = wb.createSheet("sheet");
            int rowCount = 0;
            for (; rowCount < headerRows.size(); rowCount++) {
                Row headerRow = headerRows.get(rowCount);
                Row newRow = st.createRow(rowCount);
                if (rowCount < HEADER_SIZE) {
                    var region = new CellRangeAddress(rowCount, rowCount, 0, headerRow.getLastCellNum() - 1);
                    st.addMergedRegion(region);
                    XSSFCell cell = (XSSFCell) newRow.createCell(0);
                    cell.setCellType(CellType.STRING);
                    cell.setCellValue(headerRow.getCell(0).getStringCellValue());
                } else {
                    this.copyRow(headerRow, newRow);
                }
            }
            XSSFRow newRow = st.createRow(rowCount);
            this.copyRow(dataRow, newRow);
            this.setSheetAutoSizeColumn(st, dataRow.getPhysicalNumberOfCells());
            int sn = Double.valueOf(dataRow.getCell(SERIAL_NUMBER_COLUMN).toString()).intValue();
            String name = dataRow.getCell(NAME_COLUMN).toString();
            var byteArrayOutputStream = new ByteArrayOutputStream();
            wb.write(byteArrayOutputStream);
            bookBuffers.put(sn + "-" + name, byteArrayOutputStream);
        }

        var zipBuffer = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(zipBuffer);
        for (Map.Entry<String, ByteArrayOutputStream> entry : bookBuffers.entrySet()) {
            zipOutputStream.putNextEntry(new ZipEntry(entry.getKey() + ".xlsx"));
            entry.getValue().writeTo(zipOutputStream);
            zipOutputStream.closeEntry();
        }
        zipOutputStream.close();
        return zipBuffer.toByteArray();
    }

    private void setSheetAutoSizeColumn(XSSFSheet sheet, int columnSize) {
        for (int i = 0; i < columnSize; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private void copyRow(Row from, Row to) {
        Iterator<Cell> cellIterator = from.cellIterator();
        while (cellIterator.hasNext()) {
            XSSFCell cell = (XSSFCell) cellIterator.next();
            XSSFCell newCell = (XSSFCell) to.createCell(cell.getColumnIndex());
            newCell.setCellType(CellType.STRING);
            CellType cellType = cell.getCellType();
            switch (cellType) {
                case NUMERIC:
                case FORMULA:
                    String format = "%.2f";
                    if (cell.getColumnIndex() == SERIAL_NUMBER_COLUMN) {
                        format = "%.0f";
                    }
                    newCell.setCellValue(String.format(format, cell.getNumericCellValue()));
                    break;
                case STRING:
                default:
                    newCell.setCellValue(cell.getStringCellValue());
            }
        }
    }
}
