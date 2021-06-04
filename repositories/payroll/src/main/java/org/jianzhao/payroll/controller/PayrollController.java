package org.jianzhao.payroll.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.jianzhao.payroll.service.PayrollService;
import org.springframework.http.*;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Api(tags = "Payroll")
@CrossOrigin
@RestController
@RequestMapping("/api")
public class PayrollController {

    public static final String MIME_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    private final PayrollService payrollService;

    public PayrollController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    @ApiOperation("上传表格，下载工资条")
    @PostMapping(path = "/payroll", produces = MIME_TYPE)
    public ResponseEntity<byte[]> splitToZip(
            @ApiParam("工资条表格") @RequestParam("file") MultipartFile workbook) throws IOException {
        var originalFilename = workbook.getOriginalFilename();
        var inputStream = workbook.getInputStream();
        try (inputStream) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf(MIME_TYPE));
            headers.setContentDisposition(
                    ContentDisposition.parse("attachment;filename=" + generateEncodedFilename(originalFilename)));
            byte[] zip = this.payrollService.splitToZip(inputStream);
            return new ResponseEntity<>(zip, headers, HttpStatus.OK);
        }
    }

    private static String generateEncodedFilename(String originalFilename) {
        String filename = originalFilename;
        if (ObjectUtils.isEmpty(filename)) {
            filename = new Date().getTime() + ".xlsx";
        }
        filename += ".zip";
        return URLEncoder.encode(filename, StandardCharsets.UTF_8);
    }
}
