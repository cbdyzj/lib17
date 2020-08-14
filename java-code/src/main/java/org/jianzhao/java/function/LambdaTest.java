package org.jianzhao.java.function;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.jianzhao.sugar.Sugar.println;

/**
 * @author cbdyzj
 * @see <a href="https://www.v2ex.com/t/349432">箭头函数表达式</a>
 */
public class LambdaTest {

    public static void main(String... args) {

        Function<Integer, Integer> plus1 = a -> a + 1;
        Function<Integer, Integer> multiply2 = a -> a * 2;

        Function<Stream<Function<Integer, Integer>>, Function<Integer, Integer>>
                pipeline = functions -> val -> functions.reduce((a, b) -> b.compose(a)).orElse(i -> i).apply(val);

        Function<Integer, Integer> addThenMultiply = pipeline.apply(Stream.of(plus1, multiply2));

        Integer result = addThenMultiply.apply(5);

        println(result);
    }

}
