# Java中的各种O

也有其他做法，如使用`java.util.Map`，或如Vert.x定义JsonObject。

| 类型 | 全称                       | 说明                                       |
| ---- | -------------------------- | ------------------------------------------ |
| PO   | Persistent Object          | 持久对象，如对应数据库中的记录             |
| BO   | Business Object            | 业务对象，如包含相关业务操作的数据         |
| VO   | Value Object               | 值对象，通常视图相关                       |
| DTO  | Data Transfer Object       | 数据传输对象，如RPC中传递数据的对象        |
| POJO | Plain Ordinary Java Object | 简单Java对象                               |
| DAO  | Data Access Object         | 数据访问对象，如封装了相关数据库操作的对象 |
