# 学习笔记

## 1、(必做)分别用 100 个字以上的一段话，加上一幅图 (架构图或脑图)，总结自己

对下列技术的关键点思考和经验认识:

1) JVM
2) NIO
3) 并发编程
4) Spring 和 ORM 等框架
5) MySQL 数据库和 SQL
6) 分库分表
7) RPC 和微服务
8) 分布式缓存
9) 分布式消息队列

总结：

在这十五周的学习中，开阔了技术见解，知道很多自己的盲维。建立了一定的知识体系，更多的是意识到不同知识点之间的相互联系，技术都有通用性。这也达到了当初报这个训练营的目的：建立知识体系，跟着学下来，一步步养成学习习惯。但是感觉自己还是有很多不足的地方，总是学习知识点，用别人的思考的结果强行当做自己的思考，很难有自己的见解，不够深入理解一个技术或事物，形成自己的见解。这个还要自己多思考，深入理解，提出自己的见解，对技术有自己的主见！！！对学到的东西，用自己的思考和理解的结果表达出来。

后续：

虽然训练营即将结束，但是学习依然不能停歇。自己需要从头梳理学到的知识点，对之前没有时间深入探究或了解的技术或框架，深入学习。目标在一年内，完成所有的挑战作业，加油💪！

对下列技术的关键点思考和经验认识:

1) JVM

* 了解了常见的JVM算法，针对新生代和老年代的特征，使用不同的垃圾收集算法。
* 常见的JVM的垃圾收集器，以及最新的垃圾收集器特性。目前常见的垃圾收集器还是使用分代算法，但是G1、ZGC等较新的垃圾收集器都不使用分代算法。
* 常见的JVM调优参数，不过对于大多数项目来说`-Xms`和`-Xmx`都已经够用了。

2) NIO

* IO模型的发展历程和常见的IO模型：现在性能比较高以及常用的IO模型是NIO。
* Netty作为一个高性能的网络通信框架，主要使用的IO模型是NIO的方式，对应的也就是Reactor模式。

3) 并发编程

* 单线程存在很大的性能制约，多线程可以提高程序的性能，但是也带来了共享变量的一致性问题。
* Java提供了ThreadPoolExecutor类来定义不同的线程池。也提供了便捷的创建线程池的方式比如Executors.newCachedThreadPool()。
* 对于共享变量的线程安全问题，JVM提供了synchronized关键字、JDK提供了ReentrantLock实现共享变量的互斥访问。ReentrantLock的使用更灵活，支持更多的条件上锁，但是一般使用synchronized就可以了。
* 对于共享变量的操作，JDK还提供了Atomic工具类，通过CAS支持线程安全。
* 对于线程之间的协同工作，JDK提供了Semaphore、CountDownLatch、CyclicBarrier等并发工具类，它们都是通过AQS实现的。
* 对于集合类，JDK还提供了线程安全的集合类，比如CopyOnWriteArrayList、ConcurrentHashMap等类，或者使用Collections.synchronizedXXX创建一个线程安全的集合对象。

4) Spring 和 ORM 等框架

* Spring框架主要是管理应用中的bean，核心是AOP和IOC。AOP的实现方式是通过对切面类上通过JdkProxy或CGlib做代理实现增强。
* Spring中Bean的生命周期。
* Spring Boot更像一个脚手架，能够简化配置，快速创建一个Spring项目。
* Spring Boot简化配置的原因：约定大于配置；以及内置了很多自动配置类，将项目中可能使用到的第三方技术全部整合，实现了自动化配置类。
* java8特性、Guava、设计模式、单元测试。

5) MySQL 数据库和 SQL

* 数据库的优化是系统性能优化的核心。系统的性能指标一般有吞吐和延迟。
* MySQL常见的存储引擎有MyISAM、Archive、InnoDB、Memory，经常使用的一般就是InnoDB，支持事务。
* 一条SQL在MySQL中的执行流程：先通过查询缓存判断是否命中缓存；再依次通过解释器、优化器、执行器；再调用存储引擎的接口操作或获取数据。
* MySQL参数配置优化以及数据库设计优化。
* MySQL的数据库隔离级别默认是RR（可重复读）。
* InnoDB通过日志文件：redo log（重做日志）和undo log（撤销日志）实现了MVCC以及掉电恢复。
* 通过慢查询日志可以发现需要优化的SQL，对数据进行查询时，避免like、null等索引失效的情况。对数据进行更新时，避免范围较大的间隙锁。
* MySQL的主从复制支持：异步复制、半同步复制和组复制（MGR）。

6) 分库分表

* 分库分表的常见方式：水平拆分、垂直拆分。
* 分库分表相关的框架和中间件：Apache ShardingSphere-JDBC。
* 分布式事务：强一致性事务：XA；弱一致性事务：TCC、SAGA、AT。

7) RPC 和微服务

* RPC的原理：代理、序列化/反序列化、网络传输。
* 如何自己设计和实现一个RPC框架。
* Dubbo的主要功能和框架设计。

8) 分布式缓存

空

9) 分布式消息队列

* 消息队列的四大特征：异步通信、系统解耦、削峰填谷、可靠通信。
* 常见的消息协议：STOMP、JMS、AMQP、MQTT。
* 消息中间件到现在一般发展了三代：第一代：ActiveMQ、RabbitMQ 不支持堆积，主要靠内存存储消息；第二代：Kafka、RocketMQ 支持堆积，内存满了会写到磁盘，基于WAL技术。第三代：Apache Pulsar 支持存储和计算的分离，存储节点和对外的服务节点进行分离。
* ActiveMQ简介和入门使用。
* Kafka中Topic和Partition的关系：Topic是逻辑的概念，Partition是才是物理概念；一个Topic可以有多个Partition；多Partition支持水平扩展和并行处理，顺序写入提升吞吐性能。
* Kafka中Partition和Replica的关系：多副本可以避免单节点故障的时候partition的数据丢失。每个partition可以通过副本因子添加多个副本。在创建Topic的时候不仅可以指定Partition数量，还可以指定replica数量。多个副本只有一个是leader。
* Kafka中消费组和消费者的关系：一个Consumer Group内可以有多个Consumer实例。每个Consumer Group都有一个Group Id标识。Consumer Group订阅的主题内的partition只能分给组内的某个Customer实例。这个分区也可以被其他Customer Group消费。
