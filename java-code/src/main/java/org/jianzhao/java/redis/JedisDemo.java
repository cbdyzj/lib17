package org.jianzhao.java.redis;

import redis.clients.jedis.Jedis;

import static org.jianzhao.sugar.Sugar.println;

public class JedisDemo {

    public static void main(String... args) {
        var client = new Jedis("localhost");
        client.set("foo", "echo2");
        var value = client.get("foo");
        println(value);
        client.del("foo");
    }
}
