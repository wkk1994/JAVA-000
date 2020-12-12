package com.wkk.learn.java.jvm.classloader;

/**
 * @Description helloClassLoader
 * @Author Wangkunkun
 * @Date 2020/10/18 19:40
 */
public class HelloClassLoader {

    static {
        System.out.println("Hello ClassLoader!");
    }

    public HelloClassLoader() {
        HelloRelyClassLoader relyClassLoader = new HelloRelyClassLoader();
    }
}
