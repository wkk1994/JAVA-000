package com.wkk.learn.java.jvm;

/**
 * @Description 死锁分析测试
 * @Author Wangkunkun
 * @Date 2020/10/26 13:49
 */
public class DeadlockTest {

    private static final String flagA = "flagA";

    private static final String flagB = "flagB";

    public static void main(String[] args) {
        new Thread(()->{
            testA();
        }).start();
        new Thread(()->{
            testB();
        }).start();

    }

    public static void testA() {
        synchronized (flagA) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (flagB) {
                System.out.println("testA end");
            }
        }
    }

    public static void testB() {
        synchronized (flagB) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (flagA) {
                System.out.println("testA end");
            }
        }
    }
}
