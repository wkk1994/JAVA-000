# 基于 Redis 封装分布式数据操作：
    
## 在 Java 中实现一个简单的分布式锁；

实现方式：加锁时，尝试直接获取锁，如果获取锁失败进入等待队列阻塞，在对应节点释放锁时，唤醒等待的线程进行锁获取。释放锁时，通过redis的lua脚本实现原子性释放锁。

主要代码地址：[RedisLockServiceImpl](https://github.com/wkk1994/JAVA-000/blob/main/batch-insert-demo/src/main/java/com/wkk/learn/java/spring/boot/redis/service/impl/RedisLockServiceImpl.java)

使用示例：

```java
// 获取锁
redisLockService.lock();
// 释放锁
redisLockService.unLock();
```

## 在 Java 中实现一个分布式计数器，模拟减库存。

* 方式一：使用上面的的分布式锁，在减库存之前，获取锁；减库存之后释放锁，实现减库存的线程安全。

  代码地址：[InventoryReductionServiceImpl](https://github.com/wkk1994/JAVA-000/blob/main/batch-insert-demo/src/main/java/com/wkk/learn/java/spring/boot/redis/service/impl/InventoryReductionServiceImpl.java)