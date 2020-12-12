package com.wkk.learn.java.thread.homework;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @Description 方式四：通过ForkJoin实现线程调用并获取处理结果
 * @Author Wangkunkun
 * @Date 2020/11/11 22:08
 */
public class ModeFour extends AbstractSum{


    public void printSum(int num) {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        MyRecursiveTask myRecursiveTask = new MyRecursiveTask(num);
        ForkJoinTask<Integer> submit = forkJoinPool.submit(myRecursiveTask);
        try {
            Integer result = submit.get();
            System.out.println("异步计算结果为：" + result);
            System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
        forkJoinPool.shutdown();
    }

    static class MyRecursiveTask extends RecursiveTask<Integer> {

        private int num;

        public MyRecursiveTask(int num) {
            this.num = num;
        }

        protected Integer compute() {
            if(num < 2) {
                return 1;
            }else {
                MyRecursiveTask myRecursiveTask1 = new MyRecursiveTask(num - 1);
                MyRecursiveTask myRecursiveTask2 = new MyRecursiveTask(num - 2);
                myRecursiveTask1.fork();
                myRecursiveTask2.fork();
                return myRecursiveTask1.join() + myRecursiveTask2.join();
            }
        }
    }

}
