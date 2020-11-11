package com.wkk.lean.java.thread.homework;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @Description 方式五：通过CompletableFuture实现线程调用并获取处理结果
 * @Author Wangkunkun
 * @Date 2020/11/11 22:08
 */
public class ModeFive extends AbstractSum{


    public void printSum(int num) {
        long start = System.currentTimeMillis();
        Integer join = CompletableFuture.supplyAsync(() -> {
            return sum(num);
        }).join();
        System.out.println("异步计算结果为：" + join);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }
}
