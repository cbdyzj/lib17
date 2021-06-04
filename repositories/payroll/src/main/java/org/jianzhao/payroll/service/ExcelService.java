package org.jianzhao.payroll.service;

import org.jianzhao.payroll.support.ExcelBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExcelService {

    public byte[] buildExcel(List<Map<String, String>> header,
                             List<Map<String, String>> data) {
        return new ExcelBuilder(header, data).build();
    }
}
