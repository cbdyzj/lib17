# Java中的各种O

由于Java目前不支持类似JavaScript中的解构（Destructuring）语法，为了日常写代码的方便，经常需要写各种临时的对象方便在不同场景下发送传递数据、组织代码。同时，使用不同的领域对象描述，有助于方便交流。

当然，传递数据也有其他做法，比如数据传递的时候使用`java.util.Map`封装参数，或者类似Vert.x那样定义自己的JsonObject来封装消息。不过这样虽然方便了，但是失去了静态类型检查的便利。

| 类型 | 全称                       | 说明                                       |
| ---- | -------------------------- | ------------------------------------------ |
| PO   | Persistent Object          | 持久对象，如对应数据库中的记录             |
| BO   | Business Object            | 业务对象，如包含相关业务操作的数据         |
| VO   | Value Object               | 值对象，通常视图相关                       |
| DTO  | Data Transfer Object       | 数据传输对象，如RPC中传递数据的对象        |
| POJO | Plain Ordinary Java Object | 简单Java对象                               |
| DAO  | Data Access Object         | 数据访问对象，如封装了相关数据库操作的对象 |

