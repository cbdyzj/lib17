package org.jianzhao.boot.support;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * mustache compiler
 */
public class MustacheCompiler {

    private static final int DEFAULT_CACHE_LIMIT = 256;

    private final MustacheFactory mustacheFactory = new DefaultMustacheFactory();

    /**
     * LRU内存缓存
     */
    private final Map<String, Function<Object, String>> mustacheCache =
            new LinkedHashMap<>(DEFAULT_CACHE_LIMIT, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<String, Function<Object, String>> eldest) {
                    return this.size() > DEFAULT_CACHE_LIMIT;
                }
            };

    /**
     * 编译模板，绑定变量
     */
    public String compile(String template, Object scope) {
        var mustache = this.compileTemplate(template);
        return mustache.apply(scope);
    }

    /**
     * 编译模板并缓存
     */
    private Function<Object, String> compileTemplate(String template) {
        return mustacheCache.computeIfAbsent(template, cacheKey -> {
            var mustache = this.mustacheFactory.compile(new StringReader(template), "");
            return (scope) -> {
                var writer = mustache.execute(new StringWriter(), scope);
                return writer.toString();
            };
        });
    }

}
