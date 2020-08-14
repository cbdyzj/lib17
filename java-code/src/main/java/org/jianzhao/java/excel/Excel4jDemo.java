package org.jianzhao.java.excel;

import com.github.crab2died.ExcelUtils;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Excel4jDemo {

    public static void main(String... args) throws Exception {
        var users = new ArrayList<>();
        users.add(new User("a", 16));
        users.add(new User("b", 17));
        users.add(new User("c", 18));
        var os = new FileOutputStream("x.xlsx");
        ExcelUtils.getInstance().exportObjects2Excel(users, User.class, os);
    }
}
