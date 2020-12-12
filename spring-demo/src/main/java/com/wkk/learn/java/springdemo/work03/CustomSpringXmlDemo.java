package com.wkk.learn.java.springdemo.work03;

import com.wkk.learn.java.springdemo.work03.entity.Klass;
import com.wkk.learn.java.springdemo.work03.entity.Student;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * @Description 自定义spring xml
 * @Author Wangkunkun
 * @Date 2020/11/17 21:50
 */
public class CustomSpringXmlDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("customXmlApplicationContext.xml");
        // 启动Spring应用上下文
        context.refresh();
        Student student = context.getBean(Student.class);
        System.out.println(student);
        Klass klass = context.getBean(Klass.class);
        System.out.println(klass);
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
        System.out.println("------xml配置的-----");
        klass = (Klass) context.getBean("klass");
        System.out.println(klass);
        // 关闭上下文
        context.close();
    }
}
