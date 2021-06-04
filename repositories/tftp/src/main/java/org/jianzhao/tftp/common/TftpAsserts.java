package org.jianzhao.tftp.common;

public abstract class TftpAsserts {

    public static void assertPacketType(short expect, short current) {
        if (current != expect) {
            throw new TftpException("异常包类型！期望：" + expect + "，当前：" + current);
        }
    }

    public static void assertTrue(boolean expression, String message) {
        if (!expression) {
            throw new TftpException(message);
        }
    }
}
