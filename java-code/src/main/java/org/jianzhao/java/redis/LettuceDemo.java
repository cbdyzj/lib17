package org.jianzhao.java.redis;

import io.lettuce.core.RedisClient;

import static org.jianzhao.sugar.Sugar.println;

public class LettuceDemo {

    public static void main(String... args) {
        var client = RedisClient.create("redis://localhost");
        var connection = client.connect();
        var value = connection.sync().get("key1");
        println(value);
    }
}
