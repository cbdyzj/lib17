package org.jianzhao.boot.controller;

import io.swagger.annotations.Api;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jianzhao.boot.support.MustacheCompiler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@Api(tags = "服务端渲染页面")
@RequestMapping("/page")
@RequiredArgsConstructor
public class MvcController {

    @NonNull
    private final MustacheCompiler mustacheCompiler;

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "world");
        return "hello";
    }

    @ResponseBody
    @GetMapping(path = "hello2", produces = "text/html")
    public String hello2() {
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Hello</title>
                </head>
                <body>
                   hello %s
                </body>
                </html>        
                """.formatted("world");
    }

    @ResponseBody
    @GetMapping(path = "hello3", produces = "text/html")
    public String hello3() {
        var template = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Hello</title>
                </head>
                <body>
                   hello {{name}}
                </body>
                </html>      
                """;
        var scope = Map.of("name", "world");
        return this.mustacheCompiler.compile(template, scope);
    }

}
