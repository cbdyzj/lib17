package org.jianzhao.java.curd;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Api(tags = "文件管理")
@RestController
@RequiredArgsConstructor
public class FileController {

    @NonNull
    private final FileService fileService;

    @ApiOperation("上传文件")
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.ok("文件为空！");
        }
        String path = this.fileService.saveFile(file.getInputStream(), file.getOriginalFilename(), "image");
        return ResponseEntity.ok("上传文件：/" + path);
    }

    @ApiOperation("下载文件")
    @GetMapping("/content/**")
    public ResponseEntity<?> download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = URLDecoder.decode(request.getRequestURI(), StandardCharsets.UTF_8).substring(1);
        var fi = this.fileService.findFile(path);
        if (fi.isPresent()) {
            fi.get().transferTo(response.getOutputStream());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
