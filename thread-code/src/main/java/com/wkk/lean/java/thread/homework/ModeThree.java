package com.wkk.lean.java.thread.homework;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Description 方式三：通过自定义Runnable + Future + 线程池的形式实现线程调用并获取处理结果
 * @Author Wangkunkun
 * @Date 2020/11/11 21:57
 */
public class ModeThree extends AbstractSum {

    public void printSum(final int num) {
        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        MyRunnable myRunnable = new MyRunnable();
        myRunnable.setNum(num);
        Future<?> submit = executorService.submit(myRunnable);
        try {
            submit.get();
            System.out.println("异步计算结果为：" + myRunnable.getSum());
            System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        // 然后退出main线程
        executorService.shutdown();
    }

    /**
     * 自定义runnable
     */
    public static class MyRunnable implements Runnable {

        private int sum;

        private int num;

        public void run() {
            sum = sum(num);
        }

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
