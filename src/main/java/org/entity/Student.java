package org.entity;

import java.util.Objects;

public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private int gender;
    private String mobileNumber;
    private String dateOfBirth;
    private String subject;
    private int hobby;
    private String pictureName;
    private String currentAddress;
    private String state;
    private String city;
    private String stateAndCity;

    public Student() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getHobby() {
        return hobby;
    }

    public void setHobby(int hobby) {
        this.hobby = hobby;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateAndCity() {
        return stateAndCity;
    }

    public void setStateAndCity(String stateAndCity) {
        this.stateAndCity = stateAndCity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (gender != student.gender) return false;
        if (hobby != student.hobby) return false;
        if (!firstName.equals(student.firstName)) return false;
        if (!lastName.equals(student.lastName)) return false;
        if (!Objects.equals(email, student.email)) return false;
        if (!mobileNumber.equals(student.mobileNumber)) return false;
        if (!dateOfBirth.equals(student.dateOfBirth)) return false;
        if (!Objects.equals(subject, student.subject)) return false;
        if (!Objects.equals(pictureName, student.pictureName)) return false;
        if (!Objects.equals(currentAddress, student.currentAddress))
            return false;
        return Objects.equals(stateAndCity, student.stateAndCity);
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", subject='" + subject + '\'' +
                ", hobby=" + hobby +
                ", pictureName='" + pictureName + '\'' +
                ", currentAddress='" + currentAddress + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", stateAndCity='" + stateAndCity + '\'' +
                '}';
    }
}
