package org.jianzhao.tftp;

import lombok.extern.slf4j.Slf4j;
import org.jianzhao.tftp.client.TftpClient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.ByteBuffer;

import static org.jianzhao.tftp.common.TftpConstants.*;
import static org.jianzhao.tftp.common.TftpUtils.*;

@Slf4j
public class TftpServer {

    private DatagramSocket datagramSocket;

    public TftpServer(int port) throws SocketException {
        this.datagramSocket = new DatagramSocket(port);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void start() throws IOException {
        var buffer = ByteBuffer.allocate(PACKET_SIZE);
        while (true) {
            buffer.clear();
            var packet = new DatagramPacket(buffer.array(), buffer.capacity());
            this.datagramSocket.receive(packet);
            buffer.flip();
            buffer.limit(packet.getLength());

            short packetType = buffer.getShort();
            buffer.compact();
            buffer.flip();
            var client = new TftpClient(this.datagramSocket, packet.getAddress(), packet.getPort());
            switch (packetType) {
                case RRQ:
                    client.put(getString0(buffer));
                    break;
                case WRQ:
                    client.replyAck0();
                    String string0 = getString0(buffer);
                    client.get(string0);
                    break;
                case ERROR:
                    String msg = getString0(buffer);
                    log.info("TFTP异常：{}", msg);
                    break;
                default:
                    log.warn("异常UDP请求！");
            }
        }
    }
}
