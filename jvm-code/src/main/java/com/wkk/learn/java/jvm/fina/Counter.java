package com.wkk.learn.java.jvm.fina;

/**
 * @Description java的常量替换
 * @Author Wangkunkun
 * @Date 2020/11/9 21:22
 */
public class Counter {

    public static final String A = "AAAA";


    public final String B = "BBBB";

    public String C = "CCCC";

    public static final String D;

    static {
        D = "DDDD";
    }
}
