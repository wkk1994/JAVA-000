package com.wkk.learn.java.springdemo.work04;

import com.wkk.learn.java.studentschoolstarter.entity.School;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StudentSchoolStarterApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(StudentSchoolStarterApplication.class).web(WebApplicationType.NONE)
                .run(args);
        School bean = applicationContext.getBean(School.class);
        System.out.println(bean);
        applicationContext.close();
    }

}
