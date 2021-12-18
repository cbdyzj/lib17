package draft.enumeration;

import java.util.concurrent.ThreadLocalRandom;

import static org.jianzhao.sugar.Sugar.println;

public class EnumDemo {

    public static void main(String[] args) {
        Object color = new Object();
        if(ThreadLocalRandom.current().nextBoolean()){
            color = Color.Black;
        }
        println(color.getClass().isEnum());
        println(color instanceof Enum);
    }
}
