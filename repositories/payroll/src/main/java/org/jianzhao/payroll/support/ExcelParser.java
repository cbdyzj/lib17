package org.jianzhao.payroll.support;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 解析一个简单的Excel文件
 * Using org.apache.poi:poi-ooxml
 *
 * @author cbdyzj
 * @since 2020.7.9
 */
public class ExcelParser {

    private final XSSFSheet sheet;
    private boolean parsed = false;

    public ExcelParser(byte[] excel) {
        try {
            var inputStream = new ByteArrayInputStream(excel);
            var workbook = (XSSFWorkbook) WorkbookFactory.create(inputStream);
            this.sheet = workbook.getSheetAt(0);
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    /**
     * 解析Excel
     */
    public List<Map<String, String>> parse() {
        return this.parse(Function.identity());
    }

    /**
     * 解析Excel
     */
    public List<Map<String, String>> parse(Function<String, String> headerMapFunc) {
        this.checkNotParsed();
        var rowIterator = this.sheet.rowIterator();
        var headerFlag = true;
        var headerList = new ArrayList<String>();
        var result = new ArrayList<Map<String, String>>();
        while (rowIterator.hasNext()) {
            var row = rowIterator.next();
            var rowData = getRowData(row);
            if (headerFlag) {
                var mappedHeaderList = mapHeader(rowData, headerMapFunc);
                headerList.addAll(mappedHeaderList);
                headerFlag = false;
                continue;
            }
            var dataMap = new HashMap<String, String>();
            for (int i = 0; i < headerList.size(); i++) {
                var key = headerList.get(i);
                var value = getCellStringData(row.getCell(i));
                dataMap.put(key, value);
            }
            result.add(dataMap);
        }
        this.parsed = true;
        return result;
    }

    /**
     * header转换
     */
    private static List<String> mapHeader(List<String> headerList, Function<String, String> headerMapFunc) {
        return headerList.stream().map(headerMapFunc).collect(Collectors.toList());
    }


    /**
     * 获取行数据
     */
    private static List<String> getRowData(Row row) {
        var cellIterator = row.cellIterator();
        var cellDataList = new ArrayList<String>();
        while (cellIterator.hasNext()) {
            var cell = cellIterator.next();
            var cellData = getCellStringData(cell);
            cellDataList.add(cellData);
        }
        return cellDataList;
    }

    /**
     * 获取单元的数据
     */
    private static String getCellStringData(Cell cell) {
        if (cell == null) {
            return "";
        }
        return String.valueOf(cell).trim();
    }

    /**
     * 检查未parse
     */
    private void checkNotParsed() {
        if (this.parsed) {
            throw new IllegalStateException("Excel parsed");
        }
    }
}
