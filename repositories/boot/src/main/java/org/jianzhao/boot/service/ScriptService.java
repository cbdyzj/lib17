package org.jianzhao.boot.service;

import groovy.lang.GroovyShell;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jianzhao.boot.model.CodeFragment;
import org.joor.Reflect;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngineManager;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScriptService {

    private static final String RUNNER_TEMPLATE_CLASS_NAME = "org.jianzhao.boot.component.DynamicRunner";
    private static final String RUNNER_TEMPLATE = """
            package org.jianzhao.boot.component;
            import java.util.function.Supplier;   
            public class DynamicRunner implements Supplier<Object> {
                @Override
                public Object get() {
                    %s
                }
            }
            """;

    /**
     * 动态执行代码
     */
    public String runDynamically(CodeFragment fragment) {
        Objects.requireNonNull(fragment);
        var language = fragment.getLanguage();
        Objects.requireNonNull(language);
        return switch (language.toLowerCase()) {
            case "java" -> this.runJavaCode(fragment);
            case "javascript" -> this.runJavaScriptCode(fragment);
            case "groovy" -> this.runGroovyCode(fragment);
            default -> throw new IllegalArgumentException("Language not supported: " + language);
        };
    }

    /**
     * 动态执行Java代码
     */
    private String runJavaCode(CodeFragment fragment) {
        Objects.requireNonNull(fragment);
        var codeFragment = fragment.getFragment();
        try {
            var classCode = RUNNER_TEMPLATE.formatted(codeFragment);
            var runner = Reflect.compile(RUNNER_TEMPLATE_CLASS_NAME, classCode).create();
            var result = runner.call("get").get();
            return String.valueOf(result);
        } catch (Exception ex) {
            log.info("Run dynamically failed: {}", fragment);
            return ex.getMessage();
        }
    }

    /**
     * 动态执行JavaScript代码
     */
    private String runJavaScriptCode(CodeFragment fragment) {
        Objects.requireNonNull(fragment);
        try {
            var manager = new ScriptEngineManager();
            var engine = manager.getEngineByName("graal.js");
            var bindings = engine.createBindings();
            bindings.put("functionBody", fragment.getFragment());
            var result = engine.eval("(new Function(functionBody))()", bindings);
            return String.valueOf(result);
        } catch (Exception ex) {
            log.info("Run dynamically failed: {}", fragment);
            return ex.getMessage();
        }
    }

    /**
     * 动态执行Groovy代码
     */
    private String runGroovyCode(CodeFragment fragment) {
        Objects.requireNonNull(fragment);
        try {
            var shell = new GroovyShell();
            var evaluated = shell.evaluate(fragment.getFragment());
            return String.valueOf(evaluated);
        } catch (Exception ex) {
            log.info("Run dynamically failed: {}", fragment);
            return ex.getMessage();
        }
    }
}
