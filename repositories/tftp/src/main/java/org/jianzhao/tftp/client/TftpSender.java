package org.jianzhao.tftp.client;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.jianzhao.tftp.common.TftpAsserts.assertPacketType;
import static org.jianzhao.tftp.common.TftpAsserts.assertTrue;
import static org.jianzhao.tftp.common.TftpConstants.*;
import static org.jianzhao.tftp.common.TftpUtils.receiveDatagram;
import static org.jianzhao.tftp.common.TftpUtils.sendDatagram;

/**
 * 使用UDP发送文件
 * <p>
 * 发送到：IP:Port
 *
 * @author cbdyzj
 * @since 2019.2.8
 */
@Slf4j
@RequiredArgsConstructor
class TftpSender {

    /* 本地Socket */
    @NonNull
    private DatagramSocket datagramSocket;

    /* 目标IP地址 */
    @NonNull
    private InetAddress targetAddress;

    /* 目标端口 */
    @NonNull
    private Integer targetPort;

    void send(String filename) throws IOException {
        Path path = Paths.get(filename);
        try (var fileInputStream = new FileInputStream(path.toFile())) {
            int len;
            int fileSent = 0;
            short chunkNo = 1;
            byte[] chunkBuffer = new byte[DATA_SIZE];
            var dataBuffer = ByteBuffer.allocate(PACKET_SIZE);
            while ((len = fileInputStream.read(chunkBuffer)) != -1) {
                dataBuffer.clear();
                dataBuffer.putShort(DATA);
                dataBuffer.putShort(chunkNo);
                dataBuffer.put(chunkBuffer, 0, len);
                dataBuffer.flip();
                sendDatagram(this.datagramSocket, dataBuffer, this.targetAddress, this.targetPort);
                this.waitAck(chunkNo);
                fileSent += len;
                chunkNo++;
            }
            if (dataBuffer.limit() == PACKET_SIZE) {
                this.sendEmptyPacket(chunkNo);
                this.waitAck(chunkNo);
            }
            log.info("发送文件：{}，大小：{}", filename, fileSent);
        }
    }

    private void waitAck(int chunkNo) throws IOException {
        var dataBuffer = receiveDatagram(this.datagramSocket);
        short packetType = dataBuffer.getShort();
        assertPacketType(ACK, packetType);
        short receivedChunkNo = dataBuffer.getShort();
        assertTrue(receivedChunkNo == chunkNo, "异常块编号：" + receivedChunkNo);
    }

    @SuppressWarnings("Duplicates")
    private void sendEmptyPacket(short chunkNo) throws IOException {
        var dataBuffer = ByteBuffer.allocate(DATA_META_SIZE);
        dataBuffer.putShort(DATA);
        dataBuffer.putShort(chunkNo);
        dataBuffer.flip();
        sendDatagram(this.datagramSocket, dataBuffer, this.targetAddress, this.targetPort);
    }
}
