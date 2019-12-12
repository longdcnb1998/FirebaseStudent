package com.example.firebasestudent.model;

import java.util.ArrayList;

public class Class {
    private String className;
    private ArrayList<Notice> notice;
    public ArrayList<Student> students;
    private String teacher;
    private String username;
    private String password;

    public Class() {
    }

    public Class(String className, ArrayList<Notice> notice, ArrayList<Student> students, String teacher, String username, String password) {
        this.className = className;
        this.notice = notice;
        this.students = students;
        this.teacher = teacher;
        this.username = username;
        this.password = password;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public ArrayList<Notice> getNotice() {
        return notice;
    }

    public void setNotice(ArrayList<Notice> notice) {
        this.notice = notice;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
