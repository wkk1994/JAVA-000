package com.wkk.learn.java.thread.homework;

/**
 * @Description 计算斐波那契数
 * @Author Wangkunkun
 * @Date 2020/11/11 21:55
 */
public abstract class AbstractSum {

    abstract void printSum(int num);

    protected static int sum(int num) {
        return fibo(num);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
