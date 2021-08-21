package com.codewithsandy.tutorfinder;

public class Student {
    String name;
    String email;
    String grade;
    public Student(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClg_name() {
        return clg_name;
    }

    public void setClg_name(String clg_name) {
        this.clg_name = clg_name;
    }

    public String getLocatin() {
        return locatin;
    }

    public void setLocatin(String locatin) {
        this.locatin = locatin;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCntry() {
        return cntry;
    }

    public void setCntry(String cntry) {
        this.cntry = cntry;
    }

    String clg_name;
    String locatin;
    String contact;
    String state;
    String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    String image;

    public Student(String name, String email, String grade, String clg_name, String locatin, String contact, String state, String cntry) {
        this.name = name;
        this.email = email;
        this.grade = grade;
        this.clg_name = clg_name;
        this.locatin = locatin;
        this.contact = contact;
        this.state = state;
        this.cntry = cntry;
        this.uid = "";
        this.image = "";
    }

    String cntry;

}
