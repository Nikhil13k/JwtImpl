package com.studentDemo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name="id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "marks")
    private int marks;

    public Student() {
    }

    public Student(int id, String username, int marks) {
        this.id = id;
        this.username = username;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", marks=" + marks +
                '}';
    }
}
