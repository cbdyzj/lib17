package org.jianzhao.boot.support.cache;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Cache interceptor
 */
@Slf4j
public class CacheInterceptor implements MethodInterceptor {

    private final List<CacheStrategy> cacheStrategyList;

    public CacheInterceptor(List<CacheStrategy> cacheStrategyList) {
        Objects.requireNonNull(cacheStrategyList);
        this.cacheStrategyList = cacheStrategyList;
    }

    /**
     * 代理执行
     */
    @Override
    public Object invoke(MethodInvocation invocation) {
        var method = invocation.getMethod();
        var target = invocation.getThis();
        var arguments = invocation.getArguments();
        var supplier = (Supplier<Object>) () -> ReflectionUtils.invokeMethod(method, target, arguments);
        var strategy = this.getCacheStrategy(method);
        if (strategy != null) {
            var cacheKey = generateCacheKey(String.valueOf(method), arguments);
            return strategy.apply(cacheKey, supplier);
        }
        return supplier.get();
    }

    /**
     * 获取匹配缓存策略
     */
    private CacheStrategy getCacheStrategy(Method method) {
        for (CacheStrategy strategy : this.cacheStrategyList) {
            if (strategy.matches(method)) {
                return strategy;
            }
        }
        return null;
    }

    /**
     * 生成缓存Key
     */
    private static String generateCacheKey(String methodName, Object[] arguments) {
        if (arguments.length == 0) {
            return methodName;
        } else if (arguments.length == 1 && isKeyType(arguments[0])) {
            return methodName + " " + arguments[0];
        } else {
            throw new IllegalStateException("The method is not supported: " + methodName);
        }
    }

    private static boolean isKeyType(Object object) {
        return object instanceof Number || object instanceof String;
    }
}
