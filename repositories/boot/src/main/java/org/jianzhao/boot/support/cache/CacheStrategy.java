package org.jianzhao.boot.support.cache;

import java.lang.reflect.Method;
import java.util.function.Supplier;

public interface CacheStrategy {

    Object apply(String cacheKey, Supplier<Object> supplier);

    boolean matches(Method method);
}
