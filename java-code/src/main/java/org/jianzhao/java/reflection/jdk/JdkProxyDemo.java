package org.jianzhao.java.reflection.jdk;

import java.lang.reflect.Proxy;

import static org.jianzhao.sugar.Sugar.println;

public class JdkProxyDemo {

    public static void main(String[] args) {
        User ada = () -> println("Hi, I am Ada");
        User adaProxy = (User) Proxy.newProxyInstance(
                User.class.getClassLoader(),
                new Class[]{User.class},
                (proxy, method, arguments) -> {
                    println("Before method invoke");
                    Object ret = method.invoke(ada, arguments);
                    println("After method invoke");
                    return ret;
                });
        adaProxy.sayHi();
    }
}
