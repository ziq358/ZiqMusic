package com.xogrp.john.music.test.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/3 0003.
 */

public class School {
    private String name;
    private List<Student> studentList;

    public List<Student> getStudentList() {
        return studentList;
    }
    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public static class Student{
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

    //data

    public static List<School> getSchoolData(){
        List<School> schoolList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            School school = new School();
            String schoolName = "school - "+i;
            school.setName(schoolName);
            school.setStudentList(getStudent(schoolName));
            schoolList.add(school);
        }
        return schoolList;
    }

    public static List<Student> getStudent(String schoolName){
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Student student = new Student();
            student.setName(schoolName+" student "+i);
            studentList.add(student);
        }
        return studentList;
    }

}
