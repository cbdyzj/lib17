package org.jianzhao.tftp.common;

public interface TftpConstants {

    /* 空字节 */
    byte EMPTY = 0;

    /* 数据头大小 */
    int DATA_META_SIZE = 4;

    /* 数据块长度 */
    int DATA_SIZE = 512;

    /* 数据包长度 */
    int PACKET_SIZE = DATA_META_SIZE + DATA_SIZE;

    /* 操作码长度 */
    int OPERATE_CODE_SIZE = 2;

    /* 块编号长度 */
    int CHUNK_NUMBER_SIZE = 2;


    // TFTP帧类型

    /* Read request */
    short RRQ = 1;

    /* Write request */
    short WRQ = 2;

    /* Data */
    short DATA = 3;

    /* Acknowledgment */
    short ACK = 4;

    /* Error */
    int ERROR = 5;
}
