package org.jianzhao.tftp.common;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

import static org.jianzhao.tftp.common.TftpConstants.PACKET_SIZE;

public abstract class TftpUtils {

    /**
     * 从Buffer中取出字符串
     */
    public static String getString0(ByteBuffer data) {
        ByteBuffer fn = ByteBuffer.allocate(data.limit());
        byte b;
        while ((b = data.get()) != 0) {
            fn.put(b);
        }
        fn.flip();
        return new String(fn.array(), 0, fn.limit());
    }

    /**
     * 发送数据报
     */
    public static void sendDatagram(DatagramSocket socket,
                                    ByteBuffer buffer,
                                    InetAddress targetAddress,
                                    int targetPort) throws IOException {
        var packet = new DatagramPacket(buffer.array(), buffer.limit(), targetAddress, targetPort);
        socket.send(packet);
    }

    /**
     * 接收数据报
     */
    public static ByteBuffer receiveDatagram(DatagramSocket socket) throws IOException {
        var buffer = ByteBuffer.allocate(PACKET_SIZE);
        var packet = new DatagramPacket(buffer.array(), buffer.capacity());
        socket.receive(packet);
        buffer.flip();
        buffer.limit(packet.getLength());
        return buffer;
    }
}
