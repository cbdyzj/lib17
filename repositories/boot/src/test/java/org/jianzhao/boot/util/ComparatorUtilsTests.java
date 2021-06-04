package org.jianzhao.boot.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparatorUtilsTests {

    @Test
    public void testSortAccordingToGivenList() {
        var zodiac = "鼠牛虎兔龙蛇马羊猴鸡狗猪";
        var zodiacList = List.of(zodiac.split(""));
        var randomZodiacList = new ArrayList<String>();
        randomZodiacList.add("猫");
        randomZodiacList.addAll(zodiacList);
        Collections.shuffle(randomZodiacList);
        randomZodiacList.sort(ComparatorUtils.accordingToGivenList(zodiacList));
        Assertions.assertEquals(String.join("", randomZodiacList), zodiac + "猫");
    }
}
