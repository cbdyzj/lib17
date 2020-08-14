package org.jianzhao.java.strategy;

import static org.jianzhao.sugar.Sugar.println;

public class DemoStrategy {

    private void doA() {
        println("DemoStrategy::doA");
    }

    private void doB() {
        println("DemoStrategy::doB");

    }

    private void doC() {
        println("DemoStrategy::doC");

    }

    public void applyStrategy() {
        this.doA();
        this.doB();
        this.doC();
    }
}
