package org.jianzhao.java.function;

import org.jianzhao.jojo.JsonObject;

import java.util.HashMap;
import java.util.Map;

import static org.jianzhao.sugar.Sugar.println;

public class JsFunctionDemo {

    public static JsonObject someFunction(JsonObject args) {
        String arg1 = args.getString("arg1");
        return new JsonObject().put("arg1", arg1);
    }

    public static void main(String[] args) {
        Map<String, Object> o = new HashMap<>();
        o.put("someFunction", (JsFunction) JsFunctionDemo::someFunction);
        JsFunction someFunction = (JsFunction) o.get("someFunction");
        JsonObject result = someFunction.apply(new JsonObject().put("arg1", "hello"));
        println(result.getString("arg1"));
    }
}
