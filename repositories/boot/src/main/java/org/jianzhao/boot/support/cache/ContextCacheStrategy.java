package org.jianzhao.boot.support.cache;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.function.Supplier;

public class ContextCacheStrategy implements CacheStrategy {

    private final ContextCacheProvider contextCacheProvider = new ContextCacheProvider();

    @Override
    public Object apply(String cacheKey, Supplier<Object> supplier) {
        var contextCache = contextCacheProvider.get();
        return contextCache.computeIfAbsent(cacheKey, key -> supplier.get());
    }

    @Override
    public boolean matches(Method method) {
        return method.isAnnotationPresent(ContextCache.class);
    }

    public HandlerInterceptor getContextCacheCleanInterceptor() {
        return new HandlerInterceptor() {
            @SuppressWarnings("NullableProblems")
            @Override
            public void afterCompletion(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Object handler,
                                        Exception ex) {
                ContextCacheStrategy.this.contextCacheProvider.clear();
            }
        };
    }
}
