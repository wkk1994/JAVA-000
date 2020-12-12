# 学习笔记

## 作业一：使用 GCLogAnalysis.java 自己演练一遍串行/并行/CMS/G1的案例。

* Xmn4g运行情况


>  固定随机数（从0递增）和固定时间（10s）生成 

|GC收集器|生成对象数|GC次数|Minor GC/Full GC|GC总时间|GC暂停时间(平均暂停时间)|用户线程停顿时间|用户线程停顿时间占总时间百分比|
|--|--|--|--|--|--|--|--|
|Serial GC|138087|43|33/10 |4 sec 590 ms|4 sec 590 ms(107 ms)|4.558 secs|50.646 %|
|Parallel GC|113707|55|49/6 |4 sec 750 ms|4 sec 750 ms(86.4 ms)|4.76 secs|52.888 %|
|CMS GC|121041|68|42/26|5 sec 370 ms|3 sec 740 ms(55.0 ms)|5.004 secs|50.044 %|
|G1 GC|123140|80|43/37|3 sec 873 ms|3 sec 810 ms(47.6 ms)|3.772 secs|37.725 %|

**总结：**

Serial收集器：
由GC总时间和GC暂停时间可以看出Serial收集器属于使用的是单线程，STW的垃圾收集算法，但是在这四个收集器中，它生成对象的数量最大，而且吞吐量不是最低的。

Parallel收集器：
Parallel收集器的GC总时间和GC暂停时间也是相等的，可以看出它也是STW的收集算法，但是根据之前的定义它使用的是多线程进行垃圾收集，而且老年代使用的是标记-清除-整理算法，按推理来说它的GC时间相比于Serial收集器应该更小，而且吞吐量应该更大，但是实验数据相反。分析原因的话只可能是环境因素了。

CMS收集器：
从测试结果中GC总时间和GC暂停时间不相等的，可以证明CMS收集器使用的是并发，STW收集算法。

G1收集器：
可以看出G1收集器使用的也是并发、STW的收集算法，并且相比于上面的三种，它的吞吐量最高，GC时间最短，用户线程停顿时间也最小。

## 作业二：使用压测工具（wrk或sb），演练gateway-server-0.0.1-SNAPSHOT.jar 示例

wrk:

```text
~ % wrk -c 40 -d 30s http://127.0.0.1:8088/api/hello
Running 30s test @ http://127.0.0.1:8088/api/hello
  2 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     1.81ms    8.92ms 179.54ms   97.02%
    Req/Sec    33.81k    10.60k   49.24k    79.13%
  1628600 requests in 30.07s, 194.44MB read
Requests/sec:  54153.34
Transfer/sec:      6.47MB
```

## 作业三：运行课上的例子，以及 Netty 的例子，分析相关现象

## 作业四：写一段代码，使用 HttpClient 或 OkHttp 访问http://localhost:8801，代码提交到Github。

代码示例：[HttpClientDemo](https://github.com/wkk1994/JAVA-000/blob/main/nio-code/src/main/java/com/wkk/learn/java/nio/HttpClientDemo.java)