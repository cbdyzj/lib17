package draft.closure;

/*

# Java 中的闭包

## 第一问

写一个Java类F，满足以下条件

```java
assert new F().apply(1).get() == 1;
assert new F().apply(1).apply(2).get() == 5;
assert new F().apply(1).apply(2).apply(3).get() == 14;
// 以此类推（规律是平方和）
```

## 第二问

写一个Java类F，满足以下条件

```java
F f = new F();
assert f.apply(1).get() == 1;
F c = f.apply(1);
assert c.apply(2).get() == 5;
assert c.apply(2).apply(3).get() == 14;
// 以此类推（规律是平方和）
```

## 第三问

写一个Java类F，不使用Java类的成员变量（Field），满足第二问条件

*/
