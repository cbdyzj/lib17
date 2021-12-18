package draft.type;

import java.util.function.Consumer;

import static org.jianzhao.sugar.Sugar.println;

public class TypeTest {

    public static void main(String[] args) {
        // 类型推导
        call(new Object() {
            private void doSomething() {
                println(this);
            }
        }, obj -> obj.doSomething());
    }

    private static <T> void call(T obj, Consumer<T> consumer) {
        consumer.accept(obj);
    }
}
