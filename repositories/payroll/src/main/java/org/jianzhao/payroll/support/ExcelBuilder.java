package org.jianzhao.payroll.support;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 简单在内存中构造一个Excel
 * Using org.apache.poi:poi-ooxml
 *
 * @author cbdyzj
 * @since 2020.6.19
 */
public class ExcelBuilder {

    // 常量
    public static final String HEADER_CODE = "code";
    public static final String HEADER_NAME = "name";
    private static final String DEFAULT_SHEET_NAME = "sheet";

    // 构建上下文
    private List<Map<String, String>> header;
    private List<Map<String, String>> data;
    private final XSSFWorkbook workbook;
    private final XSSFSheet sheet;

    private int rowCount = 0;
    private boolean built = false;

    public ExcelBuilder(@NotNull List<Map<String, String>> header,
                        @NotNull List<Map<String, String>> data) {
        this.checkAndSetHeader(header);
        this.checkAndSetData(data);
        this.workbook = new XSSFWorkbook();
        this.sheet = this.workbook.createSheet(DEFAULT_SHEET_NAME);
    }

    /**
     * 校验设置Header参数
     */
    private void checkAndSetHeader(List<Map<String, String>> header) {
        for (Map<String, String> item : header) {
            Objects.requireNonNull(item.get(HEADER_CODE));
            Objects.requireNonNull(item.get(HEADER_NAME));
        }
        this.header = header;
    }

    /**
     * 校验设置Data参数
     */
    private void checkAndSetData(List<Map<String, String>> data) {
        this.data = data;
    }

    /**
     * 构造Excel
     */
    public byte[] build() {
        this.checkNotBuilt();
        this.addHeader();
        this.addData();
        this.setSheetAutoSizeColumn();
        var written = this.write();
        this.built = true;
        return written;
    }

    /**
     * 增加表头
     */
    private void addHeader() {
        var row = this.sheet.createRow(this.rowCount);
        this.rowCount++;
        for (int i = 0; i < this.header.size(); i++) {
            var cell = row.createCell(i);
            cell.setCellType(CellType.STRING);
            var name = this.header.get(i).get(HEADER_NAME);
            cell.setCellValue(name);
        }
    }

    /**
     * 增加数据
     */
    private void addData() {
        for (Map<String, String> datum : this.data) {
            var row = this.sheet.createRow(this.rowCount);
            for (int i = 0; i < this.header.size(); i++) {
                var code = this.header.get(i).get(HEADER_CODE);
                var value = datum.get(code);
                var cell = row.createCell(i);
                cell.setCellType(CellType.STRING);
                cell.setCellValue(value);
            }
            this.rowCount++;
        }
    }

    /**
     * 写Excel到流
     */
    private byte[] write() {
        try {
            var stream = new ByteArrayOutputStream();
            this.workbook.write(stream);
            return stream.toByteArray();
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    /**
     * 设置自适应
     */
    private void setSheetAutoSizeColumn() {
        for (int i = 0; i < this.header.size(); i++) {
            this.sheet.autoSizeColumn(i);
        }
    }

    /**
     * 检查未build
     */
    private void checkNotBuilt() {
        if (this.built) {
            throw new IllegalStateException("Excel built");
        }
    }
}
