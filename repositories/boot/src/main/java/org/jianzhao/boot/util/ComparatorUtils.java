package org.jianzhao.boot.util;

import java.util.*;
import java.util.function.Function;

public final class ComparatorUtils {

    private ComparatorUtils() {
    }

    public static <E> Comparator<E> accordingToGivenList(List<E> givenList) {
        return accordingToGivenList(givenList, Function.identity());
    }

    public static <E, A> Comparator<E> accordingToGivenList(List<A> givenList, Function<E, A> mapFunc) {
        if (givenList == null || givenList.isEmpty()) {
            return (first, second) -> 0;
        }
        Objects.requireNonNull(mapFunc, "The mapFunc must be non null.");
        Map<A, Integer> consult = new HashMap<>();
        for (int i = 0; i < givenList.size(); i++) {
            consult.putIfAbsent(givenList.get(i), i);
        }
        return (first, second) -> {
            A firstItem = mapFunc.apply(first);
            A secondItem = mapFunc.apply(second);
            int firstIndex = consult.getOrDefault(firstItem, Integer.MAX_VALUE);
            int secondIndex = consult.getOrDefault(secondItem, Integer.MAX_VALUE);
            return firstIndex - secondIndex;
        };
    }
}
