package org.jianzhao.java.object;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static org.jianzhao.sugar.Sugar.println;

public class ObjectDemo {

    public static void main(String[] args) {
        var mapHandle = new ObjectHandle<Map<String,String>>(new HashMap<>());
        mapHandle.apply(it -> it.put("now", Instant.now().toString()));
        var map = mapHandle.get();
        println(map);
    }
}
