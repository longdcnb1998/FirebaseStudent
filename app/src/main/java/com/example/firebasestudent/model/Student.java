package com.example.firebasestudent.model;

import java.util.ArrayList;

public class Student {

    private int inA,inM;
    private String name;
    private ArrayList<Notice> notice;
    private String username;
    private  String password;


    public Student() {
    }

    public Student(int inA, int inM, String name, ArrayList<Notice> notice, String username, String password) {
        this.inA = inA;
        this.inM = inM;
        this.name = name;
        this.notice = notice;
        this.username = username;
        this.password = password;
    }

    public int getInA() {
        return inA;
    }

    public void setInA(int inA) {
        this.inA = inA;
    }

    public int getInM() {
        return inM;
    }

    public void setInM(int inM) {
        this.inM = inM;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Notice> getNotice() {
        return notice;
    }

    public void setNotice(ArrayList<Notice> notice) {
        this.notice = notice;
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
