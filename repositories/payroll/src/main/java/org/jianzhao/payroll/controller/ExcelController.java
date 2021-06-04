package org.jianzhao.payroll.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jianzhao.payroll.model.ExcelBuildRequest;
import org.jianzhao.payroll.service.ExcelService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Api(tags = "Excel")
@CrossOrigin
@RestController
@RequestMapping("/api/excel")
public class ExcelController {

    public static final String MIME_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    private final ExcelService excelService;

    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @ApiOperation("Json2Excel")
    @PostMapping(path = "/build", produces = MIME_TYPE)
    public ResponseEntity<byte[]> buildExcel(@RequestBody ExcelBuildRequest excelBuildRequest) {
        byte[] excel = this.excelService.buildExcel(excelBuildRequest.getHeader(), excelBuildRequest.getData());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MIME_TYPE));
        headers.setContentDisposition(
                ContentDisposition.parse("attachment;filename=" + generateEncodedFilename()));
        return new ResponseEntity<>(excel, headers, HttpStatus.OK);
    }

    private static String generateEncodedFilename() {
        String filename = new Date().getTime() + ".xlsx";
        return URLEncoder.encode(filename, StandardCharsets.UTF_8);
    }
}
