package org.jianzhao.boot.support;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class MustacheCompilerTest {

    private MustacheCompiler compiler;

    @BeforeEach
    public void init() {
        this.compiler = new MustacheCompiler();
    }

    @Test
    public void testCompile() {
        var template = "hello {{name}}";
        var scope = Map.of("name", "world");
        var result = this.compiler.compile(template, scope);
        Assertions.assertEquals("hello world", result);
    }
}
