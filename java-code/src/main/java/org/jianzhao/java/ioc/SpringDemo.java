package org.jianzhao.java.ioc;

import org.springframework.context.support.GenericApplicationContext;

import static org.jianzhao.sugar.Sugar.println;

public class SpringDemo {

    public static void main(String... args) {
        var context = new GenericApplicationContext();
        context.registerBean(SpringDemo.class);
        context.refresh();
        var here = context.getBean(SpringDemo.class);
        println(here.toString());
    }

}
