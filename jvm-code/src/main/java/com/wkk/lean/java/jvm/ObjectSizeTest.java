package com.wkk.lean.java.jvm;

import org.openjdk.jol.info.ClassData;
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
        System.out.println("----------");
        System.out.println(GraphLayout.parseInstance(object).toPrintable());
        System.out.println("----------");
        //查看对象总大小
        System.out.println(GraphLayout.parseInstance(object).totalSize());

        synchronized (object){
            //查看对象内部信息
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }



    }

    static class TestA {
        public int i;

    }
}
