package org.jianzhao.tftp.test;

import lombok.extern.slf4j.Slf4j;
import org.jianzhao.tftp.client.TftpClient;
import org.jianzhao.tftp.TftpServer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.net.DatagramSocket;
import java.net.InetAddress;

@SuppressWarnings("WeakerAccess")
@Slf4j
@Disabled
public class TftpTests {

    @Test
    public void serve() throws Exception {
        var server = new TftpServer(10010);
        log.info("启动TFTP服务端线程");
        server.start();
    }

    @Test
    public void put() throws Exception {
        var t = new Thread(() -> {
            try {
                this.serve();
            } catch (Exception e) {
                log.warn(e.getMessage(), e);
            }
        }, "TftpServer");
        t.start();
        var localhost = InetAddress.getByName("localhost");
        var client = new TftpClient(new DatagramSocket(), localhost, 10010);
        client.requestPut("README.md");
        Thread.sleep(3000);
        t.interrupt();
        log.info("文件传输完成，杀死服务端线程");

    }

    @Test
    public void get() throws Exception {
        var t = new Thread(() -> {
            try {
                this.serve();
            } catch (Exception e) {
                log.warn(e.getMessage(), e);
            }
        }, "TftpServer");
        t.start();
        var localhost = InetAddress.getByName("localhost");
        var client = new TftpClient(new DatagramSocket(), localhost, 10010);
        client.requestGet("README.md");
    }

}

