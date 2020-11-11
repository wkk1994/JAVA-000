package com.wkk.lean.java.thread.homework;

import java.util.concurrent.*;

/**
 * @Description 方式二：通过Callable + 线程池的形式实现线程调用并获取处理结果
 * @Author Wangkunkun
 * @Date 2020/11/11 21:57
 */
public class ModeTwo extends AbstractSum {

    public void printSum(final int num) {
        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<Integer> task = new Callable<Integer>() {
            public Integer call() throws Exception {
                return sum(num);
            }
        };

        Future<Integer> future = executorService.submit(task);
        try {
            Integer result = future.get();
            System.out.println("异步计算结果为：" + result);
            System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        // 然后退出main线程
        executorService.shutdown();
    }
}
