package com.wkk.lean.java.jvm.fina;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2020/11/9 21:25
 */
public class FinalTest {

    public void test() {
        System.out.println(Counter.A);
        System.out.println(Counter.D);

        Counter counter = new Counter();
        System.out.println(counter.B);
        System.out.println(counter.C);
    }
}
