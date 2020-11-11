package com.wkk.lean.java.thread;

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
