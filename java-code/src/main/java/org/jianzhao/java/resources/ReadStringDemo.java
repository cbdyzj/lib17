package org.jianzhao.java.resources;

import org.jianzhao.jojo.JsonObject;

import java.nio.charset.StandardCharsets;

import static org.jianzhao.sugar.Sugar.println;

/**
 * @author cbdyzj
 * @since 2018-08-29
 */
public class ReadStringDemo {

    public static void main(String[] args) throws Exception {
        try (var is = ReadStringDemo.class.getResourceAsStream("/user.json")) {
            var bytes = is.readAllBytes();
            var jsonString = new String(bytes, StandardCharsets.UTF_8);
            var jsonObject = new JsonObject(jsonString);
            println(jsonObject);
        }
    }
}

