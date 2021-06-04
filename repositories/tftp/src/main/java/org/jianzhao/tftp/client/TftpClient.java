package org.jianzhao.tftp.client;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static org.jianzhao.tftp.common.TftpAsserts.assertPacketType;
import static org.jianzhao.tftp.common.TftpAsserts.assertTrue;
import static org.jianzhao.tftp.common.TftpConstants.*;
import static org.jianzhao.tftp.common.TftpUtils.receiveDatagram;
import static org.jianzhao.tftp.common.TftpUtils.sendDatagram;

@Slf4j
@RequiredArgsConstructor
public class TftpClient {

    @NonNull
    private DatagramSocket datagramSocket;

    @NonNull
    private InetAddress targetAddress;

    @NonNull
    private Integer targetPort;

    public void put(String filename) throws IOException {
        var fileSender = new TftpSender(this.datagramSocket, this.targetAddress, this.targetPort);
        fileSender.send(filename);
    }

    public void get(String filename) throws IOException {
        var fileReceiver = new TftpReceiver(this.datagramSocket, this.targetAddress,this.targetPort);
        fileReceiver.receive(filename);
    }

    @SuppressWarnings("Duplicates")
    public void requestPut(String filename) throws IOException {
        assertTrue(filename != null && !filename.isBlank(), "非法文件名！");
        var buffer = ByteBuffer.allocate(PACKET_SIZE);
        buffer.putShort(WRQ);
        buffer.put(filename.getBytes(StandardCharsets.UTF_8));
        buffer.put((byte) 0);
        buffer.flip();
        sendDatagram(this.datagramSocket, buffer, this.targetAddress, this.targetPort);
        this.waitAck0();
        this.put(filename);
    }

    @SuppressWarnings("Duplicates")
    public void requestGet(String filename) throws IOException {
        assertTrue(filename != null && !filename.isBlank(), "非法文件名！");
        var buffer = ByteBuffer.allocate(PACKET_SIZE);
        buffer.putShort(RRQ);
        buffer.put(filename.getBytes(StandardCharsets.UTF_8));
        buffer.put((byte) 0);
        buffer.flip();
        sendDatagram(this.datagramSocket, buffer, this.targetAddress, this.targetPort);
        this.get(filename);

    }

    public void replyAck0() throws IOException {
        var buffer = ByteBuffer.allocate(DATA_META_SIZE);
        buffer.putShort(ACK);
        buffer.putShort((short) 0);
        buffer.flip();
        sendDatagram(this.datagramSocket, buffer, this.targetAddress, this.targetPort);
    }

    private void waitAck0() throws IOException {
        var dataBuffer = receiveDatagram(this.datagramSocket);
        short packetType = dataBuffer.getShort();
        assertPacketType(ACK, packetType);
        short receivedChunkNo = dataBuffer.getShort();
        assertTrue(receivedChunkNo == 0, "异常块编号：" + receivedChunkNo);
    }

}
