package draft.ioc;

import com.google.inject.Guice;

import static org.jianzhao.sugar.Sugar.println;

public class GuiceDemo {

    public static void main(String... args) {
        var injector = Guice.createInjector();
        var here = injector.getInstance(GuiceDemo.class);
        println(here.toString());
    }
}
