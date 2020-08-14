package org.jianzhao.java.strategy;

public class StrategyExecutor {

    public static void execute(Strategy strategy) {
        strategy.applyStrategy();
    }
}
