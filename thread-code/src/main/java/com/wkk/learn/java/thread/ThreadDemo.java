package com.wkk.learn.java.thread;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2020/10/31 17:48
 */
public class ThreadDemo {

    public static void main(String[] args) {
        Runnable task = new Runnable() {
            public void run() {
                try {
                    Thread.sleep(50 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread thread = Thread.currentThread();
                System.out.println("当前线程" + thread.getName());

            }
        };
        Thread thread = new Thread(task);
        thread.setName("test-thread-1");
        thread.setDaemon(true);
        thread.start();
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
