package draft.singleton;

import static org.jianzhao.sugar.Sugar.println;

public class Singleton {

    static {
        println("Singleton static block");
    }

    private static final Singleton instance = new Singleton();

    public void someMethod() {
    }

    public static Singleton get() {
        return instance;
    }

    private Singleton() {
        println("Singleton constructor");
    }
}
