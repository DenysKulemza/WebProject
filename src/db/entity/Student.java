package db.entity;

import java.io.Serializable;

public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private String surName;
    private String name;
    private String email;
    private int id;

    public Student(String name, String surName, String email){
        this.name = name;
        this.email = email;
        this.surName = surName;
    }
    public Student(){}

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
    public String getSurName() {
        return surName;
    }
}
