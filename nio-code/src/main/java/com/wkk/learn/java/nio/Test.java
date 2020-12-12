package com.wkk.learn.java.nio;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2020/10/31 23:21
 */
public class Test {

    private final static int A;
    private static int B = 20;

    static {
       A = 30;
    }
    public static void main(String[] args) {
        System.out.println(Test.A);
        System.out.println(Test.B);
    }
}
