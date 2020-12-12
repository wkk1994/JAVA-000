package com.wkk.learn.java.studentschoolstarter.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2020/11/18 21:16
 */
@Configuration
@EnableConfigurationProperties(StudentProperties.class)
public class StudentSchoolAutoConfiguration {

    @Autowired
    private StudentProperties studentProperties;

    @Bean
    public Student student() {
        Student student = new Student();
        student.setId(studentProperties.getId());
        student.setName(studentProperties.getName());
        return student;
    }

    @Bean
    public Klass klass(List<Student> studentList) {
        Klass klass = new Klass();
        klass.setStudents(studentList);
        return klass;
    }

    @Bean
    public School school(Klass klass, Student student) {
        School school = new School();
        school.setClass1(klass);
        school.setStudent100(student);
        return school;
    }
}
