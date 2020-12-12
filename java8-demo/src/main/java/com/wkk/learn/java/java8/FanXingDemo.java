package com.wkk.learn.java.java8;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 泛型使用demo
 * @Author Wangkunkun
 * @Date 2020/11/22 15:43
 */
public class FanXingDemo {
    public static void main(String[] args) {
        // 泛型多继承示例
        TestA<? extends Integer> fanxingTest = () -> {
            System.out.println("testA");
        };

        TestB<? super Integer > testB = new TestB() {
            @Override
            public void test(Object o) {
                System.out.println(o);
            }
        };
        testB.test(1);
        //testB.test(8L);
    }

    interface TestA <T extends Serializable&Comparable>{
        void test();
    }

    interface TestB <T>{
        void test(T t);
    }
}
