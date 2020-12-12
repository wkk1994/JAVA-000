package com.wkk.learn.java.thread.homework;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @Description 方式一：通过Callable + FutureTask + Thread实现线程调用并获取处理结果
 * @Author Wangkunkun
 * @Date 2020/11/11 21:57
 */
public class ModeOne extends AbstractSum {

    public void printSum(final int num) {
        // 在这里创建一个线程或线程池
        long start = System.currentTimeMillis();
        Callable<Integer> task = new Callable<Integer>() {
            public Integer call() throws Exception {
                return sum(num);
            }
        };
        FutureTask<Integer> futureTask = new FutureTask(task);
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            Integer result = futureTask.get();
            System.out.println("异步计算结果为：" + result);
            System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 然后退出main线程
    }
}
