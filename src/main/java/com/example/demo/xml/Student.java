package com.example.demo.xml;

public class Student {
    private String id;
    private String firstname;
    private String lastname;
    private String subject;
    private String marks;

    public Student() {

    }

    public Student(String id, String firstname, String lastname, String subject, String marks) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.subject = subject;
        this.marks = marks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }
}
