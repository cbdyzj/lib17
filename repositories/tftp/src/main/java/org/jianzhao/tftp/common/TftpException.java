package org.jianzhao.tftp.common;

public class TftpException extends RuntimeException {

    public TftpException(String message) {
        super(message);
    }

    public TftpException(String message, Throwable cause) {
        super(message, cause);
    }
}
