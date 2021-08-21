package com.codewithsandy.tutorfinder;

public class Tutor {

    String name;
    String strEmail;
    String qualifications;
    String Experience;
    String amount;
    String Bio;
    String location;
    String contactNumber;
    String state;
    String country;
    String uid;
    String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    float rating;
    int studentsCount;

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }

    public String getStrEmail() {
        return strEmail;
    }

    public void setStrEmail(String strEmail) {
        this.strEmail = strEmail;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getExperience() {
        return Experience;
    }

    public void setExperience(String experience) {
        Experience = experience;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBio() {
        return Bio;
    }

    public void setBio(String bio) {
        Bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    String strPassword;
    String cnfrmPassword;

    public Tutor(){}

    public Tutor(String strEmail, String name, String qualifications, String experience, String amount, String bio, String location, String contactNumber, String state, String country) {
        this.strEmail = strEmail;
        this.name = name;
        this.qualifications = qualifications;
        Experience = experience;
        this.amount = amount;
        Bio = bio;
        this.location = location;
        this.contactNumber = contactNumber;
        this.state = state;
        this.country = country;
        this.rating = 0.0f;
        this.studentsCount = 0;
        this.uid = "";
        this.image = "";
    }


}
