package org.jianzhao.java.reflection.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import static org.jianzhao.sugar.Sugar.println;

public class CglibDemo {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(User.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, arguments, proxy) -> {
            println("Before method invoke");
            Object ret = proxy.invokeSuper(obj, arguments);
            println("After method invoke");
            return ret;
        });
        User adaProxy = (User) enhancer.create(new Class[]{String.class}, new String[]{"Ada"});
        adaProxy.sayHi();
    }
}
