package org.jianzhao.java.json;

import org.jianzhao.jojo.Json;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.jianzhao.sugar.Sugar.println;

public class JsonDemo {

    public static void main(String[] args) {
//        apple();
//        banana();
//        unknown();
        json();
    }

    private static void unknown() {
        var now = new Now();
        var encode = Json.encode(now);
        println(encode);
        var decodeValue = Json.decodeValue(encode, Now.class);
        var encodeAgain = Json.encode(decodeValue);
        println(encodeAgain);
        println(Objects.equals(encode, encodeAgain));
    }

    public static void banana() {
        var banana = new Banana("angel", "blue");
        var encoded = Json.encode(banana);
        println(encoded);
        var banana2 = Json.decodeValue(encoded, Banana.class);
        println(banana2);
    }

    public static void apple() {
        var a1 = new Apple("a1", "t1");
        var a2 = new Apple("a2", "t2");
        var a3 = new Apple("a3", "t3");

        var at1 = new AppleTree("at1");
        at1.setApples(List.of(a1, a2, a3));

        var at1Json = Json.encode(at1);
        var dat1 = Json.decodeValue(at1Json, AppleTree.class);
        println(dat1);
        println(dat1.getApples().get(1).getName());
    }

    public static void json() {
        var json = """
                {
                    "name": "apple",
                    "color": "red"
                }
                """;
        var map = Json.decodeValue(json, Map.class);
        println(map);
    }
}
