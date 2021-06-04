package org.jianzhao.boot.support.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 上下文缓存
 */
public class ContextCacheProvider implements Supplier<Map<String, Object>> {

    private final ThreadLocal<Map<String, Object>> contextCache = ThreadLocal.withInitial(HashMap::new);

    @Override
    public Map<String, Object> get() {
        return this.contextCache.get();
    }

    public void clear() {
        this.contextCache.remove();
    }
}
