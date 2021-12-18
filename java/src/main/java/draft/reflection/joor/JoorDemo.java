package draft.reflection.joor;

import org.joor.Reflect;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public final class JoorDemo {

    public static void main(String[] args) throws IOException {
        prop();
        proxy();
        compile();
    }

    public static void compile() throws IOException {
        var className = "draft.reflection.joor.Banana";
        var resourcePath = className.replaceAll("\\.", "/") + ".java";
        var inputStream = new ClassPathResource(resourcePath).getInputStream();
        try (inputStream) {
            var javaCode = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            var banana = Reflect.compile(className, javaCode)
                    .create()
                    .set("color", "yellow")
                    .set("size", 7)
                    .set("taste", "great")
                    .set("price", 3.14f)
                    .get();
            var expectedToString = "Banana{price='3.14'} Fruit{color='yellow', taste='great', size=7}";
            assertEqual(banana.toString(), expectedToString);
        }
    }

    public static void prop() {
        var fruit = new Fruit();
        fruit.setSize(10);
        fruit.setColor("red");
        fruit.setTaste("good");
        var fruitMeta = Reflect.on(fruit);
        var size = fruitMeta.get("size");
        assertEqual(size, 10);
        var color = fruitMeta.get("color");
        assertEqual(color, "red");
        var taste = fruitMeta.get("taste");
        assertEqual(taste, "good");
        var toString = fruitMeta.call("toString").get();
        assertEqual(toString, fruit.toString());
    }

    public static void proxy() {
        var ada = (User) () -> "Ada";
        var adaProxy = Reflect.onClass(UserProxy.class).create(ada).as(User.class);
        assertEqual(adaProxy.name(), "ADA");
    }

    private static void assertEqual(Object target, Object expected) {
        Assert.state(Objects.equals(target, expected),
                String.format("target: %s, expected %s", target, expected));
    }
}
