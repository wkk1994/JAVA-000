package com.wkk.lean.java.springdemo.work02;

import com.wkk.lean.java.springdemo.work02.entity.School;
import com.wkk.lean.java.springdemo.work02.entity.Student;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description 通过API方式注入
 * @Author Wangkunkun
 * @Date 2020/11/17 13:30
 */
public class ApiInjectionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册bean
        context.registerBeanDefinition("student", getStudentBeanDefinition());
        context.registerBeanDefinition("school", getSchoolBeanDefinition());
        // 启动Spring应用上下文
        context.refresh();

        School schoolByType = context.getBean(School.class);
        System.out.println(schoolByType);
        // 关闭上下文
        context.close();
    }

    private static BeanDefinition getStudentBeanDefinition() {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Student.class);
        beanDefinitionBuilder.addPropertyValue("id", 1001);
        beanDefinitionBuilder.addPropertyValue("name", "hello");
        return beanDefinitionBuilder.getBeanDefinition();
    }

    private static BeanDefinition getSchoolBeanDefinition() {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(School.class);
        beanDefinitionBuilder.addPropertyReference("student", "student");
        return beanDefinitionBuilder.getBeanDefinition();
    }
}
