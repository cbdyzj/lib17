package org.jianzhao.boot.support.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * 本地缓存
 */
public class LocalCacheProvider implements Supplier<Cache<String, Object>> {

    /**
     * 过期时间（秒）
     */
    private static final int DEFAULT_EXPIRE_TIME = 9;

    private final Cache<String, Object> localCache = Caffeine
            .newBuilder()
            .expireAfterWrite(Duration.ofSeconds(DEFAULT_EXPIRE_TIME))
            .build();


    @Override
    public Cache<String, Object> get() {
        return this.localCache;
    }
}
