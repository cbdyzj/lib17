package org.jianzhao.boot.support;

import org.jianzhao.boot.support.cache.PollingCache;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PollingCacheTest {

    @Test
    public void test() throws InterruptedException {
        var count = new int[]{0};
        var pc = new PollingCache<String, Integer>(1000,
                cache -> cache.put("count", count[0]++));
        assertNull(pc.getValue("count"));
        pc.start();
        Thread.sleep(500);
        assertEquals(0,pc.getValue("count"));
        Thread.sleep(1000);
        assertEquals(1,pc.getValue("count"));
        pc.stop();
    }
}
