package com.codewithsandy.tutorfinder;

public class TutorDetails {

    String strEmail;

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
    String name;

    public TutorDetails(){}

    public TutorDetails(String strEmail, String name, String qualifications, String experience, String amount, String bio, String location, String contactNumber, String state, String country) {
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
    }

    String qualifications;
    String Experience;
    String amount;
    String Bio;
    String location;
    String contactNumber;
    String state;
    String country;
}
