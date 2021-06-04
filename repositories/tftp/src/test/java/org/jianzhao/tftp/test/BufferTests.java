package org.jianzhao.tftp.test;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

@SuppressWarnings("WeakerAccess")
public class BufferTests {

    @Test
    public void test() {
        var buff = ByteBuffer.allocate(4);
        buff.putInt(0x12345678);
        buff.flip();
        short aShort = buff.getShort();
        System.out.println(aShort);

    }
}
