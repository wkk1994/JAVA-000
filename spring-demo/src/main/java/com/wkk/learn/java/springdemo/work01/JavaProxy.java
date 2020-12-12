package com.wkk.learn.java.springdemo.work01;

import com.wkk.learn.java.springdemo.work01.dao.SayHello;
import com.wkk.learn.java.springdemo.work01.dao.SayInter;
import com.wkk.learn.java.springdemo.work01.dao.UserInfo;

/**
 * @Description jdk动态代理
 * @Author Wangkunkun
 * @Date 2020/11/16 12:53
 */
public class JavaProxy {

    public static void main(String[] args) {
        SayHello sayHello = new SayHello();
        ProxyFactory proxyFactory = new ProxyFactory(sayHello);
        SayInter sayInterProxyObject = (SayInter)proxyFactory.getProxyObject();
        sayInterProxyObject.say();
        System.out.println("代理前的类：" + sayHello.getClass());
        System.out.println("代理后的类：" + sayInterProxyObject.getClass());
        System.out.println("代理后的类是否是SayHello的子类：" + (sayInterProxyObject instanceof SayHello));

        UserInfo userInfo = new UserInfo();
        ProxyFactory userProxyFactory = new ProxyFactory(userInfo);
        Object proxyObject = userProxyFactory.getProxyObject();
        System.out.println("代理前的类：" + userInfo.getClass());
        System.out.println("代理后的类：" + proxyObject.getClass());
        System.out.println("代理后的类是否是SayHello的子类：" + (proxyObject instanceof UserInfo));
    }
}
