package com.wkk.learn.java.thread;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2020/11/10 21:39
 */
public class VolatileTest {

    private volatile int num;

    public void add() {
        num ++;
    }
}
