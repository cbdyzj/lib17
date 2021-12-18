package draft.type;


import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * ActionThrowsException
 */
interface ATE {

    void invoke() throws Exception;
}

/**
 * SupplierThrowsException
 *
 * @param <R> return value type
 */
interface STE<R> {

    R invoke() throws Exception;
}

class A {
    public void method() {
    }

    public void method(Object o) {
    }

    public static void staticMethod() {
    }

    public static void staticMethod(Object o) {
    }
}

public class LambdaTypeTest {

    public static void func(ATE block) throws Exception {
        block.invoke();
    }

    public static <R> R func(STE<? extends R> block) throws Exception {
        return block.invoke();
    }

    public static void func(Object o,ATE block) throws Exception {
        block.invoke();
    }

    public static <R> R func(Object o,STE<? extends R> block) throws Exception {
        return block.invoke();
    }

    public static void main(String[] args) throws Exception {
        CountDownLatch cdl = new CountDownLatch(1);
        cdl.countDown();
        var a = new A();

//        func(cdl::await); // not ok
        func(() -> cdl.await()); // ?
        func((ATE) cdl::await); // ok
//        func(a::method);  // not ok
        func(() -> a.method());  // ok
        func(() -> A.staticMethod());
//        func(A::staticMethod); // not ok
    }
}
