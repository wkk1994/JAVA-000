package com.wkk.lean.java.springdemo.work01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description 获取代理类工厂
 * @Author Wangkunkun
 * @Date 2020/11/16 12:58
 */
public class ProxyFactory {

    private Object object;

    public ProxyFactory(Object object) {
        this.object = object;
    }

    public Object getProxyObject() {
        ClassLoader classLoader = object.getClass().getClassLoader();
        Class<?>[] interfaces = object.getClass().getInterfaces();
        return Proxy.newProxyInstance(classLoader, interfaces, getInvocationHandler());
    }

    /**
     * 方法执行时间统计
     */
    class MethodExecutionTimeInvocationHandler implements InvocationHandler{

        @Override
        public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {
            String methodName = method.getName();
            System.out.println(methodName + "方法开始执行");
            long timeMillis = System.currentTimeMillis();
            try {
                return method.invoke(object, objects);
            } finally {
                System.out.println(methodName + "方法执行用时：" + (System.currentTimeMillis() - timeMillis) + "ms");
            }

        }
    }

    /**
     * 获取代理类
     * @return
     */
    private InvocationHandler getInvocationHandler() {
        return new MethodExecutionTimeInvocationHandler();
    }
}
