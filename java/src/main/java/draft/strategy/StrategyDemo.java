package draft.strategy;

public class StrategyDemo {

    public static void main(String[] args) {
        var demoStrategy = new DemoStrategy();
        StrategyExecutor.execute(demoStrategy::applyStrategy);
    }
}
