# 学习笔记

## 第 24 课作业实践

### 1、（必做）搭建ActiveMQ服务，基于JMS，写代码分别实现对于queue和topic的消息 生产和消费，代码提交到github。

代码示例：[ActiveMQDemo.java](https://github.com/wkk1994/JAVA-000/blob/main/activemq-demo/src/main/java/com/wkk/learn/java/activemq/demo/ActiveMQDemo.java)

## 第 25 课作业实践

### 1、（必做）搭建一个3节点Kafka集群，测试功能和性能；实现spring kafka下对kafka集群 的操作，将代码提交到github。

#### kafka集群性能测试

* 创建队列：bin/kafka-topics.sh --zookeeper localhost:2181 --create --topic test1 --partitions 3 --replication-factor 2

* 生产者压力测试：bin/kafka-producer-perf-test.sh --topic test1 --num-records 1000000 --record-size 1000 --throughput -1 --producer-props bootstrap.servers=localhost:9001,localhost:9002,localhost:9003

  ```text
    684545 records sent, 136909.0 records/sec (130.57 MB/sec), 216.2 ms avg latency, 392.0 ms max latency.
    1000000 records sent, 152835.090937 records/sec (145.75 MB/sec), 200.14 ms avg latency, 392.00 ms max latency, 248 ms 50th, 324 ms 95th, 357 ms 99th, 383 ms 99.9th.
  ```

* 消费者压力测试：bin/kafka-consumer-perf-test.sh --bootstrap-server localhost:9002,localhost:9001,localhost:9003 --topic test1 --fetch-size 1048576 --messages 10000000 | jq -R .|jq -sr 'map(./",")|transpose|map(join(": "))[]'

  ```text
   start.time: 2021-01-14 12:39:20:068
   end.time:  2021-01-14 12:39:34:289
   data.consumed.in.MB:  9537.1418
   MB.sec:  670.6379
   data.consumed.in.nMsg:  10000422
   nMsg.sec:  703215.1044
   rebalance.time.ms:  1610599160416
   fetch.time.ms:  -1610599146195
   fetch.MB.sec:  -0.0000
   fetch.nMsg.sec:  -0.0062
  ```

#### spring-boot下kafka简单操作

代码示例：[KafkaDemoApplication.java](https://github.com/wkk1994/JAVA-000/blob/main/spring-boot-kafka-demo/src/main/java/com/wkk/learn/java/springboot/kafka/demo/KafkaDemoApplication.java)