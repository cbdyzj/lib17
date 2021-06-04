package org.jianzhao.boot.support.cache;

import java.lang.reflect.Method;
import java.util.function.Supplier;

public class LocalCacheStrategy implements CacheStrategy {

    private final LocalCacheProvider localCacheProvider = new LocalCacheProvider();

    @Override
    public Object apply(String cacheKey, Supplier<Object> supplier) {
        var localCache = this.localCacheProvider.get();
        return localCache.get(cacheKey, key -> supplier.get());

    }

    @Override
    public boolean matches(Method method) {
        return method.isAnnotationPresent(LocalCache.class);
    }
}
