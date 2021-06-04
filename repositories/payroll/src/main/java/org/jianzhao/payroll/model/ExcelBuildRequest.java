package org.jianzhao.payroll.model;

import java.util.List;
import java.util.Map;

public class ExcelBuildRequest {

    private List<Map<String, String>> header;
    private List<Map<String, String>> data;

    public List<Map<String, String>> getHeader() {
        return header;
    }

    public void setHeader(List<Map<String, String>> header) {
        this.header = header;
    }

    public List<Map<String, String>> getData() {
        return data;
    }

    public void setData(List<Map<String, String>> data) {
        this.data = data;
    }
}
