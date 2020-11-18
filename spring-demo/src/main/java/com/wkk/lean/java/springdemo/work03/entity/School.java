package com.wkk.lean.java.springdemo.work03.entity;


public class School implements ISchool {
    
    Klass class1;
    
    Student student100;
    
    @Override
    public void ding(){
    
        System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student100);
        
    }
    
}
