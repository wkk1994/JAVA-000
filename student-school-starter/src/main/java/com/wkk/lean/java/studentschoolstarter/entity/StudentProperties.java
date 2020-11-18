package com.wkk.lean.java.studentschoolstarter.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2020/11/18 21:40
 */
@ConfigurationProperties(prefix = "custom.student")
public class StudentProperties {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
