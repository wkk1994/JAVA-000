package com.wkk.lean.java.springdemo.work02;

import com.wkk.lean.java.springdemo.work02.entity.School;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description 通过自动配置的方式注入
 * @Author Wangkunkun
 * @Date 2020/11/17 21:39
 */
public class AutowireInjectDemo {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("autowireInjectionApplicationContext.xml");
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
