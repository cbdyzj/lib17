# ByteBuffer

## 属性

- capacity：缓冲区容量
- limit：缓冲区存量
- position：读写游标
- mark：备忘标记

## 方法

- flip()：设置limit为当前position，设置position为0，一般在从Buffer读出数据前调用
- clear()：设置position为0，把limit设为capacity，一般在把数据写入Buffer前调用
- rewind()：设置position为0，limit不变，一般在把数据重写入Buffer前调用
- compact()：复制position与limit之间的数据到开始位置，复制后position设置为limit - position，limit = capacity
- reset()：设置position为当前mark
