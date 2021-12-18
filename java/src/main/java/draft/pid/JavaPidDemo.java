package draft.pid;

import java.lang.management.ManagementFactory;

import static org.jianzhao.sugar.Sugar.println;

public class JavaPidDemo {

    public static void main(String... args) {
        var name = ManagementFactory.getRuntimeMXBean().getName();
        println(name);
        var pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
        println(pid);
        var pid2 = ProcessHandle.current().pid();
        println(pid2);
    }
}
