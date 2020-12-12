package com.wkk.learn.java.jvm.classloader;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2020/10/19 22:09
 */
public class VolatileTest {

    boolean isStop = false;

    public void test() {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                isStop = true;
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                while (!isStop) {
                    System.out.println("1");
                }
            }
        };
        t2.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.start();
    }

    public static void main(String args[]) throws InterruptedException {
        new VolatileTest().test();
    }
}
