package org.jianzhao.java.reflection.cglib;

import static org.jianzhao.sugar.Sugar.println;

public class User {

    private String name;

    public User(String name) {
        this.name = name;
    }


    public void sayHi() {
        println(String.format("Hi, I am %s!", this.name));
    }
}
