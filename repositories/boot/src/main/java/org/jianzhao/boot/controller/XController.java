package org.jianzhao.boot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jianzhao.boot.support.MustacheCompiler;
import org.jianzhao.boot.model.CodeFragment;
import org.jianzhao.boot.model.RenderRequest;
import org.jianzhao.boot.service.ScriptService;
import org.jianzhao.boot.service.XService;
import org.jianzhao.boot.util.StreamUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Api(tags = "X")
@RestController
@RequestMapping("/x")
@RequiredArgsConstructor
public class XController {

    @NonNull
    private final XService xService;

    @NonNull
    private final ScriptService scriptService;

    @NonNull
    private final MustacheCompiler mustacheCompiler;

    @GetMapping(path = "/get")
    public ResponseEntity<?> getX() {
        String result = Stream.generate(this.xService::getX).limit(17).collect(Collectors.joining("-"));
        return ResponseEntity.ok(result);
    }

    @PostMapping(path = "/dynamic", consumes = "application/json")
    public ResponseEntity<?> runDynamically(@RequestBody CodeFragment fragment) {
        String result = this.scriptService.runDynamically(fragment);
        return ResponseEntity.ok(result);
    }

    @PostMapping(path = "/sort", consumes = "application/json")
    public ResponseEntity<?> bubbleSort(@RequestBody int[] array) {
        this.xService.bubbleSort(array);
        return ResponseEntity.ok(array);
    }

    @PostMapping(path = "/render", consumes = "application/json")
    public ResponseEntity<?> renderMustache(@RequestBody RenderRequest rr) {
        String result = this.mustacheCompiler.compile(rr.getTemplate(), rr.getScope());
        return ResponseEntity.ok(result);
    }


    @ApiOperation("Sleep")
    @PostMapping(path = "/sleep", produces = "application/json")
    public ResponseEntity<?> sleep(@RequestParam("millis") Long millis) {
        var result = this.xService.sleep(millis);
        return ResponseEntity.ok(result);
    }

    @SneakyThrows
    @ApiOperation("Script")
    @PostMapping(path = "/script")
    public void script(@RequestParam("name") String name,
                       HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf8");
        var outputStream = response.getOutputStream();
        var inputStream = this.xService.script(name);
        try (inputStream; outputStream) {
            StreamUtils.copyLines(inputStream, outputStream);
        }
    }

}
