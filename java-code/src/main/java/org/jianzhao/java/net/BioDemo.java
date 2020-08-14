package org.jianzhao.java.net;

import java.net.ServerSocket;

import static org.jianzhao.sugar.Sugar.println;

public class BioDemo {

    public static void main(String... args) throws Exception {
        var server = new ServerSocket(8000);
        for (var count = Integer.MAX_VALUE; count > 0; count--) {
            var socket = server.accept();
            //当有新的客户端接入时，会执行下面的代码
            //然后创建一个新的线程处理这条Socket链路
            new Thread(() -> {
                try (var in = socket.getInputStream()) {
                    in.transferTo(socket.getOutputStream());
                    println("end");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }
}
