# Object与wait与notify

## 问题

- 为什么wait与notify设计在Object类中？

## 答案

因为`synchronized`关键词（设计的初衷可能是为了简化多线程程序的编写）可以加在每个类的方法之上。

每个对象都有一个monitor（因此每个对象都潜在需要线程同步），虚拟机在执行`synchronized`修饰的代码块时，需要获取该对象的monitor。

对象同步可以一定程度上通过`synchronized`（本质上是monitor）完成，而当需要通过“阻塞”、“唤醒”完成对象同步时，`synchronized`关键词就捉襟见肘了，需要通过其他途径实现。代码可能需要这样写

```java
monitor.wait();    // 将当前获取monitor的线程加入该monitor的等待队列
monitor.notify();  // 唤醒该monitor的等待队列中的一个线程
```

而`synchronized`关键词已经把monitor隐藏掉了，为了实现上述代码的效果，或许可以把这样的方法加到了Object上（即当前Java的做法）。

假设不加到Object上，或许可以设计新的关键词，比如`wait`、`notify`等，我们可能要这么写

```java
Object o = new Object();
...
synchronized(o) {
    wait o;
}
...
synchronized(o) {
    notify o;
}
```

这就是聪明反被聪明误，`synchronized`看起来引入的麻烦比解决的问题多，还很难拓展。最后还是把显式的锁（如ReentrantLock）加入Java了。
