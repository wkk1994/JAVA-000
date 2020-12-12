package com.wkk.learn.java.springdemo.work01.dao;

/**
 * @Description say Hello
 * @Author Wangkunkun
 * @Date 2020/11/16 12:57
 */
public class SayHello implements SayInter {

    @Override
    public void say() {
        System.out.println("Hello");
    }
}
