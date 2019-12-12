package com.example.firebasestudent.model;

import java.util.ArrayList;

public class School {
    private String username;
    private String password;
    private ArrayList<Class> classes;

    public School() {
    }

    public School(String username, String password, ArrayList<Class> classes) {
        this.username = username;
        this.password = password;
        this.classes = classes;
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

    public ArrayList<Class> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Class> classes) {
        this.classes = classes;
    }
}
