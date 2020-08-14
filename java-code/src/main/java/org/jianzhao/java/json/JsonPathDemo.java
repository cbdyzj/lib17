package org.jianzhao.java.json;

import com.jayway.jsonpath.JsonPath;

import static org.jianzhao.sugar.Sugar.println;

public class JsonPathDemo {

    public static void main(String[] args) {
        var json = """
                {
                    "data": [
                        {"a":1,"b":2},
                        {"a":3,"b":4}
                    ]
                }
                """;
        var result = JsonPath.read(json, "$.data[0].a");
        println(result);
    }
}
