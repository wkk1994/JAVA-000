package com.wkk.lean.java.springdemo.work02;

import com.wkk.lean.java.springdemo.work02.entity.School;
import com.wkk.lean.java.springdemo.work02.entity.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * @Description 通过注解依赖注入
 * @Author Wangkunkun
 * @Date 2020/11/17 13:13
 */
public class AnnotationInjectionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotationInjectionDemo.class);
        // 启动Spring应用上下文
        context.refresh();
        School schoolByType = context.getBean(School.class);
        System.out.println(schoolByType);
        // 关闭上下文
        context.close();
    }


    @Bean
    @Primary
    private Student studentTwo() {
        return new Student(1001, "studentTwo");
    }

    @Bean
    private Student student() {
        return new Student(1001, "hello");
    }

    @Bean
    private School school(Student student) {
        School school = new School();
        school.setStudent(student);
        return school;
    }
}
