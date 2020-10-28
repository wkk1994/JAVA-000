package com.wkk.lean.java.jvm;

import org.openjdk.jol.info.GraphLayout;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Description 演示GC日志生成与解读
 * @Author Wangkunkun
 * @Date 2020/10/22 12:30
 */
public class GCLogAnalysis {

    private static Random random = new Random();

    private static Object[] cachedGarbage = new Object[2000];

    public static void main(String[] args) throws InterruptedException {
        // 当前毫秒时间戳
        long startMillis = System.currentTimeMillis();
        // 持续运行毫秒数; 可根据需要进行修改
        long timeoutMillis = TimeUnit.SECONDS.toMillis(10);
        // 结束时间戳
        long endMillis = startMillis + timeoutMillis;
        LongAdder counter = new LongAdder();
        System.out.println("正在执行...");
        // 缓存一部分对象; 进入老年代
        int cacheSize = 2000;

        // 在此时间范围内,持续循环
        int i = 0;
        while (System.currentTimeMillis() < endMillis) {
            // 生成垃圾对象
            //Object garbage = generateGarbage(100*1024);
            Object garbage = generateGarbage(i++);
            counter.increment();
            //int randomIndex = random.nextInt();
            int randomIndex = random.nextInt(i);;
            if (true) {
                cachedGarbage[i % cacheSize] = garbage;
            }
        }
        System.out.println(GraphLayout.parseInstance(cachedGarbage).totalSize());
        System.out.println("执行结束!共生成对象次数:" + counter.longValue());
        //Thread.sleep(30 * 1000);
    }

    // 生成对象 36
    private static Object generateGarbage(int max) {
        //int randomSize = random.nextInt(max);
        int randomSize = max;
        int type = randomSize % 4;
        Object result = null;
        switch (type) {
            case 0:
                result = new int[randomSize];
                break;
            case 1:
                result = new byte[randomSize];
                break;
            case 2:
                result = new double[randomSize];
                break;
            default:
                StringBuilder builder = new StringBuilder();

                String randomString = "randomString‐Anything";
                while (builder.length() < randomSize) {
                    builder.append(randomString);
                    builder.append(max);
                    builder.append(randomSize);
                }
                result = builder.toString();
                break;
        }
        return result;
    }
}
