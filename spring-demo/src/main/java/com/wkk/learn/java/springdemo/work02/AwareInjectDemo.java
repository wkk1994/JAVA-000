package com.wkk.learn.java.springdemo.work02;

import com.wkk.learn.java.springdemo.work02.entity.School;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description 通过BeanFactoryAware的方式注入
 * @Author Wangkunkun
 * @Date 2020/11/17 21:28
 */
public class AwareInjectDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanFactoryInjectionApplicationContext.xml");
        // 启动Spring应用上下文
        context.refresh();
        School schoolByName = (School)context.getBean("school");
        System.out.println(schoolByName);
        School schoolByType = context.getBean(School.class);
        System.out.println(schoolByType);
        // 关闭上下文
        context.close();
    }
}
