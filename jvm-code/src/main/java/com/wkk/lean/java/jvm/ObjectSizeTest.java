package com.wkk.lean.java.jvm;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import sun.instrument.InstrumentationImpl;

import java.lang.instrument.Instrumentation;

/**
 * @Description 查看对象内部结构
 * @Author Wangkunkun
 * @Date 2020/10/25 22:04
 */
public class ObjectSizeTest {

    public static void main(String[] args) {
        Object object = new Object();
        //查看对象内部信息
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
        System.out.println(GraphLayout.parseInstance(object).toPrintable());
        //查看对象总大小
        System.out.println(GraphLayout.parseInstance(object).totalSize());

        TestA testA = new TestA();
        //查看对象内部信息
        System.out.println(ClassLayout.parseInstance(testA).toPrintable());
        System.out.println(GraphLayout.parseInstance(testA).toPrintable());
        //查看对象总大小
        System.out.println(GraphLayout.parseInstance(testA).totalSize());
        Integer integer = new Integer(1);
        System.out.println(GraphLayout.parseInstance(integer).totalSize());
        Long long1 = new Long(1);
        System.out.println(GraphLayout.parseInstance(long1).totalSize());
        int[][] intA = new int[128][2];
        System.out.println(GraphLayout.parseInstance(intA).totalSize());
        int[][] intB = new int[2][128];
        System.out.println(GraphLayout.parseInstance(intB).totalSize());

        String str = new String("12345");
        System.out.println(GraphLayout.parseInstance(str).totalSize());


    }

    static class TestA {
        public int i;

    }
}
