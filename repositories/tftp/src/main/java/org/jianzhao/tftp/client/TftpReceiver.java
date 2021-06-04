package org.jianzhao.tftp.client;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.file.Paths;

import static org.jianzhao.tftp.common.TftpAsserts.assertPacketType;
import static org.jianzhao.tftp.common.TftpAsserts.assertTrue;
import static org.jianzhao.tftp.common.TftpConstants.*;
import static org.jianzhao.tftp.common.TftpUtils.receiveDatagram;
import static org.jianzhao.tftp.common.TftpUtils.sendDatagram;

/**
 * 使用UDP接收文件
 * <p>
 * 从指定端口接收：Port
 *
 * @author cbdyzj
 * @since 2019.2.8
 */
@Slf4j
@RequiredArgsConstructor
class TftpReceiver {

    @NonNull
    private DatagramSocket datagramSocket;

    /* 目标IP地址 */
    @NonNull
    private InetAddress targetAddress;

    /* 目标端口 */
    @NonNull
    private Integer targetPort;

    void receive(String filename) throws IOException {
        File file = Paths.get("cache", filename).toFile();
        boolean created = file.createNewFile();
        assertTrue(created, "创建文件失败！");
        try (var fileOutputStream = new FileOutputStream(file)) {
            while (true) {
                var dataBuffer = receiveDatagram(this.datagramSocket);
                short packetType = dataBuffer.getShort();
                assertPacketType(DATA, packetType);
                short chunkNo = dataBuffer.getShort();
                dataBuffer.compact();
                dataBuffer.flip();
                int len = dataBuffer.limit();
                fileOutputStream.write(dataBuffer.array(), 0, len);
                this.replyAck(chunkNo);
                if (len < DATA_SIZE) {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            log.warn(e.getLocalizedMessage(), e);
        }
    }

    @SuppressWarnings("Duplicates")
    private void replyAck(short chunkNo) throws IOException {
        var buffer = ByteBuffer.allocate(DATA_META_SIZE);
        buffer.putShort(ACK);
        buffer.putShort(chunkNo);
        buffer.flip();
        sendDatagram(this.datagramSocket, buffer, this.targetAddress, this.targetPort);
    }
}
