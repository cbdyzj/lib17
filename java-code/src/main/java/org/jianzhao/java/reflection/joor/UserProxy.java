package org.jianzhao.java.reflection.joor;

public class UserProxy {

    public UserProxy(User target) {
        this.target = target;
    }

    private User target;

    public String name() {
        if (this.target.name() == null) {
            return null;
        }
        return this.target.name().toUpperCase();
    }
}
