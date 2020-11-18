package com.wkk.lean.java.springdemo.work02.entity;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2020/11/17 13:12
 */
public class School{

    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public School() {
    }

    @Override
    public String toString() {
        return "School{" +
                "student=" + student +
                '}';
    }
}
